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

package io.sundr.dsl.internal.utils;

import static io.sundr.dsl.internal.Constants.BEGIN_SCOPE;
import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.CLASSES;
import static io.sundr.dsl.internal.Constants.END_SCOPE;
import static io.sundr.dsl.internal.Constants.FILTER;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.METHODS;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isCardinalityMultiple;
import static io.sundr.dsl.internal.utils.TypeDefUtils.isTerminal;

import java.util.Collection;
import java.util.Deque;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import io.sundr.codegen.model.TypeDef;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;

public final class GraphUtils {

  private GraphUtils() {
    //Utility Class
  }

  public static Set<TypeDef> exclusion(Set<TypeDef> one, Collection<TypeDef> excluded) {
    Set<TypeDef> result = new LinkedHashSet<TypeDef>();
    for (TypeDef item : one) {
      if (!excluded.contains(item) || isTerminal(item) || isCardinalityMultiple(item)) {
        result.add(item);
      }
    }
    return result;
  }

  public static boolean isSatisfied(TypeDef candidate, List<TypeDef> path) {
    Set<String> keywordsAndScopes = new LinkedHashSet<String>();
    Set<String> visitedKeywords = getKeywords(path);
    Deque<String> activeScopes = getScopes(path);
    keywordsAndScopes.addAll(visitedKeywords);
    keywordsAndScopes.addAll(activeScopes);

    TransitionFilter filter = (TransitionFilter) candidate.getAttributes().get(FILTER);
    Boolean multiple = (Boolean) candidate.getAttributes().get(CARDINALITY_MULTIPLE);

    Set<String> keywords = (Set<String>) candidate.getAttributes().get(KEYWORDS);
    if (!activeScopes.isEmpty() && !keywords.contains(activeScopes.getLast())) {
      return false;
    }
    int lastIndex = path.lastIndexOf(candidate);
    if (!multiple && path.contains(candidate)) {
      //Eliminate circles if not explicitly specified
      return false;
    } else if (multiple && lastIndex > 0 && lastIndex < path.size() - 1) {
      //We only accept repetition of the last element. Other wise we can end up in infinite loops
      return false;
    }
    return filter.apply(path);
  }

  public static Set<String> getClasses(Collection<TypeDef> types) {
    Set<String> result = new HashSet<String>();
    for (TypeDef type : types) {
      Set<String> classes = (Set<String>) type.getAttributes().get(CLASSES);
      if (classes != null) {
        result.addAll(classes);
      }
    }
    return result;
  }

  public static Set<String> getKeywords(Collection<TypeDef> types) {
    Set<String> result = new HashSet<String>();
    for (TypeDef type : types) {
      Set<String> keywords = (Set<String>) type.getAttributes().get(KEYWORDS);
      if (keywords != null) {
        result.addAll(keywords);
      }
    }
    return result;
  }

  public static Set<String> getMethods(Collection<TypeDef> types) {
    Set<String> result = new HashSet<String>();
    for (TypeDef type : types) {
      Set<String> methods = (Set<String>) type.getAttributes().get(METHODS);
      if (methods != null) {
        result.addAll(methods);
      }
    }
    return result;
  }

  public static LinkedList<String> getScopes(Collection<TypeDef> types) {
    Stack<String> stack = new Stack<String>();
    for (TypeDef type : types) {

      String scope = (String) type.getAttributes().get(BEGIN_SCOPE);
      if (scope != null && !scope.isEmpty()) {
        stack.push(scope);
      }

      scope = (String) type.getAttributes().get(END_SCOPE);
      if (scope != null && !scope.isEmpty()) {
        try {
          String found = stack.pop();
          if (!scope.equals(found)) {
            throw new IllegalStateException("End of scope:" + scope + " but active scope was:" + found);
          }
        } catch (EmptyStackException e) {
          throw new IllegalStateException("Expected active scope:" + scope + " but not was active.", e);
        }
      }
    }
    return new LinkedList<String>(stack);
  }
}
