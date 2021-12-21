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
package org.jooq.meta.h2.information_schema;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.h2.information_schema.tables.Constraints;
import org.jooq.meta.h2.information_schema.tables.CrossReferences;
import org.jooq.meta.h2.information_schema.tables.Domains;
import org.jooq.meta.h2.information_schema.tables.Schemata;
import org.jooq.meta.h2.information_schema.tables.Sequences;
import org.jooq.meta.h2.information_schema.tables.Tables;

/** A class modelling foreign key relationships and constraints of tables in INFORMATION_SCHEMA. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------
  public static final UniqueKey<Record> SYNTHETIC_PK_CONSTRAINTS =
      Internal.createUniqueKey(
          Constraints.CONSTRAINTS,
          DSL.name("SYNTHETIC_PK_CONSTRAINTS"),
          new TableField[] {
            Constraints.CONSTRAINTS.CONSTRAINT_CATALOG,
            Constraints.CONSTRAINTS.CONSTRAINT_SCHEMA,
            Constraints.CONSTRAINTS.CONSTRAINT_NAME
          },
          true);

  public static final UniqueKey<Record> UNIQUE_INDEX_NAME =
      Internal.createUniqueKey(
          Constraints.CONSTRAINTS,
          DSL.name("UNIQUE_INDEX_NAME"),
          new TableField[] {
            Constraints.CONSTRAINTS.TABLE_CATALOG,
            Constraints.CONSTRAINTS.TABLE_SCHEMA,
            Constraints.CONSTRAINTS.UNIQUE_INDEX_NAME
          },
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_DOMAINS =
      Internal.createUniqueKey(
          Domains.DOMAINS,
          DSL.name("SYNTHETIC_PK_DOMAINS"),
          new TableField[] {
            Domains.DOMAINS.DOMAIN_CATALOG,
            Domains.DOMAINS.DOMAIN_SCHEMA,
            Domains.DOMAINS.DOMAIN_NAME
          },
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SCHEMATA =
      Internal.createUniqueKey(
          Schemata.SCHEMATA,
          DSL.name("SYNTHETIC_PK_SCHEMATA"),
          new TableField[] {Schemata.SCHEMATA.CATALOG_NAME, Schemata.SCHEMATA.SCHEMA_NAME},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SEQUENCES =
      Internal.createUniqueKey(
          Sequences.SEQUENCES,
          DSL.name("SYNTHETIC_PK_SEQUENCES"),
          new TableField[] {
            Sequences.SEQUENCES.SEQUENCE_CATALOG,
            Sequences.SEQUENCES.SEQUENCE_SCHEMA,
            Sequences.SEQUENCES.SEQUENCE_NAME
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
  public static final ForeignKey<Record, Record> REFERENCED_CONSTRAINT =
      Internal.createForeignKey(
          CrossReferences.CROSS_REFERENCES,
          DSL.name("REFERENCED_CONSTRAINT"),
          new TableField[] {
            CrossReferences.CROSS_REFERENCES.PKTABLE_CATALOG,
            CrossReferences.CROSS_REFERENCES.PKTABLE_SCHEMA,
            CrossReferences.CROSS_REFERENCES.PK_NAME
          },
          Keys.UNIQUE_INDEX_NAME,
          new TableField[] {
            Constraints.CONSTRAINTS.TABLE_CATALOG,
            Constraints.CONSTRAINTS.TABLE_SCHEMA,
            Constraints.CONSTRAINTS.UNIQUE_INDEX_NAME
          },
          true);

  public static final ForeignKey<Record, Record> REFERENCING_CONSTRAINT =
      Internal.createForeignKey(
          CrossReferences.CROSS_REFERENCES,
          DSL.name("REFERENCING_CONSTRAINT"),
          new TableField[] {
            CrossReferences.CROSS_REFERENCES.FKTABLE_CATALOG,
            CrossReferences.CROSS_REFERENCES.FKTABLE_SCHEMA,
            CrossReferences.CROSS_REFERENCES.FK_NAME
          },
          Keys.SYNTHETIC_PK_CONSTRAINTS,
          new TableField[] {
            Constraints.CONSTRAINTS.CONSTRAINT_CATALOG,
            Constraints.CONSTRAINTS.CONSTRAINT_SCHEMA,
            Constraints.CONSTRAINTS.CONSTRAINT_NAME
          },
          true);
}
