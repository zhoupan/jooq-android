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

import java.math.BigDecimal;
import java.util.*;
import org.jooq.*;
import org.jooq.conf.*;
import org.jooq.tools.*;

/** The <code>REGR SXY</code> statement. */
@SuppressWarnings({"rawtypes", "unused"})
final class RegrSxy extends DefaultAggregateFunction<BigDecimal> {

  RegrSxy(Field<? extends Number> y, Field<? extends Number> x) {
    super(false, N_REGR_SXY, NUMERIC, nullSafeNotNull(y, INTEGER), nullSafeNotNull(x, INTEGER));
  }

  // -------------------------------------------------------------------------
  // XXX: QueryPart API
  // -------------------------------------------------------------------------
  private static final Set<SQLDialect> NO_SUPPORT_NATIVE =
      SQLDialect.supportedUntil(
          CUBRID, DERBY, FIREBIRD, H2, HSQLDB, IGNITE, MARIADB, MYSQL, SQLITE);

  @Override
  public final void accept(Context<?> ctx) {
    if (NO_SUPPORT_NATIVE.contains(ctx.dialect())) acceptEmulation(ctx);
    else super.accept(ctx);
  }

  @Override
  void acceptFunctionName(Context<?> ctx) {
    super.acceptFunctionName(ctx);
  }

  @SuppressWarnings("unchecked")
  private final void acceptEmulation(Context<?> ctx) {
    Field<? extends Number> x = (Field) getArguments().get(0);
    Field<? extends Number> y = (Field) getArguments().get(1);
    // [#11547] The formal emulation is REGR_COUNT(x, y) * COVAR_POP(x, y), but
    // COVAR_POP(x, y) can be expressed as REGR_SXY(x, y) / REGR_COUNT(x, y)
    // ctx.visit(fo(DSL.regrCount(x, y)).times(fo(DSL.covarPop(x, y))));
    ctx.visit(
        fo(DSL.sum(x.times(y)))
            .minus(
                fo(DSL.sum(x(x, y)))
                    .times(fo(DSL.sum(y(x, y))))
                    .div(fon(DSL.count(x.plus(y))).cast(d(ctx)))));
  }
}
