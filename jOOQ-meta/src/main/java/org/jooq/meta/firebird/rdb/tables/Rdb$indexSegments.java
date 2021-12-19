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
package org.jooq.meta.firebird.rdb.tables;

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
import org.jooq.meta.firebird.rdb.DefaultSchema;
import org.jooq.meta.firebird.rdb.Keys;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Rdb$indexSegments extends TableImpl<Record> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>RDB$INDEX_SEGMENTS</code> */
  public static final Rdb$indexSegments RDB$INDEX_SEGMENTS = new Rdb$indexSegments();

  /** The class holding records for this type */
  @Override
  public Class<Record> getRecordType() {
    return Record.class;
  }

  /** The column <code>RDB$INDEX_SEGMENTS.RDB$INDEX_NAME</code>. */
  public final TableField<Record, String> RDB$INDEX_NAME =
      createField(DSL.name("RDB$INDEX_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$INDEX_SEGMENTS.RDB$FIELD_NAME</code>. */
  public final TableField<Record, String> RDB$FIELD_NAME =
      createField(DSL.name("RDB$FIELD_NAME"), SQLDataType.CHAR(31), this, "");

  /** The column <code>RDB$INDEX_SEGMENTS.RDB$FIELD_POSITION</code>. */
  public final TableField<Record, Short> RDB$FIELD_POSITION =
      createField(DSL.name("RDB$FIELD_POSITION"), SQLDataType.SMALLINT, this, "");

  /** The column <code>RDB$INDEX_SEGMENTS.RDB$STATISTICS</code>. */
  public final TableField<Record, Double> RDB$STATISTICS =
      createField(DSL.name("RDB$STATISTICS"), SQLDataType.DOUBLE, this, "");

  private Rdb$indexSegments(Name alias, Table<Record> aliased) {
    this(alias, aliased, null);
  }

  private Rdb$indexSegments(Name alias, Table<Record> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /** Create an aliased <code>RDB$INDEX_SEGMENTS</code> table reference */
  public Rdb$indexSegments(String alias) {
    this(DSL.name(alias), RDB$INDEX_SEGMENTS);
  }

  /** Create an aliased <code>RDB$INDEX_SEGMENTS</code> table reference */
  public Rdb$indexSegments(Name alias) {
    this(alias, RDB$INDEX_SEGMENTS);
  }

  /** Create a <code>RDB$INDEX_SEGMENTS</code> table reference */
  public Rdb$indexSegments() {
    this(DSL.name("RDB$INDEX_SEGMENTS"), null);
  }

  public <O extends Record> Rdb$indexSegments(Table<O> child, ForeignKey<O, Record> key) {
    super(child, key, RDB$INDEX_SEGMENTS);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
  }

  @Override
  public List<ForeignKey<Record, ?>> getReferences() {
    return Arrays.asList(Keys.SYNTHETIC_FK_RDB$INDEX_SEGMENTS__RDB$INDEX_5);
  }

  private transient Rdb$indices _rdb$indices;

  public Rdb$indices rdb$indices() {
    if (_rdb$indices == null)
      _rdb$indices = new Rdb$indices(this, Keys.SYNTHETIC_FK_RDB$INDEX_SEGMENTS__RDB$INDEX_5);

    return _rdb$indices;
  }

  @Override
  public Rdb$indexSegments as(String alias) {
    return new Rdb$indexSegments(DSL.name(alias), this);
  }

  @Override
  public Rdb$indexSegments as(Name alias) {
    return new Rdb$indexSegments(alias, this);
  }

  /** Rename this table */
  @Override
  public Rdb$indexSegments rename(String name) {
    return new Rdb$indexSegments(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Rdb$indexSegments rename(Name name) {
    return new Rdb$indexSegments(name, null);
  }
}
