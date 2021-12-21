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

// ...
// ...
// ...
// ...
// ...
// ...
import static org.jooq.SQLDialect.CUBRID;
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.FIREBIRD;
import static org.jooq.SQLDialect.H2;
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.IGNITE;
import static org.jooq.SQLDialect.MARIADB;
import static org.jooq.SQLDialect.MYSQL;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLITE;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.jooq.types.Interval;

/**
 * A column expression.
 * <p>
 * Column expressions or fields can be used in a variety of SQL statements and
 * clauses, including (non-exhaustive list):
 * <ul>
 * <li><code>SELECT</code> clause, e.g. through {@link DSL#select(SelectField)}
 * (every {@link Field} is a subtype of {@link SelectField})</li>
 * <li><code>WHERE</code> clause, e.g. through
 * {@link SelectWhereStep#where(Field)} (<code>Field&lt;Boolean&gt;</code> can
 * behave like a {@link Condition}, regardless if your RDBMS supports the
 * <code>BOOLEAN</code> type)</li>
 * <li><code>GROUP BY</code> clause, e.g. through
 * {@link SelectGroupByStep#groupBy(GroupField...)} (every {@link Field} is a
 * subtype of {@link GroupField})</li>
 * <li><code>HAVING</code> clause, e.g. through
 * {@link SelectHavingStep#having(Field)}<code></li>
 * <li><code>ORDER BY</code> clause, e.g. through
 * {@link SelectOrderByStep#orderBy(OrderField)} (every {@link Field} is a
 * subtype of {@link OrderField})</li>
 * <li>When creating a {@link Condition}, e.g. through
 * {@link Field#eq(Field)}</li>
 * <li>As a function argument, e.g. through {@link DSL#abs(Field)}</li>
 * <li>Many more...</li>
 * </ul>
 * <p>
 * <strong>Example:</strong>
 * <p>
 * <code><pre>
 * // Assuming import static org.jooq.impl.DSL.*;
 *
 * using(configuration)
 *    .select(ACTOR.LAST_NAME)  // Field reference
 *    .from(ACTOR)
 *    .groupBy(ACTOR.LAST_NAME) // Field reference
 *    .orderBy(ACTOR.LAST_NAME) // Field reference
 *    .fetch();
 * </pre></code>
 * <p>
 * Instances can be created using a variety of ways, including:
 * <ul>
 * <li>{@link DSL#field(String)} and overloads for plain SQL field
 * expression.</li>
 * <li>{@link DSL#field(Name)} and overloads for field identifier
 * references.</li>
 * <li>{@link DSL#field(Condition)} for predicates as fields.</li>
 * <li>{@link DSL#field(Select)} for correlated subqueries.</li>
 * <li>{@link TableField} referenced from generated tables</li>
 * <li>{@link DSL#val(Object)} and overloads to create bind variables
 * explicitly</li>
 * <li>{@link DSL#inline(Object)} and overloads to create inline values
 * (constants, literals) explicitly</li>
 * </ul>
 *
 * @param <T> The field type
 * @author Lukas Eder
 */
