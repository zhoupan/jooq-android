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
 * Will receive call to {@link #onSubscribe(Subscription)} once after passing an instance of {@link
 * Subscriber} to {@link Publisher#subscribe(Subscriber)}.
 *
 * <p>No further notifications will be received until {@link Subscription#request(long)} is called.
 *
 * <p>After signaling demand:
 *
 * <ul>
 *   <li>One or more invocations of {@link #onNext(Object)} up to the maximum number defined by
 *       {@link Subscription#request(long)}
 *   <li>Single invocation of {@link #onError(Throwable)} or {@link Subscriber#onComplete()} which
 *       signals a terminal state after which no further events will be sent.
 * </ul>
 *
 * <p>Demand can be signaled via {@link Subscription#request(long)} whenever the {@link Subscriber}
 * instance is capable of handling more.
 *
 * @param <T> the type of element signaled.
 */
public interface Subscriber<T> {

  /**
   * Invoked after calling {@link Publisher#subscribe(Subscriber)}.
   *
   * <p>No data will start flowing until {@link Subscription#request(long)} is invoked.
   *
   * <p>It is the responsibility of this {@link Subscriber} instance to call {@link
   * Subscription#request(long)} whenever more data is wanted.
   *
   * <p>The {@link Publisher} will send notifications only in response to {@link
   * Subscription#request(long)}.
   *
   * @param s {@link Subscription} that allows requesting data via {@link
   *     Subscription#request(long)}
   */
  public void onSubscribe(Subscription s);

  /**
   * Data notification sent by the {@link Publisher} in response to requests to {@link
   * Subscription#request(long)}.
   *
   * @param t the element signaled
   */
  public void onNext(T t);

  /**
   * Failed terminal state.
   *
   * <p>No further events will be sent even if {@link Subscription#request(long)} is invoked again.
   *
   * @param t the throwable signaled
   */
  public void onError(Throwable t);

  /**
   * Successful terminal state.
   *
   * <p>No further events will be sent even if {@link Subscription#request(long)} is invoked again.
   */
  public void onComplete();
}
