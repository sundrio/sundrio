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

package io.sundr.model.functions;

import java.util.HashSet;

import io.sundr.model.ClassRef;
import io.sundr.model.Node;
import io.sundr.model.TypeDef;

public class TypeAssignable {

  private final TypeDef t;

  public TypeAssignable(TypeDef t) {
    this.t = t;
  }

  public Boolean from(TypeDef other) {
    return from(other, new HashSet<>());
  }

  public Boolean from(TypeDef other, HashSet<String> visited) {
    if (other.getFullyQualifiedName().equals(Node.JAVA_LANG_OBJECT)) {
      return false;
    }
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

    if (visited.contains(other.getFullyQualifiedName())) {
      return false;
    }
    visited.add(t.getFullyQualifiedName());

    for (ClassRef e : other.getExtendsList()) {
      if (t.getFullyQualifiedName().equals(e.getFullyQualifiedName())) {
        return true;
      } else if (from(GetDefinition.of(e), visited)) {
        //This can be a little confusing, so we practically check if the TypeDef t is assignable from the definition of the candidate superclass
        return true;
      }
    }

    for (ClassRef i : other.getImplementsList()) {
      if (t.getFullyQualifiedName().equals(i.getFullyQualifiedName())) {
        return true;
      }
      if (from(GetDefinition.of(i), visited)) {
        //This can be a little confusing, so we practically check if the TypeDef t is assignable from the definition of the candidate interface
        return true;
      }
    }

    return false;
  }
}
