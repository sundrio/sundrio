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
public class MethodFluentImpl<A extends MethodFluent<A>> extends ModifierSupportFluentImpl<A> implements MethodFluent<A> {
  public MethodFluentImpl() {
  }

  public MethodFluentImpl(io.sundr.model.Method instance) {
    this.withComments(instance.getComments());
    this.withAnnotations(instance.getAnnotations());
    this.withParameters(instance.getParameters());
    this.withName(instance.getName());
    this.withReturnType(instance.getReturnType());
    this.withArguments(instance.getArguments());
    this.withVarArgPreferred(instance.isVarArgPreferred());
    this.withExceptions(instance.getExceptions());
    this.withDefaultMethod(instance.isDefaultMethod());
    this.withBlock(instance.getBlock());
    this.withModifiers(instance.getModifiers());
    this.withAttributes(instance.getAttributes());
  }

  private List<String> comments = new ArrayList<java.lang.String>();
  private java.util.ArrayList<AnnotationRefBuilder> annotations = new java.util.ArrayList<AnnotationRefBuilder>();
  private java.util.ArrayList<TypeParamDefBuilder> parameters = new java.util.ArrayList<TypeParamDefBuilder>();
  private java.lang.String name;
  private VisitableBuilder<? extends TypeRef, ?> returnType;
  private java.util.ArrayList<PropertyBuilder> arguments = new java.util.ArrayList<PropertyBuilder>();
  private boolean varArgPreferred;
  private java.util.ArrayList<ClassRefBuilder> exceptions = new java.util.ArrayList<ClassRefBuilder>();
  private boolean defaultMethod;
  private BlockBuilder block;

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

