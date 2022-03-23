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

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class TypeParamDefFluentImpl<A extends TypeParamDefFluent<A>> extends AttributeSupportFluentImpl<A>
    implements TypeParamDefFluent<A> {
  public TypeParamDefFluentImpl() {
  }

  public TypeParamDefFluentImpl(io.sundr.model.TypeParamDef instance) {
    this.withName(instance.getName());
    this.withBounds(instance.getBounds());
    this.withAttributes(instance.getAttributes());
  }

  private String name;
  private ArrayList<ClassRefBuilder> bounds = new java.util.ArrayList<ClassRefBuilder>();

  public java.lang.String getName() {
    return this.name;
  }

  public A withName(java.lang.String name) {
    this.name = name;
    return (A) this;
  }

  public Boolean hasName() {
    return this.name != null;
  }

  public A addToBounds(Integer index, io.sundr.model.ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    _visitables.get("bounds").add(index >= 0 ? index : _visitables.get("bounds").size(), builder);
    this.bounds.add(index >= 0 ? index : bounds.size(), builder);
    return (A) this;
  }

  public A setToBounds(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
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

  public A addToBounds(io.sundr.model.ClassRef... items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToBounds(Collection<io.sundr.model.ClassRef> items) {
    if (this.bounds == null) {
      this.bounds = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromBounds(io.sundr.model.ClassRef... items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromBounds(java.util.Collection<io.sundr.model.ClassRef> items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      if (this.bounds != null) {
        this.bounds.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromBounds(Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<io.sundr.model.ClassRefBuilder> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      io.sundr.model.ClassRefBuilder builder = each.next();
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
  public List<io.sundr.model.ClassRef> getBounds() {
    return bounds != null ? build(bounds) : null;
  }

  public java.util.List<io.sundr.model.ClassRef> buildBounds() {
    return bounds != null ? build(bounds) : null;
  }

  public io.sundr.model.ClassRef buildBound(java.lang.Integer index) {
    return this.bounds.get(index).build();
  }

  public io.sundr.model.ClassRef buildFirstBound() {
    return this.bounds.get(0).build();
  }

  public io.sundr.model.ClassRef buildLastBound() {
    return this.bounds.get(bounds.size() - 1).build();
  }

  public io.sundr.model.ClassRef buildMatchingBound(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingBound(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : bounds) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withBounds(java.util.List<io.sundr.model.ClassRef> bounds) {
    if (this.bounds != null) {
      _visitables.get("bounds").removeAll(this.bounds);
    }
    if (bounds != null) {
      this.bounds = new java.util.ArrayList();
      for (io.sundr.model.ClassRef item : bounds) {
        this.addToBounds(item);
      }
    } else {
      this.bounds = null;
    }
    return (A) this;
  }

  public A withBounds(io.sundr.model.ClassRef... bounds) {
    if (this.bounds != null) {
      this.bounds.clear();
    }
    if (bounds != null) {
      for (io.sundr.model.ClassRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasBounds() {
    return bounds != null && !bounds.isEmpty();
  }

  public TypeParamDefFluent.BoundsNested<A> addNewBound() {
    return new TypeParamDefFluentImpl.BoundsNestedImpl();
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> addNewBoundLike(io.sundr.model.ClassRef item) {
    return new TypeParamDefFluentImpl.BoundsNestedImpl(-1, item);
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> setNewBoundLike(java.lang.Integer index,
      io.sundr.model.ClassRef item) {
    return new io.sundr.model.TypeParamDefFluentImpl.BoundsNestedImpl(index, item);
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editBound(java.lang.Integer index) {
    if (bounds.size() <= index)
      throw new RuntimeException("Can't edit bounds. Index exceeds size.");
    return setNewBoundLike(index, buildBound(index));
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editFirstBound() {
    if (bounds.size() == 0)
      throw new RuntimeException("Can't edit first bounds. The list is empty.");
    return setNewBoundLike(0, buildBound(0));
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editLastBound() {
    int index = bounds.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last bounds. The list is empty.");
    return setNewBoundLike(index, buildBound(index));
  }

  public io.sundr.model.TypeParamDefFluent.BoundsNested<A> editMatchingBound(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < bounds.size(); i++) {
      if (predicate.test(bounds.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching bounds. No match found.");
    return setNewBoundLike(index, buildBound(index));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeParamDefFluentImpl that = (TypeParamDefFluentImpl) o;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(name, bounds, super.hashCode());
  }

  public java.lang.String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (name != null) {
      sb.append("name:");
      sb.append(name + ",");
    }
    if (bounds != null && !bounds.isEmpty()) {
      sb.append("bounds:");
      sb.append(bounds);
    }
    sb.append("}");
    return sb.toString();
  }

  class BoundsNestedImpl<N> extends ClassRefFluentImpl<TypeParamDefFluent.BoundsNested<N>>
      implements io.sundr.model.TypeParamDefFluent.BoundsNested<N>, Nested<N> {
    BoundsNestedImpl(java.lang.Integer index, io.sundr.model.ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    BoundsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeParamDefFluentImpl.this.setToBounds(index, builder.build());
    }

    public N endBound() {
      return and();
    }

  }

}
