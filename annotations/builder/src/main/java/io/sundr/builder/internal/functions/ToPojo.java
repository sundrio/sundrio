/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder.internal.functions;

import static io.sundr.builder.Constants.ADDITIONAL_BUILDABLES;
import static io.sundr.builder.Constants.ADDITIONAL_TYPES;
import static io.sundr.builder.Constants.ARRAYS;
import static io.sundr.builder.Constants.COLLECTORS;
import static io.sundr.builder.Constants.TO_STRING_ARRAY_SNIPPET;
import static io.sundr.builder.internal.functions.ClazzAs.BUILDER;
import static io.sundr.builder.internal.functions.ClazzAs.POJO;
import static io.sundr.builder.internal.utils.BuilderUtils.findBuildableConstructor;
import static io.sundr.model.Attributeable.ALSO_IMPORT;
import static io.sundr.model.Attributeable.DEFAULT_VALUE;
import static io.sundr.model.Attributeable.INIT;
import static io.sundr.model.utils.Types.modifiersToInt;
import static io.sundr.utils.Strings.loadResourceQuietly;
import static java.util.stream.Collectors.*;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.apt.AptContext;
import io.sundr.builder.TypedVisitor;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.Pojo;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.utils.BuilderUtils;
import io.sundr.model.AnnotationRef;
import io.sundr.model.AnnotationRefBuilder;
import io.sundr.model.AttributeKey;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Method;
import io.sundr.model.MethodBuilder;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.Property;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.Statement;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.Assignable;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.Collections;
import io.sundr.model.utils.Getter;
import io.sundr.model.utils.Types;
import io.sundr.utils.Strings;

public class ToPojo implements Function<TypeDef, TypeDef> {

  private static final String TO_STRING_ARRAY_TEXT = loadResourceQuietly(TO_STRING_ARRAY_SNIPPET);

  //A stack of variable names to be used for lambda expressions.
  //We need them because nested lambdas may clash.
  private static final Stack<String> variables = new Stack<String>() {
    {
      add("t");
      add("s");
      add("r");
      add("q");
      add("p");
      add("o");
      add("n");
      add("m");
      add("l");
      add("k");
      add("j");
      add("i");
    }
  };

