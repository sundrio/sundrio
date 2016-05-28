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

import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.PrimitiveRefBuilder;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRefBuilder;

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
import java.util.ArrayList;
import java.util.List;

public class TypeRefTypeVisitor implements TypeVisitor<TypeRef, Integer> {


    public TypeRef visit(TypeMirror t, Integer dimension) {
        return null;
    }

    public TypeRef visit(TypeMirror t) {
        return null;
    }

    public TypeRef visitPrimitive(PrimitiveType t, Integer dimension) {
        return new PrimitiveRefBuilder()
                .withName(t.toString())
                .withDimensions(dimension)
                .build();
    }

    public TypeRef visitNull(NullType t, Integer dimension) {
        return null;
    }

    public TypeRef visitArray(ArrayType t, Integer dimension) {
        return t.getComponentType().accept(this, dimension + 1);
    }

    public TypeRef visitDeclared(DeclaredType t, Integer dimension) {
        List<TypeRef> arguments = new ArrayList<TypeRef>();
        for (TypeMirror typeMirror : t.getTypeArguments()) {
            TypeRef arg = typeMirror.accept(this, dimension);
            if (arg != null) {
                arguments.add(arg);
            }
        }
        return new ClassRefBuilder()
                .withDefinition(new TypeDefElementVisitor().visit(t.asElement()).build())
                .withDimensions(dimension)
                .withArguments(arguments)
                .build();
    }

    public TypeRef visitError(ErrorType t, Integer dimension) {
        return new ClassRefBuilder().withDefinition(new TypeDefElementVisitor().visit(t.asElement()).build()).build();
    }

    public TypeRef visitTypeVariable(TypeVariable t, Integer dimension) {
        return ElementTo.TYPEVARIABLE_TO_TYPEPARAM_REF.apply(t);
    }

    public TypeRef visitWildcard(WildcardType t, Integer dimension) {
        return null;
    }

    public TypeRef visitExecutable(ExecutableType t, Integer dimension) {
        return null;
    }

    public TypeRef visitNoType(NoType t, Integer dimension) {
        return new VoidRefBuilder().build();
    }

    public TypeRef visitUnknown(TypeMirror t, Integer dimension) {
        return null;
    }

    public TypeRef visitUnion(UnionType t, Integer dimension) {
        return null;
    }

}
