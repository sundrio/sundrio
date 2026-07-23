# Mockito Annotations — Migration Recipe

An [OpenRewrite](https://docs.openrewrite.org/) recipe that migrates an existing Mockito test
suite to the [`mockito-annotations`](../../annotations/mockito/readme.md) fluent stubbing and
verification DSL. It adds the dependency, generates the `@Mockables` marker, and rewrites
`when(...)` / `verify(...)` call sites into `Mocks.mock(...).when()/.verify()`, leaving anything
it cannot rewrite confidently untouched with a `// TODO` marker.

This is a **build-time only** artifact: it is run once via the `rewrite-maven-plugin` and is not a
dependency of the code it migrates. It lives in its own module (Java 17, pulling the OpenRewrite
dependency tree) precisely so that consumers of the DSL never inherit either.

## Running it

```bash
mvn org.openrewrite.maven:rewrite-maven-plugin:run \
    -Drewrite.activeRecipes=io.sundr.mockito.rewrite.MigrateToMockitoAnnotations \
    -Drewrite.recipeArtifactCoordinates=io.sundr:mockito-annotations-rewrite:LATEST
```

Or wire the plugin into the module being migrated:

```xml
<plugin>
    <groupId>org.openrewrite.maven</groupId>
    <artifactId>rewrite-maven-plugin</artifactId>
    <configuration>
        <activeRecipes>
            <recipe>io.sundr.mockito.rewrite.MigrateToMockitoAnnotations</recipe>
        </activeRecipes>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>io.sundr</groupId>
            <artifactId>mockito-annotations-rewrite</artifactId>
        </dependency>
    </dependencies>
</plugin>
```

Then `mvn rewrite:run` and review the diff.

## What the composite does

`io.sundr.mockito.rewrite.MigrateToMockitoAnnotations` chains four steps:

1. **Add dependencies** — `io.sundr:mockito-annotations` and `org.mockito:mockito-core`, both at
   `test` scope.

2. **Generate the marker** (`GenerateMarker`) — scans the test sources for every type used as a
   mock (the class literal in `mock(X.class)` and the field type of `@Mock` fields), computes the
   least-common-denominator package across them, and writes `<lcd-package>/MocksConfig.java`:

   ```java
   @Mockables({ TemplateService.class, PaymentGateway.class })
   public class MocksConfig { }
   ```

   `@Mockables` in turn generates the `Mocks` aggregator into that package, so the rewritten call
   sites resolve `Mocks.mock(...)`. An existing `MocksConfig` is merged, not overwritten. When the
   mocked types share no package prefix, the marker falls back to the test root package.

3. **Rewrite stubbing / verification** (`RewriteStubbingAndVerify`) — semantics-preserving:

   ```java
   when(service.create(eq("ID"), any(), true)).thenReturn("X");
   // becomes
   Mocks.mock(service).when().create().withId("ID").withOverwrite(true).thenReturn("X");

   verify(service, times(2)).delete("A");
   // becomes
   Mocks.mock(service).verify().delete().withId("A").times(2);

   doReturn("X").when(service).create("ID", "N", true);
   // becomes (answer-first do-family, spy- and void-safe)
   Mocks.doReturn("X").when(service).create().withId("ID").withName("N").withOverwrite(true).done();
   ```

   Per positional argument: `eq(v)` / a bare value → `.withXxx(v)`; `any()` / `anyInt()` / … →
   omitted (the DSL defaults to `any()`); `argThat(m)` → `.withXxxMatching(m)`; `captor.capture()`
   → `.capturingXxx(captor)` (verify only). The answer-first `doReturn/doThrow/doAnswer/doNothing/
   doCallRealMethod(...).when(mock).method(args)` family maps the same way, the leading value/
   throwable/answer becoming the `Mocks.doX(...)` argument and the chain closing with `.done()`.
   Verification modes map to the DSL terminals
   (`times(n)`, `never()`, `atLeastOnce()`, `atLeast(n)`, `atMost(n)`, `only()`, bare → `called()`,
   anything else → `.verified(mode)`).

4. **Rewrite delegated statics** (`RewriteDelegatedStatics`) — `verifyNoInteractions`,
   `verifyNoMoreInteractions`, `inOrder`, `reset`, `clearInvocations`, `timeout`, `after` (and any
   leftover bare `verify`/`when`) become the `Mocks.*` passthroughs.

Steps 3 and 4 also prune the now-unused `org.mockito.Mockito` / `org.mockito.ArgumentMatchers`
static imports (a wildcard import is narrowed to what remains in use); an import still referenced
by an un-migrated call is kept.

## What it leaves alone

The rewrite is conservative: any call site it cannot map confidently and completely is left exactly
as written, with a `// TODO mockito-annotations: …` comment above it that explains *why*, so the
output always compiles and never changes behavior. The comment states the specific reason:

- **synthetic parameter names** — the mocked method's parameter names are unavailable because its
  type was compiled without `-parameters` (typically a dependency), so `withXxx` names cannot be
  derived (see below);
- **unmappable argument** — an argument uses a matcher with no fluent-DSL equivalent, e.g. a
  matcher held in a variable (both the `when()` and do-family forms);
- **receiver** — the stub receiver is not a simple mock reference (e.g. `when(getSvc().foo())`);
- **no type** — the mocked method could not be type-attributed (its type may be missing from the
  rewrite classpath).

## The `-parameters` caveat

The rewrite turns positional arguments into *named* withers (`withId`, `withOverwrite`), which
requires the parameter names of the mocked method. OpenRewrite reads them from type attribution:

- **Types compiled from source in the same build** (typically your own services) carry their real
  parameter names — the rewrite works.
- **Types from a dependency JAR** only carry parameter names if that JAR was compiled with
  `-parameters` (most libraries are not). Without them the type model exposes synthetic
  `arg0`, `arg1`, … names, which are useless for withers.

When names are unavailable the recipe treats the call as unmappable and drops the TODO marker
rather than emitting `withArg0(...)`. This is the same requirement the annotation processor has at
generation time — see the [`-parameters` note in the DSL readme](../../annotations/mockito/readme.md).
For the migrated code to have meaningful wither names, compile the module under migration with
`-parameters`.

## Building

Requires **Java 17+** to build (OpenRewrite's requirement), independent of the Java 11 baseline of
the rest of the project. The recipe parses and rewrites Java 11 sources.

```bash
mvn -pl rewrite/mockito -am install
```
