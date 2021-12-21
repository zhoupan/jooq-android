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

import org.java.util.Spliterator;
import org.java.util.function.Consumer;
import org.java.util.function.IntFunction;

/**
 * Helper class for executing <a href="package-summary.html#StreamOps">stream pipelines</a>,
 * capturing all of the information about a stream pipeline (output shape, intermediate operations,
 * stream flags, parallelism, etc) in one place.
 *
 * <p>A {@code PipelineHelper} describes the initial segment of a stream pipeline, including its
 * source, intermediate operations, and may additionally incorporate information about the terminal
 * (or stateful) operation which follows the last intermediate operation described by this {@code
 * PipelineHelper}. The {@code PipelineHelper} is passed to the {@link
 * TerminalOp#evaluateParallel(PipelineHelper, org.java.util.Spliterator)}, {@link
 * TerminalOp#evaluateSequential(PipelineHelper, org.java.util.Spliterator)}, and {@link
 * AbstractPipeline#opEvaluateParallel(PipelineHelper, org.java.util.Spliterator,
 * org.java.util.function.IntFunction)}, methods, which can use the {@code PipelineHelper} to access
 * information about the pipeline such as head shape, stream flags, and size, and use the helper
 * methods such as {@link #wrapAndCopyInto(Sink, Spliterator)}, {@link #copyInto(Sink,
 * Spliterator)}, and {@link #wrapSink(Sink)} to execute pipeline operations.
 *
 * @param <P_OUT> type of output elements from the pipeline
 * @since 1.8
 */
abstract class PipelineHelper<P_OUT> {

  /**
   * Gets the stream shape for the source of the pipeline segment.
   *
   * @return the stream shape for the source of the pipeline segment.
   */
  abstract StreamShape getSourceShape();

  /**
   * Gets the combined stream and operation flags for the output of the described pipeline. This
   * will incorporate stream flags from the stream source, all the intermediate operations and the
   * terminal operation.
   *
   * @return the combined stream and operation flags
   * @see StreamOpFlag
   */
  abstract int getStreamAndOpFlags();

  /**
   * Returns the exact output size of the portion of the output resulting from applying the pipeline
   * stages described by this {@code PipelineHelper} to the portion of the input described by the
   * provided {@code Spliterator}, if known. If not known or known infinite, will return {@code -1}.
   *
   * <p><b>API Note:</b><br>
   * The exact output size is known if the {@code Spliterator} has the {@code SIZED} characteristic,
   * and the operation flags {@link StreamOpFlag#SIZED} is known on the combined stream and
   * operation flags. The exact output size may differ from spliterator size, if pipeline contains a
   * slice operation.
   *
   * @param spliterator the spliterator describing the relevant portion of the source data
   * @return the exact size if known, or -1 if infinite or unknown
   */
  abstract <P_IN> long exactOutputSizeIfKnown(Spliterator<P_IN> spliterator);

  /**
   * Applies the pipeline stages described by this {@code PipelineHelper} to the provided {@code
   * Spliterator} and send the results to the provided {@code Sink}.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The implementation behaves as if:
   *
   * <pre>{@code
   * copyInto(wrapSink(sink), spliterator);
   * }</pre>
   *
   * @param sink the {@code Sink} to receive the results
   * @param spliterator the spliterator describing the source input to process
   */
  abstract <P_IN, S extends Sink<P_OUT>> S wrapAndCopyInto(S sink, Spliterator<P_IN> spliterator);

  /**
   * Applies the pipeline stages described by this {@code PipelineHelper} to the provided {@code
   * Spliterator} and send the results to the provided {@code Consumer}.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The implementation behaves as if:
   *
   * <pre>{@code
   * copyInto(wrapSink(sink), spliterator);
   * }</pre>
   *
   * @param sink the {@code Consumer} to receive the results
   * @param spliterator the spliterator describing the source input to process
   */
  abstract <P_IN, S extends Consumer<P_OUT>> S wrapAndCopyInto(
      S sink, Spliterator<P_IN> spliterator);

