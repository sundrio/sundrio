package io.sundr.adapter.source;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

public class ProjectTest {

  private final Project project = Project.getProject();

  @Test
  public void shouldFindModuleRoot() {
    assertTrue(project.getModuleRoot().exists());
  }

  @Test
  public void shouldFindJavaRoot() {
    assertTrue(project.getSrcMainJava().exists());
    assertTrue(project.getSrcTestJava().exists());
  }

  @Test
  public void shoudFindClassByFQCN() {
    assertTrue(project.findJavaSourceFile("io.sundr.adapter.source.Project").isPresent());
  }

  @Test
  public void shoudFindClassByName() {
    assertTrue(project.findJavaSourceFile("Project").isPresent());
  }

  @Test
  public void shouldListJavaSourceFiles() {
    List<Path> sourceFiles = project.listJavaSourceFiles();
    assertFalse("Source files list should not be empty", sourceFiles.isEmpty());

    boolean foundProjectClass = sourceFiles.stream()
        .anyMatch(path -> path.toString().endsWith("Project.java"));
    assertTrue("Should find Project.java in source files", foundProjectClass);

    boolean allJavaFiles = sourceFiles.stream()
        .allMatch(path -> path.toString().endsWith(".java"));
    assertTrue("All files should be .java files", allJavaFiles);
  }

  @Test
  public void shouldListJavaTestFiles() {
    List<Path> testFiles = project.listJavaTestFiles();
    assertFalse("Test files list should not be empty", testFiles.isEmpty());

    boolean foundProjectTestClass = testFiles.stream()
        .anyMatch(path -> path.toString().endsWith("ProjectTest.java"));
    assertTrue("Should find ProjectTest.java in test files", foundProjectTestClass);

    boolean allJavaFiles = testFiles.stream()
        .allMatch(path -> path.toString().endsWith(".java"));
    assertTrue("All files should be .java files", allJavaFiles);
  }

  @Test
  public void shouldListAllJavaFiles() {
    List<Path> allFiles = project.listJavaFiles();
    List<Path> sourceFiles = project.listJavaSourceFiles();
    List<Path> testFiles = project.listJavaTestFiles();

    assertFalse("All files list should not be empty", allFiles.isEmpty());

    int expectedSize = sourceFiles.size() + testFiles.size();
    assertTrue("All files should contain both source and test files",
        allFiles.size() >= expectedSize);

    boolean foundProjectClass = allFiles.stream()
        .anyMatch(path -> path.toString().endsWith("Project.java"));
    assertTrue("Should find Project.java in all files", foundProjectClass);

    boolean foundProjectTestClass = allFiles.stream()
        .anyMatch(path -> path.toString().endsWith("ProjectTest.java"));
    assertTrue("Should find ProjectTest.java in all files", foundProjectTestClass);

    boolean allJavaFiles = allFiles.stream()
        .allMatch(path -> path.toString().endsWith(".java"));
    assertTrue("All files should be .java files", allJavaFiles);
  }

  @Test
  public void shouldCreateProjectWithFactory() {
    Project defaultProject = Project.getProject();
    assertNotNull("Default project should not be null", defaultProject);
    assertNotNull("Default project should have module root", defaultProject.getModuleRoot());
  }

  @Test
  public void shouldCreateProjectWithCustomRoot() {
    Path customRoot = Paths.get(System.getProperty("user.dir"));
    Project customProject = Project.getProject(customRoot);
    assertNotNull("Custom project should not be null", customProject);
    assertEquals("Custom project should use provided root",
        customRoot.toFile(), customProject.getModuleRoot());
  }

  // =================================================================
  // Fluent API Tests
  // =================================================================

