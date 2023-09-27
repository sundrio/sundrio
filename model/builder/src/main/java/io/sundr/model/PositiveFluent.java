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
public class PositiveFluent<A extends PositiveFluent<A>> extends BaseFluent<A> {
  public PositiveFluent() {
  }

  public PositiveFluent(Positive instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> expresion;

  protected void copyInstance(Positive instance) {
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
    }
  }

  public Expression buildExpresion() {
    return this.expresion != null ? this.expresion.build() : null;
  }

  public A withExpresion(Expression expresion) {
    if (expresion == null) {
      this.expresion = null;
      _visitables.remove("expresion");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(expresion);
    _visitables.get("expresion").clear();
    _visitables.get("expresion").add(builder);
    this.expresion = builder;
    return (A) this;
  }

  public boolean hasExpresion() {
    return this.expresion != null;
  }

  public MultiplyExpresionNested<A> withNewMultiplyExpresion() {
    return new MultiplyExpresionNested(null);
  }

  public MultiplyExpresionNested<A> withNewMultiplyExpresionLike(Multiply item) {
    return new MultiplyExpresionNested(item);
  }

  public A withNewMultiplyExpresion(Object left, Object right) {
    return (A) withExpresion(new Multiply(left, right));
  }

  public NewArrayExpresionNested<A> withNewNewArrayExpresion() {
    return new NewArrayExpresionNested(null);
  }

  public NewArrayExpresionNested<A> withNewNewArrayExpresionLike(NewArray item) {
    return new NewArrayExpresionNested(item);
  }

  public A withNewNewArrayExpresion(Class type, Integer[] sizes) {
    return (A) withExpresion(new NewArray(type, sizes));
  }

  public InstanceOfExpresionNested<A> withNewInstanceOfExpresion() {
    return new InstanceOfExpresionNested(null);
  }

  public InstanceOfExpresionNested<A> withNewInstanceOfExpresionLike(InstanceOf item) {
    return new InstanceOfExpresionNested(item);
  }

  public MethodCallExpresionNested<A> withNewMethodCallExpresion() {
    return new MethodCallExpresionNested(null);
  }

  public MethodCallExpresionNested<A> withNewMethodCallExpresionLike(MethodCall item) {
    return new MethodCallExpresionNested(item);
  }

  public InverseExpresionNested<A> withNewInverseExpresion() {
    return new InverseExpresionNested(null);
  }

  public InverseExpresionNested<A> withNewInverseExpresionLike(Inverse item) {
    return new InverseExpresionNested(item);
  }

  public IndexExpresionNested<A> withNewIndexExpresion() {
    return new IndexExpresionNested(null);
  }

  public IndexExpresionNested<A> withNewIndexExpresionLike(Index item) {
    return new IndexExpresionNested(item);
  }

  public GreaterThanOrEqualExpresionNested<A> withNewGreaterThanOrEqualExpresion() {
    return new GreaterThanOrEqualExpresionNested(null);
  }

  public GreaterThanOrEqualExpresionNested<A> withNewGreaterThanOrEqualExpresionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpresionNested(item);
  }

