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

package io.sundr.adapter.api;

import java.util.function.Function;

import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public interface Adapter<T, R, P, M> {

  Function<T, TypeDef> getTypeAdapterFunction();

  Function<R, TypeRef> getReferenceAdapterFunction();

  Function<M, Method> getMethodAdapterFunction();

  Function<P, Property> getPropertyAdapterFunction();

  default TypeDef adaptType(T input) {
    return getTypeAdapterFunction().apply(input);
  }

  default Method adaptMethod(M method) {
    return getMethodAdapterFunction().apply(method);
  }

  default Property adaptProperty(P property) {
    return getPropertyAdapterFunction().apply(property);
  }

  default TypeRef adaptReference(R ref) {
    return getReferenceAdapterFunction().apply(ref);
  }
}
