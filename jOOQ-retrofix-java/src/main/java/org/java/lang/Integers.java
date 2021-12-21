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
 * A place for static default implementations of the new Java 8 static methods in the {@link
 * Integer} class.
 */
public final class Integers {

  /**
   * Returns a hash code for a {@code int} value; compatible with {@code Integer.hashCode()}.
   *
   * @param value the value to hash
   * @return a hash code value for a {@code int} value.
   * @since 1.8
   */
  public static int hashCode(int value) {
    return value;
  }

  /**
   * Converts the argument to a {@code long} by an unsigned conversion. In an unsigned conversion to
   * a {@code long}, the high-order 32 bits of the {@code long} are zero and the low-order 32 bits
   * are equal to the bits of the integer argument.
   *
   * <p>Consequently, zero and positive {@code int} values are mapped to a numerically equal {@code
   * long} value and negative {@code int} values are mapped to a {@code long} value equal to the
   * input plus 2<sup>32</sup>.
   *
   * @param x the value to convert to an unsigned {@code long}
   * @return the argument converted to {@code long} by an unsigned conversion
   * @since 1.8
   */
  public static long toUnsignedLong(int x) {
    return ((long) x) & 0xffffffffL;
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
  public static int divideUnsigned(int dividend, int divisor) {
    // In lieu of tricky code, for now just use long arithmetic.
    return (int) (toUnsignedLong(dividend) / toUnsignedLong(divisor));
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
  public static int remainderUnsigned(int dividend, int divisor) {
    // In lieu of tricky code, for now just use long arithmetic.
    return (int) (toUnsignedLong(dividend) % toUnsignedLong(divisor));
  }

  /**
   * Compares two {@code int} values numerically. The value returned is identical to what would be
   * returned by:
   *
   * <pre>
   *    Integer.valueOf(x).compareTo(Integer.valueOf(y))
   * </pre>
   *
   * @param x the first {@code int} to compare
   * @param y the second {@code int} to compare
   * @return the value {@code 0} if {@code x == y}; a value less than {@code 0} if {@code x < y};
   *     and a value greater than {@code 0} if {@code x > y}
   * @since 1.7
   */
  public static int compare(int x, int y) {
    return (x < y) ? -1 : ((x == y) ? 0 : 1);
  }

  /**
   * Compares two {@code int} values numerically treating the values as unsigned.
   *
   * @param x the first {@code int} to compare
   * @param y the second {@code int} to compare
   * @return the value {@code 0} if {@code x == y}; a value less than {@code 0} if {@code x < y} as
   *     unsigned values; and a value greater than {@code 0} if {@code x > y} as unsigned values
   * @since 1.8
   */
  public static int compareUnsigned(int x, int y) {
    return compare(x + Integer.MIN_VALUE, y + Integer.MIN_VALUE);
  }

  /**
   * Adds two integers together as per the + operator.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the sum of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static int sum(int a, int b) {
    return a + b;
  }

  /**
   * Returns the greater of two {@code int} values as if by calling {@link Math#max(int, int)
   * Math.max}.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the greater of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static int max(int a, int b) {
    return Math.max(a, b);
  }

  /**
   * Returns the smaller of two {@code int} values as if by calling {@link Math#min(int, int)
   * Math.min}.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the smaller of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static int min(int a, int b) {
    return Math.min(a, b);
  }

  private Integers() {}
}
