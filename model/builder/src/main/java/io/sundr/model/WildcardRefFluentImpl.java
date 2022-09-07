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

  public WildcardRefFluentImpl(WildcardRef instance) {
    this.withBoundKind(instance.getBoundKind());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
  }

  private WildcardRef.BoundKind boundKind;
  private ArrayList<VisitableBuilder<? extends TypeRef, ?>> bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();

  public WildcardRef.BoundKind getBoundKind() {
    return this.boundKind;
  }

  public A withBoundKind(WildcardRef.BoundKind boundKind) {
    this.boundKind = boundKind;
    return (A) this;
  }

  public Boolean hasBoundKind() {
    return this.boundKind != null;
  }

  public A addToBounds(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("bounds").add(builder);
    this.bounds.add(builder);
    return (A) this;
  }

  public A addToBounds(Integer index, VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("bounds").add(index, builder);
    this.bounds.add(index, builder);
    return (A) this;
  }

  public A addToBounds(Integer index, TypeRef item) {
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

  public A setToBounds(Integer index, TypeRef item) {
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

  public A addToBounds(io.sundr.model.TypeRef... items) {
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

  public A removeFromBounds(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("bounds").remove(builder);
    this.bounds.remove(builder);
    return (A) this;
  }

  public A removeFromBounds(io.sundr.model.TypeRef... items) {
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

  public TypeRef buildBound(Integer index) {
    return this.bounds.get(index).build();
  }

  public TypeRef buildFirstBound() {
    return this.bounds.get(0).build();
  }

  public TypeRef buildLastBound() {
    return this.bounds.get(bounds.size() - 1).build();
  }

  public TypeRef buildMatchingBound(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingBound(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withBounds(List<TypeRef> bounds) {
    if (bounds != null) {
      this.bounds = new ArrayList();
      for (TypeRef item : bounds) {
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
      for (TypeRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public Boolean hasBounds() {
    return bounds != null && !bounds.isEmpty();
  }

  public A addToTypeParamRefBounds(Integer index, TypeParamRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToTypeParamRefBounds(Integer index, TypeParamRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A addToTypeParamRefBounds(io.sundr.model.TypeParamRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromTypeParamRefBounds(io.sundr.model.TypeParamRef... items) {
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

  public A removeMatchingFromTypeParamRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
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

  public WildcardRefFluent.TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item) {
    return new WildcardRefFluentImpl.TypeParamRefBoundsNestedImpl(-1, item);
  }

  public WildcardRefFluent.TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(Integer index, TypeParamRef item) {
    return new WildcardRefFluentImpl.TypeParamRefBoundsNestedImpl(index, item);
  }

  public A addToWildcardRefBounds(Integer index, WildcardRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new WildcardRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToWildcardRefBounds(Integer index, WildcardRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A addToWildcardRefBounds(io.sundr.model.WildcardRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromWildcardRefBounds(io.sundr.model.WildcardRef... items) {
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

  public A removeMatchingFromWildcardRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
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

  public WildcardRefFluent.WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item) {
    return new WildcardRefFluentImpl.WildcardRefBoundsNestedImpl(-1, item);
  }

  public WildcardRefFluent.WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(Integer index, WildcardRef item) {
    return new WildcardRefFluentImpl.WildcardRefBoundsNestedImpl(index, item);
  }

  public A addToClassRefBounds(Integer index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToClassRefBounds(Integer index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A addToClassRefBounds(io.sundr.model.ClassRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromClassRefBounds(io.sundr.model.ClassRef... items) {
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

  public A removeMatchingFromClassRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
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

  public WildcardRefFluent.ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item) {
    return new WildcardRefFluentImpl.ClassRefBoundsNestedImpl(-1, item);
  }

  public WildcardRefFluent.ClassRefBoundsNested<A> setNewClassRefBoundLike(Integer index, ClassRef item) {
    return new WildcardRefFluentImpl.ClassRefBoundsNestedImpl(index, item);
  }

  public A addToPrimitiveRefBounds(Integer index, PrimitiveRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToPrimitiveRefBounds(Integer index, PrimitiveRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A addToPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromPrimitiveRefBounds(io.sundr.model.PrimitiveRef... items) {
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

  public A removeMatchingFromPrimitiveRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
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

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item) {
    return new WildcardRefFluentImpl.PrimitiveRefBoundsNestedImpl(-1, item);
  }

  public WildcardRefFluent.PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(Integer index, PrimitiveRef item) {
    return new WildcardRefFluentImpl.PrimitiveRefBoundsNestedImpl(index, item);
  }

  public A addToVoidRefBounds(Integer index, VoidRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VoidRefBuilder builder = new VoidRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToVoidRefBounds(Integer index, VoidRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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

  public A addToVoidRefBounds(io.sundr.model.VoidRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
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
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromVoidRefBounds(io.sundr.model.VoidRef... items) {
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

  public A removeMatchingFromVoidRefBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
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

  public WildcardRefFluent.VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item) {
    return new WildcardRefFluentImpl.VoidRefBoundsNestedImpl(-1, item);
  }

  public WildcardRefFluent.VoidRefBoundsNested<A> setNewVoidRefBoundLike(Integer index, VoidRef item) {
    return new WildcardRefFluentImpl.VoidRefBoundsNestedImpl(index, item);
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
      implements WildcardRefFluent.TypeParamRefBoundsNested<N>, Nested<N> {
    TypeParamRefBoundsNestedImpl(Integer index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new TypeParamRefBuilder(this);
    }

    TypeParamRefBuilder builder;
    Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endTypeParamRefBound() {
      return and();
    }

  }

  class WildcardRefBoundsNestedImpl<N> extends WildcardRefFluentImpl<WildcardRefFluent.WildcardRefBoundsNested<N>>
      implements WildcardRefFluent.WildcardRefBoundsNested<N>, Nested<N> {
    WildcardRefBoundsNestedImpl(Integer index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new WildcardRefBuilder(this);
    }

    WildcardRefBuilder builder;
    Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endWildcardRefBound() {
      return and();
    }

  }

  class ClassRefBoundsNestedImpl<N> extends ClassRefFluentImpl<WildcardRefFluent.ClassRefBoundsNested<N>>
      implements WildcardRefFluent.ClassRefBoundsNested<N>, Nested<N> {
    ClassRefBoundsNestedImpl(Integer index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new ClassRefBuilder(this);
    }

    ClassRefBuilder builder;
    Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endClassRefBound() {
      return and();
    }

  }

  class PrimitiveRefBoundsNestedImpl<N> extends PrimitiveRefFluentImpl<WildcardRefFluent.PrimitiveRefBoundsNested<N>>
      implements WildcardRefFluent.PrimitiveRefBoundsNested<N>, Nested<N> {
    PrimitiveRefBoundsNestedImpl(Integer index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new PrimitiveRefBuilder(this);
    }

    PrimitiveRefBuilder builder;
    Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endPrimitiveRefBound() {
      return and();
    }

  }

  class VoidRefBoundsNestedImpl<N> extends VoidRefFluentImpl<WildcardRefFluent.VoidRefBoundsNested<N>>
      implements WildcardRefFluent.VoidRefBoundsNested<N>, Nested<N> {
    VoidRefBoundsNestedImpl(Integer index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBoundsNestedImpl() {
      this.index = -1;
      this.builder = new VoidRefBuilder(this);
    }

    VoidRefBuilder builder;
    Integer index;

    public N and() {
      return (N) WildcardRefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endVoidRefBound() {
      return and();
    }

  }

}
