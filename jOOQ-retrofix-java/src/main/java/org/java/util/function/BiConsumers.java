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
 * interface methods in the {@link BiConsumer} interface.
 */
public final class BiConsumers {

  /**
   * Returns a composed {@code BiConsumer} that performs, in sequence, {@code this_} operation
   * followed by the {@code after} operation. If performing either operation throws an exception, it
   * is relayed to the caller of the composed operation. If performing the {@code this_} operation
   * throws an exception, the {@code after} operation will not be performed.
   *
   * @param <T> the type of the first argument to the BiConsumer operation
   * @param <U> the type of the second argument to the BiConsumer operation
   * @param this_ the operation to perform before the {@code after} operation
   * @param after the operation to perform after the {@code this_} operation
   * @return a composed {@code BiConsumer} that performs in sequence the {@code this_} operation
   *     followed by the {@code after} operation
   * @throws NullPointerException if {@code this_} is null
   * @throws NullPointerException if {@code after} is null
   */
  public static <T, U> BiConsumer<T, U> andThen(
      BiConsumer<? super T, ? super U> this_, BiConsumer<? super T, ? super U> after) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(after);
    return (l, r) -> {
      this_.accept(l, r);
      after.accept(l, r);
    };
  }

  private BiConsumers() {}
}