  public TypeDef apply(TypeDef item) {

    List<Property> arguments = new CopyOnWriteArrayList<>();
    List<Property> fields = new CopyOnWriteArrayList<>();
    Map<String, Property> superClassFields = new HashMap<>();
    List<Method> getters = new CopyOnWriteArrayList<>();
    List<Method> additionalMethods = new ArrayList<>();

    List<Property> constructorArgs = new ArrayList<>();
    List<TypeDef> types = new ArrayList<TypeDef>();
    Types.visitParents(item, types);

    TypeDef superClass = null;
    List<ClassRef> extendsList = new ArrayList<>();
    List<ClassRef> implementsList = new ArrayList<>();
    List<ClassRef> additionalImports = new ArrayList<>();
    List<AnnotationRef> annotationRefs = new ArrayList<>();

    String pojoName = Strings.toPojoName(item.getName(), "Default", "");
    String relativePath = ".";

    AnnotationRef pojoRef = null;
    boolean enableStaticBuilder = true;
    boolean enableStaticAdapter = true;
    boolean enableStaticMapAdapter = false;
    boolean autobox = false;
    boolean initialize = false;
    boolean mutable = false;

    final List adapters = new ArrayList();

    for (AnnotationRef r : item.getAnnotations()) {
      if (r.getClassRef() != null) {
        if (r.getClassRef().getFullyQualifiedName().equals(Buildable.class.getTypeName())) {
          if (!annotationRefs.contains(r)) {
            annotationRefs.add(r);
          }
        }
        if (r.getClassRef().getFullyQualifiedName().equals(Pojo.class.getTypeName())) {
          pojoRef = r;
          Map<String, Object> params = r.getParameters();

          if (params.containsKey("mutable")) {
            mutable = Boolean.parseBoolean(String.valueOf(r.getParameters().getOrDefault("mutable", false)));
          }
          if (params.containsKey("autobox")) {
            autobox = Boolean.parseBoolean(String.valueOf(r.getParameters().getOrDefault("autobox", false)));
          }
          if (params.containsKey("initialize")) {
            initialize = Boolean.parseBoolean(String.valueOf(r.getParameters().getOrDefault("initialize", false)));
          }
          if (params.containsKey("name")) {
            pojoName = String.valueOf(r.getParameters().getOrDefault("name", pojoName));
          } else if (params.containsKey("prefix") || params.containsKey("suffix")) {
            String prefix = String.valueOf(r.getParameters().getOrDefault("prefix", ""));
            String suffix = String.valueOf(r.getParameters().getOrDefault("suffix", ""));
            pojoName = Strings.toPojoName(item.getName(), prefix, suffix);
          } else if (params.containsKey("relativePath")) {
            //When the package is different and there is no name clash, just use the same name unless explicitly specified.
            pojoName = item.getName();
          }

          if (params.containsKey("adapter")) {
            Object adapter = params.get("adapter");
            if (adapter != null && adapter.getClass().isArray()) {
              int length = Array.getLength(adapter);
              for (int i = 0; i < length; i++) {
                adapters.add(Array.get(adapter, i));
              }
            }
          }

          String superClassName = Types.toClassName(r.getParameters().getOrDefault("superClass", ""));
          if (!superClassName.isEmpty()) {
            superClassName = superClassName.replaceAll("\\.class$", "");
            superClass = DefinitionRepository.getRepository().getDefinition(superClassName);

            if (superClass == null) {
              BuilderContext context = BuilderContextManager.getContext();
              AptContext aptContext = AptContext.create(context.getElements(), context.getTypes(),
                  context.getDefinitionRepository());
              superClass = new TypeDefBuilder(
                  Adapters.adaptType(aptContext.getElements().getTypeElement(superClassName), aptContext.getAdapterContext()))
                      .build();

              BuilderContextManager.getContext().getDefinitionRepository().register(superClass);
              BuilderContextManager.getContext().getBuildableRepository().register(superClass);
            }
            if (superClass != null) {
              ClassRef superClassRef = superClass.toInternalReference();
              extendsList.add(superClassRef);
              BuilderContextManager.getContext().getBuildableRepository().register(GetDefinition.of(superClassRef));
              BuilderUtils.findBuildableReferences(superClassRef)
                  .stream()
                  .forEach(b -> BuilderContextManager.getContext().getBuildableRepository().register(GetDefinition.of(b)));

            }
          }
          if (item.isInterface()) {
            implementsList.add(item.toInternalReference());
          }
          Arrays.asList(r.getParameters().getOrDefault("interfaces", new Object[] {})).stream()
              .map(String::valueOf)
              .map(s -> s.replaceAll("\\.class$", ""))
              .map(n -> DefinitionRepository.getRepository().getDefinition(n))
              .filter(d -> d != null)
              .map(d -> d.toInternalReference())
              .forEach(ref -> implementsList.add(ref));

          if (params.containsKey("relativePath")) {
            relativePath = String.valueOf(r.getParameters().getOrDefault("relativePath", "."));
          }
          enableStaticBuilder = !"false".equals(String.valueOf(params.get("withStaticBuilderMethod")));
          enableStaticAdapter = !"false".equals(String.valueOf(params.get("withStaticAdapterMethod")));
          enableStaticMapAdapter = !"false".equals(String.valueOf(params.get("withStaticMapAdapterMethod")));
        }
      }
    }

    Set<TypeDef> additionalBuildables = new HashSet<>();
    Set<TypeDef> additionalTypes = new HashSet<>();

    for (TypeDef t : types) {
      if (superClass != null) {
        Method constructor = findBuildableConstructor(superClass);
        if (constructor != null) {
          for (Property p : constructor.getArguments()) {
            String name = Strings.toFieldName(p.getName());
            superClassFields.put(p.getName(), p);
          }
        }
      }

      for (Method method : t.getMethods()) {
        //We need all getters and all annotation methods.
        if (Getter.is(method) || t.equals(item)) {
          String name = Getter.propertyNameSafe(method);
          TypeRef returnType = method.getReturnType();
          if (autobox) {
            returnType = Types.box(returnType);
          }
          //If return type is an annotation also convert the annotation.
          if (method.getReturnType() instanceof ClassRef) {
            ClassRef ref = (ClassRef) method.getReturnType();
            if (GetDefinition.of(ref).isAnnotation()) {

              AnnotationRef inheritedPojoRef = (pojoRef != null ? new AnnotationRefBuilder(pojoRef)
                  : new AnnotationRefBuilder())
                      .removeFromParameters("name")
                      .removeFromParameters("superClass")
                      .removeFromParameters("interfaces")
                      .build();

              TypeDef p = hasPojoAnnotation(GetDefinition.of(ref))
                  ? POJO.apply(GetDefinition.of(ref))
                  : POJO.apply(new TypeDefBuilder(GetDefinition.of(ref))
                      .withAnnotations(annotationRefs)
                      .addToAnnotations(inheritedPojoRef)
                      .withAttributes(item.getAttributes())
                      .build());

              additionalBuildables.add(p);
              //create a reference and apply dimension
              returnType = new ClassRefBuilder(p.toInternalReference())
                  .withDimensions(ref.getDimensions())
                  .build();
            }
          }
          Map<AttributeKey, Object> fieldAttributes = new HashMap<>();
          if (method.hasAttribute(DEFAULT_VALUE)) {
            if (returnType.getDimensions() > 0 || (mutable && initialize)) {
              fieldAttributes.put(DEFAULT_VALUE, method.getAttribute(DEFAULT_VALUE));
              fieldAttributes.put(INIT,
                  getDefaultValue(new PropertyBuilder().withTypeRef(returnType).withAttributes(fieldAttributes).build()));
            }
          }
          arguments.add(new PropertyBuilder()
              .withName(name)
              .withTypeRef(returnType)
              .withModifiers(Types.modifiersToInt())
              .withAttributes(fieldAttributes)
              .build());

          if (!superClassFields.containsKey(Strings.toFieldName(name))) {
            Property field = new PropertyBuilder()
                .withName(Strings.toFieldName(name))
                .withTypeRef(returnType)
                .withModifiers(mutable ? Types.modifiersToInt(Modifier.PRIVATE)
                    : Types.modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
                .withAttributes(fieldAttributes)
                .build();

            Method getter = new MethodBuilder(Getter.forProperty(field))
                .withModifiers(modifiersToInt(Modifier.PUBLIC))
                .withNewBlock()
                .withStatements(new StringStatement("return this." + Strings.toFieldName(name) + ";"))
                .endBlock()
                .build();

            fields.add(field);
            getters.add(getter);
            if (field.getTypeRef().equals(Types.BOOLEAN_REF)) {
              Method primitiveGetter = new MethodBuilder(getter)
                  .withName("is" + getter.getName().replaceAll("^get", ""))
                  .withReturnType(Types.PRIMITIVE_BOOLEAN_REF)
                  .withNewBlock()
                  .withStatements(new StringStatement(
                      "return this." + Strings.toFieldName(name) + " != null &&  this." + Strings.toFieldName(name)
                          + ";"))
                  .endBlock()
                  .build();
              getters.add(primitiveGetter);
            }
          }
        }
      }
    }

    List<Statement> statements = new ArrayList<Statement>();
    if (superClass != null) {
      Method constructor = findBuildableConstructor(superClass);
      if (constructor != null) {
        constructorArgs.addAll(constructor.getArguments());
      }

      StringBuilder sb = new StringBuilder();
      sb.append("super(");
      sb.append(Strings.join(constructor.getArguments(), new Function<Property, String>() {
        @Override
        public String apply(Property item) {
          return Strings.toFieldName(item.getName());
        }
      }, ", "));
      sb.append(");");
      statements.add(new StringStatement(sb.toString()));
      for (Property p : fields) {
        statements.add(new StringStatement(fieldIntializer(p, initialize)));
      }
    } else

    {
      for (Property p : fields) {
        statements.add(new StringStatement(fieldIntializer(p, initialize)));
      }
    }

    List<Method> constructors = new ArrayList();

    Method emptyConstructor = new MethodBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .build();

    //We can't just rely on what getters are present in the superclass.
    //We NEED to make sure that the superclass constructor arguments are in place and then add everything else.
    for (Property p : arguments) {
      if (!constructorArgs.contains(p)) {
        constructorArgs.add(p);
      }
    }

    //We don't want to annotate the POJO as @Buildable, as this is likely to re-trigger the processor multiple times.
    //The processor instead explicitly generates fluent and builder for the new pojo.
    Method buildableConstructor = new MethodBuilder().withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withArguments(constructorArgs).withNewBlock().withStatements(statements).endBlock()
        .accept(new TypedVisitor<PropertyBuilder>() {
          @Override
          public void visit(PropertyBuilder b) {
            String name = b.getName();
            b.withName(Strings.toFieldName(name));
            //DEFAULT_VALUE is something that is available in Annotation types, but when the annotation becomes a Pojo
            //That piece of information is lost (as is the case with our super-classs). Let's work-around it.
            if (superClassFields.containsKey(name)) {
              Property f = superClassFields.get(name);
              if (f.getAttributes().containsKey(DEFAULT_VALUE)) {
                b.addToAttributes(DEFAULT_VALUE, f.getAttribute(DEFAULT_VALUE));
              }
            }
          }
        }).build();

    if (mutable) {
      constructors.add(emptyConstructor);
    }
    constructors.add(buildableConstructor);

    TypeDef generatedPojo = new TypeDefBuilder()
        .withPackageName(relativePackage(item.getPackageName(), relativePath))
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName(pojoName)
        .withAnnotations(annotationRefs)
        .withProperties(fields)
        .withConstructors(constructors)
        .withMethods(getters)
        .withImplementsList(implementsList)
        .withExtendsList(extendsList)
        .addToAttributes(item.getAttributes())
        .build();

    TypeDef pojoBuilder = BUILDER.apply(generatedPojo);

    if (enableStaticBuilder) {
      Method staticBuilder = new MethodBuilder()
          .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
          .withName(extendsList.isEmpty() ? "newBuilder" : "new" + pojoBuilder.getName()) //avoid clashes in case of inheritance
          .withReturnType(pojoBuilder.toInternalReference())
          .withNewBlock()
          .addNewStringStatementStatement("return new " + pojoBuilder.getFullyQualifiedName() + "();")
          .endBlock()
          .build();

      additionalMethods.add(staticBuilder);

      StringBuilder sb = new StringBuilder().append("return new " + pojoBuilder.getFullyQualifiedName() + "()");

      for (Method m : item.getMethods()) {
        if (m.hasAttribute(DEFAULT_VALUE)) {
          if (m.getReturnType().getDimensions() > 0) {
            continue;
          }

          String defaultValue = getDefaultValue(m);
          if (defaultValue == null || defaultValue.trim().isEmpty() || defaultValue.equals("\"\"")
              || defaultValue.equals("null")) {
            continue;
          }

          sb.append(
              ".with" + Strings.capitalizeFirst(Strings.toFieldName(m.getName())) + "(" + defaultValue + ")");
        }
      }
      sb.append(";");

      Method staticBuilderFromDefaults = new MethodBuilder()
          .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
          .withName(extendsList.isEmpty() ? "newBuilderFromDefaults" : "new" + pojoBuilder.getName() + "FromDefaults") //avoid clashes in case of inheritance
          .withReturnType(pojoBuilder.toInternalReference())
          .withNewBlock()
          .addNewStringStatementStatement(sb.toString())
          .endBlock()
          .build();

      additionalMethods.add(staticBuilderFromDefaults);
    }

    Method staticAdapter = new MethodBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("adapt")
        .addNewArgument()
        .withName("instance")
        .withTypeRef(item.toInternalReference())
        .endArgument()
        .withReturnType(generatedPojo.toInternalReference())
        .withNewBlock()
        .addNewStringStatementStatement("return newBuilder(instance).build();")
        .endBlock()
        .build();

    Method staticAdaptingBuilder = new MethodBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("newBuilder")
        .addNewArgument()
        .withName("instance")
        .withTypeRef(item.toInternalReference())
        .endArgument()
        .withReturnType(pojoBuilder.toInternalReference())
        .withNewBlock()
        .addToStatements(
            new StringStatement(() -> "return " + convertReference("instance", item, generatedPojo, pojoBuilder) + ";"))
        .endBlock()
        .build();

    Method staticMapAdapter = new MethodBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("adapt")
        .addNewArgument()
        .withName("map")
        .withTypeRef(Collections.MAP.toUnboundedReference())
        .endArgument()
        .withReturnType(generatedPojo.toInternalReference())
        .withNewBlock()
        .addToStatements(new StringStatement(() -> "return " + convertMap("map", item, generatedPojo) + ";"))
        .endBlock()
        .build();

    Method staticMapAdaptingBuilder = new MethodBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("newBuilder")
        .addNewArgument()
        .withName("map")
        .withTypeRef(Collections.MAP.toUnboundedReference())
        .endArgument()
        .withReturnType(pojoBuilder.toInternalReference())
        .withNewBlock()
        .addToStatements(new StringStatement(() -> "return " + convertMap("map", item, generatedPojo, pojoBuilder) + ";"))
        .endBlock()
        .build();

    Method staticToStringArray = new MethodBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("toStringArray")
        .addNewArgument()
        .withName("o")
        .withTypeRef(Types.OBJECT.toInternalReference())
        .endArgument()
        .withReturnType(Types.STRING_REF.withDimensions(1))
        .withNewBlock()
        .addToStatements(new StringStatement(TO_STRING_ARRAY_TEXT))
        .endBlock()
        .build();

    if (enableStaticAdapter &&

        hasArrayFields(item)) {
      item.getMethods()
          .stream()
          .filter(m -> m.getReturnType() instanceof ClassRef && ((ClassRef) m.getReturnType()).getDimensions() > 0)
          .findAny().ifPresent(m -> {
            additionalImports.add(ARRAYS);
            additionalImports.add(COLLECTORS);
          });
      additionalMethods.add(staticAdapter);
      additionalMethods.add(staticAdaptingBuilder);
      additionalMethods.add(staticMapAdapter);
      if (enableStaticMapAdapter) {
        additionalMethods.add(staticMapAdaptingBuilder);
      }
    }

    Method equals = new MethodBuilder()
        .withModifiers(Types.modifiersToInt(Modifier.PUBLIC))
        .withReturnType(Types.PRIMITIVE_BOOLEAN_REF)
        .addNewArgument().withName("o").withTypeRef(Types.OBJECT.toReference()).endArgument()
        .withName("equals")
        .withNewBlock()
        .withStatements(BuilderUtils.toEquals(generatedPojo, fields))
        .endBlock()
        .build();

    Method hashCode = new MethodBuilder()
        .withModifiers(Types.modifiersToInt(Modifier.PUBLIC))
        .withReturnType(Types.PRIMITIVE_INT_REF)
        .withName("hashCode")
        .withNewBlock()
        .withStatements(BuilderUtils.toHashCode(fields))
        .endBlock()
        .build();

    additionalMethods.add(equals);
    additionalMethods.add(hashCode);

    for (Object o : adapters) {
      if (o instanceof AnnotationRef) {
        AnnotationRef r = (AnnotationRef) o;
        String name = String.valueOf(r.getParameters().getOrDefault("name", ""));
        String prefix = String.valueOf(r.getParameters().getOrDefault("prefix", ""));
        String suffix = String.valueOf(r.getParameters().getOrDefault("suffix", ""));
        String mapperRelativePath = "";
        boolean enableMapAdapter = !"false"
            .equals(String.valueOf(r.getParameters().getOrDefault("withMapAdapterMethod", "false")));
        List<Method> adapterMethods = new ArrayList<>();

        if (Strings.isNullOrEmpty(name) && Strings.isNullOrEmpty(prefix) && Strings.isNullOrEmpty(suffix)) {
          suffix = "Adapter";
        }

        if (r.getParameters().containsKey("relativePath")) {
          mapperRelativePath = String.valueOf(r.getParameters().getOrDefault("relativePath", "."));
        }

        String adapterPackage = relativePackage(item.getPackageName(), mapperRelativePath);

        List<ClassRef> adapterImports = new ArrayList<>();
        adapterImports.add(Collections.LIST.toInternalReference());

        if (hasArrayFields(item)) {
          adapterImports.add(ARRAYS);
          adapterImports.add(COLLECTORS);
        }
        List<ClassRef> generatedRefs = new ArrayList<>();
        Types.allProperties(generatedPojo).stream().map(i -> i.getTypeRef()).filter(i -> i instanceof ClassRef)
            .forEach(i -> populateReferences((ClassRef) i, generatedRefs));
        adapterImports.addAll(generatedRefs);
        adapterImports.add(TypeAs.SHALLOW_BUILDER.apply(generatedPojo).toInternalReference());

        Types.allProperties(generatedPojo).stream()
            .filter(p -> p.getTypeRef() instanceof ClassRef)
            .map(p -> (ClassRef) p.getTypeRef())
            .filter(c -> !adapterPackage.equals(GetDefinition.of(c).getPackageName()))
            .collect(toList());

        adapterImports.addAll(Types.allProperties(generatedPojo).stream()
            .filter(p -> p.getTypeRef() instanceof ClassRef)
            .map(p -> (ClassRef) p.getTypeRef())
            .filter(c -> !adapterPackage.equals(GetDefinition.of(c).getPackageName()))
            .collect(toList()));

        adapterMethods.add(staticAdapter);
        adapterMethods.add(staticAdaptingBuilder);
        adapterMethods.add(staticMapAdapter);
        if (enableMapAdapter) {
          adapterMethods.add(staticMapAdaptingBuilder);
        }
        adapterMethods.add(staticToStringArray);

        TypeDef mapper = new TypeDefBuilder()
            .withComments("Generated")
            .withModifiers(modifiersToInt(Modifier.PUBLIC))
            .withPackageName(adapterPackage)
            .withName(!Strings.isNullOrEmpty(name) ? name : Strings.toPojoName(generatedPojo.getName(), prefix, suffix))
            .withMethods(adapterMethods)
            .addToAttributes(ALSO_IMPORT, adapterImports)
            .build();

        additionalTypes.add(mapper);
      }
    }

    return DefinitionRepository.getRepository().register(new TypeDefBuilder(generatedPojo)
        .withComments("Generated")
        .addAllToMethods(additionalMethods)
        .addToAttributes(ALSO_IMPORT, additionalImports)
        .addToAttributes(ADDITIONAL_BUILDABLES, additionalBuildables)
        .addToAttributes(ADDITIONAL_TYPES, additionalTypes)
        .build());
  }

  private static boolean hasPojoAnnotation(TypeDef typeDef) {
    return typeDef.getAnnotations().stream()
        .filter(a -> GetDefinition.of(a.getClassRef()).getFullyQualifiedName().equals(Pojo.class.getTypeName()))
        .findAny().isPresent();
  }

  private static String relativePackage(String pkg, String relativePath) {
    if (relativePath == null || relativePath.isEmpty() || relativePath.equals(".")) {
      return pkg;
    }
    Path pkgPath = Paths.get(pkg.replaceAll(Pattern.quote("."), "/"));
    String newPath = pkgPath.resolve(relativePath).normalize().toString();

    return newPath.replaceAll("/|\\\\", ".").replaceAll("\\.$", "");
  }

  /**
   * Converts a reference from the source type, to the target type by using the builder.
   * 
   * @param ref The ref.
   * @param source The source type of the reference..
   * @param target The target type.
   * @param targetBuilder The target type builder.
   * @return
   */
  private static String convertReference(String ref, TypeDef source, TypeDef target, TypeDef targetBuilder) {
    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(targetBuilder.getName()).append("(").append(convertReference(ref, source, target)).append(")");
    return sb.toString();
  }

  /**
   * Converts a reference from the source type, to the target.
   * 
   * @param ref The ref.
   * @param source The source type of the reference..
   * @param target The target type.
   * @return
   */
  private static String convertReference(String ref, TypeDef source, TypeDef target) {
    Method ctor = BuilderUtils.findBuildableConstructor(target);
    String arguments = ctor.getArguments().stream()
        .map(p -> readProperty(ref, source, p))
        .collect(joining(",\n            "));

    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(target.getFullyQualifiedName()).append("(").append(arguments).append(")");
    return sb.toString();
  }

  /**
   * Returns the string representation of the code that given a reference of the specified type, reads the specified property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readProperty(String ref, TypeDef source, Property property) {
    TypeRef propertyTypeRef = property.getTypeRef();
    Method getter = getterOf(source, property);
    if (getter == null) {
      return "null";
    }

    TypeRef getterTypeRef = getter.getReturnType();
    if (propertyTypeRef.getDimensions() == getterTypeRef.getDimensions()
        && Assignable.isAssignable(propertyTypeRef).from(getterTypeRef)) {
      return readObjectProperty(ref, source, property);
    }

    if (property.getTypeRef().getDimensions() > 0) {
      return readArrayProperty(ref, source, property);
    }
    if (property.getTypeRef() instanceof ClassRef && GetDefinition.of((ClassRef) getterTypeRef).isAnnotation()) {
      return readAnnotationProperty(ref + "." + getter.getName() + "()", GetDefinition.of((ClassRef) getterTypeRef), property);
    }
    return readObjectProperty(ref, source, property);
  }

  /**
   * Returns the string representation of the code that reads an object property from a reference using a getter.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readObjectProperty(String ref, TypeDef source, Property property) {
    return ref + "." + getterOf(source, property).getName() + "()";
  }

  /**
   * Returns the string representation of the code that reads an array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readArrayProperty(String ref, TypeDef source, Property property) {
    TypeRef typeRef = property.getTypeRef();
    if (typeRef instanceof ClassRef) {
      //TODO: This needs further breakdown, to cover edge cases.
      return readObjectArrayProperty(ref, source, property);
    }

    if (typeRef instanceof PrimitiveRef) {
      return readPrimitiveArrayProperty(ref, source, property);
    }
    throw new IllegalStateException("Property should be either an object or a primitive.");
  }

  /**
   * Returns the string representation of the code that reads an object array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readObjectArrayProperty(String ref, TypeDef source, Property property) {
    StringBuilder sb = new StringBuilder();
    Method getter = getterOf(source, property);
    TypeRef getterTypeRef = getter.getReturnType();
    TypeRef propertyTypeRef = property.getTypeRef();
    if (propertyTypeRef instanceof ClassRef && getterTypeRef instanceof ClassRef) {
      String nextRef = variables.pop();
      try {
        TypeDef propertyType = GetDefinition.of((ClassRef) propertyTypeRef);
        TypeDef getterType = GetDefinition.of((ClassRef) getterTypeRef);
        sb.append("Arrays.asList(")
            .append(ref).append(".").append(getter.getName()).append("())")
            .append(".stream().map(").append(nextRef).append(" ->").append(convertReference(nextRef, getterType, propertyType))
            .append(")")
            .append(".collect(Collectors.toList()).toArray(new " + propertyType.getFullyQualifiedName() + "[0])");
      } finally {
        variables.push(nextRef);
      }
      return sb.toString();
    }
    throw new IllegalArgumentException("Expected an object property and a matching object getter!!");
  }

  /**
   * Returns the string representation of the code that reads a primitive array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readPrimitiveArrayProperty(String ref, TypeDef source, Property property) {
    StringBuilder sb = new StringBuilder();
    Method getter = getterOf(source, property);
    sb.append("Arrays.asList(")
        .append(ref).append(".").append(getter.getName()).append("())")
        .append(".stream()").append(".collect(Collectors.toList())).toArray(new " + getter.getReturnType().toString() + "[])");
    return sb.toString();
  }

  /**
   * Returns the string representation of the code that reads a primitive array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readAnnotationProperty(String ref, TypeDef source, Property property) {
    return convertReference(ref, source, GetDefinition.of((ClassRef) property.getTypeRef()));
  }

  //
  //
  // Map references
  //
  //

  /**
   * Converts a map describing the source type, to the target type by using the builder.
   * 
   * @param ref The ref.
   * @param source The source type of the reference (e.g. the annotation).
   * @param target The target type (e.g. the generated pojo).
   * @param targetBuilder The target type builder (e.g. the pojo builder.
   * @return
   */
  private static String convertMap(String ref, TypeDef source, TypeDef target, TypeDef targetBuilder) {
    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(targetBuilder.getName()).append("(").append(convertMap(ref, source, target)).append(")");
    return sb.toString();
  }

  /**
   * Converts a map describing the source type, to the target.
   * 
   * @param ref The ref.
   * @param source The source type of the reference (e.g. the annotation).
   * @param target The target type (e.g. the generated pojo).
   * @return
   */
  private static String convertMap(String ref, TypeDef source, TypeDef target) {
    Method ctor = BuilderUtils.findBuildableConstructor(target);
    String arguments = ctor.getArguments().stream()
        .map(p -> readMapValue(ref, source, p))
        .collect(joining(",\n", "\n", ""));

    StringBuilder sb = new StringBuilder();
    sb.append("new ").append(target.getFullyQualifiedName()).append("(").append(arguments).append(")");
    return sb.toString();
  }

  /**
   * Returns the string representation of the code that given a reference of the specified type, reads the specified property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readMapValue(String ref, TypeDef source, Property property) {
    TypeRef propertyTypeRef = property.getTypeRef();
    Method getter = getterOf(source, property);
    if (getter == null) {
      return "null";
    }

    TypeRef getterTypeRef = getter.getReturnType();
    if (propertyTypeRef.getDimensions() == getterTypeRef.getDimensions()
        && Assignable.isAssignable(propertyTypeRef).from(getterTypeRef)) {
      return readObjectValue(ref, source, property);
    }

    if (property.getTypeRef().getDimensions() > 0) {
      return readArrayValue(ref, source, property);
    }

    if (getterTypeRef instanceof ClassRef) {
      TypeDef getterTypeDef = GetDefinition.of((ClassRef) getterTypeRef);
      if (getterTypeDef.isEnum()) {
        return readEnumValue(ref, source, property);
      }
      if (getterTypeDef.isAnnotation()) {
        return readAnnotationValue("((Map)(" + ref + " instanceof Map ? ((Map)" + ref + ").get(\""
            + getterOf(source, property).getName() + "\") : " + getDefaultValue(property) + "))",
            GetDefinition.of((ClassRef) getterTypeRef), property);
      }
    }
    return readObjectValue(ref, source, property);
  }

  /**
   * Returns the string representation of the code that reads an object property from a reference using a getter.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readObjectValue(String ref, TypeDef source, Property property) {
    TypeRef propertyRef = property.getTypeRef();
    if (propertyRef instanceof ClassRef) {
      ClassRef classRef = (ClassRef) propertyRef;
      if (GetDefinition.of(classRef).isEnum()) {
        return readEnumValue(ref, source, property);
      }
      if (propertyRef.getDimensions() > 0) {
        return readObjectArrayValue(ref, source, property);
      }
    } else if (propertyRef instanceof PrimitiveRef) {
      return readPrimitiveValue(ref, source, property);
    }
    return indent(ref) + "(" + property.getTypeRef().toString() + ")(" + ref + " instanceof Map ? ((Map)" + ref
        + ").getOrDefault(\"" + getterOf(source, property).getName() + "\", " + getDefaultValue(property) + ") : "
        + getDefaultValue(property) + ")";
  }

  /**
   * Returns the string representation of the code that reads an enum property from a reference using a getter.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readPrimitiveValue(String ref, TypeDef source, Property property) {
    String dv = getDefaultValue(property);
    String key = getterOf(source, property).getName();
    String type = property.getTypeRef().toString();
    TypeRef propertyRef = property.getTypeRef();
    ClassRef boxed = (ClassRef) TypeAs.BOXED_OF.apply(propertyRef);
    String parse = TypeAs.PARSER_OF.apply(propertyRef);

    String boxedName = boxed.getFullyQualifiedName();
    if (parse != null) {
      return indent(ref) + boxedName + "." + parse + "(String.valueOf(" + ref + " instanceof Map ? ((Map)" + ref
          + ").getOrDefault(\"" + key + "\",\"" + dv + "\") : \"" + dv + "\"))";
    } else {
      return indent(ref) + "(" + type + ")(" + ref + " instanceof Map ? ((Map)" + ref
          + ").getOrDefault(\"" + key + "\", " + dv + ") : " + dv + ")";
    }
  }

  /**
   * Returns the string representation of the code that reads an enum property from a reference using a getter.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readEnumValue(String ref, TypeDef source, Property property) {
    String dv = getDefaultValue(property);
    String key = getterOf(source, property).getName();
    String type = property.getTypeRef().toString();

    if (dv != null && dv.contains(".")) {
      dv = dv.substring(dv.lastIndexOf(".") + 1);
    }
    if (property.hasAttribute(DEFAULT_VALUE)) {
      return indent(ref) + property.getTypeRef().toString() + ".valueOf(String.valueOf(" + ref + " instanceof Map ? ((Map)"
          + ref + ").getOrDefault(\"" + getterOf(source, property).getName() + "\",\"" + dv + "\") : \"" + dv + "\"))";
    } else {
      return indent(ref) + "(" + type + ")(" + ref + " instanceof Map ? ( ((Map)" + ref + ").getOrDefault(\"" + key
          + "\", null) != null ? " + type + ".valueOf(String.valueOf(((Map)" + ref + ").getOrDefault(\"" + key
          + "\", null))) : null ) : null)";
    }
  }

  /**
   * Returns the string representation of the code that reads an array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readArrayValue(String ref, TypeDef source, Property property) {
    TypeRef typeRef = property.getTypeRef();
    if (typeRef instanceof ClassRef) {
      //TODO: This needs further breakdown, to cover edge cases.
      return readObjectArrayValue(ref, source, property);
    }

    if (typeRef instanceof PrimitiveRef) {
      return readPrimitiveArrayValue(ref, source, property);
    }
    throw new IllegalStateException("Property should be either an object or a primitive.");
  }

  /**
   * Returns the string representation of the code that reads an object array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readObjectArrayValue(String ref, TypeDef source, Property property) {
    StringBuilder sb = new StringBuilder();
    Method getter = getterOf(source, property);
    TypeRef getterTypeRef = getter.getReturnType();
    TypeRef propertyTypeRef = property.getTypeRef();
    if (propertyTypeRef instanceof ClassRef && getterTypeRef instanceof ClassRef) {
      String nextRef = variables.pop();
      try {
        TypeDef propertyType = GetDefinition.of((ClassRef) propertyTypeRef);
        TypeDef getterType = GetDefinition.of((ClassRef) getterTypeRef);
        if (Types.STRING.equals(getterType)) {
          sb.append(ref + " instanceof Map ? toStringArray(((Map)" + ref + ").get(\"" + getter.getName()
              + "\")) : toStringArray(" + ref + ")");
        } else {
          sb.append(indent(ref)).append("Arrays.stream(");
          sb.append("(Map[])(" + ref + " instanceof Map ? ((Map)" + ref + ").getOrDefault(\"" + getter.getName()
              + "\" , new Map[0]) : new Map[0]))");
          sb.append(".map(").append(nextRef).append(" ->").append(convertMap(nextRef, getterType, propertyType)).append(")");
          sb.append(".toArray(size-> new " + propertyType.getFullyQualifiedName() + "[size])");
        }
      } finally {
        variables.push(nextRef);
      }
      return sb.toString();
    }
    throw new IllegalArgumentException("Expected an object property and a matching object getter!!");
  }

  /**
   * Returns the string representation of the code that reads a primitive array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readPrimitiveArrayValue(String ref, TypeDef source, Property property) {
    StringBuilder sb = new StringBuilder();
    Method getter = getterOf(source, property);
    sb.append(indent(ref))
        .append("Arrays.stream(")
        .append("(" + property.getTypeRef().toString() + ")(" + ref + " instanceof Map ? ((Map)" + ref + ").getOrDefault(\""
            + getterOf(source, property).getName() + "\" , " + getDefaultValue(property) + ") : " + getDefaultValue(property)
            + ")")
        .append(".toArray(size -> new " + getter.getReturnType().toString() + "[size])");
    return sb.toString();
  }

  /**
   * Returns the string representation of the code that reads a primitive array property.
   * 
   * @param ref The reference.
   * @param source The type of the reference.
   * @param property The property to read.
   * @return The code.
   */
  private static String readAnnotationValue(String ref, TypeDef source, Property property) {
    return convertMap(ref, source, GetDefinition.of((ClassRef) property.getTypeRef()));
  }

  private void populateReferences(ClassRef ref, List<ClassRef> refs) {
    if (!refs.contains(ref) && !Types.isJdkType(ref)) {
      refs.add(ref);
      Types.allProperties(GetDefinition.of(ref)).stream()
          .filter(p -> p.getTypeRef() instanceof ClassRef)
          .forEach(p -> populateReferences((ClassRef) p.getTypeRef(), refs));
    }
  }

  private static boolean hasArrayFields(TypeDef item) {
    return item.getMethods()
        .stream()
        .filter(m -> m.getReturnType() instanceof ClassRef && ((ClassRef) m.getReturnType()).getDimensions() > 0)
        .findAny().isPresent();
  }

  private static final Method getterOf(TypeDef source, Property property) {
    Method result = source.getMethods().stream()
        .filter(m -> m.isPublic() && m.getArguments().size() == 0 && Getter.propertyNameSafe(m).equals(property.getName()))
        .findFirst()
        .orElse(null);
    return result;
  }

  private static String fieldIntializer(Property p, boolean initialize) {
    if ((initialize && p.hasAttribute(DEFAULT_VALUE) && (p.getTypeRef() instanceof ClassRef)
        || p.getTypeRef().getDimensions() > 0)) {
      return "this." + p.getName() + " = " + p.getName() + " != null ? " + p.getName() + " : " + getDefaultValue(p) + ";";
    } else {
      return "this." + p.getName() + " = " + p.getName() + ";";
    }
  }

  private static String getDefaultValue(Attributeable attributeable) {
    Object value = attributeable.getAttribute(DEFAULT_VALUE);
    String stringVal = value != null ? String.valueOf(value) : null;

    TypeRef typeRef = null;

    if (attributeable instanceof Method) {
      typeRef = ((Method) attributeable).getReturnType();
    } else if (attributeable instanceof Property) {
      typeRef = ((Property) attributeable).getTypeRef();
    } else {
      throw new IllegalArgumentException("Method 'getDefaultValue' accepts Property or Method as argument!");
    }

    if (typeRef.getDimensions() > 0) {
      StringBuilder sb = new StringBuilder();
      if (typeRef instanceof PrimitiveRef) {
        sb.append(((PrimitiveRef) typeRef).getName());
      } else if (typeRef instanceof ClassRef) {
        sb.append("new ").append(((ClassRef) typeRef).getFullyQualifiedName());
      }
      for (int d = 0; d < typeRef.getDimensions(); d++) {
        if (value == null || String.valueOf(value).isEmpty()) {
          sb.append("[0]");
        } else {
          sb.append("[]");
        }
      }
      return sb.toString();
    }
    if (Types.STRING_REF.equals(typeRef) && value != null && !String.valueOf(value).startsWith("\"")) {
      return "\"" + value + "\"";
    } else if (value instanceof Element) {
      Element element = (Element) value;
      if (element.getKind() == ElementKind.ENUM_CONSTANT) {
        return element.getEnclosingElement() + "." + element.getSimpleName();
      }
    } else if (stringVal != null && stringVal.startsWith("@")) {
      String annotationFQCN = stringVal.substring(1);
      TypeDef annotationDef = DefinitionRepository.getRepository().getDefinition(annotationFQCN);
      if (annotationDef != null) {
        TypeDef pojoDef = ClazzAs.POJO.apply(annotationDef);

        Optional<AnnotationRef> pojoAnnotation = getPojoAnnotation(pojoDef);
        Optional<Method> builderFromDefaults = getBuilderFromDefaults(pojoDef);
        if (pojoAnnotation.isPresent() && builderFromDefaults.isPresent()) {
          return pojoDef.getFullyQualifiedName() + "." + builderFromDefaults.get().getName() + "().build()";
        } else if (BuilderUtils.hasDefaultConstructor(pojoDef)) {
          return "new " + pojoDef.getFullyQualifiedName() + "()";
        }
      }
      return "null";
    } else if (stringVal == null && typeRef instanceof PrimitiveRef) {
      if (((PrimitiveRef) typeRef).getName().equals("boolean")) {
        return "false";
      } else {
        return "0";
      }
    }
    return stringVal;
  }

  public static Optional<AnnotationRef> getPojoAnnotation(TypeDef typeDef) {
    return typeDef.getAnnotations().stream()
        .filter(a -> a.getClassRef().getFullyQualifiedName().equals("io.sundr.builder.annotations.Pojo"))
        .findFirst();
  }

  public static Optional<Method> getBuilderFromDefaults(TypeDef typeDef) {
    return typeDef.getMethods().stream()
        .filter(m -> m.getName().startsWith("new") && m.getName().endsWith("BuilderFromDefaults"))
        .findFirst();
  }

  private static String indent(String ref) {
    return Arrays.stream(ref.split("\\.")).map(s -> "    ").collect(Collectors.joining("", "           ", ""));
  }
}
