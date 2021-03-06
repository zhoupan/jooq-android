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

/** one row for each table or view */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables extends TableImpl<Record> {

  private static final long serialVersionUID = -1705233434;

  /** The reference instance of <code>INFORMATION_SCHEMA.TABLES</code> */
  public static final Tables TABLES = new Tables();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.TABLES.TABLE_CATALOG</code>. */
  public final TableField<Record, String> TABLE_CATALOG =
      createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.TABLE_SCHEMA</code>. */
  public final TableField<Record, String> TABLE_SCHEMA =
      createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.TABLE_NAME</code>. */
  public final TableField<Record, String> TABLE_NAME =
      createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.TABLE_TYPE</code>. */
  public final TableField<Record, String> TABLE_TYPE =
      createField(DSL.name("TABLE_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.SELF_REFERENCING_COLUMN_NAME</code>. */
  public final TableField<Record, String> SELF_REFERENCING_COLUMN_NAME =
      createField(DSL.name("SELF_REFERENCING_COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.REFERENCE_GENERATION</code>. */
  public final TableField<Record, String> REFERENCE_GENERATION =
      createField(DSL.name("REFERENCE_GENERATION"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.USER_DEFINED_TYPE_CATALOG</code>. */
  public final TableField<Record, String> USER_DEFINED_TYPE_CATALOG =
      createField(DSL.name("USER_DEFINED_TYPE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.USER_DEFINED_TYPE_SCHEMA</code>. */
  public final TableField<Record, String> USER_DEFINED_TYPE_SCHEMA =
      createField(DSL.name("USER_DEFINED_TYPE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.USER_DEFINED_TYPE_NAME</code>. */
  public final TableField<Record, String> USER_DEFINED_TYPE_NAME =
      createField(DSL.name("USER_DEFINED_TYPE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.IS_INSERTABLE_INTO</code>. */
  public final TableField<Record, String> IS_INSERTABLE_INTO =
      createField(DSL.name("IS_INSERTABLE_INTO"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.IS_TYPED</code>. */
  public final TableField<Record, String> IS_TYPED =
      createField(DSL.name("IS_TYPED"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.TABLES.COMMIT_ACTION</code>. */
  public final TableField<Record, String> COMMIT_ACTION =
      createField(DSL.name("COMMIT_ACTION"), SQLDataType.VARCHAR(65536), this, "");

  private Tables(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Tables(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each table or view"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.TABLES</code> table reference */
  public Tables(String alias) {
    this(DSL.name(alias), TABLES);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.TABLES</code> table reference */
  public Tables(Name alias) {
    this(alias, TABLES);
  }

  /** Create a <code>INFORMATION_SCHEMA.TABLES</code> table reference */
  public Tables() {
    this(DSL.name("TABLES"), null);
  }

  public <O extends Record> Tables(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, TABLES);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_TABLES;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_TABLES);
  }

  @Override
  public Tables as(String alias) {
    return new Tables(DSL.name(alias), this);
  }

  @Override
  public Tables as(Name alias) {
    return new Tables(alias, this);
  }

  /** Rename this table */
  @Override
  public Tables rename(String name) {
    return new Tables(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Tables rename(Name name) {
    return new Tables(name, null);
  }
}
