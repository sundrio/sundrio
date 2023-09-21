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

package io.sundr.model.visitors;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.sundr.builder.Builder;
import io.sundr.builder.Visitor;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.ClassRefFluent;
import io.sundr.model.MethodBuilder;
import io.sundr.model.PropertyBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;

public class ReplacePackage implements Visitor<Builder> {

  private final String target;
  private final String replacement;
  private final Visitor<ClassRefFluent<?>> visitor;

  public ReplacePackage(String target, String replacement) {
    this.target = target;
    this.replacement = replacement;
    this.visitor = new Visitor<ClassRefFluent<?>>() {
      @Override
      public void visit(ClassRefFluent<?> ref) {
        ref.withFullyQualifiedName(ref.getFullyQualifiedName().replaceFirst(target, replacement));
      }
    };
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
    if (builder.buildReturnType() instanceof ClassRef) {
      ClassRefBuilder classRefBuilder = new ClassRefBuilder((ClassRef) builder.buildReturnType());
      builder.withReturnType(classRefBuilder.accept(visitor, this).build());
    }
  }

  private void visitPropertyBuilder(PropertyBuilder builder) {

    if (builder.buildTypeRef() instanceof ClassRef) {
      ClassRefBuilder classRefBuilder = new ClassRefBuilder((ClassRef) builder.buildTypeRef());
      builder.withTypeRef(classRefBuilder.accept(visitor, this).build());
    }
  }

  private void visitClassRefBuilder(ClassRefBuilder builder) {
    builder.withFullyQualifiedName(builder.getFullyQualifiedName().replace(target, replacement)).accept(visitor);

    List<TypeRef> updatedArguments = new ArrayList<TypeRef>();
    for (TypeRef arg : builder.buildArguments()) {
      if (arg instanceof ClassRef && ((ClassRef) arg).getPackageName().equals(target)) {
        updatedArguments.add(new ClassRefBuilder((ClassRef) arg).accept(visitor).build());
      } else {
        updatedArguments.add(arg);
      }
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
        if (target.equals(classRef.getPackageName())) {
          updatedImports.add(new ClassRefBuilder(classRef).accept(this).build());
        } else {
          updatedImports.add(classRef);
        }
      }
      builder.getAttributes().put(TypeDef.ALSO_IMPORT, updatedImports);
    }
  }
}
