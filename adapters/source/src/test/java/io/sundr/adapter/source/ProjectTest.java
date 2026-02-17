package io.sundr.adapter.source;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

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
    assertFalse(sourceFiles.isEmpty(), "Source files list should not be empty");

    boolean foundProjectClass = sourceFiles.stream()
        .anyMatch(path -> path.toString().endsWith("Project.java"));
    assertTrue(foundProjectClass, "Should find Project.java in source files");

    boolean allJavaFiles = sourceFiles.stream()
        .allMatch(path -> path.toString().endsWith(".java"));
    assertTrue(allJavaFiles, "All files should be .java files");
  }

  @Test
  public void shouldListJavaTestFiles() {
    List<Path> testFiles = project.listJavaTestFiles();
    assertFalse(testFiles.isEmpty(), "Test files list should not be empty");

    boolean foundProjectTestClass = testFiles.stream()
        .anyMatch(path -> path.toString().endsWith("ProjectTest.java"));
    assertTrue(foundProjectTestClass, "Should find ProjectTest.java in test files");

    boolean allJavaFiles = testFiles.stream()
        .allMatch(path -> path.toString().endsWith(".java"));
    assertTrue(allJavaFiles, "All files should be .java files");
  }

  @Test
  public void shouldListAllJavaFiles() {
    List<Path> allFiles = project.listJavaFiles();
    List<Path> sourceFiles = project.listJavaSourceFiles();
    List<Path> testFiles = project.listJavaTestFiles();

    assertFalse(allFiles.isEmpty(), "All files list should not be empty");

    int expectedSize = sourceFiles.size() + testFiles.size();
    assertTrue(allFiles.size() >= expectedSize,
        "All files should contain both source and test files");

    boolean foundProjectClass = allFiles.stream()
        .anyMatch(path -> path.toString().endsWith("Project.java"));
    assertTrue(foundProjectClass, "Should find Project.java in all files");

    boolean foundProjectTestClass = allFiles.stream()
        .anyMatch(path -> path.toString().endsWith("ProjectTest.java"));
    assertTrue(foundProjectTestClass, "Should find ProjectTest.java in all files");

    boolean allJavaFiles = allFiles.stream()
        .allMatch(path -> path.toString().endsWith(".java"));
    assertTrue(allJavaFiles, "All files should be .java files");
  }

  @Test
  public void shouldCreateProjectWithFactory() {
    Project defaultProject = Project.getProject();
    assertNotNull(defaultProject, "Default project should not be null");
    assertNotNull(defaultProject.getModuleRoot(), "Default project should have module root");
  }

  @Test
  public void shouldCreateProjectWithCustomRoot() {
    Path customRoot = Paths.get(System.getProperty("user.dir"));
    Project customProject = Project.getProject(customRoot);
    assertNotNull(customProject, "Custom project should not be null");
    assertEquals(customRoot.toFile(), customProject.getModuleRoot(),
        "Custom project should use provided root");
  }

  // =================================================================
  // Fluent API Tests
  // =================================================================

  @Test
  public void testSourcesWithoutFilters() {
    // Test that sources() without filters returns all source files
    List<Path> sourcesViaFluent = project.sources().list();
    List<Path> sourcesViaDirect = project.listJavaSourceFiles();

    assertEquals(sourcesViaDirect.size(), sourcesViaFluent.size(),
        "Fluent API should return same files as direct method");

    Set<Path> directSet = sourcesViaDirect.stream().collect(Collectors.toSet());
    Set<Path> fluentSet = sourcesViaFluent.stream().collect(Collectors.toSet());

    assertEquals(directSet, fluentSet, "Fluent API should return same files as direct method");
  }

  @Test
  public void testTestSourcesWithoutFilters() {
    // Test that testSources() without filters returns all test files
    List<Path> testSourcesViaFluent = project.testSources().list();
    List<Path> testSourcesViaDirect = project.listJavaTestFiles();

    assertEquals(testSourcesViaDirect.size(), testSourcesViaFluent.size(),
        "Fluent API should return same test files as direct method");

    Set<Path> directSet = testSourcesViaDirect.stream().collect(Collectors.toSet());
    Set<Path> fluentSet = testSourcesViaFluent.stream().collect(Collectors.toSet());

    assertEquals(directSet, fluentSet, "Fluent API should return same test files as direct method");
  }

  @Test
  public void testAllSourcesWithoutFilters() {
    // Test that allSources() without filters returns all files
    List<Path> allSourcesViaFluent = project.allSources().list();
    List<Path> allSourcesViaDirect = project.listJavaFiles();

    assertEquals(allSourcesViaDirect.size(), allSourcesViaFluent.size(),
        "Fluent API should return same all files as direct method");

    Set<Path> directSet = allSourcesViaDirect.stream().collect(Collectors.toSet());
    Set<Path> fluentSet = allSourcesViaFluent.stream().collect(Collectors.toSet());

    assertEquals(directSet, fluentSet, "Fluent API should return same all files as direct method");
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
      assertTrue(fileName.endsWith("Test.java") || fileName.endsWith("Tests.java"),
          "File " + fileName + " should match test patterns");
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
      assertTrue(!fileName.endsWith("Test.java") && !fileName.endsWith("Tests.java"),
          "File " + fileName + " should not match excluded test patterns");
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
      assertTrue(fileName.endsWith(".java"), "File " + fileName + " should be Java file");
      assertTrue(!fileName.endsWith("Test.java") && !fileName.endsWith("Tests.java"),
          "File " + fileName + " should not be test file");
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
    assertTrue(result1 != null, "Sources chaining should work");
    assertTrue(result2 != null, "Test sources chaining should work");
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
      assertTrue(fileName.endsWith("Test.java"),
          "File " + fileName + " should match glob pattern");
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
    assertEquals(direct.size(), withEmptyInclude.size(), "Empty include should return all files");
    assertEquals(direct.size(), withEmptyExclude.size(), "Empty exclude should return all files");
    assertEquals(direct.size(), withBothEmpty.size(), "Both empty should return all files");
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
    assertEquals(direct.size(), withNullInclude.size(), "Null include should return all files");
    assertEquals(direct.size(), withNullExclude.size(), "Null exclude should return all files");
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
    assertTrue(!allTestFiles.isEmpty(), "Should find some test files");
    assertTrue(!nonGeneratedSources.isEmpty(), "Should find some non-generated sources");
    assertTrue(!cleanSources.isEmpty(), "Should find some clean sources");

    // Clean sources should have fewer files than all sources
    List<Path> allSources = project.allSources().list();
    assertTrue(cleanSources.size() <= allSources.size(),
        "Clean sources should be subset of all sources");
  }

  // =================================================================
  // Find Method Tests
  // =================================================================

  @Test
  public void testFindWithoutFilters() {
    // Test finding classes in unfiltered sets
    Optional<Path> projectClass = project.sources().find("io.sundr.adapter.source.Project");
    assertTrue(projectClass.isPresent(), "Should find Project class in sources");

    Optional<Path> projectTestClass = project.testSources().find("io.sundr.adapter.source.ProjectTest");
    assertTrue(projectTestClass.isPresent(), "Should find ProjectTest class in test sources");

    Optional<Path> projectClassInAll = project.allSources().find("io.sundr.adapter.source.Project");
    assertTrue(projectClassInAll.isPresent(), "Should find Project class in all sources");
  }

  @Test
  public void testFindWithIncludeFilters() {
    // Test finding classes when they match include patterns
    Optional<Path> projectClass = project.sources()
        .including("*Project.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue(projectClass.isPresent(), "Should find Project class when it matches include pattern");

    // Test not finding classes when they don't match include patterns
    Optional<Path> projectClassExcluded = project.sources()
        .including("*Test.java")
        .find("io.sundr.adapter.source.Project");
    assertFalse(projectClassExcluded.isPresent(), "Should not find Project class when it doesn't match include pattern");
  }

  @Test
  public void testFindWithExcludeFilters() {
    // Test not finding classes when they match exclude patterns
    Optional<Path> projectTestExcluded = project.testSources()
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertFalse(projectTestExcluded.isPresent(), "Should not find ProjectTest when it matches exclude pattern");

    // Test finding classes when they don't match exclude patterns
    Optional<Path> projectClass = project.sources()
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue(projectClass.isPresent(), "Should find Project class when it doesn't match exclude pattern");
  }

  @Test
  public void testFindWithCombinedFilters() {
    // Test finding with both include and exclude filters
    Optional<Path> result = project.allSources()
        .including("*.java")
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue(result.isPresent(), "Should find Project class with combined filters");

    Optional<Path> excludedResult = project.allSources()
        .including("*.java")
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertFalse(excludedResult.isPresent(), "Should not find ProjectTest with exclude filter");
  }

  @Test
  public void testFindBySimpleClassName() {
    // Test finding by simple class name (no package)
    Optional<Path> result = project.sources().find("Project");
    assertTrue(result.isPresent(), "Should find class by simple name");

    String resultPath = result.get().toString();
    assertTrue(resultPath.endsWith("Project.java"), "Found path should end with Project.java");
  }

  @Test
  public void testFindNonExistentClass() {
    // Test finding classes that don't exist
    Optional<Path> nonExistent = project.allSources().find("com.example.NonExistentClass");
    assertFalse(nonExistent.isPresent(), "Should not find non-existent class");
  }

  @Test
  public void testFindWithNullAndEmptyInput() {
    // Test null input
    Optional<Path> nullResult = project.sources().find((String) null);
    assertFalse(nullResult.isPresent(), "Should return empty for null input");

    // Test empty input
    Optional<Path> emptyResult = project.sources().find("");
    assertFalse(emptyResult.isPresent(), "Should return empty for empty input");

    // Test whitespace input
    Optional<Path> whitespaceResult = project.sources().find("   ");
    assertFalse(whitespaceResult.isPresent(), "Should return empty for whitespace input");
  }

  @Test
  public void testFindInDifferentSourceTypes() {
    // Test that find respects source type boundaries

    // Project should only be found in sources, not test sources
    Optional<Path> projectInSources = project.sources().find("io.sundr.adapter.source.Project");
    Optional<Path> projectInTests = project.testSources().find("io.sundr.adapter.source.Project");

    assertTrue(projectInSources.isPresent(), "Project should be found in sources");
    assertFalse(projectInTests.isPresent(), "Project should not be found in test sources");

    // ProjectTest should only be found in test sources, not sources
    Optional<Path> testInSources = project.sources().find("io.sundr.adapter.source.ProjectTest");
    Optional<Path> testInTests = project.testSources().find("io.sundr.adapter.source.ProjectTest");

    assertFalse(testInSources.isPresent(), "ProjectTest should not be found in sources");
    assertTrue(testInTests.isPresent(), "ProjectTest should be found in test sources");

    // Both should be found in all sources
    Optional<Path> projectInAll = project.allSources().find("io.sundr.adapter.source.Project");
    Optional<Path> testInAll = project.allSources().find("io.sundr.adapter.source.ProjectTest");

    assertTrue(projectInAll.isPresent(), "Project should be found in all sources");
    assertTrue(testInAll.isPresent(), "ProjectTest should be found in all sources");
  }

  @Test
  public void testFindUsageExamples() {
    // Demonstrate typical usage patterns with find

    // Find a specific test class among all test files
    Optional<Path> specificTest = project.testSources()
        .including("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertTrue(specificTest.isPresent(), "Should find specific test class");

    // Find a source class while excluding generated files
    Optional<Path> sourceClass = project.sources()
        .excluding("*Generated.java", "*Fluent.java", "*Builder.java")
        .find("io.sundr.adapter.source.Project");
    assertTrue(sourceClass.isPresent(), "Should find source class while excluding generated");

    // Try to find a class that would be excluded
    Optional<Path> excludedClass = project.testSources()
        .excluding("*Test.java")
        .find("io.sundr.adapter.source.ProjectTest");
    assertFalse(excludedClass.isPresent(), "Should not find excluded test class");
  }
}
