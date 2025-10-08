/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.maven;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.InvocationResult;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import io.sundr.adapter.api.AdapterContext;
import io.sundr.adapter.source.Project;
import io.sundr.adapter.source.analysis.ImpactAnalysisResult;
import io.sundr.adapter.source.analysis.ImpactAnalyzer;
import io.sundr.adapter.source.change.ChangeSet;
import io.sundr.adapter.source.utils.Sources;
import io.sundr.model.Block;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.TypeDef;
import io.sundr.model.repo.DefinitionRepository;
import io.sundr.model.repo.MethodReference;
import io.sundr.tui.Key;
import io.sundr.tui.TermFrame;

/**
 * EXPERIMENTAL: Continuous testing mojo that watches for source changes and runs affected tests.
 *
 * This feature was added primarily as a way to test the limits of the Sundrio model itself
 * and as a way to further push the barrier higher. It represents an experimental exploration
 * of how Sundrio's code analysis capabilities can be applied to intelligent test execution.
 */
@Mojo(name = "continuous-test", defaultPhase = LifecyclePhase.TEST, requiresDependencyResolution = ResolutionScope.TEST, threadSafe = false)
public class ContinuousTestingMojo extends AbstractSundrioMojo {

  @Parameter(defaultValue = "${project.build.testSourceDirectory}")
  private File testSourceDirectory;

  @Parameter(defaultValue = "${project.build.sourceDirectory}")
  private File sourceDirectory;

  @Parameter(property = "maven.test.skip", defaultValue = "false")
  private boolean skipTests;

  @Parameter(property = "test.includes", defaultValue = "**/*Test.java,**/Test*.java,**/*Tests.java")
  private String testIncludes;

  @Parameter(property = "test.goal", defaultValue = "test")
  private String testGoal;

  private Project sourceProject;
  private ImpactAnalyzer impactAnalyzer;
  private final Map<String, Set<String>> testToSourceMapping = new ConcurrentHashMap<>();
  private final Map<String, TypeDef> testTypeDefs = new ConcurrentHashMap<>();
  private final Map<String, TypeDef> sourceTypeDefs = new ConcurrentHashMap<>();
  private final Map<String, Map<String, String>> sourceFileMethodSnapshots = new ConcurrentHashMap<>();
  private final Map<String, Set<String>> sourceMethodToTestMethods = new ConcurrentHashMap<>();

  // File event debouncing
  private final Map<String, ConcurrentLinkedQueue<FileChangeEvent>> pendingFileEvents = new ConcurrentHashMap<>();
  private final ScheduledExecutorService debounceExecutor = Executors.newSingleThreadScheduledExecutor();
  private final AtomicBoolean running = new AtomicBoolean(true);

  // Store the last change set for dependency tree display
  private volatile ChangeSet lastChangeSet;
  private volatile ImpactAnalysisResult lastImpactAnalysis;

  // Test status tracking - current run
  private final AtomicInteger totalTests = new AtomicInteger(0);
  private final AtomicInteger passedTests = new AtomicInteger(0);
  private final AtomicInteger failedTests = new AtomicInteger(0);
  private final AtomicInteger errorTests = new AtomicInteger(0);
  private final AtomicInteger skippedTests = new AtomicInteger(0);

  // Session-wide test status tracking - tracks current state of all tests
  private final Map<String, TestClassResult> sessionTestResults = new ConcurrentHashMap<>();
  private final AtomicInteger sessionTotalTests = new AtomicInteger(0);
  private final AtomicInteger sessionPassedTests = new AtomicInteger(0);
  private final AtomicInteger sessionFailedTests = new AtomicInteger(0);
  private final AtomicInteger sessionErrorTests = new AtomicInteger(0);
  private final AtomicInteger sessionSkippedTests = new AtomicInteger(0);

  // Individual test case tracking - tracks each test method
  private final Map<String, TestCaseResult> sessionTestCases = new ConcurrentHashMap<>();

  // Current run individual test tracking
  private final Map<String, TestCaseResult> currentRunTestCases = new ConcurrentHashMap<>();

  // Track which specific test methods were requested for the current run
  private final Set<String> requestedTestMethods = ConcurrentHashMap.newKeySet();
  private boolean runningSpecificMethods = false;

  // TUI Components
  private TermFrame termFrame;

  // Header supplier for dynamic status display
  private String generateStatusHeader() {
    // Individual test case stats

    // Build header with test status
    StringBuilder header = new StringBuilder();
    header.append(Ansi.ansi().fgBrightCyan().a("Continuous Testing").reset().a(": Tracking ").a(sessionTestCases.size())
        .a(" test methods\n"));

    return header.toString();
  }

  // Footer supplier for key commands
  private String generateCommandFooter() {
    StringBuilder footer = new StringBuilder();

    Map<TestStatus, Long> testCaseStats = sessionTestCases.values().stream()
        .collect(Collectors.groupingBy(tc -> tc.status, Collectors.counting()));

    long casePending = testCaseStats.getOrDefault(TestStatus.PENDING, 0L);
    long casePassed = testCaseStats.getOrDefault(TestStatus.PASSED, 0L);
    long caseFailed = testCaseStats.getOrDefault(TestStatus.FAILED, 0L);
    long caseErrors = testCaseStats.getOrDefault(TestStatus.ERROR, 0L);
    long caseSkipped = testCaseStats.getOrDefault(TestStatus.SKIPPED, 0L);
    long caseTotal = sessionTestCases.size();

    String statusEmoji;
    if (caseFailed > 0 || caseErrors > 0) {
      statusEmoji = "❌";
    } else if (casePending > 0) {
      statusEmoji = "⏳";
    } else if (caseTotal > 0) {
      statusEmoji = "✅";
    } else {
      statusEmoji = "⏳";
    }
    footer.append(statusEmoji).append(" Tests: ").append(caseTotal).append(" methods");

    if (casePending > 0)
      footer.append(", ").append(casePending).append(" ⏳");
    if (casePassed > 0)
      footer.append(", ").append(casePassed).append(" ✓");
    if (caseFailed > 0)
      footer.append(", ").append(caseFailed).append(" ✗");
    if (caseErrors > 0)
      footer.append(", ").append(caseErrors).append(" ⚠");
    if (caseSkipped > 0)
      footer.append(", ").append(caseSkipped).append(" ⊘");

    // Add current run info if there was a recent run
    if (!currentRunTestCases.isEmpty()) {
      Map<TestStatus, Long> currentStats = currentRunTestCases.values().stream()
          .collect(Collectors.groupingBy(tc -> tc.status, Collectors.counting()));

      long currentPassed = currentStats.getOrDefault(TestStatus.PASSED, 0L);
      long currentFailed = currentStats.getOrDefault(TestStatus.FAILED, 0L);
      long currentErrors = currentStats.getOrDefault(TestStatus.ERROR, 0L);
      long currentSkipped = currentStats.getOrDefault(TestStatus.SKIPPED, 0L);
      long totalFailures = currentFailed + currentErrors;

      footer.append(" | Last run: ").append(currentRunTestCases.size()).append(" run");

      if (currentPassed > 0) {
        footer.append(", ").append(currentPassed).append(" ✓");
      }
      if (totalFailures > 0) {
        footer.append(", ").append(totalFailures).append(" ✗");
      }
      if (currentSkipped > 0) {
        footer.append(", ").append(currentSkipped).append(" ⊘");
      }
    }

    footer.append("\n");
    footer.append(Ansi.ansi().fgBrightCyan().a("[Q]").reset().a("uit | ").fgBrightCyan().a("[D]").reset().a("ependency tree | ")
        .fgBrightCyan().a("[R]").reset().a("estart | ").fgBrightCyan().a("[H]").reset().a("elp"));
    return footer.toString();
  }

  // Initialize TermFrame with key bindings and dynamic content
  private void initializeTermFrame() throws Exception {
    termFrame = TermFrame.newFrame()
        .withHeader(this::generateStatusHeader)
        .withFooter(this::generateCommandFooter)
        .withExitKey(Key.ESC) // Will be overridden by 'q' binding
        .withHelpKey('h')
        .withKeyBinding('q', "Quit continuous testing", tf -> {
          tf.println("Quitting continuous testing...");
          running.set(false);
          System.exit(0);
        })
        .withKeyBinding('d', "Show dependency tree", tf -> {
          showDependencyTree();
        })
        .withKeyBinding('r', "Re-run all tests", tf -> {
          tf.println("Re-running all tests...");
          try {
            runAllTests();
          } catch (Exception e) {
            tf.println("❌ Error running tests: " + e.getMessage());
          }
        });

    termFrame.start();
  }

  // Run initial test suite and show simple status
  private void runInitialTests() throws MavenInvocationException {
    termFrame.println("🧪 Running initial test suite...");
    termFrame.println("");

    // Run the regular test execution
    runAllTests();
  }

  // Start file watching in background and run TermFrame event loop
  private void startTermFrameEventLoop() throws Exception {

    // Start file watching in a background thread
    Thread watcherThread = new Thread(() -> {
      try {
        sourceProject.allSources().watch(changeSet -> {
          try {
            handleChangeSetWithImpactAnalysis(changeSet);
            // No need to call updateStatusDisplay - header/footer will update automatically
          } catch (Exception e) {
            termFrame.println("❌ Error handling file changes: " + e.getMessage());
          }
        }).get(); // Wait for the watch to complete (which it never will unless interrupted)
      } catch (Exception e) {
        termFrame.println("❌ File watcher error: " + e.getMessage());
      }
    });
    watcherThread.setDaemon(true);
    watcherThread.start();

    // Run TermFrame event loop (this blocks until exit)
    termFrame.run();
  }

  // Helper class to track individual test class results
  private static class TestClassResult {
    final int total;
    final int passed;
    final int failed;
    final int errors;
    final int skipped;

    TestClassResult(int total, int passed, int failed, int errors, int skipped) {
      this.total = total;
      this.passed = passed;
      this.failed = failed;
      this.errors = errors;
      this.skipped = skipped;
    }
  }

  // Helper class to track individual test case results
  private static class TestCaseResult {
    final String testClass;
    final String testMethod;
    final TestStatus status;
    final String errorMessage;
    final long lastRunTime;

    TestCaseResult(String testClass, String testMethod, TestStatus status, String errorMessage) {
      this.testClass = testClass;
      this.testMethod = testMethod;
      this.status = status;
      this.errorMessage = errorMessage;
      this.lastRunTime = System.currentTimeMillis();
    }

    String getFullName() {
      return testClass + "#" + testMethod;
    }
  }

