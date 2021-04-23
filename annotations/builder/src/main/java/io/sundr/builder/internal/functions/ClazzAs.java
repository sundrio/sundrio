/**
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

package io.sundr.builder.internal.functions;

import static io.sundr.builder.Constants.*;
import static io.sundr.builder.internal.utils.BuilderUtils.*;
import static io.sundr.codegen.utils.TypeUtils.isAbstract;
import static io.sundr.codegen.utils.TypeUtils.modifiersToInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import io.sundr.FunctionFactory;
import io.sundr.Provider;
import io.sundr.SundrException;
import io.sundr.builder.Constants;
import io.sundr.builder.TypedVisitor;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.builder.internal.visitors.InitEnricher;
import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.functions.GetDefinition;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.Block;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.Statement;
import io.sundr.codegen.model.StringStatement;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.Getter;
import io.sundr.codegen.utils.Setter;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

public class ClazzAs {

  public static final Function<TypeDef, TypeDef> FLUENT_INTERFACE = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
    public TypeDef apply(TypeDef item) {
      List<Method> methods = new ArrayList<Method>();
      List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
      TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);
      TypeDef fluentImplType = TypeAs.FLUENT_IMPL.apply(item);

      //The generic letter is always the last
      final TypeParamDef genericType = fluentType.getParameters().get(fluentType.getParameters().size() - 1);

      for (Property property : item.getProperties()) {
        final TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF)
            .apply(property.getTypeRef());
        if (property.isStatic()) {
          continue;
        }
        if (!hasBuildableConstructorWithArgument(item, property) && !Setter.hasOrInherits(item, property)) {
          continue;
        }

        Property toAdd = new PropertyBuilder(property)
            .withModifiers(0)
            .addToAttributes(ORIGIN_TYPEDEF, item)
            .addToAttributes(OUTER_INTERFACE, fluentType)
            .addToAttributes(OUTER_CLASS, fluentImplType)
            .addToAttributes(GENERIC_TYPE_REF, genericType.toReference())
            .withComments()
            .withAnnotations()
            .build();

        boolean isBuildable = isBuildable(unwrapped);
        boolean isArray = TypeUtils.isArray(toAdd.getTypeRef());
        boolean isSet = TypeUtils.isSet(toAdd.getTypeRef());
        boolean isList = TypeUtils.isList(toAdd.getTypeRef());
        boolean isMap = TypeUtils.isMap(toAdd.getTypeRef());
        boolean isMapWithBuildableValue = isMap && isBuildable(TypeAs.UNWRAP_MAP_VALUE_OF.apply(unwrapped));
        boolean isAbstract = isAbstract(unwrapped);
        boolean isOptional = TypeUtils.isOptional(toAdd.getTypeRef())
            || TypeUtils.isOptionalInt(toAdd.getTypeRef())
            || TypeUtils.isOptionalDouble(toAdd.getTypeRef())
            || TypeUtils.isOptionalLong(toAdd.getTypeRef());

        Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(toAdd);
        toAdd = new PropertyBuilder(toAdd).addToAttributes(DESCENDANTS, descendants).accept(new InitEnricher()).build();

        if (isArray) {
          Property asList = arrayAsList(toAdd);
          methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
          methods.addAll(ToMethod.GETTER_ARRAY.apply(toAdd));
          methods.addAll(ToMethod.ADD_TO_COLLECTION.apply(asList));
          methods.addAll(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
          toAdd = asList;
        } else if (isSet || isList) {
          methods.addAll(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
          methods.addAll(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.add(ToMethod.WITH.apply(toAdd));
          methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
        } else if (isMap) {
          if (isMapWithBuildableValue && !isAbstract) {
            methods.addAll(ToMethod.ADD_NEW_VALUE_TO_MAP.apply(toAdd));
          } else if (!descendants.isEmpty()) {
            for (Property descendant : descendants) {
              methods.addAll(ToMethod.ADD_NEW_VALUE_TO_MAP.apply(descendant));
            }
          }
          methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
          methods.add(ToMethod.ADD_MAP_TO_MAP.apply(toAdd));
          methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
          methods.add(ToMethod.REMOVE_MAP_FROM_MAP.apply(toAdd));
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.add(ToMethod.WITH.apply(toAdd));
        } else if (isOptional) {
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.addAll(ToMethod.WITH_OPTIONAL.apply(toAdd));
        } else {
          toAdd = new PropertyBuilder(toAdd).addToAttributes(BUILDABLE_ENABLED, isBuildable).accept(new InitEnricher()).build();
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.add(ToMethod.WITH.apply(toAdd));
        }
        methods.add(ToMethod.HAS.apply(toAdd));
        methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(toAdd));

        if (isMap) {
          if (isMapWithBuildableValue && !isAbstract) {
            nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(toAdd));
          } else if (!descendants.isEmpty()) {
            for (Property descendant : descendants) {
              nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(descendant));
            }
          }
        } else if (isBuildable && !isAbstract) {
          methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
          methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
          if (isList || isArray) {
            methods.add(ToMethod.WITH_NEW_LIKE_NESTED_AT_INDEX.apply(toAdd));
            methods.addAll(ToMethod.EDIT_NESTED.apply(toAdd));
          } else if (!isSet) {
            methods.addAll(ToMethod.EDIT_NESTED.apply(toAdd));
            methods.add(ToMethod.EDIT_OR_NEW.apply(toAdd));
            methods.add(ToMethod.EDIT_OR_NEW_LIKE.apply(toAdd));
          }
          nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(toAdd));
        } else if (!descendants.isEmpty()) {
          for (Property descendant : descendants) {
            if (TypeUtils.isCollection(descendant.getTypeRef())) {
              methods.addAll(ToMethod.ADD_TO_COLLECTION.apply(descendant));
              methods.addAll(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
            } else {
              methods.add(ToMethod.WITH.apply(descendant));
            }

            if (isList || isArray) {
              methods.add(ToMethod.WITH_NEW_LIKE_NESTED_AT_INDEX.apply(descendant));
            }
            methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
            methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
            methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(descendant));
            nestedClazzes.add(PropertyAs.NESTED_INTERFACE.apply(descendant));
          }
        }
      }

      return new TypeDefBuilder(fluentType)
          .withAnnotations()
          .withInnerTypes(nestedClazzes)
          .withMethods(methods)
          .build();

    }
  });

  public static final Function<TypeDef, TypeDef> FLUENT_IMPL = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
    public TypeDef apply(TypeDef item) {
      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();
      List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
      final List<Property> properties = new ArrayList<Property>();
      TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);
      final TypeDef fluentImplType = TypeAs.FLUENT_IMPL.apply(item);

      //The generic letter is always the last
      final TypeParamDef genericType = fluentImplType.getParameters().get(fluentImplType.getParameters().size() - 1);

      Method emptyConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .build();

      Method instanceConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(item.toInternalReference())
          .withName("instance").and()
          .withNewBlock()
          .withStatements(toInstanceConstructorBody(item, ""))
          .endBlock()
          .build();

      constructors.add(emptyConstructor);
      constructors.add(instanceConstructor);

      for (final Property property : item.getProperties()) {
        final TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF)
            .apply(property.getTypeRef());

        if (property.isStatic()) {
          continue;
        }
        if (!hasBuildableConstructorWithArgument(item, property) && !Setter.hasOrInherits(item, property)) {
          continue;
        }

        final boolean isBuildable = isBuildable(unwrapped);
        final boolean isArray = TypeUtils.isArray(property.getTypeRef());
        final boolean isSet = TypeUtils.isSet(property.getTypeRef());
        final boolean isList = TypeUtils.isList(property.getTypeRef());
        final boolean isMap = TypeUtils.isMap(property.getTypeRef());
        final boolean isMapWithBuildableValue = isMap && isBuildable(TypeAs.UNWRAP_MAP_VALUE_OF.apply(unwrapped));
        final boolean isAbstract = isAbstract(unwrapped);
        boolean isOptional = TypeUtils.isOptional(property.getTypeRef())
            || TypeUtils.isOptionalInt(property.getTypeRef())
            || TypeUtils.isOptionalDouble(property.getTypeRef())
            || TypeUtils.isOptionalLong(property.getTypeRef());

        Property toAdd = new PropertyBuilder(property)
            .withModifiers(TypeUtils.modifiersToInt(Modifier.PRIVATE))
            .addToAttributes(ORIGIN_TYPEDEF, item)
            .addToAttributes(OUTER_INTERFACE, fluentType)
            .addToAttributes(OUTER_CLASS, fluentImplType)
            .addToAttributes(GENERIC_TYPE_REF, genericType.toReference())
            .withComments()
            .withAnnotations()
            .build();

        Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(toAdd);
        toAdd = new PropertyBuilder(toAdd).addToAttributes(DESCENDANTS, descendants).accept(new InitEnricher()).build();

        if (isArray) {
          Property asList = arrayAsList(toAdd);
          methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
          methods.addAll(ToMethod.GETTER_ARRAY.apply(toAdd));
          methods.addAll(ToMethod.ADD_TO_COLLECTION.apply(asList));
          methods.addAll(ToMethod.REMOVE_FROM_COLLECTION.apply(asList));
          toAdd = asList;
        } else if (isSet || isList) {
          methods.addAll(ToMethod.ADD_TO_COLLECTION.apply(toAdd));
          methods.addAll(ToMethod.REMOVE_FROM_COLLECTION.apply(toAdd));
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.add(ToMethod.WITH.apply(toAdd));
          methods.add(ToMethod.WITH_ARRAY.apply(toAdd));
        } else if (isMap) {
          if (isMapWithBuildableValue && !isAbstract) {
            methods.addAll(ToMethod.ADD_NEW_VALUE_TO_MAP.apply(toAdd));
            nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(toAdd));
          } else if (!descendants.isEmpty()) {
            for (Property descendant : descendants) {
              methods.addAll(ToMethod.ADD_NEW_VALUE_TO_MAP.apply(descendant));
              nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(descendant));
            }
          }
          methods.add(ToMethod.ADD_TO_MAP.apply(toAdd));
          methods.add(ToMethod.ADD_MAP_TO_MAP.apply(toAdd));
          methods.add(ToMethod.REMOVE_FROM_MAP.apply(toAdd));
          methods.add(ToMethod.REMOVE_MAP_FROM_MAP.apply(toAdd));
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.add(ToMethod.WITH.apply(toAdd));
        } else if (isOptional) {
          methods.addAll(ToMethod.WITH_OPTIONAL.apply(toAdd));
          methods.addAll(ToMethod.GETTER.apply(toAdd));
        } else {
          methods.addAll(ToMethod.GETTER.apply(toAdd));
          methods.add(ToMethod.WITH.apply(toAdd));
        }

        methods.add(ToMethod.HAS.apply(toAdd));
        methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(toAdd));
        if (isMap) {
          properties.add(toAdd);
        } else if (isBuildable && !isAbstract) {
          methods.add(ToMethod.WITH_NEW_NESTED.apply(toAdd));
          methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(toAdd));
          if (isList || isArray) {
            methods.add(ToMethod.WITH_NEW_LIKE_NESTED_AT_INDEX.apply(toAdd));
            methods.addAll(ToMethod.EDIT_NESTED.apply(toAdd));
          } else if (!isSet) {
            methods.addAll(ToMethod.EDIT_NESTED.apply(toAdd));
            methods.add(ToMethod.EDIT_OR_NEW.apply(toAdd));
            methods.add(ToMethod.EDIT_OR_NEW_LIKE.apply(toAdd));
          }

          nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(toAdd));
          properties.add(buildableField(toAdd));
        } else if (descendants.isEmpty()) {
          properties.add(toAdd);
        } else if (!descendants.isEmpty()) {
          properties.add(buildableField(toAdd));
          for (Property descendant : descendants) {
            if (TypeUtils.isCollection(descendant.getTypeRef())) {
              methods.addAll(ToMethod.ADD_TO_COLLECTION.apply(descendant));
              methods.addAll(ToMethod.REMOVE_FROM_COLLECTION.apply(descendant));
            } else {
              methods.add(ToMethod.WITH.apply(descendant));
            }
            methods.add(ToMethod.WITH_NEW_NESTED.apply(descendant));
            methods.add(ToMethod.WITH_NEW_LIKE_NESTED.apply(descendant));
            methods.addAll(ToMethod.WITH_NESTED_INLINE.apply(descendant));
            nestedClazzes.add(PropertyAs.NESTED_CLASS.apply(descendant));
            if (isList || isArray) {
              methods.add(ToMethod.WITH_NEW_LIKE_NESTED_AT_INDEX.apply(descendant));
            }
          }
        } else {
          properties.add(buildableField(toAdd));
        }
      }

      Method equals = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
          .addNewArgument().withName("o").withTypeRef(io.sundr.codegen.Constants.OBJECT.toReference()).endArgument()
          .withName("equals")
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              return BuilderUtils.toEquals(fluentImplType, properties);
            }
          })).build();

      Method hashCode = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .withReturnType(io.sundr.codegen.Constants.PRIMITIVE_INT_REF)
          .withName("hashCode")
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              return BuilderUtils.toHashCode(properties);
            }
          })).build();

      methods.add(equals);
      methods.add(hashCode);

      return BuilderContextManager.getContext().getDefinitionRepository().register(new TypeDefBuilder(fluentImplType)
          .withAnnotations()
          .withConstructors(constructors)
          .withProperties(properties)
          .withInnerTypes(nestedClazzes)
          .withMethods(methods)
          .build());
    }
  });

  public static final Function<TypeDef, TypeDef> BUILDER = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
    public TypeDef apply(final TypeDef item) {
      final boolean validationEnabled = item.hasAttribute(VALIDATION_ENABLED) ? item.getAttribute(VALIDATION_ENABLED) : false;
      final Modifier[] modifiers = item.isAbstract()
          ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      final TypeDef builderType = TypeAs.BUILDER.apply(item);
      ClassRef instanceRef = item.toInternalReference();

      ClassRef fluent = TypeAs.FLUENT_REF.apply(item);

      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();
      final List<Property> fields = new ArrayList<Property>();

      Property fluentProperty = new PropertyBuilder().withTypeRef(fluent).withName("fluent").build();
      Property validationEnabledProperty = new PropertyBuilder().withTypeRef(io.sundr.codegen.Constants.BOOLEAN_REF)
          .withName("validationEnabled").build();

      fields.add(fluentProperty);
      fields.add(validationEnabledProperty);

      Method emptyConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .withNewBlock()
          .addNewStringStatementStatement("this(true);")
          .endBlock()
          .build();

      Method validationEnabledConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(io.sundr.codegen.Constants.BOOLEAN_REF)
          .withName("validationEnabled")
          .and()
          .withNewBlock()
          .addToStatements(new StringStatement(new Provider<String>() {
            @Override
            public String get() {
              return hasDefaultConstructor(item) ? "this(new " + item.getName() + "(), validationEnabled);"
                  : "this.fluent = this; this.validationEnabled=validationEnabled;";
            }
          }))
          .endBlock()
          .build();

      Method fluentConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(fluent)
          .withName("fluent")
          .and()
          .withNewBlock()
          .addNewStringStatementStatement("this(fluent, true);")
          .endBlock()
          .build();

      Method fluentAndValidationConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(fluent)
          .withName("fluent")
          .and()
          .addNewArgument()
          .withTypeRef(io.sundr.codegen.Constants.BOOLEAN_REF)
          .withName("validationEnabled")
          .and()
          .withNewBlock()
          .addToStatements(new StringStatement(new Provider<String>() {
            @Override
            public String get() {
              return hasDefaultConstructor(item) ? "this(fluent, new " + item.getName() + "(), validationEnabled);"
                  : "this.fluent = fluent; this.validationEnabled=validationEnabled;";
            }
          }))
          .endBlock()
          .build();

      Method instanceAndFluentCosntructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(fluent)
          .withName("fluent")
          .and()
          .addNewArgument()
          .withTypeRef(instanceRef)
          .withName("instance").and()
          .withNewBlock()
          .addNewStringStatementStatement("this(fluent, instance, true);")
          .endBlock()
          .build();

      Method instanceAndFluentAndValidationEnabledCosntructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(fluent)
          .withName("fluent")
          .and()
          .addNewArgument()
          .withTypeRef(instanceRef)
          .withName("instance").and()
          .addNewArgument()
          .withTypeRef(io.sundr.codegen.Constants.BOOLEAN_REF)
          .withName("validationEnabled")
          .and()
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              List<Statement> instanceAndFluentConstructorStatements = toInstanceConstructorBody(item, "fluent");
              instanceAndFluentConstructorStatements.add(new StringStatement("this.validationEnabled = validationEnabled; "));
              return instanceAndFluentConstructorStatements;
            }
          })).build();

      Method instanceConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(instanceRef)
          .withName("instance").and()
          .withNewBlock()
          .addNewStringStatementStatement("this(instance,true);")
          .endBlock()
          .build();

      Method instanceAndValidationEnabledConstructor = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .addNewArgument()
          .withTypeRef(instanceRef)
          .withName("instance").and()
          .addNewArgument()
          .withTypeRef(io.sundr.codegen.Constants.BOOLEAN_REF)
          .withName("validationEnabled")
          .and()
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              List<Statement> statements = toInstanceConstructorBody(item, "this");
              statements.add(new StringStatement("this.validationEnabled = validationEnabled; "));
              return statements;
            }
          })).build();

      constructors.add(emptyConstructor);
      constructors.add(validationEnabledConstructor);
      constructors.add(fluentConstructor);
      constructors.add(fluentAndValidationConstructor);
      constructors.add(instanceAndFluentCosntructor);
      constructors.add(instanceAndFluentAndValidationEnabledCosntructor);
      constructors.add(instanceConstructor);
      constructors.add(instanceAndValidationEnabledConstructor);

      Method build = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(modifiers))
          .withReturnType(instanceRef)
          .withName("build")
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              return toBuild(item, item);
            }
          })).build();
      methods.add(build);

      Method equals = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .withReturnType(ClassTo.TYPEREF.apply(boolean.class))
          .addNewArgument().withName("o").withTypeRef(io.sundr.codegen.Constants.OBJECT.toReference()).endArgument()
          .withName("equals")
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              return BuilderUtils.toEquals(builderType, fields);
            }
          })).build();

      Method hashCode = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
          .withReturnType(io.sundr.codegen.Constants.PRIMITIVE_INT_REF)
          .withName("hashCode")
          .withBlock(new Block(new Provider<List<Statement>>() {
            @Override
            public List<Statement> get() {
              return BuilderUtils.toHashCode(fields);
            }
          })).build();

      methods.add(equals);
      methods.add(hashCode);

      if (validationEnabled) {
        ClassRef validatorRef = new ClassRefBuilder()
            .withFullyQualifiedName("javax.validation.Validator")
            .build();

        Property validatorProperty = new PropertyBuilder()
            .withName("validator")
            .withTypeRef(validatorRef)
            .build();

        fields.add(validatorProperty);
        Method validatorConstructor = new MethodBuilder()
            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
            .addNewArgument()
            .withTypeRef(validatorRef)
            .withName("validator")
            .and()
            .withNewBlock()
            .addToStatements(new StringStatement(new Provider<String>() {
              @Override
              public String get() {
                return hasDefaultConstructor(item) ? "this(new " + item.getName() + "(), true);"
                    : "this.fluent = this; this.validator=validator; \n this.validationEnabled = validator != null;";
              }
            }))
            .endBlock()
            .build();

        Method instanceAndFluentAndValidatorCosntructor = new MethodBuilder()
            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
            .addNewArgument()
            .withTypeRef(fluent)
            .withName("fluent")
            .and()
            .addNewArgument()
            .withTypeRef(instanceRef)
            .withName("instance").and()
            .addNewArgument()
            .withTypeRef(validatorRef)
            .withName("validator")
            .and()
            .withBlock(new Block(new Provider<List<Statement>>() {
              @Override
              public List<Statement> get() {
                List<Statement> instanceAndFluentConstructorStatements = toInstanceConstructorBody(item, "fluent");
                instanceAndFluentConstructorStatements.add(new StringStatement("this.validator = validator;"));
                instanceAndFluentConstructorStatements.add(new StringStatement("this.validationEnabled = validator != null; "));
                return instanceAndFluentConstructorStatements;
              }
            })).build();

        Method instanceAndValidatorConstructor = new MethodBuilder()
            .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
            .addNewArgument()
            .withTypeRef(instanceRef)
            .withName("instance").and()
            .addNewArgument()
            .withTypeRef(validatorRef)
            .withName("validator")
            .and()
            .withBlock(new Block(new Provider<List<Statement>>() {
              @Override
              public List<Statement> get() {
                List<Statement> statements = toInstanceConstructorBody(item, "this");
                statements.add(new StringStatement("this.validator = validator; "));
                statements.add(new StringStatement("this.validationEnabled = validator != null; "));
                return statements;
              }
            })).build();

        constructors.add(validatorConstructor);
        constructors.add(instanceAndFluentAndValidatorCosntructor);
        constructors.add(instanceAndValidatorConstructor);
      }

      return BuilderContextManager.getContext().getDefinitionRepository().register(new TypeDefBuilder(builderType)
          .withAnnotations()
          .withModifiers(TypeUtils.modifiersToInt(modifiers))
          .withProperties(fields)
          .withConstructors(constructors)
          .withMethods(methods)
          .build());
    }

  });

  public static final Function<TypeDef, TypeDef> EDITABLE_BUILDER = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
    public TypeDef apply(final TypeDef item) {
      final Modifier[] modifiers = item.isAbstract()
          ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      final TypeDef editable = EDITABLE.apply(item);
      return new TypeDefBuilder(BUILDER.apply(item))
          .withAnnotations()
          .accept(new TypedVisitor<MethodBuilder>() {
            public void visit(MethodBuilder builder) {
              if (builder.getName() != null && builder.getName().equals("build")) {
                builder.withModifiers(TypeUtils.modifiersToInt(modifiers));
                builder.withReturnType(editable.toInternalReference());
                builder.withBlock(new Block(new Provider<List<Statement>>() {
                  @Override
                  public List<Statement> get() {
                    return toBuild(editable, editable);
                  }
                }));
              }
            }
          }).build();
    }
  });

  public static final Function<TypeDef, TypeDef> EDITABLE = FunctionFactory.wrap(new Function<TypeDef, TypeDef>() {
    public TypeDef apply(TypeDef item) {
      Modifier[] modifiers = item.isAbstract()
          ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      TypeDef editableType = TypeAs.EDITABLE.apply(item);
      final TypeDef builderType = TypeAs.BUILDER.apply(item);

      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();

      for (Method constructor : item.getConstructors()) {
        constructors.add(superConstructorOf(constructor, editableType));
      }

      Method edit = new MethodBuilder()
          .withModifiers(TypeUtils.modifiersToInt(modifiers))
          .withReturnType(builderType.toInternalReference())
          .withName("edit")
          .withNewBlock()
          .addToStatements(new StringStatement(new Provider<String>() {
            @Override
            public String get() {
              return "return new " + builderType.getName() + "(this);";
            }
          }))
          .endBlock()
          .build();

      methods.add(edit);

      //We need to treat the editable classes as buildables themselves.
      return CodegenContext.getContext().getDefinitionRepository().register(
          BuilderContextManager.getContext().getBuildableRepository().register(new TypeDefBuilder(editableType)
              .withAnnotations()
              .withModifiers(TypeUtils.modifiersToInt(modifiers))
              .withConstructors(constructors)
              .withMethods(methods)
              .addToAttributes(BUILDABLE_ENABLED, true)
              .addToAttributes(GENERATED, true) // We want to know that its a generated type...
              .build()));
    }
  });

  public static final Function<TypeDef, TypeDef> POJO = FunctionFactory.wrap(new ToPojo());

  private static String staticAdapterBody(TypeDef pojo, TypeDef pojoBuilder, TypeDef source, boolean returnBuilder) {
    StringBuilder sb = new StringBuilder();
    sb.append("return new ").append(pojoBuilder.getName()).append("()");

    for (Method m : source.getMethods()) {
      String trimmedName = StringUtils.deCapitalizeFirst(m.getName().replaceAll("^get", "").replaceAll("^is", ""));
      if (m.getReturnType() instanceof ClassRef) {
        ClassRef ref = (ClassRef) m.getReturnType();
        Boolean hasSuperClass = pojo.getProperties()
            .stream()
            .filter(p -> p.getTypeRef() instanceof ClassRef && p.getName().equals(Getter.propertyNameSafe(m)))
            .map(p -> GetDefinition.of((ClassRef) p.getTypeRef()))
            .flatMap(c -> c.getExtendsList().stream())
            .count() > 0;

        if (GetDefinition.of(ref).isAnnotation()) {
          TypeDef generatedType = TypeUtils.allProperties(pojo)
              .stream()
              .filter(p -> p.getName().equals(m.getName()))
              .map(p -> p.getTypeRef())
              .filter(r -> r instanceof ClassRef)
              .map(r -> (ClassRef) r)
              .map(c -> GetDefinition.of(c))
              .findFirst()
              .orElse(null);

          if (generatedType != null) {
            Method ctor = BuilderUtils.findBuildableConstructor(generatedType);
            if (m.getReturnType().getDimensions() > 0) {
              sb.append(".addAllTo").append(StringUtils.capitalizeFirst(trimmedName)).append("(")
                  .append("Arrays.asList(")
                  .append("instance.").append(m.getName()).append("())")
                  .append(".stream().map(i ->")
                  .append("new ").append(generatedType.getName()).append("(")
                  .append(ctor.getArguments().stream()
                      .map(p -> Getter.find(GetDefinition.of((ClassRef) m.getReturnType()), p, true))
                      .map(g -> g.getName())
                      .map(s -> "i." + s + "()").collect(Collectors.joining(", ")))
                  .append(")")
                  .append(")")
                  .append(".collect(Collectors.toList()))");
            } else {
              sb.append(".with").append(StringUtils.capitalizeFirst(trimmedName)).append("(")
                  .append("new ").append(generatedType.getName()).append("(")
                  .append(ctor.getArguments().stream()
                      .map(p -> Getter.find(GetDefinition.of((ClassRef) m.getReturnType()), p, true))
                      .map(g -> g.getName())
                      .map(s -> "instance." + m.getName() + "()." + s + "()").collect(Collectors.joining(", ")))
                  .append(")")
                  .append(")");
            }
            continue;
          }
        }
      }
      String withMethod = "with" + (StringUtils.capitalizeFirst(trimmedName));

      if (TypeUtils.hasProperty(pojo, trimmedName)) {
        sb.append(".").append(withMethod).append("(").append("instance.").append(m.getName()).append("())");
      }
    }
    if (returnBuilder) {
      sb.append(".build();");
    } else {
      sb.append(";");
    }
    return sb.toString();
  }

  private static List<Statement> toInstanceConstructorBody(TypeDef clazz, String fluent) {
    Method constructor = findBuildableConstructor(clazz);
    List<Statement> statements = new ArrayList<Statement>();
    String ref = fluent;

    //We may use a reference to fluent or we may use directly "this". So we need to check.
    if (fluent != null && !fluent.isEmpty()) {
      statements.add(new StringStatement("this.fluent = " + fluent + "; "));
    } else {
      ref = "this";
    }

    for (Property property : constructor.getArguments()) {
      Method getter = Getter.find(clazz, property);
      if (getter != null) {
        String cast = property.getTypeRef() instanceof TypeParamRef ? "(" + property.getTypeRef().toString() + ")" : "";
        statements.add(new StringStatement(new StringBuilder().append(ref).append(".with").append(property.getNameCapitalized())
            .append("(").append(cast).append("instance.").append(getter.getName()).append("()); ").toString()));
      } else {
        throw new IllegalStateException("Could not find getter for property:" + property + " in class:" + clazz);
      }
    }

    TypeDef target = clazz;
    //Iterate parent objects and check for properties with setters but not ctor arguments.
    while (target != null && !io.sundr.codegen.Constants.OBJECT.equals(target) && BuilderUtils.isBuildable(target)) {
      for (Property property : target.getProperties()) {
        if (!hasBuildableConstructorWithArgument(target, property) && Setter.has(target, property)) {
          String withName = "with" + property.getNameCapitalized();
          String getterName = Getter.find(target, property).getName();
          statements.add(new StringStatement(new StringBuilder().append(ref).append(".").append(withName).append("(instance.")
              .append(getterName).append("());\n").toString()));
        }
      }

      if (!target.getExtendsList().isEmpty()) {
        target = BuilderContextManager.getContext().getBuildableRepository()
            .getBuildable(target.getExtendsList().iterator().next());
      } else {
        return statements;
      }
    }
    return statements;
  }

  private static List<Statement> toBuild(final TypeDef clazz, final TypeDef instanceType) {
    Method constructor = findBuildableConstructor(clazz);
    List<Statement> statements = new ArrayList<Statement>();

    statements.add(new StringStatement(new StringBuilder()
        .append(instanceType.getName()).append(" buildable = new ").append(instanceType.getName()).append("(")
        .append(StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
          public String apply(Property item) {
            return "fluent." + Getter.name(item) + "()";
          }
        }, ","))
        .append(");")
        .toString()));

    TypeDef target = clazz;
    List<TypeDef> parents = new ArrayList<TypeDef>();
    TypeUtils.visitParents(target, parents);
    for (TypeDef c : parents) {
      if (!isBuildable(c)) {
        continue;
      }

      for (Property property : c.getProperties()) {
        Method setter;
        try {
          setter = Setter.find(clazz, property);
        } catch (SundrException e) {
          continue; // no setter found nothing to set
        }
        if (!hasBuildableConstructorWithArgument(c, property)) {
          String getterName = Getter.name(property);
          statements.add(new StringStatement(new StringBuilder()
              .append("buildable.").append(setter.getName()).append("(fluent.").append(getterName).append("());")
              .toString()));

        }
      }
    }

    BuilderContext context = BuilderContextManager.getContext();
    if (context.isExternalvalidatorSupported()) {
      statements.add(new StringStatement(
          "if (validationEnabled) {" + context.getBuilderPackage() + ".ValidationUtils.validate(buildable, validator);}"));
    } else if (context.isValidationEnabled()) {
      statements.add(new StringStatement(
          "if (validationEnabled) {" + context.getBuilderPackage() + ".ValidationUtils.validate(buildable);}"));
    }
    statements.add(new StringStatement("return buildable;"));
    return statements;
  }

  private static Method superConstructorOf(Method constructor, TypeDef constructorType) {
    List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
    for (AnnotationRef candidate : constructor.getAnnotations()) {
      if (!candidate.getClassRef().equals(BUILDABLE_ANNOTATION.getClassRef())) {
        annotations.add(candidate);
      }
    }

    return new MethodBuilder(constructor)
        .withAnnotations(annotations)
        .withReturnType(constructorType.toReference())
        .withNewBlock()
        .addNewStringStatementStatement(
            "super(" + StringUtils.join(constructor.getArguments(), new Function<Property, String>() {
              public String apply(Property item) {
                return item.getName();
              }
            }, ", ") + ");")
        .endBlock()
        .build();
  }
}
