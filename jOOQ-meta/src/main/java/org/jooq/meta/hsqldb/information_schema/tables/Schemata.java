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

/** one row for each schema */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Schemata extends TableImpl<Record> {

  private static final long serialVersionUID = 2116125842;

  /** The reference instance of <code>INFORMATION_SCHEMA.SCHEMATA</code> */
  public static final Schemata SCHEMATA = new Schemata();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.CATALOG_NAME</code>. */
  public final TableField<Record, String> CATALOG_NAME =
      createField(DSL.name("CATALOG_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.SCHEMA_NAME</code>. */
  public final TableField<Record, String> SCHEMA_NAME =
      createField(DSL.name("SCHEMA_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.SCHEMA_OWNER</code>. */
  public final TableField<Record, String> SCHEMA_OWNER =
      createField(DSL.name("SCHEMA_OWNER"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.DEFAULT_CHARACTER_SET_CATALOG</code>. */
  public final TableField<Record, String> DEFAULT_CHARACTER_SET_CATALOG =
      createField(DSL.name("DEFAULT_CHARACTER_SET_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.DEFAULT_CHARACTER_SET_SCHEMA</code>. */
  public final TableField<Record, String> DEFAULT_CHARACTER_SET_SCHEMA =
      createField(DSL.name("DEFAULT_CHARACTER_SET_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.DEFAULT_CHARACTER_SET_NAME</code>. */
  public final TableField<Record, String> DEFAULT_CHARACTER_SET_NAME =
      createField(DSL.name("DEFAULT_CHARACTER_SET_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.SCHEMATA.SQL_PATH</code>. */
  public final TableField<Record, String> SQL_PATH =
      createField(DSL.name("SQL_PATH"), SQLDataType.VARCHAR(65536), this, "");

  private Schemata(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Schemata(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each schema"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SCHEMATA</code> table reference */
  public Schemata(String alias) {
    this(DSL.name(alias), SCHEMATA);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SCHEMATA</code> table reference */
  public Schemata(Name alias) {
    this(alias, SCHEMATA);
  }

  /** Create a <code>INFORMATION_SCHEMA.SCHEMATA</code> table reference */
  public Schemata() {
    this(DSL.name("SCHEMATA"), null);
  }

  public <O extends Record> Schemata(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, SCHEMATA);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_SCHEMATA;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_SCHEMATA);
  }

  @Override
  public Schemata as(String alias) {
    return new Schemata(DSL.name(alias), this);
  }

  @Override
  public Schemata as(Name alias) {
    return new Schemata(alias, this);
  }

  /** Rename this table */
  @Override
  public Schemata rename(String name) {
    return new Schemata(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Schemata rename(Name name) {
    return new Schemata(name, null);
  }
}
