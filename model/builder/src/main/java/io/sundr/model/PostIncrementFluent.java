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
public class PostIncrementFluent<A extends PostIncrementFluent<A>> extends BaseFluent<A> {
  public PostIncrementFluent() {
  }

  public PostIncrementFluent(PostIncrement instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> expression;

  protected void copyInstance(PostIncrement instance) {
    if (instance != null) {
      this.withExpression(instance.getExpression());
    }
  }

  public Expression buildExpression() {
    return this.expression != null ? this.expression.build() : null;
  }

  public A withExpression(Expression expression) {
    if (expression == null) {
      this.expression = null;
      this._visitables.remove("expression");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(expression);
      ;
      this._visitables.get("expression").clear();
      this._visitables.get("expression").add(builder);
      this.expression = builder;
      return (A) this;
    }
  }

  public boolean hasExpression() {
    return this.expression != null;
  }

  public MultiplyExpressionNested<A> withNewMultiplyExpression() {
    return new MultiplyExpressionNested(null);
  }

  public MultiplyExpressionNested<A> withNewMultiplyExpressionLike(Multiply item) {
    return new MultiplyExpressionNested(item);
  }

  public A withNewMultiplyExpression(Object left, Object right) {
    return (A) withExpression(new Multiply(left, right));
  }

  public NewArrayExpressionNested<A> withNewNewArrayExpression() {
    return new NewArrayExpressionNested(null);
  }

  public NewArrayExpressionNested<A> withNewNewArrayExpressionLike(NewArray item) {
    return new NewArrayExpressionNested(item);
  }

  public A withNewNewArrayExpression(Class type, Integer[] sizes) {
    return (A) withExpression(new NewArray(type, sizes));
  }

  public InstanceOfExpressionNested<A> withNewInstanceOfExpression() {
    return new InstanceOfExpressionNested(null);
  }

  public InstanceOfExpressionNested<A> withNewInstanceOfExpressionLike(InstanceOf item) {
    return new InstanceOfExpressionNested(item);
  }

  public MethodCallExpressionNested<A> withNewMethodCallExpression() {
    return new MethodCallExpressionNested(null);
  }

  public MethodCallExpressionNested<A> withNewMethodCallExpressionLike(MethodCall item) {
    return new MethodCallExpressionNested(item);
  }

  public InverseExpressionNested<A> withNewInverseExpression() {
    return new InverseExpressionNested(null);
  }

  public InverseExpressionNested<A> withNewInverseExpressionLike(Inverse item) {
    return new InverseExpressionNested(item);
  }

  public IndexExpressionNested<A> withNewIndexExpression() {
    return new IndexExpressionNested(null);
  }

  public IndexExpressionNested<A> withNewIndexExpressionLike(Index item) {
    return new IndexExpressionNested(item);
  }

  public GreaterThanOrEqualExpressionNested<A> withNewGreaterThanOrEqualExpression() {
    return new GreaterThanOrEqualExpressionNested(null);
  }

  public GreaterThanOrEqualExpressionNested<A> withNewGreaterThanOrEqualExpressionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpressionNested(item);
  }

