/*
 * Copyright 2016 The original authors.
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
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeRefBuilder;

import javax.lang.model.element.ElementVisitor;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ErrorType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.NoType;
import javax.lang.model.type.NullType;
import javax.lang.model.type.PrimitiveType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;
import javax.lang.model.type.TypeVisitor;
import javax.lang.model.type.UnionType;
import javax.lang.model.type.WildcardType;

public class TypeRefTypeVisitor implements TypeVisitor<TypeRefBuilder, Void > {

    private final Function<TypeVariable, TypeParamRef> toTypeParamRef;

    private ElementVisitor<TypeDefBuilder, Void> elementVisitor;
    private TypeRefBuilder builder = new TypeRefBuilder();

    public TypeRefTypeVisitor(Function<TypeVariable, TypeParamRef> toTypeParamRef) {
        this.toTypeParamRef = toTypeParamRef;
    }

    public TypeRefBuilder visit(TypeMirror t, Void aVoid) {
        return builder;
    }

    public TypeRefBuilder visit(TypeMirror t) {
        return builder;
    }

    public TypeRefBuilder visitPrimitive(PrimitiveType t, Void aVoid) {
        return builder.withNewDefinition().withName(t.toString()).endDefinition();
    }

    public TypeRefBuilder visitNull(NullType t, Void aVoid) {
        return builder;
    }

    public TypeRefBuilder visitArray(ArrayType t, Void aVoid) {
        return builder.withDimensions(builder.getDimensions() + 1);
    }

    public TypeRefBuilder visitDeclared(DeclaredType t, Void aVoid) {
        return builder.withDefinition(elementVisitor.visit(t.asElement()).build());
    }

    public TypeRefBuilder visitError(ErrorType t, Void aVoid) {
        return builder;
    }

    public TypeRefBuilder visitTypeVariable(TypeVariable t, Void aVoid) {
        //TODO: We may need to revisit typeParamRef...
        return builder.addToTypeParamRefArguments(toTypeParamRef.apply(t));
    }

    public TypeRefBuilder visitWildcard(WildcardType t, Void aVoid) {
        return builder;
    }

    public TypeRefBuilder visitExecutable(ExecutableType t, Void aVoid) {
        return builder;
    }

    public TypeRefBuilder visitNoType(NoType t, Void aVoid) {
        return builder.withNewDefinition().withName(t.toString()).endDefinition();
    }

    public TypeRefBuilder visitUnknown(TypeMirror t, Void aVoid) {
        return builder;
    }

    public TypeRefBuilder visitUnion(UnionType t, Void aVoid) {
        return builder;
    }

    public ElementVisitor<TypeDefBuilder, Void> getElementVisitor() {
        return elementVisitor;
    }

    public void setElementVisitor(ElementVisitor<TypeDefBuilder, Void> elementVisitor) {
        this.elementVisitor = elementVisitor;
    }
}
