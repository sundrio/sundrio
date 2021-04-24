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

import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.codegen.functions.Sources;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class SimpleClassWithInheritanceTest extends AbstractProcessorTest {

  TypeDef simpleClassDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF
      .apply(getClass().getClassLoader().getResourceAsStream("SimpleClass.java"));
  TypeDef simpleClassWithDateDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF
      .apply(getClass().getClassLoader().getResourceAsStream("SimpleClassWithDate.java"));

  @Before
  public void setUp() {
    builderContext.getBuildableRepository().register(simpleClassDef);
  }

  @Test
  public void testFluent() {
    TypeDef fluent = ClazzAs.FLUENT_INTERFACE.apply(simpleClassWithDateDef);
    System.out.println(fluent);

    assertEquals(Kind.INTERFACE, fluent.getKind());
    assertEquals("SimpleClassWithDateFluent", fluent.getName());
    assertEquals(1, fluent.getExtendsList().size());

    ClassRef superClass = fluent.getExtendsList().iterator().next();
    assertEquals("SimpleClassFluent", superClass.getName());
    assertEquals(1, superClass.getArguments().size());
    assertEquals("A", superClass.getArguments().iterator().next().toString());
  }

  @Test
  public void testFluentImpl() throws FileNotFoundException {
    TypeDef fluentImpl = ClazzAs.FLUENT_IMPL.apply(simpleClassWithDateDef);
    System.out.println(fluentImpl);

    assertEquals(Kind.CLASS, fluentImpl.getKind());
    assertEquals("SimpleClassWithDateFluentImpl", fluentImpl.getName());
    assertEquals(1, fluentImpl.getExtendsList().size());

    ClassRef superClass = fluentImpl.getExtendsList().iterator().next();
    assertEquals("SimpleClassFluentImpl", superClass.getName());
    assertEquals(1, superClass.getArguments().size());
    assertEquals("A", superClass.getArguments().iterator().next().toString());
  }

  @Test
  public void testBuilder() throws FileNotFoundException {
    TypeDef builder = ClazzAs.BUILDER.apply(simpleClassWithDateDef);
    System.out.println(builder);

    assertEquals(Kind.CLASS, builder.getKind());
    assertEquals("SimpleClassWithDateBuilder", builder.getName());
    assertEquals(1, builder.getExtendsList().size());

    ClassRef superClass = builder.getImplementsList().iterator().next();
    assertEquals(builderContext.getVisitableBuilderInterface().getName(), superClass.getName());
    assertEquals(2, superClass.getArguments().size());
    Iterator<TypeRef> argIterator = superClass.getArguments().iterator();
    TypeRef ref = argIterator.next();
    assertEquals("testpackage.SimpleClassWithDate", ref.render());
    assertEquals("testpackage.SimpleClassWithDate", ref.toString());

    ref = argIterator.next();
    assertEquals("testpackage.SimpleClassWithDateBuilder", ref.render());
    assertEquals("testpackage.SimpleClassWithDateBuilder", ref.toString());
  }

  @Test
  public void testEditable() {
    TypeDef editable = ClazzAs.EDITABLE.apply(simpleClassWithDateDef);
    System.out.println(editable);

    assertEquals(Kind.CLASS, editable.getKind());
    assertEquals("EditableSimpleClassWithDate", editable.getName());
    assertEquals(1, editable.getExtendsList().size());

    ClassRef superClass = editable.getExtendsList().iterator().next();
    assertEquals(simpleClassWithDateDef.toInternalReference(), superClass);
  }

  @Test
  public void testInline() {
    TypeDef inlineable = BuildableProcessor.inlineableOf(builderContext, simpleClassWithDateDef, inline);
    System.out.println(inlineable);
    assertEquals(Kind.CLASS, inlineable.getKind());
    assertEquals("CallableSimpleClassWithDate", inlineable.getName());
  }
}
