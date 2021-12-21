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

import java.util.List;

final class ArraysArrayListSpliterator {

  private ArraysArrayListSpliterator() {}

  static <T> Spliterator<T> spliterator(List<T> list) {
    return Spliterators.spliterator(getArray(list), Spliterator.ORDERED);
  }

  private static <T> Object[] getArray(List<T> list) {
    return (Object[]) U.getObject(list, ARRAY_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long ARRAY_OFF;

  static {
    try {
      Class<?> aal = Class.forName("java.util.Arrays$ArrayList");
      ARRAY_OFF = U.objectFieldOffset(aal.getDeclaredField("a"));
    } catch (Exception e) {
      throw new Error(e);
    }
  }
}