  private enum TestStatus {
    PENDING, PASSED, FAILED, ERROR, SKIPPED
  }

  // File change event wrapper
  private static class FileChangeEvent {
    final Path filePath;
    final WatchEvent.Kind<?> kind;
    final long timestamp;

    FileChangeEvent(Path filePath, WatchEvent.Kind<?> kind) {
      this.filePath = filePath;
      this.kind = kind;
      this.timestamp = System.currentTimeMillis();
    }
  }

  @Override
  public void execute() throws MojoExecutionException, MojoFailureException {
    if (skipTests) {
      getLog().info("Tests are skipped, continuous testing disabled");
      return;
    }

    try {
      // Initialize ANSI console for colored output
      AnsiConsole.systemInstall();

      // Initialize Project DSL and Impact Analyzer
      initializeProject();

      // Analyze project structure using legacy methods for compatibility
      analyzeTestFiles();
      analyzeSourceFiles();
      mapTestDependencies();
      createMethodSnapshots();
      buildMethodToTestMapping();

      // Analyze project structure using DSL
      analyzeProjectStructure();

      // Initialize test case data before first status display
      initializeTestCaseData();

      // Initialize TermFrame TUI
      initializeTermFrame();

      // Run initial tests and display output in TermFrame
      runInitialTests();

      // Start file watching with TermFrame event loop
      startTermFrameEventLoop();

    } catch (Exception e) {
      throw new MojoExecutionException("Error during continuous testing", e);
    } finally {
      // Close TermFrame (this will restore terminal state automatically)
      if (termFrame != null) {
        try {
          termFrame.close();
        } catch (Exception e) {
          getLog().debug("Error closing TermFrame", e);
        }
      }
      // Note: AnsiConsole cleanup is now handled by TermFrame
    }
  }

  private void initializeProject() {

    // Create project from Maven module root
    sourceProject = Project.getProject(getProject().getBasedir().toPath());
    impactAnalyzer = new ImpactAnalyzer(sourceProject, DefinitionRepository.getRepository());

    getLog().info("Project initialized: " + sourceProject.getModuleRoot());
  }

  private void analyzeProjectStructure() {
    getLog().debug("Analyzing project structure...");

    // Analyze test files and their dependencies to source files
    var testFiles = sourceProject.testSources()
        .including(testIncludes.split(","))
        .list();

    var sourceFiles = sourceProject.sources().list();

    getLog().debug("Found " + testFiles.size() + " test files");
    getLog().debug("Found " + sourceFiles.size() + " source files");

    // Build mapping of test files to source dependencies
    buildTestSourceMapping(testFiles, sourceFiles);
  }

  private void buildTestSourceMapping(java.util.List<Path> testFiles, java.util.List<Path> sourceFiles) {
    getLog().debug("Building test to source mapping...");

    AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

    for (Path testFile : testFiles) {
      try {
        TypeDef testTypeDef;
        try (java.io.FileInputStream fis = new java.io.FileInputStream(testFile.toFile())) {
          testTypeDef = Sources.readTypeDefFromStream(fis, context);
          DefinitionRepository.getRepository().registerIfAbsent(testTypeDef);
        }

        Set<String> referencedSources = new HashSet<>();

        // Use MethodReference analysis to find dependencies
        for (Method method : testTypeDef.getMethods()) {
          Set<MethodReference> methodRefs = MethodReference.getMethodReferences(method);

          for (MethodReference methodRef : methodRefs) {
            // Find source file for this method's owning type
            sourceProject.sources()
                .find(methodRef.getOwningType())
                .ifPresent(sourcePath -> referencedSources.add(sourcePath.toString()));
          }
        }

        if (!referencedSources.isEmpty()) {
          testToSourceMapping.put(testFile.toString(), referencedSources);
          getLog().info("Test " + testFile.getFileName() + " -> " + referencedSources.size() + " sources");
        }

      } catch (Exception e) {
        getLog().warn("Failed to analyze test file: " + testFile, e);
      }
    }

    getLog().debug("Built mappings for " + testToSourceMapping.size() + " test files");
  }

  private void runAllTests() throws MavenInvocationException {
    getLog().info("Running all tests...");
    runningSpecificMethods = false;
    requestedTestMethods.clear();
    resetTestCounters();
    runMavenGoal(testGoal);
  }

  private void runSpecificTests(Set<String> testClasses) throws MavenInvocationException {
    if (testClasses.isEmpty()) {
      return;
    }

    runningSpecificMethods = false;
    requestedTestMethods.clear();

    String testClassNames = testClasses.stream()
        .map(this::extractClassName)
        .collect(Collectors.joining(","));

    resetTestCounters();
    runMavenGoal(testGoal, "-Dtest=" + testClassNames);
  }

  private void runMavenGoal(String goal, String... additionalArgs) throws MavenInvocationException {
    InvocationRequest request = new DefaultInvocationRequest();
    request.setPomFile(getProject().getFile());
    request.setGoals(List.of(goal));
    request.setBatchMode(true);
    // Don't use quiet mode as it suppresses test output we need

    // Capture both standard output and error output
    java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
    java.io.ByteArrayOutputStream errorStream = new java.io.ByteArrayOutputStream();
    java.io.PrintStream outputPrintStream = new java.io.PrintStream(outputStream);
    java.io.PrintStream errorPrintStream = new java.io.PrintStream(errorStream);

    request.setOutputHandler(line -> {
      outputPrintStream.println(line);
      // Also display in TermFrame if available, filtering Maven noise
      if (termFrame != null && line != null) {
        String trimmed = line.trim();
        if (!trimmed.isEmpty() &&
            !trimmed.startsWith("[INFO] Scanning for projects") &&
            !trimmed.startsWith("[INFO] Building") &&
            !trimmed.startsWith("[INFO] Skipping") &&
            !(trimmed.startsWith("[INFO] --- ") && !trimmed.contains("surefire")) &&
            !trimmed.equals("[INFO]")) {
          termFrame.println(line);
        }
      }
    });
    request.setErrorHandler(line -> {
      errorPrintStream.println(line);
      // Also display errors in TermFrame if available
      if (termFrame != null && line != null && !line.trim().isEmpty()) {
        termFrame.println("❌ " + line);
      }
    });

    if (additionalArgs.length > 0) {
      Properties props = new Properties();
      props.setProperty("test", additionalArgs[0].substring(additionalArgs[0].indexOf('=') + 1));
      request.setProperties(props);
    }

    Invoker invoker = new DefaultInvoker();

    // Clear current run test cases before execution
    currentRunTestCases.clear();

    InvocationResult result = invoker.execute(request);

    // Extract and display only test summaries from both output streams
    String output = outputStream.toString();
    String errorOutput = errorStream.toString();
    String fullOutput = output + "\n" + errorOutput;

    // Parse individual test cases first, then summaries
    parseIndividualTestResults(fullOutput);
    extractAndLogTestSummary(fullOutput);

    // Update session test cases with current run results
    updateSessionTestCases();

    // Status display updates automatically via header/footer suppliers

    if (result.getExitCode() != 0 && termFrame != null) {
      termFrame.println("⚠️ Test execution failed with exit code: " + result.getExitCode());
    }
  }

  private void extractAndLogTestSummary(String mavenOutput) {
    if (mavenOutput == null || mavenOutput.trim().isEmpty()) {
      getLog().info("✅ Test execution completed (no output captured)");
      return;
    }

    String[] lines = mavenOutput.split("\n");
    boolean inTestResults = false;
    boolean foundTestOutput = false;

    for (String line : lines) {
      String trimmedLine = line.trim();

      // Look for test result lines like "Tests run: 1, Failures: 0, Errors: 0, Skipped: 0"
      if (trimmedLine.contains("Tests run:") && trimmedLine.contains("Time elapsed:")) {
        getLog().info(trimmedLine);
        parseTestResults(trimmedLine);
        foundTestOutput = true;
      }
      // Capture test failures with more detail
      else if (trimmedLine.contains("FAILURE") || trimmedLine.contains("ERROR")) {
        getLog().warn("❌ " + trimmedLine);
        markTestCaseAsFailed(trimmedLine);
        foundTestOutput = true;
      }
      // Capture final summary line
      else if (trimmedLine.startsWith("Results:")) {
        inTestResults = true;
        getLog().info("🏁 " + trimmedLine);
        foundTestOutput = true;
      } else if (inTestResults
          && (trimmedLine.startsWith("Tests run:") || trimmedLine.contains("Failed:") || trimmedLine.contains("Errors:"))) {
        getLog().info("📈 " + trimmedLine);
        foundTestOutput = true;
        if (trimmedLine.isEmpty()) {
          inTestResults = false;
        }
      }
      // Look for Surefire plugin output pattern
      else if (trimmedLine.contains("[INFO]") && trimmedLine.contains("Surefire") && trimmedLine.contains("Test")) {
        getLog().info("🧪 " + trimmedLine.substring(trimmedLine.indexOf("]") + 1).trim());
        foundTestOutput = true;
      }
      // Look for test class execution messages
      else if (trimmedLine.contains("Running ") && trimmedLine.contains("Test")) {
        getLog().info(trimmedLine);
        foundTestOutput = true;
      }
    }
    termFrame.println(""); // Add spacing after test output
  }

  private void markTestCaseAsFailed(String failureLine) {
    // Try to extract test method from failure line
    // Patterns like "testMethod(ClassName)" or "ClassName.testMethod"
    for (TestCaseResult testCase : currentRunTestCases.values()) {
      if (failureLine.contains(testCase.testMethod) || failureLine.contains(testCase.testClass)) {
        // Update the test case status to failed
        String testKey = testCase.getFullName();
        currentRunTestCases.put(testKey,
            new TestCaseResult(testCase.testClass, testCase.testMethod, TestStatus.FAILED, failureLine));
        getLog().debug("Marked test case as failed: " + testKey);
        break;
      }
    }
  }