  @Test
  public void testSourcesWithoutFilters() {
    // Test that sources() without filters returns all source files
    List<Path> sourcesViaFluent = project.sources().list();
    List<Path> sourcesViaDirect = project.listJavaSourceFiles();

    assertEquals("Fluent API should return same files as direct method",
        sourcesViaDirect.size(), sourcesViaFluent.size());

    Set<Path> directSet = sourcesViaDirect.stream().collect(Collectors.toSet());
    Set<Path> fluentSet = sourcesViaFluent.stream().collect(Collectors.toSet());

    assertEquals("Fluent API should return same files as direct method", directSet, fluentSet);
  }

  @Test
  public void testTestSourcesWithoutFilters() {
    // Test that testSources() without filters returns all test files
    List<Path> testSourcesViaFluent = project.testSources().list();
    List<Path> testSourcesViaDirect = project.listJavaTestFiles();

    assertEquals("Fluent API should return same test files as direct method",
        testSourcesViaDirect.size(), testSourcesViaFluent.size());

    Set<Path> directSet = testSourcesViaDirect.stream().collect(Collectors.toSet());
    Set<Path> fluentSet = testSourcesViaFluent.stream().collect(Collectors.toSet());

    assertEquals("Fluent API should return same test files as direct method", directSet, fluentSet);
  }

  @Test
  public void testAllSourcesWithoutFilters() {
    // Test that allSources() without filters returns all files
    List<Path> allSourcesViaFluent = project.allSources().list();
    List<Path> allSourcesViaDirect = project.listJavaFiles();

    assertEquals("Fluent API should return same all files as direct method",
        allSourcesViaDirect.size(), allSourcesViaFluent.size());

    Set<Path> directSet = allSourcesViaDirect.stream().collect(Collectors.toSet());
    Set<Path> fluentSet = allSourcesViaFluent.stream().collect(Collectors.toSet());

    assertEquals("Fluent API should return same all files as direct method", directSet, fluentSet);
  }

  @Test
  public void testIncludingPatterns() {
    // Test including only specific patterns
    List<Path> testFiles = project.allSources()
        .including("*Test.java", "*Tests.java")
        .list();

    // All returned files should match the patterns
    for (Path file : testFiles) {
      String fileName = file.getFileName().toString();
      assertTrue("File " + fileName + " should match test patterns",
          fileName.endsWith("Test.java") || fileName.endsWith("Tests.java"));
    }
  }

  @Test
  public void testExcludingPatterns() {
    // Test excluding specific patterns
    List<Path> nonTestFiles = project.allSources()
        .excluding("*Test.java", "*Tests.java")
        .list();

    // None of the returned files should match the excluded patterns
    for (Path file : nonTestFiles) {
      String fileName = file.getFileName().toString();
      assertTrue("File " + fileName + " should not match excluded test patterns",
          !fileName.endsWith("Test.java") && !fileName.endsWith("Tests.java"));
    }
  }

  @Test
  public void testIncludingAndExcludingPatterns() {
    // Test combining including and excluding
    List<Path> filteredFiles = project.allSources()
        .including("*.java") // Include all Java files
        .excluding("*Test.java", "*Tests.java") // But exclude test files
        .list();

    // All returned files should be Java files but not test files
    for (Path file : filteredFiles) {
      String fileName = file.getFileName().toString();
      assertTrue("File " + fileName + " should be Java file", fileName.endsWith(".java"));
      assertTrue("File " + fileName + " should not be test file",
          !fileName.endsWith("Test.java") && !fileName.endsWith("Tests.java"));
    }
  }

  @Test
  public void testFluentApiChaining() {
    // Test that the fluent API supports method chaining
    List<Path> result1 = project.sources()
        .including("*.java")
        .excluding("*Fluent.java")
        .list();

    List<Path> result2 = project.testSources()
        .excluding("*IntegrationTest.java")
        .including("*Test.java")
        .list();

    // Just verify the chaining works (results are lists)
    assertTrue("Sources chaining should work", result1 != null);
    assertTrue("Test sources chaining should work", result2 != null);
  }

