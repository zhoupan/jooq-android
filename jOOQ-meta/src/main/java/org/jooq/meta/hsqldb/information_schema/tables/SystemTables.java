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

/** the accessible tables defined within this database */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SystemTables extends TableImpl<Record> {

  private static final long serialVersionUID = 930463233;

  /** The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_TABLES</code> */
  public static final SystemTables SYSTEM_TABLES = new SystemTables();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TABLE_CAT</code>. table catalog */
  public final TableField<Record, String> TABLE_CAT =
      createField(DSL.name("TABLE_CAT"), SQLDataType.VARCHAR(128), this, "table catalog");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TABLE_SCHEM</code>. table schema */
  public final TableField<Record, String> TABLE_SCHEM =
      createField(DSL.name("TABLE_SCHEM"), SQLDataType.VARCHAR(128), this, "table schema");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TABLE_NAME</code>. table name */
  public final TableField<Record, String> TABLE_NAME =
      createField(DSL.name("TABLE_NAME"), SQLDataType.VARCHAR(128), this, "table name");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TABLE_TYPE</code>. table type: e.g. one of {
   * TABLE | VIEW | SYSTEM TABLE | GLOBAL TEMPORARY ... }
   */
  public final TableField<Record, String> TABLE_TYPE =
      createField(
          DSL.name("TABLE_TYPE"),
          SQLDataType.VARCHAR(65536),
          this,
          "table type: e.g. one of { TABLE | VIEW | SYSTEM TABLE | GLOBAL TEMPORARY ... }");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.REMARKS</code>. explanatory comment on the
   * table
   */
  public final TableField<Record, String> REMARKS =
      createField(
          DSL.name("REMARKS"),
          SQLDataType.VARCHAR(65536),
          this,
          "explanatory comment on the table");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TYPE_CAT</code>. table type catalog */
  public final TableField<Record, String> TYPE_CAT =
      createField(DSL.name("TYPE_CAT"), SQLDataType.VARCHAR(128), this, "table type catalog");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TYPE_SCHEM</code>. table type schema */
  public final TableField<Record, String> TYPE_SCHEM =
      createField(DSL.name("TYPE_SCHEM"), SQLDataType.VARCHAR(128), this, "table type schema");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.TYPE_NAME</code>. table type name */
  public final TableField<Record, String> TYPE_NAME =
      createField(DSL.name("TYPE_NAME"), SQLDataType.VARCHAR(128), this, "table type name");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.SELF_REFERENCING_COL_NAME</code>. name of the
   * designated "identifier" column of typed table (null if not typed)
   */
  public final TableField<Record, String> SELF_REFERENCING_COL_NAME =
      createField(
          DSL.name("SELF_REFERENCING_COL_NAME"),
          SQLDataType.VARCHAR(128),
          this,
          "name of the designated \"identifier\" column of typed table (null if not typed)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.REF_GENERATION</code>. how are values in
   * SELF_REFERENCING_COL_NAME created?: e.g. one of { "SYSTEM" | "USER" | "DERIVED" | NULL }
   */
  public final TableField<Record, String> REF_GENERATION =
      createField(
          DSL.name("REF_GENERATION"),
          SQLDataType.VARCHAR(65536),
          this,
          "how are values in SELF_REFERENCING_COL_NAME created?: e.g. one of { \"SYSTEM\" | \"USER\" | \"DERIVED\" | NULL }");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.HSQLDB_TYPE</code>. the HSQLDB-specific type
   * of the table, e.g. ( MEMORY | CACHED | TEXT | ...)
   */
  public final TableField<Record, String> HSQLDB_TYPE =
      createField(
          DSL.name("HSQLDB_TYPE"),
          SQLDataType.VARCHAR(128),
          this,
          "the HSQLDB-specific type of the table, e.g. ( MEMORY | CACHED | TEXT | ...)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.READ_ONLY</code>. TRUE if the table is
   * read-only, else FALSE
   */
  public final TableField<Record, Boolean> READ_ONLY =
      createField(
          DSL.name("READ_ONLY"),
          SQLDataType.BOOLEAN,
          this,
          "TRUE if the table is read-only, else FALSE");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_TABLES.COMMIT_ACTION</code>. */
  public final TableField<Record, String> COMMIT_ACTION =
      createField(DSL.name("COMMIT_ACTION"), SQLDataType.VARCHAR(65536), this, "");

  private SystemTables(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private SystemTables(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment("the accessible tables defined within this database"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_TABLES</code> table reference */
  public SystemTables(String alias) {
    this(DSL.name(alias), SYSTEM_TABLES);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_TABLES</code> table reference */
  public SystemTables(Name alias) {
    this(alias, SYSTEM_TABLES);
  }

  /** Create a <code>INFORMATION_SCHEMA.SYSTEM_TABLES</code> table reference */
  public SystemTables() {
    this(DSL.name("SYSTEM_TABLES"), null);
  }

  public <O extends Record> SystemTables(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, SYSTEM_TABLES);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public SystemTables as(String alias) {
    return new SystemTables(DSL.name(alias), this);
  }

  @Override
  public SystemTables as(Name alias) {
    return new SystemTables(alias, this);
  }

  /** Rename this table */
  @Override
  public SystemTables rename(String name) {
    return new SystemTables(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public SystemTables rename(Name name) {
    return new SystemTables(name, null);
  }
}