public interface Field<T>
    extends SelectField<T>, GroupField, OrderField<T>, FieldOrRow, FieldOrConstraint {

  // ------------------------------------------------------------------------
  // API
  // ------------------------------------------------------------------------
  /**
   * The name of the field.
   *
   * <p>The name is any of these:
   *
   * <ul>
   *   <li>The formal name of the field, if it is a <i>physical table/view field</i>
   *   <li>The alias of an <i>aliased field</i>
   *   <li>A generated / unspecified value for any other <i>expression</i>
   *   <li>The name of a parameter if it is a named {@link Param}
   * </ul>
   */
  @NotNull
  @Override
  String getName();

  /**
   * The comment given to the field.
   *
   * <p>If this <code>Field</code> is a generated field from your database, it may provide its DDL
   * comment through this method. All other column expressions return the empty string <code>""
   * </code> here, never <code>null</code>.
   */
  @NotNull
  @Override
  String getComment();

  /**
   * Create an alias for this field.
   *
   * <p>Note that the case-sensitivity of the returned field depends on {@link
   * Settings#getRenderQuotedNames()}. By default, field aliases are quoted, and thus case-sensitive
   * in many SQL dialects!
   *
   * <p>This works like {@link #as(String)}, except that field aliases are provided by a function.
   * This is useful, for instance, to prefix all columns with a common prefix (on {@link
   * Table#as(String, Function)}):
   *
   * <p><code><pre>
   * MY_TABLE.as("t1", f -&gt; "prefix_" + f.getName());
   * </pre></code>
   *
   * <p>And then to use the same function also for individual fields:
   *
   * <p><code><pre>
   * MY_TABLE.MY_COLUMN.as(f -&gt; "prefix_" + f.getName());
   * </pre></code>
   *
   * @deprecated - 3.14.0 - [#10156] - These methods will be removed without replacement from a
   *     future jOOQ. They offer convenience that is unidiomatic for jOOQ's DSL, without offering
   *     functionality that would not be possible otherwise - yet they add complexity in jOOQ's
   *     internals.
   */
  @NotNull
  @Support
  Field<T> as(Function<? super Field<T>, ? extends String> aliasFunction);

  /**
   * {@inheritDoc}
   *
   * <p><strong>Watch out! This is {@link Object#equals(Object)}, not a jOOQ DSL feature!</strong>
   */
  @Override
  boolean equals(Object other);

  // ------------------------------------------------------------------------
  // Type casts
  // ------------------------------------------------------------------------
  /**
   * Apply an ad-hoc data type {@link Binding} to this field expression.
   *
   * <p>Rather than attaching data type bindings at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataType(Binding)}, this method allows for
   * creating a derived field expression with an ad-hoc data type binding for single query usage.
   *
   * @param <U> The user type.
   * @param binding The binding to be applied on any operations using this field.
   * @return A derived field expression using a new binding.
   */
  @NotNull
  <U> Field<U> convert(Binding<T, U> binding);

  /**
   * Apply an ad-hoc data type {@link Converter} to this field expression.
   *
   * <p>Rather than attaching data type converters at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataType(Converter)}, this method allows for
   * creating a derived field expression with an ad-hoc data type converter for single query usage.
   *
   * @param <U> The user type.
   * @param converter The converter to be applied on any operations using this field.
   * @return A derived field expression using a new converter.
   */
  @NotNull
  <U> Field<U> convert(Converter<T, U> converter);

  /**
   * Apply an ad-hoc data type {@link Converter} to this field expression.
   *
   * <p>Rather than attaching data type converters at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataType(Class, Function, Function)}, this method
   * allows for creating a derived field expression with an ad-hoc data type converter for single
   * query usage.
   *
   * @param <U> The user type.
   * @param converter The converter to be applied on any operations using this field.
   * @return A derived field expression using a new converter.
   */
  @NotNull
  <U> Field<U> convert(
      Class<U> toType, Function<? super T, ? extends U> from, Function<? super U, ? extends T> to);

  /**
   * Apply an ad-hoc read-only data type {@link Converter} to this field expression.
   *
   * <p>Rather than attaching data type converters at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataTypeFrom(Class, Function)}, this method allows
   * for creating a derived field expression with an ad-hoc data type converter for single query
   * usage.
   *
   * @param <U> The user type.
   * @param converter The read-only converter to be applied on any operations using this field.
   * @return A derived field expression using a new converter.
   */
  @NotNull
  <U> Field<U> convertFrom(Class<U> toType, Function<? super T, ? extends U> from);

  /**
   * Apply an ad-hoc read-only data type {@link Converter} to this field expression.
   *
   * <p>Rather than attaching data type converters at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataTypeFrom(Class, Function)}, this method allows
   * for creating a derived field expression with an ad-hoc data type converter for single query
   * usage.
   *
   * <p>EXPERIMENTAL. Unlike {@link #convertFrom(Class, Function)}, this method attempts to work
   * without an explicit {@link Class} reference for the underlying {@link Converter#toType()}.
   * There may be some edge cases where this doesn't work. Please report any bugs here: <a
   * href="https://github.com/jOOQ/jOOQ/issues/new/choose">https://github.com/jOOQ/jOOQ/issues/new/choose</a>
   *
   * @param <U> The user type.
   * @param converter The read-only converter to be applied on any operations using this field.
   * @return A derived field expression using a new converter.
   */
  @NotNull
  <U> Field<U> convertFrom(Function<? super T, ? extends U> from);

  /**
   * Apply an ad-hoc write-only data type {@link Converter} to this field expression.
   *
   * <p>Rather than attaching data type converters at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataTypeTo(Class, Function)}, this method allows
   * for creating a derived field expression with an ad-hoc data type converter for single query
   * usage.
   *
   * @param <U> The user type.
   * @param converter The write-only converter to be applied on any operations using this field.
   * @return A derived field expression using a new converter.
   */
  @NotNull
  <U> Field<U> convertTo(Class<U> toType, Function<? super U, ? extends T> to);

  /**
   * Apply an ad-hoc write-only data type {@link Converter} to this field expression.
   *
   * <p>Rather than attaching data type converters at code generation time, or creating cumbersome
   * expressions using {@link DataType#asConvertedDataTypeTo(Class, Function)}, this method allows
   * for creating a derived field expression with an ad-hoc data type converter for single query
   * usage.
   *
   * <p>EXPERIMENTAL. Unlike {@link #convertTo(Class, Function)}, this method attempts to work
   * without an explicit {@link Class} reference for the underlying {@link Converter#toType()}.
   * There may be some edge cases where this doesn't work. Please report any bugs here: <a
   * href="https://github.com/jOOQ/jOOQ/issues/new/choose">https://github.com/jOOQ/jOOQ/issues/new/choose</a>
   *
   * @param <U> The user type.
   * @param converter The write-only converter to be applied on any operations using this field.
   * @return A derived field expression using a new converter.
   */
  @NotNull
  <U> Field<U> convertTo(Function<? super U, ? extends T> to);

  /**
   * Cast this field to the type of another field.
   *
   * <p>This results in the same as casting this field to {@link DataType#getCastTypeName()}
   *
   * @param <Z> The generic type of the cast field
   * @param field The field whose type is used for the cast
   * @return The cast field
   * @see #cast(DataType)
   */
  @NotNull
  @Support
  <Z> Field<Z> cast(Field<Z> field);

  /**
   * Cast this field to a dialect-specific data type.
   *
   * @param <Z> The generic type of the cast field
   * @param type The data type that is used for the cast
   * @return The cast field
   */
  @NotNull
  @Support
  <Z> Field<Z> cast(DataType<Z> type);

  /**
   * Cast this field to another type.
   *
   * <p>The actual cast may not be accurate as the {@link DataType} has to be "guessed" from the
   * jOOQ-configured data types. Use {@link #cast(DataType)} for more accurate casts.
   *
   * @param <Z> The generic type of the cast field
   * @param type The type that is used for the cast
   * @return The cast field
   * @see #cast(DataType)
   */
  @NotNull
  @Support
  <Z> Field<Z> cast(Class<Z> type);

  // ------------------------------------------------------------------------
  // Type coercion
  // ------------------------------------------------------------------------
  /**
   * Coerce this field to the type of another field.
   *
   * <p>Unlike with casting, coercing doesn't affect the way the database sees a <code>Field</code>
   * 's type. This is how coercing affects your SQL:
   *
   * <h3>Bind values</h3>
   *
   * <code><pre>
   * // This binds an int value to a JDBC PreparedStatement
   * DSL.val(1).coerce(String.class);
   *
   * // This binds an int value to a JDBC PreparedStatement
   * // and casts it to VARCHAR in SQL
   * DSL.val(1).cast(String.class);
   * </pre></code>
   *
   * <h3>Other Field types</h3>
   *
   * <code><pre>
   * // This fetches a String value for the BOOK.ID field from JDBC
   * BOOK.ID.coerce(String.class);
   *
   * // This fetches a String value for the BOOK.ID field from JDBC
   * // after casting it to VARCHAR in the database
   * BOOK.ID.cast(String.class);
   * </pre></code>
   *
   * @param <Z> The generic type of the coerced field
   * @param field The field whose type is used for the coercion
   * @return The coerced field
   * @see #coerce(DataType)
   * @see #cast(Field)
   */
  @NotNull
  @Support
  <Z> Field<Z> coerce(Field<Z> field);

  /**
   * Coerce this field to a dialect-specific data type.
   *
   * <p>Unlike with casting, coercing doesn't affect the way the database sees a <code>Field</code>
   * 's type. This is how coercing affects your SQL:
   *
   * <h3>Bind values</h3>
   *
   * <code><pre>
   * // This binds an int value to a JDBC PreparedStatement
   * DSL.val(1).coerce(String.class);
   *
   * // This binds an int value to a JDBC PreparedStatement
   * // and casts it to VARCHAR in SQL
   * DSL.val(1).cast(String.class);
   * </pre></code>
   *
   * <h3>Other Field types</h3>
   *
   * <code><pre>
   * // This fetches a String value for the BOOK.ID field from JDBC
   * BOOK.ID.coerce(String.class);
   *
   * // This fetches a String value for the BOOK.ID field from JDBC
   * // after casting it to VARCHAR in the database
   * BOOK.ID.cast(String.class);
   * </pre></code>
   *
   * @param <Z> The generic type of the coerced field
   * @param type The data type that is used for the coercion
   * @return The coerced field
   * @see #cast(DataType)
   */
  @NotNull
  @Support
  <Z> Field<Z> coerce(DataType<Z> type);

  /**
   * Coerce this field to another type.
   *
   * <p>Unlike with casting, coercing doesn't affect the way the database sees a <code>Field</code>
   * 's type. This is how coercing affects your SQL:
   *
   * <h3>Bind values</h3>
   *
   * <code><pre>
   * // This binds an int value to a JDBC PreparedStatement
   * DSL.val(1).coerce(String.class);
   *
   * // This binds an int value to a JDBC PreparedStatement
   * // and casts it to VARCHAR in SQL
   * DSL.val(1).cast(String.class);
   * </pre></code>
   *
   * <h3>Other Field types</h3>
   *
   * <code><pre>
   * // This fetches a String value for the BOOK.ID field from JDBC
   * BOOK.ID.coerce(String.class);
   *
   * // This fetches a String value for the BOOK.ID field from JDBC
   * // after casting it to VARCHAR in the database
   * BOOK.ID.cast(String.class);
   * </pre></code>
   *
   * @param <Z> The generic type of the coerced field
   * @param type The type that is used for the coercion
   * @return The coerced field
   * @see #coerce(DataType)
   * @see #cast(Class)
   */
  @NotNull
  @Support
  <Z> Field<Z> coerce(Class<Z> type);

  // ------------------------------------------------------------------------
  // Conversion of field into a sort field
  // ------------------------------------------------------------------------
  /**
   * Create an ascending sort field from this field.
   *
   * <p>This is the same as calling {@link #sort(SortOrder)} with {@link SortOrder#ASC}
   *
   * @return This field as an ascending sort field
   */
  @NotNull
  @Support
  SortField<T> asc();

  /**
   * Create a descending sort field from this field.
   *
   * <p>This is the same as calling {@link #sort(SortOrder)} with {@link SortOrder#DESC}
   *
   * @return This field as a descending sort field
   */
  @NotNull
  @Support
  SortField<T> desc();

  /**
   * Create a default sorted (implicit <code>ASC</code>) from this field.
   *
   * <p>This is the same as calling {@link #sort(SortOrder)} with {@link SortOrder#DEFAULT}
   *
   * @return This field as a default sorted sort field
   */
  @NotNull
  @Support
  SortField<T> sortDefault();

  /**
   * Create an ascending/descending sort field from this field.
   *
   * @param order The sort order
   * @return This field as an ascending/descending sort field.
   */
  @NotNull
  @Support
  SortField<T> sort(SortOrder order);

  /**
   * Create an indirected sort field.
   *
   * <p>Create a sort field of the form <code><pre>
   * CASE [this] WHEN [sortList.get(0)] THEN 0
   *             WHEN [sortList.get(1)] THEN 1
   *             ...
   *             WHEN [sortList.get(n)] THEN n
   *                                    ELSE null
   * END ASC
   * </pre></code>
   *
   * <p>Note: You can use this in combination with {@link SortField#nullsFirst()} or {@link
   * SortField#nullsLast()} to specify whether the default should have highest or lowest priority.
   *
   * @param sortList The list containing sort value preferences
   * @return The sort field
   */
  @NotNull
  @Support
  SortField<Integer> sortAsc(Collection<T> sortList);

  /**
   * Create an indirected sort field.
   *
   * <p>Create a sort field of the form <code><pre>
   * CASE [this] WHEN [sortList[0]] THEN 0
   *             WHEN [sortList[1]] THEN 1
   *             ...
   *             WHEN [sortList[n]] THEN n
   *                                ELSE null
   * END ASC
   * </pre></code>
   *
   * <p>Note: You can use this in combination with {@link SortField#nullsFirst()} or {@link
   * SortField#nullsLast()} to specify whether the default should have highest or lowest priority.
   *
   * @param sortList The list containing sort value preferences
   * @return The sort field
   */
  @NotNull
  @Support
  SortField<Integer> sortAsc(T... sortList);

  /**
   * Create an indirected sort field.
   *
   * <p>Create a sort field of the form <code><pre>
   * CASE [this] WHEN [sortList.get(0)] THEN 0
   *             WHEN [sortList.get(1)] THEN 1
   *             ...
   *             WHEN [sortList.get(n)] THEN n
   *                                    ELSE null
   * END DESC
   * </pre></code>
   *
   * <p>Note: You can use this in combination with {@link SortField#nullsFirst()} or {@link
   * SortField#nullsLast()} to specify whether the default should have highest or lowest priority.
   *
   * @param sortList The list containing sort value preferences
   * @return The sort field
   */
  @NotNull
  @Support
  SortField<Integer> sortDesc(Collection<T> sortList);

  /**
   * Create an indirected sort field.
   *
   * <p>Create a sort field of the form <code><pre>
   * CASE [this] WHEN [sortList[0]] THEN 0
   *             WHEN [sortList[1]] THEN 1
   *             ...
   *             WHEN [sortList[n]] THEN n
   *                                    ELSE null
   * END DESC
   * </pre></code>
   *
   * <p>Note: You can use this in combination with {@link SortField#nullsFirst()} or {@link
   * SortField#nullsLast()} to specify whether the default should have highest or lowest priority.
   *
   * @param sortList The list containing sort value preferences
   * @return The sort field
   */
  @NotNull
  @Support
  SortField<Integer> sortDesc(T... sortList);

  /**
   * Create an indirected sort field.
   *
   * <p>Create a sort field of the form (in pseudo code)<code><pre>
   * CASE [this] WHEN [sortMap.key(0)] THEN sortMap.value(0)
   *             WHEN [sortMap.key(1)] THEN sortMap.value(1)
   *             ...
   *             WHEN [sortMap.key(n)] THEN sortMap.value(n)
   *                                   ELSE null
   * END DESC
   * </pre></code>
   *
   * <p>Note: You can use this in combination with {@link SortField#nullsFirst()} or {@link
   * SortField#nullsLast()} to specify whether the default should have highest or lowest priority.
   *
   * @param sortMap The list containing sort value preferences
   * @return The sort field
   */
  @NotNull
  @Support
  <Z> SortField<Z> sort(Map<T, Z> sortMap);

  // ------------------------------------------------------------------------
  // Arithmetic operations
  // ------------------------------------------------------------------------
  /**
   * Negate this field to get its negative value.
   *
   * <p>This renders the same on all dialects: <code><pre>-[this]</pre></code>
   */
  @NotNull
  @Support
  Field<T> neg();

  /**
   * Negate this field to get its negative value.
   *
   * <p>This is an alias for {@link #neg()}, which can be recognised by the Kotlin language for
   * operator overloading.
   */
  @NotNull
  @Support
  Field<T> unaryMinus();

  /**
   * Get this field as its positive value (no effect on SQL).
   *
   * <p>This can be recognised by the Kotlin language for operator overloading.
   */
  @NotNull
  @Support
  Field<T> unaryPlus();

  /**
   * An arithmetic expression adding this to value.
   *
   * @see #add(Field)
   */
  @NotNull
  @Support
  Field<T> add(Number value);

  /**
   * An arithmetic expression to add value to this.
   *
   * <p>The behaviour of this operation is as follows:
   *
   * <table border="1">
   * <tr>
   * <th>Operand 1</th>
   * <th>Operand 2</th>
   * <th>Result Type</th>
   * </tr>
   * <tr>
   * <td>Numeric</td>
   * <td>Numeric</td>
   * <td>Numeric</td>
   * </tr>
   * <tr>
   * <td>Date / Time</td>
   * <td>Numeric</td>
   * <td>Date / Time</td>
   * </tr>
   * <tr>
   * <td>Date / Time</td>
   * <td>Interval</td>
   * <td>Date / Time</td>
   * </tr>
   * <tr>
   * <td>Interval</td>
   * <td>Interval</td>
   * <td>Interval</td>
   * </tr>
   * </table>
   */
  @NotNull
  @Support
  Field<T> add(Field<?> value);

  /**
   * An alias for {@link #add(Number)}.
   *
   * @see #add(Number)
   */
  @NotNull
  @Support
  Field<T> plus(Number value);

  /**
   * An alias for {@link #add(Field)}.
   *
   * @see #add(Field)
   */
  @NotNull
  @Support
  Field<T> plus(Field<?> value);

  /**
   * An arithmetic expression subtracting value from this.
   *
   * @see #sub(Field)
   */
  @NotNull
  @Support
  Field<T> sub(Number value);

  /**
   * An arithmetic expression subtracting value from this.
   *
   * <p>
   *
   * <table border="1">
   * <tr>
   * <th>Operand 1</th>
   * <th>Operand 2</th>
   * <th>Result Type</th>
   * </tr>
   * <tr>
   * <td>Numeric</td>
   * <td>Numeric</td>
   * <td>Numeric</td>
   * </tr>
   * <tr>
   * <td>Date / Time</td>
   * <td>Numeric</td>
   * <td>Date / Time</td>
   * </tr>
   * <tr>
   * <td>Date / Time</td>
   * <td>Interval</td>
   * <td>Date / Time</td>
   * </tr>
   * <tr>
   * <td>Interval</td>
   * <td>Interval</td>
   * <td>Interval</td>
   * </tr>
   * </table>
   *
   * <p>In order to subtract one date time field from another, use any of these methods:
   *
   * <ul>
   *   <li>{@link DSL#dateDiff(Field, Field)}
   *   <li>{@link DSL#timestampDiff(Field, Field)}
   * </ul>
   */
  @NotNull
  @Support
  Field<T> sub(Field<?> value);

  /**
   * An alias for {@link #sub(Number)}.
   *
   * @see #sub(Number)
   */
  @NotNull
  @Support
  Field<T> subtract(Number value);

  /**
   * An alias for {@link #sub(Field)}.
   *
   * @see #sub(Field)
   */
  @NotNull
  @Support
  Field<T> subtract(Field<?> value);

  /**
   * An alias for {@link #sub(Number)}.
   *
   * @see #sub(Number)
   */
  @NotNull
  @Support
  Field<T> minus(Number value);

  /**
   * An alias for {@link #sub(Field)}.
   *
   * @see #sub(Field)
   */
  @NotNull
  @Support
  Field<T> minus(Field<?> value);

  /**
   * An arithmetic expression multiplying this with value.
   *
   * <p>
   *
   * <ul>
   *   <li>If this is a numeric field, then the result is a number of the same type as this field.
   *   <li>If this is an <code>INTERVAL</code> field, then the result is also an <code>INTERVAL
   *       </code> field (see {@link Interval})
   * </ul>
   */
  @NotNull
  @Support
  Field<T> mul(Number value);

  /**
   * An arithmetic expression multiplying this with value.
   *
   * <p>
   *
   * <ul>
   *   <li>If this is a numeric field, then the result is a number of the same type as this field.
   *   <li>If this is an <code>INTERVAL</code> field, then the result is also an <code>INTERVAL
   *       </code> field (see {@link Interval})
   * </ul>
   */
  @NotNull
  @Support
  Field<T> mul(Field<? extends Number> value);

  /**
   * An alias for {@link #mul(Number)}.
   *
   * @see #mul(Number)
   */
  @NotNull
  @Support
  Field<T> multiply(Number value);

  /**
   * An alias for {@link #mul(Field)}.
   *
   * @see #mul(Field)
   */
  @NotNull
  @Support
  Field<T> multiply(Field<? extends Number> value);

  /**
   * An alias for {@link #mul(Number)}.
   *
   * @see #mul(Number)
   */
  @NotNull
  @Support
  Field<T> times(Number value);

  /**
   * An alias for {@link #mul(Field)}.
   *
   * @see #mul(Field)
   */
  @NotNull
  @Support
  Field<T> times(Field<? extends Number> value);

  /**
   * An arithmetic expression dividing this by value.
   *
   * <p>
   *
   * <ul>
   *   <li>If this is a numeric field, then the result is a number of the same type as this field.
   *   <li>If this is an <code>INTERVAL</code> field, then the result is also an <code>INTERVAL
   *       </code> field (see {@link Interval})
   * </ul>
   */
  @NotNull
  @Support
  Field<T> div(Number value);

  /**
   * An arithmetic expression dividing this by value.
   *
   * <p>
   *
   * <ul>
   *   <li>If this is a numeric field, then the result is a number of the same type as this field.
   *   <li>If this is an <code>INTERVAL</code> field, then the result is also an <code>INTERVAL
   *       </code> field (see {@link Interval})
   * </ul>
   */
  @NotNull
  @Support
  Field<T> div(Field<? extends Number> value);

  /**
   * An alias for {@link #div(Number)}.
   *
   * @see #div(Number)
   */
  @NotNull
  @Support
  Field<T> divide(Number value);

  /**
   * An alias for {@link #div(Field)}.
   *
   * @see #div(Field)
   */
  @NotNull
  @Support
  Field<T> divide(Field<? extends Number> value);

  /**
   * An arithmetic expression getting the modulo of this divided by value.
   *
   * <p>This renders the modulo operation where available: <code><pre>[this] % [value]</pre></code>
   * ... or the modulo function elsewhere: <code><pre>mod([this], [value])</pre></code>
   */
  @NotNull
  @Support
  Field<T> mod(Number value);

  /**
   * An arithmetic expression getting the modulo of this divided by value.
   *
   * <p>This renders the modulo operation where available: <code><pre>[this] % [value]</pre></code>
   * ... or the modulo function elsewhere: <code><pre>mod([this], [value])</pre></code>
   */
  @NotNull
  @Support
  Field<T> mod(Field<? extends Number> value);

  /**
   * An alias for {@link #mod(Number)}.
   *
   * @see #mod(Number)
   */
  @NotNull
  @Support
  Field<T> modulo(Number value);

  /**
   * An alias for {@link #mod(Field)}.
   *
   * @see #mod(Field)
   */
  @NotNull
  @Support
  Field<T> modulo(Field<? extends Number> value);

  /**
   * An alias for {@link #mod(Number)}.
   *
   * @see #mod(Number)
   */
  @NotNull
  @Support
  Field<T> rem(Number value);

  /**
   * An alias for {@link #mod(Field)}.
   *
   * @see #mod(Field)
   */
  @NotNull
  @Support
  Field<T> rem(Field<? extends Number> value);

  /**
   * An arithmetic expression getting this value raised to the power of <code>exponent</code>.
   *
   * <p>This renders the power operation where available: <code><pre>[this] ^ [value]</pre></code>
   * ... or the power function elsewhere: <code><pre>power([this], [value])</pre></code>
   *
   * @see DSL#power(Field, Number)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> pow(Number exponent);

  /**
   * An arithmetic expression getting this value raised to the power of <code>exponent</code>.
   *
   * <p>This renders the power operation where available: <code><pre>[this] ^ [value]</pre></code>
   * ... or the power function elsewhere: <code><pre>power([this], [value])</pre></code>
   *
   * @see DSL#power(Field, Field)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> pow(Field<? extends Number> exponent);

  /**
   * An alias for {@link #power(Number)}.
   *
   * @see #power(Number)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> power(Number exponent);

  /**
   * An alias for {@link #power(Field)}.
   *
   * @see #power(Field)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> power(Field<? extends Number> exponent);

  // ------------------------------------------------------------------------
  // Bitwise operations
  // ------------------------------------------------------------------------
  /**
   * The bitwise not operator.
   *
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitNot();

  /**
   * The bitwise and operator.
   *
   * @see DSL#bitAnd(Field, Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitAnd(T value);

  /**
   * The bitwise and operator.
   *
   * @see DSL#bitAnd(Field, Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitAnd(Field<T> value);

  /**
   * The bitwise not and operator.
   *
   * @see DSL#bitNand(Field, Field)
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitNand(T value);

  /**
   * The bitwise not and operator.
   *
   * @see DSL#bitNand(Field, Field)
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitNand(Field<T> value);

  /**
   * The bitwise or operator.
   *
   * @see DSL#bitOr(Field, Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitOr(T value);

  /**
   * The bitwise or operator.
   *
   * @see DSL#bitOr(Field, Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitOr(Field<T> value);

  /**
   * The bitwise not or operator.
   *
   * @see DSL#bitNor(Field, Field)
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitNor(T value);

  /**
   * The bitwise not or operator.
   *
   * @see DSL#bitNor(Field, Field)
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitNor(Field<T> value);

  /**
   * The bitwise xor operator.
   *
   * @see DSL#bitXor(Field, Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitXor(T value);

  /**
   * The bitwise xor operator.
   *
   * @see DSL#bitXor(Field, Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitXor(Field<T> value);

  /**
   * The bitwise not xor operator.
   *
   * @see DSL#bitXNor(Field, Field)
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitXNor(T value);

  /**
   * The bitwise not xor operator.
   *
   * @see DSL#bitXNor(Field, Field)
   * @see DSL#bitNot(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> bitXNor(Field<T> value);

  /**
   * The bitwise left shift operator.
   *
   * @see DSL#shl(Field, Field)
   * @see DSL#power(Field, Number)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> shl(Number value);

  /**
   * The bitwise left shift operator.
   *
   * @see DSL#shl(Field, Field)
   * @see DSL#power(Field, Number)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> shl(Field<? extends Number> value);

  /**
   * The bitwise right shift operator.
   *
   * @see DSL#shr(Field, Field)
   * @see DSL#power(Field, Number)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> shr(Number value);

  /**
   * The bitwise right shift operator.
   *
   * @see DSL#shr(Field, Field)
   * @see DSL#power(Field, Number)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<T> shr(Field<? extends Number> value);

  // ------------------------------------------------------------------------
  // XML predicates
  // ------------------------------------------------------------------------
  /** Create a condition to check if this field contains XML data. */
  @NotNull
  @Support({POSTGRES})
  Condition isDocument();

  /** Create a condition to check if this field does not contain XML data. */
  @NotNull
  @Support({POSTGRES})
  Condition isNotDocument();

  // ------------------------------------------------------------------------
  // JSON predicates
  // ------------------------------------------------------------------------
  /** Create a condition to check if this field contains JSON data. */
  @NotNull
  @Support({MYSQL})
  Condition isJson();

  /** Create a condition to check if this field does not contain JSON data. */
  @NotNull
  @Support({MYSQL})
  Condition isNotJson();

  // ------------------------------------------------------------------------
  // NULL predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to check this field against <code>null</code>.
   *
   * <p>SQL: <code>this is null</code>
   */
  @NotNull
  @Support
  Condition isNull();

  /**
   * Create a condition to check this field against <code>null</code>.
   *
   * <p>SQL: <code>this is not null</code>
   */
  @NotNull
  @Support
  Condition isNotNull();

  // ------------------------------------------------------------------------
  // DISTINCT predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to check if this field is <code>DISTINCT</code> from another value.
   *
   * <p>In {@link SQLDialect#MYSQL} and {@link SQLDialect#MARIADB}, this can be emulated through
   * <code><pre>not([this] &lt;=&gt; [value])</pre></code>
   *
   * <p>In {@link SQLDialect#SQLITE}, this can be emulated through <code>
   * <pre>[this] IS NOT [value]</pre></code>
   *
   * <p>In databases that support <code>INTERSECT</code> (see {@link Select#intersect(Select)}, this
   * predicate can be emulated as follows: <code><pre>
   * NOT EXISTS (SELECT [this] INTERSECT SELECT [value])
   * </pre></code>
   *
   * <p>If this is not supported by the underlying database, jOOQ will render this instead: <code>
   * <pre>
   * CASE WHEN [this] IS     NULL AND [value] IS     NULL THEN FALSE
   *      WHEN [this] IS     NULL AND [value] IS NOT NULL THEN TRUE
   *      WHEN [this] IS NOT NULL AND [value] IS     NULL THEN TRUE
   *      WHEN [this] =               [value]             THEN FALSE
   *      ELSE                                                 TRUE
   * END
   * </pre></code> SQL: <code>this is distinct from value</code>
   */
  @NotNull
  @Support
  Condition isDistinctFrom(T value);

  /**
   * Create a condition to check if this field is <code>DISTINCT</code> from another field.
   *
   * <p>In {@link SQLDialect#MYSQL} and {@link SQLDialect#MARIADB}, this can be emulated through
   * <code><pre>not([this] &lt;=&gt; [value])</pre></code>
   *
   * <p>In {@link SQLDialect#SQLITE}, this can be emulated through <code>
   * <pre>[this] IS NOT [value]</pre></code>
   *
   * <p>In databases that support <code>INTERSECT</code> (see {@link Select#intersect(Select)}, this
   * predicate can be emulated as follows: <code><pre>
   * NOT EXISTS (SELECT [this] INTERSECT SELECT [value])
   * </pre></code>
   *
   * <p>If this is not supported by the underlying database, jOOQ will render this instead: <code>
   * <pre>
   * CASE WHEN [this] IS     NULL AND [field] IS     NULL THEN FALSE
   *      WHEN [this] IS     NULL AND [field] IS NOT NULL THEN TRUE
   *      WHEN [this] IS NOT NULL AND [field] IS     NULL THEN TRUE
   *      WHEN [this] =               [field]             THEN FALSE
   *      ELSE                                                 TRUE
   * END
   * </pre></code> SQL: <code>this is distinct from field</code>
   */
  @NotNull
  @Support
  Condition isDistinctFrom(Field<T> field);

  /**
   * Create a condition to check if this field is <code>DISTINCT</code> from another field.
   *
   * <p>In {@link SQLDialect#MYSQL} and {@link SQLDialect#MARIADB}, this can be emulated through
   * <code><pre>not([this] &lt;=&gt; [value])</pre></code>
   *
   * <p>In {@link SQLDialect#SQLITE}, this can be emulated through <code>
   * <pre>[this] IS NOT [value]</pre></code>
   *
   * <p>In databases that support <code>INTERSECT</code> (see {@link Select#intersect(Select)}, this
   * predicate can be emulated as follows: <code><pre>
   * NOT EXISTS (SELECT [this] INTERSECT SELECT [value])
   * </pre></code>
   *
   * <p>If this is not supported by the underlying database, jOOQ will render this instead: <code>
   * <pre>
   * CASE WHEN [this] IS     NULL AND [field] IS     NULL THEN FALSE
   *      WHEN [this] IS     NULL AND [field] IS NOT NULL THEN TRUE
   *      WHEN [this] IS NOT NULL AND [field] IS     NULL THEN TRUE
   *      WHEN [this] =               [field]             THEN FALSE
   *      ELSE                                                 TRUE
   * END
   * </pre></code> SQL: <code>this is distinct from field</code>
   */
  @NotNull
  @Support
  Condition isDistinctFrom(Select<? extends Record1<T>> select);

  /**
   * Create a condition to check if this field is <code>NOT DISTINCT</code> from another value.
   *
   * <p>In {@link SQLDialect#MYSQL} and {@link SQLDialect#MARIADB}, this can be emulated through
   * <code><pre>[this] &lt;=&gt; [value]</pre></code>
   *
   * <p>In {@link SQLDialect#SQLITE}, this can be emulated through <code>
   * <pre>[this] IS [value]</pre></code>
   *
   * <p>In databases that support <code>INTERSECT</code> (see {@link Select#intersect(Select)}, this
   * predicate can be emulated as follows: <code><pre>
   * EXISTS (SELECT [this] INTERSECT SELECT [value])
   * </pre></code>
   *
   * <p>If this is not supported by the underlying database, jOOQ will render this instead: <code>
   * <pre>
   * CASE WHEN [this] IS     NULL AND [value] IS     NULL THEN TRUE
   *      WHEN [this] IS     NULL AND [value] IS NOT NULL THEN FALSE
   *      WHEN [this] IS NOT NULL AND [value] IS     NULL THEN FALSE
   *      WHEN [this] =               [value]             THEN TRUE
   *      ELSE                                                 FALSE
   * END
   * </pre></code> SQL: <code>this is not distinct from value</code>
   */
  @NotNull
  @Support
  Condition isNotDistinctFrom(T value);

  /**
   * Create a condition to check if this field is <code>NOT DISTINCT</code> from another field.
   *
   * <p>In {@link SQLDialect#MYSQL} and {@link SQLDialect#MARIADB}, this can be emulated through
   * <code><pre>[this] &lt;=&gt; [value]</pre></code>
   *
   * <p>In {@link SQLDialect#SQLITE}, this can be emulated through <code>
   * <pre>[this] IS [value]</pre></code>
   *
   * <p>In databases that support <code>INTERSECT</code> (see {@link Select#intersect(Select)}, this
   * predicate can be emulated as follows: <code><pre>
   * EXISTS (SELECT [this] INTERSECT SELECT [value])
   * </pre></code>
   *
   * <p>If this is not supported by the underlying database, jOOQ will render this instead: <code>
   * <pre>
   * CASE WHEN [this] IS     NULL AND [field] IS     NULL THEN TRUE
   *      WHEN [this] IS     NULL AND [field] IS NOT NULL THEN FALSE
   *      WHEN [this] IS NOT NULL AND [field] IS     NULL THEN FALSE
   *      WHEN [this] =               [value]             THEN TRUE
   *      ELSE                                                 FALSE
   * END
   * </pre></code> SQL: <code>this is not distinct from field</code>
   */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Field<T> field);

  /**
   * Create a condition to check if this field is <code>NOT DISTINCT</code> from another field.
   *
   * <p>In {@link SQLDialect#MYSQL} and {@link SQLDialect#MARIADB}, this can be emulated through
   * <code><pre>[this] &lt;=&gt; [value]</pre></code>
   *
   * <p>In {@link SQLDialect#SQLITE}, this can be emulated through <code>
   * <pre>[this] IS [value]</pre></code>
   *
   * <p>In databases that support <code>INTERSECT</code> (see {@link Select#intersect(Select)}, this
   * predicate can be emulated as follows: <code><pre>
   * EXISTS (SELECT [this] INTERSECT SELECT [value])
   * </pre></code>
   *
   * <p>If this is not supported by the underlying database, jOOQ will render this instead: <code>
   * <pre>
   * CASE WHEN [this] IS     NULL AND [field] IS     NULL THEN TRUE
   *      WHEN [this] IS     NULL AND [field] IS NOT NULL THEN FALSE
   *      WHEN [this] IS NOT NULL AND [field] IS     NULL THEN FALSE
   *      WHEN [this] =               [value]             THEN TRUE
   *      ELSE                                                 FALSE
   * END
   * </pre></code> SQL: <code>this is not distinct from field</code>
   */
  @NotNull
  @Support
  Condition isNotDistinctFrom(Select<? extends Record1<T>> select);

  // ------------------------------------------------------------------------
  // LIKE_REGEX predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to regex-pattern-check this field against a pattern.
   *
   * <p>The SQL:2008 standard specifies a <code>&lt;regex like predicate&gt;</code> of the following
   * form: <code><pre>
   * &lt;regex like predicate&gt; ::=
   *   &lt;row value predicand&gt; &lt;regex like predicate part 2&gt;
   *
   * &lt;regex like predicate part 2&gt; ::=
   *  [ NOT ] LIKE_REGEX &lt;XQuery pattern&gt; [ FLAG &lt;XQuery option flag&gt; ]
   * </pre></code>
   *
   * <p>This particular <code>LIKE_REGEX</code> operator comes in several flavours for various
   * databases. jOOQ supports regular expressions as follows:
   *
   * <table border="1">
   * <tr>
   * <th>SQL dialect</th>
   * <th>SQL syntax</th>
   * <th>Pattern syntax</th>
   * <th>Documentation</th>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#ASE}</td>
   * <td>-</td>
   * <td>-</td>
   * <td>-</td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#DB2}</td>
   * <td>-</td>
   * <td>-</td>
   * <td>-</td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#DERBY}</td>
   * <td>-</td>
   * <td>-</td>
   * <td>-</td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#H2}</td>
   * <td><code>[search] REGEXP [pattern]</code></td>
   * <td>Java</td>
   * <td><a href=
   * "http://www.h2database.com/html/grammar.html#condition_right_hand_side"
   * >http
   * ://www.h2database.com/html/grammar.html#condition_right_hand_side</a></td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#HSQLDB}</td>
   * <td><code>REGEXP_MATCHES([search], [pattern])</code></td>
   * <td>Java</td>
   * <td><a
   * href="http://hsqldb.org/doc/guide/builtinfunctions-chapt.html#N13577"
   * >http://hsqldb.org/doc/guide/builtinfunctions-chapt.html#N13577</a></td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#INGRES}</td>
   * <td>-</td>
   * <td>-</td>
   * <td>-</td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#MYSQL}</td>
   * <td><code>[search] REGEXP [pattern]</code></td>
   * <td>POSIX</td>
   * <td><a href=
   * "http://dev.mysql.com/doc/refman/5.6/en/regexp.html">http://dev
   * .mysql.com/doc/refman/5.6/en/regexp.html</a></td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#ORACLE}</td>
   * <td><code>REGEXP_LIKE([search], [pattern])</code></td>
   * <td>POSIX</td>
   * <td><a href=
   * "http://docs.oracle.com/cd/E14072_01/server.112/e10592/conditions007.htm#sthref1994"
   * >http://docs.oracle.com/cd/E14072_01/server.112/e10592/conditions007.htm#
   * sthref1994</a></td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#POSTGRES}</td>
   * <td><code>[search] ~ [pattern]</code></td>
   * <td>POSIX</td>
   * <td><a href=
   * "http://www.postgresql.org/docs/9.1/static/functions-matching.html#FUNCTIONS-POSIX-REGEXP"
   * >http://www.postgresql.org/docs/9.1/static/functions-matching.html#
   * FUNCTIONS-POSIX-REGEXP</a></td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#SQLITE}</td>
   * <td><code>[search] REGEXP [pattern]</code></td>
   * <td>? This module has to be loaded explicitly</td>
   * <td><a href="http://www.sqlite.org/lang_expr.html">http://www.sqlite.org/
   * lang_expr.html</a></td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#SQLSERVER}</td>
   * <td>-</td>
   * <td>-</td>
   * <td>-</td>
   * </tr>
   * <tr>
   * <td>{@link SQLDialect#SYBASE}</td>
   * <td><code>[search] REGEXP [pattern]</code></td>
   * <td>Perl</td>
   * <td><a href=
   * "http://infocenter.sybase.com/help/topic/com.sybase.help.sqlanywhere.12.0.1/dbreference/like-regexp-similarto.html"
   * >http://infocenter.sybase.com/help/topic/com.sybase.help.sqlanywhere.12.0
   * .1/dbreference/like-regexp-similarto.html</a></td>
   * </tr>
   * </table>
   *
   * @see #likeRegex(String)
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition likeRegex(String pattern);

  /**
   * Create a condition to regex-pattern-check this field against a pattern.
   *
   * <p>See {@link #likeRegex(String)} for more details
   *
   * @see #likeRegex(String)
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition likeRegex(Field<String> pattern);

  /**
   * Create a condition to regex-pattern-check this field against a pattern.
   *
   * <p>See {@link #likeRegex(String)} for more details
   *
   * @see #likeRegex(String)
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition notLikeRegex(String pattern);

  /**
   * Create a condition to regex-pattern-check this field against a pattern.
   *
   * <p>See {@link #likeRegex(String)} for more details
   *
   * @see #likeRegex(Field)
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition notLikeRegex(Field<String> pattern);

  // ------------------------------------------------------------------------
  // SIMILAR TO predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this similar to value</code>
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  LikeEscapeStep similarTo(Field<String> value);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this similar to value escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  Condition similarTo(Field<String> value, char escape);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this similar to value</code>
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  LikeEscapeStep similarTo(String value);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this similar to value escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  Condition similarTo(String value, char escape);

  /**
   * Create a condition to pattern-check this field against a field.
   *
   * <p>SQL: <code>this not similar to field</code>
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  LikeEscapeStep notSimilarTo(Field<String> field);

  /**
   * Create a condition to pattern-check this field against a field.
   *
   * <p>SQL: <code>this not similar to field escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  Condition notSimilarTo(Field<String> field, char escape);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this not similar to value</code>
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  LikeEscapeStep notSimilarTo(String value);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this not similar to value escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  Condition notSimilarTo(String value, char escape);

  // ------------------------------------------------------------------------
  // LIKE predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this like value</code>
   */
  @NotNull
  @Support
  LikeEscapeStep like(Field<String> value);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this like value escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition like(Field<String> value, char escape);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this like value</code>
   */
  @NotNull
  @Support
  LikeEscapeStep like(String value);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this like value escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition like(String value, char escape);

  /**
   * Create a condition to pattern-check this field against a quantified select.
   *
   * <p>For example a query like {@code field.like(any("a%", "b%"))} translates into the SQL {@code
   * (field like 'a%' or field like 'b%')}.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Field...)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Field...)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  LikeEscapeStep like(QuantifiedSelect<Record1<String>> query);

  /**
   * Create a condition to case-insensitively pattern-check this field against a field.
   *
   * <p>This translates to <code>this ilike field</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) like lower(field)</code> in all other dialects.
   */
  @NotNull
  @Support
  LikeEscapeStep likeIgnoreCase(Field<String> field);

  /**
   * Create a condition to case-insensitively pattern-check this field against a field.
   *
   * <p>This translates to <code>this ilike field</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) like lower(field)</code> in all other dialects.
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition likeIgnoreCase(Field<String> field, char escape);

  /**
   * Create a condition to case-insensitively pattern-check this field against a value.
   *
   * <p>This translates to <code>this ilike value</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) like lower(value)</code> in all other dialects.
   */
  @NotNull
  @Support
  LikeEscapeStep likeIgnoreCase(String value);

  /**
   * Create a condition to case-insensitively pattern-check this field against a value.
   *
   * <p>This translates to <code>this ilike value</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) like lower(value)</code> in all other dialects.
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition likeIgnoreCase(String value, char escape);

  /**
   * Create a condition to pattern-check this field against a field.
   *
   * <p>SQL: <code>this not like field</code>
   */
  @NotNull
  @Support
  LikeEscapeStep notLike(Field<String> field);

  /**
   * Create a condition to pattern-check this field against a field.
   *
   * <p>SQL: <code>this not like field escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition notLike(Field<String> field, char escape);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this not like value</code>
   */
  @NotNull
  @Support
  LikeEscapeStep notLike(String value);

  /**
   * Create a condition to pattern-check this field against a value.
   *
   * <p>SQL: <code>this not like value escape 'e'</code>
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition notLike(String value, char escape);

  /**
   * Create a condition to pattern-check this field against a quantified select.
   *
   * <p>For example a query like {@code field.notLike(any("a%", "b%"))} translates into the SQL
   * {@code (field not like 'a%' or field not like 'b%')}.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Field...)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Field...)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  LikeEscapeStep notLike(QuantifiedSelect<Record1<String>> query);

  /**
   * Create a condition to case-insensitively pattern-check this field against a field.
   *
   * <p>This translates to <code>this not ilike field</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) not like lower(field)</code> in all other dialects.
   */
  @NotNull
  @Support
  LikeEscapeStep notLikeIgnoreCase(Field<String> field);

  /**
   * Create a condition to case-insensitively pattern-check this field against a field.
   *
   * <p>This translates to <code>this not ilike field</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) not like lower(field)</code> in all other dialects.
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition notLikeIgnoreCase(Field<String> field, char escape);

  /**
   * Create a condition to case-insensitively pattern-check this field against a value.
   *
   * <p>This translates to <code>this not ilike value</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) not like lower(value)</code> in all other dialects.
   */
  @NotNull
  @Support
  LikeEscapeStep notLikeIgnoreCase(String value);

  /**
   * Create a condition to case-insensitively pattern-check this field against a value.
   *
   * <p>This translates to <code>this not ilike value</code> in {@link SQLDialect#POSTGRES}, or to
   * <code>lower(this) not like lower(value)</code> in all other dialects.
   *
   * @see LikeEscapeStep#escape(char)
   */
  @NotNull
  @Support
  Condition notLikeIgnoreCase(String value, char escape);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like ('%' || escape(value, '\') || '%') escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).contains(13)</code>
   *
   * <p>If you're using {@link SQLDialect#POSTGRES}, then you can use this method also to express
   * the "ARRAY contains" operator. For example: <code><pre>
   * // Use this expression
   * val(new Integer[] { 1, 2, 3 }).contains(new Integer[] { 1, 2 })
   *
   * // ... to render this SQL
   * ARRAY[1, 2, 3] @&gt; ARRAY[1, 2]
   * </pre></code>
   *
   * <p>Note, this does not correspond to the Oracle Text <code>CONTAINS()</code> function. Refer to
   * {@link OracleDSL#contains(Field, String)} instead.
   *
   * @see DSL#escape(String, char)
   * @see #like(String, char)
   */
  @NotNull
  @Support
  Condition contains(T value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like ('%' || escape(value, '\') || '%') escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).contains(13)</code>
   *
   * <p>If you're using {@link SQLDialect#POSTGRES}, then you can use this method also to express
   * the "ARRAY contains" operator. For example: <code><pre>
   * // Use this expression
   * val(new Integer[] { 1, 2, 3 }).contains(new Integer[] { 1, 2 })
   *
   * // ... to render this SQL
   * ARRAY[1, 2, 3] @&gt; ARRAY[1, 2]
   * </pre></code>
   *
   * <p>Note, this does not correspond to the Oracle Text <code>CONTAINS()</code> function. Refer to
   * {@link OracleDSL#contains(Field, String)} instead.
   *
   * @see DSL#escape(Field, char)
   * @see #like(Field, char)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition contains(Field<T> value);

  /** Inverse of {@link #contains(Object)}. */
  @NotNull
  @Support
  Condition notContains(T value);

  /** Inverse of {@link #contains(Field)}. */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition notContains(Field<T> value);

  /**
   * Convenience method for {@link #likeIgnoreCase(String, char)} including proper adding of
   * wildcards and escaping.
   *
   * <p>This translates to <code>this ilike ('%' || escape(value, '\') || '%') escape '\'</code> in
   * {@link SQLDialect#POSTGRES}, or to <code>
   * lower(this) not like lower(('%' || escape(value, '\') || '%') escape '\')</code> in all other
   * dialects.
   *
   * @see DSL#escape(Field, char)
   * @see #likeIgnoreCase(String, char)
   * @see #contains(Object)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition containsIgnoreCase(T value);

  /**
   * Convenience method for {@link #likeIgnoreCase(String, char)} including proper adding of
   * wildcards and escaping.
   *
   * <p>This translates to <code>this ilike ('%' || escape(value, '\') || '%') escape '\'</code> in
   * {@link SQLDialect#POSTGRES}, or to <code>
   * lower(this) like lower(('%' || escape(value, '\') || '%') escape '\')</code> in all other
   * dialects.
   *
   * @see DSL#escape(Field, char)
   * @see #likeIgnoreCase(Field, char)
   * @see #contains(Field)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition containsIgnoreCase(Field<T> value);

  /** Inverse of {@link #containsIgnoreCase(Object)} */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition notContainsIgnoreCase(T value);

  /** Inverse of {@link #containsIgnoreCase(Field)} */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition notContainsIgnoreCase(Field<T> value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like (escape(value, '\') || '%') escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).startsWith(11)</code>
   *
   * @see DSL#escape(String, char)
   * @see #like(String, char)
   */
  @NotNull
  @Support
  Condition startsWith(T value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like (escape(value, '\') || '%') escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).startsWith(11)</code>
   *
   * @see DSL#escape(Field, char)
   * @see #like(Field, char)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition startsWith(Field<T> value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>lower(this) like (lower(escape(value, '\')) || '%') escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).startsWithIgnoreCase(11)
   * </code>
   *
   * @see DSL#escape(String, char)
   * @see #like(String, char)
   */
  @NotNull
  @Support
  Condition startsWithIgnoreCase(T value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>lower(this) like (lower(escape(value, '\')) || '%') escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).startsWithIgnoreCase(11)
   * </code>
   *
   * @see DSL#escape(Field, char)
   * @see #like(Field, char)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition startsWithIgnoreCase(Field<T> value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like ('%' || escape(value, '\')) escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).endsWith(33)</code>
   *
   * @see DSL#escape(String, char)
   * @see #like(String, char)
   */
  @NotNull
  @Support
  Condition endsWith(T value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like ('%' || escape(value, '\')) escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).endsWith(33)</code>
   *
   * @see DSL#escape(Field, char)
   * @see #like(Field, char)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition endsWith(Field<T> value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>lower(this) like ('%' || lower(escape(value, '\'))) escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).endsWithIgnoreCase(33)
   * </code>
   *
   * @see DSL#escape(String, char)
   * @see #like(String, char)
   */
  @NotNull
  @Support
  Condition endsWithIgnoreCase(T value);

  /**
   * Convenience method for {@link #like(String, char)} including proper adding of wildcards and
   * escaping.
   *
   * <p>SQL: <code>this like ('%' || lower(escape(value, '\'))) escape '\'</code>
   *
   * <p>Note: This also works with numbers, for instance <code>val(1133).endsWithIgnoreCase(33)
   * </code>
   *
   * @see DSL#escape(Field, char)
   * @see #like(Field, char)
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES, SQLITE})
  Condition endsWithIgnoreCase(Field<T> value);

  // ------------------------------------------------------------------------
  // IN predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to check this field against several values.
   *
   * <p>SQL: <code>this in (values...)</code>
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
  Condition in(Collection<?> values);

  /**
   * Create a condition to check this field against several values from a previous query.
   *
   * <p>SQL: <code>this in (values...)</code>
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
  Condition in(Result<? extends Record1<T>> result);

  /**
   * Create a condition to check this field against several values.
   *
   * <p>SQL: <code>this in (values...)</code>
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
  Condition in(T... values);

  /**
   * Create a condition to check this field against several values.
   *
   * <p>SQL: <code>this in (values...)</code>
   */
  @NotNull
  @Support
  Condition in(Field<?>... values);

  /**
   * Create a condition to check this field against a subquery.
   *
   * <p>Note that the subquery must return exactly one field. This is not checked by jOOQ and will
   * result in syntax errors in the database, if not used correctly.
   *
   * <p>SQL: <code>this in (select...)</code>
   */
  @NotNull
  @Support
  Condition in(Select<? extends Record1<T>> query);

  /**
   * Create a condition to check this field against several values.
   *
   * <p>Note that if any of the passed values is <code>NULL</code>, then the condition will be
   * <code>NULL</code> (or <code>false</code>, depending on the dialect) as well. This is standard
   * SQL behaviour.
   *
   * <p>SQL: <code>this not in (values...)</code>
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
  Condition notIn(Collection<?> values);

  /**
   * Create a condition to check this field against several values from a previous query.
   *
   * <p>Note that if any of the passed values is <code>NULL</code>, then the condition will be
   * <code>NULL</code> (or <code>false</code>, depending on the dialect) as well. This is standard
   * SQL behaviour.
   *
   * <p>SQL: <code>this in (values...)</code>
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
  Condition notIn(Result<? extends Record1<T>> result);

  /**
   * Create a condition to check this field against several values.
   *
   * <p>Note that if any of the passed values is <code>NULL</code>, then the condition will be
   * <code>NULL</code> (or <code>false</code>, depending on the dialect) as well. This is standard
   * SQL behaviour.
   *
   * <p>SQL: <code>this not in (values...)</code>
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
  Condition notIn(T... values);

  /**
   * Create a condition to check this field against several values.
   *
   * <p>Note that if any of the passed values is <code>NULL</code>, then the condition will be
   * <code>NULL</code> (or <code>false</code>, depending on the dialect) as well. This is standard
   * SQL behaviour.
   *
   * <p>SQL: <code>this not in (values...)</code>
   */
  @NotNull
  @Support
  Condition notIn(Field<?>... values);

  /**
   * Create a condition to check this field against a subquery.
   *
   * <p>Note that the subquery must return exactly one field. This is not checked by jOOQ and will
   * result in syntax errors in the database, if not used correctly.
   *
   * <p>Note that if any of the passed values is <code>NULL</code>, then the condition will be
   * <code>NULL</code> (or <code>false</code>, depending on the dialect) as well. This is standard
   * SQL behaviour.
   *
   * <p>SQL: <code>this not in (select...)</code>
   */
  @NotNull
  @Support
  Condition notIn(Select<? extends Record1<T>> query);

  // ------------------------------------------------------------------------
  // BETWEEN predicates
  // ------------------------------------------------------------------------
  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>between(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this between minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition between(T minValue, T maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>between(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this between minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition between(Field<T> minValue, Field<T> maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>betweenSymmetric(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition betweenSymmetric(T minValue, T maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>betweenSymmetric(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition betweenSymmetric(Field<T> minValue, Field<T> maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>notBetween(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this not between minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition notBetween(T minValue, T maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>notBetween(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this not between minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition notBetween(Field<T> minValue, Field<T> maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>notBetweenSymmetric(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this not between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition notBetweenSymmetric(T minValue, T maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>This is the same as calling <code>notBetweenSymmetric(minValue).and(maxValue)</code>
   *
   * <p>SQL: <code>this not between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  Condition notBetweenSymmetric(Field<T> minValue, Field<T> maxValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this between minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> between(T minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this between minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> between(Field<T> minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> betweenSymmetric(T minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> betweenSymmetric(Field<T> minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this not between minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> notBetween(T minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this not between minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> notBetween(Field<T> minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this not between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> notBetweenSymmetric(T minValue);

  /**
   * Create a condition to check this field against some bounds.
   *
   * <p>SQL: <code>this not between symmetric minValue and maxValue</code>
   */
  @NotNull
  @Support
  BetweenAndStep<T> notBetweenSymmetric(Field<T> minValue);

  // ------------------------------------------------------------------------
  // Dynamic comparison predicates
  // ------------------------------------------------------------------------
  /**
   * Compare this field with a value using a dynamic comparator.
   *
   * @param comparator The comparator to use for comparing this field with a value
   * @param value The value to compare this field with
   * @return A comparison predicate
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, T value);

  /**
   * Compare this field with another field using a dynamic comparator.
   *
   * @param comparator The comparator to use for comparing this field with another field
   * @param field The field to compare this field with
   * @return A comparison predicate
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Field<T> field);

  /**
   * Compare this field with a subselect using a dynamic comparator.
   *
   * <p>Consider {@link Comparator#supportsSubselect()} to assess whether a comparator can be used
   * with this method.
   *
   * @param comparator The comparator to use for comparing this field with a subselect
   * @param query The subselect to compare this field with
   * @return A comparison predicate
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, Select<? extends Record1<T>> query);

  /**
   * Compare this field with a quantified subselect using a dynamic comparator.
   *
   * <p>Consider {@link Comparator#supportsQuantifier()} to assess whether a comparator can be used
   * with this method.
   *
   * @param comparator The comparator to use for comparing this field with a quantified subselect
   * @param query The quantified subselect to compare this field with
   * @return A comparison predicate
   */
  @NotNull
  @Support
  Condition compare(Comparator comparator, QuantifiedSelect<? extends Record1<T>> query);

  // ------------------------------------------------------------------------
  // Comparison predicates
  // ------------------------------------------------------------------------
  /** <code>this = value</code>. */
  @NotNull
  @Support
  Condition equal(T value);

  /** <code>this = field</code>. */
  @NotNull
  @Support
  Condition equal(Field<T> field);

  /** <code>this = (Select&lt;?&gt; ...)</code>. */
  @NotNull
  @Support
  Condition equal(Select<? extends Record1<T>> query);

  /**
   * <code>this = [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES})
  Condition equal(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * <code>this = value</code>.
   *
   * @see #equal(Object)
   */
  @NotNull
  @Support
  Condition eq(T value);

  /**
   * <code>this = field</code>.
   *
   * @see #equal(Field)
   */
  @NotNull
  @Support
  Condition eq(Field<T> field);

  /**
   * <code>this = (Select&lt;?&gt; ...)</code>.
   *
   * @see #equal(Select)
   */
  @NotNull
  @Support
  Condition eq(Select<? extends Record1<T>> query);

  /**
   * <code>this = [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, POSTGRES})
  Condition eq(QuantifiedSelect<? extends Record1<T>> query);

  /** <code>this != value</code>. */
  @NotNull
  @Support
  Condition notEqual(T value);

  /** <code>this != field</code>. */
  @NotNull
  @Support
  Condition notEqual(Field<T> field);

  /** <code>this != (Select&lt;?&gt; ...)</code>. */
  @NotNull
  @Support
  Condition notEqual(Select<? extends Record1<T>> query);

  /**
   * <code>this != [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition notEqual(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * <code>this != value</code>.
   *
   * @see #notEqual(Object)
   */
  @NotNull
  @Support
  Condition ne(T value);

  /**
   * <code>this != field</code>.
   *
   * @see #notEqual(Field)
   */
  @NotNull
  @Support
  Condition ne(Field<T> field);

  /**
   * <code>this != (Select&lt;?&gt; ...)</code>.
   *
   * @see #notEqual(Select)
   */
  @NotNull
  @Support
  Condition ne(Select<? extends Record1<T>> query);

  /**
   * <code>this != [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition ne(QuantifiedSelect<? extends Record1<T>> query);

  /** <code>this &lt; value</code>. */
  @NotNull
  @Support
  Condition lessThan(T value);

  /** <code>this &lt; field</code>. */
  @NotNull
  @Support
  Condition lessThan(Field<T> field);

  /** <code>this &lt; (Select&lt;?&gt; ...)</code>. */
  @NotNull
  @Support
  Condition lessThan(Select<? extends Record1<T>> query);

  /**
   * <code>this &lt; [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition lessThan(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * <code>this &lt; value</code>.
   *
   * @see #lessThan(Object)
   */
  @NotNull
  @Support
  Condition lt(T value);

  /**
   * <code>this &lt; field</code>.
   *
   * @see #lessThan(Field)
   */
  @NotNull
  @Support
  Condition lt(Field<T> field);

  /**
   * <code>this &lt; (Select&lt;?&gt; ...)</code>.
   *
   * @see #lessThan(Select)
   */
  @NotNull
  @Support
  Condition lt(Select<? extends Record1<T>> query);

  /**
   * <code>this &lt; [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition lt(QuantifiedSelect<? extends Record1<T>> query);

  /** <code>this &lt;= value</code>. */
  @NotNull
  @Support
  Condition lessOrEqual(T value);

  /** <code>this &lt;= field</code>. */
  @NotNull
  @Support
  Condition lessOrEqual(Field<T> field);

  /** <code>this &lt;= (Select&lt;?&gt; ...)</code>. */
  @NotNull
  @Support
  Condition lessOrEqual(Select<? extends Record1<T>> query);

  /**
   * <code>this &lt;= [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition lessOrEqual(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * <code>this &lt;= value</code>.
   *
   * @see #lessOrEqual(Object)
   */
  @NotNull
  @Support
  Condition le(T value);

  /**
   * <code>this &lt;= field</code>.
   *
   * @see #lessOrEqual(Field)
   */
  @NotNull
  @Support
  Condition le(Field<T> field);

  /**
   * <code>this &lt;= (Select&lt;?&gt; ...)</code>.
   *
   * @see #lessOrEqual(Select)
   */
  @NotNull
  @Support
  Condition le(Select<? extends Record1<T>> query);

  /**
   * <code>this &lt;= [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition le(QuantifiedSelect<? extends Record1<T>> query);

  /** <code>this &gt; value</code>. */
  @NotNull
  @Support
  Condition greaterThan(T value);

  /** <code>this &gt; field</code>. */
  @NotNull
  @Support
  Condition greaterThan(Field<T> field);

  /** <code>this &gt; (Select&lt;?&gt; ...)</code>. */
  @NotNull
  @Support
  Condition greaterThan(Select<? extends Record1<T>> query);

  /**
   * <code>this &gt; [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition greaterThan(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * <code>this &gt; value</code>.
   *
   * @see #greaterThan(Object)
   */
  @NotNull
  @Support
  Condition gt(T value);

  /**
   * <code>this &gt; field</code>.
   *
   * @see #greaterThan(Field)
   */
  @NotNull
  @Support
  Condition gt(Field<T> field);

  /**
   * <code>this &gt; (Select&lt;?&gt; ...)</code>.
   *
   * @see #greaterThan(Select)
   */
  @NotNull
  @Support
  Condition gt(Select<? extends Record1<T>> query);

  /**
   * <code>this &gt; [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition gt(QuantifiedSelect<? extends Record1<T>> query);

  /** <code>this &gt;= value</code>. */
  @NotNull
  @Support
  Condition greaterOrEqual(T value);

  /** <code>this &gt;= field</code>. */
  @NotNull
  @Support
  Condition greaterOrEqual(Field<T> field);

  /** <code>this &gt;= (Select&lt;?&gt; ...)</code>. */
  @NotNull
  @Support
  Condition greaterOrEqual(Select<? extends Record1<T>> query);

  /**
   * <code>this &gt;= [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition greaterOrEqual(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * <code>this &gt;= value</code>.
   *
   * @see #greaterOrEqual(Object)
   */
  @NotNull
  @Support
  Condition ge(T value);

  /**
   * <code>this &gt;= field</code>.
   *
   * @see #greaterOrEqual(Field)
   */
  @NotNull
  @Support
  Condition ge(Field<T> field);

  /**
   * <code>this &gt;= (Select&lt;?&gt; ...)</code>.
   *
   * @see #greaterOrEqual(Select)
   */
  @NotNull
  @Support
  Condition ge(Select<? extends Record1<T>> query);

  /**
   * <code>this &gt;= [quantifier] (Select&lt;?&gt; ...)</code>.
   *
   * @see DSL#all(Field)
   * @see DSL#all(Select)
   * @see DSL#all(Object...)
   * @see DSL#any(Field)
   * @see DSL#any(Select)
   * @see DSL#any(Object...)
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Condition ge(QuantifiedSelect<? extends Record1<T>> query);

  /**
   * Create a condition to check this field against known string literals for <code>true</code>.
   *
   * <p>SQL: <code>lcase(this) in ("1", "y", "yes", "true", "on", "enabled")</code>
   */
  @NotNull
  @Support
  Condition isTrue();

  /**
   * Create a condition to check this field against known string literals for <code>false</code>.
   *
   * <p>SQL: <code>lcase(this) in ("0", "n", "no", "false", "off", "disabled")</code>
   */
  @NotNull
  @Support
  Condition isFalse();

  /** <code>lower(this) = lower(value)</code>. */
  @NotNull
  @Support
  Condition equalIgnoreCase(String value);

  /** <code>lower(this) = lower(value)</code>. */
  @NotNull
  @Support
  Condition equalIgnoreCase(Field<String> value);

  /** <code>lower(this) != lower(value)</code>. */
  @NotNull
  @Support
  Condition notEqualIgnoreCase(String value);

  /** <code>lower(this) != lower(value)</code>. */
  @NotNull
  @Support
  Condition notEqualIgnoreCase(Field<String> value);

  // ------------------------------------------------------------------------
  // Pre-2.0 API. This API is maintained for backwards-compatibility. It will
  // be removed in the future. Consider using equivalent methods from
  // org.jooq.impl.DSL
  // ------------------------------------------------------------------------
  /**
   * @see DSL#sign(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#sign(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> sign();

  /**
   * @see DSL#abs(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#abs(Field)} instead.
   */
  @NotNull
  @Support
  Field<T> abs();

  /**
   * @see DSL#round(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#round(Field)} instead.
   */
  @NotNull
  @Support
  Field<T> round();

  /**
   * @see DSL#round(Field, int)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#round(Field, int)} instead.
   */
  @NotNull
  @Support
  Field<T> round(int decimals);

  /**
   * @see DSL#floor(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#floor(Field)} instead.
   */
  @NotNull
  @Support
  Field<T> floor();

  /**
   * @see DSL#ceil(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#ceil(Field)} instead.
   */
  @NotNull
  @Support
  Field<T> ceil();

  /**
   * @see DSL#sqrt(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#sqrt(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> sqrt();

  /**
   * @see DSL#exp(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#exp(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> exp();

  /**
   * @see DSL#ln(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#ln(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> ln();

  /**
   * @see DSL#log(Field, int)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#log(Field, int)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> log(int base);

  /**
   * @see DSL#acos(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#acos(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> acos();

  /**
   * @see DSL#asin(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#asin(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> asin();

  /**
   * @see DSL#atan(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#atan(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> atan();

  /**
   * @see DSL#atan2(Field, Number)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#atan2(Field, Number)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> atan2(Number y);

  /**
   * @see DSL#atan2(Field, Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#atan2(Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> atan2(Field<? extends Number> y);

  /**
   * @see DSL#cos(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#cos(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> cos();

  /**
   * @see DSL#sin(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#sin(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> sin();

  /**
   * @see DSL#tan(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#tan(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> tan();

  /**
   * @see DSL#cot(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#cot(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> cot();

  /**
   * @see DSL#sinh(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#sinh(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> sinh();

  /**
   * @see DSL#cosh(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#cosh(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> cosh();

  /**
   * @see DSL#tanh(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#tanh(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> tanh();

  /**
   * @see DSL#coth(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#coth(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> coth();

  /**
   * @see DSL#deg(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#deg(Field)} instead.
   */
  @NotNull
  @Support
  Field<BigDecimal> deg();

  /**
   * @see DSL#rad(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#rad(Field)} instead.
   */
  @NotNull
  @Support
  Field<BigDecimal> rad();

  /**
   * @see DSL#count(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#count(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> count();

  /**
   * @see DSL#countDistinct(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#countDistinct(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> countDistinct();

  /**
   * @see DSL#max(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#max(Field)} instead.
   */
  @NotNull
  @Support
  Field<T> max();

  /**
   * @see DSL#min(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#min(Field)} instead.
   */
  @NotNull
  @Support
  Field<T> min();

  /**
   * @see DSL#sum(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#sum(Field)} instead.
   */
  @NotNull
  @Support
  Field<BigDecimal> sum();

  /**
   * @see DSL#avg(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#avg(Field)} instead.
   */
  @NotNull
  @Support
  Field<BigDecimal> avg();

  /**
   * @see DSL#median(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#median(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, HSQLDB})
  Field<BigDecimal> median();

  /**
   * @see DSL#stddevPop(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#stddevPop(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> stddevPop();

  /**
   * @see DSL#stddevSamp(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#stddevSamp(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> stddevSamp();

  /**
   * @see DSL#varPop(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#varPop(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> varPop();

  /**
   * @see DSL#varSamp(Field)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#varSamp(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<BigDecimal> varSamp();

  /**
   * @see DSL#count(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#count(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowPartitionByStep<Integer> countOver();

  /**
   * @see DSL#max(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#max(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowPartitionByStep<T> maxOver();

  /**
   * @see DSL#min(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#min(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowPartitionByStep<T> minOver();

  /**
   * @see DSL#sum(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#sum(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowPartitionByStep<BigDecimal> sumOver();

  /**
   * @see DSL#avg(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#avg(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowPartitionByStep<BigDecimal> avgOver();

  /**
   * @see DSL#firstValue(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#firstValue(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> firstValue();

  /**
   * @see DSL#lastValue(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lastValue(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lastValue();

  /**
   * @see DSL#lead(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lead(Field)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lead();

  /**
   * @see DSL#lead(Field, int)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lead(Field, int)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lead(int offset);

  /**
   * @see DSL#lead(Field, int, Object)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lead(Field, int, Object)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lead(int offset, T defaultValue);

  /**
   * @see DSL#lead(Field, int, Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lead(Field, int, Field)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lead(int offset, Field<T> defaultValue);

  /**
   * @see DSL#lag(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lag(Field)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lag();

  /**
   * @see DSL#lag(Field, int)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lag(Field, int)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lag(int offset);

  /**
   * @see DSL#lag(Field, int, Object)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lag(Field, int, Object)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lag(int offset, T defaultValue);

  /**
   * @see DSL#lag(Field, int, Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#lag(Field, int, Field)} instead.
   */
  @NotNull
  @Support({FIREBIRD, POSTGRES})
  WindowIgnoreNullsStep<T> lag(int offset, Field<T> defaultValue);

  /**
   * @see DSL#stddevPop(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#stddevPop(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, POSTGRES})
  WindowPartitionByStep<BigDecimal> stddevPopOver();

  /**
   * @see DSL#stddevSamp(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#stddevSamp(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, POSTGRES})
  WindowPartitionByStep<BigDecimal> stddevSampOver();

  /**
   * @see DSL#varPop(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#varPop(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, POSTGRES})
  WindowPartitionByStep<BigDecimal> varPopOver();

  /**
   * @see DSL#varSamp(Field)
   * @see AggregateFunction#over()
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#varSamp(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, POSTGRES})
  WindowPartitionByStep<BigDecimal> varSampOver();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#upper(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#upper(Field)} instead.
   */
  @NotNull
  @Support
  Field<String> upper();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#lower(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#lower(Field)} instead.
   */
  @NotNull
  @Support
  Field<String> lower();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#trim(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#trim(Field)} instead.
   */
  @NotNull
  @Support
  Field<String> trim();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#rtrim(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#rtrim(Field)} instead.
   */
  @NotNull
  @Support
  Field<String> rtrim();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#ltrim(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#ltrim(Field)} instead.
   */
  @NotNull
  @Support
  Field<String> ltrim();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#rpad(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#rpad(Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> rpad(Field<? extends Number> length);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#rpad(Field, int)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#rpad(Field, int)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> rpad(int length);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#rpad(Field, Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#rpad(Field, Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> rpad(Field<? extends Number> length, Field<String> character);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#rpad(Field, int, char)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#rpad(Field, int, char)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> rpad(int length, char character);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#lpad(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#lpad(Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> lpad(Field<? extends Number> length);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#lpad(Field, int)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#lpad(Field, int)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> lpad(int length);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#lpad(Field, Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#lpad(Field, Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> lpad(Field<? extends Number> length, Field<String> character);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#lpad(Field, int, char)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#lpad(Field, int, char)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> lpad(int length, char character);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#repeat(Field, int)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#repeat(Field, int)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> repeat(Number count);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#repeat(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#repeat(Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<String> repeat(Field<? extends Number> count);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#replace(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#replace(Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> replace(Field<String> search);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#replace(Field, String)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#replace(Field, String)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> replace(String search);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#replace(Field, Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#replace(Field, Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> replace(Field<String> search, Field<String> replace);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#replace(Field, String, String)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#replace(Field, String, String)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> replace(String search, String replace);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#position(Field, String)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#position(Field, String)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<Integer> position(String search);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#position(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#position(Field, Field)} instead.
   */
  @NotNull
  @Support({CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<Integer> position(Field<String> search);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#ascii(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#ascii(Field)} instead.
   */
  @NotNull
  @Support({CUBRID, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES})
  Field<Integer> ascii();

  /**
   * Apply a collation operator to this column expression.
   *
   * @see DSL#collation(String)
   */
  @NotNull
  @Support({HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> collate(String collation);

  /**
   * Apply a collation operator to this column expression.
   *
   * @see DSL#collation(Name)
   */
  @NotNull
  @Support({HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> collate(Name collation);

  /** Apply a collation operator to this column expression. */
  @NotNull
  @Support({HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  Field<String> collate(Collation collation);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#concat(Field...)
   */
  @NotNull
  @Support
  Field<String> concat(Field<?>... fields);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#concat(String...)
   */
  @NotNull
  @Support
  Field<String> concat(String... values);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#concat(String...)
   */
  @NotNull
  @Support
  Field<String> concat(char... values);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#substring(Field, int)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#substring(Field, int)} instead.
   */
  @NotNull
  @Support
  Field<String> substring(int startingPosition);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#substring(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#substring(Field, Field)} instead.
   */
  @NotNull
  @Support
  Field<String> substring(Field<? extends Number> startingPosition);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#substring(Field, int, int)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#substring(Field, int, int)} instead.
   */
  @NotNull
  @Support
  Field<String> substring(int startingPosition, int length);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#substring(Field, Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#substring(Field, Field, Field)} instead.
   */
  @NotNull
  @Support
  Field<String> substring(Field<? extends Number> startingPosition, Field<? extends Number> length);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#length(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#length(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> length();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#charLength(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#charLength(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> charLength();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#bitLength(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#bitLength(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> bitLength();

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#octetLength(Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#octetLength(Field)} instead.
   */
  @NotNull
  @Support
  Field<Integer> octetLength();

  /**
   * @see DSL#extract(Field, DatePart)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#extract(Field, DatePart)} instead.
   */
  @NotNull
  @Support
  Field<Integer> extract(DatePart datePart);

  /**
   * @see DSL#greatest(Field, Field...)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#greatest(Field, Field...)} instead.
   */
  @NotNull
  @Support
  Field<T> greatest(T... others);

  /**
   * @see DSL#greatest(Field, Field...)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#greatest(Field, Field...)} instead.
   */
  @NotNull
  @Support
  Field<T> greatest(Field<?>... others);

  /**
   * @see DSL#least(Field, Field...)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#least(Field, Field...)} instead.
   */
  @NotNull
  @Support
  Field<T> least(T... others);

  /**
   * @see DSL#least(Field, Field...)
   * @deprecated - 3.11 - [#7538] - Use {@link DSL#least(Field, Field...)} instead.
   */
  @NotNull
  @Support
  Field<T> least(Field<?>... others);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#nvl(Field, Object)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#nvl(Field, Object)} instead.
   */
  @NotNull
  @Support
  Field<T> nvl(T defaultValue);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#nvl(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#nvl(Field, Field)} instead.
   */
  @NotNull
  @Support
  Field<T> nvl(Field<T> defaultValue);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#nvl2(Field, Object, Object)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#nvl2(Field, Object, Object)} instead.
   */
  @NotNull
  @Support
  <Z> Field<Z> nvl2(Z valueIfNotNull, Z valueIfNull);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#nvl2(Field, Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#nvl2(Field, Field, Field)} instead.
   */
  @NotNull
  @Support
  <Z> Field<Z> nvl2(Field<Z> valueIfNotNull, Field<Z> valueIfNull);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#nullif(Field, Object)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#nullif(Field, Object)} instead.
   */
  @NotNull
  @Support
  Field<T> nullif(T other);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#nullif(Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#nullif(Field, Field)} instead.
   */
  @NotNull
  @Support
  Field<T> nullif(Field<T> other);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#decode(Object, Object, Object)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#decode(Object, Object, Object)} instead.
   */
  @NotNull
  @Support
  <Z> Field<Z> decode(T search, Z result);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#decode(Object, Object, Object, Object...)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#decode(Object, Object, Object, Object...)}
   *     instead.
   */
  @NotNull
  @Support
  <Z> Field<Z> decode(T search, Z result, Object... more);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#decode(Field, Field, Field)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#decode(Field, Field, Field)} instead.
   */
  @NotNull
  @Support
  <Z> Field<Z> decode(Field<T> search, Field<Z> result);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#decode(Field, Field, Field, Field...)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#decode(Field, Field, Field, Field...)} instead.
   */
  @NotNull
  @Support
  <Z> Field<Z> decode(Field<T> search, Field<Z> result, Field<?>... more);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#coalesce(Object, Object...)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#coalesce(Object, Object...)} instead.
   */
  @NotNull
  @Support
  Field<T> coalesce(T option, T... options);

  /**
   * This method is part of the pre-2.0 API. This API is maintained for backwards-compatibility. It
   * may be removed in the future. Consider using equivalent methods from {@link DSLContext}
   *
   * @see DSL#coalesce(Field, Field...)
   * @deprecated - 3.13 - [#9407] - Use {@link DSL#coalesce(Field, Field...)} instead.
   */
  @NotNull
  @Support
  Field<T> coalesce(Field<T> option, Field<?>... options);

  // ------------------------------------------------------------------------
  // [#5518] Record method inversions, e.g. for use as method references
  // ------------------------------------------------------------------------
  /**
   * The inverse operation of {@link Record#field(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream.
   */
  @Nullable
  Field<T> field(Record record);

  /**
   * The inverse operation of {@link Record#get(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream: <code><pre>
   * DSL.using(configuration)
   *    .fetch("select * from t")
   *    .stream()
   *    .map(MY_TABLE.ID::get)
   *    .forEach(System.out::println);
   * </pre></code>
   */
  @Nullable
  T get(Record record);

  /**
   * The inverse operation of {@link Record#getValue(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream: <code><pre>
   * DSL.using(configuration)
   *    .fetch("select * from t")
   *    .stream()
   *    .map(MY_TABLE.ID::getValue)
   *    .forEach(System.out::println);
   * </pre></code>
   */
  @Nullable
  T getValue(Record record);

  /**
   * The inverse operation of {@link Record#original(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream: <code><pre>
   * DSL.using(configuration)
   *    .fetch("select * from t")
   *    .stream()
   *    .map(MY_TABLE.ID::original)
   *    .forEach(System.out::println);
   * </pre></code>
   */
  @Nullable
  T original(Record record);

  /**
   * The inverse operation of {@link Record#changed(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream: <code><pre>
   * DSL.using(configuration)
   *    .fetch("select * from t")
   *    .stream()
   *    .map(MY_TABLE.ID::changed)
   *    .forEach(System.out::println);
   * </pre></code>
   */
  boolean changed(Record record);

  /**
   * The inverse operation of {@link Record#reset(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream: <code><pre>
   * DSL.using(configuration)
   *    .fetch("select * from t")
   *    .stream()
   *    .forEach(MY_TABLE.ID::reset);
   * </pre></code>
   */
  void reset(Record record);

  /**
   * The inverse operation of {@link Record#into(Field)}.
   *
   * <p>This method can be used in its method reference form conveniently on a generated table, for
   * instance, when mapping records in a stream: <code><pre>
   * DSL.using(configuration)
   *    .fetch("select * from t")
   *    .stream()
   *    .map(MY_TABLE.ID::from)
   *    .forEach(System.out::println);
   * </pre></code>
   */
  @Nullable
  Record1<T> from(Record record);
}
