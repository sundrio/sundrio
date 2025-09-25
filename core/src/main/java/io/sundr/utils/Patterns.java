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

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Patterns {

  private Patterns() {
    //Utility class
  }

  /**
   * Returns a PatternMatcher configured to use regex patterns explicitly.
   *
   * @return a regex pattern matcher
   */
  public static PatternMatcher regex() {
    return new PatternMatcher(PatternType.REGEX);
  }

  /**
   * Returns a PatternMatcher configured to use glob patterns explicitly.
   *
   * @return a glob pattern matcher
   */
  public static PatternMatcher glob() {
    return new PatternMatcher(PatternType.GLOB);
  }

  /**
   * Returns a PatternMatcher configured to auto-detect pattern type.
   * This is the default behavior and equivalent to using the static methods directly.
   *
   * @return an auto-detecting pattern matcher
   */
  public static PatternMatcher auto() {
    return new PatternMatcher(PatternType.AUTO);
  }

  /**
   * Pattern type enumeration for explicit pattern type selection.
   */
  public enum PatternType {
    REGEX, GLOB, AUTO
  }

  /**
   * Fluent API for pattern matching with explicit type control.
   */
  public static class PatternMatcher {
    private final PatternType type;

    private PatternMatcher(PatternType type) {
      this.type = type;
    }

    /**
     * Checks if the target string matches the given pattern using the configured pattern type.
     *
     * @param target the string to match against
     * @param pattern the pattern
     * @return true if the target matches the pattern
     */
    public boolean matches(String target, String pattern) {
      switch (type) {
        case REGEX:
          return target.matches(pattern);
        case GLOB:
          return matchesGlob(target, pattern);
        case AUTO:
        default:
          return matchesPattern(target, pattern);
      }
    }

    /**
     * Checks if the target is included by any of the given patterns.
     *
     * @param target the string to test
     * @param patterns the patterns to test against
     * @return true if target matches any pattern, false otherwise
     */
    public boolean isIncluded(String target, String... patterns) {
      return patterns.length == 0 || Arrays.stream(patterns)
          .anyMatch(pattern -> matches(target, pattern));
    }

    /**
     * Checks if the target is excluded by any of the given patterns.
     *
     * @param target the string to test
     * @param patterns the patterns to test against
     * @return true if target matches any pattern, false otherwise
     */
    public boolean isExcluded(String target, String... patterns) {
      return patterns.length != 0 && Arrays.stream(patterns)
          .anyMatch(pattern -> matches(target, pattern));
    }
  }

  public static Optional<String> match(String content, String pattern) {
    return match(content, pattern, 1);
  }

  public static Optional<String> match(String content, String pattern, int index) {
    Matcher matcher = Pattern.compile(pattern).matcher(content);
    return matcher.find() ? Optional.of(matcher.group(index)) : Optional.empty();
  }

  public static boolean isIncluded(String target, String... includes) {
    return includes.length == 0 || Arrays.stream(includes)
        .anyMatch(pattern -> matchesPattern(target, pattern));
  }

  public static boolean isExcluded(String target, String... excludes) {
    return excludes.length != 0 && Arrays.stream(excludes)
        .anyMatch(pattern -> matchesPattern(target, pattern));
  }

  /**
   * Checks if the target string matches the given pattern.
   * Automatically detects whether the pattern is a glob or regex pattern.
   *
   * @param target the string to match against
   * @param pattern the pattern (glob or regex)
   * @return true if the target matches the pattern
   */
  public static boolean matchesPattern(String target, String pattern) {
    if (isGlobPattern(pattern)) {
      return matchesGlob(target, pattern);
    } else {
      return target.matches(pattern);
    }
  }

  /**
   * Determines if a pattern is a glob pattern or a regex pattern.
   * Uses heuristics to detect common glob patterns vs regex patterns.
   *
   * @param pattern the pattern to analyze
   * @return true if the pattern appears to be a glob pattern, false for regex
   */
  public static boolean isGlobPattern(String pattern) {
    // Definitely regex if contains these metacharacters
    if (pattern.matches(".*[\\[\\]()^$+{|}\\\\].*")) {
      return false;
    }

    // Definitely glob if contains directory traversal
    if (pattern.contains("**")) {
      return true;
    }

    // Likely glob if contains simple wildcards with file extensions
    if (pattern.matches(".*\\*\\.[a-zA-Z0-9]+")) {
      return true;
    }

    // Likely glob if it matches common file patterns
    if (pattern.matches("\\*[a-zA-Z0-9_]*\\.[a-zA-Z0-9]+") || // *Test.java, *Utils.xml
        pattern.matches("[a-zA-Z0-9_]*\\*\\.[a-zA-Z0-9]+")) { // Test*.java, Utils*.xml
      return true;
    }

    // Glob if contains only simple wildcards without regex metacharacters
    if ((pattern.contains("*") || pattern.contains("?")) &&
        !pattern.matches(".*[.+()^${}|\\\\].*")) {
      return true;
    }

    // Default to regex for backward compatibility
    return false;
  }

  /**
   * Matches a target string against a glob pattern.
   * Supports wildcards: * (any characters), ? (single character), ** (directory traversal).
   *
   * @param target the string to match
   * @param glob the glob pattern
   * @return true if the target matches the glob pattern
   */
  public static boolean matchesGlob(String target, String glob) {
    String regex = globToRegex(glob);
    return target.matches(regex);
  }

  /**
   * Converts a glob pattern to a regular expression.
   * Handles directory traversal (**), wildcards (*), and single character matches (?).
   *
   * @param glob the glob pattern
   * @return the equivalent regular expression
   */
  public static String globToRegex(String glob) {
    StringBuilder regex = new StringBuilder();
    String pattern = glob;

    // Handle directory traversal prefix
    if (pattern.startsWith("**/")) {
      pattern = pattern.substring(3);
      regex.append(".*");
    }

    // Escape regex special characters first
    pattern = pattern.replace("\\", "\\\\");
    pattern = pattern.replace(".", "\\.");
    pattern = pattern.replace("(", "\\(");
    pattern = pattern.replace(")", "\\)");
    pattern = pattern.replace("[", "\\[");
    pattern = pattern.replace("]", "\\]");
    pattern = pattern.replace("{", "\\{");
    pattern = pattern.replace("}", "\\}");
    pattern = pattern.replace("+", "\\+");
    pattern = pattern.replace("^", "\\^");
    pattern = pattern.replace("$", "\\$");
    pattern = pattern.replace("|", "\\|");

    // Convert glob patterns to regex (order matters: ** before *)
    pattern = pattern.replace("**", "DOUBLE_STAR_PLACEHOLDER");
    pattern = pattern.replace("*", "[^/]*"); // Single wildcard (don't cross directory boundaries)
    pattern = pattern.replace("DOUBLE_STAR_PLACEHOLDER", ".*"); // Directory traversal
    pattern = pattern.replace("?", "[^/]"); // Single character (don't cross directory boundaries)

    regex.append(pattern);
    return regex.toString();
  }

}
