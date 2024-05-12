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

package io.sundr.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public final class Strings {

  private static final String SPLITTER_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

  private static final List<String> KEYWORDS = Arrays.asList("abstract", "continue", "for", "new", "switch", "assert",
      "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double", "implements",
      "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum", "instanceof", "return", "transient",
      "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void", "class", "finally", "long",
      "strictfp", "volatile", "const", "float", "native", "super", "while");

  private static String NEWLINE = "\n";
  private static String NEWLINE_PATTERN = "\n|\r";

  public static final class ToString<X> implements Function<X, String> {
    @Override
    public String apply(X item) {
      return String.valueOf(item);
    }
  }

  private Strings() {
    //Utility Class
  }

  public static boolean isNullOrEmpty(String str) {
    return str == null || str.isEmpty();
  }

  public static boolean isNotNullOrEmpty(String str) {
    return !isNullOrEmpty(str);
  }

  public static int indexOfAlphabetic(String str) {
    if (str == null || str.isEmpty()) {
      return -1;
    }
    for (int i = 0; i < str.length(); i++) {
      if (Character.isAlphabetic(str.charAt(i))) {
        return i;
      }
    }
    return -1;
  }

  public static String capitalizeFirst(String str) {
    int first = indexOfAlphabetic(str);
    if (first < 0) {
      return str;
    } else if (first == 0) {
      return str.substring(0, 0 + 1).toUpperCase() + str.substring(0 + 1);
    } else {
      return str.substring(0, first) + str.substring(first, first + 1).toUpperCase() + str.substring(first + 1);
    }
  }

  public static String deCapitalizeFirst(String str) {
    int first = indexOfAlphabetic(str);
    if (first < 0) {
      return str;
    } else if (first == 0) {
      return str.substring(0, 0 + 1).toLowerCase() + str.substring(0 + 1);
    } else {
      return str.substring(0, first) + str.substring(first, first + 1).toLowerCase() + str.substring(first + 1);
    }
  }

  public static <T> String join(Iterable<T> items, String delimiter) {
    return join(items, new ToString<T>(), delimiter);
  }

  public static <T> String join(Iterable<T> items, Function<T, String> function, String delimiter) {
    return StreamSupport.stream(items.spliterator(), false)
        .map(function::apply)
        .collect(Collectors.joining(delimiter));
  }

  public static <T> String join(T[] items, String delimiter) {
    return join(items, new ToString<T>(), delimiter);
  }

  public static <T> String join(T[] items, Function<T, String> function, String delimiter) {
    return Stream.of(items)
        .map(function::apply)
        .collect(Collectors.joining(delimiter));
  }

  public static <T> String getPrefix(Iterable<T> items, Function<T, String> function) {
    int length = 0;
    String prefix = "";
    String current = "";

    while (true) {
      boolean first = true;
      for (T item : items) {
        String currentItem = function.apply(item);
        if (first) {
          first = false;
          if (currentItem.length() > length) {
            current = currentItem.substring(0, length);
          } else {
            return prefix;
          }
        } else if (!currentItem.startsWith(current)) {
          return prefix;
        }
      }
      length++;
      prefix = current;
    }
  }

  public static String getPrefix(Iterable<String> items) {
    return getPrefix(items, new Function<String, String>() {
      @Override
      public String apply(String item) {
        return item;
      }
    });
  }

  public static final String loadResourceQuietly(String resourceName) {
    try {
      return loadResource(resourceName);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load resource:" + resourceName, e);
    }
  }

  public static String loadResource(String resourceName) throws IOException {
    InputStream is = null;
    BufferedReader in = null;
    try {
      is = Strings.class.getClassLoader().getResourceAsStream(resourceName);
      in = new BufferedReader(new InputStreamReader(is));
      String line = null;
      StringBuffer sb = new StringBuffer();
      while ((line = in.readLine()) != null) {
        sb.append(line).append("\n");
      }
      return sb.toString();
    } finally {
      if (in != null) {
        in.close();
      }
      if (is != null) {
        is.close();
      }
    }
  }

  public static final String loadResourceQuietly(URL resourceUrl) {
    try {
      return loadResource(resourceUrl);
    } catch (Exception e) {
      throw new RuntimeException("Failed to load resource:" + resourceUrl, e);
    }
  }

  public static String loadResource(URL resourceUrl) throws IOException {
    InputStream is = null;
    BufferedReader in = null;
    try {
      is = resourceUrl.openStream();
      in = new BufferedReader(new InputStreamReader(is));
      StringBuffer sb = new StringBuffer();
      int c = 0;
      while ((c = in.read()) != -1) {
        sb.append((char) c);
      }
      return sb.toString();
    } finally {
      if (in != null) {
        in.close();
      }
      if (is != null) {
        is.close();
      }
    }
  }

  /**
   * Remove repeating strings that are appearing in the name.
   * This is done by splitting words (camel case) and using each word once.
   *
   * @return The compact name.
   */
  public static final String compact(String main, String... other) {
    List<String> parts = new ArrayList<String>();
    parts.add(main);
    Set<String> seen = new HashSet<>(Arrays.asList(main.split(SPLITTER_REGEX)));
    String[] otherParts = join(other, "").split(SPLITTER_REGEX);

    for (String part : otherParts) {
      if (seen.add(part)) {
        parts.add(part);
      }
    }
    return join(parts, "");
  }

  /**
   * Adds an underscore to the specified String, if its a Java Keyword.
   *
   * @param name The specified string.
   * @return The specified string if not a keyword, the string prefixed with underscore otherwise.
   */
  public static final String prefixKeywords(String name) {
    if (KEYWORDS.contains(name)) {
      return "_" + name;
    } else
      return name;
  }

  /**
   * Converts the string into a safe field name.
   *
   * @param name The field name.
   * @return The safe field name.
   */
  public static final String toFieldName(String name) {
    return prefixKeywords(name);
  }

  /**
   * Converts a name of an interface or abstract class to Pojo name.
   * Remove leading "I" and "Abstract" or trailing "Interface".
   *
   * @param name The name to convert.
   * @param prefix The prefix to use if needed.
   * @param suffix The suffix to user if needed.
   * @return The converted name, if a conversion actually happened or the original name prefixed and suffixed otherwise.
   */
  public static final String toPojoName(String name, String prefix, String suffix) {
    LinkedList<String> parts = new LinkedList<>(Arrays.asList(name.split(SPLITTER_REGEX)));
    if (parts.isEmpty()) {
      return prefix + name + suffix;
    }

    if (parts.getFirst().equals("I")) {
      parts.removeFirst();
    }

    if (parts.getFirst().equals("Abstract")) {
      parts.removeFirst();
    }

    if (parts.getLast().equals("Interface")) {
      parts.removeLast();
    }

    String candidate = join(parts, "");
    if (name.equals(candidate)) {
      return prefix + name + suffix;
    }

    return candidate;
  }

  public static String indent(String indent, String s) {
    StringBuilder sb = new StringBuilder();
    for (String line : s.split(NEWLINE_PATTERN)) {
      sb.append(indent).append(line).append(NEWLINE);
    }
    return sb.toString();
  }
}
