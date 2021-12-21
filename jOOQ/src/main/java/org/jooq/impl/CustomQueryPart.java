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

import static org.jooq.Clause.CUSTOM;

import java.util.function.Consumer;
import org.jooq.Clause;
import org.jooq.Condition;
import org.jooq.Context;
import org.jooq.QueryPart;

/**
 * A base class for custom {@link QueryPart} implementations in client code.
 *
 * <p>Client code may provide proper {@link Condition} implementations extending this useful base
 * class. All necessary parts of the {@link Condition} interface are already implemented. Only this
 * method needs further implementation: {@link #accept(Context)}.
 *
 * <p>Refer to that methods' Javadoc for further details about their expected behaviour.
 *
 * <p>Such custom <code>QueryPart</code> implementations can be useful in any of these scenarios:
 *
 * <ul>
 *   <li>When reusing a custom <code>QueryPart</code> in other custom <code>QueryPart</code>s, e.g.
 *       in {@link CustomCondition}, {@link CustomField}, {@link CustomTable}, etc.
 *   <li>When inlining a custom <code>QueryPart</code> in plain SQL methods, such as {@link
 *       DSL#condition(String, QueryPart...)}, {@link DSL#field(String, QueryPart...)}, {@link
 *       DSL#table(String, QueryPart...)}
 * </ul>
 *
 * @author Lukas Eder
 */
public abstract class CustomQueryPart extends AbstractQueryPart {

  private static final Clause[] CLAUSES = {CUSTOM};

  protected CustomQueryPart() {}

  /** Create a {@link CustomQueryPart} from a lambda expression. */
  public static final CustomQueryPart of(Consumer<? super Context<?>> consumer) {
    return new CustomQueryPart() {

      @Override
      public void accept(Context<?> ctx) {
        consumer.accept(ctx);
      }
    };
  }

  // -------------------------------------------------------------------------
  // Implementation required
  // -------------------------------------------------------------------------
  /** Subclasses must implement this method. <hr/> {@inheritDoc} */
  @Override
  public abstract void accept(Context<?> ctx);

  // -------------------------------------------------------------------------
  // No further overrides allowed
  // -------------------------------------------------------------------------
  @Override
  public final Clause[] clauses(Context<?> ctx) {
    return CLAUSES;
  }

  @Override
  public final boolean declaresFields() {
    return super.declaresFields();
  }

  @Override
  public final boolean declaresTables() {
    return super.declaresTables();
  }
}
