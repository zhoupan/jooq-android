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

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java element interface
 * generated in the org.jooq.meta.jaxb package.
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
   * for package: org.jooq.meta.jaxb
   */
  public ObjectFactory() {}

  /** Create an instance of {@link Configuration } */
  public Configuration createConfiguration() {
    return new Configuration();
  }

  /** Create an instance of {@link Jdbc } */
  public Jdbc createJdbc() {
    return new Jdbc();
  }

  /** Create an instance of {@link Generator } */
  public Generator createGenerator() {
    return new Generator();
  }

  /** Create an instance of {@link Property } */
  public Property createProperty() {
    return new Property();
  }

  /** Create an instance of {@link Strategy } */
  public Strategy createStrategy() {
    return new Strategy();
  }

  /** Create an instance of {@link Matchers } */
  public Matchers createMatchers() {
    return new Matchers();
  }

  /** Create an instance of {@link MatchersCatalogType } */
  public MatchersCatalogType createMatchersCatalogType() {
    return new MatchersCatalogType();
  }

  /** Create an instance of {@link MatchersSchemaType } */
  public MatchersSchemaType createMatchersSchemaType() {
    return new MatchersSchemaType();
  }

  /** Create an instance of {@link MatchersTableType } */
  public MatchersTableType createMatchersTableType() {
    return new MatchersTableType();
  }

  /** Create an instance of {@link MatchersFieldType } */
  public MatchersFieldType createMatchersFieldType() {
    return new MatchersFieldType();
  }

  /** Create an instance of {@link MatchersRoutineType } */
  public MatchersRoutineType createMatchersRoutineType() {
    return new MatchersRoutineType();
  }

  /** Create an instance of {@link MatchersSequenceType } */
  public MatchersSequenceType createMatchersSequenceType() {
    return new MatchersSequenceType();
  }

  /** Create an instance of {@link MatchersEnumType } */
  public MatchersEnumType createMatchersEnumType() {
    return new MatchersEnumType();
  }

  /** Create an instance of {@link MatchersEmbeddableType } */
  public MatchersEmbeddableType createMatchersEmbeddableType() {
    return new MatchersEmbeddableType();
  }

  /** Create an instance of {@link MatcherRule } */
  public MatcherRule createMatcherRule() {
    return new MatcherRule();
  }

  /** Create an instance of {@link Database } */
  public Database createDatabase() {
    return new Database();
  }

  /** Create an instance of {@link CommentType } */
  public CommentType createCommentType() {
    return new CommentType();
  }

  /** Create an instance of {@link SyntheticObjectsType } */
  public SyntheticObjectsType createSyntheticObjectsType() {
    return new SyntheticObjectsType();
  }

  /** Create an instance of {@link SyntheticIdentityType } */
  public SyntheticIdentityType createSyntheticIdentityType() {
    return new SyntheticIdentityType();
  }

  /** Create an instance of {@link SyntheticPrimaryKeyType } */
  public SyntheticPrimaryKeyType createSyntheticPrimaryKeyType() {
    return new SyntheticPrimaryKeyType();
  }

  /** Create an instance of {@link SyntheticUniqueKeyType } */
  public SyntheticUniqueKeyType createSyntheticUniqueKeyType() {
    return new SyntheticUniqueKeyType();
  }

  /** Create an instance of {@link SyntheticForeignKeyType } */
  public SyntheticForeignKeyType createSyntheticForeignKeyType() {
    return new SyntheticForeignKeyType();
  }

  /** Create an instance of {@link SyntheticViewType } */
  public SyntheticViewType createSyntheticViewType() {
    return new SyntheticViewType();
  }

  /** Create an instance of {@link CatalogMappingType } */
  public CatalogMappingType createCatalogMappingType() {
    return new CatalogMappingType();
  }

  /** Create an instance of {@link SchemaMappingType } */
  public SchemaMappingType createSchemaMappingType() {
    return new SchemaMappingType();
  }

  /** Create an instance of {@link CustomType } */
  public CustomType createCustomType() {
    return new CustomType();
  }

  /** Create an instance of {@link EnumType } */
  public EnumType createEnumType() {
    return new EnumType();
  }

  /** Create an instance of {@link EmbeddableDefinitionType } */
  public EmbeddableDefinitionType createEmbeddableDefinitionType() {
    return new EmbeddableDefinitionType();
  }

  /** Create an instance of {@link EmbeddableField } */
  public EmbeddableField createEmbeddableField() {
    return new EmbeddableField();
  }

  /** Create an instance of {@link ForcedType } */
  public ForcedType createForcedType() {
    return new ForcedType();
  }

  /** Create an instance of {@link LambdaConverter } */
  public LambdaConverter createLambdaConverter() {
    return new LambdaConverter();
  }

  /** Create an instance of {@link Generate } */
  public Generate createGenerate() {
    return new Generate();
  }

  /** Create an instance of {@link Target } */
  public Target createTarget() {
    return new Target();
  }
}
