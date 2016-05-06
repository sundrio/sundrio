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
import io.sundr.codegen.model.TypeRef;

import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVisitor;

public class TypeMirrorToTypeRef implements Function<TypeMirror, TypeRef> {

    private final TypeVisitor<TypeRef, Integer> visitor;

    public TypeMirrorToTypeRef(TypeVisitor<TypeRef, Integer> visitor) {
        this.visitor = visitor;
    }

    public TypeRef apply(TypeMirror item) {
        return item.accept(visitor, 0);
    }
}
