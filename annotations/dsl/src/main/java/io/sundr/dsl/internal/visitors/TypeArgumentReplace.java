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

package io.sundr.dsl.internal.visitors;

import io.sundr.builder.TypedVisitor;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.TypeRef;

import java.util.ArrayList;
import java.util.List;

public class TypeArgumentReplace extends TypedVisitor<ClassRefBuilder> {

    private final TypeRef target;
    private final TypeRef replacement;

    public TypeArgumentReplace(TypeRef target, TypeRef replacement) {
        this.target = target;
        this.replacement = replacement;
    }

    public void visit(ClassRefBuilder builder) {
        List<TypeRef> updated = new ArrayList<TypeRef>();
        for (TypeRef typeArgument : builder.getArguments()) {
            if (typeArgument.equals(target)) {
                updated.add(replacement);
            } else {
                updated.add(typeArgument);
            }
        }
    }
}
