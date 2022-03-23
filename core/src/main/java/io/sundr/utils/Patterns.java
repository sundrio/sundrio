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

  public static Optional<String> match(String content, String pattern) {
    return match(content, pattern, 1);
  }

  public static Optional<String> match(String content, String pattern, int index) {
    Matcher matcher = Pattern.compile(pattern).matcher(content);
    return matcher.find() ? Optional.of(matcher.group(index)) : Optional.empty();
  }

  public static boolean isIncluded(String target, String... includes) {
    return includes.length == 0 || Arrays.stream(includes).map(Pattern::compile).map(p -> p.matcher(target))
        .filter(Matcher::matches).findAny().isPresent();
  }

  public static boolean isExcluded(String target, String... excludes) {
    return excludes.length != 0 &&
        Arrays.stream(excludes).map(Pattern::compile).map(p -> p.matcher(target)).filter(Matcher::matches).findAny()
            .isPresent();
  }

}
