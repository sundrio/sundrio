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

package io.sundr.adapter.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class ReflectionAdapter implements Adapter<Class, Type, Field, Method> {

  private final AdapterContext context;
  private final Function<Class, TypeDef> typeAdapterFunction;
  private final Function<Type, TypeRef> referenceAdapterFunction;
  private final Function<Field, Property> propertyAdapterFunction;
  private final Function<Method, io.sundr.model.Method> methodAdapterFunction;
  private final Set<Class> references = new HashSet<>();

  @Override
  public Function<Class, TypeDef> getTypeAdapterFunction() {
    return typeAdapterFunction;
  }

  public ReflectionAdapter(AdapterContext context) {
    this.context = context;
    this.referenceAdapterFunction = new TypeToTypeRef(references);
    this.typeAdapterFunction = new ClassToTypeDef(context, references, referenceAdapterFunction,
        new TypeToTypeParamDef(referenceAdapterFunction),
        new AnnotationClassToAnnotationRef(referenceAdapterFunction),
        new ClassToKind());
    this.propertyAdapterFunction = null;
    this.methodAdapterFunction = null;
  }

  public Function<Type, TypeRef> getReferenceAdapterFunction() {
    return referenceAdapterFunction;
  }

  public Function<Field, Property> getPropertyAdapterFunction() {
    return propertyAdapterFunction;
  }

  public Function<Method, io.sundr.model.Method> getMethodAdapterFunction() {
    return methodAdapterFunction;
  }
}
