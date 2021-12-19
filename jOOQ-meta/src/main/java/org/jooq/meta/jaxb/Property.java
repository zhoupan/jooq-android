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
package org.jooq.meta.jaxb;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jooq.util.jaxb.tools.StringAdapter;
import org.jooq.util.jaxb.tools.XMLAppendable;
import org.jooq.util.jaxb.tools.XMLBuilder;

/**
 * Custom properties that are useful for Database implementations like <code>JPADatabase</code>,
 * <code>XMLDatabase</code>, or <code>DDLDatabase</code>, or to pass to JDBC drivers.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "Property",
    propOrder = {})
@SuppressWarnings({"all"})
public class Property implements Serializable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlElement(required = true)
  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String key;

  @XmlElement(required = true)
  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String value;

  public String getKey() {
    return key;
  }

  public void setKey(String value) {
    this.key = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public Property withKey(String value) {
    setKey(value);
    return this;
  }

  public Property withValue(String value) {
    setValue(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("key", key);
    builder.append("value", value);
  }

  @Override
  public String toString() {
    XMLBuilder builder = XMLBuilder.nonFormatting();
    appendTo(builder);
    return builder.toString();
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (that == null) {
      return false;
    }
    if (getClass() != that.getClass()) {
      return false;
    }
    Property other = ((Property) that);
    if (key == null) {
      if (other.key != null) {
        return false;
      }
    } else {
      if (!key.equals(other.key)) {
        return false;
      }
    }
    if (value == null) {
      if (other.value != null) {
        return false;
      }
    } else {
      if (!value.equals(other.value)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((key == null) ? 0 : key.hashCode()));
    result = ((prime * result) + ((value == null) ? 0 : value.hashCode()));
    return result;
  }
}
