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

import static org.jooq.Clause.ROLE;
import static org.jooq.impl.CommentImpl.NO_COMMENT;

import org.jooq.Clause;
import org.jooq.Context;
import org.jooq.Name;
import org.jooq.Role;

/**
 * A common implementation of the Role type.
 *
 * @author Timur Shaidullin
 */
final class RoleImpl extends AbstractNamed implements Role {

  private static final Clause[] CLAUSES = {ROLE};

  RoleImpl(Name name) {
    super(name, NO_COMMENT);
  }

  // ------------------------------------------------------------------------
  // XXX: QueryPart API
  // ------------------------------------------------------------------------
  @Override
  public final void accept(Context<?> ctx) {
    ctx.visit(getQualifiedName());
  }

  @Override
  public final Clause[] clauses(Context<?> ctx) {
    return CLAUSES;
  }
}
