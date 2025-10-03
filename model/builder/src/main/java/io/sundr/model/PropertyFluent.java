package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.RuntimeException;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;

import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class PropertyFluent<A extends io.sundr.model.PropertyFluent<A>> extends ModifierSupportFluent<A> {

  private ArrayList<AnnotationRefBuilder> annotations = new ArrayList<AnnotationRefBuilder>();
  private List<String> comments = new ArrayList<String>();
  private boolean enumConstant;
  private Optional<VisitableBuilder<? extends Expression, ?>> initialValue = Optional.empty();
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
      this.annotations = new ArrayList();
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
      this.comments = new ArrayList();
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
      this.annotations = new ArrayList();
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
      this.annotations = new ArrayList();
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
      this.comments = new ArrayList();
    }
    for (String item : items) {
      this.comments.add(item);
    }
    return (A) this;
  }

  public A addToComments(int index, String item) {
    if (this.comments == null) {
      this.comments = new ArrayList();
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

  public Optional<Expression> buildInitialValue() {
    return this.initialValue != null ? this.initialValue.map(v -> v.build()) : Optional.empty();
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
      case "ClassRef":

        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);

      case "PrimitiveRef":

        return (VisitableBuilder<T, ?>) new PrimitiveRefBuilder((PrimitiveRef) item);

      case "VoidRef":

        return (VisitableBuilder<T, ?>) new VoidRefBuilder((VoidRef) item);

      case "TypeParamRef":

        return (VisitableBuilder<T, ?>) new TypeParamRefBuilder((TypeParamRef) item);

      case "WildcardRef":

        return (VisitableBuilder<T, ?>) new WildcardRefBuilder((WildcardRef) item);

      case "Multiply":

        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);

      case "NewArray":

        return (VisitableBuilder<T, ?>) new NewArrayBuilder((NewArray) item);

      case "InstanceOf":

        return (VisitableBuilder<T, ?>) new InstanceOfBuilder((InstanceOf) item);

      case "MethodCall":

        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);

      case "Inverse":

        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);

      case "Index":

        return (VisitableBuilder<T, ?>) new IndexBuilder((Index) item);

      case "GreaterThanOrEqual":

        return (VisitableBuilder<T, ?>) new GreaterThanOrEqualBuilder((GreaterThanOrEqual) item);

      case "BitwiseAnd":

        return (VisitableBuilder<T, ?>) new BitwiseAndBuilder((BitwiseAnd) item);

      case "Minus":

        return (VisitableBuilder<T, ?>) new MinusBuilder((Minus) item);

      case "LogicalOr":

        return (VisitableBuilder<T, ?>) new LogicalOrBuilder((LogicalOr) item);

      case "NotEquals":

        return (VisitableBuilder<T, ?>) new NotEqualsBuilder((NotEquals) item);

      case "Divide":

        return (VisitableBuilder<T, ?>) new DivideBuilder((Divide) item);

      case "LessThan":

        return (VisitableBuilder<T, ?>) new LessThanBuilder((LessThan) item);

      case "BitwiseOr":

        return (VisitableBuilder<T, ?>) new BitwiseOrBuilder((BitwiseOr) item);

      case "PropertyRef":

        return (VisitableBuilder<T, ?>) new PropertyRefBuilder((PropertyRef) item);

      case "RightShift":

        return (VisitableBuilder<T, ?>) new RightShiftBuilder((RightShift) item);

      case "Super":

        return (VisitableBuilder<T, ?>) new SuperBuilder((Super) item);

      case "GreaterThan":

        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);

      case "Declare":

        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);

      case "Cast":

        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);

      case "Modulo":

        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);

      case "DotClass":

        return (VisitableBuilder<T, ?>) new DotClassBuilder((DotClass) item);

      case "ValueRef":

        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);

      case "LeftShift":

        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);

      case "Empty":

        return (VisitableBuilder<T, ?>) new EmptyBuilder((Empty) item);

      case "Ternary":

        return (VisitableBuilder<T, ?>) new TernaryBuilder((Ternary) item);

      case "BinaryExpression":

        return (VisitableBuilder<T, ?>) new BinaryExpressionBuilder((BinaryExpression) item);

      case "Equals":

        return (VisitableBuilder<T, ?>) new EqualsBuilder((Equals) item);

      case "Enclosed":

        return (VisitableBuilder<T, ?>) new EnclosedBuilder((Enclosed) item);

      case "PreDecrement":

        return (VisitableBuilder<T, ?>) new PreDecrementBuilder((PreDecrement) item);

      case "PostDecrement":

        return (VisitableBuilder<T, ?>) new PostDecrementBuilder((PostDecrement) item);

      case "Lambda":

        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);

      case "Not":

        return (VisitableBuilder<T, ?>) new NotBuilder((Not) item);

      case "Assign":

        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);

      case "This":

        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);

      case "Negative":

        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);

      case "LogicalAnd":

        return (VisitableBuilder<T, ?>) new LogicalAndBuilder((LogicalAnd) item);

      case "PostIncrement":

        return (VisitableBuilder<T, ?>) new PostIncrementBuilder((PostIncrement) item);

      case "RightUnsignedShift":

        return (VisitableBuilder<T, ?>) new RightUnsignedShiftBuilder((RightUnsignedShift) item);

      case "Plus":

        return (VisitableBuilder<T, ?>) new PlusBuilder((Plus) item);

      case "Construct":

        return (VisitableBuilder<T, ?>) new ConstructBuilder((Construct) item);

      case "Xor":

        return (VisitableBuilder<T, ?>) new XorBuilder((Xor) item);

      case "PreIncrement":

        return (VisitableBuilder<T, ?>) new PreIncrementBuilder((PreIncrement) item);

      case "Property":

        return (VisitableBuilder<T, ?>) new PropertyBuilder((Property) item);

      case "LessThanOrEqual":

        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);

      case "ContextRef":

        return (VisitableBuilder<T, ?>) new ContextRefBuilder((ContextRef) item);

      case "Positive":

        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
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
    if (annotations.size() <= index) {
      throw new RuntimeException(String.format("Can't edit %s. Index exceeds size.", "annotations"));
    }
    return this.setNewAnnotationLike(index, this.buildAnnotation(index));
  }

  public AnnotationsNested<A> editFirstAnnotation() {
    if (annotations.size() == 0) {
      throw new RuntimeException(String.format("Can't edit first %s. The list is empty.", "annotations"));
    }
    return this.setNewAnnotationLike(0, this.buildAnnotation(0));
  }

  public AnnotationsNested<A> editLastAnnotation() {
    int index = annotations.size() - 1;
    if (index < 0) {
      throw new RuntimeException(String.format("Can't edit last %s. The list is empty.", "annotations"));
    }
    return this.setNewAnnotationLike(index, this.buildAnnotation(index));
  }

  public AnnotationsNested<A> editMatchingAnnotation(Predicate<AnnotationRefBuilder> predicate) {
    int index = -1;
    for (int i = 0; i < annotations.size(); i++) {
      if (predicate.test(annotations.get(i))) {
        index = i;
        break;
      }
    }
    if (index < 0) {
      throw new RuntimeException(String.format("Can't edit matching %s. No match found.", "annotations"));
    }
    return this.setNewAnnotationLike(index, this.buildAnnotation(index));
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    PropertyFluent that = (PropertyFluent) o;
    if (!(Objects.equals(comments, that.comments))) {
      return false;
    }
    if (!(Objects.equals(annotations, that.annotations))) {
      return false;
    }
    if (!(Objects.equals(typeRef, that.typeRef))) {
      return false;
    }
    if (!(Objects.equals(name, that.name))) {
      return false;
    }
    if (!(Objects.equals(initialValue, that.initialValue))) {
      return false;
    }
    if (enumConstant != that.enumConstant) {
      return false;
    }
    if (synthetic != that.synthetic) {
      return false;
    }
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
    return Objects.hash(comments, annotations, typeRef, name, initialValue, enumConstant, synthetic);
  }

  public boolean isEnumConstant() {
    return this.enumConstant;
  }

  public boolean isSynthetic() {
    return this.synthetic;
  }

  public A removeAllFromAnnotations(Collection<AnnotationRef> items) {
    if (this.annotations == null) {
      return (A) this;
    }
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromComments(Collection<String> items) {
    if (this.comments == null) {
      return (A) this;
    }
    for (String item : items) {
      this.comments.remove(item);
    }
    return (A) this;
  }

  public A removeFromAnnotations(AnnotationRef... items) {
    if (this.annotations == null) {
      return (A) this;
    }
    for (AnnotationRef item : items) {
      AnnotationRefBuilder builder = new AnnotationRefBuilder(item);
      _visitables.get("annotations").remove(builder);
      this.annotations.remove(builder);
    }
    return (A) this;
  }

  public A removeFromComments(String... items) {
    if (this.comments == null) {
      return (A) this;
    }
    for (String item : items) {
      this.comments.remove(item);
    }
    return (A) this;
  }

  public A removeMatchingFromAnnotations(Predicate<AnnotationRefBuilder> predicate) {
    if (annotations == null) {
      return (A) this;
    }
    Iterator<AnnotationRefBuilder> each = annotations.iterator();
    List visitables = _visitables.get("annotations");
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
      this.annotations = new ArrayList();
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
      this.comments = new ArrayList();
    }
    this.comments.set(index, item);
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(comments == null) && !(comments.isEmpty())) {
      sb.append("comments:");
      sb.append(comments);
      sb.append(",");
    }
    if (!(annotations == null) && !(annotations.isEmpty())) {
      sb.append("annotations:");
      sb.append(annotations);
      sb.append(",");
    }
    if (!(typeRef == null)) {
      sb.append("typeRef:");
      sb.append(typeRef);
      sb.append(",");
    }
    if (!(name == null)) {
      sb.append("name:");
      sb.append(name);
      sb.append(",");
    }
    if (!(initialValue == null)) {
      sb.append("initialValue:");
      sb.append(initialValue);
      sb.append(",");
    }
    sb.append("enumConstant:");
    sb.append(enumConstant);
    sb.append(",");
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
      VisitableBuilder<? extends Expression, ?> b = builder(initialValue.get());
      _visitables.get("initialValue").add(b);
      this.initialValue = Optional.of(b);
    }
    return (A) this;
  }

  public A withInitialValue(Expression initialValue) {
    if (initialValue == null) {
      this.initialValue = Optional.empty();
    } else {
      VisitableBuilder<? extends Expression, ?> b = builder(initialValue);
      _visitables.get("initialValue").add(b);
      this.initialValue = Optional.of(b);
    }
    return (A) this;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public AssignInitialValueNested<A> withNewAssignInitialValue() {
    return new AssignInitialValueNested(null);
  }

  public AssignInitialValueNested<A> withNewAssignInitialValueLike(Assign item) {
    return new AssignInitialValueNested(item);
  }

  public BinaryExpressionInitialValueNested<A> withNewBinaryExpressionInitialValue() {
    return new BinaryExpressionInitialValueNested(null);
  }

  public BinaryExpressionInitialValueNested<A> withNewBinaryExpressionInitialValueLike(BinaryExpression item) {
    return new BinaryExpressionInitialValueNested(item);
  }

  public BitwiseAndInitialValueNested<A> withNewBitwiseAndInitialValue() {
    return new BitwiseAndInitialValueNested(null);
  }

  public A withNewBitwiseAndInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new BitwiseAnd(left, right));
  }

  public BitwiseAndInitialValueNested<A> withNewBitwiseAndInitialValueLike(BitwiseAnd item) {
    return new BitwiseAndInitialValueNested(item);
  }

  public BitwiseOrInitialValueNested<A> withNewBitwiseOrInitialValue() {
    return new BitwiseOrInitialValueNested(null);
  }

  public A withNewBitwiseOrInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new BitwiseOr(left, right));
  }

  public BitwiseOrInitialValueNested<A> withNewBitwiseOrInitialValueLike(BitwiseOr item) {
    return new BitwiseOrInitialValueNested(item);
  }

  public CastInitialValueNested<A> withNewCastInitialValue() {
    return new CastInitialValueNested(null);
  }

  public CastInitialValueNested<A> withNewCastInitialValueLike(Cast item) {
    return new CastInitialValueNested(item);
  }

  public ClassRefInitialValueNested<A> withNewClassRefInitialValue() {
    return new ClassRefInitialValueNested(null);
  }

  public ClassRefInitialValueNested<A> withNewClassRefInitialValueLike(ClassRef item) {
    return new ClassRefInitialValueNested(item);
  }

  public ClassRefTypeNested<A> withNewClassRefType() {
    return new ClassRefTypeNested(null);
  }

  public ClassRefTypeNested<A> withNewClassRefTypeLike(ClassRef item) {
    return new ClassRefTypeNested(item);
  }

  public ConstructInitialValueNested<A> withNewConstructInitialValue() {
    return new ConstructInitialValueNested(null);
  }

  public ConstructInitialValueNested<A> withNewConstructInitialValueLike(Construct item) {
    return new ConstructInitialValueNested(item);
  }

  public ContextRefInitialValueNested<A> withNewContextRefInitialValue() {
    return new ContextRefInitialValueNested(null);
  }

  public A withNewContextRefInitialValue(String name) {
    return (A) this.withInitialValue(new ContextRef(name));
  }

  public ContextRefInitialValueNested<A> withNewContextRefInitialValueLike(ContextRef item) {
    return new ContextRefInitialValueNested(item);
  }

  public DeclareInitialValueNested<A> withNewDeclareInitialValue() {
    return new DeclareInitialValueNested(null);
  }

  public A withNewDeclareInitialValue(Class type, String name) {
    return (A) this.withInitialValue(new Declare(type, name));
  }

  public A withNewDeclareInitialValue(Class type, String name, Object value) {
    return (A) this.withInitialValue(new Declare(type, name, value));
  }

  public DeclareInitialValueNested<A> withNewDeclareInitialValueLike(Declare item) {
    return new DeclareInitialValueNested(item);
  }

  public DivideInitialValueNested<A> withNewDivideInitialValue() {
    return new DivideInitialValueNested(null);
  }

  public A withNewDivideInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Divide(left, right));
  }

  public DivideInitialValueNested<A> withNewDivideInitialValueLike(Divide item) {
    return new DivideInitialValueNested(item);
  }

  public DotClassInitialValueNested<A> withNewDotClassInitialValue() {
    return new DotClassInitialValueNested(null);
  }

  public DotClassInitialValueNested<A> withNewDotClassInitialValueLike(DotClass item) {
    return new DotClassInitialValueNested(item);
  }

  public EmptyInitialValueNested<A> withNewEmptyInitialValue() {
    return new EmptyInitialValueNested(null);
  }

  public EmptyInitialValueNested<A> withNewEmptyInitialValueLike(Empty item) {
    return new EmptyInitialValueNested(item);
  }

  public EnclosedInitialValueNested<A> withNewEnclosedInitialValue() {
    return new EnclosedInitialValueNested(null);
  }

  public EnclosedInitialValueNested<A> withNewEnclosedInitialValueLike(Enclosed item) {
    return new EnclosedInitialValueNested(item);
  }

  public EqualsInitialValueNested<A> withNewEqualsInitialValue() {
    return new EqualsInitialValueNested(null);
  }

  public A withNewEqualsInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Equals(left, right));
  }

  public EqualsInitialValueNested<A> withNewEqualsInitialValueLike(Equals item) {
    return new EqualsInitialValueNested(item);
  }

  public GreaterThanInitialValueNested<A> withNewGreaterThanInitialValue() {
    return new GreaterThanInitialValueNested(null);
  }

  public A withNewGreaterThanInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new GreaterThan(left, right));
  }

  public GreaterThanInitialValueNested<A> withNewGreaterThanInitialValueLike(GreaterThan item) {
    return new GreaterThanInitialValueNested(item);
  }

  public GreaterThanOrEqualInitialValueNested<A> withNewGreaterThanOrEqualInitialValue() {
    return new GreaterThanOrEqualInitialValueNested(null);
  }

  public A withNewGreaterThanOrEqualInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualInitialValueNested<A> withNewGreaterThanOrEqualInitialValueLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualInitialValueNested(item);
  }

  public IndexInitialValueNested<A> withNewIndexInitialValue() {
    return new IndexInitialValueNested(null);
  }

  public IndexInitialValueNested<A> withNewIndexInitialValueLike(Index item) {
    return new IndexInitialValueNested(item);
  }

  public InstanceOfInitialValueNested<A> withNewInstanceOfInitialValue() {
    return new InstanceOfInitialValueNested(null);
  }

  public InstanceOfInitialValueNested<A> withNewInstanceOfInitialValueLike(InstanceOf item) {
    return new InstanceOfInitialValueNested(item);
  }

  public InverseInitialValueNested<A> withNewInverseInitialValue() {
    return new InverseInitialValueNested(null);
  }

  public InverseInitialValueNested<A> withNewInverseInitialValueLike(Inverse item) {
    return new InverseInitialValueNested(item);
  }

  public LambdaInitialValueNested<A> withNewLambdaInitialValue() {
    return new LambdaInitialValueNested(null);
  }

  public LambdaInitialValueNested<A> withNewLambdaInitialValueLike(Lambda item) {
    return new LambdaInitialValueNested(item);
  }

  public LeftShiftInitialValueNested<A> withNewLeftShiftInitialValue() {
    return new LeftShiftInitialValueNested(null);
  }

  public A withNewLeftShiftInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new LeftShift(left, right));
  }

  public LeftShiftInitialValueNested<A> withNewLeftShiftInitialValueLike(LeftShift item) {
    return new LeftShiftInitialValueNested(item);
  }

  public LessThanInitialValueNested<A> withNewLessThanInitialValue() {
    return new LessThanInitialValueNested(null);
  }

  public A withNewLessThanInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new LessThan(left, right));
  }

  public LessThanInitialValueNested<A> withNewLessThanInitialValueLike(LessThan item) {
    return new LessThanInitialValueNested(item);
  }

  public LessThanOrEqualInitialValueNested<A> withNewLessThanOrEqualInitialValue() {
    return new LessThanOrEqualInitialValueNested(null);
  }

  public A withNewLessThanOrEqualInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualInitialValueNested<A> withNewLessThanOrEqualInitialValueLike(LessThanOrEqual item) {
    return new LessThanOrEqualInitialValueNested(item);
  }

  public LogicalAndInitialValueNested<A> withNewLogicalAndInitialValue() {
    return new LogicalAndInitialValueNested(null);
  }

  public A withNewLogicalAndInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new LogicalAnd(left, right));
  }

  public LogicalAndInitialValueNested<A> withNewLogicalAndInitialValueLike(LogicalAnd item) {
    return new LogicalAndInitialValueNested(item);
  }

  public LogicalOrInitialValueNested<A> withNewLogicalOrInitialValue() {
    return new LogicalOrInitialValueNested(null);
  }

  public A withNewLogicalOrInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new LogicalOr(left, right));
  }

  public LogicalOrInitialValueNested<A> withNewLogicalOrInitialValueLike(LogicalOr item) {
    return new LogicalOrInitialValueNested(item);
  }

  public MethodCallInitialValueNested<A> withNewMethodCallInitialValue() {
    return new MethodCallInitialValueNested(null);
  }

  public MethodCallInitialValueNested<A> withNewMethodCallInitialValueLike(MethodCall item) {
    return new MethodCallInitialValueNested(item);
  }

  public MinusInitialValueNested<A> withNewMinusInitialValue() {
    return new MinusInitialValueNested(null);
  }

  public A withNewMinusInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Minus(left, right));
  }

  public MinusInitialValueNested<A> withNewMinusInitialValueLike(Minus item) {
    return new MinusInitialValueNested(item);
  }

  public ModuloInitialValueNested<A> withNewModuloInitialValue() {
    return new ModuloInitialValueNested(null);
  }

  public A withNewModuloInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Modulo(left, right));
  }

  public ModuloInitialValueNested<A> withNewModuloInitialValueLike(Modulo item) {
    return new ModuloInitialValueNested(item);
  }

  public MultiplyInitialValueNested<A> withNewMultiplyInitialValue() {
    return new MultiplyInitialValueNested(null);
  }

  public A withNewMultiplyInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Multiply(left, right));
  }

  public MultiplyInitialValueNested<A> withNewMultiplyInitialValueLike(Multiply item) {
    return new MultiplyInitialValueNested(item);
  }

  public NegativeInitialValueNested<A> withNewNegativeInitialValue() {
    return new NegativeInitialValueNested(null);
  }

  public NegativeInitialValueNested<A> withNewNegativeInitialValueLike(Negative item) {
    return new NegativeInitialValueNested(item);
  }

  public NewArrayInitialValueNested<A> withNewNewArrayInitialValue() {
    return new NewArrayInitialValueNested(null);
  }

  public A withNewNewArrayInitialValue(Class type, Integer[] sizes) {
    return (A) this.withInitialValue(new NewArray(type, sizes));
  }

  public NewArrayInitialValueNested<A> withNewNewArrayInitialValueLike(NewArray item) {
    return new NewArrayInitialValueNested(item);
  }

  public NotEqualsInitialValueNested<A> withNewNotEqualsInitialValue() {
    return new NotEqualsInitialValueNested(null);
  }

  public A withNewNotEqualsInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new NotEquals(left, right));
  }

  public NotEqualsInitialValueNested<A> withNewNotEqualsInitialValueLike(NotEquals item) {
    return new NotEqualsInitialValueNested(item);
  }

  public NotInitialValueNested<A> withNewNotInitialValue() {
    return new NotInitialValueNested(null);
  }

  public NotInitialValueNested<A> withNewNotInitialValueLike(Not item) {
    return new NotInitialValueNested(item);
  }

  public PlusInitialValueNested<A> withNewPlusInitialValue() {
    return new PlusInitialValueNested(null);
  }

  public A withNewPlusInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Plus(left, right));
  }

  public PlusInitialValueNested<A> withNewPlusInitialValueLike(Plus item) {
    return new PlusInitialValueNested(item);
  }

  public PositiveInitialValueNested<A> withNewPositiveInitialValue() {
    return new PositiveInitialValueNested(null);
  }

  public PositiveInitialValueNested<A> withNewPositiveInitialValueLike(Positive item) {
    return new PositiveInitialValueNested(item);
  }

  public PostDecrementInitialValueNested<A> withNewPostDecrementInitialValue() {
    return new PostDecrementInitialValueNested(null);
  }

  public PostDecrementInitialValueNested<A> withNewPostDecrementInitialValueLike(PostDecrement item) {
    return new PostDecrementInitialValueNested(item);
  }

  public PostIncrementInitialValueNested<A> withNewPostIncrementInitialValue() {
    return new PostIncrementInitialValueNested(null);
  }

  public PostIncrementInitialValueNested<A> withNewPostIncrementInitialValueLike(PostIncrement item) {
    return new PostIncrementInitialValueNested(item);
  }

  public PreDecrementInitialValueNested<A> withNewPreDecrementInitialValue() {
    return new PreDecrementInitialValueNested(null);
  }

  public PreDecrementInitialValueNested<A> withNewPreDecrementInitialValueLike(PreDecrement item) {
    return new PreDecrementInitialValueNested(item);
  }

  public PreIncrementInitialValueNested<A> withNewPreIncrementInitialValue() {
    return new PreIncrementInitialValueNested(null);
  }

  public PreIncrementInitialValueNested<A> withNewPreIncrementInitialValueLike(PreIncrement item) {
    return new PreIncrementInitialValueNested(item);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefType() {
    return new PrimitiveRefTypeNested(null);
  }

  public PrimitiveRefTypeNested<A> withNewPrimitiveRefTypeLike(PrimitiveRef item) {
    return new PrimitiveRefTypeNested(item);
  }

  public PropertyInitialValueNested<A> withNewPropertyInitialValue() {
    return new PropertyInitialValueNested(null);
  }

  public PropertyInitialValueNested<A> withNewPropertyInitialValueLike(Property item) {
    return new PropertyInitialValueNested(item);
  }

  public PropertyRefInitialValueNested<A> withNewPropertyRefInitialValue() {
    return new PropertyRefInitialValueNested(null);
  }

  public PropertyRefInitialValueNested<A> withNewPropertyRefInitialValueLike(PropertyRef item) {
    return new PropertyRefInitialValueNested(item);
  }

  public RightShiftInitialValueNested<A> withNewRightShiftInitialValue() {
    return new RightShiftInitialValueNested(null);
  }

  public A withNewRightShiftInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new RightShift(left, right));
  }

  public RightShiftInitialValueNested<A> withNewRightShiftInitialValueLike(RightShift item) {
    return new RightShiftInitialValueNested(item);
  }

  public RightUnsignedShiftInitialValueNested<A> withNewRightUnsignedShiftInitialValue() {
    return new RightUnsignedShiftInitialValueNested(null);
  }

  public A withNewRightUnsignedShiftInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftInitialValueNested<A> withNewRightUnsignedShiftInitialValueLike(RightUnsignedShift item) {
    return new RightUnsignedShiftInitialValueNested(item);
  }

  public SuperInitialValueNested<A> withNewSuperInitialValue() {
    return new SuperInitialValueNested(null);
  }

  public SuperInitialValueNested<A> withNewSuperInitialValueLike(Super item) {
    return new SuperInitialValueNested(item);
  }

  public TernaryInitialValueNested<A> withNewTernaryInitialValue() {
    return new TernaryInitialValueNested(null);
  }

  public TernaryInitialValueNested<A> withNewTernaryInitialValueLike(Ternary item) {
    return new TernaryInitialValueNested(item);
  }

  public ThisInitialValueNested<A> withNewThisInitialValue() {
    return new ThisInitialValueNested(null);
  }

  public ThisInitialValueNested<A> withNewThisInitialValueLike(This item) {
    return new ThisInitialValueNested(item);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefType() {
    return new TypeParamRefTypeNested(null);
  }

  public TypeParamRefTypeNested<A> withNewTypeParamRefTypeLike(TypeParamRef item) {
    return new TypeParamRefTypeNested(item);
  }

  public ValueRefInitialValueNested<A> withNewValueRefInitialValue() {
    return new ValueRefInitialValueNested(null);
  }

  public A withNewValueRefInitialValue(Object value) {
    return (A) this.withInitialValue(new ValueRef(value));
  }

  public ValueRefInitialValueNested<A> withNewValueRefInitialValueLike(ValueRef item) {
    return new ValueRefInitialValueNested(item);
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

  public XorInitialValueNested<A> withNewXorInitialValue() {
    return new XorInitialValueNested(null);
  }

  public A withNewXorInitialValue(Object left, Object right) {
    return (A) this.withInitialValue(new Xor(left, right));
  }

  public XorInitialValueNested<A> withNewXorInitialValueLike(Xor item) {
    return new XorInitialValueNested(item);
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

  public class AssignInitialValueNested<N> extends AssignFluent<AssignInitialValueNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignInitialValueNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endAssignInitialValue() {
      return and();
    }

  }

  public class BinaryExpressionInitialValueNested<N> extends BinaryExpressionFluent<BinaryExpressionInitialValueNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionInitialValueNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endBinaryExpressionInitialValue() {
      return and();
    }

  }

  public class BitwiseAndInitialValueNested<N> extends BitwiseAndFluent<BitwiseAndInitialValueNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndInitialValueNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endBitwiseAndInitialValue() {
      return and();
    }

  }

  public class BitwiseOrInitialValueNested<N> extends BitwiseOrFluent<BitwiseOrInitialValueNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrInitialValueNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endBitwiseOrInitialValue() {
      return and();
    }

  }

  public class CastInitialValueNested<N> extends CastFluent<CastInitialValueNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastInitialValueNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endCastInitialValue() {
      return and();
    }

  }

  public class ClassRefInitialValueNested<N> extends ClassRefFluent<ClassRefInitialValueNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefInitialValueNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endClassRefInitialValue() {
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

  public class ConstructInitialValueNested<N> extends ConstructFluent<ConstructInitialValueNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructInitialValueNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endConstructInitialValue() {
      return and();
    }

  }

  public class ContextRefInitialValueNested<N> extends ContextRefFluent<ContextRefInitialValueNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefInitialValueNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endContextRefInitialValue() {
      return and();
    }

  }

  public class DeclareInitialValueNested<N> extends DeclareFluent<DeclareInitialValueNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareInitialValueNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endDeclareInitialValue() {
      return and();
    }

  }

  public class DivideInitialValueNested<N> extends DivideFluent<DivideInitialValueNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideInitialValueNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endDivideInitialValue() {
      return and();
    }

  }

  public class DotClassInitialValueNested<N> extends DotClassFluent<DotClassInitialValueNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassInitialValueNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endDotClassInitialValue() {
      return and();
    }

  }

  public class EmptyInitialValueNested<N> extends EmptyFluent<EmptyInitialValueNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyInitialValueNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endEmptyInitialValue() {
      return and();
    }

  }

  public class EnclosedInitialValueNested<N> extends EnclosedFluent<EnclosedInitialValueNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedInitialValueNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endEnclosedInitialValue() {
      return and();
    }

  }

  public class EqualsInitialValueNested<N> extends EqualsFluent<EqualsInitialValueNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsInitialValueNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endEqualsInitialValue() {
      return and();
    }

  }

  public class GreaterThanInitialValueNested<N> extends GreaterThanFluent<GreaterThanInitialValueNested<N>>
      implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanInitialValueNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endGreaterThanInitialValue() {
      return and();
    }

  }

  public class GreaterThanOrEqualInitialValueNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualInitialValueNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualInitialValueNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endGreaterThanOrEqualInitialValue() {
      return and();
    }

  }

  public class IndexInitialValueNested<N> extends IndexFluent<IndexInitialValueNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexInitialValueNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endIndexInitialValue() {
      return and();
    }

  }

  public class InstanceOfInitialValueNested<N> extends InstanceOfFluent<InstanceOfInitialValueNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfInitialValueNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endInstanceOfInitialValue() {
      return and();
    }

  }

  public class InverseInitialValueNested<N> extends InverseFluent<InverseInitialValueNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseInitialValueNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endInverseInitialValue() {
      return and();
    }

  }

  public class LambdaInitialValueNested<N> extends LambdaFluent<LambdaInitialValueNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaInitialValueNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endLambdaInitialValue() {
      return and();
    }

  }

  public class LeftShiftInitialValueNested<N> extends LeftShiftFluent<LeftShiftInitialValueNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftInitialValueNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endLeftShiftInitialValue() {
      return and();
    }

  }

  public class LessThanInitialValueNested<N> extends LessThanFluent<LessThanInitialValueNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanInitialValueNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endLessThanInitialValue() {
      return and();
    }

  }

  public class LessThanOrEqualInitialValueNested<N> extends LessThanOrEqualFluent<LessThanOrEqualInitialValueNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualInitialValueNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endLessThanOrEqualInitialValue() {
      return and();
    }

  }

  public class LogicalAndInitialValueNested<N> extends LogicalAndFluent<LogicalAndInitialValueNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndInitialValueNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endLogicalAndInitialValue() {
      return and();
    }

  }

  public class LogicalOrInitialValueNested<N> extends LogicalOrFluent<LogicalOrInitialValueNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrInitialValueNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endLogicalOrInitialValue() {
      return and();
    }

  }

  public class MethodCallInitialValueNested<N> extends MethodCallFluent<MethodCallInitialValueNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallInitialValueNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endMethodCallInitialValue() {
      return and();
    }

  }

  public class MinusInitialValueNested<N> extends MinusFluent<MinusInitialValueNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusInitialValueNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endMinusInitialValue() {
      return and();
    }

  }

  public class ModuloInitialValueNested<N> extends ModuloFluent<ModuloInitialValueNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloInitialValueNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endModuloInitialValue() {
      return and();
    }

  }

  public class MultiplyInitialValueNested<N> extends MultiplyFluent<MultiplyInitialValueNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyInitialValueNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endMultiplyInitialValue() {
      return and();
    }

  }

  public class NegativeInitialValueNested<N> extends NegativeFluent<NegativeInitialValueNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeInitialValueNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endNegativeInitialValue() {
      return and();
    }

  }

  public class NewArrayInitialValueNested<N> extends NewArrayFluent<NewArrayInitialValueNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayInitialValueNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endNewArrayInitialValue() {
      return and();
    }

  }

  public class NotEqualsInitialValueNested<N> extends NotEqualsFluent<NotEqualsInitialValueNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsInitialValueNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endNotEqualsInitialValue() {
      return and();
    }

  }

  public class NotInitialValueNested<N> extends NotFluent<NotInitialValueNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotInitialValueNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endNotInitialValue() {
      return and();
    }

  }

  public class PlusInitialValueNested<N> extends PlusFluent<PlusInitialValueNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusInitialValueNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPlusInitialValue() {
      return and();
    }

  }

  public class PositiveInitialValueNested<N> extends PositiveFluent<PositiveInitialValueNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveInitialValueNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPositiveInitialValue() {
      return and();
    }

  }

  public class PostDecrementInitialValueNested<N> extends PostDecrementFluent<PostDecrementInitialValueNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementInitialValueNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPostDecrementInitialValue() {
      return and();
    }

  }

  public class PostIncrementInitialValueNested<N> extends PostIncrementFluent<PostIncrementInitialValueNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementInitialValueNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPostIncrementInitialValue() {
      return and();
    }

  }

  public class PreDecrementInitialValueNested<N> extends PreDecrementFluent<PreDecrementInitialValueNested<N>>
      implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementInitialValueNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPreDecrementInitialValue() {
      return and();
    }

  }

  public class PreIncrementInitialValueNested<N> extends PreIncrementFluent<PreIncrementInitialValueNested<N>>
      implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementInitialValueNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPreIncrementInitialValue() {
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

  public class PropertyInitialValueNested<N> extends PropertyFluent<PropertyInitialValueNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyInitialValueNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPropertyInitialValue() {
      return and();
    }

  }

  public class PropertyRefInitialValueNested<N> extends PropertyRefFluent<PropertyRefInitialValueNested<N>>
      implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefInitialValueNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endPropertyRefInitialValue() {
      return and();
    }

  }

  public class RightShiftInitialValueNested<N> extends RightShiftFluent<RightShiftInitialValueNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftInitialValueNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endRightShiftInitialValue() {
      return and();
    }

  }

  public class RightUnsignedShiftInitialValueNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftInitialValueNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftInitialValueNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endRightUnsignedShiftInitialValue() {
      return and();
    }

  }

  public class SuperInitialValueNested<N> extends SuperFluent<SuperInitialValueNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperInitialValueNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endSuperInitialValue() {
      return and();
    }

  }

  public class TernaryInitialValueNested<N> extends TernaryFluent<TernaryInitialValueNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryInitialValueNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endTernaryInitialValue() {
      return and();
    }

  }

  public class ThisInitialValueNested<N> extends ThisFluent<ThisInitialValueNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisInitialValueNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endThisInitialValue() {
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

  public class ValueRefInitialValueNested<N> extends ValueRefFluent<ValueRefInitialValueNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefInitialValueNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endValueRefInitialValue() {
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

  public class XorInitialValueNested<N> extends XorFluent<XorInitialValueNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorInitialValueNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) PropertyFluent.this.withInitialValue(builder.build());
    }

    public N endXorInitialValue() {
      return and();
    }

  }
}
