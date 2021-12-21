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
import java.util.concurrent.PriorityBlockingQueue;
import org.java.util.function.Consumer;

/*
 * Spliterator for java.util.concurrent.PriorityBlockingQueue.
 * Immutable snapshot spliterator that binds to elements "late".
 */
final class PBQueueSpliterator<E> implements Spliterator<E> {

  // CVS rev. 1.142
  private final PriorityBlockingQueue<E> queue;

  // null until late-bound-initialized
  private Object[] array;

  private int index;

  private int fence;

  private PBQueueSpliterator(PriorityBlockingQueue<E> queue, Object[] array, int index, int fence) {
    this.queue = queue;
    this.array = array;
    this.index = index;
    this.fence = fence;
  }

  static <T> Spliterator<T> spliterator(PriorityBlockingQueue<T> queue) {
    return new PBQueueSpliterator<T>(queue, null, 0, -1);
  }

  private int getFence() {
    if (array == null) fence = (array = queue.toArray()).length;
    return fence;
  }

  @Override
  public PBQueueSpliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return (lo >= mid) ? null : new PBQueueSpliterator<E>(queue, array, lo, index = mid);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    int hi = getFence(), lo = index;
    Object[] es = array;
    // ensure exhaustion
    index = hi;
    for (int i = lo; i < hi; i++) action.accept((E) es[i]);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    if (getFence() > index && index >= 0) {
      action.accept((E) array[index++]);
      return true;
    }
    return false;
  }

  @Override
  public long estimateSize() {
    return getFence() - index;
  }

  @Override
  public int characteristics() {
    return (Spliterator.NONNULL | Spliterator.SIZED | Spliterator.SUBSIZED);
  }

  @Override
  public Comparator<? super E> getComparator() {
    return Spliterators.getComparator(this);
  }

  @Override
  public long getExactSizeIfKnown() {
    return Spliterators.getExactSizeIfKnown(this);
  }

  @Override
  public boolean hasCharacteristics(int characteristics) {
    return Spliterators.hasCharacteristics(this, characteristics);
  }
}
