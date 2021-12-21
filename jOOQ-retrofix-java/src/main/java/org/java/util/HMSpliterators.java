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

import java.util.Collection;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.java.util.function.Consumer;

final class HMSpliterators {

  // CVS rev. 1.10
  private HMSpliterators() {}

  static <K> Spliterator<K> getKeySetSpliterator(Set<K> keySet) {
    return new KeySpliterator<K, Object>(getHashMapFromKeySet(keySet), 0, -1, 0, 0);
  }

  static <K, V> Spliterator<Map.Entry<K, V>> getEntrySetSpliterator(Set<Map.Entry<K, V>> entrySet) {
    return new EntrySpliterator<K, V>(getHashMapFromEntrySet(entrySet), 0, -1, 0, 0);
  }

  static <V> Spliterator<V> getValuesSpliterator(Collection<V> values) {
    return new ValueSpliterator<Object, V>(getHashMapFromValues(values), 0, -1, 0, 0);
  }

  static <E> Spliterator<E> getHashSetSpliterator(HashSet<E> hashSet) {
    return new KeySpliterator<E, Object>(getHashMapFromHashSet(hashSet), 0, -1, 0, 0);
  }

  private static final class KeySpliterator<K, V> extends HashMapSpliterator<K, V>
      implements Spliterator<K> {

    KeySpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
      super(m, origin, fence, est, expectedModCount);
    }

    @Override
    public KeySpliterator<K, V> trySplit() {
      int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
      return (lo >= mid || current != null)
          ? null
          : new KeySpliterator<K, V>(map, lo, index = mid, est >>>= 1, expectedModCount);
    }

    @Override
    public void forEachRemaining(Consumer<? super K> action) {
      Objects.requireNonNull(action);
      int i, hi, mc;
      HashMap<K, V> m = map;
      Object[] tab = getTable(m);
      if ((hi = fence) < 0) {
        mc = expectedModCount = getModCount(m);
        hi = fence = (tab == null) ? 0 : tab.length;
      } else {
        mc = expectedModCount;
      }
      if (tab != null
          && tab.length >= hi
          && (i = index) >= 0
          && (i < (index = hi) || current != null)) {
        Object p = current;
        current = null;
        do {
          if (p == null) {
            p = tab[i++];
          } else {
            action.accept(HashMapSpliterator.<K>getNodeKey(p));
            p = getNextNode(p);
          }
        } while (p != null || i < hi);
        if (mc != getModCount(m)) {
          throw new ConcurrentModificationException();
        }
      }
    }

