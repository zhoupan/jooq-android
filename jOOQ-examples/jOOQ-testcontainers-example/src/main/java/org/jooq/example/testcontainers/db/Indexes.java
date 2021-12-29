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
package org.jooq.example.testcontainers.db;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.example.testcontainers.db.tables.Actor;
import org.jooq.example.testcontainers.db.tables.Address;
import org.jooq.example.testcontainers.db.tables.City;
import org.jooq.example.testcontainers.db.tables.Customer;
import org.jooq.example.testcontainers.db.tables.Film;
import org.jooq.example.testcontainers.db.tables.FilmActor;
import org.jooq.example.testcontainers.db.tables.Inventory;
import org.jooq.example.testcontainers.db.tables.Payment;
import org.jooq.example.testcontainers.db.tables.PaymentP2007_01;
import org.jooq.example.testcontainers.db.tables.PaymentP2007_02;
import org.jooq.example.testcontainers.db.tables.PaymentP2007_03;
import org.jooq.example.testcontainers.db.tables.PaymentP2007_04;
import org.jooq.example.testcontainers.db.tables.PaymentP2007_05;
import org.jooq.example.testcontainers.db.tables.PaymentP2007_06;
import org.jooq.example.testcontainers.db.tables.Rental;
import org.jooq.example.testcontainers.db.tables.Store;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;

