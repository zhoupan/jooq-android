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
package org.jooq;

import static org.jooq.SQLDialect.*;

import java.util.Collection;
import org.java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

/**
 * A row value expression.
 *
 * <p>Row value expressions are mainly useful for use in predicates, when comparing several values
 * in one go, which can be more elegant than expanding the row value expression predicate in other
 * equivalent syntaxes. This is especially true for non-equality predicates. For instance, the
 * following two predicates are equivalent in SQL:
 *
 * <p><code><pre>
 * (A, B) &gt; (X, Y)
 * (A &gt; X) OR (A = X AND B &gt; Y)
 * </pre></code>
 *
 * <p><strong>Example:</strong>
 *
 * <p><code><pre>
 * // Assuming import static org.jooq.impl.DSL.*;
 *
 * using(configuration)
 *    .select()
 *    .from(CUSTOMER)
 *    .where(row(CUSTOMER.FIRST_NAME, CUSTOMER.LAST_NAME).in(
 *        select(ACTOR.FIRST_NAME, ACTOR.LAST_NAME).from(ACTOR)
 *    ))
 *    .fetch();
 * </pre></code>
 *
 * <p>Note: Not all databases support row value expressions, but many row value expression
 * operations can be emulated on all databases. See relevant row value expression method Javadocs
 * for details.
 *
 * <p>Instances can be created using {@link DSL#row(Object...)} and overloads.
 *
 * @author Lukas Eder
 */
