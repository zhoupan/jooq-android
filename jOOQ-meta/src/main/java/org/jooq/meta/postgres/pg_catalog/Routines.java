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
package org.jooq.meta.postgres.pg_catalog;

import org.jooq.AggregateFunction;
import org.jooq.Configuration;
import org.jooq.Field;
import org.jooq.meta.postgres.pg_catalog.routines.Count1;
import org.jooq.meta.postgres.pg_catalog.routines.Count2;
import org.jooq.meta.postgres.pg_catalog.routines.FormatType;

/** Convenience access to all stored procedures and functions in pg_catalog. */
@SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Routines {

  /**
   * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify
   *     how this type should be handled. Deprecation can be turned off using {@literal
   *     <deprecationOnUnknownTypes/>} in your code generator configuration.
   */
  public static AggregateFunction<Long> count1(Object __1) {
    Count1 f = new Count1();
    f.set__1(__1);
    return f.asAggregateFunction();
  }

  /**
   * @deprecated Unknown data type. Please define an explicit {@link org.jooq.Binding} to specify
   *     how this type should be handled. Deprecation can be turned off using {@literal
   *     <deprecationOnUnknownTypes/>} in your code generator configuration.
   */
  public static AggregateFunction<Long> count1(Field<Object> __1) {
    Count1 f = new Count1();
    f.set__1(__1);
    return f.asAggregateFunction();
  }

  /** Get <code>pg_catalog.count</code> as a field. */
  public static AggregateFunction<Long> count2() {
    Count2 f = new Count2();
    return f.asAggregateFunction();
  }

  /** Call <code>pg_catalog.format_type</code> */
  public static String formatType(Configuration configuration, Long __1, Integer __2) {
    FormatType f = new FormatType();
    f.set__1(__1);
    f.set__2(__2);
    f.execute(configuration);
    return f.getReturnValue();
  }

  /** Get <code>pg_catalog.format_type</code> as a field. */
  public static Field<String> formatType(Long __1, Integer __2) {
    FormatType f = new FormatType();
    f.set__1(__1);
    f.set__2(__2);
    return f.asField();
  }

  /** Get <code>pg_catalog.format_type</code> as a field. */
  public static Field<String> formatType(Field<Long> __1, Field<Integer> __2) {
    FormatType f = new FormatType();
    f.set__1(__1);
    f.set__2(__2);
    return f.asField();
  }
}
