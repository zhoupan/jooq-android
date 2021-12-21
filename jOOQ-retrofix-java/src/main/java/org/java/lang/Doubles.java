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
 * A place for static default implementations of the new Java 8 static methods in the {@link Double}
 * class.
 */
public final class Doubles {

  /**
   * Returns a hash code for a {@code double} value; compatible with {@code Double.hashCode()}.
   *
   * @param value the value to hash
   * @return a hash code value for a {@code double} value.
   * @since 1.8
   */
  public static int hashCode(double value) {
    return Longs.hashCode(Double.doubleToLongBits(value));
  }

  /**
   * Adds two {@code double} values together as per the + operator.
   *
   * <p><br>
   * <b>See <em>The Java� Language Specification</em>:</b><br>
   * 4.2.4 Floating-Point Operations
   *
   * @param a the first operand
   * @param b the second operand
   * @return the sum of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static double sum(double a, double b) {
    return a + b;
  }

  /**
   * Returns the greater of two {@code double} values as if by calling {@link Math#max(double,
   * double) Math.max}.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the greater of {@code a} and {@code b}
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static double max(double a, double b) {
    return Math.max(a, b);
  }

  /**
   * Returns the smaller of two {@code double} values as if by calling {@link Math#min(double,
   * double) Math.min}.
   *
   * @param a the first operand
   * @param b the second operand
   * @return the smaller of {@code a} and {@code b}.
   * @see org.java.util.function.BinaryOperator
   * @since 1.8
   */
  public static double min(double a, double b) {
    return Math.min(a, b);
  }

  /**
   * Returns {@code true} if the argument is a finite floating-point value; returns {@code false}
   * otherwise (for NaN and infinity arguments).
   *
   * @param d the {@code double} value to be tested
   * @return {@code true} if the argument is a finite floating-point value, {@code false} otherwise.
   * @since 1.8
   */
  public static boolean isFinite(double d) {
    return Math.abs(d) <= 1.7976931348623157E+308D;
  }

  private Doubles() {}
}
