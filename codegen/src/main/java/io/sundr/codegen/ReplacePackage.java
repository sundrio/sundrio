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

package io.sundr.codegen;

import io.sundr.builder.Builder;
import io.sundr.builder.Visitor;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeRef;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ReplacePackage implements Visitor<Builder> {

    private final String target;
    private final String replacement;

    public ReplacePackage(String target, String replacement) {
        this.target = target;
        this.replacement = replacement;
    }

    public void visit(Builder builder) {
        if (builder instanceof TypeDefBuilder) {
            visitTypeDefBuilder((TypeDefBuilder) builder);
        } else if (builder instanceof ClassRefBuilder) {
            visitClassRefBuilder((ClassRefBuilder) builder);
        } else if (builder instanceof PropertyBuilder) {
            visitPropertyBuilder((PropertyBuilder) builder);
        } else if (builder instanceof MethodBuilder) {
            visitMethodBuilder((MethodBuilder) builder);
        }
    }

    private void visitMethodBuilder(MethodBuilder builder) {
        if (builder.getReturnType() instanceof ClassRef) {
            ClassRefBuilder classRefBuilder = new ClassRefBuilder((ClassRef) builder.getReturnType());
            if (classRefBuilder.getDefinition() != null && classRefBuilder.getDefinition().getPackageName() != null && classRefBuilder.getDefinition().getPackageName().equals(target)) {
                classRefBuilder.editDefinition().withPackageName(replacement).endDefinition();
            }
            builder.withReturnType(classRefBuilder.accept(this).build());
        }
    }

    private void visitPropertyBuilder(PropertyBuilder builder) {

        if (builder.getTypeRef() instanceof ClassRef) {
            ClassRefBuilder classRefBuilder = new ClassRefBuilder((ClassRef) builder.getTypeRef());
            if (classRefBuilder.getDefinition() != null && classRefBuilder.getDefinition().getPackageName() != null && classRefBuilder.getDefinition().getPackageName().equals(target)) {
                classRefBuilder.editDefinition().withPackageName(replacement).endDefinition();
            }
            builder.withTypeRef(classRefBuilder.accept(this).build());
        }
    }

    private void visitClassRefBuilder(ClassRefBuilder builder) {
        if (builder.getDefinition() != null && builder.getDefinition().getPackageName() != null && builder.getDefinition().getPackageName().equals(target)) {
            builder.editDefinition().withPackageName(replacement).endDefinition();
        }

        List<TypeRef> updatedArguments = new ArrayList<TypeRef>();
        for (TypeRef arg : builder.getArguments()) {
            if (arg instanceof ClassRef && ((ClassRef) arg).getDefinition().getPackageName().equals(target)) {
                updatedArguments.add(new ClassRefBuilder((ClassRef) arg).editDefinition().withPackageName(replacement).endDefinition().build());
            }
            updatedArguments.add(arg);
        }
        builder.withArguments(updatedArguments);
    }

    private void visitTypeDefBuilder(TypeDefBuilder builder) {
        if (target.equals(builder.getPackageName())) {
            builder.withPackageName(replacement);
        }
        if (builder.getAttributes().containsKey(TypeDef.ALSO_IMPORT)) {
            Set<ClassRef> updatedImports = new LinkedHashSet<ClassRef>();

            for (ClassRef classRef : (Set<ClassRef>) builder.getAttributes().get(TypeDef.ALSO_IMPORT)) {
                if (target.equals(classRef.getDefinition().getPackageName())) {
                    updatedImports.add(new ClassRefBuilder(classRef).accept(this).build());
                } else {
                    updatedImports.add(classRef);
                }
            }
            builder.getAttributes().put(TypeDef.ALSO_IMPORT, updatedImports);
        }
    }


}