  public A withNewGreaterThanOrEqualExpression(Object left, Object right) {
    return (A) withExpression(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndExpressionNested<A> withNewBitwiseAndExpression() {
    return new BitwiseAndExpressionNested(null);
  }

  public BitwiseAndExpressionNested<A> withNewBitwiseAndExpressionLike(BitwiseAnd item) {
    return new BitwiseAndExpressionNested(item);
  }

  public A withNewBitwiseAndExpression(Object left, Object right) {
    return (A) withExpression(new BitwiseAnd(left, right));
  }

  public MinusExpressionNested<A> withNewMinusExpression() {
    return new MinusExpressionNested(null);
  }

  public MinusExpressionNested<A> withNewMinusExpressionLike(Minus item) {
    return new MinusExpressionNested(item);
  }

  public A withNewMinusExpression(Object left, Object right) {
    return (A) withExpression(new Minus(left, right));
  }

  public LogicalOrExpressionNested<A> withNewLogicalOrExpression() {
    return new LogicalOrExpressionNested(null);
  }

  public LogicalOrExpressionNested<A> withNewLogicalOrExpressionLike(LogicalOr item) {
    return new LogicalOrExpressionNested(item);
  }

  public A withNewLogicalOrExpression(Object left, Object right) {
    return (A) withExpression(new LogicalOr(left, right));
  }

  public NotEqualsExpressionNested<A> withNewNotEqualsExpression() {
    return new NotEqualsExpressionNested(null);
  }

  public NotEqualsExpressionNested<A> withNewNotEqualsExpressionLike(NotEquals item) {
    return new NotEqualsExpressionNested(item);
  }

  public A withNewNotEqualsExpression(Object left, Object right) {
    return (A) withExpression(new NotEquals(left, right));
  }

  public DivideExpressionNested<A> withNewDivideExpression() {
    return new DivideExpressionNested(null);
  }

  public DivideExpressionNested<A> withNewDivideExpressionLike(Divide item) {
    return new DivideExpressionNested(item);
  }

  public A withNewDivideExpression(Object left, Object right) {
    return (A) withExpression(new Divide(left, right));
  }

  public LessThanExpressionNested<A> withNewLessThanExpression() {
    return new LessThanExpressionNested(null);
  }

  public LessThanExpressionNested<A> withNewLessThanExpressionLike(LessThan item) {
    return new LessThanExpressionNested(item);
  }

  public A withNewLessThanExpression(Object left, Object right) {
    return (A) withExpression(new LessThan(left, right));
  }

  public BitwiseOrExpressionNested<A> withNewBitwiseOrExpression() {
    return new BitwiseOrExpressionNested(null);
  }

  public BitwiseOrExpressionNested<A> withNewBitwiseOrExpressionLike(BitwiseOr item) {
    return new BitwiseOrExpressionNested(item);
  }

  public A withNewBitwiseOrExpression(Object left, Object right) {
    return (A) withExpression(new BitwiseOr(left, right));
  }

  public PropertyRefExpressionNested<A> withNewPropertyRefExpression() {
    return new PropertyRefExpressionNested(null);
  }

  public PropertyRefExpressionNested<A> withNewPropertyRefExpressionLike(PropertyRef item) {
    return new PropertyRefExpressionNested(item);
  }

  public RightShiftExpressionNested<A> withNewRightShiftExpression() {
    return new RightShiftExpressionNested(null);
  }

  public RightShiftExpressionNested<A> withNewRightShiftExpressionLike(RightShift item) {
    return new RightShiftExpressionNested(item);
  }

  public A withNewRightShiftExpression(Object left, Object right) {
    return (A) withExpression(new RightShift(left, right));
  }

  public GreaterThanExpressionNested<A> withNewGreaterThanExpression() {
    return new GreaterThanExpressionNested(null);
  }

  public GreaterThanExpressionNested<A> withNewGreaterThanExpressionLike(GreaterThan item) {
    return new GreaterThanExpressionNested(item);
  }

  public A withNewGreaterThanExpression(Object left, Object right) {
    return (A) withExpression(new GreaterThan(left, right));
  }

  public DeclareExpressionNested<A> withNewDeclareExpression() {
    return new DeclareExpressionNested(null);
  }

  public DeclareExpressionNested<A> withNewDeclareExpressionLike(Declare item) {
    return new DeclareExpressionNested(item);
  }

  public A withNewDeclareExpression(Class type, String name) {
    return (A) withExpression(new Declare(type, name));
  }

  public A withNewDeclareExpression(Class type, String name, Object value) {
    return (A) withExpression(new Declare(type, name, value));
  }

  public CastExpressionNested<A> withNewCastExpression() {
    return new CastExpressionNested(null);
  }

  public CastExpressionNested<A> withNewCastExpressionLike(Cast item) {
    return new CastExpressionNested(item);
  }

  public ModuloExpressionNested<A> withNewModuloExpression() {
    return new ModuloExpressionNested(null);
  }

  public ModuloExpressionNested<A> withNewModuloExpressionLike(Modulo item) {
    return new ModuloExpressionNested(item);
  }

  public A withNewModuloExpression(Object left, Object right) {
    return (A) withExpression(new Modulo(left, right));
  }

  public ValueRefExpressionNested<A> withNewValueRefExpression() {
    return new ValueRefExpressionNested(null);
  }

  public ValueRefExpressionNested<A> withNewValueRefExpressionLike(ValueRef item) {
    return new ValueRefExpressionNested(item);
  }

  public A withNewValueRefExpression(Object value) {
    return (A) withExpression(new ValueRef(value));
  }

  public LeftShiftExpressionNested<A> withNewLeftShiftExpression() {
    return new LeftShiftExpressionNested(null);
  }

  public LeftShiftExpressionNested<A> withNewLeftShiftExpressionLike(LeftShift item) {
    return new LeftShiftExpressionNested(item);
  }

  public A withNewLeftShiftExpression(Object left, Object right) {
    return (A) withExpression(new LeftShift(left, right));
  }

  public TernaryExpressionNested<A> withNewTernaryExpression() {
    return new TernaryExpressionNested(null);
  }

  public TernaryExpressionNested<A> withNewTernaryExpressionLike(Ternary item) {
    return new TernaryExpressionNested(item);
  }

  public BinaryExpressionNested<A> withNewBinaryExpression() {
    return new BinaryExpressionNested(null);
  }

  public BinaryExpressionNested<A> withNewBinaryExpressionLike(BinaryExpression item) {
    return new BinaryExpressionNested(item);
  }

  public EqualsExpressionNested<A> withNewEqualsExpression() {
    return new EqualsExpressionNested(null);
  }

  public EqualsExpressionNested<A> withNewEqualsExpressionLike(Equals item) {
    return new EqualsExpressionNested(item);
  }

  public A withNewEqualsExpression(Object left, Object right) {
    return (A) withExpression(new Equals(left, right));
  }

  public EnclosedExpressionNested<A> withNewEnclosedExpression() {
    return new EnclosedExpressionNested(null);
  }

  public EnclosedExpressionNested<A> withNewEnclosedExpressionLike(Enclosed item) {
    return new EnclosedExpressionNested(item);
  }

  public PreDecrementExpressionNested<A> withNewPreDecrementExpression() {
    return new PreDecrementExpressionNested(null);
  }

  public PreDecrementExpressionNested<A> withNewPreDecrementExpressionLike(PreDecrement item) {
    return new PreDecrementExpressionNested(item);
  }

  public PostDecrementExpressionNested<A> withNewPostDecrementExpression() {
    return new PostDecrementExpressionNested(null);
  }

  public PostDecrementExpressionNested<A> withNewPostDecrementExpressionLike(PostDecrement item) {
    return new PostDecrementExpressionNested(item);
  }

  public LambdaExpressionNested<A> withNewLambdaExpression() {
    return new LambdaExpressionNested(null);
  }

  public LambdaExpressionNested<A> withNewLambdaExpressionLike(Lambda item) {
    return new LambdaExpressionNested(item);
  }

  public NotExpressionNested<A> withNewNotExpression() {
    return new NotExpressionNested(null);
  }

  public NotExpressionNested<A> withNewNotExpressionLike(Not item) {
    return new NotExpressionNested(item);
  }

  public AssignExpressionNested<A> withNewAssignExpression() {
    return new AssignExpressionNested(null);
  }

  public AssignExpressionNested<A> withNewAssignExpressionLike(Assign item) {
    return new AssignExpressionNested(item);
  }

  public ThisExpressionNested<A> withNewThisExpression() {
    return new ThisExpressionNested(null);
  }

  public ThisExpressionNested<A> withNewThisExpressionLike(This item) {
    return new ThisExpressionNested(item);
  }

  public NegativeExpressionNested<A> withNewNegativeExpression() {
    return new NegativeExpressionNested(null);
  }

  public NegativeExpressionNested<A> withNewNegativeExpressionLike(Negative item) {
    return new NegativeExpressionNested(item);
  }

  public LogicalAndExpressionNested<A> withNewLogicalAndExpression() {
    return new LogicalAndExpressionNested(null);
  }

  public LogicalAndExpressionNested<A> withNewLogicalAndExpressionLike(LogicalAnd item) {
    return new LogicalAndExpressionNested(item);
  }

  public A withNewLogicalAndExpression(Object left, Object right) {
    return (A) withExpression(new LogicalAnd(left, right));
  }

  public PostIncrementExpressionNested<A> withNewPostIncrementExpression() {
    return new PostIncrementExpressionNested(null);
  }

  public PostIncrementExpressionNested<A> withNewPostIncrementExpressionLike(PostIncrement item) {
    return new PostIncrementExpressionNested(item);
  }

  public RightUnsignedShiftExpressionNested<A> withNewRightUnsignedShiftExpression() {
    return new RightUnsignedShiftExpressionNested(null);
  }

  public RightUnsignedShiftExpressionNested<A> withNewRightUnsignedShiftExpressionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExpressionNested(item);
  }

  public A withNewRightUnsignedShiftExpression(Object left, Object right) {
    return (A) withExpression(new RightUnsignedShift(left, right));
  }

  public PlusExpressionNested<A> withNewPlusExpression() {
    return new PlusExpressionNested(null);
  }

  public PlusExpressionNested<A> withNewPlusExpressionLike(Plus item) {
    return new PlusExpressionNested(item);
  }

  public A withNewPlusExpression(Object left, Object right) {
    return (A) withExpression(new Plus(left, right));
  }

  public ConstructExpressionNested<A> withNewConstructExpression() {
    return new ConstructExpressionNested(null);
  }

  public ConstructExpressionNested<A> withNewConstructExpressionLike(Construct item) {
    return new ConstructExpressionNested(item);
  }

  public XorExpressionNested<A> withNewXorExpression() {
    return new XorExpressionNested(null);
  }

  public XorExpressionNested<A> withNewXorExpressionLike(Xor item) {
    return new XorExpressionNested(item);
  }

  public A withNewXorExpression(Object left, Object right) {
    return (A) withExpression(new Xor(left, right));
  }

  public PreIncrementExpressionNested<A> withNewPreIncrementExpression() {
    return new PreIncrementExpressionNested(null);
  }

  public PreIncrementExpressionNested<A> withNewPreIncrementExpressionLike(PreIncrement item) {
    return new PreIncrementExpressionNested(item);
  }

  public LessThanOrEqualExpressionNested<A> withNewLessThanOrEqualExpression() {
    return new LessThanOrEqualExpressionNested(null);
  }

  public LessThanOrEqualExpressionNested<A> withNewLessThanOrEqualExpressionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExpressionNested(item);
  }

