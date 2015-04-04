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

    private final JavaClazzFluent fluent;
    
    public JavaClazzBuilder() {
        this.fluent = this;
    }

    public JavaClazzBuilder(JavaClazzFluent fluent) {
        this.fluent = fluent;
    }
    
    public JavaClazzBuilder(JavaClazz instance) {
        this();
        withType(instance.getType());
        withConstructors(instance.getConstructors());
        withMethods(instance.getMethods());
        withFields(instance.getFields());
        withImports(instance.getImports());
        withAttributes(instance.getAttributes());
        withNested(instance.getNested());
    }
    
    public JavaClazz build() {
       return new JavaClazz( fluent.getType(), fluent.getConstructors(), fluent.getMethods(), fluent.getFields(), fluent.getImports(), fluent.getAttributes(), fluent.getNested() );
    }
}