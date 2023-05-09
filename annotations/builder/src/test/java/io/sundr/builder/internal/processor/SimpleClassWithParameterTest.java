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

package io.sundr.builder.internal.processor;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.api.Adapters;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.TypeArguments;

public class SimpleClassWithParameterTest extends AbstractProcessorTest {

  private final AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

  TypeDef stringDef = Adapters.adaptType(String.class, context);
  RichTypeDef simpleClassWithParameterDef = TypeArguments
      .apply(Sources.readTypeDefFromResource("SimpleClassWithParameter.java", context));

  @Test
  public void testFluent() {
    TypeDef fluent = ClazzAs.FLUENT.apply(simpleClassWithParameterDef);
    System.out.println(fluent);

    assertEquals(Kind.CLASS, fluent.getKind());
    assertEquals("SimpleClassWithParameterFluent", fluent.getName());
    assertEquals(1, fluent.getExtendsList().size());

    ClassRef superClass = fluent.getExtendsList().iterator().next();
    assertEquals("BaseFluent", superClass.getName());
    assertEquals(1, superClass.getArguments().size());
    assertEquals("A", superClass.getArguments().iterator().next().toString());
  }

  @Test
  public void testBuilder() {
    TypeDef builder = ClazzAs.BUILDER.apply(simpleClassWithParameterDef);
    System.out.println(builder);

    assertEquals(Kind.CLASS, builder.getKind());
    assertEquals("SimpleClassWithParameterBuilder", builder.getName());
    assertEquals(1, builder.getExtendsList().size());

    ClassRef superClass = builder.getImplementsList().iterator().next();
    assertEquals("VisitableBuilder", superClass.getName());
    assertEquals(2, superClass.getArguments().size());
    Iterator<TypeRef> argIterator = superClass.getArguments().iterator();
    TypeRef ref = argIterator.next();
    assertEquals("testpackage.SimpleClassWithParameter<N>", ref.render());
    assertEquals("testpackage.SimpleClassWithParameter<N>", ref.toString());
    ref = argIterator.next();
    assertEquals("testpackage.SimpleClassWithParameterBuilder<N>", ref.render());
    assertEquals("testpackage.SimpleClassWithParameterBuilder<N>", ref.toString());
  }

  @Test
  public void testEditable() {
    TypeDef editable = ClazzAs.EDITABLE.apply(simpleClassWithParameterDef);
    System.out.println(editable);

    assertEquals(Kind.CLASS, editable.getKind());
    assertEquals("EditableSimpleClassWithParameter", editable.getName());
    assertEquals(1, editable.getExtendsList().size());

    ClassRef superClass = editable.getExtendsList().iterator().next();
    assertEquals(simpleClassWithParameterDef.toInternalReference(), superClass);
  }

  @Test
  public void testInline() {
    TypeDef inlineable = BuildableProcessor.inlineableOf(builderContext, simpleClassWithParameterDef, inline);
    System.out.println(inlineable);
    assertEquals(Kind.CLASS, inlineable.getKind());
    assertEquals("CallableSimpleClassWithParameter", inlineable.getName());
  }
}
