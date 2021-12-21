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

import org.java.util.Objects;
import org.java.util.Spliterator;
import org.java.util.Spliterators;
import org.java.util.function.DoubleConsumer;
import org.java.util.function.DoublePredicate;
import org.java.util.function.DoubleSupplier;
import org.java.util.function.DoubleUnaryOperator;
import org.java.util.stream.DoubleStream.Builder;

/**
 * A place for the implementations of the new Java 8/9 static interface methods in the {@link
 * DoubleStream} interface.
 */
public final class DoubleStreams {

  // Static factories
  /**
   * Returns a builder for a {@code DoubleStream}.
   *
   * @return a stream builder
   */
  public static Builder builder() {
    return new Streams.DoubleStreamBuilderImpl();
  }

  /**
   * Returns an empty sequential {@code DoubleStream}.
   *
   * @return an empty sequential stream
   */
  public static DoubleStream empty() {
    return StreamSupport.doubleStream(Spliterators.emptyDoubleSpliterator(), false);
  }

  /**
   * Returns a sequential {@code DoubleStream} containing a single element.
   *
   * @param t the single element
   * @return a singleton sequential stream
   */
  public static DoubleStream of(double t) {
    return StreamSupport.doubleStream(new Streams.DoubleStreamBuilderImpl(t), false);
  }

  /**
   * Returns a sequential ordered stream whose elements are the specified values.
   *
   * @param values the elements of the new stream
   * @return the new stream
   */
  public static DoubleStream of(double... values) {
    return org.java.util.J8Arrays.stream(values);
  }

  /**
   * Returns an infinite sequential ordered {@code DoubleStream} produced by iterative application
   * of a function {@code f} to an initial element {@code seed}, producing a {@code Stream}
   * consisting of {@code seed}, {@code f(seed)}, {@code f(f(seed))}, etc.
   *
   * <p>The first element (position {@code 0}) in the {@code DoubleStream} will be the provided
   * {@code seed}. For {@code n > 0}, the element at position {@code n}, will be the result of
   * applying the function {@code f} to the element at position {@code n - 1}.
   *
   * <p>The action of applying {@code f} for one element <a
   * href="../concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a> the action
   * of applying {@code f} for subsequent elements. For any given element the action may be
   * performed in whatever thread the library chooses.
   *
   * @param seed the initial element
   * @param f a function to be applied to the previous element to produce a new element
   * @return a new sequential {@code DoubleStream}
   */
  public static DoubleStream iterate(double seed, DoubleUnaryOperator f) {
    Objects.requireNonNull(f);
    Spliterator.OfDouble spliterator =
        new Spliterators.AbstractDoubleSpliterator(
            Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL) {

          double prev;

          boolean started;

          @Override
          public boolean tryAdvance(DoubleConsumer action) {
            Objects.requireNonNull(action);
            double t;
            if (started) {
              t = f.applyAsDouble(prev);
            } else {
              t = seed;
              started = true;
            }
            action.accept(prev = t);
            return true;
          }
        };
    return StreamSupport.doubleStream(spliterator, false);
  }

