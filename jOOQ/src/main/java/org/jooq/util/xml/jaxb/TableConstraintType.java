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
 * Java class for TableConstraintType.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;simpleType name="TableConstraintType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="PRIMARY KEY"/&gt;
 *     &lt;enumeration value="UNIQUE"/&gt;
 *     &lt;enumeration value="CHECK"/&gt;
 *     &lt;enumeration value="FOREIGN KEY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 */
@XmlType(name = "TableConstraintType")
@XmlEnum
public enum TableConstraintType {
  @XmlEnumValue("PRIMARY KEY")
  PRIMARY_KEY("PRIMARY KEY"),
  UNIQUE("UNIQUE"),
  CHECK("CHECK"),
  @XmlEnumValue("FOREIGN KEY")
  FOREIGN_KEY("FOREIGN KEY");
  private final String value;

  TableConstraintType(String v) {
    value = v;
  }

  public String value() {
    return value;
  }

  public static TableConstraintType fromValue(String v) {
    for (TableConstraintType c : TableConstraintType.values()) {
      if (c.value.equals(v)) {
        return c;
      }
    }
    throw new IllegalArgumentException(v);
  }

  @Override
  public String toString() {
    switch (this) {
      case PRIMARY_KEY:
        return "PRIMARY KEY";
      case FOREIGN_KEY:
        return "FOREIGN KEY";
      default:
        return this.name();
    }
  }
}
