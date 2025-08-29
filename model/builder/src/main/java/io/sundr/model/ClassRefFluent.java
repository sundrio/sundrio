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
public class ClassRefFluent<A extends ClassRefFluent<A>> extends TypeRefFluent<A> {
  public ClassRefFluent() {
  }

  public ClassRefFluent(ClassRef instance) {
    this.copyInstance(instance);
  }

  private String fullyQualifiedName;
  private int dimensions;
  private ArrayList<VisitableBuilder<? extends TypeRef, ?>> arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();

  protected void copyInstance(ClassRef instance) {
    if (instance != null) {
      this.withFullyQualifiedName(instance.getFullyQualifiedName());
      this.withDimensions(instance.getDimensions());
      this.withArguments(instance.getArguments());
      this.withAttributes(instance.getAttributes());
    }
  }

  public String getFullyQualifiedName() {
    return this.fullyQualifiedName;
  }

  public A withFullyQualifiedName(String fullyQualifiedName) {
    this.fullyQualifiedName = fullyQualifiedName;
    return (A) this;
  }

  public boolean hasFullyQualifiedName() {
    return this.fullyQualifiedName != null;
  }

  public int getDimensions() {
    return this.dimensions;
  }

  public A withDimensions(int dimensions) {
    this.dimensions = dimensions;
    return (A) this;
  }

  public boolean hasDimensions() {
    return true;
  }

  public A addToArguments(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("arguments").add(builder);
    this.arguments.add(builder);
    return (A) this;
  }

  public A addToArguments(int index, VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(builder);
      arguments.add(index, builder);
    }
    return (A) this;
  }

  public A addToArguments(int index, TypeRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(builder);
      arguments.add(index, builder);
    }
    return (A) this;
  }

  public A setToArguments(int index, TypeRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(builder);
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToArguments(io.sundr.model.TypeRef... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToArguments(Collection<TypeRef> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromArguments(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.arguments == null)
      return (A) this;
    _visitables.get("arguments").remove(builder);
    this.arguments.remove(builder);
    return (A) this;
  }

  public A removeFromArguments(io.sundr.model.TypeRef... items) {
    if (this.arguments == null)
      return (A) this;
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromArguments(Collection<TypeRef> items) {
    if (this.arguments == null)
      return (A) this;
    for (TypeRef item : items) {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends TypeRef, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      VisitableBuilder<? extends TypeRef, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<TypeRef> buildArguments() {
    return build(arguments);
  }

  public TypeRef buildArgument(int index) {
    return this.arguments.get(index).build();
  }

  public TypeRef buildFirstArgument() {
    return this.arguments.get(0).build();
  }

  public TypeRef buildLastArgument() {
    return this.arguments.get(arguments.size() - 1).build();
  }

  public TypeRef buildMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : arguments) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
    for (VisitableBuilder<? extends TypeRef, ?> item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withArguments(List<TypeRef> arguments) {
    if (arguments != null) {
      this.arguments = new ArrayList();
      for (TypeRef item : arguments) {
        this.addToArguments(item);
      }
    } else {
      this.arguments = null;
    }
    return (A) this;
  }

  public A withArguments(io.sundr.model.TypeRef... arguments) {
    if (this.arguments != null) {
      this.arguments.clear();
      _visitables.remove("arguments");
    }
    if (arguments != null) {
      for (TypeRef item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public boolean hasArguments() {
    return this.arguments != null && !(this.arguments.isEmpty());
  }

  public ClassRefArgumentsNested<A> addNewClassRefArgument() {
    return new ClassRefArgumentsNested(-1, null);
  }

  public ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item) {
    return new ClassRefArgumentsNested(-1, item);
  }

  public ClassRefArgumentsNested<A> setNewClassRefArgumentLike(int index, ClassRef item) {
    return new ClassRefArgumentsNested(index, item);
  }

  public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument() {
    return new PrimitiveRefArgumentsNested(-1, null);
  }

  public PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item) {
    return new PrimitiveRefArgumentsNested(-1, item);
  }

  public PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(int index, PrimitiveRef item) {
    return new PrimitiveRefArgumentsNested(index, item);
  }

  public VoidRefArgumentsNested<A> addNewVoidRefArgument() {
    return new VoidRefArgumentsNested(-1, null);
  }

  public VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item) {
    return new VoidRefArgumentsNested(-1, item);
  }

  public VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(int index, VoidRef item) {
    return new VoidRefArgumentsNested(index, item);
  }

  public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument() {
    return new TypeParamRefArgumentsNested(-1, null);
  }

  public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item) {
    return new TypeParamRefArgumentsNested(-1, item);
  }

  public TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(int index, TypeParamRef item) {
    return new TypeParamRefArgumentsNested(index, item);
  }

  public WildcardRefArgumentsNested<A> addNewWildcardRefArgument() {
    return new WildcardRefArgumentsNested(-1, null);
  }

  public WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item) {
    return new WildcardRefArgumentsNested(-1, item);
  }

  public WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(int index, WildcardRef item) {
    return new WildcardRefArgumentsNested(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ClassRefFluent that = (ClassRefFluent) o;
    if (!java.util.Objects.equals(fullyQualifiedName, that.fullyQualifiedName))
      return false;
    if (dimensions != that.dimensions)
      return false;
    if (!java.util.Objects.equals(arguments, that.arguments))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(fullyQualifiedName, dimensions, arguments, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (fullyQualifiedName != null) {
      sb.append("fullyQualifiedName:");
      sb.append(fullyQualifiedName + ",");
    }
    sb.append("dimensions:");
    sb.append(dimensions + ",");
    if (arguments != null && !arguments.isEmpty()) {
      sb.append("arguments:");
      sb.append(arguments);
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

  public class ClassRefArgumentsNested<N> extends ClassRefFluent<ClassRefArgumentsNested<N>> implements Nested<N> {
    ClassRefArgumentsNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;
    int index;

    public N and() {
      return (N) ClassRefFluent.this.setToArguments(index, builder.build());
    }

    public N endClassRefArgument() {
      return and();
    }

  }

  public class PrimitiveRefArgumentsNested<N> extends PrimitiveRefFluent<PrimitiveRefArgumentsNested<N>> implements Nested<N> {
    PrimitiveRefArgumentsNested(int index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBuilder builder;
    int index;

    public N and() {
      return (N) ClassRefFluent.this.setToArguments(index, builder.build());
    }

    public N endPrimitiveRefArgument() {
      return and();
    }

  }

  public class VoidRefArgumentsNested<N> extends VoidRefFluent<VoidRefArgumentsNested<N>> implements Nested<N> {
    VoidRefArgumentsNested(int index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBuilder builder;
    int index;

    public N and() {
      return (N) ClassRefFluent.this.setToArguments(index, builder.build());
    }

    public N endVoidRefArgument() {
      return and();
    }

  }

  public class TypeParamRefArgumentsNested<N> extends TypeParamRefFluent<TypeParamRefArgumentsNested<N>> implements Nested<N> {
    TypeParamRefArgumentsNested(int index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBuilder builder;
    int index;

    public N and() {
      return (N) ClassRefFluent.this.setToArguments(index, builder.build());
    }

    public N endTypeParamRefArgument() {
      return and();
    }

  }

  public class WildcardRefArgumentsNested<N> extends WildcardRefFluent<WildcardRefArgumentsNested<N>> implements Nested<N> {
    WildcardRefArgumentsNested(int index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBuilder builder;
    int index;

    public N and() {
      return (N) ClassRefFluent.this.setToArguments(index, builder.build());
    }

    public N endWildcardRefArgument() {
      return and();
    }

  }

}
