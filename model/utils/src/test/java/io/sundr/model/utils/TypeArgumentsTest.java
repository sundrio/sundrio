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
import static io.sundr.model.utils.Types.OPTIONAL;
import static io.sundr.model.utils.Types.STRING_REF;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import io.sundr.builder.Visitor;
import io.sundr.model.ClassRef;
import io.sundr.model.ClassRefBuilder;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;
import io.sundr.model.TypeRef;
import io.sundr.model.WildcardRef;
import io.sundr.model.functions.GetDefinition;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.utils.Strings;

public class TypeArgumentsTest {

  public static final TypeDef MULTI_HASH_MAP = new TypeDefBuilder(TypeDef.forName("com.example.MultiHashMap"))
      .withKind(Kind.CLASS).withParameters(K, V)
      .withExtendsList(
          Collections.HASH_MAP.toReference(K.toReference(), Collections.LIST.toReference(V.toReference())))
      .build();

  public static final TypeDef MY_SUPERCLASS = new TypeDefBuilder(TypeDef.forName("com.example.MySuperclass"))
      .withKind(Kind.CLASS).withParameters(E)
      .accept(addBackedProperty(E.toReference(), "superValue"))
      .accept(addBackedProperty(Collections.LIST.toReference(E.toReference()), "superValues"))
      .build();

  public static final TypeDef MY_SUBCLASS = new TypeDefBuilder(TypeDef.forName("com.example.MySubclass"))
      .withKind(Kind.CLASS).withParameters(E)
      .accept(addBackedProperty(E.toReference(), "subValue"))
      .accept(addBackedProperty(Collections.LIST.toReference(E.toReference()), "subValues"))
      .withExtendsList(MY_SUPERCLASS.toReference(OPTIONAL.toReference(E.toReference())))
      .build();

  @Before
  public void setUp() {
    DefinitionRepository.getRepository().register(Collections.MAP);
    DefinitionRepository.getRepository().register(Collections.HASH_MAP);
    DefinitionRepository.getRepository().register(Collections.LIST);
    DefinitionRepository.getRepository().register(Types.STRING);
    DefinitionRepository.getRepository().register(Types.INT);
    DefinitionRepository.getRepository().register(MULTI_HASH_MAP);
    DefinitionRepository.getRepository().register(MY_SUBCLASS);
    DefinitionRepository.getRepository().register(MY_SUPERCLASS);
  }

  @Test
  public void shouldNotCauseStackOverflow() {
    ClassRef classRef = MULTI_HASH_MAP.toReference(Types.STRING_REF, Types.INT_REF);
    TypeDef typeDef = GetDefinition.of(classRef);
    RichTypeDef richDef = TypeArguments.apply(GetDefinition.of(classRef));

    System.out.println(typeDef.render());
    System.out.println(richDef.render());

    // This goes beyond the purpose of the tests, which is to check against SO.
    // Still, it's nice to know that the rendered code is not affected (for THIS scenario).
    assertEquals(typeDef.render(), richDef.render());
  }

  @Test
  public void propertiesAndMethodsFromSupertypesAreResolvedCorrectlyOnTypeDefinition() {
    ClassRef classRef = MY_SUBCLASS.toReference();
    TypeDef typeDef = GetDefinition.of(classRef);
    RichTypeDef richDef = TypeArguments.apply(GetDefinition.of(classRef));

    System.out.println(typeDef.render());
    System.out.println(richDef.render());

    Map<String, Property> propertiesByName = richDef.getAllProperties().stream()
        .collect(Collectors.toMap(Property::getName, Function.identity()));
    assertEquals(4, propertiesByName.size());
    assertEquals("E subValue", propertiesByName.get("subValue").render());
    assertEquals("java.util.List<E> subValues", propertiesByName.get("subValues").render());
    assertEquals("java.util.Optional<E> superValue", propertiesByName.get("superValue").render());
    assertEquals("java.util.List<java.util.Optional<E>> superValues", propertiesByName.get("superValues").render());

    Map<String, Method> methodsByName = richDef.getAllMethods().stream()
        .collect(Collectors.toMap(Method::getName, Function.identity()));
    assertEquals(8, methodsByName.size());

    assertEquals("E getSubValue();", methodsByName.get("getSubValue").render());
    assertEquals("void setSubValue(E subValue);", methodsByName.get("setSubValue").render());
    assertEquals("java.util.List<E> getSubValues();", methodsByName.get("getSubValues").render());
    assertEquals("void setSubValues(java.util.List<E> subValues);", methodsByName.get("setSubValues").render());

    assertEquals("java.util.Optional<E> getSuperValue();", methodsByName.get("getSuperValue").render());
    assertEquals("void setSuperValue(java.util.Optional<E> superValue);",
        methodsByName.get("setSuperValue").render());
    assertEquals("java.util.List<java.util.Optional<E>> getSuperValues();",
        methodsByName.get("getSuperValues").render());
    assertEquals("void setSuperValues(java.util.List<java.util.Optional<E>> superValues);",
        methodsByName.get("setSuperValues").render());
  }

