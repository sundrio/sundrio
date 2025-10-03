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
public class WithScopeFluent<A extends io.sundr.model.WithScopeFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> scope;

  public WithScopeFluent() {
  }

  public WithScopeFluent(WithScope instance) {
    this.copyInstance(instance);
  }

  public Expression buildScope() {
    return this.scope != null ? this.scope.build() : null;
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

  protected void copyInstance(WithScope instance) {
    if (instance != null) {
      this.withScope(instance.getScope());
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
    WithScopeFluent that = (WithScopeFluent) o;
    if (!(Objects.equals(scope, that.scope))) {
      return false;
    }
    return true;
  }

  public boolean hasScope() {
    return this.scope != null;
  }

  public int hashCode() {
    return Objects.hash(scope);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(scope == null)) {
      sb.append("scope:");
      sb.append(scope);
    }
    sb.append("}");
    return sb.toString();
  }

  public AssignScopeNested<A> withNewAssignScope() {
    return new AssignScopeNested(null);
  }

  public AssignScopeNested<A> withNewAssignScopeLike(Assign item) {
    return new AssignScopeNested(item);
  }

  public BinaryExpressionScopeNested<A> withNewBinaryExpressionScope() {
    return new BinaryExpressionScopeNested(null);
  }

  public BinaryExpressionScopeNested<A> withNewBinaryExpressionScopeLike(BinaryExpression item) {
    return new BinaryExpressionScopeNested(item);
  }

  public BitwiseAndScopeNested<A> withNewBitwiseAndScope() {
    return new BitwiseAndScopeNested(null);
  }

  public A withNewBitwiseAndScope(Object left, Object right) {
    return (A) this.withScope(new BitwiseAnd(left, right));
  }

  public BitwiseAndScopeNested<A> withNewBitwiseAndScopeLike(BitwiseAnd item) {
    return new BitwiseAndScopeNested(item);
  }

  public BitwiseOrScopeNested<A> withNewBitwiseOrScope() {
    return new BitwiseOrScopeNested(null);
  }

  public A withNewBitwiseOrScope(Object left, Object right) {
    return (A) this.withScope(new BitwiseOr(left, right));
  }

  public BitwiseOrScopeNested<A> withNewBitwiseOrScopeLike(BitwiseOr item) {
    return new BitwiseOrScopeNested(item);
  }

  public CastScopeNested<A> withNewCastScope() {
    return new CastScopeNested(null);
  }

  public CastScopeNested<A> withNewCastScopeLike(Cast item) {
    return new CastScopeNested(item);
  }

  public ClassRefScopeNested<A> withNewClassRefScope() {
    return new ClassRefScopeNested(null);
  }

  public ClassRefScopeNested<A> withNewClassRefScopeLike(ClassRef item) {
    return new ClassRefScopeNested(item);
  }

  public ConstructScopeNested<A> withNewConstructScope() {
    return new ConstructScopeNested(null);
  }

  public ConstructScopeNested<A> withNewConstructScopeLike(Construct item) {
    return new ConstructScopeNested(item);
  }

  public ContextRefScopeNested<A> withNewContextRefScope() {
    return new ContextRefScopeNested(null);
  }

  public A withNewContextRefScope(String name) {
    return (A) this.withScope(new ContextRef(name));
  }

  public ContextRefScopeNested<A> withNewContextRefScopeLike(ContextRef item) {
    return new ContextRefScopeNested(item);
  }

  public DeclareScopeNested<A> withNewDeclareScope() {
    return new DeclareScopeNested(null);
  }

  public A withNewDeclareScope(Class type, String name) {
    return (A) this.withScope(new Declare(type, name));
  }

  public A withNewDeclareScope(Class type, String name, Object value) {
    return (A) this.withScope(new Declare(type, name, value));
  }

  public DeclareScopeNested<A> withNewDeclareScopeLike(Declare item) {
    return new DeclareScopeNested(item);
  }

  public DivideScopeNested<A> withNewDivideScope() {
    return new DivideScopeNested(null);
  }

  public A withNewDivideScope(Object left, Object right) {
    return (A) this.withScope(new Divide(left, right));
  }

  public DivideScopeNested<A> withNewDivideScopeLike(Divide item) {
    return new DivideScopeNested(item);
  }

  public DotClassScopeNested<A> withNewDotClassScope() {
    return new DotClassScopeNested(null);
  }

  public DotClassScopeNested<A> withNewDotClassScopeLike(DotClass item) {
    return new DotClassScopeNested(item);
  }

  public EmptyScopeNested<A> withNewEmptyScope() {
    return new EmptyScopeNested(null);
  }

  public EmptyScopeNested<A> withNewEmptyScopeLike(Empty item) {
    return new EmptyScopeNested(item);
  }

  public EnclosedScopeNested<A> withNewEnclosedScope() {
    return new EnclosedScopeNested(null);
  }

  public EnclosedScopeNested<A> withNewEnclosedScopeLike(Enclosed item) {
    return new EnclosedScopeNested(item);
  }

  public EqualsScopeNested<A> withNewEqualsScope() {
    return new EqualsScopeNested(null);
  }

  public A withNewEqualsScope(Object left, Object right) {
    return (A) this.withScope(new Equals(left, right));
  }

  public EqualsScopeNested<A> withNewEqualsScopeLike(Equals item) {
    return new EqualsScopeNested(item);
  }

  public GreaterThanOrEqualScopeNested<A> withNewGreaterThanOrEqualScope() {
    return new GreaterThanOrEqualScopeNested(null);
  }

  public A withNewGreaterThanOrEqualScope(Object left, Object right) {
    return (A) this.withScope(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualScopeNested<A> withNewGreaterThanOrEqualScopeLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualScopeNested(item);
  }

  public GreaterThanScopeNested<A> withNewGreaterThanScope() {
    return new GreaterThanScopeNested(null);
  }

  public A withNewGreaterThanScope(Object left, Object right) {
    return (A) this.withScope(new GreaterThan(left, right));
  }

  public GreaterThanScopeNested<A> withNewGreaterThanScopeLike(GreaterThan item) {
    return new GreaterThanScopeNested(item);
  }

  public IndexScopeNested<A> withNewIndexScope() {
    return new IndexScopeNested(null);
  }

  public IndexScopeNested<A> withNewIndexScopeLike(Index item) {
    return new IndexScopeNested(item);
  }

  public InstanceOfScopeNested<A> withNewInstanceOfScope() {
    return new InstanceOfScopeNested(null);
  }

  public InstanceOfScopeNested<A> withNewInstanceOfScopeLike(InstanceOf item) {
    return new InstanceOfScopeNested(item);
  }

  public InverseScopeNested<A> withNewInverseScope() {
    return new InverseScopeNested(null);
  }

  public InverseScopeNested<A> withNewInverseScopeLike(Inverse item) {
    return new InverseScopeNested(item);
  }

  public LambdaScopeNested<A> withNewLambdaScope() {
    return new LambdaScopeNested(null);
  }

  public LambdaScopeNested<A> withNewLambdaScopeLike(Lambda item) {
    return new LambdaScopeNested(item);
  }

  public LeftShiftScopeNested<A> withNewLeftShiftScope() {
    return new LeftShiftScopeNested(null);
  }

  public A withNewLeftShiftScope(Object left, Object right) {
    return (A) this.withScope(new LeftShift(left, right));
  }

  public LeftShiftScopeNested<A> withNewLeftShiftScopeLike(LeftShift item) {
    return new LeftShiftScopeNested(item);
  }

  public LessThanOrEqualScopeNested<A> withNewLessThanOrEqualScope() {
    return new LessThanOrEqualScopeNested(null);
  }

  public A withNewLessThanOrEqualScope(Object left, Object right) {
    return (A) this.withScope(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualScopeNested<A> withNewLessThanOrEqualScopeLike(LessThanOrEqual item) {
    return new LessThanOrEqualScopeNested(item);
  }

  public LessThanScopeNested<A> withNewLessThanScope() {
    return new LessThanScopeNested(null);
  }

  public A withNewLessThanScope(Object left, Object right) {
    return (A) this.withScope(new LessThan(left, right));
  }

  public LessThanScopeNested<A> withNewLessThanScopeLike(LessThan item) {
    return new LessThanScopeNested(item);
  }

  public LogicalAndScopeNested<A> withNewLogicalAndScope() {
    return new LogicalAndScopeNested(null);
  }

  public A withNewLogicalAndScope(Object left, Object right) {
    return (A) this.withScope(new LogicalAnd(left, right));
  }

  public LogicalAndScopeNested<A> withNewLogicalAndScopeLike(LogicalAnd item) {
    return new LogicalAndScopeNested(item);
  }

  public LogicalOrScopeNested<A> withNewLogicalOrScope() {
    return new LogicalOrScopeNested(null);
  }

  public A withNewLogicalOrScope(Object left, Object right) {
    return (A) this.withScope(new LogicalOr(left, right));
  }

  public LogicalOrScopeNested<A> withNewLogicalOrScopeLike(LogicalOr item) {
    return new LogicalOrScopeNested(item);
  }

  public MethodCallScopeNested<A> withNewMethodCallScope() {
    return new MethodCallScopeNested(null);
  }

  public MethodCallScopeNested<A> withNewMethodCallScopeLike(MethodCall item) {
    return new MethodCallScopeNested(item);
  }

  public MinusScopeNested<A> withNewMinusScope() {
    return new MinusScopeNested(null);
  }

  public A withNewMinusScope(Object left, Object right) {
    return (A) this.withScope(new Minus(left, right));
  }

  public MinusScopeNested<A> withNewMinusScopeLike(Minus item) {
    return new MinusScopeNested(item);
  }

  public ModuloScopeNested<A> withNewModuloScope() {
    return new ModuloScopeNested(null);
  }

  public A withNewModuloScope(Object left, Object right) {
    return (A) this.withScope(new Modulo(left, right));
  }

  public ModuloScopeNested<A> withNewModuloScopeLike(Modulo item) {
    return new ModuloScopeNested(item);
  }

  public MultiplyScopeNested<A> withNewMultiplyScope() {
    return new MultiplyScopeNested(null);
  }

  public A withNewMultiplyScope(Object left, Object right) {
    return (A) this.withScope(new Multiply(left, right));
  }

  public MultiplyScopeNested<A> withNewMultiplyScopeLike(Multiply item) {
    return new MultiplyScopeNested(item);
  }

  public NegativeScopeNested<A> withNewNegativeScope() {
    return new NegativeScopeNested(null);
  }

  public NegativeScopeNested<A> withNewNegativeScopeLike(Negative item) {
    return new NegativeScopeNested(item);
  }

  public NewArrayScopeNested<A> withNewNewArrayScope() {
    return new NewArrayScopeNested(null);
  }

  public A withNewNewArrayScope(Class type, Integer[] sizes) {
    return (A) this.withScope(new NewArray(type, sizes));
  }

  public NewArrayScopeNested<A> withNewNewArrayScopeLike(NewArray item) {
    return new NewArrayScopeNested(item);
  }

  public NotEqualsScopeNested<A> withNewNotEqualsScope() {
    return new NotEqualsScopeNested(null);
  }

  public A withNewNotEqualsScope(Object left, Object right) {
    return (A) this.withScope(new NotEquals(left, right));
  }

  public NotEqualsScopeNested<A> withNewNotEqualsScopeLike(NotEquals item) {
    return new NotEqualsScopeNested(item);
  }

  public NotScopeNested<A> withNewNotScope() {
    return new NotScopeNested(null);
  }

  public NotScopeNested<A> withNewNotScopeLike(Not item) {
    return new NotScopeNested(item);
  }

  public PlusScopeNested<A> withNewPlusScope() {
    return new PlusScopeNested(null);
  }

  public A withNewPlusScope(Object left, Object right) {
    return (A) this.withScope(new Plus(left, right));
  }

  public PlusScopeNested<A> withNewPlusScopeLike(Plus item) {
    return new PlusScopeNested(item);
  }

  public PositiveScopeNested<A> withNewPositiveScope() {
    return new PositiveScopeNested(null);
  }

  public PositiveScopeNested<A> withNewPositiveScopeLike(Positive item) {
    return new PositiveScopeNested(item);
  }

  public PostDecrementScopeNested<A> withNewPostDecrementScope() {
    return new PostDecrementScopeNested(null);
  }

  public PostDecrementScopeNested<A> withNewPostDecrementScopeLike(PostDecrement item) {
    return new PostDecrementScopeNested(item);
  }

  public PostIncrementScopeNested<A> withNewPostIncrementScope() {
    return new PostIncrementScopeNested(null);
  }

  public PostIncrementScopeNested<A> withNewPostIncrementScopeLike(PostIncrement item) {
    return new PostIncrementScopeNested(item);
  }

  public PreDecrementScopeNested<A> withNewPreDecrementScope() {
    return new PreDecrementScopeNested(null);
  }

  public PreDecrementScopeNested<A> withNewPreDecrementScopeLike(PreDecrement item) {
    return new PreDecrementScopeNested(item);
  }

  public PreIncrementScopeNested<A> withNewPreIncrementScope() {
    return new PreIncrementScopeNested(null);
  }

  public PreIncrementScopeNested<A> withNewPreIncrementScopeLike(PreIncrement item) {
    return new PreIncrementScopeNested(item);
  }

  public PropertyRefScopeNested<A> withNewPropertyRefScope() {
    return new PropertyRefScopeNested(null);
  }

  public PropertyRefScopeNested<A> withNewPropertyRefScopeLike(PropertyRef item) {
    return new PropertyRefScopeNested(item);
  }

  public PropertyScopeNested<A> withNewPropertyScope() {
    return new PropertyScopeNested(null);
  }

  public PropertyScopeNested<A> withNewPropertyScopeLike(Property item) {
    return new PropertyScopeNested(item);
  }

  public RightShiftScopeNested<A> withNewRightShiftScope() {
    return new RightShiftScopeNested(null);
  }

  public A withNewRightShiftScope(Object left, Object right) {
    return (A) this.withScope(new RightShift(left, right));
  }

  public RightShiftScopeNested<A> withNewRightShiftScopeLike(RightShift item) {
    return new RightShiftScopeNested(item);
  }

  public RightUnsignedShiftScopeNested<A> withNewRightUnsignedShiftScope() {
    return new RightUnsignedShiftScopeNested(null);
  }

  public A withNewRightUnsignedShiftScope(Object left, Object right) {
    return (A) this.withScope(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftScopeNested<A> withNewRightUnsignedShiftScopeLike(RightUnsignedShift item) {
    return new RightUnsignedShiftScopeNested(item);
  }

  public SuperScopeNested<A> withNewSuperScope() {
    return new SuperScopeNested(null);
  }

  public SuperScopeNested<A> withNewSuperScopeLike(Super item) {
    return new SuperScopeNested(item);
  }

  public TernaryScopeNested<A> withNewTernaryScope() {
    return new TernaryScopeNested(null);
  }

  public TernaryScopeNested<A> withNewTernaryScopeLike(Ternary item) {
    return new TernaryScopeNested(item);
  }

  public ThisScopeNested<A> withNewThisScope() {
    return new ThisScopeNested(null);
  }

  public ThisScopeNested<A> withNewThisScopeLike(This item) {
    return new ThisScopeNested(item);
  }

  public ValueRefScopeNested<A> withNewValueRefScope() {
    return new ValueRefScopeNested(null);
  }

  public A withNewValueRefScope(Object value) {
    return (A) this.withScope(new ValueRef(value));
  }

  public ValueRefScopeNested<A> withNewValueRefScopeLike(ValueRef item) {
    return new ValueRefScopeNested(item);
  }

  public XorScopeNested<A> withNewXorScope() {
    return new XorScopeNested(null);
  }

  public A withNewXorScope(Object left, Object right) {
    return (A) this.withScope(new Xor(left, right));
  }

  public XorScopeNested<A> withNewXorScopeLike(Xor item) {
    return new XorScopeNested(item);
  }

  public A withScope(Expression scope) {
    if (scope == null) {
      this.scope = null;
      this._visitables.remove("scope");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(scope);
      this._visitables.get("scope").clear();
      this._visitables.get("scope").add(builder);
      this.scope = builder;
      return (A) this;
    }
  }

  public class AssignScopeNested<N> extends AssignFluent<AssignScopeNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignScopeNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endAssignScope() {
      return and();
    }

  }

  public class BinaryExpressionScopeNested<N> extends BinaryExpressionFluent<BinaryExpressionScopeNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionScopeNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endBinaryExpressionScope() {
      return and();
    }

  }

  public class BitwiseAndScopeNested<N> extends BitwiseAndFluent<BitwiseAndScopeNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndScopeNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endBitwiseAndScope() {
      return and();
    }

  }

  public class BitwiseOrScopeNested<N> extends BitwiseOrFluent<BitwiseOrScopeNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrScopeNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endBitwiseOrScope() {
      return and();
    }

  }

  public class CastScopeNested<N> extends CastFluent<CastScopeNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastScopeNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endCastScope() {
      return and();
    }

  }

  public class ClassRefScopeNested<N> extends ClassRefFluent<ClassRefScopeNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefScopeNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endClassRefScope() {
      return and();
    }

  }

  public class ConstructScopeNested<N> extends ConstructFluent<ConstructScopeNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructScopeNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endConstructScope() {
      return and();
    }

  }

  public class ContextRefScopeNested<N> extends ContextRefFluent<ContextRefScopeNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefScopeNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endContextRefScope() {
      return and();
    }

  }

  public class DeclareScopeNested<N> extends DeclareFluent<DeclareScopeNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareScopeNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endDeclareScope() {
      return and();
    }

  }

  public class DivideScopeNested<N> extends DivideFluent<DivideScopeNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideScopeNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endDivideScope() {
      return and();
    }

  }

  public class DotClassScopeNested<N> extends DotClassFluent<DotClassScopeNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassScopeNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endDotClassScope() {
      return and();
    }

  }

  public class EmptyScopeNested<N> extends EmptyFluent<EmptyScopeNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyScopeNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endEmptyScope() {
      return and();
    }

  }

  public class EnclosedScopeNested<N> extends EnclosedFluent<EnclosedScopeNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedScopeNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endEnclosedScope() {
      return and();
    }

  }

  public class EqualsScopeNested<N> extends EqualsFluent<EqualsScopeNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsScopeNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endEqualsScope() {
      return and();
    }

  }

  public class GreaterThanOrEqualScopeNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualScopeNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualScopeNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endGreaterThanOrEqualScope() {
      return and();
    }

  }

  public class GreaterThanScopeNested<N> extends GreaterThanFluent<GreaterThanScopeNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanScopeNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endGreaterThanScope() {
      return and();
    }

  }

  public class IndexScopeNested<N> extends IndexFluent<IndexScopeNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexScopeNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endIndexScope() {
      return and();
    }

  }

  public class InstanceOfScopeNested<N> extends InstanceOfFluent<InstanceOfScopeNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfScopeNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endInstanceOfScope() {
      return and();
    }

  }

  public class InverseScopeNested<N> extends InverseFluent<InverseScopeNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseScopeNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endInverseScope() {
      return and();
    }

  }

  public class LambdaScopeNested<N> extends LambdaFluent<LambdaScopeNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaScopeNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endLambdaScope() {
      return and();
    }

  }

  public class LeftShiftScopeNested<N> extends LeftShiftFluent<LeftShiftScopeNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftScopeNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endLeftShiftScope() {
      return and();
    }

  }

  public class LessThanOrEqualScopeNested<N> extends LessThanOrEqualFluent<LessThanOrEqualScopeNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualScopeNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endLessThanOrEqualScope() {
      return and();
    }

  }

  public class LessThanScopeNested<N> extends LessThanFluent<LessThanScopeNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanScopeNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endLessThanScope() {
      return and();
    }

  }

  public class LogicalAndScopeNested<N> extends LogicalAndFluent<LogicalAndScopeNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndScopeNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endLogicalAndScope() {
      return and();
    }

  }

  public class LogicalOrScopeNested<N> extends LogicalOrFluent<LogicalOrScopeNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrScopeNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endLogicalOrScope() {
      return and();
    }

  }

  public class MethodCallScopeNested<N> extends MethodCallFluent<MethodCallScopeNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallScopeNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endMethodCallScope() {
      return and();
    }

  }

  public class MinusScopeNested<N> extends MinusFluent<MinusScopeNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusScopeNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endMinusScope() {
      return and();
    }

  }

  public class ModuloScopeNested<N> extends ModuloFluent<ModuloScopeNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloScopeNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endModuloScope() {
      return and();
    }

  }

  public class MultiplyScopeNested<N> extends MultiplyFluent<MultiplyScopeNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyScopeNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endMultiplyScope() {
      return and();
    }

  }

  public class NegativeScopeNested<N> extends NegativeFluent<NegativeScopeNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeScopeNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endNegativeScope() {
      return and();
    }

  }

  public class NewArrayScopeNested<N> extends NewArrayFluent<NewArrayScopeNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayScopeNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endNewArrayScope() {
      return and();
    }

  }

  public class NotEqualsScopeNested<N> extends NotEqualsFluent<NotEqualsScopeNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsScopeNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endNotEqualsScope() {
      return and();
    }

  }

  public class NotScopeNested<N> extends NotFluent<NotScopeNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotScopeNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endNotScope() {
      return and();
    }

  }

  public class PlusScopeNested<N> extends PlusFluent<PlusScopeNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusScopeNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPlusScope() {
      return and();
    }

  }

  public class PositiveScopeNested<N> extends PositiveFluent<PositiveScopeNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveScopeNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPositiveScope() {
      return and();
    }

  }

  public class PostDecrementScopeNested<N> extends PostDecrementFluent<PostDecrementScopeNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementScopeNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPostDecrementScope() {
      return and();
    }

  }

  public class PostIncrementScopeNested<N> extends PostIncrementFluent<PostIncrementScopeNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementScopeNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPostIncrementScope() {
      return and();
    }

  }

  public class PreDecrementScopeNested<N> extends PreDecrementFluent<PreDecrementScopeNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementScopeNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPreDecrementScope() {
      return and();
    }

  }

  public class PreIncrementScopeNested<N> extends PreIncrementFluent<PreIncrementScopeNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementScopeNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPreIncrementScope() {
      return and();
    }

  }

  public class PropertyRefScopeNested<N> extends PropertyRefFluent<PropertyRefScopeNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefScopeNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPropertyRefScope() {
      return and();
    }

  }

  public class PropertyScopeNested<N> extends PropertyFluent<PropertyScopeNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyScopeNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endPropertyScope() {
      return and();
    }

  }

  public class RightShiftScopeNested<N> extends RightShiftFluent<RightShiftScopeNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftScopeNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endRightShiftScope() {
      return and();
    }

  }

  public class RightUnsignedShiftScopeNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftScopeNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftScopeNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endRightUnsignedShiftScope() {
      return and();
    }

  }

  public class SuperScopeNested<N> extends SuperFluent<SuperScopeNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperScopeNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endSuperScope() {
      return and();
    }

  }

  public class TernaryScopeNested<N> extends TernaryFluent<TernaryScopeNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryScopeNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endTernaryScope() {
      return and();
    }

  }

  public class ThisScopeNested<N> extends ThisFluent<ThisScopeNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisScopeNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endThisScope() {
      return and();
    }

  }

  public class ValueRefScopeNested<N> extends ValueRefFluent<ValueRefScopeNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefScopeNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endValueRefScope() {
      return and();
    }

  }

  public class XorScopeNested<N> extends XorFluent<XorScopeNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorScopeNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) WithScopeFluent.this.withScope(builder.build());
    }

    public N endXorScope() {
      return and();
    }

  }
}
