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

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.Collections;
import io.sundr.model.utils.Types;

public class TypeCastTest {

  public static final ClassRef STRING_INT_MAP_REF = Collections.MAP.toReference(Types.STRING_REF, Types.INT_REF);
  public static final ClassRef STRING_INT_HASHMAP_REF = Collections.HASH_MAP.toReference(Types.STRING_REF, Types.INT_REF);
  public static final ClassRef INT_DOUBLE_MAP_REF = Collections.MAP.toReference(Types.INT_REF, Types.DOUBLE_REF);

  @Before
  public void setUp() throws Exception {
    DefinitionRepository.getRepository().register(Collections.MAP);
    DefinitionRepository.getRepository().register(Collections.LIST);
    DefinitionRepository.getRepository().register(Collections.HASH_MAP);
  }

  @Test
  public void directInstance() {
    Optional<ClassRef> result = TypeCast.to(Collections.MAP.toReference()).apply(STRING_INT_MAP_REF);
    assertEquals(Optional.of(STRING_INT_MAP_REF), result);
  }

  @Test
  public void noMatch() {
    Optional<ClassRef> result = TypeCast.to(Collections.LIST.toReference()).apply(STRING_INT_MAP_REF);
    assertEquals(Optional.empty(), result);
  }

  @Test
  public void directExtends() {
    TypeDef definition = new TypeDefBuilder(TypeDef.forName("com.example.MyMap"))
        .withExtendsList(STRING_INT_HASHMAP_REF)
        .build();
    DefinitionRepository.getRepository().register(definition);

    Optional<ClassRef> result = TypeCast.to(Collections.HASH_MAP.toReference()).apply(definition.toReference());
    assertEquals(Optional.of(STRING_INT_HASHMAP_REF), result);
  }

  @Test
  public void directImplements() {
    TypeDef definition = new TypeDefBuilder(TypeDef.forName("com.example.MyMap"))
        .withImplementsList(STRING_INT_MAP_REF)
        .build();
    DefinitionRepository.getRepository().register(definition);

    Optional<ClassRef> result = TypeCast.to(Collections.MAP.toReference()).apply(definition.toReference());
    assertEquals(Optional.of(STRING_INT_MAP_REF), result);
  }

  @Test
  public void indirectSupertype() {
    TypeDef definition = new TypeDefBuilder(TypeDef.forName("com.example.MyMap"))
        .withExtendsList(STRING_INT_HASHMAP_REF)
        .build();
    DefinitionRepository.getRepository().register(definition);

    Optional<ClassRef> result = TypeCast.to(Collections.MAP.toReference()).apply(definition.toReference());
    assertEquals(Optional.of(STRING_INT_MAP_REF), result);
  }

  @Test
  public void multiMap() {
    TypeDef definition = new TypeDefBuilder(TypeDef.forName("com.example.MyMap"))
        .withParameters(Collections.K, Collections.V)
        .withImplementsList(
            Collections.MAP.toReference(
                Collections.K.toReference(),
                Collections.LIST.toReference(Collections.V.toReference())))
        .build();
    DefinitionRepository.getRepository().register(definition);
    ClassRef reference = definition.toReference(Types.STRING_REF, Types.INT_REF);

    Optional<ClassRef> result = TypeCast.to(Collections.MAP.toReference()).apply(reference);
    assertEquals(Optional.of(Collections.MAP.toReference(
        Types.STRING_REF,
        Collections.LIST.toReference(Types.INT_REF))), result);
  }

  @Test
  public void multiHashMap() {
    TypeDef definition = new TypeDefBuilder(TypeDef.forName("com.example.MyMap"))
        .withParameters(Collections.K, Collections.V)
        .withExtendsList(
            Collections.HASH_MAP.toReference(
                Collections.K.toReference(),
                Collections.LIST.toReference(Collections.V.toReference())))
        .build();
    DefinitionRepository.getRepository().register(definition);
    ClassRef reference = definition.toReference(Types.STRING_REF, Types.INT_REF);

    Optional<ClassRef> result = TypeCast.to(Collections.MAP.toReference()).apply(reference);
    assertEquals(Optional.of(Collections.MAP.toReference(
        Types.STRING_REF,
        Collections.LIST.toReference(Types.INT_REF))), result);
  }

  @Test
  public void inheritsSameInterfaceFromMultipleSources() {
    TypeDef map1 = new TypeDefBuilder(TypeDef.forName("com.example.MyMap1"))
        .withKind(Kind.INTERFACE)
        .withExtendsList(STRING_INT_MAP_REF)
        .build();
    TypeDef map2 = new TypeDefBuilder(TypeDef.forName("com.example.MyMap2"))
        .withKind(Kind.INTERFACE)
        .withExtendsList(STRING_INT_MAP_REF)
        .build();
    TypeDef map = new TypeDefBuilder(TypeDef.forName("com.example.MyMapResult"))
        .withImplementsList(map1.toReference(), map2.toReference())
        .build();

    DefinitionRepository.getRepository().register(map1);
    DefinitionRepository.getRepository().register(map2);
    DefinitionRepository.getRepository().register(map);

    Optional<ClassRef> result = TypeCast.to(Collections.MAP.toReference()).apply(map.toReference());
    assertEquals(Optional.of(STRING_INT_MAP_REF), result);
  }

  @Test
  public void inheritsSameInterfaceWithDifferentArgumentsFromMultipleSources() {
    TypeDef map1 = new TypeDefBuilder(TypeDef.forName("com.example.MyMap1"))
        .withKind(Kind.INTERFACE)
        .withExtendsList(STRING_INT_MAP_REF)
        .build();
    TypeDef map2 = new TypeDefBuilder(TypeDef.forName("com.example.MyMap2"))
        .withKind(Kind.INTERFACE)
        .withExtendsList(INT_DOUBLE_MAP_REF)
        .build();
    TypeDef map = new TypeDefBuilder(TypeDef.forName("com.example.MyMapResult"))
        .withImplementsList(map1.toReference(), map2.toReference())
        .build();

    DefinitionRepository.getRepository().register(map1);
    DefinitionRepository.getRepository().register(map2);
    DefinitionRepository.getRepository().register(map);

    IllegalStateException exception = assertThrows(IllegalStateException.class,
        () -> TypeCast.to(Collections.MAP.toReference()).apply(map.toReference()));
    assertEquals("Type com.example.MyMapResult extends or implements "
        + "java.util.Map<?,?> multiple times: "
        + "[java.util.Map<java.lang.String,java.lang.Integer>, java.util.Map<java.lang.Integer,java.lang.Double>]. "
        + "This is not legal in Java", exception.getMessage());
  }

  @Test
  public void javaLangObjectExtendsItself() {
    TypeDef object = new TypeDefBuilder(Types.OBJECT)
        .withExtendsList(Types.OBJECT.toReference())
        .build();

    DefinitionRepository.getRepository().register(object);
    // this should not cause stack overflow error
    TypeCast.to(Collections.MAP.toReference()).apply(object.toReference());
  }

  @Test
  public void arraysAreNotSupported() {
    ClassRef cls = new ClassRef("some.Type", 1, new ArrayList<>(), new HashMap<>());
    assertThrows(IllegalArgumentException.class, () -> TypeCast.to(cls));
  }

  @Test
  public void boundedWildcardsAreNotSupported() {
    ClassRef cls = new ClassRefBuilder().withFullyQualifiedName("some.Type")
        .addNewWildcardRefArgument().addToBounds(Collections.MAP.toReference()).endWildcardRefArgument()
        .build();
    assertThrows(IllegalArgumentException.class, () -> TypeCast.to(cls));
  }
}
