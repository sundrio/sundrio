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

  private VisitableBuilder<? extends Expression, ?> exception;

  public ThrowFluent() {
  }

  public ThrowFluent(Throw instance) {
    this.copyInstance(instance);
  }

  public Expression buildException() {
    return this.exception != null ? this.exception.build() : null;
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

  protected void copyInstance(Throw instance) {
    if (instance != null) {
      this.withException(instance.getException());
    }
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

  public boolean hasException() {
    return this.exception != null;
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

  public AssignExceptionNested<A> withNewAssignException() {
    return new AssignExceptionNested(null);
  }

  public AssignExceptionNested<A> withNewAssignExceptionLike(Assign item) {
    return new AssignExceptionNested(item);
  }

  public BinaryExpressionExceptionNested<A> withNewBinaryExpressionException() {
    return new BinaryExpressionExceptionNested(null);
  }

  public BinaryExpressionExceptionNested<A> withNewBinaryExpressionExceptionLike(BinaryExpression item) {
    return new BinaryExpressionExceptionNested(item);
  }

  public BitwiseAndExceptionNested<A> withNewBitwiseAndException() {
    return new BitwiseAndExceptionNested(null);
  }

  public A withNewBitwiseAndException(Object left, Object right) {
    return (A) withException(new BitwiseAnd(left, right));
  }

  public BitwiseAndExceptionNested<A> withNewBitwiseAndExceptionLike(BitwiseAnd item) {
    return new BitwiseAndExceptionNested(item);
  }

  public BitwiseOrExceptionNested<A> withNewBitwiseOrException() {
    return new BitwiseOrExceptionNested(null);
  }

  public A withNewBitwiseOrException(Object left, Object right) {
    return (A) withException(new BitwiseOr(left, right));
  }

  public BitwiseOrExceptionNested<A> withNewBitwiseOrExceptionLike(BitwiseOr item) {
    return new BitwiseOrExceptionNested(item);
  }

  public CastExceptionNested<A> withNewCastException() {
    return new CastExceptionNested(null);
  }

  public CastExceptionNested<A> withNewCastExceptionLike(Cast item) {
    return new CastExceptionNested(item);
  }

  public ClassRefExceptionNested<A> withNewClassRefException() {
    return new ClassRefExceptionNested(null);
  }

  public ClassRefExceptionNested<A> withNewClassRefExceptionLike(ClassRef item) {
    return new ClassRefExceptionNested(item);
  }

  public ConstructExceptionNested<A> withNewConstructException() {
    return new ConstructExceptionNested(null);
  }

  public ConstructExceptionNested<A> withNewConstructExceptionLike(Construct item) {
    return new ConstructExceptionNested(item);
  }

  public ContextRefExceptionNested<A> withNewContextRefException() {
    return new ContextRefExceptionNested(null);
  }

  public A withNewContextRefException(String name) {
    return (A) withException(new ContextRef(name));
  }

  public ContextRefExceptionNested<A> withNewContextRefExceptionLike(ContextRef item) {
    return new ContextRefExceptionNested(item);
  }

  public DeclareExceptionNested<A> withNewDeclareException() {
    return new DeclareExceptionNested(null);
  }

  public A withNewDeclareException(Class type, String name) {
    return (A) withException(new Declare(type, name));
  }

  public A withNewDeclareException(Class type, String name, Object value) {
    return (A) withException(new Declare(type, name, value));
  }

  public DeclareExceptionNested<A> withNewDeclareExceptionLike(Declare item) {
    return new DeclareExceptionNested(item);
  }

  public DivideExceptionNested<A> withNewDivideException() {
    return new DivideExceptionNested(null);
  }

  public A withNewDivideException(Object left, Object right) {
    return (A) withException(new Divide(left, right));
  }

  public DivideExceptionNested<A> withNewDivideExceptionLike(Divide item) {
    return new DivideExceptionNested(item);
  }

  public DotClassExceptionNested<A> withNewDotClassException() {
    return new DotClassExceptionNested(null);
  }

  public DotClassExceptionNested<A> withNewDotClassExceptionLike(DotClass item) {
    return new DotClassExceptionNested(item);
  }

  public EmptyExceptionNested<A> withNewEmptyException() {
    return new EmptyExceptionNested(null);
  }

  public EmptyExceptionNested<A> withNewEmptyExceptionLike(Empty item) {
    return new EmptyExceptionNested(item);
  }

  public EnclosedExceptionNested<A> withNewEnclosedException() {
    return new EnclosedExceptionNested(null);
  }

  public EnclosedExceptionNested<A> withNewEnclosedExceptionLike(Enclosed item) {
    return new EnclosedExceptionNested(item);
  }

  public EqualsExceptionNested<A> withNewEqualsException() {
    return new EqualsExceptionNested(null);
  }

  public A withNewEqualsException(Object left, Object right) {
    return (A) withException(new Equals(left, right));
  }

  public EqualsExceptionNested<A> withNewEqualsExceptionLike(Equals item) {
    return new EqualsExceptionNested(item);
  }

  public GreaterThanExceptionNested<A> withNewGreaterThanException() {
    return new GreaterThanExceptionNested(null);
  }

  public A withNewGreaterThanException(Object left, Object right) {
    return (A) withException(new GreaterThan(left, right));
  }

  public GreaterThanExceptionNested<A> withNewGreaterThanExceptionLike(GreaterThan item) {
    return new GreaterThanExceptionNested(item);
  }

  public GreaterThanOrEqualExceptionNested<A> withNewGreaterThanOrEqualException() {
    return new GreaterThanOrEqualExceptionNested(null);
  }

  public A withNewGreaterThanOrEqualException(Object left, Object right) {
    return (A) withException(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualExceptionNested<A> withNewGreaterThanOrEqualExceptionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExceptionNested(item);
  }

  public IndexExceptionNested<A> withNewIndexException() {
    return new IndexExceptionNested(null);
  }

  public IndexExceptionNested<A> withNewIndexExceptionLike(Index item) {
    return new IndexExceptionNested(item);
  }

  public InstanceOfExceptionNested<A> withNewInstanceOfException() {
    return new InstanceOfExceptionNested(null);
  }

  public InstanceOfExceptionNested<A> withNewInstanceOfExceptionLike(InstanceOf item) {
    return new InstanceOfExceptionNested(item);
  }

  public InverseExceptionNested<A> withNewInverseException() {
    return new InverseExceptionNested(null);
  }

  public InverseExceptionNested<A> withNewInverseExceptionLike(Inverse item) {
    return new InverseExceptionNested(item);
  }

  public LambdaExceptionNested<A> withNewLambdaException() {
    return new LambdaExceptionNested(null);
  }

  public LambdaExceptionNested<A> withNewLambdaExceptionLike(Lambda item) {
    return new LambdaExceptionNested(item);
  }

  public LeftShiftExceptionNested<A> withNewLeftShiftException() {
    return new LeftShiftExceptionNested(null);
  }

  public A withNewLeftShiftException(Object left, Object right) {
    return (A) withException(new LeftShift(left, right));
  }

  public LeftShiftExceptionNested<A> withNewLeftShiftExceptionLike(LeftShift item) {
    return new LeftShiftExceptionNested(item);
  }

  public LessThanExceptionNested<A> withNewLessThanException() {
    return new LessThanExceptionNested(null);
  }

  public A withNewLessThanException(Object left, Object right) {
    return (A) withException(new LessThan(left, right));
  }

  public LessThanExceptionNested<A> withNewLessThanExceptionLike(LessThan item) {
    return new LessThanExceptionNested(item);
  }

  public LessThanOrEqualExceptionNested<A> withNewLessThanOrEqualException() {
    return new LessThanOrEqualExceptionNested(null);
  }

  public A withNewLessThanOrEqualException(Object left, Object right) {
    return (A) withException(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualExceptionNested<A> withNewLessThanOrEqualExceptionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExceptionNested(item);
  }

  public LogicalAndExceptionNested<A> withNewLogicalAndException() {
    return new LogicalAndExceptionNested(null);
  }

  public A withNewLogicalAndException(Object left, Object right) {
    return (A) withException(new LogicalAnd(left, right));
  }

  public LogicalAndExceptionNested<A> withNewLogicalAndExceptionLike(LogicalAnd item) {
    return new LogicalAndExceptionNested(item);
  }

  public LogicalOrExceptionNested<A> withNewLogicalOrException() {
    return new LogicalOrExceptionNested(null);
  }

  public A withNewLogicalOrException(Object left, Object right) {
    return (A) withException(new LogicalOr(left, right));
  }

  public LogicalOrExceptionNested<A> withNewLogicalOrExceptionLike(LogicalOr item) {
    return new LogicalOrExceptionNested(item);
  }

  public MethodCallExceptionNested<A> withNewMethodCallException() {
    return new MethodCallExceptionNested(null);
  }

  public MethodCallExceptionNested<A> withNewMethodCallExceptionLike(MethodCall item) {
    return new MethodCallExceptionNested(item);
  }

  public MinusExceptionNested<A> withNewMinusException() {
    return new MinusExceptionNested(null);
  }

  public A withNewMinusException(Object left, Object right) {
    return (A) withException(new Minus(left, right));
  }

  public MinusExceptionNested<A> withNewMinusExceptionLike(Minus item) {
    return new MinusExceptionNested(item);
  }

  public ModuloExceptionNested<A> withNewModuloException() {
    return new ModuloExceptionNested(null);
  }

  public A withNewModuloException(Object left, Object right) {
    return (A) withException(new Modulo(left, right));
  }

  public ModuloExceptionNested<A> withNewModuloExceptionLike(Modulo item) {
    return new ModuloExceptionNested(item);
  }

  public MultiplyExceptionNested<A> withNewMultiplyException() {
    return new MultiplyExceptionNested(null);
  }

  public A withNewMultiplyException(Object left, Object right) {
    return (A) withException(new Multiply(left, right));
  }

  public MultiplyExceptionNested<A> withNewMultiplyExceptionLike(Multiply item) {
    return new MultiplyExceptionNested(item);
  }

  public NegativeExceptionNested<A> withNewNegativeException() {
    return new NegativeExceptionNested(null);
  }

  public NegativeExceptionNested<A> withNewNegativeExceptionLike(Negative item) {
    return new NegativeExceptionNested(item);
  }

  public NewArrayExceptionNested<A> withNewNewArrayException() {
    return new NewArrayExceptionNested(null);
  }

  public A withNewNewArrayException(Class type, Integer[] sizes) {
    return (A) withException(new NewArray(type, sizes));
  }

  public NewArrayExceptionNested<A> withNewNewArrayExceptionLike(NewArray item) {
    return new NewArrayExceptionNested(item);
  }

  public NotEqualsExceptionNested<A> withNewNotEqualsException() {
    return new NotEqualsExceptionNested(null);
  }

  public A withNewNotEqualsException(Object left, Object right) {
    return (A) withException(new NotEquals(left, right));
  }

  public NotEqualsExceptionNested<A> withNewNotEqualsExceptionLike(NotEquals item) {
    return new NotEqualsExceptionNested(item);
  }

  public NotExceptionNested<A> withNewNotException() {
    return new NotExceptionNested(null);
  }

  public NotExceptionNested<A> withNewNotExceptionLike(Not item) {
    return new NotExceptionNested(item);
  }

  public PlusExceptionNested<A> withNewPlusException() {
    return new PlusExceptionNested(null);
  }

  public A withNewPlusException(Object left, Object right) {
    return (A) withException(new Plus(left, right));
  }

  public PlusExceptionNested<A> withNewPlusExceptionLike(Plus item) {
    return new PlusExceptionNested(item);
  }

  public PositiveExceptionNested<A> withNewPositiveException() {
    return new PositiveExceptionNested(null);
  }

  public PositiveExceptionNested<A> withNewPositiveExceptionLike(Positive item) {
    return new PositiveExceptionNested(item);
  }

  public PostDecrementExceptionNested<A> withNewPostDecrementException() {
    return new PostDecrementExceptionNested(null);
  }

  public PostDecrementExceptionNested<A> withNewPostDecrementExceptionLike(PostDecrement item) {
    return new PostDecrementExceptionNested(item);
  }

  public PostIncrementExceptionNested<A> withNewPostIncrementException() {
    return new PostIncrementExceptionNested(null);
  }

  public PostIncrementExceptionNested<A> withNewPostIncrementExceptionLike(PostIncrement item) {
    return new PostIncrementExceptionNested(item);
  }

  public PreDecrementExceptionNested<A> withNewPreDecrementException() {
    return new PreDecrementExceptionNested(null);
  }

  public PreDecrementExceptionNested<A> withNewPreDecrementExceptionLike(PreDecrement item) {
    return new PreDecrementExceptionNested(item);
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

  public PropertyRefExceptionNested<A> withNewPropertyRefException() {
    return new PropertyRefExceptionNested(null);
  }

  public PropertyRefExceptionNested<A> withNewPropertyRefExceptionLike(PropertyRef item) {
    return new PropertyRefExceptionNested(item);
  }

  public RightShiftExceptionNested<A> withNewRightShiftException() {
    return new RightShiftExceptionNested(null);
  }

  public A withNewRightShiftException(Object left, Object right) {
    return (A) withException(new RightShift(left, right));
  }

  public RightShiftExceptionNested<A> withNewRightShiftExceptionLike(RightShift item) {
    return new RightShiftExceptionNested(item);
  }

  public RightUnsignedShiftExceptionNested<A> withNewRightUnsignedShiftException() {
    return new RightUnsignedShiftExceptionNested(null);
  }

  public A withNewRightUnsignedShiftException(Object left, Object right) {
    return (A) withException(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftExceptionNested<A> withNewRightUnsignedShiftExceptionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExceptionNested(item);
  }

  public SuperExceptionNested<A> withNewSuperException() {
    return new SuperExceptionNested(null);
  }

  public SuperExceptionNested<A> withNewSuperExceptionLike(Super item) {
    return new SuperExceptionNested(item);
  }

  public TernaryExceptionNested<A> withNewTernaryException() {
    return new TernaryExceptionNested(null);
  }

  public TernaryExceptionNested<A> withNewTernaryExceptionLike(Ternary item) {
    return new TernaryExceptionNested(item);
  }

  public ThisExceptionNested<A> withNewThisException() {
    return new ThisExceptionNested(null);
  }

  public ThisExceptionNested<A> withNewThisExceptionLike(This item) {
    return new ThisExceptionNested(item);
  }

  public ValueRefExceptionNested<A> withNewValueRefException() {
    return new ValueRefExceptionNested(null);
  }

  public A withNewValueRefException(Object value) {
    return (A) withException(new ValueRef(value));
  }

  public ValueRefExceptionNested<A> withNewValueRefExceptionLike(ValueRef item) {
    return new ValueRefExceptionNested(item);
  }

  public XorExceptionNested<A> withNewXorException() {
    return new XorExceptionNested(null);
  }

  public A withNewXorException(Object left, Object right) {
    return (A) withException(new Xor(left, right));
  }

  public XorExceptionNested<A> withNewXorExceptionLike(Xor item) {
    return new XorExceptionNested(item);
  }

  public class AssignExceptionNested<N> extends AssignFluent<AssignExceptionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignExceptionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endAssignException() {
      return and();
    }

  }

  public class BinaryExpressionExceptionNested<N> extends BinaryExpressionFluent<BinaryExpressionExceptionNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionExceptionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endBinaryExpressionException() {
      return and();
    }

  }

  public class BitwiseAndExceptionNested<N> extends BitwiseAndFluent<BitwiseAndExceptionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndExceptionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endBitwiseAndException() {
      return and();
    }

  }

  public class BitwiseOrExceptionNested<N> extends BitwiseOrFluent<BitwiseOrExceptionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrExceptionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endBitwiseOrException() {
      return and();
    }

  }

  public class CastExceptionNested<N> extends CastFluent<CastExceptionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastExceptionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endCastException() {
      return and();
    }

  }

  public class ClassRefExceptionNested<N> extends ClassRefFluent<ClassRefExceptionNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefExceptionNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endClassRefException() {
      return and();
    }

  }

  public class ConstructExceptionNested<N> extends ConstructFluent<ConstructExceptionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructExceptionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endConstructException() {
      return and();
    }

  }

  public class ContextRefExceptionNested<N> extends ContextRefFluent<ContextRefExceptionNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefExceptionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endContextRefException() {
      return and();
    }

  }

  public class DeclareExceptionNested<N> extends DeclareFluent<DeclareExceptionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareExceptionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endDeclareException() {
      return and();
    }

  }

  public class DivideExceptionNested<N> extends DivideFluent<DivideExceptionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideExceptionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endDivideException() {
      return and();
    }

  }

  public class DotClassExceptionNested<N> extends DotClassFluent<DotClassExceptionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassExceptionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endDotClassException() {
      return and();
    }

  }

  public class EmptyExceptionNested<N> extends EmptyFluent<EmptyExceptionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyExceptionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endEmptyException() {
      return and();
    }

  }

  public class EnclosedExceptionNested<N> extends EnclosedFluent<EnclosedExceptionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedExceptionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endEnclosedException() {
      return and();
    }

  }

  public class EqualsExceptionNested<N> extends EqualsFluent<EqualsExceptionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsExceptionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endEqualsException() {
      return and();
    }

  }

  public class GreaterThanExceptionNested<N> extends GreaterThanFluent<GreaterThanExceptionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanExceptionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endGreaterThanException() {
      return and();
    }

  }

  public class GreaterThanOrEqualExceptionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExceptionNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualExceptionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endGreaterThanOrEqualException() {
      return and();
    }

  }

  public class IndexExceptionNested<N> extends IndexFluent<IndexExceptionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexExceptionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endIndexException() {
      return and();
    }

  }

  public class InstanceOfExceptionNested<N> extends InstanceOfFluent<InstanceOfExceptionNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfExceptionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endInstanceOfException() {
      return and();
    }

  }

  public class InverseExceptionNested<N> extends InverseFluent<InverseExceptionNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseExceptionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endInverseException() {
      return and();
    }

  }

  public class LambdaExceptionNested<N> extends LambdaFluent<LambdaExceptionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaExceptionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLambdaException() {
      return and();
    }

  }

  public class LeftShiftExceptionNested<N> extends LeftShiftFluent<LeftShiftExceptionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftExceptionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLeftShiftException() {
      return and();
    }

  }

  public class LessThanExceptionNested<N> extends LessThanFluent<LessThanExceptionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanExceptionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLessThanException() {
      return and();
    }

  }

  public class LessThanOrEqualExceptionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExceptionNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualExceptionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLessThanOrEqualException() {
      return and();
    }

  }

  public class LogicalAndExceptionNested<N> extends LogicalAndFluent<LogicalAndExceptionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndExceptionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLogicalAndException() {
      return and();
    }

  }

  public class LogicalOrExceptionNested<N> extends LogicalOrFluent<LogicalOrExceptionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrExceptionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endLogicalOrException() {
      return and();
    }

  }

  public class MethodCallExceptionNested<N> extends MethodCallFluent<MethodCallExceptionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallExceptionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endMethodCallException() {
      return and();
    }

  }

  public class MinusExceptionNested<N> extends MinusFluent<MinusExceptionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusExceptionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endMinusException() {
      return and();
    }

  }

  public class ModuloExceptionNested<N> extends ModuloFluent<ModuloExceptionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloExceptionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endModuloException() {
      return and();
    }

  }

  public class MultiplyExceptionNested<N> extends MultiplyFluent<MultiplyExceptionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyExceptionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endMultiplyException() {
      return and();
    }

  }

  public class NegativeExceptionNested<N> extends NegativeFluent<NegativeExceptionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeExceptionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNegativeException() {
      return and();
    }

  }

  public class NewArrayExceptionNested<N> extends NewArrayFluent<NewArrayExceptionNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayExceptionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNewArrayException() {
      return and();
    }

  }

  public class NotEqualsExceptionNested<N> extends NotEqualsFluent<NotEqualsExceptionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsExceptionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNotEqualsException() {
      return and();
    }

  }

  public class NotExceptionNested<N> extends NotFluent<NotExceptionNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotExceptionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endNotException() {
      return and();
    }

  }

  public class PlusExceptionNested<N> extends PlusFluent<PlusExceptionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusExceptionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPlusException() {
      return and();
    }

  }

  public class PositiveExceptionNested<N> extends PositiveFluent<PositiveExceptionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveExceptionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPositiveException() {
      return and();
    }

  }

  public class PostDecrementExceptionNested<N> extends PostDecrementFluent<PostDecrementExceptionNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementExceptionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPostDecrementException() {
      return and();
    }

  }

  public class PostIncrementExceptionNested<N> extends PostIncrementFluent<PostIncrementExceptionNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementExceptionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPostIncrementException() {
      return and();
    }

  }

  public class PreDecrementExceptionNested<N> extends PreDecrementFluent<PreDecrementExceptionNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementExceptionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPreDecrementException() {
      return and();
    }

  }

  public class PreIncrementExceptionNested<N> extends PreIncrementFluent<PreIncrementExceptionNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementExceptionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPreIncrementException() {
      return and();
    }

  }

  public class PropertyExceptionNested<N> extends PropertyFluent<PropertyExceptionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyExceptionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPropertyException() {
      return and();
    }

  }

  public class PropertyRefExceptionNested<N> extends PropertyRefFluent<PropertyRefExceptionNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefExceptionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endPropertyRefException() {
      return and();
    }

  }

  public class RightShiftExceptionNested<N> extends RightShiftFluent<RightShiftExceptionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftExceptionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endRightShiftException() {
      return and();
    }

  }

  public class RightUnsignedShiftExceptionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExceptionNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftExceptionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endRightUnsignedShiftException() {
      return and();
    }

  }

  public class SuperExceptionNested<N> extends SuperFluent<SuperExceptionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperExceptionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endSuperException() {
      return and();
    }

  }

  public class TernaryExceptionNested<N> extends TernaryFluent<TernaryExceptionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryExceptionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endTernaryException() {
      return and();
    }

  }

  public class ThisExceptionNested<N> extends ThisFluent<ThisExceptionNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisExceptionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endThisException() {
      return and();
    }

  }

  public class ValueRefExceptionNested<N> extends ValueRefFluent<ValueRefExceptionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefExceptionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endValueRefException() {
      return and();
    }

  }

  public class XorExceptionNested<N> extends XorFluent<XorExceptionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorExceptionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ThrowFluent.this.withException(builder.build());
    }

    public N endXorException() {
      return and();
    }

  }
}
