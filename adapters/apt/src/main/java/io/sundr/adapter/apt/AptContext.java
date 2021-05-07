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

package io.sundr.adapter.apt;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.AnnotationRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeRef;
import io.sundr.model.repo.DefinitionRepository;

public class AptContext extends AdapterContext {

  private static AptContext INSTANCE;

  private final Types types;
  private final Elements elements;

  private final Function<TypeMirror, TypeRef> typeMirrorToTypeRef;
  private final Function<TypeElement, TypeDef> typeElementToTypeDef;
  private final Function<AnnotationMirror, AnnotationRef> annotationMirrorToAnnotationRef;
  private final Function<VariableElement, Property> variableElementToProperty;
  private final Function<ExecutableElement, Method> executableElementToMethod;
  private final Function<TypeParameterElement, TypeParamDef> typeParamElementToTypeParamDef;
  private final Set<TypeElement> references = new HashSet<>();

  private AptContext(Elements elements, Types types, DefinitionRepository repository) {
    super(repository);
    this.types = types;
    this.elements = elements;
    this.typeMirrorToTypeRef = new TypeMirrorToTypeRef(this);
    this.typeElementToTypeDef = new TypeElementToTypeDef(this);
    this.annotationMirrorToAnnotationRef = new AnnotationMirrorToAnnotationRef(this);
    this.variableElementToProperty = new VariableElementToProperty(this);
    this.executableElementToMethod = new ExecutableElementToMethod(this);
    this.typeParamElementToTypeParamDef = new TypePrameterElementToTypeParamDef(this);
  }

  public synchronized static AptContext create(Elements elements, Types types, DefinitionRepository repository) {
    INSTANCE = new AptContext(elements, types, repository);
    return INSTANCE;
  }

  public synchronized static AptContext getContext() {
    if (INSTANCE == null) {
      throw new IllegalStateException("CodeGenContext has not been created, yet.");
    }
    return INSTANCE;
  }

  public Types getTypes() {
    return types;
  }

  public Elements getElements() {
    return elements;
  }

  public boolean isDeep() {
    return true;
  }

  public Function<TypeMirror, TypeRef> getTypeMirrorToTypeRef() {
    return typeMirrorToTypeRef;
  }

  public Function<TypeElement, TypeDef> getTypeElementToTypeDef() {
    return typeElementToTypeDef;
  }

  public Function<AnnotationMirror, AnnotationRef> getAnnotationMirrorToAnnotationRef() {
    return annotationMirrorToAnnotationRef;
  }

  public Function<VariableElement, Property> getVariableElementToProperty() {
    return variableElementToProperty;
  }

  public Function<ExecutableElement, Method> getExecutableElementToMethod() {
    return executableElementToMethod;
  }

  public Function<TypeParameterElement, TypeParamDef> getTypeParamElementToTypeParamDef() {
    return typeParamElementToTypeParamDef;
  }

  public Set<TypeElement> getReferences() {
    return this.references;
  }

}
