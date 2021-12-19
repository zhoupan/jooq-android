package org.jooq.impl;

import static org.jooq.SQLDialect.*;

import java.util.EnumMap;

import org.jooq.SQLDialect;

// The queries generated from the various jOOQ-meta ResultQueryDatabase types.
final class MetaSQL {
    private static final EnumMap<SQLDialect, String> M_UNIQUE_KEYS = new EnumMap<>(SQLDialect.class);
    private static final EnumMap<SQLDialect, String> M_SEQUENCES = new EnumMap<>(SQLDialect.class);
    private static final EnumMap<SQLDialect, String> M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES = new EnumMap<>(SQLDialect.class);

    static final String M_UNIQUE_KEYS(SQLDialect dialect) {
        String result = M_UNIQUE_KEYS.get(dialect);
        return result != null ? result : M_UNIQUE_KEYS.get(dialect.family());
    }

    static final String M_SEQUENCES(SQLDialect dialect) {
        String result = M_SEQUENCES.get(dialect);
        return result != null ? result : M_SEQUENCES.get(dialect.family());
    }

    static final String M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES(SQLDialect dialect) {
        String result = M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.get(dialect);
        return result != null ? result : M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.get(dialect.family());
    }

    static {

        M_UNIQUE_KEYS.put(FIREBIRD, "select null catalog, null schema, trim(RDB$RELATION_CONSTRAINTS.RDB$RELATION_NAME) RDB$RELATION_NAME, trim(RDB$RELATION_CONSTRAINTS.RDB$CONSTRAINT_NAME) RDB$CONSTRAINT_NAME, trim(RDB$INDEX_SEGMENTS.RDB$FIELD_NAME) RDB$FIELD_NAME, RDB$INDEX_SEGMENTS.RDB$FIELD_POSITION from RDB$RELATION_CONSTRAINTS join RDB$INDEX_SEGMENTS on RDB$INDEX_SEGMENTS.RDB$INDEX_NAME = RDB$RELATION_CONSTRAINTS.RDB$INDEX_NAME where RDB$RELATION_CONSTRAINTS.RDB$CONSTRAINT_TYPE = 'UNIQUE' order by RDB$RELATION_CONSTRAINTS.RDB$CONSTRAINT_NAME asc, RDB$INDEX_SEGMENTS.RDB$FIELD_POSITION asc");
        M_UNIQUE_KEYS.put(H2, "select INFORMATION_SCHEMA.CONSTRAINTS.TABLE_CATALOG, INFORMATION_SCHEMA.CONSTRAINTS.TABLE_SCHEMA, INFORMATION_SCHEMA.CONSTRAINTS.TABLE_NAME, INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_NAME, INFORMATION_SCHEMA.INDEXES.COLUMN_NAME, INFORMATION_SCHEMA.INDEXES.ORDINAL_POSITION from INFORMATION_SCHEMA.CONSTRAINTS join INFORMATION_SCHEMA.INDEXES on (INFORMATION_SCHEMA.CONSTRAINTS.TABLE_SCHEMA = INFORMATION_SCHEMA.INDEXES.TABLE_SCHEMA and INFORMATION_SCHEMA.CONSTRAINTS.TABLE_NAME = INFORMATION_SCHEMA.INDEXES.TABLE_NAME and INFORMATION_SCHEMA.CONSTRAINTS.UNIQUE_INDEX_NAME = INFORMATION_SCHEMA.INDEXES.INDEX_NAME) where (INFORMATION_SCHEMA.CONSTRAINTS.TABLE_SCHEMA in (cast(? as varchar(2147483647))) and INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_TYPE in ('UNIQUE')) order by INFORMATION_SCHEMA.CONSTRAINTS.TABLE_SCHEMA, INFORMATION_SCHEMA.CONSTRAINTS.CONSTRAINT_NAME, INFORMATION_SCHEMA.INDEXES.ORDINAL_POSITION");
        M_UNIQUE_KEYS.put(HSQLDB, "select INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_CATALOG, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_SCHEMA, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_NAME, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_NAME, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.COLUMN_NAME, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.ORDINAL_POSITION from (INFORMATION_SCHEMA.KEY_COLUMN_USAGE left outer join INFORMATION_SCHEMA.TABLE_CONSTRAINTS as alias_10316587 on (INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_CATALOG = alias_10316587.CONSTRAINT_CATALOG and INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_SCHEMA = alias_10316587.CONSTRAINT_SCHEMA and INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_NAME = alias_10316587.CONSTRAINT_NAME)) where (alias_10316587.CONSTRAINT_TYPE = 'UNIQUE' and alias_10316587.TABLE_SCHEMA in (cast(? as varchar(128)))) order by INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_SCHEMA asc, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.TABLE_NAME asc, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.CONSTRAINT_NAME asc, INFORMATION_SCHEMA.KEY_COLUMN_USAGE.ORDINAL_POSITION asc");
        M_UNIQUE_KEYS.put(MARIADB, "select distinct null as TABLE_CATALOG, information_schema.STATISTICS.TABLE_SCHEMA, information_schema.STATISTICS.TABLE_NAME, information_schema.STATISTICS.INDEX_NAME, information_schema.STATISTICS.COLUMN_NAME, information_schema.STATISTICS.SEQ_IN_INDEX from information_schema.STATISTICS where (information_schema.STATISTICS.TABLE_SCHEMA in (?, 'ee7f6174-34f2-484b-8d81-20a4d9fc866d') and information_schema.STATISTICS.INDEX_NAME <> 'PRIMARY' and information_schema.STATISTICS.NON_UNIQUE = 0) order by information_schema.STATISTICS.TABLE_SCHEMA, information_schema.STATISTICS.TABLE_NAME, information_schema.STATISTICS.INDEX_NAME, information_schema.STATISTICS.SEQ_IN_INDEX");
        M_UNIQUE_KEYS.put(MYSQL, "select distinct null as TABLE_CATALOG, information_schema.STATISTICS.TABLE_SCHEMA, information_schema.STATISTICS.TABLE_NAME, information_schema.STATISTICS.INDEX_NAME, information_schema.STATISTICS.COLUMN_NAME, information_schema.STATISTICS.SEQ_IN_INDEX from information_schema.STATISTICS where (information_schema.STATISTICS.TABLE_SCHEMA in (?, 'ee7f6174-34f2-484b-8d81-20a4d9fc866d') and information_schema.STATISTICS.INDEX_NAME <> 'PRIMARY' and information_schema.STATISTICS.NON_UNIQUE = 0) order by information_schema.STATISTICS.TABLE_SCHEMA, information_schema.STATISTICS.TABLE_NAME, information_schema.STATISTICS.INDEX_NAME, information_schema.STATISTICS.SEQ_IN_INDEX");
        M_UNIQUE_KEYS.put(POSTGRES, "select k.table_catalog, k.table_schema, k.table_name, k.constraint_name, k.column_name, k.ordinal_position from (pg_catalog.pg_constraint as c join (pg_catalog.pg_class as alias_89552482 join pg_catalog.pg_namespace as alias_126892781 on alias_89552482.relnamespace = alias_126892781.oid) on c.conrelid = alias_89552482.oid join pg_catalog.pg_namespace as alias_87501535 on c.connamespace = alias_87501535.oid) join information_schema.key_column_usage as k on (k.table_schema = alias_126892781.nspname and k.table_name = alias_89552482.relname and k.constraint_schema = alias_87501535.nspname and k.constraint_name = c.conname) where (c.contype = 'u' and alias_87501535.nspname in (?)) order by k.table_schema asc, k.table_name asc, k.constraint_name asc, k.ordinal_position asc");














































        M_SEQUENCES.put(DERBY, "select cast(null as varchar(32672)) as catalog, alias_8805161.SCHEMANAME, SYS.SYSSEQUENCES.SEQUENCENAME, SYS.SYSSEQUENCES.SEQUENCEDATATYPE, cast(null as int) as numeric_precision, cast(null as int) as numeric_scale, nullif(SYS.SYSSEQUENCES.STARTVALUE, 1) as STARTVALUE, nullif(SYS.SYSSEQUENCES.INCREMENT, 1) as INCREMENT, nullif(SYS.SYSSEQUENCES.MINIMUMVALUE, case when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'SMALLINT' then -32768 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'INTEGER' then -2147483648 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'BIGINT' then -9223372036854775808 end) as MINIMUMVALUE, nullif(SYS.SYSSEQUENCES.MAXIMUMVALUE, case when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'SMALLINT' then 32767 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'INTEGER' then 2147483647 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'BIGINT' then 9223372036854775807 end) as MAXIMUMVALUE, (SYS.SYSSEQUENCES.CYCLEOPTION = 'Y') as CYCLEOPTION, cast(null as bigint) as cache from (SYS.SYSSEQUENCES join SYS.SYSSCHEMAS as alias_8805161 on SYS.SYSSEQUENCES.SCHEMAID = alias_8805161.SCHEMAID) where cast(alias_8805161.SCHEMANAME as varchar(32672)) in (cast(? as varchar(32672))) order by alias_8805161.SCHEMANAME, SYS.SYSSEQUENCES.SEQUENCENAME");
        M_SEQUENCES.put(FIREBIRD, "select null catalog, null schema, trim(RDB$GENERATORS.RDB$GENERATOR_NAME) RDB$GENERATOR_NAME, 'BIGINT' type_name, null numeric_precision, null numeric_scale, nullif(RDB$GENERATORS.RDB$INITIAL_VALUE, 0) RDB$INITIAL_VALUE, nullif(RDB$GENERATORS.RDB$GENERATOR_INCREMENT, 1) RDB$GENERATOR_INCREMENT, null min_value, null max_value, null cycle, null cache from RDB$GENERATORS where RDB$GENERATORS.RDB$GENERATOR_NAME not in (select RDB$RELATION_FIELDS.RDB$GENERATOR_NAME from RDB$RELATION_FIELDS where (RDB$RELATION_FIELDS.RDB$GENERATOR_NAME is not null and RDB$RELATION_FIELDS.RDB$IDENTITY_TYPE = 1)) order by RDB$GENERATORS.RDB$GENERATOR_NAME");
        M_SEQUENCES.put(H2, "select null catalog, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME, 'BIGINT' type_name, null precision, null scale, null start_value, nullif(INFORMATION_SCHEMA.SEQUENCES.INCREMENT, 1) INCREMENT, nullif(INFORMATION_SCHEMA.SEQUENCES.MIN_VALUE, 1) MIN_VALUE, nullif(INFORMATION_SCHEMA.SEQUENCES.MAX_VALUE, 9223372036854775807) MAX_VALUE, INFORMATION_SCHEMA.SEQUENCES.IS_CYCLE, nullif(INFORMATION_SCHEMA.SEQUENCES.CACHE, 32) CACHE from INFORMATION_SCHEMA.SEQUENCES where (INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA in (cast(? as varchar(2147483647))) and upper(INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME) not like 'SYSTEM!_SEQUENCE!_%' escape '!') order by INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME");
        M_SEQUENCES.put(HSQLDB, "select null as catalog, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME, INFORMATION_SCHEMA.SEQUENCES.DATA_TYPE, INFORMATION_SCHEMA.SEQUENCES.NUMERIC_PRECISION, INFORMATION_SCHEMA.SEQUENCES.NUMERIC_SCALE, INFORMATION_SCHEMA.SEQUENCES.START_WITH, INFORMATION_SCHEMA.SEQUENCES.INCREMENT, INFORMATION_SCHEMA.SEQUENCES.MINIMUM_VALUE, INFORMATION_SCHEMA.SEQUENCES.MAXIMUM_VALUE, case when INFORMATION_SCHEMA.SEQUENCES.CYCLE_OPTION is not distinct from 'YES' then true else false end as CYCLE_OPTION, null as cache from INFORMATION_SCHEMA.SEQUENCES where INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA in (cast(? as varchar(128))) order by INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME");
        M_SEQUENCES.put(POSTGRES, "with schemas(schema) as (select v.c1 from (values (?)) as v (c1)) (select null as catalog, information_schema.sequences.sequence_schema, information_schema.sequences.sequence_name, information_schema.sequences.data_type, information_schema.sequences.numeric_precision, information_schema.sequences.numeric_scale, nullif(cast(information_schema.sequences.start_value as bigint), 1) as start_value, nullif(cast(information_schema.sequences.increment as bigint), 1) as increment, nullif(cast(information_schema.sequences.minimum_value as bigint), 1) as minimum_value, nullif(cast(information_schema.sequences.maximum_value as decimal), (power(cast(2 as decimal), cast((information_schema.sequences.numeric_precision - 1) as decimal)) - 1)) as maximum_value, cast(information_schema.sequences.cycle_option as boolean) as cycle_option, null as cache from information_schema.sequences where (information_schema.sequences.sequence_schema in (select schemas.schema from schemas) and (information_schema.sequences.sequence_schema, information_schema.sequences.sequence_name) not in (select information_schema.columns.table_schema, (((information_schema.columns.table_name || '_') || information_schema.columns.column_name) || '_seq') from information_schema.columns where (information_schema.columns.column_default = (('nextval(''' || ((information_schema.columns.table_name || '_') || information_schema.columns.column_name)) || '_seq''::regclass)') or information_schema.columns.column_default = (('nextval(''' || ((((information_schema.columns.table_schema || '.') || information_schema.columns.table_name) || '_') || information_schema.columns.column_name)) || '_seq''::regclass)'))))) union all (select '', '', '', '', 0, 0, 0, 0, 0, 0, false, 0 where false) order by 2, 3");


































        M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.put(DERBY, "select cast(null as varchar(32672)) as catalog, alias_8805161.SCHEMANAME, SYS.SYSSEQUENCES.SEQUENCENAME, SYS.SYSSEQUENCES.SEQUENCEDATATYPE, cast(null as int) as numeric_precision, cast(null as int) as numeric_scale, nullif(SYS.SYSSEQUENCES.STARTVALUE, 1) as STARTVALUE, nullif(SYS.SYSSEQUENCES.INCREMENT, 1) as INCREMENT, nullif(SYS.SYSSEQUENCES.MINIMUMVALUE, case when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'SMALLINT' then -32768 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'INTEGER' then -2147483648 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'BIGINT' then -9223372036854775808 end) as MINIMUMVALUE, nullif(SYS.SYSSEQUENCES.MAXIMUMVALUE, case when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'SMALLINT' then 32767 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'INTEGER' then 2147483647 when cast(SYS.SYSSEQUENCES.SEQUENCEDATATYPE as varchar(32672)) = 'BIGINT' then 9223372036854775807 end) as MAXIMUMVALUE, (SYS.SYSSEQUENCES.CYCLEOPTION = 'Y') as CYCLEOPTION, cast(null as bigint) as cache from (SYS.SYSSEQUENCES join SYS.SYSSCHEMAS as alias_8805161 on SYS.SYSSEQUENCES.SCHEMAID = alias_8805161.SCHEMAID) where cast(alias_8805161.SCHEMANAME as varchar(32672)) in (cast(? as varchar(32672))) order by alias_8805161.SCHEMANAME, SYS.SYSSEQUENCES.SEQUENCENAME");
        M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.put(FIREBIRD, "select null catalog, null schema, trim(RDB$GENERATORS.RDB$GENERATOR_NAME) RDB$GENERATOR_NAME, 'BIGINT' type_name, null numeric_precision, null numeric_scale, nullif(RDB$GENERATORS.RDB$INITIAL_VALUE, 0) RDB$INITIAL_VALUE, nullif(RDB$GENERATORS.RDB$GENERATOR_INCREMENT, 1) RDB$GENERATOR_INCREMENT, null min_value, null max_value, null cycle, null cache from RDB$GENERATORS order by RDB$GENERATORS.RDB$GENERATOR_NAME");
        M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.put(H2, "select null catalog, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME, 'BIGINT' type_name, null precision, null scale, null start_value, nullif(INFORMATION_SCHEMA.SEQUENCES.INCREMENT, 1) INCREMENT, nullif(INFORMATION_SCHEMA.SEQUENCES.MIN_VALUE, 1) MIN_VALUE, nullif(INFORMATION_SCHEMA.SEQUENCES.MAX_VALUE, 9223372036854775807) MAX_VALUE, INFORMATION_SCHEMA.SEQUENCES.IS_CYCLE, nullif(INFORMATION_SCHEMA.SEQUENCES.CACHE, 32) CACHE from INFORMATION_SCHEMA.SEQUENCES where INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA in (cast(? as varchar(2147483647))) order by INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME");
        M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.put(HSQLDB, "select null as catalog, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME, INFORMATION_SCHEMA.SEQUENCES.DATA_TYPE, INFORMATION_SCHEMA.SEQUENCES.NUMERIC_PRECISION, INFORMATION_SCHEMA.SEQUENCES.NUMERIC_SCALE, INFORMATION_SCHEMA.SEQUENCES.START_WITH, INFORMATION_SCHEMA.SEQUENCES.INCREMENT, INFORMATION_SCHEMA.SEQUENCES.MINIMUM_VALUE, INFORMATION_SCHEMA.SEQUENCES.MAXIMUM_VALUE, case when INFORMATION_SCHEMA.SEQUENCES.CYCLE_OPTION is not distinct from 'YES' then true else false end as CYCLE_OPTION, null as cache from INFORMATION_SCHEMA.SEQUENCES where INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA in (cast(? as varchar(128))) order by INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_SCHEMA, INFORMATION_SCHEMA.SEQUENCES.SEQUENCE_NAME");
        M_SEQUENCES_INCLUDING_SYSTEM_SEQUENCES.put(POSTGRES, "with schemas(schema) as (select v.c1 from (values (?)) as v (c1)) (select null as catalog, information_schema.sequences.sequence_schema, information_schema.sequences.sequence_name, information_schema.sequences.data_type, information_schema.sequences.numeric_precision, information_schema.sequences.numeric_scale, nullif(cast(information_schema.sequences.start_value as bigint), 1) as start_value, nullif(cast(information_schema.sequences.increment as bigint), 1) as increment, nullif(cast(information_schema.sequences.minimum_value as bigint), 1) as minimum_value, nullif(cast(information_schema.sequences.maximum_value as decimal), (power(cast(2 as decimal), cast((information_schema.sequences.numeric_precision - 1) as decimal)) - 1)) as maximum_value, cast(information_schema.sequences.cycle_option as boolean) as cycle_option, null as cache from information_schema.sequences where information_schema.sequences.sequence_schema in (select schemas.schema from schemas)) union all (select null, alias_14763223.nspname, alias_75351712.relname, alias_109453426.typname, information_schema._pg_numeric_precision(alias_109453426.typbasetype, alias_109453426.typtypmod), 0, pg_catalog.pg_sequence.seqstart, pg_catalog.pg_sequence.seqincrement, pg_catalog.pg_sequence.seqmin, pg_catalog.pg_sequence.seqmax, pg_catalog.pg_sequence.seqcycle, null as cache from (pg_catalog.pg_sequence join (pg_catalog.pg_class as alias_75351712 join pg_catalog.pg_namespace as alias_14763223 on alias_75351712.relnamespace = alias_14763223.oid) on pg_catalog.pg_sequence.seqrelid = alias_75351712.oid join pg_catalog.pg_type as alias_109453426 on pg_catalog.pg_sequence.seqtypid = alias_109453426.oid) where (alias_14763223.nspname in (select schemas.schema from schemas) and alias_75351712.oid in (select pg_catalog.pg_depend.objid from pg_catalog.pg_depend where (pg_catalog.pg_depend.deptype = 'i' and pg_catalog.pg_depend.classid = 'pg_class'::regclass)))) order by 2, 3");

































    }
}
