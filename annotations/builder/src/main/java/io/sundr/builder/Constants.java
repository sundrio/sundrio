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

package io.sundr.builder;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.model.AnnotationRef;
import io.sundr.model.AnnotationRefBuilder;
import io.sundr.model.AttributeKey;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Kind;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamRef;
import io.sundr.model.utils.Collections;
import io.sundr.model.utils.Types;

public class Constants {

  private Constants() {
  }

  public static final String DEFAULT_BUILDER_PACKAGE = "io.sundr.builder";

  public static final AttributeKey<TypeDef> ORIGIN_TYPEDEF = new AttributeKey<TypeDef>("ORIGIN_TYPEDEF", TypeDef.class);
  public static final AttributeKey<TypeDef> OUTER_INTERFACE = new AttributeKey<TypeDef>("OUTER_INTERFACE", TypeDef.class);
  public static final AttributeKey<TypeDef> OUTER_CLASS = new AttributeKey<TypeDef>("OUTER_CLASS", TypeDef.class);

  public static final AttributeKey<TypeParamRef> GENERIC_TYPE_REF = new AttributeKey<TypeParamRef>("GENERIC_TYPE_REF",
      TypeParamRef.class);
  public static final AttributeKey<Property> DESCENDANT_OF = new AttributeKey<Property>("DESCENDANT_OF", Property.class);

  public static final AttributeKey<Set<Property>> DESCENDANTS = new AttributeKey<Set<Property>>("DESCENDANTS", Set.class);

  public static final AttributeKey<Set<TypeDef>> ADDITIONAL_BUILDABLES = new AttributeKey<Set<TypeDef>>("ADDITIONAL_BUILDERS",
      Set.class);
  public static final AttributeKey<Set<TypeDef>> ADDITIONAL_TYPES = new AttributeKey<Set<TypeDef>>("ADDITIONAL_TYPES",
      Set.class);

  public static final AttributeKey<Boolean> VALIDATION_ENABLED = new AttributeKey<Boolean>("VALIDATION_ENABLED", Boolean.class);

  public static final AttributeKey<Boolean> LAZY_COLLECTIONS_INIT_ENABLED = new AttributeKey<Boolean>(
      "LAZY_COLLECTIONS_INIT_ENABLED", Boolean.class);
  public static final AttributeKey<Boolean> LAZY_MAP_INIT_ENABLED = new AttributeKey<Boolean>("LAZY_MAPS_INIT_ENABLED",
      Boolean.class);

  public static final AttributeKey<Boolean> EDITABLE_ENABLED = new AttributeKey<Boolean>("EDITABLE_ENABLED", Boolean.class);
  public static final AttributeKey<Boolean> BUILDABLE_ENABLED = new AttributeKey<Boolean>("BUILDABLE_ENABLED", Boolean.class);

  public static final AttributeKey<Boolean> SKIP = new AttributeKey<Boolean>("SKIP", Boolean.class);

  public static final AttributeKey<Boolean> GENERATED = new AttributeKey<Boolean>("GENERATED", Boolean.class);

  public static final AttributeKey<Buildable> BUILDABLE = new AttributeKey<Buildable>("BUILDABLE_ANNOTATION", Buildable.class);

  public static final AttributeKey<String[]> IGNORE_PROPERTIES = new AttributeKey<String[]>("IGNORE_PROPERTIES",
      String[].class);

  public static final AttributeKey<ExternalBuildables> EXTERNAL_BUILDABLE = new AttributeKey<ExternalBuildables>(
      "EXTERNAL_BUILDABLE", ExternalBuildables.class);

  public static final AnnotationRef BUILDABLE_ANNOTATION = new AnnotationRefBuilder()
      .withNewClassRef()
      .withFullyQualifiedName(Buildable.class.getName())
      .endClassRef()
      .build();

  public static final AnnotationRef DEPRECATED_ANNOTATION = new AnnotationRefBuilder()
      .withNewClassRef()
      .withFullyQualifiedName(Deprecated.class.getName())
      .endClassRef()
      .build();

  public static final ClassRef ARRAYS = new ClassRefBuilder().withFullyQualifiedName(Arrays.class.getName()).build();
  public static final ClassRef COLLECTORS = new ClassRefBuilder().withFullyQualifiedName(Collectors.class.getName()).build();

  public static final Property INDEX = new PropertyBuilder().withName("index").withTypeRef(io.sundr.model.utils.Types.INT_REF)
      .build();

