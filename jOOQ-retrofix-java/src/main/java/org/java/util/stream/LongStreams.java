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

import org.java.lang.Longs;
import org.java.util.Objects;
import org.java.util.Spliterator;
import org.java.util.Spliterators;
import org.java.util.function.LongConsumer;
import org.java.util.function.LongPredicate;
import org.java.util.function.LongSupplier;
import org.java.util.function.LongUnaryOperator;
import org.java.util.stream.LongStream.Builder;

/**
 * A place for the implementations of the new Java 8/9 static interface methods in the {@link
 * LongStream} interface.
 */
public final class LongStreams {

  // Static factories
  /**
   * Returns a builder for a {@code LongStream}.
   *
   * @return a stream builder
   */
  public static Builder builder() {
    return new Streams.LongStreamBuilderImpl();
  }

  /**
   * Returns an empty sequential {@code LongStream}.
   *
   * @return an empty sequential stream
   */
  public static LongStream empty() {
    return StreamSupport.longStream(Spliterators.emptyLongSpliterator(), false);
  }

  /**
   * Returns a sequential {@code LongStream} containing a single element.
   *
   * @param t the single element
   * @return a singleton sequential stream
   */
  public static LongStream of(long t) {
    return StreamSupport.longStream(new Streams.LongStreamBuilderImpl(t), false);
  }

  /**
   * Returns a sequential ordered stream whose elements are the specified values.
   *
   * @param values the elements of the new stream
   * @return the new stream
   */
  public static LongStream of(long... values) {
    return org.java.util.J8Arrays.stream(values);
  }

  /**
   * Returns an infinite sequential ordered {@code LongStream} produced by iterative application of
   * a function {@code f} to an initial element {@code seed}, producing a {@code Stream} consisting
   * of {@code seed}, {@code f(seed)}, {@code f(f(seed))}, etc.
   *
   * <p>The first element (position {@code 0}) in the {@code LongStream} will be the provided {@code
   * seed}. For {@code n > 0}, the element at position {@code n}, will be the result of applying the
   * function {@code f} to the element at position {@code n - 1}.
   *
   * <p>The action of applying {@code f} for one element <a
   * href="../concurrent/package-summary.html#MemoryVisibility"><i>happens-before</i></a> the action
   * of applying {@code f} for subsequent elements. For any given element the action may be
   * performed in whatever thread the library chooses.
   *
   * @param seed the initial element
   * @param f a function to be applied to the previous element to produce a new element
   * @return a new sequential {@code LongStream}
   */
  public static LongStream iterate(long seed, LongUnaryOperator f) {
    Objects.requireNonNull(f);
    Spliterator.OfLong spliterator =
        new Spliterators.AbstractLongSpliterator(
            Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL) {

          long prev;

          boolean started;

          @Override
          public boolean tryAdvance(LongConsumer action) {
            Objects.requireNonNull(action);
            long t;
            if (started) {
              t = f.applyAsLong(prev);
            } else {
              t = seed;
              started = true;
            }
            action.accept(prev = t);
            return true;
          }
        };
    return StreamSupport.longStream(spliterator, false);
  }

