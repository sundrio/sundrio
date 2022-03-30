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

package io.sundr.builder;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

@FunctionalInterface
public interface Visitor<T> {

  void visit(T element);

  default void visit(List<Object> path, T element) {
    visit(element);
  }

  default Class<T> getType() {
    List<Class> args = Visitors.getTypeArguments(Visitor.class, getClass());
    if (args == null || args.isEmpty()) {
      return null;
    }
    return (Class<T>) args.get(0);
  }

  default <F> Boolean canVisit(F target) {
    if (target == null) {
      return false;
    }

    if (getType() == null) {
      return hasVisitMethodMatching(target);
    } else if (!getType().isAssignableFrom(target.getClass())) {
      return false;
    }
    return true;
  }

  default Predicate[] getRequirements() {
    return new Predicate[0];
  }

  /**
   * Checks if the specified visitor has a visit method compatible with the
   * specified fluent.
   * 
   * @param target
   * @param <F>
   * @return
   */
  default <F> Boolean hasVisitMethodMatching(F target) {
    for (Method method : getClass().getMethods()) {
      if (!method.getName().equals("visit") || method.getParameterTypes().length != 1) {
        continue;
      }
      Class<?> visitorType = method.getParameterTypes()[0];
      if (visitorType.isAssignableFrom(target.getClass())) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  default int order() {
    return 0;
  }

  default <P> Visitor<T> addRequirement(Class<P> type, Predicate<P> predicate) {
    return addRequirement(predicate);
  }

  default Visitor<T> addRequirement(Predicate predicate) {
    return new DelegatingVisitor(getType(), this) {
      @Override
      public Predicate[] getRequirements() {
        return Stream.concat(Arrays.stream(Visitor.this.getRequirements()), Stream.of(predicate))
            .toArray(size -> new Predicate[size]);
      }
    };
  }
}
