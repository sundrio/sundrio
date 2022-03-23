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
public class PropertyFluentImpl<A extends PropertyFluent<A>> extends ModifierSupportFluentImpl<A> implements PropertyFluent<A> {
  public PropertyFluentImpl() {
  }

  public PropertyFluentImpl(io.sundr.model.Property instance) {
    this.withAnnotations(instance.getAnnotations());
    this.withTypeRef(instance.getTypeRef());
    this.withName(instance.getName());
    this.withComments(instance.getComments());
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  private ArrayList<AnnotationRefBuilder> annotations = new java.util.ArrayList<AnnotationRefBuilder>();
  private VisitableBuilder<? extends TypeRef, ?> typeRef;
  private String name;
  private List<java.lang.String> comments = new java.util.ArrayList<java.lang.String>();

  public A addToAnnotations(Integer index, io.sundr.model.AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new java.util.ArrayList<io.sundr.model.AnnotationRefBuilder>();
    }
    io.sundr.model.AnnotationRefBuilder builder = new io.sundr.model.AnnotationRefBuilder(item);
    _visitables.get("annotations").add(index >= 0 ? index : _visitables.get("annotations").size(), builder);
    this.annotations.add(index >= 0 ? index : annotations.size(), builder);
    return (A) this;
  }

  public A setToAnnotations(java.lang.Integer index, io.sundr.model.AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new java.util.ArrayList<io.sundr.model.AnnotationRefBuilder>();
    }
    io.sundr.model.AnnotationRefBuilder builder = new io.sundr.model.AnnotationRefBuilder(item);
    if (index < 0 || index >= _visitables.get("annotations").size()) {
      _visitables.get("annotations").add(builder);
    } else {
      _visitables.get("annotations").set(index, builder);
    }
    if (index < 0 || index >= annotations.size()) {
      annotations.add(builder);
    } else {
      annotations.set(index, builder);
    }
    return (A) this;
  }

  public A addToAnnotations(io.sundr.model.AnnotationRef... items) {
    if (this.annotations == null) {
      this.annotations = new java.util.ArrayList<io.sundr.model.AnnotationRefBuilder>();
    }
    for (io.sundr.model.AnnotationRef item : items) {
      io.sundr.model.AnnotationRefBuilder builder = new io.sundr.model.AnnotationRefBuilder(item);
      _visitables.get("annotations").add(builder);
      this.annotations.add(builder);
    }
    return (A) this;
  }

  public A addAllToAnnotations(Collection<io.sundr.model.AnnotationRef> items) {
    if (this.annotations == null) {
      this.annotations = new java.util.ArrayList<io.sundr.model.AnnotationRefBuilder>();
    }
    for (io.sundr.model.AnnotationRef item : items) {
      io.sundr.model.AnnotationRefBuilder builder = new io.sundr.model.AnnotationRefBuilder(item);
      _visitables.get("annotations").add(builder);
      this.annotations.add(builder);
    }
    return (A) this;
  }

  public A removeFromAnnotations(io.sundr.model.AnnotationRef... items) {
    for (io.sundr.model.AnnotationRef item : items) {
      io.sundr.model.AnnotationRefBuilder builder = new io.sundr.model.AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      if (this.annotations != null) {
        this.annotations.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromAnnotations(java.util.Collection<io.sundr.model.AnnotationRef> items) {
    for (io.sundr.model.AnnotationRef item : items) {
      io.sundr.model.AnnotationRefBuilder builder = new io.sundr.model.AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      if (this.annotations != null) {
        this.annotations.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromAnnotations(Predicate<io.sundr.model.AnnotationRefBuilder> predicate) {
    if (annotations == null)
      return (A) this;
    final Iterator<io.sundr.model.AnnotationRefBuilder> each = annotations.iterator();
    final List visitables = _visitables.get("annotations");
    while (each.hasNext()) {
      io.sundr.model.AnnotationRefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildAnnotations instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public java.util.List<io.sundr.model.AnnotationRef> getAnnotations() {
    return annotations != null ? build(annotations) : null;
  }

  public java.util.List<io.sundr.model.AnnotationRef> buildAnnotations() {
    return annotations != null ? build(annotations) : null;
  }

  public io.sundr.model.AnnotationRef buildAnnotation(java.lang.Integer index) {
    return this.annotations.get(index).build();
  }

  public io.sundr.model.AnnotationRef buildFirstAnnotation() {
    return this.annotations.get(0).build();
  }

  public io.sundr.model.AnnotationRef buildLastAnnotation() {
    return this.annotations.get(annotations.size() - 1).build();
  }

  public io.sundr.model.AnnotationRef buildMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate) {
    for (io.sundr.model.AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingAnnotation(java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate) {
    for (io.sundr.model.AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withAnnotations(java.util.List<io.sundr.model.AnnotationRef> annotations) {
    if (this.annotations != null) {
      _visitables.get("annotations").removeAll(this.annotations);
    }
    if (annotations != null) {
      this.annotations = new java.util.ArrayList();
      for (io.sundr.model.AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    } else {
      this.annotations = null;
    }
    return (A) this;
  }

  public A withAnnotations(io.sundr.model.AnnotationRef... annotations) {
    if (this.annotations != null) {
      this.annotations.clear();
    }
    if (annotations != null) {
      for (io.sundr.model.AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasAnnotations() {
    return annotations != null && !annotations.isEmpty();
  }

  public PropertyFluent.AnnotationsNested<A> addNewAnnotation() {
    return new PropertyFluentImpl.AnnotationsNestedImpl();
  }

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(io.sundr.model.AnnotationRef item) {
    return new PropertyFluentImpl.AnnotationsNestedImpl(-1, item);
  }

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(java.lang.Integer index,
      io.sundr.model.AnnotationRef item) {
    return new io.sundr.model.PropertyFluentImpl.AnnotationsNestedImpl(index, item);
  }

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editAnnotation(java.lang.Integer index) {
    if (annotations.size() <= index)
      throw new RuntimeException("Can't edit annotations. Index exceeds size.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0)
      throw new RuntimeException("Can't edit first annotations. The list is empty.");
    return setNewAnnotationLike(0, buildAnnotation(0));
  }

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last annotations. The list is empty.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public io.sundr.model.PropertyFluent.AnnotationsNested<A> editMatchingAnnotation(
      java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < annotations.size(); i++) {
      if (predicate.test(annotations.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching annotations. No match found.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  /**
   * This method has been deprecated, please use method buildTypeRef instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public io.sundr.model.TypeRef getTypeRef() {
    return this.typeRef != null ? this.typeRef.build() : null;
  }

  public io.sundr.model.TypeRef buildTypeRef() {
    return this.typeRef != null ? this.typeRef.build() : null;
  }

  public A withTypeRef(io.sundr.model.TypeRef typeRef) {
    if (typeRef instanceof TypeParamRef) {
      this.typeRef = new TypeParamRefBuilder((io.sundr.model.TypeParamRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof WildcardRef) {
      this.typeRef = new WildcardRefBuilder((io.sundr.model.WildcardRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof ClassRef) {
      this.typeRef = new ClassRefBuilder((io.sundr.model.ClassRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof PrimitiveRef) {
      this.typeRef = new PrimitiveRefBuilder((io.sundr.model.PrimitiveRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof VoidRef) {
      this.typeRef = new VoidRefBuilder((io.sundr.model.VoidRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public java.lang.Boolean hasTypeRef() {
    return this.typeRef != null;
  }

  public A withTypeParamRefType(io.sundr.model.TypeParamRef typeParamRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (typeParamRefType != null) {
      this.typeRef = new io.sundr.model.TypeParamRefBuilder(typeParamRefType);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType() {
    return new PropertyFluentImpl.TypeParamRefTypeNestedImpl();
  }

  public io.sundr.model.PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(io.sundr.model.TypeParamRef item) {
    return new io.sundr.model.PropertyFluentImpl.TypeParamRefTypeNestedImpl(item);
  }

  public A withWildcardRefType(io.sundr.model.WildcardRef wildcardRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (wildcardRefType != null) {
      this.typeRef = new io.sundr.model.WildcardRefBuilder(wildcardRefType);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType() {
    return new PropertyFluentImpl.WildcardRefTypeNestedImpl();
  }

  public io.sundr.model.PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(io.sundr.model.WildcardRef item) {
    return new io.sundr.model.PropertyFluentImpl.WildcardRefTypeNestedImpl(item);
  }

  public A withClassRefType(io.sundr.model.ClassRef classRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (classRefType != null) {
      this.typeRef = new io.sundr.model.ClassRefBuilder(classRefType);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefType() {
    return new PropertyFluentImpl.ClassRefTypeNestedImpl();
  }

  public io.sundr.model.PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.PropertyFluentImpl.ClassRefTypeNestedImpl(item);
  }

  public A withPrimitiveRefType(io.sundr.model.PrimitiveRef primitiveRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (primitiveRefType != null) {
      this.typeRef = new io.sundr.model.PrimitiveRefBuilder(primitiveRefType);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType() {
    return new PropertyFluentImpl.PrimitiveRefTypeNestedImpl();
  }

  public io.sundr.model.PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(io.sundr.model.PrimitiveRef item) {
    return new io.sundr.model.PropertyFluentImpl.PrimitiveRefTypeNestedImpl(item);
  }

  public A withVoidRefType(io.sundr.model.VoidRef voidRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (voidRefType != null) {
      this.typeRef = new io.sundr.model.VoidRefBuilder(voidRefType);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType() {
    return new PropertyFluentImpl.VoidRefTypeNestedImpl();
  }

  public io.sundr.model.PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(io.sundr.model.VoidRef item) {
    return new io.sundr.model.PropertyFluentImpl.VoidRefTypeNestedImpl(item);
  }

  public java.lang.String getName() {
    return this.name;
  }

  public A withName(java.lang.String name) {
    this.name = name;
    return (A) this;
  }

  public java.lang.Boolean hasName() {
    return this.name != null;
  }

  public A addToComments(java.lang.Integer index, java.lang.String item) {
    if (this.comments == null) {
      this.comments = new java.util.ArrayList<java.lang.String>();
    }
    this.comments.add(index, item);
    return (A) this;
  }

  public A setToComments(java.lang.Integer index, java.lang.String item) {
    if (this.comments == null) {
      this.comments = new java.util.ArrayList<java.lang.String>();
    }
    this.comments.set(index, item);
    return (A) this;
  }

  public A addToComments(java.lang.String... items) {
    if (this.comments == null) {
      this.comments = new java.util.ArrayList<java.lang.String>();
    }
    for (java.lang.String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A addAllToComments(java.util.Collection<java.lang.String> items) {
    if (this.comments == null) {
      this.comments = new java.util.ArrayList<java.lang.String>();
    }
    for (java.lang.String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A removeFromComments(java.lang.String... items) {
    for (java.lang.String item : items) {
      if (this.comments != null) {
        this.comments.remove(item);
      }
    }
    return (A) this;
  }

  public A removeAllFromComments(java.util.Collection<java.lang.String> items) {
    for (java.lang.String item : items) {
      if (this.comments != null) {
        this.comments.remove(item);
      }
    }
    return (A) this;
  }

  public java.util.List<java.lang.String> getComments() {
    return this.comments;
  }

  public java.lang.String getComment(java.lang.Integer index) {
    return this.comments.get(index);
  }

  public java.lang.String getFirstComment() {
    return this.comments.get(0);
  }

  public java.lang.String getLastComment() {
    return this.comments.get(comments.size() - 1);
  }

  public java.lang.String getMatchingComment(java.util.function.Predicate<java.lang.String> predicate) {
    for (java.lang.String item : comments) {
      if (predicate.test(item)) {
        return item;
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingComment(java.util.function.Predicate<java.lang.String> predicate) {
    for (java.lang.String item : comments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withComments(java.util.List<java.lang.String> comments) {
    if (comments != null) {
      this.comments = new java.util.ArrayList();
      for (java.lang.String item : comments) {
        this.addToComments(item);
      }
    } else {
      this.comments = null;
    }
    return (A) this;
  }

  public A withComments(java.lang.String... comments) {
    if (this.comments != null) {
      this.comments.clear();
    }
    if (comments != null) {
      for (java.lang.String item : comments) {
        this.addToComments(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasComments() {
    return comments != null && !comments.isEmpty();
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PropertyFluentImpl that = (PropertyFluentImpl) o;
    if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null)
      return false;
    if (typeRef != null ? !typeRef.equals(that.typeRef) : that.typeRef != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (comments != null ? !comments.equals(that.comments) : that.comments != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(annotations, typeRef, name, comments, super.hashCode());
  }

  public java.lang.String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (annotations != null && !annotations.isEmpty()) {
      sb.append("annotations:");
      sb.append(annotations + ",");
    }
    if (typeRef != null) {
      sb.append("typeRef:");
      sb.append(typeRef + ",");
    }
    if (name != null) {
      sb.append("name:");
      sb.append(name + ",");
    }
    if (comments != null && !comments.isEmpty()) {
      sb.append("comments:");
      sb.append(comments);
    }
    sb.append("}");
    return sb.toString();
  }

  class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<PropertyFluent.AnnotationsNested<N>>
      implements io.sundr.model.PropertyFluent.AnnotationsNested<N>, Nested<N> {
    AnnotationsNestedImpl(java.lang.Integer index, AnnotationRef item) {
      this.index = index;
      this.builder = new AnnotationRefBuilder(this, item);
    }

    AnnotationsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.AnnotationRefBuilder(this);
    }

    io.sundr.model.AnnotationRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) PropertyFluentImpl.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  class TypeParamRefTypeNestedImpl<N> extends TypeParamRefFluentImpl<PropertyFluent.TypeParamRefTypeNested<N>>
      implements io.sundr.model.PropertyFluent.TypeParamRefTypeNested<N>, io.sundr.builder.Nested<N> {
    TypeParamRefTypeNestedImpl(io.sundr.model.TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefTypeNestedImpl() {
      this.builder = new io.sundr.model.TypeParamRefBuilder(this);
    }

    io.sundr.model.TypeParamRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endTypeParamRefType() {
      return and();
    }

  }

  class WildcardRefTypeNestedImpl<N> extends WildcardRefFluentImpl<PropertyFluent.WildcardRefTypeNested<N>>
      implements io.sundr.model.PropertyFluent.WildcardRefTypeNested<N>, io.sundr.builder.Nested<N> {
    WildcardRefTypeNestedImpl(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefTypeNestedImpl() {
      this.builder = new io.sundr.model.WildcardRefBuilder(this);
    }

    io.sundr.model.WildcardRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endWildcardRefType() {
      return and();
    }

  }

  class ClassRefTypeNestedImpl<N> extends ClassRefFluentImpl<PropertyFluent.ClassRefTypeNested<N>>
      implements io.sundr.model.PropertyFluent.ClassRefTypeNested<N>, io.sundr.builder.Nested<N> {
    ClassRefTypeNestedImpl(io.sundr.model.ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefTypeNestedImpl() {
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endClassRefType() {
      return and();
    }

  }

  class PrimitiveRefTypeNestedImpl<N> extends PrimitiveRefFluentImpl<PropertyFluent.PrimitiveRefTypeNested<N>>
      implements io.sundr.model.PropertyFluent.PrimitiveRefTypeNested<N>, io.sundr.builder.Nested<N> {
    PrimitiveRefTypeNestedImpl(io.sundr.model.PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefTypeNestedImpl() {
      this.builder = new io.sundr.model.PrimitiveRefBuilder(this);
    }

    io.sundr.model.PrimitiveRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endPrimitiveRefType() {
      return and();
    }

  }

  class VoidRefTypeNestedImpl<N> extends VoidRefFluentImpl<PropertyFluent.VoidRefTypeNested<N>>
      implements io.sundr.model.PropertyFluent.VoidRefTypeNested<N>, io.sundr.builder.Nested<N> {
    VoidRefTypeNestedImpl(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefTypeNestedImpl() {
      this.builder = new io.sundr.model.VoidRefBuilder(this);
    }

    io.sundr.model.VoidRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endVoidRefType() {
      return and();
    }

  }

}