  private void logCurrentRunSummary() {
    if (currentRunTestCases.isEmpty()) {
      return;
    }

    Map<TestStatus, Long> currentRunStats = currentRunTestCases.values().stream()
        .collect(Collectors.groupingBy(tc -> tc.status, Collectors.counting()));

    long runPassed = currentRunStats.getOrDefault(TestStatus.PASSED, 0L);
    long runFailed = currentRunStats.getOrDefault(TestStatus.FAILED, 0L);
    long runErrors = currentRunStats.getOrDefault(TestStatus.ERROR, 0L);
    long runSkipped = currentRunStats.getOrDefault(TestStatus.SKIPPED, 0L);

    StringBuilder summary = new StringBuilder("📋 Current run: ");
    summary.append(currentRunTestCases.size()).append(" test cases - ");

    if (runPassed > 0)
      summary.append(runPassed).append(" ✓ ");
    if (runFailed > 0)
      summary.append(runFailed).append(" ✗ ");
    if (runErrors > 0)
      summary.append(runErrors).append(" ⚠ ");
    if (runSkipped > 0)
      summary.append(runSkipped).append(" ⊘ ");

    // Show individual failed test cases
    if (runFailed > 0 || runErrors > 0) {
      getLog().warn(summary.toString());
      currentRunTestCases.values().stream()
          .filter(tc -> tc.status == TestStatus.FAILED || tc.status == TestStatus.ERROR)
          .forEach(tc -> getLog().warn("  ❌ " + tc.getFullName()));
    } else {
      getLog().info(summary.toString());
    }
  }

  private void analyzeTestFiles() throws IOException {
    getLog().debug("Analyzing test files...");
    if (!testSourceDirectory.exists()) {
      getLog().warn("Test source directory does not exist: " + testSourceDirectory);
      return;
    }

    AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

    getLog().debug("Test source directory: " + testSourceDirectory);
    getLog().debug("Test includes pattern: " + testIncludes);

    Files.walk(testSourceDirectory.toPath())
        .filter(path -> path.toString().endsWith(".java"))
        .forEach(path -> {
          boolean isTest = isTestFile(path);
          getLog().debug("🔍 Checking file: " + path + " -> isTest: " + isTest);
          if (isTest) {
            try (FileInputStream fis = new FileInputStream(path.toFile())) {
              TypeDef typeDef = Sources.readTypeDefFromStream(fis, context);
              testTypeDefs.put(path.toString(), typeDef);
              DefinitionRepository.getRepository().registerIfAbsent(typeDef);
              String fqn = typeDef.getPackageName() + "." + typeDef.getName();
              getLog().debug("🧪 Test: " + typeDef.getName() + " (" + fqn + ") -> " + path);
            } catch (Exception e) {
              getLog().warn("Failed to analyze test file: " + path, e);
            }
          }
        });

    getLog().debug("Analyzed " + testTypeDefs.size() + " test files");
  }

  private void analyzeSourceFiles() throws IOException {
    getLog().debug("Analyzing source files...");
    if (!sourceDirectory.exists()) {
      getLog().warn("Source directory does not exist: " + sourceDirectory);
      return;
    }

    AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

    Files.walk(sourceDirectory.toPath())
        .filter(path -> path.toString().endsWith(".java"))
        .forEach(path -> {
          try (FileInputStream fis = new FileInputStream(path.toFile())) {
            TypeDef typeDef = Sources.readTypeDefFromStream(fis, context);
            sourceTypeDefs.put(path.toString(), typeDef);
            DefinitionRepository.getRepository().registerIfAbsent(typeDef);
            String fqn = typeDef.getPackageName() + "." + typeDef.getName();
          } catch (Exception e) {
            getLog().debug("Failed to analyze source file: " + path + " - " + e.getMessage());
            // Continue processing other files despite individual failures
          }
        });
  }

  private void mapTestDependencies() {
    for (Map.Entry<String, TypeDef> testEntry : testTypeDefs.entrySet()) {
      String testFile = testEntry.getKey();
      TypeDef testTypeDef = testEntry.getValue();
      Set<String> referencedSources = new HashSet<>();
      Set<String> allFoundDependencies = new HashSet<>();

      String testClassName = extractClassName(testFile);

      // Analyze test methods and their dependencies
      for (Method method : testTypeDef.getMethods()) {
        Set<String> methodDependencies = extractTypeDependencies(method);
        allFoundDependencies.addAll(methodDependencies);
        for (String dependency : methodDependencies) {
          String sourceFile = findSourceFileForType(dependency);
          if (sourceFile != null) {
            referencedSources.add(sourceFile);
          }
        }
      }

      // Analyze test class fields and constructor dependencies
      Set<String> classDependencies = extractClassDependencies(testTypeDef);
      allFoundDependencies.addAll(classDependencies);

      for (String dependency : classDependencies) {
        String sourceFile = findSourceFileForType(dependency);
        if (sourceFile != null) {
          referencedSources.add(sourceFile);
          getLog().debug("    ✅ " + dependency + " -> " + sourceFile);
        } else {
          getLog().debug("    ❌ " + dependency + " -> NOT FOUND in source files");
        }
      }

      if (!referencedSources.isEmpty()) {
        testToSourceMapping.put(testFile, referencedSources);
      }
    }
  }

  private void createMethodSnapshots() {
    for (Map.Entry<String, TypeDef> entry : sourceTypeDefs.entrySet()) {
      String sourceFile = entry.getKey();
      TypeDef typeDef = entry.getValue();

      Map<String, String> methodSnapshots = new HashMap<>();

      for (Method method : typeDef.getMethods()) {
        // Create a simple signature for change detection
        String methodSignature = createMethodSignature(method);
        methodSnapshots.put(method.getName(), methodSignature);
        getLog().debug("📸 Snapshot: " + typeDef.getName() + "." + method.getName() + " -> " + methodSignature.hashCode());
      }

      sourceFileMethodSnapshots.put(sourceFile, methodSnapshots);
    }
  }

  private void buildMethodToTestMapping() {
    getLog().debug("🔧 Building method-to-test mapping for fine-grained test execution...");

    // For each test method, determine which source methods it depends on
    for (Map.Entry<String, TypeDef> testEntry : testTypeDefs.entrySet()) {
      String testFile = testEntry.getKey();
      TypeDef testTypeDef = testEntry.getValue();

      getLog().debug("🔍 Analyzing test class: " + testTypeDef.getFullyQualifiedName());

      for (Method testMethod : testTypeDef.getMethods()) {
        String testMethodKey = testTypeDef.getFullyQualifiedName() + "." + testMethod.getName();

        getLog().debug("  📋 Analyzing test method: " + testMethod.getName());

        // Extract method dependencies from the test method
        Set<String> methodDependencies = extractMethodCallDependencies(testMethod);
        getLog().debug("    🔗 Method call dependencies: " + methodDependencies);

        // Map each source method to this test method
        for (String sourceMethodCall : methodDependencies) {
          sourceMethodToTestMethods.computeIfAbsent(sourceMethodCall, k -> new HashSet<>()).add(testMethodKey);
          getLog().debug("    ✅ Mapped: " + sourceMethodCall + " -> " + testMethodKey);
        }

        // Also add dependencies from class-level analysis
        Set<String> classDependencies = extractClassDependencies(testTypeDef);
        getLog().debug("    🏗️  Class-level dependencies: " + classDependencies);

        for (String dependency : classDependencies) {
          // Find all methods in the dependent class
          for (Map.Entry<String, TypeDef> sourceEntry : sourceTypeDefs.entrySet()) {
            TypeDef sourceTypeDef = sourceEntry.getValue();
            if (sourceTypeDef.getFullyQualifiedName().equals(dependency)) {
              getLog()
                  .debug(
                      "    🎯 Found source class: " + dependency + " with " + sourceTypeDef.getMethods().size() + " methods");
              // Add dependency to all methods in this class
              for (Method sourceMethod : sourceTypeDef.getMethods()) {
                String sourceMethodKey = dependency + "." + sourceMethod.getName();
                sourceMethodToTestMethods.computeIfAbsent(sourceMethodKey, k -> new HashSet<>()).add(testMethodKey);
                getLog().debug("      ✅ Mapped (class-level): " + sourceMethodKey + " -> " + testMethodKey);
              }
            }
          }
        }
      }
    }

    getLog().debug("Built method-to-test mapping with " + sourceMethodToTestMethods.size() + " source method entries");

    // Log complete mapping for debugging
    getLog().debug("🗂️  COMPLETE METHOD-TO-TEST MAPPING:");
    for (Map.Entry<String, Set<String>> entry : sourceMethodToTestMethods.entrySet()) {
      getLog().debug("  📋 " + entry.getKey() + " -> " + entry.getValue().size() + " test method(s): " + entry.getValue());
    }

    if (sourceMethodToTestMethods.isEmpty()) {
      getLog().warn("⚠️  WARNING: No method-to-test mappings were created!");
      getLog().info("This means method-level test execution will always fall back to file-level execution.");
    }
  }

  private String createMethodSignature(Method method) {
    StringBuilder signature = new StringBuilder();

    // Method name and parameters (signature part)
    signature.append(method.getName()).append("(");
    method.getArguments().forEach(arg -> {
      signature.append(arg.getTypeRef()).append(",");
    });
    signature.append("):");
    signature.append(method.getReturnType());

    // Method body (implementation part)
    if (method.getBlock() != null) {
      // Get the actual statements in the block, not just the object reference
      String blockContent = extractBlockContent(method.getBlock());
      signature.append("{").append(blockContent).append("}");
      getLog().debug("      🔧 Method " + method.getName() + " block content: " + blockContent);
    } else {
      getLog().info("      ⚠️  Method " + method.getName() + " has no block");
    }

    String finalSignature = signature.toString();
    getLog().debug("      📋 Signature for " + method.getName() + ": " + finalSignature);
    return finalSignature;
  }

  private String extractBlockContent(Block block) {
    if (block == null || block.getStatements() == null) {
      return "EMPTY_BLOCK";
    }

    StringBuilder content = new StringBuilder();

    // Get the string representation of each statement
    block.getStatements().forEach(statement -> {
      if (statement != null) {
        // Use the statement's string representation
        content.append(statement.toString()).append(";");
      }
    });

    String result = content.toString();
    getLog().debug("        📄 Extracted block content: " + result);
    return result;
  }

  private Set<String> extractTypeDependencies(Method method) {
    Set<String> dependencies = new HashSet<>();

    // Extract return type
    if (method.getReturnType() instanceof ClassRef) {
      dependencies.add(((ClassRef) method.getReturnType()).getFullyQualifiedName());
    }

    // Extract parameter types
    method.getArguments().forEach(prop -> {
      if (prop.getTypeRef() instanceof ClassRef) {
        dependencies.add(((ClassRef) prop.getTypeRef()).getFullyQualifiedName());
      }
    });

    // Extract dependencies from method body by analyzing the block
    if (method.getBlock() != null && method.getBlock().getStatements() != null) {
      method.getBlock().getStatements().forEach(statement -> {
        String statementString = statement.toString();
        // Look for constructor calls like "new Calculator()"
        if (statementString.contains("new ")) {
          extractConstructorDependencies(statementString, dependencies);
        }
        // Look for static method calls and variable declarations
        extractVariableDependencies(statementString, dependencies);
      });
    }

    return dependencies;
  }

