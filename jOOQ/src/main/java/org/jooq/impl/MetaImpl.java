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
package org.jooq.impl;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.jooq.SQLDialect.DERBY;
import static org.jooq.SQLDialect.FIREBIRD;
import static org.jooq.SQLDialect.H2;
import static org.jooq.SQLDialect.HSQLDB;
import static org.jooq.SQLDialect.IGNITE;
import static org.jooq.SQLDialect.MARIADB;
import static org.jooq.SQLDialect.MYSQL;
import static org.jooq.SQLDialect.POSTGRES;
import static org.jooq.SQLDialect.SQLITE;
import static org.jooq.impl.AbstractNamed.findIgnoreCase;
import static org.jooq.impl.DSL.comment;
import static org.jooq.impl.DSL.condition;
import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.name;
import static org.jooq.impl.MetaSQL.M_SEQUENCES;
import static org.jooq.impl.MetaSQL.M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES;
import static org.jooq.impl.MetaSQL.M_UNIQUE_KEYS;
import static org.jooq.impl.SQLDataType.INTEGER;
import static org.jooq.impl.SQLDataType.VARCHAR;
import static org.jooq.impl.Tools.EMPTY_OBJECT;
import static org.jooq.impl.Tools.EMPTY_SORTFIELD;
import static org.jooq.impl.Tools.flatMap;
import static org.jooq.impl.Tools.map;
import static org.jooq.tools.StringUtils.defaultIfEmpty;
import static org.jooq.tools.StringUtils.defaultString;
import static org.jooq.tools.StringUtils.isEmpty;

import java.io.Serializable;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.jooq.Catalog;
import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.ConstraintEnforcementStep;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Meta;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.Schema;
import org.jooq.Sequence;
import org.jooq.SortField;
import org.jooq.Source;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.TableOptions.TableType;
import org.jooq.UniqueKey;
import org.jooq.conf.ParseUnknownFunctions;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.DataDefinitionException;
import org.jooq.exception.DataTypeException;
import org.jooq.exception.SQLDialectNotSupportedException;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.StringUtils;

/**
 * An implementation of the public {@link Meta} type.
 *
 * <p>This implementation implements {@link Serializable}, without taking care of properly
 * deserialising the referenced executor.
 *
 * @author Lukas Eder
 */
final class MetaImpl extends AbstractMeta {

  private static final JooqLogger log = JooqLogger.getLogger(MetaImpl.class);

  private static final Set<SQLDialect> INVERSE_SCHEMA_CATALOG =
      SQLDialect.supportedBy(MARIADB, MYSQL);

  private static final Set<SQLDialect> CURRENT_TIMESTAMP_COLUMN_DEFAULT =
      SQLDialect.supportedBy(MARIADB, MYSQL);

  private static final Set<SQLDialect> EXPRESSION_COLUMN_DEFAULT =
      SQLDialect.supportedBy(DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, POSTGRES, SQLITE);

  private static final Set<SQLDialect> NO_SUPPORT_SCHEMAS =
      SQLDialect.supportedBy(FIREBIRD, SQLITE);

  private static final Pattern P_SYSINDEX_DERBY = Pattern.compile("^(?i:SQL\\d{14,}).*$");

  private static final Pattern P_SYSINDEX_H2 =
      Pattern.compile("^(?i:PRIMARY_KEY_|UK_INDEX_|FK_INDEX_).*$");

  private static final Pattern P_SYSINDEX_HSQLDB =
      Pattern.compile("^(?i:SYS_IDX_(?:PK|UK|FK)_).*$");

  private static final Pattern P_SYSINDEX_SQLITE = Pattern.compile("^(?i:sqlite_autoindex_).*$");

  private final DatabaseMetaData databaseMetaData;

  private final boolean inverseSchemaCatalog;

  MetaImpl(Configuration configuration, DatabaseMetaData databaseMetaData) {
    super(configuration);
    this.databaseMetaData = databaseMetaData;
    this.inverseSchemaCatalog = INVERSE_SCHEMA_CATALOG.contains(dialect());
  }

  final boolean hasCatalog(Catalog catalog) {
    return catalog != null && !isEmpty(catalog.getName());
  }

  final <R> R catalogSchema(
      Catalog catalog, Schema schema, ThrowingBiFunction<String, String, R, SQLException> function)
      throws SQLException {
    return catalogSchema(
        catalog != null ? catalog.getName() : null,
        schema != null ? schema.getName() : null,
        function);
  }

