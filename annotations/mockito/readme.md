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
stub(service).when().create().thenReturn("TEMPLATE_ID");
stub(service).when().create().withId("MY_ID").withOverwrite(true).thenReturn("SPECIAL");
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

- **Verification and capturing in the same shape.** `stub(service).verify().create().withId("MY_ID").called()`
  and `capturingSpec(captor)` mirror the stubbing DSL exactly.

- **One import to rule the suite.** `@Mockables` generates a `Stubs` aggregator so a single static
  import (`stub`) covers every mock in the test suite.

## Two ways to use it

| Annotation | Where | Effect |
|------------|-------|--------|
| `@Mockable` on a type you own | main sources | generates `XxxMock` alongside the type, usable from downstream modules' tests |
| `@Mockables({ ... })` on a marker class | test sources | generates a mock DSL per listed type without touching production code, plus a `Stubs` aggregator |

The **marker** flow is recommended: production code stays untouched and the generated classes land
in generated *test* sources.

```java
@Mockables({ OrchestratorTemplateService.class, PaymentGateway.class })
class MockTargets {
}
```

```java
import static com.acme.tests.Stubs.stub;

stub(templates).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
stub(templates).verify().create().withId("MY_ID").called();
```

> **Note:** in the marker flow the processor resolves production types from the *compiled* main
> output, so the module must compile with **`-parameters`** — otherwise parameter names are
> unavailable and the withers degrade to `withArg0`, `withArg1`, ... (the processor warns when this
> happens). Spring Boot's parent already sets `-parameters`.

Both annotations accept `prefix` / `suffix` (default: no prefix, `Mock` suffix) and
`includes` / `excludes` method-name patterns; `@Mockables` additionally accepts `aggregator`
(default `Stubs`, empty string disables it).

## Dependency

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
