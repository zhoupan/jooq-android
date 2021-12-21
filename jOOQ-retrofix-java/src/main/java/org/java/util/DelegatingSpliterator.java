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
package org.java.util;

import java.util.Comparator;
import org.java.util.function.Consumer;
import org.java.util.function.Consumers;

/**
 * A j8.u.Spliterator implementation that delegates to a j.u.Spliterator.
 *
 * @param <T> the type of the input to the operation
 */
final class DelegatingSpliterator<T> implements Spliterator<T> {

  private final java.util.Spliterator<T> spliter;

  DelegatingSpliterator(java.util.Spliterator<T> spliterator) {
    Objects.requireNonNull(spliterator);
    this.spliter = spliterator;
  }

  @Override
  public boolean tryAdvance(Consumer<? super T> action) {
    return spliter.tryAdvance(new ConsumerDelegate<>(action));
  }

  @Override
  public Spliterator<T> trySplit() {
    java.util.Spliterator<T> spliterator = spliter.trySplit();
    if (spliterator == null) {
      return null;
    }
    return new DelegatingSpliterator<>(spliterator);
  }

  @Override
  public long estimateSize() {
    return spliter.estimateSize();
  }

  @Override
  public int characteristics() {
    return spliter.characteristics();
  }

  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    spliter.forEachRemaining(new ConsumerDelegate<>(action));
  }

  @Override
  public long getExactSizeIfKnown() {
    return spliter.getExactSizeIfKnown();
  }

  @Override
  public boolean hasCharacteristics(int characteristics) {
    return spliter.hasCharacteristics(characteristics);
  }

  @Override
  public Comparator<? super T> getComparator() {
    return spliter.getComparator();
  }

  /**
   * A j.u.f.Consumer implementation that delegates to a j8.u.f.Consumer.
   *
   * @param <T> the type of the input to the operation
   */
  private static final class ConsumerDelegate<T> implements java.util.function.Consumer<T> {

    private final Consumer<T> delegate;

    ConsumerDelegate(Consumer<T> delegate) {
      Objects.requireNonNull(delegate);
      this.delegate = delegate;
    }

    @Override
    public void accept(T t) {
      delegate.accept(t);
    }

    @Override
    public java.util.function.Consumer<T> andThen(java.util.function.Consumer<? super T> after) {
      Objects.requireNonNull(after);
      return new ConsumerDelegate<T>(
          Consumers.andThen(
              delegate,
              new org.java.util.function.Consumer<T>() {

                @Override
                public void accept(T t) {
                  after.accept(t);
                }
              }));
    }
  }
}
