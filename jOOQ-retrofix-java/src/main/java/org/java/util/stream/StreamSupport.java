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

import java.util.Collection;
import org.java.util.Objects;
import org.java.util.Spliterator;
import org.java.util.Spliterators;
import org.java.util.function.Supplier;

/**
 * Low-level utility methods for creating and manipulating streams.
 *
 * <p>This class is mostly for library writers presenting stream views of data structures; most
 * static stream methods intended for end users are in the various {@code Streams} classes.
 *
 * @since 1.8
 */
public final class StreamSupport {

  // Suppresses default constructor, ensuring non-instantiability.
  private StreamSupport() {}

  /**
   * Creates a new sequential {@code Stream} using either the given collection's {@link
   * java.util.Collection#iterator()} as the source of elements for an internally created {@code
   * Spliterator} which will report the collection's {@link java.util.Collection#size()} as its
   * initial size or a specialized {@code Spliterator} implementation (effectively the same one that
   * Java 8 uses) if the passed {@code Collection} is one of the types listed below.
   *
   * <ul>
   *   <li>java.util.ArrayList
   *   <li>java.util.Arrays.ArrayList
   *   <li>java.util.ArrayDeque
   *   <li>java.util.Vector
   *   <li>java.util.LinkedList
   *   <li>java.util.HashSet
   *   <li>java.util.LinkedHashSet
   *   <li>java.util.PriorityQueue
   *   <li>java.util.concurrent.ArrayBlockingQueue
   *   <li>java.util.concurrent.LinkedBlockingQueue
   *   <li>java.util.concurrent.LinkedBlockingDeque
   *   <li>java.util.concurrent.PriorityBlockingQueue
   *   <li>java.util.concurrent.CopyOnWriteArrayList
   *   <li>java.util.concurrent.CopyOnWriteArraySet
   *   <li>The collections returned from the java.util.HashMap methods #keySet(), #entrySet() and
   *       #values()
   * </ul>
   *
   * <p>Otherwise, if the given collection is a {@link java.util.List}, the implementation creates a
   * <em><a href="../Spliterator.html#binding">late-binding</a></em> spliterator as follows:
   *
   * <ul>
   *   <li>If the list is an instance of {@link java.util.RandomAccess} then the default
   *       implementation creates a spliterator that traverses elements by invoking the method
   *       {@link java.util.List#get}. If such invocation results or would result in an {@code
   *       IndexOutOfBoundsException} then the spliterator will <em>fail-fast</em> and throw a
   *       {@code ConcurrentModificationException}. If the list is also an instance of {@link
   *       java.util.AbstractList} then the spliterator will use the list's {@link
   *       java.util.AbstractList#modCount modCount} field to provide additional <em>fail-fast</em>
   *       behavior.
   *   <li>Otherwise, the default implementation creates a spliterator from the list's {@code
   *       Iterator}. The spliterator inherits the <em>fail-fast</em> properties of the list's
   *       iterator.
   * </ul>
   *
   * <p>The {@code Spliterator}s for {@code CopyOnWriteArrayList} and {@code CopyOnWriteArraySet}
   * provide a snapshot of the state of the collection when the {@code Stream} was created,
   * otherwise the created spliterator is <em><a
   * href="../Spliterator.html#binding">late-binding</a></em>, inherits the <em>fail-fast</em>
   * properties of the collection's iterator, and implements {@code trySplit} to permit limited
   * parallelism.
   *
   * <p>The created spliterator is only traversed, split, or queried for estimated size after the
   * terminal operation of the stream pipeline commences.
   *
   * @param <T> Type of elements
   * @param c The collection
   * @return a new sequential {@code Stream}
   * @throws NullPointerException if the given collection is {@code null}
   */
  public static <T> Stream<T> stream(Collection<? extends T> c) {
    return stream(Spliterators.spliterator(c), false);
  }

