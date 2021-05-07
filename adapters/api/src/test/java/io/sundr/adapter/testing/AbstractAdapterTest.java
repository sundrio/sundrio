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

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.testing.domain.ClassWithArray;
import io.sundr.adapter.testing.domain.ClassWithParam;
import io.sundr.adapter.testing.domain.ClassWithPrimitiveArray;
import io.sundr.adapter.testing.domain.ClassWithSelfRefParam;
import io.sundr.adapter.testing.domain.SimpleClass;
import io.sundr.model.ClassRef;
import io.sundr.model.PrimitiveRef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public abstract class AbstractAdapterTest<T, C extends AdapterContext> {

  public abstract C getContext();

  public abstract T getInput(Class type);

  @Test
  public void testWithSimpleClass() {
    T input = getInput(SimpleClass.class);
    TypeDef typeDef = Adapters.adapt(input, getContext());
    assertNotNull(typeDef);
    assertEquals("SimpleClass", typeDef.getName());
  }

  @Test
  public void testWithArray() {
    T input = getInput(ClassWithArray.class);
    TypeDef typeDef = Adapters.adapt(input, getContext());
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
    TypeDef typeDef = Adapters.adapt(input, getContext());

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
    TypeDef typeDef = Adapters.adapt(input, getContext());
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
    TypeDef typeDef = Adapters.adapt(input, getContext());
    assertNotNull(typeDef);

    assertEquals("ClassWithSelfRefParam", typeDef.getName());
    assertEquals(1, typeDef.getProperties().size());
    assertEquals(1, typeDef.getParameters().size());

    assertEquals("T", typeDef.getParameters().get(0).getName());
    assertEquals(1, typeDef.getParameters().get(0).getBounds().size());
    assertEquals(1, typeDef.getParameters().get(0).getBounds().get(0).getArguments().size());
  }

}
