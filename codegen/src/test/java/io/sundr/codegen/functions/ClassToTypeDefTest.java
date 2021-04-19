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

package io.sundr.codegen.functions;

import io.sundr.codegen.model.*;
import io.sundr.example.*;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ClassToTypeDefTest {

  @Test
  public void testConverter() {
    TypeDef def = ClassTo.TYPEDEF.apply(List.class);
    System.out.println(def);
  }

  @Test
  public void testConverterWithComplexTypes() {
    TypeDef person = ClassTo.TYPEDEF.apply(Person.class);
    Optional<ClassRef> personType = findClassRef(person, "type");
    assertTrue(personType.isPresent());
    personType.ifPresent(p -> {
      assertEquals("io.sundr.example.Person.Type", p.getFullyQualifiedName());
      Set<String> properties = p.getDefinition().getProperties().stream().map(Property::getName).collect(Collectors.toSet());
      assertTrue(properties.contains("O_MINUS"));
    });

    Optional<ClassRef> addresses = findClassRef(person, "addresses");
    assertTrue(addresses.isPresent());
    addresses.ifPresent(l -> {
      ClassRef address = (ClassRef) l.getArguments().get(0);
      Optional<ClassRef> addressType = findClassRef(address.getDefinition(), "type");
      assertFalse(address.getDefinition().getProperties().isEmpty());
      address.getDefinition().getProperties().stream().peek(p -> {
        System.out.println(p.getTypeRef() + " " + p.getName());
      }).collect(Collectors.toList());
      assertTrue(addressType.isPresent());
      addressType.ifPresent(a -> {
        assertEquals("io.sundr.example.Address.Type", a.getFullyQualifiedName());
        Set<String> properties = a.getDefinition().getProperties().stream().map(Property::getName).collect(Collectors.toSet());
        assertTrue(properties.contains("WORK"));
      });
    });
  }

  @Test
  public void extendsListShouldWork() {
    TypeDef typeDef = ClassTo.TYPEDEF.apply(Child.class);
    List<ClassRef> extendsList = typeDef.getExtendsList();
    assertNotNull(extendsList);
    assertEquals(1, extendsList.size());
    ClassRef classRef = extendsList.get(0);
    assertEquals(Super.class.getName(), classRef.getFullyQualifiedName());
    List<TypeRef> arguments = classRef.getArguments();
    assertEquals(2, arguments.size());
    assertEquals("String", arguments.get(0).toString());
    assertEquals("Other", arguments.get(1).toString());
    assertTrue(typeDef.getImplementsList().stream().anyMatch(c -> c.getFullyQualifiedName().equals(Interfazz.class.getName())));
  }

  @Test
  public void annotationsShouldHaveParameters() {
    final TypeDef typeDef = ClassTo.TYPEDEF.apply(Other.class);
    final List<Property> properties = typeDef.getProperties();
    final Property foo = properties.stream().filter(p -> p.getName().equals("foo")).findFirst()
            .orElseThrow(RuntimeException::new);
    List<AnnotationRef> annotations = foo.getAnnotations();
    assertEquals(1, annotations.size());
    AnnotationRef annotationRef = annotations.get(0);
    assertTrue(annotationRef.toString().contains("TestAnnotation"));
    assertEquals(Other.FOO_NAME, annotationRef.getParameters().get("name"));
    final Method something = typeDef.getMethods().stream().filter(p -> p.getName().equals("something")).findFirst()
            .orElseThrow(RuntimeException::new);
    annotations = something.getAnnotations();
    assertEquals(1, annotations.size());
    annotationRef = annotations.get(0);
    assertEquals(Other.SOMETHING_NAME, annotationRef.getParameters().get("name"));
    assertArrayEquals(new int[]{1, 2, 3, 5, 7}, (int[]) annotationRef.getParameters().get("values"));
  }

  public static Optional<ClassRef> findClassRef(TypeDef def, String propertyName) {
    return def.getProperties().stream()
            .filter(p -> propertyName.equals(p.getName()))
            .filter(p -> p.getTypeRef() instanceof ClassRef)
            .map(p -> (ClassRef) p.getTypeRef())
            .findFirst();
  }
}
