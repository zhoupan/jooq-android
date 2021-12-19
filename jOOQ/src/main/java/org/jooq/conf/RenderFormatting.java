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

/** All sorts of formatting flags / settings. */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "RenderFormatting",
    propOrder = {})
@SuppressWarnings({"all"})
public class RenderFormatting extends SettingsBase
    implements Serializable, Cloneable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlElement(defaultValue = "\n")
  protected String newline = "\n";

  @XmlElement(defaultValue = "  ")
  protected String indentation = "  ";

  @XmlElement(defaultValue = "80")
  protected Integer printMargin = 80;

  /** The character to be used for line breaks. */
  public String getNewline() {
    return newline;
  }

  /** The character to be used for line breaks. */
  public void setNewline(String value) {
    this.newline = value;
  }

  /** The characters to be used for indentation. */
  public String getIndentation() {
    return indentation;
  }

  /** The characters to be used for indentation. */
  public void setIndentation(String value) {
    this.indentation = value;
  }

  /** The print margin after which (some) formatted elements will break lines. */
  public Integer getPrintMargin() {
    return printMargin;
  }

  /** The print margin after which (some) formatted elements will break lines. */
  public void setPrintMargin(Integer value) {
    this.printMargin = value;
  }

  /** The character to be used for line breaks. */
  public RenderFormatting withNewline(String value) {
    setNewline(value);
    return this;
  }

  /** The characters to be used for indentation. */
  public RenderFormatting withIndentation(String value) {
    setIndentation(value);
    return this;
  }

  /** The print margin after which (some) formatted elements will break lines. */
  public RenderFormatting withPrintMargin(Integer value) {
    setPrintMargin(value);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("newline", newline);
    builder.append("indentation", indentation);
    builder.append("printMargin", printMargin);
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
    RenderFormatting other = ((RenderFormatting) that);
    if (newline == null) {
      if (other.newline != null) {
        return false;
      }
    } else {
      if (!newline.equals(other.newline)) {
        return false;
      }
    }
    if (indentation == null) {
      if (other.indentation != null) {
        return false;
      }
    } else {
      if (!indentation.equals(other.indentation)) {
        return false;
      }
    }
    if (printMargin == null) {
      if (other.printMargin != null) {
        return false;
      }
    } else {
      if (!printMargin.equals(other.printMargin)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((newline == null) ? 0 : newline.hashCode()));
    result = ((prime * result) + ((indentation == null) ? 0 : indentation.hashCode()));
    result = ((prime * result) + ((printMargin == null) ? 0 : printMargin.hashCode()));
    return result;
  }
}
