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
package org.jooq.example.testcontainers.db.routines;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.example.testcontainers.db.Public;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;

/** This class is generated by jOOQ. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class GroupConcat extends AbstractRoutine<String> {

  private static final long serialVersionUID = 1L;

  /** The parameter <code>public.group_concat.RETURN_VALUE</code>. */
  public static final Parameter<String> RETURN_VALUE =
      Internal.createParameter("RETURN_VALUE", SQLDataType.CLOB, false, false);

  /** The parameter <code>public.group_concat._1</code>. */
  public static final Parameter<String> _1 =
      Internal.createParameter("_1", SQLDataType.CLOB, false, true);

  /** Create a new routine call instance */
  public GroupConcat() {
    super("group_concat", Public.PUBLIC, SQLDataType.CLOB);

    setReturnParameter(RETURN_VALUE);
    addInParameter(_1);
  }

  /** Set the <code>_1</code> parameter IN value to the routine */
  public void set__1(String value) {
    setValue(_1, value);
  }

  /**
   * Set the <code>_1</code> parameter to the function to be used with a {@link org.jooq.Select}
   * statement
   */
  public void set__1(Field<String> field) {
    setField(_1, field);
  }
}
