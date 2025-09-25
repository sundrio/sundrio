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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

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
    assertTrue("*.java should be detected as glob", Patterns.isGlobPattern("*.java"));
    assertTrue("**/*Test.java should be detected as glob", Patterns.isGlobPattern("**/*Test.java"));
    assertTrue("Test*.java should be detected as glob", Patterns.isGlobPattern("Test*.java"));
    assertTrue("**/Test?.java should be detected as glob", Patterns.isGlobPattern("**/Test?.java"));
    assertTrue("src/main/**/*.xml should be detected as glob", Patterns.isGlobPattern("src/main/**/*.xml"));

    // Should detect as regex patterns
    assertFalse(".*Test should be detected as regex", Patterns.isGlobPattern(".*Test"));
    assertFalse("Test[0-9]+ should be detected as regex", Patterns.isGlobPattern("Test[0-9]+"));
    assertFalse("^io\\.sundr\\..* should be detected as regex", Patterns.isGlobPattern("^io\\.sundr\\..*"));
    assertFalse("(Test|Spec).* should be detected as regex", Patterns.isGlobPattern("(Test|Spec).*"));
    assertFalse("Test{1,3} should be detected as regex", Patterns.isGlobPattern("Test{1,3}"));

    // Edge cases - should default to regex for backward compatibility
    assertFalse("plain string should default to regex", Patterns.isGlobPattern("Test"));
    assertFalse("string with dots should default to regex", Patterns.isGlobPattern("io.sundr.Test"));
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
    assertTrue("Should match glob pattern", Patterns.matchesPattern("Test.java", "*.java"));
    assertTrue("Should match glob pattern", Patterns.matchesPattern("MyTest.java", "*Test.java"));
    assertTrue("Should match directory traversal", Patterns.matchesPattern("src/test/java/MyTest.java", "**/*Test.java"));

    // Regex patterns should still work
    assertTrue("Should match regex pattern", Patterns.matchesPattern("TestClass", ".*Test.*"));
    assertTrue("Should match regex pattern", Patterns.matchesPattern("io.sundr.model.TypeDefFluent", ".*Fluent"));
    assertTrue("Should match regex character class", Patterns.matchesPattern("Test123", "Test[0-9]+"));

    // Mixed scenarios through isIncluded/isExcluded
    assertTrue("Should handle mixed patterns", Patterns.isIncluded("MyTest.java", "*.java", ".*Test.*"));
    assertTrue("Should handle mixed patterns", Patterns.isExcluded("TestUtils.java", "*Test.java", ".*Utils.*"));
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
    assertTrue("Should match regex pattern", Patterns.regex().matches("TestClass", ".*Test.*"));
    assertTrue("Should match regex character class", Patterns.regex().matches("Test123", "Test[0-9]+"));
    assertTrue("Should match regex anchors", Patterns.regex().matches("start-middle-end", "^start.*end$"));
    assertFalse("Should not match when pattern doesn't match", Patterns.regex().matches("Utils", ".*Test.*"));

    // Test regex isIncluded
    assertTrue("Should be included by regex", Patterns.regex().isIncluded("TestClass", ".*Test.*", ".*Utils.*"));
    assertFalse("Should not be included by regex", Patterns.regex().isIncluded("Something", ".*Test.*", ".*Utils.*"));

    // Test regex isExcluded
    assertTrue("Should be excluded by regex", Patterns.regex().isExcluded("TestClass", ".*Test.*"));
    assertFalse("Should not be excluded by regex", Patterns.regex().isExcluded("Something", ".*Test.*"));

    // Test that glob patterns are treated as literal regex (should throw exception)
    try {
      Patterns.regex().matches("Test.java", "*.java");
      fail("Should throw PatternSyntaxException for invalid regex");
    } catch (java.util.regex.PatternSyntaxException e) {
      // Expected - glob patterns are invalid regex
      assertTrue("Should contain error about dangling meta character",
          e.getMessage().contains("Dangling meta character"));
    }
  }

  @Test
  public void testFluentApiGlob() {
    // Test explicit glob matching
    assertTrue("Should match glob pattern", Patterns.glob().matches("Test.java", "*.java"));
    assertTrue("Should match glob with directory traversal",
        Patterns.glob().matches("src/test/java/MyTest.java", "**/*Test.java"));
    assertTrue("Should match glob wildcard", Patterns.glob().matches("TestUtils.java", "Test*.java"));
    assertFalse("Should not match when pattern doesn't match", Patterns.glob().matches("Test.xml", "*.java"));

    // Test glob isIncluded
    assertTrue("Should be included by glob", Patterns.glob().isIncluded("Test.java", "*.java", "*.xml"));
    assertFalse("Should not be included by glob", Patterns.glob().isIncluded("Test.txt", "*.java", "*.xml"));

    // Test glob isExcluded
    assertTrue("Should be excluded by glob", Patterns.glob().isExcluded("MyTest.java", "*Test.java"));
    assertFalse("Should not be excluded by glob", Patterns.glob().isExcluded("Utils.java", "*Test.java"));

    // Test that regex patterns are treated as literal globs (should fail for regex-specific syntax)
    assertFalse("Regex pattern should fail as glob", Patterns.glob().matches("TestClass", ".*Test.*"));
  }

  @Test
  public void testFluentApiAuto() {
    // Test auto-detection (should behave same as static methods)
    assertTrue("Should auto-detect glob", Patterns.auto().matches("Test.java", "*.java"));
    assertTrue("Should auto-detect regex", Patterns.auto().matches("TestClass", ".*Test.*"));
    assertTrue("Should auto-detect directory traversal glob",
        Patterns.auto().matches("src/test/java/MyTest.java", "**/*Test.java"));

    // Test auto isIncluded/isExcluded
    assertTrue("Should auto-detect mixed patterns", Patterns.auto().isIncluded("Test.java", "*.java", ".*Test.*"));
    assertTrue("Should auto-detect mixed patterns", Patterns.auto().isExcluded("TestUtils.java", "*Test.java", ".*Utils.*"));
  }

  @Test
  public void testFluentApiUsageExamples() {
    // Example usage scenarios

    // Force regex when pattern might be ambiguous
    assertTrue("Force regex for complex pattern",
        Patterns.regex().matches("test123", "test[0-9]+"));

    // Force glob for file patterns
    assertTrue("Force glob for file patterns",
        Patterns.glob().matches("MyTest.java", "*Test.java"));

    // Auto-detection for mixed scenarios
    assertTrue("Auto-detect in mixed scenario",
        Patterns.auto().isIncluded("TestUtils.java", "*.java", ".*Test.*", "**/*Utils*"));

    // Explicit type selection for performance-critical code
    assertTrue("Explicit glob for performance",
        Patterns.glob().isIncluded("test.txt", "*.txt", "*.log", "*.xml"));

    assertTrue("Explicit regex for performance",
        Patterns.regex().isIncluded("TestClass", ".*Test.*", ".*Utils.*", ".*Service.*"));
  }
}
