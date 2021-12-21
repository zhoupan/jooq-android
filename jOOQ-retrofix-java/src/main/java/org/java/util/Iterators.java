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

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.java.util.function.Consumer;
import org.java.util.function.DoubleConsumer;
import org.java.util.function.IntConsumer;
import org.java.util.function.LongConsumer;

/**
 * A place for static default implementations of the new Java 8 default interface methods and static
 * interface methods in the {@link Iterator} interface.
 */
public final class Iterators {

  /**
   * Performs the given action for each remaining element until all elements have been processed or
   * the action throws an exception. Actions are performed in the order of iteration, if that order
   * is specified. Exceptions thrown by the action are relayed to the caller.
   *
   * <p>The behavior of an iterator is unspecified if the action modifies the source of elements in
   * any way (even by calling the {@link Iterator#remove remove} method or other mutator methods of
   * {@code Iterator} subtypes), unless an overriding class has specified a concurrent modification
   * policy.
   *
   * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
   *
   * <p>The implementation behaves as if:
   *
   * <pre>{@code
   * while (it.hasNext())
   *   action.accept(it.next());
   * }</pre>
   *
   * @param <E> the type of the elements for the passed iterator
   * @param it the {@code Iterator} whose remaining elements should be processed
   * @param action The action to be performed for each element
   * @throws NullPointerException if the specified iterator is null
   * @throws NullPointerException if the specified action is null
   * @since 1.8
   */
  public static <E> void forEachRemaining(Iterator<E> it, Consumer<? super E> action) {
    Objects.requireNonNull(it);
    Objects.requireNonNull(action);
    while (it.hasNext()) {
      action.accept(it.next());
    }
  }

  /**
   * Performs the given action for each remaining element until all elements have been processed or
   * the action throws an exception. Actions are performed in the order of iteration, if that order
   * is specified. Exceptions thrown by the action are relayed to the caller.
   *
   * <p>The behavior of an iterator is unspecified if the action modifies the source of elements in
   * any way (even by calling the {@link Iterator#remove remove} method or other mutator methods of
   * {@code Iterator} subtypes), unless an overriding class has specified a concurrent modification
   * policy.
   *
   * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
   *
   * <p>The implementation behaves as if:
   *
   * <pre>{@code
   * while (it.hasNext())
   *   action.accept(it.next());
   * }</pre>
   *
   * @param it the {@code Iterator} whose remaining elements should be processed
   * @param action The action to be performed for each element
   * @throws NullPointerException if the specified iterator is null
   * @throws NullPointerException if the specified action is null
   * @since 1.8
   */
  public static void forEachRemaining(PrimitiveIterator.OfInt it, IntConsumer action) {
    Objects.requireNonNull(it);
    Objects.requireNonNull(action);
    while (it.hasNext()) {
      action.accept(it.nextInt());
    }
  }

  /**
   * Performs the given action for each remaining element until all elements have been processed or
   * the action throws an exception. Actions are performed in the order of iteration, if that order
   * is specified. Exceptions thrown by the action are relayed to the caller.
   *
   * <p>The behavior of an iterator is unspecified if the action modifies the source of elements in
   * any way (even by calling the {@link Iterator#remove remove} method or other mutator methods of
   * {@code Iterator} subtypes), unless an overriding class has specified a concurrent modification
   * policy.
   *
   * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
   *
   * <p>The implementation behaves as if:
   *
   * <pre>{@code
   * while (it.hasNext())
   *   action.accept(it.next());
   * }</pre>
   *
   * @param it the {@code Iterator} whose remaining elements should be processed
   * @param action The action to be performed for each element
   * @throws NullPointerException if the specified iterator is null
   * @throws NullPointerException if the specified action is null
   * @since 1.8
   */
  public static void forEachRemaining(PrimitiveIterator.OfLong it, LongConsumer action) {
    Objects.requireNonNull(it);
    Objects.requireNonNull(action);
    while (it.hasNext()) {
      action.accept(it.nextLong());
    }
  }

