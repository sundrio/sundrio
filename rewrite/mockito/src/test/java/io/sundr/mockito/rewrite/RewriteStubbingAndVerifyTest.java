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

class RewriteStubbingAndVerifyTest implements RewriteTest {

  @Override
  public void defaults(RecipeSpec spec) {
    spec.recipe(new RewriteStubbingAndVerify())
        .parser(JavaParser.fromJavaVersion().classpath("mockito-core"))
        .afterTypeValidationOptions(TypeValidation.none());
  }

  private static final String SERVICE = "package svc;\n" +
      "public interface TemplateService {\n" +
      "  String create(String id, String name, boolean overwrite);\n" +
      "  String find(String id);\n" +
      "  void delete(String id);\n" +
      "}\n";

  private static final String MOCK_BEAN = "package org.springframework.boot.test.mock.mockito;\n" +
      "public @interface MockBean {}\n";

  private static final String LIMITS_SERVICE = "package svc;\n" +
      "public interface LimitsService {\n" +
      "  String getStats() throws Exception;\n" +
      "}\n";

  private static final String OVERLOADED_SERVICE = "package svc;\n" +
      "public interface Svc {\n" +
      "  void exec(String id, String op);\n" +
      "  void exec(Integer code, String op, boolean force);\n" +
      "}\n";

  private static final String PREFIX_OVERLOAD_SERVICE = "package svc;\n" +
      "public interface Svc {\n" +
      "  void create(String templateId, String stackName);\n" +
      "  void create(String templateId, String stackName, boolean prefetched, String requestId);\n" +
      "}\n";

