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

/** one row for each column of table of view */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Columns extends TableImpl<Record> {

  private static final long serialVersionUID = -739376577;

  /** The reference instance of <code>INFORMATION_SCHEMA.COLUMNS</code> */
  public static final Columns COLUMNS = new Columns();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.TABLE_CATALOG</code>. */
  public final TableField<Record, String> TABLE_CATALOG =
      createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.TABLE_SCHEMA</code>. */
  public final TableField<Record, String> TABLE_SCHEMA =
      createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.TABLE_NAME</code>. */
  public final TableField<Record, String> TABLE_NAME =
      createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.COLUMN_NAME</code>. */
  public final TableField<Record, String> COLUMN_NAME =
      createField(DSL.name("COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.ORDINAL_POSITION</code>. */
  public final TableField<Record, Long> ORDINAL_POSITION =
      createField(DSL.name("ORDINAL_POSITION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.COLUMN_DEFAULT</code>. */
  public final TableField<Record, String> COLUMN_DEFAULT =
      createField(DSL.name("COLUMN_DEFAULT"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_NULLABLE</code>. */
  public final TableField<Record, String> IS_NULLABLE =
      createField(DSL.name("IS_NULLABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DATA_TYPE</code>. */
  public final TableField<Record, String> DATA_TYPE =
      createField(DSL.name("DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.CHARACTER_MAXIMUM_LENGTH</code>. */
  public final TableField<Record, Long> CHARACTER_MAXIMUM_LENGTH =
      createField(DSL.name("CHARACTER_MAXIMUM_LENGTH"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.CHARACTER_OCTET_LENGTH</code>. */
  public final TableField<Record, Long> CHARACTER_OCTET_LENGTH =
      createField(DSL.name("CHARACTER_OCTET_LENGTH"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.NUMERIC_PRECISION</code>. */
  public final TableField<Record, Long> NUMERIC_PRECISION =
      createField(DSL.name("NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.NUMERIC_PRECISION_RADIX</code>. */
  public final TableField<Record, Long> NUMERIC_PRECISION_RADIX =
      createField(DSL.name("NUMERIC_PRECISION_RADIX"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.NUMERIC_SCALE</code>. */
  public final TableField<Record, Long> NUMERIC_SCALE =
      createField(DSL.name("NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DATETIME_PRECISION</code>. */
  public final TableField<Record, Long> DATETIME_PRECISION =
      createField(DSL.name("DATETIME_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.INTERVAL_TYPE</code>. */
  public final TableField<Record, String> INTERVAL_TYPE =
      createField(DSL.name("INTERVAL_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.INTERVAL_PRECISION</code>. */
  public final TableField<Record, Long> INTERVAL_PRECISION =
      createField(DSL.name("INTERVAL_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.CHARACTER_SET_CATALOG</code>. */
  public final TableField<Record, String> CHARACTER_SET_CATALOG =
      createField(DSL.name("CHARACTER_SET_CATALOG"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.CHARACTER_SET_SCHEMA</code>. */
  public final TableField<Record, String> CHARACTER_SET_SCHEMA =
      createField(DSL.name("CHARACTER_SET_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.CHARACTER_SET_NAME</code>. */
  public final TableField<Record, String> CHARACTER_SET_NAME =
      createField(DSL.name("CHARACTER_SET_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.COLLATION_CATALOG</code>. */
  public final TableField<Record, String> COLLATION_CATALOG =
      createField(DSL.name("COLLATION_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.COLLATION_SCHEMA</code>. */
  public final TableField<Record, String> COLLATION_SCHEMA =
      createField(DSL.name("COLLATION_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.COLLATION_NAME</code>. */
  public final TableField<Record, String> COLLATION_NAME =
      createField(DSL.name("COLLATION_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DOMAIN_CATALOG</code>. */
  public final TableField<Record, String> DOMAIN_CATALOG =
      createField(DSL.name("DOMAIN_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DOMAIN_SCHEMA</code>. */
  public final TableField<Record, String> DOMAIN_SCHEMA =
      createField(DSL.name("DOMAIN_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DOMAIN_NAME</code>. */
  public final TableField<Record, String> DOMAIN_NAME =
      createField(DSL.name("DOMAIN_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.UDT_CATALOG</code>. */
  public final TableField<Record, String> UDT_CATALOG =
      createField(DSL.name("UDT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.UDT_SCHEMA</code>. */
  public final TableField<Record, String> UDT_SCHEMA =
      createField(DSL.name("UDT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.UDT_NAME</code>. */
  public final TableField<Record, String> UDT_NAME =
      createField(DSL.name("UDT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.SCOPE_CATALOG</code>. */
  public final TableField<Record, String> SCOPE_CATALOG =
      createField(DSL.name("SCOPE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.SCOPE_SCHEMA</code>. */
  public final TableField<Record, String> SCOPE_SCHEMA =
      createField(DSL.name("SCOPE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.SCOPE_NAME</code>. */
  public final TableField<Record, String> SCOPE_NAME =
      createField(DSL.name("SCOPE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.MAXIMUM_CARDINALITY</code>. */
  public final TableField<Record, Long> MAXIMUM_CARDINALITY =
      createField(DSL.name("MAXIMUM_CARDINALITY"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DTD_IDENTIFIER</code>. */
  public final TableField<Record, String> DTD_IDENTIFIER =
      createField(DSL.name("DTD_IDENTIFIER"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_SELF_REFERENCING</code>. */
  public final TableField<Record, String> IS_SELF_REFERENCING =
      createField(DSL.name("IS_SELF_REFERENCING"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_IDENTITY</code>. */
  public final TableField<Record, String> IS_IDENTITY =
      createField(DSL.name("IS_IDENTITY"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IDENTITY_GENERATION</code>. */
  public final TableField<Record, String> IDENTITY_GENERATION =
      createField(DSL.name("IDENTITY_GENERATION"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IDENTITY_START</code>. */
  public final TableField<Record, String> IDENTITY_START =
      createField(DSL.name("IDENTITY_START"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IDENTITY_INCREMENT</code>. */
  public final TableField<Record, String> IDENTITY_INCREMENT =
      createField(DSL.name("IDENTITY_INCREMENT"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IDENTITY_MAXIMUM</code>. */
  public final TableField<Record, String> IDENTITY_MAXIMUM =
      createField(DSL.name("IDENTITY_MAXIMUM"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IDENTITY_MINIMUM</code>. */
  public final TableField<Record, String> IDENTITY_MINIMUM =
      createField(DSL.name("IDENTITY_MINIMUM"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IDENTITY_CYCLE</code>. */
  public final TableField<Record, String> IDENTITY_CYCLE =
      createField(DSL.name("IDENTITY_CYCLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_GENERATED</code>. */
  public final TableField<Record, String> IS_GENERATED =
      createField(DSL.name("IS_GENERATED"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.GENERATION_EXPRESSION</code>. */
  public final TableField<Record, String> GENERATION_EXPRESSION =
      createField(DSL.name("GENERATION_EXPRESSION"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_SYSTEM_TIME_PERIOD_START</code>. */
  public final TableField<Record, String> IS_SYSTEM_TIME_PERIOD_START =
      createField(DSL.name("IS_SYSTEM_TIME_PERIOD_START"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_SYSTEM_TIME_PERIOD_END</code>. */
  public final TableField<Record, String> IS_SYSTEM_TIME_PERIOD_END =
      createField(DSL.name("IS_SYSTEM_TIME_PERIOD_END"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.SYSTEM_TIME_PERIOD_TIMESTAMP_GENERATION</code>. */
  public final TableField<Record, String> SYSTEM_TIME_PERIOD_TIMESTAMP_GENERATION =
      createField(
          DSL.name("SYSTEM_TIME_PERIOD_TIMESTAMP_GENERATION"),
          SQLDataType.VARCHAR(65536),
          this,
          "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.IS_UPDATABLE</code>. */
  public final TableField<Record, String> IS_UPDATABLE =
      createField(DSL.name("IS_UPDATABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DECLARED_DATA_TYPE</code>. */
  public final TableField<Record, String> DECLARED_DATA_TYPE =
      createField(DSL.name("DECLARED_DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DECLARED_NUMERIC_PRECISION</code>. */
  public final TableField<Record, Long> DECLARED_NUMERIC_PRECISION =
      createField(DSL.name("DECLARED_NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.COLUMNS.DECLARED_NUMERIC_SCALE</code>. */
  public final TableField<Record, Long> DECLARED_NUMERIC_SCALE =
      createField(DSL.name("DECLARED_NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

  private Columns(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Columns(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each column of table of view"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.COLUMNS</code> table reference */
  public Columns(String alias) {
    this(DSL.name(alias), COLUMNS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.COLUMNS</code> table reference */
  public Columns(Name alias) {
    this(alias, COLUMNS);
  }

  /** Create a <code>INFORMATION_SCHEMA.COLUMNS</code> table reference */
  public Columns() {
    this(DSL.name("COLUMNS"), null);
  }

  public <O extends Record> Columns(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, COLUMNS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public Columns as(String alias) {
    return new Columns(DSL.name(alias), this);
  }

  @Override
  public Columns as(Name alias) {
    return new Columns(alias, this);
  }

  /** Rename this table */
  @Override
  public Columns rename(String name) {
    return new Columns(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Columns rename(Name name) {
    return new Columns(name, null);
  }
}
