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

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class TypeParamDefFluent<A extends TypeParamDefFluent<A>> extends AttributeSupportFluent<A> {
  public TypeParamDefFluent() {
  }

  public TypeParamDefFluent(TypeParamDef instance) {
    this.copyInstance(instance);
  }

  private String name;
  private ArrayList<ClassRefBuilder> bounds = new ArrayList<ClassRefBuilder>();

  protected void copyInstance(TypeParamDef instance) {
    if (instance != null) {
      this.withName(instance.getName());
      this.withBounds(instance.getBounds());
      this.withAttributes(instance.getAttributes());
    }
  }

  public String getName() {
    return this.name;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public boolean hasName() {
    return this.name != null;
  }

  public A addToBounds(int index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= bounds.size()) {
      _visitables.get("bounds").add(builder);
      bounds.add(builder);
    } else {
      _visitables.get("bounds").add(builder);
      bounds.add(index, builder);
    }
    return (A) this;
  }

  public A setToBounds(int index, ClassRef item) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= bounds.size()) {
      _visitables.get("bounds").add(builder);
      bounds.add(builder);
    } else {
      _visitables.get("bounds").add(builder);
      bounds.set(index, builder);
    }
    return (A) this;
  }

  public A addToBounds(io.sundr.model.ClassRef... items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A addAllToBounds(Collection<ClassRef> items) {
    if (this.bounds == null) {
      this.bounds = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").add(builder);
      this.bounds.add(builder);
    }
    return (A) this;
  }

  public A removeFromBounds(io.sundr.model.ClassRef... items) {
    if (this.bounds == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      this.bounds.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromBounds(Collection<ClassRef> items) {
    if (this.bounds == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("bounds").remove(builder);
      this.bounds.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromBounds(Predicate<ClassRefBuilder> predicate) {
    if (bounds == null)
      return (A) this;
    final Iterator<ClassRefBuilder> each = bounds.iterator();
    final List visitables = _visitables.get("bounds");
    while (each.hasNext()) {
      ClassRefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<ClassRef> buildBounds() {
    return this.bounds != null ? build(bounds) : null;
  }

  public ClassRef buildBound(int index) {
    return this.bounds.get(index).build();
  }

  public ClassRef buildFirstBound() {
    return this.bounds.get(0).build();
  }

  public ClassRef buildLastBound() {
    return this.bounds.get(bounds.size() - 1).build();
  }

  public ClassRef buildMatchingBound(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : bounds) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingBound(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : bounds) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withBounds(List<ClassRef> bounds) {
    if (this.bounds != null) {
      this._visitables.get("bounds").clear();
    }
    if (bounds != null) {
      this.bounds = new ArrayList();
      for (ClassRef item : bounds) {
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
      _visitables.remove("bounds");
    }
    if (bounds != null) {
      for (ClassRef item : bounds) {
        this.addToBounds(item);
      }
    }
    return (A) this;
  }

  public boolean hasBounds() {
    return this.bounds != null && !(this.bounds.isEmpty());
  }

  public BoundsNested<A> addNewBound() {
    return new BoundsNested(-1, null);
  }

  public BoundsNested<A> addNewBoundLike(ClassRef item) {
    return new BoundsNested(-1, item);
  }

  public BoundsNested<A> setNewBoundLike(int index, ClassRef item) {
    return new BoundsNested(index, item);
  }

  public BoundsNested<A> editBound(int index) {
    if (bounds.size() <= index)
      throw new RuntimeException("Can't edit bounds. Index exceeds size.");
    return setNewBoundLike(index, buildBound(index));
  }

  public BoundsNested<A> editFirstBound() {
    if (bounds.size() == 0)
      throw new RuntimeException("Can't edit first bounds. The list is empty.");
    return setNewBoundLike(0, buildBound(0));
  }

  public BoundsNested<A> editLastBound() {
    int index = bounds.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last bounds. The list is empty.");
    return setNewBoundLike(index, buildBound(index));
  }

  public BoundsNested<A> editMatchingBound(Predicate<ClassRefBuilder> predicate) {
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
    TypeParamDefFluent that = (TypeParamDefFluent) o;
    if (!java.util.Objects.equals(name, that.name))
      return false;
    if (!java.util.Objects.equals(bounds, that.bounds))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(name, bounds, super.hashCode());
  }

  public String toString() {
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

  public class BoundsNested<N> extends ClassRefFluent<BoundsNested<N>> implements Nested<N> {
    BoundsNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;
    int index;

    public N and() {
      return (N) TypeParamDefFluent.this.setToBounds(index, builder.build());
    }

    public N endBound() {
      return and();
    }

  }

}