  private Set<String> extractMethodCallDependencies(Method method) {
    Set<String> methodCalls = new HashSet<>();

    getLog().debug("    🔬 Analyzing method body for: " + method.getName());

    if (method.getBlock() != null && method.getBlock().getStatements() != null) {
      getLog().debug("      📄 Found " + method.getBlock().getStatements().size() + " statements");

      method.getBlock().getStatements().forEach(statement -> {
        String statementString = statement.toString();
        getLog().debug("      📝 Statement: " + statementString);

        // Look for method calls like "calculator.add(2, 3)" or "Calculator.staticMethod()"
        Set<String> statementCalls = new HashSet<>();
        extractMethodCallsFromStatement(statementString, statementCalls);

        if (!statementCalls.isEmpty()) {
          getLog().debug("        📞 Found method calls: " + statementCalls);
          methodCalls.addAll(statementCalls);
        } else {
          getLog().debug("        ❌ No method calls found in statement");
        }
      });
    } else {
      getLog().debug("      ⚠️  No method block or statements found");
    }

    return methodCalls;
  }

  private void extractMethodCallsFromStatement(String statement, Set<String> methodCalls) {
    getLog().debug("        🔍 Analyzing statement: '" + statement + "'");

    // Pattern for method calls: object.method() or Class.method()
    String[] parts = statement.split("\\.");
    getLog().debug("        🧩 Split into " + parts.length + " parts: " + java.util.Arrays.toString(parts));

    for (int i = 0; i < parts.length - 1; i++) {
      String beforeDot = parts[i].trim();
      String afterDot = parts[i + 1].trim();

      getLog().debug("          🔹 beforeDot: '" + beforeDot + "', afterDot: '" + afterDot + "'");

      // Look for method calls (ends with parentheses)
      int parenIndex = afterDot.indexOf('(');
      if (parenIndex > 0) {
        String methodName = afterDot.substring(0, parenIndex).trim();
        getLog().debug("          🎯 Found potential method call: '" + methodName + "'");

        // Extract the object reference - handle complex expressions
        String objectReference = extractObjectReference(beforeDot);
        getLog().debug("          🔧 Extracted object reference: '" + objectReference + "'");

        // Try to determine if this is a method call on a known type
        String objectType = inferObjectType(objectReference);
        if (objectType != null) {
          String methodCall = objectType + "." + methodName;
          methodCalls.add(methodCall);
          getLog().debug("          ✅ Added method call: " + methodCall);
        } else {
          getLog().debug("          ❌ Could not infer object type for: '" + objectReference + "'");
        }
      } else {
        getLog().debug("          ⚠️  No parentheses found in: '" + afterDot + "'");
      }
    }
  }

  private String extractObjectReference(String beforeDot) {
    // Handle cases like:
    // "Calculator calculator = new Calculator(); calculator" -> "calculator"
    // "assertEquals(5, calculator" -> "calculator"
    // "new Calculator()" -> "Calculator" (constructor call, not variable)

    // Remove common prefixes and get the last identifier
    String cleaned = beforeDot;

    // Remove method calls like "assertEquals(5, "
    int lastComma = cleaned.lastIndexOf(',');
    if (lastComma >= 0) {
      cleaned = cleaned.substring(lastComma + 1).trim();
    }

    // Remove assignment parts like "Calculator calculator = new Calculator(); "
    int lastSemicolon = cleaned.lastIndexOf(';');
    if (lastSemicolon >= 0) {
      cleaned = cleaned.substring(lastSemicolon + 1).trim();
    }

    // Remove parentheses content like "assertEquals(5"
    int lastOpenParen = cleaned.lastIndexOf('(');
    if (lastOpenParen >= 0) {
      cleaned = cleaned.substring(lastOpenParen + 1).trim();
    }

    // Get the last word (variable/class name)
    String[] words = cleaned.split("\\s+");
    if (words.length > 0) {
      String lastWord = words[words.length - 1];
      // Remove any trailing punctuation
      return lastWord.replaceAll("[^a-zA-Z_][^a-zA-Z0-9_]*$", "");
    }

    return cleaned;
  }

  private String inferObjectType(String objectReference) {
    getLog().debug("            🤔 Inferring type for object reference: '" + objectReference + "'");

    if (objectReference == null || objectReference.isEmpty()) {
      getLog().debug("            ❌ Empty object reference");
      return null;
    }

    // Simple heuristic: if it starts with uppercase, it's likely a class (static call)
    // Otherwise, try to infer from variable name patterns
    if (Character.isUpperCase(objectReference.charAt(0))) {
      // Likely a static method call on a class
      getLog().debug("            📊 Uppercase start - treating as class name: " + objectReference);
      String fqn = findFullyQualifiedName(objectReference);
      if (fqn != null) {
        getLog().debug("            ✅ Found FQN: " + fqn);
        return fqn;
      } else {
        getLog().debug("            ⚠️  No FQN found, using class name as-is: " + objectReference);
        return objectReference;
      }
    } else {
      // Instance method call - try to infer type from common patterns
      // For now, use simple naming convention (calculator -> Calculator)
      String typeName = Character.toUpperCase(objectReference.charAt(0)) + objectReference.substring(1);
      getLog().debug("            🔤 Lowercase start - inferring type name: " + objectReference + " -> " + typeName);

      String fqn = findFullyQualifiedName(typeName);
      if (fqn != null) {
        getLog().debug("            ✅ Found FQN for inferred type: " + fqn);
        return fqn;
      } else {
        getLog().debug("            ❌ No FQN found for inferred type: " + typeName);
        return null;
      }
    }
  }

  private void extractConstructorDependencies(String statement, Set<String> dependencies) {
    // Simple pattern matching for "new ClassName()" patterns
    String[] parts = statement.split("new ");
    for (int i = 1; i < parts.length; i++) {
      String part = parts[i].trim();
      int parenIndex = part.indexOf('(');
      if (parenIndex > 0) {
        String className = part.substring(0, parenIndex).trim();
        // Add the class name - assume it's in the same package if no package specified
        if (!className.contains(".") && !className.matches(".*[\\[\\]<>].*")) {
          // For simple class names, try to find the fully qualified name
          String fqn = findFullyQualifiedName(className);
          if (fqn != null) {
            dependencies.add(fqn);
          }
        } else if (className.contains(".")) {
          dependencies.add(className);
        }
      }
    }
  }

  private void extractVariableDependencies(String statement, Set<String> dependencies) {
    // Look for variable declarations like "Calculator calc = ..."
    String[] words = statement.trim().split("\\s+");
    for (int i = 0; i < words.length - 1; i++) {
      String word = words[i];
      String nextWord = words[i + 1];
      // Check if this looks like a type declaration
      if (Character.isUpperCase(word.charAt(0)) &&
          Character.isLowerCase(nextWord.charAt(0)) &&
          nextWord.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
        String fqn = findFullyQualifiedName(word);
        if (fqn != null) {
          dependencies.add(fqn);
        }
      }
    }
  }

  private String findFullyQualifiedName(String className) {
    getLog().debug("              🔎 Looking for FQN of: " + className);

    // First try to find it in our source files
    for (Map.Entry<String, TypeDef> entry : sourceTypeDefs.entrySet()) {
      TypeDef typeDef = entry.getValue();
      getLog().debug("              📋 Checking source type: " + typeDef.getName());

      if (typeDef.getName().equals(className)) {
        String fqn = typeDef.getPackageName() + "." + typeDef.getName();
        getLog().debug("              ✅ Match found: " + className + " -> " + fqn);
        return fqn;
      }
    }

    // If not found in sources, return null (could be a built-in type)
    getLog().debug("              ❌ No match found for: " + className);
    return null;
  }

  private Set<String> extractClassDependencies(TypeDef typeDef) {
    Set<String> dependencies = new HashSet<>();

    // Extract from extends clause
    typeDef.getExtendsList().forEach(ref -> dependencies.add(ref.getFullyQualifiedName()));

    // Extract from implements clause
    typeDef.getImplementsList().forEach(ref -> dependencies.add(ref.getFullyQualifiedName()));

    // Extract from properties/fields
    typeDef.getProperties().forEach(prop -> {
      if (prop.getTypeRef() instanceof ClassRef) {
        dependencies.add(((ClassRef) prop.getTypeRef()).getFullyQualifiedName());
      }
    });

    return dependencies;
  }

  private String findSourceFileForType(String fullyQualifiedName) {
    for (Map.Entry<String, TypeDef> entry : sourceTypeDefs.entrySet()) {
      TypeDef typeDef = entry.getValue();
      String typeFullName = typeDef.getPackageName() + "." + typeDef.getName();
      if (typeFullName.equals(fullyQualifiedName)) {
        return entry.getKey();
      }
    }
    return null;
  }

  private boolean isTestFile(Path path) {
    String fileName = path.getFileName().toString();
    String[] patterns = testIncludes.split(",");

    getLog().debug("🔍 isTestFile - fileName: " + fileName + ", patterns: " + java.util.Arrays.toString(patterns));

    for (String pattern : patterns) {
      boolean matches = matchesPattern(fileName, pattern.trim());
      getLog().debug("  Pattern '" + pattern.trim() + "' matches '" + fileName + "': " + matches);
      if (matches) {
        return true;
      }
    }
    return false;
  }

  private boolean matchesPattern(String fileName, String pattern) {
    getLog().debug("    matchesPattern - fileName: '" + fileName + "', pattern: '" + pattern + "'");

    // Convert glob pattern to regex
    String regex = pattern;

    // Handle **/ prefix (matches any directory structure)
    if (regex.startsWith("**/")) {
      regex = regex.substring(3); // Remove "**/"
      getLog().debug("    Removed **/ prefix, remaining: '" + regex + "'");
    }

    // Convert glob wildcards to regex
    // Escape regex special characters first
    regex = regex.replace(".", "\\.");
    regex = regex.replace("(", "\\(");
    regex = regex.replace(")", "\\)");
    regex = regex.replace("[", "\\[");
    regex = regex.replace("]", "\\]");
    regex = regex.replace("{", "\\{");
    regex = regex.replace("}", "\\}");
    regex = regex.replace("+", "\\+");
    regex = regex.replace("^", "\\^");
    regex = regex.replace("$", "\\$");

    // Convert glob patterns to regex
    regex = regex.replace("*", ".*");
    regex = regex.replace("?", ".");

    getLog().debug("    Final regex: '" + regex + "'");
    boolean result = fileName.matches(regex);
    getLog().debug("    matches result: " + result);
    return result;
  }

