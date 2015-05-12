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

public class JavaPropertyBuilder extends JavaPropertyFluent<JavaPropertyBuilder> implements Builder<JavaProperty> {

    JavaPropertyFluent<?> fluent;

    public JavaPropertyBuilder() {
        this.fluent = this;
    }

    public JavaPropertyBuilder(JavaPropertyFluent<?> fluent) {
        this.fluent = fluent;
    }

    public JavaPropertyBuilder(JavaPropertyFluent<?> fluent, JavaProperty instance) {
        this.fluent = fluent;
        fluent.withModifiers(instance.getModifiers());
        fluent.withType(instance.getType());
        fluent.withName(instance.getName());
        fluent.withArray(instance.isArray());
        fluent.withAttributes(instance.getAttributes());
    }

    public JavaPropertyBuilder(JavaProperty instance) {
        this.fluent = this;
        this.withModifiers(instance.getModifiers());
        this.withType(instance.getType());
        this.withName(instance.getName());
        this.withArray(instance.isArray());
        this.withAttributes(instance.getAttributes());
    }

    public EditableJavaProperty build() {
        EditableJavaProperty buildable = new EditableJavaProperty(fluent.getModifiers(), fluent.getType(), fluent.getName(), fluent.isArray(), fluent.getAttributes());
        validate(buildable);
        return buildable;

    }

    private <T> void validate(T item) {
    }


}
    