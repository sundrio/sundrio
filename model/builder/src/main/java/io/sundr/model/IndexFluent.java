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
public class IndexFluent<A extends IndexFluent<A>> extends BaseFluent<A> {
  public IndexFluent() {
  }

  public IndexFluent(Index instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> scope;
  private VisitableBuilder<? extends Expression, ?> expression;

  protected void copyInstance(Index instance) {
    if (instance != null) {
      this.withScope(instance.getScope());
      this.withExpression(instance.getExpression());
    }
  }

  public Expression buildScope() {
    return this.scope != null ? this.scope.build() : null;
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

  public boolean hasScope() {
    return this.scope != null;
  }

  public MultiplyScopeNested<A> withNewMultiplyScope() {
    return new MultiplyScopeNested(null);
  }

  public MultiplyScopeNested<A> withNewMultiplyScopeLike(Multiply item) {
    return new MultiplyScopeNested(item);
  }

  public A withNewMultiplyScope(Object left, Object right) {
    return (A) withScope(new Multiply(left, right));
  }

  public NewArrayScopeNested<A> withNewNewArrayScope() {
    return new NewArrayScopeNested(null);
  }

  public NewArrayScopeNested<A> withNewNewArrayScopeLike(NewArray item) {
    return new NewArrayScopeNested(item);
  }

  public A withNewNewArrayScope(Class type, Integer[] sizes) {
    return (A) withScope(new NewArray(type, sizes));
  }

  public InstanceOfScopeNested<A> withNewInstanceOfScope() {
    return new InstanceOfScopeNested(null);
  }

  public InstanceOfScopeNested<A> withNewInstanceOfScopeLike(InstanceOf item) {
    return new InstanceOfScopeNested(item);
  }

  public MethodCallScopeNested<A> withNewMethodCallScope() {
    return new MethodCallScopeNested(null);
  }

  public MethodCallScopeNested<A> withNewMethodCallScopeLike(MethodCall item) {
    return new MethodCallScopeNested(item);
  }

  public InverseScopeNested<A> withNewInverseScope() {
    return new InverseScopeNested(null);
  }

  public InverseScopeNested<A> withNewInverseScopeLike(Inverse item) {
    return new InverseScopeNested(item);
  }

  public IndexScopeNested<A> withNewIndexScope() {
    return new IndexScopeNested(null);
  }

  public IndexScopeNested<A> withNewIndexScopeLike(Index item) {
    return new IndexScopeNested(item);
  }

  public GreaterThanOrEqualScopeNested<A> withNewGreaterThanOrEqualScope() {
    return new GreaterThanOrEqualScopeNested(null);
  }

  public GreaterThanOrEqualScopeNested<A> withNewGreaterThanOrEqualScopeLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualScopeNested(item);
  }

  public A withNewGreaterThanOrEqualScope(Object left, Object right) {
    return (A) withScope(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndScopeNested<A> withNewBitwiseAndScope() {
    return new BitwiseAndScopeNested(null);
  }

  public BitwiseAndScopeNested<A> withNewBitwiseAndScopeLike(BitwiseAnd item) {
    return new BitwiseAndScopeNested(item);
  }

  public A withNewBitwiseAndScope(Object left, Object right) {
    return (A) withScope(new BitwiseAnd(left, right));
  }

  public MinusScopeNested<A> withNewMinusScope() {
    return new MinusScopeNested(null);
  }

  public MinusScopeNested<A> withNewMinusScopeLike(Minus item) {
    return new MinusScopeNested(item);
  }

  public A withNewMinusScope(Object left, Object right) {
    return (A) withScope(new Minus(left, right));
  }

  public LogicalOrScopeNested<A> withNewLogicalOrScope() {
    return new LogicalOrScopeNested(null);
  }

  public LogicalOrScopeNested<A> withNewLogicalOrScopeLike(LogicalOr item) {
    return new LogicalOrScopeNested(item);
  }

  public A withNewLogicalOrScope(Object left, Object right) {
    return (A) withScope(new LogicalOr(left, right));
  }

  public NotEqualsScopeNested<A> withNewNotEqualsScope() {
    return new NotEqualsScopeNested(null);
  }

  public NotEqualsScopeNested<A> withNewNotEqualsScopeLike(NotEquals item) {
    return new NotEqualsScopeNested(item);
  }

  public A withNewNotEqualsScope(Object left, Object right) {
    return (A) withScope(new NotEquals(left, right));
  }

  public DivideScopeNested<A> withNewDivideScope() {
    return new DivideScopeNested(null);
  }

  public DivideScopeNested<A> withNewDivideScopeLike(Divide item) {
    return new DivideScopeNested(item);
  }

  public A withNewDivideScope(Object left, Object right) {
    return (A) withScope(new Divide(left, right));
  }

  public LessThanScopeNested<A> withNewLessThanScope() {
    return new LessThanScopeNested(null);
  }

  public LessThanScopeNested<A> withNewLessThanScopeLike(LessThan item) {
    return new LessThanScopeNested(item);
  }

  public A withNewLessThanScope(Object left, Object right) {
    return (A) withScope(new LessThan(left, right));
  }

  public BitwiseOrScopeNested<A> withNewBitwiseOrScope() {
    return new BitwiseOrScopeNested(null);
  }

  public BitwiseOrScopeNested<A> withNewBitwiseOrScopeLike(BitwiseOr item) {
    return new BitwiseOrScopeNested(item);
  }

  public A withNewBitwiseOrScope(Object left, Object right) {
    return (A) withScope(new BitwiseOr(left, right));
  }

  public PropertyRefScopeNested<A> withNewPropertyRefScope() {
    return new PropertyRefScopeNested(null);
  }

  public PropertyRefScopeNested<A> withNewPropertyRefScopeLike(PropertyRef item) {
    return new PropertyRefScopeNested(item);
  }

  public RightShiftScopeNested<A> withNewRightShiftScope() {
    return new RightShiftScopeNested(null);
  }

  public RightShiftScopeNested<A> withNewRightShiftScopeLike(RightShift item) {
    return new RightShiftScopeNested(item);
  }

  public A withNewRightShiftScope(Object left, Object right) {
    return (A) withScope(new RightShift(left, right));
  }

  public SuperScopeNested<A> withNewSuperScope() {
    return new SuperScopeNested(null);
  }

  public SuperScopeNested<A> withNewSuperScopeLike(Super item) {
    return new SuperScopeNested(item);
  }

  public GreaterThanScopeNested<A> withNewGreaterThanScope() {
    return new GreaterThanScopeNested(null);
  }

  public GreaterThanScopeNested<A> withNewGreaterThanScopeLike(GreaterThan item) {
    return new GreaterThanScopeNested(item);
  }

  public A withNewGreaterThanScope(Object left, Object right) {
    return (A) withScope(new GreaterThan(left, right));
  }

  public DeclareScopeNested<A> withNewDeclareScope() {
    return new DeclareScopeNested(null);
  }

  public DeclareScopeNested<A> withNewDeclareScopeLike(Declare item) {
    return new DeclareScopeNested(item);
  }

  public A withNewDeclareScope(Class type, String name) {
    return (A) withScope(new Declare(type, name));
  }

  public A withNewDeclareScope(Class type, String name, Object value) {
    return (A) withScope(new Declare(type, name, value));
  }

  public CastScopeNested<A> withNewCastScope() {
    return new CastScopeNested(null);
  }

  public CastScopeNested<A> withNewCastScopeLike(Cast item) {
    return new CastScopeNested(item);
  }

  public ModuloScopeNested<A> withNewModuloScope() {
    return new ModuloScopeNested(null);
  }

  public ModuloScopeNested<A> withNewModuloScopeLike(Modulo item) {
    return new ModuloScopeNested(item);
  }

  public A withNewModuloScope(Object left, Object right) {
    return (A) withScope(new Modulo(left, right));
  }

  public ValueRefScopeNested<A> withNewValueRefScope() {
    return new ValueRefScopeNested(null);
  }

  public ValueRefScopeNested<A> withNewValueRefScopeLike(ValueRef item) {
    return new ValueRefScopeNested(item);
  }

  public A withNewValueRefScope(Object value) {
    return (A) withScope(new ValueRef(value));
  }

  public LeftShiftScopeNested<A> withNewLeftShiftScope() {
    return new LeftShiftScopeNested(null);
  }

  public LeftShiftScopeNested<A> withNewLeftShiftScopeLike(LeftShift item) {
    return new LeftShiftScopeNested(item);
  }

  public A withNewLeftShiftScope(Object left, Object right) {
    return (A) withScope(new LeftShift(left, right));
  }

  public TernaryScopeNested<A> withNewTernaryScope() {
    return new TernaryScopeNested(null);
  }

  public TernaryScopeNested<A> withNewTernaryScopeLike(Ternary item) {
    return new TernaryScopeNested(item);
  }

  public BinaryExpressionScopeNested<A> withNewBinaryExpressionScope() {
    return new BinaryExpressionScopeNested(null);
  }

  public BinaryExpressionScopeNested<A> withNewBinaryExpressionScopeLike(BinaryExpression item) {
    return new BinaryExpressionScopeNested(item);
  }

  public EqualsScopeNested<A> withNewEqualsScope() {
    return new EqualsScopeNested(null);
  }

  public EqualsScopeNested<A> withNewEqualsScopeLike(Equals item) {
    return new EqualsScopeNested(item);
  }

  public A withNewEqualsScope(Object left, Object right) {
    return (A) withScope(new Equals(left, right));
  }

  public EnclosedScopeNested<A> withNewEnclosedScope() {
    return new EnclosedScopeNested(null);
  }

  public EnclosedScopeNested<A> withNewEnclosedScopeLike(Enclosed item) {
    return new EnclosedScopeNested(item);
  }

  public PreDecrementScopeNested<A> withNewPreDecrementScope() {
    return new PreDecrementScopeNested(null);
  }

  public PreDecrementScopeNested<A> withNewPreDecrementScopeLike(PreDecrement item) {
    return new PreDecrementScopeNested(item);
  }

  public PostDecrementScopeNested<A> withNewPostDecrementScope() {
    return new PostDecrementScopeNested(null);
  }

  public PostDecrementScopeNested<A> withNewPostDecrementScopeLike(PostDecrement item) {
    return new PostDecrementScopeNested(item);
  }

  public LambdaScopeNested<A> withNewLambdaScope() {
    return new LambdaScopeNested(null);
  }

  public LambdaScopeNested<A> withNewLambdaScopeLike(Lambda item) {
    return new LambdaScopeNested(item);
  }

  public NotScopeNested<A> withNewNotScope() {
    return new NotScopeNested(null);
  }

  public NotScopeNested<A> withNewNotScopeLike(Not item) {
    return new NotScopeNested(item);
  }

  public AssignScopeNested<A> withNewAssignScope() {
    return new AssignScopeNested(null);
  }

  public AssignScopeNested<A> withNewAssignScopeLike(Assign item) {
    return new AssignScopeNested(item);
  }

  public ThisScopeNested<A> withNewThisScope() {
    return new ThisScopeNested(null);
  }

  public ThisScopeNested<A> withNewThisScopeLike(This item) {
    return new ThisScopeNested(item);
  }

  public NegativeScopeNested<A> withNewNegativeScope() {
    return new NegativeScopeNested(null);
  }

  public NegativeScopeNested<A> withNewNegativeScopeLike(Negative item) {
    return new NegativeScopeNested(item);
  }

  public LogicalAndScopeNested<A> withNewLogicalAndScope() {
    return new LogicalAndScopeNested(null);
  }

  public LogicalAndScopeNested<A> withNewLogicalAndScopeLike(LogicalAnd item) {
    return new LogicalAndScopeNested(item);
  }

  public A withNewLogicalAndScope(Object left, Object right) {
    return (A) withScope(new LogicalAnd(left, right));
  }

  public PostIncrementScopeNested<A> withNewPostIncrementScope() {
    return new PostIncrementScopeNested(null);
  }

  public PostIncrementScopeNested<A> withNewPostIncrementScopeLike(PostIncrement item) {
    return new PostIncrementScopeNested(item);
  }

  public RightUnsignedShiftScopeNested<A> withNewRightUnsignedShiftScope() {
    return new RightUnsignedShiftScopeNested(null);
  }

  public RightUnsignedShiftScopeNested<A> withNewRightUnsignedShiftScopeLike(RightUnsignedShift item) {
    return new RightUnsignedShiftScopeNested(item);
  }

  public A withNewRightUnsignedShiftScope(Object left, Object right) {
    return (A) withScope(new RightUnsignedShift(left, right));
  }

  public PlusScopeNested<A> withNewPlusScope() {
    return new PlusScopeNested(null);
  }

  public PlusScopeNested<A> withNewPlusScopeLike(Plus item) {
    return new PlusScopeNested(item);
  }

  public A withNewPlusScope(Object left, Object right) {
    return (A) withScope(new Plus(left, right));
  }

  public ConstructScopeNested<A> withNewConstructScope() {
    return new ConstructScopeNested(null);
  }

  public ConstructScopeNested<A> withNewConstructScopeLike(Construct item) {
    return new ConstructScopeNested(item);
  }

  public XorScopeNested<A> withNewXorScope() {
    return new XorScopeNested(null);
  }

  public XorScopeNested<A> withNewXorScopeLike(Xor item) {
    return new XorScopeNested(item);
  }

  public A withNewXorScope(Object left, Object right) {
    return (A) withScope(new Xor(left, right));
  }

  public PreIncrementScopeNested<A> withNewPreIncrementScope() {
    return new PreIncrementScopeNested(null);
  }

  public PreIncrementScopeNested<A> withNewPreIncrementScopeLike(PreIncrement item) {
    return new PreIncrementScopeNested(item);
  }

  public PropertyScopeNested<A> withNewPropertyScope() {
    return new PropertyScopeNested(null);
  }

  public PropertyScopeNested<A> withNewPropertyScopeLike(Property item) {
    return new PropertyScopeNested(item);
  }

  public LessThanOrEqualScopeNested<A> withNewLessThanOrEqualScope() {
    return new LessThanOrEqualScopeNested(null);
  }

  public LessThanOrEqualScopeNested<A> withNewLessThanOrEqualScopeLike(LessThanOrEqual item) {
    return new LessThanOrEqualScopeNested(item);
  }

  public A withNewLessThanOrEqualScope(Object left, Object right) {
    return (A) withScope(new LessThanOrEqual(left, right));
  }

  public PositiveScopeNested<A> withNewPositiveScope() {
    return new PositiveScopeNested(null);
  }

  public PositiveScopeNested<A> withNewPositiveScopeLike(Positive item) {
    return new PositiveScopeNested(item);
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

  public SuperExpressionNested<A> withNewSuperExpression() {
    return new SuperExpressionNested(null);
  }

  public SuperExpressionNested<A> withNewSuperExpressionLike(Super item) {
    return new SuperExpressionNested(item);
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

  public PropertyExpressionNested<A> withNewPropertyExpression() {
    return new PropertyExpressionNested(null);
  }

  public PropertyExpressionNested<A> withNewPropertyExpressionLike(Property item) {
    return new PropertyExpressionNested(item);
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
    IndexFluent that = (IndexFluent) o;
    if (!java.util.Objects.equals(scope, that.scope))
      return false;
    if (!java.util.Objects.equals(expression, that.expression))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(scope, expression, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (scope != null) {
      sb.append("scope:");
      sb.append(scope + ",");
    }
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

  public class MultiplyScopeNested<N> extends MultiplyFluent<MultiplyScopeNested<N>> implements Nested<N> {
    MultiplyScopeNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endMultiplyScope() {
      return and();
    }

  }

  public class NewArrayScopeNested<N> extends NewArrayFluent<NewArrayScopeNested<N>> implements Nested<N> {
    NewArrayScopeNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endNewArrayScope() {
      return and();
    }

  }

  public class InstanceOfScopeNested<N> extends InstanceOfFluent<InstanceOfScopeNested<N>> implements Nested<N> {
    InstanceOfScopeNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endInstanceOfScope() {
      return and();
    }

  }

  public class MethodCallScopeNested<N> extends MethodCallFluent<MethodCallScopeNested<N>> implements Nested<N> {
    MethodCallScopeNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endMethodCallScope() {
      return and();
    }

  }

  public class InverseScopeNested<N> extends InverseFluent<InverseScopeNested<N>> implements Nested<N> {
    InverseScopeNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endInverseScope() {
      return and();
    }

  }

  public class IndexScopeNested<N> extends IndexFluent<IndexScopeNested<N>> implements Nested<N> {
    IndexScopeNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endIndexScope() {
      return and();
    }

  }

  public class GreaterThanOrEqualScopeNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualScopeNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualScopeNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endGreaterThanOrEqualScope() {
      return and();
    }

  }

  public class BitwiseAndScopeNested<N> extends BitwiseAndFluent<BitwiseAndScopeNested<N>> implements Nested<N> {
    BitwiseAndScopeNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endBitwiseAndScope() {
      return and();
    }

  }

  public class MinusScopeNested<N> extends MinusFluent<MinusScopeNested<N>> implements Nested<N> {
    MinusScopeNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endMinusScope() {
      return and();
    }

  }

  public class LogicalOrScopeNested<N> extends LogicalOrFluent<LogicalOrScopeNested<N>> implements Nested<N> {
    LogicalOrScopeNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endLogicalOrScope() {
      return and();
    }

  }

  public class NotEqualsScopeNested<N> extends NotEqualsFluent<NotEqualsScopeNested<N>> implements Nested<N> {
    NotEqualsScopeNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endNotEqualsScope() {
      return and();
    }

  }

  public class DivideScopeNested<N> extends DivideFluent<DivideScopeNested<N>> implements Nested<N> {
    DivideScopeNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endDivideScope() {
      return and();
    }

  }

  public class LessThanScopeNested<N> extends LessThanFluent<LessThanScopeNested<N>> implements Nested<N> {
    LessThanScopeNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endLessThanScope() {
      return and();
    }

  }

  public class BitwiseOrScopeNested<N> extends BitwiseOrFluent<BitwiseOrScopeNested<N>> implements Nested<N> {
    BitwiseOrScopeNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endBitwiseOrScope() {
      return and();
    }

  }

  public class PropertyRefScopeNested<N> extends PropertyRefFluent<PropertyRefScopeNested<N>> implements Nested<N> {
    PropertyRefScopeNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPropertyRefScope() {
      return and();
    }

  }

  public class RightShiftScopeNested<N> extends RightShiftFluent<RightShiftScopeNested<N>> implements Nested<N> {
    RightShiftScopeNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endRightShiftScope() {
      return and();
    }

  }

  public class SuperScopeNested<N> extends SuperFluent<SuperScopeNested<N>> implements Nested<N> {
    SuperScopeNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    SuperBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endSuperScope() {
      return and();
    }

  }

  public class GreaterThanScopeNested<N> extends GreaterThanFluent<GreaterThanScopeNested<N>> implements Nested<N> {
    GreaterThanScopeNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endGreaterThanScope() {
      return and();
    }

  }

  public class DeclareScopeNested<N> extends DeclareFluent<DeclareScopeNested<N>> implements Nested<N> {
    DeclareScopeNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endDeclareScope() {
      return and();
    }

  }

  public class CastScopeNested<N> extends CastFluent<CastScopeNested<N>> implements Nested<N> {
    CastScopeNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endCastScope() {
      return and();
    }

  }

  public class ModuloScopeNested<N> extends ModuloFluent<ModuloScopeNested<N>> implements Nested<N> {
    ModuloScopeNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endModuloScope() {
      return and();
    }

  }

  public class ValueRefScopeNested<N> extends ValueRefFluent<ValueRefScopeNested<N>> implements Nested<N> {
    ValueRefScopeNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endValueRefScope() {
      return and();
    }

  }

  public class LeftShiftScopeNested<N> extends LeftShiftFluent<LeftShiftScopeNested<N>> implements Nested<N> {
    LeftShiftScopeNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endLeftShiftScope() {
      return and();
    }

  }

  public class TernaryScopeNested<N> extends TernaryFluent<TernaryScopeNested<N>> implements Nested<N> {
    TernaryScopeNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endTernaryScope() {
      return and();
    }

  }

  public class BinaryExpressionScopeNested<N> extends BinaryExpressionFluent<BinaryExpressionScopeNested<N>>
      implements Nested<N> {
    BinaryExpressionScopeNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endBinaryExpressionScope() {
      return and();
    }

  }

  public class EqualsScopeNested<N> extends EqualsFluent<EqualsScopeNested<N>> implements Nested<N> {
    EqualsScopeNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endEqualsScope() {
      return and();
    }

  }

  public class EnclosedScopeNested<N> extends EnclosedFluent<EnclosedScopeNested<N>> implements Nested<N> {
    EnclosedScopeNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endEnclosedScope() {
      return and();
    }

  }

  public class PreDecrementScopeNested<N> extends PreDecrementFluent<PreDecrementScopeNested<N>> implements Nested<N> {
    PreDecrementScopeNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPreDecrementScope() {
      return and();
    }

  }

  public class PostDecrementScopeNested<N> extends PostDecrementFluent<PostDecrementScopeNested<N>> implements Nested<N> {
    PostDecrementScopeNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPostDecrementScope() {
      return and();
    }

  }

  public class LambdaScopeNested<N> extends LambdaFluent<LambdaScopeNested<N>> implements Nested<N> {
    LambdaScopeNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endLambdaScope() {
      return and();
    }

  }

  public class NotScopeNested<N> extends NotFluent<NotScopeNested<N>> implements Nested<N> {
    NotScopeNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endNotScope() {
      return and();
    }

  }

  public class AssignScopeNested<N> extends AssignFluent<AssignScopeNested<N>> implements Nested<N> {
    AssignScopeNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endAssignScope() {
      return and();
    }

  }

  public class ThisScopeNested<N> extends ThisFluent<ThisScopeNested<N>> implements Nested<N> {
    ThisScopeNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endThisScope() {
      return and();
    }

  }

  public class NegativeScopeNested<N> extends NegativeFluent<NegativeScopeNested<N>> implements Nested<N> {
    NegativeScopeNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endNegativeScope() {
      return and();
    }

  }

  public class LogicalAndScopeNested<N> extends LogicalAndFluent<LogicalAndScopeNested<N>> implements Nested<N> {
    LogicalAndScopeNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endLogicalAndScope() {
      return and();
    }

  }

  public class PostIncrementScopeNested<N> extends PostIncrementFluent<PostIncrementScopeNested<N>> implements Nested<N> {
    PostIncrementScopeNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPostIncrementScope() {
      return and();
    }

  }

  public class RightUnsignedShiftScopeNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftScopeNested<N>>
      implements Nested<N> {
    RightUnsignedShiftScopeNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endRightUnsignedShiftScope() {
      return and();
    }

  }

  public class PlusScopeNested<N> extends PlusFluent<PlusScopeNested<N>> implements Nested<N> {
    PlusScopeNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPlusScope() {
      return and();
    }

  }

  public class ConstructScopeNested<N> extends ConstructFluent<ConstructScopeNested<N>> implements Nested<N> {
    ConstructScopeNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endConstructScope() {
      return and();
    }

  }

  public class XorScopeNested<N> extends XorFluent<XorScopeNested<N>> implements Nested<N> {
    XorScopeNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endXorScope() {
      return and();
    }

  }

  public class PreIncrementScopeNested<N> extends PreIncrementFluent<PreIncrementScopeNested<N>> implements Nested<N> {
    PreIncrementScopeNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPreIncrementScope() {
      return and();
    }

  }

  public class PropertyScopeNested<N> extends PropertyFluent<PropertyScopeNested<N>> implements Nested<N> {
    PropertyScopeNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPropertyScope() {
      return and();
    }

  }

  public class LessThanOrEqualScopeNested<N> extends LessThanOrEqualFluent<LessThanOrEqualScopeNested<N>> implements Nested<N> {
    LessThanOrEqualScopeNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endLessThanOrEqualScope() {
      return and();
    }

  }

  public class PositiveScopeNested<N> extends PositiveFluent<PositiveScopeNested<N>> implements Nested<N> {
    PositiveScopeNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withScope(builder.build());
    }

    public N endPositiveScope() {
      return and();
    }

  }

  public class MultiplyExpressionNested<N> extends MultiplyFluent<MultiplyExpressionNested<N>> implements Nested<N> {
    MultiplyExpressionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
    }

    public N endRightShiftExpression() {
      return and();
    }

  }

  public class SuperExpressionNested<N> extends SuperFluent<SuperExpressionNested<N>> implements Nested<N> {
    SuperExpressionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    SuperBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withExpression(builder.build());
    }

    public N endSuperExpression() {
      return and();
    }

  }

  public class GreaterThanExpressionNested<N> extends GreaterThanFluent<GreaterThanExpressionNested<N>> implements Nested<N> {
    GreaterThanExpressionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
    }

    public N endPreIncrementExpression() {
      return and();
    }

  }

  public class PropertyExpressionNested<N> extends PropertyFluent<PropertyExpressionNested<N>> implements Nested<N> {
    PropertyExpressionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;

    public N and() {
      return (N) IndexFluent.this.withExpression(builder.build());
    }

    public N endPropertyExpression() {
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
      return (N) IndexFluent.this.withExpression(builder.build());
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
      return (N) IndexFluent.this.withExpression(builder.build());
    }

    public N endPositiveExpression() {
      return and();
    }

  }

}
