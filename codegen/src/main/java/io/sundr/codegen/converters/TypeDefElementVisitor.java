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

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.functions.element.ElementContext;
import io.sundr.model.Kind;
import io.sundr.model.TypeDefBuilder;

public class TypeDefElementVisitor implements ElementVisitor<TypeDefBuilder, Void> {

  private final TypeDefBuilder builder = new TypeDefBuilder();

  private final ElementContext context;

  public TypeDefElementVisitor(ElementContext context) {
    this.context = context;
  }

  public TypeDefBuilder visit(Element e, Void aVoid) {
    return builder.withName(e.getSimpleName().toString());
  }

  public TypeDefBuilder visit(Element e) {
    if (e instanceof TypeElement) {
      return new TypeDefBuilder(DefinitionRepository.getRepository()
          .register(new TypeDefBuilder(context.getTypeElementToTypeDef().apply((TypeElement) e)).build()));
    }
    String name = e.getSimpleName().toString();
    builder.withName(name);
    if (e.getKind() == ElementKind.INTERFACE) {
      builder.withKind(Kind.INTERFACE);
    } else if (e.getKind() == ElementKind.ENUM) {
      builder.withKind(Kind.ENUM);
    } else if (e.getKind() == ElementKind.ANNOTATION_TYPE) {
      builder.withKind(Kind.ANNOTATION);
    } else {
      builder.withKind(Kind.CLASS);
    }

    if (e.getEnclosingElement() instanceof PackageElement) {
      String packageName = e.getEnclosingElement().toString();
      builder.withPackageName(packageName);
    }
    return builder;
  }

  public TypeDefBuilder visitPackage(PackageElement e, Void aVoid) {
    return builder.withPackageName(e.getQualifiedName().toString());
  }

  public TypeDefBuilder visitType(TypeElement e, Void aVoid) {
    return builder.withName(e.getSimpleName().toString());
  }

  public TypeDefBuilder visitVariable(VariableElement e, Void aVoid) {
    return builder.addToProperties(context.getVariableElementToProperty().apply(e));
  }

  public TypeDefBuilder visitExecutable(ExecutableElement e, Void aVoid) {
    return builder.addToMethods(context.getExecutableElementToMethod().apply(e));
  }

  public TypeDefBuilder visitTypeParameter(TypeParameterElement e, Void aVoid) {
    return builder.addToParameters(context.getTypeParamElementToTypeParamDef().apply(e));
  }

  public TypeDefBuilder visitUnknown(Element e, Void aVoid) {
    return builder;
  }

}
