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
public class PreIncrementFluent<A extends io.sundr.model.PreIncrementFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> expression;

  public PreIncrementFluent() {
  }

  public PreIncrementFluent(PreIncrement instance) {
    this.copyInstance(instance);
  }

  public Expression buildExpression() {
    return this.expression != null ? this.expression.build() : null;
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

  protected void copyInstance(PreIncrement instance) {
    if (instance != null) {
      this.withExpression(instance.getExpression());
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
    PreIncrementFluent that = (PreIncrementFluent) o;
    if (!(Objects.equals(expression, that.expression))) {
      return false;
    }
    return true;
  }

  public boolean hasExpression() {
    return this.expression != null;
  }

  public int hashCode() {
    return Objects.hash(expression);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(expression == null)) {
      sb.append("expression:");
      sb.append(expression);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withExpression(Expression expression) {
    if (expression == null) {
      this.expression = null;
      this._visitables.remove("expression");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(expression);
      this._visitables.get("expression").clear();
      this._visitables.get("expression").add(builder);
      this.expression = builder;
      return (A) this;
    }
  }

  public AssignExpressionNested<A> withNewAssignExpression() {
    return new AssignExpressionNested(null);
  }

  public AssignExpressionNested<A> withNewAssignExpressionLike(Assign item) {
    return new AssignExpressionNested(item);
  }

  public BinaryExpressionNested<A> withNewBinaryExpression() {
    return new BinaryExpressionNested(null);
  }

  public BinaryExpressionNested<A> withNewBinaryExpressionLike(BinaryExpression item) {
    return new BinaryExpressionNested(item);
  }

  public BitwiseAndExpressionNested<A> withNewBitwiseAndExpression() {
    return new BitwiseAndExpressionNested(null);
  }

  public A withNewBitwiseAndExpression(Object left, Object right) {
    return (A) this.withExpression(new BitwiseAnd(left, right));
  }

  public BitwiseAndExpressionNested<A> withNewBitwiseAndExpressionLike(BitwiseAnd item) {
    return new BitwiseAndExpressionNested(item);
  }

  public BitwiseOrExpressionNested<A> withNewBitwiseOrExpression() {
    return new BitwiseOrExpressionNested(null);
  }

  public A withNewBitwiseOrExpression(Object left, Object right) {
    return (A) this.withExpression(new BitwiseOr(left, right));
  }

  public BitwiseOrExpressionNested<A> withNewBitwiseOrExpressionLike(BitwiseOr item) {
    return new BitwiseOrExpressionNested(item);
  }

  public CastExpressionNested<A> withNewCastExpression() {
    return new CastExpressionNested(null);
  }

  public CastExpressionNested<A> withNewCastExpressionLike(Cast item) {
    return new CastExpressionNested(item);
  }

  public ClassRefExpressionNested<A> withNewClassRefExpression() {
    return new ClassRefExpressionNested(null);
  }

  public ClassRefExpressionNested<A> withNewClassRefExpressionLike(ClassRef item) {
    return new ClassRefExpressionNested(item);
  }

  public ConstructExpressionNested<A> withNewConstructExpression() {
    return new ConstructExpressionNested(null);
  }

  public ConstructExpressionNested<A> withNewConstructExpressionLike(Construct item) {
    return new ConstructExpressionNested(item);
  }

  public ContextRefExpressionNested<A> withNewContextRefExpression() {
    return new ContextRefExpressionNested(null);
  }

  public A withNewContextRefExpression(String name) {
    return (A) this.withExpression(new ContextRef(name));
  }

  public ContextRefExpressionNested<A> withNewContextRefExpressionLike(ContextRef item) {
    return new ContextRefExpressionNested(item);
  }

  public DeclareExpressionNested<A> withNewDeclareExpression() {
    return new DeclareExpressionNested(null);
  }

  public A withNewDeclareExpression(Class type, String name) {
    return (A) this.withExpression(new Declare(type, name));
  }

  public A withNewDeclareExpression(Class type, String name, Object value) {
    return (A) this.withExpression(new Declare(type, name, value));
  }

  public DeclareExpressionNested<A> withNewDeclareExpressionLike(Declare item) {
    return new DeclareExpressionNested(item);
  }

  public DivideExpressionNested<A> withNewDivideExpression() {
    return new DivideExpressionNested(null);
  }

  public A withNewDivideExpression(Object left, Object right) {
    return (A) this.withExpression(new Divide(left, right));
  }

  public DivideExpressionNested<A> withNewDivideExpressionLike(Divide item) {
    return new DivideExpressionNested(item);
  }

  public DotClassExpressionNested<A> withNewDotClassExpression() {
    return new DotClassExpressionNested(null);
  }

  public DotClassExpressionNested<A> withNewDotClassExpressionLike(DotClass item) {
    return new DotClassExpressionNested(item);
  }

  public EmptyExpressionNested<A> withNewEmptyExpression() {
    return new EmptyExpressionNested(null);
  }

  public EmptyExpressionNested<A> withNewEmptyExpressionLike(Empty item) {
    return new EmptyExpressionNested(item);
  }

  public EnclosedExpressionNested<A> withNewEnclosedExpression() {
    return new EnclosedExpressionNested(null);
  }

  public EnclosedExpressionNested<A> withNewEnclosedExpressionLike(Enclosed item) {
    return new EnclosedExpressionNested(item);
  }

  public EqualsExpressionNested<A> withNewEqualsExpression() {
    return new EqualsExpressionNested(null);
  }

  public A withNewEqualsExpression(Object left, Object right) {
    return (A) this.withExpression(new Equals(left, right));
  }

  public EqualsExpressionNested<A> withNewEqualsExpressionLike(Equals item) {
    return new EqualsExpressionNested(item);
  }

  public GreaterThanExpressionNested<A> withNewGreaterThanExpression() {
    return new GreaterThanExpressionNested(null);
  }

  public A withNewGreaterThanExpression(Object left, Object right) {
    return (A) this.withExpression(new GreaterThan(left, right));
  }

  public GreaterThanExpressionNested<A> withNewGreaterThanExpressionLike(GreaterThan item) {
    return new GreaterThanExpressionNested(item);
  }

  public GreaterThanOrEqualExpressionNested<A> withNewGreaterThanOrEqualExpression() {
    return new GreaterThanOrEqualExpressionNested(null);
  }

  public A withNewGreaterThanOrEqualExpression(Object left, Object right) {
    return (A) this.withExpression(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualExpressionNested<A> withNewGreaterThanOrEqualExpressionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpressionNested(item);
  }

  public IndexExpressionNested<A> withNewIndexExpression() {
    return new IndexExpressionNested(null);
  }

  public IndexExpressionNested<A> withNewIndexExpressionLike(Index item) {
    return new IndexExpressionNested(item);
  }

  public InstanceOfExpressionNested<A> withNewInstanceOfExpression() {
    return new InstanceOfExpressionNested(null);
  }

  public InstanceOfExpressionNested<A> withNewInstanceOfExpressionLike(InstanceOf item) {
    return new InstanceOfExpressionNested(item);
  }

  public InverseExpressionNested<A> withNewInverseExpression() {
    return new InverseExpressionNested(null);
  }

  public InverseExpressionNested<A> withNewInverseExpressionLike(Inverse item) {
    return new InverseExpressionNested(item);
  }

  public LambdaExpressionNested<A> withNewLambdaExpression() {
    return new LambdaExpressionNested(null);
  }

  public LambdaExpressionNested<A> withNewLambdaExpressionLike(Lambda item) {
    return new LambdaExpressionNested(item);
  }

  public LeftShiftExpressionNested<A> withNewLeftShiftExpression() {
    return new LeftShiftExpressionNested(null);
  }

  public A withNewLeftShiftExpression(Object left, Object right) {
    return (A) this.withExpression(new LeftShift(left, right));
  }

  public LeftShiftExpressionNested<A> withNewLeftShiftExpressionLike(LeftShift item) {
    return new LeftShiftExpressionNested(item);
  }

  public LessThanExpressionNested<A> withNewLessThanExpression() {
    return new LessThanExpressionNested(null);
  }

  public A withNewLessThanExpression(Object left, Object right) {
    return (A) this.withExpression(new LessThan(left, right));
  }

  public LessThanExpressionNested<A> withNewLessThanExpressionLike(LessThan item) {
    return new LessThanExpressionNested(item);
  }

  public LessThanOrEqualExpressionNested<A> withNewLessThanOrEqualExpression() {
    return new LessThanOrEqualExpressionNested(null);
  }

  public A withNewLessThanOrEqualExpression(Object left, Object right) {
    return (A) this.withExpression(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualExpressionNested<A> withNewLessThanOrEqualExpressionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExpressionNested(item);
  }

  public LogicalAndExpressionNested<A> withNewLogicalAndExpression() {
    return new LogicalAndExpressionNested(null);
  }

  public A withNewLogicalAndExpression(Object left, Object right) {
    return (A) this.withExpression(new LogicalAnd(left, right));
  }

  public LogicalAndExpressionNested<A> withNewLogicalAndExpressionLike(LogicalAnd item) {
    return new LogicalAndExpressionNested(item);
  }

  public LogicalOrExpressionNested<A> withNewLogicalOrExpression() {
    return new LogicalOrExpressionNested(null);
  }

  public A withNewLogicalOrExpression(Object left, Object right) {
    return (A) this.withExpression(new LogicalOr(left, right));
  }

  public LogicalOrExpressionNested<A> withNewLogicalOrExpressionLike(LogicalOr item) {
    return new LogicalOrExpressionNested(item);
  }

  public MethodCallExpressionNested<A> withNewMethodCallExpression() {
    return new MethodCallExpressionNested(null);
  }

  public MethodCallExpressionNested<A> withNewMethodCallExpressionLike(MethodCall item) {
    return new MethodCallExpressionNested(item);
  }

  public MinusExpressionNested<A> withNewMinusExpression() {
    return new MinusExpressionNested(null);
  }

  public A withNewMinusExpression(Object left, Object right) {
    return (A) this.withExpression(new Minus(left, right));
  }

  public MinusExpressionNested<A> withNewMinusExpressionLike(Minus item) {
    return new MinusExpressionNested(item);
  }

  public ModuloExpressionNested<A> withNewModuloExpression() {
    return new ModuloExpressionNested(null);
  }

  public A withNewModuloExpression(Object left, Object right) {
    return (A) this.withExpression(new Modulo(left, right));
  }

  public ModuloExpressionNested<A> withNewModuloExpressionLike(Modulo item) {
    return new ModuloExpressionNested(item);
  }

  public MultiplyExpressionNested<A> withNewMultiplyExpression() {
    return new MultiplyExpressionNested(null);
  }

  public A withNewMultiplyExpression(Object left, Object right) {
    return (A) this.withExpression(new Multiply(left, right));
  }

  public MultiplyExpressionNested<A> withNewMultiplyExpressionLike(Multiply item) {
    return new MultiplyExpressionNested(item);
  }

  public NegativeExpressionNested<A> withNewNegativeExpression() {
    return new NegativeExpressionNested(null);
  }

  public NegativeExpressionNested<A> withNewNegativeExpressionLike(Negative item) {
    return new NegativeExpressionNested(item);
  }

  public NewArrayExpressionNested<A> withNewNewArrayExpression() {
    return new NewArrayExpressionNested(null);
  }

  public A withNewNewArrayExpression(Class type, Integer[] sizes) {
    return (A) this.withExpression(new NewArray(type, sizes));
  }

  public NewArrayExpressionNested<A> withNewNewArrayExpressionLike(NewArray item) {
    return new NewArrayExpressionNested(item);
  }

  public NotEqualsExpressionNested<A> withNewNotEqualsExpression() {
    return new NotEqualsExpressionNested(null);
  }

  public A withNewNotEqualsExpression(Object left, Object right) {
    return (A) this.withExpression(new NotEquals(left, right));
  }

  public NotEqualsExpressionNested<A> withNewNotEqualsExpressionLike(NotEquals item) {
    return new NotEqualsExpressionNested(item);
  }

  public NotExpressionNested<A> withNewNotExpression() {
    return new NotExpressionNested(null);
  }

  public NotExpressionNested<A> withNewNotExpressionLike(Not item) {
    return new NotExpressionNested(item);
  }

  public PlusExpressionNested<A> withNewPlusExpression() {
    return new PlusExpressionNested(null);
  }

  public A withNewPlusExpression(Object left, Object right) {
    return (A) this.withExpression(new Plus(left, right));
  }

  public PlusExpressionNested<A> withNewPlusExpressionLike(Plus item) {
    return new PlusExpressionNested(item);
  }

  public PositiveExpressionNested<A> withNewPositiveExpression() {
    return new PositiveExpressionNested(null);
  }

  public PositiveExpressionNested<A> withNewPositiveExpressionLike(Positive item) {
    return new PositiveExpressionNested(item);
  }

  public PostDecrementExpressionNested<A> withNewPostDecrementExpression() {
    return new PostDecrementExpressionNested(null);
  }

  public PostDecrementExpressionNested<A> withNewPostDecrementExpressionLike(PostDecrement item) {
    return new PostDecrementExpressionNested(item);
  }

  public PostIncrementExpressionNested<A> withNewPostIncrementExpression() {
    return new PostIncrementExpressionNested(null);
  }

  public PostIncrementExpressionNested<A> withNewPostIncrementExpressionLike(PostIncrement item) {
    return new PostIncrementExpressionNested(item);
  }

  public PreDecrementExpressionNested<A> withNewPreDecrementExpression() {
    return new PreDecrementExpressionNested(null);
  }

  public PreDecrementExpressionNested<A> withNewPreDecrementExpressionLike(PreDecrement item) {
    return new PreDecrementExpressionNested(item);
  }

  public PreIncrementExpressionNested<A> withNewPreIncrementExpression() {
    return new PreIncrementExpressionNested(null);
  }

  public PreIncrementExpressionNested<A> withNewPreIncrementExpressionLike(PreIncrement item) {
    return new PreIncrementExpressionNested(item);
  }

  public PropertyExpressionNested<A> withNewPropertyExpression() {
    return new PropertyExpressionNested(null);
  }

  public PropertyExpressionNested<A> withNewPropertyExpressionLike(Property item) {
    return new PropertyExpressionNested(item);
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

  public A withNewRightShiftExpression(Object left, Object right) {
    return (A) this.withExpression(new RightShift(left, right));
  }

  public RightShiftExpressionNested<A> withNewRightShiftExpressionLike(RightShift item) {
    return new RightShiftExpressionNested(item);
  }

  public RightUnsignedShiftExpressionNested<A> withNewRightUnsignedShiftExpression() {
    return new RightUnsignedShiftExpressionNested(null);
  }

  public A withNewRightUnsignedShiftExpression(Object left, Object right) {
    return (A) this.withExpression(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftExpressionNested<A> withNewRightUnsignedShiftExpressionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExpressionNested(item);
  }

  public SuperExpressionNested<A> withNewSuperExpression() {
    return new SuperExpressionNested(null);
  }

  public SuperExpressionNested<A> withNewSuperExpressionLike(Super item) {
    return new SuperExpressionNested(item);
  }

  public TernaryExpressionNested<A> withNewTernaryExpression() {
    return new TernaryExpressionNested(null);
  }

  public TernaryExpressionNested<A> withNewTernaryExpressionLike(Ternary item) {
    return new TernaryExpressionNested(item);
  }

  public ThisExpressionNested<A> withNewThisExpression() {
    return new ThisExpressionNested(null);
  }

  public ThisExpressionNested<A> withNewThisExpressionLike(This item) {
    return new ThisExpressionNested(item);
  }

  public ValueRefExpressionNested<A> withNewValueRefExpression() {
    return new ValueRefExpressionNested(null);
  }

  public A withNewValueRefExpression(Object value) {
    return (A) this.withExpression(new ValueRef(value));
  }

  public ValueRefExpressionNested<A> withNewValueRefExpressionLike(ValueRef item) {
    return new ValueRefExpressionNested(item);
  }

  public XorExpressionNested<A> withNewXorExpression() {
    return new XorExpressionNested(null);
  }

  public A withNewXorExpression(Object left, Object right) {
    return (A) this.withExpression(new Xor(left, right));
  }

  public XorExpressionNested<A> withNewXorExpressionLike(Xor item) {
    return new XorExpressionNested(item);
  }

  public class AssignExpressionNested<N> extends AssignFluent<AssignExpressionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignExpressionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endAssignExpression() {
      return and();
    }

  }

  public class BinaryExpressionNested<N> extends BinaryExpressionFluent<BinaryExpressionNested<N>> implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endBinaryExpression() {
      return and();
    }

  }

  public class BitwiseAndExpressionNested<N> extends BitwiseAndFluent<BitwiseAndExpressionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndExpressionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endBitwiseAndExpression() {
      return and();
    }

  }

  public class BitwiseOrExpressionNested<N> extends BitwiseOrFluent<BitwiseOrExpressionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrExpressionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endBitwiseOrExpression() {
      return and();
    }

  }

  public class CastExpressionNested<N> extends CastFluent<CastExpressionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastExpressionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endCastExpression() {
      return and();
    }

  }

  public class ClassRefExpressionNested<N> extends ClassRefFluent<ClassRefExpressionNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefExpressionNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endClassRefExpression() {
      return and();
    }

  }

  public class ConstructExpressionNested<N> extends ConstructFluent<ConstructExpressionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructExpressionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endConstructExpression() {
      return and();
    }

  }

  public class ContextRefExpressionNested<N> extends ContextRefFluent<ContextRefExpressionNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefExpressionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endContextRefExpression() {
      return and();
    }

  }

  public class DeclareExpressionNested<N> extends DeclareFluent<DeclareExpressionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareExpressionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endDeclareExpression() {
      return and();
    }

  }

  public class DivideExpressionNested<N> extends DivideFluent<DivideExpressionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideExpressionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endDivideExpression() {
      return and();
    }

  }

  public class DotClassExpressionNested<N> extends DotClassFluent<DotClassExpressionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassExpressionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endDotClassExpression() {
      return and();
    }

  }

  public class EmptyExpressionNested<N> extends EmptyFluent<EmptyExpressionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyExpressionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endEmptyExpression() {
      return and();
    }

  }

  public class EnclosedExpressionNested<N> extends EnclosedFluent<EnclosedExpressionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedExpressionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endEnclosedExpression() {
      return and();
    }

  }

  public class EqualsExpressionNested<N> extends EqualsFluent<EqualsExpressionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsExpressionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endEqualsExpression() {
      return and();
    }

  }

  public class GreaterThanExpressionNested<N> extends GreaterThanFluent<GreaterThanExpressionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanExpressionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endGreaterThanExpression() {
      return and();
    }

  }

  public class GreaterThanOrEqualExpressionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExpressionNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualExpressionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endGreaterThanOrEqualExpression() {
      return and();
    }

  }

  public class IndexExpressionNested<N> extends IndexFluent<IndexExpressionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexExpressionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endIndexExpression() {
      return and();
    }

  }

  public class InstanceOfExpressionNested<N> extends InstanceOfFluent<InstanceOfExpressionNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfExpressionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endInstanceOfExpression() {
      return and();
    }

  }

  public class InverseExpressionNested<N> extends InverseFluent<InverseExpressionNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseExpressionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endInverseExpression() {
      return and();
    }

  }

  public class LambdaExpressionNested<N> extends LambdaFluent<LambdaExpressionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaExpressionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endLambdaExpression() {
      return and();
    }

  }

  public class LeftShiftExpressionNested<N> extends LeftShiftFluent<LeftShiftExpressionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftExpressionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endLeftShiftExpression() {
      return and();
    }

  }

  public class LessThanExpressionNested<N> extends LessThanFluent<LessThanExpressionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanExpressionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endLessThanExpression() {
      return and();
    }

  }

  public class LessThanOrEqualExpressionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExpressionNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualExpressionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endLessThanOrEqualExpression() {
      return and();
    }

  }

  public class LogicalAndExpressionNested<N> extends LogicalAndFluent<LogicalAndExpressionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndExpressionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endLogicalAndExpression() {
      return and();
    }

  }

  public class LogicalOrExpressionNested<N> extends LogicalOrFluent<LogicalOrExpressionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrExpressionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endLogicalOrExpression() {
      return and();
    }

  }

  public class MethodCallExpressionNested<N> extends MethodCallFluent<MethodCallExpressionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallExpressionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endMethodCallExpression() {
      return and();
    }

  }

  public class MinusExpressionNested<N> extends MinusFluent<MinusExpressionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusExpressionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endMinusExpression() {
      return and();
    }

  }

  public class ModuloExpressionNested<N> extends ModuloFluent<ModuloExpressionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloExpressionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endModuloExpression() {
      return and();
    }

  }

  public class MultiplyExpressionNested<N> extends MultiplyFluent<MultiplyExpressionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyExpressionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endMultiplyExpression() {
      return and();
    }

  }

  public class NegativeExpressionNested<N> extends NegativeFluent<NegativeExpressionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeExpressionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endNegativeExpression() {
      return and();
    }

  }

  public class NewArrayExpressionNested<N> extends NewArrayFluent<NewArrayExpressionNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayExpressionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endNewArrayExpression() {
      return and();
    }

  }

  public class NotEqualsExpressionNested<N> extends NotEqualsFluent<NotEqualsExpressionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsExpressionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endNotEqualsExpression() {
      return and();
    }

  }

  public class NotExpressionNested<N> extends NotFluent<NotExpressionNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotExpressionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endNotExpression() {
      return and();
    }

  }

  public class PlusExpressionNested<N> extends PlusFluent<PlusExpressionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusExpressionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPlusExpression() {
      return and();
    }

  }

  public class PositiveExpressionNested<N> extends PositiveFluent<PositiveExpressionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveExpressionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPositiveExpression() {
      return and();
    }

  }

  public class PostDecrementExpressionNested<N> extends PostDecrementFluent<PostDecrementExpressionNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementExpressionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPostDecrementExpression() {
      return and();
    }

  }

  public class PostIncrementExpressionNested<N> extends PostIncrementFluent<PostIncrementExpressionNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementExpressionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPostIncrementExpression() {
      return and();
    }

  }

  public class PreDecrementExpressionNested<N> extends PreDecrementFluent<PreDecrementExpressionNested<N>>
      implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementExpressionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPreDecrementExpression() {
      return and();
    }

  }

  public class PreIncrementExpressionNested<N> extends PreIncrementFluent<PreIncrementExpressionNested<N>>
      implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementExpressionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPreIncrementExpression() {
      return and();
    }

  }

  public class PropertyExpressionNested<N> extends PropertyFluent<PropertyExpressionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyExpressionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPropertyExpression() {
      return and();
    }

  }

  public class PropertyRefExpressionNested<N> extends PropertyRefFluent<PropertyRefExpressionNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefExpressionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endPropertyRefExpression() {
      return and();
    }

  }

  public class RightShiftExpressionNested<N> extends RightShiftFluent<RightShiftExpressionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftExpressionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endRightShiftExpression() {
      return and();
    }

  }

  public class RightUnsignedShiftExpressionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExpressionNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftExpressionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endRightUnsignedShiftExpression() {
      return and();
    }

  }

  public class SuperExpressionNested<N> extends SuperFluent<SuperExpressionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperExpressionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endSuperExpression() {
      return and();
    }

  }

  public class TernaryExpressionNested<N> extends TernaryFluent<TernaryExpressionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryExpressionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endTernaryExpression() {
      return and();
    }

  }

  public class ThisExpressionNested<N> extends ThisFluent<ThisExpressionNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisExpressionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endThisExpression() {
      return and();
    }

  }

  public class ValueRefExpressionNested<N> extends ValueRefFluent<ValueRefExpressionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefExpressionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endValueRefExpression() {
      return and();
    }

  }

  public class XorExpressionNested<N> extends XorFluent<XorExpressionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorExpressionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) PreIncrementFluent.this.withExpression(builder.build());
    }

    public N endXorExpression() {
      return and();
    }

  }
}
