/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.examples.dto.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import io.sundr.model.AnnotationRef;
import io.sundr.model.ClassRef;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.repo.DefinitionRepository;

/**
 * A DTO generator that creates Data Transfer Objects by:
 * - Adding "Dto" suffix to the class name
 * - Removing JPA/validation annotations from properties
 * - Replacing JPA entity properties with their ID field type and adding "Id" suffix to property name
 */
public class DtoGenerator implements Function<TypeDef, TypeDef> {

  @Override
  public TypeDef apply(TypeDef originalType) {
    if (originalType == null) {
      return null;
    }

    List<Property> dtoProperties = new ArrayList<>();

    for (Property property : originalType.getProperties()) {
      Property transformedProperty = transformProperty(property);
      if (transformedProperty != null) {
        dtoProperties.add(transformedProperty);
      }
    }

    return new TypeDefBuilder(originalType)
        .withName(originalType.getName() + "Dto")
        .withAnnotations() // Remove class-level annotations
        .withProperties(dtoProperties)
        .withConstructors()
        .withMethods()
        .build();
  }

  private Property transformProperty(Property property) {
    TypeRef typeRef = property.getTypeRef();

    // Skip static fields
    if (property.getModifiers().isStatic()) {
      return null;
    }

    // Check if this property references a JPA entity
    if (typeRef instanceof ClassRef) {
      ClassRef classRef = (ClassRef) typeRef;
      TypeDef referencedType = DefinitionRepository.getRepository().getDefinition(classRef);

      if (isJpaEntity(referencedType)) {
        // Find the @Id field in the referenced entity
        TypeRef idType = findIdFieldType(referencedType);
        if (idType != null) {
          // Replace entity property with ID property
          return new PropertyBuilder(property)
              .withNewModifiers()
              .withPublic()
              .endModifiers()
              .withTypeRef(idType)
              .withName(property.getName() + "Id")
              .withAnnotations() // Remove all annotations
              .build();
        }
      }
    }

    // For non-entity properties, just remove annotations
    return new PropertyBuilder(property)
        .withNewModifiers()
        .withPublic()
        .endModifiers()
        .withAnnotations() // Remove all annotations
        .build();
  }

  private boolean isJpaEntity(TypeDef typeDef) {
    if (typeDef == null) {
      return false;
    }

    // Check if the class has @Entity annotation
    for (AnnotationRef annotation : typeDef.getAnnotations()) {
      if (annotation.getClassRef().getFullyQualifiedName().equals("jakarta.persistence.Entity")) {
        return true;
      }
    }

    // Also check if any property has @Id annotation (common in JPA entities)
    for (Property property : typeDef.getProperties()) {
      for (AnnotationRef annotation : property.getAnnotations()) {
        if (annotation.getClassRef().getFullyQualifiedName().equals("jakarta.persistence.Id")) {
          return true;
        }
      }
    }

    return false;
  }

  private TypeRef findIdFieldType(TypeDef typeDef) {
    if (typeDef == null) {
      return null;
    }

    // Look for property with @Id annotation
    for (Property property : typeDef.getProperties()) {
      for (AnnotationRef annotation : property.getAnnotations()) {
        if (annotation.getClassRef().getFullyQualifiedName().equals("jakarta.persistence.Id")) {
          return property.getTypeRef();
        }
      }
    }

    // Default to Long if no @Id field found
    return ClassRef.forName("java.lang.Long");
  }
}