  @Test
  void noArgMethodWithThrowsMigrates() {
    rewriteRun(
        java(LIMITS_SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.LimitsService;\n" +
                "class T {\n" +
                "  void t() throws Exception {\n" +
                "    LimitsService m = mock(LimitsService.class);\n" +
                "    when(m.getStats()).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n" +
                "import svc.LimitsService;\n" +
                "import svc.Mocks;\n\n" +
                "class T {\n" +
                "  void t() throws Exception {\n" +
                "    LimitsService m = mock(LimitsService.class);\n" +
                "    Mocks.mock(m).when().getStats().thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void stubbingWithEqLiteralAndAny() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    when(m.create(eq(\"ID\"), any(), true)).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).when().create().withId(\"ID\").withOverwrite(true).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void stubbingWithMatcher() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    when(m.find(argThat(s -> s.startsWith(\"tpl-\")))).thenReturn(\"Y\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).when().find().withIdMatching(s -> s.startsWith(\"tpl-\")).thenReturn(\"Y\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void verifyTimesNeverBare() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    verify(m).delete(\"A\");\n" +
                "    verify(m, times(2)).delete(\"B\");\n" +
                "    verify(m, never()).delete(\"C\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).verify().delete().withId(\"A\").called();\n" +
                "    Mocks.mock(m).verify().delete().withId(\"B\").times(2);\n" +
                "    Mocks.mock(m).verify().delete().withId(\"C\").never();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void storedMatcherVarLeftWithTodo() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import org.mockito.ArgumentMatcher;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    ArgumentMatcher<String> matcher = s -> s.startsWith(\"tpl-\");\n" +
                "    when(m.find(argThat(matcher))).thenReturn(\"Y\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.*;\n" +
                "import org.mockito.ArgumentMatcher;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    ArgumentMatcher<String> matcher = s -> s.startsWith(\"tpl-\");\n" +
                "    // TODO mockito-annotations: cannot migrate; an argument uses a matcher with no fluent-DSL equivalent (for example a matcher held in a variable). Migrate this call by hand.\n"
                +
                "    when(m.find(argThat(matcher))).thenReturn(\"Y\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void prunesExplicitMockitoStaticImportsAfterRewrite() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.when;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import static org.mockito.ArgumentMatchers.eq;\n" +
                "import static org.mockito.ArgumentMatchers.any;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    when(m.create(eq(\"ID\"), any(), true)).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).when().create().withId(\"ID\").withOverwrite(true).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void keepsMockitoStaticImportStillUsedByAnUnmigratedTodoCall() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.when;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import static org.mockito.ArgumentMatchers.eq;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  TemplateService getSvc() { return null; }\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    when(m.find(eq(\"A\"))).thenReturn(\"X\");\n" +
                "    when(getSvc().find(eq(\"B\"))).thenReturn(\"Y\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.when;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "import static org.mockito.ArgumentMatchers.eq;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  TemplateService getSvc() { return null; }\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).when().find().withId(\"A\").thenReturn(\"X\");\n" +
                "    // TODO mockito-annotations: cannot migrate; the stub receiver is not a simple mock reference. Migrate this call by hand.\n"
                +
                "    when(getSvc().find(eq(\"B\"))).thenReturn(\"Y\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void stubbingOnSpringMockBeanField() {
    rewriteRun(
        java(SERVICE),
        java(MOCK_BEAN),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    when(svc.create(any(), any(), anyBoolean())).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    Mocks.mock(svc).when().create().thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void fullyQualifiedMatchersMapLikeBare() {
    rewriteRun(
        java(SERVICE),
        java(
            "import org.mockito.Mockito;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = Mockito.mock(TemplateService.class);\n" +
                "    Mockito.when(m.create(Mockito.eq(\"ID\"), Mockito.anyString(), Mockito.any()))" +
                ".thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import org.mockito.Mockito;\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = Mockito.mock(TemplateService.class);\n" +
                "    Mocks.mock(m).when().create().withId(\"ID\").thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void thisReceiverNormalizesToBareName() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  TemplateService m = mock(TemplateService.class);\n" +
                "  void t() {\n" +
                "    when(this.m.find(eq(\"A\"))).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  TemplateService m = mock(TemplateService.class);\n" +
                "  void t() {\n" +
                "    Mocks.mock(m).when().find().withId(\"A\").thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void nonIdentifierReceiverLeftWithTodo() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  TemplateService getSvc() { return null; }\n" +
                "  void t() {\n" +
                "    when(getSvc().find(eq(\"A\"))).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  TemplateService getSvc() { return null; }\n" +
                "  void t() {\n" +
                "    // TODO mockito-annotations: cannot migrate; the stub receiver is not a simple mock reference. Migrate this call by hand.\n"
                +
                "    when(getSvc().find(eq(\"A\"))).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void doReturnFormRewrittenToAnswerFirst() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    doReturn(\"X\").when(m).create(\"ID\", \"N\", true);\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.doReturn(\"X\").when(m).create().withId(\"ID\").withName(\"N\").withOverwrite(true).done();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void doReturnFormOnSpringMockBeanField() {
    rewriteRun(
        java(SERVICE),
        java(MOCK_BEAN),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    doReturn(\"X\").when(svc).find(\"K\");\n" +
                "  }\n" +
                "}\n",
            "import org.springframework.boot.test.mock.mockito.MockBean;\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  @MockBean TemplateService svc;\n" +
                "  void t() {\n" +
                "    Mocks.doReturn(\"X\").when(svc).find().withId(\"K\").done();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void doThrowFormRewrittenToAnswerFirst() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    doThrow(new IllegalStateException()).when(m).delete(\"LOCKED\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.doThrow(new IllegalStateException()).when(m).delete().withId(\"LOCKED\").done();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void doFormWithStoredMatcherVarLeftWithTodo() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import org.mockito.ArgumentMatcher;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    ArgumentMatcher<String> matcher = s -> s.startsWith(\"tpl-\");\n" +
                "    doReturn(\"X\").when(m).find(argThat(matcher));\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.*;\n" +
                "import org.mockito.ArgumentMatcher;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    ArgumentMatcher<String> matcher = s -> s.startsWith(\"tpl-\");\n" +
                "    // TODO mockito-annotations: cannot migrate; an argument uses a matcher with no fluent-DSL equivalent (for example a matcher held in a variable). Migrate this call by hand.\n"
                +
                "    doReturn(\"X\").when(m).find(argThat(matcher));\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void stringMatcherMapsToDedicatedStringWither() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    verify(m).find(contains(\"lobby\"));\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).verify().find().withIdContains(\"lobby\").called();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void verifyResultAssignedToVariableDropsTheLeftHandSide() {
    rewriteRun(
        java(SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.TemplateService;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    String result = verify(m).find(\"ID\");\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.TemplateService;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    TemplateService m = mock(TemplateService.class);\n" +
                "    Mocks.mock(m).verify().find().withId(\"ID\").called();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void crossPackageCallSiteGainsAggregatorImport() {
    rewriteRun(
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
                "}\n"));
  }

  @Test
  void verifyOnOverloadedMethodPinsAnyMatchers() {
    rewriteRun(
        java(OVERLOADED_SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.Svc;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    verify(m).exec(any(), any());\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.Svc;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    Mocks.mock(m).verify().exec().withIdAny().withOpAny().called();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void verifyOnShorterPrefixOverloadLocksArity() {
    rewriteRun(
        java(PREFIX_OVERLOAD_SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.Svc;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    verify(m).create(any(), any());\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.Svc;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    Mocks.mock(m).verify().create().withTemplateIdAny().withStackNameAny().andNoOtherArgs().called();\n"
                +
                "  }\n" +
                "}\n"));
  }

  @Test
  void verifyOnLongerPrefixOverloadDoesNotLockArity() {
    rewriteRun(
        java(PREFIX_OVERLOAD_SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.Svc;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    verify(m).create(any(), any(), anyBoolean(), any());\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.Svc;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    Mocks.mock(m).verify().create().withTemplateIdAny().withStackNameAny().withPrefetchedAny().withRequestIdAny().called();\n"
                +
                "  }\n" +
                "}\n"));
  }

  @Test
  void stubbingOnOverloadedMethodPinsAnyMatchers() {
    rewriteRun(
        java(OVERLOADED_SERVICE),
        java(
            "import static org.mockito.Mockito.*;\n" +
                "import svc.Svc;\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    doNothing().when(m).exec(any(), any());\n" +
                "  }\n" +
                "}\n",
            "import static org.mockito.Mockito.mock;\n\n" +
                "import svc.Mocks;\n" +
                "import svc.Svc;\n\n" +
                "class T {\n" +
                "  void t() {\n" +
                "    Svc m = mock(Svc.class);\n" +
                "    Mocks.doNothing().when(m).exec().withIdAny().withOpAny().done();\n" +
                "  }\n" +
                "}\n"));
  }

  @Test
  void samePackageCallSiteImportsOwnPackageMocks() {
    rewriteRun(
        java("package com.acme.svc;\n" +
            "public interface Foo { String find(String id); }\n"),
        java(
            "package com.acme.svc;\n" +
                "import static org.mockito.Mockito.*;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Foo foo = mock(Foo.class);\n" +
                "    when(foo.find(\"ID\")).thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n",
            "package com.acme.svc;\n" +
                "import static org.mockito.Mockito.mock;\n" +
                "class SomeTest {\n" +
                "  void t() {\n" +
                "    Foo foo = mock(Foo.class);\n" +
                "    Mocks.mock(foo).when().find().withId(\"ID\").thenReturn(\"X\");\n" +
                "  }\n" +
                "}\n"));
  }
}
