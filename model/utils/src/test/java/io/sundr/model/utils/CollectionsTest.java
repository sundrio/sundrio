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

package io.sundr.model.utils;

import static io.sundr.model.utils.Collections.E;
import static io.sundr.model.utils.Collections.K;
import static io.sundr.model.utils.Collections.V;
import static io.sundr.model.utils.Types.INT_REF;
import static io.sundr.model.utils.Types.STRING_REF;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import org.junit.Before;
import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeParamRef;
import io.sundr.model.TypeRef;
import io.sundr.model.repo.DefinitionRepository;

public class CollectionsTest {
  public static final TypeParamRef E_REF = E.toReference();

  public static final TypeDef COLLECTION = new TypeDefBuilder(TypeDef.forName(Collection.class.getName()))
      .addToParameters(E).build();
  public static final TypeDef LIST = new TypeDefBuilder(TypeDef.forName(List.class.getName()))
      .withImplementsList(COLLECTION.toInternalReference())
      .addToParameters(E).build();
  public static final TypeDef ARRAY_LIST = new TypeDefBuilder(TypeDef.forName(ArrayList.class.getName()))
      .withImplementsList(LIST.toInternalReference())
      .addToParameters(E).build();
  public static final TypeDef LINKED_LIST = new TypeDefBuilder(TypeDef.forName(LinkedList.class.getName()))
      .withImplementsList(LIST.toInternalReference())
      .addToParameters(E).build();
  public static final TypeDef CUSTOM_STRING_LIST = new TypeDefBuilder(TypeDef.forName("example.CustomStringList"))
      .withImplementsList(LIST.toReference(STRING_REF))
      .build();
  public static final TypeDef CUSTOM_STRING_ARRAY_LIST = new TypeDefBuilder(TypeDef.forName("example.CustomStringArrayList"))
      .withExtendsList(ARRAY_LIST.toReference(STRING_REF))
      .build();

  public static final TypeDef MAP = new TypeDefBuilder(TypeDef.forName(Map.class.getName()))
      .addToParameters(K, V).build();
  public static final TypeDef HASH_MAP = new TypeDefBuilder(TypeDef.forName(HashMap.class.getName()))
      .withImplementsList(MAP.toInternalReference())
      .addToParameters(K, V).build();
  public static final TypeDef TREE_MAP = new TypeDefBuilder(TypeDef.forName(TreeMap.class.getName()))
      .withImplementsList(MAP.toInternalReference())
      .addToParameters(K, V).build();
  public static final TypeDef CUSTOM_STRING_KEYED_MAP = new TypeDefBuilder(TypeDef.forName("example.CustomStringKeyedMap"))
      .withImplementsList(MAP.toReference(STRING_REF, E_REF))
      .addToParameters(E)
      .build();
  public static final TypeDef CUSTOM_INTEGER_VALUED_MAP = new TypeDefBuilder(TypeDef.forName("example.CustomIntegerValuedMap"))
      .withImplementsList(MAP.toReference(E_REF, INT_REF))
      .addToParameters(E)
      .build();
  public static final TypeDef CUSTOM_STRING_INTEGER_MAP = new TypeDefBuilder(TypeDef.forName("example.CustomStringIntegerMap"))
      .withImplementsList(MAP.toReference(STRING_REF, INT_REF))
      .build();
  public static final TypeDef CUSTOM_STRING_KEYED_HASH_MAP = new TypeDefBuilder(
      TypeDef.forName("example.CustomStringKeyedHashMap"))
      .withExtendsList(HASH_MAP.toReference(STRING_REF, E_REF))
      .addToParameters(E)
      .build();
  public static final TypeDef CUSTOM_INTEGER_VALUED_HASH_MAP = new TypeDefBuilder(
      TypeDef.forName("example.CustomIntegerValuedHashMap"))
      .withExtendsList(HASH_MAP.toReference(E_REF, INT_REF))
      .addToParameters(E)
      .build();
  public static final TypeDef CUSTOM_STRING_INTEGER_HASH_MAP = new TypeDefBuilder(
      TypeDef.forName("example.CustomStringIntegerHashMap"))
      .withExtendsList(HASH_MAP.toReference(STRING_REF, INT_REF))
      .build();

