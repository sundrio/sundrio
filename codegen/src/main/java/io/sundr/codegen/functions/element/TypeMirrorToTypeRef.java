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

package io.sundr.codegen.functions.element;

import java.util.function.Function;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.NoType;
import javax.lang.model.type.TypeMirror;

import io.sundr.codegen.CodegenContext;
import io.sundr.codegen.converters.TypeRefTypeVisitor;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;

public class TypeMirrorToTypeRef implements Function<TypeMirror, TypeRef> {

  private final ElementContext context;

  public TypeMirrorToTypeRef(ElementContext context) {
    this.context = context;
  }

  @Override
  public TypeRef apply(TypeMirror item) {
    if (item instanceof NoType) {
      return new VoidRef();
    }

    Element element = CodegenContext.getContext().getTypes().asElement(item);
    TypeRef typeRef = item.accept(new TypeRefTypeVisitor(context), 0);
    if (typeRef instanceof ClassRef && element instanceof TypeElement) {
      TypeElement typeElement = (TypeElement) element;
      String fqcn = typeElement.toString();
      context.getReferences().add((TypeElement) element);
      return new ClassRefBuilder((ClassRef) typeRef).withNewFullyQualifiedName(fqcn).build();
    }
    return typeRef;
  }
}
