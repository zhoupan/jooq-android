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
package org.jooq.tools.json;

import java.util.List;
import java.util.Map;

/**
 * Container factory for creating containers for JSON object and JSON array.
 *
 * @see JSONParser#parse(java.io.Reader, ContainerFactory)
 * @author FangYidong&lt;fangyidong@yahoo.com.cn&gt;
 */
@SuppressWarnings({"rawtypes"})
public interface ContainerFactory {

  /**
   * @return A Map instance to store JSON object, or null if you want to use
   *     org.json.simple.JSONObject.
   */
  Map createObjectContainer();

  /**
   * @return A List instance to store JSON array, or null if you want to use
   *     org.json.simple.JSONArray.
   */
  List createArrayContainer();
}
