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
package org.jooq.meta.mysql.mysql;

import org.jooq.Record;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.meta.mysql.mysql.tables.Proc;
import org.jooq.meta.mysql.mysql.tables.ProcsPriv;

/** A class modelling foreign key relationships and constraints of tables in mysql. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Keys {

  // -------------------------------------------------------------------------
  // UNIQUE and PRIMARY KEY definitions
  // -------------------------------------------------------------------------
  public static final UniqueKey<Record> KEY_PROC_PRIMARY = UniqueKeys0.KEY_PROC_PRIMARY;

  public static final UniqueKey<Record> KEY_PROCS_PRIV_PRIMARY = UniqueKeys0.KEY_PROCS_PRIV_PRIMARY;

  // -------------------------------------------------------------------------
  // FOREIGN KEY definitions
  // -------------------------------------------------------------------------
  // -------------------------------------------------------------------------
  // [#1459] distribute members to avoid static initialisers > 64kb
  // -------------------------------------------------------------------------
  private static class UniqueKeys0 {

    static final UniqueKey<Record> KEY_PROC_PRIMARY =
        Internal.createUniqueKey(
            Proc.PROC,
            DSL.name("KEY_proc_PRIMARY"),
            new TableField[] {Proc.PROC.DB, Proc.PROC.NAME, Proc.PROC.TYPE},
            true);

    static final UniqueKey<Record> KEY_PROCS_PRIV_PRIMARY =
        Internal.createUniqueKey(
            ProcsPriv.PROCS_PRIV,
            DSL.name("KEY_procs_priv_PRIMARY"),
            new TableField[] {
              ProcsPriv.PROCS_PRIV.HOST,
              ProcsPriv.PROCS_PRIV.DB,
              ProcsPriv.PROCS_PRIV.USER,
              ProcsPriv.PROCS_PRIV.ROUTINE_NAME,
              ProcsPriv.PROCS_PRIV.ROUTINE_TYPE
            },
            true);
  }
}
