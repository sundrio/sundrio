# Mockito Examples

Two examples demonstrating `mockito-annotations`: a generated, typed, fluent stubbing and
verification DSL on top of Mockito. Instead of

```java
Mockito.when(service.create(Mockito.any(), Mockito.any(), Mockito.anyBoolean(),
    Mockito.any(), Mockito.any(), Mockito.any(), Mockito.any()))
    .thenReturn("TEMPLATE_ID");
```

you write

```java
stub(service).when().create().thenReturn("TEMPLATE_ID");
stub(service).when().create().withId("MY_ID").withOverwrite(true).thenReturn("SPECIAL");
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
stub(service).when().create()
    .thenReturn("TEMPLATE_ID");

// (ii) one argument pinned to an exact value, the rest stay any()
stub(service).when().create().withId("MY_ID")
    .thenReturn("MATCHED");

// (iii) one argument matched with a custom matcher
stub(service).when().create().withIdMatching(id -> id.startsWith("tpl-"))
    .thenReturn("MATCHED");

// (iv) two arguments matched together
stub(service).when().create()
    .withIdMatching(id -> id.startsWith("tpl-"))
    .withOwnerMatching(owner -> owner.endsWith("@acme.com"))
    .thenReturn("MATCHED");
```

Beyond the basics — layering, consecutive answers and void methods:

```java
// later stubbings win for overlapping matches: broad default first, special case after
stub(service).when().create().thenReturn("DEFAULT");
stub(service).when().create().withId("MY_ID").withOverwrite(true).thenReturn("SPECIAL");

// terminals return Mockito's OngoingStubbing, so consecutive answers chain
stub(service).when().find().withId("MY_ID")
    .thenReturn(Optional.of(spec))
    .thenReturn(Optional.empty());

// void methods use the do-family under the hood
stub(service).when().delete().withId("LOCKED")
    .thenThrow(new IllegalStateException("template is locked"));
```

Overloaded methods share a single DSL method carrying the union of all withers, and a
stubbing applies to **every overload matching the pinned arguments**: pins select the
overloads, values constrain the call, `withXxxAny()` pins a parameter without constraining
it, and `andNoOtherArgs()` restricts to the overload with exactly the pinned parameters.
Pins that fit no overload fail fast. For `render(id)` / `render(spec)` /
`render(id, parameters)`:

```java
// one line stubs render(id) AND render(id, parameters)
stub(service).when().render().withId("tpl-1").thenReturn("SHARED");

// per-overload behavior when it matters
stub(service).when().render().withId("tpl-1").thenReturn("A");                     // both id-overloads
stub(service).when().render().withId("tpl-1").withParametersAny().thenReturn("B"); // only render(id, parameters)
stub(service).when().render().withId("tpl-2").andNoOtherArgs().thenReturn("C");    // only render(id)
```

When several overloads are stubbed at once the stubbings are lenient, so strict stubbing
does not flag the variants a test never exercises, and chained consecutive answers span
all of them. Positive verifications (`called()`, `times(n)`) must name one concrete
signature — disambiguate with more pins, `withXxxAny()` or `andNoOtherArgs()` — while
`never()` fans out to every matching overload. Overload groups must share one return type
and one type per parameter name; otherwise the processor warns and skips that method name.

Verification and capturing:

```java
stub(service).verify().create().withId("MY_ID").called();
stub(service).verify().delete().never();

ArgumentCaptor<TemplateSpec> captured = ArgumentCaptor.forClass(TemplateSpec.class);
stub(service).verify().create().capturingSpec(captured).called();
```

The marker also generates a `Stubs` aggregator in its package, so one static import covers
every mock of the suite:

```java
import static io.sundr.examples.mockito.Stubs.stub;

stub(templates).when().create().withId("MY_ID").thenReturn("TEMPLATE_ID");
stub(payments).when().charge().withAmountCents(2500L).thenReturn(42L);

stub(templates).verify().create().withId("MY_ID").called();
stub(payments).verify().refund().never();
```

### What gets generated

| Class | Purpose |
|-------|---------|
| `OrchestratorTemplateServiceMock` | Entry points `mock()` and `stub(mock)`; the handle exposes `when()` and `verify()` |
| `...Mock.Stub` / `...Mock.Verify` | Routers with one method per stubbable target method |
| `...Mock.CreateStub` | Per-method builder: `withXxx(value)`, `withXxxMatching(matcher)`, `thenReturn/thenThrow/thenAnswer/thenCallRealMethod` |
| `...Mock.CreateVerify` | Per-method builder: withers plus `capturingXxx(captor)`, `called()/times(n)/never()/atLeastOnce()/atLeast(n)/atMost(n)/only()/verified(mode)` |
| `Stubs` | Aggregator with one `stub` overload per mockable type |

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
GreetingServiceMock.stub(service).when().greet().withName("Ada").thenReturn("Hello Ada!");
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
`aggregator` (default `Stubs`, empty string disables it).
