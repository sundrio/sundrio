# Validation Annotations

The validation annotations module provides a composable, code-generation-based validation framework that integrates with the [Builder Generator](../builder/readme.md).

It supports multiple validation styles:
- **Jakarta Bean Validation** via constraint annotations (`@NotBlank`, `@NotNull`, etc.)
- **Custom validation methods** via the `@Validation` annotation
- **Programmatic validators** via the `Validator<T>` functional interface

## Getting started

Add the dependency to your project:

```xml
<dependency>
    <groupId>io.sundr</groupId>
    <artifactId>validation-annotations</artifactId>
    <version>${sundrio.version}</version>
</dependency>
```

Also add the annotation processor so the builder processor picks it up:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <annotationProcessorPaths>
            <path>
                <groupId>io.sundr</groupId>
                <artifactId>builder-annotations</artifactId>
                <version>${sundrio.version}</version>
            </path>
            <path>
                <groupId>io.sundr</groupId>
                <artifactId>validation-annotations</artifactId>
                <version>${sundrio.version}</version>
            </path>
        </annotationProcessorPaths>
    </configuration>
</plugin>
```

## The `@Validation` annotation

Annotate methods with `@Validation` to register them as validation methods for a type. The processor discovers all `@Validation`-annotated methods regardless of which class they are in — placing them in a `XxxValidations` class (e.g. `PersonValidations`) is a useful convention but not required.

> **Note:** `@Validation` methods must return a `ValidationError` type — either `io.sundr.validation.ValidationError` or a type from a custom `validationPackage` (any type whose fully-qualified name ends in `.validation.ValidationError` is accepted).

```java
public class PersonValidations {

  @Validation
  public static List<ValidationError> validateFirstName(Person person) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getFirstName() == null || person.getFirstName().isEmpty()) {
      errors.add(new ValidationError("firstName", "must not be empty"));
    }
    return errors;
  }

  @Validation
  public static List<ValidationError> validateLastName(Person person) {
    List<ValidationError> errors = new ArrayList<>();
    if (person.getLastName() == null || person.getLastName().isEmpty()) {
      errors.add(new ValidationError("lastName", "must not be empty"));
    }
    return errors;
  }
}
```

### Valid method signatures

A `@Validation` method must take the target type as its first (and only) parameter, and return one of:

| Return type             | Behaviour                                   |
|-------------------------|---------------------------------------------|
| `List<ValidationError>` | Returns collected errors                    |
| `T` (the target type)   | Returns the validated object or throws      |
| `void`                  | Throws `ValidationException` on failure     |

Methods can be `static` or instance methods. Instance methods require a public no-arg constructor on the enclosing class.

## Generated code

Given a `Person` class annotated with `@Buildable` and a `PersonValidations` class in the same package, the processor generates:

### `PersonValidator`

A class with a single static `validate` method that runs all registered `@Validation` methods:

```java
public final class PersonValidator {
  public static List<ValidationError> validate(Person item) {
    List<ValidationError> errors = new ArrayList();
    errors.addAll(PersonValidations.validateFirstName(item));
    errors.addAll(PersonValidations.validateLastName(item));
    return errors;
  }
}
```

### `PersonValidatorsBuilder`

A fluent builder that lets you select which validation methods to run:

```java
public final class PersonValidatorsBuilder {
  public PersonValidatorsBuilder checkFirstName() { ... }
  public PersonValidatorsBuilder checkLastName() { ... }

  public PersonValidatorsBuilder usingJakartaValidation() { ... }

  public ValidatingBuilder<Person> endValidation() { ... }
  public ValidationResult<Person> build() { ... }
}
```

### Extended `PersonBuilder`

The generated builder gains new methods for every validation style:

| Method                                      | Description                                          |
|---------------------------------------------|------------------------------------------------------|
| `usingValidation()`                         | Jakarta Bean Validation with default validator       |
| `usingValidator(jakarta.validation.Validator)` | Jakarta Bean Validation with an explicit validator |
| `validate(Validator<Person>...)`            | One or more programmatic validators                  |
| `validateAll()`                             | Runs all `@Validation` methods via `PersonValidator` |
| `usingNewValidator()`                       | Enters `PersonValidatorsBuilder` for per-method selection |

## Usage patterns

### Jakarta Bean Validation

Use constraint annotations on the model class and call `usingValidation()` on the builder:

```java
@Buildable
public class Person {

