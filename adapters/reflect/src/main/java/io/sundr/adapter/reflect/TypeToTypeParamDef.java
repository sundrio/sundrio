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

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import io.sundr.model.ClassRef;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeParamDefBuilder;
import io.sundr.model.TypeRef;

public class TypeToTypeParamDef implements Function<Type, TypeParamDef> {

  private final Function<Type, TypeRef> typeToTypeRef;

  public TypeToTypeParamDef(Function<Type, TypeRef> typeToTypeRef) {
    this.typeToTypeRef = typeToTypeRef;
  }

  @Override
  public TypeParamDef apply(Type item) {
    if (item instanceof TypeVariable) {
      TypeVariable typeVariable = (TypeVariable) item;
      String name = typeVariable.getName();
      List<ClassRef> bounds = new ArrayList<ClassRef>();

      for (Type b : typeVariable.getBounds()) {
        if (b instanceof Class) {
          Class c = (Class) b;
          bounds.add((ClassRef) typeToTypeRef.apply(c));
        }
      }
      return new TypeParamDefBuilder().withName(name).withBounds(bounds).build();
    }
    return null;
  }

}
