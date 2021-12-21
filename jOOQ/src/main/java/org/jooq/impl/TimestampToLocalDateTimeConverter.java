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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import org.jooq.Converter;

/**
 * @author Lukas Eder
 * @deprecated - 3.15.0 - [#11505] - Use {@link Converter#ofNullable(Class, Class,
 *     java.util.function.Function, java.util.function.Function)} instead, e.g. <code>
 *     Converter.ofNullable(Timestamp.class, LocalDateTime.class, Timestamp::toLocalDateTime, Timestamp::valueOf)
 *     </code>.
 */
public final class TimestampToLocalDateTimeConverter
    extends AbstractConverter<Timestamp, LocalDateTime> {

  public TimestampToLocalDateTimeConverter() {
    super(Timestamp.class, LocalDateTime.class);
  }

  @Override
  public final LocalDateTime from(Timestamp t) {
    return t == null ? null : t.toLocalDateTime();
  }

  @Override
  public final Timestamp to(LocalDateTime u) {
    return u == null ? null : Timestamp.valueOf(u);
  }
}
