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
package org.jooq.tools.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.function.Supplier;

/**
 * A default JDBC Statement implementation delegating all JDBC 4.0 calls to an internal delegate.
 *
 * @author Lukas Eder
 */
public class DefaultStatement extends JDBC41Statement implements Statement {

  private final Statement delegate;

  private final Connection creator;

  private final Supplier<? extends SQLException> errorIfUnsupported;

  public DefaultStatement(Statement delegate) {
    this(delegate, null, null);
  }

  public DefaultStatement(Statement delegate, Connection creator) {
    this(delegate, creator, null);
  }

  public DefaultStatement(
      Statement delegate, Connection creator, Supplier<? extends SQLException> errorIfUnsupported) {
    this.delegate = delegate;
    this.creator = creator;
    this.errorIfUnsupported = errorIfUnsupported;
  }

  public Statement getDelegate() throws SQLException {
    return getDelegateStatement();
  }

  public Statement getDelegateStatement() throws SQLException {
    if (delegate != null || errorIfUnsupported == null) return delegate;
    else throw errorIfUnsupported.get();
  }

  // ------------------------------------------------------------------------
  // XXX Auxiliary methods
  // ------------------------------------------------------------------------
  protected ResultSet wrap(ResultSet wrapped) {
    // [#8993] Some JDBC drivers produce null ResultSets where they shouldn't.
    // This method allows for passing along this null result value
    // rather than wrapping it, leading to unrelated NPEs later.
    return wrapped == null ? null : new DefaultResultSet(wrapped, this);
  }

  // ------------------------------------------------------------------------
  // XXX Executing the statement
  // ------------------------------------------------------------------------
  @Override
  public boolean execute(String sql) throws SQLException {
    return getDelegateStatement().execute(sql);
  }

