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

public class JavaClazzBuilder extends JavaClazzFluent<JavaClazzBuilder> implements Builder<JavaClazz> {

    JavaClazzFluent<?> fluent;

    public JavaClazzBuilder() {
        this.fluent = this;
    }

    public JavaClazzBuilder(JavaClazzFluent<?> fluent) {
        this.fluent = fluent;
    }

    public JavaClazzBuilder(JavaClazzFluent<?> fluent, JavaClazz instance) {
        this.fluent = fluent;
        fluent.withType(instance.getType());
        fluent.withConstructors(instance.getConstructors());
        fluent.withMethods(instance.getMethods());
        fluent.withFields(instance.getFields());
        fluent.withImports(instance.getImports());
        fluent.withAttributes(instance.getAttributes());
        fluent.withNested(instance.getNested());
    }

    public JavaClazzBuilder(JavaClazz instance) {
        this.fluent = this;
        this.withType(instance.getType());
        this.withConstructors(instance.getConstructors());
        this.withMethods(instance.getMethods());
        this.withFields(instance.getFields());
        this.withImports(instance.getImports());
        this.withAttributes(instance.getAttributes());
        this.withNested(instance.getNested());
    }

    public EditableJavaClazz build() {
        EditableJavaClazz buildable = new EditableJavaClazz(fluent.getType(), fluent.getConstructors(), fluent.getMethods(), fluent.getFields(), fluent.getImports(), fluent.getAttributes(), fluent.getNested());
        validate(buildable);
        return buildable;

    }

    private <T> void validate(T item) {
    }


}
    