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
package org.reactivestreams;

/**
 * A {@link Publisher} is a provider of a potentially unbounded number of sequenced elements,
 * publishing them according to the demand received from its {@link Subscriber}(s).
 *
 * <p>A {@link Publisher} can serve multiple {@link Subscriber}s subscribed {@link
 * #subscribe(Subscriber)} dynamically at various points in time.
 *
 * @param <T> the type of element signaled.
 */
public interface Publisher<T> {

  /**
   * Request {@link Publisher} to start streaming data.
   *
   * <p>This is a "factory method" and can be called multiple times, each time starting a new {@link
   * Subscription}.
   *
   * <p>Each {@link Subscription} will work for only a single {@link Subscriber}.
   *
   * <p>A {@link Subscriber} should only subscribe once to a single {@link Publisher}.
   *
   * <p>If the {@link Publisher} rejects the subscription attempt or otherwise fails it will signal
   * the error via {@link Subscriber#onError}.
   *
   * @param s the {@link Subscriber} that will consume signals from this {@link Publisher}
   */
  public void subscribe(Subscriber<? super T> s);
}
