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

import java.io.File;
import java.util.function.Function;

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterContext;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class SourceFileAdapter implements Adapter<File, Object, Object, Object> {

  private final AdapterContext context;
  private final Function<File, TypeDef> typeAdapterFunction;
  private final Function<Object, TypeRef> referenceAdapterFunction;
  private final Function<Object, Property> propertyAdapterFunction;
  private final Function<Object, Method> methodAdapterFunction;

  @Override
  public Function<File, TypeDef> getTypeAdapterFunction() {
    return typeAdapterFunction;
  }

  public SourceFileAdapter(AdapterContext context) {
    this.context = context;
    this.typeAdapterFunction = new FileToTypeDef(context);
    this.referenceAdapterFunction = null;
    this.propertyAdapterFunction = null;
    this.methodAdapterFunction = null;
  }

  public Function<Object, TypeRef> getReferenceAdapterFunction() {
    return referenceAdapterFunction;
  }

  public Function<Object, Property> getPropertyAdapterFunction() {
    return propertyAdapterFunction;
  }

  public Function<Object, Method> getMethodAdapterFunction() {
    return methodAdapterFunction;
  }

}
