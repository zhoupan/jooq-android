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
package org.jooq.meta.derby.sys;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.derby.sys.tables.Syschecks;
import org.jooq.meta.derby.sys.tables.Sysconglomerates;
import org.jooq.meta.derby.sys.tables.Sysconstraints;
import org.jooq.meta.derby.sys.tables.Syskeys;
import org.jooq.meta.derby.sys.tables.Sysschemas;
import org.jooq.meta.derby.sys.tables.Syssequences;
import org.jooq.meta.derby.sys.tables.Systables;
import org.jooq.meta.derby.sys.tables.Sysviews;

/** A class modelling foreign key relationships and constraints of tables in SYS. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------
  public static final UniqueKey<Record> SYNTHETIC_PK_SYSCONGLOMERATES =
      Internal.createUniqueKey(
          Sysconglomerates.SYSCONGLOMERATES,
          DSL.name("SYNTHETIC_PK_SYSCONGLOMERATES"),
          new TableField[] {Sysconglomerates.SYSCONGLOMERATES.CONGLOMERATEID},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SYSCONSTRAINTS =
      Internal.createUniqueKey(
          Sysconstraints.SYSCONSTRAINTS,
          DSL.name("SYNTHETIC_PK_SYSCONSTRAINTS"),
          new TableField[] {Sysconstraints.SYSCONSTRAINTS.CONSTRAINTID},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SYSSCHEMAS =
      Internal.createUniqueKey(
          Sysschemas.SYSSCHEMAS,
          DSL.name("SYNTHETIC_PK_SYSSCHEMAS"),
          new TableField[] {Sysschemas.SYSSCHEMAS.SCHEMAID},
          true);

  public static final UniqueKey<Record> SYNTHETIC_PK_SYSTABLES =
      Internal.createUniqueKey(
          Systables.SYSTABLES,
          DSL.name("SYNTHETIC_PK_SYSTABLES"),
          new TableField[] {Systables.SYSTABLES.TABLEID},
          true);

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------
  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_SYSCHECKS__SYNTHETIC_PK_SYSCONSTRAINTS =
          Internal.createForeignKey(
              Syschecks.SYSCHECKS,
              DSL.name("SYNTHETIC_FK_SYSCHECKS__SYNTHETIC_PK_SYSCONSTRAINTS"),
              new TableField[] {Syschecks.SYSCHECKS.CONSTRAINTID},
              Keys.SYNTHETIC_PK_SYSCONSTRAINTS,
              new TableField[] {Sysconstraints.SYSCONSTRAINTS.CONSTRAINTID},
              true);

  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_SYSCONGLOMERATES__SYNTHETIC_PK_SYSTABLES =
          Internal.createForeignKey(
              Sysconglomerates.SYSCONGLOMERATES,
              DSL.name("SYNTHETIC_FK_SYSCONGLOMERATES__SYNTHETIC_PK_SYSTABLES"),
              new TableField[] {Sysconglomerates.SYSCONGLOMERATES.TABLEID},
              Keys.SYNTHETIC_PK_SYSTABLES,
              new TableField[] {Systables.SYSTABLES.TABLEID},
              true);

  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_SYSCONSTRAINTS__SYNTHETIC_PK_SYSSCHEMAS =
          Internal.createForeignKey(
              Sysconstraints.SYSCONSTRAINTS,
              DSL.name("SYNTHETIC_FK_SYSCONSTRAINTS__SYNTHETIC_PK_SYSSCHEMAS"),
              new TableField[] {Sysconstraints.SYSCONSTRAINTS.SCHEMAID},
              Keys.SYNTHETIC_PK_SYSSCHEMAS,
              new TableField[] {Sysschemas.SYSSCHEMAS.SCHEMAID},
              true);

  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_SYSCONSTRAINTS__SYNTHETIC_PK_SYSTABLES =
          Internal.createForeignKey(
              Sysconstraints.SYSCONSTRAINTS,
              DSL.name("SYNTHETIC_FK_SYSCONSTRAINTS__SYNTHETIC_PK_SYSTABLES"),
              new TableField[] {Sysconstraints.SYSCONSTRAINTS.TABLEID},
              Keys.SYNTHETIC_PK_SYSTABLES,
              new TableField[] {Systables.SYSTABLES.TABLEID},
              true);

  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_SYSKEYS__SYNTHETIC_PK_SYSCONGLOMERATES =
          Internal.createForeignKey(
              Syskeys.SYSKEYS,
              DSL.name("SYNTHETIC_FK_SYSKEYS__SYNTHETIC_PK_SYSCONGLOMERATES"),
              new TableField[] {Syskeys.SYSKEYS.CONGLOMERATEID},
              Keys.SYNTHETIC_PK_SYSCONGLOMERATES,
              new TableField[] {Sysconglomerates.SYSCONGLOMERATES.CONGLOMERATEID},
              true);

  public static final ForeignKey<Record, Record> SYNTHETIC_FK_SYSKEYS__SYNTHETIC_PK_SYSCONSTRAINTS =
      Internal.createForeignKey(
          Syskeys.SYSKEYS,
          DSL.name("SYNTHETIC_FK_SYSKEYS__SYNTHETIC_PK_SYSCONSTRAINTS"),
          new TableField[] {Syskeys.SYSKEYS.CONSTRAINTID},
          Keys.SYNTHETIC_PK_SYSCONSTRAINTS,
          new TableField[] {Sysconstraints.SYSCONSTRAINTS.CONSTRAINTID},
          true);

  public static final ForeignKey<Record, Record>
      SYNTHETIC_FK_SYSSEQUENCES__SYNTHETIC_PK_SYSSCHEMAS =
          Internal.createForeignKey(
              Syssequences.SYSSEQUENCES,
              DSL.name("SYNTHETIC_FK_SYSSEQUENCES__SYNTHETIC_PK_SYSSCHEMAS"),
              new TableField[] {Syssequences.SYSSEQUENCES.SCHEMAID},
              Keys.SYNTHETIC_PK_SYSSCHEMAS,
              new TableField[] {Sysschemas.SYSSCHEMAS.SCHEMAID},
              true);

  public static final ForeignKey<Record, Record> SYNTHETIC_FK_SYSTABLES__SYNTHETIC_PK_SYSSCHEMAS =
      Internal.createForeignKey(
          Systables.SYSTABLES,
          DSL.name("SYNTHETIC_FK_SYSTABLES__SYNTHETIC_PK_SYSSCHEMAS"),
          new TableField[] {Systables.SYSTABLES.SCHEMAID},
          Keys.SYNTHETIC_PK_SYSSCHEMAS,
          new TableField[] {Sysschemas.SYSSCHEMAS.SCHEMAID},
          true);

  public static final ForeignKey<Record, Record> SYNTHETIC_FK_SYSVIEWS__SYNTHETIC_PK_SYSTABLES =
      Internal.createForeignKey(
          Sysviews.SYSVIEWS,
          DSL.name("SYNTHETIC_FK_SYSVIEWS__SYNTHETIC_PK_SYSTABLES"),
          new TableField[] {Sysviews.SYSVIEWS.TABLEID},
          Keys.SYNTHETIC_PK_SYSTABLES,
          new TableField[] {Systables.SYSTABLES.TABLEID},
          true);
}
