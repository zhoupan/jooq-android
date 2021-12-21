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
 * A {@link Subscription} represents a one-to-one lifecycle of a {@link Subscriber} subscribing to a
 * {@link Publisher}.
 *
 * <p>It can only be used once by a single {@link Subscriber}.
 *
 * <p>It is used to both signal desire for data and cancel demand (and allow resource cleanup).
 */
public interface Subscription {

  /**
   * No events will be sent by a {@link Publisher} until demand is signaled via this method.
   *
   * <p>It can be called however often and whenever needed—but if the outstanding cumulative demand
   * ever becomes Long.MAX_VALUE or more, it may be treated by the {@link Publisher} as "effectively
   * unbounded".
   *
   * <p>Whatever has been requested can be sent by the {@link Publisher} so only signal demand for
   * what can be safely handled.
   *
   * <p>A {@link Publisher} can send less than is requested if the stream ends but then must emit
   * either {@link Subscriber#onError(Throwable)} or {@link Subscriber#onComplete()}.
   *
   * @param n the strictly positive number of elements to requests to the upstream {@link Publisher}
   */
  public void request(long n);

  /**
   * Request the {@link Publisher} to stop sending data and clean up resources.
   *
   * <p>Data may still be sent to meet previously signalled demand after calling cancel.
   */
  public void cancel();
}
