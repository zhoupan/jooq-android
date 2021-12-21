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

import org.java.util.function.IntConsumer;
import org.java.util.stream.Collector;

/**
 * A state object for collecting statistics such as count, min, max, sum, and average.
 *
 * <p>This class is designed to work with (though does not require) {@linkplain
 * org.java.util.stream.Stream streams}. For example, you can compute summary statistics on a stream
 * of ints with:
 *
 * <pre>{@code
 * IntSummaryStatistics stats = intStream.collect(IntSummaryStatistics::new,
 *                                                IntSummaryStatistics::accept,
 *                                                IntSummaryStatistics::combine);
 *
 * }</pre>
 *
 * <p>{@code IntSummaryStatistics} can be used as a {@linkplain
 * org.java.util.stream.Stream#collect(Collector) reduction} target for a {@linkplain
 * org.java.util.stream.Stream stream}. For example:
 *
 * <pre>{@code
 * IntSummaryStatistics stats = people.stream()
 *                                    .collect(Collectors.summarizingInt(Person::getDependents));
 * }</pre>
 *
 * This computes, in a single pass, the count of people, as well as the minimum, maximum, sum, and
 * average of their number of dependents.
 *
 * <p><b>Implementation Note:</b><br>
 * This implementation is not thread safe. However, it is safe to use {@link
 * org.java.util.stream.Collectors#summarizingInt(java8.util.function.ToIntFunction)
 * Collectors.summarizingInt()} on a parallel stream, because the parallel implementation of {@link
 * org.java.util.stream.Stream#collect Stream.collect()} provides the necessary partitioning,
 * isolation, and merging of results for safe and efficient parallel execution.
 *
 * <p>This implementation does not check for overflow of the count or the sum.
 *
 * @since 1.8
 */
public class IntSummaryStatistics implements IntConsumer {

  private long count;

  private long sum;

  private int min = Integer.MAX_VALUE;

  private int max = Integer.MIN_VALUE;

  /**
   * Constructs an empty instance with zero count, zero sum, {@code Integer.MAX_VALUE} min, {@code
   * Integer.MIN_VALUE} max and zero average.
   */
  public IntSummaryStatistics() {}

  /**
   * Constructs a non-empty instance with the specified {@code count}, {@code min}, {@code max}, and
   * {@code sum}.
   *
   * <p>If {@code count} is zero then the remaining arguments are ignored and an empty instance is
   * constructed.
   *
   * <p>If the arguments are inconsistent then an {@code IllegalArgumentException} is thrown. The
   * necessary consistent argument conditions are:
   *
   * <ul>
   *   <li>{@code count >= 0}
   *   <li>{@code min <= max}
   * </ul>
   *
   * <p><b>API Note:</b><br>
   * The enforcement of argument correctness means that the retrieved set of recorded values
   * obtained from a {@code IntSummaryStatistics} source instance may not be a legal set of
   * arguments for this constructor due to arithmetic overflow of the source's recorded count of
   * values. The consistent argument conditions are not sufficient to prevent the creation of an
   * internally inconsistent instance. An example of such a state would be an instance with: {@code
   * count} = 2, {@code min} = 1, {@code max} = 2, and {@code sum} = 0.
   *
   * @param count the count of values
   * @param min the minimum value
   * @param max the maximum value
   * @param sum the sum of all values
   * @throws IllegalArgumentException if the arguments are inconsistent
   * @since 10
   */
  public IntSummaryStatistics(long count, int min, int max, long sum)
      throws IllegalArgumentException {
    if (count < 0L) {
      throw new IllegalArgumentException("Negative count value");
    } else if (count > 0L) {
      if (min > max) throw new IllegalArgumentException("Minimum greater than maximum");
      this.count = count;
      this.sum = sum;
      this.min = min;
      this.max = max;
    }
    // Use default field values if count == 0
  }

  /**
   * Records a new value into the summary information
   *
   * @param value the input value
   */
  @Override
  public void accept(int value) {
    ++count;
    sum += value;
    min = Math.min(min, value);
    max = Math.max(max, value);
  }

  /**
   * Combines the state of another {@code IntSummaryStatistics} into this one.
   *
   * @param other another {@code IntSummaryStatistics}
   * @throws NullPointerException if {@code other} is null
   */
  public void combine(IntSummaryStatistics other) {
    count += other.count;
    sum += other.sum;
    min = Math.min(min, other.min);
    max = Math.max(max, other.max);
  }

  /**
   * Returns the count of values recorded.
   *
   * @return the count of values
   */
  public final long getCount() {
    return count;
  }

  /**
   * Returns the sum of values recorded, or zero if no values have been recorded.
   *
   * @return the sum of values, or zero if none
   */
  public final long getSum() {
    return sum;
  }

  /**
   * Returns the minimum value recorded, or {@code Integer.MAX_VALUE} if no values have been
   * recorded.
   *
   * @return the minimum value, or {@code Integer.MAX_VALUE} if none
   */
  public final int getMin() {
    return min;
  }

  /**
   * Returns the maximum value recorded, or {@code Integer.MIN_VALUE} if no values have been
   * recorded.
   *
   * @return the maximum value, or {@code Integer.MIN_VALUE} if none
   */
  public final int getMax() {
    return max;
  }

  /**
   * Returns the arithmetic mean of values recorded, or zero if no values have been recorded.
   *
   * @return the arithmetic mean of values, or zero if none
   */
  public final double getAverage() {
    return getCount() > 0 ? (double) getSum() / getCount() : 0.0d;
  }

  /**
   * Returns a non-empty string representation of this object suitable for debugging. The exact
   * presentation format is unspecified and may vary between implementations and versions.
   *
   * @return a string representation of this object
   */
  @Override
  public String toString() {
    return String.format(
        "%s{count=%d, sum=%d, min=%d, average=%f, max=%d}",
        this.getClass().getSimpleName(), getCount(), getSum(), getMin(), getAverage(), getMax());
  }
}