  @Test
  public void propertiesAndMethodsFromSupertypesAreResolvedCorrectlyOnTypeReference() {
    ClassRef classRef = MY_SUBCLASS.toReference(STRING_REF);
    RichTypeDef richDef = TypeArguments.apply(classRef);

    System.out.println(richDef.render());

    Map<String, Property> propertiesByName = richDef.getAllProperties().stream()
        .collect(Collectors.toMap(Property::getName, Function.identity()));
    assertEquals(4, propertiesByName.size());
    assertEquals("java.lang.String subValue", propertiesByName.get("subValue").render());
    assertEquals("java.util.List<java.lang.String> subValues", propertiesByName.get("subValues").render());
    assertEquals("java.util.Optional<java.lang.String> superValue", propertiesByName.get("superValue").render());
    assertEquals("java.util.List<java.util.Optional<java.lang.String>> superValues",
        propertiesByName.get("superValues").render());

    Map<String, Method> methodsByName = richDef.getAllMethods().stream()
        .collect(Collectors.toMap(Method::getName, Function.identity()));
    assertEquals(8, methodsByName.size());

    assertEquals("java.lang.String getSubValue();", methodsByName.get("getSubValue").render());
    assertEquals("void setSubValue(java.lang.String subValue);", methodsByName.get("setSubValue").render());
    assertEquals("java.util.List<java.lang.String> getSubValues();", methodsByName.get("getSubValues").render());
    assertEquals("void setSubValues(java.util.List<java.lang.String> subValues);", methodsByName.get("setSubValues").render());

    assertEquals("java.util.Optional<java.lang.String> getSuperValue();", methodsByName.get("getSuperValue").render());
    assertEquals("void setSuperValue(java.util.Optional<java.lang.String> superValue);",
        methodsByName.get("setSuperValue").render());
    assertEquals("java.util.List<java.util.Optional<java.lang.String>> getSuperValues();",
        methodsByName.get("getSuperValues").render());
    assertEquals("void setSuperValues(java.util.List<java.util.Optional<java.lang.String>> superValues);",
        methodsByName.get("setSuperValues").render());
  }

  /**
   * Register a field with a type and name, and corresponding getter and setter methods
   */
  private static Visitor<TypeDefBuilder> addBackedProperty(TypeRef type, String name) {
    return new Visitor<TypeDefBuilder>() {
      @Override
      public void visit(TypeDefBuilder typeDef) {
        typeDef.addNewProperty().withName(name).withTypeRef(type).endProperty()

            .addNewMethod().withName("get" + Strings.capitalizeFirst(name)).withReturnType(type).endMethod()

            .addNewMethod().withName("set" + Strings.capitalizeFirst(name)).withNewVoidRefReturnType().endVoidRefReturnType()
            .addNewArgument().withTypeRef(type).withName(name).endArgument().endMethod();
      }
    };
  }

  @Test
  public void getGenericArgumentsMappingsShouldCorrectlyDetermineMappings() {
    ClassRef ref = Collections.MAP.toReference(Types.STRING_REF, Types.INT_REF);
    Map<String, TypeRef> map = TypeArguments.getGenericArgumentsMappings(ref);
    assertEquals(Types.STRING_REF, map.get("K"));
    assertEquals(Types.INT_REF, map.get("V"));
    assertEquals(2, map.size());
  }

  @Test
  public void getGenericArgumentsMappingsShouldHandleWildcards() {
    ClassRef ref = Collections.MAP.toReference();
    Map<String, TypeRef> map = TypeArguments.getGenericArgumentsMappings(ref);
    assertTrue(map.get("K") + " instanceof WildcardRef", map.get("K") instanceof WildcardRef);
    assertTrue(map.get("V") + " instanceof WildcardRef", map.get("V") instanceof WildcardRef);
    assertEquals(2, map.size());
  }

  @Test
  public void getGenericArgumentsMappingsShouldHandleRawReferences() {
    ClassRef ref = ClassRef.forName(Collections.MAP.getFullyQualifiedName());
    Map<String, TypeRef> map = TypeArguments.getGenericArgumentsMappings(ref);
    assertTrue(map.isEmpty());
  }

  @Test
  public void getGenericArgumentsMappingsShouldHandleComplexTypes() {
    TypeRef key = K.toReference();
    TypeRef value = Collections.LIST.toReference(V.toReference());
    ClassRef ref = Collections.MAP.toReference(key, value);
    Map<String, TypeRef> map = TypeArguments.getGenericArgumentsMappings(ref);
    assertEquals(key, map.get("K"));
    assertEquals(value, map.get("V"));
    assertEquals(2, map.size());
  }

  @Test
  public void getGenericArgumentsMappingsShouldErrorIfTooFewArguments() {
    ClassRef tooFew = new ClassRefBuilder(Collections.MAP.toReference())
        .withArguments(K.toReference()).build();
    IllegalStateException exception = assertThrows(IllegalStateException.class,
        () -> TypeArguments.getGenericArgumentsMappings(tooFew));
    assertEquals("Incompatible reference java.util.Map<K> to public interface Map<K,V>", exception.getMessage());
  }

  @Test
  public void getGenericArgumentsMappingsShouldErrorIfTooManyArguments() {
    ClassRef tooMany = new ClassRefBuilder(Collections.MAP.toReference())
        .withArguments(K.toReference(), K.toReference(), K.toReference()).build();
    IllegalStateException exception = assertThrows(IllegalStateException.class,
        () -> TypeArguments.getGenericArgumentsMappings(tooMany));
    assertEquals("Incompatible reference java.util.Map<K,K,K> to public interface Map<K,V>", exception.getMessage());
  }
}