  /**
   * Creates a new possibly parallel {@code Stream} using either the given collection's {@link
   * java.util.Collection#iterator()} as the source of elements for an internally created {@code
   * Spliterator} which will report the collection's {@link java.util.Collection#size()} as its
   * initial size or a specialized {@code Spliterator} implementation (effectively the same one that
   * Java 8 uses) if the passed {@code Collection} is one of the types listed below.
   *
   * <ul>
   *   <li>java.util.ArrayList
   *   <li>java.util.Arrays.ArrayList
   *   <li>java.util.ArrayDeque
   *   <li>java.util.Vector
   *   <li>java.util.LinkedList
   *   <li>java.util.HashSet
   *   <li>java.util.LinkedHashSet
   *   <li>java.util.PriorityQueue
   *   <li>java.util.concurrent.ArrayBlockingQueue
   *   <li>java.util.concurrent.LinkedBlockingQueue
   *   <li>java.util.concurrent.LinkedBlockingDeque
   *   <li>java.util.concurrent.PriorityBlockingQueue
   *   <li>java.util.concurrent.CopyOnWriteArrayList
   *   <li>java.util.concurrent.CopyOnWriteArraySet
   *   <li>The collections returned from the java.util.HashMap methods #keySet(), #entrySet() and
   *       #values()
   * </ul>
   *
   * <p>Otherwise, if the given collection is a {@link java.util.List}, the implementation creates a
   * <em><a href="../Spliterator.html#binding">late-binding</a></em> spliterator as follows:
   *
   * <ul>
   *   <li>If the list is an instance of {@link java.util.RandomAccess} then the default
   *       implementation creates a spliterator that traverses elements by invoking the method
   *       {@link java.util.List#get}. If such invocation results or would result in an {@code
   *       IndexOutOfBoundsException} then the spliterator will <em>fail-fast</em> and throw a
   *       {@code ConcurrentModificationException}. If the list is also an instance of {@link
   *       java.util.AbstractList} then the spliterator will use the list's {@link
   *       java.util.AbstractList#modCount modCount} field to provide additional <em>fail-fast</em>
   *       behavior.
   *   <li>Otherwise, the default implementation creates a spliterator from the list's {@code
   *       Iterator}. The spliterator inherits the <em>fail-fast</em> properties of the list's
   *       iterator.
   * </ul>
   *
   * <p>The {@code Spliterator}s for {@code CopyOnWriteArrayList} and {@code CopyOnWriteArraySet}
   * provide a snapshot of the state of the collection when the {@code Stream} was created,
   * otherwise the created spliterator is <em><a
   * href="../Spliterator.html#binding">late-binding</a></em>, inherits the <em>fail-fast</em>
   * properties of the collection's iterator, and implements {@code trySplit} to permit limited
   * parallelism.
   *
   * <p>The created spliterator is only traversed, split, or queried for estimated size after the
   * terminal operation of the stream pipeline commences.
   *
   * @param <T> Type of elements
   * @param c The collection
   * @return a new possibly parallel {@code Stream}
   * @throws NullPointerException if the given collection is {@code null}
   */
  public static <T> Stream<T> parallelStream(Collection<? extends T> c) {
    return stream(Spliterators.spliterator(c), true);
  }

  /**
   * Creates a new sequential {@code Stream} using the given collection's {@link
   * java.util.Collection#iterator()} as the source of elements for an internally created {@code
   * Spliterator} which will report the collection's {@link java.util.Collection#size()} as its
   * initial size.
   *
   * <p>The created spliterator is <em><a href="../Spliterator.html#binding">late-binding</a></em>,
   * inherits the <em>fail-fast</em> properties of the collection's iterator, and implements {@code
   * trySplit} to permit limited parallelism.
   *
   * <p>The created spliterator is only traversed, split, or queried for estimated size after the
   * terminal operation of the stream pipeline commences.
   *
   * <p>If the collection is immutable it is recommended to report a characteristic of {@code
   * IMMUTABLE}. The characteristics {@code SIZED} and {@code SUBSIZED} are additionally
   * (automatically) reported unless {@code CONCURRENT} is supplied.
   *
   * @param <T> Type of elements
   * @param c The collection
   * @param characteristics Characteristics of the source collection's iterator or elements. The
   *     characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported unless {@code
   *     CONCURRENT} is supplied.
   * @return a new sequential {@code Stream}
   * @throws NullPointerException if the given collection is {@code null}
   */
  public static <T> Stream<T> stream(Collection<? extends T> c, int characteristics) {
    return stream(c, characteristics, false);
  }

