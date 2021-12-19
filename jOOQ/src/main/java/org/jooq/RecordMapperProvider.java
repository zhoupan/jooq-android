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
package org.jooq;

import org.jetbrains.annotations.NotNull;
import org.jooq.impl.DefaultRecordMapper;
import org.jooq.impl.DefaultRecordMapperProvider;

/**
 * A provider for {@link RecordMapper} instances.
 *
 * <p>In order to inject custom {@link Record} to <code>POJO</code> mapping behaviour, users can
 * supply a custom {@link RecordMapperProvider} to their {@link Configuration} instances. This
 * provider will be used in any of these methods (non-exhaustive list):
 *
 * <h3><code>Cursor</code></h3>
 *
 * <ul>
 *   <li>{@link Cursor#fetchInto(Class)}
 *   <li>{@link Cursor#fetchNextInto(Class)}
 * </ul>
 *
 * <h3><code>Record</code></h3>
 *
 * <ul>
 *   <li>{@link Record#into(Class)}
 * </ul>
 *
 * <h3><code>Result</code></h3>
 *
 * <ul>
 *   <li>{@link Result#intoMap(Field, Class)}
 *   <li>{@link Result#intoMap(Field[], Class)}
 *   <li>{@link Result#intoGroups(Field, Class)}
 *   <li>{@link Result#intoGroups(Field[], Class)}
 *   <li>{@link Result#into(Class)}
 * </ul>
 *
 * <h3><code>ResultQuery</code></h3>
 *
 * <ul>
 *   <li>{@link ResultQuery#fetchMap(Field, Class)}
 *   <li>{@link ResultQuery#fetchMap(Field[], Class)}
 *   <li>{@link ResultQuery#fetchGroups(Field, Class)}
 *   <li>{@link ResultQuery#fetchGroups(Field[], Class)}
 *   <li>{@link ResultQuery#fetchInto(Class)}
 *   <li>{@link ResultQuery#fetchOneInto(Class)}
 * </ul>
 *
 * <h3><code>DAO</code></h3>
 *
 * <ul>
 *   <li>Most {@link DAO} methods make use of any of the above methods
 * </ul>
 *
 * <p>While not strictly required, it is advisable to implement a <code>RecordMapperProvider</code>
 * whose behaviour is consistent with the configured {@link RecordUnmapperProvider}.
 *
 * @author Lukas Eder
 * @see RecordMapper
 * @see Configuration
 */
@FunctionalInterface
public interface RecordMapperProvider {

  /**
   * Provide a <code>RecordMapper</code> instance.
   *
   * <p>Implementations are free to choose whether this method returns new instances at every call
   * or whether the same instance is returned repetitively.
   *
   * <p>A <code>RecordMapper</code> instance should be able to map any number of records with the
   * same <code>RecordType</code>. For example, for {@link Record#into(Class)}, <code>provide()
   * </code> and {@link RecordMapper#map(Record)} are called only once. For {@link
   * Result#into(Class)}, <code>provide()</code> is called only once, but {@link
   * RecordMapper#map(Record)} is called several times, once for every <code>Record</code> in the
   * <code>Result</code>.
   *
   * @param recordType The <code>RecordType</code> of records that shall be mapped by the returned
   *     <code>RecordMapper</code>.
   * @param type The user type that was passed into {@link Record#into(Class)} or any other method.
   * @return A <code>RecordMapper</code> instance.
   * @see RecordMapper
   * @see DefaultRecordMapper
   * @see DefaultRecordMapperProvider
   */
  @NotNull
  <R extends Record, E> RecordMapper<R, E> provide(
      RecordType<R> recordType, Class<? extends E> type);
}
