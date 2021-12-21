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
package org.java.util.stream;

import org.java.util.Spliterator;
import org.java.util.function.Consumer;
import org.java.util.function.DoubleConsumer;
import org.java.util.function.IntConsumer;
import org.java.util.function.IntFunction;
import org.java.util.function.LongConsumer;

/**
 * An immutable container for describing an ordered sequence of elements of some type {@code T}.
 *
 * <p>A {@code Node} contains a fixed number of elements, which can be accessed via the {@link
 * #count}, {@link #spliterator}, {@link #forEach}, {@link #asArray}, or {@link #copyInto} methods.
 * A {@code Node} may have zero or more child {@code Node}s; if it has no children (accessed via
 * {@link #getChildCount} and {@link #getChild(int)}, it is considered <em>flat </em> or a
 * <em>leaf</em>; if it has children, it is considered an <em>internal</em> node. The size of an
 * internal node is the sum of sizes of its children.
 *
 * <p><b>API Note:</b><br>
 *
 * <p>A {@code Node} typically does not store the elements directly, but instead mediates access to
 * one or more existing (effectively immutable) data structures such as a {@code Collection}, array,
 * or a set of other {@code Node}s. Commonly {@code Node}s are formed into a tree whose shape
 * corresponds to the computation tree that produced the elements that are contained in the leaf
 * nodes. The use of {@code Node} within the stream framework is largely to avoid copying data
 * unnecessarily during parallel operations.
 *
 * @param <T> the type of elements.
 * @since 1.8
 */
interface Node<T> {

  /**
   * Returns a {@link Spliterator} describing the elements contained in this {@code Node}.
   *
   * @return a {@code Spliterator} describing the elements contained in this {@code Node}
   */
  Spliterator<T> spliterator();

  /**
   * Traverses the elements of this node, and invoke the provided {@code Consumer} with each
   * element. Elements are provided in encounter order if the source for the {@code Node} has a
   * defined encounter order.
   *
   * @param consumer a {@code Consumer} that is to be invoked with each element in this {@code Node}
   */
  void forEach(Consumer<? super T> consumer);

  /**
   * Gets the {@code StreamShape} associated with this {@code Node}.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default in {@code Node} returns {@code StreamShape.REFERENCE}
   *
   * @return the stream shape associated with this node
   */
  StreamShape getShape();

  /**
   * Returns the number of child nodes of this node.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation returns zero.
   *
   * @return the number of child nodes
   */
  int getChildCount();

  /**
   * Retrieves the child {@code Node} at a given index.
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation always throws {@code IndexOutOfBoundsException}.
   *
   * @param i the index to the child node
   * @return the child node
   * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the
   *     number of child nodes
   */
  Node<T> getChild(int i);

  /**
   * Return a node describing a subsequence of the elements of this node, starting at the given
   * inclusive start offset and ending at the given exclusive end offset.
   *
   * @param from The (inclusive) starting offset of elements to include, must be in range
   *     0..count().
   * @param to The (exclusive) end offset of elements to include, must be in range 0..count().
   * @param generator A function to be used to create a new array, if needed, for reference nodes.
   * @return the truncated node
   */
  Node<T> truncate(long from, long to, IntFunction<T[]> generator);

  /**
   * Provides an array view of the contents of this node.
   *
   * <p>Depending on the underlying implementation, this may return a reference to an internal array
   * rather than a copy. Since the returned array may be shared, the returned array should not be
   * modified. The {@code generator} function may be consulted to create the array if a new array
   * needs to be created.
   *
   * @param generator a factory function which takes an integer parameter and returns a new, empty
   *     array of that size and of the appropriate array type
   * @return an array containing the contents of this {@code Node}
   */
  T[] asArray(IntFunction<T[]> generator);

  /**
   * Copies the content of this {@code Node} into an array, starting at a given offset into the
   * array. It is the caller's responsibility to ensure there is sufficient room in the array,
   * otherwise unspecified behaviour will occur if the array length is less than the number of
   * elements contained in this node.
   *
   * @param array the array into which to copy the contents of this {@code Node}
   * @param offset the starting offset within the array
   * @throws IndexOutOfBoundsException if copying would cause access of data outside array bounds
   * @throws NullPointerException if {@code array} is {@code null}
   */
  void copyInto(T[] array, int offset);

  /**
   * Returns the number of elements contained in this node.
   *
   * @return the number of elements contained in this node
   */
  long count();

  /**
   * A mutable builder for a {@code Node} that implements {@link Sink}, which builds a flat node
   * containing the elements that have been pushed to it.
   */
  interface Builder<T> extends Sink<T> {

    /**
     * Builds the node. Should be called after all elements have been pushed and signalled with an
     * invocation of {@link Sink#end()}.
     *
     * @return the resulting {@code Node}
     */
    Node<T> build();

    /** Specialized {@code Node.Builder} for int elements */
    interface OfInt extends Node.Builder<Integer>, Sink.OfInt {

      @Override
      Node.OfInt build();
    }

    /** Specialized {@code Node.Builder} for long elements */
    interface OfLong extends Node.Builder<Long>, Sink.OfLong {

      @Override
      Node.OfLong build();
    }

    /** Specialized {@code Node.Builder} for double elements */
    interface OfDouble extends Node.Builder<Double>, Sink.OfDouble {