  public A withNewGreaterThanOrEqualExpresion(Object left, Object right) {
    return (A) withExpresion(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndExpresionNested<A> withNewBitwiseAndExpresion() {
    return new BitwiseAndExpresionNested(null);
  }

  public BitwiseAndExpresionNested<A> withNewBitwiseAndExpresionLike(BitwiseAnd item) {
    return new BitwiseAndExpresionNested(item);
  }

  public A withNewBitwiseAndExpresion(Object left, Object right) {
    return (A) withExpresion(new BitwiseAnd(left, right));
  }

  public MinusExpresionNested<A> withNewMinusExpresion() {
    return new MinusExpresionNested(null);
  }

  public MinusExpresionNested<A> withNewMinusExpresionLike(Minus item) {
    return new MinusExpresionNested(item);
  }

  public A withNewMinusExpresion(Object left, Object right) {
    return (A) withExpresion(new Minus(left, right));
  }

  public LogicalOrExpresionNested<A> withNewLogicalOrExpresion() {
    return new LogicalOrExpresionNested(null);
  }

  public LogicalOrExpresionNested<A> withNewLogicalOrExpresionLike(LogicalOr item) {
    return new LogicalOrExpresionNested(item);
  }

  public A withNewLogicalOrExpresion(Object left, Object right) {
    return (A) withExpresion(new LogicalOr(left, right));
  }

  public NotEqualsExpresionNested<A> withNewNotEqualsExpresion() {
    return new NotEqualsExpresionNested(null);
  }

  public NotEqualsExpresionNested<A> withNewNotEqualsExpresionLike(NotEquals item) {
    return new NotEqualsExpresionNested(item);
  }

  public A withNewNotEqualsExpresion(Object left, Object right) {
    return (A) withExpresion(new NotEquals(left, right));
  }

  public DivideExpresionNested<A> withNewDivideExpresion() {
    return new DivideExpresionNested(null);
  }

  public DivideExpresionNested<A> withNewDivideExpresionLike(Divide item) {
    return new DivideExpresionNested(item);
  }

  public A withNewDivideExpresion(Object left, Object right) {
    return (A) withExpresion(new Divide(left, right));
  }

  public LessThanExpresionNested<A> withNewLessThanExpresion() {
    return new LessThanExpresionNested(null);
  }

  public LessThanExpresionNested<A> withNewLessThanExpresionLike(LessThan item) {
    return new LessThanExpresionNested(item);
  }

  public A withNewLessThanExpresion(Object left, Object right) {
    return (A) withExpresion(new LessThan(left, right));
  }

  public BitwiseOrExpresionNested<A> withNewBitwiseOrExpresion() {
    return new BitwiseOrExpresionNested(null);
  }

  public BitwiseOrExpresionNested<A> withNewBitwiseOrExpresionLike(BitwiseOr item) {
    return new BitwiseOrExpresionNested(item);
  }

  public A withNewBitwiseOrExpresion(Object left, Object right) {
    return (A) withExpresion(new BitwiseOr(left, right));
  }

  public PropertyRefExpresionNested<A> withNewPropertyRefExpresion() {
    return new PropertyRefExpresionNested(null);
  }

  public PropertyRefExpresionNested<A> withNewPropertyRefExpresionLike(PropertyRef item) {
    return new PropertyRefExpresionNested(item);
  }

  public RightShiftExpresionNested<A> withNewRightShiftExpresion() {
    return new RightShiftExpresionNested(null);
  }

  public RightShiftExpresionNested<A> withNewRightShiftExpresionLike(RightShift item) {
    return new RightShiftExpresionNested(item);
  }

  public A withNewRightShiftExpresion(Object left, Object right) {
    return (A) withExpresion(new RightShift(left, right));
  }

  public GreaterThanExpresionNested<A> withNewGreaterThanExpresion() {
    return new GreaterThanExpresionNested(null);
  }

  public GreaterThanExpresionNested<A> withNewGreaterThanExpresionLike(GreaterThan item) {
    return new GreaterThanExpresionNested(item);
  }

  public A withNewGreaterThanExpresion(Object left, Object right) {
    return (A) withExpresion(new GreaterThan(left, right));
  }

  public DeclareExpresionNested<A> withNewDeclareExpresion() {
    return new DeclareExpresionNested(null);
  }

  public DeclareExpresionNested<A> withNewDeclareExpresionLike(Declare item) {
    return new DeclareExpresionNested(item);
  }

  public A withNewDeclareExpresion(Class type, String name) {
    return (A) withExpresion(new Declare(type, name));
  }

  public A withNewDeclareExpresion(Class type, String name, Object value) {
    return (A) withExpresion(new Declare(type, name, value));
  }

  public CastExpresionNested<A> withNewCastExpresion() {
    return new CastExpresionNested(null);
  }

  public CastExpresionNested<A> withNewCastExpresionLike(Cast item) {
    return new CastExpresionNested(item);
  }

  public ModuloExpresionNested<A> withNewModuloExpresion() {
    return new ModuloExpresionNested(null);
  }

  public ModuloExpresionNested<A> withNewModuloExpresionLike(Modulo item) {
    return new ModuloExpresionNested(item);
  }

  public A withNewModuloExpresion(Object left, Object right) {
    return (A) withExpresion(new Modulo(left, right));
  }

  public ValueRefExpresionNested<A> withNewValueRefExpresion() {
    return new ValueRefExpresionNested(null);
  }

  public ValueRefExpresionNested<A> withNewValueRefExpresionLike(ValueRef item) {
    return new ValueRefExpresionNested(item);
  }

  public A withNewValueRefExpresion(Object value) {
    return (A) withExpresion(new ValueRef(value));
  }

  public LeftShiftExpresionNested<A> withNewLeftShiftExpresion() {
    return new LeftShiftExpresionNested(null);
  }

  public LeftShiftExpresionNested<A> withNewLeftShiftExpresionLike(LeftShift item) {
    return new LeftShiftExpresionNested(item);
  }

  public A withNewLeftShiftExpresion(Object left, Object right) {
    return (A) withExpresion(new LeftShift(left, right));
  }

  public TernaryExpresionNested<A> withNewTernaryExpresion() {
    return new TernaryExpresionNested(null);
  }

  public TernaryExpresionNested<A> withNewTernaryExpresionLike(Ternary item) {
    return new TernaryExpresionNested(item);
  }

  public BinaryExpressionExpresionNested<A> withNewBinaryExpressionExpresion() {
    return new BinaryExpressionExpresionNested(null);
  }

  public BinaryExpressionExpresionNested<A> withNewBinaryExpressionExpresionLike(BinaryExpression item) {
    return new BinaryExpressionExpresionNested(item);
  }

  public EqualsExpresionNested<A> withNewEqualsExpresion() {
    return new EqualsExpresionNested(null);
  }

  public EqualsExpresionNested<A> withNewEqualsExpresionLike(Equals item) {
    return new EqualsExpresionNested(item);
  }

  public A withNewEqualsExpresion(Object left, Object right) {
    return (A) withExpresion(new Equals(left, right));
  }

  public EnclosedExpresionNested<A> withNewEnclosedExpresion() {
    return new EnclosedExpresionNested(null);
  }

  public EnclosedExpresionNested<A> withNewEnclosedExpresionLike(Enclosed item) {
    return new EnclosedExpresionNested(item);
  }

  public PreDecrementExpresionNested<A> withNewPreDecrementExpresion() {
    return new PreDecrementExpresionNested(null);
  }

  public PreDecrementExpresionNested<A> withNewPreDecrementExpresionLike(PreDecrement item) {
    return new PreDecrementExpresionNested(item);
  }

  public PostDecrementExpresionNested<A> withNewPostDecrementExpresion() {
    return new PostDecrementExpresionNested(null);
  }

  public PostDecrementExpresionNested<A> withNewPostDecrementExpresionLike(PostDecrement item) {
    return new PostDecrementExpresionNested(item);
  }

  public LambdaExpresionNested<A> withNewLambdaExpresion() {
    return new LambdaExpresionNested(null);
  }

  public LambdaExpresionNested<A> withNewLambdaExpresionLike(Lambda item) {
    return new LambdaExpresionNested(item);
  }

  public NotExpresionNested<A> withNewNotExpresion() {
    return new NotExpresionNested(null);
  }

  public NotExpresionNested<A> withNewNotExpresionLike(Not item) {
    return new NotExpresionNested(item);
  }

  public AssignExpresionNested<A> withNewAssignExpresion() {
    return new AssignExpresionNested(null);
  }

  public AssignExpresionNested<A> withNewAssignExpresionLike(Assign item) {
    return new AssignExpresionNested(item);
  }

  public NegativeExpresionNested<A> withNewNegativeExpresion() {
    return new NegativeExpresionNested(null);
  }

  public NegativeExpresionNested<A> withNewNegativeExpresionLike(Negative item) {
    return new NegativeExpresionNested(item);
  }

  public ThisExpresionNested<A> withNewThisExpresion() {
    return new ThisExpresionNested(null);
  }

  public ThisExpresionNested<A> withNewThisExpresionLike(This item) {
    return new ThisExpresionNested(item);
  }

  public LogicalAndExpresionNested<A> withNewLogicalAndExpresion() {
    return new LogicalAndExpresionNested(null);
  }

  public LogicalAndExpresionNested<A> withNewLogicalAndExpresionLike(LogicalAnd item) {
    return new LogicalAndExpresionNested(item);
  }

  public A withNewLogicalAndExpresion(Object left, Object right) {
    return (A) withExpresion(new LogicalAnd(left, right));
  }

  public PostIncrementExpresionNested<A> withNewPostIncrementExpresion() {
    return new PostIncrementExpresionNested(null);
  }

  public PostIncrementExpresionNested<A> withNewPostIncrementExpresionLike(PostIncrement item) {
    return new PostIncrementExpresionNested(item);
  }

  public RightUnsignedShiftExpresionNested<A> withNewRightUnsignedShiftExpresion() {
    return new RightUnsignedShiftExpresionNested(null);
  }

  public RightUnsignedShiftExpresionNested<A> withNewRightUnsignedShiftExpresionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExpresionNested(item);
  }

  public A withNewRightUnsignedShiftExpresion(Object left, Object right) {
    return (A) withExpresion(new RightUnsignedShift(left, right));
  }

  public PlusExpresionNested<A> withNewPlusExpresion() {
    return new PlusExpresionNested(null);
  }

  public PlusExpresionNested<A> withNewPlusExpresionLike(Plus item) {
    return new PlusExpresionNested(item);
  }

  public A withNewPlusExpresion(Object left, Object right) {
    return (A) withExpresion(new Plus(left, right));
  }

  public ConstructExpresionNested<A> withNewConstructExpresion() {
    return new ConstructExpresionNested(null);
  }

  public ConstructExpresionNested<A> withNewConstructExpresionLike(Construct item) {
    return new ConstructExpresionNested(item);
  }

  public XorExpresionNested<A> withNewXorExpresion() {
    return new XorExpresionNested(null);
  }

  public XorExpresionNested<A> withNewXorExpresionLike(Xor item) {
    return new XorExpresionNested(item);
  }

  public A withNewXorExpresion(Object left, Object right) {
    return (A) withExpresion(new Xor(left, right));
  }

  public PreIncrementExpresionNested<A> withNewPreIncrementExpresion() {
    return new PreIncrementExpresionNested(null);
  }

  public PreIncrementExpresionNested<A> withNewPreIncrementExpresionLike(PreIncrement item) {
    return new PreIncrementExpresionNested(item);
  }

  public LessThanOrEqualExpresionNested<A> withNewLessThanOrEqualExpresion() {
    return new LessThanOrEqualExpresionNested(null);
  }

  public LessThanOrEqualExpresionNested<A> withNewLessThanOrEqualExpresionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExpresionNested(item);
  }

