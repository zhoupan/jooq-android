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

import org.java.util.IntSummaryStatistics;
import org.java.util.OptionalDouble;
import org.java.util.OptionalInt;
import org.java.util.PrimitiveIterator;
import org.java.util.Spliterator;
import org.java.util.function.BiConsumer;
import org.java.util.function.Function;
import org.java.util.function.IntBinaryOperator;
import org.java.util.function.IntConsumer;
import org.java.util.function.IntFunction;
import org.java.util.function.IntPredicate;
import org.java.util.function.IntSupplier;
import org.java.util.function.IntToDoubleFunction;
import org.java.util.function.IntToLongFunction;
import org.java.util.function.IntUnaryOperator;
import org.java.util.function.ObjIntConsumer;
import org.java.util.function.Supplier;

/**
 * A sequence of primitive int-valued elements supporting sequential and parallel aggregate
 * operations. This is the {@code int} primitive specialization of {@link Stream}.
 *
 * <p>The following example illustrates an aggregate operation using {@link Stream} and {@link
 * IntStream}, computing the sum of the weights of the red widgets:
 *
 * <pre>{@code
 * int sum = widgets.stream()
 *                  .filter(w -> w.getColor() == RED)
 *                  .mapToInt(w -> w.getWeight())
 *                  .sum();
 * }</pre>
 *
 * See the class documentation for {@link Stream} and the package documentation for <a
 * href="package-summary.html">java8.util.stream</a> for additional specification of streams, stream
 * operations, stream pipelines, and parallelism.
 *
 * @since 1.8
 * @see Stream
 * @see <a href="package-summary.html">java8.util.stream</a>
 */
public interface IntStream extends BaseStream<Integer, IntStream> {

  /**
   * Returns a stream consisting of the elements of this stream that match the given predicate.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> predicate to apply to each element
   *     to determine if it should be included
   * @return the new stream
   */
  IntStream filter(IntPredicate predicate);

  /**
   * Returns a stream consisting of the results of applying the given function to the elements of
   * this stream.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function to apply to each element
   * @return the new stream
   */
  IntStream map(IntUnaryOperator mapper);

  /**
   * Returns an object-valued {@code Stream} consisting of the results of applying the given
   * function to the elements of this stream.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param <U> the element type of the new stream
   * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function to apply to each element
   * @return the new stream
   */
  <U> Stream<U> mapToObj(IntFunction<? extends U> mapper);

  /**
   * Returns a {@code LongStream} consisting of the results of applying the given function to the
   * elements of this stream.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function to apply to each element
   * @return the new stream
   */
  LongStream mapToLong(IntToLongFunction mapper);

  /**
   * Returns a {@code DoubleStream} consisting of the results of applying the given function to the
   * elements of this stream.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function to apply to each element
   * @return the new stream
   */
  DoubleStream mapToDouble(IntToDoubleFunction mapper);

  /**
   * Returns a stream consisting of the results of replacing each element of this stream with the
   * contents of a mapped stream produced by applying the provided mapping function to each element.
   * Each mapped stream is {@link org.java.util.stream.BaseStream#close() closed} after its contents
   * have been placed into this stream. (If a mapped stream is {@code null} an empty stream is used,
   * instead.)
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function to apply to each element
   *     which produces an {@code IntStream} of new values
   * @return the new stream
   * @see Stream#flatMap(Function)
   */
  IntStream flatMap(IntFunction<? extends IntStream> mapper);

  /**
   * Returns a stream consisting of the results of replacing each element of this stream with
   * multiple elements, specifically zero or more elements. Replacement is performed by applying the
   * provided mapping function to each element in conjunction with a {@linkplain IntConsumer
   * consumer} argument that accepts replacement elements. The mapping function calls the consumer
   * zero or more times to provide the replacement elements.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * <p>If the {@linkplain IntConsumer consumer} argument is used outside the scope of its
   * application to the mapping function, the results are undefined.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation invokes {@link #flatMap flatMap} on this stream, passing a function
   * that behaves as follows. First, it calls the mapper function with an {@code IntConsumer} that
   * accumulates replacement elements into a newly created internal buffer. When the mapper function
   * returns, it creates an {@code IntStream} from the internal buffer. Finally, it returns this
   * stream to {@code flatMap}.
   *
   * @param mapper a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function that generates replacement
   *     elements
   * @return the new stream
   * @see Stream#mapMulti Stream.mapMulti
   * @since 16
   */
  IntStream mapMulti(IntMapMultiConsumer mapper);