  private String extractClassName(String filePath) {
    String fileName = new File(filePath).getName();
    return fileName.substring(0, fileName.lastIndexOf(".java"));
  }

  private void startSmartFileWatching() throws IOException, InterruptedException {
    getLog().info("Starting smart file watcher with impact analysis...");
    getLog().info("Press Ctrl+C to stop watching...");

    // Use Project DSL's watch capability and wait for it to complete
    try {
      sourceProject.allSources().watch(changeSet -> {
        try {
          handleChangeSetWithImpactAnalysis(changeSet);
        } catch (Exception e) {
          getLog().error("Error handling change set", e);
        }
      }).get(); // Wait for the watch to complete (which it never will unless interrupted)
    } catch (java.util.concurrent.ExecutionException e) {
      throw new RuntimeException("Error during file watching", e.getCause());
    }
  }

  private void handleChangeSetWithImpactAnalysis(ChangeSet changeSet) throws MavenInvocationException {
    getLog().info("🚀 Processing change set with impact analysis...");

    // Store for dependency tree display
    this.lastChangeSet = changeSet;

    // Report detected file changes
    reportFileChanges(changeSet);

    // Use ImpactAnalyzer to determine full impact
    ImpactAnalysisResult impact = impactAnalyzer.analyze(changeSet);

    // Store for dependency tree display
    this.lastImpactAnalysis = impact;

    getLog().info(impact.getSummary());

    if (!impact.hasAnyImpact()) {
      getLog().info("No impact detected, skipping test execution");
      return;
    }

    // Try method-level test execution first
    Set<String> affectedTestMethods = findTestMethodsAffectedByImpact(impact);

    if (!affectedTestMethods.isEmpty()) {
      reportAffectedTestMethods(affectedTestMethods);
      runSpecificTestMethods(affectedTestMethods);
    } else {
      // Fall back to file-level test execution
      getLog().info("💡 No method-level dependencies found, falling back to file-level test execution");
      Set<String> testsToRun = findTestsAffectedByImpact(impact);

      if (!testsToRun.isEmpty()) {
        reportAffectedTestFiles(testsToRun);
        runSpecificTests(testsToRun);
      } else {
        getLog().info("❌ No tests affected by changes");
      }
    }
  }

  private void reportFileChanges(ChangeSet changeSet) {
    getLog().info("📁 Changes detected:");

    // Report type changes
    if (changeSet.getOldTypeDef() == null && changeSet.getNewTypeDef() != null) {
      getLog().info("  ➕ Created: " + changeSet.getNewTypeDef().getFullyQualifiedName());
    } else if (changeSet.getOldTypeDef() != null && changeSet.getNewTypeDef() == null) {
      getLog().info("  ❌ Deleted: " + changeSet.getOldTypeDef().getFullyQualifiedName());
    } else if (changeSet.hasChanges()) {
      getLog().info("  ✏️  Modified: " + changeSet.getNewTypeDef().getFullyQualifiedName());
      if (!changeSet.getMethodChanges().isEmpty()) {
        getLog().info("    📋 Method changes: " + changeSet.getMethodChanges().size());
      }
      if (!changeSet.getPropertyChanges().isEmpty()) {
        getLog().info("    🏷️  Property changes: " + changeSet.getPropertyChanges().size());
      }
    }
  }

  private void reportAffectedTestMethods(Set<String> affectedTestMethods) {
    getLog().info("🎯 Found " + affectedTestMethods.size() + " affected test methods:");
    for (String testMethod : affectedTestMethods) {
      getLog().info("  🧪 " + testMethod);
    }
  }

  private void reportAffectedTestFiles(Set<String> affectedTestFiles) {
    getLog().info("📂 Found " + affectedTestFiles.size() + " affected test files:");
    for (String testFile : affectedTestFiles) {
      getLog().info("  📄 " + testFile);
    }
  }

  private Set<String> findTestMethodsAffectedByImpact(ImpactAnalysisResult impact) {
    Set<String> affectedTestMethods = new HashSet<>();

    // Extract test methods directly from the dependency tree instead of using legacy mappings
    Set<MethodReference> affectedMethodRefs = impact.getAffectedMethodReferences();

    for (MethodReference methodRef : affectedMethodRefs) {
      // Check if this is a test method
      if (isTestMethod(methodRef.getMethod())) {
        String testMethodKey = methodRef.getOwningType().getFullyQualifiedName() + "." + methodRef.getMethod().getName();
        affectedTestMethods.add(testMethodKey);
      }
    }

    return affectedTestMethods;
  }

  private Set<String> findTestsAffectedByImpact(ImpactAnalysisResult impact) {
    Set<String> affectedTests = new HashSet<>();

    // Extract test files directly from the dependency tree instead of using legacy mappings
    Set<MethodReference> affectedMethodRefs = impact.getAffectedMethodReferences();

    for (MethodReference methodRef : affectedMethodRefs) {
      // Check if this method belongs to a test class
      if (isTestMethod(methodRef.getMethod())) {
        TypeDef testType = methodRef.getOwningType();

        // Find the test file for this type
        sourceProject.testSources().find(testType.getFullyQualifiedName()).ifPresent(testPath -> {
          affectedTests.add(testPath.toString());
        });
      }
    }

    return affectedTests;
  }

  private void startFileWatching() throws IOException, InterruptedException {
    getLog().info("Starting file watcher...");

    WatchService watchService = FileSystems.getDefault().newWatchService();

    // Register directories for watching
    registerDirectoryRecursively(sourceDirectory.toPath(), watchService);
    registerDirectoryRecursively(testSourceDirectory.toPath(), watchService);

    getLog().info("File watcher started. Press Ctrl+C to stop.");

    while (running.get()) {
      WatchKey key = watchService.take();

      for (WatchEvent<?> event : key.pollEvents()) {
        WatchEvent.Kind<?> kind = event.kind();

        if (kind == OVERFLOW) {
          continue;
        }

        @SuppressWarnings("unchecked")
        WatchEvent<Path> ev = (WatchEvent<Path>) event;
        Path filename = ev.context();
        Path dir = (Path) key.watchable();
        Path fullPath = dir.resolve(filename);

        if (fullPath.toString().endsWith(".java")) {
          enqueueFileChangeEvent(fullPath, kind);
        }
      }

      boolean valid = key.reset();
      if (!valid) {
        break;
      }
    }
  }

  private void registerDirectoryRecursively(Path path, WatchService watchService) throws IOException {
    if (!Files.exists(path)) {
      return;
    }

    Files.walk(path)
        .filter(Files::isDirectory)
        .forEach(dir -> {
          try {
            dir.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
          } catch (IOException e) {
            getLog().warn("Failed to register directory for watching: " + dir, e);
          }
        });
  }

  private void enqueueFileChangeEvent(Path filePath, WatchEvent.Kind<?> kind) {
    String fileKey = filePath.toString();
    FileChangeEvent event = new FileChangeEvent(filePath, kind);

    // Add event to queue
    pendingFileEvents.computeIfAbsent(fileKey, k -> new ConcurrentLinkedQueue<>()).offer(event);

    getLog().debug("📝 Enqueued " + kind + " event for: " + filePath);

    // Schedule processing with debouncing (500ms delay)
    debounceExecutor.schedule(() -> processFileChangeEvents(fileKey), 500, TimeUnit.MILLISECONDS);
  }

  private void processFileChangeEvents(String fileKey) {
    ConcurrentLinkedQueue<FileChangeEvent> events = pendingFileEvents.remove(fileKey);
    if (events == null || events.isEmpty()) {
      return;
    }

    try {
      List<FileChangeEvent> eventList = new ArrayList<>(events);
      eventList.sort((e1, e2) -> Long.compare(e1.timestamp, e2.timestamp));

      getLog().info("🔄 Processing " + eventList.size() + " debounced event(s) for: " + fileKey);

      // Analyze the event sequence to determine the effective change
      EffectiveChange effectiveChange = analyzeEventSequence(eventList);

      getLog().info("📊 Effective change: " + effectiveChange.type + " for " + effectiveChange.filePath);

      // Handle the effective change
      handleEffectiveFileChange(effectiveChange);

    } catch (Exception e) {
      getLog().error("Error processing file change events for: " + fileKey, e);
    }
  }

  private EffectiveChange analyzeEventSequence(List<FileChangeEvent> events) {
    Path filePath = events.get(0).filePath;

    // Count event types
    boolean hasDelete = events.stream().anyMatch(e -> e.kind == ENTRY_DELETE);
    boolean hasCreate = events.stream().anyMatch(e -> e.kind == ENTRY_CREATE);
    boolean hasModify = events.stream().anyMatch(e -> e.kind == ENTRY_MODIFY);

    ChangeType effectiveType;

    if (hasDelete && hasCreate) {
      // Delete + Create = File replacement (common for save operations)
      effectiveType = ChangeType.REPLACE;
      getLog().info("🔄 Detected file replacement (delete→create) for: " + filePath);
    } else if (hasDelete) {
      // Just delete
      effectiveType = ChangeType.DELETE;
      getLog().info("🗑️ Detected file deletion for: " + filePath);
    } else if (hasCreate) {
      // Just create
      effectiveType = ChangeType.CREATE;
      getLog().info("➕ Detected file creation for: " + filePath);
    } else if (hasModify) {
      // Just modify
      effectiveType = ChangeType.MODIFY;
      getLog().info("✏️ Detected file modification for: " + filePath);
    } else {
      // Fallback
      effectiveType = ChangeType.MODIFY;
    }

    return new EffectiveChange(filePath, effectiveType);
  }

  private enum ChangeType {
    CREATE, MODIFY, DELETE, REPLACE
  }

  private static class EffectiveChange {
    final Path filePath;
    final ChangeType type;

    EffectiveChange(Path filePath, ChangeType type) {
      this.filePath = filePath;
      this.type = type;
    }
  }

  private void handleEffectiveFileChange(EffectiveChange change) throws MavenInvocationException {
    if (isSourceFile(change.filePath)) {
      handleEffectiveSourceFileChange(change);
    } else if (isTestFile(change.filePath)) {
      handleEffectiveTestFileChange(change);
    }
  }

  private void handleEffectiveSourceFileChange(EffectiveChange change) throws MavenInvocationException {
    String filePath = change.filePath.toString();

    switch (change.type) {
      case DELETE:
        getLog().info("🗑️ Source file deleted: " + filePath);
        handleSourceFileDeleted(filePath);
        break;

      case CREATE:
        getLog().info("➕ New source file created: " + filePath);
        // For new files, re-analyze and run all affected tests
        handleSourceFileCreated(filePath);
        break;

      case REPLACE:
      case MODIFY:
        getLog().info("🔄 Source file changed: " + filePath);
        handleSourceFileModified(filePath);
        break;
    }
  }

