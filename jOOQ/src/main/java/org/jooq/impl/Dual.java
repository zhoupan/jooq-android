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

import static org.jooq.impl.DSL.inline;
import static org.jooq.impl.DSL.select;
import static org.jooq.impl.Keywords.K_AS;
import static org.jooq.impl.Keywords.K_DUAL;
import static org.jooq.impl.Keywords.K_VALUES;
import static org.jooq.impl.Names.N_DUAL;

import org.jooq.Context;
import org.jooq.Field;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableOptions;

/** @author Lukas Eder */
final class Dual extends AbstractTable<Record> {

  private static final Table<Record> FORCED_DUAL =
      select(new Field[] {inline("X").as("DUMMY")}).asTable("DUAL");

  private static final Name DUAL_FIREBIRD = DSL.unquotedName("RDB$DATABASE");
  private static final Name DUAL_CUBRID = DSL.unquotedName("db_root");
  private static final Name DUAL_DERBY = DSL.unquotedName("SYSIBM", "SYSDUMMY1");

  private final boolean force;

  Dual() {
    this(false);
  }

  Dual(boolean force) {
    super(TableOptions.expression(), N_DUAL, (Schema) null);

    this.force = force;
  }

  @Override
  public final Class<? extends Record> getRecordType() {
    return RecordImpl1.class;
  }

  @Override
  public final Table<Record> as(Name alias) {
    if (force) return FORCED_DUAL.as(alias);
    else return new TableAlias<>(this, alias);
  }

  @Override
  public final Table<Record> as(Name alias, Name... fieldAliases) {
    if (force) return FORCED_DUAL.as(alias, fieldAliases);
    else return new TableAlias<>(this, alias, fieldAliases);
  }

  @Override
  public boolean declaresTables() {
    return true;
  }

  @Override
  public final void accept(Context<?> ctx) {
    if (force) {
      ctx.visit(FORCED_DUAL);
    } else {
      switch (ctx.family()) {
        case H2:
        case POSTGRES:
        case SQLITE:
          break;

        case FIREBIRD:
          ctx.visit(DUAL_FIREBIRD);
          break;

        case HSQLDB:
          ctx.sql('(')
              .visit(K_VALUES)
              .sql("(1)) ")
              .visit(K_AS)
              .sql(' ')
              .visit(N_DUAL)
              .sql('(')
              .visit(N_DUAL)
              .sql(')');
          break;

        case CUBRID:
          ctx.visit(DUAL_CUBRID);
          break;

          // These dialects don't have a DUAL table. But emulation is needed
          // for queries like SELECT 1 WHERE 1 = 1

        case DERBY:
          ctx.visit(DUAL_DERBY);
          break;

          // [#11790] Default to rendering a keyword, not a name
        default:
          ctx.visit(K_DUAL);
          break;
      }
    }
  }

  @Override
  final FieldsImpl<Record> fields0() {
    return new FieldsImpl<>();
  }
}
