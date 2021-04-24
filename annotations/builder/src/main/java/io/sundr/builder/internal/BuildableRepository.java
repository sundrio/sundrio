/*
 * Copyright 2015 The original authors.
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

package io.sundr.builder.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import io.sundr.codegen.functions.GetDefinition;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class BuildableRepository {

  private final Map<String, TypeDef> buildables = new HashMap<String, TypeDef>();

  public TypeDef register(TypeDef buildable) {
    if (buildable != null) {
      buildables.put(buildable.getFullyQualifiedName(), buildable);
    }
    return buildable;
  }

  public Set<TypeDef> getBuildables() {
    return Collections.unmodifiableSet(new LinkedHashSet<TypeDef>(buildables.values()));
  }

  public TypeDef getBuildable(TypeRef type) {
    if (type instanceof ClassRef) {
      return buildables.get(GetDefinition.of((ClassRef) type).getFullyQualifiedName());
    }
    return null;
  }

  public boolean isBuildable(TypeDef type) {
    return type != null && buildables.containsKey(type.getFullyQualifiedName());
  }

  public boolean isBuildable(TypeRef type) {
    if (type instanceof ClassRef) {
      return isBuildable(GetDefinition.of((ClassRef) type));
    }
    return false;
  }

  public void clear() {
    buildables.clear();
  }
}