  private void handleEffectiveTestFileChange(EffectiveChange change) throws MavenInvocationException {
    Set<String> changedTest = Set.of(change.filePath.toString());
    runSpecificTests(changedTest);

    // Re-analyze the test file if it was modified/replaced
    if (change.type == ChangeType.MODIFY || change.type == ChangeType.REPLACE) {
      reanalyzeTestFile(change.filePath);
    }
  }

  private boolean isSourceFile(Path path) {
    return path.startsWith(sourceDirectory.toPath()) && path.toString().endsWith(".java");
  }

  private void handleSourceFileChange(Path changedFile, WatchEvent.Kind<?> kind) throws MavenInvocationException {
    String changedFilePath = changedFile.toString();

    getLog().info("🔍 Analyzing method-level changes in: " + changedFile + " (event: " + kind + ")");

    // Handle file deletion
    if (kind == ENTRY_DELETE) {
      getLog().info("🗑️ File deleted, marking all methods as changed");
      handleFileDeleted(changedFilePath);
      return;
    }

    // For ENTRY_CREATE and ENTRY_MODIFY, detect method changes
    Set<String> changedMethods = detectChangedMethods(changedFilePath);

    if (!changedMethods.isEmpty()) {
      getLog().info("🎯 Changed methods: " + changedMethods);

      // Find tests that depend on the changed methods
      Set<String> affectedTestMethods = new HashSet<>();
      for (String changedMethod : changedMethods) {
        Set<String> dependentTests = sourceMethodToTestMethods.get(changedMethod);
        if (dependentTests != null) {
          affectedTestMethods.addAll(dependentTests);
        }
      }

      if (!affectedTestMethods.isEmpty()) {
        getLog()
            .info("⚡ Method-level change affects " + affectedTestMethods.size() + " test method(s): " + affectedTestMethods);
        runSpecificTestMethods(affectedTestMethods);
      } else {
        getLog().info("💡 Changed methods have no direct test dependencies, falling back to file-level detection");
        // Fall back to file-level detection
        handleSourceFileChangeFallback(changedFilePath);
      }
    } else {
      getLog().info("🔄 No method changes detected, running file-level analysis");
      handleSourceFileChangeFallback(changedFilePath);
    }
  }

  private void handleSourceFileChangeFallback(String changedFilePath) throws MavenInvocationException {
    // Find all tests that depend on this source file
    Set<String> affectedTests = new HashSet<>();
    for (Map.Entry<String, Set<String>> entry : testToSourceMapping.entrySet()) {
      if (entry.getValue().contains(changedFilePath)) {
        affectedTests.add(entry.getKey());
      }
    }

    if (!affectedTests.isEmpty()) {
      getLog().info("📂 Source file change affects " + affectedTests.size() + " test file(s)");
      runSpecificTests(affectedTests);
    } else {
      getLog().info("❌ No tests affected by source file change: " + changedFilePath);
    }
  }

  private void handleSourceFileDeleted(String filePath) throws MavenInvocationException {
    // Same logic as before, but cleaner
    handleFileDeleted(filePath);
  }

  private void handleSourceFileCreated(String filePath) throws MavenInvocationException {
    getLog().info("🆕 Analyzing new source file: " + filePath);

    // Re-analyze all files since we have a new source
    try {
      // Add to our source collection and re-map dependencies
      analyzeSourceFiles();
      mapTestDependencies();
      createMethodSnapshots();

      // Run all tests as we don't know what might depend on the new file
      runAllTests();
    } catch (Exception e) {
      getLog().warn("Failed to analyze new source file: " + filePath, e);
      // Fall back to file-level detection
      handleSourceFileChangeFallback(filePath);
    }
  }

  private void handleSourceFileModified(String filePath) throws MavenInvocationException {
    getLog().info("🔧 DETAILED ANALYSIS: Source file modified: " + filePath);

    // Use the existing method-level change detection
    Set<String> changedMethods = detectChangedMethods(filePath);

    getLog().info("🔍 STEP 1: Detected " + changedMethods.size() + " changed methods: " + changedMethods);

    if (!changedMethods.isEmpty()) {
      // Find tests that depend on the changed methods
      Set<String> affectedTestMethods = new HashSet<>();

      getLog().info("🔍 STEP 2: Looking up test dependencies for each changed method...");
      for (String changedMethod : changedMethods) {
        Set<String> dependentTests = sourceMethodToTestMethods.get(changedMethod);
        getLog().info("  📋 Method '" + changedMethod + "' -> " +
            (dependentTests != null ? dependentTests.size() + " test method(s): " + dependentTests : "No Dependencies"));

        if (dependentTests != null) {
          affectedTestMethods.addAll(dependentTests);
        }
      }

      getLog().info("🔍 STEP 3: Total affected test methods: " + affectedTestMethods.size());
      if (!affectedTestMethods.isEmpty()) {
        getLog().info("  ✅ Will run specific test methods: " + affectedTestMethods);
        runSpecificTestMethods(affectedTestMethods);
      } else {
        getLog().info("  ❌ No method-level dependencies found!");
        getLog().info("🔍 STEP 4: Checking available method mappings (showing first 10):");
        sourceMethodToTestMethods.entrySet().stream().limit(10)
            .forEach(entry -> getLog().info("    " + entry.getKey() + " -> " + entry.getValue()));

        getLog().info("💡 Falling back to file-level detection");
        handleSourceFileChangeFallback(filePath);
      }
    } else {
      getLog().info("🔄 No method changes detected, running file-level analysis");
      handleSourceFileChangeFallback(filePath);
    }
  }

  private void handleFileDeleted(String deletedFilePath) throws MavenInvocationException {
    // When a file is deleted, assume all its methods changed and run dependent tests
    Map<String, String> deletedSnapshots = sourceFileMethodSnapshots.get(deletedFilePath);
    if (deletedSnapshots != null) {
      // Find the class name from our existing TypeDef
      TypeDef deletedTypeDef = sourceTypeDefs.get(deletedFilePath);
      if (deletedTypeDef != null) {
        String className = deletedTypeDef.getPackageName() + "." + deletedTypeDef.getName();

        Set<String> affectedTestMethods = new HashSet<>();

        // Mark all methods of the deleted class as changed
        for (String methodName : deletedSnapshots.keySet()) {
          String methodKey = className + "." + methodName;
          Set<String> dependentTests = sourceMethodToTestMethods.get(methodKey);
          if (dependentTests != null) {
            affectedTestMethods.addAll(dependentTests);
          }
        }

        if (!affectedTestMethods.isEmpty()) {
          getLog().info("🔥 File deletion affects " + affectedTestMethods.size() + " test method(s): " + affectedTestMethods);
          runSpecificTestMethods(affectedTestMethods);
        } else {
          // Fall back to file-level detection
          handleSourceFileChangeFallback(deletedFilePath);
        }
      }
    } else {
      // No snapshots, fall back to file-level detection
      handleSourceFileChangeFallback(deletedFilePath);
    }
  }

  private Set<String> detectChangedMethods(String changedFilePath) {
    Set<String> changedMethods = new HashSet<>();

    // Check if file exists first
    File file = new File(changedFilePath);
    if (!file.exists()) {
      getLog().info("📭 File does not exist (yet): " + changedFilePath + ", skipping method change detection");
      return changedMethods;
    }

    try {
      // Re-analyze the changed file
      AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

      try (FileInputStream fis = new FileInputStream(file)) {
        TypeDef newTypeDef = Sources.readTypeDefFromStream(fis, context);
        DefinitionRepository.getRepository().registerIfAbsent(newTypeDef);

        // Get old snapshots
        Map<String, String> oldSnapshots = sourceFileMethodSnapshots.get(changedFilePath);
        if (oldSnapshots == null) {
          getLog().warn("⚠️  No previous snapshots found for " + changedFilePath);
          return changedMethods;
        }

        String className = newTypeDef.getPackageName() + "." + newTypeDef.getName();

        // Compare each method
        for (Method method : newTypeDef.getMethods()) {
          String methodName = method.getName();
          String newSignature = createMethodSignature(method);
          String oldSignature = oldSnapshots.get(methodName);

          getLog().info("🔍 Comparing method: " + methodName);
          getLog().info("  📝 New signature: " + newSignature);
          getLog().info("  📜 Old signature: " + oldSignature);

          if (oldSignature == null) {
            // New method added
            getLog().info("➕ New method detected: " + className + "." + methodName);
            changedMethods.add(className + "." + methodName);
          } else if (!newSignature.equals(oldSignature)) {
            // Method changed
            getLog().info("🔄 Method changed: " + className + "." + methodName);
            getLog().info("  🆚 Signatures differ:");
            getLog().info("    Old: " + oldSignature);
            getLog().info("    New: " + newSignature);
            changedMethods.add(className + "." + methodName);
          } else {
            getLog().info("✅ Method unchanged: " + className + "." + methodName);
          }

          // Update snapshot
          oldSnapshots.put(methodName, newSignature);
        }

        // Check for deleted methods
        Set<String> currentMethods = newTypeDef.getMethods().stream()
            .map(Method::getName)
            .collect(Collectors.toSet());

        for (String oldMethod : new HashSet<>(oldSnapshots.keySet())) {
          if (!currentMethods.contains(oldMethod)) {
            getLog().info("➖ Method deleted: " + className + "." + oldMethod);
            changedMethods.add(className + "." + oldMethod);
            oldSnapshots.remove(oldMethod);
          }
        }

        // Update the TypeDef
        sourceTypeDefs.put(changedFilePath, newTypeDef);

      }
    } catch (Exception e) {
      getLog().warn("Failed to detect method changes in: " + changedFilePath, e);
    }

    return changedMethods;
  }

