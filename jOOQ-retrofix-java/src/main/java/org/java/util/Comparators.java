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
import java.util.Collections;
import java.util.Comparator;
import org.java.lang.Integers;
import org.java.lang.Longs;
import org.java.util.function.Function;
import org.java.util.function.ToDoubleFunction;
import org.java.util.function.ToIntFunction;
import org.java.util.function.ToLongFunction;

/**
 * A place for static default implementations of the new Java 8 default interface methods and static
 * interface methods in the {@link Comparator} interface.
 */
public final class Comparators {

  /**
   * Compares {@link Comparable} objects in natural order.
   *
   * @see Comparable
   */
  private enum NaturalOrderComparator implements Comparator<Comparable<Object>> {
    INSTANCE;

    @Override
    public int compare(Comparable<Object> c1, Comparable<Object> c2) {
      return c1.compareTo(c2);
    }

    public Comparator<Comparable<Object>> reversed() {
      return Comparators.reverseOrder();
    }
  }

  /** Null-friendly comparators */
  private static final class NullComparator<T> implements Comparator<T>, Serializable {

    private static final long serialVersionUID = -7569533591570686392L;

    private final boolean nullFirst;

    // if null, non-null Ts are considered equal
    private final Comparator<T> real;

    @SuppressWarnings("unchecked")
    NullComparator(boolean nullFirst, Comparator<? super T> real) {
      this.nullFirst = nullFirst;
      this.real = (Comparator<T>) real;
    }

    @Override
    public int compare(T a, T b) {
      if (a == null) {
        return (b == null) ? 0 : (nullFirst ? -1 : 1);
      } else if (b == null) {
        return nullFirst ? 1 : -1;
      } else {
        return (real == null) ? 0 : real.compare(a, b);
      }
    }

    public Comparator<T> thenComparing(Comparator<? super T> other) {
      Objects.requireNonNull(other);
      return new NullComparator<T>(
          nullFirst, real == null ? other : Comparators.thenComparing(real, other));
    }

    public Comparator<T> reversed() {
      return new NullComparator<T>(
          !nullFirst, real == null ? null : Collections.reverseOrder(real));
    }
  }

