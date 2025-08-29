package io.sundr.model;

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
@SuppressWarnings("unchecked")
public class WildcardRefFluent<A extends WildcardRefFluent<A>> extends TypeRefFluent<A> {
  public WildcardRefFluent() {
  }

  public WildcardRefFluent(WildcardRef instance) {
    this.copyInstance(instance);
  }

  private WildcardRef.BoundKind boundKind;
  private ArrayList<VisitableBuilder<? extends TypeRef, ?>> bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();

  protected void copyInstance(WildcardRef instance) {
    instance = (instance != null ? instance : new WildcardRef());
    if (instance != null) {
      this.withBoundKind(instance.getBoundKind());
      this.withBounds(instance.getBounds());
      this.withAttributes(instance.getAttributes());
    }
  }

  public WildcardRef.BoundKind getBoundKind() {
    return this.boundKind;
  }

  public A withBoundKind(WildcardRef.BoundKind boundKind) {
    this.boundKind = boundKind;
    return (A) this;
  }

  public boolean hasBoundKind() {
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

  public A addToBounds(int index, VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    if (index < 0 || index >= bounds.size()) {
      _visitables.get("bounds").add(builder);
      bounds.add(builder);
    } else {
      _visitables.get("bounds").add(builder);
      bounds.add(index, builder);
    }
    return (A) this;
  }

  public A addToBounds(int index, TypeRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
    if (index < 0 || index >= bounds.size()) {
      _visitables.get("bounds").add(builder);
      bounds.add(builder);
    } else {
      _visitables.get("bounds").add(builder);
      bounds.add(index, builder);
    }
    return (A) this;
  }

  public A setToBounds(int index, TypeRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
    if (index < 0 || index >= bounds.size()) {
      _visitables.get("bounds").add(builder);
      bounds.add(builder);
    } else {
      _visitables.get("bounds").add(builder);
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToBounds(io.sundr.model.TypeRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToBounds(Collection<TypeRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromBounds(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.bounds == null)
      return (A) this;
    _visitables.get("bounds").remove(builder);
    this.bounds.remove(builder);
    return (A) this;
  }

  public A removeFromBounds(io.sundr.model.TypeRef... items) {
    if (this.bounds == null)
      return (A) this;
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("bounds").remove(builder);
      this.bounds.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromBounds(Collection<TypeRef> items) {
    if (this.bounds == null)
      return (A) this;
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("bounds").remove(builder);
      this.bounds.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromBounds(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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

  public TypeRef buildMatchingBound(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingBound(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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
      _visitables.remove("bounds");
    }
    if (bounds != null) {
      for (TypeRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public boolean hasBounds() {
    return this.bounds != null && !(this.bounds.isEmpty());
  }

  public ClassRefBoundsNested<A> addNewClassRefBound() {
    return new ClassRefBoundsNested(-1, null);
  }

  public ClassRefBoundsNested<A> addNewClassRefBoundLike(ClassRef item) {
    return new ClassRefBoundsNested(-1, item);
  }

  public ClassRefBoundsNested<A> setNewClassRefBoundLike(int index, ClassRef item) {
    return new ClassRefBoundsNested(index, item);
  }

  public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBound() {
    return new PrimitiveRefBoundsNested(-1, null);
  }

  public PrimitiveRefBoundsNested<A> addNewPrimitiveRefBoundLike(PrimitiveRef item) {
    return new PrimitiveRefBoundsNested(-1, item);
  }

  public PrimitiveRefBoundsNested<A> setNewPrimitiveRefBoundLike(int index, PrimitiveRef item) {
    return new PrimitiveRefBoundsNested(index, item);
  }

  public VoidRefBoundsNested<A> addNewVoidRefBound() {
    return new VoidRefBoundsNested(-1, null);
  }

  public VoidRefBoundsNested<A> addNewVoidRefBoundLike(VoidRef item) {
    return new VoidRefBoundsNested(-1, item);
  }

  public VoidRefBoundsNested<A> setNewVoidRefBoundLike(int index, VoidRef item) {
    return new VoidRefBoundsNested(index, item);
  }

  public TypeParamRefBoundsNested<A> addNewTypeParamRefBound() {
    return new TypeParamRefBoundsNested(-1, null);
  }

  public TypeParamRefBoundsNested<A> addNewTypeParamRefBoundLike(TypeParamRef item) {
    return new TypeParamRefBoundsNested(-1, item);
  }

  public TypeParamRefBoundsNested<A> setNewTypeParamRefBoundLike(int index, TypeParamRef item) {
    return new TypeParamRefBoundsNested(index, item);
  }

  public WildcardRefBoundsNested<A> addNewWildcardRefBound() {
    return new WildcardRefBoundsNested(-1, null);
  }

  public WildcardRefBoundsNested<A> addNewWildcardRefBoundLike(WildcardRef item) {
    return new WildcardRefBoundsNested(-1, item);
  }

  public WildcardRefBoundsNested<A> setNewWildcardRefBoundLike(int index, WildcardRef item) {
    return new WildcardRefBoundsNested(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    WildcardRefFluent that = (WildcardRefFluent) o;
    if (!java.util.Objects.equals(boundKind, that.boundKind))
      return false;
    if (!java.util.Objects.equals(bounds, that.bounds))
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

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "io.sundr.model." + "ClassRef":
        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);
      case "io.sundr.model." + "PrimitiveRef":
        return (VisitableBuilder<T, ?>) new PrimitiveRefBuilder((PrimitiveRef) item);
      case "io.sundr.model." + "VoidRef":
        return (VisitableBuilder<T, ?>) new VoidRefBuilder((VoidRef) item);
      case "io.sundr.model." + "TypeParamRef":
        return (VisitableBuilder<T, ?>) new TypeParamRefBuilder((TypeParamRef) item);
      case "io.sundr.model." + "WildcardRef":
        return (VisitableBuilder<T, ?>) new WildcardRefBuilder((WildcardRef) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class ClassRefBoundsNested<N> extends ClassRefFluent<ClassRefBoundsNested<N>> implements Nested<N> {
    ClassRefBoundsNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;
    int index;

    public N and() {
      return (N) WildcardRefFluent.this.setToBounds(index, builder.build());
    }

    public N endClassRefBound() {
      return and();
    }

  }

  public class PrimitiveRefBoundsNested<N> extends PrimitiveRefFluent<PrimitiveRefBoundsNested<N>> implements Nested<N> {
    PrimitiveRefBoundsNested(int index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBuilder builder;
    int index;

    public N and() {
      return (N) WildcardRefFluent.this.setToBounds(index, builder.build());
    }

    public N endPrimitiveRefBound() {
      return and();
    }

  }

  public class VoidRefBoundsNested<N> extends VoidRefFluent<VoidRefBoundsNested<N>> implements Nested<N> {
    VoidRefBoundsNested(int index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBuilder builder;
    int index;

    public N and() {
      return (N) WildcardRefFluent.this.setToBounds(index, builder.build());
    }

    public N endVoidRefBound() {
      return and();
    }

  }

  public class TypeParamRefBoundsNested<N> extends TypeParamRefFluent<TypeParamRefBoundsNested<N>> implements Nested<N> {
    TypeParamRefBoundsNested(int index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBuilder builder;
    int index;

    public N and() {
      return (N) WildcardRefFluent.this.setToBounds(index, builder.build());
    }

    public N endTypeParamRefBound() {
      return and();
    }

  }

  public class WildcardRefBoundsNested<N> extends WildcardRefFluent<WildcardRefBoundsNested<N>> implements Nested<N> {
    WildcardRefBoundsNested(int index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBuilder builder;
    int index;

    public N and() {
      return (N) WildcardRefFluent.this.setToBounds(index, builder.build());
    }

    public N endWildcardRefBound() {
      return and();
    }

  }

}