  final <R> R catalogSchema(
      String catalog, String schema, ThrowingBiFunction<String, String, R, SQLException> function)
      throws SQLException {
    String c = defaultIfEmpty(catalog, null);
    String s = defaultIfEmpty(schema, null);
    // [#2760] MySQL JDBC confuses "catalog" and "schema"
    if (inverseSchemaCatalog) return function.apply(s, c);
    else return function.apply(c, s);
  }

  private final Result<Record> meta(
      ThrowingFunction<DatabaseMetaData, Result<Record>, SQLException> function) {
    if (databaseMetaData == null)
      return dsl().connectionResult(connection -> function.apply(connection.getMetaData()));
    try {
      return function.apply(databaseMetaData);
    } catch (SQLException e) {
      throw new DataAccessException("Error while running MetaFunction", e);
    }
  }

  @Override
  final List<Catalog> getCatalogs0() {
    List<Catalog> result = new ArrayList<>();
    // There should always be at least one (empty) catalog in a database
    if (result.isEmpty()) result.add(new MetaCatalog(""));
    return result;
  }

  final Table<?> lookupTable(Schema schema, String tableName) {
    switch (family()) {
        // [#10741] A workaround for SQLite's case insensitivity where the
        // case of declared vs referenced identifiers may differ
        // [#2656]  TODO: Solve this more thoroughly and globally
      case SQLITE:
        return findIgnoreCase(tableName, schema.getTables());
      default:
        return schema.getTable(tableName);
    }
  }

  @Override
  final List<Schema> getSchemas0() {
    return flatMap(getCatalogs(), c -> c.getSchemas());
  }

  @Override
  final List<Table<?>> getTables0() {
    return flatMap(getSchemas(), s -> s.getTables());
  }

  @Override
  final List<UniqueKey<?>> getPrimaryKeys0() {
    List<UniqueKey<?>> result = new ArrayList<>();
    for (Table<?> table : getTables()) {
      UniqueKey<?> pk = table.getPrimaryKey();
      if (pk != null) result.add(pk);
    }
    return result;
  }

  private final class MetaCatalog extends CatalogImpl {

    MetaCatalog(String name) {
      super(name);
    }

    @Override
    public final List<Schema> getSchemas() {
      List<Schema> result = new ArrayList<>();
      if (!inverseSchemaCatalog) {
        Result<Record> schemas =
            meta(
                meta -> {
                  // [#2681] Work around a flaw in the MySQL JDBC driver
                  // TABLE_SCHEM
                  return dsl().fetch(meta.getSchemas(), VARCHAR);
                });
        for (String name : schemas.getValues(0, String.class))
          result.add(new MetaSchema(name, MetaCatalog.this));
      } else // [#2760] MySQL JDBC confuses "catalog" and "schema"
      {
        Result<Record> schemas =
            meta(
                meta ->
                    dsl()
                        .fetch(
                            meta.getCatalogs(), // TABLE_CATALOG
                            SQLDataType.VARCHAR));
        for (String name : schemas.getValues(0, String.class))
          result.add(new MetaSchema(name, MetaCatalog.this));
      }
      // There should always be at least one (empty) schema in a database
      if (result.isEmpty()) result.add(new MetaSchema("", MetaCatalog.this));
      return result;
    }
  }

  private final class MetaSchema extends SchemaImpl {

    private transient volatile Map<Name, Result<Record>> columnCache;

    private transient volatile Map<Name, Result<Record>> ukCache;

    private transient volatile Map<Name, Result<Record>> sequenceCache;

    MetaSchema(String name, Catalog catalog) {
      super(name, catalog);
    }

