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

package io.sundr.examples.mockito;

import static io.sundr.examples.mockito.Mocks.doNothing;
import static io.sundr.examples.mockito.Mocks.doReturn;
import static io.sundr.examples.mockito.Mocks.doThrow;
import static io.sundr.examples.mockito.Mocks.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

/**
 * The four essential stubbing shapes on a seven-argument method. The plain Mockito
 * equivalent of the first one is:
 *
 * <pre>
 * Mockito.when(service.create(Mockito.any(), Mockito.any(), Mockito.anyBoolean(),
 *     Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
 *     .thenReturn("TEMPLATE_ID");
 * </pre>
 */
public class OrchestratorTemplateServiceMockTest {

  private final OrchestratorTemplateService service = OrchestratorTemplateServiceMock.mock();
  private final TemplateSpec spec = new TemplateSpec("v1");

  @Test
  public void mocksBehaviorWithoutSpecifyingAnyArgument() {
    mock(service).when().create()
        .thenReturn("TEMPLATE_ID");

    assertEquals("TEMPLATE_ID", create("any-id", "any-owner"));
    assertEquals("TEMPLATE_ID", create("other-id", "other-owner"));
  }

  @Test
  public void pinsOneArgumentWithAnExactValue() {
    mock(service).when().create().withId("MY_ID")
        .thenReturn("MATCHED");

    assertEquals("MATCHED", create("MY_ID", "any-owner"));
    assertNull(create("OTHER_ID", "any-owner"));
  }

  @Test
  public void matchesOneArgumentWithACustomMatcher() {
    mock(service).when().create().withIdMatching(id -> id.startsWith("tpl-"))
        .thenReturn("MATCHED");

    assertEquals("MATCHED", create("tpl-42", "any-owner"));
    assertNull(create("raw-42", "any-owner"));
  }

  @Test
  public void matchesTwoArguments() {
    mock(service).when().create()
        .withIdMatching(id -> id.startsWith("tpl-"))
        .withOwnerMatching(owner -> owner.endsWith("@acme.com"))
        .thenReturn("MATCHED");

    assertEquals("MATCHED", create("tpl-42", "kate@acme.com"));
    assertNull(create("tpl-42", "root"));
    assertNull(create("raw-42", "kate@acme.com"));
  }

  /**
   * Overloads share one DSL method and a stubbing applies to every overload matching the
   * pinned arguments. {@code render} is overloaded as {@code render(id)},
   * {@code render(spec)} and {@code render(id, parameters)}: pinning {@code id} stubs the
   * two overloads that take an id, in one line.
   */
  @Test
  public void stubsAllOverloadsMatchingThePins() {
    mock(service).when().render().withId("tpl-1")
        .thenReturn("SHARED");

    assertEquals("SHARED", service.render("tpl-1"));
    assertEquals("SHARED", service.render("tpl-1", Map.of("env", "prod")));
    assertNull(service.render("other"));
    assertNull(service.render(spec));
  }

  /**
   * Per-overload behavior: {@code withXxxAny()} pins a parameter for overload selection
   * without constraining its value, and {@code andNoOtherArgs()} restricts to the overload
   * with exactly the pinned parameters.
   */
  @Test
  public void differentiatesOverloadsWithAnyPinsAndNoOtherArgs() {
    mock(service).when().render().withId("tpl-1")
        .thenReturn("A");
    mock(service).when().render().withId("tpl-1").withParametersAny()
        .thenReturn("B");
    mock(service).when().render().withSpec(spec)
        .thenReturn("C");
    mock(service).when().render().withId("tpl-2").andNoOtherArgs()
        .thenReturn("ONLY_ONE_ARG");

    assertEquals("A", service.render("tpl-1"));
    assertEquals("B", service.render("tpl-1", Map.of("env", "prod")));
    assertEquals("C", service.render(spec));
    assertEquals("ONLY_ONE_ARG", service.render("tpl-2"));
    assertNull(service.render("tpl-2", Map.of()));
  }

  @Test
  public void laterStubbingsWinForOverlappingMatches() {
    mock(service).when().create()
        .thenReturn("DEFAULT");
    mock(service).when().create().withId("MY_ID").withOverwrite(true)
        .thenReturn("SPECIAL");

    assertEquals("DEFAULT", service.create("other", "n", true, spec, "o", Map.of(), List.of()));
    assertEquals("DEFAULT", service.create("MY_ID", "n", false, spec, "o", Map.of(), List.of()));
    assertEquals("SPECIAL", service.create("MY_ID", "n", true, spec, "o", Map.of(), List.of()));
  }

  @Test
  public void chainsConsecutiveAnswersLikePlainMockito() {
    mock(service).when().find().withId("MY_ID")
        .thenReturn(Optional.of(spec))
        .thenReturn(Optional.empty());

    assertTrue(service.find("MY_ID").isPresent());
    assertFalse(service.find("MY_ID").isPresent());
  }

  @Test
  public void stubsVoidMethodsWithTheDoFamily() {
    mock(service).when().delete().withId("LOCKED")
        .thenThrow(new IllegalStateException("template is locked"));

    IllegalStateException failure = assertThrows(IllegalStateException.class, () -> service.delete("LOCKED"));
    assertEquals("template is locked", failure.getMessage());
    service.delete("UNLOCKED");
  }

  /**
   * The answer-first do-family: the value/throwable is given up front, so the chain closes
   * with {@code done()}. Because it compiles to {@code stubber.when(mock).method(matchers)} it
   * is void- and spy-safe, and it fans out over the overloads matching the pins just like the
   * {@code when()} form.
   */
  @Test
  public void stubsAnswerFirstWithTheDoFamily() {
    doReturn("MATCHED").when(service).create().withId("MY_ID").done();

    assertEquals("MATCHED", create("MY_ID", "any-owner"));
    assertNull(create("OTHER_ID", "any-owner"));
  }

  @Test
  public void stubsVoidMethodsAnswerFirst() {
    doThrow(new IllegalStateException("template is locked")).when(service).delete().withId("LOCKED").done();
    doNothing().when(service).delete().withId("OK").done();

    IllegalStateException failure = assertThrows(IllegalStateException.class, () -> service.delete("LOCKED"));
    assertEquals("template is locked", failure.getMessage());
    service.delete("OK");
  }

  @Test
  public void answerFirstFansOutOverMatchingOverloads() {
    doReturn("SHARED").when(service).render().withId("tpl-1").done();

    assertEquals("SHARED", service.render("tpl-1"));
    assertEquals("SHARED", service.render("tpl-1", Map.of("env", "prod")));
    assertNull(service.render("other"));
  }

  @Test
  public void verifiesWithPinsAndCaptors() {
    create("MY_ID", "owner");

    mock(service).verify().create().withId("MY_ID").called();
    mock(service).verify().delete().never();

    ArgumentCaptor<TemplateSpec> captured = ArgumentCaptor.forClass(TemplateSpec.class);
    mock(service).verify().create().capturingSpec(captured).called();
    assertEquals("v1", captured.getValue().getVersion());
  }

  /**
   * The mocked method declares a checked exception; the generated DSL terminals propagate
   * it, so stubbing and verifying it is possible only when the test itself declares throws.
   */
  @Test
  public void stubsAndVerifiesMethodsThatDeclareCheckedExceptions() throws IOException {
    mock(service).when().export().withId("a").thenReturn("EXPORTED");

    assertEquals("EXPORTED", service.export("a"));

    mock(service).verify().export().withId("a").called();
  }

  private String create(String id, String owner) {
    return service.create(id, "the-name", false, spec, owner, Map.of(), List.of("tag"));
  }
}
