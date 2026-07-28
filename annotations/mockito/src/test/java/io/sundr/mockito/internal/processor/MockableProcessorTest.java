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
  public void shouldGenerateLenientAndTypedAnswerTerminals() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public OngoingStubbing<String> thenAnswer(Answer<?> answer)");
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .contains("public OngoingStubbing<String> thenAnswerTyped(Answer<String> answer)");
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
  public void shouldStubOnlyPublicMethods() {
    JavaFileObject base = JavaFileObjects.forSourceString("test.Accessor",
        "package test;\n" +
            "public abstract class Accessor {\n" +
            "  protected String convert(Exception e) { return e.getMessage(); }\n" +
            "  String packagePrivate() { return \"x\"; }\n" +
            "}\n");
    JavaFileObject service = JavaFileObjects.forSourceString("test.RabbitService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public class RabbitService extends Accessor {\n" +
            "  public String send(String message) { return message; }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(base, service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.RabbitServiceMock")
        .contentsAsUtf8String()
        .contains("send()");
    assertThat(compilation)
        .generatedSourceFile("test.RabbitServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("convert");
    assertThat(compilation)
        .generatedSourceFile("test.RabbitServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("packagePrivate");
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
  }

  @Test
  public void shouldGenerateStringPredicateWithersForStringArguments() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    for (String predicate : new String[] { "Contains", "StartsWith", "EndsWith", "Matches" }) {
      assertThat(compilation)
          .generatedSourceFile("test.TemplateServiceMock")
          .contentsAsUtf8String()
          .contains("public TemplateServiceMock.CreateStub withId" + predicate + "(String expected)");
    }
  }

  @Test
  public void shouldNotGenerateStringPredicateWithersForNonStringArguments() {
    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(TEMPLATE_SERVICE);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.TemplateServiceMock")
        .contentsAsUtf8String()
        .doesNotContain("withOverwriteContains");
  }

  @Test
  public void shouldStubUserMethodSharingAnObjectMethodName() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.PageService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface PageService {\n" +
            "  String clone(String id);\n" +
            "  boolean equals(String a, String b);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.PageServiceMock")
        .contentsAsUtf8String()
        .contains("public PageServiceMock.CloneStub clone()");
    assertThat(compilation)
        .generatedSourceFile("test.PageServiceMock")
        .contentsAsUtf8String()
        .contains("public PageServiceMock.EqualsStub equals()");
  }

  @Test
  public void shouldSkipTheRealObjectMethods() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.Widget",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface Widget {\n" +
            "  String describe();\n" +
            "  boolean equals(Object other);\n" +
            "  int hashCode();\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.WidgetMock")
        .contentsAsUtf8String()
        .contains("describe()");
    assertThat(compilation)
        .generatedSourceFile("test.WidgetMock")
        .contentsAsUtf8String()
        .doesNotContain("EqualsStub");
    assertThat(compilation)
        .generatedSourceFile("test.WidgetMock")
        .contentsAsUtf8String()
        .doesNotContain("HashCodeStub");
  }

  @Test
  public void shouldInheritMethodsFromGenericSuperinterfaceSubstitutingTypeArguments() {
    JavaFileObject domain = JavaFileObjects.forSourceString("test.Domain",
        "package test;\n" +
            "public class Domain {}\n");
    JavaFileObject service = JavaFileObjects.forSourceString("test.DomainDAO",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "import java.util.List;\n" +
            "interface CrudRepo<T, ID> {\n" +
            "  List<T> findAllByIdIn(List<ID> ids);\n" +
            "  void emit(T message);\n" +
            "}\n" +
            "@Mockable\n" +
            "public interface DomainDAO extends CrudRepo<test.Domain, String> {}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service, domain);

    assertThat(compilation).succeeded();
    assertThat(compilation).generatedSourceFile("test.DomainDAOMock").contentsAsUtf8String()
        .contains("public DomainDAOMock.FindAllByIdInStub findAllByIdIn()");
    assertThat(compilation).generatedSourceFile("test.DomainDAOMock").contentsAsUtf8String()
        .contains("withIds(List<String> value)");
    assertThat(compilation).generatedSourceFile("test.DomainDAOMock").contentsAsUtf8String()
        .contains("capturingMessage(ArgumentCaptor<Domain> captor)");
    assertThat(compilation).generatedSourceFile("test.DomainDAOMock").contentsAsUtf8String()
        .doesNotContain("ArgumentCaptor<Object>");
  }

  @Test
  public void shouldSkipInheritedMethodWithABoundedOwnTypeVariableInAnArgument() {
    JavaFileObject domain = JavaFileObjects.forSourceString("test.Domain",
        "package test;\n" +
            "public class Domain {}\n");
    JavaFileObject service = JavaFileObjects.forSourceString("test.DomainDAO",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "interface CrudRepo<T, ID> {\n" +
            "  <S extends T> S save(S entity);\n" +
            "  T findById(ID id);\n" +
            "}\n" +
            "@Mockable\n" +
            "public interface DomainDAO extends CrudRepo<test.Domain, String> {}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service, domain);

    assertThat(compilation).succeeded();
    assertThat(compilation).generatedSourceFile("test.DomainDAOMock").contentsAsUtf8String()
        .contains("public DomainDAOMock.FindByIdStub findById()");
    assertThat(compilation).generatedSourceFile("test.DomainDAOMock").contentsAsUtf8String()
        .doesNotContain("SaveStub");
  }

  @Test
  public void shouldLetCallerInferTypeArgumentForGenericMockTargetCaptor() {
    JavaFileObject event = JavaFileObjects.forSourceString("test.StreamEvent",
        "package test;\n" +
            "public class StreamEvent {}\n");
    JavaFileObject service = JavaFileObjects.forSourceString("test.IStreamerService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface IStreamerService<T> {\n" +
            "  void emitMessage(T message);\n" +
            "}\n");
    // A caller that pins the captor to a concrete StreamEvent and stubs with a concrete value:
    // both must compile against the generated (raw) mock, so the withers must be generic.
    JavaFileObject caller = JavaFileObjects.forSourceString("test.Caller",
        "package test;\n" +
            "import org.mockito.ArgumentCaptor;\n" +
            "class Caller {\n" +
            "  void run(IStreamerService<StreamEvent> streamerService, StreamEvent event) {\n" +
            "    ArgumentCaptor<StreamEvent> captor = ArgumentCaptor.forClass(StreamEvent.class);\n" +
            "    IStreamerServiceMock.mock(streamerService).verify().emitMessage().capturingMessage(captor).called();\n"
            +
            "    IStreamerServiceMock.mock(streamerService).when().emitMessage().withMessage(event);\n" +
            "  }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(event, service, caller);

    assertThat(compilation).succeeded();
  }

  @Test
  public void shouldLetCallerInferTypeArgumentForGenericMockTargetOverloadedMethod() {
    JavaFileObject event = JavaFileObjects.forSourceString("test.StreamEvent",
        "package test;\n" +
            "public class StreamEvent {}\n");
    // emit is overloaded and one overload's argument is the type variable T: the shared builder must
    // still expose a generic wither so the caller's concrete type is inferred.
    JavaFileObject service = JavaFileObjects.forSourceString("test.IStreamerService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface IStreamerService<T> {\n" +
            "  void emit(T message);\n" +
            "  void emit(T message, String topic);\n" +
            "}\n");
    JavaFileObject caller = JavaFileObjects.forSourceString("test.Caller",
        "package test;\n" +
            "import org.mockito.ArgumentCaptor;\n" +
            "class Caller {\n" +
            "  void run(IStreamerService<StreamEvent> streamerService, StreamEvent event) {\n" +
            "    ArgumentCaptor<StreamEvent> captor = ArgumentCaptor.forClass(StreamEvent.class);\n" +
            "    IStreamerServiceMock.mock(streamerService).verify().emit()"
            + ".withMessage(event).withTopicAny().called();\n" +
            "    IStreamerServiceMock.mock(streamerService).verify().emit()"
            + ".capturingMessage(captor).withTopicAny().called();\n" +
            "  }\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(event, service, caller);

    assertThat(compilation).succeeded();
  }

  @Test
  public void shouldUnifyOverloadsWithConflictingParameterTypes() {
    // Two overloads name the parameter 'ids' but with different types (List vs Set). The shared
    // builder must widen that slot to Object rather than skip the whole method, and the generated
    // mock must still compile (each overload invocation casts the resolved slot to its own type).
    JavaFileObject service = JavaFileObjects.forSourceString("test.Svc",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "import java.util.List;\n" +
            "import java.util.Set;\n" +
            "@Mockable\n" +
            "public interface Svc {\n" +
            "  java.util.List<String> findAllByIdIn(List<String> ids);\n" +
            "  java.util.List<String> findAllByIdIn(Set<String> ids);\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation).generatedSourceFile("test.SvcMock").contentsAsUtf8String()
        .contains("public SvcMock.FindAllByIdInStub findAllByIdIn()");
    assertThat(compilation).generatedSourceFile("test.SvcMock").contentsAsUtf8String()
        .contains("withIds(Object value)");
    assertThat(compilation).generatedSourceFile("test.SvcMock").contentsAsUtf8String()
        .contains("findAllByIdIn((List<String>) this.ids.resolve())");
    assertThat(compilation).generatedSourceFile("test.SvcMock").contentsAsUtf8String()
        .contains("findAllByIdIn((Set<String>) this.ids.resolve())");
  }

  @Test
  public void shouldInheritMethodsFromNonGenericSuperinterface() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.Repo",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "interface BaseRepo { String describe(); }\n" +
            "@Mockable\n" +
            "public interface Repo extends BaseRepo { void ping(String s); }\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation).generatedSourceFile("test.RepoMock").contentsAsUtf8String()
        .contains("describe()");
    assertThat(compilation).generatedSourceFile("test.RepoMock").contentsAsUtf8String()
        .contains("ping()");
  }

  @Test
  public void shouldStubMethodDeclaringItsOwnTypeVariable() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.Adapter",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface Adapter {\n" +
            "  <T> T transmit();\n" +
            "  <R extends Number> R measure();\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.AdapterMock")
        .contentsAsUtf8String()
        .contains("public AdapterMock.TransmitStub transmit()");
    assertThat(compilation)
        .generatedSourceFile("test.AdapterMock")
        .contentsAsUtf8String()
        .contains("public AdapterMock.MeasureStub measure()");
    assertThat(compilation)
        .generatedSourceFile("test.AdapterMock")
        .contentsAsUtf8String()
        .doesNotContain("<T>");
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
        .contains("DoStubbing.ofAll(appliers)");
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

  @Test
  public void shouldLaunderCheckedExceptionsInFanOutStarters() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.ImageRepo",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface ImageRepo {\n" +
            "  String render(String id) throws java.io.IOException;\n" +
            "  String render(String id, int scale) throws java.io.IOException;\n" +
            "}\n");

    Compilation compilation = javac()
        .withProcessors(new MockableProcessor())
        .compile(service);

    assertThat(compilation).succeeded();
    assertThat(compilation)
        .generatedSourceFile("test.ImageRepoMock")
        .contentsAsUtf8String()
        .contains("private DoStubbing<String> stub() throws IOException {");
    assertThat(compilation)
        .generatedSourceFile("test.ImageRepoMock")
        .contentsAsUtf8String()
        .contains("throw new RuntimeException(t);");
  }
}
