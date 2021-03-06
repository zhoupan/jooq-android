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

/** one row for each foreign key constraint */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ReferentialConstraints extends TableImpl<Record> {

  private static final long serialVersionUID = 331405816;

  /** The reference instance of <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS</code> */
  public static final ReferentialConstraints REFERENTIAL_CONSTRAINTS = new ReferentialConstraints();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.CONSTRAINT_CATALOG</code>. */
  public final TableField<Record, String> CONSTRAINT_CATALOG =
      createField(DSL.name("CONSTRAINT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.CONSTRAINT_SCHEMA</code>. */
  public final TableField<Record, String> CONSTRAINT_SCHEMA =
      createField(DSL.name("CONSTRAINT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.CONSTRAINT_NAME</code>. */
  public final TableField<Record, String> CONSTRAINT_NAME =
      createField(DSL.name("CONSTRAINT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /**
   * The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_CATALOG</code>.
   */
  public final TableField<Record, String> UNIQUE_CONSTRAINT_CATALOG =
      createField(DSL.name("UNIQUE_CONSTRAINT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /**
   * The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_SCHEMA</code>.
   */
  public final TableField<Record, String> UNIQUE_CONSTRAINT_SCHEMA =
      createField(DSL.name("UNIQUE_CONSTRAINT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_NAME</code>. */
  public final TableField<Record, String> UNIQUE_CONSTRAINT_NAME =
      createField(DSL.name("UNIQUE_CONSTRAINT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.MATCH_OPTION</code>. */
  public final TableField<Record, String> MATCH_OPTION =
      createField(DSL.name("MATCH_OPTION"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.UPDATE_RULE</code>. */
  public final TableField<Record, String> UPDATE_RULE =
      createField(DSL.name("UPDATE_RULE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS.DELETE_RULE</code>. */
  public final TableField<Record, String> DELETE_RULE =
      createField(DSL.name("DELETE_RULE"), SQLDataType.VARCHAR(65536), this, "");

  private ReferentialConstraints(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private ReferentialConstraints(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each foreign key constraint"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS</code> table reference */
  public ReferentialConstraints(String alias) {
    this(DSL.name(alias), REFERENTIAL_CONSTRAINTS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS</code> table reference */
  public ReferentialConstraints(Name alias) {
    this(alias, REFERENTIAL_CONSTRAINTS);
  }

  /** Create a <code>INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS</code> table reference */
  public ReferentialConstraints() {
    this(DSL.name("REFERENTIAL_CONSTRAINTS"), null);
  }

  public <O extends Record> ReferentialConstraints(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, REFERENTIAL_CONSTRAINTS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(
        Keys.REFERENCING_CONSTRAINT, Keys.REFERENCED_CONSTRAINT);
  }

  public TableConstraints referencingConstraint() {
    return new TableConstraints(this, Keys.REFERENCING_CONSTRAINT);
  }

  public TableConstraints referencedConstraint() {
    return new TableConstraints(this, Keys.REFERENCED_CONSTRAINT);
  }

  @Override
  public ReferentialConstraints as(String alias) {
    return new ReferentialConstraints(DSL.name(alias), this);
  }

  @Override
  public ReferentialConstraints as(Name alias) {
    return new ReferentialConstraints(alias, this);
  }

  /** Rename this table */
  @Override
  public ReferentialConstraints rename(String name) {
    return new ReferentialConstraints(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public ReferentialConstraints rename(Name name) {
    return new ReferentialConstraints(name, null);
  }
}
