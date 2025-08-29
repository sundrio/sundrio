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
public class PropertyRefFluent<A extends PropertyRefFluent<A>> extends BaseFluent<A> {
  public PropertyRefFluent() {
  }

  public PropertyRefFluent(PropertyRef instance) {
    this.copyInstance(instance);
  }

  private PropertyBuilder property;
  private VisitableBuilder<? extends Expression, ?> scope;

  protected void copyInstance(PropertyRef instance) {
    if (instance != null) {
      this.withProperty(instance.getProperty());
      this.withScope(instance.getScope());
    }
  }

  public Property buildProperty() {
    return this.property != null ? this.property.build() : null;
  }

  public A withProperty(Property property) {
    this._visitables.remove("property");
    if (property != null) {
      this.property = new PropertyBuilder(property);
      this._visitables.get("property").add(this.property);
    } else {
      this.property = null;
      this._visitables.get("property").remove(this.property);
    }
    return (A) this;
  }

  public boolean hasProperty() {
    return this.property != null;
  }

  public PropertyNested<A> withNewProperty() {
    return new PropertyNested(null);
  }

  public PropertyNested<A> withNewPropertyLike(Property item) {
    return new PropertyNested(item);
  }

  public PropertyNested<A> editProperty() {
    return withNewPropertyLike(java.util.Optional.ofNullable(buildProperty()).orElse(null));
  }

  public PropertyNested<A> editOrNewProperty() {
    return withNewPropertyLike(java.util.Optional.ofNullable(buildProperty()).orElse(new PropertyBuilder().build()));
  }

  public PropertyNested<A> editOrNewPropertyLike(Property item) {
    return withNewPropertyLike(java.util.Optional.ofNullable(buildProperty()).orElse(item));
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

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PropertyRefFluent that = (PropertyRefFluent) o;
    if (!java.util.Objects.equals(property, that.property))
      return false;
    if (!java.util.Objects.equals(scope, that.scope))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(property, scope, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (property != null) {
      sb.append("property:");
      sb.append(property + ",");
    }
    if (scope != null) {
      sb.append("scope:");
      sb.append(scope);
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

  public class PropertyNested<N> extends PropertyFluent<PropertyNested<N>> implements Nested<N> {
    PropertyNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;

    public N and() {
      return (N) PropertyRefFluent.this.withProperty(builder.build());
    }

    public N endProperty() {
      return and();
    }

  }

  public class MultiplyScopeNested<N> extends MultiplyFluent<MultiplyScopeNested<N>> implements Nested<N> {
    MultiplyScopeNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
    }

    public N endRightShiftScope() {
      return and();
    }

  }

  public class GreaterThanScopeNested<N> extends GreaterThanFluent<GreaterThanScopeNested<N>> implements Nested<N> {
    GreaterThanScopeNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
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
      return (N) PropertyRefFluent.this.withScope(builder.build());
    }

    public N endPositiveScope() {
      return and();
    }

  }

}