public interface Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>
    extends Row, SelectField<Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> {

  // ------------------------------------------------------------------------
  // Mapping convenience methods
  // ------------------------------------------------------------------------
  /**
   * A convenience method to define a local {@link Record11} to custom type {@link RecordMapper}
   * that can be used when projecting {@link Row} types in <code>SELECT</code> or <code>RETURNING
   * </code> clauses.
   *
   * <p>EXPERIMENTAL. Unlike {@link #mapping(Class, Function11)}, this method attempts to work
   * without an explicit {@link Class} reference for the underlying {@link Converter#toType()}.
   * There may be some edge cases where this doesn't work. Please report any bugs here: <a
   * href="https://github.com/jOOQ/jOOQ/issues/new/choose">https://github.com/jOOQ/jOOQ/issues/new/choose</a>
   *
   * <p>Known issues include:
   *
   * <p>
   *
   * <ul>
   *   <li>When nesting rows in arrays, the class literal is required for reflective array creation.
   * </ul>
   */
  @NotNull
  @Internal
  <U> SelectField<U> mapping(
      Function11<
              ? super T1,
              ? super T2,
              ? super T3,
              ? super T4,
              ? super T5,
              ? super T6,
              ? super T7,
              ? super T8,
              ? super T9,
              ? super T10,
              ? super T11,
              ? extends U>
          function);

  /**
   * A convenience method to define a local {@link Record11} to custom type {@link RecordMapper}
   * that can be used when projecting {@link Row} types in <code>SELECT</code> or <code>RETURNING
   * </code> clauses.
   */
  @NotNull
  <U> SelectField<U> mapping(
      Class<U> uType,
      Function11<
              ? super T1,
              ? super T2,
              ? super T3,
              ? super T4,
              ? super T5,
              ? super T6,
              ? super T7,
              ? super T8,
              ? super T9,
              ? super T10,
              ? super T11,
              ? extends U>
          function);

  // ------------------------------------------------------------------------
  // Field accessors
  // ------------------------------------------------------------------------
  /** Get the first field. */
  @NotNull
  Field<T1> field1();

  /** Get the second field. */
  @NotNull
  Field<T2> field2();

  /** Get the third field. */
  @NotNull
  Field<T3> field3();

  /** Get the fourth field. */
  @NotNull
  Field<T4> field4();

  /** Get the fifth field. */
  @NotNull
  Field<T5> field5();

  /** Get the sixth field. */
  @NotNull
  Field<T6> field6();

  /** Get the seventh field. */
  @NotNull
  Field<T7> field7();

  /** Get the eighth field. */
  @NotNull
  Field<T8> field8();

  /** Get the ninth field. */
  @NotNull
  Field<T9> field9();

  /** Get the tenth field. */
  @NotNull
  Field<T10> field10();

  /** Get the eleventh field. */
  @NotNull
  Field<T11> field11();

  // ------------------------------------------------------------------------
  // Generic comparison predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Row11)
   * @see #notEqual(Row11)
   * @see #lessThan(Row11)
   * @see #lessOrEqual(Row11)
   * @see #greaterThan(Row11)
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Record11)
   * @see #notEqual(Record11)
   * @see #lessThan(Record11)
   * @see #lessOrEqual(Record11)
   * @see #greaterThan(Record11)
   * @see #greaterOrEqual(Record11)
   */
  @NotNull
  @Support
  Condition compare(
      Comparator comparator, Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Row11)
   * @see #notEqual(Row11)
   * @see #lessThan(Row11)
   * @see #lessOrEqual(Row11)
   * @see #greaterThan(Row11)
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition compare(
      Comparator comparator,
      T1 t1,
      T2 t2,
      T3 t3,
      T4 t4,
      T5 t5,
      T6 t6,
      T7 t7,
      T8 t8,
      T9 t9,
      T10 t10,
      T11 t11);

  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Row11)
   * @see #notEqual(Row11)
   * @see #lessThan(Row11)
   * @see #lessOrEqual(Row11)
   * @see #greaterThan(Row11)
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition compare(
      Comparator comparator,
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Select)
   * @see #notEqual(Select)
   * @see #lessThan(Select)
   * @see #lessOrEqual(Select)
   * @see #greaterThan(Select)
   * @see #greaterOrEqual(Select)
   */
  @NotNull
  @Support
  Condition compare(
      Comparator comparator,
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Select)
   * @see #notEqual(Select)
   * @see #lessThan(Select)
   * @see #lessOrEqual(Select)
   * @see #greaterThan(Select)
   * @see #greaterOrEqual(Select)
   */
  @NotNull
  @Support
  Condition compare(
      Comparator comparator,
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  // ------------------------------------------------------------------------
  // Equal / Not equal comparison predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * <p>Row equality comparison predicates can be emulated in those databases that do not support
   * such predicates natively: <code>(A, B) = (1, 2)</code> is equivalent to <code>A = 1 AND B = 2
   * </code>
   */
  @NotNull
  @Support
  Condition equal(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition equal(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition equal(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition equal(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition equal(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition equal(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition eq(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition eq(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition eq(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition eq(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #equal(Row11)
   */
  @NotNull
  @Support
  Condition eq(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition eq(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * <p>Row non-equality comparison predicates can be emulated in those databases that do not
   * support such predicates natively: <code>(A, B) &lt;&gt; (1, 2)</code> is equivalent to <code>
   * NOT(A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition notEqual(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for non-equality
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition notEqual(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for. non-equality
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition notEqual(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition notEqual(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition notEqual(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition notEqual(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition ne(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition ne(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition ne(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition ne(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notEqual(Row11)
   */
  @NotNull
  @Support
  Condition ne(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition ne(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  // ------------------------------------------------------------------------
  // [NOT] DISTINCT predicates
  // ------------------------------------------------------------------------
  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  // ------------------------------------------------------------------------
  // Ordering comparison predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B, C) &lt; (1, 2, 3)</code> is equivalent to <code>
   * A &lt; 1 OR (A = 1 AND B &lt; 2) OR (A = 1 AND B = 2 AND C &lt; 3)</code>
   */
  @NotNull
  @Support
  Condition lessThan(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lessThan(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lessThan(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lessThan(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lessThan(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition lessThan(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lt(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lt(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lt(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lt(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessThan(Row11)
   */
  @NotNull
  @Support
  Condition lt(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition lt(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B) &lt;= (1, 2)</code> is equivalent to <code>
   * A &lt; 1 OR (A = 1 AND B &lt; 2) OR (A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition lessOrEqual(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition lessOrEqual(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition lessOrEqual(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition lessOrEqual(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition lessOrEqual(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition le(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition le(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition le(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition le(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition le(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition le(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B, C) &gt; (1, 2, 3)</code> is equivalent to <code>
   * A &gt; 1 OR (A = 1 AND B &gt; 2) OR (A = 1 AND B = 2 AND C &gt; 3)</code>
   */
  @NotNull
  @Support
  Condition greaterThan(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition greaterThan(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition greaterThan(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition greaterThan(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition greaterThan(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition greaterThan(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition gt(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition gt(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition gt(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition gt(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterThan(Row11)
   */
  @NotNull
  @Support
  Condition gt(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition gt(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B) &gt;= (1, 2)</code> is equivalent to <code>
   * A &gt; 1 OR (A = 1 AND B &gt; 2) OR (A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(
      T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(
      Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition ge(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition ge(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition ge(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition ge(
      Field<T1> t1,
      Field<T2> t2,
      Field<T3> t3,
      Field<T4> t4,
      Field<T5> t5,
      Field<T6> t6,
      Field<T7> t7,
      Field<T8> t8,
      Field<T9> t9,
      Field<T10> t10,
      Field<T11> t11);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterOrEqual(Row11)
   */
  @NotNull
  @Support
  Condition ge(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support
  Condition ge(
      QuantifiedSelect<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  // ------------------------------------------------------------------------
  // [NOT] BETWEEN predicates
  // ------------------------------------------------------------------------
  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> between(
      T1 minValue1,
      T2 minValue2,
      T3 minValue3,
      T4 minValue4,
      T5 minValue5,
      T6 minValue6,
      T7 minValue7,
      T8 minValue8,
      T9 minValue9,
      T10 minValue10,
      T11 minValue11);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> between(
      Field<T1> minValue1,
      Field<T2> minValue2,
      Field<T3> minValue3,
      Field<T4> minValue4,
      Field<T5> minValue5,
      Field<T6> minValue6,
      Field<T7> minValue7,
      Field<T8> minValue8,
      Field<T9> minValue9,
      Field<T10> minValue10,
      Field<T11> minValue11);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> between(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * @see #between(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> between(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * <p>This is the same as calling <code>between(minValue).and(maxValue)</code>
   *
   * <p>The expression <code>A BETWEEN B AND C</code> is equivalent to the expression <code>
   * A &gt;= B AND A &lt;= C</code> for those SQL dialects that do not properly support the <code>
   * BETWEEN</code> predicate for row value expressions
   */
  @NotNull
  @Support
  Condition between(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * <p>This is the same as calling <code>between(minValue).and(maxValue)</code>
   *
   * @see #between(Row11, Row11)
   */
  @NotNull
  @Support
  Condition between(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> betweenSymmetric(
      T1 minValue1,
      T2 minValue2,
      T3 minValue3,
      T4 minValue4,
      T5 minValue5,
      T6 minValue6,
      T7 minValue7,
      T8 minValue8,
      T9 minValue9,
      T10 minValue10,
      T11 minValue11);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> betweenSymmetric(
      Field<T1> minValue1,
      Field<T2> minValue2,
      Field<T3> minValue3,
      Field<T4> minValue4,
      Field<T5> minValue5,
      Field<T6> minValue6,
      Field<T7> minValue7,
      Field<T8> minValue8,
      Field<T9> minValue9,
      Field<T10> minValue10,
      Field<T11> minValue11);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> betweenSymmetric(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is within a symmetric range of two records.
   *
   * @see #betweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> betweenSymmetric(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * <p>This is the same as calling <code>betweenSymmetric(minValue).and(maxValue)</code>
   *
   * <p>The expression <code>A BETWEEN SYMMETRIC B AND C</code> is equivalent to the expression
   * <code>(A &gt;= B AND A &lt;= C) OR (A &gt;= C AND A &lt;= B)</code> for those SQL dialects that
   * do not properly support the <code>BETWEEN</code> predicate for row value expressions
   */
  @NotNull
  @Support
  Condition betweenSymmetric(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is within a symmetric range of two records.
   *
   * <p>This is the same as calling <code>betweenSymmetric(minValue).and(maxValue)</code>
   *
   * @see #betweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  Condition betweenSymmetric(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #between(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetween(
      T1 minValue1,
      T2 minValue2,
      T3 minValue3,
      T4 minValue4,
      T5 minValue5,
      T6 minValue6,
      T7 minValue7,
      T8 minValue8,
      T9 minValue9,
      T10 minValue10,
      T11 minValue11);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #notBetween(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetween(
      Field<T1> minValue1,
      Field<T2> minValue2,
      Field<T3> minValue3,
      Field<T4> minValue4,
      Field<T5> minValue5,
      Field<T6> minValue6,
      Field<T7> minValue7,
      Field<T8> minValue8,
      Field<T9> minValue9,
      Field<T10> minValue10,
      Field<T11> minValue11);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #notBetween(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetween(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * @see #notBetween(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetween(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * <p>This is the same as calling <code>notBetween(minValue).and(maxValue)</code>
   *
   * <p>The expression <code>A NOT BETWEEN B AND C</code> is equivalent to the expression <code>
   * A &lt; B OR A &gt; C</code> for those SQL dialects that do not properly support the <code>
   * BETWEEN</code> predicate for row value expressions
   */
  @NotNull
  @Support
  Condition notBetween(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * <p>This is the same as calling <code>notBetween(minValue).and(maxValue)</code>
   *
   * @see #notBetween(Row11, Row11)
   */
  @NotNull
  @Support
  Condition notBetween(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetweenSymmetric(
      T1 minValue1,
      T2 minValue2,
      T3 minValue3,
      T4 minValue4,
      T5 minValue5,
      T6 minValue6,
      T7 minValue7,
      T8 minValue8,
      T9 minValue9,
      T10 minValue10,
      T11 minValue11);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetweenSymmetric(
      Field<T1> minValue1,
      Field<T2> minValue2,
      Field<T3> minValue3,
      Field<T4> minValue4,
      Field<T5> minValue5,
      Field<T6> minValue6,
      Field<T7> minValue7,
      Field<T8> minValue8,
      Field<T9> minValue9,
      Field<T10> minValue10,
      Field<T11> minValue11);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetweenSymmetric(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is not within a symmetric range of two records.
   *
   * @see #notBetweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  BetweenAndStep11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> notBetweenSymmetric(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * <p>This is the same as calling <code>notBetweenSymmetric(minValue).and(maxValue)</code>
   *
   * <p>The expression <code>A NOT BETWEEN SYMMETRIC B AND C</code> is equivalent to the expression
   * <code>(A &lt; B OR A &gt; C) AND (A &lt; C OR A &gt; B)</code> for those SQL dialects that do
   * not properly support the <code>BETWEEN</code> predicate for row value expressions
   */
  @NotNull
  @Support
  Condition notBetweenSymmetric(
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  /**
   * Check if this row value expression is not within a symmetric range of two records.
   *
   * <p>This is the same as calling <code>notBetweenSymmetric(minValue).and(maxValue)</code>
   *
   * @see #notBetweenSymmetric(Row11, Row11)
   */
  @NotNull
  @Support
  Condition notBetweenSymmetric(
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> minValue,
      Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> maxValue);

  // ------------------------------------------------------------------------
  // [NOT] DISTINCT predicates
  // ------------------------------------------------------------------------
  // ------------------------------------------------------------------------
  // [NOT] IN predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this row value expression with a set of row value expressions for equality.
   *
   * <p>Row IN predicates can be emulated in those databases that do not support such predicates
   * natively: <code>(A, B) IN ((1, 2), (3, 4))</code> is equivalent to <code>
   * ((A, B) = (1, 2)) OR ((A, B) = (3, 4))</code>, which is equivalent to <code>
   * (A = 1 AND B = 2) OR (A = 3 AND B = 4)</code>
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>IN</code> predicates can cause
   * cursor cache contention in some databases that use unique SQL strings as a statement identifier
   * (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use {@link
   * Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>IN</code> predicates on temporary tables
   *   <li><code>IN</code> predicates on unnested array bind variables
   * </ul>
   *
   * @see Rows#toRowList(Function, Function, Function, Function, Function, Function, Function,
   *     Function, Function, Function, Function)
   */
  @NotNull
  @Support
  Condition in(Collection<? extends Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> rows);

  /**
   * Compare this row value expression with a set of records for equality.
   *
   * <p>Row IN predicates can be emulated in those databases that do not support such predicates
   * natively: <code>(A, B) IN ((1, 2), (3, 4))</code> is equivalent to <code>
   * ((A, B) = (1, 2)) OR ((A, B) = (3, 4))</code>, which is equivalent to <code>
   * (A = 1 AND B = 2) OR (A = 3 AND B = 4)</code>
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>IN</code> predicates can cause
   * cursor cache contention in some databases that use unique SQL strings as a statement identifier
   * (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use {@link
   * Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>IN</code> predicates on temporary tables
   *   <li><code>IN</code> predicates on unnested array bind variables
   * </ul>
   */
  @NotNull
  @Support
  Condition in(Result<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> result);

  /**
   * Compare this row value expression with a set of row value expressions for equality.
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>IN</code> predicates can cause
   * cursor cache contention in some databases that use unique SQL strings as a statement identifier
   * (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use {@link
   * Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>IN</code> predicates on temporary tables
   *   <li><code>IN</code> predicates on unnested array bind variables
   * </ul>
   *
   * @see #in(Collection)
   * @see Rows#toRowArray(Function, Function, Function, Function, Function, Function, Function,
   *     Function, Function, Function, Function)
   */
  @SuppressWarnings("unchecked")
  @NotNull
  @Support
  Condition in(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>... rows);

  /**
   * Compare this row value expression with a set of records for equality.
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>IN</code> predicates can cause
   * cursor cache contention in some databases that use unique SQL strings as a statement identifier
   * (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use {@link
   * Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>IN</code> predicates on temporary tables
   *   <li><code>IN</code> predicates on unnested array bind variables
   * </ul>
   *
   * @see #in(Collection)
   */
  @SuppressWarnings("unchecked")
  @NotNull
  @Support
  Condition in(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>... record);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #in(Collection)
   */
  @NotNull
  @Support
  Condition in(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);

  /**
   * Compare this row value expression with a set of row value expressions for equality.
   *
   * <p>Row NOT IN predicates can be emulated in those databases that do not support such predicates
   * natively: <code>(A, B) NOT IN ((1, 2), (3, 4))</code> is equivalent to <code>
   * NOT(((A, B) = (1, 2)) OR ((A, B) = (3, 4)))</code>, which is equivalent to <code>
   * NOT((A = 1 AND B = 2) OR (A = 3 AND B = 4))</code>
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>NOT IN</code> predicates can
   * cause cursor cache contention in some databases that use unique SQL strings as a statement
   * identifier (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use
   * {@link Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>NOT IN</code> predicates on temporary tables
   *   <li><code>NOT IN</code> predicates on unnested array bind variables
   * </ul>
   *
   * @see Rows#toRowList(Function, Function, Function, Function, Function, Function, Function,
   *     Function, Function, Function, Function)
   */
  @NotNull
  @Support
  Condition notIn(Collection<? extends Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> rows);

  /**
   * Compare this row value expression with a set of records for equality.
   *
   * <p>Row NOT IN predicates can be emulated in those databases that do not support such predicates
   * natively: <code>(A, B) NOT IN ((1, 2), (3, 4))</code> is equivalent to <code>
   * NOT(((A, B) = (1, 2)) OR ((A, B) = (3, 4)))</code>, which is equivalent to <code>
   * NOT((A = 1 AND B = 2) OR (A = 3 AND B = 4))</code>
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>NOT IN</code> predicates can
   * cause cursor cache contention in some databases that use unique SQL strings as a statement
   * identifier (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use
   * {@link Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>NOT IN</code> predicates on temporary tables
   *   <li><code>NOT IN</code> predicates on unnested array bind variables
   * </ul>
   */
  @NotNull
  @Support
  Condition notIn(Result<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> result);

  /**
   * Compare this row value expression with a set of row value expressions for equality.
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>NOT IN</code> predicates can
   * cause cursor cache contention in some databases that use unique SQL strings as a statement
   * identifier (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use
   * {@link Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>NOT IN</code> predicates on temporary tables
   *   <li><code>NOT IN</code> predicates on unnested array bind variables
   * </ul>
   *
   * @see #notIn(Collection)
   * @see Rows#toRowArray(Function, Function, Function, Function, Function, Function, Function,
   *     Function, Function, Function, Function)
   */
  @SuppressWarnings("unchecked")
  @NotNull
  @Support
  Condition notIn(Row11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>... rows);

  /**
   * Compare this row value expression with a set of records for non-equality.
   *
   * <p>Note that generating dynamic SQL with arbitrary-length <code>NOT IN</code> predicates can
   * cause cursor cache contention in some databases that use unique SQL strings as a statement
   * identifier (e.g. {@link SQLDialect#ORACLE}). In order to prevent such problems, you could use
   * {@link Settings#isInListPadding()} to produce less distinct SQL strings (see also <a
   * href="https://github.com/jOOQ/jOOQ/issues/5600">[#5600]</a>), or you could avoid <code>IN
   * </code> lists, and replace them with:
   *
   * <ul>
   *   <li><code>NOT IN</code> predicates on temporary tables
   *   <li><code>NOT IN</code> predicates on unnested array bind variables
   * </ul>
   *
   * @see #notIn(Collection)
   */
  @SuppressWarnings("unchecked")
  @NotNull
  @Support
  Condition notIn(Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>... record);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notIn(Collection)
   */
  @NotNull
  @Support
  Condition notIn(Select<? extends Record11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>> select);
}