    @Override
    public final synchronized List<Table<?>> getTables() {
      Result<Record> tables =
          meta(
              meta -> {
                String[] types;
                switch (family()) {
                    // [#3977] [#11255] PostgreSQL returns other object types, too
                  case POSTGRES:
                    types =
                        new String[] {
                          "FOREIGN TABLE",
                          "MATERIALIZED VIEW",
                          "SYSTEM_TABLE",
                          "SYSTEM_VIEW",
                          "TABLE",
                          "VIEW"
                        };
                    break;
                    // [#2323] SQLite JDBC drivers have a bug. They return other
                    // object types, too: https://bitbucket.org/xerial/sqlite-jdbc/issue/68
                  case SQLITE:
                    types = new String[] {"TABLE", "VIEW"};
                    break;
                  default:
                    types = null;
                    break;
                }
                try (ResultSet rs =
                    catalogSchema(
                        getCatalog(),
                        this,
                        (c, s) -> {
                          return meta.getTables(c, s, "%", types);
                        })) {
                  return dsl()
                      .fetch(
                          rs, // [#2681] Work around a flaw in the MySQL JDBC driver
                          // TABLE_CAT
                          SQLDataType.VARCHAR, // TABLE_SCHEM
                          SQLDataType.VARCHAR, // TABLE_NAME
                          SQLDataType.VARCHAR, // TABLE_TYPE
                          SQLDataType.VARCHAR, // REMARKS
                          SQLDataType.VARCHAR);
                }
              });
      return Tools.map(
          tables,
          table -> {
            String catalog = table.get(0, String.class);
            String schema = table.get(1, String.class);
            String name = table.get(2, String.class);
            String type = table.get(3, String.class);
            String remarks = table.get(4, String.class);
            // "TABLE","VIEW", "SYSTEM TABLE", "GLOBAL TEMPORARY","LOCAL TEMPORARY", "ALIAS",
            // "SYNONYM".
            TableType tableType =
                "VIEW".equals(type)
                    ? TableType.VIEW
                    : "SYSTEM_VIEW".equals(type)
                        ? TableType.VIEW
                        : "GLOBAL TEMPORARY".equals(type)
                            ? TableType.TEMPORARY
                            : "LOCAL TEMPORARY".equals(type)
                                ? TableType.TEMPORARY
                                : "TEMPORARY".equals(type)
                                    ? TableType.TEMPORARY
                                    : "MATERIALIZED VIEW".equals(type)
                                        ? TableType.MATERIALIZED_VIEW
                                        : TableType.TABLE;
            return new MetaTable(
                name,
                this,
                getColumns(catalog, schema, name),
                getUks(catalog, schema, name),
                remarks,
                tableType);
            // TODO: Find a more efficient way to do this
            // Result<Record> pkColumns = executor.fetch(meta().getPrimaryKeys(catalog, schema,
            // name))
            // .sortAsc("KEY_SEQ");
            //
            // result.add(new MetaTable(name, this, columnCache.get(name)));
          });
    }

    private final Result<Record> getUks(String catalog, String schema, String table) {
      if (ukCache == null)
        if (family() == SQLITE) initUksSQLite(catalog, schema);
        else initUks(catalog, schema);
      if (ukCache != null) return ukCache.get(name(catalog, schema, table));
      else return null;
    }

    private final void initUks(String catalog, String schema) {
      String sql = M_UNIQUE_KEYS(family());
      if (sql != null) {
        Result<Record> result =
            meta(
                meta ->
                    DSL.using(meta.getConnection(), family())
                        .resultQuery(
                            sql,
                            NO_SUPPORT_SCHEMAS.contains(dialect())
                                ? EMPTY_OBJECT
                                : inverseSchemaCatalog
                                    ? new Object[] {catalog}
                                    : new Object[] {schema})
                        .fetch());
        // TODO Support catalogs as well
        Map<Record, Result<Record>> groups =
            result.intoGroups(new Field[] {result.field(0), result.field(1), result.field(2)});
        ukCache = new LinkedHashMap<>();
        groups.forEach(
            (k, v) -> {
              ukCache.put(
                  name(
                      catalog == null ? null : k.get(0, String.class),
                      k.get(1, String.class),
                      k.get(2, String.class)),
                  v);
            });
      }
    }

    private void initUksSQLite(String catalog, String schema) {
      ukCache = new LinkedHashMap<>();
      dsl()
          .resultQuery(
              (""
                  + "select m.tbl_name, m.sql\n"
                  + "from sqlite_master as m\n"
                  + "where m.type = 'table'\n"
                  + "and exists (\n"
                  + "  select 1\n"
                  + "  from pragma_index_list(m.name) as il\n"
                  + "  where il.origin = 'u'\n"
                  + ")\n"
                  + "order by m.tbl_name\n"
                  + ""))
          .fetchMap(field("tbl_name", VARCHAR), field("sql", VARCHAR))
          .forEach(
              (table, sql) -> {
                try {
                  Field<String> fCatalogName = field("catalog_name", VARCHAR);
                  Field<String> fSchemaName = field("schema_name", VARCHAR);
                  Field<String> fTableName = field("table_name", VARCHAR);
                  Field<String> fConstraintName = field("constraint_name", VARCHAR);
                  Field<String> fColumnName = field("column_name", VARCHAR);
                  Field<Integer> fSequenceNo = field("sequence_no", INTEGER);
                  Field<?>[] fields = {
                    fCatalogName, fSchemaName, fTableName, fConstraintName, fColumnName, fSequenceNo
                  };
                  for (Table<?> t : dsl().meta(Source.of(sql)).getTables(table)) {
                    Result<Record> result = dsl().newResult(fields);
                    int i = 0;
                    for (UniqueKey<?> uk : t.getUniqueKeys())
                      for (Field<?> ukField : uk.getFields())
                        result.add(
                            dsl()
                                .newRecord(
                                    fCatalogName,
                                    fSchemaName,
                                    fTableName,
                                    fConstraintName,
                                    fColumnName,
                                    fSequenceNo)
                                .values(
                                    catalog, schema, table, uk.getName(), ukField.getName(), i++));
                    ukCache.put(name(catalog, schema, table), result);
                  }
                } catch (ParserException | DataDefinitionException e) {
                  log.info("Cannot parse or interpret sql for table " + table + ": " + sql, e);
                }
              });
    }

