package io.sundr.model;

import java.lang.Boolean;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuffer;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

public class MethodFluentImpl<A extends MethodFluent<A>> extends ModifierSupportFluentImpl<A> implements MethodFluent<A> {

  private List<String> comments = new ArrayList<String>();
  private List<AnnotationRefBuilder> annotations = new ArrayList<AnnotationRefBuilder>();
  private List<TypeParamDefBuilder> parameters = new ArrayList<TypeParamDefBuilder>();
  private String name;
  private VisitableBuilder<? extends TypeRef, ?> returnType;
  private List<PropertyBuilder> arguments = new ArrayList<PropertyBuilder>();
  private boolean varArgPreferred;
  private List<ClassRefBuilder> exceptions = new ArrayList<ClassRefBuilder>();
  private boolean defaultMethod;
  private BlockBuilder block;

  public MethodFluentImpl() {
  }

  public MethodFluentImpl(Method instance) {
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

  public A addToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.add(index, item);
    return (A) this;
  }

  public A setToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    this.comments.set(index, item);
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

  public A addAllToComments(Collection<String> items) {
    if (this.comments == null) {
      this.comments = new ArrayList<String>();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A removeFromComments(String... items) {
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

  public String getComment(int index) {
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
    if (this.comments != null) {
      _visitables.get("comments").removeAll(this.comments);
    }
    if (comments != null) {
      this.comments = new ArrayList<String>();
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

  public A addNewComment(StringBuilder arg1) {
    return (A) addToComments(new String(arg1));
  }

  public A addNewComment(int[] arg1, int arg2, int arg3) {
    return (A) addToComments(new String(arg1, arg2, arg3));
  }

  public A addNewComment(char[] arg1) {
    return (A) addToComments(new String(arg1));
  }

  public A addNewComment(StringBuffer arg1) {
    return (A) addToComments(new String(arg1));
  }

  public A addNewComment(byte[] arg1, int arg2) {
    return (A) addToComments(new String(arg1, arg2));
  }

  public A addNewComment(byte[] arg1) {
    return (A) addToComments(new String(arg1));
  }

  public A addNewComment(char[] arg1, int arg2, int arg3) {
    return (A) addToComments(new String(arg1, arg2, arg3));
  }

  public A addNewComment(byte[] arg1, int arg2, int arg3) {
    return (A) addToComments(new String(arg1, arg2, arg3));
  }

  public A addNewComment(byte[] arg1, int arg2, int arg3, int arg4) {
    return (A) addToComments(new String(arg1, arg2, arg3, arg4));
  }

  public A addNewComment(String arg1) {
    return (A) addToComments(new String(arg1));
  }

  public A addToAnnotations(int index, AnnotationRef item) {
    if (this.annotations == null) {
      this.annotations = new ArrayList<AnnotationRefBuilder>();
    }
    AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
    _visitables.get("annotations").add(index >= 0 ? index : _visitables.get("annotations").size(), builder);
    this.annotations.add(index >= 0 ? index : annotations.size(), builder);
    return (A) this;
  }

  public A setToAnnotations(int index, AnnotationRef item) {
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

  public A removeFromAnnotations(AnnotationRef... items) {
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
    return build(annotations);
  }

  public List<AnnotationRef> buildAnnotations() {
    return build(annotations);
  }

  public AnnotationRef buildAnnotation(int index) {
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
      this.annotations = new ArrayList<AnnotationRefBuilder>();
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

  public io.sundr.model.MethodFluent.AnnotationsNested<A> addNewAnnotation() {
    return new AnnotationsNestedImpl();
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item) {
    return new AnnotationsNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item) {
    return new AnnotationsNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editAnnotation(int index) {
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

  public io.sundr.model.MethodFluent.AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
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

  public A addToParameters(int index, TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
    _visitables.get("parameters").add(index >= 0 ? index : _visitables.get("parameters").size(), builder);
    this.parameters.add(index >= 0 ? index : parameters.size(), builder);
    return (A) this;
  }

  public A setToParameters(int index, TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
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

  public A addToParameters(TypeParamDef... items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A addAllToParameters(Collection<TypeParamDef> items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").add(builder);
      this.parameters.add(builder);
    }
    return (A) this;
  }

  public A removeFromParameters(TypeParamDef... items) {
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      if (this.parameters != null) {
        this.parameters.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromParameters(Collection<TypeParamDef> items) {
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      if (this.parameters != null) {
        this.parameters.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromParameters(Predicate<TypeParamDefBuilder> predicate) {
    if (parameters == null)
      return (A) this;
    final Iterator<TypeParamDefBuilder> each = parameters.iterator();
    final List visitables = _visitables.get("parameters");
    while (each.hasNext()) {
      TypeParamDefBuilder builder = each.next();
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
  @Deprecated
  public List<TypeParamDef> getParameters() {
    return build(parameters);
  }

  public List<TypeParamDef> buildParameters() {
    return build(parameters);
  }

  public TypeParamDef buildParameter(int index) {
    return this.parameters.get(index).build();
  }

  public TypeParamDef buildFirstParameter() {
    return this.parameters.get(0).build();
  }

  public TypeParamDef buildLastParameter() {
    return this.parameters.get(parameters.size() - 1).build();
  }

  public TypeParamDef buildMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
    for (TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
    for (TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withParameters(List<TypeParamDef> parameters) {
    if (this.parameters != null) {
      _visitables.get("parameters").removeAll(this.parameters);
    }
    if (parameters != null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
      for (TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    } else {
      this.parameters = null;
    }
    return (A) this;
  }

  public A withParameters(TypeParamDef... parameters) {
    if (this.parameters != null) {
      this.parameters.clear();
    }
    if (parameters != null) {
      for (TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    }
    return (A) this;
  }

  public Boolean hasParameters() {
    return parameters != null && !parameters.isEmpty();
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> addNewParameter() {
    return new ParametersNestedImpl();
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> addNewParameterLike(TypeParamDef item) {
    return new ParametersNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> setNewParameterLike(int index, TypeParamDef item) {
    return new ParametersNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.ParametersNested<A> editParameter(int index) {
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

  public io.sundr.model.MethodFluent.ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
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

  public A withNewName(StringBuilder arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(int[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(char[] arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(StringBuffer arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(byte[] arg1, int arg2) {
    return (A) withName(new String(arg1, arg2));
  }

  public A withNewName(byte[] arg1) {
    return (A) withName(new String(arg1));
  }

  public A withNewName(char[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(byte[] arg1, int arg2, int arg3) {
    return (A) withName(new String(arg1, arg2, arg3));
  }

  public A withNewName(byte[] arg1, int arg2, int arg3, int arg4) {
    return (A) withName(new String(arg1, arg2, arg3, arg4));
  }

  public A withNewName(String arg1) {
    return (A) withName(new String(arg1));
  }

  /**
   * This method has been deprecated, please use method buildReturnType instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public TypeRef getReturnType() {
    return this.returnType != null ? this.returnType.build() : null;
  }

  public TypeRef buildReturnType() {
    return this.returnType != null ? this.returnType.build() : null;
  }

  public A withReturnType(TypeRef returnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (returnType instanceof TypeParamRef) {
      this.returnType = new TypeParamRefBuilder((TypeParamRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof WildcardRef) {
      this.returnType = new WildcardRefBuilder((WildcardRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof ClassRef) {
      this.returnType = new ClassRefBuilder((ClassRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof PrimitiveRef) {
      this.returnType = new PrimitiveRefBuilder((PrimitiveRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    if (returnType instanceof VoidRef) {
      this.returnType = new VoidRefBuilder((VoidRef) returnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public Boolean hasReturnType() {
    return this.returnType != null;
  }

  public A withTypeParamRefReturnType(TypeParamRef typeParamRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (typeParamRefReturnType != null) {
      this.returnType = new TypeParamRefBuilder(typeParamRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType() {
    return new TypeParamRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item) {
    return new TypeParamRefReturnTypeNestedImpl(item);
  }

  public A withWildcardRefReturnType(WildcardRef wildcardRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (wildcardRefReturnType != null) {
      this.returnType = new WildcardRefBuilder(wildcardRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType() {
    return new WildcardRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item) {
    return new WildcardRefReturnTypeNestedImpl(item);
  }

  public A withClassRefReturnType(ClassRef classRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (classRefReturnType != null) {
      this.returnType = new ClassRefBuilder(classRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public io.sundr.model.MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnType() {
    return new ClassRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item) {
    return new ClassRefReturnTypeNestedImpl(item);
  }

  public A withPrimitiveRefReturnType(PrimitiveRef primitiveRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (primitiveRefReturnType != null) {
      this.returnType = new PrimitiveRefBuilder(primitiveRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType() {
    return new PrimitiveRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item) {
    return new PrimitiveRefReturnTypeNestedImpl(item);
  }

  public A withVoidRefReturnType(VoidRef voidRefReturnType) {
    _visitables.get("returnType").remove(this.returnType);
    if (voidRefReturnType != null) {
      this.returnType = new VoidRefBuilder(voidRefReturnType);
      _visitables.get("returnType").add(this.returnType);
    }
    return (A) this;
  }

  public io.sundr.model.MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnType() {
    return new VoidRefReturnTypeNestedImpl();
  }

  public io.sundr.model.MethodFluent.VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item) {
    return new VoidRefReturnTypeNestedImpl(item);
  }

  public A addToArguments(int index, Property item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    _visitables.get("arguments").add(index >= 0 ? index : _visitables.get("arguments").size(), builder);
    this.arguments.add(index >= 0 ? index : arguments.size(), builder);
    return (A) this;
  }

  public A setToArguments(int index, Property item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
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

  public A addToArguments(Property... items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<PropertyBuilder>();
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A addAllToArguments(Collection<Property> items) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<PropertyBuilder>();
    }
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("arguments").add(builder);
      this.arguments.add(builder);
    }
    return (A) this;
  }

  public A removeFromArguments(Property... items) {
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromArguments(Collection<Property> items) {
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("arguments").remove(builder);
      if (this.arguments != null) {
        this.arguments.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromArguments(Predicate<PropertyBuilder> predicate) {
    if (arguments == null)
      return (A) this;
    final Iterator<PropertyBuilder> each = arguments.iterator();
    final List visitables = _visitables.get("arguments");
    while (each.hasNext()) {
      PropertyBuilder builder = each.next();
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
  @Deprecated
  public List<Property> getArguments() {
    return build(arguments);
  }

  public List<Property> buildArguments() {
    return build(arguments);
  }

  public Property buildArgument(int index) {
    return this.arguments.get(index).build();
  }

  public Property buildFirstArgument() {
    return this.arguments.get(0).build();
  }

  public Property buildLastArgument() {
    return this.arguments.get(arguments.size() - 1).build();
  }

  public Property buildMatchingArgument(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : arguments) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingArgument(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withArguments(List<Property> arguments) {
    if (this.arguments != null) {
      _visitables.get("arguments").removeAll(this.arguments);
    }
    if (arguments != null) {
      this.arguments = new ArrayList<PropertyBuilder>();
      for (Property item : arguments) {
        this.addToArguments(item);
      }
    } else {
      this.arguments = null;
    }
    return (A) this;
  }

  public A withArguments(Property... arguments) {
    if (this.arguments != null) {
      this.arguments.clear();
    }
    if (arguments != null) {
      for (Property item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public Boolean hasArguments() {
    return arguments != null && !arguments.isEmpty();
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> addNewArgument() {
    return new ArgumentsNestedImpl();
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> addNewArgumentLike(Property item) {
    return new ArgumentsNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> setNewArgumentLike(int index, Property item) {
    return new ArgumentsNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editArgument(int index) {
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

  public io.sundr.model.MethodFluent.ArgumentsNested<A> editMatchingArgument(Predicate<PropertyBuilder> predicate) {
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

  public Boolean hasVarArgPreferred() {
    return true;
  }

  public A addToExceptions(int index, ClassRef item) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    _visitables.get("exceptions").add(index >= 0 ? index : _visitables.get("exceptions").size(), builder);
    this.exceptions.add(index >= 0 ? index : exceptions.size(), builder);
    return (A) this;
  }

  public A setToExceptions(int index, ClassRef item) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
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

  public A addToExceptions(ClassRef... items) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("exceptions").add(builder);
      this.exceptions.add(builder);
    }
    return (A) this;
  }

  public A addAllToExceptions(Collection<ClassRef> items) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
    }
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("exceptions").add(builder);
      this.exceptions.add(builder);
    }
    return (A) this;
  }

  public A removeFromExceptions(ClassRef... items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("exceptions").remove(builder);
      if (this.exceptions != null) {
        this.exceptions.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeAllFromExceptions(Collection<ClassRef> items) {
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("exceptions").remove(builder);
      if (this.exceptions != null) {
        this.exceptions.remove(builder);
      }
    }
    return (A) this;
  }

  public A removeMatchingFromExceptions(Predicate<ClassRefBuilder> predicate) {
    if (exceptions == null)
      return (A) this;
    final Iterator<ClassRefBuilder> each = exceptions.iterator();
    final List visitables = _visitables.get("exceptions");
    while (each.hasNext()) {
      ClassRefBuilder builder = each.next();
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
  @Deprecated
  public List<ClassRef> getExceptions() {
    return build(exceptions);
  }

  public List<ClassRef> buildExceptions() {
    return build(exceptions);
  }

  public ClassRef buildException(int index) {
    return this.exceptions.get(index).build();
  }

  public ClassRef buildFirstException() {
    return this.exceptions.get(0).build();
  }

  public ClassRef buildLastException() {
    return this.exceptions.get(exceptions.size() - 1).build();
  }

  public ClassRef buildMatchingException(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : exceptions) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Boolean hasMatchingException(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : exceptions) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withExceptions(List<ClassRef> exceptions) {
    if (this.exceptions != null) {
      _visitables.get("exceptions").removeAll(this.exceptions);
    }
    if (exceptions != null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
      for (ClassRef item : exceptions) {
        this.addToExceptions(item);
      }
    } else {
      this.exceptions = null;
    }
    return (A) this;
  }

  public A withExceptions(ClassRef... exceptions) {
    if (this.exceptions != null) {
      this.exceptions.clear();
    }
    if (exceptions != null) {
      for (ClassRef item : exceptions) {
        this.addToExceptions(item);
      }
    }
    return (A) this;
  }

  public Boolean hasExceptions() {
    return exceptions != null && !exceptions.isEmpty();
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> addNewException() {
    return new ExceptionsNestedImpl();
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> addNewExceptionLike(ClassRef item) {
    return new ExceptionsNestedImpl(-1, item);
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> setNewExceptionLike(int index, ClassRef item) {
    return new ExceptionsNestedImpl(index, item);
  }

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editException(int index) {
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

  public io.sundr.model.MethodFluent.ExceptionsNested<A> editMatchingException(Predicate<ClassRefBuilder> predicate) {
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

  public Boolean hasDefaultMethod() {
    return true;
  }

  /**
   * This method has been deprecated, please use method buildBlock instead.
   * 
   * @return The buildable object.
   */
  @Deprecated
  public Block getBlock() {
    return this.block != null ? this.block.build() : null;
  }

  public Block buildBlock() {
    return this.block != null ? this.block.build() : null;
  }

  public A withBlock(Block block) {
    _visitables.get("block").remove(this.block);
    if (block != null) {
      this.block = new BlockBuilder(block);
      _visitables.get("block").add(this.block);
    }
    return (A) this;
  }

  public Boolean hasBlock() {
    return this.block != null;
  }

  public io.sundr.model.MethodFluent.BlockNested<A> withNewBlock() {
    return new BlockNestedImpl();
  }

  public io.sundr.model.MethodFluent.BlockNested<A> withNewBlockLike(Block item) {
    return new BlockNestedImpl(item);
  }

  public io.sundr.model.MethodFluent.BlockNested<A> editBlock() {
    return withNewBlockLike(getBlock());
  }

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlock() {
    return withNewBlockLike(getBlock() != null ? getBlock() : new BlockBuilder().build());
  }

  public io.sundr.model.MethodFluent.BlockNested<A> editOrNewBlockLike(Block item) {
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

  public class AnnotationsNestedImpl<N> extends AnnotationRefFluentImpl<io.sundr.model.MethodFluent.AnnotationsNested<N>>
      implements io.sundr.model.MethodFluent.AnnotationsNested<N>, Nested<N> {
    private final AnnotationRefBuilder builder;
    private final int index;

    AnnotationsNestedImpl(int index, AnnotationRef item) {
      this.index = index;
      this.builder = new AnnotationRefBuilder(this, item);

    }

    AnnotationsNestedImpl() {
      this.index = -1;
      this.builder = new AnnotationRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }
  }

  public class ParametersNestedImpl<N> extends TypeParamDefFluentImpl<io.sundr.model.MethodFluent.ParametersNested<N>>
      implements io.sundr.model.MethodFluent.ParametersNested<N>, Nested<N> {
    private final TypeParamDefBuilder builder;
    private final int index;

    ParametersNestedImpl(int index, TypeParamDef item) {
      this.index = index;
      this.builder = new TypeParamDefBuilder(this, item);

    }

    ParametersNestedImpl() {
      this.index = -1;
      this.builder = new TypeParamDefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.setToParameters(index, builder.build());
    }

    public N endParameter() {
      return and();
    }
  }

  public class TypeParamRefReturnTypeNestedImpl<N>
      extends TypeParamRefFluentImpl<io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.TypeParamRefReturnTypeNested<N>, Nested<N> {
    private final TypeParamRefBuilder builder;

    TypeParamRefReturnTypeNestedImpl(TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);

    }

    TypeParamRefReturnTypeNestedImpl() {
      this.builder = new TypeParamRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endTypeParamRefReturnType() {
      return and();
    }
  }

  public class WildcardRefReturnTypeNestedImpl<N>
      extends WildcardRefFluentImpl<io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.WildcardRefReturnTypeNested<N>, Nested<N> {
    private final WildcardRefBuilder builder;

    WildcardRefReturnTypeNestedImpl(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);

    }

    WildcardRefReturnTypeNestedImpl() {
      this.builder = new WildcardRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endWildcardRefReturnType() {
      return and();
    }
  }

  public class ClassRefReturnTypeNestedImpl<N>
      extends ClassRefFluentImpl<io.sundr.model.MethodFluent.ClassRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.ClassRefReturnTypeNested<N>, Nested<N> {
    private final ClassRefBuilder builder;

    ClassRefReturnTypeNestedImpl(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);

    }

    ClassRefReturnTypeNestedImpl() {
      this.builder = new ClassRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endClassRefReturnType() {
      return and();
    }
  }

  public class PrimitiveRefReturnTypeNestedImpl<N>
      extends PrimitiveRefFluentImpl<io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.PrimitiveRefReturnTypeNested<N>, Nested<N> {
    private final PrimitiveRefBuilder builder;

    PrimitiveRefReturnTypeNestedImpl(PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);

    }

    PrimitiveRefReturnTypeNestedImpl() {
      this.builder = new PrimitiveRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endPrimitiveRefReturnType() {
      return and();
    }
  }

  public class VoidRefReturnTypeNestedImpl<N> extends VoidRefFluentImpl<io.sundr.model.MethodFluent.VoidRefReturnTypeNested<N>>
      implements io.sundr.model.MethodFluent.VoidRefReturnTypeNested<N>, Nested<N> {
    private final VoidRefBuilder builder;

    VoidRefReturnTypeNestedImpl(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);

    }

    VoidRefReturnTypeNestedImpl() {
      this.builder = new VoidRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.withReturnType(builder.build());
    }

    public N endVoidRefReturnType() {
      return and();
    }
  }

  public class ArgumentsNestedImpl<N> extends PropertyFluentImpl<io.sundr.model.MethodFluent.ArgumentsNested<N>>
      implements io.sundr.model.MethodFluent.ArgumentsNested<N>, Nested<N> {
    private final PropertyBuilder builder;
    private final int index;

    ArgumentsNestedImpl(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);

    }

    ArgumentsNestedImpl() {
      this.index = -1;
      this.builder = new PropertyBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.setToArguments(index, builder.build());
    }

    public N endArgument() {
      return and();
    }
  }

  public class ExceptionsNestedImpl<N> extends ClassRefFluentImpl<io.sundr.model.MethodFluent.ExceptionsNested<N>>
      implements io.sundr.model.MethodFluent.ExceptionsNested<N>, Nested<N> {
    private final ClassRefBuilder builder;
    private final int index;

    ExceptionsNestedImpl(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);

    }

    ExceptionsNestedImpl() {
      this.index = -1;
      this.builder = new ClassRefBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.setToExceptions(index, builder.build());
    }

    public N endException() {
      return and();
    }
  }

  public class BlockNestedImpl<N> extends BlockFluentImpl<io.sundr.model.MethodFluent.BlockNested<N>>
      implements io.sundr.model.MethodFluent.BlockNested<N>, Nested<N> {
    private final BlockBuilder builder;

    BlockNestedImpl(Block item) {
      this.builder = new BlockBuilder(this, item);

    }

    BlockNestedImpl() {
      this.builder = new BlockBuilder(this);

    }

    public N and() {
      return (N) MethodFluentImpl.this.withBlock(builder.build());
    }

    public N endBlock() {
      return and();
    }
  }

}
