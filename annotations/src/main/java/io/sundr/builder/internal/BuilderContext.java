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

import io.sundr.builder.Builder;
import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;

import static io.sundr.codegen.utils.TypeUtils.newGeneric;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public class BuilderContext {
    
    private static final JavaType T = newGeneric("T");
    private static final JavaType N = newGeneric("N");
    private static final JavaType BASE_BUILDER = typeGenericOf(ClassToJavaType.FUNCTION.apply(Builder.class), T);
    private static final JavaType BASE_FLUENT = typeGenericOf(ClassToJavaType.FUNCTION.apply(Fluent.class), T);
    private static final JavaType BASE_NESTED = typeGenericOf(ClassToJavaType.FUNCTION.apply(Nested.class), N);
    
    private final JavaClazz fluentInterface;
    private final JavaClazz builderInterface;
    private final JavaClazz nestedInterface;
    private final String targetPackage;
    
    private final BuildableRepository repository;

    public BuilderContext(String targetPackage) {
        this.targetPackage = targetPackage;
        
        repository = new BuildableRepository();
        
        builderInterface = new JavaClazzBuilder()
                .withNewType()
                .withPackageName(targetPackage)
                .withKind(JavaKind.INTERFACE)
                .withClassName(BASE_BUILDER.getClassName())
                .withGenericTypes(BASE_BUILDER.getGenericTypes())
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
                .withClassName(BASE_FLUENT.getClassName())
                .withGenericTypes(BASE_FLUENT.getGenericTypes())
                .and()
                .build();

        nestedInterface = new JavaClazzBuilder()
                .withNewType()
                .withKind(JavaKind.INTERFACE)
                .withPackageName(targetPackage)
                .withClassName(BASE_NESTED.getClassName())
                .withGenericTypes(BASE_NESTED.getGenericTypes())
                .and()
                .addNewMethod()
                .withReturnType(N)
                .withName("and")
                .and()
                .build();
    }

    public String getTargetPackage() {
        return targetPackage;
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

    public BuildableRepository getRepository() {
        return repository;
    }
}
