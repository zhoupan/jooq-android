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

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.jooq.util.xml.jaxb package.
 *
 * <p>An ObjectFactory allows you to programatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema
 * derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: org.jooq.util.xml.jaxb
   */
  public ObjectFactory() {}

  /** Create an instance of {@link InformationSchema } */
  public InformationSchema createInformationSchema() {
    return new InformationSchema();
  }

  /** Create an instance of {@link Catalog } */
  public Catalog createCatalog() {
    return new Catalog();
  }

  /** Create an instance of {@link CheckConstraint } */
  public CheckConstraint createCheckConstraint() {
    return new CheckConstraint();
  }

  /** Create an instance of {@link Column } */
  public Column createColumn() {
    return new Column();
  }

  /** Create an instance of {@link DomainConstraint } */
  public DomainConstraint createDomainConstraint() {
    return new DomainConstraint();
  }

  /** Create an instance of {@link Domain } */
  public Domain createDomain() {
    return new Domain();
  }

  /** Create an instance of {@link ElementType } */
  public ElementType createElementType() {
    return new ElementType();
  }

  /** Create an instance of {@link IndexColumnUsage } */
  public IndexColumnUsage createIndexColumnUsage() {
    return new IndexColumnUsage();
  }

  /** Create an instance of {@link Index } */
  public Index createIndex() {
    return new Index();
  }

  /** Create an instance of {@link KeyColumnUsage } */
  public KeyColumnUsage createKeyColumnUsage() {
    return new KeyColumnUsage();
  }

  /** Create an instance of {@link Parameter } */
  public Parameter createParameter() {
    return new Parameter();
  }

  /** Create an instance of {@link ReferentialConstraint } */
  public ReferentialConstraint createReferentialConstraint() {
    return new ReferentialConstraint();
  }

  /** Create an instance of {@link Routine } */
  public Routine createRoutine() {
    return new Routine();
  }

  /** Create an instance of {@link Schema } */
  public Schema createSchema() {
    return new Schema();
  }

  /** Create an instance of {@link Sequence } */
  public Sequence createSequence() {
    return new Sequence();
  }

  /** Create an instance of {@link TableConstraint } */
  public TableConstraint createTableConstraint() {
    return new TableConstraint();
  }

  /** Create an instance of {@link Table } */
  public Table createTable() {
    return new Table();
  }

  /** Create an instance of {@link View } */
  public View createView() {
    return new View();
  }
}
