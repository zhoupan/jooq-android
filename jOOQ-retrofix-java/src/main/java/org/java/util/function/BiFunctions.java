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
 * interface methods in the {@link BiFunction} interface.
 */
public final class BiFunctions {

  /**
   * Returns a composed function that first applies the {@code this_} function to its inputs, and
   * then applies the {@code after} function to the result. If evaluation of either function throws
   * an exception, it is relayed to the caller of the composed function.
   *
   * @param <R> the type of the result of the {@code this_} function and the type of the input of
   *     the {@code after} function.
   * @param <T> the type of the first argument to the {@code this_} function
   * @param <U> the type of the second argument to the {@code this_} function
   * @param <V> the type of output of the {@code after} function, and of the composed function
   * @param this_ the {@code BiFunction} to be applied first.
   * @param after the function to apply after the {@code this_} function is applied
   * @return a composed function that first applies the {@code this_} function and then applies the
   *     {@code after} function
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if after is null
   */
  public static <R, T, U, V> BiFunction<T, U, V> andThen(
      BiFunction<? super T, ? super U, ? extends R> this_, Function<? super R, ? extends V> after) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(after);
    return (T t, U u) -> after.apply(this_.apply(t, u));
  }

  private BiFunctions() {}
}
