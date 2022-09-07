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

  public PropertyFluentImpl(Property instance) {
    this.withAnnotations(instance.getAnnotations());
    this.withTypeRef(instance.getTypeRef());
    this.withName(instance.getName());
    this.withComments(instance.getComments());
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  private ArrayList<AnnotationRefBuilder> annotations = new ArrayList<AnnotationRefBuilder>();
  private VisitableBuilder<? extends TypeRef, ?> typeRef;
  private String name;
  private List<String> comments = new ArrayList<String>();

  public A addToAnnotations(Integer index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
    _visitables.get("annotations").add(index >= 0 ? index : _visitables.get("annotations").size(), builder);
    this.annotations.add(index >= 0 ? index : annotations.size(), builder);
    return (A) this;
  }

  public A setToAnnotations(Integer index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
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
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").add(builder);
      this.annotations.add(builder);
    }
    return (A) this;
  }

  public A addAllToAnnotations(Collection<AnnotationRef> items) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").add(builder);
      this.annotations.add(builder);
    }
    return (A) this;
  }

  public A removeFromAnnotations(io.sundr.model.AnnotationRef... items) {
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      if (this.annotations != null) {
        this.annotations.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromAnnotations(Collection<AnnotationRef> items) {
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      if (this.annotations != null) {
        this.annotations.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromAnnotations(Predicate<AnnotationRefBuilder> predicate) {
    if (annotations == null)
      return (A) this;
    final Iterator<AnnotationRefBuilder> each = annotations.iterator();
    final List visitables = _visitables.get("annotations");
    while (each.hasNext()) {
      AnnotationRefBuilder builder = each.next();
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
  public List<AnnotationRef> getAnnotations() {
    return annotations != null ? build(annotations) : null;
  }

  public List<AnnotationRef> buildAnnotations() {
    return annotations != null ? build(annotations) : null;
  }

  public AnnotationRef buildAnnotation(Integer index) {
    return this.annotations.get(index).build();
  }

  public AnnotationRef buildFirstAnnotation() {
    return this.annotations.get(0).build();
  }

  public AnnotationRef buildLastAnnotation() {
    return this.annotations.get(annotations.size() - 1).build();
  }

  public AnnotationRef buildMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    for (AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    for (AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withAnnotations(List<AnnotationRef> annotations) {
    if (this.annotations != null) {
      _visitables.get("annotations").removeAll(this.annotations);
    }
    if (annotations != null) {
      this.annotations = new ArrayList();
      for (AnnotationRef item : annotations) {
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
      for (AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    }
    return (A) this;
  }

  public Boolean hasAnnotations() {
    return annotations != null && !annotations.isEmpty();
  }

  public PropertyFluent.AnnotationsNested<A> addNewAnnotation() {
    return new PropertyFluentImpl.AnnotationsNestedImpl();
  }

  public PropertyFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item) {
    return new PropertyFluentImpl.AnnotationsNestedImpl(-1, item);
  }

  public PropertyFluent.AnnotationsNested<A> setNewAnnotationLike(Integer index, AnnotationRef item) {
    return new PropertyFluentImpl.AnnotationsNestedImpl(index, item);
  }

  public PropertyFluent.AnnotationsNested<A> editAnnotation(Integer index) {
    if (annotations.size() <= index)
      throw new RuntimeException("Can't edit annotations. Index exceeds size.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public PropertyFluent.AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0)
      throw new RuntimeException("Can't edit first annotations. The list is empty.");
    return setNewAnnotationLike(0, buildAnnotation(0));
  }

  public PropertyFluent.AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last annotations. The list is empty.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public PropertyFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
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
  @Deprecated
  public TypeRef getTypeRef() {
    return this.typeRef != null ? this.typeRef.build() : null;
  }

  public TypeRef buildTypeRef() {
    return this.typeRef != null ? this.typeRef.build() : null;
  }

  public A withTypeRef(TypeRef typeRef) {
    if (typeRef instanceof TypeParamRef) {
      this.typeRef = new TypeParamRefBuilder((TypeParamRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof WildcardRef) {
      this.typeRef = new WildcardRefBuilder((WildcardRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof ClassRef) {
      this.typeRef = new ClassRefBuilder((ClassRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof PrimitiveRef) {
      this.typeRef = new PrimitiveRefBuilder((PrimitiveRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    if (typeRef instanceof VoidRef) {
      this.typeRef = new VoidRefBuilder((VoidRef) typeRef);
      _visitables.get("typeRef").add(this.typeRef);
    }
    return (A) this;
  }

  public Boolean hasTypeRef() {
    return this.typeRef != null;
  }

  public A withTypeParamRefType(TypeParamRef typeParamRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (typeParamRefType != null) {
      this.typeRef = new TypeParamRefBuilder(typeParamRefType);
      _visitables.get("typeRef").add(this.typeRef);
    } else {
      this.typeRef = null;
      _visitables.get("typeRef").remove(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefType() {
    return new PropertyFluentImpl.TypeParamRefTypeNestedImpl();
  }

  public PropertyFluent.TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item) {
    return new PropertyFluentImpl.TypeParamRefTypeNestedImpl(item);
  }

  public A withWildcardRefType(WildcardRef wildcardRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (wildcardRefType != null) {
      this.typeRef = new WildcardRefBuilder(wildcardRefType);
      _visitables.get("typeRef").add(this.typeRef);
    } else {
      this.typeRef = null;
      _visitables.get("typeRef").remove(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefType() {
    return new PropertyFluentImpl.WildcardRefTypeNestedImpl();
  }

  public PropertyFluent.WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item) {
    return new PropertyFluentImpl.WildcardRefTypeNestedImpl(item);
  }

  public A withClassRefType(ClassRef classRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (classRefType != null) {
      this.typeRef = new ClassRefBuilder(classRefType);
      _visitables.get("typeRef").add(this.typeRef);
    } else {
      this.typeRef = null;
      _visitables.get("typeRef").remove(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefType() {
    return new PropertyFluentImpl.ClassRefTypeNestedImpl();
  }

  public PropertyFluent.ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item) {
    return new PropertyFluentImpl.ClassRefTypeNestedImpl(item);
  }

  public A withPrimitiveRefType(PrimitiveRef primitiveRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (primitiveRefType != null) {
      this.typeRef = new PrimitiveRefBuilder(primitiveRefType);
      _visitables.get("typeRef").add(this.typeRef);
    } else {
      this.typeRef = null;
      _visitables.get("typeRef").remove(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefType() {
    return new PropertyFluentImpl.PrimitiveRefTypeNestedImpl();
  }

  public PropertyFluent.PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item) {
    return new PropertyFluentImpl.PrimitiveRefTypeNestedImpl(item);
  }

  public A withVoidRefType(VoidRef voidRefType) {
    _visitables.get("typeRef").remove(this.typeRef);
    if (voidRefType != null) {
      this.typeRef = new VoidRefBuilder(voidRefType);
      _visitables.get("typeRef").add(this.typeRef);
    } else {
      this.typeRef = null;
      _visitables.get("typeRef").remove(this.typeRef);
    }
    return (A) this;
  }

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefType() {
    return new PropertyFluentImpl.VoidRefTypeNestedImpl();
  }

  public PropertyFluent.VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item) {
    return new PropertyFluentImpl.VoidRefTypeNestedImpl(item);
  }

  public String getName() {
    return this.name;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public Boolean hasName() {
    return this.name != null;
  }

  public A addToComments(Integer index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.add(index, item);
    return (A) this;
  }

  public A setToComments(Integer index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.set(index, item);
    return (A) this;
  }

  public A addToComments(java.lang.String... items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A addAllToComments(Collection<String> items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A removeFromComments(java.lang.String... items) {
    for (String item : items) {
      if (this.comments != null) {
        this.comments.remove(item);
      }
    }
    return (A) this;
  }

  public A removeAllFromComments(Collection<String> items) {
    for (String item : items) {
      if (this.comments != null) {
        this.comments.remove(item);
      }
    }
    return (A) this;
  }

  public List<String> getComments() {
    return this.comments;
  }

  public String getComment(Integer index) {
    return this.comments.get(index);
  }

  public String getFirstComment() {
    return this.comments.get(0);
  }

  public String getLastComment() {
    return this.comments.get(comments.size() - 1);
  }

  public String getMatchingComment(Predicate<String> predicate) {
    for (String item : comments) {
      if (predicate.test(item)) {
        return item;
      }
    }
    return null;
  }

  public Boolean hasMatchingComment(Predicate<String> predicate) {
    for (String item : comments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withComments(List<String> comments) {
    if (comments != null) {
      this.comments = new ArrayList();
      for (String item : comments) {
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
      for (String item : comments) {
        this.addToComments(item);
      }
    }
    return (A) this;
  }

  public Boolean hasComments() {
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

  public String toString() {
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
      implements PropertyFluent.AnnotationsNested<N>, Nested<N> {
    AnnotationsNestedImpl(Integer index, AnnotationRef item) {
      this.index = index;
      this.builder = new AnnotationRefBuilder(this, item);
    }

    AnnotationsNestedImpl() {
      this.index = -1;
      this.builder = new AnnotationRefBuilder(this);
    }

    AnnotationRefBuilder builder;
    Integer index;

    public N and() {
      return (N) PropertyFluentImpl.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  class TypeParamRefTypeNestedImpl<N> extends TypeParamRefFluentImpl<PropertyFluent.TypeParamRefTypeNested<N>>
      implements PropertyFluent.TypeParamRefTypeNested<N>, Nested<N> {
    TypeParamRefTypeNestedImpl(TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefTypeNestedImpl() {
      this.builder = new TypeParamRefBuilder(this);
    }

    TypeParamRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endTypeParamRefType() {
      return and();
    }

  }

  class WildcardRefTypeNestedImpl<N> extends WildcardRefFluentImpl<PropertyFluent.WildcardRefTypeNested<N>>
      implements PropertyFluent.WildcardRefTypeNested<N>, Nested<N> {
    WildcardRefTypeNestedImpl(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefTypeNestedImpl() {
      this.builder = new WildcardRefBuilder(this);
    }

    WildcardRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endWildcardRefType() {
      return and();
    }

  }

  class ClassRefTypeNestedImpl<N> extends ClassRefFluentImpl<PropertyFluent.ClassRefTypeNested<N>>
      implements PropertyFluent.ClassRefTypeNested<N>, Nested<N> {
    ClassRefTypeNestedImpl(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefTypeNestedImpl() {
      this.builder = new ClassRefBuilder(this);
    }

    ClassRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endClassRefType() {
      return and();
    }

  }

  class PrimitiveRefTypeNestedImpl<N> extends PrimitiveRefFluentImpl<PropertyFluent.PrimitiveRefTypeNested<N>>
      implements PropertyFluent.PrimitiveRefTypeNested<N>, Nested<N> {
    PrimitiveRefTypeNestedImpl(PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefTypeNestedImpl() {
      this.builder = new PrimitiveRefBuilder(this);
    }

    PrimitiveRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endPrimitiveRefType() {
      return and();
    }

  }

  class VoidRefTypeNestedImpl<N> extends VoidRefFluentImpl<PropertyFluent.VoidRefTypeNested<N>>
      implements PropertyFluent.VoidRefTypeNested<N>, Nested<N> {
    VoidRefTypeNestedImpl(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefTypeNestedImpl() {
      this.builder = new VoidRefBuilder(this);
    }

    VoidRefBuilder builder;

    public N and() {
      return (N) PropertyFluentImpl.this.withTypeRef(builder.build());
    }

    public N endVoidRefType() {
      return and();
    }

  }

}
