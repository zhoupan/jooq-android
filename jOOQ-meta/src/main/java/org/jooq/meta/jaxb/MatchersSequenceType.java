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

/** Declarative naming strategy configuration for sequence names. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "MatchersSequenceType",
    propOrder = {})
@SuppressWarnings({"all"})
public class MatchersSequenceType implements Serializable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlJavaTypeAdapter(StringAdapter.class)
  protected String expression;

  protected MatcherRule sequenceIdentifier;

  /**
   * This sequence matcher applies to all unqualified or qualified sequence names matched by this
   * expression. If left empty, this matcher applies to all sequences.
   */
  public String getExpression() {
    return expression;
  }

  /**
   * This sequence matcher applies to all unqualified or qualified sequence names matched by this
   * expression. If left empty, this matcher applies to all sequences.
   */
  public void setExpression(String value) {
    this.expression = value;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Sequence} identifier. */
  public MatcherRule getSequenceIdentifier() {
    return sequenceIdentifier;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Sequence} identifier. */
  public void setSequenceIdentifier(MatcherRule value) {
    this.sequenceIdentifier = value;
  }

  /**
   * This sequence matcher applies to all unqualified or qualified sequence names matched by this
   * expression. If left empty, this matcher applies to all sequences.
   */
  public MatchersSequenceType withExpression(String value) {
    setExpression(value);
    return this;
  }

  /** This rule influences the naming of the generated {@link org.jooq.Sequence} identifier. */
  public MatchersSequenceType withSequenceIdentifier(MatcherRule value) {
    setSequenceIdentifier(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("expression", expression);
    builder.append("sequenceIdentifier", sequenceIdentifier);
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
    MatchersSequenceType other = ((MatchersSequenceType) that);
    if (expression == null) {
      if (other.expression != null) {
        return false;
      }
    } else {
      if (!expression.equals(other.expression)) {
        return false;
      }
    }
    if (sequenceIdentifier == null) {
      if (other.sequenceIdentifier != null) {
        return false;
      }
    } else {
      if (!sequenceIdentifier.equals(other.sequenceIdentifier)) {
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
    result =
        ((prime * result) + ((sequenceIdentifier == null) ? 0 : sequenceIdentifier.hashCode()));
    return result;
  }
}
