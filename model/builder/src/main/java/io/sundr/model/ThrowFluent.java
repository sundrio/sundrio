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
public class ThrowFluent<A extends ThrowFluent<A>> extends BaseFluent<A> {
  public ThrowFluent() {
  }

  public ThrowFluent(Throw instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> exception;

  protected void copyInstance(Throw instance) {
    if (instance != null) {
      this.withException(instance.getException());
    }
  }

  public Expression buildException() {
    return this.exception != null ? this.exception.build() : null;
  }

  public A withException(Expression exception) {
    if (exception == null) {
      this.exception = null;
      this._visitables.remove("exception");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(exception);
      this._visitables.get("exception").clear();
      this._visitables.get("exception").add(builder);
      this.exception = builder;
      return (A) this;
    }
  }

  public boolean hasException() {
    return this.exception != null;
  }

  public MultiplyExceptionNested<A> withNewMultiplyException() {
    return new MultiplyExceptionNested(null);
  }

  public MultiplyExceptionNested<A> withNewMultiplyExceptionLike(Multiply item) {
    return new MultiplyExceptionNested(item);
  }

  public A withNewMultiplyException(Object left, Object right) {
    return (A) withException(new Multiply(left, right));
  }

  public NewArrayExceptionNested<A> withNewNewArrayException() {
    return new NewArrayExceptionNested(null);
  }

  public NewArrayExceptionNested<A> withNewNewArrayExceptionLike(NewArray item) {
    return new NewArrayExceptionNested(item);
  }

  public A withNewNewArrayException(Class type, Integer[] sizes) {
    return (A) withException(new NewArray(type, sizes));
  }

  public InstanceOfExceptionNested<A> withNewInstanceOfException() {
    return new InstanceOfExceptionNested(null);
  }

  public InstanceOfExceptionNested<A> withNewInstanceOfExceptionLike(InstanceOf item) {
    return new InstanceOfExceptionNested(item);
  }

  public MethodCallExceptionNested<A> withNewMethodCallException() {
    return new MethodCallExceptionNested(null);
  }

  public MethodCallExceptionNested<A> withNewMethodCallExceptionLike(MethodCall item) {
    return new MethodCallExceptionNested(item);
  }

  public InverseExceptionNested<A> withNewInverseException() {
    return new InverseExceptionNested(null);
  }

  public InverseExceptionNested<A> withNewInverseExceptionLike(Inverse item) {
    return new InverseExceptionNested(item);
  }

  public IndexExceptionNested<A> withNewIndexException() {
    return new IndexExceptionNested(null);
  }

  public IndexExceptionNested<A> withNewIndexExceptionLike(Index item) {
    return new IndexExceptionNested(item);
  }

  public GreaterThanOrEqualExceptionNested<A> withNewGreaterThanOrEqualException() {
    return new GreaterThanOrEqualExceptionNested(null);
  }

  public GreaterThanOrEqualExceptionNested<A> withNewGreaterThanOrEqualExceptionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExceptionNested(item);
  }

