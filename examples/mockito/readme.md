# Mockito Examples

Examples demonstrating `mockito-annotations`: a generated, typed, fluent stubbing and
verification DSL on top of Mockito. Instead of

```java
Mockito.when(service.create(Mockito.any(), Mockito.any(), Mockito.anyBoolean(),
    Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
    .thenReturn("TEMPLATE_ID");
```

you write

```java
mock(service).when().create().thenReturn("TEMPLATE_ID");
mock(service).when().create().withId("MY_ID").withOverwrite(true).thenReturn("SPECIAL");
```

## Marker (recommended)

**Location:** [marker/](marker/)

Production code stays untouched. A small marker class in `src/test/java` lists the types to
mock; the processor runs at test-compile and the generated classes land in generated test
sources:

```java
@Mockables({ OrchestratorTemplateService.class, PaymentGateway.class })
class MockTargets {
}
```

> **Important:** in this flow the processor resolves the production types from the *main
> compilation output* (class files), so the module must compile with **`-parameters`** —
> otherwise parameter names are unavailable and the generated withers degrade to
> `withArg0`, `withArg1`, ... (the processor emits a warning when this happens). See this
> example's `pom.xml`. Spring Boot's parent already sets `-parameters` by default.

The four essential stubbing shapes, shown on the seven-argument
`create(id, name, overwrite, spec, owner, labels, tags)`:

```java
// (i) no argument specified: every argument defaults to any() / anyBoolean() / ...
mock(service).when().create()
    .thenReturn("TEMPLATE_ID");

// (ii) one argument pinned to an exact value, the rest stay any()
mock(service).when().create().withId("MY_ID")
    .thenReturn("MATCHED");

// (iii) one argument matched with a custom matcher
mock(service).when().create().withIdMatching(id -> id.startsWith("tpl-"))
    .thenReturn("MATCHED");

// (iv) two arguments matched together
mock(service).when().create()
    .withIdMatching(id -> id.startsWith("tpl-"))
    .withOwnerMatching(owner -> owner.endsWith("@acme.com"))
    .thenReturn("MATCHED");
```

Beyond the basics — layering, consecutive answers and void methods:

```java
// later stubbings win for overlapping matches: broad default first, special case after
mock(service).when().create().thenReturn("DEFAULT");
mock(service).when().create().withId("MY_ID").withOverwrite(true).thenReturn("SPECIAL");

// terminals return Mockito's OngoingStubbing, so consecutive answers chain
mock(service).when().find().withId("MY_ID")
    .thenReturn(Optional.of(spec))
    .thenReturn(Optional.empty());

// void methods use the do-family under the hood
mock(service).when().delete().withId("LOCKED")
    .thenThrow(new IllegalStateException("template is locked"));
```

The **answer-first do-family** gives the answer up front and closes with `done()`. It compiles to
Mockito's `doX(...).when(mock).method(matchers)`, which never invokes the real method during
stubbing — the spy- and void-safe way to stub. It reuses the same withers and overload fan-out as
the `when()` form:

```java
doReturn("TEMPLATE_ID").when(service).create().withId("MY_ID").done();
doThrow(new IllegalStateException("locked")).when(service).delete().withId("LOCKED").done();
doNothing().when(service).delete().withId("OK").done();
doReturn("SHARED").when(service).render().withId("tpl-1").done();  // fans out over overloads
```

Overloaded methods share a single DSL method carrying the union of all withers, and a
stubbing applies to **every overload matching the pinned arguments**: pins select the
overloads, values constrain the call, `withXxxAny()` pins a parameter without constraining
it, and `andNoOtherArgs()` restricts to the overload with exactly the pinned parameters.
Pins that fit no overload fail fast. For `render(id)` / `render(spec)` /
`render(id, parameters)`:

```java
// one line stubs render(id) AND render(id, parameters)
mock(service).when().render().withId("tpl-1").thenReturn("SHARED");

// per-overload behavior when it matters
mock(service).when().render().withId("tpl-1").thenReturn("A");                     // both id-overloads
mock(service).when().render().withId("tpl-1").withParametersAny().thenReturn("B"); // only render(id, parameters)
mock(service).when().render().withId("tpl-2").andNoOtherArgs().thenReturn("C");    // only render(id)
```

When several overloads are stubbed at once the stubbings are lenient, so strict stubbing
does not flag the variants a test never exercises, and chained consecutive answers span
all of them. Positive verifications (`called()`, `times(n)`) must name one concrete
signature — disambiguate with more pins, `withXxxAny()` or `andNoOtherArgs()` — while
`never()` fans out to every matching overload. Overload groups must share one return type
and one type per parameter name; otherwise the processor warns and skips that method name.

### Checked exceptions

When a mocked method declares checked exceptions, the generated terminals propagate them, so
the test method simply declares (or handles) them as it would for a direct call:

```java
// OrchestratorTemplateService.export(String id) throws IOException

@Test
void exportStub() throws IOException {
    mock(service).when().export().withId("tpl-1").thenReturn("EXPORTED");
    mock(service).verify().export().withId("tpl-1").called();
}
```

Verification and capturing:

```java
mock(service).verify().create().withId("MY_ID").called();
mock(service).verify().delete().never();

ArgumentCaptor<TemplateSpec> captured = ArgumentCaptor.forClass(TemplateSpec.class);
mock(service).verify().create().capturingSpec(captured).called();
```

The marker also generates a `Mocks` aggregator in its package, so one static import covers
every mock of the suite — for both the fluent DSL and plain Mockito:

```java
import static io.sundr.examples.mockito.Mocks.*;

mock(templates).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
mock(payments).when().charge().withAmountCents(2500L).thenReturn(42L);

mock(templates).verify().create().withId("MY_ID").called();
mock(payments).verify().refund().never();
```

The aggregator also exposes the answer-first do-family — `doReturn`, `doThrow` (throwable or
`Class<? extends Throwable>`), `doNothing`, `doAnswer`, `doCallRealMethod` — each opening a
`when(mock).method()<withers>.done()` chain over any mockable type:

```java
doReturn("TEMPLATE_ID").when(templates).create().withId("MY_ID").done();
doNothing().when(templates).delete().withId("OK").done();
```

The same import also exposes the common Mockito statics, delegated straight through, so a test
can drop to raw Mockito without a second import: `when`, `verify` (both arities),
`verifyNoInteractions`, `verifyNoMoreInteractions`, `inOrder`, `reset`, `clearInvocations`,
`timeout` and `after`.

```java
when(payments.charge("ACC-9", 100L)).thenReturn(7L);
verify(payments).charge("ACC-9", 100L);

verifyNoInteractions(templates);

InOrder order = inOrder(payments);
order.verify(payments).charge("ACC-1", 10L);
order.verify(payments).refund("ACC-1", 10L);
```

Argument matchers (`anyXxx`, `eq`, `argThat`, ...) are intentionally *not* delegated: the
fluent path expresses matching through `withXxxMatching` / `withXxxAny`, and on the raw path
they belong to their own home, `org.mockito.ArgumentMatchers.*` — keeping them there preserves
the fluent-vs-raw boundary and avoids the stateful-matcher misuse a facade would invite.

### What gets generated

| Class | Purpose |
|-------|---------|
| `OrchestratorTemplateServiceMock` | Entry points `mock()` (fresh mock) and `mock(existing)` (wrap a mock); the handle exposes `when()` and `verify()` |
| `...Mock.Stub` / `...Mock.Verify` | Routers with one method per stubbable target method |
| `...Mock.CreateStub` | Per-method builder: `withXxx(value)`, `withXxxMatching(matcher)`, `thenReturn/thenThrow/thenAnswer/thenCallRealMethod` |
| `...Mock.CreateVerify` | Per-method builder: withers plus `capturingXxx(captor)`, `called()/times(n)/never()/atLeastOnce()/atLeast(n)/atMost(n)/only()/verified(mode)` |
| `...Mock` do-family | Static `doReturn/doThrow/doNothing/doAnswer/doCallRealMethod` entry points returning a `DoStubber`; `when(mock)` opens the answer-first router whose `...DoStub` per-method builders reuse the withers and close with `done()` |
| `Mocks` | Aggregator with one `mock` overload per mockable type, the answer-first do-family (`doReturn/doThrow/doNothing/doAnswer/doCallRealMethod`), plus passthroughs to the common Mockito statics (`when`, `verify`, `verifyNoInteractions`, `verifyNoMoreInteractions`, `inOrder`, `reset`, `clearInvocations`, `timeout`, `after`) |

### Matcher semantics

| Builder call | Emitted matcher |
|--------------|-----------------|
| *(nothing)* | `any()` / `anyInt()` / `anyBoolean()` / ... |
| `withXxx(value)` | `eq(value)` |
| `withXxxMatching(matcher)` | `argThat(matcher)` / `intThat(matcher)` / ... |
| `capturingXxx(captor)` | `captor.capture()` (verification only) |

Slots resolve left-to-right inside the mocked invocation, so Mockito's matcher rules
(all-or-nothing matchers, registration order, primitive matchers) hold by construction.

### Maven dependencies

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

## Annotated

**Location:** [annotated/](annotated/)

`@Mockable` directly on a type you own generates the mock DSL into main sources, so
downstream modules can use it from their own tests:

```java
@Mockable
public interface GreetingService {
  String greet(String name);
}
```

```java
GreetingService service = GreetingServiceMock.mock();
GreetingServiceMock.mock(service).when().greet().withName("Ada").thenReturn("Hello Ada!");
```

Because the generated class references Mockito, `mockito-core` must be visible to the main
compilation (`provided` scope keeps it out of the runtime dependency tree):

```xml
<dependency>
    <groupId>io.sundr</groupId>
    <artifactId>mockito-annotations</artifactId>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <scope>provided</scope>
</dependency>
```

Both annotations accept `prefix` / `suffix` to control the generated class name
(defaults: no prefix, `Mock` suffix), and `@Mockables` additionally accepts
`aggregator` (default `Mocks`, empty string disables it).

## Lombok

**Location:** [lombok/](lombok/)

Integration test proving the DSL is generated against Lombok-generated members: getters and
setters become stubbable/verifiable, while Lombok's `equals`/`hashCode`/`toString` are excluded.
Uses the marker flow with real Lombok on the annotation-processor path, and requires
`-parameters` so the setter withers keep their field-derived names.
