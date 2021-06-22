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

package io.sundr.model;

import java.util.List;
import java.util.Map;

public class RichTypeDef extends TypeDef {

  private final List<Property> allProperties;
  private final List<Method> allConstructors;
  private final List<Method> allMethods;

  public RichTypeDef(Kind kind, String packageName, String name, List<String> comments, List<AnnotationRef> annotations,
      List<ClassRef> extendsList, List<ClassRef> implementsList, List<TypeParamDef> parameters,
      List<Property> properties,
      List<Property> allProperties,
      List<Method> constructors,
      List<Method> allConstructors,
      List<Method> methods,
      List<Method> allMethods,
      String outerTypeName, List<TypeDef> innerTypes, int modifiers, Map<AttributeKey, Object> attributes) {

    super(kind, packageName, name, comments, annotations, extendsList, implementsList, parameters, properties, constructors,
        methods, outerTypeName, innerTypes, modifiers, attributes);
    this.allProperties = allProperties;
    this.allConstructors = allConstructors;
    this.allMethods = allMethods;
  }

  public List<Property> getAllProperties() {
    return allProperties;
  }

  public List<Method> getAllConstructors() {
    return allConstructors;
  }

  public List<Method> getAllMethods() {
    return allMethods;
  }
}
