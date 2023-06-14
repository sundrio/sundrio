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
public class ClassRefFluentImpl<A extends ClassRefFluent<A>> extends TypeRefFluentImpl<A> implements ClassRefFluent<A> {
  public ClassRefFluentImpl() {
  }

  public ClassRefFluentImpl(ClassRef instance) {
    this.withFullyQualifiedName(instance.getFullyQualifiedName());
    this.withDimensions(instance.getDimensions());
    this.withArguments(instance.getArguments());
    this.withAttributes(instance.getAttributes());
  }

  private String fullyQualifiedName;
  private int dimensions;
  private ArrayList<VisitableBuilder<? extends TypeRef, ?>> arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();

  public String getFullyQualifiedName() {
    return this.fullyQualifiedName;
  }

  public A withFullyQualifiedName(String fullyQualifiedName) {
    this.fullyQualifiedName = fullyQualifiedName;
    return (A) this;
  }

  public Boolean hasFullyQualifiedName() {
    return this.fullyQualifiedName != null;
  }

  public int getDimensions() {
    return this.dimensions;
  }

  public A withDimensions(int dimensions) {
    this.dimensions = dimensions;
    return (A) this;
  }

  public Boolean hasDimensions() {
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

  public A addToArguments(Integer index, VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("arguments").add(index, builder);
    this.arguments.add(index, builder);
    return (A) this;
  }

  public A addToArguments(Integer index, TypeRef item) {
    if (item instanceof TypeParamRef) {
      addToTypeParamRefArguments(index, (TypeParamRef) item);
    } else if (item instanceof WildcardRef) {
      addToWildcardRefArguments(index, (WildcardRef) item);
    } else if (item instanceof ClassRef) {
      addToClassRefArguments(index, (ClassRef) item);
    } else if (item instanceof PrimitiveRef) {
      addToPrimitiveRefArguments(index, (PrimitiveRef) item);
    } else if (item instanceof VoidRef) {
      addToVoidRefArguments(index, (VoidRef) item);
    }

    return (A) this;
  }

  public A setToArguments(Integer index, TypeRef item) {
    if (item instanceof TypeParamRef) {
      setToTypeParamRefArguments(index, (TypeParamRef) item);
    } else if (item instanceof WildcardRef) {
      setToWildcardRefArguments(index, (WildcardRef) item);
    } else if (item instanceof ClassRef) {
      setToClassRefArguments(index, (ClassRef) item);
    } else if (item instanceof PrimitiveRef) {
      setToPrimitiveRefArguments(index, (PrimitiveRef) item);
    } else if (item instanceof VoidRef) {
      setToVoidRefArguments(index, (VoidRef) item);
    }

    return (A) this;
  }

  public A addToArguments(io.sundr.model.TypeRef... items) {
    if (items != null && items.length > 0 && this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        addToTypeParamRefArguments((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        addToWildcardRefArguments((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        addToClassRefArguments((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        addToPrimitiveRefArguments((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        addToVoidRefArguments((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").add(builder);
        this.arguments.add(builder);
      }
    }
    return (A) this;
  }

  public A addAllToArguments(Collection<TypeRef> items) {
    if (items != null && items.size() > 0 && this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        addToTypeParamRefArguments((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        addToWildcardRefArguments((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        addToClassRefArguments((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        addToPrimitiveRefArguments((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        addToVoidRefArguments((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").add(builder);
        this.arguments.add(builder);
      }
    }
    return (A) this;
  }

  public A removeFromArguments(VisitableBuilder<? extends TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    _visitables.get("arguments").remove(builder);
    this.arguments.remove(builder);
    return (A) this;
  }

  public A removeFromArguments(io.sundr.model.TypeRef... items) {
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        removeFromTypeParamRefArguments((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        removeFromWildcardRefArguments((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        removeFromClassRefArguments((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        removeFromPrimitiveRefArguments((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        removeFromVoidRefArguments((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").remove(builder);
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromArguments(Collection<TypeRef> items) {
    for (TypeRef item : items) {
      if (item instanceof TypeParamRef) {
        removeFromTypeParamRefArguments((TypeParamRef) item);
      } else if (item instanceof WildcardRef) {
        removeFromWildcardRefArguments((WildcardRef) item);
      } else if (item instanceof ClassRef) {
        removeFromClassRefArguments((ClassRef) item);
      } else if (item instanceof PrimitiveRef) {
        removeFromPrimitiveRefArguments((PrimitiveRef) item);
      } else if (item instanceof VoidRef) {
        removeFromVoidRefArguments((VoidRef) item);
      }

      else {
        VisitableBuilder<? extends TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").remove(builder);
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public List<TypeRef> getArguments() {
    return build(arguments);
  }

  public List<TypeRef> buildArguments() {
    return build(arguments);
  }

  public TypeRef buildArgument(Integer index) {
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

  public Boolean hasMatchingArgument(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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
    }
    if (arguments != null) {
      for (TypeRef item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public Boolean hasArguments() {
    return arguments != null && !arguments.isEmpty();
  }

  public A addToTypeParamRefArguments(Integer index, TypeParamRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToTypeParamRefArguments(Integer index, TypeParamRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
    if (index < 0 || index >= _visitables.get("arguments").size()) {
      _visitables.get("arguments").add(builder);
    } else {
      _visitables.get("arguments").set(index, builder);
    }
    if (index < 0 || index >= arguments.size()) {
      arguments.add(builder);
    } else {
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToTypeParamRefArguments(io.sundr.model.TypeParamRef... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToTypeParamRefArguments(Collection<TypeParamRef> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromTypeParamRefArguments(io.sundr.model.TypeParamRef... items) {
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromTypeParamRefArguments(Collection<TypeParamRef> items) {
    for (TypeParamRef item : items) {
      TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromTypeParamRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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

  public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument() {
    return new ClassRefFluentImpl.TypeParamRefArgumentsNestedImpl();
  }

  public ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item) {
    return new ClassRefFluentImpl.TypeParamRefArgumentsNestedImpl(-1, item);
  }

  public ClassRefFluent.TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(Integer index, TypeParamRef item) {
    return new ClassRefFluentImpl.TypeParamRefArgumentsNestedImpl(index, item);
  }

  public A addToWildcardRefArguments(Integer index, WildcardRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new WildcardRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToWildcardRefArguments(Integer index, WildcardRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new WildcardRefBuilder(item);
    if (index < 0 || index >= _visitables.get("arguments").size()) {
      _visitables.get("arguments").add(builder);
    } else {
      _visitables.get("arguments").set(index, builder);
    }
    if (index < 0 || index >= arguments.size()) {
      arguments.add(builder);
    } else {
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToWildcardRefArguments(io.sundr.model.WildcardRef... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToWildcardRefArguments(Collection<WildcardRef> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromWildcardRefArguments(io.sundr.model.WildcardRef... items) {
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromWildcardRefArguments(Collection<WildcardRef> items) {
    for (WildcardRef item : items) {
      WildcardRefBuilder builder = new WildcardRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromWildcardRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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

  public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgument() {
    return new ClassRefFluentImpl.WildcardRefArgumentsNestedImpl();
  }

  public ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(WildcardRef item) {
    return new ClassRefFluentImpl.WildcardRefArgumentsNestedImpl(-1, item);
  }

  public ClassRefFluent.WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(Integer index, WildcardRef item) {
    return new ClassRefFluentImpl.WildcardRefArgumentsNestedImpl(index, item);
  }

  public A addToClassRefArguments(Integer index, ClassRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToClassRefArguments(Integer index, ClassRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= _visitables.get("arguments").size()) {
      _visitables.get("arguments").add(builder);
    } else {
      _visitables.get("arguments").set(index, builder);
    }
    if (index < 0 || index >= arguments.size()) {
      arguments.add(builder);
    } else {
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToClassRefArguments(io.sundr.model.ClassRef... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToClassRefArguments(Collection<ClassRef> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromClassRefArguments(io.sundr.model.ClassRef... items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromClassRefArguments(Collection<ClassRef> items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromClassRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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

  public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgument() {
    return new ClassRefFluentImpl.ClassRefArgumentsNestedImpl();
  }

  public ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(ClassRef item) {
    return new ClassRefFluentImpl.ClassRefArgumentsNestedImpl(-1, item);
  }

  public ClassRefFluent.ClassRefArgumentsNested<A> setNewClassRefArgumentLike(Integer index, ClassRef item) {
    return new ClassRefFluentImpl.ClassRefArgumentsNestedImpl(index, item);
  }

  public A addToPrimitiveRefArguments(Integer index, PrimitiveRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToPrimitiveRefArguments(Integer index, PrimitiveRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
    if (index < 0 || index >= _visitables.get("arguments").size()) {
      _visitables.get("arguments").add(builder);
    } else {
      _visitables.get("arguments").set(index, builder);
    }
    if (index < 0 || index >= arguments.size()) {
      arguments.add(builder);
    } else {
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToPrimitiveRefArguments(Collection<PrimitiveRef> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items) {
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromPrimitiveRefArguments(Collection<PrimitiveRef> items) {
    for (PrimitiveRef item : items) {
      PrimitiveRefBuilder builder = new PrimitiveRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromPrimitiveRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgument() {
    return new ClassRefFluentImpl.PrimitiveRefArgumentsNestedImpl();
  }

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(PrimitiveRef item) {
    return new ClassRefFluentImpl.PrimitiveRefArgumentsNestedImpl(-1, item);
  }

  public ClassRefFluent.PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(Integer index, PrimitiveRef item) {
    return new ClassRefFluentImpl.PrimitiveRefArgumentsNestedImpl(index, item);
  }

  public A addToVoidRefArguments(Integer index, VoidRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VoidRefBuilder builder = new VoidRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToVoidRefArguments(Integer index, VoidRef item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    VoidRefBuilder builder = new VoidRefBuilder(item);
    if (index < 0 || index >= _visitables.get("arguments").size()) {
      _visitables.get("arguments").add(builder);
    } else {
      _visitables.get("arguments").set(index, builder);
    }
    if (index < 0 || index >= arguments.size()) {
      arguments.add(builder);
    } else {
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToVoidRefArguments(io.sundr.model.VoidRef... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToVoidRefArguments(Collection<VoidRef> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends TypeRef, ?>>();
    }
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromVoidRefArguments(io.sundr.model.VoidRef... items) {
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromVoidRefArguments(Collection<VoidRef> items) {
    for (VoidRef item : items) {
      VoidRefBuilder builder = new VoidRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromVoidRefArguments(Predicate<VisitableBuilder<? extends TypeRef, ?>> predicate) {
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

  public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgument() {
    return new ClassRefFluentImpl.VoidRefArgumentsNestedImpl();
  }

  public ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(VoidRef item) {
    return new ClassRefFluentImpl.VoidRefArgumentsNestedImpl(-1, item);
  }

  public ClassRefFluent.VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(Integer index, VoidRef item) {
    return new ClassRefFluentImpl.VoidRefArgumentsNestedImpl(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ClassRefFluentImpl that = (ClassRefFluentImpl) o;
    if (fullyQualifiedName != null ? !fullyQualifiedName.equals(that.fullyQualifiedName) : that.fullyQualifiedName != null)
      return false;
    if (dimensions != that.dimensions)
      return false;
    if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null)
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

  class TypeParamRefArgumentsNestedImpl<N> extends TypeParamRefFluentImpl<ClassRefFluent.TypeParamRefArgumentsNested<N>>
      implements ClassRefFluent.TypeParamRefArgumentsNested<N>, Nested<N> {
    TypeParamRefArgumentsNestedImpl(Integer index, TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new TypeParamRefBuilder(this);
    }

    TypeParamRefBuilder builder;
    Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endTypeParamRefArgument() {
      return and();
    }

  }

  class WildcardRefArgumentsNestedImpl<N> extends WildcardRefFluentImpl<ClassRefFluent.WildcardRefArgumentsNested<N>>
      implements ClassRefFluent.WildcardRefArgumentsNested<N>, Nested<N> {
    WildcardRefArgumentsNestedImpl(Integer index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new WildcardRefBuilder(this);
    }

    WildcardRefBuilder builder;
    Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endWildcardRefArgument() {
      return and();
    }

  }

  class ClassRefArgumentsNestedImpl<N> extends ClassRefFluentImpl<ClassRefFluent.ClassRefArgumentsNested<N>>
      implements ClassRefFluent.ClassRefArgumentsNested<N>, Nested<N> {
    ClassRefArgumentsNestedImpl(Integer index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new ClassRefBuilder(this);
    }

    ClassRefBuilder builder;
    Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endClassRefArgument() {
      return and();
    }

  }

  class PrimitiveRefArgumentsNestedImpl<N> extends PrimitiveRefFluentImpl<ClassRefFluent.PrimitiveRefArgumentsNested<N>>
      implements ClassRefFluent.PrimitiveRefArgumentsNested<N>, Nested<N> {
    PrimitiveRefArgumentsNestedImpl(Integer index, PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new PrimitiveRefBuilder(this);
    }

    PrimitiveRefBuilder builder;
    Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endPrimitiveRefArgument() {
      return and();
    }

  }

  class VoidRefArgumentsNestedImpl<N> extends VoidRefFluentImpl<ClassRefFluent.VoidRefArgumentsNested<N>>
      implements ClassRefFluent.VoidRefArgumentsNested<N>, Nested<N> {
    VoidRefArgumentsNestedImpl(Integer index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new VoidRefBuilder(this);
    }

    VoidRefBuilder builder;
    Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endVoidRefArgument() {
      return and();
    }

  }

}