  private void runSpecificTestMethods(Set<String> testMethods) throws MavenInvocationException {
    if (testMethods.isEmpty()) {
      return;
    }

    // Track that we're running specific methods and which ones
    runningSpecificMethods = true;
    requestedTestMethods.clear();
    requestedTestMethods.addAll(testMethods);

    // Group test methods by test class
    Map<String, Set<String>> testFileToMethods = new HashMap<>();
    for (String testMethod : testMethods) {
      String[] parts = testMethod.split("\\.");
      if (parts.length >= 2) {
        String methodName = parts[parts.length - 1];
        String testFile = testMethod.substring(0, testMethod.lastIndexOf("."));
        testFileToMethods.computeIfAbsent(testFile, k -> new HashSet<>()).add(methodName);
      }
    }

    getLog().info("🎯 Running specific test methods:");

    // Build the test specification for Maven Surefire using ClassName#methodName syntax
    List<String> testSpecs = new ArrayList<>();
    for (Map.Entry<String, Set<String>> entry : testFileToMethods.entrySet()) {
      String testClassFQN = entry.getKey();
      Set<String> methods = entry.getValue();
      // Extract simple class name from fully qualified name (e.g., "io.sundr.examples.CalculatorTest" -> "CalculatorTest")
      String testClassName = testClassFQN.substring(testClassFQN.lastIndexOf('.') + 1);

      getLog().info("  📋 " + testClassName + ": " + methods);

      // Create test specifications for each method: ClassName#methodName
      for (String methodName : methods) {
        testSpecs.add(testClassName + "#" + methodName);
      }
    }

    if (!testSpecs.isEmpty()) {
      // Join all test specifications with comma
      String testSpecification = String.join(",", testSpecs);
      getLog().info("Running test methods: " + testSpecification);
      resetTestCounters();
      runMavenGoal(testGoal, "-Dtest=" + testSpecification);
    }
  }

  private void reanalyzeTestFile(Path testFile) {
    try {
      AdapterContext context = AdapterContext.create(DefinitionRepository.getRepository());

      try (FileInputStream fis = new FileInputStream(testFile.toFile())) {
        TypeDef typeDef = Sources.readTypeDefFromStream(fis, context);
        testTypeDefs.put(testFile.toString(), typeDef);
        DefinitionRepository.getRepository().registerIfAbsent(typeDef);

        // Re-map dependencies for this test
        remapTestDependencies(testFile.toString(), typeDef);

        getLog().debug("Re-analyzed test file: " + testFile);
      }
    } catch (Exception e) {
      getLog().warn("Failed to re-analyze test file: " + testFile, e);
    }
  }

  private void remapTestDependencies(String testFile, TypeDef testTypeDef) {
    Set<String> referencedSources = new HashSet<>();

    // Analyze test methods and their dependencies
    for (Method method : testTypeDef.getMethods()) {
      Set<String> methodDependencies = extractTypeDependencies(method);
      for (String dependency : methodDependencies) {
        String sourceFile = findSourceFileForType(dependency);
        if (sourceFile != null) {
          referencedSources.add(sourceFile);
        }
      }
    }

    // Analyze test class dependencies
    Set<String> classDependencies = extractClassDependencies(testTypeDef);
    for (String dependency : classDependencies) {
      String sourceFile = findSourceFileForType(dependency);
      if (sourceFile != null) {
        referencedSources.add(sourceFile);
      }
    }

    if (!referencedSources.isEmpty()) {
      testToSourceMapping.put(testFile, referencedSources);
    } else {
      testToSourceMapping.remove(testFile);
    }
  }

  private void resetTestCounters() {
    totalTests.set(0);
    passedTests.set(0);
    failedTests.set(0);
    errorTests.set(0);
    skippedTests.set(0);
    currentRunTestCases.clear();
  }

  private void parseTestResults(String testResultLine) {
    // Parse line like: "Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in io.sundr.examples.CalculatorTest"
    try {
      int testsRun = extractNumber(testResultLine, "Tests run:");
      int failures = extractNumber(testResultLine, "Failures:");
      int errors = extractNumber(testResultLine, "Errors:");
      int skipped = extractNumber(testResultLine, "Skipped:");

      // Update current run counters
      totalTests.addAndGet(testsRun);
      failedTests.addAndGet(failures);
      errorTests.addAndGet(errors);
      skippedTests.addAndGet(skipped);
      passedTests.addAndGet(testsRun - failures - errors - skipped);

      // Update session state for this test class
      updateSessionTestResult(testResultLine, testsRun, failures, errors, skipped);

    } catch (Exception e) {
      getLog().debug("Failed to parse test results: " + testResultLine, e);
    }
  }

  private int extractNumber(String line, String prefix) {
    int startIndex = line.indexOf(prefix);
    if (startIndex == -1)
      return 0;

    startIndex += prefix.length();
    int endIndex = line.indexOf(',', startIndex);
    if (endIndex == -1)
      endIndex = line.indexOf(' ', startIndex);
    if (endIndex == -1)
      return 0;

    String numberStr = line.substring(startIndex, endIndex).trim();
    return Integer.parseInt(numberStr);
  }

  private void updateSessionTestResult(String testResultLine, int testsRun, int failures, int errors, int skipped) {
    // Extract test class name from line like: "Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 s -- in io.sundr.examples.CalculatorTest"
    String testClassName = null;
    int inIndex = testResultLine.lastIndexOf(" -- in ");
    if (inIndex > 0) {
      testClassName = testResultLine.substring(inIndex + 7).trim();
    }

    if (testClassName != null) {
      int passed = testsRun - failures - errors - skipped;
      TestClassResult newResult = new TestClassResult(testsRun, passed, failures, errors, skipped);

      // Store or update the result for this test class
      sessionTestResults.put(testClassName, newResult);

      // Recalculate session totals
      recalculateSessionTotals();
    }
  }

  private void recalculateSessionTotals() {
    int total = 0, passed = 0, failed = 0, errors = 0, skipped = 0;

    for (TestClassResult result : sessionTestResults.values()) {
      total += result.total;
      passed += result.passed;
      failed += result.failed;
      errors += result.errors;
      skipped += result.skipped;
    }

    sessionTotalTests.set(total);
    sessionPassedTests.set(passed);
    sessionFailedTests.set(failed);
    sessionErrorTests.set(errors);
    sessionSkippedTests.set(skipped);
  }

  private void parseIndividualTestResults(String mavenOutput) {
    if (mavenOutput == null || mavenOutput.trim().isEmpty()) {
      return;
    }

    String[] lines = mavenOutput.split("\n");
    String currentTestClass = null;

    for (String line : lines) {
      String trimmedLine = line.trim();

      // Look for test class execution start
      if (trimmedLine.contains("Running ") && trimmedLine.contains("Test")) {
        // Extract test class name like "Running io.sundr.examples.CalculatorTest"
        String[] parts = trimmedLine.split("Running ");
        if (parts.length > 1) {
          currentTestClass = parts[1].trim();
          getLog().debug("Found test class: " + currentTestClass);
        }
      }
      // Look for test summary lines like "Tests run: 1, Failures: 0, Errors: 0, Skipped: 0"
      else if (currentTestClass != null && trimmedLine.contains("Tests run:") && trimmedLine.contains("Time elapsed:")) {
        parseTestClassResult(trimmedLine, currentTestClass);
        currentTestClass = null; // Reset for next class
      }
    }
  }

  private void parseTestClassResult(String testResultLine, String testClass) {
    try {
      int testsRun = extractNumber(testResultLine, "Tests run:");
      int failures = extractNumber(testResultLine, "Failures:");
      int errors = extractNumber(testResultLine, "Errors:");
      int skipped = extractNumber(testResultLine, "Skipped:");

      getLog().debug("Parsing class " + testClass + ": " + testsRun + " tests, " + failures + " failures, " + errors
          + " errors, " + skipped + " skipped");

      List<String> testMethodsToUpdate;

      if (runningSpecificMethods) {
        // Only update the specific methods that were requested for this class
        testMethodsToUpdate = requestedTestMethods.stream()
            .filter(methodKey -> methodKey.startsWith(testClass + "."))
            .map(methodKey -> methodKey.substring(methodKey.lastIndexOf('.') + 1))
            .collect(Collectors.toList());

        getLog().debug("Running specific methods for " + testClass + ": " + testMethodsToUpdate);

        if (testMethodsToUpdate.isEmpty()) {
          getLog().debug("No requested methods found for class: " + testClass);
          return;
        }
      } else {
        // Running all methods in the class
        testMethodsToUpdate = getTestMethodsForClass(testClass);

        if (testMethodsToUpdate.isEmpty()) {
          getLog().debug("No test methods found for class: " + testClass);
          return;
        }
      }

      // If all tests passed, mark all relevant methods as passed
      if (failures == 0 && errors == 0) {
        for (String methodName : testMethodsToUpdate) {
          String testKey = testClass + "#" + methodName;
          currentRunTestCases.put(testKey, new TestCaseResult(testClass, methodName, TestStatus.PASSED, null));
          getLog().debug("Recorded passed test case: " + testKey);
        }
      } else {
        // Some tests failed - we can't tell which specific ones without detailed output
        // Mark the first (failures + errors) methods as failed, rest as passed
        int failureCount = failures + errors;
        for (int i = 0; i < testMethodsToUpdate.size(); i++) {
          String methodName = testMethodsToUpdate.get(i);
          String testKey = testClass + "#" + methodName;
          TestStatus status = i < failureCount ? TestStatus.FAILED : TestStatus.PASSED;
          String errorMsg = status == TestStatus.FAILED ? "Test failed (details in Maven output)" : null;
          currentRunTestCases.put(testKey, new TestCaseResult(testClass, methodName, status, errorMsg));
          getLog().debug("Recorded test case: " + testKey + " -> " + status);
        }
      }

      // Handle skipped tests
      if (skipped > 0) {
        // Mark the last 'skipped' number of tests as skipped
        List<String> methodsCopy = new ArrayList<>(testMethodsToUpdate);
        for (int i = Math.max(0, methodsCopy.size() - skipped); i < methodsCopy.size(); i++) {
          String methodName = methodsCopy.get(i);
          String testKey = testClass + "#" + methodName;
          currentRunTestCases.put(testKey, new TestCaseResult(testClass, methodName, TestStatus.SKIPPED, null));
          getLog().debug("Recorded skipped test case: " + testKey);
        }
      }

    } catch (Exception e) {
      getLog().warn("Failed to parse test class result: " + testResultLine, e);
    }
  }

  private List<String> getTestMethodsForClass(String testClassName) {
    // Look up the test methods for this class from our analysis
    for (Map.Entry<String, TypeDef> entry : testTypeDefs.entrySet()) {
      TypeDef typeDef = entry.getValue();
      String fqn = typeDef.getPackageName() + "." + typeDef.getName();

      if (fqn.equals(testClassName)) {
        return typeDef.getMethods().stream()
            .filter(method -> isTestMethod(method))
            .map(method -> method.getName())
            .collect(Collectors.toList());
      }
    }

    // If not found in our analysis, try to extract from the simple class name
    String simpleClassName = testClassName.substring(testClassName.lastIndexOf('.') + 1);
    for (Map.Entry<String, TypeDef> entry : testTypeDefs.entrySet()) {
      TypeDef typeDef = entry.getValue();

      if (typeDef.getName().equals(simpleClassName)) {
        return typeDef.getMethods().stream()
            .filter(method -> isTestMethod(method))
            .map(method -> method.getName())
            .collect(Collectors.toList());
      }
    }

    getLog().debug("Could not find test methods for class: " + testClassName);
    return new ArrayList<>();
  }