    @SuppressWarnings("unchecked")
    private final Result<Record> getColumns(String catalog, String schema, String table) {
      // SQLite JDBC's DatabaseMetaData.getColumns() can only return a single
      // table's columns
      if (columnCache == null && family() != SQLITE) {
        Result<Record> columns = getColumns0(catalog, schema, "%");
        // TABLE_CAT
        Field<String> tableCat = (Field<String>) columns.field(0);
        // TABLE_SCHEM
        Field<String> tableSchem = (Field<String>) columns.field(1);
        // TABLE_NAME
        Field<String> tableName = (Field<String>) columns.field(2);
        Map<Record, Result<Record>> groups =
            columns.intoGroups(new Field[] {tableCat, tableSchem, tableName});
        columnCache = new LinkedHashMap<>();
        groups.forEach(
            (k, v) ->
                columnCache.put(name(k.get(tableCat), k.get(tableSchem), k.get(tableName)), v));
      }
      if (columnCache != null) return columnCache.get(name(catalog, schema, table));
      else return getColumns0(catalog, schema, table);
    }

    private final Result<Record> getColumns0(
        final String catalog, final String schema, final String table) {
      return meta(
          meta -> {
            try (ResultSet rs =
                catalogSchema(catalog, schema, (c, s) -> meta.getColumns(c, s, table, "%"))) {
              // Work around a bug in the SQL Server JDBC driver by
              // coercing data types to the expected types
              // The bug was reported here:
              // https://connect.microsoft.com/SQLServer/feedback/details/775425/jdbc-4-0-databasemetadata-getcolumns-returns-a-resultset-whose-resultsetmetadata-is-inconsistent
              // [#9740] TODO: Make this call lenient with respect to
              // column count, filling unavailable columns with
              // default values.
              return rs.getMetaData().getColumnCount() < GET_COLUMNS_EXTENDED.length
                  ? dsl().fetch(rs, GET_COLUMNS_SHORT)
                  : dsl().fetch(rs, GET_COLUMNS_EXTENDED);
            }
          });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Sequence<?>> getSequences() {
      Result<Record> result = getSequences0();
      return result != null
          ? map(
              result,
              r ->
                  Internal.createSequence(
                      r.get(2, String.class),
                      this,
                      (DataType<Number>)
                          DefaultDataType.getDataType(
                              family(),
                              r.get(3, String.class),
                              r.get(4, int.class),
                              r.get(5, int.class),
                              !FALSE.equals(settings().isForceIntegerTypesOnZeroScaleDecimals())),
                      r.get(6, Long.class),
                      r.get(7, Long.class),
                      r.get(8, Long.class),
                      r.get(9, Long.class),
                      r.get(10, boolean.class),
                      r.get(11, Long.class)))
          : new ArrayList<>();
    }

    private final Result<Record> getSequences0() {
      if (sequenceCache == null) {
        final String sql =
            TRUE.equals(settings().isMetaIncludeSystemSequences())
                ? M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES(family())
                : M_SEQUENCES(family());
        if (sql != null) {
          Result<Record> result =
              meta(
                  meta ->
                      DSL.using(meta.getConnection(), family())
                          .resultQuery(sql, MetaSchema.this.getName())
                          .fetch());
          // TODO Support catalogs as well
          Map<Record, Result<Record>> groups =
              result.intoGroups(new Field[] {result.field(0), result.field(1)});
          sequenceCache = new LinkedHashMap<>();
          groups.forEach(
              (k, v) -> sequenceCache.put(name(k.get(0, String.class), k.get(1, String.class)), v));
        }
      }
      if (sequenceCache != null)
        return sequenceCache.get(
            name(MetaSchema.this.getCatalog().getName(), MetaSchema.this.getName()));
      else return null;
    }
  }

