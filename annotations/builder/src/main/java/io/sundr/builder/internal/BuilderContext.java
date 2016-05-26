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

import io.sundr.builder.TypedVisitor;
import io.sundr.builder.annotations.Inline;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;

import javax.lang.model.element.Modifier;
import javax.lang.model.util.Elements;

import static io.sundr.builder.Constants.*;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;
import static io.sundr.codegen.utils.TypeUtils.typeImplements;

public class BuilderContext {

    private final Elements elements;

    private final TypeDef baseFluentClass;
    private final TypeDef fluentInterface;
    private final TypeDef builderInterface;
    private final TypeDef nestedInterface;
    private final TypeDef editableInterface;
    private final TypeDef visitableInterface;
    private final TypeDef visitableBuilderInterface;
    private final TypeDef visitorInterface;
    private final TypeDef typedVisitorInterface;
    private final TypeDef functionInterface;
    private final TypeDef inlineableBase;
    private final Boolean generateBuilderPackage;
    private final String builderPackage;
    private final Inline[] inlineables;
    private final BuildableRepository repository;

    
    public BuilderContext(Elements elements, Boolean generateBuilderPackage, String builderPackage, Inline... inlineables) {
        this.elements = elements;
        this.generateBuilderPackage = generateBuilderPackage;
        this.builderPackage = builderPackage;
        this.inlineables = inlineables;

        repository = new BuildableRepository();

        visitorInterface = new TypeDefBuilder()
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withName(VISITOR.getClassName())

                .withGenericTypes(VISITOR.getGenericTypes())
                .and()
                .addNewMethod()
                .addToModifiers(Modifier.PUBLIC)
                .withReturnType(VOID)
                .withName("visit")
                .addNewArgument()
                .withName("item")
                .withType(V)
                .endArgument()
                .and()
                .build();

        typedVisitorInterface = new TypeDefBuilder(TYPED_VISITOR)
                .withPackageName(builderPackage)
                .accept(new TypedVisitor<MethodBuilder>() {
                    public void visit(MethodBuilder method) {
                        if (method.getName().equals("getType")) {
                            method.addToAttributes(BODY, loadResourceQuietly(GET_TYPE_SNIPPET));
                        } else if (method.getName().equals("getClass")) {
                            method.addToAttributes(BODY, loadResourceQuietly(GET_CLASS_SNIPPET));
                        } else if (method.getName().equals("getTypeArguments")) {
                            method.addToAttributes(BODY, loadResourceQuietly(GET_TYPE_ARGUMENTS_SNIPPET));
                        }
                    }
                }).build();
                //TODO: .addToImports(LIST, ARRAY_LIST, MAP, LINKED_HASH_MAP, ARRAY, TYPE, TYPE_VARIABLE, GENERIC_ARRAY_TYPE, PARAMETERIZED_TYPE)

        functionInterface = new TypeDefBuilder(FUNCTION)
                .withPackageName(builderPackage)
                .build();

        visitableInterface = new TypeDefBuilder(VISITOR)
                .withPackageName(builderPackage)
                .build();


        
        builderInterface = new TypeDefBuilder(BUILDER)
                .withPackageName(builderPackage)
                .build();

        fluentInterface = new TypeDefBuilder(FLUENT)
                .withPackageName(builderPackage)
                .build();


        baseFluentClass = new TypeDefBuilder(BASE_FLUENT)
                .withPackageName(builderPackage)
                .accept(new TypedVisitor<MethodBuilder>() {
                    public void visit(MethodBuilder method) {
                        if (method.getName().equals("build") && method.getReturnType().) {
                            method.addToAttributes(BODY, loadResourceQuietly(BUILD_LIST_SNIPPET));
                        } else if
                    }
                }).build();

                .withNewType()
                .withKind(Kind.CLASS)
                .withPackageName(builderPackage)
                .withClassName(BASE_FLUENT.getClassName())
                .withGenericTypes(BASE_FLUENT.getGenericTypes())
                .addToInterfaces(fluentInterface.getType())
                .addToInterfaces(typeGenericOf(visitableInterface.getType(),T))
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                    .withName("build")
                    .withReturnType(typeGenericOf(ARRAY_LIST,T))
                    .addNewArgument()
                        .withType(typeGenericOf(LIST, typeImplements(Q, typeGenericOf(builderInterface.getType(), typeImplements(Q,T)))))
                        .withName("list")
                    .endArgument()
                .addToAttributes(BODY, loadResourceQuietly(BUILD_LIST_SNIPPET))
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                        .withName("build")
                        .withReturnType(typeGenericOf(LINKED_HASH_SET, T))
                        .addNewArgument()
                            .withType(typeGenericOf(LINKED_HASH_SET, typeImplements(Q, typeGenericOf(builderInterface.getType(), T))))
                            .withName("set")
                        .endArgument()
                    .addToAttributes(BODY, loadResourceQuietly(BUILD_SET_SNIPPET))
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                    .withName("aggregate")
                    .withReturnType(typeGenericOf(ARRAY_LIST, T))
                    .addNewArgument()
                        .withType(typeGenericOf(LIST, typeImplements(Q, T)))
                        .withName("...lists")
                    .endArgument()
                    .addToAttributes(BODY, loadResourceQuietly(AGGREGATE_LIST_SNIPPET))
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                    .withName("aggregate")
                    .withReturnType(typeGenericOf(LINKED_HASH_SET, T))
                    .addNewArgument()
                    .withType(typeGenericOf(SET, typeImplements(Q, T)))
                        .withName("...sets")
                    .endArgument()
                    .addToAttributes(BODY, loadResourceQuietly(AGGREGATE_SET_SNIPPET))
                .and()
                .addNewMethod()
                    .addToTypeParameters(V,F)
                    .addToModifiers(Modifier.PRIVATE, Modifier.STATIC)
                    .withName("canVisit")
                    .withReturnType(BOOLEAN)
                    .addNewArgument()
                        .withType(V)
                        .withName("visitor")
                    .endArgument()
                    .addNewArgument()
                        .withType(F)
                        .withName("fluent")
                    .endArgument()
                    .addToAttributes(BODY, loadResourceQuietly(CAN_VISIT_SNIPPET))
                .endMethod()
                .addNewMethod()
                    .addToModifiers(Modifier.PUBLIC)
                    .withName("accept")
                    .withReturnType(T)
                    .addNewArgument()
                        .withType(visitorBase)
                        .withName("visitor")
                    .endArgument()
                .addToAttributes(BODY, loadResourceQuietly(ACCEPT_VISITOR_SNIPPET))
                .and()
                .addNewField()
                    .addToModifiers(Modifier.PUBLIC)
                    .addToModifiers(Modifier.FINAL)
                    .withName("_visitables")
                    .withType(new JavaTypeBuilder(typeGenericOf(LIST, visitableBase))
                            .withDefaultImplementation(typeGenericOf(ARRAY_LIST, visitableBase))
                            .build())
                .and()
                .build();

        nestedInterface = new TypeDefBuilder()
                .withNewType()
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(NESTED.getClassName())
                .withGenericTypes(NESTED.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(N)
                .withName("and")
                .and()
                .build();

        editableInterface = new TypeDefBuilder()
                .withNewType()
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(EDITABLE.getClassName())
                .withGenericTypes(EDITABLE.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(T)
                .withName("edit")
                .and()
                .build();

        visitableBuilderInterface = new TypeDefBuilder()
                .withNewType()
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(VISITABLE_BUILDER.getClassName())
                .addToInterfaces(visitableInterface.getType())
                .addToInterfaces(builderInterface.getType())
                .withGenericTypes(VISITABLE_BUILDER.getGenericTypes())
                .and()
                .build();

        inlineableBase = new TypeDefBuilder()
                .withNewType()
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(INLINEABLE.getClassName())
                .withGenericTypes(INLINEABLE.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(T)
                .withName("inline")
                .and()
                .build();
    }

    public Elements getElements() {
        return elements;
    }

    public Boolean getGenerateBuilderPackage() {
        return generateBuilderPackage;
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

    public TypeDef getFunctionInterface() {
        return functionInterface;
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

    public TypeDef getVisitorInterface() {
        return visitorInterface;
    }

    public TypeDef getTypedVisitorInterface() {
        return typedVisitorInterface;
    }

    public TypeDef getInlineableBase() {
        return inlineableBase;
    }

    public TypeDef getInlineableInterface(Inline inline) {
        return new TypeDefBuilder()
                .withNewType()
                .withKind(Kind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(inline.prefix() + (!inline.name().isEmpty() ? inline.name() : INLINEABLE.getClassName()) + inline.suffix())
                .withGenericTypes(INLINEABLE.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(T)
                .withName(inline.value())
                .and()
                .build();
    }

    public Inline[] getInlineables() {
        return inlineables;
    }

    public BuildableRepository getRepository() {
        return repository;
    }
}
