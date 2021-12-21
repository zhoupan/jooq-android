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

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import org.java.util.function.Consumer;

// NOTE: this implementation can only be used on Java 8 or below!
// The recent substantial Java 9 rewrite of ArrayDeque starting
// with CVS rev. 1.75 precludes this from being applicable on Java 9.
// Cf. https://sourceforge.net/p/streamsupport/tickets/273/
// and https://sourceforge.net/p/streamsupport/tickets/299/
final class ArrayDequeSpliterator<E> implements Spliterator<E> {

  // CVS rev. 1.74
  private final ArrayDeque<E> deq;

  // -1 until first use
  private int fence;

  // current index, modified on traverse/split
  private int index;

  /** Constructs a new spliterator covering the given array and range. */
  private ArrayDequeSpliterator(ArrayDeque<E> deq, int origin, int fence) {
    this.deq = deq;
    this.index = origin;
    this.fence = fence;
  }

  static <T> Spliterator<T> spliterator(ArrayDeque<T> deque) {
    return new ArrayDequeSpliterator<T>(deque, -1, -1);
  }

  /** Ensures late-binding initialization; then returns fence. */
  private int getFence() {
    // force initialization
    int t;
    if ((t = fence) < 0) {
      t = fence = getTail(deq);
      index = getHead(deq);
    }
    return t;
  }

  public ArrayDequeSpliterator<E> trySplit() {
    int t = getFence(), h = index, n = getData(deq).length;
    if (h != t && ((h + 1) & (n - 1)) != t) {
      if (h > t) {
        t += n;
      }
      int m = ((h + t) >>> 1) & (n - 1);
      return new ArrayDequeSpliterator<E>(deq, h, index = m);
    }
    return null;
  }

  @Override
  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    Object[] a = getData(deq);
    int m = a.length - 1, f = getFence(), i = index;
    index = f;
    while (i != f) {
      @SuppressWarnings("unchecked")
      E e = (E) a[i];
      i = (i + 1) & m;
      if (e == null) {
        throw new ConcurrentModificationException();
      }
      action.accept(e);
    }
  }

  @Override
  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    Object[] a = getData(deq);
    int m = a.length - 1, f = getFence(), i = index;
    if (i != fence) {
      @SuppressWarnings("unchecked")
      E e = (E) a[i];
      index = (i + 1) & m;
      if (e == null) {
        throw new ConcurrentModificationException();
      }
      action.accept(e);
      return true;
    }
    return false;
  }

  @Override
  public long estimateSize() {
    int n = getFence() - index;
    if (n < 0) {
      n += getData(deq).length;
    }
    return n;
  }

  @Override
  public int characteristics() {
    return Spliterator.NONNULL | Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
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

  private static <T> int getTail(ArrayDeque<T> deq) {
    return U.getInt(deq, TAIL_OFF);
  }

  private static <T> int getHead(ArrayDeque<T> deq) {
    return U.getInt(deq, HEAD_OFF);
  }

  private static <T> Object[] getData(ArrayDeque<T> deq) {
    return (Object[]) U.getObject(deq, DATA_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long TAIL_OFF;

  private static final long HEAD_OFF;

  private static final long DATA_OFF;

  static {
    try {
      TAIL_OFF = U.objectFieldOffset(ArrayDeque.class.getDeclaredField("tail"));
      HEAD_OFF = U.objectFieldOffset(ArrayDeque.class.getDeclaredField("head"));
      DATA_OFF = U.objectFieldOffset(ArrayDeque.class.getDeclaredField("elements"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
