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
import java.util.function.Function;
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
public interface Row1<T1> extends Row, SelectField<Record1<T1>> {

  // ------------------------------------------------------------------------
  // Mapping convenience methods
  // ------------------------------------------------------------------------
  /**
   * A convenience method to define a local {@link Record1} to custom type {@link RecordMapper} that
   * can be used when projecting {@link Row} types in <code>SELECT</code> or <code>RETURNING</code>
   * clauses.
   *
   * <p>EXPERIMENTAL. Unlike {@link #mapping(Class, Function1)}, this method attempts to work
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
  <U> SelectField<U> mapping(Function1<? super T1, ? extends U> function);

  /**
   * A convenience method to define a local {@link Record1} to custom type {@link RecordMapper} that
   * can be used when projecting {@link Row} types in <code>SELECT</code> or <code>RETURNING</code>
   * clauses.
   */
  @NotNull
  <U> SelectField<U> mapping(Class<U> uType, Function1<? super T1, ? extends U> function);

  // ------------------------------------------------------------------------
  // Field accessors
  // ------------------------------------------------------------------------
  /** Get the first field. */
  @NotNull
  Field<T1> field1();

  // ------------------------------------------------------------------------
  // Generic comparison predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Row1)
   * @see #notEqual(Row1)
   * @see #lessThan(Row1)
   * @see #lessOrEqual(Row1)
   * @see #greaterThan(Row1)
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Row1<T1> row);

  /**
   * Compare this row value expression with a record using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Record1)
   * @see #notEqual(Record1)
   * @see #lessThan(Record1)
   * @see #lessOrEqual(Record1)
   * @see #greaterThan(Record1)
   * @see #greaterOrEqual(Record1)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Row1)
   * @see #notEqual(Row1)
   * @see #lessThan(Row1)
   * @see #lessOrEqual(Row1)
   * @see #greaterThan(Row1)
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, T1 t1);

  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Row1)
   * @see #notEqual(Row1)
   * @see #lessThan(Row1)
   * @see #lessOrEqual(Row1)
   * @see #greaterThan(Row1)
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Field<T1> t1);

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
  Condition compare(Comparator comparator, Select<? extends Record1<T1>> select);

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
  Condition compare(Comparator comparator, QuantifiedSelect<? extends Record1<T1>> select);

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
  Condition equal(Row1<T1> row);

  /**
   * Compare this row value expression with a record for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition equal(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition equal(T1 t1);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition equal(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition equal(Select<? extends Record1<T1>> select);

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
  Condition equal(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition eq(Row1<T1> row);

  /**
   * Compare this row value expression with a record for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition eq(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition eq(T1 t1);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition eq(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #equal(Row1)
   */
  @NotNull
  @Support
  Condition eq(Select<? extends Record1<T1>> select);

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
  Condition eq(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * <p>Row non-equality comparison predicates can be emulated in those databases that do not
   * support such predicates natively: <code>(A, B) &lt;&gt; (1, 2)</code> is equivalent to <code>
   * NOT(A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition notEqual(Row1<T1> row);

  /**
   * Compare this row value expression with a record for non-equality
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition notEqual(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for. non-equality
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition notEqual(T1 t1);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition notEqual(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition notEqual(Select<? extends Record1<T1>> select);

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
  Condition notEqual(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition ne(Row1<T1> row);

  /**
   * Compare this row value expression with a record for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition ne(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition ne(T1 t1);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition ne(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notEqual(Row1)
   */
  @NotNull
  @Support
  Condition ne(Select<? extends Record1<T1>> select);

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
  Condition ne(QuantifiedSelect<? extends Record1<T1>> select);

  // ------------------------------------------------------------------------
  // [NOT] DISTINCT predicates
  // ------------------------------------------------------------------------
  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Row1<T1> row);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Record1<T1> record);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(T1 t1);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Field<T1> t1);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Select<? extends Record1<T1>> select);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Row1<T1> row);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Record1<T1> record);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(T1 t1);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Field<T1> t1);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Select<? extends Record1<T1>> select);

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
  Condition lessThan(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lessThan(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lessThan(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lessThan(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lessThan(Select<? extends Record1<T1>> select);

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
  Condition lessThan(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lt(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lt(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lt(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lt(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessThan(Row1)
   */
  @NotNull
  @Support
  Condition lt(Select<? extends Record1<T1>> select);

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
  Condition lt(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B) &lt;= (1, 2)</code> is equivalent to <code>
   * A &lt; 1 OR (A = 1 AND B &lt; 2) OR (A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition lessOrEqual(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition lessOrEqual(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Select<? extends Record1<T1>> select);

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
  Condition lessOrEqual(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition le(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition le(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition le(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition le(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition le(Select<? extends Record1<T1>> select);

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
  Condition le(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B, C) &gt; (1, 2, 3)</code> is equivalent to <code>
   * A &gt; 1 OR (A = 1 AND B &gt; 2) OR (A = 1 AND B = 2 AND C &gt; 3)</code>
   */
  @NotNull
  @Support
  Condition greaterThan(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition greaterThan(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition greaterThan(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition greaterThan(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition greaterThan(Select<? extends Record1<T1>> select);

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
  Condition greaterThan(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition gt(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition gt(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition gt(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition gt(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterThan(Row1)
   */
  @NotNull
  @Support
  Condition gt(Select<? extends Record1<T1>> select);

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
  Condition gt(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B) &gt;= (1, 2)</code> is equivalent to <code>
   * A &gt; 1 OR (A = 1 AND B &gt; 2) OR (A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Select<? extends Record1<T1>> select);

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
  Condition greaterOrEqual(QuantifiedSelect<? extends Record1<T1>> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition ge(Row1<T1> row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition ge(Record1<T1> record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition ge(T1 t1);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition ge(Field<T1> t1);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterOrEqual(Row1)
   */
  @NotNull
  @Support
  Condition ge(Select<? extends Record1<T1>> select);

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
  Condition ge(QuantifiedSelect<? extends Record1<T1>> select);

  // ------------------------------------------------------------------------
  // [NOT] BETWEEN predicates
  // ------------------------------------------------------------------------
  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> between(T1 minValue1);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> between(Field<T1> minValue1);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> between(Row1<T1> minValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * @see #between(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> between(Record1<T1> minValue);

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
  Condition between(Row1<T1> minValue, Row1<T1> maxValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * <p>This is the same as calling <code>between(minValue).and(maxValue)</code>
   *
   * @see #between(Row1, Row1)
   */
  @NotNull
  @Support
  Condition between(Record1<T1> minValue, Record1<T1> maxValue);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> betweenSymmetric(T1 minValue1);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> betweenSymmetric(Field<T1> minValue1);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> betweenSymmetric(Row1<T1> minValue);

  /**
   * Check if this row value expression is within a symmetric range of two records.
   *
   * @see #betweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> betweenSymmetric(Record1<T1> minValue);

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
  Condition betweenSymmetric(Row1<T1> minValue, Row1<T1> maxValue);

  /**
   * Check if this row value expression is within a symmetric range of two records.
   *
   * <p>This is the same as calling <code>betweenSymmetric(minValue).and(maxValue)</code>
   *
   * @see #betweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  Condition betweenSymmetric(Record1<T1> minValue, Record1<T1> maxValue);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #between(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetween(T1 minValue1);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #notBetween(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetween(Field<T1> minValue1);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #notBetween(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetween(Row1<T1> minValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * @see #notBetween(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetween(Record1<T1> minValue);

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
  Condition notBetween(Row1<T1> minValue, Row1<T1> maxValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * <p>This is the same as calling <code>notBetween(minValue).and(maxValue)</code>
   *
   * @see #notBetween(Row1, Row1)
   */
  @NotNull
  @Support
  Condition notBetween(Record1<T1> minValue, Record1<T1> maxValue);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetweenSymmetric(T1 minValue1);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetweenSymmetric(Field<T1> minValue1);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetweenSymmetric(Row1<T1> minValue);

  /**
   * Check if this row value expression is not within a symmetric range of two records.
   *
   * @see #notBetweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  BetweenAndStep1<T1> notBetweenSymmetric(Record1<T1> minValue);

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
  Condition notBetweenSymmetric(Row1<T1> minValue, Row1<T1> maxValue);

  /**
   * Check if this row value expression is not within a symmetric range of two records.
   *
   * <p>This is the same as calling <code>notBetweenSymmetric(minValue).and(maxValue)</code>
   *
   * @see #notBetweenSymmetric(Row1, Row1)
   */
  @NotNull
  @Support
  Condition notBetweenSymmetric(Record1<T1> minValue, Record1<T1> maxValue);

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
   * @see Rows#toRowList(Function)
   */
  @NotNull
  @Support
  Condition in(Collection<? extends Row1<T1>> rows);

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
  Condition in(Result<? extends Record1<T1>> result);

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
   * @see Rows#toRowArray(Function)
   */
  @SuppressWarnings("unchecked")
  @NotNull
  @Support
  Condition in(Row1<T1>... rows);

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
  Condition in(Record1<T1>... record);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #in(Collection)
   */
  @NotNull
  @Support
  Condition in(Select<? extends Record1<T1>> select);

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
   * @see Rows#toRowList(Function)
   */
  @NotNull
  @Support
  Condition notIn(Collection<? extends Row1<T1>> rows);

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
  Condition notIn(Result<? extends Record1<T1>> result);

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
   * @see Rows#toRowArray(Function)
   */
  @SuppressWarnings("unchecked")
  @NotNull
  @Support
  Condition notIn(Row1<T1>... rows);

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
  Condition notIn(Record1<T1>... record);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notIn(Collection)
   */
  @NotNull
  @Support
  Condition notIn(Select<? extends Record1<T1>> select);
}
