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
package org.jooq.example.flyway;

import static java.util.Arrays.asList;
import static org.jooq.example.flyway.j.db.h2.Tables.AUTHOR;
import static org.jooq.example.flyway.j.db.h2.Tables.BOOK;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.junit.Test;

/** @author Lukas Eder */
public class AfterMigrationTestJava {

  @Test
  public void testQueryingAfterMigration() throws Exception {
    try (Connection c = DriverManager.getConnection("jdbc:h2:~/flyway-test", "sa", "")) {
      Result<?> result =
          DSL.using(c)
              .select(AUTHOR.FIRST_NAME, AUTHOR.LAST_NAME, BOOK.ID, BOOK.TITLE)
              .from(AUTHOR)
              .join(BOOK)
              .on(AUTHOR.ID.eq(BOOK.AUTHOR_ID))
              .orderBy(BOOK.ID.asc())
              .fetch();

      assertEquals(4, result.size());
      assertEquals(asList(1, 2, 3, 4), result.getValues(BOOK.ID));
    }
  }
}
