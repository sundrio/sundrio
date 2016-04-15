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

import io.sundr.CachingFunction;
import io.sundr.Function;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaClazz;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaProperty;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaType;
import io.sundr.codegen.converters.StringToJavaClazz;
import io.sundr.codegen.converters.TypeElementToJavaClazz;
import io.sundr.codegen.converters.ExecutableElementToJavaMethod;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;


import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import static io.sundr.builder.Constants.ARRAY_LIST;
import static io.sundr.builder.Constants.BASE_FLUENT;
import static io.sundr.builder.Constants.BODY;
import static io.sundr.builder.Constants.BUILDER;
import static io.sundr.builder.Constants.EDITABLE;
import static io.sundr.builder.Constants.FLUENT;
import static io.sundr.builder.Constants.LINKED_HASH_SET;
import static io.sundr.builder.Constants.LIST;
import static io.sundr.builder.Constants.N;
import static io.sundr.builder.Constants.NESTED;
import static io.sundr.builder.Constants.T;
import static io.sundr.builder.Constants.Q;
import static io.sundr.builder.Constants.V;
import static io.sundr.builder.Constants.VISITABLE;
import static io.sundr.builder.Constants.VISITOR;
import static io.sundr.builder.Constants.VOID;
import static io.sundr.builder.Constants.*;
import static io.sundr.codegen.utils.StringUtils.loadResourceQuietly;
import static io.sundr.codegen.utils.TypeUtils.typeExtends;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;
import static io.sundr.codegen.utils.TypeUtils.unwrapGeneric;

public class BuilderContext {

    private final Elements elements;
            
    private final Function<String, JavaType> stringJavaTypeFunction;
    private final Function<String, JavaClazz> stringToJavaClazz;
    private final Function<VariableElement, JavaProperty> variableElementJavaPropertyFunction;
    private final Function<ExecutableElement, JavaMethod> executableElementToJavaMethod;
    private final Function<TypeElement, JavaClazz> typeElementToJavaClazz;

    private final JavaClazz baseFluentClass;
    private final JavaClazz fluentInterface;
    private final JavaClazz builderInterface;
    private final JavaClazz nestedInterface;
    private final JavaClazz editableInterface;
    private final JavaClazz visitableInterface;
    private final JavaClazz visitableBuilderInterface;
    private final JavaClazz visitorInterface;
    private final JavaClazz functionInterface;
    private final JavaClazz inlineableBase;
    private final Boolean generateBuilderPackage;
    private final String builderPackage;
    private final Inline[] inlineables;
    private final BuildableRepository repository;