  @NotBlank
  private final String firstName;

  @NotBlank
  private final String lastName;

  // constructor, getters ...
}
```

```java
ValidationResult<Person> result = new PersonBuilder()
    .withFirstName("John")
    .withLastName("Doe")
    .usingValidation()
    .build();

assertTrue(result.isValid());
Person person = result.orElseThrow();
```

### Run all `@Validation` methods

```java
ValidationResult<Person> result = new PersonBuilder()
    .withFirstName("John")
    .withLastName("Doe")
    .validateAll()
    .build();

if (!result.isValid()) {
  result.getErrors().forEach(System.err::println);
}
```

### Select validation methods individually

```java
ValidationResult<Person> result = new PersonBuilder()
    .withFirstName("John")
    .usingNewValidator()
    .checkFirstName()   // only this method runs; lastName is not checked
    .build();

assertTrue(result.isValid());
```

Combine with Jakarta Bean Validation by calling `usingJakartaValidation()` in the chain:

```java
ValidationResult<Person> result = new PersonBuilder()
    .withFirstName("John")
    .withLastName("Doe")
    .usingNewValidator()
    .usingJakartaValidation()
    .checkFirstName()
    .checkLastName()
    .build();
```

Call `endValidation()` to get back a `ValidatingBuilder<Person>` that can be composed further, or call `build()` directly as a shortcut.

### Programmatic validators

```java
Validator<Person> nameValidator = person -> {
  if (person.getFirstName() == null) {
    return List.of(new ValidationError("firstName", "must not be null"));
  }
  return List.of();
};

ValidationResult<Person> result = new PersonBuilder()
    .withFirstName("John")
    .withLastName("Doe")
    .validate(nameValidator)
    .build();
```

### Working with `ValidationResult`

`ValidationResult<T>` is a monad-like type similar to `Optional` or `Either`:

```java
ValidationResult<Person> result = ...;

// Check validity
if (result.isValid()) {
  Person person = result.get();
}

// Throw on invalid
Person person = result.orElseThrow();

// Custom exception
Person person = result.orElseThrow(errors ->
    new IllegalArgumentException("Invalid person: " + errors));

// Conditional actions
result.ifValidOrElse(
    person -> System.out.println("Created: " + person),
    errors  -> errors.forEach(System.err::println));

// Map the result
ValidationResult<String> nameResult = result.map(Person::getFirstName);
```

## Custom packages

By default, the validation infrastructure (`Validator`, `ValidatingBuilder`, etc.) is provided by the `io.sundr.validation` package from this module. When you want to ship your own copy — for example, to avoid a runtime dependency on sundrio — you can instruct the processor to generate the infrastructure classes into a package of your choice.

> **Note:** `@Validation` methods may return either `io.sundr.validation.ValidationError` or the `ValidationError` from a custom `validationPackage`. When a custom `validationPackage` is used, the generated `PersonValidator` and `PersonValidatorsBuilder` automatically convert between the base `io.sundr.validation.ValidationError` and the custom package's `ValidationError`.

### `validationPackage`

Generates the validation infrastructure source files into a custom package:

```java
@Buildable(
    generateValidationPackage = true,
    validationPackage = "com.example.validation"
)
public class Person { ... }
```

The infrastructure classes (`Validator`, `ValidatingBuilder`, `ValidationResult`, etc.) will be generated under `com.example.validation`, and the builder's `validate()` and `usingValidation()` methods will use them. This allows consuming projects to depend only on your artifact rather than on `io.sundr:validation-annotations` at runtime.

### `basePackage`

When both the builder and validation infrastructure should live under a common base package:

```java
@Buildable(
    basePackage = "com.example",
    generateBuilderPackage = true,
    generateValidationPackage = true
)
public class Person { ... }
```

This generates:
- Builder classes under `com.example.builder`
- Validation infrastructure under `com.example.validation`

## Examples

- [hello-validation](../../examples/builder/hello-validation) — Jakarta bean validation and custom `@Validation` methods
- [hello-validation-package](../../examples/builder/hello-validation-package) — custom `validationPackage`
- [hello-base-package](../../examples/builder/hello-base-package) — custom `basePackage` for both builder and validation
