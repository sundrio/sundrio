# Continuous Testing Example

This example demonstrates the continuous testing functionality of the Sundrio Maven plugin.

## What it does

The `continuous-test` goal provides intelligent test execution based on file changes:

1. **Initial Analysis**: Scans all test files in the test directory and creates TypeDef representations using the source adapter
2. **Dependency Mapping**: Maps each test class to the source classes it references/depends on
3. **File Watching**: Monitors both source and test directories for file changes
4. **Smart Test Execution**: Only runs tests that are affected by source code changes

## Features

- **Test Discovery**: Automatically finds test classes based on configurable patterns (default: `**/*Test.java`, `**/Test*.java`, `**/*Tests.java`)
- **Dependency Analysis**: Uses Sundrio's TypeDef model to analyze test dependencies on source classes
- **Real-time Monitoring**: Watches file system for changes and triggers relevant tests immediately
- **Intelligent Execution**: Only runs tests that actually depend on changed source files

## Configuration

```xml
<plugin>
    <groupId>io.sundr</groupId>
    <artifactId>sundr-maven-plugin</artifactId>
    <version>${project.version}</version>
    <configuration>
        <testIncludes>**/*Test.java,**/Test*.java</testIncludes>
        <testGoal>test</testGoal>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>continuous-test</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## Usage

Run the continuous testing goal:

```bash
mvn io.sundr:sundr-maven-plugin:continuous-test
```

Or configure it in your build lifecycle and run:

```bash
mvn test
```

## How it works

1. The plugin starts by running all tests once to establish a baseline
2. It analyzes all test files using the Sundrio source adapter to create TypeDef models
3. It analyzes all source files similarly
4. It maps each test to the source files it depends on by examining:
   - Method parameter types
   - Method return types
   - Field types
   - Extended classes
   - Implemented interfaces
5. It starts file system watchers on both source and test directories
6. When a source file changes, it finds all tests that depend on that source file and runs only those tests
7. When a test file changes, it runs that specific test and re-analyzes its dependencies

## Example

In this example:
- `CalculatorTest` depends on `Calculator` class
- `StringUtilsTest` depends on `StringUtils` class

If you modify the `Calculator.java` file, only `CalculatorTest` will be executed.
If you modify the `StringUtils.java` file, only `StringUtilsTest` will be executed.

This provides much faster feedback during development compared to running the entire test suite on every change.

## Limitations

- Currently supports single module projects only
- Requires Java source files (doesn't analyze bytecode dependencies)
- File watching may not work properly in some containerized environments
- Only analyzes direct type dependencies (not runtime or reflection-based dependencies)