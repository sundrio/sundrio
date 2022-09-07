/*
 *      Copyright 2016 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.builder;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map.Entry;

public class PathAwareTypedVisitor<V, P> implements Visitor<V> {

  private final Class<V> type;
  private final Class<P> parentType;

  public PathAwareTypedVisitor() {
    List<Class> args = Visitors.getTypeArguments(PathAwareTypedVisitor.class, getClass());
    if (args == null || args.isEmpty()) {
      throw new IllegalStateException("Could not determine type arguments for path aware typed visitor.");
    }
    this.type = (Class<V>) args.get(0);
    this.parentType = (Class<P>) args.get(1);
  }

  @Override
  public void visit(V element) {
  }

  @Override
  public void visit(List<Entry<String, Object>> path, V element) {
    visit(element);
  }

  /**
   * Checks if the specified visitor has a visit method compatible with the specified fluent.
   * 
   * @param target The candidate to check if current visitor can visit.
   * @param <F> The type of the candidate
   * @return True if matching method was found.
   */
  public <F> Boolean hasVisitMethodMatching(F target) {
    for (Method method : getClass().getMethods()) {
      if (!method.getName().equals("visit") || method.getParameterTypes().length != 2) {
        continue;
      }
      Class<?> visitorType = method.getParameterTypes()[1];
      if (visitorType.isAssignableFrom(target.getClass())) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public P getParent(List<Object> path) {
    return path.size() - 1 >= 0 ? (P) path.get(path.size() - 1) : null;
  }

  public Class<P> getParentType() {
    return parentType;
  }

  @Override
  public Class<V> getType() {
    return type;
  }
}