  public static final TypeDef MULTI_MAP = new TypeDefBuilder(TypeDef.forName("example.MultiMap"))
      .addToParameters(K, V)
      .withImplementsList(MAP.toReference(K.toReference(), LIST.toReference(V.toReference())))
      .build();
  public static final TypeDef MULTI_HASH_MAP = new TypeDefBuilder(TypeDef.forName("example.MultiHashMap"))
      .addToParameters(K, V)
      .withExtendsList(HASH_MAP.toReference(K.toReference(), LIST.toReference(V.toReference())))
      .build();

  @Before
  public void setUp() {
    DefinitionRepository.getRepository().register(COLLECTION);
    DefinitionRepository.getRepository().register(LIST);
    DefinitionRepository.getRepository().register(ARRAY_LIST);
    DefinitionRepository.getRepository().register(LINKED_LIST);
    DefinitionRepository.getRepository().register(CUSTOM_STRING_LIST);
    DefinitionRepository.getRepository().register(CUSTOM_STRING_ARRAY_LIST);

    DefinitionRepository.getRepository().register(MAP);
    DefinitionRepository.getRepository().register(HASH_MAP);
    DefinitionRepository.getRepository().register(TREE_MAP);
    DefinitionRepository.getRepository().register(CUSTOM_STRING_KEYED_MAP);
    DefinitionRepository.getRepository().register(CUSTOM_INTEGER_VALUED_MAP);
    DefinitionRepository.getRepository().register(CUSTOM_STRING_INTEGER_MAP);
    DefinitionRepository.getRepository().register(CUSTOM_STRING_KEYED_HASH_MAP);
    DefinitionRepository.getRepository().register(CUSTOM_INTEGER_VALUED_HASH_MAP);
    DefinitionRepository.getRepository().register(CUSTOM_STRING_INTEGER_HASH_MAP);

    DefinitionRepository.getRepository().register(MULTI_MAP);
    DefinitionRepository.getRepository().register(MULTI_HASH_MAP);
  }

  @Test
  public void testCollections() throws Exception {
    ClassRef list = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.List")
        .build();

    ClassRef listOfString = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.List")
        .addNewClassRefArgument()
        .withFullyQualifiedName("java.lang.String")
        .endClassRefArgument()
        .build();

    ClassRef arrayList = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.ArrayList")
        .build();

    ClassRef arrayListOfString = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.ArrayList")
        .addNewClassRefArgument()
        .withFullyQualifiedName("java.lang.String")
        .endClassRefArgument()
        .build();

    ClassRef linkedList = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.LinkedList")
        .build();

    ClassRef linkedListOfString = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.LinkedList")
        .addNewClassRefArgument()
        .withFullyQualifiedName("java.lang.String")
        .endClassRefArgument()
        .build();

    ClassRef map = new ClassRefBuilder()
        .withFullyQualifiedName("java.util.Map")
        .build();

    assertTrue(Collections.IS_LIST.apply(list));
    assertTrue(Collections.IS_LIST.apply(listOfString));
    assertTrue(Collections.IS_LIST.apply(arrayList));
    assertTrue(Collections.IS_LIST.apply(arrayListOfString));
    assertTrue(Collections.IS_LIST.apply(linkedList));
    assertTrue(Collections.IS_LIST.apply(linkedListOfString));
    assertFalse(Collections.IS_LIST.apply(map));
  }

