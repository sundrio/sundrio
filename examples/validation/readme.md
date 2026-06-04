# Validation Examples

Two examples demonstrating `@Validation` annotation processing with and without the builder framework.

## Standalone (no builder)

**Location:** [standalone/](standalone/)

Uses only `validation-annotations`. No `@Buildable`, no generated builders. POJOs are plain Java classes
with regular constructors.

The annotation processor generates a fluent **Validator DSL** for each validated type:

```java
// Direct validation with explicit args
List<ValidationError> errors = PersonValidator.of(person)
    .validateFirstName()
    .validateMinAge(18)
    .validateMaxAge(65)
    .validate();

// Pre-supply args via withContext, then use no-arg methods
List<ValidationError> errors = PersonValidator
    .withContext(65, 18, 18)  // maxAge, minAge, ctor minAge
    .of(person)
    .validateMinAge()          // uses 18 from context
    .validateMaxAge()          // uses 65 from context
    .validateAgeRange()        // uses ctor minAge=18, maxAge=65 from context
    .validate();

// Static convenience: runs all validations with defaults
List<ValidationError> errors = PersonValidator.validate(person);
```

### What gets generated

| Class | Purpose |
|-------|---------|
| `PersonValidator` | Fluent DSL with `of()`, `withContext()`, per-method `validateXxx()` / `validateXxx(args)`, terminal `validate()` |
| `PersonValidatorContext` | Holds pre-supplied params, returned by `withContext()`, has `of(person)` to start the chain |

### Maven dependency

```xml
<dependency>
    <groupId>io.sundr</groupId>
    <artifactId>validation-annotations</artifactId>
</dependency>
```

No other sundrio dependencies required.

## With Builder

**Location:** [with-builder/](with-builder/)

Uses both `validation-annotations` and `builder-annotations`. The `@Buildable` model class gets a
generated builder with integrated validation methods.

Both the **Validator DSL** and the **Builder validation DSL** are available:

```java
// Validator DSL (same as standalone)
List<ValidationError> errors = AddressValidator.of(address)
    .validateStreet()
    .validateNumber(1)
    .validate();

// Builder DSL: Jakarta Bean Validation
ValidationResult<?> result = new DefaultAddressBuilder()
    .withStreet("Sesame")
    .withNumber(1)
    .withZipCode("12345")
    .usingValidation()
    .build();

// Builder DSL: per-method fluent selection
ValidationResult<?> result = new DefaultAddressBuilder()
    .withStreet("Sesame")
    .withNumber(1)
    .usingNewValidator()
    .checkStreet()
    .checkNumber(1)
    .build();

// Builder DSL: programmatic validator
ValidationResult<?> result = new DefaultAddressBuilder()
    .withStreet("Sesame")
    .validate(address -> {
        if (address.getStreet() == null)
            return List.of(new ValidationError("street", "required"));
        return List.of();
    })
    .build();
```

### What gets generated

Everything from the standalone case, plus:

| Class | Purpose |
|-------|---------|
| `AddressValidatorsBuilder` | Builder-integrated fluent DSL with `checkXxx()` methods |
| `DefaultAddressBuilder` | Extended with `validate()`, `validateAll()`, `usingValidation()`, `usingNewValidator()` |

### Maven dependencies

```xml
<dependency>
    <groupId>io.sundr</groupId>
    <artifactId>builder-annotations</artifactId>
</dependency>
<dependency>
    <groupId>io.sundr</groupId>
    <artifactId>validation-annotations</artifactId>
</dependency>
```

## Validation methods with extra parameters

Both styles support `@Validation` methods that accept additional arguments beyond the target type:

```java
@Validation
public static List<ValidationError> validateMinAge(Person person, int minAge) { ... }

@Validation
public static List<ValidationError> validateMaxAge(Person person, int maxAge) { ... }
```

This generates:
- `validateMinAge()` (no-arg, uses default or context value) and `validateMinAge(int minAge)` (explicit)
- `checkMinAge()` / `checkMinAge(int minAge)` on the builder DSL

## Non-static validation methods with constructor arguments

Validation classes without a no-arg constructor are supported:

```java
public class PersonAgeValidations {
    private final int minAge;

    public PersonAgeValidations(int minAge) { this.minAge = minAge; }

    @Validation
    public List<ValidationError> validateAgeRange(Person person, int maxAge) { ... }
}
```

Constructor arguments are supplied via:
- **Validator DSL:** `PersonValidator.withContext(..., ctorMinAge).of(person).validateAgeRange().validate()`
- **Builder DSL:** `builder.usingNewValidator(ctorMinAge, ...).checkAgeRange(maxAge).build()`
