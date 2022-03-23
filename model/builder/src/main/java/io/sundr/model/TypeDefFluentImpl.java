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
public class TypeDefFluentImpl<A extends TypeDefFluent<A>> extends ModifierSupportFluentImpl<A> implements TypeDefFluent<A> {
  public TypeDefFluentImpl() {
  }

  public TypeDefFluentImpl(io.sundr.model.TypeDef instance) {
    this.withKind(instance.getKind());
    this.withPackageName(instance.getPackageName());
    this.withName(instance.getName());
    this.withComments(instance.getComments());
    this.withAnnotations(instance.getAnnotations());
    this.withExtendsList(instance.getExtendsList());
    this.withImplementsList(instance.getImplementsList());
    this.withParameters(instance.getParameters());
    this.withProperties(instance.getProperties());
    this.withConstructors(instance.getConstructors());
    this.withMethods(instance.getMethods());
    this.withOuterTypeName(instance.getOuterTypeName());
    this.withInnerTypes(instance.getInnerTypes());
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  private Kind kind;
  private String packageName;
  private java.lang.String name;
  private List<java.lang.String> comments = new ArrayList<java.lang.String>();
  private java.util.ArrayList<AnnotationRefBuilder> annotations = new java.util.ArrayList<AnnotationRefBuilder>();
  private java.util.ArrayList<ClassRefBuilder> extendsList = new java.util.ArrayList<ClassRefBuilder>();
  private java.util.ArrayList<io.sundr.model.ClassRefBuilder> implementsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
  private java.util.ArrayList<TypeParamDefBuilder> parameters = new java.util.ArrayList<TypeParamDefBuilder>();
  private java.util.ArrayList<PropertyBuilder> properties = new java.util.ArrayList<PropertyBuilder>();
  private java.util.ArrayList<MethodBuilder> constructors = new java.util.ArrayList<MethodBuilder>();
  private java.util.ArrayList<io.sundr.model.MethodBuilder> methods = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
  private java.lang.String outerTypeName;
  private java.util.ArrayList<TypeDefBuilder> innerTypes = new java.util.ArrayList<io.sundr.model.TypeDefBuilder>();

  public io.sundr.model.Kind getKind() {
    return this.kind;
  }

  public A withKind(io.sundr.model.Kind kind) {
    this.kind = kind;
    return (A) this;
  }

  public Boolean hasKind() {
    return this.kind != null;
  }

  public java.lang.String getPackageName() {
    return this.packageName;
  }

  public A withPackageName(java.lang.String packageName) {
    this.packageName = packageName;
    return (A) this;
  }

  public java.lang.Boolean hasPackageName() {
    return this.packageName != null;
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

  public A addToComments(Integer index, java.lang.String item) {
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

  public A addAllToComments(Collection<java.lang.String> items) {
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

  public java.lang.String getMatchingComment(Predicate<java.lang.String> predicate) {
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

  public A addToAnnotations(java.lang.Integer index, io.sundr.model.AnnotationRef item) {
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

  public A addAllToAnnotations(java.util.Collection<io.sundr.model.AnnotationRef> items) {
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

  public A removeMatchingFromAnnotations(java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate) {
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

  public java.lang.Boolean hasMatchingAnnotation(java.util.function.Predicate<io.sundr.model.AnnotationRefBuilder> predicate) {
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

  public TypeDefFluent.AnnotationsNested<A> addNewAnnotation() {
    return new TypeDefFluentImpl.AnnotationsNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> addNewAnnotationLike(io.sundr.model.AnnotationRef item) {
    return new TypeDefFluentImpl.AnnotationsNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> setNewAnnotationLike(java.lang.Integer index,
      io.sundr.model.AnnotationRef item) {
    return new io.sundr.model.TypeDefFluentImpl.AnnotationsNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editAnnotation(java.lang.Integer index) {
    if (annotations.size() <= index)
      throw new RuntimeException("Can't edit annotations. Index exceeds size.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0)
      throw new RuntimeException("Can't edit first annotations. The list is empty.");
    return setNewAnnotationLike(0, buildAnnotation(0));
  }

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last annotations. The list is empty.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public io.sundr.model.TypeDefFluent.AnnotationsNested<A> editMatchingAnnotation(
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

  public A addToExtendsList(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.extendsList == null) {
      this.extendsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    _visitables.get("extendsList").add(index >= 0 ? index : _visitables.get("extendsList").size(), builder);
    this.extendsList.add(index >= 0 ? index : extendsList.size(), builder);
    return (A) this;
  }

  public A setToExtendsList(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.extendsList == null) {
      this.extendsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    if (index < 0 || index >= _visitables.get("extendsList").size()) {
      _visitables.get("extendsList").add(builder);
    } else {
      _visitables.get("extendsList").set(index, builder);
    }
    if (index < 0 || index >= extendsList.size()) {
      extendsList.add(builder);
    } else {
      extendsList.set(index, builder);
    }
    return (A) this;
  }

  public A addToExtendsList(io.sundr.model.ClassRef... items) {
    if (this.extendsList == null) {
      this.extendsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("extendsList").add(builder);
      this.extendsList.add(builder);
    }
    return (A) this;
  }

  public A addAllToExtendsList(java.util.Collection<io.sundr.model.ClassRef> items) {
    if (this.extendsList == null) {
      this.extendsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("extendsList").add(builder);
      this.extendsList.add(builder);
    }
    return (A) this;
  }

  public A removeFromExtendsList(io.sundr.model.ClassRef... items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("extendsList").remove(builder);
      if (this.extendsList != null) {
        this.extendsList.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromExtendsList(java.util.Collection<io.sundr.model.ClassRef> items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("extendsList").remove(builder);
      if (this.extendsList != null) {
        this.extendsList.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromExtendsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    if (extendsList == null)
      return (A) this;
    final Iterator<io.sundr.model.ClassRefBuilder> each = extendsList.iterator();
    final List visitables = _visitables.get("extendsList");
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
   * This method has been deprecated, please use method buildExtendsList instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.ClassRef> getExtendsList() {
    return extendsList != null ? build(extendsList) : null;
  }

  public java.util.List<io.sundr.model.ClassRef> buildExtendsList() {
    return extendsList != null ? build(extendsList) : null;
  }

  public io.sundr.model.ClassRef buildExtendsList(java.lang.Integer index) {
    return this.extendsList.get(index).build();
  }

  public io.sundr.model.ClassRef buildFirstExtendsList() {
    return this.extendsList.get(0).build();
  }

  public io.sundr.model.ClassRef buildLastExtendsList() {
    return this.extendsList.get(extendsList.size() - 1).build();
  }

  public io.sundr.model.ClassRef buildMatchingExtendsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : extendsList) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingExtendsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : extendsList) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withExtendsList(java.util.List<io.sundr.model.ClassRef> extendsList) {
    if (this.extendsList != null) {
      _visitables.get("extendsList").removeAll(this.extendsList);
    }
    if (extendsList != null) {
      this.extendsList = new java.util.ArrayList();
      for (io.sundr.model.ClassRef item : extendsList) {
        this.addToExtendsList(item);
      }
    } else {
      this.extendsList = null;
    }
    return (A) this;
  }

  public A withExtendsList(io.sundr.model.ClassRef... extendsList) {
    if (this.extendsList != null) {
      this.extendsList.clear();
    }
    if (extendsList != null) {
      for (io.sundr.model.ClassRef item : extendsList) {
        this.addToExtendsList(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasExtendsList() {
    return extendsList != null && !extendsList.isEmpty();
  }

  public TypeDefFluent.ExtendsListNested<A> addNewExtendsList() {
    return new TypeDefFluentImpl.ExtendsListNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> addNewExtendsListLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.TypeDefFluentImpl.ExtendsListNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> setNewExtendsListLike(java.lang.Integer index,
      io.sundr.model.ClassRef item) {
    return new io.sundr.model.TypeDefFluentImpl.ExtendsListNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editExtendsList(java.lang.Integer index) {
    if (extendsList.size() <= index)
      throw new RuntimeException("Can't edit extendsList. Index exceeds size.");
    return setNewExtendsListLike(index, buildExtendsList(index));
  }

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editFirstExtendsList() {
    if (extendsList.size() == 0)
      throw new RuntimeException("Can't edit first extendsList. The list is empty.");
    return setNewExtendsListLike(0, buildExtendsList(0));
  }

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editLastExtendsList() {
    int index = extendsList.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last extendsList. The list is empty.");
    return setNewExtendsListLike(index, buildExtendsList(index));
  }

  public io.sundr.model.TypeDefFluent.ExtendsListNested<A> editMatchingExtendsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < extendsList.size(); i++) {
      if (predicate.test(extendsList.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching extendsList. No match found.");
    return setNewExtendsListLike(index, buildExtendsList(index));
  }

  public A addToImplementsList(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.implementsList == null) {
      this.implementsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    _visitables.get("implementsList").add(index >= 0 ? index : _visitables.get("implementsList").size(), builder);
    this.implementsList.add(index >= 0 ? index : implementsList.size(), builder);
    return (A) this;
  }

  public A setToImplementsList(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.implementsList == null) {
      this.implementsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    if (index < 0 || index >= _visitables.get("implementsList").size()) {
      _visitables.get("implementsList").add(builder);
    } else {
      _visitables.get("implementsList").set(index, builder);
    }
    if (index < 0 || index >= implementsList.size()) {
      implementsList.add(builder);
    } else {
      implementsList.set(index, builder);
    }
    return (A) this;
  }

  public A addToImplementsList(io.sundr.model.ClassRef... items) {
    if (this.implementsList == null) {
      this.implementsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("implementsList").add(builder);
      this.implementsList.add(builder);
    }
    return (A) this;
  }

  public A addAllToImplementsList(java.util.Collection<io.sundr.model.ClassRef> items) {
    if (this.implementsList == null) {
      this.implementsList = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("implementsList").add(builder);
      this.implementsList.add(builder);
    }
    return (A) this;
  }

  public A removeFromImplementsList(io.sundr.model.ClassRef... items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("implementsList").remove(builder);
      if (this.implementsList != null) {
        this.implementsList.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromImplementsList(java.util.Collection<io.sundr.model.ClassRef> items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("implementsList").remove(builder);
      if (this.implementsList != null) {
        this.implementsList.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromImplementsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    if (implementsList == null)
      return (A) this;
    final Iterator<io.sundr.model.ClassRefBuilder> each = implementsList.iterator();
    final List visitables = _visitables.get("implementsList");
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
   * This method has been deprecated, please use method buildImplementsList instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.ClassRef> getImplementsList() {
    return implementsList != null ? build(implementsList) : null;
  }

  public java.util.List<io.sundr.model.ClassRef> buildImplementsList() {
    return implementsList != null ? build(implementsList) : null;
  }

  public io.sundr.model.ClassRef buildImplementsList(java.lang.Integer index) {
    return this.implementsList.get(index).build();
  }

  public io.sundr.model.ClassRef buildFirstImplementsList() {
    return this.implementsList.get(0).build();
  }

  public io.sundr.model.ClassRef buildLastImplementsList() {
    return this.implementsList.get(implementsList.size() - 1).build();
  }

  public io.sundr.model.ClassRef buildMatchingImplementsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : implementsList) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingImplementsList(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : implementsList) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withImplementsList(java.util.List<io.sundr.model.ClassRef> implementsList) {
    if (this.implementsList != null) {
      _visitables.get("implementsList").removeAll(this.implementsList);
    }
    if (implementsList != null) {
      this.implementsList = new java.util.ArrayList();
      for (io.sundr.model.ClassRef item : implementsList) {
        this.addToImplementsList(item);
      }
    } else {
      this.implementsList = null;
    }
    return (A) this;
  }

  public A withImplementsList(io.sundr.model.ClassRef... implementsList) {
    if (this.implementsList != null) {
      this.implementsList.clear();
    }
    if (implementsList != null) {
      for (io.sundr.model.ClassRef item : implementsList) {
        this.addToImplementsList(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasImplementsList() {
    return implementsList != null && !implementsList.isEmpty();
  }

  public TypeDefFluent.ImplementsListNested<A> addNewImplementsList() {
    return new TypeDefFluentImpl.ImplementsListNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> addNewImplementsListLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.TypeDefFluentImpl.ImplementsListNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> setNewImplementsListLike(java.lang.Integer index,
      io.sundr.model.ClassRef item) {
    return new io.sundr.model.TypeDefFluentImpl.ImplementsListNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editImplementsList(java.lang.Integer index) {
    if (implementsList.size() <= index)
      throw new RuntimeException("Can't edit implementsList. Index exceeds size.");
    return setNewImplementsListLike(index, buildImplementsList(index));
  }

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editFirstImplementsList() {
    if (implementsList.size() == 0)
      throw new RuntimeException("Can't edit first implementsList. The list is empty.");
    return setNewImplementsListLike(0, buildImplementsList(0));
  }

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editLastImplementsList() {
    int index = implementsList.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last implementsList. The list is empty.");
    return setNewImplementsListLike(index, buildImplementsList(index));
  }

  public io.sundr.model.TypeDefFluent.ImplementsListNested<A> editMatchingImplementsList(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < implementsList.size(); i++) {
      if (predicate.test(implementsList.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching implementsList. No match found.");
    return setNewImplementsListLike(index, buildImplementsList(index));
  }

  public A addToParameters(java.lang.Integer index, io.sundr.model.TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new java.util.ArrayList<io.sundr.model.TypeParamDefBuilder>();
    }
    io.sundr.model.TypeParamDefBuilder builder = new io.sundr.model.TypeParamDefBuilder(item);
    _visitables.get("parameters").add(index >= 0 ? index : _visitables.get("parameters").size(), builder);
    this.parameters.add(index >= 0 ? index : parameters.size(), builder);
    return (A) this;
  }

  public A setToParameters(java.lang.Integer index, io.sundr.model.TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new java.util.ArrayList<io.sundr.model.TypeParamDefBuilder>();
    }
    io.sundr.model.TypeParamDefBuilder builder = new io.sundr.model.TypeParamDefBuilder(item);
    if (index < 0 || index >= _visitables.get("parameters").size()) {
      _visitables.get("parameters").add(builder);
    } else {
      _visitables.get("parameters").set(index, builder);
    }
    if (index < 0 || index >= parameters.size()) {
      parameters.add(builder);
    } else {
      parameters.set(index, builder);
    }
    return (A) this;
  }

  public A addToParameters(io.sundr.model.TypeParamDef... items) {
    if (this.parameters == null) {
      this.parameters = new java.util.ArrayList<io.sundr.model.TypeParamDefBuilder>();
    }
    for (io.sundr.model.TypeParamDef item : items) {
      io.sundr.model.TypeParamDefBuilder builder = new io.sundr.model.TypeParamDefBuilder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A addAllToParameters(java.util.Collection<io.sundr.model.TypeParamDef> items) {
    if (this.parameters == null) {
      this.parameters = new java.util.ArrayList<io.sundr.model.TypeParamDefBuilder>();
    }
    for (io.sundr.model.TypeParamDef item : items) {
      io.sundr.model.TypeParamDefBuilder builder = new io.sundr.model.TypeParamDefBuilder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A removeFromParameters(io.sundr.model.TypeParamDef... items) {
    for (io.sundr.model.TypeParamDef item : items) {
      io.sundr.model.TypeParamDefBuilder builder = new io.sundr.model.TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      if (this.parameters != null) {
        this.parameters.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromParameters(java.util.Collection<io.sundr.model.TypeParamDef> items) {
    for (io.sundr.model.TypeParamDef item : items) {
      io.sundr.model.TypeParamDefBuilder builder = new io.sundr.model.TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      if (this.parameters != null) {
        this.parameters.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromParameters(java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate) {
    if (parameters == null)
      return (A) this;
    final Iterator<io.sundr.model.TypeParamDefBuilder> each = parameters.iterator();
    final List visitables = _visitables.get("parameters");
    while (each.hasNext()) {
      io.sundr.model.TypeParamDefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildParameters instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.TypeParamDef> getParameters() {
    return parameters != null ? build(parameters) : null;
  }

  public java.util.List<io.sundr.model.TypeParamDef> buildParameters() {
    return parameters != null ? build(parameters) : null;
  }

  public io.sundr.model.TypeParamDef buildParameter(java.lang.Integer index) {
    return this.parameters.get(index).build();
  }

  public io.sundr.model.TypeParamDef buildFirstParameter() {
    return this.parameters.get(0).build();
  }

  public io.sundr.model.TypeParamDef buildLastParameter() {
    return this.parameters.get(parameters.size() - 1).build();
  }

  public io.sundr.model.TypeParamDef buildMatchingParameter(
      java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate) {
    for (io.sundr.model.TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingParameter(java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate) {
    for (io.sundr.model.TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withParameters(java.util.List<io.sundr.model.TypeParamDef> parameters) {
    if (this.parameters != null) {
      _visitables.get("parameters").removeAll(this.parameters);
    }
    if (parameters != null) {
      this.parameters = new java.util.ArrayList();
      for (io.sundr.model.TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    } else {
      this.parameters = null;
    }
    return (A) this;
  }

  public A withParameters(io.sundr.model.TypeParamDef... parameters) {
    if (this.parameters != null) {
      this.parameters.clear();
    }
    if (parameters != null) {
      for (io.sundr.model.TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasParameters() {
    return parameters != null && !parameters.isEmpty();
  }

  public TypeDefFluent.ParametersNested<A> addNewParameter() {
    return new TypeDefFluentImpl.ParametersNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.ParametersNested<A> addNewParameterLike(io.sundr.model.TypeParamDef item) {
    return new io.sundr.model.TypeDefFluentImpl.ParametersNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.ParametersNested<A> setNewParameterLike(java.lang.Integer index,
      io.sundr.model.TypeParamDef item) {
    return new io.sundr.model.TypeDefFluentImpl.ParametersNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editParameter(java.lang.Integer index) {
    if (parameters.size() <= index)
      throw new RuntimeException("Can't edit parameters. Index exceeds size.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editFirstParameter() {
    if (parameters.size() == 0)
      throw new RuntimeException("Can't edit first parameters. The list is empty.");
    return setNewParameterLike(0, buildParameter(0));
  }

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editLastParameter() {
    int index = parameters.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last parameters. The list is empty.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public io.sundr.model.TypeDefFluent.ParametersNested<A> editMatchingParameter(
      java.util.function.Predicate<io.sundr.model.TypeParamDefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < parameters.size(); i++) {
      if (predicate.test(parameters.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching parameters. No match found.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public A addToProperties(java.lang.Integer index, io.sundr.model.Property item) {
    if (this.properties == null) {
      this.properties = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
    _visitables.get("properties").add(index >= 0 ? index : _visitables.get("properties").size(), builder);
    this.properties.add(index >= 0 ? index : properties.size(), builder);
    return (A) this;
  }

  public A setToProperties(java.lang.Integer index, io.sundr.model.Property item) {
    if (this.properties == null) {
      this.properties = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
    if (index < 0 || index >= _visitables.get("properties").size()) {
      _visitables.get("properties").add(builder);
    } else {
      _visitables.get("properties").set(index, builder);
    }
    if (index < 0 || index >= properties.size()) {
      properties.add(builder);
    } else {
      properties.set(index, builder);
    }
    return (A) this;
  }

  public A addToProperties(io.sundr.model.Property... items) {
    if (this.properties == null) {
      this.properties = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("properties").add(builder);
      this.properties.add(builder);
    }
    return (A) this;
  }

  public A addAllToProperties(java.util.Collection<io.sundr.model.Property> items) {
    if (this.properties == null) {
      this.properties = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("properties").add(builder);
      this.properties.add(builder);
    }
    return (A) this;
  }

  public A removeFromProperties(io.sundr.model.Property... items) {
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      if (this.properties != null) {
        this.properties.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromProperties(java.util.Collection<io.sundr.model.Property> items) {
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("properties").remove(builder);
      if (this.properties != null) {
        this.properties.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromProperties(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    if (properties == null)
      return (A) this;
    final Iterator<io.sundr.model.PropertyBuilder> each = properties.iterator();
    final List visitables = _visitables.get("properties");
    while (each.hasNext()) {
      io.sundr.model.PropertyBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildProperties instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Property> getProperties() {
    return properties != null ? build(properties) : null;
  }

  public java.util.List<io.sundr.model.Property> buildProperties() {
    return properties != null ? build(properties) : null;
  }

  public io.sundr.model.Property buildProperty(java.lang.Integer index) {
    return this.properties.get(index).build();
  }

  public io.sundr.model.Property buildFirstProperty() {
    return this.properties.get(0).build();
  }

  public io.sundr.model.Property buildLastProperty() {
    return this.properties.get(properties.size() - 1).build();
  }

  public io.sundr.model.Property buildMatchingProperty(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    for (io.sundr.model.PropertyBuilder item : properties) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingProperty(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    for (io.sundr.model.PropertyBuilder item : properties) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withProperties(java.util.List<io.sundr.model.Property> properties) {
    if (this.properties != null) {
      _visitables.get("properties").removeAll(this.properties);
    }
    if (properties != null) {
      this.properties = new java.util.ArrayList();
      for (io.sundr.model.Property item : properties) {
        this.addToProperties(item);
      }
    } else {
      this.properties = null;
    }
    return (A) this;
  }

  public A withProperties(io.sundr.model.Property... properties) {
    if (this.properties != null) {
      this.properties.clear();
    }
    if (properties != null) {
      for (io.sundr.model.Property item : properties) {
        this.addToProperties(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasProperties() {
    return properties != null && !properties.isEmpty();
  }

  public TypeDefFluent.PropertiesNested<A> addNewProperty() {
    return new TypeDefFluentImpl.PropertiesNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> addNewPropertyLike(io.sundr.model.Property item) {
    return new io.sundr.model.TypeDefFluentImpl.PropertiesNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> setNewPropertyLike(java.lang.Integer index,
      io.sundr.model.Property item) {
    return new io.sundr.model.TypeDefFluentImpl.PropertiesNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editProperty(java.lang.Integer index) {
    if (properties.size() <= index)
      throw new RuntimeException("Can't edit properties. Index exceeds size.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editFirstProperty() {
    if (properties.size() == 0)
      throw new RuntimeException("Can't edit first properties. The list is empty.");
    return setNewPropertyLike(0, buildProperty(0));
  }

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editLastProperty() {
    int index = properties.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last properties. The list is empty.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public io.sundr.model.TypeDefFluent.PropertiesNested<A> editMatchingProperty(
      java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < properties.size(); i++) {
      if (predicate.test(properties.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching properties. No match found.");
    return setNewPropertyLike(index, buildProperty(index));
  }

  public A addToConstructors(java.lang.Integer index, io.sundr.model.Method item) {
    if (this.constructors == null) {
      this.constructors = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
    _visitables.get("constructors").add(index >= 0 ? index : _visitables.get("constructors").size(), builder);
    this.constructors.add(index >= 0 ? index : constructors.size(), builder);
    return (A) this;
  }

  public A setToConstructors(java.lang.Integer index, io.sundr.model.Method item) {
    if (this.constructors == null) {
      this.constructors = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
    if (index < 0 || index >= _visitables.get("constructors").size()) {
      _visitables.get("constructors").add(builder);
    } else {
      _visitables.get("constructors").set(index, builder);
    }
    if (index < 0 || index >= constructors.size()) {
      constructors.add(builder);
    } else {
      constructors.set(index, builder);
    }
    return (A) this;
  }

  public A addToConstructors(io.sundr.model.Method... items) {
    if (this.constructors == null) {
      this.constructors = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("constructors").add(builder);
      this.constructors.add(builder);
    }
    return (A) this;
  }

  public A addAllToConstructors(java.util.Collection<io.sundr.model.Method> items) {
    if (this.constructors == null) {
      this.constructors = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("constructors").add(builder);
      this.constructors.add(builder);
    }
    return (A) this;
  }

  public A removeFromConstructors(io.sundr.model.Method... items) {
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("constructors").remove(builder);
      if (this.constructors != null) {
        this.constructors.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromConstructors(java.util.Collection<io.sundr.model.Method> items) {
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("constructors").remove(builder);
      if (this.constructors != null) {
        this.constructors.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromConstructors(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    if (constructors == null)
      return (A) this;
    final Iterator<io.sundr.model.MethodBuilder> each = constructors.iterator();
    final List visitables = _visitables.get("constructors");
    while (each.hasNext()) {
      io.sundr.model.MethodBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildConstructors instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Method> getConstructors() {
    return constructors != null ? build(constructors) : null;
  }

  public java.util.List<io.sundr.model.Method> buildConstructors() {
    return constructors != null ? build(constructors) : null;
  }

  public io.sundr.model.Method buildConstructor(java.lang.Integer index) {
    return this.constructors.get(index).build();
  }

  public io.sundr.model.Method buildFirstConstructor() {
    return this.constructors.get(0).build();
  }

  public io.sundr.model.Method buildLastConstructor() {
    return this.constructors.get(constructors.size() - 1).build();
  }

  public io.sundr.model.Method buildMatchingConstructor(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    for (io.sundr.model.MethodBuilder item : constructors) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingConstructor(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    for (io.sundr.model.MethodBuilder item : constructors) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withConstructors(java.util.List<io.sundr.model.Method> constructors) {
    if (this.constructors != null) {
      _visitables.get("constructors").removeAll(this.constructors);
    }
    if (constructors != null) {
      this.constructors = new java.util.ArrayList();
      for (io.sundr.model.Method item : constructors) {
        this.addToConstructors(item);
      }
    } else {
      this.constructors = null;
    }
    return (A) this;
  }

  public A withConstructors(io.sundr.model.Method... constructors) {
    if (this.constructors != null) {
      this.constructors.clear();
    }
    if (constructors != null) {
      for (io.sundr.model.Method item : constructors) {
        this.addToConstructors(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasConstructors() {
    return constructors != null && !constructors.isEmpty();
  }

  public TypeDefFluent.ConstructorsNested<A> addNewConstructor() {
    return new TypeDefFluentImpl.ConstructorsNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> addNewConstructorLike(io.sundr.model.Method item) {
    return new io.sundr.model.TypeDefFluentImpl.ConstructorsNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> setNewConstructorLike(java.lang.Integer index,
      io.sundr.model.Method item) {
    return new io.sundr.model.TypeDefFluentImpl.ConstructorsNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editConstructor(java.lang.Integer index) {
    if (constructors.size() <= index)
      throw new RuntimeException("Can't edit constructors. Index exceeds size.");
    return setNewConstructorLike(index, buildConstructor(index));
  }

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editFirstConstructor() {
    if (constructors.size() == 0)
      throw new RuntimeException("Can't edit first constructors. The list is empty.");
    return setNewConstructorLike(0, buildConstructor(0));
  }

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editLastConstructor() {
    int index = constructors.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last constructors. The list is empty.");
    return setNewConstructorLike(index, buildConstructor(index));
  }

  public io.sundr.model.TypeDefFluent.ConstructorsNested<A> editMatchingConstructor(
      java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < constructors.size(); i++) {
      if (predicate.test(constructors.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching constructors. No match found.");
    return setNewConstructorLike(index, buildConstructor(index));
  }

  public A addToMethods(java.lang.Integer index, io.sundr.model.Method item) {
    if (this.methods == null) {
      this.methods = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
    _visitables.get("methods").add(index >= 0 ? index : _visitables.get("methods").size(), builder);
    this.methods.add(index >= 0 ? index : methods.size(), builder);
    return (A) this;
  }

  public A setToMethods(java.lang.Integer index, io.sundr.model.Method item) {
    if (this.methods == null) {
      this.methods = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
    if (index < 0 || index >= _visitables.get("methods").size()) {
      _visitables.get("methods").add(builder);
    } else {
      _visitables.get("methods").set(index, builder);
    }
    if (index < 0 || index >= methods.size()) {
      methods.add(builder);
    } else {
      methods.set(index, builder);
    }
    return (A) this;
  }

  public A addToMethods(io.sundr.model.Method... items) {
    if (this.methods == null) {
      this.methods = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("methods").add(builder);
      this.methods.add(builder);
    }
    return (A) this;
  }

  public A addAllToMethods(java.util.Collection<io.sundr.model.Method> items) {
    if (this.methods == null) {
      this.methods = new java.util.ArrayList<io.sundr.model.MethodBuilder>();
    }
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("methods").add(builder);
      this.methods.add(builder);
    }
    return (A) this;
  }

  public A removeFromMethods(io.sundr.model.Method... items) {
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("methods").remove(builder);
      if (this.methods != null) {
        this.methods.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromMethods(java.util.Collection<io.sundr.model.Method> items) {
    for (io.sundr.model.Method item : items) {
      io.sundr.model.MethodBuilder builder = new io.sundr.model.MethodBuilder(item);
      _visitables.get("methods").remove(builder);
      if (this.methods != null) {
        this.methods.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromMethods(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    if (methods == null)
      return (A) this;
    final Iterator<io.sundr.model.MethodBuilder> each = methods.iterator();
    final List visitables = _visitables.get("methods");
    while (each.hasNext()) {
      io.sundr.model.MethodBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildMethods instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Method> getMethods() {
    return methods != null ? build(methods) : null;
  }

  public java.util.List<io.sundr.model.Method> buildMethods() {
    return methods != null ? build(methods) : null;
  }

  public io.sundr.model.Method buildMethod(java.lang.Integer index) {
    return this.methods.get(index).build();
  }

  public io.sundr.model.Method buildFirstMethod() {
    return this.methods.get(0).build();
  }

  public io.sundr.model.Method buildLastMethod() {
    return this.methods.get(methods.size() - 1).build();
  }

  public io.sundr.model.Method buildMatchingMethod(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    for (io.sundr.model.MethodBuilder item : methods) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingMethod(java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    for (io.sundr.model.MethodBuilder item : methods) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withMethods(java.util.List<io.sundr.model.Method> methods) {
    if (this.methods != null) {
      _visitables.get("methods").removeAll(this.methods);
    }
    if (methods != null) {
      this.methods = new java.util.ArrayList();
      for (io.sundr.model.Method item : methods) {
        this.addToMethods(item);
      }
    } else {
      this.methods = null;
    }
    return (A) this;
  }

  public A withMethods(io.sundr.model.Method... methods) {
    if (this.methods != null) {
      this.methods.clear();
    }
    if (methods != null) {
      for (io.sundr.model.Method item : methods) {
        this.addToMethods(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasMethods() {
    return methods != null && !methods.isEmpty();
  }

  public TypeDefFluent.MethodsNested<A> addNewMethod() {
    return new TypeDefFluentImpl.MethodsNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.MethodsNested<A> addNewMethodLike(io.sundr.model.Method item) {
    return new io.sundr.model.TypeDefFluentImpl.MethodsNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.MethodsNested<A> setNewMethodLike(java.lang.Integer index, io.sundr.model.Method item) {
    return new io.sundr.model.TypeDefFluentImpl.MethodsNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editMethod(java.lang.Integer index) {
    if (methods.size() <= index)
      throw new RuntimeException("Can't edit methods. Index exceeds size.");
    return setNewMethodLike(index, buildMethod(index));
  }

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editFirstMethod() {
    if (methods.size() == 0)
      throw new RuntimeException("Can't edit first methods. The list is empty.");
    return setNewMethodLike(0, buildMethod(0));
  }

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editLastMethod() {
    int index = methods.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last methods. The list is empty.");
    return setNewMethodLike(index, buildMethod(index));
  }

  public io.sundr.model.TypeDefFluent.MethodsNested<A> editMatchingMethod(
      java.util.function.Predicate<io.sundr.model.MethodBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < methods.size(); i++) {
      if (predicate.test(methods.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching methods. No match found.");
    return setNewMethodLike(index, buildMethod(index));
  }

  public java.lang.String getOuterTypeName() {
    return this.outerTypeName;
  }

  public A withOuterTypeName(java.lang.String outerTypeName) {
    this.outerTypeName = outerTypeName;
    return (A) this;
  }

  public java.lang.Boolean hasOuterTypeName() {
    return this.outerTypeName != null;
  }

  public A addToInnerTypes(java.lang.Integer index, io.sundr.model.TypeDef item) {
    if (this.innerTypes == null) {
      this.innerTypes = new java.util.ArrayList<io.sundr.model.TypeDefBuilder>();
    }
    io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder(item);
    _visitables.get("innerTypes").add(index >= 0 ? index : _visitables.get("innerTypes").size(), builder);
    this.innerTypes.add(index >= 0 ? index : innerTypes.size(), builder);
    return (A) this;
  }

  public A setToInnerTypes(java.lang.Integer index, io.sundr.model.TypeDef item) {
    if (this.innerTypes == null) {
      this.innerTypes = new java.util.ArrayList<io.sundr.model.TypeDefBuilder>();
    }
    io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder(item);
    if (index < 0 || index >= _visitables.get("innerTypes").size()) {
      _visitables.get("innerTypes").add(builder);
    } else {
      _visitables.get("innerTypes").set(index, builder);
    }
    if (index < 0 || index >= innerTypes.size()) {
      innerTypes.add(builder);
    } else {
      innerTypes.set(index, builder);
    }
    return (A) this;
  }

  public A addToInnerTypes(io.sundr.model.TypeDef... items) {
    if (this.innerTypes == null) {
      this.innerTypes = new java.util.ArrayList<io.sundr.model.TypeDefBuilder>();
    }
    for (io.sundr.model.TypeDef item : items) {
      io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder(item);
      _visitables.get("innerTypes").add(builder);
      this.innerTypes.add(builder);
    }
    return (A) this;
  }

  public A addAllToInnerTypes(java.util.Collection<io.sundr.model.TypeDef> items) {
    if (this.innerTypes == null) {
      this.innerTypes = new java.util.ArrayList<io.sundr.model.TypeDefBuilder>();
    }
    for (io.sundr.model.TypeDef item : items) {
      io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder(item);
      _visitables.get("innerTypes").add(builder);
      this.innerTypes.add(builder);
    }
    return (A) this;
  }

  public A removeFromInnerTypes(io.sundr.model.TypeDef... items) {
    for (io.sundr.model.TypeDef item : items) {
      io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder(item);
      _visitables.get("innerTypes").remove(builder);
      if (this.innerTypes != null) {
        this.innerTypes.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromInnerTypes(java.util.Collection<io.sundr.model.TypeDef> items) {
    for (io.sundr.model.TypeDef item : items) {
      io.sundr.model.TypeDefBuilder builder = new io.sundr.model.TypeDefBuilder(item);
      _visitables.get("innerTypes").remove(builder);
      if (this.innerTypes != null) {
        this.innerTypes.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromInnerTypes(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate) {
    if (innerTypes == null)
      return (A) this;
    final Iterator<io.sundr.model.TypeDefBuilder> each = innerTypes.iterator();
    final List visitables = _visitables.get("innerTypes");
    while (each.hasNext()) {
      io.sundr.model.TypeDefBuilder builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  /**
   * This method has been deprecated, please use method buildInnerTypes instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.TypeDef> getInnerTypes() {
    return innerTypes != null ? build(innerTypes) : null;
  }

  public java.util.List<io.sundr.model.TypeDef> buildInnerTypes() {
    return innerTypes != null ? build(innerTypes) : null;
  }

  public io.sundr.model.TypeDef buildInnerType(java.lang.Integer index) {
    return this.innerTypes.get(index).build();
  }

  public io.sundr.model.TypeDef buildFirstInnerType() {
    return this.innerTypes.get(0).build();
  }

  public io.sundr.model.TypeDef buildLastInnerType() {
    return this.innerTypes.get(innerTypes.size() - 1).build();
  }

  public io.sundr.model.TypeDef buildMatchingInnerType(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate) {
    for (io.sundr.model.TypeDefBuilder item : innerTypes) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingInnerType(java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate) {
    for (io.sundr.model.TypeDefBuilder item : innerTypes) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withInnerTypes(java.util.List<io.sundr.model.TypeDef> innerTypes) {
    if (this.innerTypes != null) {
      _visitables.get("innerTypes").removeAll(this.innerTypes);
    }
    if (innerTypes != null) {
      this.innerTypes = new java.util.ArrayList();
      for (io.sundr.model.TypeDef item : innerTypes) {
        this.addToInnerTypes(item);
      }
    } else {
      this.innerTypes = null;
    }
    return (A) this;
  }

  public A withInnerTypes(io.sundr.model.TypeDef... innerTypes) {
    if (this.innerTypes != null) {
      this.innerTypes.clear();
    }
    if (innerTypes != null) {
      for (io.sundr.model.TypeDef item : innerTypes) {
        this.addToInnerTypes(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasInnerTypes() {
    return innerTypes != null && !innerTypes.isEmpty();
  }

  public A addNewInnerType(java.lang.String fullyQualifiedName) {
    return (A) addToInnerTypes(new TypeDef(fullyQualifiedName));
  }

  public TypeDefFluent.InnerTypesNested<A> addNewInnerType() {
    return new TypeDefFluentImpl.InnerTypesNestedImpl();
  }

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> addNewInnerTypeLike(io.sundr.model.TypeDef item) {
    return new io.sundr.model.TypeDefFluentImpl.InnerTypesNestedImpl(-1, item);
  }

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> setNewInnerTypeLike(java.lang.Integer index,
      io.sundr.model.TypeDef item) {
    return new io.sundr.model.TypeDefFluentImpl.InnerTypesNestedImpl(index, item);
  }

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editInnerType(java.lang.Integer index) {
    if (innerTypes.size() <= index)
      throw new RuntimeException("Can't edit innerTypes. Index exceeds size.");
    return setNewInnerTypeLike(index, buildInnerType(index));
  }

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editFirstInnerType() {
    if (innerTypes.size() == 0)
      throw new RuntimeException("Can't edit first innerTypes. The list is empty.");
    return setNewInnerTypeLike(0, buildInnerType(0));
  }

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editLastInnerType() {
    int index = innerTypes.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last innerTypes. The list is empty.");
    return setNewInnerTypeLike(index, buildInnerType(index));
  }

  public io.sundr.model.TypeDefFluent.InnerTypesNested<A> editMatchingInnerType(
      java.util.function.Predicate<io.sundr.model.TypeDefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < innerTypes.size(); i++) {
      if (predicate.test(innerTypes.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching innerTypes. No match found.");
    return setNewInnerTypeLike(index, buildInnerType(index));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeDefFluentImpl that = (TypeDefFluentImpl) o;
    if (kind != null ? !kind.equals(that.kind) : that.kind != null)
      return false;
    if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (comments != null ? !comments.equals(that.comments) : that.comments != null)
      return false;
    if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null)
      return false;
    if (extendsList != null ? !extendsList.equals(that.extendsList) : that.extendsList != null)
      return false;
    if (implementsList != null ? !implementsList.equals(that.implementsList) : that.implementsList != null)
      return false;
    if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null)
      return false;
    if (properties != null ? !properties.equals(that.properties) : that.properties != null)
      return false;
    if (constructors != null ? !constructors.equals(that.constructors) : that.constructors != null)
      return false;
    if (methods != null ? !methods.equals(that.methods) : that.methods != null)
      return false;
    if (outerTypeName != null ? !outerTypeName.equals(that.outerTypeName) : that.outerTypeName != null)
      return false;
    if (innerTypes != null ? !innerTypes.equals(that.innerTypes) : that.innerTypes != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(kind, packageName, name, comments, annotations, extendsList, implementsList, parameters,
        properties, constructors, methods, outerTypeName, innerTypes, super.hashCode());
  }

  public java.lang.String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (kind != null) {
      sb.append("kind:");
      sb.append(kind + ",");
    }
    if (packageName != null) {
      sb.append("packageName:");
      sb.append(packageName + ",");
    }
    if (name != null) {
      sb.append("name:");
      sb.append(name + ",");
    }
    if (comments != null && !comments.isEmpty()) {
      sb.append("comments:");
      sb.append(comments + ",");
    }
    if (annotations != null && !annotations.isEmpty()) {
      sb.append("annotations:");
      sb.append(annotations + ",");
    }
    if (extendsList != null && !extendsList.isEmpty()) {
      sb.append("extendsList:");
      sb.append(extendsList + ",");
    }
    if (implementsList != null && !implementsList.isEmpty()) {
      sb.append("implementsList:");
      sb.append(implementsList + ",");
    }
    if (parameters != null && !parameters.isEmpty()) {
      sb.append("parameters:");
      sb.append(parameters + ",");
    }
    if (properties != null && !properties.isEmpty()) {
      sb.append("properties:");
      sb.append(properties + ",");
    }
    if (constructors != null && !constructors.isEmpty()) {
      sb.append("constructors:");
      sb.append(constructors + ",");
    }
    if (methods != null && !methods.isEmpty()) {
      sb.append("methods:");
      sb.append(methods + ",");
    }
    if (outerTypeName != null) {
      sb.append("outerTypeName:");
      sb.append(outerTypeName + ",");
    }
    if (innerTypes != null && !innerTypes.isEmpty()) {
      sb.append("innerTypes:");
      sb.append(innerTypes);
    }
    sb.append("}");
    return sb.toString();
  }

  class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<TypeDefFluent.AnnotationsNested<N>>
      implements io.sundr.model.TypeDefFluent.AnnotationsNested<N>, Nested<N> {
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
      return (N) TypeDefFluentImpl.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  class ExtendsListNestedImpl<N> extends ClassRefFluentImpl<TypeDefFluent.ExtendsListNested<N>>
      implements io.sundr.model.TypeDefFluent.ExtendsListNested<N>, io.sundr.builder.Nested<N> {
    ExtendsListNestedImpl(java.lang.Integer index, io.sundr.model.ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ExtendsListNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToExtendsList(index, builder.build());
    }

    public N endExtendsList() {
      return and();
    }

  }

  class ImplementsListNestedImpl<N> extends ClassRefFluentImpl<TypeDefFluent.ImplementsListNested<N>>
      implements io.sundr.model.TypeDefFluent.ImplementsListNested<N>, io.sundr.builder.Nested<N> {
    ImplementsListNestedImpl(java.lang.Integer index, io.sundr.model.ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ImplementsListNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToImplementsList(index, builder.build());
    }

    public N endImplementsList() {
      return and();
    }

  }

  class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<TypeDefFluent.ParametersNested<N>>
      implements io.sundr.model.TypeDefFluent.ParametersNested<N>, io.sundr.builder.Nested<N> {
    ParametersNestedImpl(java.lang.Integer index, TypeParamDef item) {
      this.index = index;
      this.builder = new TypeParamDefBuilder(this, item);
    }

    ParametersNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.TypeParamDefBuilder(this);
    }

    io.sundr.model.TypeParamDefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToParameters(index, builder.build());
    }

    public N endParameter() {
      return and();
    }

  }

  class PropertiesNestedImpl<N> extends PropertyFluentImpl<TypeDefFluent.PropertiesNested<N>>
      implements io.sundr.model.TypeDefFluent.PropertiesNested<N>, io.sundr.builder.Nested<N> {
    PropertiesNestedImpl(java.lang.Integer index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    PropertiesNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.PropertyBuilder(this);
    }

    io.sundr.model.PropertyBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToProperties(index, builder.build());
    }

    public N endProperty() {
      return and();
    }

  }

  class ConstructorsNestedImpl<N> extends MethodFluentImpl<TypeDefFluent.ConstructorsNested<N>>
      implements io.sundr.model.TypeDefFluent.ConstructorsNested<N>, io.sundr.builder.Nested<N> {
    ConstructorsNestedImpl(java.lang.Integer index, Method item) {
      this.index = index;
      this.builder = new MethodBuilder(this, item);
    }

    ConstructorsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.MethodBuilder(this);
    }

    io.sundr.model.MethodBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToConstructors(index, builder.build());
    }

    public N endConstructor() {
      return and();
    }

  }

  class MethodsNestedImpl<N> extends MethodFluentImpl<TypeDefFluent.MethodsNested<N>>
      implements io.sundr.model.TypeDefFluent.MethodsNested<N>, io.sundr.builder.Nested<N> {
    MethodsNestedImpl(java.lang.Integer index, Method item) {
      this.index = index;
      this.builder = new MethodBuilder(this, item);
    }

    MethodsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.MethodBuilder(this);
    }

    io.sundr.model.MethodBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToMethods(index, builder.build());
    }

    public N endMethod() {
      return and();
    }

  }

  class InnerTypesNestedImpl<N> extends TypeDefFluentImpl<TypeDefFluent.InnerTypesNested<N>>
      implements io.sundr.model.TypeDefFluent.InnerTypesNested<N>, io.sundr.builder.Nested<N> {
    InnerTypesNestedImpl(java.lang.Integer index, io.sundr.model.TypeDef item) {
      this.index = index;
      this.builder = new TypeDefBuilder(this, item);
    }

    InnerTypesNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.TypeDefBuilder(this);
    }

    io.sundr.model.TypeDefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) TypeDefFluentImpl.this.setToInnerTypes(index, builder.build());
    }

    public N endInnerType() {
      return and();
    }

  }

}
