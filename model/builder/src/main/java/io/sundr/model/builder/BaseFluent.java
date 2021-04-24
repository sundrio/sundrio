package io.sundr.model.builder;

import java.lang.Boolean;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
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

  private static <V extends io.sundr.model.builder.Visitor, F> Boolean canVisit(V visitor, F fluent) {
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

  public F accept(io.sundr.model.builder.Visitor... visitors) {
    if (isPathAwareVisitorArray(visitors)) {
      return acceptPathAware(asPathAwareVisitorArray(visitors));
    } else {
      return acceptInternal(visitors);
    }
  }

  @Override
  public <V> F accept(final Class<V> type, final io.sundr.model.builder.Visitor<V> visitor) {
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

  private F acceptInternal(io.sundr.model.builder.Visitor... visitors) {
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

  private static Boolean isPathAwareVisitorArray(io.sundr.model.builder.Visitor... visitors) {
    return !Arrays.stream(visitors).filter(v -> !(v instanceof PathAwareTypedVisitor)).findAny().isPresent();
  }

  private static PathAwareTypedVisitor[] asPathAwareVisitorArray(io.sundr.model.builder.Visitor... visitors) {
    return Arrays.stream(visitors).filter(v -> v instanceof PathAwareTypedVisitor).map(v -> (PathAwareTypedVisitor) v)
        .toArray(size -> new PathAwareTypedVisitor[size]);
  }

}
