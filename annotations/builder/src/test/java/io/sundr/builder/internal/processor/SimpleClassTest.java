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
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.functions.Sources;
import io.sundr.model.ClassRef;
import io.sundr.model.Kind;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeRef;

public class SimpleClassTest extends AbstractProcessorTest {

  TypeDef simpleClassDef = Sources.FROM_INPUTSTEAM_TO_SINGLE_TYPEDEF
      .apply(getClass().getClassLoader().getResourceAsStream("SimpleClass.java"));

  @Test
  public void testFluent() {
    TypeDef fluent = ClazzAs.FLUENT_INTERFACE.apply(simpleClassDef);
    System.out.println(fluent);

    assertEquals(Kind.INTERFACE, fluent.getKind());
    assertEquals("SimpleClassFluent", fluent.getName());
    assertEquals(1, fluent.getExtendsList().size());

    ClassRef superClass = fluent.getExtendsList().iterator().next();
    assertEquals("Fluent", superClass.getName());
    assertEquals(1, superClass.getArguments().size());
    assertEquals("A", superClass.getArguments().iterator().next().toString());
  }

  @Test
  public void testFluentImpl() {
    TypeDef fluentImpl = ClazzAs.FLUENT_IMPL.apply(simpleClassDef);
    System.out.println(fluentImpl);

    assertEquals(Kind.CLASS, fluentImpl.getKind());
    assertEquals("SimpleClassFluentImpl", fluentImpl.getName());
    assertEquals(1, fluentImpl.getExtendsList().size());

    assertTrue(hasMethod(fluentImpl, "addToTags", ClassTo.TYPEREF.apply(String.class).withDimensions(1)));
    assertTrue(hasMethod(fluentImpl, "removeFromTags", ClassTo.TYPEREF.apply(String.class).withDimensions(1)));

    ClassRef superClass = fluentImpl.getExtendsList().iterator().next();
    assertEquals("BaseFluent", superClass.getName());
    assertEquals(1, superClass.getArguments().size());
    assertEquals("A", superClass.getArguments().iterator().next().toString());
  }

  @Test
  public void testBuilder() {
    TypeDef builder = ClazzAs.BUILDER.apply(simpleClassDef);
    System.out.println(builder);

    assertEquals(Kind.CLASS, builder.getKind());
    assertEquals("SimpleClassBuilder", builder.getName());
    assertEquals(1, builder.getExtendsList().size());

    ClassRef superClass = builder.getImplementsList().iterator().next();
    assertEquals("VisitableBuilder", superClass.getName());
    assertEquals(2, superClass.getArguments().size());
    Iterator<TypeRef> argIterator = superClass.getArguments().iterator();
    TypeRef ref = argIterator.next();
    assertEquals("testpackage.SimpleClass", ref.toString());
    assertEquals("testpackage.SimpleClass", ref.render());
    ref = argIterator.next();
    assertEquals("testpackage.SimpleClassBuilder", ref.toString());
    assertEquals("testpackage.SimpleClassBuilder", ref.render());
  }

  @Test
  public void testEditable() {
    TypeDef editable = ClazzAs.EDITABLE.apply(simpleClassDef);
    System.out.println(editable);

    assertEquals(Kind.CLASS, editable.getKind());
    assertEquals("EditableSimpleClass", editable.getName());
    assertEquals(1, editable.getExtendsList().size());

    ClassRef superClass = editable.getExtendsList().iterator().next();
    assertEquals(simpleClassDef.toReference(), superClass);
  }

  @Test
  public void testInline() {
    TypeDef inlineable = BuildableProcessor.inlineableOf(builderContext, simpleClassDef, inline);
    System.out.println(inlineable);
    assertEquals(Kind.CLASS, inlineable.getKind());
    assertEquals("CallableSimpleClass", inlineable.getName());
  }
}
