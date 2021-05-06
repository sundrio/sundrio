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

import io.sundr.model.ClassRef;
import io.sundr.model.Node;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class ClassAssignable {

  private final ClassRef t;

  public ClassAssignable(ClassRef t) {
    this.t = t;
  }

  public Boolean from(TypeRef other) {
    if (t == other || t.equals(other)) {
      return true;
    } else if (other == null) {
      return false;
    } else if (other instanceof PrimitiveRef) {
      TypeDef definition = GetDefinition.of(t);
      if (definition == null) {
        return false;
      }

      if (definition != null && !Node.JAVA_LANG.equals(definition.getPackageName())) {
        return false;
      }

      if (!t.getName().toUpperCase().startsWith(((PrimitiveRef) other).getName().toUpperCase())) {
        return false;
      }
      return true;
    }

    if (!(other instanceof ClassRef)) {
      return false;
    }
    ClassRef otherClassRef = (ClassRef) other;

    if (otherClassRef.getFullyQualifiedName().equals(t.getFullyQualifiedName())) {
      return true;
    }

    TypeDef definition = GetDefinition.of(t);
    TypeDef otherDefinition = GetDefinition.of(otherClassRef);
    return Assignable.isAssignable(definition).from(otherDefinition);
  }
}
