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
package org.java.util.stream;

final class SinkDefaults {

  static final class OfInt {

    static void accept(Sink.OfInt this_, Integer i) {
      this_.accept(i.intValue());
    }

    private OfInt() {}
  }

  static final class OfLong {

    static void accept(Sink.OfLong this_, Long i) {
      this_.accept(i.longValue());
    }

    private OfLong() {}
  }

  static final class OfDouble {

    static void accept(Sink.OfDouble this_, Double i) {
      this_.accept(i.doubleValue());
    }

    private OfDouble() {}
  }

  /**
   * Doesn't accept a primitive value of a particular type (either int, long or double).
   *
   * <p><b>Implementation Requirements:</b><br>
   * The implementation must throw an IllegalStateException.
   *
   * @throws IllegalStateException if the sink doesn't accept a primitive value of a particular type
   */
  static <T> void reject() {
    throw new IllegalStateException("called wrong accept method");
  }

  private SinkDefaults() {}
}
