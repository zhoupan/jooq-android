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
package org.jooq.meta.hsqldb.information_schema.tables;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.meta.hsqldb.information_schema.InformationSchema;

/** the visible columns of each accessible table defined within this database */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SystemColumns extends TableImpl<Record> {

  private static final long serialVersionUID = -15265708;

  /** The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS</code> */
  public static final SystemColumns SYSTEM_COLUMNS = new SystemColumns();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.TABLE_CAT</code>. catalog in which the table
   * containing the column is defined
   */
  public final TableField<Record, String> TABLE_CAT =
      createField(
          DSL.name("TABLE_CAT"),
          SQLDataType.VARCHAR(128),
          this,
          "catalog in which the table containing the column is defined");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.TABLE_SCHEM</code>. schema in which the
   * table containing the column is defined
   */
  public final TableField<Record, String> TABLE_SCHEM =
      createField(
          DSL.name("TABLE_SCHEM"),
          SQLDataType.VARCHAR(128),
          this,
          "schema in which the table containing the column is defined");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.TABLE_NAME</code>. simple name of the table
   * containing the column
   */
  public final TableField<Record, String> TABLE_NAME =
      createField(
          DSL.name("TABLE_NAME"),
          SQLDataType.VARCHAR(128),
          this,
          "simple name of the table containing the column");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.COLUMN_NAME</code>. simple name of the
   * column
   */
  public final TableField<Record, String> COLUMN_NAME =
      createField(
          DSL.name("COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "simple name of the column");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.DATA_TYPE</code>. SQL data type. This may be
   * a java.sql.Types data type, a SQL 200n data type or an HSQLDB-specific data type. For datetime
   * or interval data types, this column returns the concise data type (such as SQL_­TYPE_­TIME or
   * SQL_­INTERVAL_­YEAR_­TO_­MONTH).
   */
  public final TableField<Record, Short> DATA_TYPE =
      createField(
          DSL.name("DATA_TYPE"),
          SQLDataType.SMALLINT,
          this,
          "SQL data type.  This may be a java.sql.Types data type, a SQL 200n data type or an HSQLDB-specific data type.  For datetime or interval data types, this column returns the concise data type (such as SQL_­TYPE_­TIME or SQL_­INTERVAL_­YEAR_­TO_­MONTH).");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.TYPE_NAME</code>. the HSQLDB-specific data
   * type name; this is the canonical name used in CREATE TABLE and ALTER TABLE statements.
   */
  public final TableField<Record, String> TYPE_NAME =
      createField(
          DSL.name("TYPE_NAME"),
          SQLDataType.VARCHAR(128),
          this,
          "the HSQLDB-specific data type name; this is the canonical name used in CREATE TABLE and ALTER TABLE statements.");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.COLUMN_SIZE</code>. precision for number
   * types; length for sized types; NULL if not applicable
   */
  public final TableField<Record, Integer> COLUMN_SIZE =
      createField(
          DSL.name("COLUMN_SIZE"),
          SQLDataType.INTEGER,
          this,
          "precision for number types; length for sized types; NULL if not applicable");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.BUFFER_LENGTH</code>. The maximum length in
   * bytes of data, if definitely known, that would be transferred to a buffer on a fetch operation.
   * For numeric data, this size may be different than the size of the data stored on the data
   * source. This value is the same as the COLUMN_SIZE column for binary data. This value is the
   * twice the COLUMN_SIZE column for character data. If the actual value is larger than can be
   * represented in an INTEGER column value, this is NULL.
   */
  public final TableField<Record, Integer> BUFFER_LENGTH =
      createField(
          DSL.name("BUFFER_LENGTH"),
          SQLDataType.INTEGER,
          this,
          "The maximum length in bytes of data, if definitely known, that would be transferred to a buffer on a fetch operation.  For numeric data, this size may be different than the size of the data stored on the data source.  This value is the same as the COLUMN_SIZE column for binary data. This value is the twice the COLUMN_SIZE column for character data.  If the actual value is larger than can be represented in an INTEGER column value, this is NULL.");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.DECIMAL_DIGITS</code>. # of fractional
   * digits (scale) for number types
   */
  public final TableField<Record, Integer> DECIMAL_DIGITS =
      createField(
          DSL.name("DECIMAL_DIGITS"),
          SQLDataType.INTEGER,
          this,
          "# of fractional digits (scale) for number types");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.NUM_PREC_RADIX</code>. Radix of reported
   * numeric precision (i.e. base of number types)
   */
  public final TableField<Record, Integer> NUM_PREC_RADIX =
      createField(
          DSL.name("NUM_PREC_RADIX"),
          SQLDataType.INTEGER,
          this,
          "Radix of reported numeric precision (i.e. base of number types)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.NULLABLE</code>. is NULL allowed?: {
   * columnNoNulls (maybe not), columnNullable (definitely), columnNullableUnknown }
   */
  public final TableField<Record, Integer> NULLABLE =
      createField(
          DSL.name("NULLABLE"),
          SQLDataType.INTEGER,
          this,
          "is NULL allowed?: { columnNoNulls (maybe not), columnNullable (definitely), columnNullableUnknown }");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.REMARKS</code>. explanitory comment
   * describing the column (may be NULL)
   */
  public final TableField<Record, String> REMARKS =
      createField(
          DSL.name("REMARKS"),
          SQLDataType.VARCHAR(65536),
          this,
          "explanitory comment describing the column (may be NULL)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.COLUMN_DEF</code>. default value (may be
   * NULL)
   */
  public final TableField<Record, String> COLUMN_DEF =
      createField(
          DSL.name("COLUMN_DEF"), SQLDataType.VARCHAR(65536), this, "default value (may be NULL)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.SQL_DATA_TYPE</code>. The value of the SQL
   * data type as it would appear in the SQL CLI SQL_DESC_TYPE field of the SQLDA.
   */
  public final TableField<Record, Integer> SQL_DATA_TYPE =
      createField(
          DSL.name("SQL_DATA_TYPE"),
          SQLDataType.INTEGER,
          this,
          "The value of the SQL data type as it would appear in the SQL CLI SQL_DESC_TYPE field of the SQLDA.");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.SQL_DATETIME_SUB</code>. When the value of
   * SQL_DATA_TYPE is SQL_DATETIME or SQL_INTERVAL, this column contains the datetime/interval
   * subcode. For data types other than datetime and interval, this column is NULL.
   */
  public final TableField<Record, Integer> SQL_DATETIME_SUB =
      createField(
          DSL.name("SQL_DATETIME_SUB"),
          SQLDataType.INTEGER,
          this,
          "When the value of SQL_DATA_TYPE is SQL_DATETIME or SQL_INTERVAL, this column contains the datetime/interval subcode.  For data types other than datetime and interval, this column is NULL.");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.CHAR_OCTET_LENGTH</code>. for char types,
   * the maximum number of bytes in the column, if the value can be represented as an INTEGER column
   * value, else NULL
   */
  public final TableField<Record, Integer> CHAR_OCTET_LENGTH =
      createField(
          DSL.name("CHAR_OCTET_LENGTH"),
          SQLDataType.INTEGER,
          this,
          "for char types, the maximum number of bytes in the column, if the value can be represented as an INTEGER column value, else NULL");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.ORDINAL_POSITION</code>. index of column in
   * table (starting at 1)
   */
  public final TableField<Record, Integer> ORDINAL_POSITION =
      createField(
          DSL.name("ORDINAL_POSITION"),
          SQLDataType.INTEGER,
          this,
          "index of column in table (starting at 1)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.IS_NULLABLE</code>. is column nullable?: {
   * YES (might allow) | NO (definitely not) | '' (unknown) }
   */
  public final TableField<Record, String> IS_NULLABLE =
      createField(
          DSL.name("IS_NULLABLE"),
          SQLDataType.VARCHAR(3),
          this,
          "is column nullable?: { YES (might allow) | NO (definitely not)  | '' (unknown) }");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.SCOPE_CATALOG</code>. */
  public final TableField<Record, String> SCOPE_CATALOG =
      createField(DSL.name("SCOPE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.SCOPE_SCHEMA</code>. schema of table that is
   * the scope of a reference attribute (NULL if the DATA_TYPE isn't REF)
   */
  public final TableField<Record, String> SCOPE_SCHEMA =
      createField(
          DSL.name("SCOPE_SCHEMA"),
          SQLDataType.VARCHAR(128),
          this,
          "schema of table that is the scope of a reference attribute (NULL if the DATA_TYPE isn't REF)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.SCOPE_TABLE</code>. table name that this the
   * scope of a reference attribute (NULL if the DATA_TYPE isn't REF)
   */
  public final TableField<Record, String> SCOPE_TABLE =
      createField(
          DSL.name("SCOPE_TABLE"),
          SQLDataType.VARCHAR(128),
          this,
          "table name that this the scope of a reference attribute (NULL if the DATA_TYPE isn't REF)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.SOURCE_DATA_TYPE</code>. source type of a
   * distinct type or user-generated Ref type, SQL type from DITypes (NULL if DATA_TYPE isn't
   * DISTINCT or user-generated REF)
   */
  public final TableField<Record, Short> SOURCE_DATA_TYPE =
      createField(
          DSL.name("SOURCE_DATA_TYPE"),
          SQLDataType.SMALLINT,
          this,
          "source type of a distinct type or user-generated Ref type, SQL type from DITypes (NULL if DATA_TYPE isn't DISTINCT or user-generated REF)");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.IS_AUTOINCREMENT</code>. */
  public final TableField<Record, String> IS_AUTOINCREMENT =
      createField(DSL.name("IS_AUTOINCREMENT"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS.IS_GENERATEDCOLUMN</code>. */
  public final TableField<Record, String> IS_GENERATEDCOLUMN =
      createField(DSL.name("IS_GENERATEDCOLUMN"), SQLDataType.VARCHAR(3), this, "");

  private SystemColumns(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private SystemColumns(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("the visible columns of each accessible table defined within this database"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS</code> table reference */
  public SystemColumns(String alias) {
    this(DSL.name(alias), SYSTEM_COLUMNS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS</code> table reference */
  public SystemColumns(Name alias) {
    this(alias, SYSTEM_COLUMNS);
  }

  /** Create a <code>INFORMATION_SCHEMA.SYSTEM_COLUMNS</code> table reference */
  public SystemColumns() {
    this(DSL.name("SYSTEM_COLUMNS"), null);
  }

  public <O extends Record> SystemColumns(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, SYSTEM_COLUMNS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public SystemColumns as(String alias) {
    return new SystemColumns(DSL.name(alias), this);
  }

  @Override
  public SystemColumns as(Name alias) {
    return new SystemColumns(alias, this);
  }

  /** Rename this table */
  @Override
  public SystemColumns rename(String name) {
    return new SystemColumns(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public SystemColumns rename(Name name) {
    return new SystemColumns(name, null);
  }
}
