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
package org.jooq.migrations.xml.jaxb;

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

/**
 * Java class for MigrationsType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="MigrationsType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;all&gt;
 *         &lt;element name="commits" type="{http://www.jooq.org/xsd/jooq-migrations-3.15.0.xsd}CommitsType" minOccurs="0"/&gt;
 *       &lt;/all&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
    name = "MigrationsType",
    propOrder = {})
@SuppressWarnings({"all"})
public class MigrationsType implements Serializable, XMLAppendable {

  private static final long serialVersionUID = 31500L;

  @XmlElementWrapper(name = "commits")
  @XmlElement(name = "commit")
  protected List<CommitType> commits;

  public List<CommitType> getCommits() {
    if (commits == null) {
      commits = new ArrayList<CommitType>();
    }
    return commits;
  }

  public void setCommits(List<CommitType> commits) {
    this.commits = commits;
  }

  public MigrationsType withCommits(CommitType... values) {
    if (values != null) {
      for (CommitType value : values) {
        getCommits().add(value);
      }
    }
    return this;
  }

  public MigrationsType withCommits(Collection<CommitType> values) {
    if (values != null) {
      getCommits().addAll(values);
    }
    return this;
  }

  public MigrationsType withCommits(List<CommitType> commits) {
    setCommits(commits);
    return this;
  }

  @Override
  public final void appendTo(XMLBuilder builder) {
    builder.append("commits", "commit", commits);
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
    MigrationsType other = ((MigrationsType) that);
    if (commits == null) {
      if (other.commits != null) {
        return false;
      }
    } else {
      if (!commits.equals(other.commits)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = ((prime * result) + ((commits == null) ? 0 : commits.hashCode()));
    return result;
  }
}