  public Boolean hasMatchingComment(java.util.function.Predicate<java.lang.String> predicate) {
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

  public MethodFluent.AnnotationsNested<A> addNewAnnotation() {
    return new MethodFluentImpl.AnnotationsNestedImpl();
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> addNewAnnotationLike(io.sundr.model.AnnotationRef item) {
    return new MethodFluentImpl.AnnotationsNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> setNewAnnotationLike(java.lang.Integer index,
      io.sundr.model.AnnotationRef item) {
    return new io.sundr.model.MethodFluentImpl.AnnotationsNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editAnnotation(java.lang.Integer index) {
    if (annotations.size() <= index)
      throw new RuntimeException("Can't edit annotations. Index exceeds size.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0)
      throw new RuntimeException("Can't edit first annotations. The list is empty.");
    return setNewAnnotationLike(0, buildAnnotation(0));
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last annotations. The list is empty.");
    return setNewAnnotationLike(index, buildAnnotation(index));
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editMatchingAnnotation(
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

  public MethodFluent.ParametersNested<A> addNewParameter() {
    return new MethodFluentImpl.ParametersNestedImpl();
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> addNewParameterLike(io.sundr.model.TypeParamDef item) {
    return new io.sundr.model.MethodFluentImpl.ParametersNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> setNewParameterLike(java.lang.Integer index,
      io.sundr.model.TypeParamDef item) {
    return new io.sundr.model.MethodFluentImpl.ParametersNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> editParameter(java.lang.Integer index) {
    if (parameters.size() <= index)
      throw new RuntimeException("Can't edit parameters. Index exceeds size.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> editFirstParameter() {
    if (parameters.size() == 0)
      throw new RuntimeException("Can't edit first parameters. The list is empty.");
    return setNewParameterLike(0, buildParameter(0));
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> editLastParameter() {
    int index = parameters.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last parameters. The list is empty.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> editMatchingParameter(
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

  /**
   * This method has been deprecated, please use method buildReturnType instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public io.sundr.model.TypeRef getReturnType() {
    return this.returnType != null ? this.returnType.build() : null;
  }

  public io.sundr.model.TypeRef buildReturnType() {
    return this.returnType != null ? this.returnType.build() : null;
  }

  public A withReturnType(io.sundr.model.TypeRef returnType) {
    if (returnType instanceof TypeParamRef) {
      this.returnType = new TypeParamRefBuilder((io.sundr.model.TypeParamRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof WildcardRef) {
      this.returnType = new WildcardRefBuilder((io.sundr.model.WildcardRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof io.sundr.model.ClassRef) {
      this.returnType = new io.sundr.model.ClassRefBuilder((io.sundr.model.ClassRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof PrimitiveRef) {
      this.returnType = new PrimitiveRefBuilder((io.sundr.model.PrimitiveRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof VoidRef) {
      this.returnType = new VoidRefBuilder((io.sundr.model.VoidRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public java.lang.Boolean hasReturnType() {
    return this.returnType != null;
  }

  public A withTypeParamRefReturnType(io.sundr.model.TypeParamRef typeParamRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (typeParamRefReturnType != null) {
      this.returnType = new io.sundr.model.TypeParamRefBuilder(typeParamRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType() {
    return new MethodFluentImpl.TypeParamRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(
      io.sundr.model.TypeParamRef item) {
    return new io.sundr.model.MethodFluentImpl.TypeParamRefReturnTypeNestedImpl(item);
  }

  public A withWildcardRefReturnType(io.sundr.model.WildcardRef wildcardRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (wildcardRefReturnType != null) {
      this.returnType = new io.sundr.model.WildcardRefBuilder(wildcardRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType() {
    return new MethodFluentImpl.WildcardRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(
      io.sundr.model.WildcardRef item) {
    return new io.sundr.model.MethodFluentImpl.WildcardRefReturnTypeNestedImpl(item);
  }

  public A withClassRefReturnType(io.sundr.model.ClassRef classRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (classRefReturnType != null) {
      this.returnType = new io.sundr.model.ClassRefBuilder(classRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType() {
    return new MethodFluentImpl.ClassRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.MethodFluentImpl.ClassRefReturnTypeNestedImpl(item);
  }

  public A withPrimitiveRefReturnType(io.sundr.model.PrimitiveRef primitiveRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (primitiveRefReturnType != null) {
      this.returnType = new io.sundr.model.PrimitiveRefBuilder(primitiveRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType() {
    return new MethodFluentImpl.PrimitiveRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(
      io.sundr.model.PrimitiveRef item) {
    return new io.sundr.model.MethodFluentImpl.PrimitiveRefReturnTypeNestedImpl(item);
  }

  public A withVoidRefReturnType(io.sundr.model.VoidRef voidRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (voidRefReturnType != null) {
      this.returnType = new io.sundr.model.VoidRefBuilder(voidRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType() {
    return new MethodFluentImpl.VoidRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(io.sundr.model.VoidRef item) {
    return new io.sundr.model.MethodFluentImpl.VoidRefReturnTypeNestedImpl(item);
  }

  public A addToArguments(java.lang.Integer index, io.sundr.model.Property item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToArguments(java.lang.Integer index, io.sundr.model.Property item) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
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

  public A addToArguments(io.sundr.model.Property... items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToArguments(java.util.Collection<io.sundr.model.Property> items) {
    if (this.arguments == null) {
      this.arguments = new java.util.ArrayList<io.sundr.model.PropertyBuilder>();
    }
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromArguments(io.sundr.model.Property... items) {
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromArguments(java.util.Collection<io.sundr.model.Property> items) {
    for (io.sundr.model.Property item : items) {
      io.sundr.model.PropertyBuilder builder = new io.sundr.model.PropertyBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromArguments(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<io.sundr.model.PropertyBuilder> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
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
   * This method has been deprecated, please use method buildArguments instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.Property> getArguments() {
    return arguments != null ? build(arguments) : null;
  }

  public java.util.List<io.sundr.model.Property> buildArguments() {
    return arguments != null ? build(arguments) : null;
  }

  public io.sundr.model.Property buildArgument(java.lang.Integer index) {
    return this.arguments.get(index).build();
  }

  public io.sundr.model.Property buildFirstArgument() {
    return this.arguments.get(0).build();
  }

  public io.sundr.model.Property buildLastArgument() {
    return this.arguments.get(arguments.size() - 1).build();
  }

  public io.sundr.model.Property buildMatchingArgument(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    for (io.sundr.model.PropertyBuilder item : arguments) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingArgument(java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    for (io.sundr.model.PropertyBuilder item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withArguments(java.util.List<io.sundr.model.Property> arguments) {
    if (this.arguments != null) {
      _visitables.get("arguments").removeAll(this.arguments);
    }
    if (arguments != null) {
      this.arguments = new java.util.ArrayList();
      for (io.sundr.model.Property item : arguments) {
        this.addToArguments(item);
      }
    } else {
      this.arguments = null;
    }
    return (A) this;
  }

  public A withArguments(io.sundr.model.Property... arguments) {
    if (this.arguments != null) {
      this.arguments.clear();
    }
    if (arguments != null) {
      for (io.sundr.model.Property item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasArguments() {
    return arguments != null && !arguments.isEmpty();
  }

  public MethodFluent.ArgumentsNested<A> addNewArgument() {
    return new MethodFluentImpl.ArgumentsNestedImpl();
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> addNewArgumentLike(io.sundr.model.Property item) {
    return new io.sundr.model.MethodFluentImpl.ArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> setNewArgumentLike(java.lang.Integer index,
      io.sundr.model.Property item) {
    return new io.sundr.model.MethodFluentImpl.ArgumentsNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editArgument(java.lang.Integer index) {
    if (arguments.size() <= index)
      throw new RuntimeException("Can't edit arguments. Index exceeds size.");
    return setNewArgumentLike(index, buildArgument(index));
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editFirstArgument() {
    if (arguments.size() == 0)
      throw new RuntimeException("Can't edit first arguments. The list is empty.");
    return setNewArgumentLike(0, buildArgument(0));
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editLastArgument() {
    int index = arguments.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last arguments. The list is empty.");
    return setNewArgumentLike(index, buildArgument(index));
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editMatchingArgument(
      java.util.function.Predicate<io.sundr.model.PropertyBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < arguments.size(); i++) {
      if (predicate.test(arguments.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching arguments. No match found.");
    return setNewArgumentLike(index, buildArgument(index));
  }

  public boolean isVarArgPreferred() {
    return this.varArgPreferred;
  }

  public A withVarArgPreferred(boolean varArgPreferred) {
    this.varArgPreferred = varArgPreferred;
    return (A) this;
  }

  public java.lang.Boolean hasVarArgPreferred() {
    return true;
  }

  public A addToExceptions(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.exceptions == null) {
      this.exceptions = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    _visitables.get("exceptions").add(index >= 0 ? index : _visitables.get("exceptions").size(), builder);
    this.exceptions.add(index >= 0 ? index : exceptions.size(), builder);
    return (A) this;
  }

  public A setToExceptions(java.lang.Integer index, io.sundr.model.ClassRef item) {
    if (this.exceptions == null) {
      this.exceptions = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
    if (index < 0 || index >= _visitables.get("exceptions").size()) {
      _visitables.get("exceptions").add(builder);
    } else {
      _visitables.get("exceptions").set(index, builder);
    }
    if (index < 0 || index >= exceptions.size()) {
      exceptions.add(builder);
    } else {
      exceptions.set(index, builder);
    }
    return (A) this;
  }

  public A addToExceptions(io.sundr.model.ClassRef... items) {
    if (this.exceptions == null) {
      this.exceptions = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("exceptions").add(builder);
      this.exceptions.add(builder);
    }
    return (A) this;
  }

  public A addAllToExceptions(java.util.Collection<io.sundr.model.ClassRef> items) {
    if (this.exceptions == null) {
      this.exceptions = new java.util.ArrayList<io.sundr.model.ClassRefBuilder>();
    }
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("exceptions").add(builder);
      this.exceptions.add(builder);
    }
    return (A) this;
  }

  public A removeFromExceptions(io.sundr.model.ClassRef... items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("exceptions").remove(builder);
      if (this.exceptions != null) {
        this.exceptions.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromExceptions(java.util.Collection<io.sundr.model.ClassRef> items) {
    for (io.sundr.model.ClassRef item : items) {
      io.sundr.model.ClassRefBuilder builder = new io.sundr.model.ClassRefBuilder(item);
      _visitables.get("exceptions").remove(builder);
      if (this.exceptions != null) {
        this.exceptions.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromExceptions(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    if (exceptions == null)
      return (A) this;
    final Iterator<io.sundr.model.ClassRefBuilder> each = exceptions.iterator();
    final List visitables = _visitables.get("exceptions");
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
   * This method has been deprecated, please use method buildExceptions instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public java.util.List<io.sundr.model.ClassRef> getExceptions() {
    return exceptions != null ? build(exceptions) : null;
  }

  public java.util.List<io.sundr.model.ClassRef> buildExceptions() {
    return exceptions != null ? build(exceptions) : null;
  }

  public io.sundr.model.ClassRef buildException(java.lang.Integer index) {
    return this.exceptions.get(index).build();
  }

  public io.sundr.model.ClassRef buildFirstException() {
    return this.exceptions.get(0).build();
  }

  public io.sundr.model.ClassRef buildLastException() {
    return this.exceptions.get(exceptions.size() - 1).build();
  }

  public io.sundr.model.ClassRef buildMatchingException(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : exceptions) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public java.lang.Boolean hasMatchingException(java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    for (io.sundr.model.ClassRefBuilder item : exceptions) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withExceptions(java.util.List<io.sundr.model.ClassRef> exceptions) {
    if (this.exceptions != null) {
      _visitables.get("exceptions").removeAll(this.exceptions);
    }
    if (exceptions != null) {
      this.exceptions = new java.util.ArrayList();
      for (io.sundr.model.ClassRef item : exceptions) {
        this.addToExceptions(item);
      }
    } else {
      this.exceptions = null;
    }
    return (A) this;
  }

  public A withExceptions(io.sundr.model.ClassRef... exceptions) {
    if (this.exceptions != null) {
      this.exceptions.clear();
    }
    if (exceptions != null) {
      for (io.sundr.model.ClassRef item : exceptions) {
        this.addToExceptions(item);
      }
    }
    return (A) this;
  }

  public java.lang.Boolean hasExceptions() {
    return exceptions != null && !exceptions.isEmpty();
  }

  public MethodFluent.ExceptionsNested<A> addNewException() {
    return new MethodFluentImpl.ExceptionsNestedImpl();
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> addNewExceptionLike(io.sundr.model.ClassRef item) {
    return new io.sundr.model.MethodFluentImpl.ExceptionsNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> setNewExceptionLike(java.lang.Integer index,
      io.sundr.model.ClassRef item) {
    return new io.sundr.model.MethodFluentImpl.ExceptionsNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editException(java.lang.Integer index) {
    if (exceptions.size() <= index)
      throw new RuntimeException("Can't edit exceptions. Index exceeds size.");
    return setNewExceptionLike(index, buildException(index));
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editFirstException() {
    if (exceptions.size() == 0)
      throw new RuntimeException("Can't edit first exceptions. The list is empty.");
    return setNewExceptionLike(0, buildException(0));
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editLastException() {
    int index = exceptions.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last exceptions. The list is empty.");
    return setNewExceptionLike(index, buildException(index));
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editMatchingException(
      java.util.function.Predicate<io.sundr.model.ClassRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < exceptions.size(); i++) {
      if (predicate.test(exceptions.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0)
      throw new RuntimeException("Can't edit matching exceptions. No match found.");
    return setNewExceptionLike(index, buildException(index));
  }

  public boolean isDefaultMethod() {
    return this.defaultMethod;
  }

  public A withDefaultMethod(boolean defaultMethod) {
    this.defaultMethod = defaultMethod;
    return (A) this;
  }

  public java.lang.Boolean hasDefaultMethod() {
    return true;
  }

  /**
   * This method has been deprecated, please use method buildBlock instead.
   * 
   * @return The buildable object.
   */
  @java.lang.Deprecated
  public io.sundr.model.Block getBlock() {
    return this.block != null ? this.block.build() : null;
  }

  public io.sundr.model.Block buildBlock() {
    return this.block != null ? this.block.build() : null;
  }

  public A withBlock(io.sundr.model.Block block) {
    _visitables.get("block").remove(this.block);
    if (block != null) {
      this.block = new BlockBuilder(block);
      _visitables.get("block").add(this.block);
    }
    return (A) this;
  }

  public java.lang.Boolean hasBlock() {
    return this.block != null;
  }

  public MethodFluent.BlockNested<A> withNewBlock() {
    return new MethodFluentImpl.BlockNestedImpl();
  }

  public io.sundr.model.MethodFluent.BlockNested<A> withNewBlockLike(io.sundr.model.Block item) {
    return new io.sundr.model.MethodFluentImpl.BlockNestedImpl(item);
  }

  public io.sundr.model.MethodFluent.BlockNested<A> editBlock() {
    return withNewBlockLike(getBlock());
  }

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlock() {
    return withNewBlockLike(getBlock() != null ? getBlock() : new io.sundr.model.BlockBuilder().build());
  }

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlockLike(io.sundr.model.Block item) {
    return withNewBlockLike(getBlock() != null ? getBlock() : item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    MethodFluentImpl that = (MethodFluentImpl) o;
    if (comments != null ? !comments.equals(that.comments) : that.comments != null)
      return false;
    if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null)
      return false;
    if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null)
      return false;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null)
      return false;
    if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null)
      return false;
    if (varArgPreferred != that.varArgPreferred)
      return false;
    if (exceptions != null ? !exceptions.equals(that.exceptions) : that.exceptions != null)
      return false;
    if (defaultMethod != that.defaultMethod)
      return false;
    if (block != null ? !block.equals(that.block) : that.block != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(comments, annotations, parameters, name, returnType, arguments, varArgPreferred, exceptions,
        defaultMethod, block, super.hashCode());
  }

  public java.lang.String toString() {
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
    if (parameters != null && !parameters.isEmpty()) {
      sb.append("parameters:");
      sb.append(parameters + ",");
    }
    if (name != null) {
      sb.append("name:");
      sb.append(name + ",");
    }
    if (returnType != null) {
      sb.append("returnType:");
      sb.append(returnType + ",");
    }
    if (arguments != null && !arguments.isEmpty()) {
      sb.append("arguments:");
      sb.append(arguments + ",");
    }
    sb.append("varArgPreferred:");
    sb.append(varArgPreferred + ",");
    if (exceptions != null && !exceptions.isEmpty()) {
      sb.append("exceptions:");
      sb.append(exceptions + ",");
    }
    sb.append("defaultMethod:");
    sb.append(defaultMethod + ",");
    if (block != null) {
      sb.append("block:");
      sb.append(block);
    }
    sb.append("}");
    return sb.toString();
  }

  class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<MethodFluent.AnnotationsNested<N>>
      implements io.sundr.model.MethodFluent.AnnotationsNested<N>, Nested<N> {
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
      return (N) MethodFluentImpl.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<MethodFluent.ParametersNested<N>>
      implements io.sundr.model.MethodFluent.ParametersNested<N>, io.sundr.builder.Nested<N> {
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
      return (N) MethodFluentImpl.this.setToParameters(index, builder.build());
    }

    public N endParameter() {
      return and();
    }

  }

  class TypeParamRefReturnTypeNestedImpl<N> extends TypeParamRefFluentImpl<MethodFluent.TypeParamRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<N>, io.sundr.builder.Nested<N> {
    TypeParamRefReturnTypeNestedImpl(io.sundr.model.TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefReturnTypeNestedImpl() {
      this.builder = new io.sundr.model.TypeParamRefBuilder(this);
    }

    io.sundr.model.TypeParamRefBuilder builder;

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endTypeParamRefReturnType() {
      return and();
    }

  }

  class WildcardRefReturnTypeNestedImpl<N> extends WildcardRefFluentImpl<MethodFluent.WildcardRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<N>, io.sundr.builder.Nested<N> {
    WildcardRefReturnTypeNestedImpl(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefReturnTypeNestedImpl() {
      this.builder = new io.sundr.model.WildcardRefBuilder(this);
    }

    io.sundr.model.WildcardRefBuilder builder;

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endWildcardRefReturnType() {
      return and();
    }

  }

  class ClassRefReturnTypeNestedImpl<N> extends ClassRefFluentImpl<MethodFluent.ClassRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.ClassRefReturnTypeNested<N>, io.sundr.builder.Nested<N> {
    ClassRefReturnTypeNestedImpl(io.sundr.model.ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefReturnTypeNestedImpl() {
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endClassRefReturnType() {
      return and();
    }

  }

  class PrimitiveRefReturnTypeNestedImpl<N> extends PrimitiveRefFluentImpl<MethodFluent.PrimitiveRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<N>, io.sundr.builder.Nested<N> {
    PrimitiveRefReturnTypeNestedImpl(io.sundr.model.PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefReturnTypeNestedImpl() {
      this.builder = new io.sundr.model.PrimitiveRefBuilder(this);
    }

    io.sundr.model.PrimitiveRefBuilder builder;

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endPrimitiveRefReturnType() {
      return and();
    }

  }

  class VoidRefReturnTypeNestedImpl<N> extends VoidRefFluentImpl<MethodFluent.VoidRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.VoidRefReturnTypeNested<N>, io.sundr.builder.Nested<N> {
    VoidRefReturnTypeNestedImpl(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefReturnTypeNestedImpl() {
      this.builder = new io.sundr.model.VoidRefBuilder(this);
    }

    io.sundr.model.VoidRefBuilder builder;

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endVoidRefReturnType() {
      return and();
    }

  }

  class ArgumentsNestedImpl<N> extends PropertyFluentImpl<MethodFluent.ArgumentsNested<N>>
      implements io.sundr.model.MethodFluent.ArgumentsNested<N>, io.sundr.builder.Nested<N> {
    ArgumentsNestedImpl(java.lang.Integer index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    ArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.PropertyBuilder(this);
    }

    io.sundr.model.PropertyBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) MethodFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endArgument() {
      return and();
    }

  }

  class ExceptionsNestedImpl<N> extends ClassRefFluentImpl<MethodFluent.ExceptionsNested<N>>
      implements io.sundr.model.MethodFluent.ExceptionsNested<N>, io.sundr.builder.Nested<N> {
    ExceptionsNestedImpl(java.lang.Integer index, io.sundr.model.ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ExceptionsNestedImpl() {
      this.index = -1;
      this.builder = new io.sundr.model.ClassRefBuilder(this);
    }

    io.sundr.model.ClassRefBuilder builder;
    java.lang.Integer index;

    public N and() {
      return (N) MethodFluentImpl.this.setToExceptions(index, builder.build());
    }

    public N endException() {
      return and();
    }

  }

  class BlockNestedImpl<N> extends BlockFluentImpl<MethodFluent.BlockNested<N>>
      implements io.sundr.model.MethodFluent.BlockNested<N>, io.sundr.builder.Nested<N> {
    BlockNestedImpl(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    BlockNestedImpl() {
      this.builder = new io.sundr.model.BlockBuilder(this);
    }

    io.sundr.model.BlockBuilder builder;

    public N and() {
      return (N) MethodFluentImpl.this.withBlock(builder.build());
    }

    public N endBlock() {
      return and();
    }

  }

}
