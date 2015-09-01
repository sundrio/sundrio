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

package io.sundr.codegen.converters;

import io.sundr.Function;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

public class JavaMethodFunction implements Function<ExecutableElement, JavaMethod> {

    private final Function<String, JavaType> toJavaType;
    private final Function<VariableElement, JavaProperty> toJavaProperty;

    public JavaMethodFunction(Function<String, JavaType> toJavaType, Function<VariableElement, JavaProperty> toJavaProperty) {
        this.toJavaType = toJavaType;
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaMethod apply(ExecutableElement executableElement) {
        JavaMethodBuilder methodBuilder = new JavaMethodBuilder()
                .withName(executableElement.getSimpleName().toString())
                .withReturnType(toJavaType.apply(executableElement.getReturnType().toString()));
        //Populate constructor parameters
        for (VariableElement variableElement : executableElement.getParameters()) {
            methodBuilder = methodBuilder
                    .withName(executableElement.getSimpleName().toString())
                    .withReturnType(toJavaType.apply(executableElement.getReturnType().toString()))
                    .addToArguments(toJavaProperty.apply(variableElement));
            for (TypeMirror thrownType : executableElement.getThrownTypes()) {
                methodBuilder = methodBuilder.addToExceptions(toJavaType.apply(thrownType.toString()));
            }
        }
        return methodBuilder.build();
    }
}
