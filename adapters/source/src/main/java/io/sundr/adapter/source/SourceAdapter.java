/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.adapter.source;

import java.util.function.Function;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class SourceAdapter
    implements Adapter<TypeDeclaration, ClassOrInterfaceType, FieldDeclaration, MethodDeclaration> {

  private final AdapterContext context;
  private final Function<TypeDeclaration, TypeDef> typeAdapterFunction;
  private final Function<ClassOrInterfaceType, TypeRef> referenceAdapterFunction;
  private final Function<FieldDeclaration, Property> propertyAdapterFunction;
  private final Function<MethodDeclaration, Method> methodAdapterFunction;

  @Override
  public Function<TypeDeclaration, TypeDef> getTypeAdapterFunction() {
    return typeAdapterFunction;
  }

  public SourceAdapter(AdapterContext context) {
    this.context = context;
    this.typeAdapterFunction = new TypeDeclarationToTypeDef(context);
    this.referenceAdapterFunction = null;
    this.propertyAdapterFunction = null;
    this.methodAdapterFunction = null;
  }

  public Function<ClassOrInterfaceType, TypeRef> getReferenceAdapterFunction() {
    return referenceAdapterFunction;
  }

  public Function<FieldDeclaration, Property> getPropertyAdapterFunction() {
    return propertyAdapterFunction;
  }

  public Function<MethodDeclaration, Method> getMethodAdapterFunction() {
    return methodAdapterFunction;
  }
}
