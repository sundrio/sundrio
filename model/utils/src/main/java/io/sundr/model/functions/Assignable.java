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
import io.sundr.model.PrimitiveRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;

public class Assignable {

  public static RefAssignable isAssignable(TypeRef type) {
    return new RefAssignable(type);
  }

  public static DefAssignable isAssignable(TypeDef type) {
    return new DefAssignable(type);
  }

  public static class RefAssignable {

    public final TypeRef type;

    public RefAssignable(TypeRef type) {
      this.type = type;
    }

    public boolean from(TypeRef other) {
      if (type instanceof ClassRef) {
        return new ClassAssignable((ClassRef) type).from(other);
      } else if (type instanceof PrimitiveRef) {
        return new PrimitiveAssignable((PrimitiveRef) type).from(other);
      } else if (type instanceof TypeParamRef) {
        return type.equals(other);
      }
      return false;
    }
  }

  public static class DefAssignable {

    public final TypeDef type;

    public DefAssignable(TypeDef type) {
      this.type = type;
    }

    public boolean from(TypeDef other) {
      return new TypeAssignable(type).from(other);
    }
  }
}