      @Override
      Node.OfDouble build();
    }
  }

  public interface OfPrimitive<
          T,
          T_CONS,
          T_ARR,
          T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>,
          T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>>
      extends Node<T> {

    /**
     * {@inheritDoc}
     *
     * @return a {@link Spliterator.OfPrimitive} describing the elements of this node
     */
    @Override
    T_SPLITR spliterator();

    /**
     * Traverses the elements of this node, and invoke the provided {@code action} with each
     * element.
     *
     * @param action a consumer that is to be invoked with each element in this {@code
     *     Node.OfPrimitive}
     */
    void forEach(T_CONS action);

    @Override
    T_NODE getChild(int i);

    T_NODE truncate(long from, long to, IntFunction<T[]> generator);

    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * the default implementation invokes the generator to create an instance of a boxed primitive
     * array with a length of {@link #count()} and then invokes {@link #copyInto(T[], int)} with
     * that array at an offset of 0.
     */
    @Override
    T[] asArray(IntFunction<T[]> generator);

    /**
     * Views this node as a primitive array.
     *
     * <p>Depending on the underlying implementation this may return a reference to an internal
     * array rather than a copy. It is the callers responsibility to decide if either this node or
     * the array is utilized as the primary reference for the data.
     *
     * @return an array containing the contents of this {@code Node}
     */
    T_ARR asPrimitiveArray();

    /**
     * Creates a new primitive array.
     *
     * @param count the length of the primitive array.
     * @return the new primitive array.
     */
    T_ARR newArray(int count);

    /**
     * Copies the content of this {@code Node} into a primitive array, starting at a given offset
     * into the array. It is the caller's responsibility to ensure there is sufficient room in the
     * array.
     *
     * @param array the array into which to copy the contents of this {@code Node}
     * @param offset the starting offset within the array
     * @throws IndexOutOfBoundsException if copying would cause access of data outside array bounds
     * @throws NullPointerException if {@code array} is {@code null}
     */
    void copyInto(T_ARR array, int offset);
  }

  /** Specialized {@code Node} for int elements */
  interface OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, OfInt> {

    /**
     * {@inheritDoc}
     *
     * @param consumer a {@code Consumer} that is to be invoked with each element in this {@code
     *     Node}. If this is an {@code IntConsumer}, it is cast to {@code IntConsumer} so the
     *     elements may be processed without boxing.
     */
    @Override
    void forEach(Consumer<? super Integer> consumer);

    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * the default implementation invokes {@link #asPrimitiveArray()} to obtain an int[] array then
     * and copies the elements from that int[] array into the boxed Integer[] array. This is not
     * efficient and it is recommended to invoke {@link #copyInto(Object, int)}.
     */
    @Override
    void copyInto(Integer[] boxed, int offset);

    @Override
    Node.OfInt truncate(long from, long to, IntFunction<Integer[]> generator);

    @Override
    int[] newArray(int count);

    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default in {@code Node.OfInt} returns {@code StreamShape.INT_VALUE}
     */
    StreamShape getShape();
  }

  /** Specialized {@code Node} for long elements */
  interface OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, OfLong> {

    /**
     * {@inheritDoc}
     *
     * @param consumer A {@code Consumer} that is to be invoked with each element in this {@code
     *     Node}. If this is an {@code LongConsumer}, it is cast to {@code LongConsumer} so the
     *     elements may be processed without boxing.
     */
    @Override
    void forEach(Consumer<? super Long> consumer);

    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * the default implementation invokes {@link #asPrimitiveArray()} to obtain a long[] array then
     * and copies the elements from that long[] array into the boxed Long[] array. This is not
     * efficient and it is recommended to invoke {@link #copyInto(Object, int)}.
     */
    @Override
    void copyInto(Long[] boxed, int offset);

    @Override
    Node.OfLong truncate(long from, long to, IntFunction<Long[]> generator);

    @Override
    long[] newArray(int count);

    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default in {@code Node.OfLong} returns {@code StreamShape.LONG_VALUE}
     */
    StreamShape getShape();
  }

  /** Specialized {@code Node} for double elements */
  interface OfDouble
      extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, OfDouble> {

    /**
     * {@inheritDoc}
     *
     * @param consumer A {@code Consumer} that is to be invoked with each element in this {@code
     *     Node}. If this is an {@code DoubleConsumer}, it is cast to {@code DoubleConsumer} so the
     *     elements may be processed without boxing.
     */
    @Override
    void forEach(Consumer<? super Double> consumer);

    //
    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * the default implementation invokes {@link #asPrimitiveArray()} to obtain a double[] array
     * then and copies the elements from that double[] array into the boxed Double[] array. This is
     * not efficient and it is recommended to invoke {@link #copyInto(Object, int)}.
     */
    @Override
    void copyInto(Double[] boxed, int offset);

    @Override
    Node.OfDouble truncate(long from, long to, IntFunction<Double[]> generator);

    @Override
    double[] newArray(int count);

    /**
     * {@inheritDoc}
     *
     * <p><b>Implementation Requirements:</b><br>
     * The default in {@code Node.OfDouble} returns {@code StreamShape.DOUBLE_VALUE}
     */
    StreamShape getShape();
  }
}
