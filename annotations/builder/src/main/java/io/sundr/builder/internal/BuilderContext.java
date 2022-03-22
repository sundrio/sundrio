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
import static io.sundr.model.utils.Types.BOOLEAN_REF;
import static io.sundr.model.utils.Types.CLASS;
import static io.sundr.model.utils.Types.CLASS_REF_NO_ARG;
import static io.sundr.model.utils.Types.OPTIONAL;
import static io.sundr.model.utils.Types.STRING_REF;
import static io.sundr.model.utils.Types.TYPE;
import static io.sundr.model.utils.Types.modifiersToInt;
import static io.sundr.model.utils.Types.newTypeParamRef;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Spliterator;
import java.util.function.Consumer;

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
import io.sundr.model.visitors.ApplyImportsFromResources;
import io.sundr.model.visitors.ApplyMethodBlockFromResources;
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

    visitorInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .addNewAnnotation()
        .withClassRef(functionalInterfaceType.toInternalReference())
        .endAnnotation()

        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Visitor")
        .withParameters(T)

        //visit
        .addNewMethod()
        .withName("visit")
        .addNewArgument()
        .withName("element")
        .withTypeRef(T.toReference())
        .endArgument()
        .withReturnType(new VoidRef())
        .endMethod()

        //default void visit(List<Object> path, T element) {
        .addNewMethod()
        .withDefaultMethod(true)
        .withName("visit")
        .addNewArgument()
        .withName("path")
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .endArgument()
        .addNewArgument()
        .withName("element")
        .withTypeRef(T.toReference())
        .endArgument()
        .withReturnType(new VoidRef())
        .endMethod()

        .addNewMethod()
        .withDefaultMethod(true)
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withParameters(F)
        .withName("canVisit")
        .withReturnType(BOOLEAN_REF)
        .addNewArgument()
        .withName("target")
        .withTypeRef(F.toReference())
        .endArgument()
        .endMethod()

        .addNewMethod()
        .withDefaultMethod(true)
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withParameters(F)
        .withName("hasVisitMethodMatching")
        .withReturnType(BOOLEAN_REF)
        .addNewArgument()
        .withName("target")
        .withTypeRef(F.toReference())
        .endArgument()
        .endMethod()

        .addNewMethod()
        .withDefaultMethod(true)
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("getType")
        .withReturnType(CLASS.toReference(T.toReference()))
        .endMethod()

        // getRawName
        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.STATIC))
        .withName("getRawName")
        .withReturnType(STRING_REF)
        .addNewArgument()
        .withName("type")
        .withTypeRef(TYPE.toInternalReference())
        .endArgument()
        .endMethod()

        // getClass
        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.STATIC))
        .withName("getClass")
        .withReturnType(CLASS.toReference(new WildcardRef()))
        .addNewArgument()
        .withName("type")
        .withTypeRef(TYPE.toInternalReference())
        .endArgument()
        .withNewBlock()
        .endBlock()
        .endMethod()

        // getMatchingInterface
        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.STATIC))
        .withParameters(T)
        .withName("getMatchingInterface")
        .withReturnType(OPTIONAL.toReference(TYPE.toInternalReference()))
        .addNewArgument()
        .withName("targetInterface")
        .withTypeRef(CLASS.toReference())
        .endArgument()
        .addNewArgument()
        .withName("candidates")
        .withTypeRef(new ClassRefBuilder(TYPE.toReference()).withDimensions(1).build())
        .endArgument()
        .withVarArgPreferred(true)
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.STATIC))
        .withParameters(T)
        .withName("getTypeArguments")
        .withReturnType(Collections.LIST.toReference(CLASS_REF_NO_ARG))
        .addNewArgument()
        .withTypeRef(CLASS.toReference(T.toReference()))
        .withName("baseClass")
        .endArgument()
        .addNewArgument()
        .withTypeRef(CLASS.toReference(new WildcardRefBuilder().withBounds(T.toReference()).build()))
        .withName("childClass")
        .endArgument()
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .accept(new ApplyMethodBlockFromResources("Visitor", "io/sundr/builder/Visitor.java", true))
        .accept(new ApplyImportsFromResources("io/sundr/builder/Visitor.java"))
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
        .endMethod()
        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .accept(new ApplyMethodBlockFromResources("TypedVisitor", "io/sundr/builder/TypedVisitor.java"))
        .accept(new ApplyImportsFromResources("io/sundr/builder/TypedVisitor.java"))
        .build();

    pathAwareVisitorClass = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.CLASS)
        .withPackageName("io.sundr.builder")
        .withName("PathAwareTypedVisitor")
        .withParameters(V, P)
        .withExtendsList(typedVisitorInterface.toReference(V.toReference()))

        .addNewProperty()
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
        .withName("type")
        .withTypeRef(CLASS.toReference(V.toReference()))
        .endProperty()

        .addNewProperty()
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.FINAL))
        .withName("parentType")
        .withTypeRef(CLASS.toReference(P.toReference()))
        .endProperty()

        .addNewConstructor() // default constructor
        .endConstructor()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("visit")
        .addNewArgument()
        .withName("element")
        .withTypeRef(V.toReference())
        .endArgument()
        .withReturnType(new VoidRef())
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("visit")
        .addNewArgument()
        .withName("path")
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .endArgument()
        .addNewArgument()
        .withName("element")
        .withTypeRef(V.toReference())
        .endArgument()
        .withReturnType(new VoidRef())
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withParameters(F)
        .withName("canVisit")
        .withReturnType(BOOLEAN_REF)
        .addNewArgument()
        .withName("target")
        .withTypeRef(F.toReference())
        .endArgument()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("getParent")
        .withReturnType(P.toReference())
        .addNewArgument()
        .withName("path")
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .endArgument()
        .withNewBlock()
        .addNewStringStatementStatement("return path.size() - 2 >= 0 ? (P) path.get(path.size() - 2) : null;")
        .endBlock()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("getParentType")
        .withReturnType(CLASS.toReference(P.toReference()))
        .withNewBlock()
        .addNewStringStatementStatement(
            "return parentType != null ? parentType : delegate.getParentType();")
        .endBlock()
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .accept(new ApplyMethodBlockFromResources("PathAwareTypedVisitor", "io/sundr/builder/PathAwareTypedVisitor.java"))
        .accept(new ApplyImportsFromResources("io/sundr/builder/PathAwareTypedVisitor.java"))
        .build();

    visitableInterface = new TypeDefBuilder()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withKind(Kind.INTERFACE)
        .withPackageName("io.sundr.builder")
        .withName("Visitable")
        .withParameters(T)

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
        .endMethod()

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
        .withReturnType(T.toReference())
        .addNewArgument()
        .withName("path")
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .endArgument()
        .addNewArgument()
        .withTypeRef(
            new ClassRefBuilder().withNewFullyQualifiedName(visitorInterface.getFullyQualifiedName()).withDimensions(1).build())
        .withName("visitor")
        .endArgument()
        .withVarArgPreferred(true)
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .accept(new ApplyMethodBlockFromResources("Visitable", "io/sundr/builder/Visitable.java", true))
        .accept(new ApplyImportsFromResources("io/sundr/builder/Visitable.java"))
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
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("aggregate")
        .withReturnType(Collections.LIST.toReference(visitableInterface.toReference()))
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("iterator")
        .withReturnType(Collections.ITERATOR.toReference(visitableInterface.toReference()))
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
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("spliterator")
        .withReturnType(TypeDef.forName(Spliterator.class.getName()).toReference(visitableInterface.toReference()))
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .accept(new ApplyMethodBlockFromResources("VisitableMap", "io/sundr/builder/VisitableMap.java"))
        .accept(new ApplyImportsFromResources("io/sundr/builder/VisitableMap.java"))
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
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("build")
        .withParameters(T)
        .withReturnType(Collections.LIST.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(Collections.LIST.toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS)
            .withBounds(builderInterface
                .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
            .build()))
        .withName("list")
        .endArgument()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("build")
        .withParameters(T)
        .withReturnType(Collections.LIST.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(Collections.SET.toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS)
            .withBounds(builderInterface
                .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
            .build()))
        .withName("set")
        .endArgument()
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("aggregate")
        .withParameters(T)
        .withReturnType(Collections.LIST.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(Collections.LIST
            .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
                .withDimensions(1).build())
        .withName("lists")
        .endArgument()
        .withVarArgPreferred(true)
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC, Modifier.STATIC))
        .withName("aggregate")
        .withParameters(T)
        .withReturnType(Collections.SET.toReference(T.toReference()))
        .addNewArgument()
        .withTypeRef(new ClassRefBuilder(Collections.SET
            .toReference(new WildcardRefBuilder().withBoundKind(BoundKind.EXTENDS).withBounds(T.toReference()).build()))
                .withDimensions(1).build())
        .withName("sets")
        .endArgument()
        .withVarArgPreferred(true)
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
        .endMethod()

        .addNewMethod()
        .withModifiers(modifiersToInt(Modifier.PUBLIC))
        .withName("accept")
        .withReturnType(F.toReference())
        .addNewArgument()
        .withName("path")
        .withTypeRef(Collections.LIST.toReference(TypeDef.OBJECT_REF))
        .endArgument()
        .addNewArgument()
        .withTypeRef(
            new ClassRefBuilder().withFullyQualifiedName(visitorInterface.getFullyQualifiedName()).withDimensions(1).build())
        .withName("visitors")
        .endArgument()
        .withVarArgPreferred(true)
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder", builderPackage))
        .accept(new ApplyMethodBlockFromResources("BaseFluent", "io/sundr/builder/BaseFluent.java"))
        .accept(new ApplyImportsFromResources("io/sundr/builder/BaseFluent.java"))
        .build();

    ClassRef validatorRef = ClassRef.forName("javax.validation.Validator");
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
        .endMethod()

        .addNewMethod()
        .withName("getValidator")
        .withModifiers(modifiersToInt(Modifier.PRIVATE, Modifier.STATIC))
        .withReturnType(validatorRef)
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
        .endMethod()

        .accept(new ReplacePackage("io.sundr.builder.internal.resources", builderPackage))
        .accept(
            new ApplyMethodBlockFromResources("ValidationUtils", "io/sundr/builder/internal/resources/ValidationUtils.java"))
        .accept(new ApplyImportsFromResources("io/sundr/builder/internal/resources/ValidationUtils.java"))

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
