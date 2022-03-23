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

  public ClassRefFluentImpl(io.sundr.model.ClassRef instance) {
    this.withFullyQualifiedName(instance.getFullyQualifiedName());
    this.withDimensions(instance.getDimensions());
    this.withArguments(instance.getArguments());
    this.withAttributes(instance.getAttributes());
  }

  private String fullyQualifiedName;
  private int dimensions;
  private ArrayList<VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();

  public java.lang.String getFullyQualifiedName() {
    return this.fullyQualifiedName;
  }

  public A withFullyQualifiedName(java.lang.String fullyQualifiedName) {
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

  public java.lang.Boolean hasDimensions() {
    return true;
  }

  public A addToArguments(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    _visitables.get("arguments").add(builder);
    this.arguments.add(builder);
    return (A) this;
  }

  public A addToArguments(Integer index, io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    _visitables.get("arguments").add(index, builder);
    this.arguments.add(index, builder);
    return (A) this;
  }

  public A addToArguments(java.lang.Integer index, io.sundr.model.TypeRef item) {
    if (item instanceof TypeParamRef) {
      addToTypeParamRefArguments(index, (io.sundr.model.TypeParamRef) item);
    } else if (item instanceof WildcardRef) {
      addToWildcardRefArguments(index, (io.sundr.model.WildcardRef) item);
    } else if (item instanceof io.sundr.model.ClassRef) {
      addToClassRefArguments(index, (io.sundr.model.ClassRef) item);
    } else if (item instanceof PrimitiveRef) {
      addToPrimitiveRefArguments(index, (io.sundr.model.PrimitiveRef) item);
    } else if (item instanceof VoidRef) {
      addToVoidRefArguments(index, (io.sundr.model.VoidRef) item);
    }

    return (A) this;
  }

  public A setToArguments(java.lang.Integer index, io.sundr.model.TypeRef item) {
    if (item instanceof io.sundr.model.TypeParamRef) {
      setToTypeParamRefArguments(index, (io.sundr.model.TypeParamRef) item);
    } else if (item instanceof io.sundr.model.WildcardRef) {
      setToWildcardRefArguments(index, (io.sundr.model.WildcardRef) item);
    } else if (item instanceof io.sundr.model.ClassRef) {
      setToClassRefArguments(index, (io.sundr.model.ClassRef) item);
    } else if (item instanceof io.sundr.model.PrimitiveRef) {
      setToPrimitiveRefArguments(index, (io.sundr.model.PrimitiveRef) item);
    } else if (item instanceof io.sundr.model.VoidRef) {
      setToVoidRefArguments(index, (io.sundr.model.VoidRef) item);
    }

    return (A) this;
  }

  public A addToArguments(io.sundr.model.TypeRef... items) {
    if (items != null && items.length > 0 && this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        addToTypeParamRefArguments((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        addToWildcardRefArguments((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        addToClassRefArguments((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        addToPrimitiveRefArguments((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        addToVoidRefArguments((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").add(builder);
        this.arguments.add(builder);
      }
    }
    return (A) this;
  }

  public A addAllToArguments(Collection<io.sundr.model.TypeRef> items) {
    if (items != null && items.size() > 0 && this.arguments == null) {
      this.arguments = new ArrayList<VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        addToTypeParamRefArguments((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        addToWildcardRefArguments((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        addToClassRefArguments((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        addToPrimitiveRefArguments((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        addToVoidRefArguments((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").add(builder);
        this.arguments.add(builder);
      }
    }
    return (A) this;
  }

  public A removeFromArguments(io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    _visitables.get("arguments").remove(builder);
    this.arguments.remove(builder);
    return (A) this;
  }

  public A removeFromArguments(io.sundr.model.TypeRef... items) {
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        removeFromTypeParamRefArguments((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        removeFromWildcardRefArguments((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        removeFromClassRefArguments((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        removeFromPrimitiveRefArguments((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        removeFromVoidRefArguments((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
        _visitables.get("arguments").remove(builder);
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromArguments(java.util.Collection<io.sundr.model.TypeRef> items) {
    for (io.sundr.model.TypeRef item : items) {
      if (item instanceof io.sundr.model.TypeParamRef) {
        removeFromTypeParamRefArguments((io.sundr.model.TypeParamRef) item);
      } else if (item instanceof io.sundr.model.WildcardRef) {
        removeFromWildcardRefArguments((io.sundr.model.WildcardRef) item);
      } else if (item instanceof io.sundr.model.ClassRef) {
        removeFromClassRefArguments((io.sundr.model.ClassRef) item);
      } else if (item instanceof io.sundr.model.PrimitiveRef) {
        removeFromPrimitiveRefArguments((io.sundr.model.PrimitiveRef) item);
      } else if (item instanceof io.sundr.model.VoidRef) {
        removeFromVoidRefArguments((io.sundr.model.VoidRef) item);
      }

      else {
        VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = builderOf(item);
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
  public List<io.sundr.model.TypeRef> getArguments() {
    return build(arguments);
  }

  public java.util.List<io.sundr.model.TypeRef> buildArguments() {
    return build(arguments);
  }

  public io.sundr.model.TypeRef buildArgument(java.lang.Integer index) {
    return this.arguments.get(index).build();
  }

  public io.sundr.model.TypeRef buildFirstArgument() {
    return this.arguments.get(0).build();
  }

  public io.sundr.model.TypeRef buildLastArgument() {
    return this.arguments.get(arguments.size() - 1).build();
  }

  public io.sundr.model.TypeRef buildMatchingArgument(
      Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    for (io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> item : arguments) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingArgument(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    for (io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withArguments(java.util.List<io.sundr.model.TypeRef> arguments) {
    if (arguments != null) {
      this.arguments = new java.util.ArrayList();
      for (io.sundr.model.TypeRef item : arguments) {
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
      for (io.sundr.model.TypeRef item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasArguments() {
    return arguments != null && !arguments.isEmpty();
  }

  public A addToTypeParamRefArguments(java.lang.Integer index, io.sundr.model.TypeParamRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToTypeParamRefArguments(java.lang.Integer index, io.sundr.model.TypeParamRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
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
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToTypeParamRefArguments(java.util.Collection<io.sundr.model.TypeParamRef> items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromTypeParamRefArguments(io.sundr.model.TypeParamRef... items) {
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromTypeParamRefArguments(java.util.Collection<io.sundr.model.TypeParamRef> items) {
    for (io.sundr.model.TypeParamRef item : items) {
      io.sundr.model.TypeParamRefBuilder builder = new io.sundr.model.TypeParamRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromTypeParamRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
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

  public io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(
      io.sundr.model.TypeParamRef item) {
    return new ClassRefFluentImpl.TypeParamRefArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<A> setNewTypeParamRefArgumentLike(java.lang.Integer index,
      io.sundr.model.TypeParamRef item) {
    return new io.sundr.model.ClassRefFluentImpl.TypeParamRefArgumentsNestedImpl(index, item);
  }

  public A addToWildcardRefArguments(java.lang.Integer index, io.sundr.model.WildcardRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToWildcardRefArguments(java.lang.Integer index, io.sundr.model.WildcardRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
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
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToWildcardRefArguments(java.util.Collection<io.sundr.model.WildcardRef> items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromWildcardRefArguments(io.sundr.model.WildcardRef... items) {
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromWildcardRefArguments(java.util.Collection<io.sundr.model.WildcardRef> items) {
    for (io.sundr.model.WildcardRef item : items) {
      io.sundr.model.WildcardRefBuilder builder = new io.sundr.model.WildcardRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromWildcardRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
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

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> addNewWildcardRefArgumentLike(
      io.sundr.model.WildcardRef item) {
    return new io.sundr.model.ClassRefFluentImpl.WildcardRefArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<A> setNewWildcardRefArgumentLike(java.lang.Integer index,
      io.sundr.model.WildcardRef item) {
    return new io.sundr.model.ClassRefFluentImpl.WildcardRefArgumentsNestedImpl(index, item);
  }

  public A addToClassRefArguments(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToClassRefArguments(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
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
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToClassRefArguments(java.util.Collection<io.sundr.model.ClassRef> items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromClassRefArguments(io.sundr.model.ClassRef... items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromClassRefArguments(java.util.Collection<io.sundr.model.ClassRef> items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromClassRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
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

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> addNewClassRefArgumentLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.ClassRefFluentImpl.ClassRefArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<A> setNewClassRefArgumentLike(java.lang.Integer index,
      io.sundr.model.ClassRef item) {
    return new io.sundr.model.ClassRefFluentImpl.ClassRefArgumentsNestedImpl(index, item);
  }

  public A addToPrimitiveRefArguments(java.lang.Integer index, io.sundr.model.PrimitiveRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToPrimitiveRefArguments(java.lang.Integer index, io.sundr.model.PrimitiveRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
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
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToPrimitiveRefArguments(java.util.Collection<io.sundr.model.PrimitiveRef> items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromPrimitiveRefArguments(io.sundr.model.PrimitiveRef... items) {
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromPrimitiveRefArguments(java.util.Collection<io.sundr.model.PrimitiveRef> items) {
    for (io.sundr.model.PrimitiveRef item : items) {
      io.sundr.model.PrimitiveRefBuilder builder = new io.sundr.model.PrimitiveRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromPrimitiveRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
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

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> addNewPrimitiveRefArgumentLike(
      io.sundr.model.PrimitiveRef item) {
    return new io.sundr.model.ClassRefFluentImpl.PrimitiveRefArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<A> setNewPrimitiveRefArgumentLike(java.lang.Integer index,
      io.sundr.model.PrimitiveRef item) {
    return new io.sundr.model.ClassRefFluentImpl.PrimitiveRefArgumentsNestedImpl(index, item);
  }

  public A addToVoidRefArguments(java.lang.Integer index, io.sundr.model.VoidRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToVoidRefArguments(java.lang.Integer index, io.sundr.model.VoidRef item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
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
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToVoidRefArguments(java.util.Collection<io.sundr.model.VoidRef> items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>>();
    }
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromVoidRefArguments(io.sundr.model.VoidRef... items) {
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromVoidRefArguments(java.util.Collection<io.sundr.model.VoidRef> items) {
    for (io.sundr.model.VoidRef item : items) {
      io.sundr.model.VoidRefBuilder builder = new io.sundr.model.VoidRefBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromVoidRefArguments(
      java.util.function.Predicate<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?>> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      io.sundr.builder.VisitableBuilder<? extends io.sundr.model.TypeRef, ?> builder = each.next();
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

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> addNewVoidRefArgumentLike(io.sundr.model.VoidRef item) {
    return new io.sundr.model.ClassRefFluentImpl.VoidRefArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<A> setNewVoidRefArgumentLike(java.lang.Integer index,
      io.sundr.model.VoidRef item) {
    return new io.sundr.model.ClassRefFluentImpl.VoidRefArgumentsNestedImpl(index, item);
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

  public java.lang.String toString() {
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
      implements io.sundr.model.ClassRefFluent.TypeParamRefArgumentsNested<N>, Nested<N> {
    TypeParamRefArgumentsNestedImpl(java.lang.Integer index, io.sundr.model.TypeParamRef item) {
      this.index = index;
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.TypeParamRefBuilder(this);
    }

    io.sundr.model.TypeParamRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endTypeParamRefArgument() {
      return and();
    }

  }

  class WildcardRefArgumentsNestedImpl<N> extends WildcardRefFluentImpl<ClassRefFluent.WildcardRefArgumentsNested<N>>
      implements io.sundr.model.ClassRefFluent.WildcardRefArgumentsNested<N>, io.sundr.builder.Nested<N> {
    WildcardRefArgumentsNestedImpl(java.lang.Integer index, WildcardRef item) {
      this.index = index;
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.WildcardRefBuilder(this);
    }

    io.sundr.model.WildcardRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endWildcardRefArgument() {
      return and();
    }

  }

  class ClassRefArgumentsNestedImpl<N> extends ClassRefFluentImpl<ClassRefFluent.ClassRefArgumentsNested<N>>
      implements io.sundr.model.ClassRefFluent.ClassRefArgumentsNested<N>, io.sundr.builder.Nested<N> {
    ClassRefArgumentsNestedImpl(java.lang.Integer index, io.sundr.model.ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endClassRefArgument() {
      return and();
    }

  }

  class PrimitiveRefArgumentsNestedImpl<N> extends PrimitiveRefFluentImpl<ClassRefFluent.PrimitiveRefArgumentsNested<N>>
      implements io.sundr.model.ClassRefFluent.PrimitiveRefArgumentsNested<N>, io.sundr.builder.Nested<N> {
    PrimitiveRefArgumentsNestedImpl(java.lang.Integer index, io.sundr.model.PrimitiveRef item) {
      this.index = index;
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.PrimitiveRefBuilder(this);
    }

    io.sundr.model.PrimitiveRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endPrimitiveRefArgument() {
      return and();
    }

  }

  class VoidRefArgumentsNestedImpl<N> extends VoidRefFluentImpl<ClassRefFluent.VoidRefArgumentsNested<N>>
      implements io.sundr.model.ClassRefFluent.VoidRefArgumentsNested<N>, io.sundr.builder.Nested<N> {
    VoidRefArgumentsNestedImpl(java.lang.Integer index, VoidRef item) {
      this.index = index;
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.VoidRefBuilder(this);
    }

    io.sundr.model.VoidRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) ClassRefFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endVoidRefArgument() {
      return and();
    }

  }

}
