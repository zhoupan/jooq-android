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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.jooq.conf package.
 *
 * <p>An ObjectFactory allows you to programatically construct new instances of the Java
 * representation for XML content. The Java representation of XML content can consist of schema
 * derived interfaces and classes representing the binding of schema type definitions, element
 * declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

  private static final QName _Settings_QNAME =
      new QName("http://www.jooq.org/xsd/jooq-runtime-3.15.0.xsd", "settings");

  /**
   * Create a new ObjectFactory that can be used to create new instances of schema derived classes
   * for package: org.jooq.conf
   */
  public ObjectFactory() {}

  /** Create an instance of {@link Settings } */
  public Settings createSettings() {
    return new Settings();
  }

  /** Create an instance of {@link ParseSearchSchema } */
  public ParseSearchSchema createParseSearchSchema() {
    return new ParseSearchSchema();
  }

  /** Create an instance of {@link InterpreterSearchSchema } */
  public InterpreterSearchSchema createInterpreterSearchSchema() {
    return new InterpreterSearchSchema();
  }

  /** Create an instance of {@link MigrationSchema } */
  public MigrationSchema createMigrationSchema() {
    return new MigrationSchema();
  }

  /** Create an instance of {@link RenderMapping } */
  public RenderMapping createRenderMapping() {
    return new RenderMapping();
  }

  /** Create an instance of {@link MappedCatalog } */
  public MappedCatalog createMappedCatalog() {
    return new MappedCatalog();
  }

  /** Create an instance of {@link MappedSchema } */
  public MappedSchema createMappedSchema() {
    return new MappedSchema();
  }

  /** Create an instance of {@link MappedTable } */
  public MappedTable createMappedTable() {
    return new MappedTable();
  }

  /** Create an instance of {@link RenderFormatting } */
  public RenderFormatting createRenderFormatting() {
    return new RenderFormatting();
  }

  /**
   * Create an instance of {@link JAXBElement }{@code <}{@link Settings }{@code >}
   *
   * @param value Java instance representing xml element's value.
   * @return the new instance of {@link JAXBElement }{@code <}{@link Settings }{@code >}
   */
  @XmlElementDecl(namespace = "http://www.jooq.org/xsd/jooq-runtime-3.15.0.xsd", name = "settings")
  public JAXBElement<Settings> createSettings(Settings value) {
    return new JAXBElement<Settings>(_Settings_QNAME, Settings.class, null, value);
  }
}
