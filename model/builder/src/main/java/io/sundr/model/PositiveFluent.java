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
public class PositiveFluent<A extends io.sundr.model.PositiveFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> expresion;

  public PositiveFluent() {
  }

  public PositiveFluent(Positive instance) {
    this.copyInstance(instance);
  }

  public Expression buildExpresion() {
    return this.expresion != null ? this.expresion.build() : null;
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

  protected void copyInstance(Positive instance) {
    if (instance != null) {
      this.withExpresion(instance.getExpresion());
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
    PositiveFluent that = (PositiveFluent) o;
    if (!(Objects.equals(expresion, that.expresion))) {
      return false;
    }
    return true;
  }

  public boolean hasExpresion() {
    return this.expresion != null;
  }

  public int hashCode() {
    return Objects.hash(expresion);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(expresion == null)) {
      sb.append("expresion:");
      sb.append(expresion);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withExpresion(Expression expresion) {
    if (expresion == null) {
      this.expresion = null;
      this._visitables.remove("expresion");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(expresion);
      this._visitables.get("expresion").clear();
      this._visitables.get("expresion").add(builder);
      this.expresion = builder;
      return (A) this;
    }
  }

  public AssignExpresionNested<A> withNewAssignExpresion() {
    return new AssignExpresionNested(null);
  }

  public AssignExpresionNested<A> withNewAssignExpresionLike(Assign item) {
    return new AssignExpresionNested(item);
  }

  public BinaryExpressionExpresionNested<A> withNewBinaryExpressionExpresion() {
    return new BinaryExpressionExpresionNested(null);
  }

  public BinaryExpressionExpresionNested<A> withNewBinaryExpressionExpresionLike(BinaryExpression item) {
    return new BinaryExpressionExpresionNested(item);
  }

  public BitwiseAndExpresionNested<A> withNewBitwiseAndExpresion() {
    return new BitwiseAndExpresionNested(null);
  }

  public A withNewBitwiseAndExpresion(Object left, Object right) {
    return (A) this.withExpresion(new BitwiseAnd(left, right));
  }

  public BitwiseAndExpresionNested<A> withNewBitwiseAndExpresionLike(BitwiseAnd item) {
    return new BitwiseAndExpresionNested(item);
  }

  public BitwiseOrExpresionNested<A> withNewBitwiseOrExpresion() {
    return new BitwiseOrExpresionNested(null);
  }

  public A withNewBitwiseOrExpresion(Object left, Object right) {
    return (A) this.withExpresion(new BitwiseOr(left, right));
  }

  public BitwiseOrExpresionNested<A> withNewBitwiseOrExpresionLike(BitwiseOr item) {
    return new BitwiseOrExpresionNested(item);
  }

  public CastExpresionNested<A> withNewCastExpresion() {
    return new CastExpresionNested(null);
  }

  public CastExpresionNested<A> withNewCastExpresionLike(Cast item) {
    return new CastExpresionNested(item);
  }

  public ClassRefExpresionNested<A> withNewClassRefExpresion() {
    return new ClassRefExpresionNested(null);
  }

  public ClassRefExpresionNested<A> withNewClassRefExpresionLike(ClassRef item) {
    return new ClassRefExpresionNested(item);
  }

  public ConstructExpresionNested<A> withNewConstructExpresion() {
    return new ConstructExpresionNested(null);
  }

  public ConstructExpresionNested<A> withNewConstructExpresionLike(Construct item) {
    return new ConstructExpresionNested(item);
  }

  public ContextRefExpresionNested<A> withNewContextRefExpresion() {
    return new ContextRefExpresionNested(null);
  }

  public A withNewContextRefExpresion(String name) {
    return (A) this.withExpresion(new ContextRef(name));
  }

  public ContextRefExpresionNested<A> withNewContextRefExpresionLike(ContextRef item) {
    return new ContextRefExpresionNested(item);
  }

  public DeclareExpresionNested<A> withNewDeclareExpresion() {
    return new DeclareExpresionNested(null);
  }

  public A withNewDeclareExpresion(Class type, String name) {
    return (A) this.withExpresion(new Declare(type, name));
  }

  public A withNewDeclareExpresion(Class type, String name, Object value) {
    return (A) this.withExpresion(new Declare(type, name, value));
  }

  public DeclareExpresionNested<A> withNewDeclareExpresionLike(Declare item) {
    return new DeclareExpresionNested(item);
  }

  public DivideExpresionNested<A> withNewDivideExpresion() {
    return new DivideExpresionNested(null);
  }

  public A withNewDivideExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Divide(left, right));
  }

  public DivideExpresionNested<A> withNewDivideExpresionLike(Divide item) {
    return new DivideExpresionNested(item);
  }

  public DotClassExpresionNested<A> withNewDotClassExpresion() {
    return new DotClassExpresionNested(null);
  }

  public DotClassExpresionNested<A> withNewDotClassExpresionLike(DotClass item) {
    return new DotClassExpresionNested(item);
  }

  public EmptyExpresionNested<A> withNewEmptyExpresion() {
    return new EmptyExpresionNested(null);
  }

  public EmptyExpresionNested<A> withNewEmptyExpresionLike(Empty item) {
    return new EmptyExpresionNested(item);
  }

  public EnclosedExpresionNested<A> withNewEnclosedExpresion() {
    return new EnclosedExpresionNested(null);
  }

  public EnclosedExpresionNested<A> withNewEnclosedExpresionLike(Enclosed item) {
    return new EnclosedExpresionNested(item);
  }

  public EqualsExpresionNested<A> withNewEqualsExpresion() {
    return new EqualsExpresionNested(null);
  }

  public A withNewEqualsExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Equals(left, right));
  }

  public EqualsExpresionNested<A> withNewEqualsExpresionLike(Equals item) {
    return new EqualsExpresionNested(item);
  }

  public GreaterThanExpresionNested<A> withNewGreaterThanExpresion() {
    return new GreaterThanExpresionNested(null);
  }

  public A withNewGreaterThanExpresion(Object left, Object right) {
    return (A) this.withExpresion(new GreaterThan(left, right));
  }

  public GreaterThanExpresionNested<A> withNewGreaterThanExpresionLike(GreaterThan item) {
    return new GreaterThanExpresionNested(item);
  }

  public GreaterThanOrEqualExpresionNested<A> withNewGreaterThanOrEqualExpresion() {
    return new GreaterThanOrEqualExpresionNested(null);
  }

  public A withNewGreaterThanOrEqualExpresion(Object left, Object right) {
    return (A) this.withExpresion(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualExpresionNested<A> withNewGreaterThanOrEqualExpresionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpresionNested(item);
  }

  public IndexExpresionNested<A> withNewIndexExpresion() {
    return new IndexExpresionNested(null);
  }

  public IndexExpresionNested<A> withNewIndexExpresionLike(Index item) {
    return new IndexExpresionNested(item);
  }

  public InstanceOfExpresionNested<A> withNewInstanceOfExpresion() {
    return new InstanceOfExpresionNested(null);
  }

  public InstanceOfExpresionNested<A> withNewInstanceOfExpresionLike(InstanceOf item) {
    return new InstanceOfExpresionNested(item);
  }

  public InverseExpresionNested<A> withNewInverseExpresion() {
    return new InverseExpresionNested(null);
  }

  public InverseExpresionNested<A> withNewInverseExpresionLike(Inverse item) {
    return new InverseExpresionNested(item);
  }

  public LambdaExpresionNested<A> withNewLambdaExpresion() {
    return new LambdaExpresionNested(null);
  }

  public LambdaExpresionNested<A> withNewLambdaExpresionLike(Lambda item) {
    return new LambdaExpresionNested(item);
  }

  public LeftShiftExpresionNested<A> withNewLeftShiftExpresion() {
    return new LeftShiftExpresionNested(null);
  }

  public A withNewLeftShiftExpresion(Object left, Object right) {
    return (A) this.withExpresion(new LeftShift(left, right));
  }

  public LeftShiftExpresionNested<A> withNewLeftShiftExpresionLike(LeftShift item) {
    return new LeftShiftExpresionNested(item);
  }

  public LessThanExpresionNested<A> withNewLessThanExpresion() {
    return new LessThanExpresionNested(null);
  }

  public A withNewLessThanExpresion(Object left, Object right) {
    return (A) this.withExpresion(new LessThan(left, right));
  }

  public LessThanExpresionNested<A> withNewLessThanExpresionLike(LessThan item) {
    return new LessThanExpresionNested(item);
  }

  public LessThanOrEqualExpresionNested<A> withNewLessThanOrEqualExpresion() {
    return new LessThanOrEqualExpresionNested(null);
  }

  public A withNewLessThanOrEqualExpresion(Object left, Object right) {
    return (A) this.withExpresion(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualExpresionNested<A> withNewLessThanOrEqualExpresionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExpresionNested(item);
  }

  public LogicalAndExpresionNested<A> withNewLogicalAndExpresion() {
    return new LogicalAndExpresionNested(null);
  }

  public A withNewLogicalAndExpresion(Object left, Object right) {
    return (A) this.withExpresion(new LogicalAnd(left, right));
  }

  public LogicalAndExpresionNested<A> withNewLogicalAndExpresionLike(LogicalAnd item) {
    return new LogicalAndExpresionNested(item);
  }

  public LogicalOrExpresionNested<A> withNewLogicalOrExpresion() {
    return new LogicalOrExpresionNested(null);
  }

  public A withNewLogicalOrExpresion(Object left, Object right) {
    return (A) this.withExpresion(new LogicalOr(left, right));
  }

  public LogicalOrExpresionNested<A> withNewLogicalOrExpresionLike(LogicalOr item) {
    return new LogicalOrExpresionNested(item);
  }

  public MethodCallExpresionNested<A> withNewMethodCallExpresion() {
    return new MethodCallExpresionNested(null);
  }

  public MethodCallExpresionNested<A> withNewMethodCallExpresionLike(MethodCall item) {
    return new MethodCallExpresionNested(item);
  }

  public MinusExpresionNested<A> withNewMinusExpresion() {
    return new MinusExpresionNested(null);
  }

  public A withNewMinusExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Minus(left, right));
  }

  public MinusExpresionNested<A> withNewMinusExpresionLike(Minus item) {
    return new MinusExpresionNested(item);
  }

  public ModuloExpresionNested<A> withNewModuloExpresion() {
    return new ModuloExpresionNested(null);
  }

  public A withNewModuloExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Modulo(left, right));
  }

  public ModuloExpresionNested<A> withNewModuloExpresionLike(Modulo item) {
    return new ModuloExpresionNested(item);
  }

  public MultiplyExpresionNested<A> withNewMultiplyExpresion() {
    return new MultiplyExpresionNested(null);
  }

  public A withNewMultiplyExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Multiply(left, right));
  }

  public MultiplyExpresionNested<A> withNewMultiplyExpresionLike(Multiply item) {
    return new MultiplyExpresionNested(item);
  }

  public NegativeExpresionNested<A> withNewNegativeExpresion() {
    return new NegativeExpresionNested(null);
  }

  public NegativeExpresionNested<A> withNewNegativeExpresionLike(Negative item) {
    return new NegativeExpresionNested(item);
  }

  public NewArrayExpresionNested<A> withNewNewArrayExpresion() {
    return new NewArrayExpresionNested(null);
  }

  public A withNewNewArrayExpresion(Class type, Integer[] sizes) {
    return (A) this.withExpresion(new NewArray(type, sizes));
  }

  public NewArrayExpresionNested<A> withNewNewArrayExpresionLike(NewArray item) {
    return new NewArrayExpresionNested(item);
  }

  public NotEqualsExpresionNested<A> withNewNotEqualsExpresion() {
    return new NotEqualsExpresionNested(null);
  }

  public A withNewNotEqualsExpresion(Object left, Object right) {
    return (A) this.withExpresion(new NotEquals(left, right));
  }

  public NotEqualsExpresionNested<A> withNewNotEqualsExpresionLike(NotEquals item) {
    return new NotEqualsExpresionNested(item);
  }

  public NotExpresionNested<A> withNewNotExpresion() {
    return new NotExpresionNested(null);
  }

  public NotExpresionNested<A> withNewNotExpresionLike(Not item) {
    return new NotExpresionNested(item);
  }

  public PlusExpresionNested<A> withNewPlusExpresion() {
    return new PlusExpresionNested(null);
  }

  public A withNewPlusExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Plus(left, right));
  }

  public PlusExpresionNested<A> withNewPlusExpresionLike(Plus item) {
    return new PlusExpresionNested(item);
  }

  public PositiveExpresionNested<A> withNewPositiveExpresion() {
    return new PositiveExpresionNested(null);
  }

  public PositiveExpresionNested<A> withNewPositiveExpresionLike(Positive item) {
    return new PositiveExpresionNested(item);
  }

  public PostDecrementExpresionNested<A> withNewPostDecrementExpresion() {
    return new PostDecrementExpresionNested(null);
  }

  public PostDecrementExpresionNested<A> withNewPostDecrementExpresionLike(PostDecrement item) {
    return new PostDecrementExpresionNested(item);
  }

  public PostIncrementExpresionNested<A> withNewPostIncrementExpresion() {
    return new PostIncrementExpresionNested(null);
  }

  public PostIncrementExpresionNested<A> withNewPostIncrementExpresionLike(PostIncrement item) {
    return new PostIncrementExpresionNested(item);
  }

  public PreDecrementExpresionNested<A> withNewPreDecrementExpresion() {
    return new PreDecrementExpresionNested(null);
  }

  public PreDecrementExpresionNested<A> withNewPreDecrementExpresionLike(PreDecrement item) {
    return new PreDecrementExpresionNested(item);
  }

  public PreIncrementExpresionNested<A> withNewPreIncrementExpresion() {
    return new PreIncrementExpresionNested(null);
  }

  public PreIncrementExpresionNested<A> withNewPreIncrementExpresionLike(PreIncrement item) {
    return new PreIncrementExpresionNested(item);
  }

  public PropertyExpresionNested<A> withNewPropertyExpresion() {
    return new PropertyExpresionNested(null);
  }

  public PropertyExpresionNested<A> withNewPropertyExpresionLike(Property item) {
    return new PropertyExpresionNested(item);
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

  public A withNewRightShiftExpresion(Object left, Object right) {
    return (A) this.withExpresion(new RightShift(left, right));
  }

  public RightShiftExpresionNested<A> withNewRightShiftExpresionLike(RightShift item) {
    return new RightShiftExpresionNested(item);
  }

  public RightUnsignedShiftExpresionNested<A> withNewRightUnsignedShiftExpresion() {
    return new RightUnsignedShiftExpresionNested(null);
  }

  public A withNewRightUnsignedShiftExpresion(Object left, Object right) {
    return (A) this.withExpresion(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftExpresionNested<A> withNewRightUnsignedShiftExpresionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExpresionNested(item);
  }

  public SuperExpresionNested<A> withNewSuperExpresion() {
    return new SuperExpresionNested(null);
  }

  public SuperExpresionNested<A> withNewSuperExpresionLike(Super item) {
    return new SuperExpresionNested(item);
  }

  public TernaryExpresionNested<A> withNewTernaryExpresion() {
    return new TernaryExpresionNested(null);
  }

  public TernaryExpresionNested<A> withNewTernaryExpresionLike(Ternary item) {
    return new TernaryExpresionNested(item);
  }

  public ThisExpresionNested<A> withNewThisExpresion() {
    return new ThisExpresionNested(null);
  }

  public ThisExpresionNested<A> withNewThisExpresionLike(This item) {
    return new ThisExpresionNested(item);
  }

  public ValueRefExpresionNested<A> withNewValueRefExpresion() {
    return new ValueRefExpresionNested(null);
  }

  public A withNewValueRefExpresion(Object value) {
    return (A) this.withExpresion(new ValueRef(value));
  }

  public ValueRefExpresionNested<A> withNewValueRefExpresionLike(ValueRef item) {
    return new ValueRefExpresionNested(item);
  }

  public XorExpresionNested<A> withNewXorExpresion() {
    return new XorExpresionNested(null);
  }

  public A withNewXorExpresion(Object left, Object right) {
    return (A) this.withExpresion(new Xor(left, right));
  }

  public XorExpresionNested<A> withNewXorExpresionLike(Xor item) {
    return new XorExpresionNested(item);
  }

  public class AssignExpresionNested<N> extends AssignFluent<AssignExpresionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignExpresionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endAssignExpresion() {
      return and();
    }

  }

  public class BinaryExpressionExpresionNested<N> extends BinaryExpressionFluent<BinaryExpressionExpresionNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionExpresionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endBinaryExpressionExpresion() {
      return and();
    }

  }

  public class BitwiseAndExpresionNested<N> extends BitwiseAndFluent<BitwiseAndExpresionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndExpresionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endBitwiseAndExpresion() {
      return and();
    }

  }

  public class BitwiseOrExpresionNested<N> extends BitwiseOrFluent<BitwiseOrExpresionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrExpresionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endBitwiseOrExpresion() {
      return and();
    }

  }

  public class CastExpresionNested<N> extends CastFluent<CastExpresionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastExpresionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endCastExpresion() {
      return and();
    }

  }

  public class ClassRefExpresionNested<N> extends ClassRefFluent<ClassRefExpresionNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefExpresionNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endClassRefExpresion() {
      return and();
    }

  }

  public class ConstructExpresionNested<N> extends ConstructFluent<ConstructExpresionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructExpresionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endConstructExpresion() {
      return and();
    }

  }

  public class ContextRefExpresionNested<N> extends ContextRefFluent<ContextRefExpresionNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefExpresionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endContextRefExpresion() {
      return and();
    }

  }

  public class DeclareExpresionNested<N> extends DeclareFluent<DeclareExpresionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareExpresionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endDeclareExpresion() {
      return and();
    }

  }

  public class DivideExpresionNested<N> extends DivideFluent<DivideExpresionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideExpresionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endDivideExpresion() {
      return and();
    }

  }

  public class DotClassExpresionNested<N> extends DotClassFluent<DotClassExpresionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassExpresionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endDotClassExpresion() {
      return and();
    }

  }

  public class EmptyExpresionNested<N> extends EmptyFluent<EmptyExpresionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyExpresionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endEmptyExpresion() {
      return and();
    }

  }

  public class EnclosedExpresionNested<N> extends EnclosedFluent<EnclosedExpresionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedExpresionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endEnclosedExpresion() {
      return and();
    }

  }

  public class EqualsExpresionNested<N> extends EqualsFluent<EqualsExpresionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsExpresionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endEqualsExpresion() {
      return and();
    }

  }

  public class GreaterThanExpresionNested<N> extends GreaterThanFluent<GreaterThanExpresionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanExpresionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endGreaterThanExpresion() {
      return and();
    }

  }

  public class GreaterThanOrEqualExpresionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualExpresionNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualExpresionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endGreaterThanOrEqualExpresion() {
      return and();
    }

  }

  public class IndexExpresionNested<N> extends IndexFluent<IndexExpresionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexExpresionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endIndexExpresion() {
      return and();
    }

  }

  public class InstanceOfExpresionNested<N> extends InstanceOfFluent<InstanceOfExpresionNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfExpresionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endInstanceOfExpresion() {
      return and();
    }

  }

  public class InverseExpresionNested<N> extends InverseFluent<InverseExpresionNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseExpresionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endInverseExpresion() {
      return and();
    }

  }

  public class LambdaExpresionNested<N> extends LambdaFluent<LambdaExpresionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaExpresionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLambdaExpresion() {
      return and();
    }

  }

  public class LeftShiftExpresionNested<N> extends LeftShiftFluent<LeftShiftExpresionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftExpresionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLeftShiftExpresion() {
      return and();
    }

  }

  public class LessThanExpresionNested<N> extends LessThanFluent<LessThanExpresionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanExpresionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLessThanExpresion() {
      return and();
    }

  }

  public class LessThanOrEqualExpresionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualExpresionNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualExpresionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLessThanOrEqualExpresion() {
      return and();
    }

  }

  public class LogicalAndExpresionNested<N> extends LogicalAndFluent<LogicalAndExpresionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndExpresionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLogicalAndExpresion() {
      return and();
    }

  }

  public class LogicalOrExpresionNested<N> extends LogicalOrFluent<LogicalOrExpresionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrExpresionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endLogicalOrExpresion() {
      return and();
    }

  }

  public class MethodCallExpresionNested<N> extends MethodCallFluent<MethodCallExpresionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallExpresionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endMethodCallExpresion() {
      return and();
    }

  }

  public class MinusExpresionNested<N> extends MinusFluent<MinusExpresionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusExpresionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endMinusExpresion() {
      return and();
    }

  }

  public class ModuloExpresionNested<N> extends ModuloFluent<ModuloExpresionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloExpresionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endModuloExpresion() {
      return and();
    }

  }

  public class MultiplyExpresionNested<N> extends MultiplyFluent<MultiplyExpresionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyExpresionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endMultiplyExpresion() {
      return and();
    }

  }

  public class NegativeExpresionNested<N> extends NegativeFluent<NegativeExpresionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeExpresionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNegativeExpresion() {
      return and();
    }

  }

  public class NewArrayExpresionNested<N> extends NewArrayFluent<NewArrayExpresionNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayExpresionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNewArrayExpresion() {
      return and();
    }

  }

  public class NotEqualsExpresionNested<N> extends NotEqualsFluent<NotEqualsExpresionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsExpresionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNotEqualsExpresion() {
      return and();
    }

  }

  public class NotExpresionNested<N> extends NotFluent<NotExpresionNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotExpresionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endNotExpresion() {
      return and();
    }

  }

  public class PlusExpresionNested<N> extends PlusFluent<PlusExpresionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusExpresionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPlusExpresion() {
      return and();
    }

  }

  public class PositiveExpresionNested<N> extends PositiveFluent<PositiveExpresionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveExpresionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPositiveExpresion() {
      return and();
    }

  }

  public class PostDecrementExpresionNested<N> extends PostDecrementFluent<PostDecrementExpresionNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementExpresionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPostDecrementExpresion() {
      return and();
    }

  }

  public class PostIncrementExpresionNested<N> extends PostIncrementFluent<PostIncrementExpresionNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementExpresionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPostIncrementExpresion() {
      return and();
    }

  }

  public class PreDecrementExpresionNested<N> extends PreDecrementFluent<PreDecrementExpresionNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementExpresionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPreDecrementExpresion() {
      return and();
    }

  }

  public class PreIncrementExpresionNested<N> extends PreIncrementFluent<PreIncrementExpresionNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementExpresionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPreIncrementExpresion() {
      return and();
    }

  }

  public class PropertyExpresionNested<N> extends PropertyFluent<PropertyExpresionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyExpresionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPropertyExpresion() {
      return and();
    }

  }

  public class PropertyRefExpresionNested<N> extends PropertyRefFluent<PropertyRefExpresionNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefExpresionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endPropertyRefExpresion() {
      return and();
    }

  }

  public class RightShiftExpresionNested<N> extends RightShiftFluent<RightShiftExpresionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftExpresionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endRightShiftExpresion() {
      return and();
    }

  }

  public class RightUnsignedShiftExpresionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftExpresionNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftExpresionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endRightUnsignedShiftExpresion() {
      return and();
    }

  }

  public class SuperExpresionNested<N> extends SuperFluent<SuperExpresionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperExpresionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endSuperExpresion() {
      return and();
    }

  }

  public class TernaryExpresionNested<N> extends TernaryFluent<TernaryExpresionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryExpresionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endTernaryExpresion() {
      return and();
    }

  }

  public class ThisExpresionNested<N> extends ThisFluent<ThisExpresionNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisExpresionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endThisExpresion() {
      return and();
    }

  }

  public class ValueRefExpresionNested<N> extends ValueRefFluent<ValueRefExpresionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefExpresionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endValueRefExpresion() {
      return and();
    }

  }

  public class XorExpresionNested<N> extends XorFluent<XorExpresionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorExpresionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) PositiveFluent.this.withExpresion(builder.build());
    }

    public N endXorExpresion() {
      return and();
    }

  }
}
