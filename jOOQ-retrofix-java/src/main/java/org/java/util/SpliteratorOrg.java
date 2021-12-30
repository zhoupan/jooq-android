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
import org.java.util.function.Consumer;

public class SpliteratorOrg<T> implements org.java.util.Spliterator<T> {

  public final java.util.Spliterator<T> wrapper;

  public SpliteratorOrg(java.util.Spliterator<T> wrapper) {
    super();
    this.wrapper = wrapper;
  }

  @Override
  public boolean tryAdvance(Consumer<? super T> action) {
    return wrapper.tryAdvance((t) -> action.accept(t));
  }

  @Override
  public org.java.util.Spliterator<T> trySplit() {
    java.util.Spliterator<T> split = this.wrapper.trySplit();
    return new SpliteratorOrg<T>(split);
  }

  @Override
  public long estimateSize() {
    return this.wrapper.estimateSize();
  }

  @Override
  public int characteristics() {
    return this.wrapper.characteristics();
  }

  @Override
  public void forEachRemaining(Consumer<? super T> action) {
    this.wrapper.forEachRemaining((t) -> action.accept(t));
  }

  @Override
  public long getExactSizeIfKnown() {
    return this.wrapper.getExactSizeIfKnown();
  }

  @Override
  public boolean hasCharacteristics(int characteristics) {
    return this.wrapper.hasCharacteristics(characteristics);
  }

  @Override
  public Comparator<? super T> getComparator() {
    return this.wrapper.getComparator();
  }
}