  /**
   * Returns a stream consisting of the distinct elements of this stream.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
   *
   * @return the new stream
   */
  IntStream distinct();

  /**
   * Returns a stream consisting of the elements of this stream in sorted order.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
   *
   * @return the new stream
   */
  IntStream sorted();

  /**
   * Returns a stream consisting of the elements of this stream, additionally performing the
   * provided action on each element as elements are consumed from the resulting stream.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * <p>For parallel stream pipelines, the action may be called at whatever time and in whatever
   * thread the element is made available by the upstream operation. If the action modifies shared
   * state, it is responsible for providing the required synchronization.
   *
   * <p><b>API Note:</b><br>
   * This method exists mainly to support debugging, where you want to see the elements as they flow
   * past a certain point in a pipeline:
   *
   * <pre>{@code
   * IntStreams.of(1, 2, 3, 4)
   *     .filter(e -> e > 2)
   *     .peek(e -> System.out.println("Filtered value: " + e))
   *     .map(e -> e * e)
   *     .peek(e -> System.out.println("Mapped value: " + e))
   *     .sum();
   * }</pre>
   *
   * <p>In cases where the stream implementation is able to optimize away the production of some or
   * all the elements (such as with short-circuiting operations like {@code findFirst}, or in the
   * example described in {@link #count}), the action will not be invoked for those elements.
   *
   * @param action a <a href="package-summary.html#NonInterference">non-interfering</a> action to
   *     perform on the elements as they are consumed from the stream
   * @return the new stream
   */
  IntStream peek(IntConsumer action);

  /**
   * Returns a stream consisting of the elements of this stream, truncated to be no longer than
   * {@code maxSize} in length.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting stateful intermediate
   * operation</a>.
   *
   * <p><b>API Note:</b><br>
   * While {@code limit()} is generally a cheap operation on sequential stream pipelines, it can be
   * quite expensive on ordered parallel pipelines, especially for large values of {@code maxSize},
   * since {@code limit(n)} is constrained to return not just any <em>n</em> elements, but the
   * <em>first n</em> elements in the encounter order. Using an unordered stream source (such as
   * {@link IntStreams#generate(IntSupplier)}) or removing the ordering constraint with {@link
   * #unordered()} may result in significant speedups of {@code limit()} in parallel pipelines, if
   * the semantics of your situation permit. If consistency with encounter order is required, and
   * you are experiencing poor performance or memory utilization with {@code limit()} in parallel
   * pipelines, switching to sequential execution with {@link #sequential()} may improve
   * performance.
   *
   * @param maxSize the number of elements the stream should be limited to
   * @return the new stream
   * @throws IllegalArgumentException if {@code maxSize} is negative
   */
  IntStream limit(long maxSize);

  /**
   * Returns a stream consisting of the remaining elements of this stream after discarding the first
   * {@code n} elements of the stream. If this stream contains fewer than {@code n} elements then an
   * empty stream will be returned.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
   *
   * <p><b>API Note:</b><br>
   * While {@code skip()} is generally a cheap operation on sequential stream pipelines, it can be
   * quite expensive on ordered parallel pipelines, especially for large values of {@code n}, since
   * {@code skip(n)} is constrained to skip not just any <em>n</em> elements, but the <em>first
   * n</em> elements in the encounter order. Using an unordered stream source (such as {@link
   * IntStreams#generate(IntSupplier)}) or removing the ordering constraint with {@link
   * #unordered()} may result in significant speedups of {@code skip()} in parallel pipelines, if
   * the semantics of your situation permit. If consistency with encounter order is required, and
   * you are experiencing poor performance or memory utilization with {@code skip()} in parallel
   * pipelines, switching to sequential execution with {@link #sequential()} may improve
   * performance.
   *
   * @param n the number of leading elements to skip
   * @return the new stream
   * @throws IllegalArgumentException if {@code n} is negative
   */
  IntStream skip(long n);