  /**
   * Returns a comparator that imposes the reverse of the <em>natural ordering</em>.
   *
   * <p>The returned comparator is serializable and throws {@link NullPointerException} when
   * comparing {@code null}.
   *
   * @param  <T> the {@link Comparable} type of element to be compared
   * @return a comparator that imposes the reverse of the <i>natural ordering</i> on {@code
   *     Comparable} objects.
   * @see Comparable
   * @since 1.8
   */
  public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
    return Collections.reverseOrder();
  }

  /**
   * Returns a comparator that compares {@link Comparable} objects in natural order.
   *
   * <p>The returned comparator is serializable and throws {@link NullPointerException} when
   * comparing {@code null}.
   *
   * @param  <T> the {@link Comparable} type of element to be compared
   * @return a comparator that imposes the <i>natural ordering</i> on {@code Comparable} objects.
   * @see Comparable
   * @since 1.8
   */
  @SuppressWarnings("unchecked")
  public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
    return (Comparator<T>) Comparators.NaturalOrderComparator.INSTANCE;
  }

  /**
   * Accepts a function that extracts a sort key from a type {@code T}, and returns a {@code
   * Comparator<T>} that compares by that sort key using the specified {@link Comparator}.
   *
   * <p>The returned comparator is serializable if the specified function and comparator are both
   * serializable.
   *
   * <p><b>API Note:</b><br>
   * For example, to obtain a {@code Comparator} that compares {@code Person} objects by their last
   * name ignoring case differences,
   *
   * <pre>{@code
   * Comparator<Person> cmp = Comparators.comparing(
   *         Person::getLastName,
   *         String.CASE_INSENSITIVE_ORDER);
   * }</pre>
   *
   * @param  <T> the type of element to be compared
   * @param  <U> the type of the sort key
   * @param keyExtractor the function used to extract the sort key
   * @param keyComparator the {@code Comparator} used to compare the sort key
   * @return a comparator that compares by an extracted key using the specified {@code Comparator}
   * @throws NullPointerException if either argument is null
   * @since 1.8
   */
  public static <T, U> Comparator<T> comparing(
      Function<? super T, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
    Objects.requireNonNull(keyExtractor);
    Objects.requireNonNull(keyComparator);
    return (Comparator<T> & Serializable)
        (c1, c2) -> keyComparator.compare(keyExtractor.apply(c1), keyExtractor.apply(c2));
  }

  /**
   * Accepts a function that extracts a {@link java.lang.Comparable Comparable} sort key from a type
   * {@code T}, and returns a {@code Comparator<T>} that compares by that sort key.
   *
   * <p>The returned comparator is serializable if the specified function is also serializable.
   *
   * <p><b>API Note:</b><br>
   * For example, to obtain a {@code Comparator} that compares {@code Person} objects by their last
   * name,
   *
   * <pre>{@code
   * Comparator<Person> byLastName = Comparators.comparing(Person::getLastName);
   * }</pre>
   *
   * @param  <T> the type of element to be compared
   * @param  <U> the type of the {@code Comparable} sort key
   * @param keyExtractor the function used to extract the {@link Comparable} sort key
   * @return a comparator that compares by an extracted key
   * @throws NullPointerException if the argument is null
   * @since 1.8
   */
  public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
      Function<? super T, ? extends U> keyExtractor) {
    Objects.requireNonNull(keyExtractor);
    return (Comparator<T> & Serializable)
        (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
  }

  /**
   * Accepts a function that extracts an {@code int} sort key from a type {@code T}, and returns a
   * {@code Comparator<T>} that compares by that sort key.
   *
   * <p>The returned comparator is serializable if the specified function is also serializable.
   *
   * @param  <T> the type of element to be compared
   * @param keyExtractor the function used to extract the integer sort key
   * @return a comparator that compares by an extracted key
   * @see #comparing(Function)
   * @throws NullPointerException if the argument is null
   * @since 1.8
   */
  public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> keyExtractor) {
    Objects.requireNonNull(keyExtractor);
    return (Comparator<T> & Serializable)
        (c1, c2) -> Integers.compare(keyExtractor.applyAsInt(c1), keyExtractor.applyAsInt(c2));
  }

  /**
   * Accepts a function that extracts a {@code long} sort key from a type {@code T}, and returns a
   * {@code Comparator<T>} that compares by that sort key.
   *
   * <p>The returned comparator is serializable if the specified function is also serializable.
   *
   * @param  <T> the type of element to be compared
   * @param keyExtractor the function used to extract the long sort key
   * @return a comparator that compares by an extracted key
   * @see #comparing(Function)
   * @throws NullPointerException if the argument is null
   * @since 1.8
   */
  public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> keyExtractor) {
    Objects.requireNonNull(keyExtractor);
    return (Comparator<T> & Serializable)
        (c1, c2) -> Longs.compare(keyExtractor.applyAsLong(c1), keyExtractor.applyAsLong(c2));
  }

  /**
   * Accepts a function that extracts a {@code double} sort key from a type {@code T}, and returns a
   * {@code Comparator<T>} that compares by that sort key.
   *
   * <p>The returned comparator is serializable if the specified function is also serializable.
   *
   * @param  <T> the type of element to be compared
   * @param keyExtractor the function used to extract the double sort key
   * @return a comparator that compares by an extracted key
   * @see #comparing(Function)
   * @throws NullPointerException if the argument is null
   * @since 1.8
   */
  public static <T> Comparator<T> comparingDouble(ToDoubleFunction<? super T> keyExtractor) {
    Objects.requireNonNull(keyExtractor);
    return (Comparator<T> & Serializable)
        (c1, c2) -> Double.compare(keyExtractor.applyAsDouble(c1), keyExtractor.applyAsDouble(c2));
  }

  /**
   * Returns a lexicographic-order comparator with another comparator. If the {@code this_} {@code
   * Comparator} considers two elements equal, i.e. {@code compare(a, b) == 0}, {@code other} is
   * used to determine the order.
   *
   * <p>The returned comparator is serializable if the specified comparators are also serializable.
   *
   * <p><b>API Note:</b><br>
   * For example, to sort a collection of {@code String} based on the length and then
   * case-insensitive natural ordering, the comparators can be composed using following code,
   *
   * <pre>{@code
   * Comparator<String> cmp = Comparators.thenComparing(Comparators.comparingInt(String::length),
   *                    String.CASE_INSENSITIVE_ORDER);
   * }</pre>
   *
   * @param <T> the type of objects that may be compared by the passed comparators
   * @param this_ the comparator to be used first
   * @param other the other comparator to be used when the {@code this_} comparator compares two
   *     objects that are equal.
   * @return a lexicographic-order comparator composed of the {@code this_} and then the other
   *     comparator
   * @throws NullPointerException if {@code this_} is null.
   * @throws NullPointerException if {@code other} is null.
   * @since 1.8
   */
  public static <T> Comparator<T> thenComparing(
      Comparator<? super T> this_, Comparator<? super T> other) {
    Objects.requireNonNull(this_);
    Objects.requireNonNull(other);
    if (this_ instanceof NullComparator) {
      return ((NullComparator<T>) this_).thenComparing(other);
    }
    return (Comparator<T> & Serializable)
        (c1, c2) -> {
          int res = this_.compare(c1, c2);
          return (res != 0) ? res : other.compare(c1, c2);
        };
  }

  /**
   * Returns a lexicographic-order comparator with a function that extracts a key to be compared
   * with the given {@code Comparator}.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This default implementation behaves as if {@code thenComparing(this_, comparing(keyExtractor,
   * keyComparator))}.
   *
   * @param <T> the type of objects that may be compared by the {@code this_} comparator
   * @param  <U> the type of the sort key
   * @param this_ the comparator to be used first
   * @param keyExtractor the function used to extract the sort key
   * @param keyComparator the {@code Comparator} used to compare the sort key
   * @return a lexicographic-order comparator composed of the {@code this_} comparator and then
   *     comparing on the key extracted by the keyExtractor function
   * @throws NullPointerException if either argument is null.
   * @see #comparing(Function, Comparator)
   * @see #thenComparing(Comparator, Comparator)
   * @since 1.8
   */
  public static <T, U> Comparator<T> thenComparing(
      Comparator<? super T> this_,
      Function<? super T, ? extends U> keyExtractor,
      Comparator<? super U> keyComparator) {
    return thenComparing(this_, comparing(keyExtractor, keyComparator));
  }

  /**
   * Returns a lexicographic-order comparator with a function that extracts a {@code Comparable}
   * sort key.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This default implementation behaves as if {@code thenComparing(this_,
   * comparing(keyExtractor))}.
   *
   * @param <T> the type of objects that may be compared by the {@code this_} comparator
   * @param  <U> the type of the {@link Comparable} sort key
   * @param this_ the comparator to be used first
   * @param keyExtractor the function used to extract the {@link Comparable} sort key
   * @return a lexicographic-order comparator composed of {@code this_} and then the {@link
   *     Comparable} sort key.
   * @throws NullPointerException if either argument is null.
   * @see #comparing(Function)
   * @see #thenComparing(Comparator, Comparator)
   * @since 1.8
   */
  public static <T, U extends Comparable<? super U>> Comparator<T> thenComparing(
      Comparator<? super T> this_, Function<? super T, ? extends U> keyExtractor) {
    return thenComparing(this_, comparing(keyExtractor));
  }

  /**
   * Returns a lexicographic-order comparator with a function that extracts a {@code int} sort key.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This default implementation behaves as if {@code thenComparing(this_,
   * comparingInt(keyExtractor))}.
   *
   * @param <T> the type of objects that may be compared by the {@code this_} comparator
   * @param this_ the comparator to be used first
   * @param keyExtractor the function used to extract the integer sort key
   * @return a lexicographic-order comparator composed of {@code this_} and then the {@code int}
   *     sort key
   * @throws NullPointerException if either argument is null.
   * @see #comparingInt(ToIntFunction)
   * @see #thenComparing(Comparator, Comparator)
   * @since 1.8
   */
  public static <T> Comparator<T> thenComparingInt(
      Comparator<? super T> this_, ToIntFunction<? super T> keyExtractor) {
    return thenComparing(this_, comparingInt(keyExtractor));
  }

  /**
   * Returns a lexicographic-order comparator with a function that extracts a {@code long} sort key.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This default implementation behaves as if {@code thenComparing(this_,
   * comparingLong(keyExtractor))}.
   *
   * @param <T> the type of objects that may be compared by the {@code this_} comparator
   * @param this_ the comparator to be used first
   * @param keyExtractor the function used to extract the long sort key
   * @return a lexicographic-order comparator composed of {@code this_} and then the {@code long}
   *     sort key
   * @throws NullPointerException if either argument is null.
   * @see #comparingLong(ToLongFunction)
   * @see #thenComparing(Comparator, Comparator)
   * @since 1.8
   */
  public static <T> Comparator<T> thenComparingLong(
      Comparator<? super T> this_, ToLongFunction<? super T> keyExtractor) {
    return thenComparing(this_, comparingLong(keyExtractor));
  }

  /**
   * Returns a lexicographic-order comparator with a function that extracts a {@code double} sort
   * key.
   *
   * <p><b>Implementation Requirements:</b><br>
   * This default implementation behaves as if {@code thenComparing(this_,
   * comparingDouble(keyExtractor))}.
   *
   * @param <T> the type of objects that may be compared by the {@code this_} comparator
   * @param this_ the comparator to be used first
   * @param keyExtractor the function used to extract the double sort key
   * @return a lexicographic-order comparator composed of {@code this_} and then the {@code double}
   *     sort key
   * @throws NullPointerException if either argument is null.
   * @see #comparingDouble(ToDoubleFunction)
   * @see #thenComparing(Comparator, Comparator)
   * @since 1.8
   */
  public static <T> Comparator<T> thenComparingDouble(
      Comparator<? super T> this_, ToDoubleFunction<? super T> keyExtractor) {
    return thenComparing(this_, comparingDouble(keyExtractor));
  }

  /**
   * Returns a comparator that imposes the reverse ordering of the passed {@code comparator}.
   *
   * @param <T> the type of objects that may be compared by the comparator argument
   * @param comparator the comparator whose ordering needs to be reversed
   * @return a comparator that imposes the reverse ordering of the passed {@code comparator}.
   * @since 1.8
   */
  public static <T> Comparator<T> reversed(Comparator<T> comparator) {
    if (comparator instanceof NullComparator) {
      return ((NullComparator<T>) comparator).reversed();
    }
    return Collections.reverseOrder(comparator);
  }

  /**
   * Returns a null-friendly comparator that considers {@code null} to be less than non-null. When
   * both are {@code null}, they are considered equal. If both are non-null, the specified {@code
   * Comparator} is used to determine the order. If the specified comparator is {@code null}, then
   * the returned comparator considers all non-null values to be equal.
   *
   * <p>The returned comparator is serializable if the specified comparator is serializable.
   *
   * @param  <T> the type of the elements to be compared
   * @param comparator a {@code Comparator} for comparing non-null values
   * @return a comparator that considers {@code null} to be less than non-null, and compares
   *     non-null objects with the supplied {@code Comparator}.
   * @since 1.8
   */
  public static <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
    return new Comparators.NullComparator<>(true, comparator);
  }

  /**
   * Returns a null-friendly comparator that considers {@code null} to be greater than non-null.
   * When both are {@code null}, they are considered equal. If both are non-null, the specified
   * {@code Comparator} is used to determine the order. If the specified comparator is {@code null},
   * then the returned comparator considers all non-null values to be equal.
   *
   * <p>The returned comparator is serializable if the specified comparator is serializable.
   *
   * @param  <T> the type of the elements to be compared
   * @param comparator a {@code Comparator} for comparing non-null values
   * @return a comparator that considers {@code null} to be greater than non-null, and compares
   *     non-null objects with the supplied {@code Comparator}.
   * @since 1.8
   */
  public static <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
    return new Comparators.NullComparator<>(false, comparator);
  }

  private Comparators() {}
}
