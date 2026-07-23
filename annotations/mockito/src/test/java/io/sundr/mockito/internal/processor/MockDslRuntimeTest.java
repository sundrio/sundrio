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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.tools.JavaFileObject;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.google.testing.compile.Compilation;
import com.google.testing.compile.JavaFileObjects;

/**
 * Compiles a mockable fixture, loads the generated DSL and exercises it against real Mockito.
 */
public class MockDslRuntimeTest {

  private static ClassLoader classLoader;
  private static Class<?> serviceClass;
  private static Class<?> mockDslClass;

  @BeforeAll
  public static void compileFixture() {
    JavaFileObject service = JavaFileObjects.forSourceString("test.TemplateService",
        "package test;\n" +
            "import io.sundr.mockito.annotations.Mockable;\n" +
            "@Mockable\n" +
            "public interface TemplateService {\n" +
            "  String create(String id, String name, boolean overwrite);\n" +
            "  void delete(String id);\n" +
            "  String render(String id);\n" +
            "  String render(String id, String format);\n" +
            "  String label(String name);\n" +
            "  String label(int code);\n" +
            "}\n");

    Compilation compilation = javac().withProcessors(new MockableProcessor()).compile(service);
    assertThat(compilation).succeeded();

    classLoader = loaderOf(compilation);
    serviceClass = load("test.TemplateService");
    mockDslClass = load("test.TemplateServiceMock");
  }

  @Test
  public void shouldStubWithDefaultsAndPinnedArguments() {
    Object mock = mock();

    Object broad = createStub(mock);
    call(broad, "thenReturn", types(String.class), args("DEFAULT"));

    Object pinned = createStub(mock);
    call(pinned, "withId", types(String.class), args("SPECIAL"));
    call(pinned, "withOverwrite", types(boolean.class), args(true));
    call(pinned, "thenReturn", types(String.class), args("SPECIAL_RESULT"));

    assertEquals("DEFAULT", create(mock, "anything", "name", false));
    assertEquals("DEFAULT", create(mock, "SPECIAL", "name", false));
    assertEquals("SPECIAL_RESULT", create(mock, "SPECIAL", "name", true));
  }

  @Test
  public void shouldStubVoidMethodToThrow() {
    Object mock = mock();

    Object stub = deleteStub(mock);
    call(stub, "withId", types(String.class), args("BAD"));
    call(stub, "thenThrow", types(Throwable.class), args(new IllegalStateException("boom")));

    IllegalStateException failure = assertThrows(IllegalStateException.class, () -> delete(mock, "BAD"));
    assertEquals("boom", failure.getMessage());
    delete(mock, "GOOD");
  }

  @Test
  public void shouldVerifyWithPinsAndCaptors() {
    Object mock = mock();
    assertNull(create(mock, "MY_ID", "the-name", true));

    Object verify = createVerify(mock);
    call(verify, "withId", types(String.class), args("MY_ID"));
    call(verify, "called", types(), args());

    Object never = createVerify(mock);
    call(never, "withId", types(String.class), args("OTHER"));
    call(never, "never", types(), args());

    ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
    Object capturing = createVerify(mock);
    call(capturing, "capturingName", types(ArgumentCaptor.class), args(captor));
    call(capturing, "called", types(), args());
    assertEquals("the-name", captor.getValue());
  }

  @Test
  public void shouldStubAllMatchingOverloads() {
    Object mock = mock();

    Object shared = stubOf(mock, "render");
    call(shared, "withId", types(String.class), args("A"));
    call(shared, "thenReturn", types(String.class), args("SHARED"));

    assertEquals("SHARED", render(mock, "A"));
    assertEquals("SHARED", render(mock, "A", "html"));
    assertNull(render(mock, "B"));
  }