  public A withNewGreaterThanOrEqualException(Object left, Object right) {
    return (A) withException(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndExceptionNested<A> withNewBitwiseAndException() {
    return new BitwiseAndExceptionNested(null);
  }

  public BitwiseAndExceptionNested<A> withNewBitwiseAndExceptionLike(BitwiseAnd item) {
    return new BitwiseAndExceptionNested(item);
  }

  public A withNewBitwiseAndException(Object left, Object right) {
    return (A) withException(new BitwiseAnd(left, right));
  }

  public MinusExceptionNested<A> withNewMinusException() {
    return new MinusExceptionNested(null);
  }

  public MinusExceptionNested<A> withNewMinusExceptionLike(Minus item) {
    return new MinusExceptionNested(item);
  }

  public A withNewMinusException(Object left, Object right) {
    return (A) withException(new Minus(left, right));
  }

  public LogicalOrExceptionNested<A> withNewLogicalOrException() {
    return new LogicalOrExceptionNested(null);
  }

  public LogicalOrExceptionNested<A> withNewLogicalOrExceptionLike(LogicalOr item) {
    return new LogicalOrExceptionNested(item);
  }

  public A withNewLogicalOrException(Object left, Object right) {
    return (A) withException(new LogicalOr(left, right));
  }

  public NotEqualsExceptionNested<A> withNewNotEqualsException() {
    return new NotEqualsExceptionNested(null);
  }

  public NotEqualsExceptionNested<A> withNewNotEqualsExceptionLike(NotEquals item) {
    return new NotEqualsExceptionNested(item);
  }

  public A withNewNotEqualsException(Object left, Object right) {
    return (A) withException(new NotEquals(left, right));
  }

  public DivideExceptionNested<A> withNewDivideException() {
    return new DivideExceptionNested(null);
  }

  public DivideExceptionNested<A> withNewDivideExceptionLike(Divide item) {
    return new DivideExceptionNested(item);
  }

  public A withNewDivideException(Object left, Object right) {
    return (A) withException(new Divide(left, right));
  }

  public LessThanExceptionNested<A> withNewLessThanException() {
    return new LessThanExceptionNested(null);
  }

  public LessThanExceptionNested<A> withNewLessThanExceptionLike(LessThan item) {
    return new LessThanExceptionNested(item);
  }

  public A withNewLessThanException(Object left, Object right) {
    return (A) withException(new LessThan(left, right));
  }

  public BitwiseOrExceptionNested<A> withNewBitwiseOrException() {
    return new BitwiseOrExceptionNested(null);
  }

  public BitwiseOrExceptionNested<A> withNewBitwiseOrExceptionLike(BitwiseOr item) {
    return new BitwiseOrExceptionNested(item);
  }

  public A withNewBitwiseOrException(Object left, Object right) {
    return (A) withException(new BitwiseOr(left, right));
  }

  public PropertyRefExceptionNested<A> withNewPropertyRefException() {
    return new PropertyRefExceptionNested(null);
  }

  public PropertyRefExceptionNested<A> withNewPropertyRefExceptionLike(PropertyRef item) {
    return new PropertyRefExceptionNested(item);
  }

  public RightShiftExceptionNested<A> withNewRightShiftException() {
    return new RightShiftExceptionNested(null);
  }

  public RightShiftExceptionNested<A> withNewRightShiftExceptionLike(RightShift item) {
    return new RightShiftExceptionNested(item);
  }

  public A withNewRightShiftException(Object left, Object right) {
    return (A) withException(new RightShift(left, right));
  }

  public GreaterThanExceptionNested<A> withNewGreaterThanException() {
    return new GreaterThanExceptionNested(null);
  }

  public GreaterThanExceptionNested<A> withNewGreaterThanExceptionLike(GreaterThan item) {
    return new GreaterThanExceptionNested(item);
  }

  public A withNewGreaterThanException(Object left, Object right) {
    return (A) withException(new GreaterThan(left, right));
  }

  public DeclareExceptionNested<A> withNewDeclareException() {
    return new DeclareExceptionNested(null);
  }

  public DeclareExceptionNested<A> withNewDeclareExceptionLike(Declare item) {
    return new DeclareExceptionNested(item);
  }

  public A withNewDeclareException(Class type, String name) {
    return (A) withException(new Declare(type, name));
  }

  public A withNewDeclareException(Class type, String name, Object value) {
    return (A) withException(new Declare(type, name, value));
  }

  public CastExceptionNested<A> withNewCastException() {
    return new CastExceptionNested(null);
  }

  public CastExceptionNested<A> withNewCastExceptionLike(Cast item) {
    return new CastExceptionNested(item);
  }

  public ModuloExceptionNested<A> withNewModuloException() {
    return new ModuloExceptionNested(null);
  }

  public ModuloExceptionNested<A> withNewModuloExceptionLike(Modulo item) {
    return new ModuloExceptionNested(item);
  }

  public A withNewModuloException(Object left, Object right) {
    return (A) withException(new Modulo(left, right));
  }

  public ValueRefExceptionNested<A> withNewValueRefException() {
    return new ValueRefExceptionNested(null);
  }

  public ValueRefExceptionNested<A> withNewValueRefExceptionLike(ValueRef item) {
    return new ValueRefExceptionNested(item);
  }

  public A withNewValueRefException(Object value) {
    return (A) withException(new ValueRef(value));
  }

  public LeftShiftExceptionNested<A> withNewLeftShiftException() {
    return new LeftShiftExceptionNested(null);
  }

  public LeftShiftExceptionNested<A> withNewLeftShiftExceptionLike(LeftShift item) {
    return new LeftShiftExceptionNested(item);
  }

  public A withNewLeftShiftException(Object left, Object right) {
    return (A) withException(new LeftShift(left, right));
  }

  public TernaryExceptionNested<A> withNewTernaryException() {
    return new TernaryExceptionNested(null);
  }

  public TernaryExceptionNested<A> withNewTernaryExceptionLike(Ternary item) {
    return new TernaryExceptionNested(item);
  }

  public BinaryExpressionExceptionNested<A> withNewBinaryExpressionException() {
    return new BinaryExpressionExceptionNested(null);
  }

  public BinaryExpressionExceptionNested<A> withNewBinaryExpressionExceptionLike(BinaryExpression item) {
    return new BinaryExpressionExceptionNested(item);
  }

  public EqualsExceptionNested<A> withNewEqualsException() {
    return new EqualsExceptionNested(null);
  }

  public EqualsExceptionNested<A> withNewEqualsExceptionLike(Equals item) {
    return new EqualsExceptionNested(item);
  }

  public A withNewEqualsException(Object left, Object right) {
    return (A) withException(new Equals(left, right));
  }

  public EnclosedExceptionNested<A> withNewEnclosedException() {
    return new EnclosedExceptionNested(null);
  }

  public EnclosedExceptionNested<A> withNewEnclosedExceptionLike(Enclosed item) {
    return new EnclosedExceptionNested(item);
  }

  public PreDecrementExceptionNested<A> withNewPreDecrementException() {
    return new PreDecrementExceptionNested(null);
  }

  public PreDecrementExceptionNested<A> withNewPreDecrementExceptionLike(PreDecrement item) {
    return new PreDecrementExceptionNested(item);
  }

  public PostDecrementExceptionNested<A> withNewPostDecrementException() {
    return new PostDecrementExceptionNested(null);
  }

  public PostDecrementExceptionNested<A> withNewPostDecrementExceptionLike(PostDecrement item) {
    return new PostDecrementExceptionNested(item);
  }

  public LambdaExceptionNested<A> withNewLambdaException() {
    return new LambdaExceptionNested(null);
  }

  public LambdaExceptionNested<A> withNewLambdaExceptionLike(Lambda item) {
    return new LambdaExceptionNested(item);
  }

  public NotExceptionNested<A> withNewNotException() {
    return new NotExceptionNested(null);
  }

  public NotExceptionNested<A> withNewNotExceptionLike(Not item) {
    return new NotExceptionNested(item);
  }

  public AssignExceptionNested<A> withNewAssignException() {
    return new AssignExceptionNested(null);
  }

  public AssignExceptionNested<A> withNewAssignExceptionLike(Assign item) {
    return new AssignExceptionNested(item);
  }

  public ThisExceptionNested<A> withNewThisException() {
    return new ThisExceptionNested(null);
  }

  public ThisExceptionNested<A> withNewThisExceptionLike(This item) {
    return new ThisExceptionNested(item);
  }

  public NegativeExceptionNested<A> withNewNegativeException() {
    return new NegativeExceptionNested(null);
  }

  public NegativeExceptionNested<A> withNewNegativeExceptionLike(Negative item) {
    return new NegativeExceptionNested(item);
  }

  public LogicalAndExceptionNested<A> withNewLogicalAndException() {
    return new LogicalAndExceptionNested(null);
  }

  public LogicalAndExceptionNested<A> withNewLogicalAndExceptionLike(LogicalAnd item) {
    return new LogicalAndExceptionNested(item);
  }

  public A withNewLogicalAndException(Object left, Object right) {
    return (A) withException(new LogicalAnd(left, right));
  }

  public PostIncrementExceptionNested<A> withNewPostIncrementException() {
    return new PostIncrementExceptionNested(null);
  }

  public PostIncrementExceptionNested<A> withNewPostIncrementExceptionLike(PostIncrement item) {
    return new PostIncrementExceptionNested(item);
  }

  public RightUnsignedShiftExceptionNested<A> withNewRightUnsignedShiftException() {
    return new RightUnsignedShiftExceptionNested(null);
  }

  public RightUnsignedShiftExceptionNested<A> withNewRightUnsignedShiftExceptionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExceptionNested(item);
  }

  public A withNewRightUnsignedShiftException(Object left, Object right) {
    return (A) withException(new RightUnsignedShift(left, right));
  }

  public PlusExceptionNested<A> withNewPlusException() {
    return new PlusExceptionNested(null);
  }

  public PlusExceptionNested<A> withNewPlusExceptionLike(Plus item) {
    return new PlusExceptionNested(item);
  }

  public A withNewPlusException(Object left, Object right) {
    return (A) withException(new Plus(left, right));
  }

  public ConstructExceptionNested<A> withNewConstructException() {
    return new ConstructExceptionNested(null);
  }

  public ConstructExceptionNested<A> withNewConstructExceptionLike(Construct item) {
    return new ConstructExceptionNested(item);
  }

  public XorExceptionNested<A> withNewXorException() {
    return new XorExceptionNested(null);
  }

  public XorExceptionNested<A> withNewXorExceptionLike(Xor item) {
    return new XorExceptionNested(item);
  }

  public A withNewXorException(Object left, Object right) {
    return (A) withException(new Xor(left, right));
  }

  public PreIncrementExceptionNested<A> withNewPreIncrementException() {
    return new PreIncrementExceptionNested(null);
  }

  public PreIncrementExceptionNested<A> withNewPreIncrementExceptionLike(PreIncrement item) {
    return new PreIncrementExceptionNested(item);
  }

  public PropertyExceptionNested<A> withNewPropertyException() {
    return new PropertyExceptionNested(null);
  }

  public PropertyExceptionNested<A> withNewPropertyExceptionLike(Property item) {
    return new PropertyExceptionNested(item);
  }

  public LessThanOrEqualExceptionNested<A> withNewLessThanOrEqualException() {
    return new LessThanOrEqualExceptionNested(null);
  }

  public LessThanOrEqualExceptionNested<A> withNewLessThanOrEqualExceptionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExceptionNested(item);
  }

