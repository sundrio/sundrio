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
Continuous Testing: Tracking 8 test methods
[INFO] --- surefire:3.5.2:test (default-test) @ continuous-testing-example ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit4.JUnit4Provider
[INFO] -------------------------------------------------------
[INFO] -------------------------------------------------------
[INFO] Running io.sundr.examples.StringUtilsTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.032 s -- in io.sundr.examples.StringUtilsTest
[INFO] Running io.sundr.examples.CalculatorTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in io.sundr.examples.CalculatorTest
[INFO] Results:
[INFO] Tests run: 8, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.491 s
[INFO] Finished at: 2025-10-08T10:26:58+03:00
[INFO] ------------------------------------------------------------------------


⏳ Tests: 8 methods, 8 ⏳ | Last run: 8 run, 8 ✓
[Q]uit | [D]ependency tree | [R]estart | [H]elp
```

The interface shows:
- **Tracking Status**: Total number of test methods being monitored
- **Real-time Feedback**: Interactive status line with test results
- **Controls**: Keyboard shortcuts for quit, dependency analysis, restart, and help

By performing a change in a source file, say `Calculator.java`, you'll see:

```
Continuous Testing: Tracking 8 test methods
[INFO] Compiling 2 source files with javac [debug release 11] to target/test-classes
[INFO] --- surefire:3.5.2:test (default-test) @ continuous-testing-example ---
[INFO] Using auto detected provider org.apache.maven.surefire.junit4.JUnit4Provider
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running io.sundr.examples.CalculatorTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.030 s -- in io.sundr.examples.CalculatorTest
[INFO] Results:
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  1.887 s
[INFO] Finished at: 2025-10-08T10:36:07+03:00
[INFO] ------------------------------------------------------------------------


✅ Tests: 8 methods, 8 ✓ | Last run: 1 run, 1 ✓
[Q]uit | [D]ependency tree | [R]estart | [H]elp
```

The out now shows that only 1 test was run, the one affected by the change.

### Dependency Tree Analysis

Pressing `[D]` shows the dependency tree for troubleshooting and clarity:

```
Continuous Testing: Tracking 8 test methods
🌳 Dependency Tree - From Last Changes to Affected Tests


📁 Changes Types:
  ✏️  Modified: io.sundr.examples.Calculator
    📋 Method changes: 1
      └─ MODIFIED: public Int add(Int a,Int b) (implementation changed)

🌳 Method Dependency Tree:

  Calculator.add
  \- CalculatorTest.testAdd

🧪 Affected Test Methods:
  └─ io.sundr.examples.CalculatorTest.testAdd


Press any key to return to continuous testing...
✅ Tests: 8 methods, 8 ✓ | Last run: 1 run, 1 ✓
[Q]uit | [D]ependency tree | [R]estart | [H]elp
```

This view helps understand:
- **What Changed**: Which classes and methods were modified
- **Impact Analysis**: How changes propagate through method dependencies
- **Test Selection**: Why specific tests were chosen to run
- **Dependency Chain**: The exact path from source changes to affected tests

## Limitations

- Currently, supports single module projects only
- Requires Java source files (doesn't analyze bytecode dependencies)
- File watching may not work properly in some containerized environments
- Only analyzes direct type dependencies (not runtime or reflection-based dependencies)
