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

package io.sundr.model.utils;

import static io.sundr.model.Node.CP;
import static io.sundr.model.Node.OB;
import static io.sundr.model.Node.OP;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import io.sundr.SundrException;
import io.sundr.model.ClassRef;
import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.utils.Strings;

public class Parsers {

  public static List<ClassRef> parseImports(Path path) {
    try {
      return parseImports(Files.readString(path));
    } catch (IOException e) {
      throw SundrException.launderThrowable(e);
    }
  }

  public static List<ClassRef> parseImports(String content) {
    return Arrays.stream(content.trim().split("(\\r\\n)|(\\n)"))
        .filter(l -> l.matches("^\\s*import .*;\\s*$"))
        .map(l -> l.replaceFirst("\\s*import", ""))
        .map(l -> l.replaceAll("\\s*;\\s*", ""))
        .map(String::trim)
        .map(ClassRef::forName)
        .collect(Collectors.toList());
  }

  public static String parseMethodBody(Path path, Method method) {
    try {
      return parseMethodBody(Files.readString(path), method);
    } catch (Exception e) {
      throw SundrException.launderThrowable(e);
    }
  }

  public static String parseMethodBody(String content, Method method) {
    try {
      return parseMethodBody(content, Pattern.compile(createMethodSignatureRegex(method)));
    } catch (Exception e) {
      throw new SundrException(
          "Could not match method:" + method.getName() + " with arugments:"
              + method.getArguments().stream().map(p -> p.withErasure().render()).collect(Collectors.joining(",", "(", ")")),
          e);
    }
  }

  public static String parseMethodBody(String content, String name, List<Property> arguments) {
    try {
      return parseMethodBody(content, Pattern.compile(createMethodSignatureRegex(name, arguments)));
    } catch (IllegalStateException e) {
      throw new SundrException("Could not match method:" + name + " with arugments:"
          + arguments.stream().map(p -> p.withErasure().render()).collect(Collectors.joining(",", "(", ")")), e);
    }
  }

  public static String parseMethodBody(String content, Pattern pattern) {
    Matcher m = pattern.matcher(content);
    if (m.find()) {
      int start = m.end() - 1;
      boolean op = false;
      int counter = 0;
      int bodyStart = 0;
      int bodyEnd = 0;
      for (int i = start; i < content.length() && !(op && counter == 0); i++) {
        char c = content.charAt(i);
        if (c == '{') {
          counter++;
          if (!op) {
            op = true;
            bodyStart = i + 1;
          }
        } else if (c == '}') {
          counter--;
          bodyEnd = i - 1;
        }
      }

      return content.substring(bodyStart, bodyEnd - 1).trim();
    }
    throw new IllegalStateException("No match for method.");
  }

  public static String createMethodSignatureRegex(Method method) {
    String nameOrType = Strings.isNotNullOrEmpty(method.getName()) ? method.getName() : method.getReturnType().getName();
    return createMethodSignatureRegex(nameOrType, method.getArguments());
  }

  public static String createMethodSignatureRegex(String name, List<Property> arguments) {
    StringBuilder sb = new StringBuilder();
    sb.append(Pattern.quote(name));
    sb.append("\\s*");
    sb.append(Pattern.quote(OP));
    sb.append("\\s*");
    sb.append(arguments.stream().map(a -> (String) Pattern.quote(a.getTypeRef().getName()) + "(<.*>)?\\s*\\.*\\s*\\w+")
        .collect(Collectors.joining("\\s*,\\s*")));
    sb.append("\\s*");
    sb.append(Pattern.quote(CP));
    sb.append("\\s*");
    sb.append(Pattern.quote(OB));
    return sb.toString();
  }
}
