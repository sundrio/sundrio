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

package io.sundr.model.functions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.repo.DefinitionRepository;

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
    assertTrue(Assignable.isAssignable(collection).from(list));
    assertTrue(Assignable.isAssignable(collection).from(arrayList));

    assertTrue(Assignable.isAssignable(list).from(arrayList));
    assertTrue(Assignable.isAssignable(arrayList).from(arrayList));

    assertFalse(Assignable.isAssignable(list).from(collection));
    assertFalse(Assignable.isAssignable(arrayList).from(collection));

    assertFalse(Assignable.isAssignable(arrayList).from(list));
  }

  @Test
  public void testWithClassRef() {
    assertTrue(Assignable.isAssignable(collectionRef).from(listRef));
    assertTrue(Assignable.isAssignable(collectionRef).from(arrayListRef));

    assertTrue(Assignable.isAssignable(listRef).from(arrayListRef));
    assertTrue(Assignable.isAssignable(arrayListRef).from(arrayListRef));

    assertFalse(Assignable.isAssignable(listRef).from(collectionRef));
    assertFalse(Assignable.isAssignable(arrayListRef).from(collectionRef));

    assertFalse(Assignable.isAssignable(arrayListRef).from(listRef));
  }
}
