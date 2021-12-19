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
package org.jooq.impl;

import static org.jooq.impl.DSL.keyword;

import org.jooq.Keyword;

/**
 * An internal {@link Keyword} cache.
 *
 * @author Lukas Eder
 */
final class Keywords {

  static final Keyword K_ABSENT = keyword("absent");
  static final Keyword K_ADD = keyword("add");
  static final Keyword K_ADD_COLUMN = keyword("add column");
  static final Keyword K_AFTER = keyword("after");
  static final Keyword K_ALIAS = keyword("alias");
  static final Keyword K_ALL = keyword("all");
  static final Keyword K_ALTER = keyword("alter");
  static final Keyword K_ALTER_COLUMN = keyword("alter column");
  static final Keyword K_ALTER_CONSTRAINT = keyword("alter constraint");
  static final Keyword K_ALTER_INDEX = keyword("alter index");
  static final Keyword K_ALTER_SCHEMA = keyword("alter schema");
  static final Keyword K_ALTER_TABLE = keyword("alter table");
  static final Keyword K_AND = keyword("and");
  static final Keyword K_ARRAY = keyword("array");
  static final Keyword K_AS = keyword("as");
  static final Keyword K_AS_OF = keyword("as of");
  static final Keyword K_ATOMIC = keyword("atomic");
  static final Keyword K_AUTO = keyword("auto");
  static final Keyword K_AUTOINCREMENT = keyword("autoincrement");
  static final Keyword K_AUTO_INCREMENT = keyword("auto_increment");
  static final Keyword K_BEFORE = keyword("before");
  static final Keyword K_BEGIN = keyword("begin");
  static final Keyword K_BEGIN_CATCH = keyword("begin catch");
  static final Keyword K_BEGIN_TRY = keyword("begin try");
  static final Keyword K_BETWEEN = keyword("between");
  static final Keyword K_BLOB = keyword("blob");
  static final Keyword K_BOOLEAN = keyword("boolean");
  static final Keyword K_BOTH = keyword("both");
  static final Keyword K_BREAK = keyword("break");
  static final Keyword K_BULK_COLLECT_INTO = keyword("bulk collect into");
  static final Keyword K_BY = keyword("by");
  static final Keyword K_CACHE = keyword("cache");
  static final Keyword K_CALL = keyword("call");
  static final Keyword K_CALLED = keyword("called");
  static final Keyword K_CASCADE = keyword("cascade");
  static final Keyword K_CASE = keyword("case");
  static final Keyword K_CAST = keyword("cast");
  static final Keyword K_CATALOG = keyword("catalog");
  static final Keyword K_CHANGE = keyword("change");
  static final Keyword K_CHANGE_COLUMN = keyword("change column");
  static final Keyword K_CHARACTER_SET = keyword("character set");
  static final Keyword K_CHECK = keyword("check");
  static final Keyword K_COLLATE = keyword("collate");
  static final Keyword K_COLLATION = keyword("collation");
  static final Keyword K_COLUMN = keyword("column");
  static final Keyword K_COLUMNS = keyword("columns");
  static final Keyword K_COMMENT = keyword("comment");
  static final Keyword K_CONNECT_BY = keyword("connect by");
  static final Keyword K_CONSTRAINT = keyword("constraint");
  static final Keyword K_CONSTRAINTS = keyword("constraints");
  static final Keyword K_CONTAINED = keyword("contained");
  static final Keyword K_CONTAINS = keyword("contains");
  static final Keyword K_CONTENT = keyword("content");
  static final Keyword K_CONTINUE = keyword("continue");
  static final Keyword K_CONTINUE_IDENTITY = keyword("continue identity");
  static final Keyword K_CREATE = keyword("create");
  static final Keyword K_CROSS_JOIN_LATERAL = keyword("cross join lateral");
  static final Keyword K_CUBE = keyword("cube");
  static final Keyword K_CURRENT = keyword("current");
  static final Keyword K_CURRENT_ROW = keyword("current row");
  static final Keyword K_CURRENT_SCHEMA = keyword("current_schema");
  static final Keyword K_CURRENT_VALUE_FOR = keyword("current value for");
  static final Keyword K_CURRVAL = keyword("currval");
  static final Keyword K_CYCLE = keyword("cycle");
  static final Keyword K_DATA = keyword("data");
  static final Keyword K_DATABASE = keyword("database");
  static final Keyword K_DATE = keyword("date");
  static final Keyword K_DATETIME = keyword("datetime");
  static final Keyword K_DATETIME2 = keyword("datetime2");
  static final Keyword K_DATETIMEOFFSET = keyword("datetimeoffset");
  static final Keyword K_DAY = keyword("day");
  static final Keyword K_DAY_MICROSECOND = keyword("day_microsecond");
  static final Keyword K_DAY_MILLISECOND = keyword("day_millisecond");
  static final Keyword K_DAY_TO_SECOND = keyword("day to second");
  static final Keyword K_DECIMAL = keyword("decimal");
  static final Keyword K_DECLARE = keyword("declare");
  static final Keyword K_DEFAULT = keyword("default");
  static final Keyword K_DEFAULT_VALUES = keyword("default values");
  static final Keyword K_DEFINE = keyword("define");
  static final Keyword K_DELETE = keyword("delete");
  static final Keyword K_DENSE_RANK = keyword("dense_rank");
  static final Keyword K_DESCRIPTION = keyword("description");
  static final Keyword K_DETERMINISTIC = keyword("deterministic");
  static final Keyword K_DISABLE = keyword("disable");
  static final Keyword K_DISTINCT = keyword("distinct");
  static final Keyword K_DISTINCT_ON = keyword("distinct on");
  static final Keyword K_DO = keyword("do");
  static final Keyword K_DOCUMENT = keyword("document");
  static final Keyword K_DOMAIN = keyword("domain");
  static final Keyword K_DO_NOTHING = keyword("do nothing");
  static final Keyword K_DO_UPDATE = keyword("do update");
  static final Keyword K_DROP = keyword("drop");
  static final Keyword K_DROP_COLUMN = keyword("drop column");
  static final Keyword K_DROP_CONSTRAINT = keyword("drop constraint");
  static final Keyword K_DROP_DEFAULT = keyword("drop default");
  static final Keyword K_DROP_INDEX = keyword("drop index");
  static final Keyword K_DROP_NOT_NULL = keyword("drop not null");
  static final Keyword K_DROP_SCHEMA = keyword("drop schema");
  static final Keyword K_DROP_TABLE = keyword("drop table");
  static final Keyword K_DROP_VIEW = keyword("drop view");
  static final Keyword K_DUAL = keyword("dual");
  static final Keyword K_EACH = keyword("each");
  static final Keyword K_ELEMENTS = keyword("elements");
  static final Keyword K_ELIF = keyword("elif");
  static final Keyword K_ELSE = keyword("else");
  static final Keyword K_ELSEIF = keyword("elseif");
  static final Keyword K_ELSIF = keyword("elsif");
  static final Keyword K_EMPTY = keyword("empty");
  static final Keyword K_ENABLE = keyword("enable");
  static final Keyword K_END = keyword("end");
  static final Keyword K_END_CATCH = keyword("end catch");
  static final Keyword K_END_IF = keyword("end if");
  static final Keyword K_END_LOOP = keyword("end loop");
  static final Keyword K_END_TRY = keyword("end try");
  static final Keyword K_ENFORCED = keyword("enforced");
  static final Keyword K_ENUM = keyword("enum");
  static final Keyword K_ERROR = keyword("error");
  static final Keyword K_ESCAPE = keyword("escape");
  static final Keyword K_EXCEPT = keyword("except");
  static final Keyword K_EXCEPTION = keyword("exception");
  static final Keyword K_EXCLUDE = keyword("exclude");
  static final Keyword K_EXEC = keyword("exec");
  static final Keyword K_EXECUTE = keyword("execute");
  static final Keyword K_EXECUTE_BLOCK = keyword("execute block");
  static final Keyword K_EXECUTE_IMMEDIATE = keyword("execute immediate");
  static final Keyword K_EXECUTE_STATEMENT = keyword("execute statement");
  static final Keyword K_EXISTS = keyword("exists");
  static final Keyword K_EXIT = keyword("exit");
  static final Keyword K_FALSE = keyword("false");
  static final Keyword K_FETCH_FIRST = keyword("fetch first");
  static final Keyword K_FETCH_NEXT = keyword("fetch next");
  static final Keyword K_FILTER = keyword("filter");
  static final Keyword K_FINAL = keyword("final");
  static final Keyword K_FIRST = keyword("first");
  static final Keyword K_FOLLOWING = keyword("following");
  static final Keyword K_FOR = keyword("for");
  static final Keyword K_FORALL = keyword("forall");
  static final Keyword K_FOREIGN_KEY = keyword("foreign key");
  static final Keyword K_FORMAT = keyword("format");
  static final Keyword K_FOR_PORTION_OF = keyword("for portion of");
  static final Keyword K_FOR_SHARE = keyword("for share");
  static final Keyword K_FOR_UPDATE = keyword("for update");
  static final Keyword K_FROM = keyword("from");
  static final Keyword K_FUNCTION = keyword("function");
  static final Keyword K_GENERATED_BY_DEFAULT_AS_IDENTITY =
      keyword("generated by default as identity");
  static final Keyword K_GLOBAL_TEMPORARY = keyword("global temporary");
  static final Keyword K_GOTO = keyword("goto");
  static final Keyword K_GRANT = keyword("grant");
  static final Keyword K_GRANT_OPTION_FOR = keyword("grant option for");
  static final Keyword K_GROUPING_SETS = keyword("grouping sets");
  static final Keyword K_GROUP_BY = keyword("group by");
  static final Keyword K_HAVING = keyword("having");
  static final Keyword K_HOUR = keyword("hour");
  static final Keyword K_HOUR_TO_SECOND = keyword("hour to second");
  static final Keyword K_IDENTITY = keyword("identity");
  static final Keyword K_IF = keyword("if");
  static final Keyword K_IF_EXISTS = keyword("if exists");
  static final Keyword K_IF_NOT_EXISTS = keyword("if not exists");
  static final Keyword K_IGNORE = keyword("ignore");
  static final Keyword K_IGNORE_NULLS = keyword("ignore nulls");
  static final Keyword K_IMMEDIATE = keyword("immediate");
  static final Keyword K_IMMUTABLE = keyword("immutable");
  static final Keyword K_IN = keyword("in");
  static final Keyword K_INCLUDE = keyword("include");
  static final Keyword K_INCLUDE_NULL_VALUES = keyword("include_null_values");
  static final Keyword K_INCREMENT_BY = keyword("increment by");
  static final Keyword K_INDEX = keyword("index");
  static final Keyword K_INLINE = keyword("inline");
  static final Keyword K_INNER_JOIN = keyword("inner join");
  static final Keyword K_INOUT = keyword("inout");
  static final Keyword K_INPUT = keyword("input");
  static final Keyword K_INSERT = keyword("insert");
  static final Keyword K_INSTEAD = keyword("instead");
  static final Keyword K_INT = keyword("int");
  static final Keyword K_INTERVAL = keyword("interval");
  static final Keyword K_INTO = keyword("into");
  static final Keyword K_IS = keyword("is");
  static final Keyword K_IS_DOCUMENT = keyword("is document");
  static final Keyword K_IS_JSON = keyword("is json");
  static final Keyword K_IS_NOT_DOCUMENT = keyword("is not document");
  static final Keyword K_IS_NOT_JSON = keyword("is not json");
  static final Keyword K_IS_NOT_NULL = keyword("is not null");
  static final Keyword K_IS_NULL = keyword("is null");
  static final Keyword K_ITERATE = keyword("iterate");
  static final Keyword K_JSON = keyword("json");
  static final Keyword K_JSON_ARRAY = keyword("json_array");
  static final Keyword K_JSON_EXISTS = keyword("json_exists");
  static final Keyword K_JSON_OBJECT = keyword("json_object");
  static final Keyword K_JSON_TABLE = keyword("json_table");
  static final Keyword K_KEEP = keyword("keep");
  static final Keyword K_KEY = keyword("key");
  static final Keyword K_KEYS = keyword("keys");
  static final Keyword K_LANGUAGE = keyword("language");
  static final Keyword K_LAST = keyword("last");
  static final Keyword K_LATERAL = keyword("lateral");
  static final Keyword K_LEADING = keyword("leading");
  static final Keyword K_LEAVE = keyword("leave");
  static final Keyword K_LEFT_JOIN_LATERAL = keyword("left join lateral");
  static final Keyword K_LEFT_OUTER_JOIN_LATERAL = keyword("left outer join lateral");
  static final Keyword K_LET = keyword("let");
  static final Keyword K_LIKE = keyword("like");
  static final Keyword K_LIKE_REGEX = keyword("like_regex");
  static final Keyword K_LIMIT = keyword("limit");
  static final Keyword K_LOCAL = keyword("local");
  static final Keyword K_LOCK_IN_SHARE_MODE = keyword("lock in share mode");
  static final Keyword K_LOOP = keyword("loop");
  static final Keyword K_MATCHED = keyword("matched");
  static final Keyword K_MATERIALIZE = keyword("materialize");
  static final Keyword K_MATERIALIZED = keyword("materialized");
  static final Keyword K_MAXVALUE = keyword("maxvalue");
  static final Keyword K_MERGE_INTO = keyword("merge into");
  static final Keyword K_MESSAGE = keyword("message");
  static final Keyword K_MESSAGE_TEXT = keyword("message_text");
  static final Keyword K_MILLISECOND = keyword("millisecond");
  static final Keyword K_MINUS = keyword("minus");
  static final Keyword K_MINUTE = keyword("minute");
  static final Keyword K_MINVALUE = keyword("minvalue");
  static final Keyword K_MOD = keyword("mod");
  static final Keyword K_MODIFIES = keyword("modifies");
  static final Keyword K_MODIFY = keyword("modify");
  static final Keyword K_MONTH = keyword("month");
  static final Keyword K_MULTISET = keyword("multiset");
  static final Keyword K_NAME = keyword("name");
  static final Keyword K_NEW = keyword("new");
  static final Keyword K_NEXTVAL = keyword("nextval");
  static final Keyword K_NEXT_VALUE_FOR = keyword("next value for");
  static final Keyword K_NO = keyword("no");
  static final Keyword K_NOCYCLE = keyword("nocycle");
  static final Keyword K_NONCLUSTERED = keyword("nonclustered");
  static final Keyword K_NOT = keyword("not");
  static final Keyword K_NOT_ENFORCED = keyword("not enforced");
  static final Keyword K_NOT_EXISTS = keyword("not exists");
  static final Keyword K_NOT_IN = keyword("not in");
  static final Keyword K_NOT_NULL = keyword("not null");
  static final Keyword K_NOWAIT = keyword("nowait");
  static final Keyword K_NULL = keyword("null");
  static final Keyword K_NULLS_FIRST = keyword("nulls first");
  static final Keyword K_NULLS_LAST = keyword("nulls last");
  static final Keyword K_NUMERIC = keyword("numeric");
  static final Keyword K_NVARCHAR = keyword("nvarchar");
  static final Keyword K_OCCURRENCE = keyword("occurrence");
  static final Keyword K_OF = keyword("of");
  static final Keyword K_OFFSET = keyword("offset");
  static final Keyword K_OLD = keyword("old");
  static final Keyword K_ON = keyword("on");
  static final Keyword K_ON_COMMIT_DELETE_ROWS = keyword("on commit delete rows");
  static final Keyword K_ON_COMMIT_DROP = keyword("on commit drop");
  static final Keyword K_ON_COMMIT_PRESERVE_ROWS = keyword("on commit preserve rows");
  static final Keyword K_ON_CONFLICT = keyword("on conflict");
  static final Keyword K_ON_CONSTRAINT = keyword("on constraint");
  static final Keyword K_ON_DELETE = keyword("on delete");
  static final Keyword K_ON_DUPLICATE_KEY_UPDATE = keyword("on duplicate key update");
  static final Keyword K_ON_UPDATE = keyword("on update");
  static final Keyword K_OPEN = keyword("open");
  static final Keyword K_OPTIONS = keyword("options");
  static final Keyword K_OR = keyword("or");
  static final Keyword K_ORDER = keyword("order");
  static final Keyword K_ORDER_BY = keyword("order by");
  static final Keyword K_ORDINAL = keyword("ordinal");
  static final Keyword K_ORDINALITY = keyword("ordinality");
  static final Keyword K_OUT = keyword("out");
  static final Keyword K_OUTPUT = keyword("output");
  static final Keyword K_OVER = keyword("over");
  static final Keyword K_OVERLAPS = keyword("overlaps");
  static final Keyword K_PARTITION_BY = keyword("partition by");
  static final Keyword K_PASSING = keyword("passing");
  static final Keyword K_PATH = keyword("path");
  static final Keyword K_PERCENT = keyword("percent");
  static final Keyword K_PERIOD = keyword("period");
  static final Keyword K_PIVOT = keyword("pivot");
  static final Keyword K_PLACING = keyword("placing");
  static final Keyword K_POSITION = keyword("position");
  static final Keyword K_PRECEDING = keyword("preceding");
  static final Keyword K_PRESERVE = keyword("preserve");
  static final Keyword K_PREVIOUS_VALUE_FOR = keyword("previous value for");
  static final Keyword K_PRIMARY_KEY = keyword("primary key");
  static final Keyword K_PRIOR = keyword("prior");
  static final Keyword K_PROCEDURE = keyword("procedure");
  static final Keyword K_PUBLIC = keyword("public");
  static final Keyword K_QUALIFY = keyword("qualify");
  static final Keyword K_RAISE = keyword("raise");
  static final Keyword K_RAISERROR = keyword("raiserror");
  static final Keyword K_RAW = keyword("raw");
  static final Keyword K_READPAST = keyword("readpast");
  static final Keyword K_READS = keyword("reads");
  static final Keyword K_RECORD = keyword("record");
  static final Keyword K_RECURSIVE = keyword("recursive");
  static final Keyword K_REF = keyword("ref");
  static final Keyword K_REFERENCES = keyword("references");
  static final Keyword K_REFERENCING = keyword("referencing");
  static final Keyword K_REGEXP = keyword("regexp");
  static final Keyword K_RENAME = keyword("rename");
  static final Keyword K_RENAME_COLUMN = keyword("rename column");
  static final Keyword K_RENAME_CONSTRAINT = keyword("rename constraint");
  static final Keyword K_RENAME_INDEX = keyword("rename index");
  static final Keyword K_RENAME_OBJECT = keyword("rename object");
  static final Keyword K_RENAME_SEQUENCE = keyword("rename sequence");
  static final Keyword K_RENAME_TABLE = keyword("rename table");
  static final Keyword K_RENAME_TO = keyword("rename to");
  static final Keyword K_REPEAT = keyword("repeat");
  static final Keyword K_REPLACE = keyword("replace");
  static final Keyword K_RESPECT_NULLS = keyword("respect nulls");
  static final Keyword K_RESTART = keyword("restart");
  static final Keyword K_RESTART_IDENTITY = keyword("restart identity");
  static final Keyword K_RESTART_WITH = keyword("restart with");
  static final Keyword K_RESTRICT = keyword("restrict");
  static final Keyword K_RETURN = keyword("return");
  static final Keyword K_RETURNING = keyword("returning");
  static final Keyword K_RETURNS = keyword("returns");
  static final Keyword K_REVERSE = keyword("reverse");
  static final Keyword K_REVOKE = keyword("revoke");
  static final Keyword K_ROOT = keyword("root");
  static final Keyword K_ROW = keyword("row");
  static final Keyword K_ROWCOUNT = keyword("rowcount");
  static final Keyword K_ROWLOCK = keyword("rowlock");
  static final Keyword K_ROWS = keyword("rows");
  static final Keyword K_ROWS_FROM = keyword("rows from");
  static final Keyword K_ROWS_ONLY = keyword("rows only");
  static final Keyword K_ROWS_WITH_TIES = keyword("rows with ties");
  static final Keyword K_SAFE_ORDINAL = keyword("safe_ordinal");
  static final Keyword K_SCHEMA = keyword("schema");
  static final Keyword K_SCN = keyword("scn");
  static final Keyword K_SEARCH_PATH = keyword("search_path");
  static final Keyword K_SECOND = keyword("second");
  static final Keyword K_SELECT = keyword("select");
  static final Keyword K_SEPARATOR = keyword("separator");
  static final Keyword K_SEQUENCE = keyword("sequence");
  static final Keyword K_SERIAL = keyword("serial");
  static final Keyword K_SERIAL4 = keyword("serial4");
  static final Keyword K_SERIAL8 = keyword("serial8");
  static final Keyword K_SESSION = keyword("session");
  static final Keyword K_SET = keyword("set");
  static final Keyword K_SET_DATA_TYPE = keyword("set data type");
  static final Keyword K_SET_DEFAULT = keyword("set default");
  static final Keyword K_SET_NOT_NULL = keyword("set not null");
  static final Keyword K_SIBLINGS = keyword("siblings");
  static final Keyword K_SIGNAL = keyword("signal");
  static final Keyword K_SKIP = keyword("skip");
  static final Keyword K_SQL = keyword("sql");
  static final Keyword K_SQLCODE = keyword("sqlcode");
  static final Keyword K_SQLSTATE = keyword("sqlstate");
  static final Keyword K_SQL_ERROR_CODE = keyword("sql_error_code");
  static final Keyword K_START_AT = keyword("start at");
  static final Keyword K_START_WITH = keyword("start with");
  static final Keyword K_STATEMENT = keyword("statement");
  static final Keyword K_STEP = keyword("step");
  static final Keyword K_STORING = keyword("storing");
  static final Keyword K_STRUCT = keyword("struct");
  static final Keyword K_SWITCH = keyword("switch");
  static final Keyword K_SYMMETRIC = keyword("symmetric");
  static final Keyword K_TABLE = keyword("table");
  static final Keyword K_TEMPORARY = keyword("temporary");
  static final Keyword K_THEN = keyword("then");
  static final Keyword K_THROW = keyword("throw");
  static final Keyword K_TIME = keyword("time");
  static final Keyword K_TIMESTAMP = keyword("timestamp");
  static final Keyword K_TIMESTAMP_WITH_TIME_ZONE = keyword("timestamp with time zone");
  static final Keyword K_TIME_WITH_TIME_ZONE = keyword("time with time zone");
  static final Keyword K_TO = keyword("to");
  static final Keyword K_TOP = keyword("top");
  static final Keyword K_TRAILING = keyword("trailing");
  static final Keyword K_TRIGGER = keyword("trigger");
  static final Keyword K_TRIM = keyword("trim");
  static final Keyword K_TRUE = keyword("true");
  static final Keyword K_TRUNCATE = keyword("truncate");
  static final Keyword K_TYPE = keyword("type");
  static final Keyword K_UNBOUNDED_FOLLOWING = keyword("unbounded following");
  static final Keyword K_UNBOUNDED_PRECEDING = keyword("unbounded preceding");
  static final Keyword K_UNIQUE = keyword("unique");
  static final Keyword K_UNNEST = keyword("unnest");
  static final Keyword K_UNTIL = keyword("until");
  static final Keyword K_UPDATE = keyword("update");
  static final Keyword K_UPDLOCK = keyword("updlock");
  static final Keyword K_UPSERT = keyword("upsert");
  static final Keyword K_USE = keyword("use");
  static final Keyword K_USING = keyword("using");
  static final Keyword K_USING_INDEX = keyword("using index");
  static final Keyword K_VALUE = keyword("value");
  static final Keyword K_VALUES = keyword("values");
  static final Keyword K_VARCHAR = keyword("varchar");
  static final Keyword K_VERSIONS = keyword("versions");
  static final Keyword K_VIEW = keyword("view");
  static final Keyword K_VOLATILE = keyword("volatile");
  static final Keyword K_WHEN = keyword("when");
  static final Keyword K_WHERE = keyword("where");
  static final Keyword K_WHILE = keyword("while");
  static final Keyword K_WHITESPACE = keyword("whitespace");
  static final Keyword K_WINDOW = keyword("window");
  static final Keyword K_WITH = keyword("with");
  static final Keyword K_WITHIN_GROUP = keyword("within group");
  static final Keyword K_WITHOUT_ARRAY_WRAPPER = keyword("without_array_wrapper");
  static final Keyword K_WITH_CHECK_OPTION = keyword("with check option");
  static final Keyword K_WITH_DATA = keyword("with data");
  static final Keyword K_WITH_GRANT_OPTION = keyword("with grant option");
  static final Keyword K_WITH_LOCK = keyword("with lock");
  static final Keyword K_WITH_NO_DATA = keyword("with no data");
  static final Keyword K_WITH_NO_DATACOPY = keyword("with no datacopy");
  static final Keyword K_WITH_PRIMARY_KEY = keyword("with primary key");
  static final Keyword K_WITH_READ_ONLY = keyword("with read only");
  static final Keyword K_WITH_ROLLUP = keyword("with rollup");
  static final Keyword K_WITH_TIES = keyword("with ties");
  static final Keyword K_XML = keyword("xml");
  static final Keyword K_XMLEXISTS = keyword("xmlexists");
  static final Keyword K_XMLTABLE = keyword("xmltable");
  static final Keyword K_XSINIL = keyword("xsinil");
  static final Keyword K_YEAR = keyword("year");
  static final Keyword K_YEAR_MONTH = keyword("year_month");
  static final Keyword K_YEAR_TO_DAY = keyword("year to day");
  static final Keyword K_YEAR_TO_FRACTION = keyword("year to fraction");
  static final Keyword K_YEAR_TO_MONTH = keyword("year to month");

  private Keywords() {}
}
