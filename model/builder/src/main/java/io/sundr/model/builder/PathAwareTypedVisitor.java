package io.sundr.model.builder;

import java.lang.Class;
import java.lang.Object;
import java.lang.Override;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PathAwareTypedVisitor<V, P> extends TypedVisitor<V> {

  private List<Object> path;
  private final PathAwareTypedVisitor<V, P> delegate;
  private final Class<P> parentType;

  public PathAwareTypedVisitor() {
    this.path = new ArrayList<Object>();
    this.delegate = this;
    this.parentType = (Class<P>) getTypeArguments(PathAwareTypedVisitor.class, getClass()).get(1);
  }

  public PathAwareTypedVisitor(List<Object> path) {
    this.path = path;
    this.delegate = this;
    this.parentType = (Class<P>) getTypeArguments(PathAwareTypedVisitor.class, getClass()).get(1);
  }

  public PathAwareTypedVisitor(List<Object> path, PathAwareTypedVisitor<V, P> delegate) {
    this.path = path;
    this.delegate = delegate;
    this.parentType = (Class<P>) getTypeArguments(PathAwareTypedVisitor.class, delegate.getClass()).get(1);
  }

  public PathAwareTypedVisitor<V, P> next(Object item) {
    List<Object> path = new ArrayList<Object>(this.path);
    path.add(item);
    return new PathAwareTypedVisitor<V, P>(path, this);
  }

  @Override
  public void visit(V element) {
    delegate.path = path;
    delegate.visit(element);
  }

  public P getParent() {
    int size = path.size();
    int parentIndex = size - 2;
    if (parentIndex >= 0) {
      return (P) path.get(parentIndex);
    } else {
      return null;
    }
  }

  public List<Object> getPath() {
    return Collections.unmodifiableList(path);
  }

  @Override
  public Class<V> getType() {
    Class<V> superType = super.getType();
    if (superType != null) {
      return superType;
    }
    return delegate.getType();
  }

  public Class<P> getParentType() {
    if (parentType != null) {
      return parentType;
    }
    return delegate.getParentType();
  }

  Class getActualParentType() {
    int size = path.size();
    int parentIndex = size - 2;
    if (parentIndex >= 0) {
      return path.get(parentIndex).getClass();
    } else {
      return Void.class;
    }
  }

}
