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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.jooq.util.jaxb.tools.StringAdapter;
import org.jooq.util.jaxb.tools.XMLAppendable;
import org.jooq.util.jaxb.tools.XMLBuilder;

/** Declarative naming strategy configuration for enum names. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "MatchersEnumType",
    propOrder = {})
@SuppressWarnings({"all"})
public class MatchersEnumType implements Serializable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String expression;

  protected MatcherRule enumClass;

  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String enumImplements;

  /**
   * This enum matcher applies to all unqualified or qualified enum names matched by this
   * expression. If left empty, this matcher applies to all enums.
   */
  public String getExpression() {
    return expression;
  }

  /**
   * This enum matcher applies to all unqualified or qualified enum names matched by this
   * expression. If left empty, this matcher applies to all enums.
   */
  public void setExpression(String value) {
    this.expression = value;
  }

  /** This rule influences the naming of the generated {@link org.jooq.EnumType} object. */
  public MatcherRule getEnumClass() {
    return enumClass;
  }

  /** This rule influences the naming of the generated {@link org.jooq.EnumType} object. */
  public void setEnumClass(MatcherRule value) {
    this.enumClass = value;
  }

  /**
   * This string provides additional interfaces that a generated {@link org.jooq.EnumType} should
   * implement.
   */
  public String getEnumImplements() {
    return enumImplements;
  }

  /**
   * This string provides additional interfaces that a generated {@link org.jooq.EnumType} should
   * implement.
   */
  public void setEnumImplements(String value) {
    this.enumImplements = value;
  }

  /**
   * This enum matcher applies to all unqualified or qualified enum names matched by this
   * expression. If left empty, this matcher applies to all enums.
   */
  public MatchersEnumType withExpression(String value) {
    setExpression(value);
    return this;
  }

  /** This rule influences the naming of the generated {@link org.jooq.EnumType} object. */
  public MatchersEnumType withEnumClass(MatcherRule value) {
    setEnumClass(value);
    return this;
  }

  /**
   * This string provides additional interfaces that a generated {@link org.jooq.EnumType} should
   * implement.
   */
  public MatchersEnumType withEnumImplements(String value) {
    setEnumImplements(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("expression", expression);
    builder.append("enumClass", enumClass);
    builder.append("enumImplements", enumImplements);
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
    MatchersEnumType other = ((MatchersEnumType) that);
    if (expression == null) {
      if (other.expression != null) {
        return false;
      }
    } else {
      if (!expression.equals(other.expression)) {
        return false;
      }
    }
    if (enumClass == null) {
      if (other.enumClass != null) {
        return false;
      }
    } else {
      if (!enumClass.equals(other.enumClass)) {
        return false;
      }
    }
    if (enumImplements == null) {
      if (other.enumImplements != null) {
        return false;
      }
    } else {
      if (!enumImplements.equals(other.enumImplements)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((expression == null) ? 0 : expression.hashCode()));
    result = ((prime * result) + ((enumClass == null) ? 0 : enumClass.hashCode()));
    result = ((prime * result) + ((enumImplements == null) ? 0 : enumImplements.hashCode()));
    return result;
  }
}
