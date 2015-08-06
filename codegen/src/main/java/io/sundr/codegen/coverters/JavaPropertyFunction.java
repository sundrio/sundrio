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

package io.sundr.codegen.coverters;

import io.sundr.Function;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;

public class JavaPropertyFunction implements Function<VariableElement, JavaProperty> {

    private final Function<String, JavaType> toType;

    public JavaPropertyFunction(Function<String, JavaType> toType) {
        this.toType = toType;
    }

    @Override
    public JavaProperty apply(final VariableElement variableElement) {
        String name = variableElement.getSimpleName().toString();
        boolean isArray = variableElement.asType().toString().endsWith("[]");
        JavaType type = new JavaTypeBuilder(toType.apply(variableElement.asType().toString())).withArray(isArray).build();
        return new JavaPropertyBuilder()
                .withName(name)
                .withType(type)
                .withArray(isArray)
                .withModifiers(variableElement.getModifiers())
                .build();
    }

}
