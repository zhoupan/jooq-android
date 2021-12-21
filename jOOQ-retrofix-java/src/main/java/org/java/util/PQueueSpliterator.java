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
import java.util.ConcurrentModificationException;
import java.util.PriorityQueue;
import org.java.util.function.Consumer;

// Spliterator for java.util.PriorityQueue
final class PQueueSpliterator<E> implements Spliterator<E> {

  // CVS rev. 1.133
  private final PriorityQueue<E> pq;

  // current index, modified on advance/split
  private int index;

  // -1 until first use
  private int fence;

  // initialized when fence set
  private int expectedModCount;

  /** Creates new spliterator covering the given range. */
  private PQueueSpliterator(PriorityQueue<E> pq, int origin, int fence, int expectedModCount) {
    this.pq = pq;
    this.index = origin;
    this.fence = fence;
    this.expectedModCount = expectedModCount;
  }

  static <T> Spliterator<T> spliterator(PriorityQueue<T> pq) {
    return new PQueueSpliterator<T>(pq, 0, -1, 0);
  }

  private int getFence() {
    // initialize fence to size on first use
    int hi;
    if ((hi = fence) < 0) {
      expectedModCount = getModCount(pq);
      hi = fence = getSize(pq);
    }
    return hi;
  }

  @Override
  public PQueueSpliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return (lo >= mid) ? null : new PQueueSpliterator<E>(pq, lo, index = mid, expectedModCount);
  }

  @Override
  @SuppressWarnings("unchecked")
  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    PriorityQueue<E> q = pq;
    if (fence < 0) {
      fence = getSize(q);
      expectedModCount = getModCount(q);
    }
    Object[] es = getQueue(q);
    int i, hi;
    E e;
    for (i = index, index = hi = fence; i < hi; i++) {
      if ((e = (E) es[i]) == null) {
        // must be CME
        break;
      }
      action.accept(e);
    }
    if (getModCount(q) != expectedModCount) {
      throw new ConcurrentModificationException();
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    PriorityQueue<E> q = pq;
    if (fence < 0) {
      fence = getSize(q);
      expectedModCount = getModCount(q);
    }
    int i;
    if ((i = index) < fence) {
      index = i + 1;
      E e;
      if ((e = (E) getQueue(q)[i]) == null || getModCount(q) != expectedModCount) {
        throw new ConcurrentModificationException();
      }
      action.accept(e);
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
    return Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.NONNULL;
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

  private static <T> int getSize(PriorityQueue<T> pq) {
    return U.getInt(pq, SIZE_OFF);
  }

  private static <T> int getModCount(PriorityQueue<T> pq) {
    if (IS_HARMONY) {
      return 0;
    }
    return U.getInt(pq, MODCOUNT_OFF);
  }

  private static <T> Object[] getQueue(PriorityQueue<T> pq) {
    return (Object[]) U.getObject(pq, QUEUE_OFF);
  }

  // Unsafe mechanics
  private static final boolean IS_HARMONY = Spliterators.IS_HARMONY_ANDROID;

  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long SIZE_OFF;

  private static final long MODCOUNT_OFF;

  private static final long QUEUE_OFF;

  static {
    try {
      SIZE_OFF = U.objectFieldOffset(PriorityQueue.class.getDeclaredField("size"));
      if (!IS_HARMONY) {
        MODCOUNT_OFF = U.objectFieldOffset(PriorityQueue.class.getDeclaredField("modCount"));
      } else {
        // unused
        MODCOUNT_OFF = 0L;
      }
      String queueFieldName = IS_HARMONY ? "elements" : "queue";
      QUEUE_OFF = U.objectFieldOffset(PriorityQueue.class.getDeclaredField(queueFieldName));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
