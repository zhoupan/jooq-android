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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import org.java.util.function.Consumer;

/** Index-based split-by-two, lazily initialized Spliterator for ArrayLists. */
final class ArrayListSpliterator<E> implements Spliterator<E> {

  // CVS rev. 1.70
  /*
   * If ArrayLists were immutable, or structurally immutable (no
   * adds, removes, etc), we could implement their spliterators
   * with Arrays.spliterator. Instead we detect as much
   * interference during traversal as practical without
   * sacrificing much performance. We rely primarily on
   * modCounts. These are not guaranteed to detect concurrency
   * violations, and are sometimes overly conservative about
   * within-thread interference, but detect enough problems to
   * be worthwhile in practice. To carry this out, we (1) lazily
   * initialize fence and expectedModCount until the latest
   * point that we need to commit to the state we are checking
   * against; thus improving precision.  (This doesn't apply to
   * SubLists, that create spliterators with current non-lazy
   * values).  (2) We perform only a single
   * ConcurrentModificationException check at the end of forEach
   * (the most performance-sensitive method). When using forEach
   * (as opposed to iterators), we can normally only detect
   * interference after actions, not before. Further
   * CME-triggering checks apply to all other possible
   * violations of assumptions for example null or too-small
   * elementData array given its size(), that could only have
   * occurred due to interference.  This allows the inner loop
   * of forEach to run without any further checks, and
   * simplifies lambda-resolution. While this does entail a
   * number of checks, note that in the common case of
   * list.stream().forEach(a), no checks or other computation
   * occur anywhere other than inside forEach itself.  The other
   * less-often-used methods cannot take advantage of most of
   * these streamlinings.
   */
  private final ArrayList<E> list;

  // current index, modified on advance/split
  private int index;

  // -1 until used; then one past last index
  private int fence;

  // initialized when fence set
  private int expectedModCount;

  /** Create new spliterator covering the given range */
  private ArrayListSpliterator(ArrayList<E> list, int origin, int fence, int expectedModCount) {
    // OK if null unless traversed
    this.list = list;
    this.index = origin;
    this.fence = fence;
    this.expectedModCount = expectedModCount;
  }

  static <T> Spliterator<T> spliterator(ArrayList<T> list) {
    return new ArrayListSpliterator<T>(list, 0, -1, 0);
  }

  private int getFence() {
    // initialize fence to size on first use
    // (a specialized variant appears in method forEach)
    int hi;
    if ((hi = fence) < 0) {
      ArrayList<E> lst = list;
      expectedModCount = getModCount(lst);
      hi = fence = getSize(lst);
    }
    return hi;
  }

  @Override
  public ArrayListSpliterator<E> trySplit() {
    int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
    return // divide range in half unless too small
    (lo >= mid)
        ? // divide range in half unless too small
        null
        : new ArrayListSpliterator<E>(list, lo, index = mid, expectedModCount);
  }

  @Override
  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    int hi = getFence(), i = index;
    if (i < hi) {
      index = i + 1;
      @SuppressWarnings("unchecked")
      E e = (E) getData(list)[i];
      action.accept(e);
      if (expectedModCount != getModCount(list)) {
        throw new ConcurrentModificationException();
      }
      return true;
    }
    return false;
  }

  @Override
  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    // hoist accesses and checks from loop
    int i, hi, mc;
    Object[] a;
    ArrayList<E> lst = list;
    if ((a = getData(lst)) != null) {
      if ((hi = fence) < 0) {
        mc = getModCount(lst);
        hi = getSize(lst);
      } else {
        mc = expectedModCount;
      }
      if ((i = index) >= 0 && (index = hi) <= a.length) {
        for (; i < hi; ++i) {
          @SuppressWarnings("unchecked")
          E e = (E) a[i];
          action.accept(e);
        }
        if (mc == getModCount(lst)) {
          return;
        }
      }
    }
    throw new ConcurrentModificationException();
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

  private static <T> int getSize(ArrayList<T> lst) {
    return U.getInt(lst, SIZE_OFF);
  }

  private static <T> int getModCount(ArrayList<T> lst) {
    return U.getInt(lst, MODCOUNT_OFF);
  }

  private static <T> Object[] getData(ArrayList<T> lst) {
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
      SIZE_OFF = U.objectFieldOffset(ArrayList.class.getDeclaredField("size"));
      String arrayFieldName = Spliterators.IS_HARMONY_ANDROID ? "array" : "elementData";
      DATA_OFF = U.objectFieldOffset(ArrayList.class.getDeclaredField(arrayFieldName));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
