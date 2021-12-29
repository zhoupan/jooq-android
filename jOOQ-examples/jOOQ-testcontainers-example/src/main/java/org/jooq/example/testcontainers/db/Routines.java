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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.jooq.AggregateFunction;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.example.testcontainers.db.routines.GetCustomerBalance;
import org.jooq.example.testcontainers.db.routines.GroupConcat;
import org.jooq.example.testcontainers.db.routines.InventoryHeldByCustomer;
import org.jooq.example.testcontainers.db.routines.InventoryInStock;
import org.jooq.example.testcontainers.db.routines.LastDay;
import org.jooq.example.testcontainers.db.routines._GroupConcat;
import org.jooq.example.testcontainers.db.tables.FilmInStock;
import org.jooq.example.testcontainers.db.tables.FilmNotInStock;
import org.jooq.example.testcontainers.db.tables.RewardsReport;
import org.jooq.example.testcontainers.db.tables.records.FilmInStockRecord;
import org.jooq.example.testcontainers.db.tables.records.FilmNotInStockRecord;
import org.jooq.example.testcontainers.db.tables.records.RewardsReportRecord;

/** Convenience access to all stored procedures and functions in public. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Routines {

  /** Call <code>public._group_concat</code> */
  public static String _GroupConcat(Configuration configuration, String __1, String __2) {
    _GroupConcat f = new _GroupConcat();
    f.set__1(__1);
    f.set__2(__2);

    f.execute(configuration);
    return f.getReturnValue();
  }

  /** Get <code>public._group_concat</code> as a field. */
  public static Field<String> _GroupConcat(String __1, String __2) {
    _GroupConcat f = new _GroupConcat();
    f.set__1(__1);
    f.set__2(__2);

    return f.asField();
  }

  /** Get <code>public._group_concat</code> as a field. */
  public static Field<String> _GroupConcat(Field<String> __1, Field<String> __2) {
    _GroupConcat f = new _GroupConcat();
    f.set__1(__1);
    f.set__2(__2);

    return f.asField();
  }

  /** Call <code>public.get_customer_balance</code> */
  public static BigDecimal getCustomerBalance(
      Configuration configuration, Long pCustomerId, LocalDateTime pEffectiveDate) {
    GetCustomerBalance f = new GetCustomerBalance();
    f.setPCustomerId(pCustomerId);
    f.setPEffectiveDate(pEffectiveDate);

    f.execute(configuration);
    return f.getReturnValue();
  }

  /** Get <code>public.get_customer_balance</code> as a field. */
  public static Field<BigDecimal> getCustomerBalance(
      Long pCustomerId, LocalDateTime pEffectiveDate) {
    GetCustomerBalance f = new GetCustomerBalance();
    f.setPCustomerId(pCustomerId);
    f.setPEffectiveDate(pEffectiveDate);

    return f.asField();
  }

  /** Get <code>public.get_customer_balance</code> as a field. */
  public static Field<BigDecimal> getCustomerBalance(
      Field<Long> pCustomerId, Field<LocalDateTime> pEffectiveDate) {
    GetCustomerBalance f = new GetCustomerBalance();
    f.setPCustomerId(pCustomerId);
    f.setPEffectiveDate(pEffectiveDate);

    return f.asField();
  }

  /** Get <code>public.group_concat</code> as a field. */
  public static AggregateFunction<String> groupConcat(String __1) {
    GroupConcat f = new GroupConcat();
    f.set__1(__1);

    return f.asAggregateFunction();
  }

  /** Get <code>public.group_concat</code> as a field. */
  public static AggregateFunction<String> groupConcat(Field<String> __1) {
    GroupConcat f = new GroupConcat();
    f.set__1(__1);

    return f.asAggregateFunction();
  }

  /** Call <code>public.inventory_held_by_customer</code> */
  public static Integer inventoryHeldByCustomer(Configuration configuration, Long pInventoryId) {
    InventoryHeldByCustomer f = new InventoryHeldByCustomer();
    f.setPInventoryId(pInventoryId);

    f.execute(configuration);
    return f.getReturnValue();
  }

  /** Get <code>public.inventory_held_by_customer</code> as a field. */
  public static Field<Integer> inventoryHeldByCustomer(Long pInventoryId) {
    InventoryHeldByCustomer f = new InventoryHeldByCustomer();
    f.setPInventoryId(pInventoryId);

    return f.asField();
  }

  /** Get <code>public.inventory_held_by_customer</code> as a field. */
  public static Field<Integer> inventoryHeldByCustomer(Field<Long> pInventoryId) {
    InventoryHeldByCustomer f = new InventoryHeldByCustomer();
    f.setPInventoryId(pInventoryId);

    return f.asField();
  }

  /** Call <code>public.inventory_in_stock</code> */
  public static Boolean inventoryInStock(Configuration configuration, Long pInventoryId) {
    InventoryInStock f = new InventoryInStock();
    f.setPInventoryId(pInventoryId);

    f.execute(configuration);
    return f.getReturnValue();
  }

  /** Get <code>public.inventory_in_stock</code> as a field. */
  public static Field<Boolean> inventoryInStock(Long pInventoryId) {
    InventoryInStock f = new InventoryInStock();
    f.setPInventoryId(pInventoryId);

    return f.asField();
  }

  /** Get <code>public.inventory_in_stock</code> as a field. */
  public static Field<Boolean> inventoryInStock(Field<Long> pInventoryId) {
    InventoryInStock f = new InventoryInStock();
    f.setPInventoryId(pInventoryId);

    return f.asField();
  }

  /** Call <code>public.last_day</code> */
  public static LocalDate lastDay(Configuration configuration, LocalDateTime __1) {
    LastDay f = new LastDay();
    f.set__1(__1);

    f.execute(configuration);
    return f.getReturnValue();
  }

  /** Get <code>public.last_day</code> as a field. */
  public static Field<LocalDate> lastDay(LocalDateTime __1) {
    LastDay f = new LastDay();
    f.set__1(__1);

    return f.asField();
  }

  /** Get <code>public.last_day</code> as a field. */
  public static Field<LocalDate> lastDay(Field<LocalDateTime> __1) {
    LastDay f = new LastDay();
    f.set__1(__1);

    return f.asField();
  }

  /** Call <code>public.film_in_stock</code>. */
  public static Result<FilmInStockRecord> filmInStock(
      Configuration configuration, Long pFilmId, Long pStoreId) {
    return configuration
        .dsl()
        .selectFrom(
            org.jooq.example.testcontainers.db.tables.FilmInStock.FILM_IN_STOCK.call(
                pFilmId, pStoreId))
        .fetch();
  }

  /** Get <code>public.film_in_stock</code> as a table. */
  public static FilmInStock filmInStock(Long pFilmId, Long pStoreId) {
    return org.jooq.example.testcontainers.db.tables.FilmInStock.FILM_IN_STOCK.call(
        pFilmId, pStoreId);
  }

  /** Get <code>public.film_in_stock</code> as a table. */
  public static FilmInStock filmInStock(Field<Long> pFilmId, Field<Long> pStoreId) {
    return org.jooq.example.testcontainers.db.tables.FilmInStock.FILM_IN_STOCK.call(
        pFilmId, pStoreId);
  }

  /** Call <code>public.film_not_in_stock</code>. */
  public static Result<FilmNotInStockRecord> filmNotInStock(
      Configuration configuration, Long pFilmId, Long pStoreId) {
    return configuration
        .dsl()
        .selectFrom(
            org.jooq.example.testcontainers.db.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(
                pFilmId, pStoreId))
        .fetch();
  }

  /** Get <code>public.film_not_in_stock</code> as a table. */
  public static FilmNotInStock filmNotInStock(Long pFilmId, Long pStoreId) {
    return org.jooq.example.testcontainers.db.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(
        pFilmId, pStoreId);
  }

  /** Get <code>public.film_not_in_stock</code> as a table. */
  public static FilmNotInStock filmNotInStock(Field<Long> pFilmId, Field<Long> pStoreId) {
    return org.jooq.example.testcontainers.db.tables.FilmNotInStock.FILM_NOT_IN_STOCK.call(
        pFilmId, pStoreId);
  }

  /** Call <code>public.rewards_report</code>. */
  public static Result<RewardsReportRecord> rewardsReport(
      Configuration configuration,
      Integer minMonthlyPurchases,
      BigDecimal minDollarAmountPurchased) {
    return configuration
        .dsl()
        .selectFrom(
            org.jooq.example.testcontainers.db.tables.RewardsReport.REWARDS_REPORT.call(
                minMonthlyPurchases, minDollarAmountPurchased))
        .fetch();
  }

  /** Get <code>public.rewards_report</code> as a table. */
  public static RewardsReport rewardsReport(
      Integer minMonthlyPurchases, BigDecimal minDollarAmountPurchased) {
    return org.jooq.example.testcontainers.db.tables.RewardsReport.REWARDS_REPORT.call(
        minMonthlyPurchases, minDollarAmountPurchased);
  }

  /** Get <code>public.rewards_report</code> as a table. */
  public static RewardsReport rewardsReport(
      Field<Integer> minMonthlyPurchases, Field<BigDecimal> minDollarAmountPurchased) {
    return org.jooq.example.testcontainers.db.tables.RewardsReport.REWARDS_REPORT.call(
        minMonthlyPurchases, minDollarAmountPurchased);
  }
}