  @Test
  public void shouldDifferentiateOverloadsWithAnyPinsAndNoOtherArgs() {
    Object mock = mock();

    Object broad = stubOf(mock, "render");
    call(broad, "withId", types(String.class), args("A"));
    call(broad, "thenReturn", types(String.class), args("BOTH"));

    Object wide = stubOf(mock, "render");
    call(wide, "withId", types(String.class), args("A"));
    call(wide, "withFormatAny", types(), args());
    call(wide, "thenReturn", types(String.class), args("TWO_ARGS"));

    Object narrow = stubOf(mock, "render");
    call(narrow, "withId", types(String.class), args("B"));
    call(narrow, "andNoOtherArgs", types(), args());
    call(narrow, "thenReturn", types(String.class), args("ONE_ARG_ONLY"));

    assertEquals("BOTH", render(mock, "A"));
    assertEquals("TWO_ARGS", render(mock, "A", "html"));
    assertEquals("ONE_ARG_ONLY", render(mock, "B"));
    assertNull(render(mock, "B", "html"));
  }

  @Test
  public void shouldChainConsecutiveAnswersAcrossAllMatchedOverloads() {
    Object mock = mock();

    Object stub = stubOf(mock, "render");
    call(stub, "withId", types(String.class), args("A"));
    Object ongoing = call(stub, "thenReturn", types(String.class), args("FIRST"));
    call(ongoing, "thenReturn", types(Object.class), args("SECOND"));

    assertEquals("FIRST", render(mock, "A"));
    assertEquals("SECOND", render(mock, "A"));
    assertEquals("SECOND", render(mock, "A"));
    assertEquals("FIRST", render(mock, "A", "html"));
    assertEquals("SECOND", render(mock, "A", "html"));
  }

  @Test
  public void shouldRequireUnambiguousPinsForPositiveVerification() {
    Object mock = mock();
    render(mock, "A");

    Object ambiguous = verifyOf(mock, "render");
    call(ambiguous, "withId", types(String.class), args("A"));
    IllegalStateException ambiguity = assertThrows(IllegalStateException.class,
        () -> call(ambiguous, "called", types(), args()));
    assertEquals(true, ambiguity.getMessage().contains("andNoOtherArgs"));

    Object precise = verifyOf(mock, "render");
    call(precise, "withId", types(String.class), args("A"));
    call(precise, "andNoOtherArgs", types(), args());
    call(precise, "called", types(), args());

    Object never = verifyOf(mock, "render");
    call(never, "withId", types(String.class), args("Z"));
    call(never, "never", types(), args());
  }

  @Test
  public void shouldFailWhenPinsMatchNoOverload() {
    Object mock = mock();

    Object impossible = stubOf(mock, "label");
    call(impossible, "withName", types(String.class), args("x"));
    call(impossible, "withCode", types(int.class), args(42));

    IllegalStateException failure = assertThrows(IllegalStateException.class,
        () -> call(impossible, "thenReturn", types(String.class), args("NEVER")));
    assertEquals(true, failure.getMessage().contains("name"));
    assertEquals(true, failure.getMessage().contains("code"));
  }

  @Test
  public void shouldStubAnswerFirstWithDoReturn() {
    Object mock = mock();

    Object stub = doStubOf(mock, "doReturn", types(Object.class), args("MATCHED"), "create");
    call(stub, "withId", types(String.class), args("MY_ID"));
    call(stub, "done", types(), args());

    assertEquals("MATCHED", create(mock, "MY_ID", "name", true));
    assertNull(create(mock, "OTHER", "name", true));
  }

  @Test
  public void shouldStubVoidMethodAnswerFirstWithDoThrowAndDoNothing() {
    Object mock = mock();

    Object throwing = doStubOf(mock, "doThrow", types(Throwable[].class),
        args((Object) new Throwable[] { new IllegalStateException("boom") }), "delete");
    call(throwing, "withId", types(String.class), args("BAD"));
    call(throwing, "done", types(), args());

    Object nothing = doStubOf(mock, "doNothing", types(), args(), "delete");
    call(nothing, "withId", types(String.class), args("OK"));
    call(nothing, "done", types(), args());

    IllegalStateException failure = assertThrows(IllegalStateException.class, () -> delete(mock, "BAD"));
    assertEquals("boom", failure.getMessage());
    delete(mock, "OK");
  }

  @Test
  public void shouldFanOutAnswerFirstOverAllMatchingOverloads() {
    Object mock = mock();

    Object shared = doStubOf(mock, "doReturn", types(Object.class), args("SHARED"), "render");
    call(shared, "withId", types(String.class), args("A"));
    call(shared, "done", types(), args());

    assertEquals("SHARED", render(mock, "A"));
    assertEquals("SHARED", render(mock, "A", "html"));
    assertNull(render(mock, "B"));
  }

