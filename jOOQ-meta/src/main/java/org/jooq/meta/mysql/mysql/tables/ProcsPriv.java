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
package org.jooq.meta.mysql.mysql.tables;

import java.sql.Timestamp;
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
import org.jooq.impl.TableImpl;
import org.jooq.meta.mysql.mysql.Keys;
import org.jooq.meta.mysql.mysql.Mysql;
import org.jooq.meta.mysql.mysql.enums.ProcsPrivRoutineType;

/** Procedure privileges */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class ProcsPriv extends TableImpl<Record> {

  private static final long serialVersionUID = -721047867;

  /** The reference instance of <code>mysql.procs_priv</code> */
  public static final ProcsPriv PROCS_PRIV = new ProcsPriv();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>mysql.procs_priv.Host</code>. */
  public final TableField<Record, String> HOST =
      createField(
          DSL.name("Host"),
          org.jooq.impl.SQLDataType.CHAR(60)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.procs_priv.Db</code>. */
  public final TableField<Record, String> DB =
      createField(
          DSL.name("Db"),
          org.jooq.impl.SQLDataType.CHAR(64)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.procs_priv.User</code>. */
  public final TableField<Record, String> USER =
      createField(
          DSL.name("User"),
          org.jooq.impl.SQLDataType.CHAR(32)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.procs_priv.Routine_name</code>. */
  public final TableField<Record, String> ROUTINE_NAME =
      createField(
          DSL.name("Routine_name"),
          org.jooq.impl.SQLDataType.CHAR(64)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.procs_priv.Routine_type</code>. */
  public final TableField<Record, ProcsPrivRoutineType> ROUTINE_TYPE =
      createField(
          DSL.name("Routine_type"),
          org.jooq.impl.SQLDataType.VARCHAR(9)
              .nullable(false)
              .asEnumDataType(org.jooq.meta.mysql.mysql.enums.ProcsPrivRoutineType.class),
          this,
          "");

  /** The column <code>mysql.procs_priv.Grantor</code>. */
  public final TableField<Record, String> GRANTOR =
      createField(
          DSL.name("Grantor"),
          org.jooq.impl.SQLDataType.CHAR(93)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.procs_priv.Proc_priv</code>. */
  public final TableField<Record, String> PROC_PRIV =
      createField(
          DSL.name("Proc_priv"),
          org.jooq.impl.SQLDataType.VARCHAR(27)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)),
          this,
          "");

  /** The column <code>mysql.procs_priv.Timestamp</code>. */
  public final TableField<Record, Timestamp> TIMESTAMP =
      createField(
          DSL.name("Timestamp"),
          org.jooq.impl.SQLDataType.TIMESTAMP(0)
              .nullable(false)
              .defaultValue(
                  org.jooq.impl.DSL.field(
                      "CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)),
          this,
          "");

  private ProcsPriv(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private ProcsPriv(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("Procedure privileges"),
        TableOptions.table());
  }

  /** Create an aliased <code>mysql.procs_priv</code> table reference */
  public ProcsPriv(String alias) {
    this(DSL.name(alias), PROCS_PRIV);
  }

  /** Create an aliased <code>mysql.procs_priv</code> table reference */
  public ProcsPriv(Name alias) {
    this(alias, PROCS_PRIV);
  }

  /** Create a <code>mysql.procs_priv</code> table reference */
  public ProcsPriv() {
    this(DSL.name("procs_priv"), null);
  }

  public <O extends Record> ProcsPriv(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, PROCS_PRIV);
  }

  @Override
  public Schema getSchema() {
    return Mysql.MYSQL;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.KEY_PROCS_PRIV_PRIMARY;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.KEY_PROCS_PRIV_PRIMARY);
  }

  @Override
  public ProcsPriv as(String alias) {
    return new ProcsPriv(DSL.name(alias), this);
  }

  @Override
  public ProcsPriv as(Name alias) {
    return new ProcsPriv(alias, this);
  }

  /** Rename this table */
  @Override
  public ProcsPriv rename(String name) {
    return new ProcsPriv(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public ProcsPriv rename(Name name) {
    return new ProcsPriv(name, null);
  }
}