  public A withNewLessThanOrEqualExpression(Object left, Object right) {
    return (A) withExpression(new LessThanOrEqual(left, right));
  }

  public PositiveExpressionNested<A> withNewPositiveExpression() {
    return new PositiveExpressionNested(null);
  }

  public PositiveExpressionNested<A> withNewPositiveExpressionLike(Positive item) {
    return new PositiveExpressionNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PostIncrementFluent that = (PostIncrementFluent) o;
    if (!java.util.Objects.equals(expression, that.expression))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(expression, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (expression != null) {
      sb.append("expression:");
      sb.append(expression);
    }
    sb.append("}");
    return sb.toString();
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
      case "io.sundr.model." + "GreaterThan":
        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);
      case "io.sundr.model." + "Declare":
        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);
      case "io.sundr.model." + "Cast":
        return (VisitableBuilder<T, ?>) new CastBuilder((Cast) item);
      case "io.sundr.model." + "Modulo":
        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);
      case "io.sundr.model." + "ValueRef":
        return (VisitableBuilder<T, ?>) new ValueRefBuilder((ValueRef) item);
      case "io.sundr.model." + "LeftShift":
        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);
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
      case "io.sundr.model." + "LessThanOrEqual":
        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);
      case "io.sundr.model." + "Positive":
        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class MultiplyExpressionNested<N> extends MultiplyFluent<MultiplyExpressionNested<N>> implements Nested<N> {
    MultiplyExpressionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endMultiplyExpression() {
      return and();
    }

  }

  public class NewArrayExpressionNested<N> extends NewArrayFluent<NewArrayExpressionNested<N>> implements Nested<N> {
    NewArrayExpressionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endNewArrayExpression() {
      return and();
    }

  }

  public class InstanceOfExpressionNested<N> extends InstanceOfFluent<InstanceOfExpressionNested<N>> implements Nested<N> {
    InstanceOfExpressionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endInstanceOfExpression() {
      return and();
    }

  }

  public class MethodCallExpressionNested<N> extends MethodCallFluent<MethodCallExpressionNested<N>> implements Nested<N> {
    MethodCallExpressionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endMethodCallExpression() {
      return and();
    }

  }

  public class InverseExpressionNested<N> extends InverseFluent<InverseExpressionNested<N>> implements Nested<N> {
    InverseExpressionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endInverseExpression() {
      return and();
    }

  }

  public class IndexExpressionNested<N> extends IndexFluent<IndexExpressionNested<N>> implements Nested<N> {
    IndexExpressionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endIndexExpression() {
      return and();
    }

  }

  public class GreaterThanOrEqualExpressionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExpressionNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualExpressionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endGreaterThanOrEqualExpression() {
      return and();
    }

  }

  public class BitwiseAndExpressionNested<N> extends BitwiseAndFluent<BitwiseAndExpressionNested<N>> implements Nested<N> {
    BitwiseAndExpressionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endBitwiseAndExpression() {
      return and();
    }

  }

  public class MinusExpressionNested<N> extends MinusFluent<MinusExpressionNested<N>> implements Nested<N> {
    MinusExpressionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endMinusExpression() {
      return and();
    }

  }

  public class LogicalOrExpressionNested<N> extends LogicalOrFluent<LogicalOrExpressionNested<N>> implements Nested<N> {
    LogicalOrExpressionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endLogicalOrExpression() {
      return and();
    }

  }

  public class NotEqualsExpressionNested<N> extends NotEqualsFluent<NotEqualsExpressionNested<N>> implements Nested<N> {
    NotEqualsExpressionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endNotEqualsExpression() {
      return and();
    }

  }

  public class DivideExpressionNested<N> extends DivideFluent<DivideExpressionNested<N>> implements Nested<N> {
    DivideExpressionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endDivideExpression() {
      return and();
    }

  }

  public class LessThanExpressionNested<N> extends LessThanFluent<LessThanExpressionNested<N>> implements Nested<N> {
    LessThanExpressionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endLessThanExpression() {
      return and();
    }

  }

  public class BitwiseOrExpressionNested<N> extends BitwiseOrFluent<BitwiseOrExpressionNested<N>> implements Nested<N> {
    BitwiseOrExpressionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endBitwiseOrExpression() {
      return and();
    }

  }

  public class PropertyRefExpressionNested<N> extends PropertyRefFluent<PropertyRefExpressionNested<N>> implements Nested<N> {
    PropertyRefExpressionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPropertyRefExpression() {
      return and();
    }

  }

  public class RightShiftExpressionNested<N> extends RightShiftFluent<RightShiftExpressionNested<N>> implements Nested<N> {
    RightShiftExpressionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endRightShiftExpression() {
      return and();
    }

  }

  public class GreaterThanExpressionNested<N> extends GreaterThanFluent<GreaterThanExpressionNested<N>> implements Nested<N> {
    GreaterThanExpressionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endGreaterThanExpression() {
      return and();
    }

  }

  public class DeclareExpressionNested<N> extends DeclareFluent<DeclareExpressionNested<N>> implements Nested<N> {
    DeclareExpressionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endDeclareExpression() {
      return and();
    }

  }

  public class CastExpressionNested<N> extends CastFluent<CastExpressionNested<N>> implements Nested<N> {
    CastExpressionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endCastExpression() {
      return and();
    }

  }

  public class ModuloExpressionNested<N> extends ModuloFluent<ModuloExpressionNested<N>> implements Nested<N> {
    ModuloExpressionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endModuloExpression() {
      return and();
    }

  }

  public class ValueRefExpressionNested<N> extends ValueRefFluent<ValueRefExpressionNested<N>> implements Nested<N> {
    ValueRefExpressionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endValueRefExpression() {
      return and();
    }

  }

  public class LeftShiftExpressionNested<N> extends LeftShiftFluent<LeftShiftExpressionNested<N>> implements Nested<N> {
    LeftShiftExpressionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endLeftShiftExpression() {
      return and();
    }

  }

  public class TernaryExpressionNested<N> extends TernaryFluent<TernaryExpressionNested<N>> implements Nested<N> {
    TernaryExpressionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endTernaryExpression() {
      return and();
    }

  }

  public class BinaryExpressionNested<N> extends BinaryExpressionFluent<BinaryExpressionNested<N>> implements Nested<N> {
    BinaryExpressionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endBinaryExpression() {
      return and();
    }

  }

  public class EqualsExpressionNested<N> extends EqualsFluent<EqualsExpressionNested<N>> implements Nested<N> {
    EqualsExpressionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endEqualsExpression() {
      return and();
    }

  }

  public class EnclosedExpressionNested<N> extends EnclosedFluent<EnclosedExpressionNested<N>> implements Nested<N> {
    EnclosedExpressionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endEnclosedExpression() {
      return and();
    }

  }

  public class PreDecrementExpressionNested<N> extends PreDecrementFluent<PreDecrementExpressionNested<N>>
      implements Nested<N> {
    PreDecrementExpressionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPreDecrementExpression() {
      return and();
    }

  }

  public class PostDecrementExpressionNested<N> extends PostDecrementFluent<PostDecrementExpressionNested<N>>
      implements Nested<N> {
    PostDecrementExpressionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPostDecrementExpression() {
      return and();
    }

  }

  public class LambdaExpressionNested<N> extends LambdaFluent<LambdaExpressionNested<N>> implements Nested<N> {
    LambdaExpressionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endLambdaExpression() {
      return and();
    }

  }

  public class NotExpressionNested<N> extends NotFluent<NotExpressionNested<N>> implements Nested<N> {
    NotExpressionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endNotExpression() {
      return and();
    }

  }

  public class AssignExpressionNested<N> extends AssignFluent<AssignExpressionNested<N>> implements Nested<N> {
    AssignExpressionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endAssignExpression() {
      return and();
    }

  }

  public class ThisExpressionNested<N> extends ThisFluent<ThisExpressionNested<N>> implements Nested<N> {
    ThisExpressionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endThisExpression() {
      return and();
    }

  }

  public class NegativeExpressionNested<N> extends NegativeFluent<NegativeExpressionNested<N>> implements Nested<N> {
    NegativeExpressionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endNegativeExpression() {
      return and();
    }

  }

  public class LogicalAndExpressionNested<N> extends LogicalAndFluent<LogicalAndExpressionNested<N>> implements Nested<N> {
    LogicalAndExpressionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endLogicalAndExpression() {
      return and();
    }

  }

  public class PostIncrementExpressionNested<N> extends PostIncrementFluent<PostIncrementExpressionNested<N>>
      implements Nested<N> {
    PostIncrementExpressionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPostIncrementExpression() {
      return and();
    }

  }

  public class RightUnsignedShiftExpressionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExpressionNested<N>>
      implements Nested<N> {
    RightUnsignedShiftExpressionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endRightUnsignedShiftExpression() {
      return and();
    }

  }

  public class PlusExpressionNested<N> extends PlusFluent<PlusExpressionNested<N>> implements Nested<N> {
    PlusExpressionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPlusExpression() {
      return and();
    }

  }

  public class ConstructExpressionNested<N> extends ConstructFluent<ConstructExpressionNested<N>> implements Nested<N> {
    ConstructExpressionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endConstructExpression() {
      return and();
    }

  }

  public class XorExpressionNested<N> extends XorFluent<XorExpressionNested<N>> implements Nested<N> {
    XorExpressionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endXorExpression() {
      return and();
    }

  }

  public class PreIncrementExpressionNested<N> extends PreIncrementFluent<PreIncrementExpressionNested<N>>
      implements Nested<N> {
    PreIncrementExpressionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPreIncrementExpression() {
      return and();
    }

  }

  public class LessThanOrEqualExpressionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExpressionNested<N>>
      implements Nested<N> {
    LessThanOrEqualExpressionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endLessThanOrEqualExpression() {
      return and();
    }

  }

  public class PositiveExpressionNested<N> extends PositiveFluent<PositiveExpressionNested<N>> implements Nested<N> {
    PositiveExpressionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) PostIncrementFluent.this.withExpression(builder.build());
    }

    public N endPositiveExpression() {
      return and();
    }

  }

}
