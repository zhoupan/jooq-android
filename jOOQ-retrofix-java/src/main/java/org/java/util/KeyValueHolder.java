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

import java.util.Map;

/**
 * An immutable container for a key and a value, suitable for use in creating and populating {@code
 * Map} instances.
 *
 * <p>This is a <a href="../lang/package-summary.html#Value-based-Classes">value-based</a> class;
 * use of identity-sensitive operations (including reference equality ({@code ==}), identity hash
 * code, or synchronization) on instances of {@code KeyValueHolder} may have unpredictable results
 * and should be avoided.
 *
 * <p><b>API Note:</b><br>
 * This class is not public. Instances can be created using the {@link Maps#entry Map.entry(k, v)}
 * factory method, which is public.
 *
 * <p>This class differs from AbstractMap.SimpleImmutableEntry in the following ways: it is not
 * serializable, it is final, and its key and value must be non-null.
 *
 * @param <K> the key type
 * @param <V> the value type
 * @see Maps#ofEntries Maps.ofEntries()
 * @since 9
 */
final class KeyValueHolder<K, V> implements Map.Entry<K, V> {

  final K key;

  final V value;

  KeyValueHolder(K k, V v) {
    key = Objects.requireNonNull(k);
    value = Objects.requireNonNull(v);
  }

  /**
   * Gets the key from this holder.
   *
   * @return the key
   */
  @Override
  public K getKey() {
    return key;
  }

  /**
   * Gets the value from this holder.
   *
   * @return the value
   */
  @Override
  public V getValue() {
    return value;
  }

  /**
   * Throws {@link UnsupportedOperationException}.
   *
   * @param value ignored
   * @return never returns normally
   */
  @Override
  public V setValue(V value) {
    throw new UnsupportedOperationException("not supported");
  }

  /**
   * Compares the specified object with this entry for equality. Returns {@code true} if the given
   * object is also a map entry and the two entries' keys and values are equal. Note that key and
   * value are non-null, so equals() can be called safely on them.
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Map.Entry)) return false;
    Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
    return key.equals(e.getKey()) && value.equals(e.getValue());
  }

  /**
   * Returns the hash code value for this map entry. The hash code is {@code key.hashCode() ^
   * value.hashCode()}. Note that key and value are non-null, so hashCode() can be called safely on
   * them.
   */
  @Override
  public int hashCode() {
    return key.hashCode() ^ value.hashCode();
  }

  /**
   * Returns a String representation of this map entry. This implementation returns the string
   * representation of this entry's key followed by the equals character ("{@code =}") followed by
   * the string representation of this entry's value.
   *
   * @return a String representation of this map entry
   */
  @Override
  public String toString() {
    return key + "=" + value;
  }
}
