/*
 * Copyright 2016 The original authors.
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

package io.sundr.codegen.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.sundr.codegen.DefinitionRepository;

public class AssignableTest {

  TypeDef collection = new TypeDefBuilder()
      .withKind(Kind.INTERFACE)
      .withPackageName("java.utl")
      .withName("Collection")
      .build();

  ClassRef collectionRef = collection.toReference();

  TypeDef list = new TypeDefBuilder()
      .withKind(Kind.INTERFACE)
      .withPackageName("java.utl")
      .withName("List")
      .withExtendsList(collectionRef)
      .build();

  ClassRef listRef = list.toReference();

  TypeDef arrayList = new TypeDefBuilder()
      .withKind(Kind.INTERFACE)
      .withPackageName("java.utl")
      .withName("ArrayList")
      .withImplementsList(listRef)
      .build();

  ClassRef arrayListRef = arrayList.toReference();

  @Before
  public void init() {
    DefinitionRepository.getRepository().register(collection);
    DefinitionRepository.getRepository().register(list);
    DefinitionRepository.getRepository().register(arrayList);
  }

  @Test
  public void testWithTypeDef() {
    assertTrue(collection.isAssignableFrom(list));
    assertTrue(collection.isAssignableFrom(arrayList));

    assertTrue(list.isAssignableFrom(arrayList));
    assertTrue(arrayList.isAssignableFrom(arrayList));

    assertFalse(list.isAssignableFrom(collection));
    assertFalse(arrayList.isAssignableFrom(collection));

    assertFalse(arrayList.isAssignableFrom(list));
  }

  @Test
  public void testWithClassRef() {
    assertTrue(collectionRef.isAssignableFrom(listRef));
    assertTrue(collectionRef.isAssignableFrom(arrayListRef));

    assertTrue(listRef.isAssignableFrom(arrayListRef));
    assertTrue(arrayListRef.isAssignableFrom(arrayListRef));

    assertFalse(listRef.isAssignableFrom(collectionRef));
    assertFalse(arrayListRef.isAssignableFrom(collectionRef));

    assertFalse(arrayListRef.isAssignableFrom(listRef));
  }
}
