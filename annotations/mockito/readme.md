# Mockito Annotations

A typed, fluent stubbing and verification DSL generated on top of [Mockito](https://site.mockito.org/).
Annotate a type, and a companion `XxxMock` class gives you `stub`/`verify` builders with one
`withXxx` method per parameter — no positional matchers, no `import static` soup, and full
compile-time safety.

## The pain points

Plain Mockito is powerful, but writing stubs by hand accumulates friction that this module removes:

- Mockito forces you to specify *every* argument, even when you only care about one. For example:

  ```java
  Mockito.when(service.create(Mockito.any(), Mockito.any(), Mockito.anyBoolean(),
      Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
      .thenReturn("TEMPLATE_ID");
  ```

  A seven-argument method means seven `any()` calls just to constrain one of them, and the reader
  has to count commas to know *which* argument was pinned.

- **No names, only positions.** `eq(x)` at position 3 says nothing about what argument 3 *is*.
  Reorder the method's parameters and the stub still compiles but now lies.

- **Primitive matcher zoo.** `any()`, `anyInt()`, `anyBoolean()`, `anyLong()`, `intThat(...)`,
  `booleanThat(...)` — you have to remember which flavor each parameter needs, and getting it wrong
  is a runtime failure, not a compile error.

- **Static-import soup.** Idiomatic Mockito tests pull in a wall of static imports
  (`when`, `verify`, `any`, `eq`, `argThat`, `times`, `never`, ...) with nothing tying them to the
  type under test.

- **Overloads are a minefield.** Stubbing one overload of `render(...)` while leaving the others
  alone means carefully shaping matcher lists per signature, and strict stubbing flags the variants
  a test never exercises.

- **Refactors break silently.** Because everything is positional and string-free, renaming a
  parameter or changing an argument type leaves stale stubs that compile but no longer match.

## What this module brings

For every mockable type it generates a companion class exposing a fluent DSL, so the seven-argument
stub above becomes:

```java
mock(service).when().create().thenReturn("TEMPLATE_ID");
mock(service).when().create().withId("MY_ID").withOverwrite(true).thenReturn("SPECIAL");
```

Concretely:

- **One `withXxx` per parameter, by name.** Pin only what matters; every unpinned argument defaults
  to the correct `any()` / `anyBoolean()` / ... automatically. The parameter name is in the method
  name, so stubs read like sentences and survive parameter reordering.

- **The right matcher, picked for you.** `withId("x")` emits `eq("x")`, `withIdMatching(p)` emits
  `argThat(p)`, primitives get their primitive matchers — all resolved left-to-right so Mockito's
  matcher rules hold by construction.

- **Compile-time safety.** Wrong argument type, wrong name, or a pin that fits no overload is a
  compile error, not a surprise at test time.

- **Overloads unified.** Overloaded methods collapse into a single DSL method carrying the union of
  all `withers`; a stubbing fans out to every overload matching the pinned arguments, with
  `withXxxAny()` and `andNoOtherArgs()` to narrow when you need to.

- **Verification and capturing in the same shape.** `mock(service).verify().create().withId("MY_ID").called()`
  and `capturingSpec(captor)` mirror the stubbing DSL exactly.

- **Answer-first do-family, spy- and void-safe.** For spies and void methods, the answer is given
  up front and the chain compiles to Mockito's `doX(...).when(mock).method(matchers)`, which never
  invokes the real method during stubbing:

  ```java
  doReturn("TEMPLATE_ID").when(service).create().withId("MY_ID").done();
  doThrow(ex).when(service).delete().withId("LOCKED").done();
  doNothing().when(service).delete().withId("X").done();
  ```

  The value/answer is supplied to `doReturn`/`doThrow`/`doAnswer`/`doCallRealMethod`/`doNothing`, so
  `.done()` (no argument) closes the chain. It reuses the same withers and overload fan-out as the
  `when()` form.

- **Checked exceptions propagated.** If a mocked method declares checked exceptions, the generated
  terminals declare them too, so the test method handles them exactly as it would a direct call.

- **One import to rule the suite.** `@Mockables` generates a `Mocks` aggregator so a single static
  import covers every mock in the test suite — both the fluent DSL (`mock(x).when()/.verify()`) and
  the common Mockito statics delegated straight through: `when`, `verify`, `verifyNoInteractions`,
  `verifyNoMoreInteractions`, `inOrder`, `reset`, `clearInvocations`, `timeout`, `after`.

## Two ways to use it

| Annotation | Where | Effect |
|------------|-------|--------|
| `@Mockable` on a type you own | main sources | generates `XxxMock` alongside the type, usable from downstream modules' tests |
| `@Mockables({ ... })` on a marker class | test sources | generates a mock DSL per listed type without touching production code, plus a `Mocks` aggregator |

The **marker** flow is recommended: production code stays untouched and the generated classes land
in generated *test* sources.

```java
@Mockables({ OrchestratorTemplateService.class, PaymentGateway.class })
class MockTargets {
}
```

```java
import static com.acme.tests.Mocks.*;

mock(templates).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
mock(templates).verify().create().withId("MY_ID").called();

when(templates.other(any())).thenReturn("X"); // plain Mockito, same import
```

> **Note:** in the marker flow the processor resolves production types from the *compiled* main
> output, so the module must compile with **`-parameters`** — otherwise parameter names are
> unavailable and the withers degrade to `withArg0`, `withArg1`, ... (the processor warns when this
> happens). Spring Boot's parent already sets `-parameters`.

Both annotations accept `prefix` / `suffix` (default: no prefix, `Mock` suffix) and
`includes` / `excludes` method-name patterns; `@Mockables` additionally accepts `aggregator`
(default `Mocks`, empty string disables it).

If the generated name (e.g. `FooMock`) is already taken by an existing type — a hand-written
class, a dependency on the classpath, or a class generated in a prior round — the
`onNameCollision` policy decides what happens:

- `RENAME` (default) — generate under a fallback name with a `Generated` marker
  (`FooMock` → `FooGeneratedMock`), emitting a warning. If the fallback is also taken, generation
  fails with an error.
- `FAIL` — fail the build immediately, leaving it to you to rename the existing type or set a
  custom `prefix` / `suffix`.

```java
@Mockable(onNameCollision = OnNameCollision.FAIL)
public interface FooService { ... }
```

## Lombok targets

Lombok-generated members are treated like any other: a `@Getter` / `@Data` getter is an ordinary
no-argument method, so it becomes a stubbable target (`mock(config).when().getZone()...`), while the
generated `equals` / `hashCode` / `toString` are skipped like all `Object` methods. The only
requirement is that those members are *visible* to this processor when it runs:

- **`@Mockables` marker flow (recommended)** — the target is resolved from the already-compiled
  output, so Lombok's generated methods are always present. Always safe.
- **`@Mockable` directly on a Lombok class in the same module** — this depends on Lombok's
  annotation processor running before this one in the same compilation round (usually the case, but
  processor ordering is not contractual). If a Lombok target's getters are ever missing from the
  generated DSL, prefer the marker flow, which sidesteps the ordering entirely.

```xml
<dependency>
    <groupId>io.sundr</groupId>
    <artifactId>mockito-annotations</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <scope>test</scope>
</dependency>
```

With `@Mockable` on a main-source type the generated class references Mockito at compile time, so
`mockito-core` must be visible to the main compilation (`provided` keeps it out of the runtime
tree).

## Learn more

- [Worked examples](../../examples/mockito/readme.md) — the marker and annotated flows end to end,
  including overloads, layering, consecutive answers, void methods, verification and capturing.
- Matcher semantics, the full list of generated classes, and every terminal are documented there.
- [Migration recipe](../../rewrite/mockito/readme.md) — an OpenRewrite recipe that migrates an
  existing Mockito suite to this DSL (adds the dependency, generates the marker, rewrites call sites).
