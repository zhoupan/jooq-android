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

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

// Spliterator for java.util.concurrent.CopyOnWriteArraySet
final class COWArraySetSpliterator {

  // CVS rev. 1.74
  private COWArraySetSpliterator() {}

  static <T> Spliterator<T> spliterator(CopyOnWriteArraySet<T> set) {
    Object[] array = COWArrayListSpliterator.getArray(getCowArrayList(set));
    return Spliterators.spliterator(array, Spliterator.IMMUTABLE | Spliterator.DISTINCT);
  }

  private static <T> CopyOnWriteArrayList<T> getCowArrayList(CopyOnWriteArraySet<T> set) {
    return (CopyOnWriteArrayList<T>) U.getObject(set, COW_ARRAY_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long COW_ARRAY_OFF;

  static {
    try {
      COW_ARRAY_OFF = U.objectFieldOffset(CopyOnWriteArraySet.class.getDeclaredField("al"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
