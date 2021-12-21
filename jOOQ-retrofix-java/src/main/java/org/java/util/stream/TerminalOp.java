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

/**
 * An operation in a stream pipeline that takes a stream as input and produces a result or
 * side-effect. A {@code TerminalOp} has an input type and stream shape, and a result type. A {@code
 * TerminalOp} also has a set of <em>operation flags</em> that describes how the operation processes
 * elements of the stream (such as short-circuiting or respecting encounter order; see {@link
 * StreamOpFlag}).
 *
 * <p>A {@code TerminalOp} must provide a sequential and parallel implementation of the operation
 * relative to a given stream source and set of intermediate operations.
 *
 * @param <E_IN> the type of input elements
 * @param <R> the type of the result
 * @since 1.8
 */
interface TerminalOp<E_IN, R> {

  /**
   * Gets the shape of the input type of this operation.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default returns {@code StreamShape.REFERENCE}.
   *
   * @return StreamShape of the input type of this operation
   */
  StreamShape inputShape();

  /**
   * Gets the stream flags of the operation. Terminal operations may set a limited subset of the
   * stream flags defined in {@link StreamOpFlag}, and these flags are combined with the previously
   * combined stream and intermediate operation flags for the pipeline.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation returns zero.
   *
   * @return the stream flags for this operation
   * @see StreamOpFlag
   */
  int getOpFlags();

  /**
   * Performs a parallel evaluation of the operation using the specified {@code PipelineHelper},
   * which describes the upstream intermediate operations.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default performs a sequential evaluation of the operation using the specified {@code
   * PipelineHelper}.
   *
   * @param helper the pipeline helper
   * @param spliterator the source spliterator
   * @return the result of the evaluation
   */
  <P_IN> R evaluateParallel(PipelineHelper<E_IN> helper, Spliterator<P_IN> spliterator);

  /**
   * Performs a sequential evaluation of the operation using the specified {@code PipelineHelper},
   * which describes the upstream intermediate operations.
   *
   * @param helper the pipeline helper
   * @param spliterator the source spliterator
   * @return the result of the evaluation
   */
  <P_IN> R evaluateSequential(PipelineHelper<E_IN> helper, Spliterator<P_IN> spliterator);
}
