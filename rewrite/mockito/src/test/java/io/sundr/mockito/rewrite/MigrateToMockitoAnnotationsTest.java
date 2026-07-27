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
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    Mocks.mock(svc).when().create().withId(\"ID\").thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void generatedMarkerAndRewrittenImportAgreeOnPackage() {
    rewriteRun(
        spec -> spec.recipe(new StubThenDelegateRecipe.WithMarker()),
        java("package com.acme.a;\n" +
            "public interface Foo { String find(String id); }\n"),
        java("package com.acme.b;\n" +
            "public interface Bar { void delete(String id); }\n"),
        java(
            "package com.acme.x;\n" +
                "import static org.mockito.Mockito.*;\n" +
                "import com.acme.a.Foo;\n" +
                "import com.acme.b.Bar;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Foo foo = mock(Foo.class);\n" +
                "    Bar bar = mock(Bar.class);\n" +
                "    when(foo.find(\"ID\")).thenReturn(\"X\");\n" +
                "    verify(bar).delete(\"A\");\n" +
                "  }\n" +
                "}\n",
            "package com.acme.x;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import com.acme.a.Foo;\n" +
                "import com.acme.a.Mocks;\n" +
                "import com.acme.b.Bar;\n\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Foo foo = mock(Foo.class);\n" +
                "    Bar bar = mock(Bar.class);\n" +
                "    Mocks.mock(foo).when().find().withId(\"ID\").thenReturn(\"X\");\n" +
                "    Mocks.mock(bar).verify().delete().withId(\"A\").called();\n" +
                "  }\n" +
                "}\n"),
        java(null,
            "package com.acme.a;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n" +
                "import com.acme.b.Bar;\n\n" +
                "@Mockables({ Foo.class, Bar.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("src/test/java/com/acme/a/MocksConfig.java")));
  }

  /**
   * The real-world regression: a hand-written marker (named {@code MyMarker}, not {@code MocksConfig})
   * lives in {@code com.acme.app}, but the mocked types span {@code com.acme.app.sub} and
   * {@code com.acme.other} whose least-common-denominator is {@code com.acme}. The aggregator lives
   * in the marker's package, so the rewritten call site must import {@code com.acme.app.Mocks}, NOT
   * {@code com.acme.Mocks} (the types' LCD, which has no {@code Mocks}). No new marker is generated.
   */
  @Test
  void handWrittenMarkerOutsideTypesLcdDrivesTheImport() {
    rewriteRun(
        spec -> spec.recipe(new StubThenDelegateRecipe.WithMarker()),
        java("package io.sundr.mockito.annotations;\n" +
            "public @interface Mockables { Class<?>[] value(); }\n"),
        java("package com.acme.app.sub;\n" +
            "public interface A { String foo(String id); }\n"),
        java("package com.acme.other;\n" +
            "public interface B { void bar(String id); }\n"),
        java("package com.acme.app;\n" +
            "import io.sundr.mockito.annotations.Mockables;\n" +
            "import com.acme.app.sub.A;\n" +
            "import com.acme.other.B;\n" +
            "@Mockables({ A.class, B.class })\n" +
            "public class MyMarker {\n}\n"),
        java(
            "package com.acme.app.svc;\n" +
                "import static org.mockito.Mockito.*;\n" +
                "import com.acme.app.sub.A;\n" +
                "import com.acme.other.B;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    A a = mock(A.class);\n" +
                "    B b = mock(B.class);\n" +
                "    when(a.foo(\"ID\")).thenReturn(\"X\");\n" +
                "    verify(b).bar(\"A\");\n" +
                "  }\n" +
                "}\n",
            "package com.acme.app.svc;\n" +
                "import static org.mockito.Mockito.mock;\n\n" +
                "import com.acme.app.Mocks;\n" +
                "import com.acme.app.sub.A;\n" +
                "import com.acme.other.B;\n\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    A a = mock(A.class);\n" +
                "    B b = mock(B.class);\n" +
                "    Mocks.mock(a).when().foo().withId(\"ID\").thenReturn(\"X\");\n" +
                "    Mocks.mock(b).verify().bar().withId(\"A\").called();\n" +
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
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = Mockito.mock(TemplateService.class);\n" +
                "    Mocks.doReturn(\"X\").when(m).create().withId(\"ID\").done();\n" +
                "  }\n" +
                "}\n"));
  }
}
