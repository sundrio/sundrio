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
import static io.sundr.model.utils.Types.isAbstract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import io.sundr.FunctionFactory;
import io.sundr.SundrException;
import io.sundr.adapter.apt.AptContext;
import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.builder.internal.visitors.AddNoArgWithMethod;
import io.sundr.builder.internal.visitors.InitEnricher;
import io.sundr.model.AnnotationRef;
import io.sundr.model.AnnotationRefBuilder;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.Modifiers;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.RichTypeDef;
import io.sundr.model.Statement;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.utils.Getter;
import io.sundr.model.utils.Setter;
import io.sundr.model.utils.TypeArguments;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class ClazzAs {

  public static final Function<RichTypeDef, TypeDef> FLUENT_INTERFACE = FunctionFactory
      .wrap(new Function<RichTypeDef, TypeDef>() {
        public TypeDef apply(RichTypeDef item) {
          List<Method> methods = new ArrayList<Method>();
          List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
          TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);
          TypeDef fluentImplType = TypeAs.FLUENT_IMPL.apply(item);

          Set<String> propertiesToIgnore = item.hasAttribute(IGNORE_PROPERTIES)
              ? new HashSet<>(Arrays.asList(item.getAttribute(IGNORE_PROPERTIES)))
              : new HashSet<>();

          //The generic letter is always the last
          final TypeParamDef genericType = fluentType.getParameters().get(fluentType.getParameters().size() - 1);

          Map<String, Property> itemProperties = item.getProperties().stream()
              .collect(Collectors.toMap(Property::getName, p -> p));

          for (Property property : item.getAllProperties()) {
            final TypeRef unwrapped = TypeAs
                .combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF)
                .apply(property.getTypeRef());

            if (property.isStatic()) {
              continue;
            }
            if (propertiesToIgnore.contains(property.getName())) {
              continue;
            }
            if (!hasBuildableConstructorWithArgument(item, property) && !Setter.hasOrInherits(item, property)) {
              continue;
            }

            boolean isInherited = !itemProperties.containsKey(property.getName());
            boolean isGeneric = property.hasAttribute(TypeArguments.ORIGINAL_TYPE_PARAMETER);
            boolean hasBuildableSuperClass = item.getExtendsList().stream().anyMatch(s -> isBuildable(s));

            // We should skip fields that originate from buildable superclasses, unless they are generic.
            // Generic fields need to be processed at the level they can be resolved to an actual type.
            if (isInherited && hasBuildableSuperClass && !isGeneric) {
              continue;
            }

            Property toAdd = new PropertyBuilder(property).withNewModifiers().endModifiers()
                .addToAttributes(ORIGIN_TYPEDEF, item)
                .addToAttributes(OUTER_INTERFACE, fluentType).addToAttributes(OUTER_CLASS, fluentImplType)
                .addToAttributes(GENERIC_TYPE_REF, genericType.toReference()).withComments().withAnnotations().build();

            boolean isBuildable = isBuildable(unwrapped);
            boolean isArray = Types.isArray(toAdd.getTypeRef());
            boolean isSet = Types.isSet(toAdd.getTypeRef());
            boolean isList = Types.isList(toAdd.getTypeRef());
            boolean isMap = Types.isMap(toAdd.getTypeRef());
            boolean isMapWithBuildableValue = isMap && isBuildable(TypeAs.UNWRAP_MAP_VALUE_OF.apply(unwrapped));
            boolean isAbstract = isAbstract(unwrapped);
            boolean isOptional = Types.isOptional(toAdd.getTypeRef()) || Types.isOptionalInt(toAdd.getTypeRef())
                || Types.isOptionalDouble(toAdd.getTypeRef()) || Types.isOptionalLong(toAdd.getTypeRef());

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
              toAdd = new PropertyBuilder(toAdd).addToAttributes(BUILDABLE_ENABLED, isBuildable).accept(new InitEnricher())
                  .build();
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
                if (Types.isCollection(descendant.getTypeRef())) {
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

          return new TypeDefBuilder(fluentType).withComments("Generated").withAnnotations().withInnerTypes(nestedClazzes)
              .withProperties()
              .withMethods(methods)
              .accept(new AddNoArgWithMethod())
              .build();

        }
      });

  public static final Function<RichTypeDef, TypeDef> FLUENT_IMPL = FunctionFactory.wrap(new Function<RichTypeDef, TypeDef>() {
    public TypeDef apply(RichTypeDef item) {
      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();
      List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
      final List<Property> properties = new ArrayList<Property>();
      TypeDef fluentType = TypeAs.FLUENT_INTERFACE.apply(item);
      final TypeDef fluentImplType = TypeAs.FLUENT_IMPL.apply(item);

      Set<String> propertiesToIgnore = item.hasAttribute(IGNORE_PROPERTIES)
          ? new HashSet<>(Arrays.asList(item.getAttribute(IGNORE_PROPERTIES)))
          : new HashSet<>();
      //The generic letter is always the last
      final TypeParamDef genericType = fluentImplType.getParameters().get(fluentImplType.getParameters().size() - 1);

      Method emptyConstructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .build();

      Method instanceConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers().addNewArgument()
          .withTypeRef(item.toInternalReference()).withName("instance").and().withNewBlock()
          .withStatements(toInstanceConstructorBody(item, item, "")).endBlock().build();

      constructors.add(emptyConstructor);
      constructors.add(instanceConstructor);

      Map<String, Property> itemProperties = item.getProperties().stream().collect(Collectors.toMap(Property::getName, p -> p));
      for (final Property property : item.getAllProperties()) {
        final TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF)
            .apply(property.getTypeRef());

        if (property.isStatic()) {
          continue;
        }
        if (propertiesToIgnore.contains(property.getName())) {
          continue;
        }

        if (!hasBuildableConstructorWithArgument(item, property) && !Setter.hasOrInherits(item, property)) {
          continue;
        }
        boolean isInherited = !itemProperties.containsKey(property.getName());
        boolean isGeneric = property.hasAttribute(TypeArguments.ORIGINAL_TYPE_PARAMETER);
        boolean hasBuildableSuperClass = item.getExtendsList().stream().anyMatch(s -> isBuildable(s));

        // We should skip fields that originate from buildable superclasses, unless they are generic.
        // Generic fields need to be processed at the level they can be resolved to an actual type.
        if (isInherited && hasBuildableSuperClass && !isGeneric) {
          continue;
        }

        final boolean isBuildable = isBuildable(unwrapped);
        final boolean isArray = Types.isArray(property.getTypeRef());
        final boolean isSet = Types.isSet(property.getTypeRef());
        final boolean isList = Types.isList(property.getTypeRef());
        final boolean isMap = Types.isMap(property.getTypeRef());
        final boolean isMapWithBuildableValue = isMap && isBuildable(TypeAs.UNWRAP_MAP_VALUE_OF.apply(unwrapped));
        final boolean isAbstract = isAbstract(unwrapped);
        boolean isOptional = Types.isOptional(property.getTypeRef()) || Types.isOptionalInt(property.getTypeRef())
            || Types.isOptionalDouble(property.getTypeRef()) || Types.isOptionalLong(property.getTypeRef());

        Property toAdd = new PropertyBuilder(property)
            .withNewModifiers().withPrivate().endModifiers()
            .addToAttributes(ORIGIN_TYPEDEF, item).addToAttributes(OUTER_INTERFACE, fluentType)
            .addToAttributes(OUTER_CLASS, fluentImplType).addToAttributes(GENERIC_TYPE_REF, genericType.toReference())
            .withComments().withAnnotations().build();

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
        } else if (isBuildable) {
          if (!isAbstract) {
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
          }
          properties.add(buildableField(toAdd));
        } else if (descendants.isEmpty()) {
          properties.add(toAdd);
        } else if (!descendants.isEmpty()) {
          properties.add(buildableField(toAdd));
          for (Property descendant : descendants) {
            if (Types.isCollection(descendant.getTypeRef())) {
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
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(Types.PRIMITIVE_BOOLEAN_REF).addNewArgument().withName("o")
          .withTypeRef(Types.OBJECT.toReference()).endArgument().withName("equals").withNewBlock()
          .withStatements(BuilderUtils.toEquals(fluentImplType, properties)).endBlock()
          .build();

      Method hashCode = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(io.sundr.model.utils.Types.PRIMITIVE_INT_REF).withName("hashCode").withNewBlock()
          .withStatements(BuilderUtils.toHashCode(properties)).endBlock()
          .build();

      Method toString = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(io.sundr.model.utils.Types.STRING_REF).withName("toString").withNewBlock()
          .withStatements(BuilderUtils.toString(fluentImplType.getName(), properties)).endBlock()
          .build();

      methods.add(equals);
      methods.add(hashCode);
      methods.add(toString);

      return BuilderContextManager.getContext().getDefinitionRepository()
          .register(
              new TypeDefBuilder(fluentImplType).withComments("Generated")
                  .withAnnotations(
                      new AnnotationRefBuilder().withClassRef(ClassRef.forName(SuppressWarnings.class.getCanonicalName()))
                          .addToParameters("value", "unchecked").build())
                  .withConstructors(constructors)
                  .withProperties(properties).withInnerTypes(nestedClazzes).withMethods(methods)
                  .accept(new AddNoArgWithMethod())
                  .build());
    }
  });

  public static final Function<RichTypeDef, TypeDef> BUILDER = FunctionFactory.wrap(new Function<RichTypeDef, TypeDef>() {
    public TypeDef apply(final RichTypeDef item) {
      final boolean validationEnabled = item.hasAttribute(VALIDATION_ENABLED) ? item.getAttribute(VALIDATION_ENABLED) : false;
      final Modifier[] modifiers = item.isAbstract() ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      final TypeDef builderType = TypeAs.BUILDER.apply(item);
      ClassRef instanceRef = item.toInternalReference();

      ClassRef fluent = TypeAs.FLUENT_REF.apply(item);

      Optional<TypeDef> buildableInterface = item.getImplementsList().stream().map(GetDefinition::of)
          .filter(t -> t.getAnnotations().stream()
              .anyMatch(a -> a.getClassRef().getFullyQualifiedName().equals(Buildable.class.getName())))
          .findFirst();

      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();
      final List<Property> fields = new ArrayList<Property>();

      Property fluentProperty = new PropertyBuilder().withTypeRef(fluent).withName("fluent").build();
      Property validationEnabledProperty = new PropertyBuilder().withTypeRef(io.sundr.model.utils.Types.BOOLEAN_REF)
          .withName("validationEnabled").build();

      fields.add(fluentProperty);
      fields.add(validationEnabledProperty);

      Method emptyConstructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withNewBlock()
          .addNewStringStatementStatement("this(false);").endBlock().build();

      Method validationEnabledConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
          .addNewArgument().withTypeRef(io.sundr.model.utils.Types.BOOLEAN_REF).withName("validationEnabled").and()
          .withNewBlock().addToStatements(new StringStatement(new Supplier<String>() {
            @Override
            public String get() {
              return hasDefaultConstructor(item) ? "this(new " + item.getName() + "(), validationEnabled);"
                  : "this.fluent = this; this.validationEnabled=validationEnabled;";
            }
          })).endBlock().build();

      Method fluentConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers().addNewArgument()
          .withTypeRef(fluent).withName("fluent").and().withNewBlock().addNewStringStatementStatement("this(fluent, false);")
          .endBlock().build();

      Method fluentAndValidationConstructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .addNewArgument().withTypeRef(fluent).withName("fluent").and().addNewArgument()
          .withTypeRef(io.sundr.model.utils.Types.BOOLEAN_REF).withName("validationEnabled").and().withNewBlock()
          .addToStatements(new StringStatement(new Supplier<String>() {
            @Override
            public String get() {
              return hasDefaultConstructor(item) ? "this(fluent, new " + item.getName() + "(), validationEnabled);"
                  : "this.fluent = fluent; this.validationEnabled=validationEnabled;";
            }
          })).endBlock().build();

      Method instanceAndFluentCosntructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .addNewArgument().withTypeRef(fluent).withName("fluent").and().addNewArgument().withTypeRef(instanceRef)
          .withName("instance").and().withNewBlock().addNewStringStatementStatement("this(fluent, instance, false);").endBlock()
          .build();

      Method instanceAndFluentAndValidationEnabledCosntructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers().addNewArgument().withTypeRef(fluent).withName("fluent")
          .and().addNewArgument().withTypeRef(instanceRef).withName("instance").and().addNewArgument()
          .withTypeRef(Types.BOOLEAN_REF).withName("validationEnabled").and()
          .withNewBlock()
          .addAllToStatements(toInstanceConstructorBody(item, item, "fluent"))
          .addNewStringStatementStatement("this.validationEnabled = validationEnabled; ")
          .endBlock()
          .build();

      Method instanceConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers().addNewArgument()
          .withTypeRef(instanceRef).withName("instance").and().withNewBlock()
          .addNewStringStatementStatement("this(instance,false);").endBlock()
          .build();

      Method instanceAndValidationEnabledConstructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers().addNewArgument().withTypeRef(instanceRef)
          .withName("instance").and().addNewArgument().withTypeRef(Types.BOOLEAN_REF)
          .withName("validationEnabled").and()
          .withNewBlock()
          .addAllToStatements(toInstanceConstructorBody(item, item, "this"))
          .addNewStringStatementStatement("this.validationEnabled = validationEnabled; ")
          .endBlock()
          .build();

      constructors.add(emptyConstructor);
      constructors.add(validationEnabledConstructor);
      constructors.add(fluentConstructor);
      constructors.add(fluentAndValidationConstructor);
      constructors.add(instanceAndFluentCosntructor);
      constructors.add(instanceAndFluentAndValidationEnabledCosntructor);
      constructors.add(instanceConstructor);
      constructors.add(instanceAndValidationEnabledConstructor);

      buildableInterface.ifPresent(i -> {
        List<ClassRef> getterExceptions = i.getMethods().stream()
            .filter(Getter::is)
            .filter(m -> m != null && m.getExceptions() != null)
            .flatMap(m -> m.getExceptions().stream()).collect(Collectors.toList());

        ClassRef buildableInterfaceRef = item.getImplementsList().stream()
            .filter(candidate -> candidate.getFullyQualifiedName().equals(i.getFullyQualifiedName())).findFirst()
            .orElseThrow(() -> new IllegalStateException(
                "Could not find reference for implemented interface:" + i.getFullyQualifiedName()));

        Method sourceInstanceAndFluentCosntructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .addNewArgument().withTypeRef(fluent).withName("fluent").and().addNewArgument().withTypeRef(buildableInterfaceRef)
            .withName("instance").and().withNewBlock().addNewStringStatementStatement("this(fluent, instance, false);")
            .endBlock()
            .build();

        Method sourceInstanceAndFluentAndValidationEnabledCosntructor = new MethodBuilder()
            .withNewModifiers().withPublic().endModifiers().addNewArgument().withTypeRef(fluent).withName("fluent")
            .and().addNewArgument().withTypeRef(buildableInterfaceRef).withName("instance").and().addNewArgument()
            .withTypeRef(Types.BOOLEAN_REF).withName("validationEnabled").and()
            .withNewBlock()
            .addAllToStatements(toInstanceConstructorBody(item, i, "fluent"))
            .addNewStringStatementStatement("this.validationEnabled = validationEnabled; ")
            .endBlock()
            .build();

        Method sourceInstanceConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .addNewArgument()
            .withTypeRef(buildableInterfaceRef).withName("instance").and().withNewBlock()
            .addNewStringStatementStatement("this(instance,false);").endBlock()
            .build();

        Method sourceInstanceAndValidationEnabledConstructor = new MethodBuilder()
            .withNewModifiers().withPublic().endModifiers().addNewArgument().withTypeRef(buildableInterfaceRef)
            .withName("instance").and().addNewArgument().withTypeRef(Types.BOOLEAN_REF)
            .withName("validationEnabled").and()
            .withNewBlock()
            .addAllToStatements(toInstanceConstructorBody(item, i, "this"))
            .addNewStringStatementStatement("this.validationEnabled = validationEnabled; ")
            .endBlock()
            .build();

        constructors.add(sourceInstanceAndFluentCosntructor);
        constructors.add(sourceInstanceAndFluentAndValidationEnabledCosntructor);
        constructors.add(sourceInstanceAndValidationEnabledConstructor);
        constructors.add(sourceInstanceConstructor);
      });

      Method build = new MethodBuilder().withModifiers(Modifiers.from(modifiers)).withReturnType(instanceRef)
          .withName("build")
          .withNewBlock()
          .withStatements(toBuild(item, item))
          .endBlock()
          .build();
      methods.add(build);

      //
      // We don't want to generate equals and hashCode for the builder as in most cases contains self references, leading to stack overflow errors
      //

      if (validationEnabled) {
        ClassRef validatorRef = new ClassRefBuilder().withFullyQualifiedName("javax.validation.Validator").build();

        Property validatorProperty = new PropertyBuilder().withName("validator").withTypeRef(validatorRef).build();

        fields.add(validatorProperty);
        Method validatorConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .addNewArgument().withTypeRef(validatorRef).withName("validator").and().withNewBlock()
            .addToStatements(new StringStatement(new Supplier<String>() {
              @Override
              public String get() {
                return hasDefaultConstructor(item) ? "this(new " + item.getName() + "(), true);"
                    : "this.fluent = this; this.validator=validator; \n this.validationEnabled = validator != null;";
              }
            })).endBlock().build();

        Method instanceAndFluentAndValidatorConstructor = new MethodBuilder()
            .withNewModifiers().withPublic().endModifiers().addNewArgument().withTypeRef(fluent).withName("fluent")
            .and().addNewArgument().withTypeRef(instanceRef).withName("instance").and().addNewArgument()
            .withTypeRef(validatorRef).withName("validator").and()
            .withNewBlock()
            .withStatements(toInstanceConstructorBody(item, item, "fluent"))
            .addNewStringStatementStatement("this.validator = validator;")
            .addNewStringStatementStatement("this.validationEnabled = validator != null; ")
            .endBlock()
            .build();

        Method instanceAndValidatorConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .addNewArgument().withTypeRef(instanceRef).withName("instance").and().addNewArgument().withTypeRef(validatorRef)
            .withName("validator").and()
            .withNewBlock()
            .withStatements(toInstanceConstructorBody(item, item, "this"))
            .addNewStringStatementStatement("this.validator = validator;")
            .addNewStringStatementStatement("this.validationEnabled = validator != null; ")
            .endBlock()
            .build();

        Method fluentAndValidatorConstructor = new MethodBuilder()
            .withNewModifiers().withPublic().endModifiers().addNewArgument().withTypeRef(fluent).withName("fluent")
            .and().addNewArgument()
            .withTypeRef(validatorRef).withName("validator").and()
            .withNewBlock()
            .addNewStringStatementStatement("this.fluent = fluent;")
            .addNewStringStatementStatement("this.validator = validator;")
            .addNewStringStatementStatement("this.validationEnabled = validator != null; ")
            .endBlock()
            .build();

        Method usingValidation = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .withReturnType(builderType.toInternalReference())
            .withName("usingValidation")
            .withNewBlock()
            .addNewStringStatementStatement("return new " + builderType.getName() + "(this, true);")
            .endBlock()
            .build();

        Method usingValidator = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .withReturnType(builderType.toInternalReference())
            .withName("usingValidator")
            .addNewArgument().withName("validator").withTypeRef(validatorRef).endArgument()
            .withNewBlock()
            .addNewStringStatementStatement("return new " + builderType.getName() + "(this, validator);")
            .endBlock()
            .build();

        BuilderContext context = BuilderContextManager.getContext();
        methods.add(usingValidation);
        if (context.isExternalvalidatorSupported()) {
          methods.add(usingValidator);
        }
        constructors.add(validatorConstructor);
        constructors.add(instanceAndFluentAndValidatorConstructor);
        constructors.add(instanceAndValidatorConstructor);
        constructors.add(fluentAndValidatorConstructor);
      }

      return BuilderContextManager.getContext().getDefinitionRepository()
          .register(new TypeDefBuilder(builderType).withAnnotations().withModifiers(Modifiers.from(modifiers))
              .withProperties(fields).withConstructors(constructors).withMethods(methods).build());
    }

  });

  public static final Function<RichTypeDef, TypeDef> EDITABLE_BUILDER = FunctionFactory
      .wrap(new Function<RichTypeDef, TypeDef>() {
        public TypeDef apply(final RichTypeDef item) {
          final Modifier[] modifiers = item.isAbstract() ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
              : new Modifier[] { Modifier.PUBLIC };

          final TypeDef editable = EDITABLE.apply(item);
          return new TypeDefBuilder(BUILDER.apply(item)).withComments("Generated").withAnnotations()
              .accept(new Visitor<MethodBuilder>() {
                public void visit(MethodBuilder builder) {
                  if (builder.getName() != null && builder.getName().equals("build")) {
                    builder.withModifiers(Modifiers.from(modifiers));
                    builder.withReturnType(editable.toInternalReference());
                    builder.withNewBlock().withStatements(toBuild(editable, editable)).endBlock();
                  }
                }
              }).build();
        }
      });

  public static final Function<RichTypeDef, TypeDef> EDITABLE = FunctionFactory.wrap(new Function<RichTypeDef, TypeDef>() {
    public TypeDef apply(RichTypeDef item) {
      Modifier[] modifiers = item.isAbstract() ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      TypeDef editableType = TypeAs.EDITABLE.apply(item);
      final TypeDef builderType = TypeAs.BUILDER.apply(item);

      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();

      constructors.addAll(item.getConstructors().stream()
          .filter(c -> !c.isPrivate())
          .map(c -> superConstructorOf(c, editableType))
          .collect(Collectors.toList()));

      Method edit = new MethodBuilder().withModifiers(Modifiers.from(modifiers))
          .withReturnType(builderType.toInternalReference()).withName("edit").withNewBlock()
          .addToStatements(new StringStatement(new Supplier<String>() {
            @Override
            public String get() {
              return "return new " + builderType.getName() + "(this);";
            }
          })).endBlock().build();

      methods.add(edit);

      //We need to treat the editable classes as buildables themselves.
      return AptContext.getContext().getDefinitionRepository()
          .register(BuilderContextManager.getContext().getBuildableRepository()
              .register(new TypeDefBuilder(editableType).withComments("Generated").withAnnotations()
                  .withModifiers(Modifiers.from(modifiers)).withConstructors(constructors).withMethods(methods)
                  .addToAttributes(BUILDABLE_ENABLED, true).addToAttributes(GENERATED, true) // We want to know that its a generated type...
                  .build()));
    }
  });

  public static final Function<RichTypeDef, TypeDef> POJO = FunctionFactory.wrap(new ToPojo());

  private static String staticAdapterBody(TypeDef pojo, TypeDef pojoBuilder, TypeDef source, boolean returnBuilder) {
    StringBuilder sb = new StringBuilder();
    sb.append("return new ").append(pojoBuilder.getName()).append("()");

    for (Method m : source.getMethods()) {
      String trimmedName = Strings.deCapitalizeFirst(m.getName().replaceAll("^get", "").replaceAll("^is", ""));
      if (m.getReturnType() instanceof ClassRef) {
        ClassRef ref = (ClassRef) m.getReturnType();
        Boolean hasSuperClass = pojo.getProperties()
            .stream()
            .filter(p -> p.getTypeRef() instanceof ClassRef && p.getName().equals(Getter.propertyNameSafe(m)))
            .map(p -> GetDefinition.of((ClassRef) p.getTypeRef()))
            .flatMap(c -> c.getExtendsList().stream())
            .count() > 0;

        if (GetDefinition.of(ref).isAnnotation()) {
          TypeDef generatedType = Types.allProperties(pojo)
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
              sb.append(".addAllTo").append(Strings.capitalizeFirst(trimmedName)).append("(")
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
              sb.append(".with").append(Strings.capitalizeFirst(trimmedName)).append("(")
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
      String withMethod = "with" + (Strings.capitalizeFirst(trimmedName));

      if (Types.hasProperty(pojo, trimmedName)) {
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

  private static List<Statement> toInstanceConstructorBody(TypeDef clazz, TypeDef instance, String fluent) {
    Method constructor = findBuildableConstructor(clazz);
    List<Statement> statements = new ArrayList<Statement>();
    final String ref = fluent != null && !fluent.isEmpty() ? fluent : "this";

    //We may use a reference to fluent or we may use directly "this". So we need to check.
    if (fluent != null && !fluent.isEmpty()) {
      statements.add(new StringStatement("this.fluent = " + fluent + "; "));
    }

    for (Property property : constructor.getArguments()) {
      Optional<Method> getter = Getter.findOptional(instance, property);
      getter.ifPresent(g -> {
        String cast = property.getTypeRef() instanceof TypeParamRef ? "(" + property.getTypeRef().toString() + ")" : "";
        statements.add(new StringStatement(new StringBuilder().append(ref).append(".with").append(property.getNameCapitalized())
            .append("(").append(cast).append("instance.").append(g.getName()).append("()); ").toString()));
      });

      // } else {
      //   throw new IllegalStateException("Could not find getter for property:" + property + " in class:" + clazz);
      // }
    }

    TypeDef target = clazz;
    Set<String> propertiesToIgnore = clazz.hasAttribute(IGNORE_PROPERTIES)
        ? new HashSet<>(Arrays.asList(clazz.getAttribute(IGNORE_PROPERTIES)))
        : new HashSet<>();

    //Iterate parent objects and check for properties with setters but not ctor arguments.
    while (target != null && !Types.OBJECT.equals(target) && BuilderUtils.isRegisteredAsBuildable(target)) { // We want to allow abstract parents marked as buildable
      for (Property property : target.getProperties()) {
        if (propertiesToIgnore.contains(property.getName())) {
          continue;
        } else if (!hasBuildableConstructorWithArgument(target, property) && Setter.has(target, property)) {
          Getter.findOptional(instance, property).map(Method::getName).ifPresent(getterName -> {
            String withName = "with" + property.getNameCapitalized();
            statements.add(new StringStatement(new StringBuilder().append(ref).append(".").append(withName).append("(instance.")
                .append(getterName).append("());\n").toString()));
          });
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
    Set<String> propertiesToIgnore = clazz.hasAttribute(IGNORE_PROPERTIES)
        ? new HashSet<>(Arrays.asList(clazz.getAttribute(IGNORE_PROPERTIES)))
        : new HashSet<>();

    statements.add(new StringStatement(new StringBuilder()
        .append(instanceType.getName()).append(" buildable = new ").append(instanceType.getName()).append("(")
        .append(Strings.join(constructor.getArguments(), new Function<Property, String>() {
          public String apply(Property item) {
            return "fluent." + Getter.name(item) + "()";
          }
        }, ","))
        .append(");")
        .toString()));

    TypeDef target = clazz;
    List<TypeDef> parents = new ArrayList<TypeDef>();
    Types.visitParents(target, parents);
    for (TypeDef c : parents) {
      if (!isRegisteredAsBuildable(c)) {
        continue;
      }

      for (Property property : c.getProperties()) {
        if (propertiesToIgnore.contains(property.getName())) {
          continue;
        }
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
            "super(" + Strings.join(constructor.getArguments(), new Function<Property, String>() {
              public String apply(Property item) {
                return item.getName();
              }
            }, ", ") + ");")
        .endBlock()
        .build();
  }
}
