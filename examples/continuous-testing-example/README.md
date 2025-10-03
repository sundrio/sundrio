# Continuous Testing Example (EXPERIMENTAL)

This example demonstrates the **experimental** continuous testing functionality of the Sundrio Maven plugin.

> **Note**: This feature is experimental and was added primarily as a way to test the limits of the Sundrio model itself and to further push the barriers of what's possible with code analysis and intelligent test execution.

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

## Example Output

When running continuous testing, you'll see output like this:

```
Continuous Testing: Tracking 32 test methods
[INFO] /home/iocanel/workspace/src/github.com/sundrio/sundrio/core/src/main/java/io/sundr/FunctionFactory.java: Some input files use unchecked or unsafe operations.
[INFO] /home/iocanel/workspace/src/github.com/sundrio/sundrio/core/src/main/java/io/sundr/FunctionFactory.java: Recompile with -Xlint:unchecked for details.
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/iocanel/workspace/src/github.com/sundrio/sundrio/core/src/test/resources
[INFO] Recompiling the module because of changed dependency.
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 8 source files with javac [debug release 11] to target/test-classes
[INFO] --- surefire:3.5.2:test (default-test) @ sundr-core ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit4.JUnit4Provider
[INFO] -------------------------------------------------------
[INFO] -------------------------------------------------------
[INFO] Running io.sundr.utils.MapsTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.069 s -- in io.sundr.utils.MapsTest
[INFO] Results:
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  5.828 s
[INFO] Finished at: 2025-10-03T17:46:15+03:00
[INFO] ------------------------------------------------------------------------


✅ Tests: 32 methods, 32 ✓ | Last run: 4 run, 4 ✓
[Q]uit | [D]ependency tree | [R]estart | [H]elp
```

The interface shows:
- **Tracking Status**: Total number of test methods being monitored
- **Smart Execution**: Only 4 out of 32 tests were run (those affected by the change)
- **Real-time Feedback**: Interactive status line with test results
- **Controls**: Keyboard shortcuts for quit, dependency analysis, restart, and help

### Dependency Tree Analysis

Pressing `[D]` shows the dependency tree for troubleshooting and clarity:

```
🌳 Dependency Tree - From Last Changes to Affected Tests


📁 Changes Types:
  ✏️  Modified: io.sundr.utils.Maps
    📋 Method changes: 1
      └─ MODIFIED: public static java.lang.String extractKey(java.lang.String mapping) (implementation changed)

🌳 Method Dependency Tree:

  Maps.extractKey
  \- Maps.create
     +- MapsTest.shouldCreateMapWithOneElement
     +- MapsTest.shouldCreateMapWithThreeElements
     +- MapsTest.shouldCreateMapWithTwoElements
     \- MapsTest.shouldCreateFromMapping

📄 Affected Test Files:
  └─ /home/iocanel/workspace/src/github.com/sundrio/sundrio/core/src/test/java/io/sundr/utils/MapsTest.java


Press any key to return to continuous testing...
```

This view helps understand:
- **What Changed**: Which classes and methods were modified
- **Impact Analysis**: How changes propagate through method dependencies
- **Test Selection**: Why specific tests were chosen to run
- **Dependency Chain**: The exact path from source changes to affected tests

## Limitations

- Currently supports single module projects only
- Requires Java source files (doesn't analyze bytecode dependencies)
- File watching may not work properly in some containerized environments
- Only analyzes direct type dependencies (not runtime or reflection-based dependencies)