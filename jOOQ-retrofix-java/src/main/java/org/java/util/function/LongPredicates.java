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
 * interface methods in the {@link LongPredicate} interface.
 */
public final class LongPredicates {

  /**
   * Returns a composed predicate that represents a short-circuiting logical AND of the {@code
   * this_} predicate and another. When evaluating the composed predicate, if the {@code this_}
   * predicate is {@code false}, then the {@code other} predicate is not evaluated.
   *
   * <p>Any exceptions thrown during evaluation of either predicate are relayed to the caller; if
   * evaluation of the {@code this_} predicate throws an exception, the {@code other} predicate will
   * not be evaluated.
   *
   * @param this_ a predicate that will be logically-ANDed with the {@code other} predicate
   * @param other a predicate that will be logically-ANDed with the {@code this_} predicate
   * @return a composed predicate that represents the short-circuiting logical AND of the {@code
   *     this_} predicate and the {@code other} predicate
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if other is null
   */
  public static LongPredicate and(LongPredicate this_, LongPredicate other) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(other);
    return (value) -> this_.test(value) && other.test(value);
  }

  /**
   * Returns a predicate that represents the logical negation of the {@code this_} predicate.
   *
   * @param this_ the predicate that will be negated
   * @return a predicate that represents the logical negation of the {@code this_} predicate
   * @throws NullPointerException if {@code this_} is null
   */
  public static LongPredicate negate(LongPredicate this_) {
    Objects.requireNonNull(this_);
    return (value) -> !this_.test(value);
  }

  /**
   * Returns a composed predicate that represents a short-circuiting logical OR of the {@code this_}
   * predicate and another. When evaluating the composed predicate, if the {@code this_} predicate
   * is {@code true}, then the {@code other} predicate is not evaluated.
   *
   * <p>Any exceptions thrown during evaluation of either predicate are relayed to the caller; if
   * evaluation of the {@code this_} predicate throws an exception, the {@code other} predicate will
   * not be evaluated.
   *
   * @param this_ a predicate that will be logically-ORed with the {@code other} predicate
   * @param other a predicate that will be logically-ORed with the {@code this_} predicate
   * @return a composed predicate that represents the short-circuiting logical OR of the {@code
   *     this_} predicate and the {@code other} predicate
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if other is null
   */
  public static LongPredicate or(LongPredicate this_, LongPredicate other) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(other);
    return (value) -> this_.test(value) || other.test(value);
  }

  private LongPredicates() {}
}
