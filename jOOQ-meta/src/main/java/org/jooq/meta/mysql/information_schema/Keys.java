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
package org.jooq.meta.mysql.information_schema;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.mysql.information_schema.tables.CheckConstraints;
import org.jooq.meta.mysql.information_schema.tables.KeyColumnUsage;
import org.jooq.meta.mysql.information_schema.tables.ReferentialConstraints;
import org.jooq.meta.mysql.information_schema.tables.Routines;
import org.jooq.meta.mysql.information_schema.tables.Schemata;
import org.jooq.meta.mysql.information_schema.tables.TableConstraints;
import org.jooq.meta.mysql.information_schema.tables.Tables;
import org.jooq.meta.mysql.information_schema.tables.Views;

/** A class modelling foreign key relationships and constraints of tables in information_schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------
  public static final UniqueKey<Record> SYNTHETIC_PK_ROUTINES =
      Internal.createUniqueKey(
          Routines.ROUTINES,
          DSL.name("SYNTHETIC_PK_ROUTINES"),
          new TableField[] {Routines.ROUTINES.SPECIFIC_NAME},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SCHEMATA =
      Internal.createUniqueKey(
          Schemata.SCHEMATA,
          DSL.name("SYNTHETIC_PK_SCHEMATA"),
          new TableField[] {Schemata.SCHEMATA.CATALOG_NAME, Schemata.SCHEMATA.SCHEMA_NAME},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_TABLE_CONSTRAINTS =
      Internal.createUniqueKey(
          TableConstraints.TABLE_CONSTRAINTS,
          DSL.name("SYNTHETIC_PK_TABLE_CONSTRAINTS"),
          new TableField[] {
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_CATALOG,
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_SCHEMA,
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_NAME
          },
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_TABLES =
      Internal.createUniqueKey(
          Tables.TABLES,
          DSL.name("SYNTHETIC_PK_TABLES"),
          new TableField[] {
            Tables.TABLES.TABLE_CATALOG, Tables.TABLES.TABLE_SCHEMA, Tables.TABLES.TABLE_NAME
          },
          true);

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------
  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_CHECK_CONSTRAINTS__SYNTHETIC_PK_TABLE_CONSTRAINTS =
          Internal.createForeignKey(
              CheckConstraints.CHECK_CONSTRAINTS,
              DSL.name("SYNTHETIC_FK_CHECK_CONSTRAINTS__SYNTHETIC_PK_TABLE_CONSTRAINTS"),
              new TableField[] {
                CheckConstraints.CHECK_CONSTRAINTS.CONSTRAINT_CATALOG,
                CheckConstraints.CHECK_CONSTRAINTS.CONSTRAINT_SCHEMA,
                CheckConstraints.CHECK_CONSTRAINTS.CONSTRAINT_NAME
              },
              Keys.SYNTHETIC_PK_TABLE_CONSTRAINTS,
              new TableField[] {
                TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_CATALOG,
                TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_SCHEMA,
                TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_NAME
              },
              true);

  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_KEY_COLUMN_USAGE__SYNTHETIC_PK_TABLE_CONSTRAINTS =
          Internal.createForeignKey(
              KeyColumnUsage.KEY_COLUMN_USAGE,
              DSL.name("SYNTHETIC_FK_KEY_COLUMN_USAGE__SYNTHETIC_PK_TABLE_CONSTRAINTS"),
              new TableField[] {
                KeyColumnUsage.KEY_COLUMN_USAGE.CONSTRAINT_CATALOG,
                KeyColumnUsage.KEY_COLUMN_USAGE.CONSTRAINT_SCHEMA,
                KeyColumnUsage.KEY_COLUMN_USAGE.CONSTRAINT_NAME
              },
              Keys.SYNTHETIC_PK_TABLE_CONSTRAINTS,
              new TableField[] {
                TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_CATALOG,
                TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_SCHEMA,
                TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_NAME
              },
              true);

  public static final ForeignKey<Record, Record> REFERENCED_CONSTRAINT =
      Internal.createForeignKey(
          ReferentialConstraints.REFERENTIAL_CONSTRAINTS,
          DSL.name("REFERENCED_CONSTRAINT"),
          new TableField[] {
            ReferentialConstraints.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_CATALOG,
            ReferentialConstraints.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_SCHEMA,
            ReferentialConstraints.REFERENTIAL_CONSTRAINTS.UNIQUE_CONSTRAINT_NAME
          },
          Keys.SYNTHETIC_PK_TABLE_CONSTRAINTS,
          new TableField[] {
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_CATALOG,
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_SCHEMA,
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_NAME
          },
          true);

  public static final ForeignKey<Record, Record> REFERENCING_CONSTRAINT =
      Internal.createForeignKey(
          ReferentialConstraints.REFERENTIAL_CONSTRAINTS,
          DSL.name("REFERENCING_CONSTRAINT"),
          new TableField[] {
            ReferentialConstraints.REFERENTIAL_CONSTRAINTS.CONSTRAINT_CATALOG,
            ReferentialConstraints.REFERENTIAL_CONSTRAINTS.CONSTRAINT_SCHEMA,
            ReferentialConstraints.REFERENTIAL_CONSTRAINTS.CONSTRAINT_NAME
          },
          Keys.SYNTHETIC_PK_TABLE_CONSTRAINTS,
          new TableField[] {
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_CATALOG,
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_SCHEMA,
            TableConstraints.TABLE_CONSTRAINTS.CONSTRAINT_NAME
          },
          true);

  public static final ForeignKey<Record, Record> SYNTHETIC_FK_VIEWS__SYNTHETIC_PK_TABLES =
      Internal.createForeignKey(
          Views.VIEWS,
          DSL.name("SYNTHETIC_FK_VIEWS__SYNTHETIC_PK_TABLES"),
          new TableField[] {
            Views.VIEWS.TABLE_CATALOG, Views.VIEWS.TABLE_SCHEMA, Views.VIEWS.TABLE_NAME
          },
          Keys.SYNTHETIC_PK_TABLES,
          new TableField[] {
            Tables.TABLES.TABLE_CATALOG, Tables.TABLES.TABLE_SCHEMA, Tables.TABLES.TABLE_NAME
          },
          true);
}
