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
package io.r2dbc.spi;

import java.util.ServiceLoader;

/**
 * A Java Service interface for implementations to examine a collection of {@link
 * ConnectionFactoryOptions} and optionally return an implementation of {@link ConnectionFactory}.
 *
 * <p>{@link ConnectionFactoryProvider} implementations are typically discovered by {@link
 * ConnectionFactories} from the class path using {@link ServiceLoader}.
 *
 * @see ConnectionFactoryOptions
 * @see ConnectionFactory
 * @see ConnectionFactories
 * @see ServiceLoader
 */
public interface ConnectionFactoryProvider {

  /**
   * Creates a new {@link ConnectionFactory} given a collection of {@link ConnectionFactoryOptions}.
   * This method is only called if a previous invocation of {@link
   * #supports(ConnectionFactoryOptions)} has returned {@code true}.
   *
   * @param connectionFactoryOptions a collection of {@link ConnectionFactoryOptions}
   * @return the {@link ConnectionFactory} created from this collection of {@link
   *     ConnectionFactoryOptions}
   * @throws IllegalArgumentException if {@code connectionFactoryOptions} is {@code null}
   */
  ConnectionFactory create(ConnectionFactoryOptions connectionFactoryOptions);

  /**
   * Whether this {@link ConnectionFactoryProvider} supports this collection of {@link
   * ConnectionFactoryOptions}.
   *
   * @param connectionFactoryOptions a collection of {@link ConnectionFactoryOptions}
   * @return {@code true} if this {@link ConnectionFactoryProvider} supports this collection of
   *     {@link ConnectionFactoryOptions}, {@code false} otherwise
   * @throws IllegalArgumentException if {@code connectionFactoryOptions} is {@code null}
   */
  boolean supports(ConnectionFactoryOptions connectionFactoryOptions);

  /**
   * Returns the driver identifier used by the driver. The identifier for drivers would be the value
   * applicable to {@link ConnectionFactoryOptions#DRIVER}
   *
   * @return the driver identifier used by the driver
   */
  String getDriver();
}
