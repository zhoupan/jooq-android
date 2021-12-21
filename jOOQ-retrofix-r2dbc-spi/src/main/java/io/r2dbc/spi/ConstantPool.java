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
package io.r2dbc.spi;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * A pool of constant instances. Only a single instance of each constant (by name) should ever
 * exist.
 *
 * @param <T> the type of the constant
 */
abstract class ConstantPool<T> {

  private final ConcurrentMap<String, T> constants = new ConcurrentHashMap<>();

  @Override
  public String toString() {
    return "ConstantPool{" + "constants=" + this.constants + '}';
  }

  /**
   * Creates a new instance of the constant. Implementations of this method should return a new
   * instance each time.
   *
   * @param name the name of the constant
   * @param sensitive whether the value represented by this constant is sensitive
   * @return a new instance of the constant
   */
  abstract T createConstant(String name, boolean sensitive);

  /**
   * Returns a cached or newly created instance of a constant.
   *
   * @param name the name of the constant
   * @param sensitive whether the value represented by this constant is sensitive
   * @return a cached or newly created instance of a constant
   * @throws IllegalArgumentException if {@code name} is {@code null} or empty
   */
  final T valueOf(String name, boolean sensitive) {
    Assert.requireNonNull(name, "name must not be null");
    Assert.requireNonEmpty(name, "name must not be empty");
    return this.constants.computeIfAbsent(name, n -> createConstant(n, sensitive));
  }
}