  // Columns available from JDBC 3.0+
  private static final Class<?>[] GET_COLUMNS_SHORT = { // TABLE_CAT
    String.class, // TABLE_SCHEM
    String.class, // TABLE_NAME
    String.class, // COLUMN_NAME
    String.class, // DATA_TYPE
    int.class, // TYPE_NAME
    String.class, // COLUMN_SIZE
    int.class, // BUFFER_LENGTH
    String.class, // DECIMAL_DIGITS
    int.class, // NUM_PREC_RADIX
    int.class, // NULLABLE
    int.class, // REMARKS
    String.class, // COLUMN_DEF
    String.class
  };

  // Columns available from JDBC 4.0+
  private static final Class<?>[] GET_COLUMNS_EXTENDED = { // TABLE_CAT
    String.class, // TABLE_SCHEM
    String.class, // TABLE_NAME
    String.class, // COLUMN_NAME
    String.class, // DATA_TYPE
    int.class, // TYPE_NAME
    String.class, // COLUMN_SIZE
    int.class, // BUFFER_LENGTH
    String.class, // DECIMAL_DIGITS
    int.class, // NUM_PREC_RADIX
    int.class, // NULLABLE
    int.class, // REMARKS
    String.class, // COLUMN_DEF
    String.class, // SQL_DATA_TYPE
    int.class, // SQL_DATETIME_SUB
    int.class, // CHAR_OCTET_LENGTH
    int.class, // ORDINAL_POSITION
    int.class, // IS_NULLABLE
    String.class, // SCOPE_CATALOG
    String.class, // SCOPE_SCHEMA
    String.class, // SCOPE_TABLE
    String.class, // SOURCE_DATA_TYPE
    String.class, // IS_AUTOINCREMENT
    String.class
  };

  private final class MetaTable extends TableImpl<Record> {

    private final Result<Record> uks;

    MetaTable(
        String name,
        Schema schema,
        Result<Record> columns,
        Result<Record> uks,
        String remarks,
        TableType tableType) {
      super(
          name(name), schema, null, null, null, null, comment(remarks), TableOptions.of(tableType));
      // Possible scenarios for columns being null:
      // - The "table" is in fact a SYNONYM
      if (columns != null) initColumns(columns);
      this.uks = uks;
    }

    @Override
    public final List<Index> getIndexes() {
      Result<Record> result =
          removeSystemIndexes(
              meta(
                  meta -> {
                    try (ResultSet rs =
                        catalogSchema(
                            getCatalog(),
                            getSchema(),
                            (c, s) -> meta.getIndexInfo(c, s, getName(), false, true))) {
                      return dsl()
                          .fetch(
                              rs, // TABLE_CAT
                              String.class, // TABLE_SCHEM
                              String.class, // TABLE_NAME
                              String.class, // NON_UNIQUE
                              boolean.class, // INDEX_QUALIFIER
                              String.class, // INDEX_NAME
                              String.class, // TYPE
                              int.class, // ORDINAL_POSITION
                              int.class, // COLUMN_NAME
                              String.class, // ASC_OR_DESC
                              String.class, // CARDINALITY
                              long.class, // PAGES
                              long.class, // FILTER_CONDITION
                              String.class);
                    }
                  }));
      // Sort by INDEX_NAME (5), ORDINAL_POSITION (7)
      result.sortAsc(7).sortAsc(5);
      return createIndexes(result);
    }

