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

import org.jooq.meta.postgres.information_schema.tables.Attributes;
import org.jooq.meta.postgres.information_schema.tables.CheckConstraints;
import org.jooq.meta.postgres.information_schema.tables.Columns;
import org.jooq.meta.postgres.information_schema.tables.ConstraintColumnUsage;
import org.jooq.meta.postgres.information_schema.tables.Domains;
import org.jooq.meta.postgres.information_schema.tables.KeyColumnUsage;
import org.jooq.meta.postgres.information_schema.tables.Parameters;
import org.jooq.meta.postgres.information_schema.tables.ReferentialConstraints;
import org.jooq.meta.postgres.information_schema.tables.Routines;
import org.jooq.meta.postgres.information_schema.tables.Schemata;
import org.jooq.meta.postgres.information_schema.tables.Sequences;
import org.jooq.meta.postgres.information_schema.tables.Views;

/** Convenience access to all tables in information_schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** The table <code>information_schema.attributes</code>. */
  public static final Attributes ATTRIBUTES = Attributes.ATTRIBUTES;

  /** The table <code>information_schema.check_constraints</code>. */
  public static final CheckConstraints CHECK_CONSTRAINTS = CheckConstraints.CHECK_CONSTRAINTS;

  /** The table <code>information_schema.columns</code>. */
  public static final Columns COLUMNS = Columns.COLUMNS;

  /** The table <code>information_schema.constraint_column_usage</code>. */
  public static final ConstraintColumnUsage CONSTRAINT_COLUMN_USAGE =
      ConstraintColumnUsage.CONSTRAINT_COLUMN_USAGE;

  /** The table <code>information_schema.domains</code>. */
  public static final Domains DOMAINS = Domains.DOMAINS;

  /** The table <code>information_schema.key_column_usage</code>. */
  public static final KeyColumnUsage KEY_COLUMN_USAGE = KeyColumnUsage.KEY_COLUMN_USAGE;

  /** The table <code>information_schema.parameters</code>. */
  public static final Parameters PARAMETERS = Parameters.PARAMETERS;

  /** The table <code>information_schema.referential_constraints</code>. */
  public static final ReferentialConstraints REFERENTIAL_CONSTRAINTS =
      ReferentialConstraints.REFERENTIAL_CONSTRAINTS;

  /** The table <code>information_schema.routines</code>. */
  public static final Routines ROUTINES = Routines.ROUTINES;

  /** The table <code>information_schema.schemata</code>. */
  public static final Schemata SCHEMATA = Schemata.SCHEMATA;

  /** The table <code>information_schema.sequences</code>. */
  public static final Sequences SEQUENCES = Sequences.SEQUENCES;

  /** The table <code>information_schema.tables</code>. */
  public static final org.jooq.meta.postgres.information_schema.tables.Tables TABLES =
      org.jooq.meta.postgres.information_schema.tables.Tables.TABLES;

  /** The table <code>information_schema.views</code>. */
  public static final Views VIEWS = Views.VIEWS;
}
