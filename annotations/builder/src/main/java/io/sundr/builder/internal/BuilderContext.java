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

package io.sundr.builder.internal;

import static io.sundr.builder.Constants.INLINEABLE;
import static io.sundr.model.Attributeable.ALSO_IMPORT;
import static io.sundr.model.utils.Types.CLASS;
import static io.sundr.model.utils.Types.STRING_REF;
import static io.sundr.model.utils.Types.TYPE;
import static io.sundr.model.utils.Types.modifiersToInt;
import static io.sundr.model.utils.Types.newTypeParamRef;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import io.sundr.adapter.apt.AptContext;
import io.sundr.builder.Visitor;
import io.sundr.builder.annotations.Inline;
import io.sundr.model.Attributeable;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Kind;
import io.sundr.model.StringStatement;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.VoidRef;
import io.sundr.model.WildcardRef;
import io.sundr.model.WildcardRef.BoundKind;
import io.sundr.model.WildcardRefBuilder;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.Collections;
import io.sundr.model.visitors.ReplacePackage;

public class BuilderContext {

  private final Elements elements;
  private final Types types;
  private final AptContext aptContext;

  private final TypeDef visitorInterface;
  private final TypeDef typedVisitorInterface;
  private final TypeDef pathAwareVisitorClass;
  private final TypeDef fluentInterface;
  private final TypeDef builderInterface;
  private final TypeDef nestedInterface;
  private final TypeDef editableInterface;
  private final TypeDef visitableInterface;
  private final TypeDef visitableBuilderInterface;
  private final TypeDef visitableMapClass;
  private final TypeDef inlineableBase;
  private final TypeDef validationUtils;
  private final TypeDef baseFluentClass;
  private final Boolean generateBuilderPackage;
  private final Boolean validationEnabled;
  private final Boolean externalValidatorSupported;
  private final String builderPackage;
  private final Inline[] inlineables;
  private final BuildableRepository buildableRepository;