  /**
   * Returns, if this stream is ordered, a stream consisting of the longest prefix of elements taken
   * from this stream that match the given predicate. Otherwise returns, if this stream is
   * unordered, a stream consisting of a subset of elements taken from this stream that match the
   * given predicate.
   *
   * <p>If this stream is ordered then the longest prefix is a contiguous sequence of elements of
   * this stream that match the given predicate. The first element of the sequence is the first
   * element of this stream, and the element immediately following the last element of the sequence
   * does not match the given predicate.
   *
   * <p>If this stream is unordered, and some (but not all) elements of this stream match the given
   * predicate, then the behavior of this operation is nondeterministic; it is free to take any
   * subset of matching elements (which includes the empty set).
   *
   * <p>Independent of whether this stream is ordered or unordered if all elements of this stream
   * match the given predicate then this operation takes all elements (the result is the same as the
   * input), or if no elements of the stream match the given predicate then no elements are taken
   * (the result is an empty stream).
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting stateful intermediate
   * operation</a>.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation obtains the {@link #spliterator() spliterator} of this stream, wraps
   * that spliterator so as to support the semantics of this operation on traversal, and returns a
   * new stream associated with the wrapped spliterator. The returned stream preserves the execution
   * characteristics of this stream (namely parallel or sequential execution as per {@link
   * #isParallel()}) but the wrapped spliterator may choose to not support splitting. When the
   * returned stream is closed, the close handlers for both the returned and this stream are
   * invoked.
   *
   * <p><b>API Note:</b><br>
   * While {@code takeWhile()} is generally a cheap operation on sequential stream pipelines, it can
   * be quite expensive on ordered parallel pipelines, since the operation is constrained to return
   * not just any valid prefix, but the longest prefix of elements in the encounter order. Using an
   * unordered stream source (such as {@link IntStreams#generate(IntSupplier)}) or removing the
   * ordering constraint with {@link #unordered()} may result in significant speedups of {@code
   * takeWhile()} in parallel pipelines, if the semantics of your situation permit. If consistency
   * with encounter order is required, and you are experiencing poor performance or memory
   * utilization with {@code takeWhile()} in parallel pipelines, switching to sequential execution
   * with {@link #sequential()} may improve performance.
   *
   * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements to
   *     determine the longest prefix of elements.
   * @return the new stream
   * @since 9
   */
  IntStream takeWhile(IntPredicate predicate);