  private void initializeTestCaseData() {

    // Initialize all test cases as PENDING before any test runs
    for (Map.Entry<String, TypeDef> entry : testTypeDefs.entrySet()) {
      TypeDef typeDef = entry.getValue();
      String testClassName = typeDef.getPackageName() + "." + typeDef.getName();

      List<String> testMethods = typeDef.getMethods().stream()
          .filter(method -> method.getAnnotations().stream()
              .anyMatch(ann -> ann.getClassRef().getName().equals("Test")))
          .map(method -> method.getName())
          .collect(Collectors.toList());

      for (String methodName : testMethods) {
        String testKey = testClassName + "#" + methodName;
        // Initialize as PENDING status - will be updated after first test run
        sessionTestCases.put(testKey, new TestCaseResult(testClassName, methodName, TestStatus.PENDING, null));
        getLog().debug("Initialized test case: " + testKey);
      }
    }

    getLog().info("Initialized " + sessionTestCases.size() + " test methods");
  }

  private boolean isTestMethod(Method method) {
    // Check for @Test annotation (JUnit 4/5)
    boolean hasTestAnnotation = method.getAnnotations().stream()
        .anyMatch(ann -> {
          String annotationName = ann.getClassRef().getName();
          return "Test".equals(annotationName) || annotationName.endsWith(".Test");
        });

    if (hasTestAnnotation) {
      return true;
    }

    // Check for JUnit 3 style test methods (public void testXxx())
    if (method.getName().startsWith("test") &&
        method.getReturnType().toString().equals("void") &&
        method.getModifiers().isPublic() &&
        method.getArguments().isEmpty()) {
      return true;
    }

    // Check for other test annotations (TestNG, etc.)
    boolean hasOtherTestAnnotations = method.getAnnotations().stream()
        .anyMatch(ann -> {
          String annotationName = ann.getClassRef().getName();
          return annotationName.contains("Test") ||
              "BeforeEach".equals(annotationName) ||
              "AfterEach".equals(annotationName) ||
              "ParameterizedTest".equals(annotationName) ||
              annotationName.endsWith(".ParameterizedTest");
        });

    return hasOtherTestAnnotations;
  }

  private void updateSessionTestCases() {
    // Update session test cases with current run results
    for (TestCaseResult testCase : currentRunTestCases.values()) {
      sessionTestCases.put(testCase.getFullName(), testCase);
    }
  }

  private void showDependencyTree() {
    if (termFrame == null) {
      return;
    }

    try {
      // Save current state before showing dependency tree
      termFrame.saveState();
      termFrame.clearContent();

      if (lastChangeSet == null || lastImpactAnalysis == null) {
        termFrame.println("🔍 No recent changes to analyze. Make a change to see the dependency tree.");
        termFrame.println("");
        termFrame.println("Press any key to return to continuous testing...");
        return;
      }

      termFrame.println("🌳 Dependency Tree - From Last Changes to Affected Tests");
      termFrame.println("");

      // Show changed types
      termFrame.println("\n📁 Changes Types:");
      if (lastChangeSet.getOldTypeDef() == null && lastChangeSet.getNewTypeDef() != null) {
        termFrame.println("  ➕ Created: " + lastChangeSet.getNewTypeDef().getFullyQualifiedName());
      } else if (lastChangeSet.getOldTypeDef() != null && lastChangeSet.getNewTypeDef() == null) {
        termFrame.println("  ❌ Deleted: " + lastChangeSet.getOldTypeDef().getFullyQualifiedName());
      } else if (lastChangeSet.hasChanges()) {
        termFrame.println("  ✏️  Modified: " + lastChangeSet.getNewTypeDef().getFullyQualifiedName());
        if (!lastChangeSet.getMethodChanges().isEmpty()) {
          termFrame.println("    📋 Method changes: " + lastChangeSet.getMethodChanges().size());
          var methodChanges = lastChangeSet.getMethodChanges().toArray();
          for (int i = 0; i < methodChanges.length; i++) {
            var methodChange = methodChanges[i];
            String connector = (i == methodChanges.length - 1) ? "└─" : "├─";
            String changeDesc = formatMethodChange(methodChange);
            termFrame.println("      " + connector + " " + changeDesc);
          }
        }
        if (!lastChangeSet.getPropertyChanges().isEmpty()) {
          termFrame.println("    🏷️  Property changes: " + lastChangeSet.getPropertyChanges().size());
          var propertyChanges = lastChangeSet.getPropertyChanges().toArray();
          for (int i = 0; i < propertyChanges.length; i++) {
            var propertyChange = propertyChanges[i];
            String connector = (i == propertyChanges.length - 1) ? "└─" : "├─";
            termFrame.println("      " + connector + " " + propertyChange.toString());
          }
        }
      }

      // Show dependency tree visualization from impact analysis
      if (lastImpactAnalysis.hasAnyImpact()) {
        termFrame.println("\n🌳 Method Dependency Tree:");
        termFrame.println("");

        // Get the dependency tree visualization
        String treeVisualization = lastImpactAnalysis.getDependencyTreeVisualization();
        if (!treeVisualization.trim().isEmpty()) {
          // Display the tree with proper indentation
          String[] lines = treeVisualization.split("\n");
          for (String line : lines) {
            termFrame.println("  " + line);
          }
        } else {
          termFrame.println("  (No method dependencies found)");
        }

        termFrame.println("");

        // Show test dependencies
        Set<String> affectedTestMethods = findTestMethodsAffectedByImpact(lastImpactAnalysis);
        if (!affectedTestMethods.isEmpty()) {
          termFrame.println("🧪 Affected Test Methods:");
          var testMethodsArray = affectedTestMethods.toArray(new String[0]);
          for (int i = 0; i < testMethodsArray.length; i++) {
            String connector = (i == testMethodsArray.length - 1) ? "└─" : "├─";
            termFrame.println("  " + connector + " " + testMethodsArray[i]);
          }
        } else {
          Set<String> affectedTestFiles = findTestsAffectedByImpact(lastImpactAnalysis);
          if (!affectedTestFiles.isEmpty()) {
            termFrame.println("📄 Affected Test Files:");
            var testFilesArray = affectedTestFiles.toArray(new String[0]);
            for (int i = 0; i < testFilesArray.length; i++) {
              String connector = (i == testFilesArray.length - 1) ? "└─" : "├─";
              termFrame.println("  " + connector + " " + testFilesArray[i]);
            }
          }
        }
      } else {
        termFrame.println("\n❌ No test impact detected");
      }

      termFrame.println("");
      termFrame.println("");
      termFrame.println("Press any key to return to continuous testing...");

      // Wait for key press and restore state
      termFrame.waitForAnyKeyPress((tf, key) -> {
        tf.restoreState();
      });

    } catch (Exception e) {
      getLog().error("Error showing dependency tree", e);
      try {
        // Status updates automatically via header/footer
      } catch (Exception ex) {
        // Ignore
      }
    }
  }

  private String formatMethodChange(Object methodChange) {
    // Use reflection to access the Change object methods safely
    try {
      var changeClass = methodChange.getClass();
      var getChangeTypeMethod = changeClass.getMethod("getChangeType");
      var getOldElementMethod = changeClass.getMethod("getOldElement");
      var getNewElementMethod = changeClass.getMethod("getNewElement");

      var changeType = getChangeTypeMethod.invoke(methodChange);
      var oldElement = getOldElementMethod.invoke(methodChange);
      var newElement = getNewElementMethod.invoke(methodChange);

      switch (changeType.toString()) {
        case "ADDED":
          return "ADDED: " + newElement;
        case "REMOVED":
          return "REMOVED: " + oldElement;
        case "MODIFIED":
          // For methods, show just the signature since implementation changes don't affect the signature
          return "MODIFIED: " + (newElement != null ? newElement : oldElement) + " (implementation changed)";
        default:
          return changeType + ": " + (newElement != null ? newElement : oldElement);
      }
    } catch (Exception e) {
      // Fallback to original toString if reflection fails
      return methodChange.toString();
    }
  }

  /**
   * Returns a summary of the current test session state including individual test case results.
   * This method can be used by external monitoring tools or for debugging.
   */
  public TestSessionSummary getTestSessionSummary() {
    Map<TestStatus, Long> statusCounts = sessionTestCases.values().stream()
        .collect(Collectors.groupingBy(tc -> tc.status, Collectors.counting()));

    return new TestSessionSummary(
        sessionTestCases.size(),
        statusCounts.getOrDefault(TestStatus.PASSED, 0L),
        statusCounts.getOrDefault(TestStatus.FAILED, 0L),
        statusCounts.getOrDefault(TestStatus.ERROR, 0L),
        statusCounts.getOrDefault(TestStatus.SKIPPED, 0L),
        new HashMap<>(sessionTestCases),
        currentRunTestCases.size());
  }

  /**
   * Summary information about the test session
   */
  public static class TestSessionSummary {
    public final long totalTestCases;
    public final long passedTestCases;
    public final long failedTestCases;
    public final long errorTestCases;
    public final long skippedTestCases;
    public final Map<String, TestCaseResult> allTestCases;
    public final int lastRunTestCount;

    public TestSessionSummary(long totalTestCases, long passedTestCases, long failedTestCases,
        long errorTestCases, long skippedTestCases,
        Map<String, TestCaseResult> allTestCases, int lastRunTestCount) {
      this.totalTestCases = totalTestCases;
      this.passedTestCases = passedTestCases;
      this.failedTestCases = failedTestCases;
      this.errorTestCases = errorTestCases;
      this.skippedTestCases = skippedTestCases;
      this.allTestCases = allTestCases;
      this.lastRunTestCount = lastRunTestCount;
    }

    public boolean hasFailures() {
      return failedTestCases > 0 || errorTestCases > 0;
    }

    public List<TestCaseResult> getFailedTests() {
      return allTestCases.values().stream()
          .filter(tc -> tc.status == TestStatus.FAILED || tc.status == TestStatus.ERROR)
          .collect(Collectors.toList());
    }

    @Override
    public String toString() {
      return String.format("TestSession[total=%d, passed=%d, failed=%d, errors=%d, skipped=%d, lastRun=%d]",
          totalTestCases, passedTestCases, failedTestCases, errorTestCases, skippedTestCases, lastRunTestCount);
    }
  }
}
