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
package io.r2dbc.spi;

import java.util.NoSuchElementException;
import org.reactivestreams.Publisher;

/**
 * A statement that can be executed multiple times in a prepared and optimized way. Bound parameters
 * can be either scalar values (using type inference for the database parameter type) or {@link
 * Parameter} objects.
 *
 * @see Result
 * @see Row
 * @see Blob
 * @see Clob
 * @see Parameter
 */
public interface Statement {

  /**
   * Save the current binding and create a new one to indicate the statement should be executed
   * again with new bindings provided through subsequent calls to {@code bind} and {@code bindNull}.
   * For example:
   *
   * <pre class="code">
   * Statement statement = connection.createStatement("INSERT INTO books (author, publisher) VALUES ($1, $2)");
   * statement.bind(0, "John Doe").bind(1, "Happy Books LLC").add();
   * statement.bind(0, "Jane Doe").bind(1, "Scary Books Inc");
   * statement.execute();
   * </pre>
   *
   * @return this {@link Statement}
   * @throws IllegalStateException if the statement is parametrized and not all parameter values are
   *     provided
   */
  Statement add();

  /**
   * Bind a non-{@code null} value to an indexed parameter. Indexes are zero-based.
   *
   * @param index the index to bind to
   * @param value the value to bind
   * @return this {@link Statement}
   * @throws IllegalArgumentException if {@code value} is {@code null}
   * @throws IndexOutOfBoundsException if the parameter {@code index} is out of range
   */
  Statement bind(int index, Object value);

  /**
   * Bind a non-{@code null} to a named parameter.
   *
   * @param name the name of identifier to bind to
   * @param value the value to bind
   * @return this {@link Statement}
   * @throws IllegalArgumentException if {@code name} or {@code value} is {@code null}
   * @throws NoSuchElementException if {@code name} is not a known name to bind
   */
  Statement bind(String name, Object value);

  /**
   * Bind a {@code null} value to an indexed parameter. Indexes are zero-based.
   *
   * @param index the index to bind to
   * @param type the type of null value
   * @return this {@link Statement}
   * @throws IllegalArgumentException if {@code type} is {@code null}
   * @throws IndexOutOfBoundsException if the parameter {@code index} is out of range
   */
  Statement bindNull(int index, Class<?> type);

  /**
   * Bind a {@code null} value to a named parameter.
   *
   * @param name the name of identifier to bind to
   * @param type the type of null value
   * @return this {@link Statement}
   * @throws IllegalArgumentException if {@code name} or {@code type} is {@code null}
   * @throws NoSuchElementException if {@code name} is not a known name to bind
   */
  Statement bindNull(String name, Class<?> type);

  /**
   * Executes one or more SQL statements and returns the {@link Result}s. {@link Result} objects
   * must be fully consumed to ensure full execution of the {@link Statement}. This statement is
   * executed additionally for each binding saved through {@link #add()}.
   *
   * @return the {@link Result}s, returned by each statement
   * @throws IllegalStateException if the statement is parametrized and not all parameter values are
   *     provided
   */
  Publisher<? extends Result> execute();

  /**
   * Configures {@link Statement} to return the generated values from any rows created by this
   * {@link Statement} in the {@link Result} returned from {@link #execute()}. If no columns are
   * specified, implementations are free to choose which columns will be returned. If called
   * multiple times, only the columns requested in the final invocation will be returned.
   *
   * <p>The default implementation of this method is a no-op.
   *
   * @param columns the names of the columns to return
   * @return this {@code Statement}
   * @throws IllegalArgumentException if {@code columns}, or any item in {@code columns} is {@code
   *     null}
   */
  default Statement returnGeneratedValues(String... columns) {
    Assert.requireNonNull(columns, "columns must not be null");
    return this;
  }

  /**
   * Configures {@link Statement} to retrieve a fixed number of rows when fetching results from a
   * query instead deriving fetch size from back pressure. If called multiple times, only the fetch
   * size configured in the final invocation will be applied. If the value specified is zero, then
   * the hint is ignored.
   *
   * <p>The default implementation of this method is a no op and the default value is zero.
   *
   * @param rows the number of rows to fetch
   * @return this {@code Statement}
   */
  default Statement fetchSize(int rows) {
    return this;
  }
}
