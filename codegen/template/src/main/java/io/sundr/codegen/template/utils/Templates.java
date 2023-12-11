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

package io.sundr.codegen.template.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.stream.Collectors;

import io.sundr.SundrException;
import io.sundr.utils.Strings;

public final class Templates {

  private static final String DOT = ".";

  private Templates() {
    //Utility Class
  }

  /**
   * Utility to read the template from the specified {@link URL}.
   *
   * @param templateUrl the specified url
   * @return the content of the template as {@link String}
   */
  public static String read(URL templateUrl) {
    try (InputStream in = templateUrl.openStream()) {
      BufferedReader reader = new BufferedReader(new InputStreamReader(in));
      return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    } catch (IOException e) {
      throw SundrException.launderThrowable(e);
    }
  }

  /**
   * Utility that gets the file extension from the specified path if present.
   *
   * @param templatePath The specified path
   * @return an {@link Optional} String or empty, if no extension was found.
   */
  public static Optional<String> getExtension(String templatePath) {
    if (!Strings.isNullOrEmpty(templatePath) && templatePath.contains(DOT)) {
      return Optional.of(templatePath.substring(templatePath.lastIndexOf(DOT) + 1));
    }
    return Optional.empty();
  }

  /**
   * Utility that gets the file extension from the specified {@link URL} if present.
   *
   * @param templateUrl The specified {@link URL}
   * @return an {@link Optional} String or empty, if no extension was found.
   */
  public static Optional<String> getExtension(URL templateUrl) {
    return getExtension(templateUrl.getPath());
  }

}
