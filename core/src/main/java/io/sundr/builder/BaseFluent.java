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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class BaseFluent<F extends Fluent<F>> implements Fluent<F>, Visitable<F> {

  private static final String VISIT = "visit";

  public final VisitableMap _visitables = new VisitableMap();

  public static <T> VisitableBuilder<T, ?> builderOf(T item) {
    if (item instanceof Editable) {
      Object editor = ((Editable) item).edit();
      if (editor instanceof VisitableBuilder) {
        return (VisitableBuilder<T, ?>) editor;
      }
    }

    try {
      return (VisitableBuilder<T, ?>) Class.forName(item.getClass().getName() + "Builder").getConstructor(item.getClass())
          .newInstance(item);
    } catch (Exception e) {
      throw new IllegalStateException("Failed to create builder for: " + item.getClass(), e);
    }
  }

  public static <T> ArrayList<T> build(List<? extends Builder<? extends T>> list) {
    if (list == null) {
      return null;
    }
    ArrayList<T> result = new ArrayList<T>();
    for (Builder<? extends T> builder : list) {
      result.add(builder.build());
    }
    return result;
  }

  public static <T> List<T> build(Set<? extends Builder<? extends T>> set) {
    if (set == null) {
      return null;
    }

    List<T> result = new ArrayList<T>();
    for (Builder<? extends T> builder : set) {
      result.add(builder.build());
    }
    return result;
  }

  public static <T> ArrayList<T> aggregate(List<? extends T>... lists) {
    ArrayList<T> result = new ArrayList<T>();

    for (List<? extends T> list : lists) {
      if (list != null) {
        result.addAll(list);
      }
    }
    return result;
  }

  public static <T> LinkedHashSet<T> aggregate(Set<? extends T>... sets) {
    LinkedHashSet<T> result = new LinkedHashSet<T>();

    for (Set<? extends T> set : sets) {
      if (set != null) {
        result.addAll(set);
      }
    }
    return result;
  }

  private static <V extends Visitor, F> Boolean canVisit(V visitor, F fluent) {
    if (visitor instanceof TypedVisitor) {
      if (!((TypedVisitor) visitor).getType().isAssignableFrom(fluent.getClass())) {
        return false;
      }
    }

    if (visitor instanceof PathAwareTypedVisitor) {
      PathAwareTypedVisitor pathAwareTypedVisitor = (PathAwareTypedVisitor) visitor;
      Class parentType = pathAwareTypedVisitor.getParentType();
      Class actaulParentType = pathAwareTypedVisitor.getActualParentType();
      if (!parentType.isAssignableFrom(actaulParentType)) {
        return false;
      }
    }

    return hasCompatibleVisitMethod(visitor, fluent);
  }

  /**
   * Checks if the specified visitor has a visit method compatible with the specified fluent.
   * 
   * @param visitor
   * @param fluent
   * @param <V>
   * @param <F>
   * @return
   */
  private static <V, F> Boolean hasCompatibleVisitMethod(V visitor, F fluent) {
    for (Method method : visitor.getClass().getMethods()) {
      if (!method.getName().equals(VISIT) || method.getParameterTypes().length != 1) {
        continue;
      }
      Class visitorType = method.getParameterTypes()[0];
      if (visitorType.isAssignableFrom(fluent.getClass())) {
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  public F accept(Visitor... visitors) {
    if (isPathAwareVisitorArray(visitors)) {
      return acceptPathAware(asPathAwareVisitorArray(visitors));
    } else {
      return acceptInternal(visitors);
    }
  }

  @Override
  public <V> F accept(final Class<V> type, final Visitor<V> visitor) {
    return accept(new TypedVisitor<V>() {
      @Override
      public Class<V> getType() {
        return type;
      }

      @Override
      public void visit(V element) {
        visitor.visit(element);
      }
    });
  }

  private F acceptInternal(Visitor... visitors) {
    for (Visitor visitor : visitors) {
      for (Visitable visitable : _visitables) {
        visitable.accept(visitor);
      }

      if (canVisit(visitor, this)) {
        visitor.visit(this);
      }
    }
    return (F) this;
  }

  private F acceptPathAware(PathAwareTypedVisitor... pathAwareTypedVisitors) {
    return acceptInternal(
        Arrays.stream(pathAwareTypedVisitors).map(p -> p.next(this)).toArray(size -> new PathAwareTypedVisitor[size]));
  }

  private static boolean isPathAwareVisitorArray(Visitor... visitors) {
    return !Arrays.stream(visitors).filter(v -> !(v instanceof PathAwareTypedVisitor)).findAny().isPresent();
  }

  private static PathAwareTypedVisitor[] asPathAwareVisitorArray(Visitor... visitors) {
    return Arrays.stream(visitors).filter(v -> v instanceof PathAwareTypedVisitor)
        .map(v -> (PathAwareTypedVisitor) v)
        .toArray(size -> new PathAwareTypedVisitor[size]);
  }
}
