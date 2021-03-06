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
package org.java.lang;

/**
 * A place for static default implementations of the new Java 8 static methods in the {@link Long}
 * class.
 */
public final class Longs {

  /**
   * Returns a hash code for a {@code long} value; compatible with {@code Long.hashCode()}.
   *
   * @param value the value to hash
   * @return a hash code value for a {@code long} value.
   * @since 1.8
   */
  public static int hashCode(long value) {
    return (int) (value ^ (value >>> 32));
  }

  /**
   * Returns the unsigned quotient of dividing the first argument by the second where each argument
   * and the result is interpreted as an unsigned value.
   *
   * <p>Note that in two's complement arithmetic, the three other basic arithmetic operations of
   * add, subtract, and multiply are bit-wise identical if the two operands are regarded as both
   * being signed or both being unsigned. Therefore separate {@code addUnsigned}, etc. methods are
   * not provided.
   *
   * @param dividend the value to be divided
   * @param divisor the value doing the dividing
   * @return the unsigned quotient of the first argument divided by the second argument
   * @see #remainderUnsigned
   * @since 1.8
   */
  public static long divideUnsigned(long dividend, long divisor) {
    /* See Hacker's Delight (2nd ed), section 9.3 */
    if (divisor >= 0) {
      long q = (dividend >>> 1) / divisor << 1;
      long r = dividend - q * divisor;
      return q + ((r | ~(r - divisor)) >>> (Long.SIZE - 1));
    }
    return (dividend & ~(dividend - divisor)) >>> (Long.SIZE - 1);
  }

  /**
   * Compares two {@code long} values numerically. The value returned is identical to what would be
   * returned by:
   *
   * <pre>
   *    Long.valueOf(x).compareTo(Long.valueOf(y))
   * </pre>
   *
   * @param x the first {@code long} to compare
   * @param y the second {@code long} to compare
   * @return the value {@code 0} if {@code x == y}; a value less than {@code 0} if {@code x < y};
   *     and a value greater than {@code 0} if {@code x > y}
   * @since 1.7
   */
  public static int compare(long x, long y) {
    return (x < y) ? -1 : ((x == y) ? 0 : 1);
  }

  /**
   * Compares two {@code long} values numerically treating the values as unsigned.
   *
   * @param x the first {@code long} to compare
   * @param y the second {@code long} to compare
   * @return the value {@code 0} if {@code x == y}; a value less than {@code 0} if {@code x < y} as
   *     unsigned values; and a value greater than {@code 0} if {@code x > y} as unsigned values
   * @since 1.8
   */
  public static int compareUnsigned(long x, long y) {
    return compare(x + Long.MIN_VALUE, y + Long.MIN_VALUE);
  }

  /**
   * Returns the unsigned remainder from dividing the first argument by the second where each
   * argument and the result is interpreted as an unsigned value.
   *
   * @param dividend the value to be divided
   * @param divisor the value doing the dividing
   * @return the unsigned remainder of the first argument divided by the second argument
   * @see #divideUnsigned
   * @since 1.8
   */
  public static long remainderUnsigned(long dividend, long divisor) {
    /* See Hacker's Delight (2nd ed), section 9.3 */
    if (divisor >= 0) {
      long q = (dividend >>> 1) / divisor << 1;
      long r = dividend - q * divisor;
      /*
       * Here, 0 <= r < 2 * divisor
       * (1) When 0 <= r < divisor, the remainder is simply r.
       * (2) Otherwise the remainder is r - divisor.
       *
       * In case (1), r - divisor < 0. Applying ~ produces a long with
       * sign bit 0, so >> produces 0. The returned value is thus r.
       *
       * In case (2), a similar reasoning shows that >> produces -1,
       * so the returned value is r - divisor.
       */
      return r - ((~(r - divisor) >> (Long.SIZE - 1)) & divisor);
    }
    /*
     * (1) When dividend >= 0, the remainder is dividend.
     * (2) Otherwise
     *      (2.1) When dividend < divisor, the remainder is dividend.
     *      (2.2) Otherwise the remainder is dividend - divisor
     *
     * A reasoning similar to the above shows that the returned value
     * is as expected.
     */
    return dividend - (((dividend & ~(dividend - divisor)) >> (Long.SIZE - 1)) & divisor);
  }

  /**
   * Adds two {@code long} values together as per the + operator.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the sum of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static long sum(long a, long b) {
    return a + b;
  }

  /**
   * Returns the greater of two {@code long} values as if by calling {@link Math#max(long, long)
   * Math.max}.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the greater of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static long max(long a, long b) {
    return Math.max(a, b);
  }

  /**
   * Returns the smaller of two {@code long} values as if by calling {@link Math#min(long, long)
   * Math.min}.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the smaller of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static long min(long a, long b) {
    return Math.min(a, b);
  }

  private Longs() {}
}