  @Test
  public void testGlobPatterns() {
    // Test glob patterns work correctly
    List<Path> globResults = project.allSources()
        .including("**/*Test.java") // Directory traversal glob
        .list();

    // All results should end with Test.java
    for (Path file : globResults) {
      String fileName = file.getFileName().toString();
      assertTrue("File " + fileName + " should match glob pattern",
          fileName.endsWith("Test.java"));
    }
  }

  @Test
  public void testEmptyFilters() {
    // Test that empty filters work as expected
    List<Path> withEmptyInclude = project.sources()
        .including() // Empty include patterns
        .list();

    List<Path> withEmptyExclude = project.sources()
        .excluding() // Empty exclude patterns
        .list();

    List<Path> withBothEmpty = project.sources()
        .including()
        .excluding()
        .list();

    List<Path> direct = project.listJavaSourceFiles();

    // All should return the same as the direct method
    assertEquals("Empty include should return all files", direct.size(), withEmptyInclude.size());
    assertEquals("Empty exclude should return all files", direct.size(), withEmptyExclude.size());
    assertEquals("Both empty should return all files", direct.size(), withBothEmpty.size());
  }

  @Test
  public void testNullFilters() {
    // Test that null filters are handled gracefully
    List<Path> withNullInclude = project.sources()
        .including((String[]) null)
        .list();

    List<Path> withNullExclude = project.sources()
        .excluding((String[]) null)
        .list();

    List<Path> direct = project.listJavaSourceFiles();

    // All should return the same as the direct method
    assertEquals("Null include should return all files", direct.size(), withNullInclude.size());
    assertEquals("Null exclude should return all files", direct.size(), withNullExclude.size());
  }

  @Test
  public void testUsageExamples() {
    // Demonstrate typical usage patterns

    // Find all test files
    List<Path> allTestFiles = project.testSources()
        .including("*Test.java", "*Tests.java", "*TestCase.java")
        .list();

    // Find source files but exclude generated ones
    List<Path> nonGeneratedSources = project.sources()
        .excluding("*Generated.java", "*Fluent.java", "*Builder.java")
        .list();

    // Find all sources except test and generated files
    List<Path> cleanSources = project.allSources()
        .including("*.java")
        .excluding("*Test.java", "*Tests.java", "*Generated.java")
        .list();

    // Verify results make sense
    assertTrue("Should find some test files", !allTestFiles.isEmpty());
    assertTrue("Should find some non-generated sources", !nonGeneratedSources.isEmpty());
    assertTrue("Should find some clean sources", !cleanSources.isEmpty());

    // Clean sources should have fewer files than all sources
    List<Path> allSources = project.allSources().list();
    assertTrue("Clean sources should be subset of all sources",
        cleanSources.size() <= allSources.size());
  }

  // =================================================================
  // Find Method Tests
  // =================================================================

  @Test
  public void testFindWithoutFilters() {
    // Test finding classes in unfiltered sets
    Optional<Path> projectClass = project.sources().find("io.sundr.adapter.source.Project");
    assertTrue("Should find Project class in sources", projectClass.isPresent());

    Optional<Path> projectTestClass = project.testSources().find("io.sundr.adapter.source.ProjectTest");
    assertTrue("Should find ProjectTest class in test sources", projectTestClass.isPresent());

    Optional<Path> projectClassInAll = project.allSources().find("io.sundr.adapter.source.Project");
    assertTrue("Should find Project class in all sources", projectClassInAll.isPresent());
  }