  private static Object doStubOf(Object mock, String doFactory, Class<?>[] doTypes, Object[] doArgs, String method) {
    Object stubber = call(mockDslClass, null, doFactory, doTypes, doArgs);
    Object router = call(stubber, "when", types(serviceClass), args(mock));
    return call(router, method, types(), args());
  }

  private static Object stubOf(Object mock, String method) {
    Object handle = call(mockDslClass, null, "mock", types(serviceClass), args(mock));
    Object router = call(handle, "when", types(), args());
    return call(router, method, types(), args());
  }

  private static Object verifyOf(Object mock, String method) {
    Object handle = call(mockDslClass, null, "mock", types(serviceClass), args(mock));
    Object router = call(handle, "verify", types(), args());
    return call(router, method, types(), args());
  }

  private static Object render(Object mock, String id) {
    return call(serviceClass, mock, "render", types(String.class), args(id));
  }

  private static Object render(Object mock, String id, String format) {
    return call(serviceClass, mock, "render", types(String.class, String.class), args(id, format));
  }

  private static Object mock() {
    return call(mockDslClass, null, "mock", types(), args());
  }

  private static Object createStub(Object mock) {
    Object handle = call(mockDslClass, null, "mock", types(serviceClass), args(mock));
    Object router = call(handle, "when", types(), args());
    return call(router, "create", types(), args());
  }

  private static Object deleteStub(Object mock) {
    Object handle = call(mockDslClass, null, "mock", types(serviceClass), args(mock));
    Object router = call(handle, "when", types(), args());
    return call(router, "delete", types(), args());
  }

  private static Object createVerify(Object mock) {
    Object handle = call(mockDslClass, null, "mock", types(serviceClass), args(mock));
    Object router = call(handle, "verify", types(), args());
    return call(router, "create", types(), args());
  }

  private static Object create(Object mock, String id, String name, boolean overwrite) {
    return call(serviceClass, mock, "create", types(String.class, String.class, boolean.class),
        args(id, name, overwrite));
  }

  private static void delete(Object mock, String id) {
    call(serviceClass, mock, "delete", types(String.class), args(id));
  }

  private static Object call(Object target, String name, Class<?>[] parameterTypes, Object[] arguments) {
    return call(target.getClass(), target, name, parameterTypes, arguments);
  }

  private static Object call(Class<?> type, Object target, String name, Class<?>[] parameterTypes, Object[] arguments) {
    try {
      Method method = type.getMethod(name, parameterTypes);
      return method.invoke(target, arguments);
    } catch (InvocationTargetException e) {
      if (e.getCause() instanceof RuntimeException) {
        throw (RuntimeException) e.getCause();
      }
      if (e.getCause() instanceof Error) {
        throw (Error) e.getCause();
      }
      throw new RuntimeException(e.getCause());
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  private static Class<?>[] types(Class<?>... types) {
    return types;
  }

  private static Object[] args(Object... arguments) {
    return arguments;
  }

  private static Class<?> load(String name) {
    try {
      return classLoader.loadClass(name);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  private static ClassLoader loaderOf(Compilation compilation) {
    Map<String, byte[]> classes = new HashMap<>();
    for (JavaFileObject file : compilation.generatedFiles()) {
      String name = file.getName();
      if (!name.endsWith(".class")) {
        continue;
      }
      String binaryName = name
          .replaceFirst("^/CLASS_OUTPUT/", "")
          .replaceAll("\\.class$", "")
          .replace('/', '.');
      classes.put(binaryName, bytesOf(file));
    }
    return new ClassLoader(MockDslRuntimeTest.class.getClassLoader()) {
      @Override
      protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = classes.get(name);
        if (bytes == null) {
          throw new ClassNotFoundException(name);
        }
        return defineClass(name, bytes, 0, bytes.length);
      }
    };
  }

  private static byte[] bytesOf(JavaFileObject file) {
    try (InputStream in = file.openInputStream()) {
      java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
      byte[] buffer = new byte[4096];
      int read;
      while ((read = in.read(buffer)) != -1) {
        out.write(buffer, 0, read);
      }
      return out.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
