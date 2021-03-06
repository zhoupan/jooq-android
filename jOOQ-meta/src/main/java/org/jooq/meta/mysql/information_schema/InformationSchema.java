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

import java.util.Arrays;
import java.util.List;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;
import org.jooq.meta.mysql.information_schema.tables.CheckConstraints;
import org.jooq.meta.mysql.information_schema.tables.Columns;
import org.jooq.meta.mysql.information_schema.tables.KeyColumnUsage;
import org.jooq.meta.mysql.information_schema.tables.Parameters;
import org.jooq.meta.mysql.information_schema.tables.ReferentialConstraints;
import org.jooq.meta.mysql.information_schema.tables.Routines;
import org.jooq.meta.mysql.information_schema.tables.Schemata;
import org.jooq.meta.mysql.information_schema.tables.Statistics;
import org.jooq.meta.mysql.information_schema.tables.TableConstraints;
import org.jooq.meta.mysql.information_schema.tables.Tables;
import org.jooq.meta.mysql.information_schema.tables.Views;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class InformationSchema extends SchemaImpl {

  private static final long serialVersionUID = 1825826342;

  /** The reference instance of <code>information_schema</code> */
  public static final InformationSchema INFORMATION_SCHEMA = new InformationSchema();

  /** The table <code>information_schema.CHECK_CONSTRAINTS</code>. */
  public final CheckConstraints CHECK_CONSTRAINTS = CheckConstraints.CHECK_CONSTRAINTS;

  /** The table <code>information_schema.COLUMNS</code>. */
  public final Columns COLUMNS = Columns.COLUMNS;

  /** The table <code>information_schema.KEY_COLUMN_USAGE</code>. */
  public final KeyColumnUsage KEY_COLUMN_USAGE = KeyColumnUsage.KEY_COLUMN_USAGE;

  /** The table <code>information_schema.PARAMETERS</code>. */
  public final Parameters PARAMETERS = Parameters.PARAMETERS;

  /** The table <code>information_schema.REFERENTIAL_CONSTRAINTS</code>. */
  public final ReferentialConstraints REFERENTIAL_CONSTRAINTS =
      ReferentialConstraints.REFERENTIAL_CONSTRAINTS;

  /** The table <code>information_schema.ROUTINES</code>. */
  public final Routines ROUTINES = Routines.ROUTINES;

  /** The table <code>information_schema.SCHEMATA</code>. */
  public final Schemata SCHEMATA = Schemata.SCHEMATA;

  /** The table <code>information_schema.STATISTICS</code>. */
  public final Statistics STATISTICS = Statistics.STATISTICS;

  /** The table <code>information_schema.TABLE_CONSTRAINTS</code>. */
  public final TableConstraints TABLE_CONSTRAINTS = TableConstraints.TABLE_CONSTRAINTS;

  /** The table <code>information_schema.TABLES</code>. */
  public final Tables TABLES = Tables.TABLES;

  /** The table <code>information_schema.VIEWS</code>. */
  public final Views VIEWS = Views.VIEWS;

  /** No further instances allowed */
  private InformationSchema() {
    super("information_schema", null);
  }

  @Override
  public Catalog getCatalog() {
    return DefaultCatalog.DEFAULT_CATALOG;
  }

  @Override
  public final List<Table<?>> getTables() {
    return Arrays.<Table<?>>asList(
        CheckConstraints.CHECK_CONSTRAINTS,
        Columns.COLUMNS,
        KeyColumnUsage.KEY_COLUMN_USAGE,
        Parameters.PARAMETERS,
        ReferentialConstraints.REFERENTIAL_CONSTRAINTS,
        Routines.ROUTINES,
        Schemata.SCHEMATA,
        Statistics.STATISTICS,
        TableConstraints.TABLE_CONSTRAINTS,
        Tables.TABLES,
        Views.VIEWS);
  }
}
