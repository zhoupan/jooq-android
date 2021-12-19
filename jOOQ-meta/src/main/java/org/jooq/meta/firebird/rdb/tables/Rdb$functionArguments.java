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
package org.jooq.meta.firebird.rdb.tables;

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
import org.jooq.meta.firebird.rdb.DefaultSchema;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Rdb$functionArguments extends TableImpl<Record> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>RDB$FUNCTION_ARGUMENTS</code> */
  public static final Rdb$functionArguments RDB$FUNCTION_ARGUMENTS = new Rdb$functionArguments();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FUNCTION_NAME</code>. */
  public final TableField<Record, String> RDB$FUNCTION_NAME =
      createField(DSL.name("RDB$FUNCTION_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$ARGUMENT_POSITION</code>. */
  public final TableField<Record, Short> RDB$ARGUMENT_POSITION =
      createField(DSL.name("RDB$ARGUMENT_POSITION"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$MECHANISM</code>. */
  public final TableField<Record, Short> RDB$MECHANISM =
      createField(DSL.name("RDB$MECHANISM"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_TYPE</code>. */
  public final TableField<Record, Short> RDB$FIELD_TYPE =
      createField(DSL.name("RDB$FIELD_TYPE"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_SCALE</code>. */
  public final TableField<Record, Short> RDB$FIELD_SCALE =
      createField(DSL.name("RDB$FIELD_SCALE"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_LENGTH</code>. */
  public final TableField<Record, Short> RDB$FIELD_LENGTH =
      createField(DSL.name("RDB$FIELD_LENGTH"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_SUB_TYPE</code>. */
  public final TableField<Record, Short> RDB$FIELD_SUB_TYPE =
      createField(DSL.name("RDB$FIELD_SUB_TYPE"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$CHARACTER_SET_ID</code>. */
  public final TableField<Record, Short> RDB$CHARACTER_SET_ID =
      createField(DSL.name("RDB$CHARACTER_SET_ID"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_PRECISION</code>. */
  public final TableField<Record, Short> RDB$FIELD_PRECISION =
      createField(DSL.name("RDB$FIELD_PRECISION"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$CHARACTER_LENGTH</code>. */
  public final TableField<Record, Short> RDB$CHARACTER_LENGTH =
      createField(DSL.name("RDB$CHARACTER_LENGTH"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$PACKAGE_NAME</code>. */
  public final TableField<Record, String> RDB$PACKAGE_NAME =
      createField(DSL.name("RDB$PACKAGE_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$ARGUMENT_NAME</code>. */
  public final TableField<Record, String> RDB$ARGUMENT_NAME =
      createField(DSL.name("RDB$ARGUMENT_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_SOURCE</code>. */
  public final TableField<Record, String> RDB$FIELD_SOURCE =
      createField(DSL.name("RDB$FIELD_SOURCE"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$DEFAULT_VALUE</code>. */
  public final TableField<Record, byte[]> RDB$DEFAULT_VALUE =
      createField(DSL.name("RDB$DEFAULT_VALUE"), SQLDataType.BLOB, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$DEFAULT_SOURCE</code>. */
  public final TableField<Record, String> RDB$DEFAULT_SOURCE =
      createField(DSL.name("RDB$DEFAULT_SOURCE"), SQLDataType.CLOB, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$COLLATION_ID</code>. */
  public final TableField<Record, Short> RDB$COLLATION_ID =
      createField(DSL.name("RDB$COLLATION_ID"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$NULL_FLAG</code>. */
  public final TableField<Record, Short> RDB$NULL_FLAG =
      createField(DSL.name("RDB$NULL_FLAG"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$ARGUMENT_MECHANISM</code>. */
  public final TableField<Record, Short> RDB$ARGUMENT_MECHANISM =
      createField(DSL.name("RDB$ARGUMENT_MECHANISM"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$FIELD_NAME</code>. */
  public final TableField<Record, String> RDB$FIELD_NAME =
      createField(DSL.name("RDB$FIELD_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$RELATION_NAME</code>. */
  public final TableField<Record, String> RDB$RELATION_NAME =
      createField(DSL.name("RDB$RELATION_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$SYSTEM_FLAG</code>. */
  public final TableField<Record, Short> RDB$SYSTEM_FLAG =
      createField(DSL.name("RDB$SYSTEM_FLAG"), SQLDataType.SMALLINT.nullable(false), this, "");

  /** The column <code>RDB$FUNCTION_ARGUMENTS.RDB$DESCRIPTION</code>. */
  public final TableField<Record, String> RDB$DESCRIPTION =
      createField(DSL.name("RDB$DESCRIPTION"), SQLDataType.CLOB, this, "");

  private Rdb$functionArguments(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Rdb$functionArguments(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /** Create an aliased <code>RDB$FUNCTION_ARGUMENTS</code> table reference */
  public Rdb$functionArguments(String alias) {
    this(DSL.name(alias), RDB$FUNCTION_ARGUMENTS);
  }

  /** Create an aliased <code>RDB$FUNCTION_ARGUMENTS</code> table reference */
  public Rdb$functionArguments(Name alias) {
    this(alias, RDB$FUNCTION_ARGUMENTS);
  }

  /** Create a <code>RDB$FUNCTION_ARGUMENTS</code> table reference */
  public Rdb$functionArguments() {
    this(DSL.name("RDB$FUNCTION_ARGUMENTS"), null);
  }

  public <O extends Record> Rdb$functionArguments(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, RDB$FUNCTION_ARGUMENTS);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
  }

  @Override
  public Rdb$functionArguments as(String alias) {
    return new Rdb$functionArguments(DSL.name(alias), this);
  }

  @Override
  public Rdb$functionArguments as(Name alias) {
    return new Rdb$functionArguments(alias, this);
  }

  /** Rename this table */
  @Override
  public Rdb$functionArguments rename(String name) {
    return new Rdb$functionArguments(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Rdb$functionArguments rename(Name name) {
    return new Rdb$functionArguments(name, null);
  }
}
