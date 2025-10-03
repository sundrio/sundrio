package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class BinaryExpressionFluent<A extends io.sundr.model.BinaryExpressionFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> left;
  private VisitableBuilder<? extends Expression, ?> right;

  public BinaryExpressionFluent() {
  }

  public BinaryExpressionFluent(BinaryExpression instance) {
    this.copyInstance(instance);
  }

  public Expression buildLeft() {
    return this.left != null ? this.left.build() : null;
  }

  public Expression buildRight() {
    return this.right != null ? this.right.build() : null;
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "Multiply":

        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);

      case "NewArray":

        return (VisitableBuilder<T, ?>) new NewArrayBuilder((NewArray) item);

      case "InstanceOf":

        return (VisitableBuilder<T, ?>) new InstanceOfBuilder((InstanceOf) item);

      case "MethodCall":

        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);

      case "ClassRef":

        return (VisitableBuilder<T, ?>) new ClassRefBuilder((ClassRef) item);

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

  protected void copyInstance(BinaryExpression instance) {
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
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
    BinaryExpressionFluent that = (BinaryExpressionFluent) o;
    if (!(Objects.equals(left, that.left))) {
      return false;
    }
    if (!(Objects.equals(right, that.right))) {
      return false;
    }
    return true;
  }

  public boolean hasLeft() {
    return this.left != null;
  }

  public boolean hasRight() {
    return this.right != null;
  }

  public int hashCode() {
    return Objects.hash(left, right);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(left == null)) {
      sb.append("left:");
      sb.append(left);
      sb.append(",");
    }
    if (!(right == null)) {
      sb.append("right:");
      sb.append(right);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withLeft(Expression left) {
    if (left == null) {
      this.left = null;
      this._visitables.remove("left");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(left);
      this._visitables.get("left").clear();
      this._visitables.get("left").add(builder);
      this.left = builder;
      return (A) this;
    }
  }

  public AssignLeftNested<A> withNewAssignLeft() {
    return new AssignLeftNested(null);
  }

  public AssignLeftNested<A> withNewAssignLeftLike(Assign item) {
    return new AssignLeftNested(item);
  }

  public AssignRightNested<A> withNewAssignRight() {
    return new AssignRightNested(null);
  }

  public AssignRightNested<A> withNewAssignRightLike(Assign item) {
    return new AssignRightNested(item);
  }

  public BinaryExpressionLeftNested<A> withNewBinaryExpressionLeft() {
    return new BinaryExpressionLeftNested(null);
  }

  public BinaryExpressionLeftNested<A> withNewBinaryExpressionLeftLike(BinaryExpression item) {
    return new BinaryExpressionLeftNested(item);
  }

  public BinaryExpressionRightNested<A> withNewBinaryExpressionRight() {
    return new BinaryExpressionRightNested(null);
  }

  public BinaryExpressionRightNested<A> withNewBinaryExpressionRightLike(BinaryExpression item) {
    return new BinaryExpressionRightNested(item);
  }

  public BitwiseAndLeftNested<A> withNewBitwiseAndLeft() {
    return new BitwiseAndLeftNested(null);
  }

  public A withNewBitwiseAndLeft(Object left, Object right) {
    return (A) this.withLeft(new BitwiseAnd(left, right));
  }

  public BitwiseAndLeftNested<A> withNewBitwiseAndLeftLike(BitwiseAnd item) {
    return new BitwiseAndLeftNested(item);
  }

  public BitwiseAndRightNested<A> withNewBitwiseAndRight() {
    return new BitwiseAndRightNested(null);
  }

  public A withNewBitwiseAndRight(Object left, Object right) {
    return (A) this.withRight(new BitwiseAnd(left, right));
  }

  public BitwiseAndRightNested<A> withNewBitwiseAndRightLike(BitwiseAnd item) {
    return new BitwiseAndRightNested(item);
  }

  public BitwiseOrLeftNested<A> withNewBitwiseOrLeft() {
    return new BitwiseOrLeftNested(null);
  }

  public A withNewBitwiseOrLeft(Object left, Object right) {
    return (A) this.withLeft(new BitwiseOr(left, right));
  }

  public BitwiseOrLeftNested<A> withNewBitwiseOrLeftLike(BitwiseOr item) {
    return new BitwiseOrLeftNested(item);
  }

  public BitwiseOrRightNested<A> withNewBitwiseOrRight() {
    return new BitwiseOrRightNested(null);
  }

  public A withNewBitwiseOrRight(Object left, Object right) {
    return (A) this.withRight(new BitwiseOr(left, right));
  }

  public BitwiseOrRightNested<A> withNewBitwiseOrRightLike(BitwiseOr item) {
    return new BitwiseOrRightNested(item);
  }

  public CastLeftNested<A> withNewCastLeft() {
    return new CastLeftNested(null);
  }

  public CastLeftNested<A> withNewCastLeftLike(Cast item) {
    return new CastLeftNested(item);
  }

  public CastRightNested<A> withNewCastRight() {
    return new CastRightNested(null);
  }

  public CastRightNested<A> withNewCastRightLike(Cast item) {
    return new CastRightNested(item);
  }

  public ClassRefLeftNested<A> withNewClassRefLeft() {
    return new ClassRefLeftNested(null);
  }

  public ClassRefLeftNested<A> withNewClassRefLeftLike(ClassRef item) {
    return new ClassRefLeftNested(item);
  }

  public ClassRefRightNested<A> withNewClassRefRight() {
    return new ClassRefRightNested(null);
  }

  public ClassRefRightNested<A> withNewClassRefRightLike(ClassRef item) {
    return new ClassRefRightNested(item);
  }

  public ConstructLeftNested<A> withNewConstructLeft() {
    return new ConstructLeftNested(null);
  }

  public ConstructLeftNested<A> withNewConstructLeftLike(Construct item) {
    return new ConstructLeftNested(item);
  }

  public ConstructRightNested<A> withNewConstructRight() {
    return new ConstructRightNested(null);
  }

  public ConstructRightNested<A> withNewConstructRightLike(Construct item) {
    return new ConstructRightNested(item);
  }

  public ContextRefLeftNested<A> withNewContextRefLeft() {
    return new ContextRefLeftNested(null);
  }

  public A withNewContextRefLeft(String name) {
    return (A) this.withLeft(new ContextRef(name));
  }

  public ContextRefLeftNested<A> withNewContextRefLeftLike(ContextRef item) {
    return new ContextRefLeftNested(item);
  }

  public ContextRefRightNested<A> withNewContextRefRight() {
    return new ContextRefRightNested(null);
  }

  public A withNewContextRefRight(String name) {
    return (A) this.withRight(new ContextRef(name));
  }

  public ContextRefRightNested<A> withNewContextRefRightLike(ContextRef item) {
    return new ContextRefRightNested(item);
  }

  public DeclareLeftNested<A> withNewDeclareLeft() {
    return new DeclareLeftNested(null);
  }

  public A withNewDeclareLeft(Class type, String name) {
    return (A) this.withLeft(new Declare(type, name));
  }

  public A withNewDeclareLeft(Class type, String name, Object value) {
    return (A) this.withLeft(new Declare(type, name, value));
  }

  public DeclareLeftNested<A> withNewDeclareLeftLike(Declare item) {
    return new DeclareLeftNested(item);
  }

  public DeclareRightNested<A> withNewDeclareRight() {
    return new DeclareRightNested(null);
  }

  public A withNewDeclareRight(Class type, String name) {
    return (A) this.withRight(new Declare(type, name));
  }

  public A withNewDeclareRight(Class type, String name, Object value) {
    return (A) this.withRight(new Declare(type, name, value));
  }

  public DeclareRightNested<A> withNewDeclareRightLike(Declare item) {
    return new DeclareRightNested(item);
  }

  public DivideLeftNested<A> withNewDivideLeft() {
    return new DivideLeftNested(null);
  }

  public A withNewDivideLeft(Object left, Object right) {
    return (A) this.withLeft(new Divide(left, right));
  }

  public DivideLeftNested<A> withNewDivideLeftLike(Divide item) {
    return new DivideLeftNested(item);
  }

  public DivideRightNested<A> withNewDivideRight() {
    return new DivideRightNested(null);
  }

  public A withNewDivideRight(Object left, Object right) {
    return (A) this.withRight(new Divide(left, right));
  }

  public DivideRightNested<A> withNewDivideRightLike(Divide item) {
    return new DivideRightNested(item);
  }

  public DotClassLeftNested<A> withNewDotClassLeft() {
    return new DotClassLeftNested(null);
  }

  public DotClassLeftNested<A> withNewDotClassLeftLike(DotClass item) {
    return new DotClassLeftNested(item);
  }

  public DotClassRightNested<A> withNewDotClassRight() {
    return new DotClassRightNested(null);
  }

  public DotClassRightNested<A> withNewDotClassRightLike(DotClass item) {
    return new DotClassRightNested(item);
  }

  public EmptyLeftNested<A> withNewEmptyLeft() {
    return new EmptyLeftNested(null);
  }

  public EmptyLeftNested<A> withNewEmptyLeftLike(Empty item) {
    return new EmptyLeftNested(item);
  }

  public EmptyRightNested<A> withNewEmptyRight() {
    return new EmptyRightNested(null);
  }

  public EmptyRightNested<A> withNewEmptyRightLike(Empty item) {
    return new EmptyRightNested(item);
  }

  public EnclosedLeftNested<A> withNewEnclosedLeft() {
    return new EnclosedLeftNested(null);
  }

  public EnclosedLeftNested<A> withNewEnclosedLeftLike(Enclosed item) {
    return new EnclosedLeftNested(item);
  }

  public EnclosedRightNested<A> withNewEnclosedRight() {
    return new EnclosedRightNested(null);
  }

  public EnclosedRightNested<A> withNewEnclosedRightLike(Enclosed item) {
    return new EnclosedRightNested(item);
  }

  public EqualsLeftNested<A> withNewEqualsLeft() {
    return new EqualsLeftNested(null);
  }

  public A withNewEqualsLeft(Object left, Object right) {
    return (A) this.withLeft(new Equals(left, right));
  }

  public EqualsLeftNested<A> withNewEqualsLeftLike(Equals item) {
    return new EqualsLeftNested(item);
  }

  public EqualsRightNested<A> withNewEqualsRight() {
    return new EqualsRightNested(null);
  }

  public A withNewEqualsRight(Object left, Object right) {
    return (A) this.withRight(new Equals(left, right));
  }

  public EqualsRightNested<A> withNewEqualsRightLike(Equals item) {
    return new EqualsRightNested(item);
  }

  public GreaterThanLeftNested<A> withNewGreaterThanLeft() {
    return new GreaterThanLeftNested(null);
  }

  public A withNewGreaterThanLeft(Object left, Object right) {
    return (A) this.withLeft(new GreaterThan(left, right));
  }

  public GreaterThanLeftNested<A> withNewGreaterThanLeftLike(GreaterThan item) {
    return new GreaterThanLeftNested(item);
  }

  public GreaterThanOrEqualLeftNested<A> withNewGreaterThanOrEqualLeft() {
    return new GreaterThanOrEqualLeftNested(null);
  }

  public A withNewGreaterThanOrEqualLeft(Object left, Object right) {
    return (A) this.withLeft(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualLeftNested<A> withNewGreaterThanOrEqualLeftLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualLeftNested(item);
  }

  public GreaterThanOrEqualRightNested<A> withNewGreaterThanOrEqualRight() {
    return new GreaterThanOrEqualRightNested(null);
  }

  public A withNewGreaterThanOrEqualRight(Object left, Object right) {
    return (A) this.withRight(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualRightNested<A> withNewGreaterThanOrEqualRightLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualRightNested(item);
  }

  public GreaterThanRightNested<A> withNewGreaterThanRight() {
    return new GreaterThanRightNested(null);
  }

  public A withNewGreaterThanRight(Object left, Object right) {
    return (A) this.withRight(new GreaterThan(left, right));
  }

  public GreaterThanRightNested<A> withNewGreaterThanRightLike(GreaterThan item) {
    return new GreaterThanRightNested(item);
  }

  public IndexLeftNested<A> withNewIndexLeft() {
    return new IndexLeftNested(null);
  }

  public IndexLeftNested<A> withNewIndexLeftLike(Index item) {
    return new IndexLeftNested(item);
  }

  public IndexRightNested<A> withNewIndexRight() {
    return new IndexRightNested(null);
  }

  public IndexRightNested<A> withNewIndexRightLike(Index item) {
    return new IndexRightNested(item);
  }

  public InstanceOfLeftNested<A> withNewInstanceOfLeft() {
    return new InstanceOfLeftNested(null);
  }

  public InstanceOfLeftNested<A> withNewInstanceOfLeftLike(InstanceOf item) {
    return new InstanceOfLeftNested(item);
  }

  public InstanceOfRightNested<A> withNewInstanceOfRight() {
    return new InstanceOfRightNested(null);
  }

  public InstanceOfRightNested<A> withNewInstanceOfRightLike(InstanceOf item) {
    return new InstanceOfRightNested(item);
  }

  public InverseLeftNested<A> withNewInverseLeft() {
    return new InverseLeftNested(null);
  }

  public InverseLeftNested<A> withNewInverseLeftLike(Inverse item) {
    return new InverseLeftNested(item);
  }

  public InverseRightNested<A> withNewInverseRight() {
    return new InverseRightNested(null);
  }

  public InverseRightNested<A> withNewInverseRightLike(Inverse item) {
    return new InverseRightNested(item);
  }

  public LambdaLeftNested<A> withNewLambdaLeft() {
    return new LambdaLeftNested(null);
  }

  public LambdaLeftNested<A> withNewLambdaLeftLike(Lambda item) {
    return new LambdaLeftNested(item);
  }

  public LambdaRightNested<A> withNewLambdaRight() {
    return new LambdaRightNested(null);
  }

  public LambdaRightNested<A> withNewLambdaRightLike(Lambda item) {
    return new LambdaRightNested(item);
  }

  public LeftShiftLeftNested<A> withNewLeftShiftLeft() {
    return new LeftShiftLeftNested(null);
  }

  public A withNewLeftShiftLeft(Object left, Object right) {
    return (A) this.withLeft(new LeftShift(left, right));
  }

  public LeftShiftLeftNested<A> withNewLeftShiftLeftLike(LeftShift item) {
    return new LeftShiftLeftNested(item);
  }

  public LeftShiftRightNested<A> withNewLeftShiftRight() {
    return new LeftShiftRightNested(null);
  }

  public A withNewLeftShiftRight(Object left, Object right) {
    return (A) this.withRight(new LeftShift(left, right));
  }

  public LeftShiftRightNested<A> withNewLeftShiftRightLike(LeftShift item) {
    return new LeftShiftRightNested(item);
  }

  public LessThanLeftNested<A> withNewLessThanLeft() {
    return new LessThanLeftNested(null);
  }

  public A withNewLessThanLeft(Object left, Object right) {
    return (A) this.withLeft(new LessThan(left, right));
  }

  public LessThanLeftNested<A> withNewLessThanLeftLike(LessThan item) {
    return new LessThanLeftNested(item);
  }

  public LessThanOrEqualLeftNested<A> withNewLessThanOrEqualLeft() {
    return new LessThanOrEqualLeftNested(null);
  }

  public A withNewLessThanOrEqualLeft(Object left, Object right) {
    return (A) this.withLeft(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualLeftNested<A> withNewLessThanOrEqualLeftLike(LessThanOrEqual item) {
    return new LessThanOrEqualLeftNested(item);
  }

  public LessThanOrEqualRightNested<A> withNewLessThanOrEqualRight() {
    return new LessThanOrEqualRightNested(null);
  }

  public A withNewLessThanOrEqualRight(Object left, Object right) {
    return (A) this.withRight(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualRightNested<A> withNewLessThanOrEqualRightLike(LessThanOrEqual item) {
    return new LessThanOrEqualRightNested(item);
  }

  public LessThanRightNested<A> withNewLessThanRight() {
    return new LessThanRightNested(null);
  }

  public A withNewLessThanRight(Object left, Object right) {
    return (A) this.withRight(new LessThan(left, right));
  }

  public LessThanRightNested<A> withNewLessThanRightLike(LessThan item) {
    return new LessThanRightNested(item);
  }

  public LogicalAndLeftNested<A> withNewLogicalAndLeft() {
    return new LogicalAndLeftNested(null);
  }

  public A withNewLogicalAndLeft(Object left, Object right) {
    return (A) this.withLeft(new LogicalAnd(left, right));
  }

  public LogicalAndLeftNested<A> withNewLogicalAndLeftLike(LogicalAnd item) {
    return new LogicalAndLeftNested(item);
  }

  public LogicalAndRightNested<A> withNewLogicalAndRight() {
    return new LogicalAndRightNested(null);
  }

  public A withNewLogicalAndRight(Object left, Object right) {
    return (A) this.withRight(new LogicalAnd(left, right));
  }

  public LogicalAndRightNested<A> withNewLogicalAndRightLike(LogicalAnd item) {
    return new LogicalAndRightNested(item);
  }

  public LogicalOrLeftNested<A> withNewLogicalOrLeft() {
    return new LogicalOrLeftNested(null);
  }

  public A withNewLogicalOrLeft(Object left, Object right) {
    return (A) this.withLeft(new LogicalOr(left, right));
  }

  public LogicalOrLeftNested<A> withNewLogicalOrLeftLike(LogicalOr item) {
    return new LogicalOrLeftNested(item);
  }

  public LogicalOrRightNested<A> withNewLogicalOrRight() {
    return new LogicalOrRightNested(null);
  }

  public A withNewLogicalOrRight(Object left, Object right) {
    return (A) this.withRight(new LogicalOr(left, right));
  }

  public LogicalOrRightNested<A> withNewLogicalOrRightLike(LogicalOr item) {
    return new LogicalOrRightNested(item);
  }

  public MethodCallLeftNested<A> withNewMethodCallLeft() {
    return new MethodCallLeftNested(null);
  }

  public MethodCallLeftNested<A> withNewMethodCallLeftLike(MethodCall item) {
    return new MethodCallLeftNested(item);
  }

  public MethodCallRightNested<A> withNewMethodCallRight() {
    return new MethodCallRightNested(null);
  }

  public MethodCallRightNested<A> withNewMethodCallRightLike(MethodCall item) {
    return new MethodCallRightNested(item);
  }

  public MinusLeftNested<A> withNewMinusLeft() {
    return new MinusLeftNested(null);
  }

  public A withNewMinusLeft(Object left, Object right) {
    return (A) this.withLeft(new Minus(left, right));
  }

  public MinusLeftNested<A> withNewMinusLeftLike(Minus item) {
    return new MinusLeftNested(item);
  }

  public MinusRightNested<A> withNewMinusRight() {
    return new MinusRightNested(null);
  }

  public A withNewMinusRight(Object left, Object right) {
    return (A) this.withRight(new Minus(left, right));
  }

  public MinusRightNested<A> withNewMinusRightLike(Minus item) {
    return new MinusRightNested(item);
  }

  public ModuloLeftNested<A> withNewModuloLeft() {
    return new ModuloLeftNested(null);
  }

  public A withNewModuloLeft(Object left, Object right) {
    return (A) this.withLeft(new Modulo(left, right));
  }

  public ModuloLeftNested<A> withNewModuloLeftLike(Modulo item) {
    return new ModuloLeftNested(item);
  }

  public ModuloRightNested<A> withNewModuloRight() {
    return new ModuloRightNested(null);
  }

  public A withNewModuloRight(Object left, Object right) {
    return (A) this.withRight(new Modulo(left, right));
  }

  public ModuloRightNested<A> withNewModuloRightLike(Modulo item) {
    return new ModuloRightNested(item);
  }

  public MultiplyLeftNested<A> withNewMultiplyLeft() {
    return new MultiplyLeftNested(null);
  }

  public A withNewMultiplyLeft(Object left, Object right) {
    return (A) this.withLeft(new Multiply(left, right));
  }

  public MultiplyLeftNested<A> withNewMultiplyLeftLike(Multiply item) {
    return new MultiplyLeftNested(item);
  }

  public MultiplyRightNested<A> withNewMultiplyRight() {
    return new MultiplyRightNested(null);
  }

  public A withNewMultiplyRight(Object left, Object right) {
    return (A) this.withRight(new Multiply(left, right));
  }

  public MultiplyRightNested<A> withNewMultiplyRightLike(Multiply item) {
    return new MultiplyRightNested(item);
  }

  public NegativeLeftNested<A> withNewNegativeLeft() {
    return new NegativeLeftNested(null);
  }

  public NegativeLeftNested<A> withNewNegativeLeftLike(Negative item) {
    return new NegativeLeftNested(item);
  }

  public NegativeRightNested<A> withNewNegativeRight() {
    return new NegativeRightNested(null);
  }

  public NegativeRightNested<A> withNewNegativeRightLike(Negative item) {
    return new NegativeRightNested(item);
  }

  public NewArrayLeftNested<A> withNewNewArrayLeft() {
    return new NewArrayLeftNested(null);
  }

  public A withNewNewArrayLeft(Class type, Integer[] sizes) {
    return (A) this.withLeft(new NewArray(type, sizes));
  }

  public NewArrayLeftNested<A> withNewNewArrayLeftLike(NewArray item) {
    return new NewArrayLeftNested(item);
  }

  public NewArrayRightNested<A> withNewNewArrayRight() {
    return new NewArrayRightNested(null);
  }

  public A withNewNewArrayRight(Class type, Integer[] sizes) {
    return (A) this.withRight(new NewArray(type, sizes));
  }

  public NewArrayRightNested<A> withNewNewArrayRightLike(NewArray item) {
    return new NewArrayRightNested(item);
  }

  public NotEqualsLeftNested<A> withNewNotEqualsLeft() {
    return new NotEqualsLeftNested(null);
  }

  public A withNewNotEqualsLeft(Object left, Object right) {
    return (A) this.withLeft(new NotEquals(left, right));
  }

  public NotEqualsLeftNested<A> withNewNotEqualsLeftLike(NotEquals item) {
    return new NotEqualsLeftNested(item);
  }

  public NotEqualsRightNested<A> withNewNotEqualsRight() {
    return new NotEqualsRightNested(null);
  }

  public A withNewNotEqualsRight(Object left, Object right) {
    return (A) this.withRight(new NotEquals(left, right));
  }

  public NotEqualsRightNested<A> withNewNotEqualsRightLike(NotEquals item) {
    return new NotEqualsRightNested(item);
  }

  public NotLeftNested<A> withNewNotLeft() {
    return new NotLeftNested(null);
  }

  public NotLeftNested<A> withNewNotLeftLike(Not item) {
    return new NotLeftNested(item);
  }

  public NotRightNested<A> withNewNotRight() {
    return new NotRightNested(null);
  }

  public NotRightNested<A> withNewNotRightLike(Not item) {
    return new NotRightNested(item);
  }

  public PlusLeftNested<A> withNewPlusLeft() {
    return new PlusLeftNested(null);
  }

  public A withNewPlusLeft(Object left, Object right) {
    return (A) this.withLeft(new Plus(left, right));
  }

  public PlusLeftNested<A> withNewPlusLeftLike(Plus item) {
    return new PlusLeftNested(item);
  }

  public PlusRightNested<A> withNewPlusRight() {
    return new PlusRightNested(null);
  }

  public A withNewPlusRight(Object left, Object right) {
    return (A) this.withRight(new Plus(left, right));
  }

  public PlusRightNested<A> withNewPlusRightLike(Plus item) {
    return new PlusRightNested(item);
  }

  public PositiveLeftNested<A> withNewPositiveLeft() {
    return new PositiveLeftNested(null);
  }

  public PositiveLeftNested<A> withNewPositiveLeftLike(Positive item) {
    return new PositiveLeftNested(item);
  }

  public PositiveRightNested<A> withNewPositiveRight() {
    return new PositiveRightNested(null);
  }

  public PositiveRightNested<A> withNewPositiveRightLike(Positive item) {
    return new PositiveRightNested(item);
  }

  public PostDecrementLeftNested<A> withNewPostDecrementLeft() {
    return new PostDecrementLeftNested(null);
  }

  public PostDecrementLeftNested<A> withNewPostDecrementLeftLike(PostDecrement item) {
    return new PostDecrementLeftNested(item);
  }

  public PostDecrementRightNested<A> withNewPostDecrementRight() {
    return new PostDecrementRightNested(null);
  }

  public PostDecrementRightNested<A> withNewPostDecrementRightLike(PostDecrement item) {
    return new PostDecrementRightNested(item);
  }

  public PostIncrementLeftNested<A> withNewPostIncrementLeft() {
    return new PostIncrementLeftNested(null);
  }

  public PostIncrementLeftNested<A> withNewPostIncrementLeftLike(PostIncrement item) {
    return new PostIncrementLeftNested(item);
  }

  public PostIncrementRightNested<A> withNewPostIncrementRight() {
    return new PostIncrementRightNested(null);
  }

  public PostIncrementRightNested<A> withNewPostIncrementRightLike(PostIncrement item) {
    return new PostIncrementRightNested(item);
  }

  public PreDecrementLeftNested<A> withNewPreDecrementLeft() {
    return new PreDecrementLeftNested(null);
  }

  public PreDecrementLeftNested<A> withNewPreDecrementLeftLike(PreDecrement item) {
    return new PreDecrementLeftNested(item);
  }

  public PreDecrementRightNested<A> withNewPreDecrementRight() {
    return new PreDecrementRightNested(null);
  }

  public PreDecrementRightNested<A> withNewPreDecrementRightLike(PreDecrement item) {
    return new PreDecrementRightNested(item);
  }

  public PreIncrementLeftNested<A> withNewPreIncrementLeft() {
    return new PreIncrementLeftNested(null);
  }

  public PreIncrementLeftNested<A> withNewPreIncrementLeftLike(PreIncrement item) {
    return new PreIncrementLeftNested(item);
  }

  public PreIncrementRightNested<A> withNewPreIncrementRight() {
    return new PreIncrementRightNested(null);
  }

  public PreIncrementRightNested<A> withNewPreIncrementRightLike(PreIncrement item) {
    return new PreIncrementRightNested(item);
  }

  public PropertyLeftNested<A> withNewPropertyLeft() {
    return new PropertyLeftNested(null);
  }

  public PropertyLeftNested<A> withNewPropertyLeftLike(Property item) {
    return new PropertyLeftNested(item);
  }

  public PropertyRefLeftNested<A> withNewPropertyRefLeft() {
    return new PropertyRefLeftNested(null);
  }

  public PropertyRefLeftNested<A> withNewPropertyRefLeftLike(PropertyRef item) {
    return new PropertyRefLeftNested(item);
  }

  public PropertyRefRightNested<A> withNewPropertyRefRight() {
    return new PropertyRefRightNested(null);
  }

  public PropertyRefRightNested<A> withNewPropertyRefRightLike(PropertyRef item) {
    return new PropertyRefRightNested(item);
  }

  public PropertyRightNested<A> withNewPropertyRight() {
    return new PropertyRightNested(null);
  }

  public PropertyRightNested<A> withNewPropertyRightLike(Property item) {
    return new PropertyRightNested(item);
  }

  public RightShiftLeftNested<A> withNewRightShiftLeft() {
    return new RightShiftLeftNested(null);
  }

  public A withNewRightShiftLeft(Object left, Object right) {
    return (A) this.withLeft(new RightShift(left, right));
  }

  public RightShiftLeftNested<A> withNewRightShiftLeftLike(RightShift item) {
    return new RightShiftLeftNested(item);
  }

  public RightShiftRightNested<A> withNewRightShiftRight() {
    return new RightShiftRightNested(null);
  }

  public A withNewRightShiftRight(Object left, Object right) {
    return (A) this.withRight(new RightShift(left, right));
  }

  public RightShiftRightNested<A> withNewRightShiftRightLike(RightShift item) {
    return new RightShiftRightNested(item);
  }

  public RightUnsignedShiftLeftNested<A> withNewRightUnsignedShiftLeft() {
    return new RightUnsignedShiftLeftNested(null);
  }

  public A withNewRightUnsignedShiftLeft(Object left, Object right) {
    return (A) this.withLeft(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftLeftNested<A> withNewRightUnsignedShiftLeftLike(RightUnsignedShift item) {
    return new RightUnsignedShiftLeftNested(item);
  }

  public RightUnsignedShiftRightNested<A> withNewRightUnsignedShiftRight() {
    return new RightUnsignedShiftRightNested(null);
  }

  public A withNewRightUnsignedShiftRight(Object left, Object right) {
    return (A) this.withRight(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftRightNested<A> withNewRightUnsignedShiftRightLike(RightUnsignedShift item) {
    return new RightUnsignedShiftRightNested(item);
  }

  public SuperLeftNested<A> withNewSuperLeft() {
    return new SuperLeftNested(null);
  }

  public SuperLeftNested<A> withNewSuperLeftLike(Super item) {
    return new SuperLeftNested(item);
  }

  public SuperRightNested<A> withNewSuperRight() {
    return new SuperRightNested(null);
  }

  public SuperRightNested<A> withNewSuperRightLike(Super item) {
    return new SuperRightNested(item);
  }

  public TernaryLeftNested<A> withNewTernaryLeft() {
    return new TernaryLeftNested(null);
  }

  public TernaryLeftNested<A> withNewTernaryLeftLike(Ternary item) {
    return new TernaryLeftNested(item);
  }

  public TernaryRightNested<A> withNewTernaryRight() {
    return new TernaryRightNested(null);
  }

  public TernaryRightNested<A> withNewTernaryRightLike(Ternary item) {
    return new TernaryRightNested(item);
  }

  public ThisLeftNested<A> withNewThisLeft() {
    return new ThisLeftNested(null);
  }

  public ThisLeftNested<A> withNewThisLeftLike(This item) {
    return new ThisLeftNested(item);
  }

  public ThisRightNested<A> withNewThisRight() {
    return new ThisRightNested(null);
  }

  public ThisRightNested<A> withNewThisRightLike(This item) {
    return new ThisRightNested(item);
  }

  public ValueRefLeftNested<A> withNewValueRefLeft() {
    return new ValueRefLeftNested(null);
  }

  public A withNewValueRefLeft(Object value) {
    return (A) this.withLeft(new ValueRef(value));
  }

  public ValueRefLeftNested<A> withNewValueRefLeftLike(ValueRef item) {
    return new ValueRefLeftNested(item);
  }

  public ValueRefRightNested<A> withNewValueRefRight() {
    return new ValueRefRightNested(null);
  }

  public A withNewValueRefRight(Object value) {
    return (A) this.withRight(new ValueRef(value));
  }

  public ValueRefRightNested<A> withNewValueRefRightLike(ValueRef item) {
    return new ValueRefRightNested(item);
  }

  public XorLeftNested<A> withNewXorLeft() {
    return new XorLeftNested(null);
  }

  public A withNewXorLeft(Object left, Object right) {
    return (A) this.withLeft(new Xor(left, right));
  }

  public XorLeftNested<A> withNewXorLeftLike(Xor item) {
    return new XorLeftNested(item);
  }

  public XorRightNested<A> withNewXorRight() {
    return new XorRightNested(null);
  }

  public A withNewXorRight(Object left, Object right) {
    return (A) this.withRight(new Xor(left, right));
  }

  public XorRightNested<A> withNewXorRightLike(Xor item) {
    return new XorRightNested(item);
  }

  public A withRight(Expression right) {
    if (right == null) {
      this.right = null;
      this._visitables.remove("right");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(right);
      this._visitables.get("right").clear();
      this._visitables.get("right").add(builder);
      this.right = builder;
      return (A) this;
    }
  }

  public class AssignLeftNested<N> extends AssignFluent<AssignLeftNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignLeftNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endAssignLeft() {
      return and();
    }

  }

  public class AssignRightNested<N> extends AssignFluent<AssignRightNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignRightNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endAssignRight() {
      return and();
    }

  }

  public class BinaryExpressionLeftNested<N> extends BinaryExpressionFluent<BinaryExpressionLeftNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionLeftNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endBinaryExpressionLeft() {
      return and();
    }

  }

  public class BinaryExpressionRightNested<N> extends BinaryExpressionFluent<BinaryExpressionRightNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionRightNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endBinaryExpressionRight() {
      return and();
    }

  }

  public class BitwiseAndLeftNested<N> extends BitwiseAndFluent<BitwiseAndLeftNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndLeftNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endBitwiseAndLeft() {
      return and();
    }

  }

  public class BitwiseAndRightNested<N> extends BitwiseAndFluent<BitwiseAndRightNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndRightNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endBitwiseAndRight() {
      return and();
    }

  }

  public class BitwiseOrLeftNested<N> extends BitwiseOrFluent<BitwiseOrLeftNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrLeftNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endBitwiseOrLeft() {
      return and();
    }

  }

  public class BitwiseOrRightNested<N> extends BitwiseOrFluent<BitwiseOrRightNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrRightNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endBitwiseOrRight() {
      return and();
    }

  }

  public class CastLeftNested<N> extends CastFluent<CastLeftNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastLeftNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endCastLeft() {
      return and();
    }

  }

  public class CastRightNested<N> extends CastFluent<CastRightNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastRightNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endCastRight() {
      return and();
    }

  }

  public class ClassRefLeftNested<N> extends ClassRefFluent<ClassRefLeftNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefLeftNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endClassRefLeft() {
      return and();
    }

  }

  public class ClassRefRightNested<N> extends ClassRefFluent<ClassRefRightNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefRightNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endClassRefRight() {
      return and();
    }

  }

  public class ConstructLeftNested<N> extends ConstructFluent<ConstructLeftNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructLeftNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endConstructLeft() {
      return and();
    }

  }

  public class ConstructRightNested<N> extends ConstructFluent<ConstructRightNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructRightNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endConstructRight() {
      return and();
    }

  }

  public class ContextRefLeftNested<N> extends ContextRefFluent<ContextRefLeftNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefLeftNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endContextRefLeft() {
      return and();
    }

  }

  public class ContextRefRightNested<N> extends ContextRefFluent<ContextRefRightNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefRightNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endContextRefRight() {
      return and();
    }

  }

  public class DeclareLeftNested<N> extends DeclareFluent<DeclareLeftNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareLeftNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endDeclareLeft() {
      return and();
    }

  }

  public class DeclareRightNested<N> extends DeclareFluent<DeclareRightNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareRightNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endDeclareRight() {
      return and();
    }

  }

  public class DivideLeftNested<N> extends DivideFluent<DivideLeftNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideLeftNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endDivideLeft() {
      return and();
    }

  }

  public class DivideRightNested<N> extends DivideFluent<DivideRightNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideRightNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endDivideRight() {
      return and();
    }

  }

  public class DotClassLeftNested<N> extends DotClassFluent<DotClassLeftNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassLeftNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endDotClassLeft() {
      return and();
    }

  }

  public class DotClassRightNested<N> extends DotClassFluent<DotClassRightNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassRightNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endDotClassRight() {
      return and();
    }

  }

  public class EmptyLeftNested<N> extends EmptyFluent<EmptyLeftNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyLeftNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endEmptyLeft() {
      return and();
    }

  }

  public class EmptyRightNested<N> extends EmptyFluent<EmptyRightNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyRightNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endEmptyRight() {
      return and();
    }

  }

  public class EnclosedLeftNested<N> extends EnclosedFluent<EnclosedLeftNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedLeftNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endEnclosedLeft() {
      return and();
    }

  }

  public class EnclosedRightNested<N> extends EnclosedFluent<EnclosedRightNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedRightNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endEnclosedRight() {
      return and();
    }

  }

  public class EqualsLeftNested<N> extends EqualsFluent<EqualsLeftNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsLeftNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endEqualsLeft() {
      return and();
    }

  }

  public class EqualsRightNested<N> extends EqualsFluent<EqualsRightNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsRightNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endEqualsRight() {
      return and();
    }

  }

  public class GreaterThanLeftNested<N> extends GreaterThanFluent<GreaterThanLeftNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanLeftNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endGreaterThanLeft() {
      return and();
    }

  }

  public class GreaterThanOrEqualLeftNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualLeftNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualLeftNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endGreaterThanOrEqualLeft() {
      return and();
    }

  }

  public class GreaterThanOrEqualRightNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualRightNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualRightNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endGreaterThanOrEqualRight() {
      return and();
    }

  }

  public class GreaterThanRightNested<N> extends GreaterThanFluent<GreaterThanRightNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanRightNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endGreaterThanRight() {
      return and();
    }

  }

  public class IndexLeftNested<N> extends IndexFluent<IndexLeftNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexLeftNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endIndexLeft() {
      return and();
    }

  }

  public class IndexRightNested<N> extends IndexFluent<IndexRightNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexRightNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endIndexRight() {
      return and();
    }

  }

  public class InstanceOfLeftNested<N> extends InstanceOfFluent<InstanceOfLeftNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfLeftNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endInstanceOfLeft() {
      return and();
    }

  }

  public class InstanceOfRightNested<N> extends InstanceOfFluent<InstanceOfRightNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfRightNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endInstanceOfRight() {
      return and();
    }

  }

  public class InverseLeftNested<N> extends InverseFluent<InverseLeftNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseLeftNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endInverseLeft() {
      return and();
    }

  }

  public class InverseRightNested<N> extends InverseFluent<InverseRightNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseRightNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endInverseRight() {
      return and();
    }

  }

  public class LambdaLeftNested<N> extends LambdaFluent<LambdaLeftNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaLeftNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLambdaLeft() {
      return and();
    }

  }

  public class LambdaRightNested<N> extends LambdaFluent<LambdaRightNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaRightNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLambdaRight() {
      return and();
    }

  }

  public class LeftShiftLeftNested<N> extends LeftShiftFluent<LeftShiftLeftNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftLeftNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLeftShiftLeft() {
      return and();
    }

  }

  public class LeftShiftRightNested<N> extends LeftShiftFluent<LeftShiftRightNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftRightNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLeftShiftRight() {
      return and();
    }

  }

  public class LessThanLeftNested<N> extends LessThanFluent<LessThanLeftNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanLeftNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLessThanLeft() {
      return and();
    }

  }

  public class LessThanOrEqualLeftNested<N> extends LessThanOrEqualFluent<LessThanOrEqualLeftNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualLeftNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLessThanOrEqualLeft() {
      return and();
    }

  }

  public class LessThanOrEqualRightNested<N> extends LessThanOrEqualFluent<LessThanOrEqualRightNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualRightNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLessThanOrEqualRight() {
      return and();
    }

  }

  public class LessThanRightNested<N> extends LessThanFluent<LessThanRightNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanRightNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLessThanRight() {
      return and();
    }

  }

  public class LogicalAndLeftNested<N> extends LogicalAndFluent<LogicalAndLeftNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndLeftNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLogicalAndLeft() {
      return and();
    }

  }

  public class LogicalAndRightNested<N> extends LogicalAndFluent<LogicalAndRightNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndRightNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLogicalAndRight() {
      return and();
    }

  }

  public class LogicalOrLeftNested<N> extends LogicalOrFluent<LogicalOrLeftNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrLeftNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endLogicalOrLeft() {
      return and();
    }

  }

  public class LogicalOrRightNested<N> extends LogicalOrFluent<LogicalOrRightNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrRightNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endLogicalOrRight() {
      return and();
    }

  }

  public class MethodCallLeftNested<N> extends MethodCallFluent<MethodCallLeftNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallLeftNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endMethodCallLeft() {
      return and();
    }

  }

  public class MethodCallRightNested<N> extends MethodCallFluent<MethodCallRightNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallRightNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endMethodCallRight() {
      return and();
    }

  }

  public class MinusLeftNested<N> extends MinusFluent<MinusLeftNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusLeftNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endMinusLeft() {
      return and();
    }

  }

  public class MinusRightNested<N> extends MinusFluent<MinusRightNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusRightNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endMinusRight() {
      return and();
    }

  }

  public class ModuloLeftNested<N> extends ModuloFluent<ModuloLeftNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloLeftNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endModuloLeft() {
      return and();
    }

  }

  public class ModuloRightNested<N> extends ModuloFluent<ModuloRightNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloRightNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endModuloRight() {
      return and();
    }

  }

  public class MultiplyLeftNested<N> extends MultiplyFluent<MultiplyLeftNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyLeftNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endMultiplyLeft() {
      return and();
    }

  }

  public class MultiplyRightNested<N> extends MultiplyFluent<MultiplyRightNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyRightNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endMultiplyRight() {
      return and();
    }

  }

  public class NegativeLeftNested<N> extends NegativeFluent<NegativeLeftNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeLeftNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNegativeLeft() {
      return and();
    }

  }

  public class NegativeRightNested<N> extends NegativeFluent<NegativeRightNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeRightNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNegativeRight() {
      return and();
    }

  }

  public class NewArrayLeftNested<N> extends NewArrayFluent<NewArrayLeftNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayLeftNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNewArrayLeft() {
      return and();
    }

  }

  public class NewArrayRightNested<N> extends NewArrayFluent<NewArrayRightNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayRightNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNewArrayRight() {
      return and();
    }

  }

  public class NotEqualsLeftNested<N> extends NotEqualsFluent<NotEqualsLeftNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsLeftNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNotEqualsLeft() {
      return and();
    }

  }

  public class NotEqualsRightNested<N> extends NotEqualsFluent<NotEqualsRightNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsRightNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNotEqualsRight() {
      return and();
    }

  }

  public class NotLeftNested<N> extends NotFluent<NotLeftNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotLeftNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endNotLeft() {
      return and();
    }

  }

  public class NotRightNested<N> extends NotFluent<NotRightNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotRightNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endNotRight() {
      return and();
    }

  }

  public class PlusLeftNested<N> extends PlusFluent<PlusLeftNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusLeftNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPlusLeft() {
      return and();
    }

  }

  public class PlusRightNested<N> extends PlusFluent<PlusRightNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusRightNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPlusRight() {
      return and();
    }

  }

  public class PositiveLeftNested<N> extends PositiveFluent<PositiveLeftNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveLeftNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPositiveLeft() {
      return and();
    }

  }

  public class PositiveRightNested<N> extends PositiveFluent<PositiveRightNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveRightNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPositiveRight() {
      return and();
    }

  }

  public class PostDecrementLeftNested<N> extends PostDecrementFluent<PostDecrementLeftNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementLeftNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPostDecrementLeft() {
      return and();
    }

  }

  public class PostDecrementRightNested<N> extends PostDecrementFluent<PostDecrementRightNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementRightNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPostDecrementRight() {
      return and();
    }

  }

  public class PostIncrementLeftNested<N> extends PostIncrementFluent<PostIncrementLeftNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementLeftNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPostIncrementLeft() {
      return and();
    }

  }

  public class PostIncrementRightNested<N> extends PostIncrementFluent<PostIncrementRightNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementRightNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPostIncrementRight() {
      return and();
    }

  }

  public class PreDecrementLeftNested<N> extends PreDecrementFluent<PreDecrementLeftNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementLeftNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPreDecrementLeft() {
      return and();
    }

  }

  public class PreDecrementRightNested<N> extends PreDecrementFluent<PreDecrementRightNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementRightNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPreDecrementRight() {
      return and();
    }

  }

  public class PreIncrementLeftNested<N> extends PreIncrementFluent<PreIncrementLeftNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementLeftNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPreIncrementLeft() {
      return and();
    }

  }

  public class PreIncrementRightNested<N> extends PreIncrementFluent<PreIncrementRightNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementRightNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPreIncrementRight() {
      return and();
    }

  }

  public class PropertyLeftNested<N> extends PropertyFluent<PropertyLeftNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyLeftNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPropertyLeft() {
      return and();
    }

  }

  public class PropertyRefLeftNested<N> extends PropertyRefFluent<PropertyRefLeftNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefLeftNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endPropertyRefLeft() {
      return and();
    }

  }

  public class PropertyRefRightNested<N> extends PropertyRefFluent<PropertyRefRightNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefRightNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPropertyRefRight() {
      return and();
    }

  }

  public class PropertyRightNested<N> extends PropertyFluent<PropertyRightNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyRightNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endPropertyRight() {
      return and();
    }

  }

  public class RightShiftLeftNested<N> extends RightShiftFluent<RightShiftLeftNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftLeftNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endRightShiftLeft() {
      return and();
    }

  }

  public class RightShiftRightNested<N> extends RightShiftFluent<RightShiftRightNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftRightNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endRightShiftRight() {
      return and();
    }

  }

  public class RightUnsignedShiftLeftNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftLeftNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftLeftNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endRightUnsignedShiftLeft() {
      return and();
    }

  }

  public class RightUnsignedShiftRightNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftRightNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftRightNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endRightUnsignedShiftRight() {
      return and();
    }

  }

  public class SuperLeftNested<N> extends SuperFluent<SuperLeftNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperLeftNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endSuperLeft() {
      return and();
    }

  }

  public class SuperRightNested<N> extends SuperFluent<SuperRightNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperRightNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endSuperRight() {
      return and();
    }

  }

  public class TernaryLeftNested<N> extends TernaryFluent<TernaryLeftNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryLeftNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endTernaryLeft() {
      return and();
    }

  }

  public class TernaryRightNested<N> extends TernaryFluent<TernaryRightNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryRightNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endTernaryRight() {
      return and();
    }

  }

  public class ThisLeftNested<N> extends ThisFluent<ThisLeftNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisLeftNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endThisLeft() {
      return and();
    }

  }

  public class ThisRightNested<N> extends ThisFluent<ThisRightNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisRightNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endThisRight() {
      return and();
    }

  }

  public class ValueRefLeftNested<N> extends ValueRefFluent<ValueRefLeftNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefLeftNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endValueRefLeft() {
      return and();
    }

  }

  public class ValueRefRightNested<N> extends ValueRefFluent<ValueRefRightNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefRightNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endValueRefRight() {
      return and();
    }

  }

  public class XorLeftNested<N> extends XorFluent<XorLeftNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorLeftNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withLeft(builder.build());
    }

    public N endXorLeft() {
      return and();
    }

  }

  public class XorRightNested<N> extends XorFluent<XorRightNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorRightNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) BinaryExpressionFluent.this.withRight(builder.build());
    }

    public N endXorRight() {
      return and();
    }

  }
}
