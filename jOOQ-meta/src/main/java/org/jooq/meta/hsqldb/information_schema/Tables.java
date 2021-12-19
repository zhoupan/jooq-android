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
package org.jooq.meta.hsqldb.information_schema;

import org.jooq.meta.hsqldb.information_schema.tables.CheckConstraints;
import org.jooq.meta.hsqldb.information_schema.tables.Columns;
import org.jooq.meta.hsqldb.information_schema.tables.DomainConstraints;
import org.jooq.meta.hsqldb.information_schema.tables.Domains;
import org.jooq.meta.hsqldb.information_schema.tables.ElementTypes;
import org.jooq.meta.hsqldb.information_schema.tables.KeyColumnUsage;
import org.jooq.meta.hsqldb.information_schema.tables.Parameters;
import org.jooq.meta.hsqldb.information_schema.tables.ReferentialConstraints;
import org.jooq.meta.hsqldb.information_schema.tables.Routines;
import org.jooq.meta.hsqldb.information_schema.tables.Schemata;
import org.jooq.meta.hsqldb.information_schema.tables.Sequences;
import org.jooq.meta.hsqldb.information_schema.tables.SystemColumns;
import org.jooq.meta.hsqldb.information_schema.tables.SystemIndexinfo;
import org.jooq.meta.hsqldb.information_schema.tables.SystemTables;
import org.jooq.meta.hsqldb.information_schema.tables.TableConstraints;
import org.jooq.meta.hsqldb.information_schema.tables.Views;

/** Convenience access to all tables in INFORMATION_SCHEMA. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** one row for each domain constraint, table check constraint, and assertion. */
  public static final CheckConstraints CHECK_CONSTRAINTS = CheckConstraints.CHECK_CONSTRAINTS;

  /** one row for each column of table of view */
  public static final Columns COLUMNS = Columns.COLUMNS;

  /** one row for each check constraint included in a domain definition */
  public static final DomainConstraints DOMAIN_CONSTRAINTS = DomainConstraints.DOMAIN_CONSTRAINTS;

  /** one row for each domain identified */
  public static final Domains DOMAINS = Domains.DOMAINS;

  /** The table <code>INFORMATION_SCHEMA.ELEMENT_TYPES</code>. */
  public static final ElementTypes ELEMENT_TYPES = ElementTypes.ELEMENT_TYPES;

  /** one row for each column used in s primary key or unique constraint */
  public static final KeyColumnUsage KEY_COLUMN_USAGE = KeyColumnUsage.KEY_COLUMN_USAGE;

  /** one row for each routine parameter */
  public static final Parameters PARAMETERS = Parameters.PARAMETERS;

  /** one row for each foreign key constraint */
  public static final ReferentialConstraints REFERENTIAL_CONSTRAINTS =
      ReferentialConstraints.REFERENTIAL_CONSTRAINTS;

  /** one row for each routine */
  public static final Routines ROUTINES = Routines.ROUTINES;

  /** one row for each schema */
  public static final Schemata SCHEMATA = Schemata.SCHEMATA;

  /** one row for each external sequence generator */
  public static final Sequences SEQUENCES = Sequences.SEQUENCES;

  /** the visible columns of each accessible table defined within this database */
  public static final SystemColumns SYSTEM_COLUMNS = SystemColumns.SYSTEM_COLUMNS;

  /** information about the indices of each accessible table defined within this database */
  public static final SystemIndexinfo SYSTEM_INDEXINFO = SystemIndexinfo.SYSTEM_INDEXINFO;

  /** the accessible tables defined within this database */
  public static final SystemTables SYSTEM_TABLES = SystemTables.SYSTEM_TABLES;

  /** one row for each table constraint associated with a table */
  public static final TableConstraints TABLE_CONSTRAINTS = TableConstraints.TABLE_CONSTRAINTS;

  /** one row for each table or view */
  public static final org.jooq.meta.hsqldb.information_schema.tables.Tables TABLES =
      org.jooq.meta.hsqldb.information_schema.tables.Tables.TABLES;

  /** the view descriptors of the accessible views defined within this database */
  public static final Views VIEWS = Views.VIEWS;
}