    @Override
    public boolean tryAdvance(Consumer<? super K> action) {
      Objects.requireNonNull(action);
      int hi;
      Object[] tab = getTable(map);
      if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
        while (current != null || index < hi) {
          if (current == null) {
            current = tab[index++];
          } else {
            K k = getNodeKey(current);
            current = getNextNode(current);
            action.accept(k);
            if (expectedModCount != getModCount(map)) {
              throw new ConcurrentModificationException();
            }
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public int characteristics() {
      return (fence < 0 || est == map.size() ? Spliterator.SIZED : 0) | Spliterator.DISTINCT;
    }

    @Override
    public Comparator<? super K> getComparator() {
      return Spliterators.getComparator(null);
    }
  }

  private static final class ValueSpliterator<K, V> extends HashMapSpliterator<K, V>
      implements Spliterator<V> {

    ValueSpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
      super(m, origin, fence, est, expectedModCount);
    }

    @Override
    public ValueSpliterator<K, V> trySplit() {
      int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
      return (lo >= mid || current != null)
          ? null
          : new ValueSpliterator<K, V>(map, lo, index = mid, est >>>= 1, expectedModCount);
    }

    @Override
    public void forEachRemaining(Consumer<? super V> action) {
      Objects.requireNonNull(action);
      int i, hi, mc;
      HashMap<K, V> m = map;
      Object[] tab = getTable(m);
      if ((hi = fence) < 0) {
        mc = expectedModCount = getModCount(m);
        hi = fence = (tab == null) ? 0 : tab.length;
      } else {
        mc = expectedModCount;
      }
      if (tab != null
          && tab.length >= hi
          && (i = index) >= 0
          && (i < (index = hi) || current != null)) {
        Object p = current;
        current = null;
        do {
          if (p == null) {
            p = tab[i++];
          } else {
            action.accept(HashMapSpliterator.<V>getNodeValue(p));
            p = getNextNode(p);
          }
        } while (p != null || i < hi);
        if (mc != getModCount(m)) {
          throw new ConcurrentModificationException();
        }
      }
    }

    @Override
    public boolean tryAdvance(Consumer<? super V> action) {
      Objects.requireNonNull(action);
      int hi;
      Object[] tab = getTable(map);
      if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
        while (current != null || index < hi) {
          if (current == null) {
            current = tab[index++];
          } else {
            V v = getNodeValue(current);
            current = getNextNode(current);
            action.accept(v);
            if (expectedModCount != getModCount(map)) {
              throw new ConcurrentModificationException();
            }
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public int characteristics() {
      return (fence < 0 || est == map.size() ? Spliterator.SIZED : 0);
    }

    @Override
    public Comparator<? super V> getComparator() {
      return Spliterators.getComparator(null);
    }
  }

  private static final class EntrySpliterator<K, V> extends HashMapSpliterator<K, V>
      implements Spliterator<Map.Entry<K, V>> {

    EntrySpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
      super(m, origin, fence, est, expectedModCount);
    }

    @Override
    public EntrySpliterator<K, V> trySplit() {
      int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
      return (lo >= mid || current != null)
          ? null
          : new EntrySpliterator<K, V>(map, lo, index = mid, est >>>= 1, expectedModCount);
    }

    @Override
    public void forEachRemaining(Consumer<? super Map.Entry<K, V>> action) {
      Objects.requireNonNull(action);
      int i, hi, mc;
      HashMap<K, V> m = map;
      Object[] tab = getTable(m);
      if ((hi = fence) < 0) {
        mc = expectedModCount = getModCount(m);
        hi = fence = (tab == null) ? 0 : tab.length;
      } else {
        mc = expectedModCount;
      }
      if (tab != null
          && tab.length >= hi
          && (i = index) >= 0
          && (i < (index = hi) || current != null)) {
        Object p = current;
        current = null;
        do {
          if (p == null) {
            p = tab[i++];
          } else {
            action.accept((Map.Entry<K, V>) p);
            p = getNextNode(p);
          }
        } while (p != null || i < hi);
        if (mc != getModCount(m)) {
          throw new ConcurrentModificationException();
        }
      }
    }

    @Override
    public boolean tryAdvance(Consumer<? super Map.Entry<K, V>> action) {
      Objects.requireNonNull(action);
      int hi;
      Object[] tab = getTable(map);
      if (tab != null && tab.length >= (hi = getFence()) && index >= 0) {
        while (current != null || index < hi) {
          if (current == null) {
            current = tab[index++];
          } else {
            Map.Entry<K, V> e = (Map.Entry<K, V>) current;
            current = getNextNode(current);
            action.accept(e);
            if (expectedModCount != getModCount(map)) {
              throw new ConcurrentModificationException();
            }
            return true;
          }
        }
      }
      return false;
    }

    @Override
    public int characteristics() {
      return (fence < 0 || est == map.size() ? Spliterator.SIZED : 0) | Spliterator.DISTINCT;
    }

    @Override
    public Comparator<? super Entry<K, V>> getComparator() {
      return Spliterators.getComparator(null);
    }
  }

  private abstract static class HashMapSpliterator<K, V> {

    final HashMap<K, V> map;

    // current node
    Object current;

    // current index, modified on advance/split
    int index;

    // one past last index
    int fence;

    // size estimate
    int est;

    // for co-modification checks
    int expectedModCount;

    HashMapSpliterator(HashMap<K, V> m, int origin, int fence, int est, int expectedModCount) {
      this.map = m;
      this.index = origin;
      this.fence = fence;
      this.est = est;
      this.expectedModCount = expectedModCount;
    }

    final int getFence() {
      // initialize fence and size on first use
      int hi;
      if ((hi = fence) < 0) {
        HashMap<K, V> m = map;
        est = m.size();
        expectedModCount = getModCount(m);
        Object[] tab = getTable(m);
        hi = fence = (tab == null) ? 0 : tab.length;
      }
      return hi;
    }

    public abstract int characteristics();

    public final long getExactSizeIfKnown() {
      return Spliterators.getExactSizeIfKnown((Spliterator<?>) this);
    }

    public final boolean hasCharacteristics(int characteristics) {
      return Spliterators.hasCharacteristics((Spliterator<?>) this, characteristics);
    }

    public final long estimateSize() {
      // force init
      getFence();
      return (long) est;
    }

    static int getModCount(HashMap<?, ?> map) {
      return U.getInt(map, MODCOUNT_OFF);
    }

    static Object[] getTable(HashMap<?, ?> map) {
      return (Object[]) U.getObject(map, TABLE_OFF);
    }

    static <K> K getNodeKey(Object node) {
      return (K) U.getObject(node, NODE_KEY_OFF);
    }

    static <T> T getNodeValue(Object node) {
      return (T) U.getObject(node, NODE_VAL_OFF);
    }

    static Object getNextNode(Object node) {
      return U.getObject(node, NODE_NXT_OFF);
    }

    static Class<?> nodeClass() throws ClassNotFoundException {
      String nodeName =
          new StringBuilder("java.util.HashMap$")
              .append((Spliterators.IS_ANDROID || Spliterators.HAS_STREAMS) ? "Node" : "Entry")
              .toString();
      try {
        return Class.forName(nodeName);
      } catch (ClassNotFoundException e) {
        // we might be running on the first Nougat release
        // (or even on one of the Android N developer previews)
        if (Spliterators.IS_ANDROID) {
          return Class.forName("java.util.HashMap$HashMapEntry");
        }
        throw e;
      }
    }

    // Unsafe mechanics
    private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

    private static final long TABLE_OFF;

    private static final long MODCOUNT_OFF;

    private static final long NODE_KEY_OFF;

    private static final long NODE_VAL_OFF;

    private static final long NODE_NXT_OFF;

    static {
      try {
        TABLE_OFF = U.objectFieldOffset(HashMap.class.getDeclaredField("table"));
        MODCOUNT_OFF = U.objectFieldOffset(HashMap.class.getDeclaredField("modCount"));
        Class<?> nc = nodeClass();
        NODE_KEY_OFF = U.objectFieldOffset(nc.getDeclaredField("key"));
        NODE_VAL_OFF = U.objectFieldOffset(nc.getDeclaredField("value"));
        NODE_NXT_OFF = U.objectFieldOffset(nc.getDeclaredField("next"));
      } catch (Exception e) {
        throw new Error(e);
      }
    }
  }

  private static <K, V> HashMap<K, V> getHashMapFromKeySet(Set<K> keySet) {
    return (HashMap<K, V>) U.getObject(keySet, KEYSET_$0_OFF);
  }

  private static <K, V> HashMap<K, V> getHashMapFromEntrySet(Set<Map.Entry<K, V>> entrySet) {
    return (HashMap<K, V>) U.getObject(entrySet, ENTRYSET_$0_OFF);
  }

  private static <K, V> HashMap<K, V> getHashMapFromValues(Collection<V> values) {
    return (HashMap<K, V>) U.getObject(values, VALUES_$0_OFF);
  }

  private static <K, V> HashMap<K, V> getHashMapFromHashSet(HashSet<K> hashSet) {
    return (HashMap<K, V>) U.getObject(hashSet, HASHSET_MAP_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long VALUES_$0_OFF;

  private static final long KEYSET_$0_OFF;

  private static final long ENTRYSET_$0_OFF;

  private static final long HASHSET_MAP_OFF;

  static {
    try {
      Class<?> vc = Class.forName("java.util.HashMap$Values");
      Class<?> ksc = Class.forName("java.util.HashMap$KeySet");
      Class<?> esc = Class.forName("java.util.HashMap$EntrySet");
      VALUES_$0_OFF = U.objectFieldOffset(vc.getDeclaredField("this$0"));
      KEYSET_$0_OFF = U.objectFieldOffset(ksc.getDeclaredField("this$0"));
      ENTRYSET_$0_OFF = U.objectFieldOffset(esc.getDeclaredField("this$0"));
      HASHSET_MAP_OFF = U.objectFieldOffset(HashSet.class.getDeclaredField("map"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
