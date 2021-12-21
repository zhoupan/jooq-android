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
package org.jooq.conf;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.jooq.util.jaxb.tools.XMLAppendable;
import org.jooq.util.jaxb.tools.XMLBuilder;

/** A schema that is on the search path. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "InterpreterSearchSchema",
    propOrder = {})
@SuppressWarnings({"all"})
public class InterpreterSearchSchema extends SettingsBase
    implements Serializable, Cloneable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  protected String catalog;

  @XmlElement(required = true)
  protected String schema;

  /** The catalog qualifier of the schema, if applicable. */
  public String getCatalog() {
    return catalog;
  }

  /** The catalog qualifier of the schema, if applicable. */
  public void setCatalog(String value) {
    this.catalog = value;
  }

  /** The schema qualifier whose elements can be found from the search path. */
  public String getSchema() {
    return schema;
  }

  /** The schema qualifier whose elements can be found from the search path. */
  public void setSchema(String value) {
    this.schema = value;
  }

  /** The catalog qualifier of the schema, if applicable. */
  public InterpreterSearchSchema withCatalog(String value) {
    setCatalog(value);
    return this;
  }

  /** The schema qualifier whose elements can be found from the search path. */
  public InterpreterSearchSchema withSchema(String value) {
    setSchema(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("catalog", catalog);
    builder.append("schema", schema);
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
    InterpreterSearchSchema other = ((InterpreterSearchSchema) that);
    if (catalog == null) {
      if (other.catalog != null) {
        return false;
      }
    } else {
      if (!catalog.equals(other.catalog)) {
        return false;
      }
    }
    if (schema == null) {
      if (other.schema != null) {
        return false;
      }
    } else {
      if (!schema.equals(other.schema)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((catalog == null) ? 0 : catalog.hashCode()));
    result = ((prime * result) + ((schema == null) ? 0 : schema.hashCode()));
    return result;
  }
}
