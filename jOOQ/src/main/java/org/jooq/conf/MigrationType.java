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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import org.jooq.util.jaxb.tools.XMLAppendable;
import org.jooq.util.jaxb.tools.XMLBuilder;

/** The migration schema and table configuration. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "MigrationType",
    propOrder = {})
@SuppressWarnings({"all"})
public class MigrationType extends SettingsBase implements Serializable, Cloneable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlElementWrapper(name = "schemata")
  @XmlElement(name = "schema")
  protected List<MigrationSchema> schemata;

  public List<MigrationSchema> getSchemata() {
    if (schemata == null) {
      schemata = new ArrayList<MigrationSchema>();
    }
    return schemata;
  }

  public void setSchemata(List<MigrationSchema> schemata) {
    this.schemata = schemata;
  }

  public MigrationType withSchemata(MigrationSchema... values) {
    if (values != null) {
      for (MigrationSchema value : values) {
        getSchemata().add(value);
      }
    }
    return this;
  }

  public MigrationType withSchemata(Collection<MigrationSchema> values) {
    if (values != null) {
      getSchemata().addAll(values);
    }
    return this;
  }

  public MigrationType withSchemata(List<MigrationSchema> schemata) {
    setSchemata(schemata);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("schemata", "schema", schemata);
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
    MigrationType other = ((MigrationType) that);
    if (schemata == null) {
      if (other.schemata != null) {
        return false;
      }
    } else {
      if (!schemata.equals(other.schemata)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((schemata == null) ? 0 : schemata.hashCode()));
    return result;
  }
}
