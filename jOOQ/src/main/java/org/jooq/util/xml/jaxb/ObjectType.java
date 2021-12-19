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
package org.jooq.util.xml.jaxb;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

/**
 * Java class for ObjectType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="ObjectType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DOMAIN"/&gt;
 *     &lt;enumeration value="ROUTINE"/&gt;
 *     &lt;enumeration value="TABLE"/&gt;
 *     &lt;enumeration value="USER-DEFINED TYPE"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "ObjectType")
@XmlEnum
public enum ObjectType {
  DOMAIN("DOMAIN"),
  ROUTINE("ROUTINE"),
  TABLE("TABLE"),
  @XmlEnumValue("USER-DEFINED TYPE")
  USER_DEFINED_TYPE("USER-DEFINED TYPE");
  private final String value;

  ObjectType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static ObjectType fromValue(String v) {
    for (ObjectType c : ObjectType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

  @Override
  public String toString() {
    switch (this) {
      case USER_DEFINED_TYPE:
        return "USER-DEFINED TYPE";
      default:
        return this.name();
    }
  }
}
