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

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.java.util.function.DoubleConsumer;
import org.java.util.function.IntConsumer;
import org.java.util.function.LongConsumer;

/**
 * A base type for primitive specializations of {@code Iterator}. Specialized subtypes are provided
 * for {@link OfInt int}, {@link OfLong long}, and {@link OfDouble double} values.
 *
 * <p>The specialized subtype default implementations of {@link Iterator#next} and {@link
 * Iterators#forEachRemaining(Iterator, org.java.util.function.Consumer)} box primitive values to
 * instances of their corresponding wrapper class. Such boxing may offset any advantages gained when
 * using the primitive specializations. To avoid boxing, the corresponding primitive-based methods
 * should be used. For example, {@link PrimitiveIterator.OfInt#nextInt()} and {@link
 * Iterators#forEachRemaining(PrimitiveIterator.OfInt, IntConsumer)} should be used in preference to
 * {@link PrimitiveIterator.OfInt#next()} and {@link Iterators#forEachRemaining(Iterator,
 * org.java.util.function.Consumer)}.
 *
 * <p>Iteration of primitive values using boxing-based methods {@link Iterator#next next()} and
 * {@link Iterators#forEachRemaining(Iterator, org.java.util.function.Consumer) forEachRemaining()},
 * does not affect the order in which the values, transformed to boxed values, are encountered.
 *
 * @param <T> the type of elements returned by this PrimitiveIterator. The type must be a wrapper
 *     type for a primitive type, such as {@code Integer} for the primitive {@code int} type.
 * @param <T_CONS> the type of primitive consumer. The type must be a primitive specialization of
 *     {@link org.java.util.function.Consumer} for {@code T}, such as {@link
 *     org.java.util.function.IntConsumer} for {@code Integer}.
 * @since 1.8
 */
public interface PrimitiveIterator<T, T_CONS> extends Iterator<T> {

  /**
   * Performs the given action for each remaining element until all elements have been processed or
   * the action throws an exception. Actions are performed in the order of iteration, if that order
   * is specified. Exceptions thrown by the action are relayed to the caller.
   *
   * <p>The behavior of an iterator is unspecified if the action modifies the source of elements in
   * any way (even by calling the {@link #remove remove} method or other mutator methods of {@code
   * Iterator} subtypes), unless an overriding class has specified a concurrent modification policy.
   *
   * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
   *
   * @param action The action to be performed for each element
   * @throws NullPointerException if the specified action is null
   */
  void forEachRemaining(T_CONS action);

  /**
   * An Iterator specialized for {@code int} values.
   *
   * @since 1.8
   */
  public static interface OfInt extends PrimitiveIterator<Integer, IntConsumer> {

    /**
     * Returns the next {@code int} element in the iteration.
     *
     * @return the next {@code int} element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    int nextInt();

    /**
     * Performs the given action for each remaining element until all elements have been processed
     * or the action throws an exception. Actions are performed in the order of iteration, if that
     * order is specified. Exceptions thrown by the action are relayed to the caller.
     *
     * <p>The behavior of an iterator is unspecified if the action modifies the underlying source of
     * elements in any way (even by calling the {@link Iterator#remove() remove} method or other
     * mutator methods of {@code Iterator} subtypes), unless an overriding class has specified a
     * concurrent modification policy.
     *
     * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
     *
     * <p><b>Implementation Requirements:</b><br>
     *
     * <p>The default implementation behaves as if:
     *
     * <pre>{@code
     * while (hasNext())
     *     action.accept(nextInt());
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     */
    void forEachRemaining(IntConsumer action);

    /**
     * Returns the next element in the iteration.
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default implementation boxes the result of calling {@link #nextInt()}, and returns that
     * boxed result.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    Integer next();
  }

  /**
   * An Iterator specialized for {@code long} values.
   *
   * @since 1.8
   */
  public static interface OfLong extends PrimitiveIterator<Long, LongConsumer> {

    /**
     * Returns the next {@code long} element in the iteration.
     *
     * @return the next {@code long} element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    long nextLong();

    /**
     * Performs the given action for each remaining element until all elements have been processed
     * or the action throws an exception. Actions are performed in the order of iteration, if that
     * order is specified. Exceptions thrown by the action are relayed to the caller.
     *
     * <p>The behavior of an iterator is unspecified if the action modifies the underlying source of
     * elements in any way (even by calling the {@link Iterator#remove() remove} method or other
     * mutator methods of {@code Iterator} subtypes), unless an overriding class has specified a
     * concurrent modification policy.
     *
     * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
     *
     * <p><b>Implementation Requirements:</b><br>
     *
     * <p>The default implementation behaves as if:
     *
     * <pre>{@code
     * while (hasNext())
     *     action.accept(nextLong());
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     */
    void forEachRemaining(LongConsumer action);

    /**
     * Returns the next element in the iteration.
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default implementation boxes the result of calling {@link #nextLong()}, and returns that
     * boxed result.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    Long next();
  }

  /**
   * An Iterator specialized for {@code double} values.
   *
   * @since 1.8
   */
  public static interface OfDouble extends PrimitiveIterator<Double, DoubleConsumer> {

    /**
     * Returns the next {@code double} element in the iteration.
     *
     * @return the next {@code double} element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    double nextDouble();

    /**
     * Performs the given action for each remaining element until all elements have been processed
     * or the action throws an exception. Actions are performed in the order of iteration, if that
     * order is specified. Exceptions thrown by the action are relayed to the caller.
     *
     * <p>The behavior of an iterator is unspecified if the action modifies the underlying source of
     * elements in any way (even by calling the {@link Iterator#remove() remove} method or other
     * mutator methods of {@code Iterator} subtypes), unless an overriding class has specified a
     * concurrent modification policy.
     *
     * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
     *
     * <p><b>Implementation Requirements:</b><br>
     *
     * <p>The default implementation behaves as if:
     *
     * <pre>{@code
     * while (hasNext())
     *     action.accept(nextDouble());
     * }</pre>
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     */
    void forEachRemaining(DoubleConsumer action);

    /**
     * Returns the next element in the iteration.
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default implementation boxes the result of calling {@link #nextDouble()}, and returns
     * that boxed result.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    @Override
    Double next();
  }
}
