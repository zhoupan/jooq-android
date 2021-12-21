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
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.ReentrantLock;
import org.java.util.function.Consumer;

/**
 * A customized variant of Spliterators.IteratorSpliterator for LinkedBlockingDeques. Keep this
 * class in sync with (very similar) LBQSpliterator.
 *
 * <p>The returned spliterator is <i>weakly consistent</i>.
 *
 * <p>The {@code Spliterator} reports {@link Spliterator#CONCURRENT}, {@link Spliterator#ORDERED},
 * and {@link Spliterator#NONNULL}.
 *
 * <p>The {@code Spliterator} implements {@code trySplit} to permit limited parallelism.
 *
 * @param <E> the type of elements held in the LinkedBlockingDeque
 */
final class LBDSpliterator<E> implements Spliterator<E> {

  // CVS rev. 1.83
  // max batch array size
  private static final int MAX_BATCH = 1 << 25;

  private final LinkedBlockingDeque<E> queue;

  private final ReentrantLock queueLock;

  // current node; null until initialized
  private Object current;

  // batch size for splits
  private int batch;

  // true when no more nodes
  private boolean exhausted;

  // size estimate
  private long est;

  private LBDSpliterator(LinkedBlockingDeque<E> queue) {
    this.queue = queue;
    this.est = queue.size();
    this.queueLock = getQueueLock(queue);
  }

  static <T> Spliterator<T> spliterator(LinkedBlockingDeque<T> queue) {
    return new LBDSpliterator<T>(queue);
  }

  Object succ(Object p) {
    return (p == (p = getNextNode(p))) ? getQueueFirst(queue) : p;
  }

  @Override
  public int characteristics() {
    return (Spliterator.ORDERED | Spliterator.NONNULL | Spliterator.CONCURRENT);
  }

  @Override
  public long estimateSize() {
    return est;
  }

  @Override
  public void forEachRemaining(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    if (!exhausted) {
      exhausted = true;
      Object p = current;
      current = null;
      forEachFrom(action, p);
    }
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

  @Override
  public boolean tryAdvance(Consumer<? super E> action) {
    Objects.requireNonNull(action);
    if (!exhausted) {
      E e = null;
      ReentrantLock lock = queueLock;
      lock.lock();
      try {
        Object p;
        if ((p = current) != null || (p = getQueueFirst(queue)) != null)
          do {
            e = getNodeItem(p);
            p = succ(p);
          } while (e == null && p != null);
        if ((current = p) == null) exhausted = true;
      } finally {
        // checkInvariants();
        lock.unlock();
      }
      if (e != null) {
        action.accept(e);
        return true;
      }
    }
    return false;
  }

  @Override
  public Spliterator<E> trySplit() {
    Object h;
    LinkedBlockingDeque<E> q = queue;
    if (!exhausted
        && ((h = current) != null || (h = getQueueFirst(q)) != null)
        && getNextNode(h) != null) {
      int n = batch = Math.min(batch + 1, MAX_BATCH);
      Object[] a = new Object[n];
      ReentrantLock lock = queueLock;
      int i = 0;
      Object p = current;
      lock.lock();
      try {
        if (p != null || (p = getQueueFirst(q)) != null)
          for (; p != null && i < n; p = succ(p)) if ((a[i] = getNodeItem(p)) != null) i++;
      } finally {
        // checkInvariants();
        lock.unlock();
      }
      if ((current = p) == null) {
        est = 0L;
        exhausted = true;
      } else if ((est -= i) < 0L) est = 0L;
      if (i > 0)
        return Spliterators.spliterator(
            a, 0, i, (Spliterator.ORDERED | Spliterator.NONNULL | Spliterator.CONCURRENT));
    }
    return null;
  }

  /**
   * Runs action on each element found during a traversal starting at p. If p is null, traversal
   * starts at head.
   */
  void forEachFrom(Consumer<? super E> action, Object p) {
    // Extract batches of elements while holding the lock; then
    // run the action on the elements while not
    ReentrantLock lock = queueLock;
    // max number of elements per batch
    final int batchSize = 64;
    // container for batch of elements
    Object[] es = null;
    int n, len = 0;
    do {
      lock.lock();
      try {
        if (es == null) {
          if (p == null) p = getQueueFirst(queue);
          for (Object q = p; q != null; q = succ(q))
            if (getNodeItem(q) != null && ++len == batchSize) break;
          es = new Object[len];
        }
        for (n = 0; p != null && n < len; p = succ(p)) if ((es[n] = getNodeItem(p)) != null) n++;
      } finally {
        // checkInvariants();
        lock.unlock();
      }
      for (int i = 0; i < n; i++) {
        @SuppressWarnings("unchecked")
        E e = (E) es[i];
        action.accept(e);
      }
    } while (n > 0 && p != null);
  }

  private static ReentrantLock getQueueLock(LinkedBlockingDeque<?> queue) {
    return (ReentrantLock) U.getObject(queue, LOCK_OFF);
  }

  private static Object getQueueFirst(LinkedBlockingDeque<?> queue) {
    return U.getObject(queue, FIRST_OFF);
  }

  /** Returns node.next as an Object */
  private static Object getNextNode(Object node) {
    return U.getObject(node, NODE_NEXT_OFF);
  }

  /** Returns node.item as a T */
  private static <T> T getNodeItem(Object node) {
    return (T) U.getObject(node, NODE_ITEM_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long FIRST_OFF;

  private static final long LOCK_OFF;

  private static final long NODE_ITEM_OFF;

  private static final long NODE_NEXT_OFF;

  static {
    try {
      Class<?> nc = Class.forName("java.util.concurrent.LinkedBlockingDeque$Node");
      FIRST_OFF = U.objectFieldOffset(LinkedBlockingDeque.class.getDeclaredField("first"));
      LOCK_OFF = U.objectFieldOffset(LinkedBlockingDeque.class.getDeclaredField("lock"));
      NODE_ITEM_OFF = U.objectFieldOffset(nc.getDeclaredField("item"));
      NODE_NEXT_OFF = U.objectFieldOffset(nc.getDeclaredField("next"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
