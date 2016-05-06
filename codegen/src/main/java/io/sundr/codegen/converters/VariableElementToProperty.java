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
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.TypeRefBuilder;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class VariableElementToProperty implements Function<VariableElement, Property> {

    private final static String ARRAY_DELIM = "[]";

    private final Function<Collection<Modifier>, Integer> modifiersToInt;
    private final Function<TypeMirror, TypeRef> toTypeRef;

    public VariableElementToProperty(Function<TypeMirror, TypeRef> toTypeRef) {
        this(toTypeRef, new ModifiersToInt());
    }

    public VariableElementToProperty(Function<TypeMirror, TypeRef> toTypeRef, Function<Collection<Modifier>, Integer> modifiersToInt) {
        this.toTypeRef = toTypeRef;
        this.modifiersToInt = modifiersToInt;
    }

    public Property apply(final VariableElement variableElement) {
        String name = variableElement.getSimpleName().toString();

        String elementType = variableElement.asType().toString();
        StringTokenizer tokenizer = new StringTokenizer(elementType, ARRAY_DELIM);
        int dimension = tokenizer.countTokens() - 1;

        TypeRef type = new TypeRefBuilder(toTypeRef.apply(variableElement.asType())).withDimensions(dimension).build();

        Set<TypeRef> annotations = new LinkedHashSet<TypeRef>();
        for (AnnotationMirror annotationMirror : variableElement.getAnnotationMirrors()) {
            TypeRef annotationType = toTypeRef.apply(annotationMirror.getAnnotationType());
            annotations.add(annotationType);
        }
        return new PropertyBuilder()
                .withName(name)
                .withTypeRef(type)
                .withAnnotations(annotations)
                .withModifiers(modifiersToInt.apply(variableElement.getModifiers()).intValue())
                .build();
    }
}
