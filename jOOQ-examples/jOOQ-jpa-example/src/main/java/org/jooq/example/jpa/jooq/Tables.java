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
package org.jooq.example.jpa.jooq;

import org.jooq.example.jpa.jooq.tables.Actor;
import org.jooq.example.jpa.jooq.tables.Film;
import org.jooq.example.jpa.jooq.tables.FilmActor;
import org.jooq.example.jpa.jooq.tables.Language;

/** Convenience access to all tables in the default schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** The table <code>ACTOR</code>. */
  public static final Actor ACTOR = Actor.ACTOR;

  /** The table <code>FILM</code>. */
  public static final Film FILM = Film.FILM;

  /** The table <code>FILM_ACTOR</code>. */
  public static final FilmActor FILM_ACTOR = FilmActor.FILM_ACTOR;

  /** The table <code>LANGUAGE</code>. */
  public static final Language LANGUAGE = Language.LANGUAGE;
}
