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

package io.sundr.mockito.internal.processor;

import static com.google.testing.compile.CompilationSubject.assertThat;
import static com.google.testing.compile.Compiler.javac;

import javax.tools.JavaFileObject;

import org.junit.jupiter.api.Test;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.CompilationSubject;
import com.google.testing.compile.JavaFileObjects;

public class MockableProcessorTest {

  private static final JavaFileObject TEMPLATE_SERVICE = JavaFileObjects.forSourceString("test.TemplateService",
      "package test;\n" +
          "import io.sundr.mockito.annotations.Mockable;\n" +
          "@Mockable\n" +
          "public interface TemplateService {\n" +
          "  String create(String id, String name, boolean overwrite);\n" +
          "  void delete(String id);\n" +
          "}\n");

  @Test
  public void shouldGenerateStubDslForAnnotatedInterface() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    CompilationSubject.assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateService mock()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock mock(TemplateService mock)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.Stub when()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.CreateStub withId(String value)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.CreateStub withOverwrite(boolean value)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("thenReturn(String value)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("new Arg<String>()");
  }

  @Test
  public void shouldGenerateVerifyDsl() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.Verify verify()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.CreateVerify withId(String value)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.CreateVerify capturingId(ArgumentCaptor<String> captor)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public void called()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public void times(int invocations)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public void verified(VerificationMode mode)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("Mockito.verify(this.mock, mode).create(this.id.resolve(), this.name.resolve(), this.overwrite.resolve())");
  }

  @Test
  public void shouldSkipVerifyDslWhenDisabled() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.QuietService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable(verification = false)\n" +
            "public interface QuietService {\n" +
            "  String name();\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.QuietServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("Verify");
  }

  @Test
  public void shouldEraseTypeParametersOfGenericMockableType() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.GenericService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface GenericService<T> {\n" +
            "  String describe();\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.GenericServiceMock")
        .contentsAsUtf8String()
        .contains("private final GenericService mock");
    assertThat(compilation)
        .generatedSourceFile("test.GenericServiceMock")
        .contentsAsUtf8String()
        .contains("public static GenericService mock()");
    assertThat(compilation)
        .generatedSourceFile("test.GenericServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("<T>");
  }

  @Test
  public void shouldStubMethodsUsingClassOwnTypeVariable() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.Repo",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface Repo<T> {\n" +
            "  T findById(String id);\n" +
            "  java.util.List<T> findAll();\n" +
            "  <R> R convert(R in);\n" +
            "  void delete(String id);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.RepoMock")
        .contentsAsUtf8String()
        .contains("public RepoMock.FindByIdStub findById()");
    assertThat(compilation)
        .generatedSourceFile("test.RepoMock")
        .contentsAsUtf8String()
        .contains("thenReturn(Object value)");
    assertThat(compilation)
        .generatedSourceFile("test.RepoMock")
        .contentsAsUtf8String()
        .contains("public RepoMock.FindAllStub findAll()");
    assertThat(compilation)
        .generatedSourceFile("test.RepoMock")
        .contentsAsUtf8String()
        .doesNotContain("convert");
  }

  @Test
  public void shouldEraseBoundedClassTypeVariableToItsBound() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.BoundedRepo",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface BoundedRepo<T extends CharSequence> {\n" +
            "  T findById(String id);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.BoundedRepoMock")
        .contentsAsUtf8String()
        .contains("thenReturn(CharSequence value)");
  }

  @Test
  public void shouldUseTypedSlotsForPrimitiveArguments() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.CounterService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface CounterService {\n" +
            "  long increment(String name, int delta, boolean force);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.CounterServiceMock")
        .contentsAsUtf8String()
        .contains("IntArg");
    assertThat(compilation)
        .generatedSourceFile("test.CounterServiceMock")
        .contentsAsUtf8String()
        .contains("BooleanArg");
    assertThat(compilation)
        .generatedSourceFile("test.CounterServiceMock")
        .contentsAsUtf8String()
        .contains("public CounterServiceMock.IncrementStub withDelta(int value)");
  }

  @Test
  public void shouldGenerateDoFamilyTerminalsForVoidMethods() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("doThrow");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public void doNothing()");
  }

  @Test
  public void shouldGenerateAnswerFirstDoFamily() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock.TemplateServiceMockDoStubber doReturn(Object value)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock.TemplateServiceMockDoStubber doThrow(Throwable... throwables)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains(
            "public static TemplateServiceMock.TemplateServiceMockDoStubber doThrow(Class<? extends Throwable> throwableType)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock.TemplateServiceMockDoStubber doNothing()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock.TemplateServiceMockDoStubber doCallRealMethod()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public TemplateServiceMock.CreateDoStub withId(String value)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public void done()");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("this.stubber.when(this.mock).delete(this.id.resolve())");
  }

  @Test
  public void shouldDeclareCheckedExceptionsOnDoFamilyTerminal() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.ExportService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "import java.io.IOException;\n" +
            "@Mockable\n" +
            "public interface ExportService {\n" +
            "  String export(String id) throws IOException;\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.ExportServiceMock")
        .contentsAsUtf8String()
        .contains("public void done() throws IOException");
  }

  @Test
  public void shouldGenerateMocksAndAggregatorForMockablesMarker() {
    JavaFileObject templateService = JavaFileObjects.forSourceString("test.TemplateService",
        "package test;\n" +
            "public interface TemplateService {\n" +
            "  String create(String id, String name, boolean overwrite);\n" +
            "}\n");
    JavaFileObject paymentService = JavaFileObjects.forSourceString("test.PaymentService",
        "package test;\n" +
            "public interface PaymentService {\n" +
            "  void charge(String account, long amount);\n" +
            "}\n");
    JavaFileObject marker = JavaFileObjects.forSourceString("test.mocks.MockTargets",
        "package test.mocks;\n" +
            "import io.sundr.mockito.annotations.Mockables;\n" +
            "import test.TemplateService;\n" +
            "import test.PaymentService;\n" +
            "@Mockables({ TemplateService.class, PaymentService.class })\n" +
            "class MockTargets {\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(templateService, paymentService, marker);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock mock(TemplateService mock)");
    assertThat(compilation)
        .generatedSourceFile("test.mocks.Mocks")
        .contentsAsUtf8String()
        .contains("public static TemplateServiceMock mock(TemplateService mock)");
    assertThat(compilation)
        .generatedSourceFile("test.mocks.Mocks")
        .contentsAsUtf8String()
        .contains("return TemplateServiceMock.mock(mock)");
    assertThat(compilation)
        .generatedSourceFile("test.mocks.Mocks")
        .contentsAsUtf8String()
        .contains("public static PaymentServiceMock mock(PaymentService mock)");
  }

  @Test
  public void shouldSkipAggregatorWhenDisabled() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.SoloService",
        "package test;\n" +
            "public interface SoloService {\n" +
            "  String name();\n" +
            "}\n");
    JavaFileObject marker = JavaFileObjects.forSourceString("test.MockTargets",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockables;\n" +
            "@Mockables(value = SoloService.class, aggregator = \"\")\n" +
            "class MockTargets {\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service, marker);

    assertThat(compilation).succeeded();
    org.junit.jupiter.api.Assertions.assertTrue(compilation.generatedSourceFiles().stream()
        .noneMatch(file -> file.getName().endsWith("Mocks.java")));
  }

  @Test
  public void shouldFailWhenAggregatorCollidesWithMarkerName() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.SoloService",
        "package test;\n" +
            "public interface SoloService {\n" +
            "  String name();\n" +
            "}\n");
    JavaFileObject marker = JavaFileObjects.forSourceString("test.Mocks",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockables;\n" +
            "@Mockables(SoloService.class)\n" +
            "class Mocks {\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service, marker);

    assertThat(compilation).hadErrorContaining("aggregator");
  }

  @Test
  public void shouldUnifyOverloadsBehindASingleDslMethod() {
    JavaFileObject spec = JavaFileObjects.forSourceString("test.Spec",
        "package test;\n" +
            "public class Spec {\n" +
            "}\n");
    JavaFileObject service = JavaFileObjects.forSourceString("test.RepositoryService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface RepositoryService {\n" +
            "  String load(String id);\n" +
            "  String load(Spec spec);\n" +
            "  String load(String id, int version);\n" +
            "  String save(String id);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(spec, service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.LoadStub load()");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("Load1");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.LoadStub withId(String value)");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.LoadStub withSpec(Spec value)");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.LoadStub withVersion(int value)");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("selector.selectAll(this.pinned, this.exact)");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.LoadStub withIdAny()");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.LoadStub andNoOtherArgs()");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("Mockito.lenient()");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("FanOutStubbing.of(starters");
    assertThat(compilation)
        .generatedSourceFile("test.RepositoryServiceMock")
        .contentsAsUtf8String()
        .contains("public RepositoryServiceMock.SaveStub save()");
  }

  @Test
  public void shouldSkipOverloadsWithHeterogeneousReturnTypes() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.MixedService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface MixedService {\n" +
            "  String find(String id);\n" +
            "  void find(String id, boolean force);\n" +
            "  String ok(String id);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation).hadWarningContaining("find");
    assertThat(compilation)
        .generatedSourceFile("test.MixedServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("FindStub");
    assertThat(compilation)
        .generatedSourceFile("test.MixedServiceMock")
        .contentsAsUtf8String()
        .contains("public MixedServiceMock.OkStub ok()");
  }

  @Test
  public void shouldHonorPrefixAndSuffix() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.NamedService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable(prefix = \"Mock\", suffix = \"\")\n" +
            "public interface NamedService {\n" +
            "  String name();\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.MockNamedService")
        .contentsAsUtf8String()
        .contains("public static MockNamedService mock(NamedService mock)");
  }

  @Test
  public void shouldRenameWhenMockNameAlreadyExists() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.NamedService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface NamedService {\n" +
            "  String name();\n" +
            "}\n");
    JavaFileObject existing = JavaFileObjects.forSourceString("test.NamedServiceMock",
        "package test;\n" +
            "public class NamedServiceMock {\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service, existing);

    assertThat(compilation).succeeded();
    assertThat(compilation).hadWarningContaining("NamedServiceMock' already exists");
    assertThat(compilation)
        .generatedSourceFile("test.NamedServiceGeneratedMock")
        .contentsAsUtf8String()
        .contains("public static NamedServiceGeneratedMock mock(NamedService mock)");
  }

  @Test
  public void shouldFailWhenMockNameExistsAndPolicyIsFail() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.NamedService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "import io.sundr.mockito.annotations.OnNameCollision;\n" +
            "@Mockable(onNameCollision = OnNameCollision.FAIL)\n" +
            "public interface NamedService {\n" +
            "  String name();\n" +
            "}\n");
    JavaFileObject existing = JavaFileObjects.forSourceString("test.NamedServiceMock",
        "package test;\n" +
            "public class NamedServiceMock {\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service, existing);

    assertThat(compilation).failed();
    assertThat(compilation).hadErrorContaining("NamedServiceMock' already exists");
  }
}
