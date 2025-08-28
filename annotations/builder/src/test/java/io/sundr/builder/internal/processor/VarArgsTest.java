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

import org.junit.Test;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.ClazzAs;
import io.sundr.model.Kind;
import io.sundr.model.Method;
import io.sundr.model.RichTypeDef;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.utils.TypeArguments;

public class VarArgsTest extends AbstractProcessorTest {

  private final AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

  RichTypeDef varArgsClassDef = TypeArguments.apply(Sources.readTypeDefFromResource("VarArgsClass.java", context));
  RichTypeDef buildableItemDef = TypeArguments.apply(Sources.readTypeDefFromResource("BuildableItem.java", context));

  @Test
  public void testFluentWithBuildableVarArgs() {
    // Register BuildableItem in the buildable repository so it's recognized as buildable
    BuilderContextManager.getContext().getBuildableRepository().register(buildableItemDef);

    TypeDef fluent = ClazzAs.FLUENT.apply(varArgsClassDef);
    System.out.println(fluent);

    assertEquals(Kind.CLASS, fluent.getKind());
    assertEquals("VarArgsClassFluent", fluent.getName());

    // Check if there's a method for handling the buildable var-args
    boolean hasItemsMethod = false;
    boolean hasAddToItemsMethod = false;
    boolean hasRemoveFromItemsMethod = false;
    boolean hasBuildItemsMethod = false;

    for (Method method : fluent.getMethods()) {
      if (method.getName().equals("withItems")) {
        hasItemsMethod = true;
      }
      if (method.getName().equals("addToItems")) {
        hasAddToItemsMethod = true;
      }
      if (method.getName().equals("removeFromItems")) {
        hasRemoveFromItemsMethod = true;
      }
      if (method.getName().equals("buildItems")) {
        hasBuildItemsMethod = true;
      }
    }

    System.out.println("Methods found:");
    for (Method method : fluent.getMethods()) {
      System.out.println("  " + method.getName() + "(" + method.getArguments() + ")");
    }

    assertTrue("Should have withItems method for buildable var-args", hasItemsMethod);
    assertTrue("Should have addToItems method for buildable var-args", hasAddToItemsMethod);
    assertTrue("Should have removeFromItems method for buildable var-args", hasRemoveFromItemsMethod);
    assertTrue("Should have buildItems method for buildable var-args", hasBuildItemsMethod);
  }

  @Test
  public void testBuilderWithBuildableVarArgs() {
    TypeDef builder = ClazzAs.BUILDER.apply(varArgsClassDef);
    System.out.println(builder);

    assertEquals(Kind.CLASS, builder.getKind());
    assertEquals("VarArgsClassBuilder", builder.getName());

    // Check if there's a build method that properly handles var-args
    boolean hasBuildMethod = false;
    for (Method method : builder.getMethods()) {
      if (method.getName().equals("build")) {
        hasBuildMethod = true;
        break;
      }
    }

    assertTrue("Should have build method", hasBuildMethod);
  }
}
