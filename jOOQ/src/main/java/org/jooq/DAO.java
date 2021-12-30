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
import static org.jooq.SQLDialect.CUBRID;
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.H2;
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.MARIADB;
import static org.jooq.SQLDialect.MYSQL;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLITE;

import java.util.Collection;
import java.util.List;
import org.java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;

/**
 * A generic DAO interface for a pojo and a primary key type.
 *
 * <p>This type is implemented by generated DAO classes to provide a common API for common actions
 * on POJOs
 *
 * @author Lukas Eder
 * @param <R> The generic record type.
 * @param <P> The generic POJO type.
 * @param <T> The generic primary key type. This is a regular <code>&lt;T&gt;</code> type for
 *     single-column keys, or a {@link Record} subtype for composite keys.
 */
public interface DAO<R extends TableRecord<R>, P, T> {

  /**
   * Expose the configuration in whose context this <code>DAO</code> is operating.
   *
   * @return the <code>DAO</code>'s underlying <code>Configuration</code>
   */
  @NotNull
  Configuration configuration();

  /**
   * The settings wrapped by this context.
   *
   * <p>This method is a convenient way of accessing <code>configuration().settings()</code>.
   */
  @NotNull
  Settings settings();

  /**
   * The {@link SQLDialect} wrapped by this context.
   *
   * <p>This method is a convenient way of accessing <code>configuration().dialect()</code>.
   */
  @NotNull
  SQLDialect dialect();

  /**
   * The {@link SQLDialect#family()} wrapped by this context.
   *
   * <p>This method is a convenient way of accessing <code>configuration().family()</code>.
   */
  @NotNull
  SQLDialect family();

  /**
   * Expose the {@link RecordMapper} that is used internally by this <code>DAO</code> to map from
   * records of type <code>R</code> to POJOs of type <code>P</code>.
   *
   * @return the <code>DAO</code>'s underlying <code>RecordMapper</code>
   */
  @NotNull
  RecordMapper<R, P> mapper();

  /**
   * Performs an <code>INSERT</code> statement for a given POJO.
   *
   * @param object The POJO to be inserted
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support
  void insert(P object) throws DataAccessException;

  /**
   * Performs a batch <code>INSERT</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be inserted
   * @throws DataAccessException if something went wrong executing the query
   * @see #insert(Collection)
   */
  @Support
  void insert(P... objects) throws DataAccessException;

  /**
   * Performs a batch <code>INSERT</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be inserted
   * @throws DataAccessException if something went wrong executing the query
   * @see #insert(Object...)
   */
  @Support
  void insert(Collection<P> objects) throws DataAccessException;

  /**
   * Performs an <code>UPDATE</code> statement for a given POJO.
   *
   * @param object The POJO to be updated
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support
  void update(P object) throws DataAccessException;

  /**
   * Performs a batch <code>UPDATE</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be updated
   * @throws DataAccessException if something went wrong executing the query
   * @see #update(Collection)
   */
  @Support
  void update(P... objects) throws DataAccessException;

  /**
   * Performs a batch <code>UPDATE</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be updated
   * @throws DataAccessException if something went wrong executing the query
   * @see #update(Object...)
   */
  @Support
  void update(Collection<P> objects) throws DataAccessException;

