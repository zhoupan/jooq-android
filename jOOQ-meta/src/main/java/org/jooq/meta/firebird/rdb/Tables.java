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

import org.jooq.meta.firebird.rdb.tables.Rdb$checkConstraints;
import org.jooq.meta.firebird.rdb.tables.Rdb$fields;
import org.jooq.meta.firebird.rdb.tables.Rdb$functionArguments;
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

/** Convenience access to all tables in the default schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** The table <code>RDB$CHECK_CONSTRAINTS</code>. */
  public static final Rdb$checkConstraints RDB$CHECK_CONSTRAINTS =
      Rdb$checkConstraints.RDB$CHECK_CONSTRAINTS;

  /** The table <code>RDB$FIELDS</code>. */
  public static final Rdb$fields RDB$FIELDS = Rdb$fields.RDB$FIELDS;

  /** The table <code>RDB$FUNCTION_ARGUMENTS</code>. */
  public static final Rdb$functionArguments RDB$FUNCTION_ARGUMENTS =
      Rdb$functionArguments.RDB$FUNCTION_ARGUMENTS;

  /** The table <code>RDB$FUNCTIONS</code>. */
  public static final Rdb$functions RDB$FUNCTIONS = Rdb$functions.RDB$FUNCTIONS;

  /** The table <code>RDB$GENERATORS</code>. */
  public static final Rdb$generators RDB$GENERATORS = Rdb$generators.RDB$GENERATORS;

  /** The table <code>RDB$INDEX_SEGMENTS</code>. */
  public static final Rdb$indexSegments RDB$INDEX_SEGMENTS = Rdb$indexSegments.RDB$INDEX_SEGMENTS;

  /** The table <code>RDB$INDICES</code>. */
  public static final Rdb$indices RDB$INDICES = Rdb$indices.RDB$INDICES;

  /** The table <code>RDB$PROCEDURE_PARAMETERS</code>. */
  public static final Rdb$procedureParameters RDB$PROCEDURE_PARAMETERS =
      Rdb$procedureParameters.RDB$PROCEDURE_PARAMETERS;

  /** The table <code>RDB$PROCEDURES</code>. */
  public static final Rdb$procedures RDB$PROCEDURES = Rdb$procedures.RDB$PROCEDURES;

  /** The table <code>RDB$REF_CONSTRAINTS</code>. */
  public static final Rdb$refConstraints RDB$REF_CONSTRAINTS =
      Rdb$refConstraints.RDB$REF_CONSTRAINTS;

  /** The table <code>RDB$RELATION_CONSTRAINTS</code>. */
  public static final Rdb$relationConstraints RDB$RELATION_CONSTRAINTS =
      Rdb$relationConstraints.RDB$RELATION_CONSTRAINTS;

  /** The table <code>RDB$RELATION_FIELDS</code>. */
  public static final Rdb$relationFields RDB$RELATION_FIELDS =
      Rdb$relationFields.RDB$RELATION_FIELDS;

  /** The table <code>RDB$RELATIONS</code>. */
  public static final Rdb$relations RDB$RELATIONS = Rdb$relations.RDB$RELATIONS;

  /** The table <code>RDB$TRIGGERS</code>. */
  public static final Rdb$triggers RDB$TRIGGERS = Rdb$triggers.RDB$TRIGGERS;
}