  @Test
  public void testFindWithIncludeFilters() {
    // Test finding classes when they match include patterns
    Optional<Path> projectClass = project.sources()
        .including("*Project.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue("Should find Project class when it matches include pattern", projectClass.isPresent());

    // Test not finding classes when they don't match include patterns
    Optional<Path> projectClassExcluded = project.sources()
        .including("*Test.java")
        .find("io.sundr.adapter.source.Project");
    assertFalse("Should not find Project class when it doesn't match include pattern", projectClassExcluded.isPresent());
  }

  @Test
  public void testFindWithExcludeFilters() {
    // Test not finding classes when they match exclude patterns
    Optional<Path> projectTestExcluded = project.testSources()
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertFalse("Should not find ProjectTest when it matches exclude pattern", projectTestExcluded.isPresent());

    // Test finding classes when they don't match exclude patterns
    Optional<Path> projectClass = project.sources()
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue("Should find Project class when it doesn't match exclude pattern", projectClass.isPresent());
  }

  @Test
  public void testFindWithCombinedFilters() {
    // Test finding with both include and exclude filters
    Optional<Path> result = project.allSources()
        .including("*.java")
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue("Should find Project class with combined filters", result.isPresent());

    Optional<Path> excludedResult = project.allSources()
        .including("*.java")
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertFalse("Should not find ProjectTest with exclude filter", excludedResult.isPresent());
  }

  @Test
  public void testFindBySimpleClassName() {
    // Test finding by simple class name (no package)
    Optional<Path> result = project.sources().find("Project");
    assertTrue("Should find class by simple name", result.isPresent());

    String resultPath = result.get().toString();
    assertTrue("Found path should end with Project.java", resultPath.endsWith("Project.java"));
  }

  @Test
  public void testFindNonExistentClass() {
    // Test finding classes that don't exist
    Optional<Path> nonExistent = project.allSources().find("com.example.NonExistentClass");
    assertFalse("Should not find non-existent class", nonExistent.isPresent());
  }

  @Test
  public void testFindWithNullAndEmptyInput() {
    // Test null input
    Optional<Path> nullResult = project.sources().find(null);
    assertFalse("Should return empty for null input", nullResult.isPresent());

    // Test empty input
    Optional<Path> emptyResult = project.sources().find("");
    assertFalse("Should return empty for empty input", emptyResult.isPresent());

    // Test whitespace input
    Optional<Path> whitespaceResult = project.sources().find("   ");
    assertFalse("Should return empty for whitespace input", whitespaceResult.isPresent());
  }

  @Test
  public void testFindInDifferentSourceTypes() {
    // Test that find respects source type boundaries

    // Project should only be found in sources, not test sources
    Optional<Path> projectInSources = project.sources().find("io.sundr.adapter.source.Project");
    Optional<Path> projectInTests = project.testSources().find("io.sundr.adapter.source.Project");

    assertTrue("Project should be found in sources", projectInSources.isPresent());
    assertFalse("Project should not be found in test sources", projectInTests.isPresent());

    // ProjectTest should only be found in test sources, not sources
    Optional<Path> testInSources = project.sources().find("io.sundr.adapter.source.ProjectTest");
    Optional<Path> testInTests = project.testSources().find("io.sundr.adapter.source.ProjectTest");

    assertFalse("ProjectTest should not be found in sources", testInSources.isPresent());
    assertTrue("ProjectTest should be found in test sources", testInTests.isPresent());

    // Both should be found in all sources
    Optional<Path> projectInAll = project.allSources().find("io.sundr.adapter.source.Project");
    Optional<Path> testInAll = project.allSources().find("io.sundr.adapter.source.ProjectTest");

    assertTrue("Project should be found in all sources", projectInAll.isPresent());
    assertTrue("ProjectTest should be found in all sources", testInAll.isPresent());
  }

  @Test
  public void testFindUsageExamples() {
    // Demonstrate typical usage patterns with find

    // Find a specific test class among all test files
    Optional<Path> specificTest = project.testSources()
        .including("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertTrue("Should find specific test class", specificTest.isPresent());

    // Find a source class while excluding generated files
    Optional<Path> sourceClass = project.sources()
        .excluding("*Generated.java", "*Fluent.java", "*Builder.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue("Should find source class while excluding generated", sourceClass.isPresent());

    // Try to find a class that would be excluded
    Optional<Path> excludedClass = project.testSources()
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertFalse("Should not find excluded test class", excludedClass.isPresent());
  }
}