/** A class modelling indexes of tables in public. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Indexes {

  // -------------------------------------------------------------------------
  // INDEX definitions
  // -------------------------------------------------------------------------

  public static final Index FILM_FULLTEXT_IDX =
      Internal.createIndex(
          DSL.name("film_fulltext_idx"), Film.FILM, new OrderField[] {Film.FILM.FULLTEXT}, false);
  public static final Index IDX_ACTOR_LAST_NAME =
      Internal.createIndex(
          DSL.name("idx_actor_last_name"),
          Actor.ACTOR,
          new OrderField[] {Actor.ACTOR.LAST_NAME},
          false);
  public static final Index IDX_FK_ADDRESS_ID =
      Internal.createIndex(
          DSL.name("idx_fk_address_id"),
          Customer.CUSTOMER,
          new OrderField[] {Customer.CUSTOMER.ADDRESS_ID},
          false);
  public static final Index IDX_FK_CITY_ID =
      Internal.createIndex(
          DSL.name("idx_fk_city_id"),
          Address.ADDRESS,
          new OrderField[] {Address.ADDRESS.CITY_ID},
          false);
  public static final Index IDX_FK_COUNTRY_ID =
      Internal.createIndex(
          DSL.name("idx_fk_country_id"), City.CITY, new OrderField[] {City.CITY.COUNTRY_ID}, false);
  public static final Index IDX_FK_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_customer_id"),
          Payment.PAYMENT,
          new OrderField[] {Payment.PAYMENT.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_FILM_ID =
      Internal.createIndex(
          DSL.name("idx_fk_film_id"),
          FilmActor.FILM_ACTOR,
          new OrderField[] {FilmActor.FILM_ACTOR.FILM_ID},
          false);
  public static final Index IDX_FK_INVENTORY_ID =
      Internal.createIndex(
          DSL.name("idx_fk_inventory_id"),
          Rental.RENTAL,
          new OrderField[] {Rental.RENTAL.INVENTORY_ID},
          false);
  public static final Index IDX_FK_LANGUAGE_ID =
      Internal.createIndex(
          DSL.name("idx_fk_language_id"),
          Film.FILM,
          new OrderField[] {Film.FILM.LANGUAGE_ID},
          false);
  public static final Index IDX_FK_ORIGINAL_LANGUAGE_ID =
      Internal.createIndex(
          DSL.name("idx_fk_original_language_id"),
          Film.FILM,
          new OrderField[] {Film.FILM.ORIGINAL_LANGUAGE_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_01_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_01_customer_id"),
          PaymentP2007_01.PAYMENT_P2007_01,
          new OrderField[] {PaymentP2007_01.PAYMENT_P2007_01.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_01_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_01_staff_id"),
          PaymentP2007_01.PAYMENT_P2007_01,
          new OrderField[] {PaymentP2007_01.PAYMENT_P2007_01.STAFF_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_02_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_02_customer_id"),
          PaymentP2007_02.PAYMENT_P2007_02,
          new OrderField[] {PaymentP2007_02.PAYMENT_P2007_02.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_02_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_02_staff_id"),
          PaymentP2007_02.PAYMENT_P2007_02,
          new OrderField[] {PaymentP2007_02.PAYMENT_P2007_02.STAFF_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_03_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_03_customer_id"),
          PaymentP2007_03.PAYMENT_P2007_03,
          new OrderField[] {PaymentP2007_03.PAYMENT_P2007_03.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_03_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_03_staff_id"),
          PaymentP2007_03.PAYMENT_P2007_03,
          new OrderField[] {PaymentP2007_03.PAYMENT_P2007_03.STAFF_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_04_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_04_customer_id"),
          PaymentP2007_04.PAYMENT_P2007_04,
          new OrderField[] {PaymentP2007_04.PAYMENT_P2007_04.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_04_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_04_staff_id"),
          PaymentP2007_04.PAYMENT_P2007_04,
          new OrderField[] {PaymentP2007_04.PAYMENT_P2007_04.STAFF_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_05_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_05_customer_id"),
          PaymentP2007_05.PAYMENT_P2007_05,
          new OrderField[] {PaymentP2007_05.PAYMENT_P2007_05.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_05_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_05_staff_id"),
          PaymentP2007_05.PAYMENT_P2007_05,
          new OrderField[] {PaymentP2007_05.PAYMENT_P2007_05.STAFF_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_06_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_06_customer_id"),
          PaymentP2007_06.PAYMENT_P2007_06,
          new OrderField[] {PaymentP2007_06.PAYMENT_P2007_06.CUSTOMER_ID},
          false);
  public static final Index IDX_FK_PAYMENT_P2007_06_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_payment_p2007_06_staff_id"),
          PaymentP2007_06.PAYMENT_P2007_06,
          new OrderField[] {PaymentP2007_06.PAYMENT_P2007_06.STAFF_ID},
          false);
  public static final Index IDX_FK_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_fk_staff_id"),
          Payment.PAYMENT,
          new OrderField[] {Payment.PAYMENT.STAFF_ID},
          false);
  public static final Index IDX_FK_STORE_ID =
      Internal.createIndex(
          DSL.name("idx_fk_store_id"),
          Customer.CUSTOMER,
          new OrderField[] {Customer.CUSTOMER.STORE_ID},
          false);
  public static final Index IDX_LAST_NAME =
      Internal.createIndex(
          DSL.name("idx_last_name"),
          Customer.CUSTOMER,
          new OrderField[] {Customer.CUSTOMER.LAST_NAME},
          false);
  public static final Index IDX_STORE_ID_FILM_ID =
      Internal.createIndex(
          DSL.name("idx_store_id_film_id"),
          Inventory.INVENTORY,
          new OrderField[] {Inventory.INVENTORY.STORE_ID, Inventory.INVENTORY.FILM_ID},
          false);
  public static final Index IDX_TITLE =
      Internal.createIndex(
          DSL.name("idx_title"), Film.FILM, new OrderField[] {Film.FILM.TITLE}, false);
  public static final Index IDX_UNQ_MANAGER_STAFF_ID =
      Internal.createIndex(
          DSL.name("idx_unq_manager_staff_id"),
          Store.STORE,
          new OrderField[] {Store.STORE.MANAGER_STAFF_ID},
          true);
  public static final Index IDX_UNQ_RENTAL_RENTAL_DATE_INVENTORY_ID_CUSTOMER_ID =
      Internal.createIndex(
          DSL.name("idx_unq_rental_rental_date_inventory_id_customer_id"),
          Rental.RENTAL,
          new OrderField[] {
            Rental.RENTAL.RENTAL_DATE, Rental.RENTAL.INVENTORY_ID, Rental.RENTAL.CUSTOMER_ID
          },
          true);
}
