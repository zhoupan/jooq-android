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
package org.jooq.impl;

import static org.jooq.impl.Tools.EMPTY_TABLE;

import java.util.Collection;
import org.jooq.Configuration;
import org.jooq.Meta;
import org.jooq.MetaProvider;
import org.jooq.Table;

/**
 * A {@link MetaProvider} that provides its meta data based on (possibly generated) schemas.
 *
 * @author Lukas Eder
 */
public class TableMetaProvider implements MetaProvider {

  private final Configuration configuration;
  private final Table<?>[] tables;

  public TableMetaProvider(Configuration configuration, Table<?>... tables) {
    this.configuration = configuration;
    this.tables = tables;
  }

  public TableMetaProvider(Configuration configuration, Collection<? extends Table<?>> tables) {
    this(configuration, tables.toArray(EMPTY_TABLE));
  }

  @Override
  public Meta provide() {
    return CatalogMetaImpl.filterTables(configuration, tables);
  }
}
