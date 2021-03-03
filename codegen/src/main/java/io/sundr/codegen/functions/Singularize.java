/*
 * Copyright 2016 The original authors.
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

package io.sundr.codegen.functions;

import java.util.Arrays;
import java.util.List;

import io.sundr.Function;

/**
 * This is heavily inspired by the Inflector, which has many different incarnations in mandy different languages.
 * I was particularly inspired by the work of of Randall Hauch as described in:
 * https://github.com/bayofmany/peapod/blob/master/core/src/main/java/peapod/internal/Inflector.java
 */
public enum Singularize implements Function<String, String> {

  FUNCTION;

  private static final List<String> UNCOUNTABLE = Arrays.asList("equipment", "fish", "information", "money", "rice", "series",
      "sheep", "species");

  private static final List<Function<String, String>> SINGULARS = Arrays.<Function<String, String>> asList(
      //Irregulars
      new StringReplace("(p)erson$", "$1eople"),
      new StringReplace("(m)an$", "$1en"),
      new StringReplace("(c)hild$", "$1hildren"),
      new StringReplace("(s)ex$", "$1exes"),
      new StringReplace("(m)ove$", "$1oves"),
      new StringReplace("(s)tadium$", "$1tadiums"),

      //Rules
      new StringReplace("(quiz)zes$", "$1"),
      new StringReplace("(matr)ices$", "$1ix"),
      new StringReplace("(vert|ind)ices$", "$1ex"),
      new StringReplace("^(ox)en", "$1"),
      new StringReplace("(alias|status)$", "$1"),
      new StringReplace("(alias|status)es$", "$1"),
      new StringReplace("(octop|vir)us$", "$1us"),
      new StringReplace("(octop|vir)i$", "$1us"),
      new StringReplace("(cris|ax|test)es$", "$1is"),
      new StringReplace("(cris|ax|test)is$", "$1is"),
      new StringReplace("(shoe)s$", "$1"),
      new StringReplace("(o)es$", "$1"),
      new StringReplace("(bus)es$", "$1"),
      new StringReplace("([m|l])ice$", "$1ouse"),
      new StringReplace("(x|ch|ss|sh)es$", "$1"),
      new StringReplace("(m)ovies$", "$1ovie"),
      new StringReplace("(s)eries$", "$1eries"),
      new StringReplace("([^aeiouy]|qu)ies$", "$1y"),
      new StringReplace("([lr])ves$", "$1f"),
      new StringReplace("(tive)s$", "$1"),
      new StringReplace("(hive)s$", "$1"),
      new StringReplace("([^f])ves$", "$1fe"),
      new StringReplace("(^analy)sis$", "$1sis"),
      new StringReplace("(^analy)ses$", "$1sis"),
      new StringReplace("((a)naly|(b)a|(d)iagno|(p)arenthe|(p)rogno|(s)ynop|(t)he)ses$", "$1$2sis"),
      new StringReplace("([ti])a$", "$1um"),
      new StringReplace("(n)ews$", "$1ews"),
      new StringReplace("(s|si|u)s$", "$1s"),
      new StringReplace("s$", ""));

  public String apply(String word) {
    if (word == null) {
      return null;
    } else if (word.isEmpty()) {
      return word;
    } else if (UNCOUNTABLE.contains(word)) {
      return word;
    }

    for (Function<String, String> function : SINGULARS) {
      String result = function.apply(word);
      if (result != null) {
        return result;
      }
    }
    return word;
  }
}
