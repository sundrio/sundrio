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

import java.util.Optional;
import java.util.ServiceLoader;
import java.util.stream.StreamSupport;

import io.sundr.model.TypeDef;

public interface TypeLookup<T> {

  public static Optional<TypeDef> lookup(String fullyQualifiedName, AdapterContextAware context) {
    return StreamSupport.stream(ServiceLoader.load(TypeLookup.class, TypeLookup.class.getClassLoader()).spliterator(), false)
        .map(l -> l.forName(fullyQualifiedName))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(t -> Adapters.adaptType(t, context))
        .findFirst();
  }

  /**
   * Performs a lookup for the specified fqcn.
   *
   * @param fullyQualifiedName The specified fqcn.
   * @return The specified type if found, empty otherwise.
   */
  Optional<T> forName(String fullyQualifiedName);

}