  /**
   * Returns a sequential ordered {@code LongStream} produced by iterative application of the given
   * {@code next} function to an initial element, conditioned on satisfying the given {@code
   * hasNext} predicate. The stream terminates as soon as the {@code hasNext} predicate returns
   * false.
   *
   * <p>{@code LongStreams.iterate} should produce the same sequence of elements as produced by the
   * corresponding for-loop:
   *
   * <pre>{@code
   * for (long index=seed; hasNext.test(index); index = next.applyAsLong(index)) {
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
   * @return a new sequential {@code LongStream}
   * @since 9
   */
  public static LongStream iterate(long seed, LongPredicate hasNext, LongUnaryOperator next) {
    Objects.requireNonNull(next);
    Objects.requireNonNull(hasNext);
    Spliterator.OfLong spliterator =
        new Spliterators.AbstractLongSpliterator(
            Long.MAX_VALUE, Spliterator.ORDERED | Spliterator.IMMUTABLE | Spliterator.NONNULL) {

          long prev;

          boolean started, finished;

          @Override
          public boolean tryAdvance(LongConsumer action) {
            Objects.requireNonNull(action);
            if (finished) {
              return false;
            }
            long t;
            if (started) {
              t = next.applyAsLong(prev);
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
          public void forEachRemaining(LongConsumer action) {
            Objects.requireNonNull(action);
            if (finished) {
              return;
            }
            finished = true;
            long t = started ? next.applyAsLong(prev) : seed;
            while (hasNext.test(t)) {
              action.accept(t);
              t = next.applyAsLong(t);
            }
          }
        };
    return StreamSupport.longStream(spliterator, false);
  }

  /**
   * Returns an infinite sequential unordered stream where each element is generated by the provided
   * {@code LongSupplier}. This is suitable for generating constant streams, streams of random
   * elements, etc.
   *
   * @param s the {@code LongSupplier} for generated elements
   * @return a new infinite sequential unordered {@code LongStream}
   */
  public static LongStream generate(LongSupplier s) {
    Objects.requireNonNull(s);
    return StreamSupport.longStream(
        new StreamSpliterators.InfiniteSupplyingSpliterator.OfLong(Long.MAX_VALUE, s), false);
  }

  /**
   * Returns a sequential ordered {@code LongStream} from {@code startInclusive} (inclusive) to
   * {@code endExclusive} (exclusive) by an incremental step of {@code 1}.
   *
   * <p><b>API Note:</b><br>
   *
   * <p>An equivalent sequence of increasing values can be produced sequentially using a {@code for}
   * loop as follows:
   *
   * <pre>{@code
   * for (long i = startInclusive; i < endExclusive ; i++) { ... }
   * }</pre>
   *
   * @param startInclusive the (inclusive) initial value
   * @param endExclusive the exclusive upper bound
   * @return a sequential {@code LongStream} for the range of {@code long} elements
   */
  public static LongStream range(long startInclusive, long endExclusive) {
    if (startInclusive >= endExclusive) {
      return empty();
    } else if (endExclusive - startInclusive < 0) {
      // Size of range > Long.MAX_VALUE
      // Split the range in two and concatenate
      // Note: if the range is [Long.MIN_VALUE, Long.MAX_VALUE) then
      // the lower range, [Long.MIN_VALUE, 0) will be further split in two
      long m = startInclusive + Longs.divideUnsigned(endExclusive - startInclusive, 2) + 1;
      return concat(range(startInclusive, m), range(m, endExclusive));
    } else {
      return StreamSupport.longStream(
          new Streams.RangeLongSpliterator(startInclusive, endExclusive, false), false);
    }
  }

  /**
   * Returns a sequential ordered {@code LongStream} from {@code startInclusive} (inclusive) to
   * {@code endInclusive} (inclusive) by an incremental step of {@code 1}.
   *
   * <p><b>API Note:</b><br>
   *
   * <p>An equivalent sequence of increasing values can be produced sequentially using a {@code for}
   * loop as follows:
   *
   * <pre>{@code
   * for (long i = startInclusive; i <= endInclusive ; i++) { ... }
   * }</pre>
   *
   * @param startInclusive the (inclusive) initial value
   * @param endInclusive the inclusive upper bound
   * @return a sequential {@code LongStream} for the range of {@code long} elements
   */
  public static LongStream rangeClosed(long startInclusive, long endInclusive) {
    if (startInclusive > endInclusive) {
      return empty();
    } else if (endInclusive - startInclusive + 1 <= 0) {
      // Size of range > Long.MAX_VALUE
      // Split the range in two and concatenate
      // Note: if the range is [Long.MIN_VALUE, Long.MAX_VALUE] then
      // the lower range, [Long.MIN_VALUE, 0), and upper range,
      // [0, Long.MAX_VALUE], will both be further split in two
      long m = startInclusive + Longs.divideUnsigned(endInclusive - startInclusive, 2) + 1;
      return concat(range(startInclusive, m), rangeClosed(m, endInclusive));
    } else {
      return StreamSupport.longStream(
          new Streams.RangeLongSpliterator(startInclusive, endInclusive, true), false);
    }
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
   * LongStream concat = RefStreams.of(s1, s2, s3, s4).flatMapToLong(s -> s);
   * }</pre>
   *
   * @param a the first stream
   * @param b the second stream
   * @return the concatenation of the two input streams
   */
  public static LongStream concat(LongStream a, LongStream b) {
    Objects.requireNonNull(a);
    Objects.requireNonNull(b);
    Spliterator.OfLong split =
        new Streams.ConcatSpliterator.OfLong(a.spliterator(), b.spliterator());
    LongStream stream = StreamSupport.longStream(split, a.isParallel() || b.isParallel());
    return stream.onClose(Streams.composedClose(a, b));
  }

  private LongStreams() {}
}
