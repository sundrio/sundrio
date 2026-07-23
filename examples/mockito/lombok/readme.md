# Mockito + Lombok Example

Integration test proving the `mockito-annotations` DSL is generated against **Lombok-generated
members**. Lombok and the `MockableProcessor` both run on this module's annotation-processor path,
exactly like a user project (compile-testing cannot run Lombok, so this has to be a real Maven
module).

## What it demonstrates

- Lombok-generated **getters** (`getZone()`, `getReplicas()`, `isEnabled()`) and **setters**
  (`setZone(...)`) are exposed as stubbable/verifiable DSL methods.
- Lombok-generated `equals` / `hashCode` / `toString` are **excluded** from the DSL
  (asserted reflectively in `LombokMockTest.excludesEqualsHashCodeToString`).
- Covers `@Data` (getters + setters + equals/hashCode/toString), `@Getter`/`@Setter`, and
  `@Value` (immutable, getters only).

## Marker flow

Production types stay plain Lombok classes. A marker in `src/test/java` lists them:

```java
@Mockables({ CloudConfig.class, ProxySettings.class, Endpoint.class })
class MockTargets {
}
```

The targets are compiled (with Lombok) in this module's **main** compilation, so by test-compile
the processor resolves their Lombok-generated members from the compiled class files. Lombok methods
are therefore materialized before the processor sees the types.

## `-parameters` is required

The setter wither name comes from Lombok's setter parameter, which Lombok names after the field
(`setZone(String zone)` -> `withZone(...)`). Without `-parameters` the parameter name is lost and
the wither degrades to `withArg0`. The `pom.xml` sets `-parameters` (and `-proc:full`).

Generated setter surface (from `CloudConfigMock`):

```java
public CloudConfigMock.SetZoneStub setZone();          // router
public SetZoneStub withZone(String value);             // pin the argument
public void doNothing();                               // void terminal
// verify: mock(cfg).verify().setZone().withZone("x").called();
```

Usage:

```java
mock(cfg).when().getZone().thenReturn("eu-west");
mock(cfg).when().setZone().withZone("x").doNothing();
cfg.setZone("x");
mock(cfg).verify().setZone().withZone("x").called();
```

## Notes / findings

- `@Value` makes the class **final**. Mockito 5's default inline mock maker mocks final classes
  with no extra config, so the `@Value` getter-only case (`Endpoint`) mocks and stubs fine.
- Lombok's `canEqual(Object)` (a public helper `@Data` generates) does surface as a DSL method
  (`canEqual()`), since it is an ordinary public method, unlike `equals`/`hashCode`/`toString`
  which are explicitly excluded. `@Value` did not surface `canEqual` here.
