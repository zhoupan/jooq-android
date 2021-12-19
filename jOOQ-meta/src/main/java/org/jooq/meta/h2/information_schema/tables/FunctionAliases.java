/*
 * This file is generated by jOOQ.
 */
package org.jooq.meta.h2.information_schema.tables;


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
import org.jooq.meta.h2.information_schema.InformationSchema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FunctionAliases extends TableImpl<Record> {

    private static final long serialVersionUID = -1921953301;

    /**
     * The reference instance of <code>INFORMATION_SCHEMA.FUNCTION_ALIASES</code>
     */
    public static final FunctionAliases FUNCTION_ALIASES = new FunctionAliases();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.ALIAS_CATALOG</code>.
     */
    public final TableField<Record, String> ALIAS_CATALOG = createField(DSL.name("ALIAS_CATALOG"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.ALIAS_SCHEMA</code>.
     */
    public final TableField<Record, String> ALIAS_SCHEMA = createField(DSL.name("ALIAS_SCHEMA"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.ALIAS_NAME</code>.
     */
    public final TableField<Record, String> ALIAS_NAME = createField(DSL.name("ALIAS_NAME"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.JAVA_CLASS</code>.
     */
    public final TableField<Record, String> JAVA_CLASS = createField(DSL.name("JAVA_CLASS"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.JAVA_METHOD</code>.
     */
    public final TableField<Record, String> JAVA_METHOD = createField(DSL.name("JAVA_METHOD"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.DATA_TYPE</code>.
     */
    public final TableField<Record, Integer> DATA_TYPE = createField(DSL.name("DATA_TYPE"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.TYPE_NAME</code>.
     */
    public final TableField<Record, String> TYPE_NAME = createField(DSL.name("TYPE_NAME"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.COLUMN_COUNT</code>.
     */
    public final TableField<Record, Integer> COLUMN_COUNT = createField(DSL.name("COLUMN_COUNT"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.RETURNS_RESULT</code>.
     */
    public final TableField<Record, Short> RETURNS_RESULT = createField(DSL.name("RETURNS_RESULT"), SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.REMARKS</code>.
     */
    public final TableField<Record, String> REMARKS = createField(DSL.name("REMARKS"), SQLDataType.VARCHAR(2147483647), this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.ID</code>.
     */
    public final TableField<Record, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>INFORMATION_SCHEMA.FUNCTION_ALIASES.SOURCE</code>.
     */
    public final TableField<Record, String> SOURCE = createField(DSL.name("SOURCE"), SQLDataType.VARCHAR(2147483647), this, "");

    private FunctionAliases(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private FunctionAliases(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.FUNCTION_ALIASES</code> table reference
     */
    public FunctionAliases(String alias) {
        this(DSL.name(alias), FUNCTION_ALIASES);
    }

    /**
     * Create an aliased <code>INFORMATION_SCHEMA.FUNCTION_ALIASES</code> table reference
     */
    public FunctionAliases(Name alias) {
        this(alias, FUNCTION_ALIASES);
    }

    /**
     * Create a <code>INFORMATION_SCHEMA.FUNCTION_ALIASES</code> table reference
     */
    public FunctionAliases() {
        this(DSL.name("FUNCTION_ALIASES"), null);
    }

    public <O extends Record> FunctionAliases(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, FUNCTION_ALIASES);
    }

    @Override
    public Schema getSchema() {
        return InformationSchema.INFORMATION_SCHEMA;
    }

    @Override
    public FunctionAliases as(String alias) {
        return new FunctionAliases(DSL.name(alias), this);
    }

    @Override
    public FunctionAliases as(Name alias) {
        return new FunctionAliases(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public FunctionAliases rename(String name) {
        return new FunctionAliases(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public FunctionAliases rename(Name name) {
        return new FunctionAliases(name, null);
    }
}
