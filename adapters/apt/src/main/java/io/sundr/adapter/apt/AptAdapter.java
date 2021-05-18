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

import java.util.function.Function;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

import io.sundr.adapter.api.Adapter;
import io.sundr.model.AnnotationRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeRef;

public class AptAdapter implements Adapter<TypeElement, TypeMirror, VariableElement, ExecutableElement> {

  private final AptContext context;
  private final Function<TypeElement, TypeDef> typeAdapterFunction;
  private final Function<TypeMirror, TypeRef> referenceAdapterFunction;
  private final Function<VariableElement, Property> propertyAdapterFunction;
  private final Function<ExecutableElement, Method> methodAdapterFunction;

  private final Function<AnnotationMirror, AnnotationRef> annotationAdapterFunction;
  private final Function<TypeParameterElement, TypeParamDef> typeParamAdapterFunction;

  public AptAdapter(AptContext context) {
    this.context = context;
    this.referenceAdapterFunction = new TypeMirrorToTypeRef(context);
    this.annotationAdapterFunction = new AnnotationMirrorToAnnotationRef(context, referenceAdapterFunction);
    this.typeParamAdapterFunction = new TypePrameterElementToTypeParamDef(context, referenceAdapterFunction);
    this.propertyAdapterFunction = new VariableElementToProperty(context, referenceAdapterFunction, annotationAdapterFunction);
    this.methodAdapterFunction = new ExecutableElementToMethod(context, referenceAdapterFunction, propertyAdapterFunction,
        annotationAdapterFunction);
    this.typeAdapterFunction = new TypeElementToTypeDef(context, referenceAdapterFunction, propertyAdapterFunction,
        methodAdapterFunction, annotationAdapterFunction, typeParamAdapterFunction);
  }

  public Function<TypeElement, TypeDef> getTypeAdapterFunction() {
    return typeAdapterFunction;
  }

  public Function<TypeMirror, TypeRef> getReferenceAdapterFunction() {
    return referenceAdapterFunction;
  }

  public Function<VariableElement, Property> getPropertyAdapterFunction() {
    return propertyAdapterFunction;
  }

  public Function<ExecutableElement, Method> getMethodAdapterFunction() {
    return methodAdapterFunction;
  }
}
