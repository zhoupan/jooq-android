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

// Spliterator for java.util.concurrent.CopyOnWriteArrayList
final class COWArrayListSpliterator {

  // CVS rev. 1.155
  private COWArrayListSpliterator() {}

  static <T> Spliterator<T> spliterator(CopyOnWriteArrayList<T> list) {
    return Spliterators.spliterator(getArray(list), Spliterator.IMMUTABLE | Spliterator.ORDERED);
  }

  static <T> Object[] getArray(CopyOnWriteArrayList<T> list) {
    return (Object[]) U.getObject(list, ARRAY_OFF);
  }

  // Unsafe mechanics
  private static final sun.misc.Unsafe U = UnsafeAccess.unsafe;

  private static final long ARRAY_OFF;

  static {
    ARRAY_OFF = fieldOffset(Spliterators.IS_ANDROID ? "elements" : "array", true);
  }

  static long fieldOffset(String arrayFieldName, boolean recursive) {
    try {
      return U.objectFieldOffset(CopyOnWriteArrayList.class.getDeclaredField(arrayFieldName));
    } catch (Exception e) {
      if (recursive
          && e instanceof NoSuchFieldException
          && (Spliterators.IS_ANDROID && !Spliterators.IS_HARMONY_ANDROID)) {
        // https://android.googlesource.com/platform/libcore/+/29957558cf0db700bfaae360a80c42dc3871d0e5
        return fieldOffset("array", false);
      }
      throw new Error(e);
    }
  }
}
