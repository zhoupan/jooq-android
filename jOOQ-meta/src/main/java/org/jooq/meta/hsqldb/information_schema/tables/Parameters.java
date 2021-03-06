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

/** one row for each routine parameter */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Parameters extends TableImpl<Record> {

  private static final long serialVersionUID = -1303024575;

  /** The reference instance of <code>INFORMATION_SCHEMA.PARAMETERS</code> */
  public static final Parameters PARAMETERS = new Parameters();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.SPECIFIC_CATALOG</code>. */
  public final TableField<Record, String> SPECIFIC_CATALOG =
      createField(DSL.name("SPECIFIC_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.SPECIFIC_SCHEMA</code>. */
  public final TableField<Record, String> SPECIFIC_SCHEMA =
      createField(DSL.name("SPECIFIC_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.SPECIFIC_NAME</code>. */
  public final TableField<Record, String> SPECIFIC_NAME =
      createField(DSL.name("SPECIFIC_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.ORDINAL_POSITION</code>. */
  public final TableField<Record, Long> ORDINAL_POSITION =
      createField(DSL.name("ORDINAL_POSITION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.PARAMETER_MODE</code>. */
  public final TableField<Record, String> PARAMETER_MODE =
      createField(DSL.name("PARAMETER_MODE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.IS_RESULT</code>. */
  public final TableField<Record, String> IS_RESULT =
      createField(DSL.name("IS_RESULT"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.AS_LOCATOR</code>. */
  public final TableField<Record, String> AS_LOCATOR =
      createField(DSL.name("AS_LOCATOR"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.PARAMETER_NAME</code>. */
  public final TableField<Record, String> PARAMETER_NAME =
      createField(DSL.name("PARAMETER_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.FROM_SQL_SPECIFIC_CATALOG</code>. */
  public final TableField<Record, String> FROM_SQL_SPECIFIC_CATALOG =
      createField(DSL.name("FROM_SQL_SPECIFIC_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.FROM_SQL_SPECIFIC_SCHEMA</code>. */
  public final TableField<Record, String> FROM_SQL_SPECIFIC_SCHEMA =
      createField(DSL.name("FROM_SQL_SPECIFIC_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.FROM_SQL_SPECIFIC_NAME</code>. */
  public final TableField<Record, String> FROM_SQL_SPECIFIC_NAME =
      createField(DSL.name("FROM_SQL_SPECIFIC_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.TO_SQL_SPECIFIC_CATALOG</code>. */
  public final TableField<Record, String> TO_SQL_SPECIFIC_CATALOG =
      createField(DSL.name("TO_SQL_SPECIFIC_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.TO_SQL_SPECIFIC_SCHEMA</code>. */
  public final TableField<Record, String> TO_SQL_SPECIFIC_SCHEMA =
      createField(DSL.name("TO_SQL_SPECIFIC_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.TO_SQL_SPECIFIC_NAME</code>. */
  public final TableField<Record, String> TO_SQL_SPECIFIC_NAME =
      createField(DSL.name("TO_SQL_SPECIFIC_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.DATA_TYPE</code>. */
  public final TableField<Record, String> DATA_TYPE =
      createField(DSL.name("DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_MAXIMUM_LENGTH</code>. */
  public final TableField<Record, Long> CHARACTER_MAXIMUM_LENGTH =
      createField(DSL.name("CHARACTER_MAXIMUM_LENGTH"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_OCTET_LENGTH</code>. */
  public final TableField<Record, Long> CHARACTER_OCTET_LENGTH =
      createField(DSL.name("CHARACTER_OCTET_LENGTH"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_SET_CATALOG</code>. */
  public final TableField<Record, String> CHARACTER_SET_CATALOG =
      createField(DSL.name("CHARACTER_SET_CATALOG"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_SET_SCHEMA</code>. */
  public final TableField<Record, String> CHARACTER_SET_SCHEMA =
      createField(DSL.name("CHARACTER_SET_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.CHARACTER_SET_NAME</code>. */
  public final TableField<Record, String> CHARACTER_SET_NAME =
      createField(DSL.name("CHARACTER_SET_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.COLLATION_CATALOG</code>. */
  public final TableField<Record, String> COLLATION_CATALOG =
      createField(DSL.name("COLLATION_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.COLLATION_SCHEMA</code>. */
  public final TableField<Record, String> COLLATION_SCHEMA =
      createField(DSL.name("COLLATION_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.COLLATION_NAME</code>. */
  public final TableField<Record, String> COLLATION_NAME =
      createField(DSL.name("COLLATION_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.NUMERIC_PRECISION</code>. */
  public final TableField<Record, Long> NUMERIC_PRECISION =
      createField(DSL.name("NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.NUMERIC_PRECISION_RADIX</code>. */
  public final TableField<Record, Long> NUMERIC_PRECISION_RADIX =
      createField(DSL.name("NUMERIC_PRECISION_RADIX"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.NUMERIC_SCALE</code>. */
  public final TableField<Record, Long> NUMERIC_SCALE =
      createField(DSL.name("NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.DATETIME_PRECISION</code>. */
  public final TableField<Record, Long> DATETIME_PRECISION =
      createField(DSL.name("DATETIME_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.INTERVAL_TYPE</code>. */
  public final TableField<Record, String> INTERVAL_TYPE =
      createField(DSL.name("INTERVAL_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.INTERVAL_PRECISION</code>. */
  public final TableField<Record, Long> INTERVAL_PRECISION =
      createField(DSL.name("INTERVAL_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.UDT_CATALOG</code>. */
  public final TableField<Record, String> UDT_CATALOG =
      createField(DSL.name("UDT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.UDT_SCHEMA</code>. */
  public final TableField<Record, String> UDT_SCHEMA =
      createField(DSL.name("UDT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.UDT_NAME</code>. */
  public final TableField<Record, String> UDT_NAME =
      createField(DSL.name("UDT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.SCOPE_CATALOG</code>. */
  public final TableField<Record, String> SCOPE_CATALOG =
      createField(DSL.name("SCOPE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.SCOPE_SCHEMA</code>. */
  public final TableField<Record, String> SCOPE_SCHEMA =
      createField(DSL.name("SCOPE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.SCOPE_NAME</code>. */
  public final TableField<Record, String> SCOPE_NAME =
      createField(DSL.name("SCOPE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.MAXIMUM_CARDINALITY</code>. */
  public final TableField<Record, Long> MAXIMUM_CARDINALITY =
      createField(DSL.name("MAXIMUM_CARDINALITY"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.DTD_IDENTIFIER</code>. */
  public final TableField<Record, String> DTD_IDENTIFIER =
      createField(DSL.name("DTD_IDENTIFIER"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.DECLARED_DATA_TYPE</code>. */
  public final TableField<Record, String> DECLARED_DATA_TYPE =
      createField(DSL.name("DECLARED_DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.DECLARED_NUMERIC_PRECISION</code>. */
  public final TableField<Record, Long> DECLARED_NUMERIC_PRECISION =
      createField(DSL.name("DECLARED_NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.PARAMETERS.DECLARED_NUMERIC_SCALE</code>. */
  public final TableField<Record, Long> DECLARED_NUMERIC_SCALE =
      createField(DSL.name("DECLARED_NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

  private Parameters(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Parameters(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each routine parameter"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.PARAMETERS</code> table reference */
  public Parameters(String alias) {
    this(DSL.name(alias), PARAMETERS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.PARAMETERS</code> table reference */
  public Parameters(Name alias) {
    this(alias, PARAMETERS);
  }

  /** Create a <code>INFORMATION_SCHEMA.PARAMETERS</code> table reference */
  public Parameters() {
    this(DSL.name("PARAMETERS"), null);
  }

  public <O extends Record> Parameters(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, PARAMETERS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public Parameters as(String alias) {
    return new Parameters(DSL.name(alias), this);
  }

  @Override
  public Parameters as(Name alias) {
    return new Parameters(alias, this);
  }

  /** Rename this table */
  @Override
  public Parameters rename(String name) {
    return new Parameters(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Parameters rename(Name name) {
    return new Parameters(name, null);
  }
}
