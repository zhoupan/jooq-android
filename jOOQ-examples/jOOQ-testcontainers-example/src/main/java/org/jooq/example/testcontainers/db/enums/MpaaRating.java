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
package org.jooq.example.testcontainers.db.enums;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;
import org.jooq.example.testcontainers.db.Public;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public enum MpaaRating implements EnumType {
  G("G"),

  PG("PG"),

  PG_13("PG-13"),

  R("R"),

  NC_17("NC-17");

  private final String literal;

  private MpaaRating(String literal) {
    this.literal = literal;
  }

  @Override
  public Catalog getCatalog() {
    return getSchema().getCatalog();
  }

  @Override
  public Schema getSchema() {
    return Public.PUBLIC;
  }

  @Override
  public String getName() {
    return "mpaa_rating";
  }

  @Override
  public String getLiteral() {
    return literal;
  }
}
