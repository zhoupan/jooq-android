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
package org.jooq.meta.postgres.information_schema;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.postgres.information_schema.tables.CheckConstraints;
import org.jooq.meta.postgres.information_schema.tables.Domains;
import org.jooq.meta.postgres.information_schema.tables.Schemata;
import org.jooq.meta.postgres.information_schema.tables.Sequences;
import org.jooq.meta.postgres.information_schema.tables.Tables;
import org.jooq.meta.postgres.information_schema.tables.Views;

/** A class modelling foreign key relationships and constraints of tables in information_schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------
  public static final UniqueKey<Record> SYNTHETIC_PK_CHECK_CONSTRAINTS =
      Internal.createUniqueKey(
          CheckConstraints.CHECK_CONSTRAINTS,
          DSL.name("SYNTHETIC_PK_check_constraints"),
          new TableField[] {
            CheckConstraints.CHECK_CONSTRAINTS.CONSTRAINT_CATALOG,
            CheckConstraints.CHECK_CONSTRAINTS.CONSTRAINT_SCHEMA,
            CheckConstraints.CHECK_CONSTRAINTS.CONSTRAINT_NAME
          },
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_DOMAINS =
      Internal.createUniqueKey(
          Domains.DOMAINS,
          DSL.name("SYNTHETIC_PK_domains"),
          new TableField[] {
            Domains.DOMAINS.DOMAIN_CATALOG,
            Domains.DOMAINS.DOMAIN_SCHEMA,
            Domains.DOMAINS.DOMAIN_NAME
          },
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SCHEMATA =
      Internal.createUniqueKey(
          Schemata.SCHEMATA,
          DSL.name("SYNTHETIC_PK_schemata"),
          new TableField[] {Schemata.SCHEMATA.CATALOG_NAME, Schemata.SCHEMATA.SCHEMA_NAME},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SEQUENCES =
      Internal.createUniqueKey(
          Sequences.SEQUENCES,
          DSL.name("SYNTHETIC_PK_sequences"),
          new TableField[] {
            Sequences.SEQUENCES.SEQUENCE_CATALOG,
            Sequences.SEQUENCES.SEQUENCE_SCHEMA,
            Sequences.SEQUENCES.SEQUENCE_NAME
          },
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_TABLES =
      Internal.createUniqueKey(
          Tables.TABLES,
          DSL.name("SYNTHETIC_PK_tables"),
          new TableField[] {
            Tables.TABLES.TABLE_CATALOG, Tables.TABLES.TABLE_SCHEMA, Tables.TABLES.TABLE_NAME
          },
          true);

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------
  public static final ForeignKey<Record, Record>
      SEQUENCES__SYNTHETIC_FK_SEQUENCES__SYNTHETIC_PK_SCHEMATA =
          Internal.createForeignKey(
              Sequences.SEQUENCES,
              DSL.name("SYNTHETIC_FK_sequences__SYNTHETIC_PK_schemata"),
              new TableField[] {
                Sequences.SEQUENCES.SEQUENCE_CATALOG, Sequences.SEQUENCES.SEQUENCE_SCHEMA
              },
              Keys.SYNTHETIC_PK_SCHEMATA,
              new TableField[] {Schemata.SCHEMATA.CATALOG_NAME, Schemata.SCHEMATA.SCHEMA_NAME},
              true);

  public static final ForeignKey<Record, Record> VIEWS__SYNTHETIC_FK_VIEWS__SYNTHETIC_PK_TABLES =
      Internal.createForeignKey(
          Views.VIEWS,
          DSL.name("SYNTHETIC_FK_views__SYNTHETIC_PK_tables"),
          new TableField[] {
            Views.VIEWS.TABLE_CATALOG, Views.VIEWS.TABLE_SCHEMA, Views.VIEWS.TABLE_NAME
          },
          Keys.SYNTHETIC_PK_TABLES,
          new TableField[] {
            Tables.TABLES.TABLE_CATALOG, Tables.TABLES.TABLE_SCHEMA, Tables.TABLES.TABLE_NAME
          },
          true);
}
