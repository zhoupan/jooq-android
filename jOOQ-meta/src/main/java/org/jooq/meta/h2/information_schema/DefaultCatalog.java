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

import java.util.Arrays;
import java.util.List;
import org.jooq.Schema;
import org.jooq.impl.CatalogImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class DefaultCatalog extends CatalogImpl {

  private static final long serialVersionUID = 1874194768;

  /** The reference instance of <code>DEFAULT_CATALOG</code> */
  public static final DefaultCatalog DEFAULT_CATALOG = new DefaultCatalog();

  /** The schema <code>INFORMATION_SCHEMA</code>. */
  public final InformationSchema INFORMATION_SCHEMA = InformationSchema.INFORMATION_SCHEMA;

  /** No further instances allowed */
  private DefaultCatalog() {
    super("");
  }

  @Override
  public final List<Schema> getSchemas() {
    return Arrays.<Schema>asList(InformationSchema.INFORMATION_SCHEMA);
  }
}
