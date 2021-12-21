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

import java.util.Iterator;
import org.java.util.Spliterator;

/**
 * Base interface for streams, which are sequences of elements supporting sequential and parallel
 * aggregate operations. The following example illustrates an aggregate operation using the stream
 * types {@link Stream} and {@link IntStream}, computing the sum of the weights of the red widgets:
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
 * operations, stream pipelines, and parallelism, which governs the behavior of all stream types.
 *
 * @param <T> the type of the stream elements
 * @param <S> the type of the stream implementing {@code BaseStream}
 * @since 1.8
 * @see Stream
 * @see IntStream
 * @see LongStream
 * @see DoubleStream
 * @see <a href="package-summary.html">java8.util.stream</a>
 */
public interface BaseStream<T, S extends BaseStream<T, S>> {

  /**
   * Returns an iterator for the elements of this stream.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * @return the element iterator for this stream
   */
  Iterator<T> iterator();

  /**
   * Returns a spliterator for the elements of this stream.
   *
   * <p>This is a <a href="package-summary.html#StreamOps">terminal operation</a>.
   *
   * <p>The returned spliterator should report the set of characteristics derived from the stream
   * pipeline (namely the characteristics derived from the stream source spliterator and the
   * intermediate operations). Implementations may report a sub-set of those characteristics. For
   * example, it may be too expensive to compute the entire set for some or all possible stream
   * pipelines.
   *
   * @return the element spliterator for this stream
   */
  Spliterator<T> spliterator();

  /**
   * Returns whether this stream, if a terminal operation were to be executed, would execute in
   * parallel. Calling this method after invoking an terminal stream operation method may yield
   * unpredictable results.
   *
   * @return {@code true} if this stream would execute in parallel if executed
   */
  boolean isParallel();

  /**
   * Returns an equivalent stream that is sequential. May return itself, either because the stream
   * was already sequential, or because the underlying stream state was modified to be sequential.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @return a sequential stream
   */
  S sequential();

  /**
   * Returns an equivalent stream that is parallel. May return itself, either because the stream was
   * already parallel, or because the underlying stream state was modified to be parallel.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @return a parallel stream
   */
  S parallel();

  /**
   * Returns an equivalent stream that is <a href="package-summary.html#Ordering">unordered</a>. May
   * return itself, either because the stream was already unordered, or because the underlying
   * stream state was modified to be unordered.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @return an unordered stream
   */
  S unordered();

  /**
   * Returns an equivalent stream with an additional close handler. Close handlers are run when the
   * {@link #close()} method is called on the stream, and are executed in the order they were added.
   * All close handlers are run, even if earlier close handlers throw exceptions. If any close
   * handler throws an exception, the first exception thrown will be relayed to the caller of {@code
   * close()}, with any remaining exceptions added to that exception as suppressed exceptions
   * (unless one of the remaining exceptions is the same exception as the first exception, since an
   * exception cannot suppress itself.) May return itself.
   *
   * <p>This is an <a href="package-summary.html#StreamOps">intermediate operation</a>.
   *
   * @param closeHandler A task to execute when the stream is closed
   * @return a stream with a handler that is run if the stream is closed
   */
  S onClose(Runnable closeHandler);

  /**
   * Closes this stream, causing all close handlers for this stream pipeline to be called.
   *
   * @see AutoCloseable#close()
   */
  void close();
}
