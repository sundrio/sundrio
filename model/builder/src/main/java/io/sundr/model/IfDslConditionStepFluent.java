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
public class IfDslConditionStepFluent<A extends IfDslConditionStepFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> condition;

  public IfDslConditionStepFluent() {
  }

  public IfDslConditionStepFluent(IfDslConditionStep instance) {
    this.copyInstance(instance);
  }

  public Expression buildCondition() {
    return this.condition != null ? this.condition.build() : null;
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

  protected void copyInstance(IfDslConditionStep instance) {
    if (instance != null) {
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    IfDslConditionStepFluent that = (IfDslConditionStepFluent) o;
    if (!java.util.Objects.equals(condition, that.condition))
      return false;
    return true;
  }

  public boolean hasCondition() {
    return this.condition != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(condition, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (condition != null) {
      sb.append("condition:");
      sb.append(condition);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withCondition(Expression condition) {
    if (condition == null) {
      this.condition = null;
      this._visitables.remove("condition");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(condition);
      this._visitables.get("condition").clear();
      this._visitables.get("condition").add(builder);
      this.condition = builder;
      return (A) this;
    }
  }

  public AssignConditionNested<A> withNewAssignCondition() {
    return new AssignConditionNested(null);
  }

  public AssignConditionNested<A> withNewAssignConditionLike(Assign item) {
    return new AssignConditionNested(item);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionCondition() {
    return new BinaryExpressionConditionNested(null);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionConditionLike(BinaryExpression item) {
    return new BinaryExpressionConditionNested(item);
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndCondition() {
    return new BitwiseAndConditionNested(null);
  }

  public A withNewBitwiseAndCondition(Object left, Object right) {
    return (A) withCondition(new BitwiseAnd(left, right));
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndConditionLike(BitwiseAnd item) {
    return new BitwiseAndConditionNested(item);
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrCondition() {
    return new BitwiseOrConditionNested(null);
  }

  public A withNewBitwiseOrCondition(Object left, Object right) {
    return (A) withCondition(new BitwiseOr(left, right));
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrConditionLike(BitwiseOr item) {
    return new BitwiseOrConditionNested(item);
  }

  public CastConditionNested<A> withNewCastCondition() {
    return new CastConditionNested(null);
  }

  public CastConditionNested<A> withNewCastConditionLike(Cast item) {
    return new CastConditionNested(item);
  }

  public ClassRefConditionNested<A> withNewClassRefCondition() {
    return new ClassRefConditionNested(null);
  }

  public ClassRefConditionNested<A> withNewClassRefConditionLike(ClassRef item) {
    return new ClassRefConditionNested(item);
  }

  public ConstructConditionNested<A> withNewConstructCondition() {
    return new ConstructConditionNested(null);
  }

  public ConstructConditionNested<A> withNewConstructConditionLike(Construct item) {
    return new ConstructConditionNested(item);
  }

  public ContextRefConditionNested<A> withNewContextRefCondition() {
    return new ContextRefConditionNested(null);
  }

  public A withNewContextRefCondition(String name) {
    return (A) withCondition(new ContextRef(name));
  }

  public ContextRefConditionNested<A> withNewContextRefConditionLike(ContextRef item) {
    return new ContextRefConditionNested(item);
  }

  public DeclareConditionNested<A> withNewDeclareCondition() {
    return new DeclareConditionNested(null);
  }

  public A withNewDeclareCondition(Class type, String name) {
    return (A) withCondition(new Declare(type, name));
  }

  public A withNewDeclareCondition(Class type, String name, Object value) {
    return (A) withCondition(new Declare(type, name, value));
  }

  public DeclareConditionNested<A> withNewDeclareConditionLike(Declare item) {
    return new DeclareConditionNested(item);
  }

  public DivideConditionNested<A> withNewDivideCondition() {
    return new DivideConditionNested(null);
  }

  public A withNewDivideCondition(Object left, Object right) {
    return (A) withCondition(new Divide(left, right));
  }

  public DivideConditionNested<A> withNewDivideConditionLike(Divide item) {
    return new DivideConditionNested(item);
  }

  public DotClassConditionNested<A> withNewDotClassCondition() {
    return new DotClassConditionNested(null);
  }

  public DotClassConditionNested<A> withNewDotClassConditionLike(DotClass item) {
    return new DotClassConditionNested(item);
  }

  public EmptyConditionNested<A> withNewEmptyCondition() {
    return new EmptyConditionNested(null);
  }

  public EmptyConditionNested<A> withNewEmptyConditionLike(Empty item) {
    return new EmptyConditionNested(item);
  }

  public EnclosedConditionNested<A> withNewEnclosedCondition() {
    return new EnclosedConditionNested(null);
  }

  public EnclosedConditionNested<A> withNewEnclosedConditionLike(Enclosed item) {
    return new EnclosedConditionNested(item);
  }

  public EqualsConditionNested<A> withNewEqualsCondition() {
    return new EqualsConditionNested(null);
  }

  public A withNewEqualsCondition(Object left, Object right) {
    return (A) withCondition(new Equals(left, right));
  }

  public EqualsConditionNested<A> withNewEqualsConditionLike(Equals item) {
    return new EqualsConditionNested(item);
  }

  public GreaterThanConditionNested<A> withNewGreaterThanCondition() {
    return new GreaterThanConditionNested(null);
  }

  public A withNewGreaterThanCondition(Object left, Object right) {
    return (A) withCondition(new GreaterThan(left, right));
  }

  public GreaterThanConditionNested<A> withNewGreaterThanConditionLike(GreaterThan item) {
    return new GreaterThanConditionNested(item);
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualCondition() {
    return new GreaterThanOrEqualConditionNested(null);
  }

  public A withNewGreaterThanOrEqualCondition(Object left, Object right) {
    return (A) withCondition(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualConditionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualConditionNested(item);
  }

  public IndexConditionNested<A> withNewIndexCondition() {
    return new IndexConditionNested(null);
  }

  public IndexConditionNested<A> withNewIndexConditionLike(Index item) {
    return new IndexConditionNested(item);
  }

  public InstanceOfConditionNested<A> withNewInstanceOfCondition() {
    return new InstanceOfConditionNested(null);
  }

  public InstanceOfConditionNested<A> withNewInstanceOfConditionLike(InstanceOf item) {
    return new InstanceOfConditionNested(item);
  }

  public InverseConditionNested<A> withNewInverseCondition() {
    return new InverseConditionNested(null);
  }

  public InverseConditionNested<A> withNewInverseConditionLike(Inverse item) {
    return new InverseConditionNested(item);
  }

  public LambdaConditionNested<A> withNewLambdaCondition() {
    return new LambdaConditionNested(null);
  }

  public LambdaConditionNested<A> withNewLambdaConditionLike(Lambda item) {
    return new LambdaConditionNested(item);
  }

  public LeftShiftConditionNested<A> withNewLeftShiftCondition() {
    return new LeftShiftConditionNested(null);
  }

  public A withNewLeftShiftCondition(Object left, Object right) {
    return (A) withCondition(new LeftShift(left, right));
  }

  public LeftShiftConditionNested<A> withNewLeftShiftConditionLike(LeftShift item) {
    return new LeftShiftConditionNested(item);
  }

  public LessThanConditionNested<A> withNewLessThanCondition() {
    return new LessThanConditionNested(null);
  }

  public A withNewLessThanCondition(Object left, Object right) {
    return (A) withCondition(new LessThan(left, right));
  }

  public LessThanConditionNested<A> withNewLessThanConditionLike(LessThan item) {
    return new LessThanConditionNested(item);
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualCondition() {
    return new LessThanOrEqualConditionNested(null);
  }

  public A withNewLessThanOrEqualCondition(Object left, Object right) {
    return (A) withCondition(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualConditionLike(LessThanOrEqual item) {
    return new LessThanOrEqualConditionNested(item);
  }

  public LogicalAndConditionNested<A> withNewLogicalAndCondition() {
    return new LogicalAndConditionNested(null);
  }

  public A withNewLogicalAndCondition(Object left, Object right) {
    return (A) withCondition(new LogicalAnd(left, right));
  }

  public LogicalAndConditionNested<A> withNewLogicalAndConditionLike(LogicalAnd item) {
    return new LogicalAndConditionNested(item);
  }

  public LogicalOrConditionNested<A> withNewLogicalOrCondition() {
    return new LogicalOrConditionNested(null);
  }

  public A withNewLogicalOrCondition(Object left, Object right) {
    return (A) withCondition(new LogicalOr(left, right));
  }

  public LogicalOrConditionNested<A> withNewLogicalOrConditionLike(LogicalOr item) {
    return new LogicalOrConditionNested(item);
  }

  public MethodCallConditionNested<A> withNewMethodCallCondition() {
    return new MethodCallConditionNested(null);
  }

  public MethodCallConditionNested<A> withNewMethodCallConditionLike(MethodCall item) {
    return new MethodCallConditionNested(item);
  }

  public MinusConditionNested<A> withNewMinusCondition() {
    return new MinusConditionNested(null);
  }

  public A withNewMinusCondition(Object left, Object right) {
    return (A) withCondition(new Minus(left, right));
  }

  public MinusConditionNested<A> withNewMinusConditionLike(Minus item) {
    return new MinusConditionNested(item);
  }

  public ModuloConditionNested<A> withNewModuloCondition() {
    return new ModuloConditionNested(null);
  }

  public A withNewModuloCondition(Object left, Object right) {
    return (A) withCondition(new Modulo(left, right));
  }

  public ModuloConditionNested<A> withNewModuloConditionLike(Modulo item) {
    return new ModuloConditionNested(item);
  }

  public MultiplyConditionNested<A> withNewMultiplyCondition() {
    return new MultiplyConditionNested(null);
  }

  public A withNewMultiplyCondition(Object left, Object right) {
    return (A) withCondition(new Multiply(left, right));
  }

  public MultiplyConditionNested<A> withNewMultiplyConditionLike(Multiply item) {
    return new MultiplyConditionNested(item);
  }

  public NegativeConditionNested<A> withNewNegativeCondition() {
    return new NegativeConditionNested(null);
  }

  public NegativeConditionNested<A> withNewNegativeConditionLike(Negative item) {
    return new NegativeConditionNested(item);
  }

  public NewArrayConditionNested<A> withNewNewArrayCondition() {
    return new NewArrayConditionNested(null);
  }

  public A withNewNewArrayCondition(Class type, Integer[] sizes) {
    return (A) withCondition(new NewArray(type, sizes));
  }

  public NewArrayConditionNested<A> withNewNewArrayConditionLike(NewArray item) {
    return new NewArrayConditionNested(item);
  }

  public NotConditionNested<A> withNewNotCondition() {
    return new NotConditionNested(null);
  }

  public NotConditionNested<A> withNewNotConditionLike(Not item) {
    return new NotConditionNested(item);
  }

  public NotEqualsConditionNested<A> withNewNotEqualsCondition() {
    return new NotEqualsConditionNested(null);
  }

  public A withNewNotEqualsCondition(Object left, Object right) {
    return (A) withCondition(new NotEquals(left, right));
  }

  public NotEqualsConditionNested<A> withNewNotEqualsConditionLike(NotEquals item) {
    return new NotEqualsConditionNested(item);
  }

  public PlusConditionNested<A> withNewPlusCondition() {
    return new PlusConditionNested(null);
  }

  public A withNewPlusCondition(Object left, Object right) {
    return (A) withCondition(new Plus(left, right));
  }

  public PlusConditionNested<A> withNewPlusConditionLike(Plus item) {
    return new PlusConditionNested(item);
  }

  public PositiveConditionNested<A> withNewPositiveCondition() {
    return new PositiveConditionNested(null);
  }

  public PositiveConditionNested<A> withNewPositiveConditionLike(Positive item) {
    return new PositiveConditionNested(item);
  }

  public PostDecrementConditionNested<A> withNewPostDecrementCondition() {
    return new PostDecrementConditionNested(null);
  }

  public PostDecrementConditionNested<A> withNewPostDecrementConditionLike(PostDecrement item) {
    return new PostDecrementConditionNested(item);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementCondition() {
    return new PostIncrementConditionNested(null);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementConditionLike(PostIncrement item) {
    return new PostIncrementConditionNested(item);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementCondition() {
    return new PreDecrementConditionNested(null);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementConditionLike(PreDecrement item) {
    return new PreDecrementConditionNested(item);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementCondition() {
    return new PreIncrementConditionNested(null);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementConditionLike(PreIncrement item) {
    return new PreIncrementConditionNested(item);
  }

  public PropertyConditionNested<A> withNewPropertyCondition() {
    return new PropertyConditionNested(null);
  }

  public PropertyConditionNested<A> withNewPropertyConditionLike(Property item) {
    return new PropertyConditionNested(item);
  }

  public PropertyRefConditionNested<A> withNewPropertyRefCondition() {
    return new PropertyRefConditionNested(null);
  }

  public PropertyRefConditionNested<A> withNewPropertyRefConditionLike(PropertyRef item) {
    return new PropertyRefConditionNested(item);
  }

  public RightShiftConditionNested<A> withNewRightShiftCondition() {
    return new RightShiftConditionNested(null);
  }

  public A withNewRightShiftCondition(Object left, Object right) {
    return (A) withCondition(new RightShift(left, right));
  }

  public RightShiftConditionNested<A> withNewRightShiftConditionLike(RightShift item) {
    return new RightShiftConditionNested(item);
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftCondition() {
    return new RightUnsignedShiftConditionNested(null);
  }

  public A withNewRightUnsignedShiftCondition(Object left, Object right) {
    return (A) withCondition(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftConditionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftConditionNested(item);
  }

  public SuperConditionNested<A> withNewSuperCondition() {
    return new SuperConditionNested(null);
  }

  public SuperConditionNested<A> withNewSuperConditionLike(Super item) {
    return new SuperConditionNested(item);
  }

  public TernaryConditionNested<A> withNewTernaryCondition() {
    return new TernaryConditionNested(null);
  }

  public TernaryConditionNested<A> withNewTernaryConditionLike(Ternary item) {
    return new TernaryConditionNested(item);
  }

  public ThisConditionNested<A> withNewThisCondition() {
    return new ThisConditionNested(null);
  }

  public ThisConditionNested<A> withNewThisConditionLike(This item) {
    return new ThisConditionNested(item);
  }

  public ValueRefConditionNested<A> withNewValueRefCondition() {
    return new ValueRefConditionNested(null);
  }

  public A withNewValueRefCondition(Object value) {
    return (A) withCondition(new ValueRef(value));
  }

  public ValueRefConditionNested<A> withNewValueRefConditionLike(ValueRef item) {
    return new ValueRefConditionNested(item);
  }

  public XorConditionNested<A> withNewXorCondition() {
    return new XorConditionNested(null);
  }

  public A withNewXorCondition(Object left, Object right) {
    return (A) withCondition(new Xor(left, right));
  }

  public XorConditionNested<A> withNewXorConditionLike(Xor item) {
    return new XorConditionNested(item);
  }

  public class AssignConditionNested<N> extends AssignFluent<AssignConditionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignConditionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endAssignCondition() {
      return and();
    }

  }

  public class BinaryExpressionConditionNested<N> extends BinaryExpressionFluent<BinaryExpressionConditionNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionConditionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endBinaryExpressionCondition() {
      return and();
    }

  }

  public class BitwiseAndConditionNested<N> extends BitwiseAndFluent<BitwiseAndConditionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndConditionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endBitwiseAndCondition() {
      return and();
    }

  }

  public class BitwiseOrConditionNested<N> extends BitwiseOrFluent<BitwiseOrConditionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrConditionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endBitwiseOrCondition() {
      return and();
    }

  }

  public class CastConditionNested<N> extends CastFluent<CastConditionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastConditionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endCastCondition() {
      return and();
    }

  }

  public class ClassRefConditionNested<N> extends ClassRefFluent<ClassRefConditionNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefConditionNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endClassRefCondition() {
      return and();
    }

  }

  public class ConstructConditionNested<N> extends ConstructFluent<ConstructConditionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructConditionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endConstructCondition() {
      return and();
    }

  }

  public class ContextRefConditionNested<N> extends ContextRefFluent<ContextRefConditionNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefConditionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endContextRefCondition() {
      return and();
    }

  }

  public class DeclareConditionNested<N> extends DeclareFluent<DeclareConditionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareConditionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endDeclareCondition() {
      return and();
    }

  }

  public class DivideConditionNested<N> extends DivideFluent<DivideConditionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideConditionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endDivideCondition() {
      return and();
    }

  }

  public class DotClassConditionNested<N> extends DotClassFluent<DotClassConditionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassConditionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endDotClassCondition() {
      return and();
    }

  }

  public class EmptyConditionNested<N> extends EmptyFluent<EmptyConditionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyConditionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endEmptyCondition() {
      return and();
    }

  }

  public class EnclosedConditionNested<N> extends EnclosedFluent<EnclosedConditionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedConditionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endEnclosedCondition() {
      return and();
    }

  }

  public class EqualsConditionNested<N> extends EqualsFluent<EqualsConditionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsConditionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endEqualsCondition() {
      return and();
    }

  }

  public class GreaterThanConditionNested<N> extends GreaterThanFluent<GreaterThanConditionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanConditionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanCondition() {
      return and();
    }

  }

  public class GreaterThanOrEqualConditionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualConditionNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualConditionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanOrEqualCondition() {
      return and();
    }

  }

  public class IndexConditionNested<N> extends IndexFluent<IndexConditionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexConditionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endIndexCondition() {
      return and();
    }

  }

  public class InstanceOfConditionNested<N> extends InstanceOfFluent<InstanceOfConditionNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfConditionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endInstanceOfCondition() {
      return and();
    }

  }

  public class InverseConditionNested<N> extends InverseFluent<InverseConditionNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseConditionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endInverseCondition() {
      return and();
    }

  }

  public class LambdaConditionNested<N> extends LambdaFluent<LambdaConditionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaConditionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endLambdaCondition() {
      return and();
    }

  }

  public class LeftShiftConditionNested<N> extends LeftShiftFluent<LeftShiftConditionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftConditionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endLeftShiftCondition() {
      return and();
    }

  }

  public class LessThanConditionNested<N> extends LessThanFluent<LessThanConditionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanConditionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endLessThanCondition() {
      return and();
    }

  }

  public class LessThanOrEqualConditionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualConditionNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualConditionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endLessThanOrEqualCondition() {
      return and();
    }

  }

  public class LogicalAndConditionNested<N> extends LogicalAndFluent<LogicalAndConditionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndConditionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endLogicalAndCondition() {
      return and();
    }

  }

  public class LogicalOrConditionNested<N> extends LogicalOrFluent<LogicalOrConditionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrConditionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endLogicalOrCondition() {
      return and();
    }

  }

  public class MethodCallConditionNested<N> extends MethodCallFluent<MethodCallConditionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallConditionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endMethodCallCondition() {
      return and();
    }

  }

  public class MinusConditionNested<N> extends MinusFluent<MinusConditionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusConditionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endMinusCondition() {
      return and();
    }

  }

  public class ModuloConditionNested<N> extends ModuloFluent<ModuloConditionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloConditionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endModuloCondition() {
      return and();
    }

  }

  public class MultiplyConditionNested<N> extends MultiplyFluent<MultiplyConditionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyConditionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endMultiplyCondition() {
      return and();
    }

  }

  public class NegativeConditionNested<N> extends NegativeFluent<NegativeConditionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeConditionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endNegativeCondition() {
      return and();
    }

  }

  public class NewArrayConditionNested<N> extends NewArrayFluent<NewArrayConditionNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayConditionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endNewArrayCondition() {
      return and();
    }

  }

  public class NotConditionNested<N> extends NotFluent<NotConditionNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotConditionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endNotCondition() {
      return and();
    }

  }

  public class NotEqualsConditionNested<N> extends NotEqualsFluent<NotEqualsConditionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsConditionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endNotEqualsCondition() {
      return and();
    }

  }

  public class PlusConditionNested<N> extends PlusFluent<PlusConditionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusConditionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPlusCondition() {
      return and();
    }

  }

  public class PositiveConditionNested<N> extends PositiveFluent<PositiveConditionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveConditionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPositiveCondition() {
      return and();
    }

  }

  public class PostDecrementConditionNested<N> extends PostDecrementFluent<PostDecrementConditionNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementConditionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPostDecrementCondition() {
      return and();
    }

  }

  public class PostIncrementConditionNested<N> extends PostIncrementFluent<PostIncrementConditionNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementConditionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPostIncrementCondition() {
      return and();
    }

  }

  public class PreDecrementConditionNested<N> extends PreDecrementFluent<PreDecrementConditionNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementConditionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPreDecrementCondition() {
      return and();
    }

  }

  public class PreIncrementConditionNested<N> extends PreIncrementFluent<PreIncrementConditionNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementConditionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPreIncrementCondition() {
      return and();
    }

  }

  public class PropertyConditionNested<N> extends PropertyFluent<PropertyConditionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyConditionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPropertyCondition() {
      return and();
    }

  }

  public class PropertyRefConditionNested<N> extends PropertyRefFluent<PropertyRefConditionNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefConditionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endPropertyRefCondition() {
      return and();
    }

  }

  public class RightShiftConditionNested<N> extends RightShiftFluent<RightShiftConditionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftConditionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endRightShiftCondition() {
      return and();
    }

  }

  public class RightUnsignedShiftConditionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftConditionNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftConditionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endRightUnsignedShiftCondition() {
      return and();
    }

  }

  public class SuperConditionNested<N> extends SuperFluent<SuperConditionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperConditionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endSuperCondition() {
      return and();
    }

  }

  public class TernaryConditionNested<N> extends TernaryFluent<TernaryConditionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryConditionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endTernaryCondition() {
      return and();
    }

  }

  public class ThisConditionNested<N> extends ThisFluent<ThisConditionNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisConditionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endThisCondition() {
      return and();
    }

  }

  public class ValueRefConditionNested<N> extends ValueRefFluent<ValueRefConditionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefConditionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endValueRefCondition() {
      return and();
    }

  }

  public class XorConditionNested<N> extends XorFluent<XorConditionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorConditionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) IfDslConditionStepFluent.this.withCondition(builder.build());
    }

    public N endXorCondition() {
      return and();
    }

  }
}
