package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.model.builder.Nested;
import io.sundr.model.builder.VisitableBuilder;

public class WildcardRefFluentImpl<A extends WildcardRefFluent<A>> extends TypeRefFluentImpl<A>
    implements WildcardRefFluent<A> {

  private io.sundr.model.WildcardRef.BoundKind boundKind;
  private List<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> bounds;

  public WildcardRefFluentImpl() {
  }

  public WildcardRefFluentImpl(WildcardRef instance) {
    this.withBoundKind(instance.getBoundKind());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
  }

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

  public A addToBounds(io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("bounds").add(builder);
    this.bounds.add(builder);
    return (A) this;
  }

  public A addToBounds(int index, io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("bounds").add(index, builder);
    this.bounds.add(index, builder);
    return (A) this;
  }

  public A addToBounds(int index, TypeRef item) {
    if (item instanceof TypeParamRef) {
      addToTypeParamRefBounds(index, (TypeParamRef) item);
    } else if (item instanceof WildcardRef) {
      addToWildcardRefBounds(index, (WildcardRef) item);
    } else if (item instanceof ClassRef) {
      addToClassRefBounds(index, (ClassRef) item);
    } else if (item instanceof PrimitiveRef) {
      addToPrimitiveRefBounds(index, (PrimitiveRef) item);
    } else if (item instanceof VoidRef) {
      addToVoidRefBounds(index, (VoidRef) item);
    }

    return (A) this;
  }

  public A setToBounds(int index, TypeRef item) {
    if (item instanceof TypeParamRef) {
      setToTypeParamRefBounds(index, (TypeParamRef) item);
    } else if (item instanceof WildcardRef) {
      setToWildcardRefBounds(index, (WildcardRef) item);
    } else if (item instanceof ClassRef) {
      setToClassRefBounds(index, (ClassRef) item);
    } else if (item instanceof PrimitiveRef) {
      setToPrimitiveRefBounds(index, (PrimitiveRef) item);
    } else if (item instanceof VoidRef) {
      setToVoidRefBounds(index, (VoidRef) item);
    }

    return (A) this;
  }

  public A addToBounds(TypeRef... items) {
    if (items != null && items.length > 0 && this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        addToTypeParamRefBounds((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        addToWildcardRefBounds((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        addToClassRefBounds((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        addToPrimitiveRefBounds((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        addToVoidRefBounds((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").add(builder);
        this.bounds.add(builder);
      }
    }
    return (A) this;
  }

  public A addAllToBounds(Collection<TypeRef> items) {
    if (items != null && items.size() > 0 && this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        addToTypeParamRefBounds((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        addToWildcardRefBounds((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        addToClassRefBounds((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        addToPrimitiveRefBounds((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        addToVoidRefBounds((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").add(builder);
        this.bounds.add(builder);
      }
    }
    return (A) this;
  }

  public A removeFromBounds(io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("bounds").remove(builder);
    this.bounds.remove(builder);
    return (A) this;
  }

  public A removeFromBounds(TypeRef... items) {
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        removeFromTypeParamRefBounds((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        removeFromWildcardRefBounds((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        removeFromClassRefBounds((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        removeFromPrimitiveRefBounds((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        removeFromVoidRefBounds((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").remove(builder);
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromBounds(Collection<TypeRef> items) {
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        removeFromTypeParamRefBounds((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        removeFromWildcardRefBounds((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        removeFromClassRefBounds((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        removeFromPrimitiveRefBounds((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        removeFromVoidRefBounds((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("bounds").remove(builder);
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromBounds(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
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
  public List<TypeRef> getBounds() {
    return build(bounds);
  }

  public List<TypeRef> buildBounds() {
    return build(bounds);
  }

  public TypeRef buildBound(int index) {
    return this.bounds.get(index).build();
  }

  public TypeRef buildFirstBound() {
    return this.bounds.get(0).build();
  }

  public TypeRef buildLastBound() {
    return this.bounds.get(bounds.size() - 1).build();
  }

  public TypeRef buildMatchingBound(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingBound(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withBounds(List<TypeRef> bounds) {
    if (this.bounds != null) {
      _visitables.get("bounds").removeAll(this.bounds);
    }
    if (bounds != null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
      for (TypeRef item : bounds) {
        this.addToBounds(item);
      }
    } else {
      this.bounds = null;
    }
    return (A) this;
  }

  public A withBounds(TypeRef... bounds) {
    if (this.bounds != null) {
      this.bounds.clear();
    }
    if (bounds != null) {
      for (TypeRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public Boolean hasBounds() {
    return bounds != null && !bounds.isEmpty();
  }

  public A addToTypeParamRefBounds(int index, TypeParamRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToTypeParamRefBounds(int index, TypeParamRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
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

  public A addToTypeParamRefBounds(TypeParamRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToTypeParamRefBounds(Collection<TypeParamRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromTypeParamRefBounds(TypeParamRef... items) {
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromTypeParamRefBounds(Collection<TypeParamRef> items) {
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromTypeParamRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBound() {
    return new TypeParamRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item) {
    return new TypeParamRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(int index,
      TypeParamRef item) {
    return new TypeParamRefBoundsNestedImpl(index, item);
  }

  public A addToWildcardRefBounds(int index, WildcardRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new WildcardRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToWildcardRefBounds(int index, WildcardRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new WildcardRefBuilder(item);
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

  public A addToWildcardRefBounds(WildcardRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToWildcardRefBounds(Collection<WildcardRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromWildcardRefBounds(WildcardRef... items) {
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromWildcardRefBounds(Collection<WildcardRef> items) {
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromWildcardRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBound() {
    return new WildcardRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item) {
    return new WildcardRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(int index, WildcardRef item) {
    return new WildcardRefBoundsNestedImpl(index, item);
  }

  public A addToClassRefBounds(int index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToClassRefBounds(int index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
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

  public A addToClassRefBounds(ClassRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToClassRefBounds(Collection<ClassRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromClassRefBounds(ClassRef... items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromClassRefBounds(Collection<ClassRef> items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromClassRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBound() {
    return new ClassRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item) {
    return new ClassRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(int index, ClassRef item) {
    return new ClassRefBoundsNestedImpl(index, item);
  }

  public A addToPrimitiveRefBounds(int index, PrimitiveRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToPrimitiveRefBounds(int index, PrimitiveRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
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

  public A addToPrimitiveRefBounds(PrimitiveRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToPrimitiveRefBounds(Collection<PrimitiveRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromPrimitiveRefBounds(PrimitiveRef... items) {
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromPrimitiveRefBounds(Collection<PrimitiveRef> items) {
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromPrimitiveRefBounds(
      Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound() {
    return new PrimitiveRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item) {
    return new PrimitiveRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(int index,
      PrimitiveRef item) {
    return new PrimitiveRefBoundsNestedImpl(index, item);
  }

  public A addToVoidRefBounds(int index, VoidRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    VoidRefBuilder builder = new VoidRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToVoidRefBounds(int index, VoidRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    VoidRefBuilder builder = new VoidRefBuilder(item);
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

  public A addToVoidRefBounds(VoidRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToVoidRefBounds(Collection<VoidRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromVoidRefBounds(VoidRef... items) {
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromVoidRefBounds(Collection<VoidRef> items) {
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromVoidRefBounds(Predicate<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.builder.VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBound() {
    return new VoidRefBoundsNestedImpl();
  }

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item) {
    return new VoidRefBoundsNestedImpl(-1, item);
  }

  public io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(int index, VoidRef item) {
    return new VoidRefBoundsNestedImpl(index, item);
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

  public class TypeParamRefBoundsNestedImpl<N>
      extends TypeParamRefFluentImpl<io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.TypeParamRefBoundsNested<N>, io.sundr.model.builder.Nested<N> {
    private final TypeParamRefBuilder builder;
    private final int index;

    TypeParamRefBoundsNestedImpl(int index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);

    }

    TypeParamRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new TypeParamRefBuilder(this);

    }

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endTypeParamRefBound() {
      return and();
    }
  }

  public class WildcardRefBoundsNestedImpl<N>
      extends WildcardRefFluentImpl<io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.WildcardRefBoundsNested<N>, io.sundr.model.builder.Nested<N> {
    private final WildcardRefBuilder builder;
    private final int index;

    WildcardRefBoundsNestedImpl(int index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);

    }

    WildcardRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new WildcardRefBuilder(this);

    }

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endWildcardRefBound() {
      return and();
    }
  }

  public class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.ClassRefBoundsNested<N>, io.sundr.model.builder.Nested<N> {
    private final ClassRefBuilder builder;
    private final int index;

    ClassRefBoundsNestedImpl(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);

    }

    ClassRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new ClassRefBuilder(this);

    }

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endClassRefBound() {
      return and();
    }
  }

  public class PrimitiveRefBoundsNestedImpl<N>
      extends PrimitiveRefFluentImpl<io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.PrimitiveRefBoundsNested<N>, io.sundr.model.builder.Nested<N> {
    private final PrimitiveRefBuilder builder;
    private final int index;

    PrimitiveRefBoundsNestedImpl(int index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);

    }

    PrimitiveRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new PrimitiveRefBuilder(this);

    }

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endPrimitiveRefBound() {
      return and();
    }
  }

  public class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<N>>
      implements io.sundr.model.WildcardRefFluent.VoidRefBoundsNested<N>, io.sundr.model.builder.Nested<N> {
    private final VoidRefBuilder builder;
    private final int index;

    VoidRefBoundsNestedImpl(int index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);

    }

    VoidRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new VoidRefBuilder(this);

    }

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endVoidRefBound() {
      return and();
    }
  }

}
