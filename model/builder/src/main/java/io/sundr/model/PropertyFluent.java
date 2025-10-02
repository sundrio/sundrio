package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class PropertyFluent<A extends PropertyFluent<A>> extends ModifierSupportFluent<A> {

  private ArrayList<AnnotationRefBuilder> annotations = new ArrayList<AnnotationRefBuilder>();
  private List<String> comments = new ArrayList<String>();
  private boolean enumConstant;
  private Optional<Expression> initialValue = Optional.empty();
  private String name;
  private boolean synthetic;
  private VisitableBuilder<? extends TypeRef, ?> typeRef;

  public PropertyFluent() {
  }

  public PropertyFluent(Property instance) {
    this.copyInstance(instance);
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

  public A addAllToComments(Collection<String> items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public AnnotationsNested<A> addNewAnnotation() {
    return new AnnotationsNested(-1, null);
  }

  public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item) {
    return new AnnotationsNested(-1, item);
  }

  public A addToAnnotations(AnnotationRef... items) {
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

  public A addToAnnotations(int index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
    if (index < 0 || index >= annotations.size()) {
      _visitables.get("annotations").add(builder);
      annotations.add(builder);
    } else {
      _visitables.get("annotations").add(builder);
      annotations.add(index, builder);
    }
    return (A) this;
  }

  public A addToComments(String... items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A addToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.add(index, item);
    return (A) this;
  }

  public AnnotationRef buildAnnotation(int index) {
    return this.annotations.get(index).build();
  }

  public List<AnnotationRef> buildAnnotations() {
    return this.annotations != null ? build(annotations) : null;
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

  public TypeRef buildTypeRef() {
    return this.typeRef != null ? this.typeRef.build() : null;
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

  protected void copyInstance(Property instance) {
    if (instance != null) {
      this.withModifiers(instance.getModifiers());
      this.withAttributes(instance.getAttributes());
      this.withComments(instance.getComments());
      this.withAnnotations(instance.getAnnotations());
      this.withTypeRef(instance.getTypeRef());
      this.withName(instance.getName());
      this.withInitialValue(instance.getInitialValue());
      this.withEnumConstant(instance.isEnumConstant());
      this.withSynthetic(instance.isSynthetic());
    }
  }

  public AnnotationsNested<A> editAnnotation(int index) {
    if (annotations.size() <= index)
      throw new RuntimeException("Can't edit annotations. Index exceeds size.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0)
      throw new RuntimeException("Can't edit first annotations. The list is empty.");
    return setNewAnnotationLike(0, buildAnnotation(0));
  }

  public AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last annotations. The list is empty.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
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

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PropertyFluent that = (PropertyFluent) o;
    if (!java.util.Objects.equals(comments, that.comments))
      return false;
    if (!java.util.Objects.equals(annotations, that.annotations))
      return false;
    if (!java.util.Objects.equals(typeRef, that.typeRef))
      return false;
    if (!java.util.Objects.equals(name, that.name))
      return false;
    if (!java.util.Objects.equals(initialValue, that.initialValue))
      return false;
    if (enumConstant != that.enumConstant)
      return false;
    if (synthetic != that.synthetic)
      return false;
    return true;
  }

  public String getComment(int index) {
    return this.comments.get(index);
  }

  public List<String> getComments() {
    return this.comments;
  }

  public String getFirstComment() {
    return this.comments.get(0);
  }

  public Optional<Expression> getInitialValue() {
    return this.initialValue;
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

  public String getName() {
    return this.name;
  }

  public boolean hasAnnotations() {
    return this.annotations != null && !(this.annotations.isEmpty());
  }

  public boolean hasComments() {
    return this.comments != null && !(this.comments.isEmpty());
  }

  public boolean hasEnumConstant() {
    return true;
  }

  public boolean hasInitialValue() {
    return this.initialValue != null && this.initialValue.isPresent();
  }

  public boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    for (AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasMatchingComment(Predicate<String> predicate) {
    for (String item : comments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasName() {
    return this.name != null;
  }

  public boolean hasSynthetic() {
    return true;
  }

  public boolean hasTypeRef() {
    return this.typeRef != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(comments, annotations, typeRef, name, initialValue, enumConstant, synthetic,
        super.hashCode());
  }

  public boolean isEnumConstant() {
    return this.enumConstant;
  }

  public boolean isSynthetic() {
    return this.synthetic;
  }

  public A removeAllFromAnnotations(Collection<AnnotationRef> items) {
    if (this.annotations == null)
      return (A) this;
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromComments(Collection<String> items) {
    if (this.comments == null)
      return (A) this;
    for (String item : items) {
      this.comments.remove(item);
    }
    return (A) this;
  }

  public A removeFromAnnotations(AnnotationRef... items) {
    if (this.annotations == null)
      return (A) this;
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
  }

  public A removeFromComments(String... items) {
    if (this.comments == null)
      return (A) this;
    for (String item : items) {
      this.comments.remove(item);
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

  public AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item) {
    return new AnnotationsNested(index, item);
  }

  public A setToAnnotations(int index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
    if (index < 0 || index >= annotations.size()) {
      _visitables.get("annotations").add(builder);
      annotations.add(builder);
    } else {
      _visitables.get("annotations").add(builder);
      annotations.set(index, builder);
    }
    return (A) this;
  }

  public A setToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.set(index, item);
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (comments != null && !comments.isEmpty()) {
      sb.append("comments:");
      sb.append(comments + ",");
    }
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
    if (initialValue != null) {
      sb.append("initialValue:");
      sb.append(initialValue + ",");
    }
    sb.append("enumConstant:");
    sb.append(enumConstant + ",");
    sb.append("synthetic:");
    sb.append(synthetic);
    sb.append("}");
    return sb.toString();
  }

  public A withAnnotations(List<AnnotationRef> annotations) {
    if (this.annotations != null) {
      this._visitables.get("annotations").clear();
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

  public A withAnnotations(AnnotationRef... annotations) {
    if (this.annotations != null) {
      this.annotations.clear();
      _visitables.remove("annotations");
    }
    if (annotations != null) {
      for (AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    }
    return (A) this;
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

  public A withComments(String... comments) {
    if (this.comments != null) {
      this.comments.clear();
      _visitables.remove("comments");
    }
    if (comments != null) {
      for (String item : comments) {
        this.addToComments(item);
      }
    }
    return (A) this;
  }

  public A withEnumConstant() {
    return withEnumConstant(true);
  }

  public A withEnumConstant(boolean enumConstant) {
    this.enumConstant = enumConstant;
    return (A) this;
  }

  public A withInitialValue(Optional<Expression> initialValue) {
    if (initialValue == null || !(initialValue.isPresent())) {
      this.initialValue = Optional.empty();
    } else {
      this.initialValue = initialValue;
    }
    return (A) this;
  }

  public A withInitialValue(Expression initialValue) {
    if (initialValue == null) {
      this.initialValue = Optional.empty();
    } else {
      this.initialValue = Optional.of(initialValue);
    }
    return (A) this;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public ClassRefTypeNested<A> withNewClassRefType() {
    return new ClassRefTypeNested(null);
  }

  public ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item) {
    return new ClassRefTypeNested(item);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefType() {
    return new PrimitiveRefTypeNested(null);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item) {
    return new PrimitiveRefTypeNested(item);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefType() {
    return new TypeParamRefTypeNested(null);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item) {
    return new TypeParamRefTypeNested(item);
  }

  public VoidRefTypeNested<A> withNewVoidRefType() {
    return new VoidRefTypeNested(null);
  }

  public VoidRefTypeNested<A> withNewVoidRefTypeLike(VoidRef item) {
    return new VoidRefTypeNested(item);
  }

  public WildcardRefTypeNested<A> withNewWildcardRefType() {
    return new WildcardRefTypeNested(null);
  }

  public WildcardRefTypeNested<A> withNewWildcardRefTypeLike(WildcardRef item) {
    return new WildcardRefTypeNested(item);
  }

  public A withSynthetic() {
    return withSynthetic(true);
  }

  public A withSynthetic(boolean synthetic) {
    this.synthetic = synthetic;
    return (A) this;
  }

  public A withTypeRef(TypeRef typeRef) {
    if (typeRef == null) {
      this.typeRef = null;
      this._visitables.remove("typeRef");
      return (A) this;
    } else {
      VisitableBuilder<? extends TypeRef, ?> builder = builder(typeRef);
      this._visitables.get("typeRef").clear();
      this._visitables.get("typeRef").add(builder);
      this.typeRef = builder;
      return (A) this;
    }
  }

  public class AnnotationsNested<N> extends AnnotationRefFluent<AnnotationsNested<N>> implements Nested<N> {

    AnnotationRefBuilder builder;
    int index;

    AnnotationsNested(int index, AnnotationRef item) {
      this.index = index;
      this.builder = new AnnotationRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  public class ClassRefTypeNested<N> extends ClassRefFluent<ClassRefTypeNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefTypeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withTypeRef(builder.build());
    }

    public N endClassRefType() {
      return and();
    }

  }

  public class PrimitiveRefTypeNested<N> extends PrimitiveRefFluent<PrimitiveRefTypeNested<N>> implements Nested<N> {

    PrimitiveRefBuilder builder;

    PrimitiveRefTypeNested(PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withTypeRef(builder.build());
    }

    public N endPrimitiveRefType() {
      return and();
    }

  }

  public class TypeParamRefTypeNested<N> extends TypeParamRefFluent<TypeParamRefTypeNested<N>> implements Nested<N> {

    TypeParamRefBuilder builder;

    TypeParamRefTypeNested(TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withTypeRef(builder.build());
    }

    public N endTypeParamRefType() {
      return and();
    }

  }

  public class VoidRefTypeNested<N> extends VoidRefFluent<VoidRefTypeNested<N>> implements Nested<N> {

    VoidRefBuilder builder;

    VoidRefTypeNested(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withTypeRef(builder.build());
    }

    public N endVoidRefType() {
      return and();
    }

  }

  public class WildcardRefTypeNested<N> extends WildcardRefFluent<WildcardRefTypeNested<N>> implements Nested<N> {

    WildcardRefBuilder builder;

    WildcardRefTypeNested(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withTypeRef(builder.build());
    }

    public N endWildcardRefType() {
      return and();
    }

  }
}
