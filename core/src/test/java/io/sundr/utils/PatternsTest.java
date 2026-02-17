/**
 * Copyright 2015 The original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
**/

package io.sundr.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class PatternsTest {

  @Test
  public void testExcludes() {
    assertFalse(Patterns.isExcluded("abc"));
    assertTrue(Patterns.isExcluded("abc", "abc"));
    assertTrue(Patterns.isExcluded("abc", "abc", "def", "ghi"));
    assertTrue(Patterns.isExcluded("abc", "a.*"));
    assertFalse(Patterns.isExcluded("abc", "ba.*"));
    assertTrue(Patterns.isExcluded("io.sundr.model.TypeDefFluent", ".*Fluent"));
  }

  @Test
  public void testIncludes() {
    assertTrue(Patterns.isIncluded("abc"));
    assertTrue(Patterns.isIncluded("abc", "abc"));
    assertTrue(Patterns.isIncluded("abc", "abc", "def", "ghi"));
    assertTrue(Patterns.isIncluded("abc", "a.*"));
    assertFalse(Patterns.isIncluded("abc", "ba.*"));
    assertTrue(Patterns.isIncluded("io.sundr.model.TypeDefFluent", ".*Fluent"));
  }

  @Test
  public void testGlobPatternDetection() {
    // Should detect as glob patterns
    assertTrue(Patterns.isGlobPattern("*.java"), "*.java should be detected as glob");
    assertTrue(Patterns.isGlobPattern("**/*Test.java"), "**/*Test.java should be detected as glob");
    assertTrue(Patterns.isGlobPattern("Test*.java"), "Test*.java should be detected as glob");
    assertTrue(Patterns.isGlobPattern("**/Test?.java"), "**/Test?.java should be detected as glob");
    assertTrue(Patterns.isGlobPattern("src/main/**/*.xml"), "src/main/**/*.xml should be detected as glob");

    // Should detect as regex patterns
    assertFalse(Patterns.isGlobPattern(".*Test"), ".*Test should be detected as regex");
    assertFalse(Patterns.isGlobPattern("Test[0-9]+"), "Test[0-9]+ should be detected as regex");
    assertFalse(Patterns.isGlobPattern("^io\\.sundr\\..*"), "^io\\.sundr\\..* should be detected as regex");
    assertFalse(Patterns.isGlobPattern("(Test|Spec).*"), "(Test|Spec).* should be detected as regex");
    assertFalse(Patterns.isGlobPattern("Test{1,3}"), "Test{1,3} should be detected as regex");

    // Edge cases - should default to regex for backward compatibility
    assertFalse(Patterns.isGlobPattern("Test"), "plain string should default to regex");
    assertFalse(Patterns.isGlobPattern("io.sundr.Test"), "string with dots should default to regex");
  }

  @Test
  public void testGlobToRegex() {
    // Basic wildcards
    assertEquals("[^/]*\\.java", Patterns.globToRegex("*.java"));
    assertEquals("Test[^/]*\\.java", Patterns.globToRegex("Test*.java"));
    assertEquals("Test[^/]\\.java", Patterns.globToRegex("Test?.java"));

    // Directory traversal
    assertEquals(".*Test[^/]*\\.java", Patterns.globToRegex("**/Test*.java"));
    assertEquals(".*[^/]*Test\\.java", Patterns.globToRegex("**/*Test.java"));

    // Complex patterns
    assertEquals("src/main/java/.*/[^/]*\\.java", Patterns.globToRegex("src/main/java/**/*.java"));

    // Special characters should be escaped
    assertEquals("Test\\(1\\)\\.java", Patterns.globToRegex("Test(1).java"));
    assertEquals("Test\\[1\\]\\.java", Patterns.globToRegex("Test[1].java"));
  }

  @Test
  public void testGlobMatching() {
    // File extension patterns
    assertTrue(Patterns.matchesGlob("Test.java", "*.java"));
    assertTrue(Patterns.matchesGlob("MyTest.java", "*Test.java"));
    assertTrue(Patterns.matchesGlob("TestUtils.java", "Test*.java"));
    assertFalse(Patterns.matchesGlob("Test.xml", "*.java"));

    // Single character wildcard
    assertTrue(Patterns.matchesGlob("Test1.java", "Test?.java"));
    assertTrue(Patterns.matchesGlob("TestA.java", "Test?.java"));
    assertFalse(Patterns.matchesGlob("Test12.java", "Test?.java"));

    // Directory traversal
    assertTrue(Patterns.matchesGlob("src/main/java/Test.java", "**/Test.java"));
    assertTrue(Patterns.matchesGlob("a/b/c/d/Test.java", "**/Test.java"));
    assertTrue(Patterns.matchesGlob("Test.java", "**/Test.java"));

    // Complex patterns
    assertTrue(Patterns.matchesGlob("src/test/java/MyTest.java", "**/*Test.java"));
    assertTrue(Patterns.matchesGlob("src/test/java/TestUtils.java", "**/Test*.java"));
    assertFalse(Patterns.matchesGlob("src/main/java/Utils.java", "**/*Test.java"));
  }

  @Test
  public void testAutoPatternDetectionWithMatching() {
    // Glob patterns should work automatically
    assertTrue(Patterns.matchesPattern("Test.java", "*.java"), "Should match glob pattern");
    assertTrue(Patterns.matchesPattern("MyTest.java", "*Test.java"), "Should match glob pattern");
    assertTrue(Patterns.matchesPattern("src/test/java/MyTest.java", "**/*Test.java"), "Should match directory traversal");

    // Regex patterns should still work
    assertTrue(Patterns.matchesPattern("TestClass", ".*Test.*"), "Should match regex pattern");
    assertTrue(Patterns.matchesPattern("io.sundr.model.TypeDefFluent", ".*Fluent"), "Should match regex pattern");
    assertTrue(Patterns.matchesPattern("Test123", "Test[0-9]+"), "Should match regex character class");

    // Mixed scenarios through isIncluded/isExcluded
    assertTrue(Patterns.isIncluded("MyTest.java", "*.java", ".*Test.*"), "Should handle mixed patterns");
    assertTrue(Patterns.isExcluded("TestUtils.java", "*Test.java", ".*Utils.*"), "Should handle mixed patterns");
  }

  @Test
  public void testBackwardCompatibility() {
    // Existing regex patterns should continue working exactly as before
    assertTrue(Patterns.isExcluded("io.sundr.model.TypeDefFluent", ".*Fluent"));
    assertTrue(Patterns.isIncluded("TestClass", ".*Test.*"));
    assertFalse(Patterns.isIncluded("Utils", ".*Test.*"));

    // Existing behavior with character classes, anchors, etc.
    assertTrue(Patterns.matchesPattern("Test123", "Test[0-9]+"));
    assertTrue(Patterns.matchesPattern("start-middle-end", "^start.*end$"));
    assertTrue(Patterns.matchesPattern("choice1", "(choice1|choice2)"));
  }

  @Test
  public void testFluentApiRegex() {
    // Test explicit regex matching
    assertTrue(Patterns.regex().matches("TestClass", ".*Test.*"), "Should match regex pattern");
    assertTrue(Patterns.regex().matches("Test123", "Test[0-9]+"), "Should match regex character class");
    assertTrue(Patterns.regex().matches("start-middle-end", "^start.*end$"), "Should match regex anchors");
    assertFalse(Patterns.regex().matches("Utils", ".*Test.*"), "Should not match when pattern doesn't match");

    // Test regex isIncluded
    assertTrue(Patterns.regex().isIncluded("TestClass", ".*Test.*", ".*Utils.*"), "Should be included by regex");
    assertFalse(Patterns.regex().isIncluded("Something", ".*Test.*", ".*Utils.*"), "Should not be included by regex");

    // Test regex isExcluded
    assertTrue(Patterns.regex().isExcluded("TestClass", ".*Test.*"), "Should be excluded by regex");
    assertFalse(Patterns.regex().isExcluded("Something", ".*Test.*"), "Should not be excluded by regex");

    // Test that glob patterns are treated as literal regex (should throw exception)
    try {
      Patterns.regex().matches("Test.java", "*.java");
      fail("Should throw PatternSyntaxException for invalid regex");
    } catch (java.util.regex.PatternSyntaxException e) {
      // Expected - glob patterns are invalid regex
      assertTrue(e.getMessage().contains("Dangling meta character"),
          "Should contain error about dangling meta character");
    }
  }

  @Test
  public void testFluentApiGlob() {
    // Test explicit glob matching
    assertTrue(Patterns.glob().matches("Test.java", "*.java"), "Should match glob pattern");
    assertTrue(Patterns.glob().matches("src/test/java/MyTest.java", "**/*Test.java"),
        "Should match glob with directory traversal");
    assertTrue(Patterns.glob().matches("TestUtils.java", "Test*.java"), "Should match glob wildcard");
    assertFalse(Patterns.glob().matches("Test.xml", "*.java"), "Should not match when pattern doesn't match");

    // Test glob isIncluded
    assertTrue(Patterns.glob().isIncluded("Test.java", "*.java", "*.xml"), "Should be included by glob");
    assertFalse(Patterns.glob().isIncluded("Test.txt", "*.java", "*.xml"), "Should not be included by glob");

    // Test glob isExcluded
    assertTrue(Patterns.glob().isExcluded("MyTest.java", "*Test.java"), "Should be excluded by glob");
    assertFalse(Patterns.glob().isExcluded("Utils.java", "*Test.java"), "Should not be excluded by glob");

    // Test that regex patterns are treated as literal globs (should fail for regex-specific syntax)
    assertFalse(Patterns.glob().matches("TestClass", ".*Test.*"), "Regex pattern should fail as glob");
  }

  @Test
  public void testFluentApiAuto() {
    // Test auto-detection (should behave same as static methods)
    assertTrue(Patterns.auto().matches("Test.java", "*.java"), "Should auto-detect glob");
    assertTrue(Patterns.auto().matches("TestClass", ".*Test.*"), "Should auto-detect regex");
    assertTrue(Patterns.auto().matches("src/test/java/MyTest.java", "**/*Test.java"),
        "Should auto-detect directory traversal glob");

    // Test auto isIncluded/isExcluded
    assertTrue(Patterns.auto().isIncluded("Test.java", "*.java", ".*Test.*"), "Should auto-detect mixed patterns");
    assertTrue(Patterns.auto().isExcluded("TestUtils.java", "*Test.java", ".*Utils.*"), "Should auto-detect mixed patterns");
  }

  @Test
  public void testFluentApiUsageExamples() {
    // Example usage scenarios

    // Force regex when pattern might be ambiguous
    assertTrue(Patterns.regex().matches("test123", "test[0-9]+"),
        "Force regex for complex pattern");

    // Force glob for file patterns
    assertTrue(Patterns.glob().matches("MyTest.java", "*Test.java"),
        "Force glob for file patterns");

    // Auto-detection for mixed scenarios
    assertTrue(Patterns.auto().isIncluded("TestUtils.java", "*.java", ".*Test.*", "**/*Utils*"),
        "Auto-detect in mixed scenario");

    // Explicit type selection for performance-critical code
    assertTrue(Patterns.glob().isIncluded("test.txt", "*.txt", "*.log", "*.xml"),
        "Explicit glob for performance");

    assertTrue(Patterns.regex().isIncluded("TestClass", ".*Test.*", ".*Utils.*", ".*Service.*"),
        "Explicit regex for performance");
  }
}
