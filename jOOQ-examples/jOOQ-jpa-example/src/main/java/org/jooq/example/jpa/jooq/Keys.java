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

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.example.jpa.jooq.tables.Actor;
import org.jooq.example.jpa.jooq.tables.Film;
import org.jooq.example.jpa.jooq.tables.FilmActor;
import org.jooq.example.jpa.jooq.tables.Language;
import org.jooq.example.jpa.jooq.tables.records.ActorRecord;
import org.jooq.example.jpa.jooq.tables.records.FilmActorRecord;
import org.jooq.example.jpa.jooq.tables.records.FilmRecord;
import org.jooq.example.jpa.jooq.tables.records.LanguageRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

/** A class modelling foreign key relationships and constraints of tables in the default schema. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------

  public static final UniqueKey<ActorRecord> CONSTRAINT_3 =
      Internal.createUniqueKey(
          Actor.ACTOR, DSL.name("CONSTRAINT_3"), new TableField[] {Actor.ACTOR.ACTORID}, true);
  public static final UniqueKey<FilmRecord> CONSTRAINT_2 =
      Internal.createUniqueKey(
          Film.FILM, DSL.name("CONSTRAINT_2"), new TableField[] {Film.FILM.FILMID}, true);
  public static final UniqueKey<FilmActorRecord> CONSTRAINT_7 =
      Internal.createUniqueKey(
          FilmActor.FILM_ACTOR,
          DSL.name("CONSTRAINT_7"),
          new TableField[] {FilmActor.FILM_ACTOR.FILMS_FILMID, FilmActor.FILM_ACTOR.ACTORS_ACTORID},
          true);
  public static final UniqueKey<LanguageRecord> CONSTRAINT_C =
      Internal.createUniqueKey(
          Language.LANGUAGE,
          DSL.name("CONSTRAINT_C"),
          new TableField[] {Language.LANGUAGE.LANGUAGEID},
          true);

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------

  public static final ForeignKey<FilmRecord, LanguageRecord> FKD2YJC1RU34H1SMWLA3FX7B6NX =
      Internal.createForeignKey(
          Film.FILM,
          DSL.name("FKD2YJC1RU34H1SMWLA3FX7B6NX"),
          new TableField[] {Film.FILM.LANGUAGE_LANGUAGEID},
          Keys.CONSTRAINT_C,
          new TableField[] {Language.LANGUAGE.LANGUAGEID},
          true);
  public static final ForeignKey<FilmRecord, LanguageRecord> FKN2UB730RPO5B5E9X6U2LWL9FT =
      Internal.createForeignKey(
          Film.FILM,
          DSL.name("FKN2UB730RPO5B5E9X6U2LWL9FT"),
          new TableField[] {Film.FILM.ORIGINALLANGUAGE_LANGUAGEID},
          Keys.CONSTRAINT_C,
          new TableField[] {Language.LANGUAGE.LANGUAGEID},
          true);
  public static final ForeignKey<FilmActorRecord, FilmRecord> FK3FSUXQ0JJ1XONRE7BHROOPVBX =
      Internal.createForeignKey(
          FilmActor.FILM_ACTOR,
          DSL.name("FK3FSUXQ0JJ1XONRE7BHROOPVBX"),
          new TableField[] {FilmActor.FILM_ACTOR.FILMS_FILMID},
          Keys.CONSTRAINT_2,
          new TableField[] {Film.FILM.FILMID},
          true);
  public static final ForeignKey<FilmActorRecord, ActorRecord> FK43SD2F45W7YN0GAXQ94EHTWT2 =
      Internal.createForeignKey(
          FilmActor.FILM_ACTOR,
          DSL.name("FK43SD2F45W7YN0GAXQ94EHTWT2"),
          new TableField[] {FilmActor.FILM_ACTOR.ACTORS_ACTORID},
          Keys.CONSTRAINT_3,
          new TableField[] {Actor.ACTOR.ACTORID},
          true);
}
