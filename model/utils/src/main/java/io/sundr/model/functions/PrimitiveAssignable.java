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
import io.sundr.model.TypeRef;

public class PrimitiveAssignable {

  private final PrimitiveRef t;

  public PrimitiveAssignable(PrimitiveRef t) {
    this.t = t;
  }

  public Boolean from(TypeRef other) {
    if (t == other) {
      return true;
    } else if (other == null) {
      return false;
    } else if (other instanceof ClassRef) {
      if (!((ClassRef) other).getPackageName().equals(Node.JAVA_LANG)) {
        return false;
      }
      if (!((ClassRef) other).getName().toUpperCase().startsWith(t.getName().toUpperCase())) {
        return false;
      }
      return true;
    }

    if (other == null || t.getClass() != other.getClass())
      return false;

    PrimitiveRef that = (PrimitiveRef) other;

    if (t.getDimensions() != that.getDimensions())
      return false;
    return t.getName() != null ? t.getName().equals(that.getName()) : that.getName() == null;

  }

}
