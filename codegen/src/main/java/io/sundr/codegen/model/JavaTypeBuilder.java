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

package io.sundr.codegen.model;

import io.sundr.builder.Builder;

public class JavaTypeBuilder extends JavaTypeFluent<JavaTypeBuilder> implements Builder<JavaType> {

    private final JavaTypeFluent<?> fluent;

    public JavaTypeBuilder() {
        this.fluent = this;
    }

    public JavaTypeBuilder(JavaTypeFluent<?> fluent) {
        this.fluent = fluent;
    }

    public JavaTypeBuilder(JavaType instance) {
        this();
        withKind(instance.getKind());
        withPackageName(instance.getPackageName());
        withClassName(instance.getClassName());
        withArray(instance.isArray());
        withCollection(instance.isCollection());
        withConcrete(instance.isConcrete());
        withDefaultImplementation(instance.getDefaultImplementation());
        withSuperClass(instance.getSuperClass());
        withInterfaces(instance.getInterfaces());
        withGenericTypes(instance.getGenericTypes());
        withAttributes(instance.getAttributes());
    }

    public JavaType build() {
        return new JavaType(fluent.getKind(), fluent.getPackageName(), fluent.getClassName(), fluent.isArray(), fluent.isCollection(), fluent.isConcrete(), fluent.getDefaultImplementation(), fluent.getSuperClass(), fluent.getInterfaces(), fluent.getGenericTypes(), fluent.getAttributes());

    }
}
    