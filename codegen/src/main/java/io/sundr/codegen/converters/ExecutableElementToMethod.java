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
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeRef;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.Collection;

public class ExecutableElementToMethod implements Function<ExecutableElement, Method> {

    private final Function<TypeMirror, TypeRef> toTypeRef;
    private final Function<VariableElement, Property> toProperty;
    private final Function<Collection<Modifier>, Integer> modifiersToInt;

    public ExecutableElementToMethod(Function<TypeMirror, TypeRef> toTypeRef, Function<VariableElement, Property> toProperty, Function<Collection<Modifier>, Integer> modifiersToInt) {
        this.toTypeRef = toTypeRef;
        this.toProperty = toProperty;
        this.modifiersToInt = modifiersToInt;
    }

    public Method apply(ExecutableElement executableElement) {
        MethodBuilder methodBuilder = new MethodBuilder()
                .withModifiers(modifiersToInt.apply(executableElement.getModifiers()))
                .withName(executableElement.getSimpleName().toString())
                .withReturnType(toTypeRef.apply(executableElement.getReturnType()));


        //Populate constructor parameters
        for (VariableElement variableElement : executableElement.getParameters()) {
            methodBuilder = methodBuilder
                    .withName(executableElement.getSimpleName().toString())
                    .withReturnType(toTypeRef.apply(executableElement.getReturnType()))
                    .addToArguments(toProperty.apply(variableElement));

            for (TypeMirror thrownType : executableElement.getThrownTypes()) {
                methodBuilder = methodBuilder.addToExceptions(toTypeRef.apply(thrownType));
            }
        }
        for (AnnotationMirror annotationMirror :executableElement.getAnnotationMirrors()) {
            TypeRef annotationType = toTypeRef.apply(annotationMirror.getAnnotationType());
            methodBuilder.addToAnnotations(annotationType);
        }
        return methodBuilder.build();
    }
}
