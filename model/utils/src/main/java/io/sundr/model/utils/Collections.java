/*
 *      Copyright 2016 The original authors.
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

package io.sundr.model.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamDef;
import io.sundr.model.TypeRef;
import io.sundr.model.VoidRef;

public class Collections {

  public static final TypeParamDef E = new TypeParamDef("E", java.util.Collections.emptyList(),
      java.util.Collections.emptyMap());
  public static final TypeParamDef K = new TypeParamDef("K", java.util.Collections.emptyList(),
      java.util.Collections.emptyMap());
  public static final TypeParamDef V = new TypeParamDef("V", java.util.Collections.emptyList(),
      java.util.Collections.emptyMap());

  public static final TypeDef ITERABLE = new TypeDefBuilder(TypeDef.forName(Iterable.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(E)
      .build();

  public static final TypeDef ITERATOR = new TypeDefBuilder(TypeDef.forName(Iterator.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(E)
      .addNewMethod()
      .withReturnType(Types.PRIMITIVE_BOOLEAN_REF)
      .withName("hasNext")
      .endMethod()
      .addNewMethod()
      .withReturnType(E.toReference())
      .withName("next")
      .endMethod()
      .addNewMethod()
      .withDefaultMethod(true)
      .withReturnType(new VoidRef())
      .withName("remove")
      .endMethod()
      .build();

  public static final TypeDef COLLECTION = new TypeDefBuilder(TypeDef.forName(Collection.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(E)
      .withExtendsList(ITERABLE.toReference(E.toReference()))
      .build();

  public static final TypeDef MAP = new TypeDefBuilder(TypeDef.forName(Map.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(K, V)
      .build();

  public static final TypeDef MAP_ENTRY = new TypeDefBuilder(TypeDef.forName(Map.Entry.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(K, V)
      .withExtendsList(COLLECTION.toReference(K.toReference(), V.toReference())).build();

  public static final TypeDef HASH_MAP = new TypeDefBuilder(TypeDef.forName(HashMap.class.getName()))
      .withKind(Kind.CLASS)
      .withParameters(K, V)
      .addNewConstructor()
      .endConstructor()
      .addNewConstructor()
      .addNewArgument()
      .withName("m")
      .withTypeRef(MAP.toReference(K.toReference(), V.toReference()))
      .endArgument()
      .endConstructor()
      .withImplementsList(MAP.toReference(K.toReference(), V.toReference()))
      .build();

  public static final TypeDef LINKED_HASH_MAP = new TypeDefBuilder(TypeDef.forName(LinkedHashMap.class.getName()))
      .withKind(Kind.CLASS)
      .withParameters(K, V)
      .addNewConstructor()
      .endConstructor()
      .addNewConstructor()
      .addNewArgument()
      .withName("m")
      .withTypeRef(MAP.toReference(K.toReference(), V.toReference()))
      .endArgument()
      .endConstructor()
      .withImplementsList(MAP.toReference(K.toReference(), V.toReference()))
      .build();

  public static final TypeDef LIST = new TypeDefBuilder(TypeDef.forName(List.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(E)
      .withExtendsList(COLLECTION.toReference(E.toReference())).build();

  public static final TypeDef ARRAY_LIST = new TypeDefBuilder(TypeDef.forName(ArrayList.class.getName()))
      .withKind(Kind.CLASS)
      .withParameters(E)
      .addNewConstructor()
      .endConstructor()
      .addNewConstructor()
      .addNewArgument()
      .withName("c")
      .withTypeRef(COLLECTION.toReference(E.toReference()))
      .endArgument()
      .endConstructor()
      .withImplementsList(LIST.toReference(E.toReference())).build();

  public static final TypeDef SET = new TypeDefBuilder(TypeDef.forName(Set.class.getName()))
      .withKind(Kind.INTERFACE)
      .withParameters(E)
      .withExtendsList(COLLECTION.toReference(E.toReference())).build();

  public static final TypeDef LINKED_HASH_SET = new TypeDefBuilder(TypeDef.forName(LinkedHashSet.class.getName()))
      .withKind(Kind.CLASS)
      .withParameters(E)
      .addNewConstructor()
      .endConstructor()
      .addNewConstructor()
      .addNewArgument()
      .withName("c")
      .withTypeRef(COLLECTION.toReference(E.toReference()))
      .endArgument()
      .endConstructor()
      .withImplementsList(SET.toReference(E.toReference())).build();

  public static final Function<TypeRef, Boolean> IS_LIST = new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, LIST, IS_LIST);
    }
  };

  public static final Function<TypeRef, Boolean> IS_SET = new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, SET, IS_SET);
    }
  };

  public static final Function<TypeRef, Boolean> IS_MAP = new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return Types.isInstanceOf(type, MAP, IS_MAP);
    }
  };

  public static final Function<TypeRef, Boolean> IS_COLLECTION = new Function<TypeRef, Boolean>() {
    public Boolean apply(TypeRef type) {
      return IS_LIST.apply(type) || IS_SET.apply(type);
    }
  };

  /**
   * Checks if a {@link TypeRef} is a {@link Collection}.
   * 
   * @param type The type to check.
   * @return True if its a Collection.
   */
  public static boolean isCollection(TypeRef type) {
    return IS_COLLECTION.apply(type);
  }
}
