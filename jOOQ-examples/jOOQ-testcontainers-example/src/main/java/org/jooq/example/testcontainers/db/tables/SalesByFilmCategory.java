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
package org.jooq.example.testcontainers.db.tables;

import java.math.BigDecimal;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.example.testcontainers.db.Public;
import org.jooq.example.testcontainers.db.tables.records.SalesByFilmCategoryRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class SalesByFilmCategory extends TableImpl<SalesByFilmCategoryRecord> {

  private static final long serialVersionUID = 1L;

  /** The reference instance of <code>public.sales_by_film_category</code> */
  public static final SalesByFilmCategory SALES_BY_FILM_CATEGORY = new SalesByFilmCategory();

  /** The class holding records for this type */
  @Override
  public Class<SalesByFilmCategoryRecord> getRecordType() {
    return SalesByFilmCategoryRecord.class;
  }

  /** The column <code>public.sales_by_film_category.category</code>. */
  public final TableField<SalesByFilmCategoryRecord, String> CATEGORY =
      createField(DSL.name("category"), SQLDataType.VARCHAR(25), this, "");

  /** The column <code>public.sales_by_film_category.total_sales</code>. */
  public final TableField<SalesByFilmCategoryRecord, BigDecimal> TOTAL_SALES =
      createField(DSL.name("total_sales"), SQLDataType.NUMERIC, this, "");

  private SalesByFilmCategory(Name alias, Table<SalesByFilmCategoryRecord> aliased) {
    this(alias, aliased, null);
  }

  private SalesByFilmCategory(
      Name alias, Table<SalesByFilmCategoryRecord> aliased, Field<?>[] parameters) {
    super(
        alias,
        null,
        aliased,
        parameters,
        DSL.comment(""),
        TableOptions.view(
            "create view \"sales_by_film_category\" as  SELECT c.name AS category,\n    sum(p.amount) AS total_sales\n   FROM (((((payment p\n     JOIN rental r ON ((p.rental_id = r.rental_id)))\n     JOIN inventory i ON ((r.inventory_id = i.inventory_id)))\n     JOIN film f ON ((i.film_id = f.film_id)))\n     JOIN film_category fc ON ((f.film_id = fc.film_id)))\n     JOIN category c ON ((fc.category_id = c.category_id)))\n  GROUP BY c.name\n  ORDER BY (sum(p.amount)) DESC;"));
  }

  /** Create an aliased <code>public.sales_by_film_category</code> table reference */
  public SalesByFilmCategory(String alias) {
    this(DSL.name(alias), SALES_BY_FILM_CATEGORY);
  }

  /** Create an aliased <code>public.sales_by_film_category</code> table reference */
  public SalesByFilmCategory(Name alias) {
    this(alias, SALES_BY_FILM_CATEGORY);
  }

  /** Create a <code>public.sales_by_film_category</code> table reference */
  public SalesByFilmCategory() {
    this(DSL.name("sales_by_film_category"), null);
  }

  public <O extends Record> SalesByFilmCategory(
      Table<O> child, ForeignKey<O, SalesByFilmCategoryRecord> key) {
    super(child, key, SALES_BY_FILM_CATEGORY);
  }

  @Override
  public Schema getSchema() {
    return aliased() ? null : Public.PUBLIC;
  }

  @Override
  public SalesByFilmCategory as(String alias) {
    return new SalesByFilmCategory(DSL.name(alias), this);
  }

  @Override
  public SalesByFilmCategory as(Name alias) {
    return new SalesByFilmCategory(alias, this);
  }

  /** Rename this table */
  @Override
  public SalesByFilmCategory rename(String name) {
    return new SalesByFilmCategory(DSL.name(name), null);
  }

  /** Rename this table */
  @Override
  public SalesByFilmCategory rename(Name name) {
    return new SalesByFilmCategory(name, null);
  }

  // -------------------------------------------------------------------------
  // Row2 type methods
  // -------------------------------------------------------------------------

  @Override
  public Row2<String, BigDecimal> fieldsRow() {
    return (Row2) super.fieldsRow();
  }
}
