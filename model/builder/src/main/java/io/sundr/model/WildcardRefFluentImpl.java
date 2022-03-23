package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class WildcardRefFluentImpl<A extends WildcardRefFluent<A>> extends TypeRefFluentImpl<A>
    implements WildcardRefFluent<A> {
  public WildcardRefFluentImpl() {
  }

  public WildcardRefFluentImpl(io.sundr.model.WildcardRef instance) {
    this.withBoundKind(instance.getBoundKind());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
  }

  private WildcardRef.BoundKind boundKind;
  private ArrayList<VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();

  public io.sundr.model.WildcardRef.BoundKind getBoundKind() {
    return this.boundKind;
  }

  public A withBoundKind(io.sundr.model.WildcardRef.BoundKind boundKind) {
    this.boundKind = boundKind;
    return (A) this;
  }

  public Boolean hasBoundKind() {
    return this.boundKind != null;
  }

  public A addToBounds(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    _visitables.get("bounds").add(builder);
    this.bounds.add(builder);
    return (A) this;
  }

  public A addToBounds(Integer index, io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    _visitables.get("bounds").add(index, builder);
    this.bounds.add(index, builder);
    return (A) this;
  }

  public A addToBounds(java.lang.Integer index, io.sundr.model.TypeRef item) {
    if (item instanceof TypeParamRef) {
      addToTypeParamRefBounds(index, (io.sundr.model.TypeParamRef) item);
    } else if (item instanceof io.sundr.model.WildcardRef) {
      addToWildcardRefBounds(index, (io.sundr.model.WildcardRef) item);
    } else if (item instanceof ClassRef) {
      addToClassRefBounds(index, (io.sundr.model.ClassRef) item);
    } else if (item instanceof PrimitiveRef) {
      addToPrimitiveRefBounds(index, (io.sundr.model.PrimitiveRef) item);
    } else if (item instanceof VoidRef) {
      addToVoidRefBounds(index, (io.sundr.model.VoidRef) item);
    }

    return (A) this;
  }

  public A setToBounds(java.lang.Integer index, io.sundr.model.TypeRef item) {
    if (item instanceof io.sundr.model.TypeParamRef) {
      setToTypeParamRefBounds(index, (io.sundr.model.TypeParamRef) item);
    } else if (item instanceof io.sundr.model.WildcardRef) {
      setToWildcardRefBounds(index, (io.sundr.model.WildcardRef) item);
    } else if (item instanceof io.sundr.model.ClassRef) {
      setToClassRefBounds(index, (io.sundr.model.ClassRef) item);
    } else if (item instanceof io.sundr.model.PrimitiveRef) {
      setToPrimitiveRefBounds(index, (io.sundr.model.PrimitiveRef) item);
    } else if (item instanceof io.sundr.model.VoidRef) {
      setToVoidRefBounds(index, (io.sundr.model.VoidRef) item);
    }

    return (A) this;
  }

  public A addToBounds(io.sundr.model.TypeRef... items) {
    if (items != null && items.length > 0 && this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        addToTypeParamRefBounds((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        addToWildcardRefBounds((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        addToClassRefBounds((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        addToPrimitiveRefBounds((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        addToVoidRefBounds((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").add(builder);
        this.bounds.add(builder);
      }
    }
    return (A) this;
  }

  public A addAllToBounds(Collection<io.sundr.model.TypeRef> items) {
    if (items != null && items.size() > 0 && this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        addToTypeParamRefBounds((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        addToWildcardRefBounds((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        addToClassRefBounds((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        addToPrimitiveRefBounds((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        addToVoidRefBounds((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").add(builder);
        this.bounds.add(builder);
      }
    }
    return (A) this;
  }

  public A removeFromBounds(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    _visitables.get("bounds").remove(builder);
    this.bounds.remove(builder);
    return (A) this;
  }

  public A removeFromBounds(io.sundr.model.TypeRef... items) {
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        removeFromTypeParamRefBounds((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        removeFromWildcardRefBounds((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        removeFromClassRefBounds((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        removeFromPrimitiveRefBounds((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        removeFromVoidRefBounds((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").remove(builder);
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromBounds(java.util.Collection<io.sundr.model.TypeRef> items) {
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        removeFromTypeParamRefBounds((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        removeFromWildcardRefBounds((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        removeFromClassRefBounds((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        removeFromPrimitiveRefBounds((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        removeFromVoidRefBounds((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").remove(builder);
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildBounds instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<io.sundr.model.TypeRef> getBounds() {
    return build(bounds);
  }

  public java.util.List<io.sundr.model.TypeRef> buildBounds() {
    return build(bounds);
  }

  public io.sundr.model.TypeRef buildBound(java.lang.Integer index) {
    return this.bounds.get(index).build();
  }

  public io.sundr.model.TypeRef buildFirstBound() {
    return this.bounds.get(0).build();
  }

  public io.sundr.model.TypeRef buildLastBound() {
    return this.bounds.get(bounds.size() - 1).build();
  }

  public io.sundr.model.TypeRef buildMatchingBound(
      Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    for (io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingBound(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    for (io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withBounds(java.util.List<io.sundr.model.TypeRef> bounds) {
    if (bounds != null) {
      this.bounds = new java.util.ArrayList();
      for (io.sundr.model.TypeRef item : bounds) {
        this.addToBounds(item);
      }
    } else {
      this.bounds = null;
    }
    return (A) this;
  }

  public A withBounds(io.sundr.model.TypeRef... bounds) {
    if (this.bounds != null) {
      this.bounds.clear();
    }
    if (bounds != null) {
      for (io.sundr.model.TypeRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasBounds() {
    return bounds != null && !bounds.isEmpty();
  }

  public A addToTypeParamRefBounds(java.lang.Integer index, io.sundr.model.TypeParamRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToTypeParamRefBounds(java.lang.Integer index, io.sundr.model.TypeParamRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
    if (index < 0 || index >= _visitables.get("bounds").size()) {
      _visitables.get("bounds").add(builder);
    } else {
      _visitables.get("bounds").set(index, builder);
    }
    if (index < 0 || index >= bounds.size()) {
      bounds.add(builder);
    } else {
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToTypeParamRefBounds(io.sundr.model.TypeParamRef... items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToTypeParamRefBounds(java.util.Collection<io.sundr.model.TypeParamRef> items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromTypeParamRefBounds(io.sundr.model.TypeParamRef... items) {
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromTypeParamRefBounds(java.util.Collection<io.sundr.model.TypeParamRef> items) {
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromTypeParamRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound() {
    return new WildcardRefFluentImpl.TypeParamRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(
      io.sundr.model.TypeParamRef item) {
    return new WildcardRefFluentImpl.TypeParamRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(java.lang.Integer index,
      io.sundr.model.TypeParamRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.TypeParamRefBoundsNestedImpl(index, item);
  }

  public A addToWildcardRefBounds(java.lang.Integer index, io.sundr.model.WildcardRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToWildcardRefBounds(java.lang.Integer index, io.sundr.model.WildcardRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
    if (index < 0 || index >= _visitables.get("bounds").size()) {
      _visitables.get("bounds").add(builder);
    } else {
      _visitables.get("bounds").set(index, builder);
    }
    if (index < 0 || index >= bounds.size()) {
      bounds.add(builder);
    } else {
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToWildcardRefBounds(io.sundr.model.WildcardRef... items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToWildcardRefBounds(java.util.Collection<io.sundr.model.WildcardRef> items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromWildcardRefBounds(io.sundr.model.WildcardRef... items) {
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromWildcardRefBounds(java.util.Collection<io.sundr.model.WildcardRef> items) {
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromWildcardRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound() {
    return new WildcardRefFluentImpl.WildcardRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(
      io.sundr.model.WildcardRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.WildcardRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(java.lang.Integer index,
      io.sundr.model.WildcardRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.WildcardRefBoundsNestedImpl(index, item);
  }

  public A addToClassRefBounds(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToClassRefBounds(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    if (index < 0 || index >= _visitables.get("bounds").size()) {
      _visitables.get("bounds").add(builder);
    } else {
      _visitables.get("bounds").set(index, builder);
    }
    if (index < 0 || index >= bounds.size()) {
      bounds.add(builder);
    } else {
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToClassRefBounds(io.sundr.model.ClassRef... items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToClassRefBounds(java.util.Collection<io.sundr.model.ClassRef> items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromClassRefBounds(io.sundr.model.ClassRef... items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromClassRefBounds(java.util.Collection<io.sundr.model.ClassRef> items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromClassRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound() {
    return new WildcardRefFluentImpl.ClassRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.ClassRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(java.lang.Integer index,
      io.sundr.model.ClassRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.ClassRefBoundsNestedImpl(index, item);
  }

  public A addToPrimitiveRefBounds(java.lang.Integer index, io.sundr.model.PrimitiveRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToPrimitiveRefBounds(java.lang.Integer index, io.sundr.model.PrimitiveRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
    if (index < 0 || index >= _visitables.get("bounds").size()) {
      _visitables.get("bounds").add(builder);
    } else {
      _visitables.get("bounds").set(index, builder);
    }
    if (index < 0 || index >= bounds.size()) {
      bounds.add(builder);
    } else {
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToPrimitiveRefBounds(java.util.Collection<io.sundr.model.PrimitiveRef> items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items) {
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromPrimitiveRefBounds(java.util.Collection<io.sundr.model.PrimitiveRef> items) {
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromPrimitiveRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound() {
    return new WildcardRefFluentImpl.PrimitiveRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(
      io.sundr.model.PrimitiveRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.PrimitiveRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(java.lang.Integer index,
      io.sundr.model.PrimitiveRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.PrimitiveRefBoundsNestedImpl(index, item);
  }

  public A addToVoidRefBounds(java.lang.Integer index, io.sundr.model.VoidRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToVoidRefBounds(java.lang.Integer index, io.sundr.model.VoidRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
    if (index < 0 || index >= _visitables.get("bounds").size()) {
      _visitables.get("bounds").add(builder);
    } else {
      _visitables.get("bounds").set(index, builder);
    }
    if (index < 0 || index >= bounds.size()) {
      bounds.add(builder);
    } else {
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToVoidRefBounds(io.sundr.model.VoidRef... items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToVoidRefBounds(java.util.Collection<io.sundr.model.VoidRef> items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromVoidRefBounds(io.sundr.model.VoidRef... items) {
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromVoidRefBounds(java.util.Collection<io.sundr.model.VoidRef> items) {
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromVoidRefBounds(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound() {
    return new WildcardRefFluentImpl.VoidRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(io.sundr.model.VoidRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.VoidRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(java.lang.Integer index,
      io.sundr.model.VoidRef item) {
    return new io.sundr.model.WildcardRefFluentImpl.VoidRefBoundsNestedImpl(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    WildcardRefFluentImpl that = (WildcardRefFluentImpl) o;
    if (boundKind != null ? !boundKind.equals(that.boundKind) : that.boundKind != null)
      return false;
    if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(boundKind, bounds, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (boundKind != null) {
      sb.append("boundKind:");
      sb.append(boundKind + ",");
    }
    if (bounds != null && !bounds.isEmpty()) {
      sb.append("bounds:");
      sb.append(bounds);
    }
    sb.append("}");
    return sb.toString();
  }

  class TypeParamRefBoundsNestedImpl<N> extends TypeParamRefFluentImpl<WildcardRefFluent.TypeParamRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<N>, Nested<N> {
    TypeParamRefBoundsNestedImpl(java.lang.Integer index, io.sundr.model.TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.TypeParamRefBuilder(this);
    }

    io.sundr.model.TypeParamRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endTypeParamRefBound() {
      return and();
    }

  }

  class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefFluent.WildcardRefBoundsNested<N>>
      implements WildcardRefFluent.WildcardRefBoundsNested<N>, io.sundr.builder.Nested<N> {
    WildcardRefBoundsNestedImpl(java.lang.Integer index, io.sundr.model.WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.WildcardRefBuilder(this);
    }

    io.sundr.model.WildcardRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endWildcardRefBound() {
      return and();
    }

  }

  class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<WildcardRefFluent.ClassRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<N>, io.sundr.builder.Nested<N> {
    ClassRefBoundsNestedImpl(java.lang.Integer index, io.sundr.model.ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endClassRefBound() {
      return and();
    }

  }

  class PrimitiveRefBoundsNestedImpl<N> extends PrimitiveRefFluentImpl<WildcardRefFluent.PrimitiveRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<N>, io.sundr.builder.Nested<N> {
    PrimitiveRefBoundsNestedImpl(java.lang.Integer index, io.sundr.model.PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.PrimitiveRefBuilder(this);
    }

    io.sundr.model.PrimitiveRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endPrimitiveRefBound() {
      return and();
    }

  }

  class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<WildcardRefFluent.VoidRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<N>, io.sundr.builder.Nested<N> {
    VoidRefBoundsNestedImpl(java.lang.Integer index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.VoidRefBuilder(this);
    }

    io.sundr.model.VoidRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endVoidRefBound() {
      return and();
    }

  }

}
