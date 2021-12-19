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

import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.conf.Settings;
import org.jooq.exception.DataAccessException;

/**
 * A record originating from a single table
 *
 * @param <R> The record type
 * @author Lukas Eder
 */
public interface TableRecord<R extends TableRecord<R>> extends QualifiedRecord<R> {

  /** The table from which this record was read. */
  @NotNull
  Table<R> getTable();

  @NotNull
  @Override
  R original();

  /**
   * Store this record to the database using an <code>INSERT</code> statement.
   *
   * <p>If you want to enforce re-insertion this record's values, regardless if the values in this
   * record were changed, you can explicitly set the changed flags for all values with {@link
   * #changed(boolean)} or for single values with {@link #changed(Field, boolean)}, prior to
   * insertion.
   *
   * @return <code>1</code> if the record was stored to the database. <code>0
   *         </code> if storing was not necessary and {@link Settings#isInsertUnchangedRecords()} is
   *     set to false.
   * @throws DataAccessException if something went wrong executing the query
   */
  @Support
  int insert() throws DataAccessException;

  /**
   * Store parts of this record to the database using an <code>INSERT</code> statement.
   *
   * @return <code>1</code> if the record was stored to the database. <code>0
   *         </code> if storing was not necessary.
   * @throws DataAccessException if something went wrong executing the query
   * @see #insert()
   */
  @Support
  int insert(Field<?>... fields) throws DataAccessException;

  /**
   * Store parts of this record to the database using an <code>INSERT</code> statement.
   *
   * @return <code>1</code> if the record was stored to the database. <code>0
   *         </code> if storing was not necessary.
   * @throws DataAccessException if something went wrong executing the query
   * @see #insert()
   */
  @Support
  int insert(Collection<? extends Field<?>> fields) throws DataAccessException;

  /**
   * Fetch a parent record of this record, given a foreign key.
   *
   * <p>This returns a parent record referenced by this record through a given foreign key, as if
   * fetching from {@link #parent(ForeignKey)}. If no parent record was found, this returns <code>
   * null</code>
   *
   * @throws DataAccessException if something went wrong executing the query
   * @see ForeignKey#fetchParent(Record)
   * @see ForeignKey#fetchParents(java.util.Collection)
   * @see ForeignKey#fetchParents(Record...)
   */
  @Nullable
  @Support
  <O extends UpdatableRecord<O>> O fetchParent(ForeignKey<R, O> key) throws DataAccessException;

  /** Get a table expression representing the parent of this record, given a foreign key. */
  @NotNull
  @Support
  <O extends UpdatableRecord<O>> Table<O> parent(ForeignKey<R, O> key);
}
