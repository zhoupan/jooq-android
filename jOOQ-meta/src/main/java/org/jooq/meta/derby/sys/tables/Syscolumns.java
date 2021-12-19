/*
 * This file is generated by jOOQ.
 */
package org.jooq.meta.derby.sys.tables;


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
import org.jooq.meta.derby.sys.Sys;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Syscolumns extends TableImpl<Record> {

    private static final long serialVersionUID = -62833438;

    /**
     * The reference instance of <code>SYS.SYSCOLUMNS</code>
     */
    public static final Syscolumns SYSCOLUMNS = new Syscolumns();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>SYS.SYSCOLUMNS.REFERENCEID</code>.
     */
    public final TableField<Record, String> REFERENCEID = createField(DSL.name("REFERENCEID"), SQLDataType.CHAR(36).nullable(false), this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.COLUMNNAME</code>.
     */
    public final TableField<Record, String> COLUMNNAME = createField(DSL.name("COLUMNNAME"), SQLDataType.VARCHAR(128).nullable(false), this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.COLUMNNUMBER</code>.
     */
    public final TableField<Record, Integer> COLUMNNUMBER = createField(DSL.name("COLUMNNUMBER"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.COLUMNDATATYPE</code>.
     */
    public final TableField<Record, String> COLUMNDATATYPE = createField(DSL.name("COLUMNDATATYPE"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.COLUMNDEFAULT</code>.
     */
    public final TableField<Record, String> COLUMNDEFAULT = createField(DSL.name("COLUMNDEFAULT"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.COLUMNDEFAULTID</code>.
     */
    public final TableField<Record, String> COLUMNDEFAULTID = createField(DSL.name("COLUMNDEFAULTID"), SQLDataType.CHAR(36), this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.AUTOINCREMENTVALUE</code>.
     */
    public final TableField<Record, Long> AUTOINCREMENTVALUE = createField(DSL.name("AUTOINCREMENTVALUE"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.AUTOINCREMENTSTART</code>.
     */
    public final TableField<Record, Long> AUTOINCREMENTSTART = createField(DSL.name("AUTOINCREMENTSTART"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.AUTOINCREMENTINC</code>.
     */
    public final TableField<Record, Long> AUTOINCREMENTINC = createField(DSL.name("AUTOINCREMENTINC"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>SYS.SYSCOLUMNS.AUTOINCREMENTCYCLE</code>.
     */
    public final TableField<Record, Boolean> AUTOINCREMENTCYCLE = createField(DSL.name("AUTOINCREMENTCYCLE"), SQLDataType.BOOLEAN, this, "");

    private Syscolumns(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Syscolumns(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>SYS.SYSCOLUMNS</code> table reference
     */
    public Syscolumns(String alias) {
        this(DSL.name(alias), SYSCOLUMNS);
    }

    /**
     * Create an aliased <code>SYS.SYSCOLUMNS</code> table reference
     */
    public Syscolumns(Name alias) {
        this(alias, SYSCOLUMNS);
    }

    /**
     * Create a <code>SYS.SYSCOLUMNS</code> table reference
     */
    public Syscolumns() {
        this(DSL.name("SYSCOLUMNS"), null);
    }

    public <O extends Record> Syscolumns(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, SYSCOLUMNS);
    }

    @Override
    public Schema getSchema() {
        return Sys.SYS;
    }

    @Override
    public Syscolumns as(String alias) {
        return new Syscolumns(DSL.name(alias), this);
    }

    @Override
    public Syscolumns as(Name alias) {
        return new Syscolumns(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Syscolumns rename(String name) {
        return new Syscolumns(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Syscolumns rename(Name name) {
        return new Syscolumns(name, null);
    }
}