  /**
   * Returns a sequential ordered {@code DoubleStream} produced by iterative application of the
   * given {@code next} function to an initial element, conditioned on satisfying the given {@code
   * hasNext} predicate. The stream terminates as soon as the {@code hasNext} predicate returns
   * false.
   *
   * <p>{@code DoubleStreams.iterate} should produce the same sequence of elements as produced by
   * the corresponding for-loop:
   *
   * <pre>{@code
   * for (double index=seed; hasNext.test(index); index = next.applyAsDouble(index)) {
   *     ...
   * }
   * }</pre>
   *
   * <p>The resulting sequence may be empty if the {@code hasNext} predicate does not hold on the
   * seed value. Otherwise the first element will be the supplied {@code seed} value, the next
   * element (if present) will be the result of applying the {@code next} function to the {@code
   * seed} value, and so on iteratively until the {@code hasNext} predicate indicates that the
   * stream should terminate.
   *
   * <p>The action of applying the {@code hasNext} predicate to an element <a
   * href="../concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a> the action
   * of applying the {@code next} function to that element. The action of applying the {@code next}
   * function for one element <i>happens-before</i> the action of applying the {@code hasNext}
   * predicate for subsequent elements. For any given element an action may be performed in whatever
   * thread the library chooses.
   *
   * @param seed the initial element
   * @param hasNext a predicate to apply to elements to determine when the stream must terminate
   * @param next a function to be applied to the previous element to produce a new element
   * @return a new sequential {@code DoubleStream}
   * @since 9
   */
  public static DoubleStream iterate(
      double seed, DoublePredicate hasNext, DoubleUnaryOperator next) {
    Objects.requireNonNull(next);
    Objects.requireNonNull(hasNext);
    Spliterator.OfDouble spliterator =
        new Spliterators.AbstractDoubleSpliterator(
            Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL) {

          double prev;

          boolean started, finished;

          @Override
          public boolean tryAdvance(DoubleConsumer action) {
            Objects.requireNonNull(action);
            if (finished) {
              return false;
            }
            double t;
            if (started) {
              t = next.applyAsDouble(prev);
            } else {
              t = seed;
              started = true;
            }
            if (!hasNext.test(t)) {
              finished = true;
              return false;
            }
            action.accept(prev = t);
            return true;
          }

          @Override
          public void forEachRemaining(DoubleConsumer action) {
            Objects.requireNonNull(action);
            if (finished) {
              return;
            }
            finished = true;
            double t = started ? next.applyAsDouble(prev) : seed;
            while (hasNext.test(t)) {
              action.accept(t);
              t = next.applyAsDouble(t);
            }
          }
        };
    return StreamSupport.doubleStream(spliterator, false);
  }

  /**
   * Returns an infinite sequential unordered stream where each element is generated by the provided
   * {@code DoubleSupplier}. This is suitable for generating constant streams, streams of random
   * elements, etc.
   *
   * @param s the {@code DoubleSupplier} for generated elements
   * @return a new infinite sequential unordered {@code DoubleStream}
   */
  public static DoubleStream generate(DoubleSupplier s) {
    Objects.requireNonNull(s);
    return StreamSupport.doubleStream(
        new StreamSpliterators.InfiniteSupplyingSpliterator.OfDouble(Long.MAX_VALUE, s), false);
  }

  /**
   * Creates a lazily concatenated stream whose elements are all the elements of the first stream
   * followed by all the elements of the second stream. The resulting stream is ordered if both of
   * the input streams are ordered, and parallel if either of the input streams is parallel. When
   * the resulting stream is closed, the close handlers for both input streams are invoked.
   *
   * <p>This method operates on the two input streams and binds each stream to its source. As a
   * result subsequent modifications to an input stream source may not be reflected in the
   * concatenated stream result.
   *
   * <p><b>Implementation Note:</b><br>
   * Use caution when constructing streams from repeated concatenation. Accessing an element of a
   * deeply concatenated stream can result in deep call chains, or even {@code StackOverflowError}.
   *
   * <p>Subsequent changes to the sequential/parallel execution mode of the returned stream are not
   * guaranteed to be propagated to the input streams.
   *
   * <p><b>API Note:</b><br>
   * To preserve optimization opportunities this method binds each stream to its source and accepts
   * only two streams as parameters. For example, the exact size of the concatenated stream source
   * can be computed if the exact size of each input stream source is known. To concatenate more
   * streams without binding, or without nested calls to this method, try creating a stream of
   * streams and flat-mapping with the identity function, for example:
   *
   * <pre>{@code
   * DoubleStream concat = RefStreams.of(s1, s2, s3, s4).flatMapToDouble(s -> s);
   * }</pre>
   *
   * @param a the first stream
   * @param b the second stream
   * @return the concatenation of the two input streams
   */
  public static DoubleStream concat(DoubleStream a, DoubleStream b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Spliterator.OfDouble split =
        new Streams.ConcatSpliterator.OfDouble(a.spliterator(), b.spliterator());
    DoubleStream stream = StreamSupport.doubleStream(split, a.isParallel() || b.isParallel());
    return stream.onClose(Streams.composedClose(a, b));
  }

  private DoubleStreams() {}
}
