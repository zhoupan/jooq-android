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

/** Declarative naming strategy configuration for schema names. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "MatchersSchemaType",
    propOrder = {})
@SuppressWarnings({"all"})
public class MatchersSchemaType implements Serializable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String expression;

  protected MatcherRule schemaClass;
  protected MatcherRule schemaIdentifier;

  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String schemaImplements;

  /**
   * This schema matcher applies to all unqualified or qualified schema names matched by this
   * expression. If left empty, this matcher applies to all schemas.
   */
  public String getExpression() {
    return expression;
  }

  /**
   * This schema matcher applies to all unqualified or qualified schema names matched by this
   * expression. If left empty, this matcher applies to all schemas.
   */
  public void setExpression(String value) {
    this.expression = value;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Schema} object. */
  public MatcherRule getSchemaClass() {
    return schemaClass;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Schema} object. */
  public void setSchemaClass(MatcherRule value) {
    this.schemaClass = value;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Schema} identifier. */
  public MatcherRule getSchemaIdentifier() {
    return schemaIdentifier;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Schema} identifier. */
  public void setSchemaIdentifier(MatcherRule value) {
    this.schemaIdentifier = value;
  }

  /**
   * This string provides additional interfaces that a generated {@link org.jooq.Schema} should
   * implement.
   */
  public String getSchemaImplements() {
    return schemaImplements;
  }

  /**
   * This string provides additional interfaces that a generated {@link org.jooq.Schema} should
   * implement.
   */
  public void setSchemaImplements(String value) {
    this.schemaImplements = value;
  }

  /**
   * This schema matcher applies to all unqualified or qualified schema names matched by this
   * expression. If left empty, this matcher applies to all schemas.
   */
  public MatchersSchemaType withExpression(String value) {
    setExpression(value);
    return this;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Schema} object. */
  public MatchersSchemaType withSchemaClass(MatcherRule value) {
    setSchemaClass(value);
    return this;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Schema} identifier. */
  public MatchersSchemaType withSchemaIdentifier(MatcherRule value) {
    setSchemaIdentifier(value);
    return this;
  }

  /**
   * This string provides additional interfaces that a generated {@link org.jooq.Schema} should
   * implement.
   */
  public MatchersSchemaType withSchemaImplements(String value) {
    setSchemaImplements(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("expression", expression);
    builder.append("schemaClass", schemaClass);
    builder.append("schemaIdentifier", schemaIdentifier);
    builder.append("schemaImplements", schemaImplements);
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
    MatchersSchemaType other = ((MatchersSchemaType) that);
    if (expression == null) {
      if (other.expression != null) {
        return false;
      }
    } else {
      if (!expression.equals(other.expression)) {
        return false;
      }
    }
    if (schemaClass == null) {
      if (other.schemaClass != null) {
        return false;
      }
    } else {
      if (!schemaClass.equals(other.schemaClass)) {
        return false;
      }
    }
    if (schemaIdentifier == null) {
      if (other.schemaIdentifier != null) {
        return false;
      }
    } else {
      if (!schemaIdentifier.equals(other.schemaIdentifier)) {
        return false;
      }
    }
    if (schemaImplements == null) {
      if (other.schemaImplements != null) {
        return false;
      }
    } else {
      if (!schemaImplements.equals(other.schemaImplements)) {
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
    result = ((prime * result) + ((schemaClass == null) ? 0 : schemaClass.hashCode()));
    result = ((prime * result) + ((schemaIdentifier == null) ? 0 : schemaIdentifier.hashCode()));
    result = ((prime * result) + ((schemaImplements == null) ? 0 : schemaImplements.hashCode()));
    return result;
  }
}
