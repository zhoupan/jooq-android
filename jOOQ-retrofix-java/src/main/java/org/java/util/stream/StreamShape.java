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

/**
 * An enum describing the known shape specializations for stream abstractions. Each will correspond
 * to a specific subinterface of {@link BaseStream} (e.g., {@code REFERENCE} corresponds to {@code
 * Stream}, {@code INT_VALUE} corresponds to {@code IntStream}). Each may also correspond to
 * specializations of value-handling abstractions such as {@code Spliterator}, {@code Consumer},
 * etc.
 *
 * <p><b>API Note:</b><br>
 * This enum is used by implementations to determine compatibility between streams and operations
 * (i.e., if the output shape of a stream is compatible with the input shape of the next operation).
 *
 * <p>Some APIs require you to specify both a generic type and a stream shape for input or output
 * elements, such as {@link TerminalOp} which has both generic type parameters for its input types,
 * and a getter for the input shape. When representing primitive streams in this way, the generic
 * type parameter should correspond to the wrapper type for that primitive type.
 *
 * @since 1.8
 */
enum StreamShape {

  /**
   * The shape specialization corresponding to {@code Stream} and elements that are object
   * references.
   */
  REFERENCE,
  /**
   * The shape specialization corresponding to {@code IntStream} and elements that are {@code int}
   * values.
   */
  INT_VALUE,
  /**
   * The shape specialization corresponding to {@code LongStream} and elements that are {@code long}
   * values.
   */
  LONG_VALUE,
  /**
   * The shape specialization corresponding to {@code DoubleStream} and elements that are {@code
   * double} values.
   */
  DOUBLE_VALUE
}
