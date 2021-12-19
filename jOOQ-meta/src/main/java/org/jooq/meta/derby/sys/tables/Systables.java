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
public class Systables extends TableImpl<Record> {

  private static final long serialVersionUID = -1556687250;

  /** The reference instance of <code>SYS.SYSTABLES</code> */
  public static final Systables SYSTABLES = new Systables();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>SYS.SYSTABLES.TABLEID</code>. */
  public final TableField<Record, String> TABLEID =
      createField(DSL.name("TABLEID"), SQLDataType.CHAR(36).nullable(false), this, "");

  /** The column <code>SYS.SYSTABLES.TABLENAME</code>. */
  public final TableField<Record, String> TABLENAME =
      createField(DSL.name("TABLENAME"), SQLDataType.VARCHAR(128).nullable(false), this, "");

  /** The column <code>SYS.SYSTABLES.TABLETYPE</code>. */
  public final TableField<Record, String> TABLETYPE =
      createField(DSL.name("TABLETYPE"), SQLDataType.CHAR(1).nullable(false), this, "");

  /** The column <code>SYS.SYSTABLES.SCHEMAID</code>. */
  public final TableField<Record, String> SCHEMAID =
      createField(DSL.name("SCHEMAID"), SQLDataType.CHAR(36).nullable(false), this, "");

  /** The column <code>SYS.SYSTABLES.LOCKGRANULARITY</code>. */
  public final TableField<Record, String> LOCKGRANULARITY =
      createField(DSL.name("LOCKGRANULARITY"), SQLDataType.CHAR(1).nullable(false), this, "");

  private Systables(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Systables(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /** Create an aliased <code>SYS.SYSTABLES</code> table reference */
  public Systables(String alias) {
    this(DSL.name(alias), SYSTABLES);
  }

  /** Create an aliased <code>SYS.SYSTABLES</code> table reference */
  public Systables(Name alias) {
    this(alias, SYSTABLES);
  }

  /** Create a <code>SYS.SYSTABLES</code> table reference */
  public Systables() {
    this(DSL.name("SYSTABLES"), null);
  }

  public <O extends Record> Systables(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, SYSTABLES);
  }

  @Override
  public Schema getSchema() {
    return Sys.SYS;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.SYNTHETIC_PK_SYSTABLES;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.SYNTHETIC_PK_SYSTABLES);
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.<ForeignKey<Record, ?>>asList(
        Keys.SYNTHETIC_FK_SYSTABLES__SYNTHETIC_PK_SYSSCHEMAS);
  }

  public Sysschemas sysschemas() {
    return new Sysschemas(this, Keys.SYNTHETIC_FK_SYSTABLES__SYNTHETIC_PK_SYSSCHEMAS);
  }

  @Override
  public Systables as(String alias) {
    return new Systables(DSL.name(alias), this);
  }

  @Override
  public Systables as(Name alias) {
    return new Systables(alias, this);
  }

  /** Rename this table */
  @Override
  public Systables rename(String name) {
    return new Systables(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Systables rename(Name name) {
    return new Systables(name, null);
  }
}
