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

import org.jooq.meta.mysql.information_schema.tables.CheckConstraints;
import org.jooq.meta.mysql.information_schema.tables.Columns;
import org.jooq.meta.mysql.information_schema.tables.KeyColumnUsage;
import org.jooq.meta.mysql.information_schema.tables.Parameters;
import org.jooq.meta.mysql.information_schema.tables.ReferentialConstraints;
import org.jooq.meta.mysql.information_schema.tables.Routines;
import org.jooq.meta.mysql.information_schema.tables.Schemata;
import org.jooq.meta.mysql.information_schema.tables.Statistics;
import org.jooq.meta.mysql.information_schema.tables.TableConstraints;
import org.jooq.meta.mysql.information_schema.tables.Views;

/** Convenience access to all tables in information_schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** The table <code>information_schema.CHECK_CONSTRAINTS</code>. */
  public static final CheckConstraints CHECK_CONSTRAINTS = CheckConstraints.CHECK_CONSTRAINTS;

  /** The table <code>information_schema.COLUMNS</code>. */
  public static final Columns COLUMNS = Columns.COLUMNS;

  /** The table <code>information_schema.KEY_COLUMN_USAGE</code>. */
  public static final KeyColumnUsage KEY_COLUMN_USAGE = KeyColumnUsage.KEY_COLUMN_USAGE;

  /** The table <code>information_schema.PARAMETERS</code>. */
  public static final Parameters PARAMETERS = Parameters.PARAMETERS;

  /** The table <code>information_schema.REFERENTIAL_CONSTRAINTS</code>. */
  public static final ReferentialConstraints REFERENTIAL_CONSTRAINTS =
      ReferentialConstraints.REFERENTIAL_CONSTRAINTS;

  /** The table <code>information_schema.ROUTINES</code>. */
  public static final Routines ROUTINES = Routines.ROUTINES;

  /** The table <code>information_schema.SCHEMATA</code>. */
  public static final Schemata SCHEMATA = Schemata.SCHEMATA;

  /** The table <code>information_schema.STATISTICS</code>. */
  public static final Statistics STATISTICS = Statistics.STATISTICS;

  /** The table <code>information_schema.TABLE_CONSTRAINTS</code>. */
  public static final TableConstraints TABLE_CONSTRAINTS = TableConstraints.TABLE_CONSTRAINTS;

  /** The table <code>information_schema.TABLES</code>. */
  public static final org.jooq.meta.mysql.information_schema.tables.Tables TABLES =
      org.jooq.meta.mysql.information_schema.tables.Tables.TABLES;

  /** The table <code>information_schema.VIEWS</code>. */
  public static final Views VIEWS = Views.VIEWS;
}
