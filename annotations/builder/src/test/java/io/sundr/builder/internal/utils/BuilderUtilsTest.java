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

package io.sundr.builder.internal.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.internal.processor.AbstractProcessorTest;
import io.sundr.model.ClassRef;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;

public class BuilderUtilsTest extends AbstractProcessorTest {

  private final AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());
  TypeDef stringDef = Adapters.adaptType(String.class, context);

  @Buildable
  class A {
  }

  @Buildable
  class B {
    private final A a;

    public B(A a) {
      this.a = a;
    }

    public A getA() {
      return this.a;
    }
  }

  @Buildable
  class C {
    private final Optional<A> optA;
    private final List<B> bList;

    public C(Optional<A> optA, List<B> bList) {
      this.optA = optA;
      this.bList = bList;
    }

    public Optional<A> getOptA() {
      return optA;
    }

    public List<B> getbList() {
      return bList;
    }
  }

  @Buildable
  class D {
    private final C c;

    public D(C c) {
      this.c = c;
    }

    public C getC() {
      return c;
    }
  }

  @Test
  public void testIsBuildable() {
    TypeDef a = Adapters.adaptType(A.class, context);
    TypeDef b = Adapters.adaptType(B.class, context);
    TypeDef c = Adapters.adaptType(C.class, context);

    builderContext.getBuildableRepository().register(a);
    builderContext.getBuildableRepository().register(b);
    builderContext.getBuildableRepository().register(c);

    assertTrue(BuilderUtils.isBuildable(b));
    assertTrue(BuilderUtils.isBuildable(a));
  }

  @Test
  public void testEnclosingBuildables() {
    TypeDef a = Adapters.adaptType(A.class, context);
    TypeDef b = Adapters.adaptType(B.class, context);
    TypeDef c = Adapters.adaptType(C.class, context);
    TypeDef d = Adapters.adaptType(D.class, context);

    builderContext.getBuildableRepository().register(a);
    builderContext.getBuildableRepository().register(b);
    builderContext.getBuildableRepository().register(c);
    builderContext.getBuildableRepository().register(d);

    assertTrue(BuilderUtils.enclosedBuildables(a).isEmpty());
    assertFalse(BuilderUtils.enclosedBuildables(b).isEmpty());
    assertEquals(getClass().getSimpleName() + ".A",
        BuilderUtils.enclosedBuildables(b).entrySet().iterator().next().getValue().getName());

    Map<String, ClassRef> map = BuilderUtils.enclosedBuildables(c);
    assertFalse(map.isEmpty());
    assertEquals(2, map.size());
    assertTrue(map.containsKey(getClass().getName() + ".A"));
    assertTrue(map.containsKey(getClass().getName() + ".B"));

    map = BuilderUtils.enclosedBuildables(d);

    assertFalse(map.isEmpty());
    assertEquals(3, map.size());
    assertTrue(map.containsKey(getClass().getName() + ".A"));
    assertTrue(map.containsKey(getClass().getName() + ".B"));
    assertTrue(map.containsKey(getClass().getName() + ".C"));
  }

}
