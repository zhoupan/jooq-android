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

import java.io.Serializable;
import org.java.util.stream.DoubleStream;
import org.java.util.stream.IntStream;
import org.java.util.stream.LongStream;

/** A compilation stub only - <b>must not</b> be included in the binary distribution! */
@SuppressWarnings("serial")
public class Random implements Serializable {

  /**
   * Throws {@link AssertionError} always.
   *
   * @param streamSize ignored
   * @return never
   * @throws AssertionError always
   */
  public IntStream ints(long streamSize) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @return never
   * @throws AssertionError always
   */
  public IntStream ints() {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param streamSize ignored
   * @param randomNumberOrigin ignored
   * @param randomNumberBound ignored
   * @return never
   * @throws AssertionError always
   */
  public IntStream ints(long streamSize, int randomNumberOrigin, int randomNumberBound) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param randomNumberOrigin ignored
   * @param randomNumberBound ignored
   * @return never
   * @throws AssertionError always
   */
  public IntStream ints(int randomNumberOrigin, int randomNumberBound) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param streamSize ignored
   * @return never
   * @throws AssertionError always
   */
  public LongStream longs(long streamSize) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @return never
   * @throws AssertionError always
   */
  public LongStream longs() {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param streamSize ignored
   * @param randomNumberOrigin ignored
   * @param randomNumberBound ignored
   * @return never
   * @throws AssertionError always
   */
  public LongStream longs(long streamSize, long randomNumberOrigin, long randomNumberBound) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param randomNumberOrigin ignored
   * @param randomNumberBound ignored
   * @return never
   * @throws AssertionError always
   */
  public LongStream longs(long randomNumberOrigin, long randomNumberBound) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param streamSize ignored
   * @return never
   * @throws AssertionError always
   */
  public DoubleStream doubles(long streamSize) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @return never
   * @throws AssertionError always
   */
  public DoubleStream doubles() {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param streamSize ignored
   * @param randomNumberOrigin ignored
   * @param randomNumberBound ignored
   * @return never
   * @throws AssertionError always
   */
  public DoubleStream doubles(
      long streamSize, double randomNumberOrigin, double randomNumberBound) {
    throw new AssertionError();
  }

  /**
   * Throws {@link AssertionError} always.
   *
   * @param randomNumberOrigin ignored
   * @param randomNumberBound ignored
   * @return never
   * @throws AssertionError always
   */
  public DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
    throw new AssertionError();
  }
}
