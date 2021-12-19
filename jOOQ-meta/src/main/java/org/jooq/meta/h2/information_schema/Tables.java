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

import org.jooq.meta.h2.information_schema.tables.Columns;
import org.jooq.meta.h2.information_schema.tables.Constraints;
import org.jooq.meta.h2.information_schema.tables.CrossReferences;
import org.jooq.meta.h2.information_schema.tables.Domains;
import org.jooq.meta.h2.information_schema.tables.FunctionAliases;
import org.jooq.meta.h2.information_schema.tables.FunctionColumns;
import org.jooq.meta.h2.information_schema.tables.Indexes;
import org.jooq.meta.h2.information_schema.tables.Schemata;
import org.jooq.meta.h2.information_schema.tables.Sequences;
import org.jooq.meta.h2.information_schema.tables.TypeInfo;
import org.jooq.meta.h2.information_schema.tables.Views;

/** Convenience access to all tables in INFORMATION_SCHEMA. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** The table <code>INFORMATION_SCHEMA.COLUMNS</code>. */
  public static final Columns COLUMNS = Columns.COLUMNS;

  /** The table <code>INFORMATION_SCHEMA.CONSTRAINTS</code>. */
  public static final Constraints CONSTRAINTS = Constraints.CONSTRAINTS;

  /** The table <code>INFORMATION_SCHEMA.CROSS_REFERENCES</code>. */
  public static final CrossReferences CROSS_REFERENCES = CrossReferences.CROSS_REFERENCES;

  /** The table <code>INFORMATION_SCHEMA.DOMAINS</code>. */
  public static final Domains DOMAINS = Domains.DOMAINS;

  /** The table <code>INFORMATION_SCHEMA.FUNCTION_ALIASES</code>. */
  public static final FunctionAliases FUNCTION_ALIASES = FunctionAliases.FUNCTION_ALIASES;

  /** The table <code>INFORMATION_SCHEMA.FUNCTION_COLUMNS</code>. */
  public static final FunctionColumns FUNCTION_COLUMNS = FunctionColumns.FUNCTION_COLUMNS;

  /** The table <code>INFORMATION_SCHEMA.INDEXES</code>. */
  public static final Indexes INDEXES = Indexes.INDEXES;

  /** The table <code>INFORMATION_SCHEMA.SCHEMATA</code>. */
  public static final Schemata SCHEMATA = Schemata.SCHEMATA;

  /** The table <code>INFORMATION_SCHEMA.SEQUENCES</code>. */
  public static final Sequences SEQUENCES = Sequences.SEQUENCES;

  /** The table <code>INFORMATION_SCHEMA.TABLES</code>. */
  public static final org.jooq.meta.h2.information_schema.tables.Tables TABLES =
      org.jooq.meta.h2.information_schema.tables.Tables.TABLES;

  /** The table <code>INFORMATION_SCHEMA.TYPE_INFO</code>. */
  public static final TypeInfo TYPE_INFO = TypeInfo.TYPE_INFO;

  /** The table <code>INFORMATION_SCHEMA.VIEWS</code>. */
  public static final Views VIEWS = Views.VIEWS;
}
