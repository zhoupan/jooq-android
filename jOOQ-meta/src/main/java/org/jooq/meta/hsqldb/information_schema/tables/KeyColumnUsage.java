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

import java.util.Arrays;
import java.util.List;
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
import org.jooq.meta.hsqldb.information_schema.Keys;

/** one row for each column used in s primary key or unique constraint */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class KeyColumnUsage extends TableImpl<Record> {

  private static final long serialVersionUID = 1228813308;

  /** The reference instance of <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE</code> */
  public static final KeyColumnUsage KEY_COLUMN_USAGE = new KeyColumnUsage();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_CATALOG</code>. */
  public final TableField<Record, String> CONSTRAINT_CATALOG =
      createField(DSL.name("CONSTRAINT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_SCHEMA</code>. */
  public final TableField<Record, String> CONSTRAINT_SCHEMA =
      createField(DSL.name("CONSTRAINT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_NAME</code>. */
  public final TableField<Record, String> CONSTRAINT_NAME =
      createField(DSL.name("CONSTRAINT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_CATALOG</code>. */
  public final TableField<Record, String> TABLE_CATALOG =
      createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_SCHEMA</code>. */
  public final TableField<Record, String> TABLE_SCHEMA =
      createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_NAME</code>. */
  public final TableField<Record, String> TABLE_NAME =
      createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.COLUMN_NAME</code>. */
  public final TableField<Record, String> COLUMN_NAME =
      createField(DSL.name("COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.ORDINAL_POSITION</code>. */
  public final TableField<Record, Long> ORDINAL_POSITION =
      createField(DSL.name("ORDINAL_POSITION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE.POSITION_IN_UNIQUE_CONSTRAINT</code>. */
  public final TableField<Record, Long> POSITION_IN_UNIQUE_CONSTRAINT =
      createField(DSL.name("POSITION_IN_UNIQUE_CONSTRAINT"), SQLDataType.BIGINT, this, "");

  private KeyColumnUsage(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private KeyColumnUsage(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each column used in s primary key or unique constraint"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE</code> table reference */
  public KeyColumnUsage(String alias) {
    this(DSL.name(alias), KEY_COLUMN_USAGE);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE</code> table reference */
  public KeyColumnUsage(Name alias) {
    this(alias, KEY_COLUMN_USAGE);
  }

  /** Create a <code>INFORMATION_SCHEMA.KEY_COLUMN_USAGE</code> table reference */
  public KeyColumnUsage() {
    this(DSL.name("KEY_COLUMN_USAGE"), null);
  }

  public <O extends Record> KeyColumnUsage(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, KEY_COLUMN_USAGE);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(
        Keys.SYNTHETIC_FK_KEY_COLUMN_USAGE__SYNTHETIC_PK_TABLE_CONSTRAINTS);
  }

  public TableConstraints tableConstraints() {
    return new TableConstraints(
        this, Keys.SYNTHETIC_FK_KEY_COLUMN_USAGE__SYNTHETIC_PK_TABLE_CONSTRAINTS);
  }

  @Override
  public KeyColumnUsage as(String alias) {
    return new KeyColumnUsage(DSL.name(alias), this);
  }

  @Override
  public KeyColumnUsage as(Name alias) {
    return new KeyColumnUsage(alias, this);
  }

  /** Rename this table */
  @Override
  public KeyColumnUsage rename(String name) {
    return new KeyColumnUsage(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public KeyColumnUsage rename(Name name) {
    return new KeyColumnUsage(name, null);
  }
}
