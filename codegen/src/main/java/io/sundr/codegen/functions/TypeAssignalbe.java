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

package io.sundr.codegen.functions;

import java.util.function.Function;

import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Node;
import io.sundr.codegen.model.TypeDef;

public class TypeAssignalbe implements Function<TypeDef, Boolean> {

  private final TypeDef other;

  private TypeAssignalbe(TypeDef other) {
    this.other = other;
  }

  public static TypeAssignalbe from(TypeDef other) {
    return new TypeAssignalbe(other);
  }

  @Override
  public Boolean apply(TypeDef t) {
    if (t == other || t.equals(other)) {
      return true;
    }

    if (t.getFullyQualifiedName().equals(other.getFullyQualifiedName())) {
      return true;
    }

    if (t.getPackageName() == null && Node.JAVA_LANG.equals(other.getPackageName())
        && t.getName().equalsIgnoreCase(other.getName())) {
      return true;
    }
    if (other.getPackageName() == null && Node.JAVA_LANG.equals(t.getPackageName())
        && t.getName().equalsIgnoreCase(other.getName())) {
      return true;
    }

    for (ClassRef e : other.getExtendsList()) {
      //This can be a little confusing, so we practically check if the TypeDef t is assignable from the definition of the candidate superclass
      if (t.map(TypeAssignalbe.from(e.map(GetDefinition.FUNCTION)))) {
        return true;
      }
    }

    for (ClassRef i : other.getImplementsList()) {
      //This can be a little confusing, so we practically check if the TypeDef t is assignable from the definition of the candidate interface
      if (t.map(TypeAssignalbe.from(i.map(GetDefinition.FUNCTION)))) {
        return true;
      }
    }

    return false;

  }

}
