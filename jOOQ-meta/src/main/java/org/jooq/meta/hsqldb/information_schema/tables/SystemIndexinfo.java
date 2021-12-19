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

/** information about the indices of each accessible table defined within this database */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SystemIndexinfo extends TableImpl<Record> {

  private static final long serialVersionUID = -19572269;

  /** The reference instance of <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO</code> */
  public static final SystemIndexinfo SYSTEM_INDEXINFO = new SystemIndexinfo();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.TABLE_CAT</code>. catalog in which the
   * table using the index is defined
   */
  public final TableField<Record, String> TABLE_CAT =
      createField(
          DSL.name("TABLE_CAT"),
          SQLDataType.VARCHAR(128),
          this,
          "catalog in which the table using the index is defined");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.TABLE_SCHEM</code>. schema in which the
   * table using the index is defined
   */
  public final TableField<Record, String> TABLE_SCHEM =
      createField(
          DSL.name("TABLE_SCHEM"),
          SQLDataType.VARCHAR(128),
          this,
          "schema in which the table using the index is defined");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.TABLE_NAME</code>. simple name of the
   * table using the index
   */
  public final TableField<Record, String> TABLE_NAME =
      createField(
          DSL.name("TABLE_NAME"),
          SQLDataType.VARCHAR(128),
          this,
          "simple name of the table using the index");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.NON_UNIQUE</code>. can index values be
   * non-unique?
   */
  public final TableField<Record, Boolean> NON_UNIQUE =
      createField(
          DSL.name("NON_UNIQUE"), SQLDataType.BOOLEAN, this, "can index values be non-unique?");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.INDEX_QUALIFIER</code>. catalog in which
   * the index is defined
   */
  public final TableField<Record, String> INDEX_QUALIFIER =
      createField(
          DSL.name("INDEX_QUALIFIER"),
          SQLDataType.VARCHAR(128),
          this,
          "catalog in which the index is defined");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.INDEX_NAME</code>. simple name of the
   * index
   */
  public final TableField<Record, String> INDEX_NAME =
      createField(
          DSL.name("INDEX_NAME"), SQLDataType.VARCHAR(128), this, "simple name of the index");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.TYPE</code>. index type: e.g. { Clustered
   * | Hashed | Other }
   */
  public final TableField<Record, Short> TYPE =
      createField(
          DSL.name("TYPE"),
          SQLDataType.SMALLINT,
          this,
          "index type: e.g. { Clustered | Hashed | Other }");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.ORDINAL_POSITION</code>. column sequence
   * number within index
   */
  public final TableField<Record, Short> ORDINAL_POSITION =
      createField(
          DSL.name("ORDINAL_POSITION"),
          SQLDataType.SMALLINT,
          this,
          "column sequence number within index");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.COLUMN_NAME</code>. simple column name */
  public final TableField<Record, String> COLUMN_NAME =
      createField(DSL.name("COLUMN_NAME"), SQLDataType.VARCHAR(128), this, "simple column name");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.ASC_OR_DESC</code>. column sort sequence:
   * e.g. { "A" (Ascending) | "D" (Descending) }
   */
  public final TableField<Record, String> ASC_OR_DESC =
      createField(
          DSL.name("ASC_OR_DESC"),
          SQLDataType.VARCHAR(65536),
          this,
          "column sort sequence: e.g. { \"A\" (Ascending) | \"D\" (Descending) }");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.CARDINALITY</code>. index cardinality: #
   * of unique values in the index (currently unused)
   */
  public final TableField<Record, Long> CARDINALITY =
      createField(
          DSL.name("CARDINALITY"),
          SQLDataType.BIGINT,
          this,
          "index cardinality: # of unique values in the index (currently unused)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.PAGES</code>. index page use (currently
   * unused)
   */
  public final TableField<Record, Long> PAGES =
      createField(DSL.name("PAGES"), SQLDataType.BIGINT, this, "index page use (currently unused)");

  /**
   * The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.FILTER_CONDITION</code>. filter condition,
   * if any (currently unused)
   */
  public final TableField<Record, String> FILTER_CONDITION =
      createField(
          DSL.name("FILTER_CONDITION"),
          SQLDataType.VARCHAR(65536),
          this,
          "filter condition, if any (currently unused)");

  /** The column <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO.ROW_CARDINALITY</code>. */
  public final TableField<Record, Integer> ROW_CARDINALITY =
      createField(DSL.name("ROW_CARDINALITY"), SQLDataType.INTEGER, this, "");

  private SystemIndexinfo(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private SystemIndexinfo(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment(
            "information about the indices of each accessible table defined within this database"),
        TableOptions.table());
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO</code> table reference */
  public SystemIndexinfo(String alias) {
    this(DSL.name(alias), SYSTEM_INDEXINFO);
  }

  /** Create an aliased <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO</code> table reference */
  public SystemIndexinfo(Name alias) {
    this(alias, SYSTEM_INDEXINFO);
  }

  /** Create a <code>INFORMATION_SCHEMA.SYSTEM_INDEXINFO</code> table reference */
  public SystemIndexinfo() {
    this(DSL.name("SYSTEM_INDEXINFO"), null);
  }

  public <O extends Record> SystemIndexinfo(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, SYSTEM_INDEXINFO);
  }

  @Override
  public Schema getSchema() {
    return InformationSchema.INFORMATION_SCHEMA;
  }

  @Override
  public SystemIndexinfo as(String alias) {
    return new SystemIndexinfo(DSL.name(alias), this);
  }

  @Override
  public SystemIndexinfo as(Name alias) {
    return new SystemIndexinfo(alias, this);
  }

  /** Rename this table */
  @Override
  public SystemIndexinfo rename(String name) {
    return new SystemIndexinfo(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public SystemIndexinfo rename(Name name) {
    return new SystemIndexinfo(name, null);
  }
}
