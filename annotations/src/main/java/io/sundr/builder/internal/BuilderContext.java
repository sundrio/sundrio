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
import io.sundr.builder.Builder;
import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaProperty;
import io.sundr.builder.internal.functions.overrides.ToBuildableJavaType;
import io.sundr.codegen.coverters.JavaClazzFunction;
import io.sundr.codegen.coverters.JavaMethodFunction;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;

import static io.sundr.codegen.utils.TypeUtils.newGeneric;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public class BuilderContext {
    
    private static final JavaType T = newGeneric("T");
    private static final JavaType N = newGeneric("N");
    private static final JavaType BASE_BUILDER = typeGenericOf(ClassToJavaType.FUNCTION.apply(Builder.class), T);
    private static final JavaType BASE_FLUENT = typeGenericOf(ClassToJavaType.FUNCTION.apply(Fluent.class), T);
    private static final JavaType BASE_NESTED = typeGenericOf(ClassToJavaType.FUNCTION.apply(Nested.class), N);

    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final JavaMethodFunction toMethod;
    private final JavaClazzFunction toClazz;

            
    private final JavaClazz fluentInterface;
    private final JavaClazz builderInterface;
    private final JavaClazz nestedInterface;
    private final String targetPackage;
    
    private final BuildableRepository repository;

    public BuilderContext(Elements elements, String targetPackage) {
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
