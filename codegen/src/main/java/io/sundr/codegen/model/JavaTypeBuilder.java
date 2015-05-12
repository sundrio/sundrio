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

    JavaTypeFluent<?> fluent;

    public JavaTypeBuilder() {
        this.fluent = this;
    }

    public JavaTypeBuilder(JavaTypeFluent<?> fluent) {
        this.fluent = fluent;
    }

    public JavaTypeBuilder(JavaTypeFluent<?> fluent, JavaType instance) {
        this.fluent = fluent;
        fluent.withKind(instance.getKind());
        fluent.withPackageName(instance.getPackageName());
        fluent.withClassName(instance.getClassName());
        fluent.withArray(instance.isArray());
        fluent.withCollection(instance.isCollection());
        fluent.withConcrete(instance.isConcrete());
        fluent.withDefaultImplementation(instance.getDefaultImplementation());
        fluent.withSuperClass(instance.getSuperClass());
        fluent.withInterfaces(instance.getInterfaces());
        fluent.withGenericTypes(instance.getGenericTypes());
        fluent.withAttributes(instance.getAttributes());
    }

    public JavaTypeBuilder(JavaType instance) {
        this.fluent = this;
        this.withKind(instance.getKind());
        this.withPackageName(instance.getPackageName());
        this.withClassName(instance.getClassName());
        this.withArray(instance.isArray());
        this.withCollection(instance.isCollection());
        this.withConcrete(instance.isConcrete());
        this.withDefaultImplementation(instance.getDefaultImplementation());
        this.withSuperClass(instance.getSuperClass());
        this.withInterfaces(instance.getInterfaces());
        this.withGenericTypes(instance.getGenericTypes());
        this.withAttributes(instance.getAttributes());
    }

    public EditableJavaType build() {
        EditableJavaType buildable = new EditableJavaType(fluent.getKind(), fluent.getPackageName(), fluent.getClassName(), fluent.isArray(), fluent.isCollection(), fluent.isConcrete(), fluent.getDefaultImplementation(), fluent.getSuperClass(), fluent.getInterfaces(), fluent.getGenericTypes(), fluent.getAttributes());
        validate(buildable);
        return buildable;

    }

    private <T> void validate(T item) {
    }


}
    