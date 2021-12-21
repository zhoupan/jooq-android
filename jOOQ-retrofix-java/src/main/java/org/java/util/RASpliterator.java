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
import java.util.List;
import org.java.util.function.Consumer;

/**
 * An index-based split-by-two, lazily initialized Spliterator covering a List that access elements
 * via {@link List#get}.
 *
 * <p>If access results in an IndexOutOfBoundsException then a ConcurrentModificationException is
 * thrown instead (since the list has been structurally modified while traversing).
 *
 * <p>If the List is an instance of AbstractList then concurrent modification checking is performed
 * using the AbstractList's modCount field.
 */
final class RASpliterator<E> implements Spliterator<E> {

  private final List<E> list;

  // current index, modified on advance/split
  private int index;

  // -1 until used; then one past last index
  private int fence;

  // The following fields are valid if covering an AbstractList
  private final AbstractList<E> alist;

  // initialized when fence set
  private int expectedModCount;

  /** Create new spliterator covering the given range */
  private RASpliterator(List<E> list, int origin, int fence, int expectedModCount) {
    this.list = list;
    this.index = origin;
    this.fence = fence;
    this.alist = // "lgtm[java/abstract-to-concrete-cast]"
        list instanceof AbstractList
            ? // "lgtm[java/abstract-to-concrete-cast]"
            (AbstractList<E>) list
            : null;
    this.expectedModCount = expectedModCount;
  }

  static <T> Spliterator<T> spliterator(List<T> list) {
    return new RASpliterator<T>(list, 0, -1, 0);
  }

  private int getFence() {
    // initialize fence to size on first use
    // (a specialized variant appears in method forEachRemaining)
    int hi;
    List<E> lst = list;
    if ((hi = fence) < 0) {
      if (alist != null) {
        expectedModCount = getModCount(alist);
      }
      hi = fence = lst.size();
    }
    return hi;
  }

  public Spliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return // divide range in half unless too small
    (lo >= mid)
        ? // divide range in half unless too small
        null
        : new RASpliterator<E>(list, lo, index = mid, expectedModCount);
  }

  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    int hi = getFence(), i = index;
    if (i < hi) {
      index = i + 1;
      action.accept(list.get(i));
      checkAbsListModCount(alist, expectedModCount);
      return true;
    }
    return false;
  }

  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    List<E> lst = list;
    int hi = getFence();
    int i = index;
    index = hi;
    try {
      for (; i < hi; ++i) {
        action.accept(lst.get(i));
      }
    } catch (IndexOutOfBoundsException e) {
      // action must have modified the list
      throw new ConcurrentModificationException();
    }
    checkAbsListModCount(alist, expectedModCount);
  }

  public long estimateSize() {
    return (long) (getFence() - index);
  }

  public int characteristics() {
    return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
  }

  @Override
  public long getExactSizeIfKnown() {
    return Spliterators.getExactSizeIfKnown(this);
  }

  @Override
  public boolean hasCharacteristics(int characteristics) {
    return Spliterators.hasCharacteristics(this, characteristics);
  }

  @Override
  public Comparator<? super E> getComparator() {
    return Spliterators.getComparator(this);
  }

  private static void checkAbsListModCount(AbstractList<?> alist, int expectedModCount) {
    if (alist != null && getModCount(alist) != expectedModCount) {
      throw new ConcurrentModificationException();
    }
  }

  private static <T> int getModCount(List<T> lst) {
    return U.getInt(lst, MODCOUNT_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long MODCOUNT_OFF;

  static {
    try {
      MODCOUNT_OFF = U.objectFieldOffset(AbstractList.class.getDeclaredField("modCount"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
