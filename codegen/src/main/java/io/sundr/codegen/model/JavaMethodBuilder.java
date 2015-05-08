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

public class JavaMethodBuilder extends JavaMethodFluent<JavaMethodBuilder> implements Builder<JavaMethod> {

    private final JavaMethodFluent<?> fluent;

    public JavaMethodBuilder() {
        this.fluent = this;
    }

    public JavaMethodBuilder(JavaMethodFluent<?> fluent) {
        this.fluent = fluent;
    }

    public JavaMethodBuilder(JavaMethod instance) {
        this();
        withName(instance.getName());
        withReturnType(instance.getReturnType());
        withArguments(instance.getArguments());
        withExceptions(instance.getExceptions());
        withAttributes(instance.getAttributes());
    }

    public JavaMethod build() {
        return new JavaMethod(fluent.getModifiers(), fluent.getTypeParameters(), fluent.getName(), fluent.getReturnType(), fluent.getArguments(), fluent.getExceptions(), fluent.getAttributes());

    }


}
    