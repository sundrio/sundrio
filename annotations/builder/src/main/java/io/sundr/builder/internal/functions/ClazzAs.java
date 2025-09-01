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
import static io.sundr.model.Attributeable.ALSO_IMPORT;
import static io.sundr.model.utils.Types.isAbstract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;

import io.sundr.FunctionFactory;
import io.sundr.adapter.apt.AptContext;
import io.sundr.builder.Constants;
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
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.utils.Getter;
import io.sundr.model.utils.Setter;
import io.sundr.model.utils.TypeArguments;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class ClazzAs {

  public static final Function<RichTypeDef, TypeDef> FLUENT = FunctionFactory.wrap(new Function<RichTypeDef, TypeDef>() {
    public TypeDef apply(RichTypeDef item) {
      BuilderContext ctx = BuilderContextManager.getContext();
      List<Method> constructors = new ArrayList<Method>();
      List<Method> allMethods = new ArrayList<Method>();
      List<TypeDef> nestedClazzes = new ArrayList<TypeDef>();
      final List<Property> properties = new ArrayList<Property>();

      ClassRef itemRef = item.toInternalReference();
      ClassRef fluentRef = TypeAs.FLUENT_A_REF.apply(itemRef);
      ClassRef fluent = TypeAs.FLUENT_REF.apply(itemRef);

      List<TypeParamDef> parameters = new ArrayList<>(item.getParameters());
      TypeParamDef nextParameter = getNextGeneric(item, parameters);
      TypeParamDef parameterFluent = new TypeParamDefBuilder(nextParameter).addToBounds(fluentRef).build();
      parameters.add(parameterFluent);

      List<TypeRef> superClassParameters = new ArrayList<>();
      ClassRef buildableSuperClassRef = findBuildableSuperClassRef(item);
      if (buildableSuperClassRef != null) {
        superClassParameters.addAll(buildableSuperClassRef.getArguments());
      }

      superClassParameters.add(parameterFluent.toReference());
      ClassRef superClassRef = buildableSuperClassRef != null
          ? new ClassRefBuilder(buildableSuperClassRef)
              .withFullyQualifiedName(buildableSuperClassRef.getFullyQualifiedName() + "Fluent")
              .withArguments(superClassParameters).build()
          : ctx.getBaseFluentClass().toReference(superClassParameters);

      Method emptyConstructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .build();

      Method instanceConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers().addNewArgument()
          .withTypeRef(item.toInternalReference()).withName("instance").and().withNewBlock()
          .addNewStringStatementStatement("this.copyInstance(instance);").endBlock().build();

      Method copyInstance = new MethodBuilder().withName("copyInstance")
          .withNewModifiers().withProtected().endModifiers()
          .withReturnType(Types.VOID)
          .addNewArgument().withTypeRef(item.toInternalReference()).withName("instance").and().withNewBlock()
          .withStatements(toInstanceConstructorBody(item, item, "")).endBlock().build();

      allMethods.add(copyInstance);
      constructors.add(emptyConstructor);
      constructors.add(instanceConstructor);

      Set<Property> allDescendants = new LinkedHashSet<>();

      item.getAllProperties().stream().filter(isPropertyApplicable(item)).forEach(property -> {
        final TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_OPTIONAL_OF)
            .apply(property.getTypeRef());

        // only looking at the field, but the getter/setter could have annotations as well
        boolean deprecated = property.getAnnotations().stream().anyMatch(Constants.DEPRECATED_ANNOTATION::equals);

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
            .addToAttributes(ORIGIN_TYPEDEF, item)
            .addToAttributes(OUTER_TYPE, fluent)
            .addToAttributes(GENERIC_TYPE_REF, nextParameter.toReference())
            .withComments().withAnnotations().build();

        Set<Property> descendants = Descendants.PROPERTY_BUILDABLE_DESCENDANTS.apply(toAdd);
        allDescendants.addAll(descendants);
        toAdd = new PropertyBuilder(toAdd).addToAttributes(DESCENDANTS, descendants).accept(new InitEnricher()).build();
        List<Method> methods = new ArrayList<Method>();
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
        for (Method m : methods) {
          if (deprecated && !m.getAnnotations().stream().anyMatch(Constants.DEPRECATED_ANNOTATION::equals)) {
            m = new MethodBuilder(m).addToAnnotations(Constants.DEPRECATED_ANNOTATION)
                .addToComments(
                    String.format("The field %s has been deprecated, please see the pojo class for more information",
                        property.getName()))
                .build();
          }
          allMethods.add(m);
        }
      });

      Method equals = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(Types.PRIMITIVE_BOOLEAN_REF).addNewArgument().withName("o")
          .withTypeRef(Types.OBJECT.toReference()).endArgument().withName("equals").withNewBlock()
          .withStatements(BuilderUtils.toEquals(fluent, properties)).endBlock()
          .build();

      Method hashCode = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(io.sundr.model.utils.Types.PRIMITIVE_INT_REF).withName("hashCode").withNewBlock()
          .withStatements(BuilderUtils.toHashCode(properties)).endBlock()
          .build();

      Method toString = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withReturnType(io.sundr.model.utils.Types.STRING_REF).withName("toString").withNewBlock()
          .withStatements(BuilderUtils.toString(fluent.getName(), properties)).endBlock()
          .build();

      allMethods.add(equals);
      allMethods.add(hashCode);
      allMethods.add(toString);

      if (!allDescendants.isEmpty()) {
        allMethods.add(createDescendantBuilderMethod(allDescendants));
      }

      return ctx.getDefinitionRepository()
          .register(
              new TypeDefBuilder().withComments("Generated")
                  .withModifiers(Modifiers.from(Modifier.PUBLIC))
                  .withPackageName(fluent.getPackageName())
                  .withName(fluent.getName())
                  .withParameters(parameters)
                  .withExtendsList(superClassRef)
                  .addToAttributes(ALSO_IMPORT,
                      Arrays.asList(ClassRef.forClass(Optional.class), ClassRef.forClass(Predicate.class),
                          ClassRef.forClass(Objects.class)))
                  .withAnnotations(
                      new AnnotationRefBuilder().withClassRef(ClassRef.forName(SuppressWarnings.class.getCanonicalName()))
                          .addToParameters("value", "unchecked").build())
                  .withConstructors(constructors)
                  .withProperties(properties).withInnerTypes(nestedClazzes).withMethods(allMethods)
                  .accept(new AddNoArgWithMethod())
                  .build());
    }

    private Method createDescendantBuilderMethod(Set<Property> allDescendants) {
      List<Statement> statements = new ArrayList<>();
      statements.add(new StringStatement("switch (item.getClass().getName()) {"));

      Set<String> seen = new HashSet<>();
      for (Property descendant : allDescendants) {
        ClassRef dunwraped = new ClassRefBuilder(
            (ClassRef) TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_OPTIONAL_OF)
                .apply(descendant.getTypeRef()))
            .withArguments(new TypeRef[0]).build();
        String className = dunwraped.getFullyQualifiedName();
        if (!seen.add(className) || !isBuildable(dunwraped)) {
          continue;
        }
        // use a string concat for the case key to prevent conversion of fully qualified names to short names
        String packageName = dunwraped.getPackageName();
        String classShortName = dunwraped.getName();

        ClassRef builderRef = TypeAs.BUILDER_REF.apply(dunwraped);
        statements.add(new StringStatement("case \"" + packageName + ".\"+\"" + classShortName
            + "\": return (VisitableBuilder<T, ?>)new " + builderRef + "(("
            + dunwraped + ") item);"));
      }
      statements.add(new StringStatement("}\n return (VisitableBuilder<T, ?>)builderOf(item);"));

      return new MethodBuilder().withNewModifiers().withProtected().withStatic()
          .endModifiers()
          .withParameters(Types.T)
          .addNewArgument().withName("item").withTypeRef(Types.OBJECT_REF).endArgument()
          .withReturnType(BuilderContextManager.getContext().getVisitableBuilderInterface().toReference(Types.T_REF))
          .withName("builder")
          .withNewBlock()
          .withStatements(statements)
          .endBlock().build();
    }
  });

  public static final Function<RichTypeDef, TypeDef> BUILDER = FunctionFactory.wrap(new Function<RichTypeDef, TypeDef>() {
    public TypeDef apply(final RichTypeDef item) {
      final boolean validationEnabled = item.hasAttribute(VALIDATION_ENABLED) ? item.getAttribute(VALIDATION_ENABLED) : false;
      final Modifier[] modifiers = item.isAbstract() ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      ClassRef itemRef = item.toInternalReference();
      ClassRef fluent = TypeAs.FLUENT_Q_REF.apply(itemRef);
      ClassRef fluentImplRef = TypeAs.FLUENT_REF.apply(itemRef);
      ClassRef builderRef = TypeAs.BUILDER_REF.apply(itemRef);
      ClassRef visitableBuilderRef = TypeAs.VISITABLE_BUILDER_REF.apply(itemRef);

      Optional<TypeDef> buildableInterface = item.getImplementsList().stream().map(GetDefinition::of)
          .filter(t -> t.getAnnotations().stream()
              .anyMatch(a -> a.getClassRef().getFullyQualifiedName().equals(Buildable.class.getName())))
          .findFirst();

      List<Method> constructors = new ArrayList<Method>();
      List<Method> basicConstructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();
      final List<Property> fields = new ArrayList<Property>();

      Property fluentProperty = new PropertyBuilder().withTypeRef(fluent).withName("fluent").build();

      fields.add(fluentProperty);

      Method emptyConstructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .withNewBlock()
          .addNewStringStatementStatement(hasDefaultConstructor(item) ? "this(new " + item.getName() + "());"
              : "this.fluent = this;")
          .endBlock().build();

      Method fluentConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers().addNewArgument()
          .withTypeRef(fluent).withName("fluent").and().withNewBlock()
          .addNewStringStatementStatement(hasDefaultConstructor(item) ? "this(fluent, new " + item.getName() + "());"
              : "this.fluent = fluent;")
          .endBlock().build();

      Method instanceAndFluentCosntructor = new MethodBuilder()
          .withNewModifiers().withPublic().endModifiers()
          .addNewArgument().withTypeRef(fluent).withName("fluent").and().addNewArgument().withTypeRef(itemRef)
          .withName("instance").and().withNewBlock()
          .addNewStringStatementStatement("this.fluent = fluent;")
          .addNewStringStatementStatement("fluent.copyInstance(instance);")
          .endBlock()
          .build();

      Method instanceConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers().addNewArgument()
          .withTypeRef(itemRef).withName("instance").and().withNewBlock()
          .addNewStringStatementStatement("this.fluent = this;")
          .addNewStringStatementStatement("this.copyInstance(instance);").endBlock()
          .build();

      basicConstructors.add(emptyConstructor);
      basicConstructors.add(fluentConstructor);
      basicConstructors.add(instanceAndFluentCosntructor);
      basicConstructors.add(instanceConstructor);

      buildableInterface.ifPresent(i -> {
        ClassRef buildableInterfaceRef = item.getImplementsList().stream()
            .filter(candidate -> candidate.getFullyQualifiedName().equals(i.getFullyQualifiedName())).findFirst()
            .orElseThrow(() -> new IllegalStateException(
                "Could not find reference for implemented interface:" + i.getFullyQualifiedName()));

        Method sourceInstanceAndFluentCosntructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .addNewArgument().withTypeRef(fluent).withName("fluent").and().addNewArgument().withTypeRef(buildableInterfaceRef)
            .withName("instance").and().withNewBlock().addAllToStatements(toInstanceConstructorBody(item, i, "fluent"))
            .endBlock()
            .build();

        Method sourceInstanceConstructor = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .addNewArgument()
            .withTypeRef(buildableInterfaceRef).withName("instance").and().withNewBlock()
            .addAllToStatements(toInstanceConstructorBody(item, i, "this")).endBlock()
            .build();

        basicConstructors.add(sourceInstanceAndFluentCosntructor);
        basicConstructors.add(sourceInstanceConstructor);
      });

      constructors.addAll(basicConstructors);

      Method build = new MethodBuilder().withModifiers(Modifiers.from(modifiers)).withReturnType(itemRef)
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
        Property validationEnabledProperty = new PropertyBuilder().withTypeRef(io.sundr.model.utils.Types.PRIMITIVE_BOOLEAN_REF)
            .withName("validationEnabled").build();

        fields.add(validationEnabledProperty);

        basicConstructors.stream().map(c -> new MethodBuilder(c).addNewArgument()
            .withTypeRef(io.sundr.model.utils.Types.PRIMITIVE_BOOLEAN_REF).withName("validationEnabled").and().withNewBlock()
            .addNewStringStatementStatement(
                c.getArguments().stream().map(Property::getName).collect(Collectors.joining(", ", "this(", ");")))
            .addNewStringStatementStatement("this.validationEnabled=validationEnabled;").endBlock().build())
            .forEach(constructors::add);

        ClassRef validatorRef = new ClassRefBuilder().withFullyQualifiedName("javax.validation.Validator").build();

        Property validatorProperty = new PropertyBuilder().withName("validator").withTypeRef(validatorRef).build();

        fields.add(validatorProperty);

        BuilderContext context = BuilderContextManager.getContext();
        if (context.isExternalvalidatorSupported()) {
          basicConstructors.stream().map(c -> new MethodBuilder(c).addNewArgument()
              .withTypeRef(validatorRef).withName("validator").and().withNewBlock()
              .addNewStringStatementStatement(
                  c.getArguments().stream().map(Property::getName).collect(Collectors.joining(", ", "this(", ");")))
              .addNewStringStatementStatement("this.validator=validator;")
              .addNewStringStatementStatement("this.validationEnabled = validator != null;").endBlock().build())
              .forEach(constructors::add);

          Method usingValidator = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
              .withReturnType(builderRef)
              .withName("usingValidator")
              .addNewArgument().withName("validator").withTypeRef(validatorRef).endArgument()
              .withNewBlock()
              .addNewStringStatementStatement("return new " + builderRef.getName() + "(this, validator);")
              .endBlock()
              .build();

          methods.add(usingValidator);
        }

        Method usingValidation = new MethodBuilder().withNewModifiers().withPublic().endModifiers()
            .withReturnType(builderRef)
            .withName("usingValidation")
            .withNewBlock()
            .addNewStringStatementStatement("return new " + builderRef.getName() + "(this, true);")
            .endBlock()
            .build();

        methods.add(usingValidation);
      }

      return BuilderContextManager.getContext().getDefinitionRepository()
          .register(new TypeDefBuilder()
              .withAnnotations()
              .withPackageName(item.getPackageName())
              .withName(item.getName() + "Builder")
              .withParameters(item.getParameters())
              .withExtendsList(fluentImplRef)
              .withImplementsList(visitableBuilderRef)
              .withNewModifiers().withPublic().endModifiers()
              .withProperties(fields)
              .withConstructors(constructors)
              .withMethods(methods).build());
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
                    builder.withNewBlock().withStatements(toBuild(TypeArguments.apply(editable), editable)).endBlock();
                  }
                }
              }).build();
        }
      });

  public static final Function<RichTypeDef, TypeDef> EDITABLE = FunctionFactory.wrap(new Function<RichTypeDef, TypeDef>() {
    public TypeDef apply(RichTypeDef item) {
      Modifier[] modifiers = item.isAbstract() ? new Modifier[] { Modifier.PUBLIC, Modifier.ABSTRACT }
          : new Modifier[] { Modifier.PUBLIC };

      ClassRef itemRef = item.toInternalReference();
      TypeDef editableType = TypeAs.EDITABLE.apply(item);
      final ClassRef builderRef = TypeAs.BUILDER_REF.apply(itemRef);

      List<Method> constructors = new ArrayList<Method>();
      List<Method> methods = new ArrayList<Method>();

      constructors.addAll(item.getConstructors().stream().filter(c -> !c.isPrivate())
          .map(c -> superConstructorOf(c, editableType)).collect(Collectors.toList()));

      Method edit = new MethodBuilder().withModifiers(Modifiers.from(modifiers)).withReturnType(builderRef).withName("edit")
          .withNewBlock().addToStatements(new StringStatement(new Supplier<String>() {
            @Override
            public String get() {
              return "return new " + builderRef.getName() + "(this);";
            }
          })).endBlock().build();

      methods.add(edit);

      //We need to treat the editable classes as buildables themselves.
      return AptContext.getContext().getDefinitionRepository()
          .register(BuilderContextManager.getContext().getBuildableRepository()
              .register(new TypeDefBuilder(editableType).withComments("Generated").withAnnotations()
                  .withModifiers(Modifiers.from(modifiers)).withConstructors(constructors).withMethods(methods)
                  .addToAttributes(BUILDABLE_ENABLED, true).addToAttributes(GENERATED, true) // We want to know that its a generated type...
                  .addToAttributes(IGNORE_PROPERTIES, item.getAttribute(IGNORE_PROPERTIES)) // We want to know that its a generated type...
                  .build()));
    }
  });

  public static final Function<RichTypeDef, TypeDef> POJO = FunctionFactory.wrap(new ToPojo());

  private static List<Statement> toInstanceConstructorBody(RichTypeDef clazz, TypeDef instance, String fluent) {
    Method constructor = findBuildableConstructor(clazz);
    List<Statement> statements = new ArrayList<Statement>();
    final String ref = fluent != null && !fluent.isEmpty() ? fluent : "this";

    //We may use a reference to fluent or we may use directly "this". So we need to check.
    if (fluent != null && !fluent.isEmpty()) {
      statements.add(new StringStatement("this.fluent = " + fluent + "; "));
    }

    if (!isAbstract(clazz.toReference()) && hasDefaultConstructor(clazz)) {
      statements.add(
          new StringStatement("instance = (instance != null ? instance : new " + clazz.getFullyQualifiedName() + "());\n"));
    }

    StringBuilder builder = new StringBuilder("if (instance != null) {\n");

    Set<String> constructorProperties = new HashSet<>();
    for (Property property : constructor.getArguments()) {
      Optional<Method> getter = Getter.findOptional(instance, property);
      getter.ifPresent(g -> {
        String cast = property.getTypeRef() instanceof TypeParamRef ? "(" + property.getTypeRef().toString() + ")" : "";
        constructorProperties.add(property.getNameCapitalized());
        builder.append("      ").append(ref).append(".with").append(property.getNameCapitalized())
            .append("(").append(cast).append("instance.").append(g.getName()).append("());\n");
      });

      // } else {
      //   throw new IllegalStateException("Could not find getter for property:" + property + " in class:" + clazz);
      // }
    }

    Predicate<Property> propertyFilter = isPropertyApplicable(clazz, false);
    clazz.getAllProperties().stream()
        .filter(propertyFilter)
        .filter(p -> !constructorProperties.contains(p.getNameCapitalized()))
        .filter(p -> Setter.hasOrInherits(clazz, p))
        .forEach(property -> {
          Optional<Method> optionalGetter = Getter.findOptional(instance, property);
          if (optionalGetter.isPresent()) {
            String withName = "with" + property.getNameCapitalized();
            builder.append("      ").append(ref).append(".").append(withName).append("(instance.")
                .append(optionalGetter.map(Method::getName).get()).append("());\n");
          }
        });

    statements.add(new StringStatement(builder.append("    }").toString()));
    return statements;
  }

  private static List<Statement> toBuild(final RichTypeDef item, final TypeDef instanceType) {
    Method constructor = findBuildableConstructor(item);
    List<Statement> statements = new ArrayList<Statement>();

    statements.add(new StringStatement(new StringBuilder()
        .append(instanceType.getName()).append(" buildable = new ").append(instanceType.getName()).append("(")
        .append(Strings.join(constructor.getArguments(), new Function<Property, String>() {
          public String apply(Property item) {
            return "fluent." + ToMethod.getterOrBuildMethodName(item) + "()";
          }
        }, ","))
        .append(");")
        .toString()));

    Predicate<Property> propertyFilter = isPropertyApplicable(item, false);
    item.getAllProperties().stream()
        .filter(propertyFilter)
        .filter(p -> Setter.hasOrInherits(item, p))
        .filter(p -> !constructor.getArguments().stream().anyMatch(a -> a.getName().equals(p.getName()))) //Exclude fields that are set via constructor!
        .forEach(property -> {
          Method setter = Setter.find(item, property);
          statements.add(new StringStatement(new StringBuilder("buildable.").append(setter.getName())
              .append("(fluent.").append(ToMethod.getterOrBuildMethodName(property)).append("());").toString()));
        });

    BuilderContext context = BuilderContextManager.getContext();
    final boolean validationEnabled = item.hasAttribute(VALIDATION_ENABLED) ? item.getAttribute(VALIDATION_ENABLED) : false;
    if (validationEnabled) {
      if (context.isExternalvalidatorSupported()) {
        statements.add(new StringStatement(
            "if (validationEnabled) {" + context.getBuilderPackage() + ".ValidationUtils.validate(buildable, validator);}"));
      } else {
        statements.add(new StringStatement(
            "if (validationEnabled) {" + context.getBuilderPackage() + ".ValidationUtils.validate(buildable);}"));
      }
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

  private static Predicate<Property> isPropertyApplicable(RichTypeDef item) {
    return isPropertyApplicable(item, true);
  }

  private static Predicate<Property> isPropertyApplicable(RichTypeDef item, boolean excludeBuildableSuperClassFields) {
    final Set<String> propertiesToIgnore = item.hasAttribute(IGNORE_PROPERTIES)
        ? new HashSet<>(Arrays.asList(item.getAttribute(IGNORE_PROPERTIES)))
        : new HashSet<>();

    final Map<String, Property> itemProperties = item.getProperties().stream()
        .collect(Collectors.toMap(Property::getName, p -> p));
    return property -> {
      if (property.isStatic()) {
        return false;
      }
      if (propertiesToIgnore.contains(property.getName())) {
        return false;
      }
      if (!hasBuildableConstructorWithArgument(item, property) && !Setter.hasOrInherits(item, property)) {
        return false;
      }

      boolean isInherited = !itemProperties.containsKey(property.getName());
      boolean isGeneric = property.hasAttribute(TypeArguments.ORIGINAL_TYPE_PARAMETER);
      boolean hasBuildableSuperClass = item.getExtendsList().stream().anyMatch(s -> isBuildable(s));

      // We should skip fields that originate from buildable superclasses, unless they are generic.
      // Generic fields need to be processed at the level they can be resolved to an actual type.
      if (excludeBuildableSuperClassFields && (isInherited && hasBuildableSuperClass && !isGeneric)) {
        return false;
      }
      return true;
    };
  }
}