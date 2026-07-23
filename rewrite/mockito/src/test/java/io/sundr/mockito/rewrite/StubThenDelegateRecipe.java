/*
 * Copyright 2015 The original authors.
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

package io.sundr.mockito.rewrite;

import java.util.List;

import org.openrewrite.Recipe;

/**
 * Test-only composition running the stubbing rewrite before the delegated-statics rewrite, in the
 * same order as the declarative composite, to prove the two steps never emit a half-migrated
 * {@code Mocks.when(...)} chain.
 */
public class StubThenDelegateRecipe extends Recipe {

  @Override
  public String getDisplayName() {
    return "Stubbing then delegated statics";
  }

  @Override
  public String getDescription() {
    return "Composition of the stubbing and delegated-statics rewrites.";
  }

  @Override
  public List<Recipe> getRecipeList() {
    return List.of(new RewriteStubbingAndVerify(), new RewriteDelegatedStatics());
  }

  /**
   * Full migration order (marker generation followed by the two rewrites), used to prove that the
   * package {@code GenerateMarker} emits {@code Mocks} into is the exact package the rewrites import.
   */
  public static final class WithMarker extends Recipe {

    @Override
    public String getDisplayName() {
      return "Generate marker then rewrite";
    }

    @Override
    public String getDescription() {
      return "Marker generation followed by the stubbing and delegated-statics rewrites.";
    }

    @Override
    public List<Recipe> getRecipeList() {
      return List.of(new GenerateMarker(), new RewriteStubbingAndVerify(),
          new RewriteDelegatedStatics());
    }
  }
}
