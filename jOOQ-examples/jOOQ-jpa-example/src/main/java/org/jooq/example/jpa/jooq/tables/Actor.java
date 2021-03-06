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
package org.jooq.example.jpa.jooq.tables;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.example.jpa.jooq.DefaultSchema;
import org.jooq.example.jpa.jooq.Keys;
import org.jooq.example.jpa.jooq.tables.records.ActorRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Actor extends TableImpl<ActorRecord> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>ACTOR</code> */
  public static final Actor ACTOR = new Actor();

  /** The class holding records for this type */
  @Override
  public Class<ActorRecord> getRecordType() {
    return ActorRecord.class;
  }

  /** The column <code>ACTOR.ACTORID</code>. */
  public final TableField<ActorRecord, Integer> ACTORID =
      createField(
          DSL.name("ACTORID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

  /** The column <code>ACTOR.FIRSTNAME</code>. */
  public final TableField<ActorRecord, String> FIRSTNAME =
      createField(DSL.name("FIRSTNAME"), SQLDataType.VARCHAR(255), this, "");

  /** The column <code>ACTOR.LASTNAME</code>. */
  public final TableField<ActorRecord, String> LASTNAME =
      createField(DSL.name("LASTNAME"), SQLDataType.VARCHAR(255), this, "");

  private Actor(Name alias, Table<ActorRecord> aliased) {
    this(alias, aliased, null);
  }

  private Actor(Name alias, Table<ActorRecord> aliased, Field<?>[] parameters) {
    super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
  }

  /** Create an aliased <code>ACTOR</code> table reference */
  public Actor(String alias) {
    this(DSL.name(alias), ACTOR);
  }

  /** Create an aliased <code>ACTOR</code> table reference */
  public Actor(Name alias) {
    this(alias, ACTOR);
  }

  /** Create a <code>ACTOR</code> table reference */
  public Actor() {
    this(DSL.name("ACTOR"), null);
  }

  public <O extends Record> Actor(Table<O> child, ForeignKey<O, ActorRecord> key) {
    super(child, key, ACTOR);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
  }

  @Override
  public Identity<ActorRecord, Integer> getIdentity() {
    return (Identity<ActorRecord, Integer>) super.getIdentity();
  }

  @Override
  public UniqueKey<ActorRecord> getPrimaryKey() {
    return Keys.CONSTRAINT_3;
  }

  @Override
  public Actor as(String alias) {
    return new Actor(DSL.name(alias), this);
  }

  @Override
  public Actor as(Name alias) {
    return new Actor(alias, this);
  }

  /** Rename this table */
  @Override
  public Actor rename(String name) {
    return new Actor(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public Actor rename(Name name) {
    return new Actor(name, null);
  }

  // -------------------------------------------------------------------------
  // Row3 type methods
  // -------------------------------------------------------------------------

  @Override
  public Row3<Integer, String, String> fieldsRow() {
    return (Row3) super.fieldsRow();
  }
}