  /**
   * Returns, if this stream is ordered, a stream consisting of the remaining elements of this
   * stream after dropping the longest prefix of elements that match the given predicate. Otherwise
   * returns, if this stream is unordered, a stream consisting of the remaining elements of this
   * stream after dropping a subset of elements that match the given predicate.
   *
   * <p>If this stream is ordered then the longest prefix is a contiguous sequence of elements of
   * this stream that match the given predicate. The first element of the sequence is the first
   * element of this stream, and the element immediately following the last element of the sequence
   * does not match the given predicate.
   *
   * <p>If this stream is unordered, and some (but not all) elements of this stream match the given
   * predicate, then the behavior of this operation is nondeterministic; it is free to drop any
   * subset of matching elements (which includes the empty set).
   *
   * <p>Independent of whether this stream is ordered or unordered if all elements of this stream
   * match the given predicate then this operation drops all elements (the result is an empty
   * stream), or if no elements of the stream match the given predicate then no elements are dropped
   * (the result is the same as the input).
   *
   * <p>This is a <a href="package-summary.html#StreamOps">stateful intermediate operation</a>.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation obtains the {@link #spliterator() spliterator} of this stream, wraps
   * that spliterator so as to support the semantics of this operation on traversal, and returns a
   * new stream associated with the wrapped spliterator. The returned stream preserves the execution
   * characteristics of this stream (namely parallel or sequential execution as per {@link
   * #isParallel()}) but the wrapped spliterator may choose to not support splitting. When the
   * returned stream is closed, the close handlers for both the returned and this stream are
   * invoked.
   *
   * <p><b>API Note:</b><br>
   * While {@code dropWhile()} is generally a cheap operation on sequential stream pipelines, it can
   * be quite expensive on ordered parallel pipelines, since the operation is constrained to return
   * not just any valid prefix, but the longest prefix of elements in the encounter order. Using an
   * unordered stream source (such as {@link IntStreams#generate(IntSupplier)}) or removing the
   * ordering constraint with {@link #unordered()} may result in significant speedups of {@code
   * dropWhile()} in parallel pipelines, if the semantics of your situation permit. If consistency
   * with encounter order is required, and you are experiencing poor performance or memory
   * utilization with {@code dropWhile()} in parallel pipelines, switching to sequential execution
   * with {@link #sequential()} may improve performance.
   *
   * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements to
   *     determine the longest prefix of elements.
   * @return the new stream
   * @since 9
   */
  IntStream dropWhile(IntPredicate predicate);

  /**
   * Performs an action for each element of this stream.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * <p>For parallel stream pipelines, this operation does <em>not</em> guarantee to respect the
   * encounter order of the stream, as doing so would sacrifice the benefit of parallelism. For any
   * given element, the action may be performed at whatever time and in whatever thread the library
   * chooses. If the action accesses shared state, it is responsible for providing the required
   * synchronization.
   *
   * @param action a <a href="package-summary.html#NonInterference">non-interfering</a> action to
   *     perform on the elements
   */
  void forEach(IntConsumer action);

  /**
   * Performs an action for each element of this stream, guaranteeing that each element is processed
   * in encounter order for streams that have a defined encounter order.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @param action a <a href="package-summary.html#NonInterference">non-interfering</a> action to
   *     perform on the elements
   * @see #forEach(IntConsumer)
   */
  void forEachOrdered(IntConsumer action);

  /**
   * Returns an array containing the elements of this stream.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return an array containing the elements of this stream
   */
  int[] toArray();

  /**
   * Performs a <a href="package-summary.html#Reduction">reduction</a> on the elements of this
   * stream, using the provided identity value and an <a
   * href="package-summary.html#Associativity">associative</a> accumulation function, and returns
   * the reduced value. This is equivalent to:
   *
   * <pre>{@code
   * int result = identity;
   * for (int element : this stream)
   *     result = accumulator.applyAsInt(result, element)
   * return result;
   * }</pre>
   *
   * but is not constrained to execute sequentially.
   *
   * <p>The {@code identity} value must be an identity for the accumulator function. This means that
   * for all {@code x}, {@code accumulator.apply(identity, x)} is equal to {@code x}. The {@code
   * accumulator} function must be an <a href="package-summary.html#Associativity">associative</a>
   * function.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * <p><b>API Note:</b><br>
   * Sum, min and max are all special cases of reduction that can be expressed using this method.
   * For example, summing a stream can be expressed as:
   *
   * <pre>{@code
   * int sum = integers.reduce(0, (a, b) -> a+b);
   * }</pre>
   *
   * or more compactly:
   *
   * <pre>{@code
   * int sum = integers.reduce(0, Integer::sum);
   * }</pre>
   *
   * <p>While this may seem a more roundabout way to perform an aggregation compared to simply
   * mutating a running total in a loop, reduction operations parallelize more gracefully, without
   * needing additional synchronization and with greatly reduced risk of data races.
   *
   * @param identity the identity value for the accumulating function
   * @param op an <a href="package-summary.html#Associativity">associative</a>, <a
   *     href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function for combining two values
   * @return the result of the reduction
   * @see #sum()
   * @see #min()
   * @see #max()
   * @see #average()
   */
  int reduce(int identity, IntBinaryOperator op);

