package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.model.builder.BaseFluent;
import io.sundr.model.builder.Nested;

public class SourceFluentImpl<A extends SourceFluent<A>> extends io.sundr.model.builder.BaseFluent<A>
    implements SourceFluent<A> {

  private List<TypeDefBuilder> types;

  public SourceFluentImpl() {
  }

  public SourceFluentImpl(Source instance) {
    this.withTypes(instance.getTypes());
  }

  public A addToTypes(int index, TypeDef item) {
    if (this.types == null) {
      this.types = new ArrayList<TypeDefBuilder>();
    }
    TypeDefBuilder builder = new TypeDefBuilder(item);
    _visitables.get("types").add(index >= 0 ? index : _visitables.get("types").size(), builder);
    this.types.add(index >= 0 ? index : types.size(), builder);
    return (A) this;
  }

  public A setToTypes(int index, TypeDef item) {
    if (this.types == null) {
      this.types = new ArrayList<TypeDefBuilder>();
    }
    TypeDefBuilder builder = new TypeDefBuilder(item);
    if (index < 0 || index >= _visitables.get("types").size()) {
      _visitables.get("types").add(builder);
    } else {
      _visitables.get("types").set(index, builder);
    }
    if (index < 0 || index >= types.size()) {
      types.add(builder);
    } else {
      types.set(index, builder);
    }
    return (A) this;
  }

  public A addToTypes(TypeDef... items) {
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

  public A removeFromTypes(TypeDef... items) {
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("types").remove(builder);
      if (this.types != null) {
        this.types.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromTypes(Collection<TypeDef> items) {
    for (TypeDef item : items) {
      TypeDefBuilder builder = new TypeDefBuilder(item);
      _visitables.get("types").remove(builder);
      if (this.types != null) {
        this.types.remove(builder);
      }
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

  /**
   * This method has been deprecated, please use method buildTypes instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeDef> getTypes() {
    return build(types);
  }

  public List<TypeDef> buildTypes() {
    return build(types);
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

  public Boolean hasMatchingType(Predicate<TypeDefBuilder> predicate) {
    for (TypeDefBuilder item : types) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withTypes(List<TypeDef> types) {
    if (this.types != null) {
      _visitables.get("types").removeAll(this.types);
    }
    if (types != null) {
      this.types = new ArrayList<TypeDefBuilder>();
      for (TypeDef item : types) {
        this.addToTypes(item);
      }
    } else {
      this.types = null;
    }
    return (A) this;
  }

  public A withTypes(TypeDef... types) {
    if (this.types != null) {
      this.types.clear();
    }
    if (types != null) {
      for (TypeDef item : types) {
        this.addToTypes(item);
      }
    }
    return (A) this;
  }

  public Boolean hasTypes() {
    return types != null && !types.isEmpty();
  }

  public A addNewType(String fullyQualifiedName) {
    return (A) addToTypes(new TypeDef(fullyQualifiedName));
  }

  public io.sundr.model.SourceFluent.TypesNested<A> addNewType() {
    return new TypesNestedImpl();
  }

  public io.sundr.model.SourceFluent.TypesNested<A> addNewTypeLike(TypeDef item) {
    return new TypesNestedImpl(-1, item);
  }

  public io.sundr.model.SourceFluent.TypesNested<A> setNewTypeLike(int index, TypeDef item) {
    return new TypesNestedImpl(index, item);
  }

  public io.sundr.model.SourceFluent.TypesNested<A> editType(int index) {
    if (types.size() <= index)
      throw new RuntimeException("Can't edit types. Index exceeds size.");
    return setNewTypeLike(index, buildType(index));
  }

  public io.sundr.model.SourceFluent.TypesNested<A> editFirstType() {
    if (types.size() == 0)
      throw new RuntimeException("Can't edit first types. The list is empty.");
    return setNewTypeLike(0, buildType(0));
  }

  public io.sundr.model.SourceFluent.TypesNested<A> editLastType() {
    int index = types.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last types. The list is empty.");
    return setNewTypeLike(index, buildType(index));
  }

  public io.sundr.model.SourceFluent.TypesNested<A> editMatchingType(Predicate<TypeDefBuilder> predicate) {
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
    SourceFluentImpl that = (SourceFluentImpl) o;
    if (types != null ? !types.equals(that.types) : that.types != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(types, super.hashCode());
  }

  public class TypesNestedImpl<N> extends TypeDefFluentImpl<io.sundr.model.SourceFluent.TypesNested<N>>
      implements io.sundr.model.SourceFluent.TypesNested<N>, io.sundr.model.builder.Nested<N> {
    private final TypeDefBuilder builder;
    private final int index;

    TypesNestedImpl(int index, TypeDef item) {
      this.index = index;
      this.builder = new TypeDefBuilder(this, item);

    }

    TypesNestedImpl() {
      this.index = -1;
      this.builder = new TypeDefBuilder(this);

    }

    public N and() {
      return (N) SourceFluentImpl.this.setToTypes(index, builder.build());
    }

    public N endType() {
      return and();
    }
  }

}
