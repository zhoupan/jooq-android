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
package org.jooq.example;

import static org.jooq.example.db.h2.Tables.AUTHOR;
import static org.jooq.example.db.h2.Tables.BOOK;
import static org.jooq.example.db.h2.Tables.BOOK_STORE;
import static org.jooq.example.db.h2.Tables.BOOK_TO_BOOK_STORE;
import static org.jooq.impl.DSL.countDistinct;
import static org.junit.Assert.assertEquals;

import java.util.List;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.jooq.ResultQuery;
import org.jooq.example.db.h2.tables.Author;
import org.jooq.example.db.h2.tables.Book;
import org.jooq.example.db.h2.tables.BookStore;
import org.jooq.example.db.h2.tables.BookToBookStore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/** @author Lukas Eder */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/jooq-spring.xml"})
public class JdbcTemplateTest {

  @Autowired DSLContext create;

  @Autowired DataSource dataSource;

  @Test
  public void testExecuteQueryWithJdbcTemplate() throws Exception {
    // All of these tables were generated by jOOQ's Maven plugin
    Book b = BOOK.as("b");
    Author a = AUTHOR.as("a");
    BookStore s = BOOK_STORE.as("s");
    BookToBookStore t = BOOK_TO_BOOK_STORE.as("t");

    ResultQuery<Record3<String, String, Integer>> query =
        create
            .select(a.FIRST_NAME, a.LAST_NAME, countDistinct(s.NAME))
            .from(a)
            .join(b)
            .on(b.AUTHOR_ID.equal(a.ID))
            .join(t)
            .on(t.BOOK_ID.equal(b.ID))
            .join(s)
            .on(t.BOOK_STORE_NAME.equal(s.NAME))
            .groupBy(a.FIRST_NAME, a.LAST_NAME)
            .orderBy(countDistinct(s.NAME).desc());

    JdbcTemplate template = new JdbcTemplate(dataSource);
    List<A> result =
        template.query(
            query.getSQL(),
            query.getBindValues().toArray(),
            (r, i) -> new A(r.getString(1), r.getString(2), r.getInt(3)));

    assertEquals(2, result.size());
    assertEquals("Paulo", result.get(0).firstName);
    assertEquals("George", result.get(1).firstName);

    assertEquals("Coelho", result.get(0).lastName);
    assertEquals("Orwell", result.get(1).lastName);

    assertEquals(3, result.get(0).books);
    assertEquals(2, result.get(1).books);
  }

  static class A {
    final String firstName;
    final String lastName;
    final int books;

    A(String firstName, String lastName, int books) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.books = books;
    }
  }
}
