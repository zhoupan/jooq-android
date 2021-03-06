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

/** one row for each domain identified */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Domains extends TableImpl<Record> {

  private static final long serialVersionUID = 1923709268;

  /** The reference instance of <code>INFORMATION_SCHEMA.DOMAINS</code> */
  public static final Domains DOMAINS = new Domains();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DOMAIN_CATALOG</code>. */
  public final TableField<Record, String> DOMAIN_CATALOG =
      createField(DSL.name("DOMAIN_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DOMAIN_SCHEMA</code>. */
  public final TableField<Record, String> DOMAIN_SCHEMA =
      createField(DSL.name("DOMAIN_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DOMAIN_NAME</code>. */
  public final TableField<Record, String> DOMAIN_NAME =
      createField(DSL.name("DOMAIN_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DATA_TYPE</code>. */
  public final TableField<Record, String> DATA_TYPE =
      createField(DSL.name("DATA_TYPE"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.CHARACTER_MAXIMUM_LENGTH</code>. */
  public final TableField<Record, Long> CHARACTER_MAXIMUM_LENGTH =
      createField(DSL.name("CHARACTER_MAXIMUM_LENGTH"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.CHARACTER_OCTET_LENGTH</code>. */
  public final TableField<Record, Long> CHARACTER_OCTET_LENGTH =
      createField(DSL.name("CHARACTER_OCTET_LENGTH"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.CHARACTER_SET_CATALOG</code>. */
  public final TableField<Record, String> CHARACTER_SET_CATALOG =
      createField(DSL.name("CHARACTER_SET_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.CHARACTER_SET_SCHEMA</code>. */
  public final TableField<Record, String> CHARACTER_SET_SCHEMA =
      createField(DSL.name("CHARACTER_SET_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.CHARACTER_SET_NAME</code>. */
  public final TableField<Record, String> CHARACTER_SET_NAME =
      createField(DSL.name("CHARACTER_SET_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.COLLATION_CATALOG</code>. */
  public final TableField<Record, String> COLLATION_CATALOG =
      createField(DSL.name("COLLATION_CATALOG"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.COLLATION_SCHEMA</code>. */
  public final TableField<Record, String> COLLATION_SCHEMA =
      createField(DSL.name("COLLATION_SCHEMA"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.COLLATION_NAME</code>. */
  public final TableField<Record, String> COLLATION_NAME =
      createField(DSL.name("COLLATION_NAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.NUMERIC_PRECISION</code>. */
  public final TableField<Record, Long> NUMERIC_PRECISION =
      createField(DSL.name("NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.NUMERIC_PRECISION_RADIX</code>. */
  public final TableField<Record, Long> NUMERIC_PRECISION_RADIX =
      createField(DSL.name("NUMERIC_PRECISION_RADIX"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.NUMERIC_SCALE</code>. */
  public final TableField<Record, Long> NUMERIC_SCALE =
      createField(DSL.name("NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DATETIME_PRECISION</code>. */
  public final TableField<Record, Long> DATETIME_PRECISION =
      createField(DSL.name("DATETIME_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.INTERVAL_TYPE</code>. */
  public final TableField<Record, String> INTERVAL_TYPE =
      createField(DSL.name("INTERVAL_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.INTERVAL_PRECISION</code>. */
  public final TableField<Record, Long> INTERVAL_PRECISION =
      createField(DSL.name("INTERVAL_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DOMAIN_DEFAULT</code>. */
  public final TableField<Record, String> DOMAIN_DEFAULT =
      createField(DSL.name("DOMAIN_DEFAULT"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.MAXIMUM_CARDINALITY</code>. */
  public final TableField<Record, Long> MAXIMUM_CARDINALITY =
      createField(DSL.name("MAXIMUM_CARDINALITY"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DTD_IDENTIFIER</code>. */
  public final TableField<Record, String> DTD_IDENTIFIER =
      createField(DSL.name("DTD_IDENTIFIER"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DECLARED_DATA_TYPE</code>. */
  public final TableField<Record, String> DECLARED_DATA_TYPE =
      createField(DSL.name("DECLARED_DATA_TYPE"), SQLDataType.VARCHAR(65536), this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DECLARED_NUMERIC_PRECISION</code>. */
  public final TableField<Record, Long> DECLARED_NUMERIC_PRECISION =
      createField(DSL.name("DECLARED_NUMERIC_PRECISION"), SQLDataType.BIGINT, this, "");

  /** The column <code>INFORMATION_SCHEMA.DOMAINS.DECLARED_NUMERIC_SCALE</code>. */
  public final TableField<Record, Long> DECLARED_NUMERIC_SCALE =
      createField(DSL.name("DECLARED_NUMERIC_SCALE"), SQLDataType.BIGINT, this, "");

  private Domains(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Domains(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("one row for each domain identified"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.DOMAINS</code> table reference */
  public Domains(String alias) {
    this(DSL.name(alias), DOMAINS);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.DOMAINS</code> table reference */
  public Domains(Name alias) {
    this(alias, DOMAINS);
  }

  /** Create a <code>INFORMATION_SCHEMA.DOMAINS</code> table reference */
  public Domains() {
    this(DSL.name("DOMAINS"), null);
  }

  public <O extends Record> Domains(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, DOMAINS);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_DOMAINS;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_DOMAINS);
  }

  @Override
  public Domains as(String alias) {
    return new Domains(DSL.name(alias), this);
  }

  @Override
  public Domains as(Name alias) {
    return new Domains(alias, this);
  }

  /** Rename this table */
  @Override
  public Domains rename(String name) {
    return new Domains(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Domains rename(Name name) {
    return new Domains(name, null);
  }
}
