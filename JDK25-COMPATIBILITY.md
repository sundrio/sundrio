# JDK 25 Compatibility Guide

## Overview

Starting with JDK 25, stricter module encapsulation rules can prevent Sundrio from introspecting classes loaded from JAR files, even with `--add-opens` flags. This document explains the issue and provides workarounds.

## The Problem

### Symptoms

When building on JDK 25 with `@Buildable` classes that reference types from dependency JARs:

1. **Without `--add-opens`**: Builders are not generated at all
2. **With `--add-opens`**: Builders are generated but may be incomplete - missing nested collection builder methods like `addNew*()` methods

### Root Cause

JDK 25's module system enforces stricter encapsulation that prevents deep reflection into classes from JAR files. When Sundrio uses the reflection adapter to introspect a class from a JAR:

- `getDeclaredFields()` may throw `InaccessibleObjectException`
- `getDeclaredMethods()` may throw `InaccessibleObjectException`
- `getDeclaredConstructors()` may throw `InaccessibleObjectException`
- `getDeclaredClasses()` may throw `InaccessibleObjectException`

These failures prevent discovery of:
- Field types (e.g., `Requirement` from `List<Requirement>`)
- Nested inner classes
- Method signatures needed for builder generation

### Example

```java
// In dependency JAR: my-api-1.0.jar
public class Selector {
    private List<Requirement> matchExpressions;  // JDK 25 may not discover Requirement
    // ...
}

// In your module
@Buildable(refs = @BuildableReference(Selector.class))
public class PolicyBinding {
    private Selector selector;
}
```

**On JDK 17/21**: Sundrio introspects `Selector` → discovers `List<Requirement>` → introspects `Requirement` → generates `addNewMatchExpression()` ✅

**On JDK 25**: Sundrio reads `Selector` but `getDeclaredFields()` fails → cannot discover `Requirement` → missing `addNewMatchExpression()` ❌

## Workarounds

### Option 1: Use --add-opens Flags (Partial Fix)

Add the following to your Maven compiler plugin configuration:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <configuration>
        <compilerArgs>
            <!-- Add opens for modules containing referenced classes -->
            <arg>--add-opens</arg>
            <arg>java.base/java.lang=ALL-UNNAMED</arg>
            <arg>--add-opens</arg>
            <arg>your.api.module/your.api.package=ALL-UNNAMED</arg>
        </compilerArgs>
    </configuration>
</plugin>
```

**Note**: This may allow basic builder generation but might not fully resolve nested type discovery issues.

### Option 2: Move Classes to Same Module (Best Solution)

If possible, move the `@Buildable` class and its references into the same source module:

```
your-project/
  ├── src/main/java/
  │   ├── com/example/Selector.java        ← Source available
  │   ├── com/example/Requirement.java     ← Source available
  │   └── com/example/PolicyBinding.java   ← @Buildable class
```

When classes are in the same compilation unit, Sundrio uses the APT (Annotation Processing Tool) adapter instead of reflection, which doesn't have module restrictions.

### Option 3: Bundle Sources in Dependency JARs

Configure your dependency modules to include source files in the JAR:

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-source-plugin</artifactId>
    <executions>
        <execution>
            <id>attach-sources</id>
            <goals>
                <goal>jar-no-fork</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

Then add the source JAR as a dependency. Sundrio can parse source files using the JavaParser adapter when reflection fails.

### Option 4: Downgrade to JDK 17 or 21 (Temporary)

JDK 17 and 21 LTS versions work correctly with current Sundrio versions:

```bash
export JAVA_HOME=/path/to/jdk-21
mvn clean install
```

## Changes in Sundrio

As of version 0.230.2+, Sundrio includes the following improvements for JDK 25 compatibility:

1. **Graceful Degradation**: Reflection failures no longer cause build failures. Instead, Sundrio:
   - Catches `InaccessibleObjectException` exceptions
   - Logs clear warning messages
   - Returns empty collections instead of failing
   - Continues builder generation with available information

2. **Improved Error Messages**: When reflection fails, you'll see:
   ```
   Warning: Cannot introspect fields of com.example.Selector due to module access restrictions (InaccessibleObjectException).
   This will prevent discovery of nested types from field declarations.
   Consider using --add-opens flags for the module containing this class.
   ```

3. **CI Testing**: GitHub Actions now test on JDK 25 to catch compatibility issues early.

## Known Limitations

- **Incomplete Builders**: Builders generated from JAR-based references may be missing nested builder methods on JDK 25
- **No Complete Fix**: The JDK 25 module system restrictions cannot be fully bypassed with code changes alone
- **Reflection-Based Discovery**: Classes loaded via reflection from JARs are most affected

## Checking Your Build

To verify if your build is affected:

1. Look for warning messages during compilation:
   ```
   Warning: Cannot introspect fields of <class> due to module access restrictions
   ```

2. Check if generated builders are missing expected methods

3. Compare builder generation between JDK 21 and JDK 25

## Recommendations

For new projects:

1. ✅ Use JDK 17 or 21 LTS for production builds
2. ✅ Keep `@Buildable` classes and their references in the same module when possible
3. ✅ Test on JDK 25 but don't require it for builds yet
4. ✅ Monitor Sundrio releases for further JDK 25 improvements

For existing projects experiencing issues:

1. ✅ Apply `--add-opens` flags as a first step
2. ✅ Consider restructuring modules to avoid cross-JAR references
3. ✅ Report specific issues to help improve JDK 25 support

## Reporting Issues

If you encounter JDK 25 compatibility issues:

1. Verify you're using Sundrio 0.230.2 or later
2. Check the warning messages in your build logs
3. Try the workarounds listed above
4. Report issues at: https://github.com/sundrio/sundrio/issues

Include:
- JDK version (`java -version`)
- Sundrio version
- Build logs showing the warnings
- Whether `--add-opens` flags were used
- Minimal reproduction case if possible

## Future Directions

The Sundrio team is investigating:

1. Alternative introspection strategies for JDK 25+
2. Enhanced source-based adapters
3. Build-time code generation alternatives
4. Module-aware builder generation

Stay tuned for updates in future releases.