  public A withNewLessThanOrEqualException(Object left, Object right) {
    return (A) withException(new LessThanOrEqual(left, right));
  }

  public PositiveExceptionNested<A> withNewPositiveException() {
    return new PositiveExceptionNested(null);
  }

  public PositiveExceptionNested<A> withNewPositiveExceptionLike(Positive item) {
    return new PositiveExceptionNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ThrowFluent that = (ThrowFluent) o;
    if (!java.util.Objects.equals(exception, that.exception))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(exception, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (exception != null) {
      sb.append("exception:");
      sb.append(exception);
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
      case "io.sundr.model." + "Property":
        return (VisitableBuilder<T, ?>) new PropertyBuilder((Property) item);
      case "io.sundr.model." + "LessThanOrEqual":
        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);
      case "io.sundr.model." + "Positive":
        return (VisitableBuilder<T, ?>) new PositiveBuilder((Positive) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class MultiplyExceptionNested<N> extends MultiplyFluent<MultiplyExceptionNested<N>> implements Nested<N> {
    MultiplyExceptionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endMultiplyException() {
      return and();
    }

  }

  public class NewArrayExceptionNested<N> extends NewArrayFluent<NewArrayExceptionNested<N>> implements Nested<N> {
    NewArrayExceptionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNewArrayException() {
      return and();
    }

  }

  public class InstanceOfExceptionNested<N> extends InstanceOfFluent<InstanceOfExceptionNested<N>> implements Nested<N> {
    InstanceOfExceptionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endInstanceOfException() {
      return and();
    }

  }

  public class MethodCallExceptionNested<N> extends MethodCallFluent<MethodCallExceptionNested<N>> implements Nested<N> {
    MethodCallExceptionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endMethodCallException() {
      return and();
    }

  }

  public class InverseExceptionNested<N> extends InverseFluent<InverseExceptionNested<N>> implements Nested<N> {
    InverseExceptionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endInverseException() {
      return and();
    }

  }

  public class IndexExceptionNested<N> extends IndexFluent<IndexExceptionNested<N>> implements Nested<N> {
    IndexExceptionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endIndexException() {
      return and();
    }

  }

  public class GreaterThanOrEqualExceptionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExceptionNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualExceptionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endGreaterThanOrEqualException() {
      return and();
    }

  }

  public class BitwiseAndExceptionNested<N> extends BitwiseAndFluent<BitwiseAndExceptionNested<N>> implements Nested<N> {
    BitwiseAndExceptionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endBitwiseAndException() {
      return and();
    }

  }

  public class MinusExceptionNested<N> extends MinusFluent<MinusExceptionNested<N>> implements Nested<N> {
    MinusExceptionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endMinusException() {
      return and();
    }

  }

  public class LogicalOrExceptionNested<N> extends LogicalOrFluent<LogicalOrExceptionNested<N>> implements Nested<N> {
    LogicalOrExceptionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLogicalOrException() {
      return and();
    }

  }

  public class NotEqualsExceptionNested<N> extends NotEqualsFluent<NotEqualsExceptionNested<N>> implements Nested<N> {
    NotEqualsExceptionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNotEqualsException() {
      return and();
    }

  }

  public class DivideExceptionNested<N> extends DivideFluent<DivideExceptionNested<N>> implements Nested<N> {
    DivideExceptionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endDivideException() {
      return and();
    }

  }

  public class LessThanExceptionNested<N> extends LessThanFluent<LessThanExceptionNested<N>> implements Nested<N> {
    LessThanExceptionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLessThanException() {
      return and();
    }

  }

  public class BitwiseOrExceptionNested<N> extends BitwiseOrFluent<BitwiseOrExceptionNested<N>> implements Nested<N> {
    BitwiseOrExceptionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endBitwiseOrException() {
      return and();
    }

  }

  public class PropertyRefExceptionNested<N> extends PropertyRefFluent<PropertyRefExceptionNested<N>> implements Nested<N> {
    PropertyRefExceptionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPropertyRefException() {
      return and();
    }

  }

  public class RightShiftExceptionNested<N> extends RightShiftFluent<RightShiftExceptionNested<N>> implements Nested<N> {
    RightShiftExceptionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endRightShiftException() {
      return and();
    }

  }

  public class GreaterThanExceptionNested<N> extends GreaterThanFluent<GreaterThanExceptionNested<N>> implements Nested<N> {
    GreaterThanExceptionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endGreaterThanException() {
      return and();
    }

  }

  public class DeclareExceptionNested<N> extends DeclareFluent<DeclareExceptionNested<N>> implements Nested<N> {
    DeclareExceptionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endDeclareException() {
      return and();
    }

  }

  public class CastExceptionNested<N> extends CastFluent<CastExceptionNested<N>> implements Nested<N> {
    CastExceptionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endCastException() {
      return and();
    }

  }

  public class ModuloExceptionNested<N> extends ModuloFluent<ModuloExceptionNested<N>> implements Nested<N> {
    ModuloExceptionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endModuloException() {
      return and();
    }

  }

  public class ValueRefExceptionNested<N> extends ValueRefFluent<ValueRefExceptionNested<N>> implements Nested<N> {
    ValueRefExceptionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endValueRefException() {
      return and();
    }

  }

  public class LeftShiftExceptionNested<N> extends LeftShiftFluent<LeftShiftExceptionNested<N>> implements Nested<N> {
    LeftShiftExceptionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLeftShiftException() {
      return and();
    }

  }

  public class TernaryExceptionNested<N> extends TernaryFluent<TernaryExceptionNested<N>> implements Nested<N> {
    TernaryExceptionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endTernaryException() {
      return and();
    }

  }

  public class BinaryExpressionExceptionNested<N> extends BinaryExpressionFluent<BinaryExpressionExceptionNested<N>>
      implements Nested<N> {
    BinaryExpressionExceptionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endBinaryExpressionException() {
      return and();
    }

  }

  public class EqualsExceptionNested<N> extends EqualsFluent<EqualsExceptionNested<N>> implements Nested<N> {
    EqualsExceptionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endEqualsException() {
      return and();
    }

  }

  public class EnclosedExceptionNested<N> extends EnclosedFluent<EnclosedExceptionNested<N>> implements Nested<N> {
    EnclosedExceptionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endEnclosedException() {
      return and();
    }

  }

  public class PreDecrementExceptionNested<N> extends PreDecrementFluent<PreDecrementExceptionNested<N>> implements Nested<N> {
    PreDecrementExceptionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPreDecrementException() {
      return and();
    }

  }

  public class PostDecrementExceptionNested<N> extends PostDecrementFluent<PostDecrementExceptionNested<N>>
      implements Nested<N> {
    PostDecrementExceptionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPostDecrementException() {
      return and();
    }

  }

  public class LambdaExceptionNested<N> extends LambdaFluent<LambdaExceptionNested<N>> implements Nested<N> {
    LambdaExceptionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLambdaException() {
      return and();
    }

  }

  public class NotExceptionNested<N> extends NotFluent<NotExceptionNested<N>> implements Nested<N> {
    NotExceptionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNotException() {
      return and();
    }

  }

  public class AssignExceptionNested<N> extends AssignFluent<AssignExceptionNested<N>> implements Nested<N> {
    AssignExceptionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endAssignException() {
      return and();
    }

  }

  public class ThisExceptionNested<N> extends ThisFluent<ThisExceptionNested<N>> implements Nested<N> {
    ThisExceptionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endThisException() {
      return and();
    }

  }

  public class NegativeExceptionNested<N> extends NegativeFluent<NegativeExceptionNested<N>> implements Nested<N> {
    NegativeExceptionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNegativeException() {
      return and();
    }

  }

  public class LogicalAndExceptionNested<N> extends LogicalAndFluent<LogicalAndExceptionNested<N>> implements Nested<N> {
    LogicalAndExceptionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLogicalAndException() {
      return and();
    }

  }

  public class PostIncrementExceptionNested<N> extends PostIncrementFluent<PostIncrementExceptionNested<N>>
      implements Nested<N> {
    PostIncrementExceptionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPostIncrementException() {
      return and();
    }

  }

  public class RightUnsignedShiftExceptionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExceptionNested<N>>
      implements Nested<N> {
    RightUnsignedShiftExceptionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endRightUnsignedShiftException() {
      return and();
    }

  }

  public class PlusExceptionNested<N> extends PlusFluent<PlusExceptionNested<N>> implements Nested<N> {
    PlusExceptionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPlusException() {
      return and();
    }

  }

  public class ConstructExceptionNested<N> extends ConstructFluent<ConstructExceptionNested<N>> implements Nested<N> {
    ConstructExceptionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endConstructException() {
      return and();
    }

  }

  public class XorExceptionNested<N> extends XorFluent<XorExceptionNested<N>> implements Nested<N> {
    XorExceptionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endXorException() {
      return and();
    }

  }

  public class PreIncrementExceptionNested<N> extends PreIncrementFluent<PreIncrementExceptionNested<N>> implements Nested<N> {
    PreIncrementExceptionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPreIncrementException() {
      return and();
    }

  }

  public class PropertyExceptionNested<N> extends PropertyFluent<PropertyExceptionNested<N>> implements Nested<N> {
    PropertyExceptionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPropertyException() {
      return and();
    }

  }

  public class LessThanOrEqualExceptionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExceptionNested<N>>
      implements Nested<N> {
    LessThanOrEqualExceptionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLessThanOrEqualException() {
      return and();
    }

  }

  public class PositiveExceptionNested<N> extends PositiveFluent<PositiveExceptionNested<N>> implements Nested<N> {
    PositiveExceptionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPositiveException() {
      return and();
    }

  }

}