  public static final TypeDef FUNCTION = new TypeDefBuilder().withKind(Kind.INTERFACE).withPackageName("java.util.function")
      .withName("Function").withParameters(io.sundr.model.utils.Types.I, io.sundr.model.utils.Types.O)
      .addNewMethod()
      .withName("apply")
      .withReturnType(io.sundr.model.utils.Types.O.toReference())
      .addNewArgument()
      .withName("item")
      .withTypeRef(io.sundr.model.utils.Types.I.toReference())
      .endArgument()
      .endMethod()
      .build();

  public static final TypeDef PREDICATE = new TypeDefBuilder().withKind(Kind.INTERFACE).withPackageName("java.util.function")
      .withName("Predicate").withParameters(io.sundr.model.utils.Types.I)
      .addNewMethod()
      .withName("test")
      .withReturnType(Types.BOOLEAN_REF)
      .addNewArgument()
      .withName("item")
      .withTypeRef(io.sundr.model.utils.Types.I.toReference())
      .endArgument()
      .endMethod()
      .build();

  public static final TypeDef INLINEABLE = new TypeDefBuilder().withKind(Kind.INTERFACE).withPackageName("io.sundr.builder")
      .withName("Inlineable").withParameters(io.sundr.model.utils.Types.T)
      .addNewMethod()
      .withName("update")
      .withReturnType(io.sundr.model.utils.Types.T.toReference())
      .endMethod()
      .build();

  //The classes below are created programmatically rather than by class to avoid bringing in more deps
  public static final ClassRef VALIDATION = new TypeDefBuilder()
      .withPackageName("javax.validation")
      .withName("Validation")
      .build().toInternalReference();

  public static final ClassRef VALIDATOR = new TypeDefBuilder()
      .withPackageName("javax.validation")
      .withName("Validator")
      .build().toInternalReference();

  public static final ClassRef VALIDATOR_FACTORY = new TypeDefBuilder()
      .withPackageName("javax.validation")
      .withName("ValidatorFactory")
      .build().toInternalReference();

  public static final ClassRef VALIDATION_EXCEPTION = new TypeDefBuilder()
      .withPackageName("javax.validation")
      .withName("ValidationException")
      .build().toInternalReference();

  public static final ClassRef CONSTRAIN_VIOLATION = new TypeDefBuilder()
      .withPackageName("javax.validation")
      .withName("ConstraintViolation")
      .build().toInternalReference();

  public static final ClassRef CONSTRAIN_VIOLATION_EXCEPTION = new TypeDefBuilder()
      .withPackageName("javax.validation")
      .withName("ConstraintViolationException")
      .build().toInternalReference();

  public static List<ClassRef> VALIDATION_REFS = Arrays.<ClassRef> asList(VALIDATION, VALIDATOR, VALIDATOR_FACTORY,
      VALIDATION_EXCEPTION, CONSTRAIN_VIOLATION, CONSTRAIN_VIOLATION_EXCEPTION, Collections.SET.toInternalReference());

  public static final String DEFAULT_SOURCEFILE_TEMPLATE_LOCATION = "templates/builder/sourcefile.vm";

  public static final String ACCEPT_VISITOR_SNIPPET = "snippets/accept-visitor.txt";
  public static final String BUILD_LIST_SNIPPET = "snippets/build-list.txt";
  public static final String CAN_VISIT_SNIPPET = "snippets/can-visit.txt";
  public static final String GET_TYPE_SNIPPET = "snippets/get-type.txt";
  public static final String GET_CLASS_SNIPPET = "snippets/get-class.txt";
  public static final String GET_MATCHING_INTERFACE_SNIPPET = "snippets/get-matching-interface.txt";
  public static final String GET_TYPE_ARGUMENTS_SNIPPET = "snippets/get-type-arguments.txt";
  public static final String AGGREGATE_SET_SNIPPET = "snippets/aggregate-set.txt";
  public static final String AGGREGATE_LIST_SNIPPET = "snippets/aggregate-list.txt";
  public static final String BUILD_SET_SNIPPET = "snippets/build-set.txt";
  public static final String SIMPLE_ARRAY_GETTER_SNIPPET = "snippets/simple-array-getter.txt";
  public static final String BUILDABLE_ARRAY_GETTER_SNIPPET = "snippets/buildable-array-getter.txt";
  public static final String VALIDATE_SNIPPET = "snippets/validate.txt";
  public static final String EMPTY_FUNCTION_SNIPPET = "snippets/empty-function.txt";
  public static final String TO_STRING_ARRAY_SNIPPET = "snippets/to-string-array.txt";

}
