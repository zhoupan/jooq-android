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
import org.jooq.meta.mysql.mysql.enums.ProcIsDeterministic;
import org.jooq.meta.mysql.mysql.enums.ProcLanguage;
import org.jooq.meta.mysql.mysql.enums.ProcSecurityType;
import org.jooq.meta.mysql.mysql.enums.ProcSqlDataAccess;
import org.jooq.meta.mysql.mysql.enums.ProcType;

/** Stored Procedures */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Proc extends TableImpl<Record> {

  private static final long serialVersionUID = -540297129;

  /** The reference instance of <code>mysql.proc</code> */
  public static final Proc PROC = new Proc();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>mysql.proc.db</code>. */
  public final TableField<Record, String> DB =
      createField(
          DSL.name("db"),
          org.jooq.impl.SQLDataType.CHAR(64)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.proc.name</code>. */
  public final TableField<Record, String> NAME =
      createField(
          DSL.name("name"),
          org.jooq.impl.SQLDataType.CHAR(64)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.proc.type</code>. */
  public final TableField<Record, ProcType> TYPE =
      createField(
          DSL.name("type"),
          org.jooq.impl.SQLDataType.VARCHAR(9)
              .nullable(false)
              .asEnumDataType(org.jooq.meta.mysql.mysql.enums.ProcType.class),
          this,
          "");

  /** The column <code>mysql.proc.specific_name</code>. */
  public final TableField<Record, String> SPECIFIC_NAME =
      createField(
          DSL.name("specific_name"),
          org.jooq.impl.SQLDataType.CHAR(64)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.proc.language</code>. */
  public final TableField<Record, ProcLanguage> LANGUAGE =
      createField(
          DSL.name("language"),
          org.jooq.impl.SQLDataType.VARCHAR(3)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("SQL", org.jooq.impl.SQLDataType.VARCHAR))
              .asEnumDataType(org.jooq.meta.mysql.mysql.enums.ProcLanguage.class),
          this,
          "");

  /** The column <code>mysql.proc.sql_data_access</code>. */
  public final TableField<Record, ProcSqlDataAccess> SQL_DATA_ACCESS =
      createField(
          DSL.name("sql_data_access"),
          org.jooq.impl.SQLDataType.VARCHAR(17)
              .nullable(false)
              .defaultValue(
                  org.jooq.impl.DSL.inline("CONTAINS_SQL", org.jooq.impl.SQLDataType.VARCHAR))
              .asEnumDataType(org.jooq.meta.mysql.mysql.enums.ProcSqlDataAccess.class),
          this,
          "");

  /** The column <code>mysql.proc.is_deterministic</code>. */
  public final TableField<Record, ProcIsDeterministic> IS_DETERMINISTIC =
      createField(
          DSL.name("is_deterministic"),
          org.jooq.impl.SQLDataType.VARCHAR(3)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("NO", org.jooq.impl.SQLDataType.VARCHAR))
              .asEnumDataType(org.jooq.meta.mysql.mysql.enums.ProcIsDeterministic.class),
          this,
          "");

  /** The column <code>mysql.proc.security_type</code>. */
  public final TableField<Record, ProcSecurityType> SECURITY_TYPE =
      createField(
          DSL.name("security_type"),
          org.jooq.impl.SQLDataType.VARCHAR(7)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("DEFINER", org.jooq.impl.SQLDataType.VARCHAR))
              .asEnumDataType(org.jooq.meta.mysql.mysql.enums.ProcSecurityType.class),
          this,
          "");

  /** The column <code>mysql.proc.param_list</code>. */
  public final TableField<Record, byte[]> PARAM_LIST =
      createField(DSL.name("param_list"), org.jooq.impl.SQLDataType.BLOB.nullable(false), this, "");

  /** The column <code>mysql.proc.returns</code>. */
  public final TableField<Record, byte[]> RETURNS =
      createField(DSL.name("returns"), org.jooq.impl.SQLDataType.BLOB.nullable(false), this, "");

  /** The column <code>mysql.proc.body</code>. */
  public final TableField<Record, byte[]> BODY =
      createField(DSL.name("body"), org.jooq.impl.SQLDataType.BLOB.nullable(false), this, "");

  /** The column <code>mysql.proc.definer</code>. */
  public final TableField<Record, String> DEFINER =
      createField(
          DSL.name("definer"),
          org.jooq.impl.SQLDataType.CHAR(93)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.CHAR)),
          this,
          "");

  /** The column <code>mysql.proc.created</code>. */
  public final TableField<Record, Timestamp> CREATED =
      createField(
          DSL.name("created"),
          org.jooq.impl.SQLDataType.TIMESTAMP(0)
              .nullable(false)
              .defaultValue(
                  org.jooq.impl.DSL.field(
                      "CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)),
          this,
          "");

  /** The column <code>mysql.proc.modified</code>. */
  public final TableField<Record, Timestamp> MODIFIED =
      createField(
          DSL.name("modified"),
          org.jooq.impl.SQLDataType.TIMESTAMP(0)
              .nullable(false)
              .defaultValue(
                  org.jooq.impl.DSL.inline(
                      "0000-00-00 00:00:00", org.jooq.impl.SQLDataType.TIMESTAMP)),
          this,
          "");

  /** The column <code>mysql.proc.sql_mode</code>. */
  public final TableField<Record, String> SQL_MODE =
      createField(
          DSL.name("sql_mode"),
          org.jooq.impl.SQLDataType.VARCHAR(478)
              .nullable(false)
              .defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)),
          this,
          "");

  /** The column <code>mysql.proc.comment</code>. */
  public final TableField<Record, String> COMMENT =
      createField(DSL.name("comment"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

  /** The column <code>mysql.proc.character_set_client</code>. */
  public final TableField<Record, String> CHARACTER_SET_CLIENT =
      createField(DSL.name("character_set_client"), org.jooq.impl.SQLDataType.CHAR(32), this, "");

  /** The column <code>mysql.proc.collation_connection</code>. */
  public final TableField<Record, String> COLLATION_CONNECTION =
      createField(DSL.name("collation_connection"), org.jooq.impl.SQLDataType.CHAR(32), this, "");

  /** The column <code>mysql.proc.db_collation</code>. */
  public final TableField<Record, String> DB_COLLATION =
      createField(DSL.name("db_collation"), org.jooq.impl.SQLDataType.CHAR(32), this, "");

  /** The column <code>mysql.proc.body_utf8</code>. */
  public final TableField<Record, byte[]> BODY_UTF8 =
      createField(DSL.name("body_utf8"), org.jooq.impl.SQLDataType.BLOB, this, "");

  private Proc(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Proc(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment("Stored Procedures"), TableOptions.table());
  }

  /** Create an aliased <code>mysql.proc</code> table reference */
  public Proc(String alias) {
    this(DSL.name(alias), PROC);
  }

  /** Create an aliased <code>mysql.proc</code> table reference */
  public Proc(Name alias) {
    this(alias, PROC);
  }

  /** Create a <code>mysql.proc</code> table reference */
  public Proc() {
    this(DSL.name("proc"), null);
  }

  public <O extends Record> Proc(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, PROC);
  }

  @Override
  public Schema getSchema() {
    return Mysql.MYSQL;
  }

  @Override
  public UniqueKey<Record> getPrimaryKey() {
    return Keys.KEY_PROC_PRIMARY;
  }

  @Override
  public List<UniqueKey<Record>> getKeys() {
    return Arrays.<UniqueKey<Record>>asList(Keys.KEY_PROC_PRIMARY);
  }

  @Override
  public Proc as(String alias) {
    return new Proc(DSL.name(alias), this);
  }

  @Override
  public Proc as(Name alias) {
    return new Proc(alias, this);
  }

  /** Rename this table */
  @Override
  public Proc rename(String name) {
    return new Proc(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Proc rename(Name name) {
    return new Proc(name, null);
  }
}
