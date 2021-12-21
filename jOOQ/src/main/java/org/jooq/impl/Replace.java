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

import static org.jooq.SQLDialect.*;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.Internal.*;
import static org.jooq.impl.Keywords.*;
import static org.jooq.impl.Names.*;
import static org.jooq.impl.SQLDataType.*;
import static org.jooq.impl.Tools.*;
import static org.jooq.impl.Tools.BooleanDataKey.*;
import static org.jooq.impl.Tools.DataExtendedKey.*;
import static org.jooq.impl.Tools.DataKey.*;

import java.util.*;
import org.jooq.*;
import org.jooq.conf.*;
import org.jooq.tools.*;

/** The <code>REPLACE</code> statement. */
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
final class Replace extends AbstractField<String> {

  private final Field<String> string;

  private final Field<String> search;

  private final Field<String> replace;

  Replace(Field<String> string, Field<String> search) {
    super(N_REPLACE, allNotNull(VARCHAR, string, search));
    this.string = nullSafeNotNull(string, VARCHAR);
    this.search = nullSafeNotNull(search, VARCHAR);
    this.replace = null;
  }

  Replace(Field<String> string, Field<String> search, Field<String> replace) {
    super(N_REPLACE, allNotNull(VARCHAR, string, search, replace));
    this.string = nullSafeNotNull(string, VARCHAR);
    this.search = nullSafeNotNull(search, VARCHAR);
    this.replace = nullSafeNotNull(replace, VARCHAR);
  }

  // -------------------------------------------------------------------------
  // XXX: QueryPart API
  // -------------------------------------------------------------------------
  @Override
  public final void accept(Context<?> ctx) {
    // [#861] Most dialects don't ship with a two-argument replace function:
    switch (ctx.family()) {
      case FIREBIRD:
      case HSQLDB:
      case MARIADB:
      case MYSQL:
      case POSTGRES:
      case SQLITE:
        if (replace == null) ctx.visit(function(N_REPLACE, VARCHAR, string, search, inline("")));
        else ctx.visit(function(N_REPLACE, VARCHAR, string, search, replace));
        return;
      default:
        if (replace == null) ctx.visit(function(N_REPLACE, VARCHAR, string, search));
        else ctx.visit(function(N_REPLACE, VARCHAR, string, search, replace));
        return;
    }
  }

  // -------------------------------------------------------------------------
  // The Object API
  // -------------------------------------------------------------------------
  @Override
  public boolean equals(Object that) {
    if (that instanceof Replace) {
      return StringUtils.equals(string, ((Replace) that).string)
          && StringUtils.equals(search, ((Replace) that).search)
          && StringUtils.equals(replace, ((Replace) that).replace);
    } else return super.equals(that);
  }
}