    private final Result<Record> removeSystemIndexes(Result<Record> result) {
      if (TRUE.equals(settings().isMetaIncludeSystemIndexes())) return result;
      // [#8655] [#9627] TODO Re-use more precise, dialect-specific logic from jOOQ-meta's
      // Database::getIncludeSystemReferences
      Set<String> constraints = new HashSet<>();
      for (UniqueKey<?> key : getKeys()) constraints.add(key.getName());
      for (ForeignKey<?, ?> key : getReferences()) constraints.add(key.getName());
      Iterator<Record> it = result.iterator();
      while (it.hasNext()) {
        String indexName = it.next().get(5, String.class);
        // It's generally a good heuristic to assume an index that shares the name of the constraint
        // is system generated
        if (constraints.contains(indexName)) it.remove();
        else
          switch (family()) {
            case DERBY:
              if (P_SYSINDEX_DERBY.matcher(indexName).matches()) it.remove();
              break;
            case H2:
              if (P_SYSINDEX_H2.matcher(indexName).matches()) it.remove();
              break;
            case HSQLDB:
              if (P_SYSINDEX_HSQLDB.matcher(indexName).matches()) it.remove();
              break;
            case SQLITE:
              if (P_SYSINDEX_SQLITE.matcher(indexName).matches()) it.remove();
              break;
          }
      }
      return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final List<UniqueKey<Record>> getUniqueKeys() {
      List<UniqueKey<Record>> result = new ArrayList<>();
      if (uks != null) {
        Map<String, Result<Record>> groups = uks.intoGroups((Field<String>) uks.field(3));
        groups.forEach(
            (k, v) -> {
              v.sortAsc(5);
              result.add(createUniqueKey(v, 4, 3, false));
            });
      }
      return result;
    }

    @Override
    public final UniqueKey<Record> getPrimaryKey() {
      Result<Record> result =
          meta(
              meta -> {
                try (ResultSet rs =
                    catalogSchema(
                        getCatalog(),
                        getSchema(),
                        (c, s) -> meta.getPrimaryKeys(c, s, getName()))) {
                  return dsl()
                      .fetch(
                          rs, // TABLE_CAT
                          String.class, // TABLE_SCHEM
                          String.class, // TABLE_NAME
                          String.class, // COLUMN_NAME
                          String.class, // KEY_SEQ
                          int.class, // PK_NAME
                          String.class);
                }
              });
      // Sort by KEY_SEQ
      result.sortAsc(4);
      return createUniqueKey(result, 3, 5, true);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final List<ForeignKey<Record, ?>> getReferences() {
      Result<Record> result =
          meta(
              meta -> {
                try (ResultSet rs =
                    catalogSchema(
                        getCatalog(),
                        getSchema(),
                        (c, s) -> meta.getImportedKeys(c, s, getName()))) {
                  return dsl()
                      .fetch(
                          rs, // PKTABLE_CAT
                          String.class, // PKTABLE_SCHEM
                          String.class, // PKTABLE_NAME
                          String.class, // PKCOLUMN_NAME
                          String.class, // FKTABLE_CAT
                          String.class, // FKTABLE_SCHEM
                          String.class, // FKTABLE_NAME
                          String.class, // FKCOLUMN_NAME
                          String.class, // KEY_SEQ
                          Short.class, // UPDATE_RULE
                          Short.class, // DELETE_RULE
                          Short.class, // FK_NAME
                          String.class, // PK_NAME
                          String.class);
                }
              });
      Map<Record, Result<Record>> groups =
          result.intoGroups(
              new Field[] {
                result.field(inverseSchemaCatalog ? 1 : 0),
                result.field(inverseSchemaCatalog ? 0 : 1),
                result.field(2),
                result.field(11),
                result.field(12)
              });
      Map<Name, Schema> schemas = new HashMap<>();
      for (Schema schema : getSchemas()) schemas.put(schema.getQualifiedName(), schema);
      List<ForeignKey<Record, ?>> references = new ArrayList<>(groups.size());
      groups.forEach(
          (k, v) -> {
            // [#7377] The schema may be null instead of "" in some dialects
            // [#12243] [#12240] Not all dialects have catalog support
            Schema schema =
                schemas.get(
                    hasCatalog(getCatalog())
                        ? name(
                            defaultString(k.get(0, String.class)),
                            defaultString(k.get(1, String.class)))
                        : name(defaultString(k.get(1, String.class))));
            String fkName = k.get(3, String.class);
            String pkName = k.get(4, String.class);
            Table<Record> pkTable = (Table<Record>) lookupTable(schema, k.get(2, String.class));
            TableField<Record, ?>[] pkFields = new TableField[v.size()];
            TableField<Record, ?>[] fkFields = new TableField[v.size()];
            for (int i = 0; i < v.size(); i++) {
              Record record = v.get(i);
              String pkFieldName = record.get(3, String.class);
              String fkFieldName = record.get(7, String.class);
              pkFields[i] = (TableField<Record, ?>) pkTable.field(pkFieldName);
              fkFields[i] = (TableField<Record, ?>) field(fkFieldName);
              // [#2656] TODO: Find a more generally reusable way to perform case insensitive
              // lookups
              if (pkFields[i] == null)
                if ((pkFields[i] = lookup(pkTable, pkFieldName)) == null) return;
              if (fkFields[i] == null)
                if ((fkFields[i] = lookup(this, fkFieldName)) == null) return;
            }
            references.add(
                new ReferenceImpl<>(
                    this,
                    name(fkName),
                    fkFields, // TODO: Can we know whether it is a PK or UK?
                    new MetaUniqueKey(pkTable, pkName, pkFields, true),
                    pkFields,
                    true));
          });
      return references;
    }

    @SuppressWarnings("unchecked")
    private final TableField<Record, ?> lookup(Table<?> table, String fieldName) {
      for (Field<?> field : table.fields())
        if (field.getName().equalsIgnoreCase(fieldName)) return (TableField<Record, ?>) field;
      log.info("Could not look up key field : " + fieldName + " in table : " + table);
      return null;
    }

    @SuppressWarnings("unchecked")
    private final UniqueKey<Record> createUniqueKey(
        Result<Record> result, int columnName, int keyName, boolean isPrimary) {
      if (result.size() > 0) {
        TableField<Record, ?>[] f = new TableField[result.size()];
        for (int i = 0; i < f.length; i++) {
          String name = result.get(i).get(columnName, String.class);
          f[i] = (TableField<Record, ?>) field(name);
          // [#5097] Work around a bug in the Xerial JDBC driver for SQLite
          if (f[i] == null && family() == SQLITE)
            // [#2656] Use native support for case-insensitive column
            // lookup, once this is implemented
            for (Field<?> field : fields())
              if (field.getName().equalsIgnoreCase(name)) f[i] = (TableField<Record, ?>) field;
        }
        String indexName = result.get(0).get(keyName, String.class);
        return new MetaUniqueKey(this, indexName, f, isPrimary);
      } else {
        return null;
      }
    }

    private final List<Index> createIndexes(Result<Record> result) {
      List<Index> indexes = new ArrayList<>();
      List<SortField<?>> sortFields = new ArrayList<>();
      String previousIndexName = null;
      Name name = null;
      Condition where = null;
      boolean unique = false;
      for (int i = 0; i < result.size(); i++) {
        Record record = result.get(i);
        // INDEX_NAME
        String indexName = record.get(5, String.class);
        if (indexName == null) continue;
        if (!indexName.equals(previousIndexName)) {
          previousIndexName = indexName;
          sortFields.clear();
          name =
              name( // TABLE_CAT
                  record.get(0, String.class), // TABLE_SCHEM
                  record.get(1, String.class),
                  indexName);
          // FILTER_CONDITION
          String filter = record.get(12, String.class);
          where = !StringUtils.isBlank(filter) ? condition(filter) : null;
          // NON_UNIQUE
          unique = !record.get(3, boolean.class);
        }
        String columnName = record.get(8, String.class);
        // COLUMN_NAME
        Field<?> field = field(columnName);
        if (field == null) field = DSL.field(columnName);
        // ASC_OR_DESC
        boolean desc = "D".equalsIgnoreCase(record.get(9, String.class));
        sortFields.add(desc ? field.desc() : field.asc());
        if (i + 1 == result.size()
            || !result.get(i + 1).get(5, String.class).equals(previousIndexName))
          indexes.add(
              new IndexImpl(name, this, sortFields.toArray(EMPTY_SORTFIELD), where, unique));
      }
      return indexes;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private final void initColumns(Result<Record> columns) {
      boolean hasAutoIncrement = false;
      for (Record column : columns) {
        // COLUMN_NAME
        String columnName = column.get(3, String.class);
        // TYPE_NAME
        String typeName = column.get(5, String.class);
        // COLUMN_SIZE
        int precision = column.get(6, int.class);
        // DECIMAL_DIGITS
        int scale = column.get(8, int.class);
        // NULLABLE
        int nullable = column.get(10, int.class);
        // REMARKS
        String remarks = column.get(11, String.class);
        // COLUMN_DEF
        String defaultValue = column.get(12, String.class);
        // [#10817] Some dialects may produce NULL (the expression) rather than NULL (the value)
        if ("null".equalsIgnoreCase(defaultValue)) defaultValue = null;
        boolean isAutoIncrement =
            column.size() >= 23
                ? // IS_AUTOINCREMENT
                column.get(22, boolean.class)
                : false;
        // TODO: Exception handling should be moved inside SQLDataType
        DataType type;
        try {
          type =
              DefaultDataType.getDataType(
                  family(),
                  typeName,
                  precision,
                  scale,
                  !FALSE.equals(settings().isForceIntegerTypesOnZeroScaleDecimals()));
        } catch (SQLDialectNotSupportedException e) {
          type = SQLDataType.OTHER;
        }
        // [#10207] Ignore secondary identity columns, as allowed e.g. in PostgreSQL
        if (isAutoIncrement)
          if (!hasAutoIncrement) type = type.identity(hasAutoIncrement = isAutoIncrement);
          else
            log.info(
                "Multiple identities",
                "jOOQ does not support tables with multiple identities. Identity is ignored on column: "
                    + columnName);
        if (nullable == DatabaseMetaData.columnNoNulls) type = type.nullable(false);
        // [#6883] Default values may be present
        if (!isAutoIncrement && !StringUtils.isEmpty(defaultValue)) {
          try {
            // [#7194] [#8469] Some databases report all default values as expressions, not as
            // values
            if (EXPRESSION_COLUMN_DEFAULT.contains(dialect())) {
              if (FALSE.equals(settings().isParseMetaDefaultExpressions())) {
                type = type.defaultValue(DSL.field(defaultValue, type));
              } else {
                try {
                  type =
                      type.defaultValue(
                          dsl()
                              .configuration()
                              .deriveSettings(
                                  s -> s.withParseUnknownFunctions(ParseUnknownFunctions.IGNORE))
                              .dsl()
                              .parser()
                              .parseField(defaultValue));
                } catch (ParserException e) {
                  log.info("Cannot parse default expression: " + defaultValue, e);
                  type = type.defaultValue(DSL.field(defaultValue, type));
                }
              }
            } else // [#5574] MySQL mixes constant value expressions with other column expressions
            // here
            if (CURRENT_TIMESTAMP_COLUMN_DEFAULT.contains(dialect())
                && "CURRENT_TIMESTAMP".equalsIgnoreCase(defaultValue))
              type = type.defaultValue(DSL.field(defaultValue, type));
            else type = type.defaultValue(DSL.inline(defaultValue, type));
          } // [#8469] Rather than catching exceptions after conversions, we should use the
          // parser to parse default values, if they're expressions
          catch (DataTypeException e) {
            log.warn(
                "Default value",
                "Could not load default value: " + defaultValue + " for type: " + type,
                e);
          }
        }
        createField(name(columnName), type, this, remarks);
      }
    }
  }

  private final class MetaUniqueKey extends AbstractKey<Record> implements UniqueKey<Record> {

    private final boolean isPrimary;

    MetaUniqueKey(
        Table<Record> table, String name, TableField<Record, ?>[] fields, boolean isPrimary) {
      super(table, name == null ? null : name(name), fields, true);
      this.isPrimary = isPrimary;
    }

    @Override
    public final boolean isPrimary() {
      return isPrimary;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final List<ForeignKey<?, Record>> getReferences() {
      Result<Record> result =
          meta(
              meta -> {
                try (ResultSet rs =
                    catalogSchema(
                        getTable().getCatalog(),
                        getTable().getSchema(),
                        (c, s) -> meta.getExportedKeys(c, s, getTable().getName()))) {
                  return dsl()
                      .fetch(
                          rs, // PKTABLE_CAT
                          String.class, // PKTABLE_SCHEM
                          String.class, // PKTABLE_NAME
                          String.class, // PKCOLUMN_NAME
                          String.class, // FKTABLE_CAT
                          String.class, // FKTABLE_SCHEM
                          String.class, // FKTABLE_NAME
                          String.class, // FKCOLUMN_NAME
                          String.class, // KEY_SEQ
                          Short.class, // UPDATE_RULE
                          Short.class, // DELETE_RULE
                          Short.class, // FK_NAME
                          String.class, // PK_NAME
                          String.class);
                }
              });
      Map<Record, Result<Record>> groups =
          result.intoGroups(
              new Field[] {
                result.field(inverseSchemaCatalog ? 5 : 4),
                result.field(inverseSchemaCatalog ? 4 : 5),
                result.field(6),
                result.field(11),
                result.field(12)
              });
      Map<String, Schema> schemas = new HashMap<>();
      for (Schema schema : getSchemas()) schemas.put(schema.getName(), schema);
      List<ForeignKey<?, Record>> references = new ArrayList<>(groups.size());
      groups.forEach(
          (k, v) -> {
            // [#7377] The schema may be null instead of "" in some dialects
            Schema schema = schemas.get(defaultString(k.get(1, String.class)));
            Table<Record> fkTable = (Table<Record>) lookupTable(schema, k.get(2, String.class));
            references.add(
                new ReferenceImpl<>(
                    fkTable,
                    name(k.get(3, String.class)),
                    map(
                        v,
                        f -> (TableField<Record, ?>) fkTable.field(f.get(7, String.class)),
                        TableField[]::new),
                    this,
                    map(
                        v,
                        f -> (TableField<Record, ?>) getTable().field(f.get(3, String.class)),
                        TableField[]::new),
                    true));
          });
      return references;
    }

    @Override
    final ConstraintEnforcementStep constraint0() {
      if (isPrimary()) return DSL.constraint(getName()).primaryKey(getFieldsArray());
      else return DSL.constraint(getName()).unique(getFieldsArray());
    }
  }

  @Override
  public String toString() {
    // [#9428] Prevent long running toString() calls
    return "MetaImpl";
  }
}