  /**
   * Pushes elements obtained from the {@code Spliterator} into the provided {@code Sink}. If the
   * stream pipeline is known to have short-circuiting stages in it (see {@link
   * StreamOpFlag#SHORT_CIRCUIT}), the {@link Sink#cancellationRequested()} is checked after each
   * element, stopping if cancellation is requested.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This method conforms to the {@code Sink} protocol of calling {@code Sink.begin} before pushing
   * elements, via {@code Sink.accept}, and calling {@code Sink.end} after all elements have been
   * pushed.
   *
   * @param wrappedSink the destination {@code Sink}
   * @param spliterator the source {@code Spliterator}
   */
  abstract <P_IN> void copyInto(Sink<P_IN> wrappedSink, Spliterator<P_IN> spliterator);

  /**
   * Pushes elements obtained from the {@code Spliterator} into the provided {@code Sink}, checking
   * {@link Sink#cancellationRequested()} after each element, and stopping if cancellation is
   * requested.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This method conforms to the {@code Sink} protocol of calling {@code Sink.begin} before pushing
   * elements, via {@code Sink.accept}, and calling {@code Sink.end} after all elements have been
   * pushed or if cancellation is requested.
   *
   * @param wrappedSink the destination {@code Sink}
   * @param spliterator the source {@code Spliterator}
   * @return true if the cancellation was requested
   */
  abstract <P_IN> boolean copyIntoWithCancel(Sink<P_IN> wrappedSink, Spliterator<P_IN> spliterator);

  /**
   * Takes a {@code Sink} that accepts elements of the output type of the {@code PipelineHelper},
   * and wrap it with a {@code Sink} that accepts elements of the input type and implements all the
   * intermediate operations described by this {@code PipelineHelper}, delivering the result into
   * the provided {@code Sink}.
   *
   * @param sink the {@code Sink} to receive the results
   * @return a {@code Sink} that implements the pipeline stages and sends results to the provided
   *     {@code Sink}
   */
  abstract <P_IN> Sink<P_IN> wrapSink(Sink<P_OUT> sink);

  /**
   * Takes a {@code Consumer} that accepts elements of the output type of the {@code
   * PipelineHelper}, and wrap it with a {@code Sink} that accepts elements of the input type and
   * implements all the intermediate operations described by this {@code PipelineHelper}, delivering
   * the result into the provided {@code Consumer}.
   *
   * @param sink the {@code Consumer} to receive the results
   * @return a {@code Sink} that implements the pipeline stages and sends results to the provided
   *     {@code Consumer}
   */
  abstract <P_IN> Sink<P_IN> wrapSink(Consumer<P_OUT> sink);

  /**
   * @param spliterator
   * @param <P_IN>
   * @return
   */
  abstract <P_IN> Spliterator<P_OUT> wrapSpliterator(Spliterator<P_IN> spliterator);

  /**
   * Constructs a {@link Node.Builder} compatible with the output shape of this {@code
   * PipelineHelper}.
   *
   * @param exactSizeIfKnown if >=0 then a builder will be created that has a fixed capacity of
   *     exactly sizeIfKnown elements; if < 0 then the builder has variable capacity. A fixed
   *     capacity builder will fail if an element is added after the builder has reached capacity.
   * @param generator a factory function for array instances
   * @return a {@code Node.Builder} compatible with the output shape of this {@code PipelineHelper}
   */
  abstract Node.Builder<P_OUT> makeNodeBuilder(
      long exactSizeIfKnown, IntFunction<P_OUT[]> generator);

  /**
   * Collects all output elements resulting from applying the pipeline stages to the source {@code
   * Spliterator} into a {@code Node}.
   *
   * <p><b>Implementation Note:</b><br>
   * If the pipeline has no intermediate operations and the source is backed by a {@code Node} then
   * that {@code Node} will be returned (or flattened and then returned). This reduces copying for a
   * pipeline consisting of a stateful operation followed by a terminal operation that returns an
   * array, such as:
   *
   * <pre>{@code
   * stream.sorted().toArray();
   * }</pre>
   *
   * @param spliterator the source {@code Spliterator}
   * @param flatten if true and the pipeline is a parallel pipeline then the {@code Node} returned
   *     will contain no children, otherwise the {@code Node} may represent the root in a tree that
   *     reflects the shape of the computation tree.
   * @param generator a factory function for array instances
   * @return the {@code Node} containing all output elements
   */
  abstract <P_IN> Node<P_OUT> evaluate(
      Spliterator<P_IN> spliterator, boolean flatten, IntFunction<P_OUT[]> generator);
}
