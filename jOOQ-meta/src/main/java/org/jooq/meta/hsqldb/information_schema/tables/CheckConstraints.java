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

/** one row for each domain constraint, table check constraint, and assertion. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class CheckConstraints extends TableImpl<Record> {

  private static final long serialVersionUID = 1517558087;

  /** The reference instance of <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS</code> */
  public static final CheckConstraints CHECK_CONSTRAINTS = new CheckConstraints();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS.CONSTRAINT_CATALOG</code>. */
  public final TableField<Record, String> CONSTRAINT_CATALOG =
      createField(DSL.name("CONSTRAINT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS.CONSTRAINT_SCHEMA</code>. */
  public final TableField<Record, String> CONSTRAINT_SCHEMA =
      createField(DSL.name("CONSTRAINT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS.CONSTRAINT_NAME</code>. */
  public final TableField<Record, String> CONSTRAINT_NAME =
      createField(DSL.name("CONSTRAINT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS.CHECK_CLAUSE</code>. */
  public final TableField<Record, String> CHECK_CLAUSE =
      createField(DSL.name("CHECK_CLAUSE"), SQLDataType.VARCHAR(65536), this, "");

  private CheckConstraints(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private CheckConstraints(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each domain constraint, table check constraint, and assertion."),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS</code> table reference */
  public CheckConstraints(String alias) {
    this(DSL.name(alias), CHECK_CONSTRAINTS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS</code> table reference */
  public CheckConstraints(Name alias) {
    this(alias, CHECK_CONSTRAINTS);
  }

  /** Create a <code>INFORMATION_SCHEMA.CHECK_CONSTRAINTS</code> table reference */
  public CheckConstraints() {
    this(DSL.name("CHECK_CONSTRAINTS"), null);
  }

  public <O extends Record> CheckConstraints(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, CHECK_CONSTRAINTS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_CHECK_CONSTRAINTS;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_CHECK_CONSTRAINTS);
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(
        Keys.SYNTHETIC_FK_CHECK_CONSTRAINTS__SYNTHETIC_PK_TABLE_CONSTRAINTS);
  }

  public TableConstraints tableConstraints() {
    return new TableConstraints(
        this, Keys.SYNTHETIC_FK_CHECK_CONSTRAINTS__SYNTHETIC_PK_TABLE_CONSTRAINTS);
  }

  @Override
  public CheckConstraints as(String alias) {
    return new CheckConstraints(DSL.name(alias), this);
  }

  @Override
  public CheckConstraints as(Name alias) {
    return new CheckConstraints(alias, this);
  }

  /** Rename this table */
  @Override
  public CheckConstraints rename(String name) {
    return new CheckConstraints(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public CheckConstraints rename(Name name) {
    return new CheckConstraints(name, null);
  }
}