  @Test
  public void listElementTypeIsDetermined() {
    final Optional<TypeRef> STRING_RESULT = Optional.of(STRING_REF);
    assertEquals(STRING_RESULT, Collections.getCollectionElementType(COLLECTION.toReference(STRING_REF)));
    assertEquals(STRING_RESULT, Collections.getCollectionElementType(LIST.toReference(STRING_REF)));
    assertEquals(STRING_RESULT, Collections.getCollectionElementType(ARRAY_LIST.toReference(STRING_REF)));
    assertEquals(STRING_RESULT, Collections.getCollectionElementType(LINKED_LIST.toReference(STRING_REF)));
    assertEquals(STRING_RESULT, Collections.getCollectionElementType(CUSTOM_STRING_LIST.toReference()));
    assertEquals(STRING_RESULT, Collections.getCollectionElementType(CUSTOM_STRING_ARRAY_LIST.toReference()));

    assertEquals(Optional.empty(), Collections.getCollectionElementType(MAP.toReference(STRING_REF, STRING_REF)));
  }

  @Test
  public void mapKeyTypeIsDetermined() {
    final Optional<TypeRef> STRING_RESULT = Optional.of(STRING_REF);
    assertEquals(STRING_RESULT, Collections.getMapKeyType(MAP.toReference(STRING_REF, INT_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(HASH_MAP.toReference(STRING_REF, INT_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(TREE_MAP.toReference(STRING_REF, INT_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(CUSTOM_STRING_KEYED_MAP.toReference(INT_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(CUSTOM_INTEGER_VALUED_MAP.toReference(STRING_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(CUSTOM_STRING_INTEGER_MAP.toReference()));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(CUSTOM_STRING_KEYED_HASH_MAP.toReference(INT_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(CUSTOM_INTEGER_VALUED_HASH_MAP.toReference(STRING_REF)));
    assertEquals(STRING_RESULT, Collections.getMapKeyType(CUSTOM_STRING_INTEGER_HASH_MAP.toReference()));

    assertEquals(Optional.empty(), Collections.getMapKeyType(COLLECTION.toReference(STRING_REF)));
  }

  @Test
  public void mapValueTypeIsDetermined() {
    final Optional<TypeRef> INT_RESULT = Optional.of(INT_REF);
    assertEquals(INT_RESULT, Collections.getMapValueType(MAP.toReference(STRING_REF, INT_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(HASH_MAP.toReference(STRING_REF, INT_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(TREE_MAP.toReference(STRING_REF, INT_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(CUSTOM_STRING_KEYED_MAP.toReference(INT_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(CUSTOM_INTEGER_VALUED_MAP.toReference(STRING_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(CUSTOM_STRING_INTEGER_MAP.toReference()));
    assertEquals(INT_RESULT, Collections.getMapValueType(CUSTOM_STRING_KEYED_HASH_MAP.toReference(INT_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(CUSTOM_INTEGER_VALUED_HASH_MAP.toReference(STRING_REF)));
    assertEquals(INT_RESULT, Collections.getMapValueType(CUSTOM_STRING_INTEGER_HASH_MAP.toReference()));

    assertEquals(Optional.empty(), Collections.getMapValueType(COLLECTION.toReference(STRING_REF)));
  }

  @Test
  public void mapKeyAndValueTypesAreDeterminedForMultiMaps() {
    final Optional<TypeRef> STRING_RESULT = Optional.of(STRING_REF);
    final Optional<TypeRef> LIST_OF_INT_RESULT = Optional.of(LIST.toReference(INT_REF));

    ClassRef STRING_INT_MULTI_MAP_REF = MULTI_MAP.toReference(STRING_REF, INT_REF);
    assertEquals(STRING_RESULT, Collections.getMapKeyType(STRING_INT_MULTI_MAP_REF));
    assertEquals(LIST_OF_INT_RESULT, Collections.getMapValueType(STRING_INT_MULTI_MAP_REF));

    ClassRef STRING_INT_MULTI_HASH_MAP_REF = MULTI_HASH_MAP.toReference(STRING_REF, INT_REF);
    assertEquals(STRING_RESULT, Collections.getMapKeyType(STRING_INT_MULTI_HASH_MAP_REF));
    assertEquals(LIST_OF_INT_RESULT, Collections.getMapValueType(STRING_INT_MULTI_HASH_MAP_REF));
  }
}