  /**
   * Creates a new sequential or parallel {@code Stream} using the given collection's {@link
   * java.util.Collection#iterator()} as the source of elements for an internally created {@code
   * Spliterator} which will report the collection's {@link java.util.Collection#size()} as its
   * initial size.
   *
   * <p>The created spliterator is <em><a href="../Spliterator.html#binding">late-binding</a></em>,
   * inherits the <em>fail-fast</em> properties of the collection's iterator, and implements {@code
   * trySplit} to permit limited parallelism.
   *
   * <p>The created spliterator is only traversed, split, or queried for estimated size after the
   * terminal operation of the stream pipeline commences.
   *
   * <p>If possible for the collection, it is strongly recommended to report a characteristic of
   * {@code IMMUTABLE} or {@code CONCURRENT} especially if you want a parallel {@code Stream}. The
   * characteristics {@code SIZED} and {@code SUBSIZED} are additionally (automatically) reported
   * unless {@code CONCURRENT} is supplied.
   *
   * @param <T> Type of elements
   * @param c The collection
   * @param characteristics Characteristics of the source collection's iterator or elements. The
   *     characteristics {@code SIZED} and {@code SUBSIZED} are additionally reported unless {@code
   *     CONCURRENT} is supplied.
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code Stream}
   * @throws NullPointerException if the given collection is {@code null}
   */
  public static <T> Stream<T> stream(
      Collection<? extends T> c, int characteristics, boolean parallel) {
    Objects.requireNonNull(c);
    return stream(Spliterators.spliterator(c, characteristics), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code Stream} from a {@code Spliterator}.
   *
   * <p>The spliterator is only traversed, split, or queried for estimated size after the terminal
   * operation of the stream pipeline commences.
   *
   * <p>It is strongly recommended the spliterator report a characteristic of {@code IMMUTABLE} or
   * {@code CONCURRENT}, or be <a href="../Spliterator.html#binding">late-binding</a>. Otherwise,
   * {@link #stream(org.java.util.function.Supplier, int, boolean)} should be used to reduce the
   * scope of potential interference with the source. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param <T> the type of stream elements
   * @param spliterator a {@code Spliterator} describing the stream elements
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code Stream}
   */
  public static <T> Stream<T> stream(Spliterator<T> spliterator, boolean parallel) {
    Objects.requireNonNull(spliterator);
    return new ReferencePipeline.Head<>(
        spliterator, StreamOpFlag.fromCharacteristics(spliterator), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code Stream} from a {@code Supplier} of {@code
   * Spliterator}.
   *
   * <p>The {@link Supplier#get()} method will be invoked on the supplier no more than once, and
   * only after the terminal operation of the stream pipeline commences.
   *
   * <p>For spliterators that report a characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or
   * that are <a href="../Spliterator.html#binding">late-binding</a>, it is likely more efficient to
   * use {@link #stream(org.java.util.Spliterator, boolean)} instead.
   *
   * <p>The use of a {@code Supplier} in this form provides a level of indirection that reduces the
   * scope of potential interference with the source. Since the supplier is only invoked after the
   * terminal operation commences, any modifications to the source up to the start of the terminal
   * operation are reflected in the stream result. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param <T> the type of stream elements
   * @param supplier a {@code Supplier} of a {@code Spliterator}
   * @param characteristics Spliterator characteristics of the supplied {@code Spliterator}. The
   *     characteristics must be equal to {@code supplier.get().characteristics()}, otherwise
   *     undefined behavior may occur when terminal operation commences.
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code Stream}
   * @see #stream(org.java.util.Spliterator, boolean)
   */
  public static <T> Stream<T> stream(
      Supplier<? extends Spliterator<T>> supplier, int characteristics, boolean parallel) {
    Objects.requireNonNull(supplier);
    return new ReferencePipeline.Head<>(
        supplier, StreamOpFlag.fromCharacteristics(characteristics), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code IntStream} from a {@code Spliterator.OfInt}.
   *
   * <p>The spliterator is only traversed, split, or queried for estimated size after the terminal
   * operation of the stream pipeline commences.
   *
   * <p>It is strongly recommended the spliterator report a characteristic of {@code IMMUTABLE} or
   * {@code CONCURRENT}, or be <a href="../Spliterator.html#binding">late-binding</a>. Otherwise,
   * {@link #intStream(org.java.util.function.Supplier, int, boolean)} should be used to reduce the
   * scope of potential interference with the source. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param spliterator a {@code Spliterator.OfInt} describing the stream elements
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code IntStream}
   */
  public static IntStream intStream(Spliterator.OfInt spliterator, boolean parallel) {
    return new IntPipeline.Head<>(
        spliterator, StreamOpFlag.fromCharacteristics(spliterator), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code IntStream} from a {@code Supplier} of {@code
   * Spliterator.OfInt}.
   *
   * <p>The {@link Supplier#get()} method will be invoked on the supplier no more than once, and
   * only after the terminal operation of the stream pipeline commences.
   *
   * <p>For spliterators that report a characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or
   * that are <a href="../Spliterator.html#binding">late-binding</a>, it is likely more efficient to
   * use {@link #intStream(org.java.util.Spliterator.OfInt, boolean)} instead.
   *
   * <p>The use of a {@code Supplier} in this form provides a level of indirection that reduces the
   * scope of potential interference with the source. Since the supplier is only invoked after the
   * terminal operation commences, any modifications to the source up to the start of the terminal
   * operation are reflected in the stream result. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param supplier a {@code Supplier} of a {@code Spliterator.OfInt}
   * @param characteristics Spliterator characteristics of the supplied {@code Spliterator.OfInt}.
   *     The characteristics must be equal to {@code supplier.get().characteristics()}, otherwise
   *     undefined behavior may occur when terminal operation commences.
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code IntStream}
   * @see #intStream(org.java.util.Spliterator.OfInt, boolean)
   */
  public static IntStream intStream(
      Supplier<? extends Spliterator.OfInt> supplier, int characteristics, boolean parallel) {
    return new IntPipeline.Head<>(
        supplier, StreamOpFlag.fromCharacteristics(characteristics), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code LongStream} from a {@code Spliterator.OfLong}.
   *
   * <p>The spliterator is only traversed, split, or queried for estimated size after the terminal
   * operation of the stream pipeline commences.
   *
   * <p>It is strongly recommended the spliterator report a characteristic of {@code IMMUTABLE} or
   * {@code CONCURRENT}, or be <a href="../Spliterator.html#binding">late-binding</a>. Otherwise,
   * {@link #longStream(org.java.util.function.Supplier, int, boolean)} should be used to reduce the
   * scope of potential interference with the source. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param spliterator a {@code Spliterator.OfLong} describing the stream elements
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code LongStream}
   */
  public static LongStream longStream(Spliterator.OfLong spliterator, boolean parallel) {
    return new LongPipeline.Head<>(
        spliterator, StreamOpFlag.fromCharacteristics(spliterator), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code LongStream} from a {@code Supplier} of {@code
   * Spliterator.OfLong}.
   *
   * <p>The {@link Supplier#get()} method will be invoked on the supplier no more than once, and
   * only after the terminal operation of the stream pipeline commences.
   *
   * <p>For spliterators that report a characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or
   * that are <a href="../Spliterator.html#binding">late-binding</a>, it is likely more efficient to
   * use {@link #longStream(org.java.util.Spliterator.OfLong, boolean)} instead.
   *
   * <p>The use of a {@code Supplier} in this form provides a level of indirection that reduces the
   * scope of potential interference with the source. Since the supplier is only invoked after the
   * terminal operation commences, any modifications to the source up to the start of the terminal
   * operation are reflected in the stream result. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param supplier a {@code Supplier} of a {@code Spliterator.OfLong}
   * @param characteristics Spliterator characteristics of the supplied {@code Spliterator.OfLong}.
   *     The characteristics must be equal to {@code supplier.get().characteristics()}, otherwise
   *     undefined behavior may occur when terminal operation commences.
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code LongStream}
   * @see #longStream(org.java.util.Spliterator.OfLong, boolean)
   */
  public static LongStream longStream(
      Supplier<? extends Spliterator.OfLong> supplier, int characteristics, boolean parallel) {
    return new LongPipeline.Head<>(
        supplier, StreamOpFlag.fromCharacteristics(characteristics), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code DoubleStream} from a {@code Spliterator.OfDouble}.
   *
   * <p>The spliterator is only traversed, split, or queried for estimated size after the terminal
   * operation of the stream pipeline commences.
   *
   * <p>It is strongly recommended the spliterator report a characteristic of {@code IMMUTABLE} or
   * {@code CONCURRENT}, or be <a href="../Spliterator.html#binding">late-binding</a>. Otherwise,
   * {@link #doubleStream(org.java.util.function.Supplier, int, boolean)} should be used to reduce
   * the scope of potential interference with the source. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param spliterator A {@code Spliterator.OfDouble} describing the stream elements
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code DoubleStream}
   */
  public static DoubleStream doubleStream(Spliterator.OfDouble spliterator, boolean parallel) {
    return new DoublePipeline.Head<>(
        spliterator, StreamOpFlag.fromCharacteristics(spliterator), parallel);
  }

  /**
   * Creates a new sequential or parallel {@code DoubleStream} from a {@code Supplier} of {@code
   * Spliterator.OfDouble}.
   *
   * <p>The {@link Supplier#get()} method will be invoked on the supplier no more than once, and
   * only after the terminal operation of the stream pipeline commences.
   *
   * <p>For spliterators that report a characteristic of {@code IMMUTABLE} or {@code CONCURRENT}, or
   * that are <a href="../Spliterator.html#binding">late-binding</a>, it is likely more efficient to
   * use {@link #doubleStream(org.java.util.Spliterator.OfDouble, boolean)} instead.
   *
   * <p>The use of a {@code Supplier} in this form provides a level of indirection that reduces the
   * scope of potential interference with the source. Since the supplier is only invoked after the
   * terminal operation commences, any modifications to the source up to the start of the terminal
   * operation are reflected in the stream result. See <a
   * href="package-summary.html#NonInterference">Non-Interference</a> for more details.
   *
   * @param supplier A {@code Supplier} of a {@code Spliterator.OfDouble}
   * @param characteristics Spliterator characteristics of the supplied {@code
   *     Spliterator.OfDouble}. The characteristics must be equal to {@code
   *     supplier.get().characteristics()}, otherwise undefined behavior may occur when terminal
   *     operation commences.
   * @param parallel if {@code true} then the returned stream is a parallel stream; if {@code false}
   *     the returned stream is a sequential stream.
   * @return a new sequential or parallel {@code DoubleStream}
   * @see #doubleStream(org.java.util.Spliterator.OfDouble, boolean)
   */
  public static DoubleStream doubleStream(
      Supplier<? extends Spliterator.OfDouble> supplier, int characteristics, boolean parallel) {
    return new DoublePipeline.Head<>(
        supplier, StreamOpFlag.fromCharacteristics(characteristics), parallel);
  }

  static Runnable closeHandler(BaseStream<?, ?> stream) {
    return stream::close;
  }
}
