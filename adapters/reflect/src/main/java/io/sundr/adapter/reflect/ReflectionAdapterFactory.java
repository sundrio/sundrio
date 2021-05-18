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

import io.sundr.adapter.api.Adapter;
import io.sundr.adapter.api.AdapterFactory;

public class ReflectionAdapterFactory implements AdapterFactory<ReflectionContext, Class, Type, Field, Method> {

  @Override
  public Class<ReflectionContext> getContextType() {
    return ReflectionContext.class;
  }

  @Override
  public Adapter<Class, Type, Field, Method> create(ReflectionContext ctx) {
    return new ReflectionAdapter(ctx);
  }

  @Override
  public Class<Class> getTypeAdapterType() {
    return Class.class;
  }

  @Override
  public Class<Type> getReferenceAdapterType() {
    return Type.class;
  }

  @Override
  public Class<Method> getMethodAdapterType() {
    return Method.class;
  }

  @Override
  public Class<Field> getPropertyAdapterType() {
    return Field.class;
  }
}
