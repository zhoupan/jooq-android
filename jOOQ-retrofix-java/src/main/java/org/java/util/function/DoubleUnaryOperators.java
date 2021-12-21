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
package org.java.util.function;

import org.java.util.Objects;

/**
 * A place for static default implementations of the new Java 8 default interface methods and static
 * interface methods in the {@link DoubleUnaryOperator} interface.
 */
public final class DoubleUnaryOperators {

  /**
   * Returns a composed operator that first applies the {@code before} operator to its input, and
   * then applies the {@code this_} operator to the result. If evaluation of either operator throws
   * an exception, it is relayed to the caller of the composed operator.
   *
   * @param this_ the operator to apply after the {@code before} operator is applied
   * @param before the operator to apply before the {@code this_} operator is applied
   * @return a composed operator that first applies the {@code before} operator and then applies the
   *     {@code this_} operator
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if before is null
   * @see #andThen(DoubleUnaryOperator, DoubleUnaryOperator)
   */
  public static DoubleUnaryOperator compose(DoubleUnaryOperator this_, DoubleUnaryOperator before) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(before);
    return (double v) -> this_.applyAsDouble(before.applyAsDouble(v));
  }

  /**
   * Returns a composed operator that first applies the {@code this_} operator to its input, and
   * then applies the {@code after} operator to the result. If evaluation of either operator throws
   * an exception, it is relayed to the caller of the composed operator.
   *
   * @param this_ the operator to apply before the {@code after} operator is applied
   * @param after the operator to apply after the {@code this_} operator is applied
   * @return a composed operator that first applies the {@code this_} operator and then applies the
   *     {@code after} operator
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if after is null
   * @see #compose(DoubleUnaryOperator, DoubleUnaryOperator)
   */
  public static DoubleUnaryOperator andThen(DoubleUnaryOperator this_, DoubleUnaryOperator after) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(after);
    return (double t) -> after.applyAsDouble(this_.applyAsDouble(t));
  }

  /**
   * Returns a unary operator that always returns its input argument.
   *
   * @return a unary operator that always returns its input argument
   */
  public static DoubleUnaryOperator identity() {
    return t -> t;
  }

  private DoubleUnaryOperators() {}
}
