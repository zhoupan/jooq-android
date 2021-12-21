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

import java.nio.ByteBuffer;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/** Represents a handle to a large binary object. */
public interface Blob {

  /**
   * Creates a new {@link Blob} wrapper that is backed by a {@link Publisher} of {@link ByteBuffer}.
   * The wrapper subscribes and cancels the subscription immediately on {@link #discard()}.
   *
   * @param p the backing {@link Publisher} of {@link ByteBuffer}.
   * @return the {@link Blob} wrapper
   */
  static Blob from(Publisher<ByteBuffer> p) {
    Assert.requireNonNull(p, "Publisher must not be null");
    DefaultLob<ByteBuffer> lob = new DefaultLob<>(p);
    return new Blob() {

      @Override
      public Publisher<ByteBuffer> stream() {
        return lob.stream();
      }

      @Override
      public Publisher<Void> discard() {
        return lob.discard();
      }
    };
  }

  /**
   * Returns the content stream as a {@link Publisher} emitting {@link ByteBuffer} chunks.
   *
   * <p>The content stream can be consumed ("subscribed to") only once. Subsequent consumptions
   * result in a {@link IllegalStateException}.
   *
   * <p>Once {@link Publisher#subscribe(Subscriber) subscribed}, {@link Subscription#cancel()
   * canceling} the subscription releases resources associated with this {@link Blob}.
   *
   * @return a {@link Publisher} emitting {@link ByteBuffer} chunks.
   */
  Publisher<ByteBuffer> stream();

  /**
   * Release any resources held by the {@link Clob} when not subscribing to the {@link #stream()
   * stream content}.
   *
   * @return a {@link Publisher} that termination is complete
   */
  Publisher<Void> discard();
}