  public A withNewLessThanOrEqualExpresion(Object left, Object right) {
    return (A) withExpresion(new LessThanOrEqual(left, right));
  }

  public PositiveExpresionNested<A> withNewPositiveExpresion() {
    return new PositiveExpresionNested(null);
  }

  public PositiveExpresionNested<A> withNewPositiveExpresionLike(Positive item) {
    return new PositiveExpresionNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PositiveFluent that = (PositiveFluent) o;
    if (!java.util.Objects.equals(expresion, that.expresion))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(expresion, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (expresion != null) {
      sb.append("expresion:");
      sb.append(expresion);
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
      case "io.sundr.model." + "Negative":
        return (VisitableBuilder<T, ?>) new NegativeBuilder((Negative) item);
      case "io.sundr.model." + "This":
        return (VisitableBuilder<T, ?>) new ThisBuilder((This) item);
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

  public class MultiplyExpresionNested<N> extends MultiplyFluent<MultiplyExpresionNested<N>> implements Nested<N> {
    MultiplyExpresionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endMultiplyExpresion() {
      return and();
    }

  }

  public class NewArrayExpresionNested<N> extends NewArrayFluent<NewArrayExpresionNested<N>> implements Nested<N> {
    NewArrayExpresionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNewArrayExpresion() {
      return and();
    }

  }

  public class InstanceOfExpresionNested<N> extends InstanceOfFluent<InstanceOfExpresionNested<N>> implements Nested<N> {
    InstanceOfExpresionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endInstanceOfExpresion() {
      return and();
    }

  }

  public class MethodCallExpresionNested<N> extends MethodCallFluent<MethodCallExpresionNested<N>> implements Nested<N> {
    MethodCallExpresionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endMethodCallExpresion() {
      return and();
    }

  }

  public class InverseExpresionNested<N> extends InverseFluent<InverseExpresionNested<N>> implements Nested<N> {
    InverseExpresionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endInverseExpresion() {
      return and();
    }

  }

  public class IndexExpresionNested<N> extends IndexFluent<IndexExpresionNested<N>> implements Nested<N> {
    IndexExpresionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endIndexExpresion() {
      return and();
    }

  }

  public class GreaterThanOrEqualExpresionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExpresionNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualExpresionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endGreaterThanOrEqualExpresion() {
      return and();
    }

  }

  public class BitwiseAndExpresionNested<N> extends BitwiseAndFluent<BitwiseAndExpresionNested<N>> implements Nested<N> {
    BitwiseAndExpresionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endBitwiseAndExpresion() {
      return and();
    }

  }

  public class MinusExpresionNested<N> extends MinusFluent<MinusExpresionNested<N>> implements Nested<N> {
    MinusExpresionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endMinusExpresion() {
      return and();
    }

  }

  public class LogicalOrExpresionNested<N> extends LogicalOrFluent<LogicalOrExpresionNested<N>> implements Nested<N> {
    LogicalOrExpresionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLogicalOrExpresion() {
      return and();
    }

  }

  public class NotEqualsExpresionNested<N> extends NotEqualsFluent<NotEqualsExpresionNested<N>> implements Nested<N> {
    NotEqualsExpresionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNotEqualsExpresion() {
      return and();
    }

  }

  public class DivideExpresionNested<N> extends DivideFluent<DivideExpresionNested<N>> implements Nested<N> {
    DivideExpresionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endDivideExpresion() {
      return and();
    }

  }

  public class LessThanExpresionNested<N> extends LessThanFluent<LessThanExpresionNested<N>> implements Nested<N> {
    LessThanExpresionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLessThanExpresion() {
      return and();
    }

  }

  public class BitwiseOrExpresionNested<N> extends BitwiseOrFluent<BitwiseOrExpresionNested<N>> implements Nested<N> {
    BitwiseOrExpresionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endBitwiseOrExpresion() {
      return and();
    }

  }

  public class PropertyRefExpresionNested<N> extends PropertyRefFluent<PropertyRefExpresionNested<N>> implements Nested<N> {
    PropertyRefExpresionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPropertyRefExpresion() {
      return and();
    }

  }

  public class RightShiftExpresionNested<N> extends RightShiftFluent<RightShiftExpresionNested<N>> implements Nested<N> {
    RightShiftExpresionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endRightShiftExpresion() {
      return and();
    }

  }

  public class GreaterThanExpresionNested<N> extends GreaterThanFluent<GreaterThanExpresionNested<N>> implements Nested<N> {
    GreaterThanExpresionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endGreaterThanExpresion() {
      return and();
    }

  }

  public class DeclareExpresionNested<N> extends DeclareFluent<DeclareExpresionNested<N>> implements Nested<N> {
    DeclareExpresionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endDeclareExpresion() {
      return and();
    }

  }

  public class CastExpresionNested<N> extends CastFluent<CastExpresionNested<N>> implements Nested<N> {
    CastExpresionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endCastExpresion() {
      return and();
    }

  }

  public class ModuloExpresionNested<N> extends ModuloFluent<ModuloExpresionNested<N>> implements Nested<N> {
    ModuloExpresionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endModuloExpresion() {
      return and();
    }

  }

  public class ValueRefExpresionNested<N> extends ValueRefFluent<ValueRefExpresionNested<N>> implements Nested<N> {
    ValueRefExpresionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endValueRefExpresion() {
      return and();
    }

  }

  public class LeftShiftExpresionNested<N> extends LeftShiftFluent<LeftShiftExpresionNested<N>> implements Nested<N> {
    LeftShiftExpresionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLeftShiftExpresion() {
      return and();
    }

  }

  public class TernaryExpresionNested<N> extends TernaryFluent<TernaryExpresionNested<N>> implements Nested<N> {
    TernaryExpresionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endTernaryExpresion() {
      return and();
    }

  }

  public class BinaryExpressionExpresionNested<N> extends BinaryExpressionFluent<BinaryExpressionExpresionNested<N>>
      implements Nested<N> {
    BinaryExpressionExpresionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endBinaryExpressionExpresion() {
      return and();
    }

  }

  public class EqualsExpresionNested<N> extends EqualsFluent<EqualsExpresionNested<N>> implements Nested<N> {
    EqualsExpresionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endEqualsExpresion() {
      return and();
    }

  }

  public class EnclosedExpresionNested<N> extends EnclosedFluent<EnclosedExpresionNested<N>> implements Nested<N> {
    EnclosedExpresionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endEnclosedExpresion() {
      return and();
    }

  }

  public class PreDecrementExpresionNested<N> extends PreDecrementFluent<PreDecrementExpresionNested<N>> implements Nested<N> {
    PreDecrementExpresionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPreDecrementExpresion() {
      return and();
    }

  }

  public class PostDecrementExpresionNested<N> extends PostDecrementFluent<PostDecrementExpresionNested<N>>
      implements Nested<N> {
    PostDecrementExpresionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPostDecrementExpresion() {
      return and();
    }

  }

  public class LambdaExpresionNested<N> extends LambdaFluent<LambdaExpresionNested<N>> implements Nested<N> {
    LambdaExpresionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLambdaExpresion() {
      return and();
    }

  }

  public class NotExpresionNested<N> extends NotFluent<NotExpresionNested<N>> implements Nested<N> {
    NotExpresionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNotExpresion() {
      return and();
    }

  }

  public class AssignExpresionNested<N> extends AssignFluent<AssignExpresionNested<N>> implements Nested<N> {
    AssignExpresionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endAssignExpresion() {
      return and();
    }

  }

  public class NegativeExpresionNested<N> extends NegativeFluent<NegativeExpresionNested<N>> implements Nested<N> {
    NegativeExpresionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNegativeExpresion() {
      return and();
    }

  }

  public class ThisExpresionNested<N> extends ThisFluent<ThisExpresionNested<N>> implements Nested<N> {
    ThisExpresionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endThisExpresion() {
      return and();
    }

  }

  public class LogicalAndExpresionNested<N> extends LogicalAndFluent<LogicalAndExpresionNested<N>> implements Nested<N> {
    LogicalAndExpresionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLogicalAndExpresion() {
      return and();
    }

  }

  public class PostIncrementExpresionNested<N> extends PostIncrementFluent<PostIncrementExpresionNested<N>>
      implements Nested<N> {
    PostIncrementExpresionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPostIncrementExpresion() {
      return and();
    }

  }

  public class RightUnsignedShiftExpresionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExpresionNested<N>>
      implements Nested<N> {
    RightUnsignedShiftExpresionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endRightUnsignedShiftExpresion() {
      return and();
    }

  }

  public class PlusExpresionNested<N> extends PlusFluent<PlusExpresionNested<N>> implements Nested<N> {
    PlusExpresionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPlusExpresion() {
      return and();
    }

  }

  public class ConstructExpresionNested<N> extends ConstructFluent<ConstructExpresionNested<N>> implements Nested<N> {
    ConstructExpresionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endConstructExpresion() {
      return and();
    }

  }

  public class XorExpresionNested<N> extends XorFluent<XorExpresionNested<N>> implements Nested<N> {
    XorExpresionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endXorExpresion() {
      return and();
    }

  }

  public class PreIncrementExpresionNested<N> extends PreIncrementFluent<PreIncrementExpresionNested<N>> implements Nested<N> {
    PreIncrementExpresionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPreIncrementExpresion() {
      return and();
    }

  }

  public class LessThanOrEqualExpresionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExpresionNested<N>>
      implements Nested<N> {
    LessThanOrEqualExpresionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLessThanOrEqualExpresion() {
      return and();
    }

  }

  public class PositiveExpresionNested<N> extends PositiveFluent<PositiveExpresionNested<N>> implements Nested<N> {
    PositiveExpresionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPositiveExpresion() {
      return and();
    }

  }

}
