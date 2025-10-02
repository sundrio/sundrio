package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class TernaryFluent<A extends TernaryFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> alternative;
  private VisitableBuilder<? extends Expression, ?> condition;
  private VisitableBuilder<? extends Expression, ?> result;

  public TernaryFluent() {
  }

  public TernaryFluent(Ternary instance) {
    this.copyInstance(instance);
  }

  public Expression buildAlternative() {
    return this.alternative != null ? this.alternative.build() : null;
  }

  public Expression buildCondition() {
    return this.condition != null ? this.condition.build() : null;
  }

  public Expression buildResult() {
    return this.result != null ? this.result.build() : null;
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "io.sundr.model." + "Multiply":
        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);
      case "io.sundr.model." + "NewArray":
        return (VisitableBuilder<T, ?>) new NewArrayBuilder((NewArray) item);
      case "io.sundr.model." + "InstanceOf":
        return (VisitableBuilder<T, ?>) new InstanceOfBuilder((InstanceOf) item);
      case "io.sundr.model." + "MethodCall":
        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);
      case "io.sundr.model." + "ClassRef":
        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);
      case "io.sundr.model." + "Inverse":
        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);
      case "io.sundr.model." + "Index":
        return (VisitableBuilder<T, ?>) new IndexBuilder((Index) item);
      case "io.sundr.model." + "GreaterThanOrEqual":
        return (VisitableBuilder<T, ?>) new GreaterThanOrEqualBuilder((GreaterThanOrEqual) item);
      case "io.sundr.model." + "BitwiseAnd":
        return (VisitableBuilder<T, ?>) new BitwiseAndBuilder((BitwiseAnd) item);
      case "io.sundr.model." + "Minus":
        return (VisitableBuilder<T, ?>) new MinusBuilder((Minus) item);
      case "io.sundr.model." + "LogicalOr":
        return (VisitableBuilder<T, ?>) new LogicalOrBuilder((LogicalOr) item);
      case "io.sundr.model." + "NotEquals":
        return (VisitableBuilder<T, ?>) new NotEqualsBuilder((NotEquals) item);
      case "io.sundr.model." + "Divide":
        return (VisitableBuilder<T, ?>) new DivideBuilder((Divide) item);
      case "io.sundr.model." + "LessThan":
        return (VisitableBuilder<T, ?>) new LessThanBuilder((LessThan) item);
      case "io.sundr.model." + "BitwiseOr":
        return (VisitableBuilder<T, ?>) new BitwiseOrBuilder((BitwiseOr) item);
      case "io.sundr.model." + "PropertyRef":
        return (VisitableBuilder<T, ?>) new PropertyRefBuilder((PropertyRef) item);
      case "io.sundr.model." + "RightShift":
        return (VisitableBuilder<T, ?>) new RightShiftBuilder((RightShift) item);
      case "io.sundr.model." + "Super":
        return (VisitableBuilder<T, ?>) new SuperBuilder((Super) item);
      case "io.sundr.model." + "GreaterThan":
        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);
      case "io.sundr.model." + "Declare":
        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);
      case "io.sundr.model." + "Cast":
        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);
      case "io.sundr.model." + "Modulo":
        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);
      case "io.sundr.model." + "DotClass":
        return (VisitableBuilder<T, ?>) new DotClassBuilder((DotClass) item);
      case "io.sundr.model." + "ValueRef":
        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);
      case "io.sundr.model." + "LeftShift":
        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);
      case "io.sundr.model." + "Empty":
        return (VisitableBuilder<T, ?>) new EmptyBuilder((Empty) item);
      case "io.sundr.model." + "Ternary":
        return (VisitableBuilder<T, ?>) new TernaryBuilder((Ternary) item);
      case "io.sundr.model." + "BinaryExpression":
        return (VisitableBuilder<T, ?>) new BinaryExpressionBuilder((BinaryExpression) item);
      case "io.sundr.model." + "Equals":
        return (VisitableBuilder<T, ?>) new EqualsBuilder((Equals) item);
      case "io.sundr.model." + "Enclosed":
        return (VisitableBuilder<T, ?>) new EnclosedBuilder((Enclosed) item);
      case "io.sundr.model." + "PreDecrement":
        return (VisitableBuilder<T, ?>) new PreDecrementBuilder((PreDecrement) item);
      case "io.sundr.model." + "PostDecrement":
        return (VisitableBuilder<T, ?>) new PostDecrementBuilder((PostDecrement) item);
      case "io.sundr.model." + "Lambda":
        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);
      case "io.sundr.model." + "Not":
        return (VisitableBuilder<T, ?>) new NotBuilder((Not) item);
      case "io.sundr.model." + "Assign":
        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);
      case "io.sundr.model." + "This":
        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);
      case "io.sundr.model." + "Negative":
        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);
      case "io.sundr.model." + "LogicalAnd":
        return (VisitableBuilder<T, ?>) new LogicalAndBuilder((LogicalAnd) item);
      case "io.sundr.model." + "PostIncrement":
        return (VisitableBuilder<T, ?>) new PostIncrementBuilder((PostIncrement) item);
      case "io.sundr.model." + "RightUnsignedShift":
        return (VisitableBuilder<T, ?>) new RightUnsignedShiftBuilder((RightUnsignedShift) item);
      case "io.sundr.model." + "Plus":
        return (VisitableBuilder<T, ?>) new PlusBuilder((Plus) item);
      case "io.sundr.model." + "Construct":
        return (VisitableBuilder<T, ?>) new ConstructBuilder((Construct) item);
      case "io.sundr.model." + "Xor":
        return (VisitableBuilder<T, ?>) new XorBuilder((Xor) item);
      case "io.sundr.model." + "PreIncrement":
        return (VisitableBuilder<T, ?>) new PreIncrementBuilder((PreIncrement) item);
      case "io.sundr.model." + "Property":
        return (VisitableBuilder<T, ?>) new PropertyBuilder((Property) item);
      case "io.sundr.model." + "LessThanOrEqual":
        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);
      case "io.sundr.model." + "ContextRef":
        return (VisitableBuilder<T, ?>) new ContextRefBuilder((ContextRef) item);
      case "io.sundr.model." + "Positive":
        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  protected void copyInstance(Ternary instance) {
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withResult(instance.getResult());
      this.withAlternative(instance.getAlternative());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TernaryFluent that = (TernaryFluent) o;
    if (!java.util.Objects.equals(condition, that.condition))
      return false;
    if (!java.util.Objects.equals(result, that.result))
      return false;
    if (!java.util.Objects.equals(alternative, that.alternative))
      return false;
    return true;
  }

  public boolean hasAlternative() {
    return this.alternative != null;
  }

  public boolean hasCondition() {
    return this.condition != null;
  }

  public boolean hasResult() {
    return this.result != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(condition, result, alternative, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (condition != null) {
      sb.append("condition:");
      sb.append(condition + ",");
    }
    if (result != null) {
      sb.append("result:");
      sb.append(result + ",");
    }
    if (alternative != null) {
      sb.append("alternative:");
      sb.append(alternative);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withAlternative(Expression alternative) {
    if (alternative == null) {
      this.alternative = null;
      this._visitables.remove("alternative");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(alternative);
      this._visitables.get("alternative").clear();
      this._visitables.get("alternative").add(builder);
      this.alternative = builder;
      return (A) this;
    }
  }

  public A withCondition(Expression condition) {
    if (condition == null) {
      this.condition = null;
      this._visitables.remove("condition");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(condition);
      this._visitables.get("condition").clear();
      this._visitables.get("condition").add(builder);
      this.condition = builder;
      return (A) this;
    }
  }

  public AssignAlternativeNested<A> withNewAssignAlternative() {
    return new AssignAlternativeNested(null);
  }

  public AssignAlternativeNested<A> withNewAssignAlternativeLike(Assign item) {
    return new AssignAlternativeNested(item);
  }

  public AssignConditionNested<A> withNewAssignCondition() {
    return new AssignConditionNested(null);
  }

  public AssignConditionNested<A> withNewAssignConditionLike(Assign item) {
    return new AssignConditionNested(item);
  }

  public AssignResultNested<A> withNewAssignResult() {
    return new AssignResultNested(null);
  }

  public AssignResultNested<A> withNewAssignResultLike(Assign item) {
    return new AssignResultNested(item);
  }

  public BinaryExpressionAlternativeNested<A> withNewBinaryExpressionAlternative() {
    return new BinaryExpressionAlternativeNested(null);
  }

  public BinaryExpressionAlternativeNested<A> withNewBinaryExpressionAlternativeLike(BinaryExpression item) {
    return new BinaryExpressionAlternativeNested(item);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionCondition() {
    return new BinaryExpressionConditionNested(null);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionConditionLike(BinaryExpression item) {
    return new BinaryExpressionConditionNested(item);
  }

  public BinaryExpressionResultNested<A> withNewBinaryExpressionResult() {
    return new BinaryExpressionResultNested(null);
  }

  public BinaryExpressionResultNested<A> withNewBinaryExpressionResultLike(BinaryExpression item) {
    return new BinaryExpressionResultNested(item);
  }

  public BitwiseAndAlternativeNested<A> withNewBitwiseAndAlternative() {
    return new BitwiseAndAlternativeNested(null);
  }

  public A withNewBitwiseAndAlternative(Object left, Object right) {
    return (A) withAlternative(new BitwiseAnd(left, right));
  }

  public BitwiseAndAlternativeNested<A> withNewBitwiseAndAlternativeLike(BitwiseAnd item) {
    return new BitwiseAndAlternativeNested(item);
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndCondition() {
    return new BitwiseAndConditionNested(null);
  }

  public A withNewBitwiseAndCondition(Object left, Object right) {
    return (A) withCondition(new BitwiseAnd(left, right));
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndConditionLike(BitwiseAnd item) {
    return new BitwiseAndConditionNested(item);
  }

  public BitwiseAndResultNested<A> withNewBitwiseAndResult() {
    return new BitwiseAndResultNested(null);
  }

  public A withNewBitwiseAndResult(Object left, Object right) {
    return (A) withResult(new BitwiseAnd(left, right));
  }

  public BitwiseAndResultNested<A> withNewBitwiseAndResultLike(BitwiseAnd item) {
    return new BitwiseAndResultNested(item);
  }

  public BitwiseOrAlternativeNested<A> withNewBitwiseOrAlternative() {
    return new BitwiseOrAlternativeNested(null);
  }

  public A withNewBitwiseOrAlternative(Object left, Object right) {
    return (A) withAlternative(new BitwiseOr(left, right));
  }

  public BitwiseOrAlternativeNested<A> withNewBitwiseOrAlternativeLike(BitwiseOr item) {
    return new BitwiseOrAlternativeNested(item);
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrCondition() {
    return new BitwiseOrConditionNested(null);
  }

  public A withNewBitwiseOrCondition(Object left, Object right) {
    return (A) withCondition(new BitwiseOr(left, right));
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrConditionLike(BitwiseOr item) {
    return new BitwiseOrConditionNested(item);
  }

  public BitwiseOrResultNested<A> withNewBitwiseOrResult() {
    return new BitwiseOrResultNested(null);
  }

  public A withNewBitwiseOrResult(Object left, Object right) {
    return (A) withResult(new BitwiseOr(left, right));
  }

  public BitwiseOrResultNested<A> withNewBitwiseOrResultLike(BitwiseOr item) {
    return new BitwiseOrResultNested(item);
  }

  public CastAlternativeNested<A> withNewCastAlternative() {
    return new CastAlternativeNested(null);
  }

  public CastAlternativeNested<A> withNewCastAlternativeLike(Cast item) {
    return new CastAlternativeNested(item);
  }

  public CastConditionNested<A> withNewCastCondition() {
    return new CastConditionNested(null);
  }

  public CastConditionNested<A> withNewCastConditionLike(Cast item) {
    return new CastConditionNested(item);
  }

  public CastResultNested<A> withNewCastResult() {
    return new CastResultNested(null);
  }

  public CastResultNested<A> withNewCastResultLike(Cast item) {
    return new CastResultNested(item);
  }

  public ClassRefAlternativeNested<A> withNewClassRefAlternative() {
    return new ClassRefAlternativeNested(null);
  }

  public ClassRefAlternativeNested<A> withNewClassRefAlternativeLike(ClassRef item) {
    return new ClassRefAlternativeNested(item);
  }

  public ClassRefConditionNested<A> withNewClassRefCondition() {
    return new ClassRefConditionNested(null);
  }

  public ClassRefConditionNested<A> withNewClassRefConditionLike(ClassRef item) {
    return new ClassRefConditionNested(item);
  }

  public ClassRefResultNested<A> withNewClassRefResult() {
    return new ClassRefResultNested(null);
  }

  public ClassRefResultNested<A> withNewClassRefResultLike(ClassRef item) {
    return new ClassRefResultNested(item);
  }

  public ConstructAlternativeNested<A> withNewConstructAlternative() {
    return new ConstructAlternativeNested(null);
  }

  public ConstructAlternativeNested<A> withNewConstructAlternativeLike(Construct item) {
    return new ConstructAlternativeNested(item);
  }

  public ConstructConditionNested<A> withNewConstructCondition() {
    return new ConstructConditionNested(null);
  }

  public ConstructConditionNested<A> withNewConstructConditionLike(Construct item) {
    return new ConstructConditionNested(item);
  }

  public ConstructResultNested<A> withNewConstructResult() {
    return new ConstructResultNested(null);
  }

  public ConstructResultNested<A> withNewConstructResultLike(Construct item) {
    return new ConstructResultNested(item);
  }

  public ContextRefAlternativeNested<A> withNewContextRefAlternative() {
    return new ContextRefAlternativeNested(null);
  }

  public A withNewContextRefAlternative(String name) {
    return (A) withAlternative(new ContextRef(name));
  }

  public ContextRefAlternativeNested<A> withNewContextRefAlternativeLike(ContextRef item) {
    return new ContextRefAlternativeNested(item);
  }

  public ContextRefConditionNested<A> withNewContextRefCondition() {
    return new ContextRefConditionNested(null);
  }

  public A withNewContextRefCondition(String name) {
    return (A) withCondition(new ContextRef(name));
  }

  public ContextRefConditionNested<A> withNewContextRefConditionLike(ContextRef item) {
    return new ContextRefConditionNested(item);
  }

  public ContextRefResultNested<A> withNewContextRefResult() {
    return new ContextRefResultNested(null);
  }

  public A withNewContextRefResult(String name) {
    return (A) withResult(new ContextRef(name));
  }

  public ContextRefResultNested<A> withNewContextRefResultLike(ContextRef item) {
    return new ContextRefResultNested(item);
  }

  public DeclareAlternativeNested<A> withNewDeclareAlternative() {
    return new DeclareAlternativeNested(null);
  }

  public A withNewDeclareAlternative(Class type, String name) {
    return (A) withAlternative(new Declare(type, name));
  }

  public A withNewDeclareAlternative(Class type, String name, Object value) {
    return (A) withAlternative(new Declare(type, name, value));
  }

  public DeclareAlternativeNested<A> withNewDeclareAlternativeLike(Declare item) {
    return new DeclareAlternativeNested(item);
  }

  public DeclareConditionNested<A> withNewDeclareCondition() {
    return new DeclareConditionNested(null);
  }

  public A withNewDeclareCondition(Class type, String name) {
    return (A) withCondition(new Declare(type, name));
  }

  public A withNewDeclareCondition(Class type, String name, Object value) {
    return (A) withCondition(new Declare(type, name, value));
  }

  public DeclareConditionNested<A> withNewDeclareConditionLike(Declare item) {
    return new DeclareConditionNested(item);
  }

  public DeclareResultNested<A> withNewDeclareResult() {
    return new DeclareResultNested(null);
  }

  public A withNewDeclareResult(Class type, String name) {
    return (A) withResult(new Declare(type, name));
  }

  public A withNewDeclareResult(Class type, String name, Object value) {
    return (A) withResult(new Declare(type, name, value));
  }

  public DeclareResultNested<A> withNewDeclareResultLike(Declare item) {
    return new DeclareResultNested(item);
  }

  public DivideAlternativeNested<A> withNewDivideAlternative() {
    return new DivideAlternativeNested(null);
  }

  public A withNewDivideAlternative(Object left, Object right) {
    return (A) withAlternative(new Divide(left, right));
  }

  public DivideAlternativeNested<A> withNewDivideAlternativeLike(Divide item) {
    return new DivideAlternativeNested(item);
  }

  public DivideConditionNested<A> withNewDivideCondition() {
    return new DivideConditionNested(null);
  }

  public A withNewDivideCondition(Object left, Object right) {
    return (A) withCondition(new Divide(left, right));
  }

  public DivideConditionNested<A> withNewDivideConditionLike(Divide item) {
    return new DivideConditionNested(item);
  }

  public DivideResultNested<A> withNewDivideResult() {
    return new DivideResultNested(null);
  }

  public A withNewDivideResult(Object left, Object right) {
    return (A) withResult(new Divide(left, right));
  }

  public DivideResultNested<A> withNewDivideResultLike(Divide item) {
    return new DivideResultNested(item);
  }

  public DotClassAlternativeNested<A> withNewDotClassAlternative() {
    return new DotClassAlternativeNested(null);
  }

  public DotClassAlternativeNested<A> withNewDotClassAlternativeLike(DotClass item) {
    return new DotClassAlternativeNested(item);
  }

  public DotClassConditionNested<A> withNewDotClassCondition() {
    return new DotClassConditionNested(null);
  }

  public DotClassConditionNested<A> withNewDotClassConditionLike(DotClass item) {
    return new DotClassConditionNested(item);
  }

  public DotClassResultNested<A> withNewDotClassResult() {
    return new DotClassResultNested(null);
  }

  public DotClassResultNested<A> withNewDotClassResultLike(DotClass item) {
    return new DotClassResultNested(item);
  }

  public EmptyAlternativeNested<A> withNewEmptyAlternative() {
    return new EmptyAlternativeNested(null);
  }

  public EmptyAlternativeNested<A> withNewEmptyAlternativeLike(Empty item) {
    return new EmptyAlternativeNested(item);
  }

  public EmptyConditionNested<A> withNewEmptyCondition() {
    return new EmptyConditionNested(null);
  }

  public EmptyConditionNested<A> withNewEmptyConditionLike(Empty item) {
    return new EmptyConditionNested(item);
  }

  public EmptyResultNested<A> withNewEmptyResult() {
    return new EmptyResultNested(null);
  }

  public EmptyResultNested<A> withNewEmptyResultLike(Empty item) {
    return new EmptyResultNested(item);
  }

  public EnclosedAlternativeNested<A> withNewEnclosedAlternative() {
    return new EnclosedAlternativeNested(null);
  }

  public EnclosedAlternativeNested<A> withNewEnclosedAlternativeLike(Enclosed item) {
    return new EnclosedAlternativeNested(item);
  }

  public EnclosedConditionNested<A> withNewEnclosedCondition() {
    return new EnclosedConditionNested(null);
  }

  public EnclosedConditionNested<A> withNewEnclosedConditionLike(Enclosed item) {
    return new EnclosedConditionNested(item);
  }

  public EnclosedResultNested<A> withNewEnclosedResult() {
    return new EnclosedResultNested(null);
  }

  public EnclosedResultNested<A> withNewEnclosedResultLike(Enclosed item) {
    return new EnclosedResultNested(item);
  }

  public EqualsAlternativeNested<A> withNewEqualsAlternative() {
    return new EqualsAlternativeNested(null);
  }

  public A withNewEqualsAlternative(Object left, Object right) {
    return (A) withAlternative(new Equals(left, right));
  }

  public EqualsAlternativeNested<A> withNewEqualsAlternativeLike(Equals item) {
    return new EqualsAlternativeNested(item);
  }

  public EqualsConditionNested<A> withNewEqualsCondition() {
    return new EqualsConditionNested(null);
  }

  public A withNewEqualsCondition(Object left, Object right) {
    return (A) withCondition(new Equals(left, right));
  }

  public EqualsConditionNested<A> withNewEqualsConditionLike(Equals item) {
    return new EqualsConditionNested(item);
  }

  public EqualsResultNested<A> withNewEqualsResult() {
    return new EqualsResultNested(null);
  }

  public A withNewEqualsResult(Object left, Object right) {
    return (A) withResult(new Equals(left, right));
  }

  public EqualsResultNested<A> withNewEqualsResultLike(Equals item) {
    return new EqualsResultNested(item);
  }

  public GreaterThanAlternativeNested<A> withNewGreaterThanAlternative() {
    return new GreaterThanAlternativeNested(null);
  }

  public A withNewGreaterThanAlternative(Object left, Object right) {
    return (A) withAlternative(new GreaterThan(left, right));
  }

  public GreaterThanAlternativeNested<A> withNewGreaterThanAlternativeLike(GreaterThan item) {
    return new GreaterThanAlternativeNested(item);
  }

  public GreaterThanConditionNested<A> withNewGreaterThanCondition() {
    return new GreaterThanConditionNested(null);
  }

  public A withNewGreaterThanCondition(Object left, Object right) {
    return (A) withCondition(new GreaterThan(left, right));
  }

  public GreaterThanConditionNested<A> withNewGreaterThanConditionLike(GreaterThan item) {
    return new GreaterThanConditionNested(item);
  }

  public GreaterThanOrEqualAlternativeNested<A> withNewGreaterThanOrEqualAlternative() {
    return new GreaterThanOrEqualAlternativeNested(null);
  }

  public A withNewGreaterThanOrEqualAlternative(Object left, Object right) {
    return (A) withAlternative(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualAlternativeNested<A> withNewGreaterThanOrEqualAlternativeLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualAlternativeNested(item);
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualCondition() {
    return new GreaterThanOrEqualConditionNested(null);
  }

  public A withNewGreaterThanOrEqualCondition(Object left, Object right) {
    return (A) withCondition(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualConditionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualConditionNested(item);
  }

  public GreaterThanOrEqualResultNested<A> withNewGreaterThanOrEqualResult() {
    return new GreaterThanOrEqualResultNested(null);
  }

  public A withNewGreaterThanOrEqualResult(Object left, Object right) {
    return (A) withResult(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualResultNested<A> withNewGreaterThanOrEqualResultLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualResultNested(item);
  }

  public GreaterThanResultNested<A> withNewGreaterThanResult() {
    return new GreaterThanResultNested(null);
  }

  public A withNewGreaterThanResult(Object left, Object right) {
    return (A) withResult(new GreaterThan(left, right));
  }

  public GreaterThanResultNested<A> withNewGreaterThanResultLike(GreaterThan item) {
    return new GreaterThanResultNested(item);
  }

  public IndexAlternativeNested<A> withNewIndexAlternative() {
    return new IndexAlternativeNested(null);
  }

  public IndexAlternativeNested<A> withNewIndexAlternativeLike(Index item) {
    return new IndexAlternativeNested(item);
  }

  public IndexConditionNested<A> withNewIndexCondition() {
    return new IndexConditionNested(null);
  }

  public IndexConditionNested<A> withNewIndexConditionLike(Index item) {
    return new IndexConditionNested(item);
  }

  public IndexResultNested<A> withNewIndexResult() {
    return new IndexResultNested(null);
  }

  public IndexResultNested<A> withNewIndexResultLike(Index item) {
    return new IndexResultNested(item);
  }

  public InstanceOfAlternativeNested<A> withNewInstanceOfAlternative() {
    return new InstanceOfAlternativeNested(null);
  }

  public InstanceOfAlternativeNested<A> withNewInstanceOfAlternativeLike(InstanceOf item) {
    return new InstanceOfAlternativeNested(item);
  }

  public InstanceOfConditionNested<A> withNewInstanceOfCondition() {
    return new InstanceOfConditionNested(null);
  }

  public InstanceOfConditionNested<A> withNewInstanceOfConditionLike(InstanceOf item) {
    return new InstanceOfConditionNested(item);
  }

  public InstanceOfResultNested<A> withNewInstanceOfResult() {
    return new InstanceOfResultNested(null);
  }

  public InstanceOfResultNested<A> withNewInstanceOfResultLike(InstanceOf item) {
    return new InstanceOfResultNested(item);
  }

  public InverseAlternativeNested<A> withNewInverseAlternative() {
    return new InverseAlternativeNested(null);
  }

  public InverseAlternativeNested<A> withNewInverseAlternativeLike(Inverse item) {
    return new InverseAlternativeNested(item);
  }

  public InverseConditionNested<A> withNewInverseCondition() {
    return new InverseConditionNested(null);
  }

  public InverseConditionNested<A> withNewInverseConditionLike(Inverse item) {
    return new InverseConditionNested(item);
  }

  public InverseResultNested<A> withNewInverseResult() {
    return new InverseResultNested(null);
  }

  public InverseResultNested<A> withNewInverseResultLike(Inverse item) {
    return new InverseResultNested(item);
  }

  public LambdaAlternativeNested<A> withNewLambdaAlternative() {
    return new LambdaAlternativeNested(null);
  }

  public LambdaAlternativeNested<A> withNewLambdaAlternativeLike(Lambda item) {
    return new LambdaAlternativeNested(item);
  }

  public LambdaConditionNested<A> withNewLambdaCondition() {
    return new LambdaConditionNested(null);
  }

  public LambdaConditionNested<A> withNewLambdaConditionLike(Lambda item) {
    return new LambdaConditionNested(item);
  }

  public LambdaResultNested<A> withNewLambdaResult() {
    return new LambdaResultNested(null);
  }

  public LambdaResultNested<A> withNewLambdaResultLike(Lambda item) {
    return new LambdaResultNested(item);
  }

  public LeftShiftAlternativeNested<A> withNewLeftShiftAlternative() {
    return new LeftShiftAlternativeNested(null);
  }

  public A withNewLeftShiftAlternative(Object left, Object right) {
    return (A) withAlternative(new LeftShift(left, right));
  }

  public LeftShiftAlternativeNested<A> withNewLeftShiftAlternativeLike(LeftShift item) {
    return new LeftShiftAlternativeNested(item);
  }

  public LeftShiftConditionNested<A> withNewLeftShiftCondition() {
    return new LeftShiftConditionNested(null);
  }

  public A withNewLeftShiftCondition(Object left, Object right) {
    return (A) withCondition(new LeftShift(left, right));
  }

  public LeftShiftConditionNested<A> withNewLeftShiftConditionLike(LeftShift item) {
    return new LeftShiftConditionNested(item);
  }

  public LeftShiftResultNested<A> withNewLeftShiftResult() {
    return new LeftShiftResultNested(null);
  }

  public A withNewLeftShiftResult(Object left, Object right) {
    return (A) withResult(new LeftShift(left, right));
  }

  public LeftShiftResultNested<A> withNewLeftShiftResultLike(LeftShift item) {
    return new LeftShiftResultNested(item);
  }

  public LessThanAlternativeNested<A> withNewLessThanAlternative() {
    return new LessThanAlternativeNested(null);
  }

  public A withNewLessThanAlternative(Object left, Object right) {
    return (A) withAlternative(new LessThan(left, right));
  }

  public LessThanAlternativeNested<A> withNewLessThanAlternativeLike(LessThan item) {
    return new LessThanAlternativeNested(item);
  }

  public LessThanConditionNested<A> withNewLessThanCondition() {
    return new LessThanConditionNested(null);
  }

  public A withNewLessThanCondition(Object left, Object right) {
    return (A) withCondition(new LessThan(left, right));
  }

  public LessThanConditionNested<A> withNewLessThanConditionLike(LessThan item) {
    return new LessThanConditionNested(item);
  }

  public LessThanOrEqualAlternativeNested<A> withNewLessThanOrEqualAlternative() {
    return new LessThanOrEqualAlternativeNested(null);
  }

  public A withNewLessThanOrEqualAlternative(Object left, Object right) {
    return (A) withAlternative(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualAlternativeNested<A> withNewLessThanOrEqualAlternativeLike(LessThanOrEqual item) {
    return new LessThanOrEqualAlternativeNested(item);
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualCondition() {
    return new LessThanOrEqualConditionNested(null);
  }

  public A withNewLessThanOrEqualCondition(Object left, Object right) {
    return (A) withCondition(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualConditionLike(LessThanOrEqual item) {
    return new LessThanOrEqualConditionNested(item);
  }

  public LessThanOrEqualResultNested<A> withNewLessThanOrEqualResult() {
    return new LessThanOrEqualResultNested(null);
  }

  public A withNewLessThanOrEqualResult(Object left, Object right) {
    return (A) withResult(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualResultNested<A> withNewLessThanOrEqualResultLike(LessThanOrEqual item) {
    return new LessThanOrEqualResultNested(item);
  }

  public LessThanResultNested<A> withNewLessThanResult() {
    return new LessThanResultNested(null);
  }

  public A withNewLessThanResult(Object left, Object right) {
    return (A) withResult(new LessThan(left, right));
  }

  public LessThanResultNested<A> withNewLessThanResultLike(LessThan item) {
    return new LessThanResultNested(item);
  }

  public LogicalAndAlternativeNested<A> withNewLogicalAndAlternative() {
    return new LogicalAndAlternativeNested(null);
  }

  public A withNewLogicalAndAlternative(Object left, Object right) {
    return (A) withAlternative(new LogicalAnd(left, right));
  }

  public LogicalAndAlternativeNested<A> withNewLogicalAndAlternativeLike(LogicalAnd item) {
    return new LogicalAndAlternativeNested(item);
  }

  public LogicalAndConditionNested<A> withNewLogicalAndCondition() {
    return new LogicalAndConditionNested(null);
  }

  public A withNewLogicalAndCondition(Object left, Object right) {
    return (A) withCondition(new LogicalAnd(left, right));
  }

  public LogicalAndConditionNested<A> withNewLogicalAndConditionLike(LogicalAnd item) {
    return new LogicalAndConditionNested(item);
  }

  public LogicalAndResultNested<A> withNewLogicalAndResult() {
    return new LogicalAndResultNested(null);
  }

  public A withNewLogicalAndResult(Object left, Object right) {
    return (A) withResult(new LogicalAnd(left, right));
  }

  public LogicalAndResultNested<A> withNewLogicalAndResultLike(LogicalAnd item) {
    return new LogicalAndResultNested(item);
  }

  public LogicalOrAlternativeNested<A> withNewLogicalOrAlternative() {
    return new LogicalOrAlternativeNested(null);
  }

  public A withNewLogicalOrAlternative(Object left, Object right) {
    return (A) withAlternative(new LogicalOr(left, right));
  }

  public LogicalOrAlternativeNested<A> withNewLogicalOrAlternativeLike(LogicalOr item) {
    return new LogicalOrAlternativeNested(item);
  }

  public LogicalOrConditionNested<A> withNewLogicalOrCondition() {
    return new LogicalOrConditionNested(null);
  }

  public A withNewLogicalOrCondition(Object left, Object right) {
    return (A) withCondition(new LogicalOr(left, right));
  }

  public LogicalOrConditionNested<A> withNewLogicalOrConditionLike(LogicalOr item) {
    return new LogicalOrConditionNested(item);
  }

  public LogicalOrResultNested<A> withNewLogicalOrResult() {
    return new LogicalOrResultNested(null);
  }

  public A withNewLogicalOrResult(Object left, Object right) {
    return (A) withResult(new LogicalOr(left, right));
  }

  public LogicalOrResultNested<A> withNewLogicalOrResultLike(LogicalOr item) {
    return new LogicalOrResultNested(item);
  }

  public MethodCallAlternativeNested<A> withNewMethodCallAlternative() {
    return new MethodCallAlternativeNested(null);
  }

  public MethodCallAlternativeNested<A> withNewMethodCallAlternativeLike(MethodCall item) {
    return new MethodCallAlternativeNested(item);
  }

  public MethodCallConditionNested<A> withNewMethodCallCondition() {
    return new MethodCallConditionNested(null);
  }

  public MethodCallConditionNested<A> withNewMethodCallConditionLike(MethodCall item) {
    return new MethodCallConditionNested(item);
  }

  public MethodCallResultNested<A> withNewMethodCallResult() {
    return new MethodCallResultNested(null);
  }

  public MethodCallResultNested<A> withNewMethodCallResultLike(MethodCall item) {
    return new MethodCallResultNested(item);
  }

  public MinusAlternativeNested<A> withNewMinusAlternative() {
    return new MinusAlternativeNested(null);
  }

  public A withNewMinusAlternative(Object left, Object right) {
    return (A) withAlternative(new Minus(left, right));
  }

  public MinusAlternativeNested<A> withNewMinusAlternativeLike(Minus item) {
    return new MinusAlternativeNested(item);
  }

  public MinusConditionNested<A> withNewMinusCondition() {
    return new MinusConditionNested(null);
  }

  public A withNewMinusCondition(Object left, Object right) {
    return (A) withCondition(new Minus(left, right));
  }

  public MinusConditionNested<A> withNewMinusConditionLike(Minus item) {
    return new MinusConditionNested(item);
  }

  public MinusResultNested<A> withNewMinusResult() {
    return new MinusResultNested(null);
  }

  public A withNewMinusResult(Object left, Object right) {
    return (A) withResult(new Minus(left, right));
  }

  public MinusResultNested<A> withNewMinusResultLike(Minus item) {
    return new MinusResultNested(item);
  }

  public ModuloAlternativeNested<A> withNewModuloAlternative() {
    return new ModuloAlternativeNested(null);
  }

  public A withNewModuloAlternative(Object left, Object right) {
    return (A) withAlternative(new Modulo(left, right));
  }

  public ModuloAlternativeNested<A> withNewModuloAlternativeLike(Modulo item) {
    return new ModuloAlternativeNested(item);
  }

  public ModuloConditionNested<A> withNewModuloCondition() {
    return new ModuloConditionNested(null);
  }

  public A withNewModuloCondition(Object left, Object right) {
    return (A) withCondition(new Modulo(left, right));
  }

  public ModuloConditionNested<A> withNewModuloConditionLike(Modulo item) {
    return new ModuloConditionNested(item);
  }

  public ModuloResultNested<A> withNewModuloResult() {
    return new ModuloResultNested(null);
  }

  public A withNewModuloResult(Object left, Object right) {
    return (A) withResult(new Modulo(left, right));
  }

  public ModuloResultNested<A> withNewModuloResultLike(Modulo item) {
    return new ModuloResultNested(item);
  }

  public MultiplyAlternativeNested<A> withNewMultiplyAlternative() {
    return new MultiplyAlternativeNested(null);
  }

  public A withNewMultiplyAlternative(Object left, Object right) {
    return (A) withAlternative(new Multiply(left, right));
  }

  public MultiplyAlternativeNested<A> withNewMultiplyAlternativeLike(Multiply item) {
    return new MultiplyAlternativeNested(item);
  }

  public MultiplyConditionNested<A> withNewMultiplyCondition() {
    return new MultiplyConditionNested(null);
  }

  public A withNewMultiplyCondition(Object left, Object right) {
    return (A) withCondition(new Multiply(left, right));
  }

  public MultiplyConditionNested<A> withNewMultiplyConditionLike(Multiply item) {
    return new MultiplyConditionNested(item);
  }

  public MultiplyResultNested<A> withNewMultiplyResult() {
    return new MultiplyResultNested(null);
  }

  public A withNewMultiplyResult(Object left, Object right) {
    return (A) withResult(new Multiply(left, right));
  }

  public MultiplyResultNested<A> withNewMultiplyResultLike(Multiply item) {
    return new MultiplyResultNested(item);
  }

  public NegativeAlternativeNested<A> withNewNegativeAlternative() {
    return new NegativeAlternativeNested(null);
  }

  public NegativeAlternativeNested<A> withNewNegativeAlternativeLike(Negative item) {
    return new NegativeAlternativeNested(item);
  }

  public NegativeConditionNested<A> withNewNegativeCondition() {
    return new NegativeConditionNested(null);
  }

  public NegativeConditionNested<A> withNewNegativeConditionLike(Negative item) {
    return new NegativeConditionNested(item);
  }

  public NegativeResultNested<A> withNewNegativeResult() {
    return new NegativeResultNested(null);
  }

  public NegativeResultNested<A> withNewNegativeResultLike(Negative item) {
    return new NegativeResultNested(item);
  }

  public NewArrayAlternativeNested<A> withNewNewArrayAlternative() {
    return new NewArrayAlternativeNested(null);
  }

  public A withNewNewArrayAlternative(Class type, Integer[] sizes) {
    return (A) withAlternative(new NewArray(type, sizes));
  }

  public NewArrayAlternativeNested<A> withNewNewArrayAlternativeLike(NewArray item) {
    return new NewArrayAlternativeNested(item);
  }

  public NewArrayConditionNested<A> withNewNewArrayCondition() {
    return new NewArrayConditionNested(null);
  }

  public A withNewNewArrayCondition(Class type, Integer[] sizes) {
    return (A) withCondition(new NewArray(type, sizes));
  }

  public NewArrayConditionNested<A> withNewNewArrayConditionLike(NewArray item) {
    return new NewArrayConditionNested(item);
  }

  public NewArrayResultNested<A> withNewNewArrayResult() {
    return new NewArrayResultNested(null);
  }

  public A withNewNewArrayResult(Class type, Integer[] sizes) {
    return (A) withResult(new NewArray(type, sizes));
  }

  public NewArrayResultNested<A> withNewNewArrayResultLike(NewArray item) {
    return new NewArrayResultNested(item);
  }

  public NotAlternativeNested<A> withNewNotAlternative() {
    return new NotAlternativeNested(null);
  }

  public NotAlternativeNested<A> withNewNotAlternativeLike(Not item) {
    return new NotAlternativeNested(item);
  }

  public NotConditionNested<A> withNewNotCondition() {
    return new NotConditionNested(null);
  }

  public NotConditionNested<A> withNewNotConditionLike(Not item) {
    return new NotConditionNested(item);
  }

  public NotEqualsAlternativeNested<A> withNewNotEqualsAlternative() {
    return new NotEqualsAlternativeNested(null);
  }

  public A withNewNotEqualsAlternative(Object left, Object right) {
    return (A) withAlternative(new NotEquals(left, right));
  }

  public NotEqualsAlternativeNested<A> withNewNotEqualsAlternativeLike(NotEquals item) {
    return new NotEqualsAlternativeNested(item);
  }

  public NotEqualsConditionNested<A> withNewNotEqualsCondition() {
    return new NotEqualsConditionNested(null);
  }

  public A withNewNotEqualsCondition(Object left, Object right) {
    return (A) withCondition(new NotEquals(left, right));
  }

  public NotEqualsConditionNested<A> withNewNotEqualsConditionLike(NotEquals item) {
    return new NotEqualsConditionNested(item);
  }

  public NotEqualsResultNested<A> withNewNotEqualsResult() {
    return new NotEqualsResultNested(null);
  }

  public A withNewNotEqualsResult(Object left, Object right) {
    return (A) withResult(new NotEquals(left, right));
  }

  public NotEqualsResultNested<A> withNewNotEqualsResultLike(NotEquals item) {
    return new NotEqualsResultNested(item);
  }

  public NotResultNested<A> withNewNotResult() {
    return new NotResultNested(null);
  }

  public NotResultNested<A> withNewNotResultLike(Not item) {
    return new NotResultNested(item);
  }

  public PlusAlternativeNested<A> withNewPlusAlternative() {
    return new PlusAlternativeNested(null);
  }

  public A withNewPlusAlternative(Object left, Object right) {
    return (A) withAlternative(new Plus(left, right));
  }

  public PlusAlternativeNested<A> withNewPlusAlternativeLike(Plus item) {
    return new PlusAlternativeNested(item);
  }

  public PlusConditionNested<A> withNewPlusCondition() {
    return new PlusConditionNested(null);
  }

  public A withNewPlusCondition(Object left, Object right) {
    return (A) withCondition(new Plus(left, right));
  }

  public PlusConditionNested<A> withNewPlusConditionLike(Plus item) {
    return new PlusConditionNested(item);
  }

  public PlusResultNested<A> withNewPlusResult() {
    return new PlusResultNested(null);
  }

  public A withNewPlusResult(Object left, Object right) {
    return (A) withResult(new Plus(left, right));
  }

  public PlusResultNested<A> withNewPlusResultLike(Plus item) {
    return new PlusResultNested(item);
  }

  public PositiveAlternativeNested<A> withNewPositiveAlternative() {
    return new PositiveAlternativeNested(null);
  }

  public PositiveAlternativeNested<A> withNewPositiveAlternativeLike(Positive item) {
    return new PositiveAlternativeNested(item);
  }

  public PositiveConditionNested<A> withNewPositiveCondition() {
    return new PositiveConditionNested(null);
  }

  public PositiveConditionNested<A> withNewPositiveConditionLike(Positive item) {
    return new PositiveConditionNested(item);
  }

  public PositiveResultNested<A> withNewPositiveResult() {
    return new PositiveResultNested(null);
  }

  public PositiveResultNested<A> withNewPositiveResultLike(Positive item) {
    return new PositiveResultNested(item);
  }

  public PostDecrementAlternativeNested<A> withNewPostDecrementAlternative() {
    return new PostDecrementAlternativeNested(null);
  }

  public PostDecrementAlternativeNested<A> withNewPostDecrementAlternativeLike(PostDecrement item) {
    return new PostDecrementAlternativeNested(item);
  }

  public PostDecrementConditionNested<A> withNewPostDecrementCondition() {
    return new PostDecrementConditionNested(null);
  }

  public PostDecrementConditionNested<A> withNewPostDecrementConditionLike(PostDecrement item) {
    return new PostDecrementConditionNested(item);
  }

  public PostDecrementResultNested<A> withNewPostDecrementResult() {
    return new PostDecrementResultNested(null);
  }

  public PostDecrementResultNested<A> withNewPostDecrementResultLike(PostDecrement item) {
    return new PostDecrementResultNested(item);
  }

  public PostIncrementAlternativeNested<A> withNewPostIncrementAlternative() {
    return new PostIncrementAlternativeNested(null);
  }

  public PostIncrementAlternativeNested<A> withNewPostIncrementAlternativeLike(PostIncrement item) {
    return new PostIncrementAlternativeNested(item);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementCondition() {
    return new PostIncrementConditionNested(null);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementConditionLike(PostIncrement item) {
    return new PostIncrementConditionNested(item);
  }

  public PostIncrementResultNested<A> withNewPostIncrementResult() {
    return new PostIncrementResultNested(null);
  }

  public PostIncrementResultNested<A> withNewPostIncrementResultLike(PostIncrement item) {
    return new PostIncrementResultNested(item);
  }

  public PreDecrementAlternativeNested<A> withNewPreDecrementAlternative() {
    return new PreDecrementAlternativeNested(null);
  }

  public PreDecrementAlternativeNested<A> withNewPreDecrementAlternativeLike(PreDecrement item) {
    return new PreDecrementAlternativeNested(item);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementCondition() {
    return new PreDecrementConditionNested(null);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementConditionLike(PreDecrement item) {
    return new PreDecrementConditionNested(item);
  }

  public PreDecrementResultNested<A> withNewPreDecrementResult() {
    return new PreDecrementResultNested(null);
  }

  public PreDecrementResultNested<A> withNewPreDecrementResultLike(PreDecrement item) {
    return new PreDecrementResultNested(item);
  }

  public PreIncrementAlternativeNested<A> withNewPreIncrementAlternative() {
    return new PreIncrementAlternativeNested(null);
  }

  public PreIncrementAlternativeNested<A> withNewPreIncrementAlternativeLike(PreIncrement item) {
    return new PreIncrementAlternativeNested(item);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementCondition() {
    return new PreIncrementConditionNested(null);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementConditionLike(PreIncrement item) {
    return new PreIncrementConditionNested(item);
  }

  public PreIncrementResultNested<A> withNewPreIncrementResult() {
    return new PreIncrementResultNested(null);
  }

  public PreIncrementResultNested<A> withNewPreIncrementResultLike(PreIncrement item) {
    return new PreIncrementResultNested(item);
  }

  public PropertyAlternativeNested<A> withNewPropertyAlternative() {
    return new PropertyAlternativeNested(null);
  }

  public PropertyAlternativeNested<A> withNewPropertyAlternativeLike(Property item) {
    return new PropertyAlternativeNested(item);
  }

  public PropertyConditionNested<A> withNewPropertyCondition() {
    return new PropertyConditionNested(null);
  }

  public PropertyConditionNested<A> withNewPropertyConditionLike(Property item) {
    return new PropertyConditionNested(item);
  }

  public PropertyRefAlternativeNested<A> withNewPropertyRefAlternative() {
    return new PropertyRefAlternativeNested(null);
  }

  public PropertyRefAlternativeNested<A> withNewPropertyRefAlternativeLike(PropertyRef item) {
    return new PropertyRefAlternativeNested(item);
  }

  public PropertyRefConditionNested<A> withNewPropertyRefCondition() {
    return new PropertyRefConditionNested(null);
  }

  public PropertyRefConditionNested<A> withNewPropertyRefConditionLike(PropertyRef item) {
    return new PropertyRefConditionNested(item);
  }

  public PropertyRefResultNested<A> withNewPropertyRefResult() {
    return new PropertyRefResultNested(null);
  }

  public PropertyRefResultNested<A> withNewPropertyRefResultLike(PropertyRef item) {
    return new PropertyRefResultNested(item);
  }

  public PropertyResultNested<A> withNewPropertyResult() {
    return new PropertyResultNested(null);
  }

  public PropertyResultNested<A> withNewPropertyResultLike(Property item) {
    return new PropertyResultNested(item);
  }

  public RightShiftAlternativeNested<A> withNewRightShiftAlternative() {
    return new RightShiftAlternativeNested(null);
  }

  public A withNewRightShiftAlternative(Object left, Object right) {
    return (A) withAlternative(new RightShift(left, right));
  }

  public RightShiftAlternativeNested<A> withNewRightShiftAlternativeLike(RightShift item) {
    return new RightShiftAlternativeNested(item);
  }

  public RightShiftConditionNested<A> withNewRightShiftCondition() {
    return new RightShiftConditionNested(null);
  }

  public A withNewRightShiftCondition(Object left, Object right) {
    return (A) withCondition(new RightShift(left, right));
  }

  public RightShiftConditionNested<A> withNewRightShiftConditionLike(RightShift item) {
    return new RightShiftConditionNested(item);
  }

  public RightShiftResultNested<A> withNewRightShiftResult() {
    return new RightShiftResultNested(null);
  }

  public A withNewRightShiftResult(Object left, Object right) {
    return (A) withResult(new RightShift(left, right));
  }

  public RightShiftResultNested<A> withNewRightShiftResultLike(RightShift item) {
    return new RightShiftResultNested(item);
  }

  public RightUnsignedShiftAlternativeNested<A> withNewRightUnsignedShiftAlternative() {
    return new RightUnsignedShiftAlternativeNested(null);
  }

  public A withNewRightUnsignedShiftAlternative(Object left, Object right) {
    return (A) withAlternative(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftAlternativeNested<A> withNewRightUnsignedShiftAlternativeLike(RightUnsignedShift item) {
    return new RightUnsignedShiftAlternativeNested(item);
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftCondition() {
    return new RightUnsignedShiftConditionNested(null);
  }

  public A withNewRightUnsignedShiftCondition(Object left, Object right) {
    return (A) withCondition(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftConditionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftConditionNested(item);
  }

  public RightUnsignedShiftResultNested<A> withNewRightUnsignedShiftResult() {
    return new RightUnsignedShiftResultNested(null);
  }

  public A withNewRightUnsignedShiftResult(Object left, Object right) {
    return (A) withResult(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftResultNested<A> withNewRightUnsignedShiftResultLike(RightUnsignedShift item) {
    return new RightUnsignedShiftResultNested(item);
  }

  public SuperAlternativeNested<A> withNewSuperAlternative() {
    return new SuperAlternativeNested(null);
  }

  public SuperAlternativeNested<A> withNewSuperAlternativeLike(Super item) {
    return new SuperAlternativeNested(item);
  }

  public SuperConditionNested<A> withNewSuperCondition() {
    return new SuperConditionNested(null);
  }

  public SuperConditionNested<A> withNewSuperConditionLike(Super item) {
    return new SuperConditionNested(item);
  }

  public SuperResultNested<A> withNewSuperResult() {
    return new SuperResultNested(null);
  }

  public SuperResultNested<A> withNewSuperResultLike(Super item) {
    return new SuperResultNested(item);
  }

  public TernaryAlternativeNested<A> withNewTernaryAlternative() {
    return new TernaryAlternativeNested(null);
  }

  public TernaryAlternativeNested<A> withNewTernaryAlternativeLike(Ternary item) {
    return new TernaryAlternativeNested(item);
  }

  public TernaryConditionNested<A> withNewTernaryCondition() {
    return new TernaryConditionNested(null);
  }

  public TernaryConditionNested<A> withNewTernaryConditionLike(Ternary item) {
    return new TernaryConditionNested(item);
  }

  public TernaryResultNested<A> withNewTernaryResult() {
    return new TernaryResultNested(null);
  }

  public TernaryResultNested<A> withNewTernaryResultLike(Ternary item) {
    return new TernaryResultNested(item);
  }

  public ThisAlternativeNested<A> withNewThisAlternative() {
    return new ThisAlternativeNested(null);
  }

  public ThisAlternativeNested<A> withNewThisAlternativeLike(This item) {
    return new ThisAlternativeNested(item);
  }

  public ThisConditionNested<A> withNewThisCondition() {
    return new ThisConditionNested(null);
  }

  public ThisConditionNested<A> withNewThisConditionLike(This item) {
    return new ThisConditionNested(item);
  }

  public ThisResultNested<A> withNewThisResult() {
    return new ThisResultNested(null);
  }

  public ThisResultNested<A> withNewThisResultLike(This item) {
    return new ThisResultNested(item);
  }

  public ValueRefAlternativeNested<A> withNewValueRefAlternative() {
    return new ValueRefAlternativeNested(null);
  }

  public A withNewValueRefAlternative(Object value) {
    return (A) withAlternative(new ValueRef(value));
  }

  public ValueRefAlternativeNested<A> withNewValueRefAlternativeLike(ValueRef item) {
    return new ValueRefAlternativeNested(item);
  }

  public ValueRefConditionNested<A> withNewValueRefCondition() {
    return new ValueRefConditionNested(null);
  }

  public A withNewValueRefCondition(Object value) {
    return (A) withCondition(new ValueRef(value));
  }

  public ValueRefConditionNested<A> withNewValueRefConditionLike(ValueRef item) {
    return new ValueRefConditionNested(item);
  }

  public ValueRefResultNested<A> withNewValueRefResult() {
    return new ValueRefResultNested(null);
  }

  public A withNewValueRefResult(Object value) {
    return (A) withResult(new ValueRef(value));
  }

  public ValueRefResultNested<A> withNewValueRefResultLike(ValueRef item) {
    return new ValueRefResultNested(item);
  }

  public XorAlternativeNested<A> withNewXorAlternative() {
    return new XorAlternativeNested(null);
  }

  public A withNewXorAlternative(Object left, Object right) {
    return (A) withAlternative(new Xor(left, right));
  }

  public XorAlternativeNested<A> withNewXorAlternativeLike(Xor item) {
    return new XorAlternativeNested(item);
  }

  public XorConditionNested<A> withNewXorCondition() {
    return new XorConditionNested(null);
  }

  public A withNewXorCondition(Object left, Object right) {
    return (A) withCondition(new Xor(left, right));
  }

  public XorConditionNested<A> withNewXorConditionLike(Xor item) {
    return new XorConditionNested(item);
  }

  public XorResultNested<A> withNewXorResult() {
    return new XorResultNested(null);
  }

  public A withNewXorResult(Object left, Object right) {
    return (A) withResult(new Xor(left, right));
  }

  public XorResultNested<A> withNewXorResultLike(Xor item) {
    return new XorResultNested(item);
  }

  public A withResult(Expression result) {
    if (result == null) {
      this.result = null;
      this._visitables.remove("result");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(result);
      this._visitables.get("result").clear();
      this._visitables.get("result").add(builder);
      this.result = builder;
      return (A) this;
    }
  }

  public class AssignAlternativeNested<N> extends AssignFluent<AssignAlternativeNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignAlternativeNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endAssignAlternative() {
      return and();
    }

  }

  public class AssignConditionNested<N> extends AssignFluent<AssignConditionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignConditionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endAssignCondition() {
      return and();
    }

  }

  public class AssignResultNested<N> extends AssignFluent<AssignResultNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignResultNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endAssignResult() {
      return and();
    }

  }

  public class BinaryExpressionAlternativeNested<N> extends BinaryExpressionFluent<BinaryExpressionAlternativeNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionAlternativeNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endBinaryExpressionAlternative() {
      return and();
    }

  }

  public class BinaryExpressionConditionNested<N> extends BinaryExpressionFluent<BinaryExpressionConditionNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionConditionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endBinaryExpressionCondition() {
      return and();
    }

  }

  public class BinaryExpressionResultNested<N> extends BinaryExpressionFluent<BinaryExpressionResultNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionResultNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endBinaryExpressionResult() {
      return and();
    }

  }

  public class BitwiseAndAlternativeNested<N> extends BitwiseAndFluent<BitwiseAndAlternativeNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndAlternativeNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endBitwiseAndAlternative() {
      return and();
    }

  }

  public class BitwiseAndConditionNested<N> extends BitwiseAndFluent<BitwiseAndConditionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndConditionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endBitwiseAndCondition() {
      return and();
    }

  }

  public class BitwiseAndResultNested<N> extends BitwiseAndFluent<BitwiseAndResultNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndResultNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endBitwiseAndResult() {
      return and();
    }

  }

  public class BitwiseOrAlternativeNested<N> extends BitwiseOrFluent<BitwiseOrAlternativeNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrAlternativeNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endBitwiseOrAlternative() {
      return and();
    }

  }

  public class BitwiseOrConditionNested<N> extends BitwiseOrFluent<BitwiseOrConditionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrConditionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endBitwiseOrCondition() {
      return and();
    }

  }

  public class BitwiseOrResultNested<N> extends BitwiseOrFluent<BitwiseOrResultNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrResultNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endBitwiseOrResult() {
      return and();
    }

  }

  public class CastAlternativeNested<N> extends CastFluent<CastAlternativeNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastAlternativeNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endCastAlternative() {
      return and();
    }

  }

  public class CastConditionNested<N> extends CastFluent<CastConditionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastConditionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endCastCondition() {
      return and();
    }

  }

  public class CastResultNested<N> extends CastFluent<CastResultNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastResultNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endCastResult() {
      return and();
    }

  }

  public class ClassRefAlternativeNested<N> extends ClassRefFluent<ClassRefAlternativeNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefAlternativeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endClassRefAlternative() {
      return and();
    }

  }

  public class ClassRefConditionNested<N> extends ClassRefFluent<ClassRefConditionNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefConditionNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endClassRefCondition() {
      return and();
    }

  }

  public class ClassRefResultNested<N> extends ClassRefFluent<ClassRefResultNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefResultNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endClassRefResult() {
      return and();
    }

  }

  public class ConstructAlternativeNested<N> extends ConstructFluent<ConstructAlternativeNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructAlternativeNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endConstructAlternative() {
      return and();
    }

  }

  public class ConstructConditionNested<N> extends ConstructFluent<ConstructConditionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructConditionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endConstructCondition() {
      return and();
    }

  }

  public class ConstructResultNested<N> extends ConstructFluent<ConstructResultNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructResultNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endConstructResult() {
      return and();
    }

  }

  public class ContextRefAlternativeNested<N> extends ContextRefFluent<ContextRefAlternativeNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefAlternativeNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endContextRefAlternative() {
      return and();
    }

  }

  public class ContextRefConditionNested<N> extends ContextRefFluent<ContextRefConditionNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefConditionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endContextRefCondition() {
      return and();
    }

  }

  public class ContextRefResultNested<N> extends ContextRefFluent<ContextRefResultNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefResultNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endContextRefResult() {
      return and();
    }

  }

  public class DeclareAlternativeNested<N> extends DeclareFluent<DeclareAlternativeNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareAlternativeNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endDeclareAlternative() {
      return and();
    }

  }

  public class DeclareConditionNested<N> extends DeclareFluent<DeclareConditionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareConditionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endDeclareCondition() {
      return and();
    }

  }

  public class DeclareResultNested<N> extends DeclareFluent<DeclareResultNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareResultNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endDeclareResult() {
      return and();
    }

  }

  public class DivideAlternativeNested<N> extends DivideFluent<DivideAlternativeNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideAlternativeNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endDivideAlternative() {
      return and();
    }

  }

  public class DivideConditionNested<N> extends DivideFluent<DivideConditionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideConditionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endDivideCondition() {
      return and();
    }

  }

  public class DivideResultNested<N> extends DivideFluent<DivideResultNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideResultNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endDivideResult() {
      return and();
    }

  }

  public class DotClassAlternativeNested<N> extends DotClassFluent<DotClassAlternativeNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassAlternativeNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endDotClassAlternative() {
      return and();
    }

  }

  public class DotClassConditionNested<N> extends DotClassFluent<DotClassConditionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassConditionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endDotClassCondition() {
      return and();
    }

  }

  public class DotClassResultNested<N> extends DotClassFluent<DotClassResultNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassResultNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endDotClassResult() {
      return and();
    }

  }

  public class EmptyAlternativeNested<N> extends EmptyFluent<EmptyAlternativeNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyAlternativeNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endEmptyAlternative() {
      return and();
    }

  }

  public class EmptyConditionNested<N> extends EmptyFluent<EmptyConditionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyConditionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endEmptyCondition() {
      return and();
    }

  }

  public class EmptyResultNested<N> extends EmptyFluent<EmptyResultNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyResultNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endEmptyResult() {
      return and();
    }

  }

  public class EnclosedAlternativeNested<N> extends EnclosedFluent<EnclosedAlternativeNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedAlternativeNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endEnclosedAlternative() {
      return and();
    }

  }

  public class EnclosedConditionNested<N> extends EnclosedFluent<EnclosedConditionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedConditionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endEnclosedCondition() {
      return and();
    }

  }

  public class EnclosedResultNested<N> extends EnclosedFluent<EnclosedResultNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedResultNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endEnclosedResult() {
      return and();
    }

  }

  public class EqualsAlternativeNested<N> extends EqualsFluent<EqualsAlternativeNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsAlternativeNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endEqualsAlternative() {
      return and();
    }

  }

  public class EqualsConditionNested<N> extends EqualsFluent<EqualsConditionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsConditionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endEqualsCondition() {
      return and();
    }

  }

  public class EqualsResultNested<N> extends EqualsFluent<EqualsResultNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsResultNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endEqualsResult() {
      return and();
    }

  }

  public class GreaterThanAlternativeNested<N> extends GreaterThanFluent<GreaterThanAlternativeNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanAlternativeNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endGreaterThanAlternative() {
      return and();
    }

  }

  public class GreaterThanConditionNested<N> extends GreaterThanFluent<GreaterThanConditionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanConditionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanCondition() {
      return and();
    }

  }

  public class GreaterThanOrEqualAlternativeNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualAlternativeNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualAlternativeNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endGreaterThanOrEqualAlternative() {
      return and();
    }

  }

  public class GreaterThanOrEqualConditionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualConditionNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualConditionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanOrEqualCondition() {
      return and();
    }

  }

  public class GreaterThanOrEqualResultNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualResultNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualResultNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endGreaterThanOrEqualResult() {
      return and();
    }

  }

  public class GreaterThanResultNested<N> extends GreaterThanFluent<GreaterThanResultNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanResultNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endGreaterThanResult() {
      return and();
    }

  }

  public class IndexAlternativeNested<N> extends IndexFluent<IndexAlternativeNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexAlternativeNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endIndexAlternative() {
      return and();
    }

  }

  public class IndexConditionNested<N> extends IndexFluent<IndexConditionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexConditionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endIndexCondition() {
      return and();
    }

  }

  public class IndexResultNested<N> extends IndexFluent<IndexResultNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexResultNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endIndexResult() {
      return and();
    }

  }

  public class InstanceOfAlternativeNested<N> extends InstanceOfFluent<InstanceOfAlternativeNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfAlternativeNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endInstanceOfAlternative() {
      return and();
    }

  }

  public class InstanceOfConditionNested<N> extends InstanceOfFluent<InstanceOfConditionNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfConditionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endInstanceOfCondition() {
      return and();
    }

  }

  public class InstanceOfResultNested<N> extends InstanceOfFluent<InstanceOfResultNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfResultNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endInstanceOfResult() {
      return and();
    }

  }

  public class InverseAlternativeNested<N> extends InverseFluent<InverseAlternativeNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseAlternativeNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endInverseAlternative() {
      return and();
    }

  }

  public class InverseConditionNested<N> extends InverseFluent<InverseConditionNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseConditionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endInverseCondition() {
      return and();
    }

  }

  public class InverseResultNested<N> extends InverseFluent<InverseResultNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseResultNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endInverseResult() {
      return and();
    }

  }

  public class LambdaAlternativeNested<N> extends LambdaFluent<LambdaAlternativeNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaAlternativeNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLambdaAlternative() {
      return and();
    }

  }

  public class LambdaConditionNested<N> extends LambdaFluent<LambdaConditionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaConditionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLambdaCondition() {
      return and();
    }

  }

  public class LambdaResultNested<N> extends LambdaFluent<LambdaResultNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaResultNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLambdaResult() {
      return and();
    }

  }

  public class LeftShiftAlternativeNested<N> extends LeftShiftFluent<LeftShiftAlternativeNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftAlternativeNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLeftShiftAlternative() {
      return and();
    }

  }

  public class LeftShiftConditionNested<N> extends LeftShiftFluent<LeftShiftConditionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftConditionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLeftShiftCondition() {
      return and();
    }

  }

  public class LeftShiftResultNested<N> extends LeftShiftFluent<LeftShiftResultNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftResultNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLeftShiftResult() {
      return and();
    }

  }

  public class LessThanAlternativeNested<N> extends LessThanFluent<LessThanAlternativeNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanAlternativeNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLessThanAlternative() {
      return and();
    }

  }

  public class LessThanConditionNested<N> extends LessThanFluent<LessThanConditionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanConditionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLessThanCondition() {
      return and();
    }

  }

  public class LessThanOrEqualAlternativeNested<N> extends LessThanOrEqualFluent<LessThanOrEqualAlternativeNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualAlternativeNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLessThanOrEqualAlternative() {
      return and();
    }

  }

  public class LessThanOrEqualConditionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualConditionNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualConditionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLessThanOrEqualCondition() {
      return and();
    }

  }

  public class LessThanOrEqualResultNested<N> extends LessThanOrEqualFluent<LessThanOrEqualResultNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualResultNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLessThanOrEqualResult() {
      return and();
    }

  }

  public class LessThanResultNested<N> extends LessThanFluent<LessThanResultNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanResultNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLessThanResult() {
      return and();
    }

  }

  public class LogicalAndAlternativeNested<N> extends LogicalAndFluent<LogicalAndAlternativeNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndAlternativeNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLogicalAndAlternative() {
      return and();
    }

  }

  public class LogicalAndConditionNested<N> extends LogicalAndFluent<LogicalAndConditionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndConditionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLogicalAndCondition() {
      return and();
    }

  }

  public class LogicalAndResultNested<N> extends LogicalAndFluent<LogicalAndResultNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndResultNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLogicalAndResult() {
      return and();
    }

  }

  public class LogicalOrAlternativeNested<N> extends LogicalOrFluent<LogicalOrAlternativeNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrAlternativeNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLogicalOrAlternative() {
      return and();
    }

  }

  public class LogicalOrConditionNested<N> extends LogicalOrFluent<LogicalOrConditionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrConditionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLogicalOrCondition() {
      return and();
    }

  }

  public class LogicalOrResultNested<N> extends LogicalOrFluent<LogicalOrResultNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrResultNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLogicalOrResult() {
      return and();
    }

  }

  public class MethodCallAlternativeNested<N> extends MethodCallFluent<MethodCallAlternativeNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallAlternativeNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endMethodCallAlternative() {
      return and();
    }

  }

  public class MethodCallConditionNested<N> extends MethodCallFluent<MethodCallConditionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallConditionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endMethodCallCondition() {
      return and();
    }

  }

  public class MethodCallResultNested<N> extends MethodCallFluent<MethodCallResultNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallResultNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endMethodCallResult() {
      return and();
    }

  }

  public class MinusAlternativeNested<N> extends MinusFluent<MinusAlternativeNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusAlternativeNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endMinusAlternative() {
      return and();
    }

  }

  public class MinusConditionNested<N> extends MinusFluent<MinusConditionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusConditionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endMinusCondition() {
      return and();
    }

  }

  public class MinusResultNested<N> extends MinusFluent<MinusResultNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusResultNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endMinusResult() {
      return and();
    }

  }

  public class ModuloAlternativeNested<N> extends ModuloFluent<ModuloAlternativeNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloAlternativeNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endModuloAlternative() {
      return and();
    }

  }

  public class ModuloConditionNested<N> extends ModuloFluent<ModuloConditionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloConditionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endModuloCondition() {
      return and();
    }

  }

  public class ModuloResultNested<N> extends ModuloFluent<ModuloResultNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloResultNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endModuloResult() {
      return and();
    }

  }

  public class MultiplyAlternativeNested<N> extends MultiplyFluent<MultiplyAlternativeNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyAlternativeNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endMultiplyAlternative() {
      return and();
    }

  }

  public class MultiplyConditionNested<N> extends MultiplyFluent<MultiplyConditionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyConditionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endMultiplyCondition() {
      return and();
    }

  }

  public class MultiplyResultNested<N> extends MultiplyFluent<MultiplyResultNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyResultNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endMultiplyResult() {
      return and();
    }

  }

  public class NegativeAlternativeNested<N> extends NegativeFluent<NegativeAlternativeNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeAlternativeNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNegativeAlternative() {
      return and();
    }

  }

  public class NegativeConditionNested<N> extends NegativeFluent<NegativeConditionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeConditionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNegativeCondition() {
      return and();
    }

  }

  public class NegativeResultNested<N> extends NegativeFluent<NegativeResultNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeResultNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNegativeResult() {
      return and();
    }

  }

  public class NewArrayAlternativeNested<N> extends NewArrayFluent<NewArrayAlternativeNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayAlternativeNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNewArrayAlternative() {
      return and();
    }

  }

  public class NewArrayConditionNested<N> extends NewArrayFluent<NewArrayConditionNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayConditionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNewArrayCondition() {
      return and();
    }

  }

  public class NewArrayResultNested<N> extends NewArrayFluent<NewArrayResultNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayResultNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNewArrayResult() {
      return and();
    }

  }

  public class NotAlternativeNested<N> extends NotFluent<NotAlternativeNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotAlternativeNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNotAlternative() {
      return and();
    }

  }

  public class NotConditionNested<N> extends NotFluent<NotConditionNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotConditionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNotCondition() {
      return and();
    }

  }

  public class NotEqualsAlternativeNested<N> extends NotEqualsFluent<NotEqualsAlternativeNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsAlternativeNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNotEqualsAlternative() {
      return and();
    }

  }

  public class NotEqualsConditionNested<N> extends NotEqualsFluent<NotEqualsConditionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsConditionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNotEqualsCondition() {
      return and();
    }

  }

  public class NotEqualsResultNested<N> extends NotEqualsFluent<NotEqualsResultNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsResultNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNotEqualsResult() {
      return and();
    }

  }

  public class NotResultNested<N> extends NotFluent<NotResultNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotResultNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNotResult() {
      return and();
    }

  }

  public class PlusAlternativeNested<N> extends PlusFluent<PlusAlternativeNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusAlternativeNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPlusAlternative() {
      return and();
    }

  }

  public class PlusConditionNested<N> extends PlusFluent<PlusConditionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusConditionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPlusCondition() {
      return and();
    }

  }

  public class PlusResultNested<N> extends PlusFluent<PlusResultNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusResultNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPlusResult() {
      return and();
    }

  }

  public class PositiveAlternativeNested<N> extends PositiveFluent<PositiveAlternativeNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveAlternativeNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPositiveAlternative() {
      return and();
    }

  }

  public class PositiveConditionNested<N> extends PositiveFluent<PositiveConditionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveConditionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPositiveCondition() {
      return and();
    }

  }

  public class PositiveResultNested<N> extends PositiveFluent<PositiveResultNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveResultNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPositiveResult() {
      return and();
    }

  }

  public class PostDecrementAlternativeNested<N> extends PostDecrementFluent<PostDecrementAlternativeNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementAlternativeNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPostDecrementAlternative() {
      return and();
    }

  }

  public class PostDecrementConditionNested<N> extends PostDecrementFluent<PostDecrementConditionNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementConditionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPostDecrementCondition() {
      return and();
    }

  }

  public class PostDecrementResultNested<N> extends PostDecrementFluent<PostDecrementResultNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementResultNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPostDecrementResult() {
      return and();
    }

  }

  public class PostIncrementAlternativeNested<N> extends PostIncrementFluent<PostIncrementAlternativeNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementAlternativeNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPostIncrementAlternative() {
      return and();
    }

  }

  public class PostIncrementConditionNested<N> extends PostIncrementFluent<PostIncrementConditionNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementConditionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPostIncrementCondition() {
      return and();
    }

  }

  public class PostIncrementResultNested<N> extends PostIncrementFluent<PostIncrementResultNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementResultNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPostIncrementResult() {
      return and();
    }

  }

  public class PreDecrementAlternativeNested<N> extends PreDecrementFluent<PreDecrementAlternativeNested<N>>
      implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementAlternativeNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPreDecrementAlternative() {
      return and();
    }

  }

  public class PreDecrementConditionNested<N> extends PreDecrementFluent<PreDecrementConditionNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementConditionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPreDecrementCondition() {
      return and();
    }

  }

  public class PreDecrementResultNested<N> extends PreDecrementFluent<PreDecrementResultNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementResultNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPreDecrementResult() {
      return and();
    }

  }

  public class PreIncrementAlternativeNested<N> extends PreIncrementFluent<PreIncrementAlternativeNested<N>>
      implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementAlternativeNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPreIncrementAlternative() {
      return and();
    }

  }

  public class PreIncrementConditionNested<N> extends PreIncrementFluent<PreIncrementConditionNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementConditionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPreIncrementCondition() {
      return and();
    }

  }

  public class PreIncrementResultNested<N> extends PreIncrementFluent<PreIncrementResultNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementResultNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPreIncrementResult() {
      return and();
    }

  }

  public class PropertyAlternativeNested<N> extends PropertyFluent<PropertyAlternativeNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyAlternativeNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPropertyAlternative() {
      return and();
    }

  }

  public class PropertyConditionNested<N> extends PropertyFluent<PropertyConditionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyConditionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPropertyCondition() {
      return and();
    }

  }

  public class PropertyRefAlternativeNested<N> extends PropertyRefFluent<PropertyRefAlternativeNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefAlternativeNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPropertyRefAlternative() {
      return and();
    }

  }

  public class PropertyRefConditionNested<N> extends PropertyRefFluent<PropertyRefConditionNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefConditionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPropertyRefCondition() {
      return and();
    }

  }

  public class PropertyRefResultNested<N> extends PropertyRefFluent<PropertyRefResultNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefResultNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPropertyRefResult() {
      return and();
    }

  }

  public class PropertyResultNested<N> extends PropertyFluent<PropertyResultNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyResultNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPropertyResult() {
      return and();
    }

  }

  public class RightShiftAlternativeNested<N> extends RightShiftFluent<RightShiftAlternativeNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftAlternativeNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endRightShiftAlternative() {
      return and();
    }

  }

  public class RightShiftConditionNested<N> extends RightShiftFluent<RightShiftConditionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftConditionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endRightShiftCondition() {
      return and();
    }

  }

  public class RightShiftResultNested<N> extends RightShiftFluent<RightShiftResultNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftResultNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endRightShiftResult() {
      return and();
    }

  }

  public class RightUnsignedShiftAlternativeNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftAlternativeNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftAlternativeNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endRightUnsignedShiftAlternative() {
      return and();
    }

  }

  public class RightUnsignedShiftConditionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftConditionNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftConditionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endRightUnsignedShiftCondition() {
      return and();
    }

  }

  public class RightUnsignedShiftResultNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftResultNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftResultNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endRightUnsignedShiftResult() {
      return and();
    }

  }

  public class SuperAlternativeNested<N> extends SuperFluent<SuperAlternativeNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperAlternativeNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endSuperAlternative() {
      return and();
    }

  }

  public class SuperConditionNested<N> extends SuperFluent<SuperConditionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperConditionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endSuperCondition() {
      return and();
    }

  }

  public class SuperResultNested<N> extends SuperFluent<SuperResultNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperResultNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endSuperResult() {
      return and();
    }

  }

  public class TernaryAlternativeNested<N> extends TernaryFluent<TernaryAlternativeNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryAlternativeNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endTernaryAlternative() {
      return and();
    }

  }

  public class TernaryConditionNested<N> extends TernaryFluent<TernaryConditionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryConditionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endTernaryCondition() {
      return and();
    }

  }

  public class TernaryResultNested<N> extends TernaryFluent<TernaryResultNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryResultNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endTernaryResult() {
      return and();
    }

  }

  public class ThisAlternativeNested<N> extends ThisFluent<ThisAlternativeNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisAlternativeNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endThisAlternative() {
      return and();
    }

  }

  public class ThisConditionNested<N> extends ThisFluent<ThisConditionNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisConditionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endThisCondition() {
      return and();
    }

  }

  public class ThisResultNested<N> extends ThisFluent<ThisResultNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisResultNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endThisResult() {
      return and();
    }

  }

  public class ValueRefAlternativeNested<N> extends ValueRefFluent<ValueRefAlternativeNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefAlternativeNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endValueRefAlternative() {
      return and();
    }

  }

  public class ValueRefConditionNested<N> extends ValueRefFluent<ValueRefConditionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefConditionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endValueRefCondition() {
      return and();
    }

  }

  public class ValueRefResultNested<N> extends ValueRefFluent<ValueRefResultNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefResultNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endValueRefResult() {
      return and();
    }

  }

  public class XorAlternativeNested<N> extends XorFluent<XorAlternativeNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorAlternativeNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endXorAlternative() {
      return and();
    }

  }

  public class XorConditionNested<N> extends XorFluent<XorConditionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorConditionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endXorCondition() {
      return and();
    }

  }

  public class XorResultNested<N> extends XorFluent<XorResultNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorResultNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endXorResult() {
      return and();
    }

  }
}
