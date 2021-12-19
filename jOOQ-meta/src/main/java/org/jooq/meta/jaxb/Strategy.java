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
 * Definitions of custom naming strategies (declarative or programmatic) to define how generated
 * Java objects should be named.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "Strategy",
    propOrder = {"name", "matchers"})
@SuppressWarnings({"all"})
public class Strategy implements Serializable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlElement(defaultValue = "org.jooq.codegen.DefaultGeneratorStrategy")
  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String name = "org.jooq.codegen.DefaultGeneratorStrategy";

  protected Matchers matchers;

  /**
   * The class used to provide a naming strategy for generated source code. You may override this
   * with your custom naming strategy. This cannot be combined with a matcher configuration.
   */
  public String getName() {
    return name;
  }

  /**
   * The class used to provide a naming strategy for generated source code. You may override this
   * with your custom naming strategy. This cannot be combined with a matcher configuration.
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * The matcher strategy configuration used when applying an XML-based strategy. This cannot be
   * combined with a named strategy configuration.
   */
  public Matchers getMatchers() {
    return matchers;
  }

  /**
   * The matcher strategy configuration used when applying an XML-based strategy. This cannot be
   * combined with a named strategy configuration.
   */
  public void setMatchers(Matchers value) {
    this.matchers = value;
  }

  /**
   * The class used to provide a naming strategy for generated source code. You may override this
   * with your custom naming strategy. This cannot be combined with a matcher configuration.
   */
  public Strategy withName(String value) {
    setName(value);
    return this;
  }

  /**
   * The matcher strategy configuration used when applying an XML-based strategy. This cannot be
   * combined with a named strategy configuration.
   */
  public Strategy withMatchers(Matchers value) {
    setMatchers(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("name", name);
    builder.append("matchers", matchers);
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
    Strategy other = ((Strategy) that);
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else {
      if (!name.equals(other.name)) {
        return false;
      }
    }
    if (matchers == null) {
      if (other.matchers != null) {
        return false;
      }
    } else {
      if (!matchers.equals(other.matchers)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((name == null) ? 0 : name.hashCode()));
    result = ((prime * result) + ((matchers == null) ? 0 : matchers.hashCode()));
    return result;
  }
}