  public BuilderContext(Elements elements, Types types, Boolean generateBuilderPackage, Boolean validationEnabled,
      String builderPackage, Inline... inlineables) {
    this.elements = elements;
    this.types = types;
    this.validationEnabled = validationEnabled;
    this.aptContext = AptContext.create(elements, types, DefinitionRepository.getRepository());
    this.generateBuilderPackage = generateBuilderPackage;
    this.builderPackage = builderPackage;
    this.inlineables = inlineables;

    buildableRepository = new BuildableRepository();

    TypeParamDef T = new TypeParamDefBuilder()
        .withName("T")
        .build();

    TypeParamDef V = new TypeParamDefBuilder()
        .withName("V")
        .build();

    TypeParamDef F = new TypeParamDefBuilder()
        .withName("F")
        .build();

    TypeParamDef P = new TypeParamDefBuilder()
        .withName("P")
        .build();

    TypeDef consumerInterface = new TypeDefBuilder(TypeDef.forName(Consumer.class.getName()))
        .withParameters(T)
        .build();

    TypeDef functionalInterfaceType = TypeDef.forName(FunctionalInterface.class.getName());

    visitorInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .addNewAnnotation()
        .withClassRef(functionalInterfaceType.toInternalReference())
        .endAnnotation()
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Visitor")
        .withParameters(T)
        .addNewMethod()
        .withName("visit")
        .addNewArgument()
        .withName("element")
        .withTypeRef(T.toReference())
        .endArgument()
        .withReturnType(new VoidRef())
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    builderInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Builder")
        .withParameters(T)
        .addNewAnnotation()
        .withClassRef(ClassRef.forName(FunctionalInterface.class.getName()))
        .endAnnotation()
        .addNewMethod()
        .withName("build")
        .withReturnType(T.toReference())
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    typedVisitorInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.ABSTRACT))
        .withKind(Kind.CLASS)
        .withPackageName("io.sundr.builder")
        .withName("TypedVisitor")
        .withParameters(V)
        .withImplementsList(visitorInterface.toReference(V.toReference()))

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("getType")
        .withReturnType(CLASS.toReference(V.toReference()))
        .withNewBlock()
        .addNewStringStatementStatement("return (Class<V>) getTypeArguments(TypedVisitor.class, getClass()).get(0);")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("getClass")
        .withReturnType(CLASS.toReference(new WildcardRef()))
        .addNewArgument()
        .withName("type")
        .withTypeRef(TYPE.toInternalReference())
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "if (type instanceof Class) {return (Class) type;} else if (type instanceof ParameterizedType) {return getClass(((ParameterizedType) type).getRawType());} else if (type instanceof GenericArrayType) {Type componentType = ((GenericArrayType) type).getGenericComponentType(); Class<?> componentClass = getClass(componentType); if (componentClass != null) {return Array.newInstance(componentClass, 0).getClass();} else {return null;}} else {return null;}")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withParameters(T)
        .withName("getTypeArguments")
        .withReturnType(Collections.LIST.toReference(CLASS.toReference()))
        .addNewArgument()
        .withTypeRef(CLASS.toReference(T.toReference()))
        .withName("baseClass")
        .endArgument()
        .addNewArgument()
        .withTypeRef(CLASS.toReference(new WildcardRefBuilder().withBounds(T.toReference()).build()))
        .withName("childClass")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "    Map<Type, Type> resolvedTypes = new LinkedHashMap<>();" + "\n" +
                "    Type type = childClass;" + "\n" +
                "    // start walking up the inheritance hierarchy until we hit baseClass" + "\n" +
                "    while (!getClass(type).equals(baseClass)) {" + "\n" +
                "      if (type instanceof Class) {" + "\n" +
                "        // there is no useful information for us in raw types, so just keep going." + "\n" +
                "        type = ((Class) type).getGenericSuperclass();" + "\n" +
                "      } else {" + "\n" +
                "        ParameterizedType parameterizedType = (ParameterizedType) type;" + "\n" +
                "        Class<?> rawType = (Class) parameterizedType.getRawType();" + "\n" +
                "        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();" + "\n" +
                "        TypeVariable<?>[] typeParameters = rawType.getTypeParameters();" + "\n" +
                "        for (int i = 0; i < actualTypeArguments.length; i++) {" + "\n" +
                "          resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);" + "\n" +
                "        }" + "\n" +
                "        if (!rawType.equals(baseClass)) {" + "\n" +
                "          type = rawType.getGenericSuperclass();" + "\n" +
                "        }" + "\n" +
                "      }" + "\n" +
                "    }" + "\n" +
                "    // finally, for each actual type argument provided to baseClass, determine (if possible)" + "\n" +
                "    // the raw class for that type argument." + "\n" +
                "    Type[] actualTypeArguments;" + "\n" +
                "    if (type instanceof Class) {" + "\n" +
                "      actualTypeArguments = ((Class) type).getTypeParameters();" + "\n" +
                "    } else {" + "\n" +
                "      actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();" + "\n" +
                "    }" + "\n" +
                "    List<Class<?>> typeArgumentsAsClasses = new ArrayList<>();" + "\n" +
                "    // resolve types by chasing down type variables." + "\n" +
                "    for (Type baseType : actualTypeArguments) {" + "\n" +
                "      while (resolvedTypes.containsKey(baseType)) {" + "\n" +
                "        baseType = resolvedTypes.get(baseType);" + "\n" +
                "      }" + "\n" +
                "      typeArgumentsAsClasses.add(getClass(baseType));" + "\n" +
                "    }" + "\n" +
                "    return typeArgumentsAsClasses;" + "\n" +
                "")
        .endBlock()
        .endMethod()
        .addToAttributes(ALSO_IMPORT,
            new LinkedHashSet<>(Arrays.asList(ClassRef.forName(ParameterizedType.class.getName()),
                ClassRef.forName(GenericArrayType.class.getName()),
                ClassRef.forName(TypeVariable.class.getName()),
                ClassRef.forName(Array.class.getName()),
                Collections.ARRAY_LIST.toReference(),
                Collections.MAP.toReference(),
                Collections.LINKED_HASH_MAP.toReference())))
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    pathAwareVisitorClass = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.CLASS)
        .withPackageName("io.sundr.builder")
        .withName("PathAwareTypedVisitor")
        .withParameters(V, P)
        .withExtendsList(typedVisitorInterface.toReference(V.toReference()))

        .addNewProperty()
        .withName("path")
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .endProperty()

        .addNewProperty()
        .withName("delegate")
        .withTypeRef(new ClassRefBuilder().withNewFullyQualifiedName("io.sundr.builder.PathAwareTypedVisitor")
            .withArguments(V.toReference(), P.toReference()).build())
        .endProperty()

        .addNewProperty()
        .withName("parentType")
        .withTypeRef(CLASS.toReference(P.toReference()))
        .endProperty()

        .addNewConstructor()
        .addNewArgument()
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .withName("path")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement("this.path = path;")
        .addNewStringStatementStatement("this.delegate = this;")
        .addNewStringStatementStatement(
            "this.parentType = (Class<P>) getTypeArguments(PathAwareTypedVisitor.class, getClass()).get(1);")
        .endBlock()
        .endConstructor()

        .addNewConstructor()
        .addNewArgument()
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .withName("path")
        .endArgument()
        .addNewArgument()
        .withName("delegate")
        .withTypeRef(new ClassRefBuilder().withNewFullyQualifiedName("io.sundr.builder.PathAwareTypedVisitor")
            .withArguments(V.toReference(), P.toReference()).build())
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement("this.path = path;")
        .addNewStringStatementStatement("this.delegate = this;")
        .addNewStringStatementStatement(
            "this.parentType = (Class<P>) getTypeArguments(PathAwareTypedVisitor.class, delegate.getClass()).get(1);")
        .endBlock()
        .endConstructor()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("visit")
        .addNewArgument()
        .withName("element")
        .withTypeRef(V.toReference())
        .endArgument()
        .withReturnType(new VoidRef())
        .withNewBlock()
        .addNewStringStatementStatement("delegate.path = path;")
        .addNewStringStatementStatement("delegate.visit(element);")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("next")
        .addNewArgument()
        .withName("item")
        .withTypeRef(TypeDef.OBJECT_REF)
        .endArgument()
        .withReturnType(new ClassRefBuilder().withNewFullyQualifiedName("io.sundr.builder.PathAwareTypedVisitor")
            .withArguments(V.toReference(), P.toReference()).build())
        .withNewBlock()
        .addNewStringStatementStatement("List<Object> path = new ArrayList<Object>(this.path);")
        .addNewStringStatementStatement("path.add(item);")
        .addNewStringStatementStatement("return new PathAwareTypedVisitor<V, P>(path, this);")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("getParent")
        .withReturnType(P.toReference())
        .withNewBlock()
        .addNewStringStatementStatement("return path.size() - 2 >= 0 ? (P) path.get(path.size() - 2) : null;")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("getParentType")
        .withReturnType(CLASS.toReference(P.toReference()))
        .withNewBlock()
        .addNewStringStatementStatement(
            "return parentType != null ? parentType : delegate.getParentType();")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PROTECTED))
        .withName("getActualParentType")
        .withReturnType(CLASS.toReference())
        .withNewBlock()
        .addNewStringStatementStatement("return path.size() - 2 >= 0 ? path.get(path.size() - 2).getClass() : Void.class;")
        .endBlock()
        .endMethod()
        .addToAttributes(ALSO_IMPORT, new LinkedHashSet<>(Arrays.asList(Collections.ARRAY_LIST.toReference())))
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    visitableInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .addNewAnnotation()
        .withClassRef(functionalInterfaceType.toInternalReference())
        .endAnnotation()
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Visitable")
        .withParameters(T)
        .addNewMethod()
        .withName("accept")
        .withReturnType(T.toReference())
        .addNewArgument()
        .withName("visitor")
        .withNewClassRefType().withNewFullyQualifiedName(visitorInterface.getFullyQualifiedName()).withDimensions(1)
        .endClassRefType()
        .endArgument()
        .withVarArgPreferred(true)
        .endMethod()
        .addNewMethod()
        .withName("accept")
        .withParameters(V)
        .withDefaultMethod(true)
        .withReturnType(T.toReference())
        .addNewArgument()
        .withTypeRef(CLASS.toReference(V.toReference()))
        .withName("type")
        .endArgument()
        .addNewArgument()
        .withTypeRef(visitorInterface.toReference(V.toReference()))
        .withName("visitor")
        .endArgument()
        .withNewBlock()
        .withStatements(new StringStatement(
            "return accept(new TypedVisitor<V>() {@Override public Class<V> getType() {return type;} @Override public void visit(V element) {visitor.visit(element);}});"))
        .endBlock()
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    visitableBuilderInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("VisitableBuilder")
        .withParameters(T,
            new TypeParamDefBuilder(V).addNewBound().withArguments(V.toReference())
                .withNewFullyQualifiedName("VisitableBuilder").withArguments(T.toReference(), V.toReference())
                .endBound().build())
        .withExtendsList(builderInterface.toReference(T.toReference()), visitableInterface.toReference(V.toReference()))
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    fluentInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Fluent")
        .addNewParameter()
        .withName("F")
        .withBounds(
            new ClassRefBuilder().withFullyQualifiedName("io.sundr.builder.Fluent").withArguments(F.toReference()).build())
        .endParameter()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    nestedInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Nested")
        .withParameters(F)
        .addNewMethod()
        .withName("and")
        .withReturnType(F.toReference())
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    editableInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Editable")
        .withParameters(T)
        .addNewMethod()
        .withName("edit")
        .withReturnType(T.toReference())
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    inlineableBase = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .addNewAnnotation()
        .withClassRef(functionalInterfaceType.toInternalReference())
        .endAnnotation()
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Inlineable")
        .withParameters(T)
        .addNewMethod()
        .withName("update")
        .withReturnType(new VoidRef())
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    visitableMapClass = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.CLASS)
        .withPackageName("io.sundr.builder")
        .withName("VisitableMap")
        .withExtendsList(Collections.HASH_MAP.toReference(STRING_REF,
            Collections.LIST.toReference(visitableInterface.toReference())))
        .withImplementsList(Collections.ITERABLE.toReference(visitableInterface.toReference()))

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("get")
        .withReturnType(Collections.LIST.toReference(visitableInterface.toReference()))
        .addNewArgument()
        .withTypeRef(TypeDef.OBJECT_REF)
        .withName("key")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement("if (!containsKey(key)) {put(String.valueOf(key), new ArrayList());}")
        .addNewStringStatementStatement("return super.get(key);")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("aggregate")
        .withReturnType(Collections.LIST.toReference(visitableInterface.toReference()))
        .withNewBlock()
        .addNewStringStatementStatement("return values().stream().flatMap(l -> l.stream()).collect(Collectors.toList());")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("iterator")
        .withReturnType(Collections.ITERATOR.toReference(visitableInterface.toReference()))
        .withNewBlock()
        .addNewStringStatementStatement("return aggregate().iterator();")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("forEach")
        .withReturnType(new VoidRef())
        .addNewArgument()
        .withName("action")
        .withTypeRef(consumerInterface.toReference(
            new WildcardRefBuilder().withBoundKind(BoundKind.SUPER).withBounds(visitableInterface.toReference()).build()))
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement("aggregate().forEach(action);")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("spliterator")
        .withReturnType(TypeDef.forName(Spliterator.class.getName()).toReference(visitableInterface.toReference()))
        .withNewBlock()
        .addNewStringStatementStatement("return aggregate().spliterator();")
        .endBlock()
        .endMethod()

        .addToAttributes(ALSO_IMPORT,
            new LinkedHashSet<>(
                Arrays.asList(Collections.ARRAY_LIST.toReference(), ClassRef.forName(Collectors.class.getName()))))
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    baseFluentClass = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.CLASS)
        .withPackageName("io.sundr.builder")
        .withName("BaseFluent")
        .addNewParameter()
        .withName("F")
        .withBounds(fluentInterface.toReference(F.toReference()))
        .endParameter()
        .withImplementsList(fluentInterface.toReference(F.toReference()), visitableInterface.toReference(F.toReference()))

        .addNewProperty()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))
        .withTypeRef(STRING_REF)
        .withName("VISIT")
        .addToAttributes(Attributeable.INIT, "visit")
        .endProperty()

        .addNewProperty()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.FINAL))
        .withTypeRef(visitableMapClass.toReference())
        .withName("_visitables")
        .addToAttributes(Attributeable.INIT, "new VisitableMap()")
        .endProperty()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("builderOf")
        .withParameters(T)
        .withReturnType(visitableBuilderInterface.toReference(T.toReference(), new WildcardRef()))
        .addNewArgument()
        .withTypeRef(T.toReference())
        .withName("item")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "if (item instanceof Editable) { " + "\n" +
                "  Object editor = ((Editable) item).edit(); " + "\n" +
                "  if (editor instanceof VisitableBuilder) { " + "\n" +
                "    return (VisitableBuilder<T, ?>) editor; " + "\n" +
                "  } " + "\n" +
                "} " + "\n" +
                "try { " + "\n" +
                "  return (VisitableBuilder<T, ?>) Class.forName(item.getClass().getName() + \"Builder\").getConstructor(item.getClass()) "
                + "\n" +
                "      .newInstance(item); " + "\n" +
                "} catch (Exception e) { " + "\n" +
                "  throw new IllegalStateException(\"Failed to create builder for: \" + item.getClass(), e); " + "\n" +
                "} " + "\n")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("build")
        .withParameters(T)
        .withReturnType(Collections.ARRAY_LIST.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(Collections.LIST.toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS)
            .withBounds(builderInterface
                .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
            .build()))
        .withName("list")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "return list == null ? null : new ArrayList<T>(list.stream().map(Builder::build).collect(Collectors.toList()));")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("build")
        .withParameters(T)
        .withReturnType(Collections.ARRAY_LIST.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(Collections.SET.toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS)
            .withBounds(builderInterface
                .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
            .build()))
        .withName("set")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "return set == null ? null : new ArrayList<>(set.stream().map(Builder::build).collect(Collectors.toList()));")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("aggregate")
        .withParameters(T)
        .withReturnType(Collections.ARRAY_LIST.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(Collections.LIST
            .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
                .withDimensions(1).build())
        .withName("lists")
        .endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addNewStringStatementStatement(
            "return new ArrayList(Arrays.stream(lists).filter(Objects::nonNull).collect(Collectors.toList()));")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("aggregate")
        .withParameters(T)
        .withReturnType(Collections.LINKED_HASH_SET.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(Collections.SET
            .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
                .withDimensions(1).build())
        .withName("sets")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "return new LinkedHashSet(Arrays.stream(sets).filter(Objects::nonNull).collect(Collectors.toSet()));")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("canVisit")
        .withParameters(new TypeParamDefBuilder().withName("V").withBounds(visitorInterface.toReference()).build(), F)
        .withReturnType(io.sundr.model.utils.Types.BOOLEAN_REF)
        .addNewArgument()
        .withTypeRef(V.toReference())
        .withName("visitor")
        .endArgument()
        .addNewArgument()
        .withName("fluent")
        .withTypeRef(F.toReference())
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "if (visitor instanceof TypedVisitor) { " + "\n" +
                "  if (!((TypedVisitor) visitor).getType().isAssignableFrom(fluent.getClass())) { " + "\n" +
                "    return false; " + "\n" +
                "  } " + "\n" +
                "} " + "\n" +
                "if (visitor instanceof PathAwareTypedVisitor) { " + "\n" +
                "  PathAwareTypedVisitor pathAwareTypedVisitor = (PathAwareTypedVisitor) visitor; " + "\n" +
                "  Class parentType = pathAwareTypedVisitor.getParentType(); " + "\n" +
                "  Class actaulParentType = pathAwareTypedVisitor.getActualParentType(); " + "\n" +
                "  if (!parentType.isAssignableFrom(actaulParentType)) { " + "\n" +
                "    return false; " + "\n" +
                "  } " + "\n" +
                "} " + "\n" +
                "return hasCompatibleVisitMethod(visitor, fluent); " + "\n")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("hasCompatibleVisitMethod")
        .withParameters(V, F)
        .withReturnType(io.sundr.model.utils.Types.BOOLEAN_REF)
        .addNewArgument()
        .withTypeRef(V.toReference())
        .withName("visitor")
        .endArgument()
        .addNewArgument()
        .withName("fluent")
        .withTypeRef(F.toReference())
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "for (java.lang.reflect.Method method : visitor.getClass().getMethods()) {" + "\n" +
                "  if (!method.getName().equals(VISIT) || method.getParameterTypes().length != 1) {" + "\n" +
                "    continue;" + "\n" +
                "  }" + "\n" +
                "  Class visitorType = method.getParameterTypes()[0];" + "\n" +
                "  if (visitorType.isAssignableFrom(fluent.getClass())) {" + "\n" +
                "    return true;" + "\n" +
                "  } else {" + "\n" +
                "    return false;" + "\n" +
                "  }" + "\n" +
                "}" + "\n" +
                "return false;")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("accept")
        .withReturnType(F.toReference())
        .addNewArgument()
        .withName("visitors")
        .withTypeRef(new ClassRefBuilder().withFullyQualifiedName(Visitor.class.getName()).withDimensions(1).build())
        .endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addNewStringStatementStatement(
            "return isPathAwareVisitorArray(visitors) ? acceptPathAware(asPathAwareVisitorArray(visitors)) : acceptInternal(visitors);")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("accept")
        .withParameters(V)
        .withReturnType(F.toReference())
        .addNewArgument()
        .withTypeRef(CLASS.toReference(V.toReference()))
        .withName("type")
        .endArgument()
        .addNewArgument()
        .withTypeRef(visitorInterface.toReference(V.toReference()))
        .withModifiers(modifiersToInt(Modifier.FINAL))
        .withName("visitor")
        .endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addNewStringStatementStatement(
            "return accept(new TypedVisitor<V>() {" + "\n" +
                "  @Override" + "\n" +
                "  public Class<V> getType() {" + "\n" +
                "    return type;" + "\n" +
                "  }" + "\n" +
                "  @Override" + "\n" +
                "  public void visit(V element) {" + "\n" +
                "    visitor.visit(element);" + "\n" +
                "  }" + "\n" +
                "});")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("acceptInternal")
        .withReturnType(F.toReference())
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(visitorInterface.toReference()).withDimensions(1).build())
        .withName("visitors")
        .endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addNewStringStatementStatement(
            "for (Visitor visitor : visitors) {" + "\n" +
                "  for (Visitable visitable : _visitables) {" + "\n" +
                "    visitable.accept(visitor);" + "\n" +
                "  }" + "\n" +
                "" + "\n" +
                "  if (canVisit(visitor, this)) {" + "\n" +
                "    visitor.visit(this);" + "\n" +
                "  }" + "\n" +
                "}" + "\n" +
                "return (F) this;")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PRIVATE))
        .withName("acceptPathAware")
        .withReturnType(F.toReference())
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(pathAwareVisitorClass.toReference()).withDimensions(1).build())
        .withName("pathAwareTypedVisitors")
        .endArgument()
        .withVarArgPreferred(true)
        .withNewBlock()
        .addNewStringStatementStatement(
            "return acceptInternal(Arrays.stream(pathAwareTypedVisitors).map(p -> p.next(this)).toArray(size -> new PathAwareTypedVisitor[size]));")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC))
        .withName("isPathAwareVisitorArray")
        .withReturnType(io.sundr.model.utils.Types.PRIMITIVE_BOOLEAN_REF)
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(visitorInterface.toReference()).withDimensions(1).build())
        .withName("visitors")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "return !Arrays.stream(visitors).filter(v -> !(v instanceof PathAwareTypedVisitor)).findAny().isPresent();")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC))
        .withName("asPathAwareVisitorArray")
        .withReturnType(new ClassRefBuilder(pathAwareVisitorClass.toReference()).withDimensions(1).build())
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(visitorInterface.toReference()).withDimensions(1).build())
        .withName("visitors")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "return Arrays.stream(visitors).filter(v -> v instanceof PathAwareTypedVisitor).map(v -> (PathAwareTypedVisitor) v).toArray(size -> new PathAwareTypedVisitor[size]);")
        .endBlock()
        .endMethod()
        .addToAttributes(ALSO_IMPORT, new LinkedHashSet<>(Arrays.asList(
            ClassRef.forName(Collectors.class.getName()),
            ClassRef.forName(Objects.class.getName()),
            ClassRef.forName(Arrays.class.getName()))))
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .build();

    ClassRef validatorRef = ClassRef.forName("javax.validation.Validator");
    ClassRef validationRef = ClassRef.forName("javax.validation.Validation");
    ClassRef validationExceptionRef = ClassRef.forName("javax.validation.ValidationException");
    ClassRef validatorFactoryRef = ClassRef.forName("javax.validation.ValidatorFactory");

    ClassRef constraintViolationRef = ClassRef.forName("javax.validation.ConstraintViolation");
    ClassRef constraintViolationExceptionRef = ClassRef.forName("javax.validation.ConstraintViolationException");

    validationUtils = new TypeDefBuilder()
        .withPackageName("io.sundr.builder.internal.resources")
        .withName("ValidationUtils")
        .withKind(Kind.CLASS)
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.FINAL))

        .addNewProperty()
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC, Modifier.FINAL))
        .withName("LOCK")
        .withTypeRef(TypeDef.OBJECT_REF)
        .addToAttributes(Attributeable.INIT, "new Object()")
        .endProperty()

        .addNewProperty()
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC))
        .withName("validator")
        .withTypeRef(validatorRef)
        .endProperty()

        .addNewMethod()
        .withName("createValidator")
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC))
        .withReturnType(validatorRef)
        .withNewBlock()
        .addNewStringStatementStatement(
            "try {" + "\n" +
                "  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();" + "\n" +
                "  return factory.getValidator();" + "\n" +
                "} catch (ValidationException e) {" + "\n" +
                "  return null;" + "\n" +
                "}")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("getValidator")
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC))
        .withReturnType(validatorRef)
        .withNewBlock()
        .addNewStringStatementStatement(
            "Validator v = validator;" + "\n" +
                "if (v == null) {" + "\n" +
                "  synchronized (LOCK) {" + "\n" +
                "    v = validator;" + "\n" +
                "    if (validator == null) {" + "\n" +
                "      v = createValidator();" + "\n" +
                "      validator = v;" + "\n" +
                "    }" + "\n" +
                "  }" + "\n" +
                "}" + "\n" +
                "return v;")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("validate")
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withParameters(T)
        .withReturnType(new VoidRef())
        .addNewArgument()
        .withTypeRef(T.toReference())
        .withName("item")
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement("validate(item, getValidator());")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withName("validate")
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withParameters(T)
        .withReturnType(new VoidRef())
        .addNewArgument()
        .withTypeRef(T.toReference())
        .withName("item")
        .endArgument()
        .addNewArgument()
        .withName("v")
        .withTypeRef(validatorRef)
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement(
            "if (v == null) {" + "\n" +
                "  v = getValidator();" + "\n" +
                "}" + "\n" +
                "if (v == null) {" + "\n" +
                "  return;" + "\n" +
                "}" + "\n" +
                "Set<ConstraintViolation<T>> violations = v.validate(item);" + "\n" +
                "if (!violations.isEmpty()) {" + "\n" +
                "  StringBuilder sb = new StringBuilder(\"Constraint Validations: \");" + "\n" +
                "  boolean first = true;" + "\n" +
                "  for (ConstraintViolation violation : violations) {" + "\n" +
                "    if (first) {" + "\n" +
                "      first = false;" + "\n" +
                "    } else {" + "\n" +
                "      sb.append(\", \");" + "\n" +
                "    }" + "\n" +
                "    Object leafBean = violation.getLeafBean();" + "\n" +
                "    sb.append(violation.getPropertyPath() + \" \" + violation.getMessage() + \" on bean: \" + leafBean);"
                + "\n" +
                "  }" + "\n" +
                "  throw new ConstraintViolationException(sb.toString(), violations);" + "\n" +
                "}")
        .endBlock()
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder.internal.resources", builderPackage))
        .addToAttributes(ALSO_IMPORT,
            new LinkedHashSet<>(
                Arrays.asList(Collections.SET.toReference(), validatorFactoryRef, validationRef, validationExceptionRef,
                    constraintViolationRef, constraintViolationExceptionRef)))
        .withAnnotations(new ArrayList<>())
        .build();

    this.externalValidatorSupported = hasValidatorArg(builderPackage + ".ValidationUtils");
  }

  private static boolean hasValidatorArg(String c) {
    Class validator;
    try {
      validator = Class.forName("javax.validation.Validator");
    } catch (ClassNotFoundException e) {
      return false;
    }
    try {
      Method m = Class.forName(c).getMethod("validate", Object.class, validator);
      return true;
    } catch (ClassNotFoundException e) {
      return true;
    } catch (NoSuchMethodException e) {
      return false;
    }
  }

  public Elements getElements() {
    return elements;
  }

  public Types getTypes() {
    return types;
  }

  public Boolean getGenerateBuilderPackage() {
    return generateBuilderPackage;
  }

  public Boolean isValidationEnabled() {
    return validationEnabled;
  }

  public Boolean isExternalvalidatorSupported() {
    return validationEnabled && externalValidatorSupported;
  }

  public String getBuilderPackage() {
    return builderPackage;
  }

  public TypeDef getBaseFluentClass() {
    return baseFluentClass;
  }

  public TypeDef getFluentInterface() {
    return fluentInterface;
  }

  public TypeDef getBuilderInterface() {
    return builderInterface;
  }

  public TypeDef getNestedInterface() {
    return nestedInterface;
  }

  public TypeDef getEditableInterface() {
    return editableInterface;
  }

  public TypeDef getVisitableInterface() {
    return visitableInterface;
  }

  public TypeDef getVisitableBuilderInterface() {
    return visitableBuilderInterface;
  }

  public TypeDef getVisitableMapClass() {
    return visitableMapClass;
  }

  public TypeDef getVisitorInterface() {
    return visitorInterface;
  }

  public TypeDef getTypedVisitorInterface() {
    return typedVisitorInterface;
  }

  public TypeDef getPathAwareVisitorClass() {
    return pathAwareVisitorClass;
  }

  public TypeDef getInlineableBase() {
    return inlineableBase;
  }

  public Boolean getValidationEnabled() {
    return validationEnabled;
  }

  public TypeDef getInlineableInterface(Inline inline) {
    return new TypeDefBuilder(inlineableBase)
        .withKind(Kind.INTERFACE)
        .withPackageName(builderPackage)
        .withName(inline.prefix() + (!inline.name().isEmpty() ? inline.name() : INLINEABLE.getName()) + inline.suffix())
        .withParameters(INLINEABLE.getParameters())
        .addNewMethod()
        .withReturnType(newTypeParamRef("T"))
        .withName(inline.value())
        .and()
        .build();
  }

  public Inline[] getInlineables() {
    return inlineables;
  }

  public TypeDef getValidationUtils() {
    return validationUtils;
  }

  public BuildableRepository getBuildableRepository() {
    return buildableRepository;
  }

  public DefinitionRepository getDefinitionRepository() {
    return aptContext.getDefinitionRepository();
  }

  public AptContext getAptContext() {
    return aptContext;
  }
}
