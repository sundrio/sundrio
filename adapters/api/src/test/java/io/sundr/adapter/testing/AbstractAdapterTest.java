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

package io.sundr.adapter.testing;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.testing.general.ClassWithAnnotation;
import io.sundr.adapter.testing.general.ClassWithArray;
import io.sundr.adapter.testing.general.ClassWithParam;
import io.sundr.adapter.testing.general.ClassWithPrimitiveArray;
import io.sundr.adapter.testing.general.ClassWithSelfRefParam;
import io.sundr.adapter.testing.general.ClassWithSuperClassParam;
import io.sundr.adapter.testing.general.SimpleClass;
import io.sundr.adapter.testing.person.Address;
import io.sundr.adapter.testing.person.Person;
import io.sundr.model.AnnotationRef;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;
import io.sundr.model.functions.GetDefinition;

public abstract class AbstractAdapterTest<T> {

  public abstract AdapterContext getContext();

  public abstract T getInput(Class type);

  @Test
  public void testWithSimpleClass() {
    T input = getInput(SimpleClass.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());
    assertNotNull(typeDef);
    assertEquals("SimpleClass", typeDef.getName());
  }

  @Test
  public void testWithArray() {
    T input = getInput(ClassWithArray.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());
    assertNotNull(typeDef);
    assertEquals("ClassWithArray", typeDef.getName());
    assertEquals(1, typeDef.getProperties().size());
    TypeRef typeRef = typeDef.getProperties().iterator().next().getTypeRef();

    assertTrue(typeRef instanceof ClassRef);
    assertEquals(1, ((ClassRef) typeRef).getDimensions());
  }

  @Test
  public void testWithPrimitiveArray() {
    T input = getInput(ClassWithPrimitiveArray.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());

    assertNotNull(typeDef);
    assertEquals("ClassWithPrimitiveArray", typeDef.getName());
    assertEquals(1, typeDef.getProperties().size());
    TypeRef typeRef = typeDef.getProperties().iterator().next().getTypeRef();

    assertTrue(typeRef instanceof PrimitiveRef);
    assertEquals(1, ((PrimitiveRef) typeRef).getDimensions());
  }

  @Test
  public void testWithParam() {
    T input = getInput(ClassWithParam.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());
    assertNotNull(typeDef);

    assertEquals("ClassWithParam", typeDef.getName());
    assertEquals(1, typeDef.getProperties().size());
    assertEquals(1, typeDef.getParameters().size());

    assertEquals("T", typeDef.getParameters().get(0).getName());
    assertEquals(0, typeDef.getParameters().get(0).getBounds().size());
  }

  @Test
  public void testWithSelfRefParam() {
    T input = getInput(ClassWithSelfRefParam.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());
    assertNotNull(typeDef);

    assertEquals("ClassWithSelfRefParam", typeDef.getName());
    assertEquals(1, typeDef.getProperties().size());
    assertEquals(1, typeDef.getParameters().size());

    assertEquals("T", typeDef.getParameters().get(0).getName());
    assertEquals(1, typeDef.getParameters().get(0).getBounds().size());
    assertEquals(1, typeDef.getParameters().get(0).getBounds().get(0).getArguments().size());
  }

  @Test
  public void testWithSuperClassParam() {
    T input = getInput(ClassWithSuperClassParam.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());
    List<ClassRef> extendsList = typeDef.getExtendsList();
    assertNotNull(extendsList);
    assertEquals(1, extendsList.size());
    ClassRef classRef = extendsList.get(0);
    assertEquals(ClassWithParam.class.getName(), classRef.getFullyQualifiedName());
    List<TypeRef> arguments = classRef.getArguments();
    assertEquals(1, arguments.size());
    assertEquals("java.lang.String", arguments.get(0).render());
  }

  @Test
  public void testClassWithAnnotationParams() {
    T input = getInput(ClassWithAnnotation.class);
    TypeDef typeDef = Adapters.adaptType(input, getContext());
    final List<Property> properties = typeDef.getProperties();
    final Property foo = properties.stream().filter(p -> p.getName().equals("foo")).findFirst()
        .orElseThrow(RuntimeException::new);

    List<AnnotationRef> annotations = foo.getAnnotations();
    assertEquals(1, annotations.size());
    AnnotationRef annotationRef = annotations.get(0);
    assertTrue(annotationRef.toString().contains("SimpleAnnotation"));
    assertEquals("foo", annotationRef.getParameters().get("name"));

    final Method bar = typeDef.getMethods().stream().filter(p -> p.getName().equals("bar")).findFirst()
        .orElseThrow(RuntimeException::new);
    annotations = bar.getAnnotations();
    assertEquals(1, annotations.size());
    annotationRef = annotations.get(0);
    assertEquals("bar", annotationRef.getParameters().get("name"));
    Object params = annotationRef.getParameters().get("values");
    assertArrayEquals(new int[] { 1, 2, 3, 5, 7 }, (int[]) annotationRef.getParameters().get("values"));
  }

  //
  // Person
  //

  @Test
  public void testWithInnerTypes() {
    T input = getInput(Person.class);
    TypeDef person = Adapters.adaptType(input, getContext());
    Optional<ClassRef> personType = findClassRef(person, "type");
    assertTrue(personType.isPresent());
    personType.ifPresent(p -> {
      assertEquals(Person.class.getName() + ".Type", p.getFullyQualifiedName());
      Set<String> properties = GetDefinition.of(p).getProperties().stream().map(Property::getName).collect(Collectors.toSet());
      assertTrue(properties.contains("O_MINUS"));
    });

    Optional<ClassRef> addresses = findClassRef(person, "addresses");
    assertTrue(addresses.isPresent());
    addresses.ifPresent(l -> {
      ClassRef address = (ClassRef) l.getArguments().get(0);
      TypeDef addressDef = GetDefinition.of(address);
      Optional<ClassRef> addressType = findClassRef(addressDef, "type");
      assertFalse(addressDef.getProperties().isEmpty());

      assertTrue(addressType.isPresent());
      addressType.ifPresent(a -> {
        assertEquals(Address.class.getName() + ".Type", a.getFullyQualifiedName());
        Set<String> properties = GetDefinition.of(a).getProperties().stream().map(Property::getName)
            .collect(Collectors.toSet());
        assertTrue(properties.contains("WORK"));
      });
    });
  }

  public static Optional<ClassRef> findClassRef(TypeDef def, String propertyName) {
    return def.getProperties().stream().filter(p -> propertyName.equals(p.getName()))
        .filter(p -> p.getTypeRef() instanceof ClassRef).map(p -> (ClassRef) p.getTypeRef()).findFirst();
  }
}
