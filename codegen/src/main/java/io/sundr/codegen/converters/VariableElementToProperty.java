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
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeVisitor;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class VariableElementToProperty implements Function<VariableElement, Property> {

    private final TypeVisitor<TypeRef, Integer> typeVisitor;

    public VariableElementToProperty(TypeVisitor<TypeRef, Integer> typeVisitor) {
        this.typeVisitor = typeVisitor;
    }

    public Property apply(final VariableElement variableElement) {
        String name = variableElement.getSimpleName().toString();

        String elementType = variableElement.asType().toString();

        TypeRef type = variableElement.asType().accept(typeVisitor, 0);

        Set<ClassRef> annotations = new LinkedHashSet<ClassRef>();
        for (AnnotationMirror annotationMirror : variableElement.getAnnotationMirrors()) {
            TypeRef annotationType = annotationMirror.getAnnotationType().accept(typeVisitor, 0);
            if (annotationType instanceof ClassRef) {
                annotations.add((ClassRef) annotationType);
            } else {
                throw new IllegalStateException();
            }
        }

        return new PropertyBuilder()
                .withName(name)
                .withTypeRef(type)
                .withAnnotations(annotations)
                .withModifiers(TypeUtils.modifiersToInt(variableElement.getModifiers()))
                .build();
    }
}
