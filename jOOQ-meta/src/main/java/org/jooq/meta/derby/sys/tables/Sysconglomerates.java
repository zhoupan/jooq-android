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
package org.jooq.meta.derby.sys.tables;

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
import org.jooq.meta.derby.sys.Keys;
import org.jooq.meta.derby.sys.Sys;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Sysconglomerates extends TableImpl<Record> {

  private static final long serialVersionUID = 679295714;

  /** The reference instance of <code>SYS.SYSCONGLOMERATES</code> */
  public static final Sysconglomerates SYSCONGLOMERATES = new Sysconglomerates();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>SYS.SYSCONGLOMERATES.SCHEMAID</code>. */
  public final TableField<Record, String> SCHEMAID =
      createField(DSL.name("SCHEMAID"), SQLDataType.CHAR(36).nullable(false), this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.TABLEID</code>. */
  public final TableField<Record, String> TABLEID =
      createField(DSL.name("TABLEID"), SQLDataType.CHAR(36).nullable(false), this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.CONGLOMERATENUMBER</code>. */
  public final TableField<Record, Long> CONGLOMERATENUMBER =
      createField(DSL.name("CONGLOMERATENUMBER"), SQLDataType.BIGINT.nullable(false), this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.CONGLOMERATENAME</code>. */
  public final TableField<Record, String> CONGLOMERATENAME =
      createField(DSL.name("CONGLOMERATENAME"), SQLDataType.VARCHAR(128), this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.ISINDEX</code>. */
  public final TableField<Record, Boolean> ISINDEX =
      createField(DSL.name("ISINDEX"), SQLDataType.BOOLEAN.nullable(false), this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.DESCRIPTOR</code>. */
  public final TableField<Record, String> DESCRIPTOR =
      createField(DSL.name("DESCRIPTOR"), SQLDataType.CLOB, this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.ISCONSTRAINT</code>. */
  public final TableField<Record, Boolean> ISCONSTRAINT =
      createField(DSL.name("ISCONSTRAINT"), SQLDataType.BOOLEAN, this, "");

  /** The column <code>SYS.SYSCONGLOMERATES.CONGLOMERATEID</code>. */
  public final TableField<Record, String> CONGLOMERATEID =
      createField(DSL.name("CONGLOMERATEID"), SQLDataType.CHAR(36).nullable(false), this, "");

  private Sysconglomerates(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Sysconglomerates(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /** Create an aliased <code>SYS.SYSCONGLOMERATES</code> table reference */
  public Sysconglomerates(String alias) {
    this(DSL.name(alias), SYSCONGLOMERATES);
  }

  /** Create an aliased <code>SYS.SYSCONGLOMERATES</code> table reference */
  public Sysconglomerates(Name alias) {
    this(alias, SYSCONGLOMERATES);
  }

  /** Create a <code>SYS.SYSCONGLOMERATES</code> table reference */
  public Sysconglomerates() {
    this(DSL.name("SYSCONGLOMERATES"), null);
  }

  public <O extends Record> Sysconglomerates(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, SYSCONGLOMERATES);
  }

  @Override
  public Schema getSchema() {
    return Sys.SYS;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_SYSCONGLOMERATES;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_SYSCONGLOMERATES);
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(
        Keys.SYNTHETIC_FK_SYSCONGLOMERATES__SYNTHETIC_PK_SYSTABLES);
  }

  public Systables systables() {
    return new Systables(this, Keys.SYNTHETIC_FK_SYSCONGLOMERATES__SYNTHETIC_PK_SYSTABLES);
  }

  @Override
  public Sysconglomerates as(String alias) {
    return new Sysconglomerates(DSL.name(alias), this);
  }

  @Override
  public Sysconglomerates as(Name alias) {
    return new Sysconglomerates(alias, this);
  }

  /** Rename this table */
  @Override
  public Sysconglomerates rename(String name) {
    return new Sysconglomerates(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Sysconglomerates rename(Name name) {
    return new Sysconglomerates(name, null);
  }
}