    public BuilderContext(Elements elements, Boolean generateBuilderPackage, String builderPackage, Inline... inlineables) {
        this.elements = elements;
        this.generateBuilderPackage = generateBuilderPackage;
        this.builderPackage = builderPackage;
        this.inlineables = inlineables;

        stringJavaTypeFunction = CachingFunction.wrap(new ToBuildableJavaType(elements));
        variableElementJavaPropertyFunction = CachingFunction.wrap(new ToBuildableJavaProperty(stringJavaTypeFunction));
        executableElementToJavaMethod = CachingFunction.wrap(new ExecutableElementToJavaMethod(stringJavaTypeFunction, variableElementJavaPropertyFunction));
        typeElementToJavaClazz = CachingFunction.wrap(new ToBuildableJavaClazz(new TypeElementToJavaClazz(elements, stringJavaTypeFunction, executableElementToJavaMethod, variableElementJavaPropertyFunction)));
        stringToJavaClazz = CachingFunction.wrap(new StringToJavaClazz(elements, typeElementToJavaClazz));

        repository = new BuildableRepository();

        visitorInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(VISITOR.getClassName())
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

        functionInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(FUNCTION.getClassName())
                .withGenericTypes(FUNCTION.getGenericTypes())
                .and()
                .addNewMethod()
                .addToModifiers(Modifier.PUBLIC)
                .withReturnType(O)
                .withName("apply")
                .addNewArgument()
                .withName("item")
                .withType(I)
                .endArgument()
                .and()
                .build();

        JavaType visitorBase = unwrapGeneric(visitorInterface.getType());

        visitableInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(VISITABLE.getClassName())
                .withGenericTypes(new JavaType[]{V})
                .and()
                .addNewMethod()
                .addToModifiers(Modifier.PUBLIC)
                .withReturnType(V)
                .withName("accept")
                .addNewArgument()
                .withName("visitor")
                .withType(visitorBase)
                .endArgument()
                .and()
                .build();

        JavaType visitableBase = unwrapGeneric(visitableInterface.getType());
        
        builderInterface = new JavaClazzBuilder()
                .withNewType()
                .withPackageName(builderPackage)
                .withKind(JavaKind.INTERFACE)
                .withClassName(BUILDER.getClassName())
                .withGenericTypes(BUILDER.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(T)
                .withName("build")
                .and()
                .build();

        fluentInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(FLUENT.getClassName())
                .withGenericTypes(FLUENT.getGenericTypes())
                .and()
                .build();

        baseFluentClass = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.CLASS)
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
                    .withReturnType(typeGenericOf(ARRAY_LIST, typeExtends(Q,T)))
                    .addNewArgument()
                        .withType(typeGenericOf(LIST, typeExtends(Q, typeGenericOf(builderInterface.getType(), T))))
                        .withName("list")
                    .endArgument()
                .addToAttributes(BODY, loadResourceQuietly(BUILD_LIST_SNIPPET))
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                        .withName("build")
                        .withReturnType(typeGenericOf(LINKED_HASH_SET,  typeExtends(Q,T)))
                        .addNewArgument()
                            .withType(typeGenericOf(LINKED_HASH_SET, typeExtends(Q, typeGenericOf(builderInterface.getType(), T))))
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
                        .withType(typeGenericOf(LIST, typeExtends(Q, T)))
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
                .withType(typeGenericOf(SET, typeExtends(Q, T)))
                        .withName("...sets")
                    .endArgument()
                    .addToAttributes(BODY, loadResourceQuietly(AGGREGATE_SET_SNIPPET))
                .and()
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

        nestedInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(NESTED.getClassName())
                .withGenericTypes(NESTED.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(N)
                .withName("and")
                .and()
                .build();

        editableInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(EDITABLE.getClassName())
                .withGenericTypes(EDITABLE.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(T)
                .withName("edit")
                .and()
                .build();

        visitableBuilderInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(builderPackage)
                .withClassName(VISITABLE_BUILDER.getClassName())
                .addToInterfaces(visitableInterface.getType())
                .addToInterfaces(builderInterface.getType())
                .withGenericTypes(VISITABLE_BUILDER.getGenericTypes())
                .and()
                .build();

        inlineableBase = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
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

    public JavaClazz getBaseFluentClass() {
        return baseFluentClass;
    }

    public JavaClazz getFluentInterface() {
        return fluentInterface;
    }

    public JavaClazz getFunctionInterface() {
        return functionInterface;
    }

    public JavaClazz getBuilderInterface() {
        return builderInterface;
    }

    public JavaClazz getNestedInterface() {
        return nestedInterface;
    }

    public JavaClazz getEditableInterface() {
        return editableInterface;
    }

    public JavaClazz getVisitableInterface() {
        return visitableInterface;
    }

    public JavaClazz getVisitableBuilderInterface() {
        return visitableBuilderInterface;
    }

    public JavaClazz getVisitorInterface() {
        return visitorInterface;
    }

    public JavaClazz getInlineableBase() {
        return inlineableBase;
    }

    public JavaClazz getInlineableInterface(Inline inline) {
        return new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
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

    public Function<String, JavaType> getStringJavaTypeFunction() {
        return stringJavaTypeFunction;
    }

    public Function<VariableElement, JavaProperty> getVariableElementJavaPropertyFunction() {
        return variableElementJavaPropertyFunction;
    }

    public Function<ExecutableElement, JavaMethod> getExecutableElementToJavaMethod() {
        return executableElementToJavaMethod;
    }

    public Function<TypeElement, JavaClazz> getTypeElementToJavaClazz() {
        return typeElementToJavaClazz;
    }

    public Function<String, JavaClazz> getStringToJavaClazz() {
        return stringToJavaClazz;
    }
}
