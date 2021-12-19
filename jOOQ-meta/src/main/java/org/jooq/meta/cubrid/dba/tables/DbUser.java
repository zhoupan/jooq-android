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
package org.jooq.meta.cubrid.dba.tables;

/** This class is generated by jOOQ. */
@java.lang.SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DbUser extends org.jooq.impl.TableImpl<org.jooq.Record> {

  private static final long serialVersionUID = -394454388;

  /** The singleton instance of <code>db_user</code> */
  public static final org.jooq.meta.cubrid.dba.tables.DbUser DB_USER =
      new org.jooq.meta.cubrid.dba.tables.DbUser();

  /** The class holding records for this type */
  @Override
  public java.lang.Class<org.jooq.Record> getRecordType() {
    return org.jooq.Record.class;
  }

  /** The column <code>db_user.name</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.String> NAME =
      createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(1073741823), this, "");

  /** The column <code>db_user.id</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.Integer> ID =
      createField("id", org.jooq.impl.SQLDataType.INTEGER, this, "");

  /** The column <code>db_user.password</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.Object> PASSWORD =
      createField("password", org.jooq.impl.SQLDataType.OTHER, this, "");

  /** The column <code>db_user.direct_groups</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.Object> DIRECT_GROUPS =
      createField("direct_groups", org.jooq.impl.SQLDataType.OTHER, this, "");

  /** The column <code>db_user.groups</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.Object> GROUPS =
      createField("groups", org.jooq.impl.SQLDataType.OTHER, this, "");

  /** The column <code>db_user.authorization</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.Object> AUTHORIZATION =
      createField("authorization", org.jooq.impl.SQLDataType.OTHER, this, "");

  /** The column <code>db_user.triggers</code>. */
  public final org.jooq.TableField<org.jooq.Record, java.lang.Object> TRIGGERS =
      createField("triggers", org.jooq.impl.SQLDataType.OTHER, this, "");

  /** Create a <code>db_user</code> table reference */
  public DbUser() {
    this("db_user", null);
  }

  /** Create an aliased <code>db_user</code> table reference */
  public DbUser(java.lang.String alias) {
    this(alias, org.jooq.meta.cubrid.dba.tables.DbUser.DB_USER);
  }

  private DbUser(java.lang.String alias, org.jooq.Table<org.jooq.Record> aliased) {
    this(alias, aliased, null);
  }

  private DbUser(
      java.lang.String alias,
      org.jooq.Table<org.jooq.Record> aliased,
      org.jooq.Field<?>[] parameters) {
    super(alias, org.jooq.meta.cubrid.dba.DefaultSchema.DEFAULT_SCHEMA, aliased, parameters, "");
  }

  /** {@inheritDoc} */
  @Override
  public org.jooq.meta.cubrid.dba.tables.DbUser as(java.lang.String alias) {
    return new org.jooq.meta.cubrid.dba.tables.DbUser(alias, this);
  }

  /** Rename this table */
  public org.jooq.meta.cubrid.dba.tables.DbUser rename(java.lang.String name) {
    return new org.jooq.meta.cubrid.dba.tables.DbUser(name, null);
  }
}
