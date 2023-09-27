package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class SourceFluent<A extends SourceFluent<A>> extends BaseFluent<A> {
  public SourceFluent() {
  }

  public SourceFluent(Source instance) {
    this.copyInstance(instance);
  }

  private ArrayList<TypeDefBuilder> types = new ArrayList<TypeDefBuilder>();

  protected void copyInstance(Source instance) {
    instance = (instance != null ? instance : new Source());
    if (instance != null) {
      this.withTypes(instance.getTypes());
    }
  }

  public A addToTypes(int index, TypeDef item) {
    if (this.types == null) {
      this.types = new ArrayList<TypeDefBuilder>();
    }
    TypeDefBuilder builder = new TypeDefBuilder(item);
    if (index < 0 || index >= types.size()) {
      _visitables.get("types").add(builder);
      types.add(builder);
    } else {
      _visitables.get("types").add(index, builder);
      types.add(index, builder);
    }
    return (A) this;
  }

  public A setToTypes(int index, TypeDef item) {
    if (this.types == null) {
      this.types = new ArrayList<TypeDefBuilder>();
    }
    TypeDefBuilder builder = new TypeDefBuilder(item);
    if (index < 0 || index >= types.size()) {
      _visitables.get("types").add(builder);
      types.add(builder);
    } else {
      _visitables.get("types").set(index, builder);
      types.set(index, builder);
    }
    return (A) this;
  }

  public A addToTypes(io.sundr.model.TypeDef... items) {
    if (this.types == null) {
      this.types = new ArrayList<TypeDefBuilder>();
    }
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("types").add(builder);
      this.types.add(builder);
    }
    return (A) this;
  }

  public A addAllToTypes(Collection<TypeDef> items) {
    if (this.types == null) {
      this.types = new ArrayList<TypeDefBuilder>();
    }
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("types").add(builder);
      this.types.add(builder);
    }
    return (A) this;
  }

  public A removeFromTypes(io.sundr.model.TypeDef... items) {
    if (this.types == null)
      return (A) this;
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("types").remove(builder);
      this.types.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromTypes(Collection<TypeDef> items) {
    if (this.types == null)
      return (A) this;
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("types").remove(builder);
      this.types.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromTypes(Predicate<TypeDefBuilder> predicate) {
    if (types == null)
      return (A) this;
    final Iterator<TypeDefBuilder> each = types.iterator();
    final List visitables = _visitables.get("types");
    while (each.hasNext()) {
      TypeDefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<TypeDef> buildTypes() {
    return types != null ? build(types) : null;
  }

  public TypeDef buildType(int index) {
    return this.types.get(index).build();
  }

  public TypeDef buildFirstType() {
    return this.types.get(0).build();
  }

  public TypeDef buildLastType() {
    return this.types.get(types.size() - 1).build();
  }

  public TypeDef buildMatchingType(Predicate<TypeDefBuilder> predicate) {
    for (TypeDefBuilder item : types) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingType(Predicate<TypeDefBuilder> predicate) {
    for (TypeDefBuilder item : types) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withTypes(List<TypeDef> types) {
    if (this.types != null) {
      _visitables.get("types").clear();
    }
    if (types != null) {
      this.types = new ArrayList();
      for (TypeDef item : types) {
        this.addToTypes(item);
      }
    } else {
      this.types = null;
    }
    return (A) this;
  }

  public A withTypes(io.sundr.model.TypeDef... types) {
    if (this.types != null) {
      this.types.clear();
      _visitables.remove("types");
    }
    if (types != null) {
      for (TypeDef item : types) {
        this.addToTypes(item);
      }
    }
    return (A) this;
  }

  public boolean hasTypes() {
    return types != null && !types.isEmpty();
  }

  public A addNewType(String fullyQualifiedName) {
    return (A) addToTypes(new TypeDef(fullyQualifiedName));
  }

  public TypesNested<A> addNewType() {
    return new TypesNested(-1, null);
  }

  public TypesNested<A> addNewTypeLike(TypeDef item) {
    return new TypesNested(-1, item);
  }

  public TypesNested<A> setNewTypeLike(int index, TypeDef item) {
    return new TypesNested(index, item);
  }

  public TypesNested<A> editType(int index) {
    if (types.size() <= index)
      throw new RuntimeException("Can't edit types. Index exceeds size.");
    return setNewTypeLike(index, buildType(index));
  }

  public TypesNested<A> editFirstType() {
    if (types.size() == 0)
      throw new RuntimeException("Can't edit first types. The list is empty.");
    return setNewTypeLike(0, buildType(0));
  }

  public TypesNested<A> editLastType() {
    int index = types.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last types. The list is empty.");
    return setNewTypeLike(index, buildType(index));
  }

  public TypesNested<A> editMatchingType(Predicate<TypeDefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < types.size(); i++) {
      if (predicate.test(types.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching types. No match found.");
    return setNewTypeLike(index, buildType(index));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    SourceFluent that = (SourceFluent) o;
    if (!java.util.Objects.equals(types, that.types))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(types, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (types != null && !types.isEmpty()) {
      sb.append("types:");
      sb.append(types);
    }
    sb.append("}");
    return sb.toString();
  }

  public class TypesNested<N> extends TypeDefFluent<TypesNested<N>> implements Nested<N> {
    TypesNested(int index, TypeDef item) {
      this.index = index;
      this.builder = new TypeDefBuilder(this, item);
    }

    TypeDefBuilder builder;
    int index;

    public N and() {
      return (N) SourceFluent.this.setToTypes(index, builder.build());
    }

    public N endType() {
      return and();
    }

  }

}
