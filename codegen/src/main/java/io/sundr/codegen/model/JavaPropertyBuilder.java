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

    private final JavaPropertyFluent<?> fluent;

    public JavaPropertyBuilder() {
        this.fluent = this;
    }

    public JavaPropertyBuilder(JavaPropertyFluent<?> fluent) {
        this.fluent = fluent;
    }

    public JavaPropertyBuilder(JavaProperty instance) {
        this();
        withType(instance.getType());
        withName(instance.getName());
        withAttributes(instance.getAttributes());
        withArray(instance.isArray());
    }

    public JavaProperty build() {
        return new JavaProperty(fluent.getType(), fluent.getName(), fluent.getAttributes(), fluent.isArray());

    }
}
    