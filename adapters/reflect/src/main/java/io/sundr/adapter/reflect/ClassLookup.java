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

import java.util.Optional;

import io.sundr.adapter.api.TypeLookup;

public class ClassLookup implements TypeLookup<Class> {

  @Override
  public Optional<Class> forName(String fullyQualifiedName) {
    try {
      return Optional.of(Class.forName(fullyQualifiedName));
    } catch (Exception e) {
      return Optional.empty();
    }
  }
}
