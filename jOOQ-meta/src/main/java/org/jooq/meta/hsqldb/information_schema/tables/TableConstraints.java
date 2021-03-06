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
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.meta.hsqldb.information_schema.InformationSchema;
import org.jooq.meta.hsqldb.information_schema.Keys;

/** one row for each table constraint associated with a table */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class TableConstraints extends TableImpl<Record> {

  private static final long serialVersionUID = 1492215078;

  /** The reference instance of <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS</code> */
  public static final TableConstraints TABLE_CONSTRAINTS = new TableConstraints();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_CATALOG</code>. */
  public final TableField<Record, String> CONSTRAINT_CATALOG =
      createField(DSL.name("CONSTRAINT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_SCHEMA</code>. */
  public final TableField<Record, String> CONSTRAINT_SCHEMA =
      createField(DSL.name("CONSTRAINT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_NAME</code>. */
  public final TableField<Record, String> CONSTRAINT_NAME =
      createField(DSL.name("CONSTRAINT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.CONSTRAINT_TYPE</code>. */
  public final TableField<Record, String> CONSTRAINT_TYPE =
      createField(DSL.name("CONSTRAINT_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.TABLE_CATALOG</code>. */
  public final TableField<Record, String> TABLE_CATALOG =
      createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.TABLE_SCHEMA</code>. */
  public final TableField<Record, String> TABLE_SCHEMA =
      createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.TABLE_NAME</code>. */
  public final TableField<Record, String> TABLE_NAME =
      createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.IS_DEFERRABLE</code>. */
  public final TableField<Record, String> IS_DEFERRABLE =
      createField(DSL.name("IS_DEFERRABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS.INITIALLY_DEFERRED</code>. */
  public final TableField<Record, String> INITIALLY_DEFERRED =
      createField(DSL.name("INITIALLY_DEFERRED"), SQLDataType.VARCHAR(3), this, "");

  private TableConstraints(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private TableConstraints(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each table constraint associated with a table"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS</code> table reference */
  public TableConstraints(String alias) {
    this(DSL.name(alias), TABLE_CONSTRAINTS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS</code> table reference */
  public TableConstraints(Name alias) {
    this(alias, TABLE_CONSTRAINTS);
  }

  /** Create a <code>INFORMATION_SCHEMA.TABLE_CONSTRAINTS</code> table reference */
  public TableConstraints() {
    this(DSL.name("TABLE_CONSTRAINTS"), null);
  }

  public <O extends Record> TableConstraints(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, TABLE_CONSTRAINTS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_TABLE_CONSTRAINTS;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_TABLE_CONSTRAINTS);
  }

  @Override
  public TableConstraints as(String alias) {
    return new TableConstraints(DSL.name(alias), this);
  }

  @Override
  public TableConstraints as(Name alias) {
    return new TableConstraints(alias, this);
  }

  /** Rename this table */
  @Override
  public TableConstraints rename(String name) {
    return new TableConstraints(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public TableConstraints rename(Name name) {
    return new TableConstraints(name, null);
  }
}
