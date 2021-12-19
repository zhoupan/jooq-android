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
package org.jooq.meta.postgres.pg_catalog;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.postgres.pg_catalog.tables.PgClass;
import org.jooq.meta.postgres.pg_catalog.tables.PgConstraint;
import org.jooq.meta.postgres.pg_catalog.tables.PgEnum;
import org.jooq.meta.postgres.pg_catalog.tables.PgIndex;
import org.jooq.meta.postgres.pg_catalog.tables.PgNamespace;
import org.jooq.meta.postgres.pg_catalog.tables.PgSequence;
import org.jooq.meta.postgres.pg_catalog.tables.PgType;

/** A class modelling foreign key relationships and constraints of tables in pg_catalog. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------

  public static final UniqueKey<Record> SYNTHETIC_PK_PG_CLASS =
      Internal.createUniqueKey(
          PgClass.PG_CLASS,
          DSL.name("SYNTHETIC_PK_pg_class"),
          new TableField[] {PgClass.PG_CLASS.OID},
          true);
  public static final UniqueKey<Record> SYNTHETIC_PK_PG_NAMESPACE =
      Internal.createUniqueKey(
          PgNamespace.PG_NAMESPACE,
          DSL.name("SYNTHETIC_PK_pg_namespace"),
          new TableField[] {PgNamespace.PG_NAMESPACE.OID},
          true);
  public static final UniqueKey<Record> SYNTHETIC_PK_PG_TYPE =
      Internal.createUniqueKey(
          PgType.PG_TYPE,
          DSL.name("SYNTHETIC_PK_pg_type"),
          new TableField[] {PgType.PG_TYPE.OID},
          true);

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------

  public static final ForeignKey<Record, Record>
      PG_CLASS__SYNTHETIC_FK_PG_CLASS__SYNTHETIC_PK_PG_NAMESPACE =
          Internal.createForeignKey(
              PgClass.PG_CLASS,
              DSL.name("SYNTHETIC_FK_pg_class__SYNTHETIC_PK_pg_namespace"),
              new TableField[] {PgClass.PG_CLASS.RELNAMESPACE},
              Keys.SYNTHETIC_PK_PG_NAMESPACE,
              new TableField[] {PgNamespace.PG_NAMESPACE.OID},
              true);
  public static final ForeignKey<Record, Record>
      PG_CONSTRAINT__SYNTHETIC_FK_PG_CONSTRAINT__SYNTHETIC_PK_PG_CLASS =
          Internal.createForeignKey(
              PgConstraint.PG_CONSTRAINT,
              DSL.name("SYNTHETIC_FK_pg_constraint__SYNTHETIC_PK_pg_class"),
              new TableField[] {PgConstraint.PG_CONSTRAINT.CONRELID},
              Keys.SYNTHETIC_PK_PG_CLASS,
              new TableField[] {PgClass.PG_CLASS.OID},
              true);
  public static final ForeignKey<Record, Record>
      PG_CONSTRAINT__SYNTHETIC_FK_PG_CONSTRAINT__SYNTHETIC_PK_PG_NAMESPACE =
          Internal.createForeignKey(
              PgConstraint.PG_CONSTRAINT,
              DSL.name("SYNTHETIC_FK_pg_constraint__SYNTHETIC_PK_pg_namespace"),
              new TableField[] {PgConstraint.PG_CONSTRAINT.CONNAMESPACE},
              Keys.SYNTHETIC_PK_PG_NAMESPACE,
              new TableField[] {PgNamespace.PG_NAMESPACE.OID},
              true);
  public static final ForeignKey<Record, Record>
      PG_ENUM__SYNTHETIC_FK_PG_ENUM__SYNTHETIC_PK_PG_TYPE =
          Internal.createForeignKey(
              PgEnum.PG_ENUM,
              DSL.name("SYNTHETIC_FK_pg_enum__SYNTHETIC_PK_pg_type"),
              new TableField[] {PgEnum.PG_ENUM.ENUMTYPID},
              Keys.SYNTHETIC_PK_PG_TYPE,
              new TableField[] {PgType.PG_TYPE.OID},
              true);
  public static final ForeignKey<Record, Record> PG_INDEX__INDEX_CLASS =
      Internal.createForeignKey(
          PgIndex.PG_INDEX,
          DSL.name("INDEX_CLASS"),
          new TableField[] {PgIndex.PG_INDEX.INDEXRELID},
          Keys.SYNTHETIC_PK_PG_CLASS,
          new TableField[] {PgClass.PG_CLASS.OID},
          true);
  public static final ForeignKey<Record, Record> PG_INDEX__TABLE_CLASS =
      Internal.createForeignKey(
          PgIndex.PG_INDEX,
          DSL.name("TABLE_CLASS"),
          new TableField[] {PgIndex.PG_INDEX.INDRELID},
          Keys.SYNTHETIC_PK_PG_CLASS,
          new TableField[] {PgClass.PG_CLASS.OID},
          true);
  public static final ForeignKey<Record, Record>
      PG_SEQUENCE__SYNTHETIC_FK_PG_SEQUENCE__SYNTHETIC_PK_PG_CLASS =
          Internal.createForeignKey(
              PgSequence.PG_SEQUENCE,
              DSL.name("SYNTHETIC_FK_pg_sequence__SYNTHETIC_PK_pg_class"),
              new TableField[] {PgSequence.PG_SEQUENCE.SEQRELID},
              Keys.SYNTHETIC_PK_PG_CLASS,
              new TableField[] {PgClass.PG_CLASS.OID},
              true);
  public static final ForeignKey<Record, Record>
      PG_SEQUENCE__SYNTHETIC_FK_PG_SEQUENCE__SYNTHETIC_PK_PG_TYPE =
          Internal.createForeignKey(
              PgSequence.PG_SEQUENCE,
              DSL.name("SYNTHETIC_FK_pg_sequence__SYNTHETIC_PK_pg_type"),
              new TableField[] {PgSequence.PG_SEQUENCE.SEQTYPID},
              Keys.SYNTHETIC_PK_PG_TYPE,
              new TableField[] {PgType.PG_TYPE.OID},
              true);
  public static final ForeignKey<Record, Record>
      PG_TYPE__SYNTHETIC_FK_PG_TYPE__SYNTHETIC_PK_PG_NAMESPACE =
          Internal.createForeignKey(
              PgType.PG_TYPE,
              DSL.name("SYNTHETIC_FK_pg_type__SYNTHETIC_PK_pg_namespace"),
              new TableField[] {PgType.PG_TYPE.TYPNAMESPACE},
              Keys.SYNTHETIC_PK_PG_NAMESPACE,
              new TableField[] {PgNamespace.PG_NAMESPACE.OID},
              true);
}
