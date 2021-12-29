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
package org.jooq.example.flyway.ddl.db.h2;

import org.jooq.Sequence;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** Convenience access to all sequences in FLYWAY_TEST. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Sequences {

  /** The sequence <code>FLYWAY_TEST.S_AUTHOR_ID</code> */
  public static final Sequence<Long> S_AUTHOR_ID =
      Internal.createSequence(
          "S_AUTHOR_ID",
          FlywayTest.FLYWAY_TEST,
          SQLDataType.BIGINT,
          null,
          null,
          null,
          null,
          false,
          null);
}
