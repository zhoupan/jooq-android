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

import java.util.AbstractList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import org.java.util.function.Consumer;

/** Similar to ArrayListSpliterator */
final class VectorSpliterator<E> implements Spliterator<E> {

  // CVS rev. 1.57
  private final Vector<E> list;

  private Object[] array;

  // current index, modified on advance/split
  private int index;

  // -1 until used; then one past last index
  private int fence;

  // initialized when fence set
  private int expectedModCount;

  /** Create new spliterator covering the given range */
  private VectorSpliterator(
      Vector<E> list, Object[] array, int origin, int fence, int expectedModCount) {
    this.list = list;
    this.array = array;
    this.index = origin;
    this.fence = fence;
    this.expectedModCount = expectedModCount;
  }

  static <T> Spliterator<T> spliterator(Vector<T> vec) {
    return new VectorSpliterator<T>(vec, null, 0, -1, 0);
  }

  private int getFence() {
    // initialize on first use
    int hi;
    if ((hi = fence) < 0) {
      synchronized (list) {
        array = getData(list);
        expectedModCount = getModCount(list);
        hi = fence = getSize(list);
      }
    }
    return hi;
  }

  @Override
  public Spliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return (lo >= mid)
        ? null
        : new VectorSpliterator<E>(list, array, lo, index = mid, expectedModCount);
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    int i;
    if (getFence() > (i = index)) {
      index = i + 1;
      action.accept((E) array[i]);
      if (expectedModCount != getModCount(list)) {
        throw new ConcurrentModificationException();
      }
      return true;
    }
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    int hi = getFence();
    Object[] a = array;
    int i;
    for (i = index, index = hi; i < hi; i++) {
      action.accept((E) a[i]);
    }
    if (getModCount(list) != expectedModCount) {
      throw new ConcurrentModificationException();
    }
  }

  @Override
  public long estimateSize() {
    return getFence() - index;
  }

  @Override
  public int characteristics() {
    return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
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

  private static <T> int getSize(Vector<T> lst) {
    return U.getInt(lst, SIZE_OFF);
  }

  private static <T> int getModCount(Vector<T> lst) {
    return U.getInt(lst, MODCOUNT_OFF);
  }

  private static <T> Object[] getData(Vector<T> lst) {
    return (Object[]) U.getObject(lst, DATA_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long SIZE_OFF;

  private static final long MODCOUNT_OFF;

  private static final long DATA_OFF;

  static {
    try {
      MODCOUNT_OFF = U.objectFieldOffset(AbstractList.class.getDeclaredField("modCount"));
      SIZE_OFF = U.objectFieldOffset(Vector.class.getDeclaredField("elementCount"));
      DATA_OFF = U.objectFieldOffset(Vector.class.getDeclaredField("elementData"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
