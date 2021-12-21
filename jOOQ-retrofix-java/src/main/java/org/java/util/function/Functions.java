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
 * interface methods in the {@link Function} interface.
 */
public final class Functions {

  /**
   * Returns a composed function that first applies the {@code before} function to its input, and
   * then applies the {@code this_} function to the result. If evaluation of either function throws
   * an exception, it is relayed to the caller of the composed function.
   *
   * @param <R> the type of the result of the {@code this_} function and of the composed function
   * @param <T> the type of the input to the {@code this_} and of the result of the {@code before}
   *     function
   * @param <V> the type of input to the {@code before} function, and to the composed function
   * @param this_ the function to apply after the {@code before} function is applied
   * @param before the function to apply before the {@code this_} function is applied
   * @return a composed function that first applies the {@code before} function and then applies the
   *     {@code this_} function
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if before is null
   * @see #andThen(Function, Function)
   */
  public static <R, T, V> Function<V, R> compose(
      Function<? super T, ? extends R> this_, Function<? super V, ? extends T> before) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(before);
    return (V v) -> this_.apply(before.apply(v));
  }

  /**
   * Returns a composed function that first applies the {@code this_} function to its input, and
   * then applies the {@code after} function to the result. If evaluation of either function throws
   * an exception, it is relayed to the caller of the composed function.
   *
   * @param <R> the type of the result of the {@code this_} function and of the input to the {@code
   *     after} function.
   * @param <T> the type of the input to the {@code this_} function and to the composed function
   * @param <V> the type of output of the {@code after} function, and of the composed function
   * @param this_ the function to apply before the {@code after} function is applied
   * @param after the function to apply after the {@code this_} function is applied
   * @return a composed function that first applies the {@code this_} function and then applies the
   *     {@code after} function
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if after is null
   * @see #compose(Function, Function)
   */
  public static <R, T, V> Function<T, V> andThen(
      Function<? super T, ? extends R> this_, Function<? super R, ? extends V> after) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(after);
    return (T t) -> after.apply(this_.apply(t));
  }

  /**
   * Returns a function that always returns its input argument.
   *
   * @param <T> the type of the input and output objects to the function
   * @return a function that always returns its input argument
   */
  public static <T> Function<T, T> identity() {
    return t -> t;
  }

  private Functions() {}
}
