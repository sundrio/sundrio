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

import static org.openrewrite.java.Assertions.java;

import org.junit.jupiter.api.Test;
import org.openrewrite.java.JavaParser;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;
import org.openrewrite.test.TypeValidation;

class RewriteDelegatedStaticsTest implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new RewriteDelegatedStatics())
        .parser(JavaParser.fromJavaVersion().classpath("mockito-core"))
        .afterTypeValidationOptions(TypeValidation.none());
  }

  private static final String SERVICE = "package svc;\n" +
      "public interface TemplateService {\n" +
      "  void delete(String id);\n" +
      "}\n";

  @Test
  void verifyNoInteractionsAndInOrder() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import org.mockito.InOrder;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    verifyNoInteractions(m);\n" +
                "    InOrder order = inOrder(m);\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n" +
                "import org.mockito.InOrder;\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.verifyNoInteractions(m);\n" +
                "    InOrder order = Mocks.inOrder(m);\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void crossPackageCallSiteGainsAggregatorImport() {
    rewriteRun(
        java("package com.acme.a;\n" +
            "public interface Foo { void delete(String id); }\n"),
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
                "    verifyNoInteractions(foo, bar);\n" +
                "  }\n" +
                "}\n",
            "package com.acme.x;\n" +
                "import static org.mockito.Mockito.mock;\n\n" +
                "import com.acme.Mocks;\n" +
                "import com.acme.a.Foo;\n" +
                "import com.acme.b.Bar;\n\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Foo foo = mock(Foo.class);\n" +
                "    Bar bar = mock(Bar.class);\n" +
                "    Mocks.verifyNoInteractions(foo, bar);\n" +
                "  }\n" +
                "}\n"));
  }
}
