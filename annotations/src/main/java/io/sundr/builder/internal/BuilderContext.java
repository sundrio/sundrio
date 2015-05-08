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

import io.sundr.Function;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaProperty;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaType;
import io.sundr.codegen.coverters.JavaClazzFunction;
import io.sundr.codegen.coverters.JavaMethodFunction;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import static io.sundr.builder.Constants.ARRAY_LIST;
import static io.sundr.builder.Constants.B;
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
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public class BuilderContext {

    private final Elements elements;
            
    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final JavaMethodFunction toMethod;
    private final JavaClazzFunction toClazz;

    private final JavaClazz baseFluentClass;
    private final JavaClazz fluentInterface;
    private final JavaClazz builderInterface;
    private final JavaClazz nestedInterface;
    private final JavaClazz editableInterface;
    private final String targetPackage;
    
    private final BuildableRepository repository;

    public BuilderContext(Elements elements, String targetPackage) {
        this.elements = elements;
        this.targetPackage = targetPackage;

        toType = new ToBuildableJavaType(elements);
        toProperty = new ToBuildableJavaProperty(toType);
        toMethod = new JavaMethodFunction(toType, toProperty);
        toClazz = new JavaClazzFunction(elements, toType, toMethod, toProperty);
        
        repository = new BuildableRepository();
        
        builderInterface = new JavaClazzBuilder()
                .withNewType()
                .withPackageName(targetPackage)
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
                .withPackageName(targetPackage)
                .withClassName(FLUENT.getClassName())
                .withGenericTypes(FLUENT.getGenericTypes())
                .and()
                .build();

        baseFluentClass = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.CLASS)
                .withPackageName(targetPackage)
                .withClassName(BASE_FLUENT.getClassName())
                .withGenericTypes(BASE_FLUENT.getGenericTypes())
                .addToInterfaces(fluentInterface.getType())
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                    .withName("build")
                    .withReturnType(typeGenericOf(ARRAY_LIST, T))
                    .addNewArgument()
                        .withType(typeGenericOf(LIST, typeGenericOf(BUILDER, T)))
                        .withName("list")
                    .endArgument()
                    .addToAttributes(BODY, "ArrayList<T> r = new ArrayList<>();for (Builder<T> b : list) {r.add(b.build());}return r;")
                .and()
                .addNewMethod()
                    .addToTypeParameters(T)
                    .addToModifiers(Modifier.PUBLIC)
                    .withName("build")
                    .withReturnType(typeGenericOf(LINKED_HASH_SET, T))
                    .addNewArgument()
                        .withType(typeGenericOf(LINKED_HASH_SET, typeGenericOf(BUILDER, T)))
                        .withName("set")
                    .endArgument()
                    .addToAttributes(BODY, "LinkedHashSet<T> r = new LinkedHashSet<>();for (Builder<T> b : set) {r.add(b.build());}return r;")
                .and()
                .build();

        nestedInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(targetPackage)
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
                .withPackageName(targetPackage)
                .withClassName(EDITABLE.getClassName())
                .withGenericTypes(EDITABLE.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(B)
                .withName("edit")
                .and()
                .build();

    }

    public Elements getElements() {
        return elements;
    }

    public String getTargetPackage() {
        return targetPackage;
    }

    public JavaClazz getBaseFluentClass() {
        return baseFluentClass;
    }

    public JavaClazz getFluentInterface() {
        return fluentInterface;
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

    public BuildableRepository getRepository() {
        return repository;
    }

    public Function<String, JavaType> getToType() {
        return toType;
    }

    public Function<VariableElement, JavaProperty> getToProperty() {
        return toProperty;
    }

    public JavaMethodFunction getToMethod() {
        return toMethod;
    }

    public JavaClazzFunction getToClazz() {
        return toClazz;
    }
}
