/*
 *      Copyright 2018 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.swagger.experimental;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import io.sundr.codegen.functions.GetDefinition;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeRef;

public class SwaggerRepository {

  private final Map<String, TypeDef> apis = new HashMap<String, TypeDef>();
  private final Map<String, TypeDef> models = new HashMap<String, TypeDef>();

  public TypeDef registerApi(TypeDef api) {
    if (api != null) {
      apis.put(api.getFullyQualifiedName(), api);
    }
    return api;
  }

  public Set<TypeDef> getApis() {
    return Collections.unmodifiableSet(new LinkedHashSet<TypeDef>(apis.values()));
  }

  public TypeDef getApi(TypeRef type) {
    if (type instanceof ClassRef) {
      return apis.get(((ClassRef) type).getFullyQualifiedName());
    }
    return null;
  }

  public boolean isApi(TypeDef type) {
    return type != null && apis.containsKey(type.getFullyQualifiedName());
  }

  public boolean isApi(TypeRef type) {
    if (type instanceof ClassRef) {
      return isApi(GetDefinition.of((ClassRef) type));
    }
    return false;
  }

  public TypeDef registerModel(TypeDef model) {
    if (model != null) {
      models.put(model.getFullyQualifiedName(), model);
    }
    return model;
  }

  public Set<TypeDef> getModels() {
    return Collections.unmodifiableSet(new LinkedHashSet<TypeDef>(models.values()));
  }

  public TypeDef getModel(TypeRef type) {
    if (type instanceof ClassRef) {
      return models.get(((ClassRef) type).getFullyQualifiedName());
    }
    return null;
  }

  public boolean isModel(TypeDef type) {
    return type != null && models.containsKey(type.getFullyQualifiedName());
  }

  public boolean isModel(TypeRef type) {
    if (type instanceof ClassRef) {
      return isModel(GetDefinition.of((ClassRef) type));
    }
    return false;
  }

  public void clear() {
    apis.clear();
    models.clear();
  }
}
