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

/** the view descriptors of the accessible views defined within this database */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Views extends TableImpl<Record> {

  private static final long serialVersionUID = 213426792;

  /** The reference instance of <code>INFORMATION_SCHEMA.VIEWS</code> */
  public static final Views VIEWS = new Views();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.VIEWS.TABLE_CATALOG</code>. */
  public final TableField<Record, String> TABLE_CATALOG =
      createField(DSL.name("TABLE_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.TABLE_SCHEMA</code>. */
  public final TableField<Record, String> TABLE_SCHEMA =
      createField(DSL.name("TABLE_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.TABLE_NAME</code>. */
  public final TableField<Record, String> TABLE_NAME =
      createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.VIEW_DEFINITION</code>. */
  public final TableField<Record, String> VIEW_DEFINITION =
      createField(DSL.name("VIEW_DEFINITION"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.CHECK_OPTION</code>. */
  public final TableField<Record, String> CHECK_OPTION =
      createField(DSL.name("CHECK_OPTION"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.IS_UPDATABLE</code>. */
  public final TableField<Record, String> IS_UPDATABLE =
      createField(DSL.name("IS_UPDATABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.INSERTABLE_INTO</code>. */
  public final TableField<Record, String> INSERTABLE_INTO =
      createField(DSL.name("INSERTABLE_INTO"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.IS_TRIGGER_UPDATABLE</code>. */
  public final TableField<Record, String> IS_TRIGGER_UPDATABLE =
      createField(DSL.name("IS_TRIGGER_UPDATABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.IS_TRIGGER_DELETABLE</code>. */
  public final TableField<Record, String> IS_TRIGGER_DELETABLE =
      createField(DSL.name("IS_TRIGGER_DELETABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.VIEWS.IS_TRIGGER_INSERTABLE_INTO</code>. */
  public final TableField<Record, String> IS_TRIGGER_INSERTABLE_INTO =
      createField(DSL.name("IS_TRIGGER_INSERTABLE_INTO"), SQLDataType.VARCHAR(3), this, "");

  private Views(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Views(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("the view descriptors of the accessible views defined within this database"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.VIEWS</code> table reference */
  public Views(String alias) {
    this(DSL.name(alias), VIEWS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.VIEWS</code> table reference */
  public Views(Name alias) {
    this(alias, VIEWS);
  }

  /** Create a <code>INFORMATION_SCHEMA.VIEWS</code> table reference */
  public Views() {
    this(DSL.name("VIEWS"), null);
  }

  public <O extends Record> Views(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, VIEWS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(Keys.SYNTHETIC_FK_VIEWS__SYNTHETIC_PK_TABLES);
  }

  public Tables tables() {
    return new Tables(this, Keys.SYNTHETIC_FK_VIEWS__SYNTHETIC_PK_TABLES);
  }

  @Override
  public Views as(String alias) {
    return new Views(DSL.name(alias), this);
  }

  @Override
  public Views as(Name alias) {
    return new Views(alias, this);
  }

  /** Rename this table */
  @Override
  public Views rename(String name) {
    return new Views(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Views rename(Name name) {
    return new Views(name, null);
  }
}
