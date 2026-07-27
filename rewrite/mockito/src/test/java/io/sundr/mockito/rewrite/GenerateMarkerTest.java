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

import java.util.List;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;
import org.openrewrite.test.TypeValidation;

class GenerateMarkerTest implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new GenerateMarker())
        .parser(JavaParser.fromJavaVersion().classpath("mockito-core"))
        .afterTypeValidationOptions(TypeValidation.none());
  }

  @Test
  void markerPackagePicksTheModalPackage() {
    assertThat(GenerateMarker.markerPackage(List.of(
        "com.acme.svc.Foo", "com.acme.svc.Baz", "com.acme.pay.Bar"))).isEqualTo("com.acme.svc");
  }

  @Test
  void markerPackageNeverEmptyForUnrelatedPackages() {
    assertThat(GenerateMarker.markerPackage(List.of(
        "com.acme.Foo", "org.other.Bar"))).isNotEmpty();
  }

  @Test
  void generatesMarkerFromMockCallAndMockField() {
    rewriteRun(
        java(
            "package com.acme.svc;\n" +
                "public interface Templates { String render(String id); }\n"),
        java(
            "package com.acme.pay;\n" +
                "public interface Payments { long charge(long cents); }\n"),
        java(
            "package com.acme.tests;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import org.mockito.Mock;\n" +
                "import com.acme.svc.Templates;\n" +
                "import com.acme.pay.Payments;\n" +
                "class SomeTest {\n" +
                "  @Mock Payments payments;\n" +
                "  void t() {\n" +
                "    Templates templates = mock(Templates.class);\n" +
                "  }\n" +
                "}\n"),
        java(null,
            "package com.acme.pay;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n" +
                "import com.acme.svc.Templates;\n\n" +
                "@Mockables({ Payments.class, Templates.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("src/test/java/com/acme/pay/MocksConfig.java")));
  }

  @Test
  void generatesMarkerInModalPackageWhenNoSharedPrefix() {
    rewriteRun(
        java(
            "package com.acme;\n" +
                "public interface Templates { String render(String id); }\n"),
        java(
            "package org.other;\n" +
                "public interface Payments { long charge(long cents); }\n"),
        java(
            "package tests;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import com.acme.Templates;\n" +
                "import org.other.Payments;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Templates templates = mock(Templates.class);\n" +
                "    Payments payments = mock(Payments.class);\n" +
                "  }\n" +
                "}\n"),
        java(null,
            "package com.acme;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n" +
                "import org.other.Payments;\n\n" +
                "@Mockables({ Templates.class, Payments.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("src/test/java/com/acme/MocksConfig.java")));
  }

  @Test
  void includesWhenVerifyReceiverTypeWithoutMockLiteral() {
    rewriteRun(
        java(
            "package com.acme.svc;\n" +
                "public interface Templates { String render(String id); }\n"),
        java(
            "package org.springframework.boot.test.mock.mockito;\n" +
                "public @interface MockBean {}\n"),
        java(
            "package com.acme.tests;\n" +
                "import static org.mockito.Mockito.when;\n" +
                "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import com.acme.svc.Templates;\n" +
                "class SomeTest {\n" +
                "  @MockBean Templates templates;\n" +
                "  void t() {\n" +
                "    when(templates.render(\"id\")).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"),
        java(null,
            "package com.acme.svc;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n\n" +
                "@Mockables({ Templates.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("src/test/java/com/acme/svc/MocksConfig.java")));
  }

  @Test
  void doesNotGenerateWhenHandWrittenMarkerUnderAnyNameExists() {
    rewriteRun(
        java(
            "package io.sundr.mockito.annotations;\n" +
                "public @interface Mockables { Class<?>[] value(); }\n"),
        java(
            "package com.acme.svc;\n" +
                "public interface Templates { String render(String id); }\n"),
        java(
            "package com.acme.app;\n" +
                "import io.sundr.mockito.annotations.Mockables;\n" +
                "import com.acme.svc.Templates;\n" +
                "@Mockables({ Templates.class })\n" +
                "public class MyMarker {\n}\n"),
        java(
            "package com.acme.tests;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import com.acme.svc.Templates;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Templates templates = mock(Templates.class);\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void generatesMarkerUnderSubmoduleInMultiModuleReactor() {
    rewriteRun(
        java(
            "package com.acme.svc;\n" +
                "public interface Templates { String render(String id); }\n",
            spec -> spec.path("cloudcomposer/src/main/java/com/acme/svc/Templates.java")),
        java(
            "package com.acme.tests;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import com.acme.svc.Templates;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Templates templates = mock(Templates.class);\n" +
                "  }\n" +
                "}\n",
            spec -> spec.path("cloudcomposer/src/test/java/com/acme/tests/SomeTest.java")),
        java(null,
            "package com.acme.svc;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n\n" +
                "@Mockables({ Templates.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("cloudcomposer/src/test/java/com/acme/svc/MocksConfig.java")));
  }

  @Test
  void generatesOnePerModuleMarkerScopedToEachModulesOwnMocks() {
    rewriteRun(
        // module A: mocks Templates
        java(
            "package com.acme.svc;\n" +
                "public interface Templates { String render(String id); }\n",
            spec -> spec.path("moduleA/src/main/java/com/acme/svc/Templates.java")),
        java(
            "package com.acme.a;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import com.acme.svc.Templates;\n" +
                "class ATest {\n" +
                "  void t() { Templates t = mock(Templates.class); }\n" +
                "}\n",
            spec -> spec.path("moduleA/src/test/java/com/acme/a/ATest.java")),
        // module B: mocks Payments only
        java(
            "package com.acme.pay;\n" +
                "public interface Payments { long charge(long cents); }\n",
            spec -> spec.path("moduleB/src/main/java/com/acme/pay/Payments.java")),
        java(
            "package com.acme.b;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import com.acme.pay.Payments;\n" +
                "class BTest {\n" +
                "  void t() { Payments p = mock(Payments.class); }\n" +
                "}\n",
            spec -> spec.path("moduleB/src/test/java/com/acme/b/BTest.java")),
        // module A's marker: only Templates, under moduleA
        java(null,
            "package com.acme.svc;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n\n" +
                "@Mockables({ Templates.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("moduleA/src/test/java/com/acme/svc/MocksConfig.java")),
        // module B's marker: only Payments, under moduleB
        java(null,
            "package com.acme.pay;\n\n" +
                "import io.sundr.mockito.annotations.Mockables;\n\n" +
                "@Mockables({ Payments.class })\n" +
                "public class MocksConfig {\n}\n",
            spec -> spec.path("moduleB/src/test/java/com/acme/pay/MocksConfig.java")));
  }
}