  /**
   * Performs an <code>MERGE</code> statement for a given POJO.
   *
   * @param object The POJO to be merged
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support({CUBRID, DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  void merge(P object) throws DataAccessException;

  /**
   * Performs a batch <code>MERGE</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be merged
   * @throws DataAccessException if something went wrong executing the query
   * @see #update(Collection)
   */
  @Support({CUBRID, DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  void merge(P... objects) throws DataAccessException;

  /**
   * Performs a batch <code>MERGE</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be merged
   * @throws DataAccessException if something went wrong executing the query
   * @see #update(Object...)
   */
  @Support({CUBRID, DERBY, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE})
  void merge(Collection<P> objects) throws DataAccessException;

  /**
   * Performs a <code>DELETE</code> statement for a POJO
   *
   * @param object The POJO to be deleted
   * @throws DataAccessException if something went wrong executing the query
   * @see #delete(Collection)
   */
  @Support
  void delete(P object) throws DataAccessException;

  /**
   * Performs a <code>DELETE</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be deleted
   * @throws DataAccessException if something went wrong executing the query
   * @see #delete(Collection)
   */
  @Support
  void delete(P... objects) throws DataAccessException;

  /**
   * Performs a <code>DELETE</code> statement for a given set of POJOs.
   *
   * @param objects The POJOs to be deleted
   * @throws DataAccessException if something went wrong executing the query
   * @see #delete(Object...)
   */
  @Support
  void delete(Collection<P> objects) throws DataAccessException;

  /**
   * Performs a <code>DELETE</code> statement for a given set of IDs.
   *
   * @param ids The IDs to be deleted
   * @throws DataAccessException if something went wrong executing the query
   * @see #delete(Collection)
   */
  @Support
  void deleteById(T... ids) throws DataAccessException;

  /**
   * Performs a <code>DELETE</code> statement for a given set of IDs.
   *
   * @param ids The IDs to be deleted
   * @throws DataAccessException if something went wrong executing the query
   * @see #delete(Object...)
   */
  @Support
  void deleteById(Collection<T> ids) throws DataAccessException;

  /**
   * Checks if a given POJO exists.
   *
   * @param object The POJO whose existence is checked
   * @return Whether the POJO already exists
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support
  boolean exists(P object) throws DataAccessException;

  /**
   * Checks if a given ID exists.
   *
   * @param id The ID whose existence is checked
   * @return Whether the ID already exists
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support
  boolean existsById(T id) throws DataAccessException;

  /**
   * Count all records of the underlying table.
   *
   * @return The number of records of the underlying table
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support
  long count() throws DataAccessException;

  /**
   * Find all records of the underlying table.
   *
   * @return All records of the underlying table
   * @throws DataAccessException if something went wrong executing the query
   */
  @NotNull
  @Support
  List<P> findAll() throws DataAccessException;

  /**
   * Find a record of the underlying table by ID.
   *
   * @param id The ID of a record in the underlying table
   * @return A record of the underlying table given its ID, or <code>null</code> if no record was
   *     found.
   * @throws DataAccessException if something went wrong executing the query
   */
  @Nullable
  @Support
  P findById(T id) throws DataAccessException;

  /**
   * Find records by a given field and a set of values.
   *
   * @param field The field to compare values against
   * @param values The accepted values
   * @return A list of records fulfilling <code>field IN (values)</code>
   * @throws DataAccessException if something went wrong executing the query
   */
  @NotNull
  @Support
  <Z> List<P> fetch(Field<Z> field, Z... values) throws DataAccessException;

  /**
   * Find records by a given field and a set of values.
   *
   * @param field The field to compare values against
   * @param values The accepted values
   * @return A list of records fulfilling <code>field IN (values)</code>
   * @throws DataAccessException if something went wrong executing the query
   */
  @NotNull
  @Support
  <Z> List<P> fetch(Field<Z> field, Collection<? extends Z> values) throws DataAccessException;

  /**
   * Find records by a given field and a range of values.
   *
   * @param field The field to compare values against
   * @param lowerInclusive The range's lower bound (inclusive), or unbounded if <code>null</code>.
   * @param upperInclusive The range's upper bound (inclusive), or unbounded if <code>null</code>.
   * @return A list of records fulfilling <code>field BETWEEN lowerInclusive AND upperInclusive
   *     </code>
   * @throws DataAccessException if something went wrong executing the query
   */
  @NotNull
  @Support
  <Z> List<P> fetchRange(Field<Z> field, Z lowerInclusive, Z upperInclusive)
      throws DataAccessException;

  /**
   * Find a unique record by a given field and a value.
   *
   * @param field The field to compare value against
   * @param value The accepted value
   * @return A record fulfilling <code>field = value</code>, or <code>null</code>
   * @throws DataAccessException This exception is thrown
   *     <ul>
   *       <li>if something went wrong executing the query
   *       <li>if the query returned more than one value
   *     </ul>
   */
  @Nullable
  @Support
  <Z> P fetchOne(Field<Z> field, Z value) throws DataAccessException;

  /**
   * Find a unique record by a given field and a value.
   *
   * @param field The field to compare value against
   * @param value The accepted value
   * @return A record fulfilling <code>field = value</code>
   * @throws DataAccessException This exception is thrown
   *     <ul>
   *       <li>if something went wrong executing the query
   *       <li>if the query returned more than one value
   *     </ul>
   */
  @NotNull
  @Support
  <Z> Optional<P> fetchOptional(Field<Z> field, Z value) throws DataAccessException;

  /** Get the underlying table. */
  @NotNull
  Table<R> getTable();

  /** Get the underlying POJO type. */
  @NotNull
  Class<P> getType();

  /** Extract the ID value from a POJO. */
  T getId(P object);
}
