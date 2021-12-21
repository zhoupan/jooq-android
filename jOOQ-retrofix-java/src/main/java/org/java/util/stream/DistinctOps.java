/* 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.java.util.stream;

import java.util.AbstractSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.java.util.Objects;
import org.java.util.Spliterator;
import org.java.util.concurrent.ForkJoinPool;
import org.java.util.function.IntFunction;

/**
 * Factory methods for transforming streams into duplicate-free streams, using {@link
 * Object#equals(Object)} to determine equality.
 *
 * @since 1.8
 */
final class DistinctOps {

  private DistinctOps() {}

  /**
   * Appends a "distinct" operation to the provided stream, and returns the new stream.
   *
   * @param <T> the type of both input and output elements
   * @param upstream a reference stream with element type T
   * @return the new stream
   */
  static <T> ReferencePipeline<T, T> makeRef(AbstractPipeline<?, T, ?> upstream) {
    return new ReferencePipeline.StatefulOp<T, T>(
        upstream, StreamShape.REFERENCE, StreamOpFlag.IS_DISTINCT | StreamOpFlag.NOT_SIZED) {

      <P_IN> Node<T> reduce(PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
        // If the stream is SORTED then it should also be ORDERED so the following will also
        // preserve the sort order
        TerminalOp<T, LinkedHashSet<T>> reduceOp =
            ReduceOps.<T, LinkedHashSet<T>>makeRef(
                LinkedHashSet::new, LinkedHashSet::add, LinkedHashSet::addAll);
        return Nodes.node(reduceOp.evaluateParallel(helper, spliterator));
      }

      @Override
      <P_IN> Node<T> opEvaluateParallel(
          PipelineHelper<T> helper, Spliterator<P_IN> spliterator, IntFunction<T[]> generator) {
        if (StreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
          // No-op
          return helper.evaluate(spliterator, false, generator);
        } else if (StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
          return reduce(helper, spliterator);
        } else {
          // Holder of null state since ConcurrentHashMap does not support null values
          AtomicBoolean seenNull = new AtomicBoolean();
          // Pre-size map to reduce concurrent re-sizes
          ConcurrentMap<T, Boolean> map =
              new ConcurrentHashMap<T, Boolean>(
                  512, 0.75f, ForkJoinPool.getCommonPoolParallelism() + 1);
          TerminalOp<T, Void> forEachOp =
              ForEachOps.makeRef(
                  t -> {
                    if (t == null) {
                      seenNull.set(true);
                    } else {
                      map.putIfAbsent(t, Boolean.TRUE);
                    }
                  },
                  false);
          forEachOp.evaluateParallel(helper, spliterator);
          // If null has been seen then copy the key set into a HashSet that supports null values
          // and add null
          Set<T> keys = map.keySet();
          if (seenNull.get()) {
            int size = keys.size();
            if (size >= 127) {
              // a more efficient set-union view, rather than copying
              keys = new KeysAndNullSet<>(keys, size);
            } else {
              HashSet<T> tmp = new HashSet<>(Math.max((int) ((size + 1) / 0.75f) + 1, 16));
              tmp.addAll(keys);
              tmp.add(null);
              keys = tmp;
            }
          }
          return Nodes.node(keys);
        }
      }

      @Override
      <P_IN> Spliterator<T> opEvaluateParallelLazy(
          PipelineHelper<T> helper, Spliterator<P_IN> spliterator) {
        if (StreamOpFlag.DISTINCT.isKnown(helper.getStreamAndOpFlags())) {
          // No-op
          return helper.wrapSpliterator(spliterator);
        } else if (StreamOpFlag.ORDERED.isKnown(helper.getStreamAndOpFlags())) {
          // Not lazy, barrier required to preserve order
          return reduce(helper, spliterator).spliterator();
        } else {
          // Lazy
          return new StreamSpliterators.DistinctSpliterator<>(helper.wrapSpliterator(spliterator));
        }
      }

      @Override
      Sink<T> opWrapSink(int flags, Sink<T> sink) {
        Objects.requireNonNull(sink);
        if (StreamOpFlag.DISTINCT.isKnown(flags)) {
          return sink;
        } else if (StreamOpFlag.SORTED.isKnown(flags)) {
          return new Sink.ChainedReference<T, T>(sink) {

            boolean seenNull;

            T lastSeen;

            @Override
            public void begin(long size) {
              seenNull = false;
              lastSeen = null;
              downstream.begin(-1);
            }

            @Override
            public void end() {
              seenNull = false;
              lastSeen = null;
              downstream.end();
            }

            @Override
            public void accept(T t) {
              if (t == null) {
                if (!seenNull) {
                  seenNull = true;
                  downstream.accept(lastSeen = null);
                }
              } else if (lastSeen == null || !t.equals(lastSeen)) {
                downstream.accept(lastSeen = t);
              }
            }
          };
        } else {
          return new Sink.ChainedReference<T, T>(sink) {

            Set<T> seen;

            @Override
            public void begin(long size) {
              seen = new HashSet<>();
              downstream.begin(-1);
            }

            @Override
            public void end() {
              seen = null;
              downstream.end();
            }

            @Override
            public void accept(T t) {
              if (seen.add(t)) {
                downstream.accept(t);
              }
            }
          };
        }
      }
    };
  }

  static final class KeysAndNullSet<E> extends AbstractSet<E> {

    final Set<E> keys;

    final int size;

    KeysAndNullSet(Set<E> keys, int size) {
      this.keys = keys;
      this.size = size + 1;
    }

    @Override
    public Iterator<E> iterator() {
      return new Iterator<E>() {

        boolean nullDelivered = false;

        Iterator<E> it = keys.iterator();

        @Override
        public boolean hasNext() {
          if (!nullDelivered) {
            return true;
          }
          return it.hasNext();
        }

        @Override
        public E next() {
          if (!nullDelivered) {
            nullDelivered = true;
            return null;
          }
          return it.next();
        }

        @Override
        public void remove() {
          throw new UnsupportedOperationException();
        }
      };
    }

    @Override
    public int size() {
      return size;
    }
  }
}
