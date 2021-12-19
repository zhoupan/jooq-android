/*
 * This file is generated by jOOQ.
 */
package org.jooq.example.testcontainers.db.tables;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.example.testcontainers.db.Public;
import org.jooq.example.testcontainers.db.tables.records.RewardsReportRecord;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RewardsReport extends TableImpl<RewardsReportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.rewards_report</code>
     */
    public static final RewardsReport REWARDS_REPORT = new RewardsReport();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RewardsReportRecord> getRecordType() {
        return RewardsReportRecord.class;
    }

    /**
     * The column <code>public.rewards_report.customer_id</code>.
     */
    public final TableField<RewardsReportRecord, Long> CUSTOMER_ID = createField(DSL.name("customer_id"), SQLDataType.BIGINT.nullable(false).identity(true).defaultValue(DSL.field("nextval('customer_customer_id_seq'::regclass)", SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.rewards_report.store_id</code>.
     */
    public final TableField<RewardsReportRecord, Long> STORE_ID = createField(DSL.name("store_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.rewards_report.first_name</code>.
     */
    public final TableField<RewardsReportRecord, String> FIRST_NAME = createField(DSL.name("first_name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>public.rewards_report.last_name</code>.
     */
    public final TableField<RewardsReportRecord, String> LAST_NAME = createField(DSL.name("last_name"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>public.rewards_report.email</code>.
     */
    public final TableField<RewardsReportRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(50), this, "");

    /**
     * The column <code>public.rewards_report.address_id</code>.
     */
    public final TableField<RewardsReportRecord, Long> ADDRESS_ID = createField(DSL.name("address_id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.rewards_report.activebool</code>.
     */
    public final TableField<RewardsReportRecord, Boolean> ACTIVEBOOL = createField(DSL.name("activebool"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.rewards_report.create_date</code>.
     */
    public final TableField<RewardsReportRecord, LocalDate> CREATE_DATE = createField(DSL.name("create_date"), SQLDataType.LOCALDATE.nullable(false).defaultValue(DSL.field("('now'::text)::date", SQLDataType.LOCALDATE)), this, "");

    /**
     * The column <code>public.rewards_report.last_update</code>.
     */
    public final TableField<RewardsReportRecord, LocalDateTime> LAST_UPDATE = createField(DSL.name("last_update"), SQLDataType.LOCALDATETIME(0).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.rewards_report.active</code>.
     */
    public final TableField<RewardsReportRecord, Integer> ACTIVE = createField(DSL.name("active"), SQLDataType.INTEGER, this, "");

    private RewardsReport(Name alias, Table<RewardsReportRecord> aliased) {
        this(alias, aliased, new Field[] {
            DSL.val(null, SQLDataType.INTEGER),
            DSL.val(null, SQLDataType.NUMERIC)
        });
    }

    private RewardsReport(Name alias, Table<RewardsReportRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.function());
    }

    /**
     * Create an aliased <code>public.rewards_report</code> table reference
     */
    public RewardsReport(String alias) {
        this(DSL.name(alias), REWARDS_REPORT);
    }

    /**
     * Create an aliased <code>public.rewards_report</code> table reference
     */
    public RewardsReport(Name alias) {
        this(alias, REWARDS_REPORT);
    }

    /**
     * Create a <code>public.rewards_report</code> table reference
     */
    public RewardsReport() {
        this(DSL.name("rewards_report"), null);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Public.PUBLIC;
    }

    @Override
    public Identity<RewardsReportRecord, Long> getIdentity() {
        return (Identity<RewardsReportRecord, Long>) super.getIdentity();
    }

    @Override
    public RewardsReport as(String alias) {
        return new RewardsReport(DSL.name(alias), this, parameters);
    }

    @Override
    public RewardsReport as(Name alias) {
        return new RewardsReport(alias, this, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public RewardsReport rename(String name) {
        return new RewardsReport(DSL.name(name), null, parameters);
    }

    /**
     * Rename this table
     */
    @Override
    public RewardsReport rename(Name name) {
        return new RewardsReport(name, null, parameters);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Long, Long, String, String, String, Long, Boolean, LocalDate, LocalDateTime, Integer> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    /**
     * Call this table-valued function
     */
    public RewardsReport call(
          Integer minMonthlyPurchases
        , BigDecimal minDollarAmountPurchased
    ) {
        RewardsReport result = new RewardsReport(DSL.name("rewards_report"), null, new Field[] {
            DSL.val(minMonthlyPurchases, SQLDataType.INTEGER),
            DSL.val(minDollarAmountPurchased, SQLDataType.NUMERIC)
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }

    /**
     * Call this table-valued function
     */
    public RewardsReport call(
          Field<Integer> minMonthlyPurchases
        , Field<BigDecimal> minDollarAmountPurchased
    ) {
        RewardsReport result = new RewardsReport(DSL.name("rewards_report"), null, new Field[] {
            minMonthlyPurchases,
            minDollarAmountPurchased
        });

        return aliased() ? result.as(getUnqualifiedName()) : result;
    }
}
