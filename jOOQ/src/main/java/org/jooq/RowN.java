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
public interface RowN extends Row, SelectField<Record> {

  // ------------------------------------------------------------------------
  // Generic comparison predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(RowN)
   * @see #notEqual(RowN)
   * @see #lessThan(RowN)
   * @see #lessOrEqual(RowN)
   * @see #greaterThan(RowN)
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, RowN row);

  /**
   * Compare this row value expression with a record using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(Record)
   * @see #notEqual(Record)
   * @see #lessThan(Record)
   * @see #lessOrEqual(Record)
   * @see #greaterThan(Record)
   * @see #greaterOrEqual(Record)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Record record);

  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(RowN)
   * @see #notEqual(RowN)
   * @see #lessThan(RowN)
   * @see #lessOrEqual(RowN)
   * @see #greaterThan(RowN)
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Object... values);

  /**
   * Compare this row value expression with another row value expression using a dynamic comparator.
   *
   * <p>See the explicit comparison methods for details. Note, not all {@link Comparator} types are
   * supported
   *
   * @see #equal(RowN)
   * @see #notEqual(RowN)
   * @see #lessThan(RowN)
   * @see #lessOrEqual(RowN)
   * @see #greaterThan(RowN)
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Field<?>... values);

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
  Condition compare(Comparator comparator, Select<? extends Record> select);

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
  Condition compare(Comparator comparator, QuantifiedSelect<? extends Record> select);

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
  Condition equal(RowN row);

  /**
   * Compare this row value expression with a record for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition equal(Record record);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition equal(Object... values);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition equal(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition equal(Select<? extends Record> select);

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
  Condition equal(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition eq(RowN row);

  /**
   * Compare this row value expression with a record for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition eq(Record record);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition eq(Object... values);

  /**
   * Compare this row value expression with another row value expression for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition eq(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #equal(RowN)
   */
  @NotNull
  @Support
  Condition eq(Select<? extends Record> select);

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
  Condition eq(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * <p>Row non-equality comparison predicates can be emulated in those databases that do not
   * support such predicates natively: <code>(A, B) &lt;&gt; (1, 2)</code> is equivalent to <code>
   * NOT(A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition notEqual(RowN row);

  /**
   * Compare this row value expression with a record for non-equality
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition notEqual(Record record);

  /**
   * Compare this row value expression with another row value expression for. non-equality
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition notEqual(Object... values);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition notEqual(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition notEqual(Select<? extends Record> select);

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
  Condition notEqual(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition ne(RowN row);

  /**
   * Compare this row value expression with a record for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition ne(Record record);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition ne(Object... values);

  /**
   * Compare this row value expression with another row value expression for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition ne(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notEqual(RowN)
   */
  @NotNull
  @Support
  Condition ne(Select<? extends Record> select);

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
  Condition ne(QuantifiedSelect<? extends Record> select);

  // ------------------------------------------------------------------------
  // [NOT] DISTINCT predicates
  // ------------------------------------------------------------------------
  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(RowN row);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Record record);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Object... values);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Field<?>... values);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isDistinctFrom(Select<? extends Record> select);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(RowN row);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Record record);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Object... values);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Field<?>... values);

  /** Compare this row value expression with another row value expression for distinctness. */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Select<? extends Record> select);

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
  Condition lessThan(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lessThan(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lessThan(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lessThan(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lessThan(Select<? extends Record> select);

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
  Condition lessThan(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lt(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lt(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lt(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lt(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessThan(RowN)
   */
  @NotNull
  @Support
  Condition lt(Select<? extends Record> select);

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
  Condition lt(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B) &lt;= (1, 2)</code> is equivalent to <code>
   * A &lt; 1 OR (A = 1 AND B &lt; 2) OR (A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition lessOrEqual(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition lessOrEqual(Select<? extends Record> select);

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
  Condition lessOrEqual(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition le(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition le(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition le(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition le(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #lessOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition le(Select<? extends Record> select);

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
  Condition le(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B, C) &gt; (1, 2, 3)</code> is equivalent to <code>
   * A &gt; 1 OR (A = 1 AND B &gt; 2) OR (A = 1 AND B = 2 AND C &gt; 3)</code>
   */
  @NotNull
  @Support
  Condition greaterThan(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition greaterThan(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition greaterThan(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition greaterThan(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition greaterThan(Select<? extends Record> select);

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
  Condition greaterThan(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition gt(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition gt(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition gt(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition gt(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterThan(RowN)
   */
  @NotNull
  @Support
  Condition gt(Select<? extends Record> select);

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
  Condition gt(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * <p>Row order comparison predicates can be emulated in those databases that do not support such
   * predicates natively: <code>(A, B) &gt;= (1, 2)</code> is equivalent to <code>
   * A &gt; 1 OR (A = 1 AND B &gt; 2) OR (A = 1 AND B = 2)</code>
   */
  @NotNull
  @Support
  Condition greaterOrEqual(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition greaterOrEqual(Select<? extends Record> select);

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
  Condition greaterOrEqual(QuantifiedSelect<? extends Record> select);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition ge(RowN row);

  /**
   * Compare this row value expression with a record for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition ge(Record record);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition ge(Object... values);

  /**
   * Compare this row value expression with another row value expression for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition ge(Field<?>... values);

  /**
   * Compare this row value expression with a subselect for order.
   *
   * @see #greaterOrEqual(RowN)
   */
  @NotNull
  @Support
  Condition ge(Select<? extends Record> select);

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
  Condition ge(QuantifiedSelect<? extends Record> select);

  // ------------------------------------------------------------------------
  // [NOT] BETWEEN predicates
  // ------------------------------------------------------------------------
  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN between(Object... minValues);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN between(Field<?>... minValues);

  /**
   * Check if this row value expression is within a range of two other row value expressions.
   *
   * @see #between(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN between(RowN minValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * @see #between(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN between(Record minValue);

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
  Condition between(RowN minValue, RowN maxValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * <p>This is the same as calling <code>between(minValue).and(maxValue)</code>
   *
   * @see #between(RowN, RowN)
   */
  @NotNull
  @Support
  Condition between(Record minValue, Record maxValue);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN betweenSymmetric(Object... minValues);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN betweenSymmetric(Field<?>... minValues);

  /**
   * Check if this row value expression is within a symmetric range of two other row value
   * expressions.
   *
   * @see #betweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN betweenSymmetric(RowN minValue);

  /**
   * Check if this row value expression is within a symmetric range of two records.
   *
   * @see #betweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN betweenSymmetric(Record minValue);

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
  Condition betweenSymmetric(RowN minValue, RowN maxValue);

  /**
   * Check if this row value expression is within a symmetric range of two records.
   *
   * <p>This is the same as calling <code>betweenSymmetric(minValue).and(maxValue)</code>
   *
   * @see #betweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  Condition betweenSymmetric(Record minValue, Record maxValue);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #between(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetween(Object... minValues);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #notBetween(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetween(Field<?>... minValues);

  /**
   * Check if this row value expression is not within a range of two other row value expressions.
   *
   * @see #notBetween(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetween(RowN minValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * @see #notBetween(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetween(Record minValue);

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
  Condition notBetween(RowN minValue, RowN maxValue);

  /**
   * Check if this row value expression is within a range of two records.
   *
   * <p>This is the same as calling <code>notBetween(minValue).and(maxValue)</code>
   *
   * @see #notBetween(RowN, RowN)
   */
  @NotNull
  @Support
  Condition notBetween(Record minValue, Record maxValue);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetweenSymmetric(Object... minValues);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetweenSymmetric(Field<?>... minValues);

  /**
   * Check if this row value expression is not within a symmetric range of two other row value
   * expressions.
   *
   * @see #notBetweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetweenSymmetric(RowN minValue);

  /**
   * Check if this row value expression is not within a symmetric range of two records.
   *
   * @see #notBetweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  BetweenAndStepN notBetweenSymmetric(Record minValue);

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
  Condition notBetweenSymmetric(RowN minValue, RowN maxValue);

  /**
   * Check if this row value expression is not within a symmetric range of two records.
   *
   * <p>This is the same as calling <code>notBetweenSymmetric(minValue).and(maxValue)</code>
   *
   * @see #notBetweenSymmetric(RowN, RowN)
   */
  @NotNull
  @Support
  Condition notBetweenSymmetric(Record minValue, Record maxValue);

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
   * @see Rows#toRowList(Function, Function)
   */
  @NotNull
  @Support
  Condition in(Collection<? extends RowN> rows);

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
  Condition in(Result<? extends Record> result);

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
   * @see Rows#toRowArray(Function, Function)
   */
  @NotNull
  @Support
  Condition in(RowN... rows);

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
  @NotNull
  @Support
  Condition in(Record... record);

  /**
   * Compare this row value expression with a subselect for equality.
   *
   * @see #in(Collection)
   */
  @NotNull
  @Support
  Condition in(Select<? extends Record> select);

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
   * @see Rows#toRowList(Function, Function)
   */
  @NotNull
  @Support
  Condition notIn(Collection<? extends RowN> rows);

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
  Condition notIn(Result<? extends Record> result);

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
   * @see Rows#toRowArray(Function, Function)
   */
  @NotNull
  @Support
  Condition notIn(RowN... rows);

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
  @NotNull
  @Support
  Condition notIn(Record... record);

  /**
   * Compare this row value expression with a subselect for non-equality.
   *
   * @see #notIn(Collection)
   */
  @NotNull
  @Support
  Condition notIn(Select<? extends Record> select);
}
