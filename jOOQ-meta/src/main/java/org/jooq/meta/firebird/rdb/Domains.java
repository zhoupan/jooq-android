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

import org.jooq.Domain;
import org.jooq.Schema;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.impl.LazySchema;
import org.jooq.impl.SQLDataType;

/** Convenience access to all Domains in the default schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Domains {

  /** The domain <code>RDB$PROCEDURE_PARAMETERS</code>. */
  public static final Domain<Short> RDB$PROCEDURE_PARAMETERS =
      Internal.createDomain(schema(), DSL.name("RDB$PROCEDURE_PARAMETERS"), SQLDataType.SMALLINT);

  private static final Schema schema() {
    return new LazySchema(DSL.name(""), DSL.comment(""), () -> DefaultSchema.DEFAULT_SCHEMA);
  }
}