  @Override
  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
    return getDelegateStatement().execute(sql, autoGeneratedKeys);
  }

  @Override
  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
    return getDelegateStatement().execute(sql, columnIndexes);
  }

  @Override
  public boolean execute(String sql, String[] columnNames) throws SQLException {
    return getDelegateStatement().execute(sql, columnNames);
  }

  @Override
  public int[] executeBatch() throws SQLException {
    return getDelegateStatement().executeBatch();
  }

  @Override
  public ResultSet executeQuery(String sql) throws SQLException {
    return wrap(getDelegateStatement().executeQuery(sql));
  }

  @Override
  public int executeUpdate(String sql) throws SQLException {
    return getDelegateStatement().executeUpdate(sql);
  }

  @Override
  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return getDelegateStatement().executeUpdate(sql, autoGeneratedKeys);
  }

  @Override
  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return getDelegateStatement().executeUpdate(sql, columnIndexes);
  }

  @Override
  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
    return getDelegateStatement().executeUpdate(sql, columnNames);
  }

  // ------------------------------------------------------------------------
  // XXX Other methods
  // ------------------------------------------------------------------------
  @Override
  public <T> T unwrap(Class<T> iface) throws SQLException {
    return getDelegateStatement().unwrap(iface);
  }

  @Override
  public boolean isWrapperFor(Class<?> iface) throws SQLException {
    return getDelegateStatement().isWrapperFor(iface);
  }

  @Override
  public void close() throws SQLException {
    getDelegateStatement().close();
  }

  @Override
  public int getMaxFieldSize() throws SQLException {
    return getDelegateStatement().getMaxFieldSize();
  }

  @Override
  public void setMaxFieldSize(int max) throws SQLException {
    getDelegateStatement().setMaxFieldSize(max);
  }

  @Override
  public int getMaxRows() throws SQLException {
    return getDelegateStatement().getMaxRows();
  }

  @Override
  public void setMaxRows(int max) throws SQLException {
    getDelegateStatement().setMaxRows(max);
  }

  @Override
  public void setEscapeProcessing(boolean enable) throws SQLException {
    getDelegateStatement().setEscapeProcessing(enable);
  }

  @Override
  public int getQueryTimeout() throws SQLException {
    return getDelegateStatement().getQueryTimeout();
  }

  @Override
  public void setQueryTimeout(int seconds) throws SQLException {
    getDelegateStatement().setQueryTimeout(seconds);
  }

  @Override
  public void cancel() throws SQLException {
    getDelegateStatement().cancel();
  }

  @Override
  public SQLWarning getWarnings() throws SQLException {
    return getDelegateStatement().getWarnings();
  }

  @Override
  public void clearWarnings() throws SQLException {
    getDelegateStatement().clearWarnings();
  }

  @Override
  public void setCursorName(String name) throws SQLException {
    getDelegateStatement().setCursorName(name);
  }

  @Override
  public ResultSet getResultSet() throws SQLException {
    return wrap(getDelegateStatement().getResultSet());
  }

  @Override
  public int getUpdateCount() throws SQLException {
    return getDelegateStatement().getUpdateCount();
  }

  @Override
  public boolean getMoreResults() throws SQLException {
    return getDelegateStatement().getMoreResults();
  }

  @Override
  public void setFetchDirection(int direction) throws SQLException {
    getDelegateStatement().setFetchDirection(direction);
  }

  @Override
  public int getFetchDirection() throws SQLException {
    return getDelegateStatement().getFetchDirection();
  }

  @Override
  public void setFetchSize(int rows) throws SQLException {
    getDelegateStatement().setFetchSize(rows);
  }

  @Override
  public int getFetchSize() throws SQLException {
    return getDelegateStatement().getFetchSize();
  }

  @Override
  public int getResultSetConcurrency() throws SQLException {
    return getDelegateStatement().getResultSetConcurrency();
  }

  @Override
  public int getResultSetType() throws SQLException {
    return getDelegateStatement().getResultSetType();
  }

  @Override
  public void addBatch(String sql) throws SQLException {
    getDelegateStatement().addBatch(sql);
  }

  @Override
  public void clearBatch() throws SQLException {
    getDelegateStatement().clearBatch();
  }

  @Override
  public Connection getConnection() throws SQLException {
    return creator != null ? creator : getDelegateStatement().getConnection();
  }

  @Override
  public boolean getMoreResults(int current) throws SQLException {
    return getDelegateStatement().getMoreResults(current);
  }

  @Override
  public ResultSet getGeneratedKeys() throws SQLException {
    return wrap(getDelegateStatement().getGeneratedKeys());
  }

  @Override
  public int getResultSetHoldability() throws SQLException {
    return getDelegateStatement().getResultSetHoldability();
  }

  @Override
  public boolean isClosed() throws SQLException {
    return getDelegateStatement().isClosed();
  }

  @Override
  public void setPoolable(boolean poolable) throws SQLException {
    getDelegateStatement().setPoolable(poolable);
  }

  @Override
  public boolean isPoolable() throws SQLException {
    return getDelegateStatement().isPoolable();
  }

  // ------------------------------------------------------------------------
  // JDBC 4.1
  // ------------------------------------------------------------------------
  @Override
  public void closeOnCompletion() throws SQLException {
    getDelegate().closeOnCompletion();
  }

  @Override
  public boolean isCloseOnCompletion() throws SQLException {
    return getDelegate().isCloseOnCompletion();
  }

  // ------------------------------------------------------------------------
  // JDBC 4.2
  // ------------------------------------------------------------------------
  @Override
  public long getLargeUpdateCount() throws SQLException {
    return getDelegate().getLargeUpdateCount();
  }

  @Override
  public void setLargeMaxRows(long max) throws SQLException {
    getDelegate().setLargeMaxRows(max);
  }

  @Override
  public long getLargeMaxRows() throws SQLException {
    return getDelegate().getLargeMaxRows();
  }

  @Override
  public long[] executeLargeBatch() throws SQLException {
    return getDelegate().executeLargeBatch();
  }

  @Override
  public long executeLargeUpdate(String sql) throws SQLException {
    return getDelegate().executeLargeUpdate(sql);
  }

  @Override
  public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
    return getDelegate().executeLargeUpdate(sql, autoGeneratedKeys);
  }

  @Override
  public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
    return getDelegate().executeLargeUpdate(sql, columnIndexes);
  }

  @Override
  public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
    return getDelegate().executeLargeUpdate(sql, columnNames);
  }

  // ------------------------------------------------------------------------
  // JDBC 4.3
  // ------------------------------------------------------------------------
  @Override
  public String enquoteLiteral(String val) throws SQLException {
    return getDelegate().enquoteLiteral(val);
  }

  @Override
  public String enquoteIdentifier(String identifier, boolean alwaysQuote) throws SQLException {
    return getDelegate().enquoteIdentifier(identifier, alwaysQuote);
  }

  @Override
  public boolean isSimpleIdentifier(String identifier) throws SQLException {
    return getDelegate().isSimpleIdentifier(identifier);
  }

  @Override
  public String enquoteNCharLiteral(String val) throws SQLException {
    return getDelegate().enquoteNCharLiteral(val);
  }
}
