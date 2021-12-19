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
package org.jooq.meta.derby.sys;

import org.jooq.meta.derby.sys.tables.Syschecks;
import org.jooq.meta.derby.sys.tables.Syscolumns;
import org.jooq.meta.derby.sys.tables.Sysconglomerates;
import org.jooq.meta.derby.sys.tables.Sysconstraints;
import org.jooq.meta.derby.sys.tables.Syskeys;
import org.jooq.meta.derby.sys.tables.Sysschemas;
import org.jooq.meta.derby.sys.tables.Syssequences;
import org.jooq.meta.derby.sys.tables.Systables;
import org.jooq.meta.derby.sys.tables.Sysviews;

/** Convenience access to all tables in SYS. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Tables {

  /** The table <code>SYS.SYSCHECKS</code>. */
  public static final Syschecks SYSCHECKS = Syschecks.SYSCHECKS;

  /** The table <code>SYS.SYSCOLUMNS</code>. */
  public static final Syscolumns SYSCOLUMNS = Syscolumns.SYSCOLUMNS;

  /** The table <code>SYS.SYSCONGLOMERATES</code>. */
  public static final Sysconglomerates SYSCONGLOMERATES = Sysconglomerates.SYSCONGLOMERATES;

  /** The table <code>SYS.SYSCONSTRAINTS</code>. */
  public static final Sysconstraints SYSCONSTRAINTS = Sysconstraints.SYSCONSTRAINTS;

  /** The table <code>SYS.SYSKEYS</code>. */
  public static final Syskeys SYSKEYS = Syskeys.SYSKEYS;

  /** The table <code>SYS.SYSSCHEMAS</code>. */
  public static final Sysschemas SYSSCHEMAS = Sysschemas.SYSSCHEMAS;

  /** The table <code>SYS.SYSSEQUENCES</code>. */
  public static final Syssequences SYSSEQUENCES = Syssequences.SYSSEQUENCES;

  /** The table <code>SYS.SYSTABLES</code>. */
  public static final Systables SYSTABLES = Systables.SYSTABLES;

  /** The table <code>SYS.SYSVIEWS</code>. */
  public static final Sysviews SYSVIEWS = Sysviews.SYSVIEWS;
}
