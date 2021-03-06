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

/** one row for each check constraint included in a domain definition */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DomainConstraints extends TableImpl<Record> {

  private static final long serialVersionUID = -753526410;

  /** The reference instance of <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS</code> */
  public static final DomainConstraints DOMAIN_CONSTRAINTS = new DomainConstraints();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.CONSTRAINT_CATALOG</code>. */
  public final TableField<Record, String> CONSTRAINT_CATALOG =
      createField(DSL.name("CONSTRAINT_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.CONSTRAINT_SCHEMA</code>. */
  public final TableField<Record, String> CONSTRAINT_SCHEMA =
      createField(DSL.name("CONSTRAINT_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.CONSTRAINT_NAME</code>. */
  public final TableField<Record, String> CONSTRAINT_NAME =
      createField(DSL.name("CONSTRAINT_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.DOMAIN_CATALOG</code>. */
  public final TableField<Record, String> DOMAIN_CATALOG =
      createField(DSL.name("DOMAIN_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.DOMAIN_SCHEMA</code>. */
  public final TableField<Record, String> DOMAIN_SCHEMA =
      createField(DSL.name("DOMAIN_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.DOMAIN_NAME</code>. */
  public final TableField<Record, String> DOMAIN_NAME =
      createField(DSL.name("DOMAIN_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.IS_DEFERRABLE</code>. */
  public final TableField<Record, String> IS_DEFERRABLE =
      createField(DSL.name("IS_DEFERRABLE"), SQLDataType.VARCHAR(3), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS.INITIALLY_DEFERRED</code>. */
  public final TableField<Record, String> INITIALLY_DEFERRED =
      createField(DSL.name("INITIALLY_DEFERRED"), SQLDataType.VARCHAR(3), this, "");

  private DomainConstraints(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private DomainConstraints(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each check constraint included in a domain definition"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS</code> table reference */
  public DomainConstraints(String alias) {
    this(DSL.name(alias), DOMAIN_CONSTRAINTS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS</code> table reference */
  public DomainConstraints(Name alias) {
    this(alias, DOMAIN_CONSTRAINTS);
  }

  /** Create a <code>INFORMATION_SCHEMA.DOMAIN_CONSTRAINTS</code> table reference */
  public DomainConstraints() {
    this(DSL.name("DOMAIN_CONSTRAINTS"), null);
  }

  public <O extends Record> DomainConstraints(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, DOMAIN_CONSTRAINTS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(
        Keys.SYNTHETIC_FK_DOMAIN_CONSTRAINTS__SYNTHETIC_PK_CHECK_CONSTRAINTS,
        Keys.SYNTHETIC_FK_DOMAIN_CONSTRAINTS__SYNTHETIC_PK_DOMAINS);
  }

  public CheckConstraints checkConstraints() {
    return new CheckConstraints(
        this, Keys.SYNTHETIC_FK_DOMAIN_CONSTRAINTS__SYNTHETIC_PK_CHECK_CONSTRAINTS);
  }

  public Domains domains() {
    return new Domains(this, Keys.SYNTHETIC_FK_DOMAIN_CONSTRAINTS__SYNTHETIC_PK_DOMAINS);
  }

  @Override
  public DomainConstraints as(String alias) {
    return new DomainConstraints(DSL.name(alias), this);
  }

  @Override
  public DomainConstraints as(Name alias) {
    return new DomainConstraints(alias, this);
  }

  /** Rename this table */
  @Override
  public DomainConstraints rename(String name) {
    return new DomainConstraints(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public DomainConstraints rename(Name name) {
    return new DomainConstraints(name, null);
  }
}
