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
package org.jooq.meta.firebird.rdb;

import org.jooq.ForeignKey;
import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.firebird.rdb.tables.Rdb$fields;
import org.jooq.meta.firebird.rdb.tables.Rdb$functions;
import org.jooq.meta.firebird.rdb.tables.Rdb$generators;
import org.jooq.meta.firebird.rdb.tables.Rdb$indexSegments;
import org.jooq.meta.firebird.rdb.tables.Rdb$indices;
import org.jooq.meta.firebird.rdb.tables.Rdb$procedureParameters;
import org.jooq.meta.firebird.rdb.tables.Rdb$procedures;
import org.jooq.meta.firebird.rdb.tables.Rdb$refConstraints;
import org.jooq.meta.firebird.rdb.tables.Rdb$relationConstraints;
import org.jooq.meta.firebird.rdb.tables.Rdb$relationFields;
import org.jooq.meta.firebird.rdb.tables.Rdb$relations;
import org.jooq.meta.firebird.rdb.tables.Rdb$triggers;

/** A class modelling foreign key relationships and constraints of tables in the default schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------

  public static final UniqueKey<Record> RDB$INDEX_2 =
      Internal.createUniqueKey(
          Rdb$fields.RDB$FIELDS,
          DSL.name("RDB$INDEX_2"),
          new TableField[] {Rdb$fields.RDB$FIELDS.RDB$FIELD_NAME},
          true);
  public static final UniqueKey<Record> RDB$INDEX_53 =
      Internal.createUniqueKey(
          Rdb$functions.RDB$FUNCTIONS,
          DSL.name("RDB$INDEX_53"),
          new TableField[] {Rdb$functions.RDB$FUNCTIONS.RDB$FUNCTION_ID},
          true);
  public static final UniqueKey<Record> RDB$INDEX_9 =
      Internal.createUniqueKey(
          Rdb$functions.RDB$FUNCTIONS,
          DSL.name("RDB$INDEX_9"),
          new TableField[] {
            Rdb$functions.RDB$FUNCTIONS.RDB$PACKAGE_NAME,
            Rdb$functions.RDB$FUNCTIONS.RDB$FUNCTION_NAME
          },
          true);
  public static final UniqueKey<Record> RDB$INDEX_11 =
      Internal.createUniqueKey(
          Rdb$generators.RDB$GENERATORS,
          DSL.name("RDB$INDEX_11"),
          new TableField[] {Rdb$generators.RDB$GENERATORS.RDB$GENERATOR_NAME},
          true);
  public static final UniqueKey<Record> RDB$INDEX_46 =
      Internal.createUniqueKey(
          Rdb$generators.RDB$GENERATORS,
          DSL.name("RDB$INDEX_46"),
          new TableField[] {Rdb$generators.RDB$GENERATORS.RDB$GENERATOR_ID},
          true);
  public static final UniqueKey<Record> RDB$INDEX_5 =
      Internal.createUniqueKey(
          Rdb$indices.RDB$INDICES,
          DSL.name("RDB$INDEX_5"),
          new TableField[] {Rdb$indices.RDB$INDICES.RDB$INDEX_NAME},
          true);
  public static final UniqueKey<Record> RDB$INDEX_18 =
      Internal.createUniqueKey(
          Rdb$procedureParameters.RDB$PROCEDURE_PARAMETERS,
          DSL.name("RDB$INDEX_18"),
          new TableField[] {
            Rdb$procedureParameters.RDB$PROCEDURE_PARAMETERS.RDB$PACKAGE_NAME,
            Rdb$procedureParameters.RDB$PROCEDURE_PARAMETERS.RDB$PROCEDURE_NAME,
            Rdb$procedureParameters.RDB$PROCEDURE_PARAMETERS.RDB$PARAMETER_NAME
          },
          true);
  public static final UniqueKey<Record> RDB$INDEX_21 =
      Internal.createUniqueKey(
          Rdb$procedures.RDB$PROCEDURES,
          DSL.name("RDB$INDEX_21"),
          new TableField[] {
            Rdb$procedures.RDB$PROCEDURES.RDB$PACKAGE_NAME,
            Rdb$procedures.RDB$PROCEDURES.RDB$PROCEDURE_NAME
          },
          true);
  public static final UniqueKey<Record> RDB$INDEX_22 =
      Internal.createUniqueKey(
          Rdb$procedures.RDB$PROCEDURES,
          DSL.name("RDB$INDEX_22"),
          new TableField[] {Rdb$procedures.RDB$PROCEDURES.RDB$PROCEDURE_ID},
          true);
  public static final UniqueKey<Record> RDB$INDEX_13 =
      Internal.createUniqueKey(
          Rdb$refConstraints.RDB$REF_CONSTRAINTS,
          DSL.name("RDB$INDEX_13"),
          new TableField[] {Rdb$refConstraints.RDB$REF_CONSTRAINTS.RDB$CONSTRAINT_NAME},
          true);
  public static final UniqueKey<Record> RDB$INDEX_12 =
      Internal.createUniqueKey(
          Rdb$relationConstraints.RDB$RELATION_CONSTRAINTS,
          DSL.name("RDB$INDEX_12"),
          new TableField[] {Rdb$relationConstraints.RDB$RELATION_CONSTRAINTS.RDB$CONSTRAINT_NAME},
          true);
  public static final UniqueKey<Record> RDB$INDEX_15 =
      Internal.createUniqueKey(
          Rdb$relationFields.RDB$RELATION_FIELDS,
          DSL.name("RDB$INDEX_15"),
          new TableField[] {
            Rdb$relationFields.RDB$RELATION_FIELDS.RDB$FIELD_NAME,
            Rdb$relationFields.RDB$RELATION_FIELDS.RDB$RELATION_NAME
          },
          true);
  public static final UniqueKey<Record> RDB$INDEX_0 =
      Internal.createUniqueKey(
          Rdb$relations.RDB$RELATIONS,
          DSL.name("RDB$INDEX_0"),
          new TableField[] {Rdb$relations.RDB$RELATIONS.RDB$RELATION_NAME},
          true);
  public static final UniqueKey<Record> RDB$INDEX_8 =
      Internal.createUniqueKey(
          Rdb$triggers.RDB$TRIGGERS,
          DSL.name("RDB$INDEX_8"),
          new TableField[] {Rdb$triggers.RDB$TRIGGERS.RDB$TRIGGER_NAME},
          true);

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------

  public static final ForeignKey<Record, Record> SYNTHETIC_FK_RDB$INDEX_SEGMENTS__RDB$INDEX_5 =
      Internal.createForeignKey(
          Rdb$indexSegments.RDB$INDEX_SEGMENTS,
          DSL.name("SYNTHETIC_FK_RDB$INDEX_SEGMENTS__RDB$INDEX_5"),
          new TableField[] {Rdb$indexSegments.RDB$INDEX_SEGMENTS.RDB$INDEX_NAME},
          Keys.RDB$INDEX_5,
          new TableField[] {Rdb$indices.RDB$INDICES.RDB$INDEX_NAME},
          true);
}
