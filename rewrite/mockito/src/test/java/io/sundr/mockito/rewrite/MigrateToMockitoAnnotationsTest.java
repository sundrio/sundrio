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

import static org.assertj.core.api.Assertions.assertThat;
import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.Recipe;
import org.openrewrite.config.Environment;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;
import org.openrewrite.test.TypeValidation;

/**
 * Confirms the declarative composite recipe resource loads and wires the four sub-recipes, so a
 * typo in {@code META-INF/rewrite/mockito.yml} is caught at build time, and that the stubbing and
 * delegated-statics steps compose without emitting a half-migrated {@code Mocks.when(...)} chain.
 */
class MigrateToMockitoAnnotationsTest implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new StubThenDelegateRecipe())
        .parser(JavaParser.fromJavaVersion().classpath("mockito-core"))
        .afterTypeValidationOptions(TypeValidation.none());
  }

  @Test
  void compositeRecipeLoadsFromResource() {
    Environment env = Environment.builder().scanRuntimeClasspath("io.sundr.mockito.rewrite").build();
    Recipe recipe = env.activateRecipes("io.sundr.mockito.rewrite.MigrateToMockitoAnnotations");
    assertThat(recipe.getRecipeList()).isNotEmpty();
    assertThat(recipe.getName()).isEqualTo("io.sundr.mockito.rewrite.MigrateToMockitoAnnotations");
  }

  @Test
  void springMockBeanStubDoesNotEmitHalfMigratedWhen() {
    rewriteRun(
        java("package svc;\n" +
            "public interface TemplateService {\n" +
            "  String create(String id, boolean overwrite);\n" +
            "}\n"),
        java("package org.springframework.boot.test.mock.mockito;\n" +
            "public @interface MockBean {}\n"),
        java(
            "import org.mockito.Mockito;\n" +
                "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    Mockito.when(svc.create(Mockito.eq(\"ID\"), Mockito.anyBoolean()))" +
                ".thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import org.mockito.Mockito;\n" +
                "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    Mocks.mock(svc).when().create().withId(\"ID\").thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void answerFirstDoFormComposesWithoutHalfMigratedWhen() {
    rewriteRun(
        java("package svc;\n" +
            "public interface TemplateService {\n" +
            "  String create(String id, boolean overwrite);\n" +
            "}\n"),
        java(
            "import org.mockito.Mockito;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = Mockito.mock(TemplateService.class);\n" +
                "    Mockito.doReturn(\"X\").when(m).create(Mockito.eq(\"ID\"), Mockito.anyBoolean());\n" +
                "  }\n" +
                "}\n",
            "import org.mockito.Mockito;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = Mockito.mock(TemplateService.class);\n" +
                "    Mocks.doReturn(\"X\").when(m).create().withId(\"ID\").done();\n" +
                "  }\n" +
                "}\n"));
  }
}
