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
package io.r2dbc.spi;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/**
 * A {@link Closeable} is an object that can be closed. The {@link #close()} method is invoked to
 * release resources that the object is holding (such as open connections).
 */
@FunctionalInterface
public interface Closeable {

  /**
   * Close this object and release any resources associated with it. If the object is already
   * closed, then {@link Publisher#subscribe(Subscriber) subscriptions} complete successfully and
   * the close operation has no effect.
   *
   * @return a {@link Publisher} that termination is complete
   */
  Publisher<Void> close();
}
