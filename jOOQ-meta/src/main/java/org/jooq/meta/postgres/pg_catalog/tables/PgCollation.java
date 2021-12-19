/*
 * This file is generated by jOOQ.
 */
package org.jooq.meta.postgres.pg_catalog.tables;


import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.meta.postgres.pg_catalog.PgCatalog;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PgCollation extends TableImpl<Record> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>pg_catalog.pg_collation</code>
     */
    public static final PgCollation PG_COLLATION = new PgCollation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>pg_catalog.pg_collation.oid</code>.
     */
    public final TableField<Record, Long> OID = createField(DSL.name("oid"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collname</code>.
     */
    public final TableField<Record, String> COLLNAME = createField(DSL.name("collname"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collnamespace</code>.
     */
    public final TableField<Record, Long> COLLNAMESPACE = createField(DSL.name("collnamespace"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collowner</code>.
     */
    public final TableField<Record, Long> COLLOWNER = createField(DSL.name("collowner"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collprovider</code>.
     */
    public final TableField<Record, String> COLLPROVIDER = createField(DSL.name("collprovider"), SQLDataType.CHAR.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collisdeterministic</code>.
     */
    public final TableField<Record, Boolean> COLLISDETERMINISTIC = createField(DSL.name("collisdeterministic"), SQLDataType.BOOLEAN.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collencoding</code>.
     */
    public final TableField<Record, Integer> COLLENCODING = createField(DSL.name("collencoding"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collcollate</code>.
     */
    public final TableField<Record, String> COLLCOLLATE = createField(DSL.name("collcollate"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collctype</code>.
     */
    public final TableField<Record, String> COLLCTYPE = createField(DSL.name("collctype"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>pg_catalog.pg_collation.collversion</code>.
     */
    public final TableField<Record, String> COLLVERSION = createField(DSL.name("collversion"), SQLDataType.CLOB, this, "");

    private PgCollation(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private PgCollation(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>pg_catalog.pg_collation</code> table reference
     */
    public PgCollation(String alias) {
        this(DSL.name(alias), PG_COLLATION);
    }

    /**
     * Create an aliased <code>pg_catalog.pg_collation</code> table reference
     */
    public PgCollation(Name alias) {
        this(alias, PG_COLLATION);
    }

    /**
     * Create a <code>pg_catalog.pg_collation</code> table reference
     */
    public PgCollation() {
        this(DSL.name("pg_collation"), null);
    }

    public <O extends Record> PgCollation(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, PG_COLLATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : PgCatalog.PG_CATALOG;
    }

    @Override
    public PgCollation as(String alias) {
        return new PgCollation(DSL.name(alias), this);
    }

    @Override
    public PgCollation as(Name alias) {
        return new PgCollation(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PgCollation rename(String name) {
        return new PgCollation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PgCollation rename(Name name) {
        return new PgCollation(name, null);
    }
}
