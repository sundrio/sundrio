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
public class MethodFluent<A extends MethodFluent<A>> extends ModifierSupportFluent<A> {
  public MethodFluent() {
  }

  public MethodFluent(Method instance) {
    if (instance != null) {
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
  }

  private List<String> comments = new ArrayList<String>();
  private ArrayList<AnnotationRefBuilder> annotations = new ArrayList<AnnotationRefBuilder>();
  private ArrayList<TypeParamDefBuilder> parameters = new ArrayList<TypeParamDefBuilder>();
  private String name;
  private VisitableBuilder<? extends TypeRef, ?> returnType;
  private ArrayList<PropertyBuilder> arguments = new ArrayList<PropertyBuilder>();
  private boolean varArgPreferred;
  private ArrayList<ClassRefBuilder> exceptions = new ArrayList<ClassRefBuilder>();
  private boolean defaultMethod;
  private BlockBuilder block;

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
    if (this.comments == null)
      return (A) this;
    for (String item : items) {
      this.comments.remove(item);
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

  public boolean hasMatchingComment(Predicate<String> predicate) {
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
      _visitables.remove("comments");
    }
    if (comments != null) {
      for (String item : comments) {
        this.addToComments(item);
      }
    }
    return (A) this;
  }

  public boolean hasComments() {
    return comments != null && !comments.isEmpty();
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
      _visitables.get("annotations").add(index, builder);
      annotations.add(index, builder);
    }
    return (A) this;
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
      _visitables.get("annotations").set(index, builder);
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
    if (this.annotations == null)
      return (A) this;
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
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

  public List<AnnotationRef> buildAnnotations() {
    return annotations != null ? build(annotations) : null;
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

  public boolean hasMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    for (AnnotationRefBuilder item : annotations) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withAnnotations(List<AnnotationRef> annotations) {
    if (this.annotations != null) {
      _visitables.get("annotations").clear();
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
      _visitables.remove("annotations");
    }
    if (annotations != null) {
      for (AnnotationRef item : annotations) {
        this.addToAnnotations(item);
      }
    }
    return (A) this;
  }

  public boolean hasAnnotations() {
    return annotations != null && !annotations.isEmpty();
  }

  public AnnotationsNested<A> addNewAnnotation() {
    return new AnnotationsNested(-1, null);
  }

  public AnnotationsNested<A> addNewAnnotationLike(AnnotationRef item) {
    return new AnnotationsNested(-1, item);
  }

  public AnnotationsNested<A> setNewAnnotationLike(int index, AnnotationRef item) {
    return new AnnotationsNested(index, item);
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

  public A addToParameters(int index, TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").add(index, builder);
      parameters.add(index, builder);
    }
    return (A) this;
  }

  public A setToParameters(int index, TypeParamDef item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<TypeParamDefBuilder>();
    }
    TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
    if (index < 0 || index >= parameters.size()) {
      _visitables.get("parameters").add(builder);
      parameters.add(builder);
    } else {
      _visitables.get("parameters").set(index, builder);
      parameters.set(index, builder);
    }
    return (A) this;
  }

  public A addToParameters(io.sundr.model.TypeParamDef... items) {
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

  public A removeFromParameters(io.sundr.model.TypeParamDef... items) {
    if (this.parameters == null)
      return (A) this;
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromParameters(Collection<TypeParamDef> items) {
    if (this.parameters == null)
      return (A) this;
    for (TypeParamDef item : items) {
      TypeParamDefBuilder builder = new TypeParamDefBuilder(item);
      _visitables.get("parameters").remove(builder);
      this.parameters.remove(builder);
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

  public List<TypeParamDef> buildParameters() {
    return parameters != null ? build(parameters) : null;
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

  public boolean hasMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
    for (TypeParamDefBuilder item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withParameters(List<TypeParamDef> parameters) {
    if (this.parameters != null) {
      _visitables.get("parameters").clear();
    }
    if (parameters != null) {
      this.parameters = new ArrayList();
      for (TypeParamDef item : parameters) {
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
      _visitables.remove("parameters");
    }
    if (parameters != null) {
      for (TypeParamDef item : parameters) {
        this.addToParameters(item);
      }
    }
    return (A) this;
  }

  public boolean hasParameters() {
    return parameters != null && !parameters.isEmpty();
  }

  public ParametersNested<A> addNewParameter() {
    return new ParametersNested(-1, null);
  }

  public ParametersNested<A> addNewParameterLike(TypeParamDef item) {
    return new ParametersNested(-1, item);
  }

  public ParametersNested<A> setNewParameterLike(int index, TypeParamDef item) {
    return new ParametersNested(index, item);
  }

  public ParametersNested<A> editParameter(int index) {
    if (parameters.size() <= index)
      throw new RuntimeException("Can't edit parameters. Index exceeds size.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public ParametersNested<A> editFirstParameter() {
    if (parameters.size() == 0)
      throw new RuntimeException("Can't edit first parameters. The list is empty.");
    return setNewParameterLike(0, buildParameter(0));
  }

  public ParametersNested<A> editLastParameter() {
    int index = parameters.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last parameters. The list is empty.");
    return setNewParameterLike(index, buildParameter(index));
  }

  public ParametersNested<A> editMatchingParameter(Predicate<TypeParamDefBuilder> predicate) {
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

  public boolean hasName() {
    return this.name != null;
  }

  public TypeRef buildReturnType() {
    return this.returnType != null ? this.returnType.build() : null;
  }

  public A withReturnType(TypeRef returnType) {
    if (returnType == null) {
      this.returnType = null;
      _visitables.remove("returnType");
      return (A) this;
    }
    VisitableBuilder<? extends TypeRef, ?> builder = builder(returnType);
    _visitables.get("returnType").clear();
    _visitables.get("returnType").add(builder);
    this.returnType = builder;
    return (A) this;
  }

  public boolean hasReturnType() {
    return this.returnType != null;
  }

  public ClassRefReturnTypeNested<A> withNewClassRefReturnType() {
    return new ClassRefReturnTypeNested(null);
  }

  public ClassRefReturnTypeNested<A> withNewClassRefReturnTypeLike(ClassRef item) {
    return new ClassRefReturnTypeNested(item);
  }

  public PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnType() {
    return new PrimitiveRefReturnTypeNested(null);
  }

  public PrimitiveRefReturnTypeNested<A> withNewPrimitiveRefReturnTypeLike(PrimitiveRef item) {
    return new PrimitiveRefReturnTypeNested(item);
  }

  public VoidRefReturnTypeNested<A> withNewVoidRefReturnType() {
    return new VoidRefReturnTypeNested(null);
  }

  public VoidRefReturnTypeNested<A> withNewVoidRefReturnTypeLike(VoidRef item) {
    return new VoidRefReturnTypeNested(item);
  }

  public TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnType() {
    return new TypeParamRefReturnTypeNested(null);
  }

  public TypeParamRefReturnTypeNested<A> withNewTypeParamRefReturnTypeLike(TypeParamRef item) {
    return new TypeParamRefReturnTypeNested(item);
  }

  public WildcardRefReturnTypeNested<A> withNewWildcardRefReturnType() {
    return new WildcardRefReturnTypeNested(null);
  }

  public WildcardRefReturnTypeNested<A> withNewWildcardRefReturnTypeLike(WildcardRef item) {
    return new WildcardRefReturnTypeNested(item);
  }

  public A addToArguments(int index, Property item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").add(index, builder);
      arguments.add(index, builder);
    }
    return (A) this;
  }

  public A setToArguments(int index, Property item) {
    if (this.arguments == null) {
      this.arguments = new ArrayList<PropertyBuilder>();
    }
    PropertyBuilder builder = new PropertyBuilder(item);
    if (index < 0 || index >= arguments.size()) {
      _visitables.get("arguments").add(builder);
      arguments.add(builder);
    } else {
      _visitables.get("arguments").set(index, builder);
      arguments.set(index, builder);
    }
    return (A) this;
  }

  public A addToArguments(io.sundr.model.Property... items) {
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

  public A removeFromArguments(io.sundr.model.Property... items) {
    if (this.arguments == null)
      return (A) this;
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromArguments(Collection<Property> items) {
    if (this.arguments == null)
      return (A) this;
    for (Property item : items) {
      PropertyBuilder builder = new PropertyBuilder(item);
      _visitables.get("arguments").remove(builder);
      this.arguments.remove(builder);
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

  public List<Property> buildArguments() {
    return arguments != null ? build(arguments) : null;
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

  public boolean hasMatchingArgument(Predicate<PropertyBuilder> predicate) {
    for (PropertyBuilder item : arguments) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withArguments(List<Property> arguments) {
    if (this.arguments != null) {
      _visitables.get("arguments").clear();
    }
    if (arguments != null) {
      this.arguments = new ArrayList();
      for (Property item : arguments) {
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
      _visitables.remove("arguments");
    }
    if (arguments != null) {
      for (Property item : arguments) {
        this.addToArguments(item);
      }
    }
    return (A) this;
  }

  public boolean hasArguments() {
    return arguments != null && !arguments.isEmpty();
  }

  public ArgumentsNested<A> addNewArgument() {
    return new ArgumentsNested(-1, null);
  }

  public ArgumentsNested<A> addNewArgumentLike(Property item) {
    return new ArgumentsNested(-1, item);
  }

  public ArgumentsNested<A> setNewArgumentLike(int index, Property item) {
    return new ArgumentsNested(index, item);
  }

  public ArgumentsNested<A> editArgument(int index) {
    if (arguments.size() <= index)
      throw new RuntimeException("Can't edit arguments. Index exceeds size.");
    return setNewArgumentLike(index, buildArgument(index));
  }

  public ArgumentsNested<A> editFirstArgument() {
    if (arguments.size() == 0)
      throw new RuntimeException("Can't edit first arguments. The list is empty.");
    return setNewArgumentLike(0, buildArgument(0));
  }

  public ArgumentsNested<A> editLastArgument() {
    int index = arguments.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last arguments. The list is empty.");
    return setNewArgumentLike(index, buildArgument(index));
  }

  public ArgumentsNested<A> editMatchingArgument(Predicate<PropertyBuilder> predicate) {
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

  public boolean hasVarArgPreferred() {
    return true;
  }

  public A addToExceptions(int index, ClassRef item) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= exceptions.size()) {
      _visitables.get("exceptions").add(builder);
      exceptions.add(builder);
    } else {
      _visitables.get("exceptions").add(index, builder);
      exceptions.add(index, builder);
    }
    return (A) this;
  }

  public A setToExceptions(int index, ClassRef item) {
    if (this.exceptions == null) {
      this.exceptions = new ArrayList<ClassRefBuilder>();
    }
    ClassRefBuilder builder = new ClassRefBuilder(item);
    if (index < 0 || index >= exceptions.size()) {
      _visitables.get("exceptions").add(builder);
      exceptions.add(builder);
    } else {
      _visitables.get("exceptions").set(index, builder);
      exceptions.set(index, builder);
    }
    return (A) this;
  }

  public A addToExceptions(io.sundr.model.ClassRef... items) {
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

  public A removeFromExceptions(io.sundr.model.ClassRef... items) {
    if (this.exceptions == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("exceptions").remove(builder);
      this.exceptions.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromExceptions(Collection<ClassRef> items) {
    if (this.exceptions == null)
      return (A) this;
    for (ClassRef item : items) {
      ClassRefBuilder builder = new ClassRefBuilder(item);
      _visitables.get("exceptions").remove(builder);
      this.exceptions.remove(builder);
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

  public List<ClassRef> buildExceptions() {
    return exceptions != null ? build(exceptions) : null;
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

  public boolean hasMatchingException(Predicate<ClassRefBuilder> predicate) {
    for (ClassRefBuilder item : exceptions) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public A withExceptions(List<ClassRef> exceptions) {
    if (this.exceptions != null) {
      _visitables.get("exceptions").clear();
    }
    if (exceptions != null) {
      this.exceptions = new ArrayList();
      for (ClassRef item : exceptions) {
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
      _visitables.remove("exceptions");
    }
    if (exceptions != null) {
      for (ClassRef item : exceptions) {
        this.addToExceptions(item);
      }
    }
    return (A) this;
  }

  public boolean hasExceptions() {
    return exceptions != null && !exceptions.isEmpty();
  }

  public ExceptionsNested<A> addNewException() {
    return new ExceptionsNested(-1, null);
  }

  public ExceptionsNested<A> addNewExceptionLike(ClassRef item) {
    return new ExceptionsNested(-1, item);
  }

  public ExceptionsNested<A> setNewExceptionLike(int index, ClassRef item) {
    return new ExceptionsNested(index, item);
  }

  public ExceptionsNested<A> editException(int index) {
    if (exceptions.size() <= index)
      throw new RuntimeException("Can't edit exceptions. Index exceeds size.");
    return setNewExceptionLike(index, buildException(index));
  }

  public ExceptionsNested<A> editFirstException() {
    if (exceptions.size() == 0)
      throw new RuntimeException("Can't edit first exceptions. The list is empty.");
    return setNewExceptionLike(0, buildException(0));
  }

  public ExceptionsNested<A> editLastException() {
    int index = exceptions.size() - 1;
    if (index < 0)
      throw new RuntimeException("Can't edit last exceptions. The list is empty.");
    return setNewExceptionLike(index, buildException(index));
  }

  public ExceptionsNested<A> editMatchingException(Predicate<ClassRefBuilder> predicate) {
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

  public boolean hasDefaultMethod() {
    return true;
  }

  public Block buildBlock() {
    return this.block != null ? this.block.build() : null;
  }

  public A withBlock(Block block) {
    _visitables.get("block").remove(this.block);
    if (block != null) {
      this.block = new BlockBuilder(block);
      _visitables.get("block").add(this.block);
    } else {
      this.block = null;
      _visitables.get("block").remove(this.block);
    }
    return (A) this;
  }

  public boolean hasBlock() {
    return this.block != null;
  }

  public BlockNested<A> withNewBlock() {
    return new BlockNested(null);
  }

  public BlockNested<A> withNewBlockLike(Block item) {
    return new BlockNested(item);
  }

  public BlockNested<A> editBlock() {
    return withNewBlockLike(java.util.Optional.ofNullable(buildBlock()).orElse(null));
  }

  public BlockNested<A> editOrNewBlock() {
    return withNewBlockLike(java.util.Optional.ofNullable(buildBlock()).orElse(new BlockBuilder().build()));
  }

  public BlockNested<A> editOrNewBlockLike(Block item) {
    return withNewBlockLike(java.util.Optional.ofNullable(buildBlock()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    MethodFluent that = (MethodFluent) o;
    if (!java.util.Objects.equals(comments, that.comments))
      return false;

    if (!java.util.Objects.equals(annotations, that.annotations))
      return false;

    if (!java.util.Objects.equals(parameters, that.parameters))
      return false;

    if (!java.util.Objects.equals(name, that.name))
      return false;

    if (!java.util.Objects.equals(returnType, that.returnType))
      return false;

    if (!java.util.Objects.equals(arguments, that.arguments))
      return false;

    if (varArgPreferred != that.varArgPreferred)
      return false;
    if (!java.util.Objects.equals(exceptions, that.exceptions))
      return false;

    if (defaultMethod != that.defaultMethod)
      return false;
    if (!java.util.Objects.equals(block, that.block))
      return false;

    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(comments, annotations, parameters, name, returnType, arguments, varArgPreferred, exceptions,
        defaultMethod, block, super.hashCode());
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

  public A withVarArgPreferred() {
    return withVarArgPreferred(true);
  }

  public A withDefaultMethod() {
    return withDefaultMethod(true);
  }

  public class AnnotationsNested<N> extends AnnotationRefFluent<AnnotationsNested<N>> implements Nested<N> {
    AnnotationsNested(int index, AnnotationRef item) {
      this.index = index;
      this.builder = new AnnotationRefBuilder(this, item);
    }

    AnnotationRefBuilder builder;
    int index;

    public N and() {
      return (N) MethodFluent.this.setToAnnotations(index, builder.build());
    }

    public N endAnnotation() {
      return and();
    }

  }

  public class ParametersNested<N> extends TypeParamDefFluent<ParametersNested<N>> implements Nested<N> {
    ParametersNested(int index, TypeParamDef item) {
      this.index = index;
      this.builder = new TypeParamDefBuilder(this, item);
    }

    TypeParamDefBuilder builder;
    int index;

    public N and() {
      return (N) MethodFluent.this.setToParameters(index, builder.build());
    }

    public N endParameter() {
      return and();
    }

  }

  public class ClassRefReturnTypeNested<N> extends ClassRefFluent<ClassRefReturnTypeNested<N>> implements Nested<N> {
    ClassRefReturnTypeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;

    public N and() {
      return (N) MethodFluent.this.withReturnType(builder.build());
    }

    public N endClassRefReturnType() {
      return and();
    }

  }

  public class PrimitiveRefReturnTypeNested<N> extends PrimitiveRefFluent<PrimitiveRefReturnTypeNested<N>>
      implements Nested<N> {
    PrimitiveRefReturnTypeNested(PrimitiveRef item) {
      this.builder = new PrimitiveRefBuilder(this, item);
    }

    PrimitiveRefBuilder builder;

    public N and() {
      return (N) MethodFluent.this.withReturnType(builder.build());
    }

    public N endPrimitiveRefReturnType() {
      return and();
    }

  }

  public class VoidRefReturnTypeNested<N> extends VoidRefFluent<VoidRefReturnTypeNested<N>> implements Nested<N> {
    VoidRefReturnTypeNested(VoidRef item) {
      this.builder = new VoidRefBuilder(this, item);
    }

    VoidRefBuilder builder;

    public N and() {
      return (N) MethodFluent.this.withReturnType(builder.build());
    }

    public N endVoidRefReturnType() {
      return and();
    }

  }

  public class TypeParamRefReturnTypeNested<N> extends TypeParamRefFluent<TypeParamRefReturnTypeNested<N>>
      implements Nested<N> {
    TypeParamRefReturnTypeNested(TypeParamRef item) {
      this.builder = new TypeParamRefBuilder(this, item);
    }

    TypeParamRefBuilder builder;

    public N and() {
      return (N) MethodFluent.this.withReturnType(builder.build());
    }

    public N endTypeParamRefReturnType() {
      return and();
    }

  }

  public class WildcardRefReturnTypeNested<N> extends WildcardRefFluent<WildcardRefReturnTypeNested<N>> implements Nested<N> {
    WildcardRefReturnTypeNested(WildcardRef item) {
      this.builder = new WildcardRefBuilder(this, item);
    }

    WildcardRefBuilder builder;

    public N and() {
      return (N) MethodFluent.this.withReturnType(builder.build());
    }

    public N endWildcardRefReturnType() {
      return and();
    }

  }

  public class ArgumentsNested<N> extends PropertyFluent<ArgumentsNested<N>> implements Nested<N> {
    ArgumentsNested(int index, Property item) {
      this.index = index;
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;
    int index;

    public N and() {
      return (N) MethodFluent.this.setToArguments(index, builder.build());
    }

    public N endArgument() {
      return and();
    }

  }

  public class ExceptionsNested<N> extends ClassRefFluent<ExceptionsNested<N>> implements Nested<N> {
    ExceptionsNested(int index, ClassRef item) {
      this.index = index;
      this.builder = new ClassRefBuilder(this, item);
    }

    ClassRefBuilder builder;
    int index;

    public N and() {
      return (N) MethodFluent.this.setToExceptions(index, builder.build());
    }

    public N endException() {
      return and();
    }

  }

  public class BlockNested<N> extends BlockFluent<BlockNested<N>> implements Nested<N> {
    BlockNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    BlockBuilder builder;

    public N and() {
      return (N) MethodFluent.this.withBlock(builder.build());
    }

    public N endBlock() {
      return and();
    }

  }

}