  /**
   * Performs a <a href="package-summary.html#Reduction">reduction</a> on the elements of this
   * stream, using an <a href="package-summary.html#Associativity">associative</a> accumulation
   * function, and returns an {@code OptionalInt} describing the reduced value, if any. This is
   * equivalent to:
   *
   * <pre>{@code
   * boolean foundAny = false;
   * int result = null;
   * for (int element : this stream) {
   *     if (!foundAny) {
   *         foundAny = true;
   *         result = element;
   *     }
   *     else
   *         result = accumulator.applyAsInt(result, element);
   * }
   * return foundAny ? OptionalInt.of(result) : OptionalInt.empty();
   * }</pre>
   *
   * but is not constrained to execute sequentially.
   *
   * <p>The {@code accumulator} function must be an <a
   * href="package-summary.html#Associativity">associative</a> function.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @param op an <a href="package-summary.html#Associativity">associative</a>, <a
   *     href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function for combining two values
   * @return the result of the reduction
   * @see #reduce(int, IntBinaryOperator)
   */
  OptionalInt reduce(IntBinaryOperator op);

  /**
   * Performs a <a href="package-summary.html#MutableReduction">mutable reduction</a> operation on
   * the elements of this stream. A mutable reduction is one in which the reduced value is a mutable
   * result container, such as an {@code ArrayList}, and elements are incorporated by updating the
   * state of the result rather than by replacing the result. This produces a result equivalent to:
   *
   * <pre>{@code
   * R result = supplier.get();
   * for (int element : this stream)
   *     accumulator.accept(result, element);
   * return result;
   * }</pre>
   *
   * <p>Like {@link #reduce(int, IntBinaryOperator)}, {@code collect} operations can be parallelized
   * without requiring additional synchronization.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @param <R> the type of the mutable result container
   * @param supplier a function that creates a new mutable result container. For a parallel
   *     execution, this function may be called multiple times and must return a fresh value each
   *     time
   * @param accumulator an <a href="package-summary.html#Associativity">associative</a>, <a
   *     href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function that must fold an element
   *     into a result container
   * @param combiner an <a href="package-summary.html#Associativity">associative</a>, <a
   *     href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> function that accepts two partial
   *     result containers and merges them, which must be compatible with the accumulator function.
   *     The combiner function must fold the elements from the second result container into the
   *     first result container
   * @return the result of the reduction
   * @see Stream#collect(Supplier, BiConsumer, BiConsumer)
   */
  <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner);

  /**
   * Returns the sum of elements in this stream. This is a special case of a <a
   * href="package-summary.html#Reduction">reduction</a> and is equivalent to:
   *
   * <pre>{@code
   * return reduce(0, Integer::sum);
   * }</pre>
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return the sum of elements in this stream
   */
  int sum();

  /**
   * Returns an {@code OptionalInt} describing the minimum element of this stream, or an empty
   * optional if this stream is empty. This is a special case of a <a
   * href="package-summary.html#Reduction">reduction</a> and is equivalent to:
   *
   * <pre>{@code
   * return reduce(Integer::min);
   * }</pre>
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return an {@code OptionalInt} containing the minimum element of this stream, or an empty
   *     {@code OptionalInt} if the stream is empty
   */
  OptionalInt min();

  /**
   * Returns an {@code OptionalInt} describing the maximum element of this stream, or an empty
   * optional if this stream is empty. This is a special case of a <a
   * href="package-summary.html#Reduction">reduction</a> and is equivalent to:
   *
   * <pre>{@code
   * return reduce(Integer::max);
   * }</pre>
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return an {@code OptionalInt} containing the maximum element of this stream, or an empty
   *     {@code OptionalInt} if the stream is empty
   */
  OptionalInt max();

  /**
   * Returns the count of elements in this stream. This is a special case of a <a
   * href="package-summary.html#Reduction">reduction</a> and is (at least in the predominant case
   * where the count can't be directly obtained from the stream source) equivalent to:
   *
   * <pre>{@code
   * return mapToLong(e -> 1L).sum();
   * }</pre>
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * <p><b>API Note:</b><br>
   * An implementation may choose to not execute the stream pipeline (either sequentially or in
   * parallel) if it is capable of computing the count directly from the stream source. In such
   * cases no source elements will be traversed and no intermediate operations will be evaluated.
   * Behavioral parameters with side-effects, which are strongly discouraged except for harmless
   * cases such as debugging, may be affected. For example, consider the following stream:
   *
   * <pre>{@code
   * IntStream s = IntStreams.of(1, 2, 3, 4);
   * long count = s.peek(System.out::println).count();
   * }</pre>
   *
   * The number of elements covered by the stream source is known and the intermediate operation,
   * {@code peek}, does not inject into or remove elements from the stream (as may be the case for
   * {@code flatMap} or {@code filter} operations). Thus the count is 4 and there is no need to
   * execute the pipeline and, as a side-effect, print out the elements.
   *
   * @return the count of elements in this stream
   */
  long count();

  /**
   * Returns an {@code OptionalDouble} describing the arithmetic mean of elements of this stream, or
   * an empty optional if this stream is empty. This is a special case of a <a
   * href="package-summary.html#Reduction">reduction</a>.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return an {@code OptionalDouble} containing the average element of this stream, or an empty
   *     optional if the stream is empty
   */
  OptionalDouble average();

  /**
   * Returns an {@code IntSummaryStatistics} describing various summary data about the elements of
   * this stream. This is a special case of a <a
   * href="package-summary.html#Reduction">reduction</a>.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return an {@code IntSummaryStatistics} describing various summary data about the elements of
   *     this stream
   */
  IntSummaryStatistics summaryStatistics();

  /**
   * Returns whether any elements of this stream match the provided predicate. May not evaluate the
   * predicate on all elements if not necessary for determining the result. If the stream is empty
   * then {@code false} is returned and the predicate is not evaluated.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
   *
   * <p><b>API Note:</b><br>
   * This method evaluates the <em>existential quantification</em> of the predicate over the
   * elements of the stream (for some x P(x)).
   *
   * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements of
   *     this stream
   * @return {@code true} if any elements of the stream match the provided predicate, otherwise
   *     {@code false}
   */
  boolean anyMatch(IntPredicate predicate);

  /**
   * Returns whether all elements of this stream match the provided predicate. May not evaluate the
   * predicate on all elements if not necessary for determining the result. If the stream is empty
   * then {@code true} is returned and the predicate is not evaluated.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
   *
   * <p><b>API Note:</b><br>
   * This method evaluates the <em>universal quantification</em> of the predicate over the elements
   * of the stream (for all x P(x)). If the stream is empty, the quantification is said to be
   * <em>vacuously satisfied</em> and is always {@code true} (regardless of P(x)).
   *
   * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements of
   *     this stream
   * @return {@code true} if either all elements of the stream match the provided predicate or the
   *     stream is empty, otherwise {@code false}
   */
  boolean allMatch(IntPredicate predicate);

  /**
   * Returns whether no elements of this stream match the provided predicate. May not evaluate the
   * predicate on all elements if not necessary for determining the result. If the stream is empty
   * then {@code true} is returned and the predicate is not evaluated.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
   *
   * <p><b>API Note:</b><br>
   * This method evaluates the <em>universal quantification</em> of the negated predicate over the
   * elements of the stream (for all x ~P(x)). If the stream is empty, the quantification is said to
   * be vacuously satisfied and is always {@code true}, regardless of P(x).
   *
   * @param predicate a <a href="package-summary.html#NonInterference">non-interfering</a>, <a
   *     href="package-summary.html#Statelessness">stateless</a> predicate to apply to elements of
   *     this stream
   * @return {@code true} if either no elements of the stream match the provided predicate or the
   *     stream is empty, otherwise {@code false}
   */
  boolean noneMatch(IntPredicate predicate);

  /**
   * Returns an {@link OptionalInt} describing the first element of this stream, or an empty {@code
   * OptionalInt} if the stream is empty. If the stream has no encounter order, then any element may
   * be returned.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
   *
   * @return an {@code OptionalInt} describing the first element of this stream, or an empty {@code
   *     OptionalInt} if the stream is empty
   */
  OptionalInt findFirst();

  /**
   * Returns an {@link OptionalInt} describing some element of the stream, or an empty {@code
   * OptionalInt} if the stream is empty.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">short-circuiting terminal operation</a>.
   *
   * <p>The behavior of this operation is explicitly nondeterministic; it is free to select any
   * element in the stream. This is to allow for maximal performance in parallel operations; the
   * cost is that multiple invocations on the same source may not return the same result. (If a
   * stable result is desired, use {@link #findFirst()} instead.)
   *
   * @return an {@code OptionalInt} describing some element of this stream, or an empty {@code
   *     OptionalInt} if the stream is empty
   * @see #findFirst()
   */
  OptionalInt findAny();

  /**
   * Returns a {@code LongStream} consisting of the elements of this stream, converted to {@code
   * long}.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @return a {@code LongStream} consisting of the elements of this stream, converted to {@code
   *     long}
   */
  LongStream asLongStream();

  /**
   * Returns a {@code DoubleStream} consisting of the elements of this stream, converted to {@code
   * double}.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @return a {@code DoubleStream} consisting of the elements of this stream, converted to {@code
   *     double}
   */
  DoubleStream asDoubleStream();

  /**
   * Returns a {@code Stream} consisting of the elements of this stream, each boxed to an {@code
   * Integer}.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @return a {@code Stream} consistent of the elements of this stream, each boxed to an {@code
   *     Integer}
   */
  Stream<Integer> boxed();

  @Override
  IntStream sequential();

  @Override
  IntStream parallel();

  @Override
  PrimitiveIterator.OfInt iterator();

  @Override
  Spliterator.OfInt spliterator();

  /**
   * A mutable builder for an {@code IntStream}.
   *
   * <p>A stream builder has a lifecycle, which starts in a building phase, during which elements
   * can be added, and then transitions to a built phase, after which elements may not be added. The
   * built phase begins when the {@link #build()} method is called, which creates an ordered stream
   * whose elements are the elements that were added to the stream builder, in the order they were
   * added.
   *
   * @see IntStreams#builder()
   * @since 1.8
   */
  public interface Builder extends IntConsumer {

    /**
     * Adds an element to the stream being built.
     *
     * @throws IllegalStateException if the builder has already transitioned to the built state
     */
    @Override
    void accept(int t);

    /**
     * Adds an element to the stream being built.
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default implementation behaves as if:
     *
     * <pre>{@code
     * accept(t)
     * return this;
     * }</pre>
     *
     * @param t the element to add
     * @return {@code this} builder
     * @throws IllegalStateException if the builder has already transitioned to the built state
     */
    Builder add(int t);

    /**
     * Builds the stream, transitioning this builder to the built state. An {@code
     * IllegalStateException} is thrown if there are further attempts to operate on the builder
     * after it has entered the built state.
     *
     * @return the built stream
     * @throws IllegalStateException if the builder has already transitioned to the built state
     */
    IntStream build();
  }

  /**
   * Represents an operation that accepts an {@code int}-valued argument and an IntConsumer, and
   * returns no result. This functional interface is used by {@link
   * IntStream#mapMulti(IntStream.IntMapMultiConsumer) IntStream.mapMulti} to replace an int value
   * with zero or more int values.
   *
   * <p>This is a <a href="../function/package-summary.html">functional interface</a> whose
   * functional method is {@link #accept(int, IntConsumer)}.
   *
   * @see IntStream#mapMulti
   * @since 16
   */
  interface IntMapMultiConsumer {

    /**
     * Replaces the given {@code value} with zero or more values by feeding the mapped values to the
     * {@code ic} consumer.
     *
     * @param value the int value coming from upstream
     * @param ic an {@code IntConsumer} accepting the mapped values
     */
    void accept(int value, IntConsumer ic);
  }
}
