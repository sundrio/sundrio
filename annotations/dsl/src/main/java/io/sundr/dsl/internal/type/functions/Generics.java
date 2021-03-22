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

package io.sundr.dsl.internal.type.functions;

import static io.sundr.dsl.internal.Constants.IS_GENERIC;
import static io.sundr.dsl.internal.Constants.ORIGINAL_REF;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.Constants.TRANSPARENT_REF;
import static io.sundr.dsl.internal.Constants.VOID_REF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.function.Function;
import io.sundr.builder.TypedVisitor;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeRef;

public class Generics {

  private static final String[] GENERIC_NAMES = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
      "P", "Q", "R", "S" };
  private static final Map<TypeRef, TypeParamDef> GENERIC_MAPPINGS = new HashMap<TypeRef, TypeParamDef>();
  private static int counter = 0;

  public static final Function<TypeRef, TypeParamDef> MAP = new Function<TypeRef, TypeParamDef>() {
    public TypeParamDef apply(TypeRef item) {
      if (!GENERIC_MAPPINGS.containsKey(item)) {
        int iteration = counter / GENERIC_NAMES.length;
        String name = GENERIC_NAMES[counter % GENERIC_NAMES.length];
        if (iteration > 0) {
          name += iteration;
        }
        counter++;
        GENERIC_MAPPINGS.put(item, new TypeParamDefBuilder().withName(name)
            .addToAttributes(IS_GENERIC, true)
            .addToAttributes(ORIGINAL_REF, item)
            .build());
      }
      return GENERIC_MAPPINGS.get(item);
    }
  };

  public static final Function<TypeDef, TypeDef> UNWRAP = new Function<TypeDef, TypeDef>() {

    public TypeDef apply(TypeDef type) {
      return new TypeDefBuilder(type).accept(UNWRAP_CLASSREF_VISITOR).build();
    }
  };

  private static final TypedVisitor<ClassRefBuilder> UNWRAP_CLASSREF_VISITOR = new TypedVisitor<ClassRefBuilder>() {
    public void visit(ClassRefBuilder builder) {
      List<TypeRef> unwrappedArguments = new ArrayList<TypeRef>();
      for (TypeRef argument : builder.getArguments()) {
        TypeRef key = getKeyForValue(GENERIC_MAPPINGS, argument);
        if (TRANSPARENT_REF.equals(key)) {
          continue;
        } else if (key != null) {
          unwrappedArguments.add(key);
        } else if (argument instanceof ClassRef) {
          unwrappedArguments.add(new ClassRefBuilder((ClassRef) argument).accept(UNWRAP_CLASSREF_VISITOR).build());
        } else {
          unwrappedArguments.add(argument);
        }
      }
      builder.withArguments(unwrappedArguments);
    }
  };

  private static boolean containsValue(Map<TypeRef, TypeParamDef> map, TypeRef value) {
    for (Map.Entry<TypeRef, TypeParamDef> entry : map.entrySet()) {
      if (value instanceof TypeParamRef && ((TypeParamRef) value).getName().equals(entry.getValue().getName())) {
        return true;
      }
    }
    return false;
  }

  private static TypeRef getKeyForValue(Map<TypeRef, TypeParamDef> map, TypeRef value) {
    for (Map.Entry<TypeRef, TypeParamDef> entry : map.entrySet()) {
      if (value instanceof TypeParamRef && ((TypeParamRef) value).getName().equals(entry.getValue().getName())) {
        return entry.getKey();
      }
    }
    return null;
    //throw new IllegalStateException("Key not found for value:[" + value +"].");
  }

  public static void clear() {
    counter = 0;
    GENERIC_MAPPINGS.clear();
    GENERIC_MAPPINGS.put(VOID_REF, new TypeParamDefBuilder().withName("V").addToAttributes(IS_GENERIC, true).build());
    GENERIC_MAPPINGS.put(TRANSPARENT_REF, TRANSPARENT);
  }

  static {
    clear();
  }
}