  /**
   * Performs the given action for each remaining element until all elements have been processed or
   * the action throws an exception. Actions are performed in the order of iteration, if that order
   * is specified. Exceptions thrown by the action are relayed to the caller.
   *
   * <p>The behavior of an iterator is unspecified if the action modifies the source of elements in
   * any way (even by calling the {@link Iterator#remove remove} method or other mutator methods of
   * {@code Iterator} subtypes), unless an overriding class has specified a concurrent modification
   * policy.
   *
   * <p>Subsequent behavior of an iterator is unspecified if the action throws an exception.
   *
   * <p>The implementation behaves as if:
   *
   * <pre>{@code
   * while (it.hasNext())
   *   action.accept(it.next());
   * }</pre>
   *
   * @param it the {@code Iterator} whose remaining elements should be processed
   * @param action The action to be performed for each element
   * @throws NullPointerException if the specified iterator is null
   * @throws NullPointerException if the specified action is null
   * @since 1.8
   */
  public static void forEachRemaining(PrimitiveIterator.OfDouble it, DoubleConsumer action) {
    Objects.requireNonNull(it);
    Objects.requireNonNull(action);
    while (it.hasNext()) {
      action.accept(it.nextDouble());
    }
  }

  /**
   * Returns an {@link Iterator} that traverses the remaining elements covered by the passed
   * enumeration. Traversal is undefined if any methods are called on the passed enumeration after
   * the call to {@code asIterator}.
   *
   * <p><b>API Note:</b><br>
   * This method is intended to help adapt code that produces {@code Enumeration} instances to code
   * that consumes {@code Iterator} instances. For example, the {@link java.util.jar.JarFile#entries
   * JarFile.entries()} method returns an {@code Enumeration<JarEntry>}. This can be turned into an
   * {@code Iterator}, and then the {@code forEachRemaining()} method can be used:
   *
   * <pre>{@code
   * JarFile jarFile = ... ;
   * Iterators.forEachRemaining(Iterators.asIterator(jarFile.entries()), entry -> ... );
   * }</pre>
   *
   * <p><b>Implementation Requirements:</b><br>
   * The default implementation returns an {@code Iterator} whose {@link Iterator#hasNext hasNext}
   * method calls the passed Enumeration's {@code hasMoreElements} method, whose {@link
   * Iterator#next next} method calls the passed Enumeration's {@code nextElement} method, and whose
   * {@link Iterator#remove remove} method throws {@code UnsupportedOperationException}.
   *
   * @param <E> the type of the elements for the passed enumeration
   * @param en the {@code Enumeration} whose remaining elements should be processed
   * @return an Iterator representing the remaining elements of the passed Enumeration
   * @throws NullPointerException if the specified enumeration is null
   * @since 9
   */
  public static <E> Iterator<E> asIterator(Enumeration<E> en) {
    Objects.requireNonNull(en);
    return new ImmutableIt<E>() {

      @Override
      public boolean hasNext() {
        return en.hasMoreElements();
      }

      @Override
      public E next() {
        return en.nextElement();
      }
    };
  }

  /**
   * Returns an iterator that has no elements. More precisely,
   *
   * <ul>
   *   <li>{@link Iterator#hasNext hasNext} always returns {@code false}.
   *   <li>{@link Iterator#next next} always throws {@link NoSuchElementException}.
   *   <li>{@link Iterator#remove remove} always throws {@link IllegalStateException}.
   * </ul>
   *
   * <p>Implementations of this method are permitted, but not required, to return the same object
   * from multiple invocations.
   *
   * @param <T> type of elements, if there were any, in the iterator
   * @return an empty iterator
   * @since 1.7
   */
  @SuppressWarnings("unchecked")
  public static <T> Iterator<T> emptyIterator() {
    return (Iterator<T>) EmptyIt.EMPTY_ITERATOR;
  }

  static <E> Iterator<E> singletonIterator(E e) {
    return new ImmutableIt<E>() {

      private boolean hasNext = true;

      public boolean hasNext() {
        return hasNext;
      }

      public E next() {
        if (hasNext) {
          hasNext = false;
          return e;
        }
        throw new NoSuchElementException();
      }
    };
  }

  static final class EmptyIt<E> extends ImmutableIt<E> {

    static final EmptyIt<Object> EMPTY_ITERATOR = new EmptyIt<Object>();

    public boolean hasNext() {
      return false;
    }

    public E next() {
      throw new NoSuchElementException();
    }
  }

  abstract static class ImmutableIt<T> implements Iterator<T> {

    @Override
    public void remove() {
      throw new UnsupportedOperationException("remove");
    }
  }

  private Iterators() {}
}
