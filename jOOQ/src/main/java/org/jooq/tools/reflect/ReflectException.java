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
package org.jooq.tools.reflect;

import java.lang.reflect.InvocationTargetException;

/**
 * A unchecked wrapper for any of Java's checked reflection exceptions:
 *
 * <p>These exceptions are
 *
 * <ul>
 *   <li>{@link ClassNotFoundException}
 *   <li>{@link IllegalAccessException}
 *   <li>{@link IllegalArgumentException}
 *   <li>{@link InstantiationException}
 *   <li>{@link InvocationTargetException}
 *   <li>{@link NoSuchMethodException}
 *   <li>{@link NoSuchFieldException}
 *   <li>{@link SecurityException}
 * </ul>
 *
 * @author Lukas Eder
 */
public class ReflectException extends RuntimeException {

  /** Generated UID */
  private static final long serialVersionUID = -6213149635297151442L;

  public ReflectException(String message) {
    super(message);
  }

  public ReflectException(String message, Throwable cause) {
    super(message, cause);
  }

  public ReflectException() {
    super();
  }

  public ReflectException(Throwable cause) {
    super(cause);
  }
}
