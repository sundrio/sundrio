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
public class TernaryFluent<A extends TernaryFluent<A>> extends BaseFluent<A> {
  public TernaryFluent() {
  }

  public TernaryFluent(Ternary instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> condition;
  private VisitableBuilder<? extends Expression, ?> result;
  private VisitableBuilder<? extends Expression, ?> alternative;

  protected void copyInstance(Ternary instance) {
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withResult(instance.getResult());
      this.withAlternative(instance.getAlternative());
    }
  }

  public Expression buildCondition() {
    return this.condition != null ? this.condition.build() : null;
  }

  public A withCondition(Expression condition) {
    if (condition == null) {
      this.condition = null;
      _visitables.remove("condition");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(condition);
    _visitables.get("condition").clear();
    _visitables.get("condition").add(builder);
    this.condition = builder;
    return (A) this;
  }

  public boolean hasCondition() {
    return this.condition != null;
  }

  public MultiplyConditionNested<A> withNewMultiplyCondition() {
    return new MultiplyConditionNested(null);
  }

  public MultiplyConditionNested<A> withNewMultiplyConditionLike(Multiply item) {
    return new MultiplyConditionNested(item);
  }

  public A withNewMultiplyCondition(Object left, Object right) {
    return (A) withCondition(new Multiply(left, right));
  }

  public NewArrayConditionNested<A> withNewNewArrayCondition() {
    return new NewArrayConditionNested(null);
  }

  public NewArrayConditionNested<A> withNewNewArrayConditionLike(NewArray item) {
    return new NewArrayConditionNested(item);
  }

  public A withNewNewArrayCondition(Class type, Integer[] sizes) {
    return (A) withCondition(new NewArray(type, sizes));
  }

  public InstanceOfConditionNested<A> withNewInstanceOfCondition() {
    return new InstanceOfConditionNested(null);
  }

  public InstanceOfConditionNested<A> withNewInstanceOfConditionLike(InstanceOf item) {
    return new InstanceOfConditionNested(item);
  }

  public MethodCallConditionNested<A> withNewMethodCallCondition() {
    return new MethodCallConditionNested(null);
  }

  public MethodCallConditionNested<A> withNewMethodCallConditionLike(MethodCall item) {
    return new MethodCallConditionNested(item);
  }

  public InverseConditionNested<A> withNewInverseCondition() {
    return new InverseConditionNested(null);
  }

  public InverseConditionNested<A> withNewInverseConditionLike(Inverse item) {
    return new InverseConditionNested(item);
  }

  public IndexConditionNested<A> withNewIndexCondition() {
    return new IndexConditionNested(null);
  }

  public IndexConditionNested<A> withNewIndexConditionLike(Index item) {
    return new IndexConditionNested(item);
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualCondition() {
    return new GreaterThanOrEqualConditionNested(null);
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualConditionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualConditionNested(item);
  }

  public A withNewGreaterThanOrEqualCondition(Object left, Object right) {
    return (A) withCondition(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndCondition() {
    return new BitwiseAndConditionNested(null);
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndConditionLike(BitwiseAnd item) {
    return new BitwiseAndConditionNested(item);
  }

  public A withNewBitwiseAndCondition(Object left, Object right) {
    return (A) withCondition(new BitwiseAnd(left, right));
  }

  public MinusConditionNested<A> withNewMinusCondition() {
    return new MinusConditionNested(null);
  }

  public MinusConditionNested<A> withNewMinusConditionLike(Minus item) {
    return new MinusConditionNested(item);
  }

  public A withNewMinusCondition(Object left, Object right) {
    return (A) withCondition(new Minus(left, right));
  }

  public LogicalOrConditionNested<A> withNewLogicalOrCondition() {
    return new LogicalOrConditionNested(null);
  }

  public LogicalOrConditionNested<A> withNewLogicalOrConditionLike(LogicalOr item) {
    return new LogicalOrConditionNested(item);
  }

  public A withNewLogicalOrCondition(Object left, Object right) {
    return (A) withCondition(new LogicalOr(left, right));
  }

  public NotEqualsConditionNested<A> withNewNotEqualsCondition() {
    return new NotEqualsConditionNested(null);
  }

  public NotEqualsConditionNested<A> withNewNotEqualsConditionLike(NotEquals item) {
    return new NotEqualsConditionNested(item);
  }

  public A withNewNotEqualsCondition(Object left, Object right) {
    return (A) withCondition(new NotEquals(left, right));
  }

  public DivideConditionNested<A> withNewDivideCondition() {
    return new DivideConditionNested(null);
  }

  public DivideConditionNested<A> withNewDivideConditionLike(Divide item) {
    return new DivideConditionNested(item);
  }

  public A withNewDivideCondition(Object left, Object right) {
    return (A) withCondition(new Divide(left, right));
  }

  public LessThanConditionNested<A> withNewLessThanCondition() {
    return new LessThanConditionNested(null);
  }

  public LessThanConditionNested<A> withNewLessThanConditionLike(LessThan item) {
    return new LessThanConditionNested(item);
  }

  public A withNewLessThanCondition(Object left, Object right) {
    return (A) withCondition(new LessThan(left, right));
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrCondition() {
    return new BitwiseOrConditionNested(null);
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrConditionLike(BitwiseOr item) {
    return new BitwiseOrConditionNested(item);
  }

  public A withNewBitwiseOrCondition(Object left, Object right) {
    return (A) withCondition(new BitwiseOr(left, right));
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

  public RightShiftConditionNested<A> withNewRightShiftConditionLike(RightShift item) {
    return new RightShiftConditionNested(item);
  }

  public A withNewRightShiftCondition(Object left, Object right) {
    return (A) withCondition(new RightShift(left, right));
  }

  public GreaterThanConditionNested<A> withNewGreaterThanCondition() {
    return new GreaterThanConditionNested(null);
  }

  public GreaterThanConditionNested<A> withNewGreaterThanConditionLike(GreaterThan item) {
    return new GreaterThanConditionNested(item);
  }

  public A withNewGreaterThanCondition(Object left, Object right) {
    return (A) withCondition(new GreaterThan(left, right));
  }

  public DeclareConditionNested<A> withNewDeclareCondition() {
    return new DeclareConditionNested(null);
  }

  public DeclareConditionNested<A> withNewDeclareConditionLike(Declare item) {
    return new DeclareConditionNested(item);
  }

  public A withNewDeclareCondition(Class type, String name) {
    return (A) withCondition(new Declare(type, name));
  }

  public A withNewDeclareCondition(Class type, String name, Object value) {
    return (A) withCondition(new Declare(type, name, value));
  }

  public CastConditionNested<A> withNewCastCondition() {
    return new CastConditionNested(null);
  }

  public CastConditionNested<A> withNewCastConditionLike(Cast item) {
    return new CastConditionNested(item);
  }

  public ModuloConditionNested<A> withNewModuloCondition() {
    return new ModuloConditionNested(null);
  }

  public ModuloConditionNested<A> withNewModuloConditionLike(Modulo item) {
    return new ModuloConditionNested(item);
  }

  public A withNewModuloCondition(Object left, Object right) {
    return (A) withCondition(new Modulo(left, right));
  }

  public ValueRefConditionNested<A> withNewValueRefCondition() {
    return new ValueRefConditionNested(null);
  }

  public ValueRefConditionNested<A> withNewValueRefConditionLike(ValueRef item) {
    return new ValueRefConditionNested(item);
  }

  public A withNewValueRefCondition(Object value) {
    return (A) withCondition(new ValueRef(value));
  }

  public LeftShiftConditionNested<A> withNewLeftShiftCondition() {
    return new LeftShiftConditionNested(null);
  }

  public LeftShiftConditionNested<A> withNewLeftShiftConditionLike(LeftShift item) {
    return new LeftShiftConditionNested(item);
  }

  public A withNewLeftShiftCondition(Object left, Object right) {
    return (A) withCondition(new LeftShift(left, right));
  }

  public TernaryConditionNested<A> withNewTernaryCondition() {
    return new TernaryConditionNested(null);
  }

  public TernaryConditionNested<A> withNewTernaryConditionLike(Ternary item) {
    return new TernaryConditionNested(item);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionCondition() {
    return new BinaryExpressionConditionNested(null);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionConditionLike(BinaryExpression item) {
    return new BinaryExpressionConditionNested(item);
  }

  public EqualsConditionNested<A> withNewEqualsCondition() {
    return new EqualsConditionNested(null);
  }

  public EqualsConditionNested<A> withNewEqualsConditionLike(Equals item) {
    return new EqualsConditionNested(item);
  }

  public A withNewEqualsCondition(Object left, Object right) {
    return (A) withCondition(new Equals(left, right));
  }

  public EnclosedConditionNested<A> withNewEnclosedCondition() {
    return new EnclosedConditionNested(null);
  }

  public EnclosedConditionNested<A> withNewEnclosedConditionLike(Enclosed item) {
    return new EnclosedConditionNested(item);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementCondition() {
    return new PreDecrementConditionNested(null);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementConditionLike(PreDecrement item) {
    return new PreDecrementConditionNested(item);
  }

  public PostDecrementConditionNested<A> withNewPostDecrementCondition() {
    return new PostDecrementConditionNested(null);
  }

  public PostDecrementConditionNested<A> withNewPostDecrementConditionLike(PostDecrement item) {
    return new PostDecrementConditionNested(item);
  }

  public LambdaConditionNested<A> withNewLambdaCondition() {
    return new LambdaConditionNested(null);
  }

  public LambdaConditionNested<A> withNewLambdaConditionLike(Lambda item) {
    return new LambdaConditionNested(item);
  }

  public NotConditionNested<A> withNewNotCondition() {
    return new NotConditionNested(null);
  }

  public NotConditionNested<A> withNewNotConditionLike(Not item) {
    return new NotConditionNested(item);
  }

  public AssignConditionNested<A> withNewAssignCondition() {
    return new AssignConditionNested(null);
  }

  public AssignConditionNested<A> withNewAssignConditionLike(Assign item) {
    return new AssignConditionNested(item);
  }

  public NegativeConditionNested<A> withNewNegativeCondition() {
    return new NegativeConditionNested(null);
  }

  public NegativeConditionNested<A> withNewNegativeConditionLike(Negative item) {
    return new NegativeConditionNested(item);
  }

  public ThisConditionNested<A> withNewThisCondition() {
    return new ThisConditionNested(null);
  }

  public ThisConditionNested<A> withNewThisConditionLike(This item) {
    return new ThisConditionNested(item);
  }

  public LogicalAndConditionNested<A> withNewLogicalAndCondition() {
    return new LogicalAndConditionNested(null);
  }

  public LogicalAndConditionNested<A> withNewLogicalAndConditionLike(LogicalAnd item) {
    return new LogicalAndConditionNested(item);
  }

  public A withNewLogicalAndCondition(Object left, Object right) {
    return (A) withCondition(new LogicalAnd(left, right));
  }

  public PostIncrementConditionNested<A> withNewPostIncrementCondition() {
    return new PostIncrementConditionNested(null);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementConditionLike(PostIncrement item) {
    return new PostIncrementConditionNested(item);
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftCondition() {
    return new RightUnsignedShiftConditionNested(null);
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftConditionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftConditionNested(item);
  }

  public A withNewRightUnsignedShiftCondition(Object left, Object right) {
    return (A) withCondition(new RightUnsignedShift(left, right));
  }

  public PlusConditionNested<A> withNewPlusCondition() {
    return new PlusConditionNested(null);
  }

  public PlusConditionNested<A> withNewPlusConditionLike(Plus item) {
    return new PlusConditionNested(item);
  }

  public A withNewPlusCondition(Object left, Object right) {
    return (A) withCondition(new Plus(left, right));
  }

  public ConstructConditionNested<A> withNewConstructCondition() {
    return new ConstructConditionNested(null);
  }

  public ConstructConditionNested<A> withNewConstructConditionLike(Construct item) {
    return new ConstructConditionNested(item);
  }

  public XorConditionNested<A> withNewXorCondition() {
    return new XorConditionNested(null);
  }

  public XorConditionNested<A> withNewXorConditionLike(Xor item) {
    return new XorConditionNested(item);
  }

  public A withNewXorCondition(Object left, Object right) {
    return (A) withCondition(new Xor(left, right));
  }

  public PreIncrementConditionNested<A> withNewPreIncrementCondition() {
    return new PreIncrementConditionNested(null);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementConditionLike(PreIncrement item) {
    return new PreIncrementConditionNested(item);
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualCondition() {
    return new LessThanOrEqualConditionNested(null);
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualConditionLike(LessThanOrEqual item) {
    return new LessThanOrEqualConditionNested(item);
  }

  public A withNewLessThanOrEqualCondition(Object left, Object right) {
    return (A) withCondition(new LessThanOrEqual(left, right));
  }

  public PositiveConditionNested<A> withNewPositiveCondition() {
    return new PositiveConditionNested(null);
  }

  public PositiveConditionNested<A> withNewPositiveConditionLike(Positive item) {
    return new PositiveConditionNested(item);
  }

  public Expression buildResult() {
    return this.result != null ? this.result.build() : null;
  }

  public A withResult(Expression result) {
    if (result == null) {
      this.result = null;
      _visitables.remove("result");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(result);
    _visitables.get("result").clear();
    _visitables.get("result").add(builder);
    this.result = builder;
    return (A) this;
  }

  public boolean hasResult() {
    return this.result != null;
  }

  public MultiplyResultNested<A> withNewMultiplyResult() {
    return new MultiplyResultNested(null);
  }

  public MultiplyResultNested<A> withNewMultiplyResultLike(Multiply item) {
    return new MultiplyResultNested(item);
  }

  public A withNewMultiplyResult(Object left, Object right) {
    return (A) withResult(new Multiply(left, right));
  }

  public NewArrayResultNested<A> withNewNewArrayResult() {
    return new NewArrayResultNested(null);
  }

  public NewArrayResultNested<A> withNewNewArrayResultLike(NewArray item) {
    return new NewArrayResultNested(item);
  }

  public A withNewNewArrayResult(Class type, Integer[] sizes) {
    return (A) withResult(new NewArray(type, sizes));
  }

  public InstanceOfResultNested<A> withNewInstanceOfResult() {
    return new InstanceOfResultNested(null);
  }

  public InstanceOfResultNested<A> withNewInstanceOfResultLike(InstanceOf item) {
    return new InstanceOfResultNested(item);
  }

  public MethodCallResultNested<A> withNewMethodCallResult() {
    return new MethodCallResultNested(null);
  }

  public MethodCallResultNested<A> withNewMethodCallResultLike(MethodCall item) {
    return new MethodCallResultNested(item);
  }

  public InverseResultNested<A> withNewInverseResult() {
    return new InverseResultNested(null);
  }

  public InverseResultNested<A> withNewInverseResultLike(Inverse item) {
    return new InverseResultNested(item);
  }

  public IndexResultNested<A> withNewIndexResult() {
    return new IndexResultNested(null);
  }

  public IndexResultNested<A> withNewIndexResultLike(Index item) {
    return new IndexResultNested(item);
  }

  public GreaterThanOrEqualResultNested<A> withNewGreaterThanOrEqualResult() {
    return new GreaterThanOrEqualResultNested(null);
  }

  public GreaterThanOrEqualResultNested<A> withNewGreaterThanOrEqualResultLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualResultNested(item);
  }

  public A withNewGreaterThanOrEqualResult(Object left, Object right) {
    return (A) withResult(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndResultNested<A> withNewBitwiseAndResult() {
    return new BitwiseAndResultNested(null);
  }

  public BitwiseAndResultNested<A> withNewBitwiseAndResultLike(BitwiseAnd item) {
    return new BitwiseAndResultNested(item);
  }

  public A withNewBitwiseAndResult(Object left, Object right) {
    return (A) withResult(new BitwiseAnd(left, right));
  }

  public MinusResultNested<A> withNewMinusResult() {
    return new MinusResultNested(null);
  }

  public MinusResultNested<A> withNewMinusResultLike(Minus item) {
    return new MinusResultNested(item);
  }

  public A withNewMinusResult(Object left, Object right) {
    return (A) withResult(new Minus(left, right));
  }

  public LogicalOrResultNested<A> withNewLogicalOrResult() {
    return new LogicalOrResultNested(null);
  }

  public LogicalOrResultNested<A> withNewLogicalOrResultLike(LogicalOr item) {
    return new LogicalOrResultNested(item);
  }

  public A withNewLogicalOrResult(Object left, Object right) {
    return (A) withResult(new LogicalOr(left, right));
  }

  public NotEqualsResultNested<A> withNewNotEqualsResult() {
    return new NotEqualsResultNested(null);
  }

  public NotEqualsResultNested<A> withNewNotEqualsResultLike(NotEquals item) {
    return new NotEqualsResultNested(item);
  }

  public A withNewNotEqualsResult(Object left, Object right) {
    return (A) withResult(new NotEquals(left, right));
  }

  public DivideResultNested<A> withNewDivideResult() {
    return new DivideResultNested(null);
  }

  public DivideResultNested<A> withNewDivideResultLike(Divide item) {
    return new DivideResultNested(item);
  }

  public A withNewDivideResult(Object left, Object right) {
    return (A) withResult(new Divide(left, right));
  }

  public LessThanResultNested<A> withNewLessThanResult() {
    return new LessThanResultNested(null);
  }

  public LessThanResultNested<A> withNewLessThanResultLike(LessThan item) {
    return new LessThanResultNested(item);
  }

  public A withNewLessThanResult(Object left, Object right) {
    return (A) withResult(new LessThan(left, right));
  }

  public BitwiseOrResultNested<A> withNewBitwiseOrResult() {
    return new BitwiseOrResultNested(null);
  }

  public BitwiseOrResultNested<A> withNewBitwiseOrResultLike(BitwiseOr item) {
    return new BitwiseOrResultNested(item);
  }

  public A withNewBitwiseOrResult(Object left, Object right) {
    return (A) withResult(new BitwiseOr(left, right));
  }

  public PropertyRefResultNested<A> withNewPropertyRefResult() {
    return new PropertyRefResultNested(null);
  }

  public PropertyRefResultNested<A> withNewPropertyRefResultLike(PropertyRef item) {
    return new PropertyRefResultNested(item);
  }

  public RightShiftResultNested<A> withNewRightShiftResult() {
    return new RightShiftResultNested(null);
  }

  public RightShiftResultNested<A> withNewRightShiftResultLike(RightShift item) {
    return new RightShiftResultNested(item);
  }

  public A withNewRightShiftResult(Object left, Object right) {
    return (A) withResult(new RightShift(left, right));
  }

  public GreaterThanResultNested<A> withNewGreaterThanResult() {
    return new GreaterThanResultNested(null);
  }

  public GreaterThanResultNested<A> withNewGreaterThanResultLike(GreaterThan item) {
    return new GreaterThanResultNested(item);
  }

  public A withNewGreaterThanResult(Object left, Object right) {
    return (A) withResult(new GreaterThan(left, right));
  }

  public DeclareResultNested<A> withNewDeclareResult() {
    return new DeclareResultNested(null);
  }

  public DeclareResultNested<A> withNewDeclareResultLike(Declare item) {
    return new DeclareResultNested(item);
  }

  public A withNewDeclareResult(Class type, String name) {
    return (A) withResult(new Declare(type, name));
  }

  public A withNewDeclareResult(Class type, String name, Object value) {
    return (A) withResult(new Declare(type, name, value));
  }

  public CastResultNested<A> withNewCastResult() {
    return new CastResultNested(null);
  }

  public CastResultNested<A> withNewCastResultLike(Cast item) {
    return new CastResultNested(item);
  }

  public ModuloResultNested<A> withNewModuloResult() {
    return new ModuloResultNested(null);
  }

  public ModuloResultNested<A> withNewModuloResultLike(Modulo item) {
    return new ModuloResultNested(item);
  }

  public A withNewModuloResult(Object left, Object right) {
    return (A) withResult(new Modulo(left, right));
  }

  public ValueRefResultNested<A> withNewValueRefResult() {
    return new ValueRefResultNested(null);
  }

  public ValueRefResultNested<A> withNewValueRefResultLike(ValueRef item) {
    return new ValueRefResultNested(item);
  }

  public A withNewValueRefResult(Object value) {
    return (A) withResult(new ValueRef(value));
  }

  public LeftShiftResultNested<A> withNewLeftShiftResult() {
    return new LeftShiftResultNested(null);
  }

  public LeftShiftResultNested<A> withNewLeftShiftResultLike(LeftShift item) {
    return new LeftShiftResultNested(item);
  }

  public A withNewLeftShiftResult(Object left, Object right) {
    return (A) withResult(new LeftShift(left, right));
  }

  public TernaryResultNested<A> withNewTernaryResult() {
    return new TernaryResultNested(null);
  }

  public TernaryResultNested<A> withNewTernaryResultLike(Ternary item) {
    return new TernaryResultNested(item);
  }

  public BinaryExpressionResultNested<A> withNewBinaryExpressionResult() {
    return new BinaryExpressionResultNested(null);
  }

  public BinaryExpressionResultNested<A> withNewBinaryExpressionResultLike(BinaryExpression item) {
    return new BinaryExpressionResultNested(item);
  }

  public EqualsResultNested<A> withNewEqualsResult() {
    return new EqualsResultNested(null);
  }

  public EqualsResultNested<A> withNewEqualsResultLike(Equals item) {
    return new EqualsResultNested(item);
  }

  public A withNewEqualsResult(Object left, Object right) {
    return (A) withResult(new Equals(left, right));
  }

  public EnclosedResultNested<A> withNewEnclosedResult() {
    return new EnclosedResultNested(null);
  }

  public EnclosedResultNested<A> withNewEnclosedResultLike(Enclosed item) {
    return new EnclosedResultNested(item);
  }

  public PreDecrementResultNested<A> withNewPreDecrementResult() {
    return new PreDecrementResultNested(null);
  }

  public PreDecrementResultNested<A> withNewPreDecrementResultLike(PreDecrement item) {
    return new PreDecrementResultNested(item);
  }

  public PostDecrementResultNested<A> withNewPostDecrementResult() {
    return new PostDecrementResultNested(null);
  }

  public PostDecrementResultNested<A> withNewPostDecrementResultLike(PostDecrement item) {
    return new PostDecrementResultNested(item);
  }

  public LambdaResultNested<A> withNewLambdaResult() {
    return new LambdaResultNested(null);
  }

  public LambdaResultNested<A> withNewLambdaResultLike(Lambda item) {
    return new LambdaResultNested(item);
  }

  public NotResultNested<A> withNewNotResult() {
    return new NotResultNested(null);
  }

  public NotResultNested<A> withNewNotResultLike(Not item) {
    return new NotResultNested(item);
  }

  public AssignResultNested<A> withNewAssignResult() {
    return new AssignResultNested(null);
  }

  public AssignResultNested<A> withNewAssignResultLike(Assign item) {
    return new AssignResultNested(item);
  }

  public NegativeResultNested<A> withNewNegativeResult() {
    return new NegativeResultNested(null);
  }

  public NegativeResultNested<A> withNewNegativeResultLike(Negative item) {
    return new NegativeResultNested(item);
  }

  public ThisResultNested<A> withNewThisResult() {
    return new ThisResultNested(null);
  }

  public ThisResultNested<A> withNewThisResultLike(This item) {
    return new ThisResultNested(item);
  }

  public LogicalAndResultNested<A> withNewLogicalAndResult() {
    return new LogicalAndResultNested(null);
  }

  public LogicalAndResultNested<A> withNewLogicalAndResultLike(LogicalAnd item) {
    return new LogicalAndResultNested(item);
  }

  public A withNewLogicalAndResult(Object left, Object right) {
    return (A) withResult(new LogicalAnd(left, right));
  }

  public PostIncrementResultNested<A> withNewPostIncrementResult() {
    return new PostIncrementResultNested(null);
  }

  public PostIncrementResultNested<A> withNewPostIncrementResultLike(PostIncrement item) {
    return new PostIncrementResultNested(item);
  }

  public RightUnsignedShiftResultNested<A> withNewRightUnsignedShiftResult() {
    return new RightUnsignedShiftResultNested(null);
  }

  public RightUnsignedShiftResultNested<A> withNewRightUnsignedShiftResultLike(RightUnsignedShift item) {
    return new RightUnsignedShiftResultNested(item);
  }

  public A withNewRightUnsignedShiftResult(Object left, Object right) {
    return (A) withResult(new RightUnsignedShift(left, right));
  }

  public PlusResultNested<A> withNewPlusResult() {
    return new PlusResultNested(null);
  }

  public PlusResultNested<A> withNewPlusResultLike(Plus item) {
    return new PlusResultNested(item);
  }

  public A withNewPlusResult(Object left, Object right) {
    return (A) withResult(new Plus(left, right));
  }

  public ConstructResultNested<A> withNewConstructResult() {
    return new ConstructResultNested(null);
  }

  public ConstructResultNested<A> withNewConstructResultLike(Construct item) {
    return new ConstructResultNested(item);
  }

  public XorResultNested<A> withNewXorResult() {
    return new XorResultNested(null);
  }

  public XorResultNested<A> withNewXorResultLike(Xor item) {
    return new XorResultNested(item);
  }

  public A withNewXorResult(Object left, Object right) {
    return (A) withResult(new Xor(left, right));
  }

  public PreIncrementResultNested<A> withNewPreIncrementResult() {
    return new PreIncrementResultNested(null);
  }

  public PreIncrementResultNested<A> withNewPreIncrementResultLike(PreIncrement item) {
    return new PreIncrementResultNested(item);
  }

  public LessThanOrEqualResultNested<A> withNewLessThanOrEqualResult() {
    return new LessThanOrEqualResultNested(null);
  }

  public LessThanOrEqualResultNested<A> withNewLessThanOrEqualResultLike(LessThanOrEqual item) {
    return new LessThanOrEqualResultNested(item);
  }

  public A withNewLessThanOrEqualResult(Object left, Object right) {
    return (A) withResult(new LessThanOrEqual(left, right));
  }

  public PositiveResultNested<A> withNewPositiveResult() {
    return new PositiveResultNested(null);
  }

  public PositiveResultNested<A> withNewPositiveResultLike(Positive item) {
    return new PositiveResultNested(item);
  }

  public Expression buildAlternative() {
    return this.alternative != null ? this.alternative.build() : null;
  }

  public A withAlternative(Expression alternative) {
    if (alternative == null) {
      this.alternative = null;
      _visitables.remove("alternative");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(alternative);
    _visitables.get("alternative").clear();
    _visitables.get("alternative").add(builder);
    this.alternative = builder;
    return (A) this;
  }

  public boolean hasAlternative() {
    return this.alternative != null;
  }

  public MultiplyAlternativeNested<A> withNewMultiplyAlternative() {
    return new MultiplyAlternativeNested(null);
  }

  public MultiplyAlternativeNested<A> withNewMultiplyAlternativeLike(Multiply item) {
    return new MultiplyAlternativeNested(item);
  }

  public A withNewMultiplyAlternative(Object left, Object right) {
    return (A) withAlternative(new Multiply(left, right));
  }

  public NewArrayAlternativeNested<A> withNewNewArrayAlternative() {
    return new NewArrayAlternativeNested(null);
  }

  public NewArrayAlternativeNested<A> withNewNewArrayAlternativeLike(NewArray item) {
    return new NewArrayAlternativeNested(item);
  }

  public A withNewNewArrayAlternative(Class type, Integer[] sizes) {
    return (A) withAlternative(new NewArray(type, sizes));
  }

  public InstanceOfAlternativeNested<A> withNewInstanceOfAlternative() {
    return new InstanceOfAlternativeNested(null);
  }

  public InstanceOfAlternativeNested<A> withNewInstanceOfAlternativeLike(InstanceOf item) {
    return new InstanceOfAlternativeNested(item);
  }

  public MethodCallAlternativeNested<A> withNewMethodCallAlternative() {
    return new MethodCallAlternativeNested(null);
  }

  public MethodCallAlternativeNested<A> withNewMethodCallAlternativeLike(MethodCall item) {
    return new MethodCallAlternativeNested(item);
  }

  public InverseAlternativeNested<A> withNewInverseAlternative() {
    return new InverseAlternativeNested(null);
  }

  public InverseAlternativeNested<A> withNewInverseAlternativeLike(Inverse item) {
    return new InverseAlternativeNested(item);
  }

  public IndexAlternativeNested<A> withNewIndexAlternative() {
    return new IndexAlternativeNested(null);
  }

  public IndexAlternativeNested<A> withNewIndexAlternativeLike(Index item) {
    return new IndexAlternativeNested(item);
  }

  public GreaterThanOrEqualAlternativeNested<A> withNewGreaterThanOrEqualAlternative() {
    return new GreaterThanOrEqualAlternativeNested(null);
  }

  public GreaterThanOrEqualAlternativeNested<A> withNewGreaterThanOrEqualAlternativeLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualAlternativeNested(item);
  }

  public A withNewGreaterThanOrEqualAlternative(Object left, Object right) {
    return (A) withAlternative(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndAlternativeNested<A> withNewBitwiseAndAlternative() {
    return new BitwiseAndAlternativeNested(null);
  }

  public BitwiseAndAlternativeNested<A> withNewBitwiseAndAlternativeLike(BitwiseAnd item) {
    return new BitwiseAndAlternativeNested(item);
  }

  public A withNewBitwiseAndAlternative(Object left, Object right) {
    return (A) withAlternative(new BitwiseAnd(left, right));
  }

  public MinusAlternativeNested<A> withNewMinusAlternative() {
    return new MinusAlternativeNested(null);
  }

  public MinusAlternativeNested<A> withNewMinusAlternativeLike(Minus item) {
    return new MinusAlternativeNested(item);
  }

  public A withNewMinusAlternative(Object left, Object right) {
    return (A) withAlternative(new Minus(left, right));
  }

  public LogicalOrAlternativeNested<A> withNewLogicalOrAlternative() {
    return new LogicalOrAlternativeNested(null);
  }

  public LogicalOrAlternativeNested<A> withNewLogicalOrAlternativeLike(LogicalOr item) {
    return new LogicalOrAlternativeNested(item);
  }

  public A withNewLogicalOrAlternative(Object left, Object right) {
    return (A) withAlternative(new LogicalOr(left, right));
  }

  public NotEqualsAlternativeNested<A> withNewNotEqualsAlternative() {
    return new NotEqualsAlternativeNested(null);
  }

  public NotEqualsAlternativeNested<A> withNewNotEqualsAlternativeLike(NotEquals item) {
    return new NotEqualsAlternativeNested(item);
  }

  public A withNewNotEqualsAlternative(Object left, Object right) {
    return (A) withAlternative(new NotEquals(left, right));
  }

  public DivideAlternativeNested<A> withNewDivideAlternative() {
    return new DivideAlternativeNested(null);
  }

  public DivideAlternativeNested<A> withNewDivideAlternativeLike(Divide item) {
    return new DivideAlternativeNested(item);
  }

  public A withNewDivideAlternative(Object left, Object right) {
    return (A) withAlternative(new Divide(left, right));
  }

  public LessThanAlternativeNested<A> withNewLessThanAlternative() {
    return new LessThanAlternativeNested(null);
  }

  public LessThanAlternativeNested<A> withNewLessThanAlternativeLike(LessThan item) {
    return new LessThanAlternativeNested(item);
  }

  public A withNewLessThanAlternative(Object left, Object right) {
    return (A) withAlternative(new LessThan(left, right));
  }

  public BitwiseOrAlternativeNested<A> withNewBitwiseOrAlternative() {
    return new BitwiseOrAlternativeNested(null);
  }

  public BitwiseOrAlternativeNested<A> withNewBitwiseOrAlternativeLike(BitwiseOr item) {
    return new BitwiseOrAlternativeNested(item);
  }

  public A withNewBitwiseOrAlternative(Object left, Object right) {
    return (A) withAlternative(new BitwiseOr(left, right));
  }

  public PropertyRefAlternativeNested<A> withNewPropertyRefAlternative() {
    return new PropertyRefAlternativeNested(null);
  }

  public PropertyRefAlternativeNested<A> withNewPropertyRefAlternativeLike(PropertyRef item) {
    return new PropertyRefAlternativeNested(item);
  }

  public RightShiftAlternativeNested<A> withNewRightShiftAlternative() {
    return new RightShiftAlternativeNested(null);
  }

  public RightShiftAlternativeNested<A> withNewRightShiftAlternativeLike(RightShift item) {
    return new RightShiftAlternativeNested(item);
  }

  public A withNewRightShiftAlternative(Object left, Object right) {
    return (A) withAlternative(new RightShift(left, right));
  }

  public GreaterThanAlternativeNested<A> withNewGreaterThanAlternative() {
    return new GreaterThanAlternativeNested(null);
  }

  public GreaterThanAlternativeNested<A> withNewGreaterThanAlternativeLike(GreaterThan item) {
    return new GreaterThanAlternativeNested(item);
  }

  public A withNewGreaterThanAlternative(Object left, Object right) {
    return (A) withAlternative(new GreaterThan(left, right));
  }

  public DeclareAlternativeNested<A> withNewDeclareAlternative() {
    return new DeclareAlternativeNested(null);
  }

  public DeclareAlternativeNested<A> withNewDeclareAlternativeLike(Declare item) {
    return new DeclareAlternativeNested(item);
  }

  public A withNewDeclareAlternative(Class type, String name) {
    return (A) withAlternative(new Declare(type, name));
  }

  public A withNewDeclareAlternative(Class type, String name, Object value) {
    return (A) withAlternative(new Declare(type, name, value));
  }

  public CastAlternativeNested<A> withNewCastAlternative() {
    return new CastAlternativeNested(null);
  }

  public CastAlternativeNested<A> withNewCastAlternativeLike(Cast item) {
    return new CastAlternativeNested(item);
  }

  public ModuloAlternativeNested<A> withNewModuloAlternative() {
    return new ModuloAlternativeNested(null);
  }

  public ModuloAlternativeNested<A> withNewModuloAlternativeLike(Modulo item) {
    return new ModuloAlternativeNested(item);
  }

  public A withNewModuloAlternative(Object left, Object right) {
    return (A) withAlternative(new Modulo(left, right));
  }

  public ValueRefAlternativeNested<A> withNewValueRefAlternative() {
    return new ValueRefAlternativeNested(null);
  }

  public ValueRefAlternativeNested<A> withNewValueRefAlternativeLike(ValueRef item) {
    return new ValueRefAlternativeNested(item);
  }

  public A withNewValueRefAlternative(Object value) {
    return (A) withAlternative(new ValueRef(value));
  }

  public LeftShiftAlternativeNested<A> withNewLeftShiftAlternative() {
    return new LeftShiftAlternativeNested(null);
  }

  public LeftShiftAlternativeNested<A> withNewLeftShiftAlternativeLike(LeftShift item) {
    return new LeftShiftAlternativeNested(item);
  }

  public A withNewLeftShiftAlternative(Object left, Object right) {
    return (A) withAlternative(new LeftShift(left, right));
  }

  public TernaryAlternativeNested<A> withNewTernaryAlternative() {
    return new TernaryAlternativeNested(null);
  }

  public TernaryAlternativeNested<A> withNewTernaryAlternativeLike(Ternary item) {
    return new TernaryAlternativeNested(item);
  }

  public BinaryExpressionAlternativeNested<A> withNewBinaryExpressionAlternative() {
    return new BinaryExpressionAlternativeNested(null);
  }

  public BinaryExpressionAlternativeNested<A> withNewBinaryExpressionAlternativeLike(BinaryExpression item) {
    return new BinaryExpressionAlternativeNested(item);
  }

  public EqualsAlternativeNested<A> withNewEqualsAlternative() {
    return new EqualsAlternativeNested(null);
  }

  public EqualsAlternativeNested<A> withNewEqualsAlternativeLike(Equals item) {
    return new EqualsAlternativeNested(item);
  }

  public A withNewEqualsAlternative(Object left, Object right) {
    return (A) withAlternative(new Equals(left, right));
  }

  public EnclosedAlternativeNested<A> withNewEnclosedAlternative() {
    return new EnclosedAlternativeNested(null);
  }

  public EnclosedAlternativeNested<A> withNewEnclosedAlternativeLike(Enclosed item) {
    return new EnclosedAlternativeNested(item);
  }

  public PreDecrementAlternativeNested<A> withNewPreDecrementAlternative() {
    return new PreDecrementAlternativeNested(null);
  }

  public PreDecrementAlternativeNested<A> withNewPreDecrementAlternativeLike(PreDecrement item) {
    return new PreDecrementAlternativeNested(item);
  }

  public PostDecrementAlternativeNested<A> withNewPostDecrementAlternative() {
    return new PostDecrementAlternativeNested(null);
  }

  public PostDecrementAlternativeNested<A> withNewPostDecrementAlternativeLike(PostDecrement item) {
    return new PostDecrementAlternativeNested(item);
  }

  public LambdaAlternativeNested<A> withNewLambdaAlternative() {
    return new LambdaAlternativeNested(null);
  }

  public LambdaAlternativeNested<A> withNewLambdaAlternativeLike(Lambda item) {
    return new LambdaAlternativeNested(item);
  }

  public NotAlternativeNested<A> withNewNotAlternative() {
    return new NotAlternativeNested(null);
  }

  public NotAlternativeNested<A> withNewNotAlternativeLike(Not item) {
    return new NotAlternativeNested(item);
  }

  public AssignAlternativeNested<A> withNewAssignAlternative() {
    return new AssignAlternativeNested(null);
  }

  public AssignAlternativeNested<A> withNewAssignAlternativeLike(Assign item) {
    return new AssignAlternativeNested(item);
  }

  public NegativeAlternativeNested<A> withNewNegativeAlternative() {
    return new NegativeAlternativeNested(null);
  }

  public NegativeAlternativeNested<A> withNewNegativeAlternativeLike(Negative item) {
    return new NegativeAlternativeNested(item);
  }

  public ThisAlternativeNested<A> withNewThisAlternative() {
    return new ThisAlternativeNested(null);
  }

  public ThisAlternativeNested<A> withNewThisAlternativeLike(This item) {
    return new ThisAlternativeNested(item);
  }

  public LogicalAndAlternativeNested<A> withNewLogicalAndAlternative() {
    return new LogicalAndAlternativeNested(null);
  }

  public LogicalAndAlternativeNested<A> withNewLogicalAndAlternativeLike(LogicalAnd item) {
    return new LogicalAndAlternativeNested(item);
  }

  public A withNewLogicalAndAlternative(Object left, Object right) {
    return (A) withAlternative(new LogicalAnd(left, right));
  }

  public PostIncrementAlternativeNested<A> withNewPostIncrementAlternative() {
    return new PostIncrementAlternativeNested(null);
  }

  public PostIncrementAlternativeNested<A> withNewPostIncrementAlternativeLike(PostIncrement item) {
    return new PostIncrementAlternativeNested(item);
  }

  public RightUnsignedShiftAlternativeNested<A> withNewRightUnsignedShiftAlternative() {
    return new RightUnsignedShiftAlternativeNested(null);
  }

  public RightUnsignedShiftAlternativeNested<A> withNewRightUnsignedShiftAlternativeLike(RightUnsignedShift item) {
    return new RightUnsignedShiftAlternativeNested(item);
  }

  public A withNewRightUnsignedShiftAlternative(Object left, Object right) {
    return (A) withAlternative(new RightUnsignedShift(left, right));
  }

  public PlusAlternativeNested<A> withNewPlusAlternative() {
    return new PlusAlternativeNested(null);
  }

  public PlusAlternativeNested<A> withNewPlusAlternativeLike(Plus item) {
    return new PlusAlternativeNested(item);
  }

  public A withNewPlusAlternative(Object left, Object right) {
    return (A) withAlternative(new Plus(left, right));
  }

  public ConstructAlternativeNested<A> withNewConstructAlternative() {
    return new ConstructAlternativeNested(null);
  }

  public ConstructAlternativeNested<A> withNewConstructAlternativeLike(Construct item) {
    return new ConstructAlternativeNested(item);
  }

  public XorAlternativeNested<A> withNewXorAlternative() {
    return new XorAlternativeNested(null);
  }

  public XorAlternativeNested<A> withNewXorAlternativeLike(Xor item) {
    return new XorAlternativeNested(item);
  }

  public A withNewXorAlternative(Object left, Object right) {
    return (A) withAlternative(new Xor(left, right));
  }

  public PreIncrementAlternativeNested<A> withNewPreIncrementAlternative() {
    return new PreIncrementAlternativeNested(null);
  }

  public PreIncrementAlternativeNested<A> withNewPreIncrementAlternativeLike(PreIncrement item) {
    return new PreIncrementAlternativeNested(item);
  }

  public LessThanOrEqualAlternativeNested<A> withNewLessThanOrEqualAlternative() {
    return new LessThanOrEqualAlternativeNested(null);
  }

  public LessThanOrEqualAlternativeNested<A> withNewLessThanOrEqualAlternativeLike(LessThanOrEqual item) {
    return new LessThanOrEqualAlternativeNested(item);
  }

  public A withNewLessThanOrEqualAlternative(Object left, Object right) {
    return (A) withAlternative(new LessThanOrEqual(left, right));
  }

  public PositiveAlternativeNested<A> withNewPositiveAlternative() {
    return new PositiveAlternativeNested(null);
  }

  public PositiveAlternativeNested<A> withNewPositiveAlternativeLike(Positive item) {
    return new PositiveAlternativeNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TernaryFluent that = (TernaryFluent) o;
    if (!java.util.Objects.equals(condition, that.condition))
      return false;
    if (!java.util.Objects.equals(result, that.result))
      return false;
    if (!java.util.Objects.equals(alternative, that.alternative))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(condition, result, alternative, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (condition != null) {
      sb.append("condition:");
      sb.append(condition + ",");
    }
    if (result != null) {
      sb.append("result:");
      sb.append(result + ",");
    }
    if (alternative != null) {
      sb.append("alternative:");
      sb.append(alternative);
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

  public class MultiplyConditionNested<N> extends MultiplyFluent<MultiplyConditionNested<N>> implements Nested<N> {
    MultiplyConditionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endMultiplyCondition() {
      return and();
    }

  }

  public class NewArrayConditionNested<N> extends NewArrayFluent<NewArrayConditionNested<N>> implements Nested<N> {
    NewArrayConditionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNewArrayCondition() {
      return and();
    }

  }

  public class InstanceOfConditionNested<N> extends InstanceOfFluent<InstanceOfConditionNested<N>> implements Nested<N> {
    InstanceOfConditionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endInstanceOfCondition() {
      return and();
    }

  }

  public class MethodCallConditionNested<N> extends MethodCallFluent<MethodCallConditionNested<N>> implements Nested<N> {
    MethodCallConditionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endMethodCallCondition() {
      return and();
    }

  }

  public class InverseConditionNested<N> extends InverseFluent<InverseConditionNested<N>> implements Nested<N> {
    InverseConditionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endInverseCondition() {
      return and();
    }

  }

  public class IndexConditionNested<N> extends IndexFluent<IndexConditionNested<N>> implements Nested<N> {
    IndexConditionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endIndexCondition() {
      return and();
    }

  }

  public class GreaterThanOrEqualConditionNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualConditionNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualConditionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanOrEqualCondition() {
      return and();
    }

  }

  public class BitwiseAndConditionNested<N> extends BitwiseAndFluent<BitwiseAndConditionNested<N>> implements Nested<N> {
    BitwiseAndConditionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endBitwiseAndCondition() {
      return and();
    }

  }

  public class MinusConditionNested<N> extends MinusFluent<MinusConditionNested<N>> implements Nested<N> {
    MinusConditionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endMinusCondition() {
      return and();
    }

  }

  public class LogicalOrConditionNested<N> extends LogicalOrFluent<LogicalOrConditionNested<N>> implements Nested<N> {
    LogicalOrConditionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLogicalOrCondition() {
      return and();
    }

  }

  public class NotEqualsConditionNested<N> extends NotEqualsFluent<NotEqualsConditionNested<N>> implements Nested<N> {
    NotEqualsConditionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNotEqualsCondition() {
      return and();
    }

  }

  public class DivideConditionNested<N> extends DivideFluent<DivideConditionNested<N>> implements Nested<N> {
    DivideConditionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endDivideCondition() {
      return and();
    }

  }

  public class LessThanConditionNested<N> extends LessThanFluent<LessThanConditionNested<N>> implements Nested<N> {
    LessThanConditionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLessThanCondition() {
      return and();
    }

  }

  public class BitwiseOrConditionNested<N> extends BitwiseOrFluent<BitwiseOrConditionNested<N>> implements Nested<N> {
    BitwiseOrConditionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endBitwiseOrCondition() {
      return and();
    }

  }

  public class PropertyRefConditionNested<N> extends PropertyRefFluent<PropertyRefConditionNested<N>> implements Nested<N> {
    PropertyRefConditionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPropertyRefCondition() {
      return and();
    }

  }

  public class RightShiftConditionNested<N> extends RightShiftFluent<RightShiftConditionNested<N>> implements Nested<N> {
    RightShiftConditionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endRightShiftCondition() {
      return and();
    }

  }

  public class GreaterThanConditionNested<N> extends GreaterThanFluent<GreaterThanConditionNested<N>> implements Nested<N> {
    GreaterThanConditionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanCondition() {
      return and();
    }

  }

  public class DeclareConditionNested<N> extends DeclareFluent<DeclareConditionNested<N>> implements Nested<N> {
    DeclareConditionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endDeclareCondition() {
      return and();
    }

  }

  public class CastConditionNested<N> extends CastFluent<CastConditionNested<N>> implements Nested<N> {
    CastConditionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endCastCondition() {
      return and();
    }

  }

  public class ModuloConditionNested<N> extends ModuloFluent<ModuloConditionNested<N>> implements Nested<N> {
    ModuloConditionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endModuloCondition() {
      return and();
    }

  }

  public class ValueRefConditionNested<N> extends ValueRefFluent<ValueRefConditionNested<N>> implements Nested<N> {
    ValueRefConditionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endValueRefCondition() {
      return and();
    }

  }

  public class LeftShiftConditionNested<N> extends LeftShiftFluent<LeftShiftConditionNested<N>> implements Nested<N> {
    LeftShiftConditionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLeftShiftCondition() {
      return and();
    }

  }

  public class TernaryConditionNested<N> extends TernaryFluent<TernaryConditionNested<N>> implements Nested<N> {
    TernaryConditionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endTernaryCondition() {
      return and();
    }

  }

  public class BinaryExpressionConditionNested<N> extends BinaryExpressionFluent<BinaryExpressionConditionNested<N>>
      implements Nested<N> {
    BinaryExpressionConditionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endBinaryExpressionCondition() {
      return and();
    }

  }

  public class EqualsConditionNested<N> extends EqualsFluent<EqualsConditionNested<N>> implements Nested<N> {
    EqualsConditionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endEqualsCondition() {
      return and();
    }

  }

  public class EnclosedConditionNested<N> extends EnclosedFluent<EnclosedConditionNested<N>> implements Nested<N> {
    EnclosedConditionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endEnclosedCondition() {
      return and();
    }

  }

  public class PreDecrementConditionNested<N> extends PreDecrementFluent<PreDecrementConditionNested<N>> implements Nested<N> {
    PreDecrementConditionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPreDecrementCondition() {
      return and();
    }

  }

  public class PostDecrementConditionNested<N> extends PostDecrementFluent<PostDecrementConditionNested<N>>
      implements Nested<N> {
    PostDecrementConditionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPostDecrementCondition() {
      return and();
    }

  }

  public class LambdaConditionNested<N> extends LambdaFluent<LambdaConditionNested<N>> implements Nested<N> {
    LambdaConditionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLambdaCondition() {
      return and();
    }

  }

  public class NotConditionNested<N> extends NotFluent<NotConditionNested<N>> implements Nested<N> {
    NotConditionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNotCondition() {
      return and();
    }

  }

  public class AssignConditionNested<N> extends AssignFluent<AssignConditionNested<N>> implements Nested<N> {
    AssignConditionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endAssignCondition() {
      return and();
    }

  }

  public class NegativeConditionNested<N> extends NegativeFluent<NegativeConditionNested<N>> implements Nested<N> {
    NegativeConditionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endNegativeCondition() {
      return and();
    }

  }

  public class ThisConditionNested<N> extends ThisFluent<ThisConditionNested<N>> implements Nested<N> {
    ThisConditionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endThisCondition() {
      return and();
    }

  }

  public class LogicalAndConditionNested<N> extends LogicalAndFluent<LogicalAndConditionNested<N>> implements Nested<N> {
    LogicalAndConditionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLogicalAndCondition() {
      return and();
    }

  }

  public class PostIncrementConditionNested<N> extends PostIncrementFluent<PostIncrementConditionNested<N>>
      implements Nested<N> {
    PostIncrementConditionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPostIncrementCondition() {
      return and();
    }

  }

  public class RightUnsignedShiftConditionNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftConditionNested<N>>
      implements Nested<N> {
    RightUnsignedShiftConditionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endRightUnsignedShiftCondition() {
      return and();
    }

  }

  public class PlusConditionNested<N> extends PlusFluent<PlusConditionNested<N>> implements Nested<N> {
    PlusConditionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPlusCondition() {
      return and();
    }

  }

  public class ConstructConditionNested<N> extends ConstructFluent<ConstructConditionNested<N>> implements Nested<N> {
    ConstructConditionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endConstructCondition() {
      return and();
    }

  }

  public class XorConditionNested<N> extends XorFluent<XorConditionNested<N>> implements Nested<N> {
    XorConditionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endXorCondition() {
      return and();
    }

  }

  public class PreIncrementConditionNested<N> extends PreIncrementFluent<PreIncrementConditionNested<N>> implements Nested<N> {
    PreIncrementConditionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPreIncrementCondition() {
      return and();
    }

  }

  public class LessThanOrEqualConditionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualConditionNested<N>>
      implements Nested<N> {
    LessThanOrEqualConditionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endLessThanOrEqualCondition() {
      return and();
    }

  }

  public class PositiveConditionNested<N> extends PositiveFluent<PositiveConditionNested<N>> implements Nested<N> {
    PositiveConditionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withCondition(builder.build());
    }

    public N endPositiveCondition() {
      return and();
    }

  }

  public class MultiplyResultNested<N> extends MultiplyFluent<MultiplyResultNested<N>> implements Nested<N> {
    MultiplyResultNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endMultiplyResult() {
      return and();
    }

  }

  public class NewArrayResultNested<N> extends NewArrayFluent<NewArrayResultNested<N>> implements Nested<N> {
    NewArrayResultNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNewArrayResult() {
      return and();
    }

  }

  public class InstanceOfResultNested<N> extends InstanceOfFluent<InstanceOfResultNested<N>> implements Nested<N> {
    InstanceOfResultNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endInstanceOfResult() {
      return and();
    }

  }

  public class MethodCallResultNested<N> extends MethodCallFluent<MethodCallResultNested<N>> implements Nested<N> {
    MethodCallResultNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endMethodCallResult() {
      return and();
    }

  }

  public class InverseResultNested<N> extends InverseFluent<InverseResultNested<N>> implements Nested<N> {
    InverseResultNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endInverseResult() {
      return and();
    }

  }

  public class IndexResultNested<N> extends IndexFluent<IndexResultNested<N>> implements Nested<N> {
    IndexResultNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endIndexResult() {
      return and();
    }

  }

  public class GreaterThanOrEqualResultNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualResultNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualResultNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endGreaterThanOrEqualResult() {
      return and();
    }

  }

  public class BitwiseAndResultNested<N> extends BitwiseAndFluent<BitwiseAndResultNested<N>> implements Nested<N> {
    BitwiseAndResultNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endBitwiseAndResult() {
      return and();
    }

  }

  public class MinusResultNested<N> extends MinusFluent<MinusResultNested<N>> implements Nested<N> {
    MinusResultNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endMinusResult() {
      return and();
    }

  }

  public class LogicalOrResultNested<N> extends LogicalOrFluent<LogicalOrResultNested<N>> implements Nested<N> {
    LogicalOrResultNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLogicalOrResult() {
      return and();
    }

  }

  public class NotEqualsResultNested<N> extends NotEqualsFluent<NotEqualsResultNested<N>> implements Nested<N> {
    NotEqualsResultNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNotEqualsResult() {
      return and();
    }

  }

  public class DivideResultNested<N> extends DivideFluent<DivideResultNested<N>> implements Nested<N> {
    DivideResultNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endDivideResult() {
      return and();
    }

  }

  public class LessThanResultNested<N> extends LessThanFluent<LessThanResultNested<N>> implements Nested<N> {
    LessThanResultNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLessThanResult() {
      return and();
    }

  }

  public class BitwiseOrResultNested<N> extends BitwiseOrFluent<BitwiseOrResultNested<N>> implements Nested<N> {
    BitwiseOrResultNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endBitwiseOrResult() {
      return and();
    }

  }

  public class PropertyRefResultNested<N> extends PropertyRefFluent<PropertyRefResultNested<N>> implements Nested<N> {
    PropertyRefResultNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPropertyRefResult() {
      return and();
    }

  }

  public class RightShiftResultNested<N> extends RightShiftFluent<RightShiftResultNested<N>> implements Nested<N> {
    RightShiftResultNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endRightShiftResult() {
      return and();
    }

  }

  public class GreaterThanResultNested<N> extends GreaterThanFluent<GreaterThanResultNested<N>> implements Nested<N> {
    GreaterThanResultNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endGreaterThanResult() {
      return and();
    }

  }

  public class DeclareResultNested<N> extends DeclareFluent<DeclareResultNested<N>> implements Nested<N> {
    DeclareResultNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endDeclareResult() {
      return and();
    }

  }

  public class CastResultNested<N> extends CastFluent<CastResultNested<N>> implements Nested<N> {
    CastResultNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endCastResult() {
      return and();
    }

  }

  public class ModuloResultNested<N> extends ModuloFluent<ModuloResultNested<N>> implements Nested<N> {
    ModuloResultNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endModuloResult() {
      return and();
    }

  }

  public class ValueRefResultNested<N> extends ValueRefFluent<ValueRefResultNested<N>> implements Nested<N> {
    ValueRefResultNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endValueRefResult() {
      return and();
    }

  }

  public class LeftShiftResultNested<N> extends LeftShiftFluent<LeftShiftResultNested<N>> implements Nested<N> {
    LeftShiftResultNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLeftShiftResult() {
      return and();
    }

  }

  public class TernaryResultNested<N> extends TernaryFluent<TernaryResultNested<N>> implements Nested<N> {
    TernaryResultNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endTernaryResult() {
      return and();
    }

  }

  public class BinaryExpressionResultNested<N> extends BinaryExpressionFluent<BinaryExpressionResultNested<N>>
      implements Nested<N> {
    BinaryExpressionResultNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endBinaryExpressionResult() {
      return and();
    }

  }

  public class EqualsResultNested<N> extends EqualsFluent<EqualsResultNested<N>> implements Nested<N> {
    EqualsResultNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endEqualsResult() {
      return and();
    }

  }

  public class EnclosedResultNested<N> extends EnclosedFluent<EnclosedResultNested<N>> implements Nested<N> {
    EnclosedResultNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endEnclosedResult() {
      return and();
    }

  }

  public class PreDecrementResultNested<N> extends PreDecrementFluent<PreDecrementResultNested<N>> implements Nested<N> {
    PreDecrementResultNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPreDecrementResult() {
      return and();
    }

  }

  public class PostDecrementResultNested<N> extends PostDecrementFluent<PostDecrementResultNested<N>> implements Nested<N> {
    PostDecrementResultNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPostDecrementResult() {
      return and();
    }

  }

  public class LambdaResultNested<N> extends LambdaFluent<LambdaResultNested<N>> implements Nested<N> {
    LambdaResultNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLambdaResult() {
      return and();
    }

  }

  public class NotResultNested<N> extends NotFluent<NotResultNested<N>> implements Nested<N> {
    NotResultNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNotResult() {
      return and();
    }

  }

  public class AssignResultNested<N> extends AssignFluent<AssignResultNested<N>> implements Nested<N> {
    AssignResultNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endAssignResult() {
      return and();
    }

  }

  public class NegativeResultNested<N> extends NegativeFluent<NegativeResultNested<N>> implements Nested<N> {
    NegativeResultNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endNegativeResult() {
      return and();
    }

  }

  public class ThisResultNested<N> extends ThisFluent<ThisResultNested<N>> implements Nested<N> {
    ThisResultNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endThisResult() {
      return and();
    }

  }

  public class LogicalAndResultNested<N> extends LogicalAndFluent<LogicalAndResultNested<N>> implements Nested<N> {
    LogicalAndResultNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLogicalAndResult() {
      return and();
    }

  }

  public class PostIncrementResultNested<N> extends PostIncrementFluent<PostIncrementResultNested<N>> implements Nested<N> {
    PostIncrementResultNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPostIncrementResult() {
      return and();
    }

  }

  public class RightUnsignedShiftResultNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftResultNested<N>>
      implements Nested<N> {
    RightUnsignedShiftResultNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endRightUnsignedShiftResult() {
      return and();
    }

  }

  public class PlusResultNested<N> extends PlusFluent<PlusResultNested<N>> implements Nested<N> {
    PlusResultNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPlusResult() {
      return and();
    }

  }

  public class ConstructResultNested<N> extends ConstructFluent<ConstructResultNested<N>> implements Nested<N> {
    ConstructResultNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endConstructResult() {
      return and();
    }

  }

  public class XorResultNested<N> extends XorFluent<XorResultNested<N>> implements Nested<N> {
    XorResultNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endXorResult() {
      return and();
    }

  }

  public class PreIncrementResultNested<N> extends PreIncrementFluent<PreIncrementResultNested<N>> implements Nested<N> {
    PreIncrementResultNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPreIncrementResult() {
      return and();
    }

  }

  public class LessThanOrEqualResultNested<N> extends LessThanOrEqualFluent<LessThanOrEqualResultNested<N>>
      implements Nested<N> {
    LessThanOrEqualResultNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endLessThanOrEqualResult() {
      return and();
    }

  }

  public class PositiveResultNested<N> extends PositiveFluent<PositiveResultNested<N>> implements Nested<N> {
    PositiveResultNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withResult(builder.build());
    }

    public N endPositiveResult() {
      return and();
    }

  }

  public class MultiplyAlternativeNested<N> extends MultiplyFluent<MultiplyAlternativeNested<N>> implements Nested<N> {
    MultiplyAlternativeNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endMultiplyAlternative() {
      return and();
    }

  }

  public class NewArrayAlternativeNested<N> extends NewArrayFluent<NewArrayAlternativeNested<N>> implements Nested<N> {
    NewArrayAlternativeNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    NewArrayBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNewArrayAlternative() {
      return and();
    }

  }

  public class InstanceOfAlternativeNested<N> extends InstanceOfFluent<InstanceOfAlternativeNested<N>> implements Nested<N> {
    InstanceOfAlternativeNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    InstanceOfBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endInstanceOfAlternative() {
      return and();
    }

  }

  public class MethodCallAlternativeNested<N> extends MethodCallFluent<MethodCallAlternativeNested<N>> implements Nested<N> {
    MethodCallAlternativeNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endMethodCallAlternative() {
      return and();
    }

  }

  public class InverseAlternativeNested<N> extends InverseFluent<InverseAlternativeNested<N>> implements Nested<N> {
    InverseAlternativeNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endInverseAlternative() {
      return and();
    }

  }

  public class IndexAlternativeNested<N> extends IndexFluent<IndexAlternativeNested<N>> implements Nested<N> {
    IndexAlternativeNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    IndexBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endIndexAlternative() {
      return and();
    }

  }

  public class GreaterThanOrEqualAlternativeNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualAlternativeNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualAlternativeNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endGreaterThanOrEqualAlternative() {
      return and();
    }

  }

  public class BitwiseAndAlternativeNested<N> extends BitwiseAndFluent<BitwiseAndAlternativeNested<N>> implements Nested<N> {
    BitwiseAndAlternativeNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endBitwiseAndAlternative() {
      return and();
    }

  }

  public class MinusAlternativeNested<N> extends MinusFluent<MinusAlternativeNested<N>> implements Nested<N> {
    MinusAlternativeNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endMinusAlternative() {
      return and();
    }

  }

  public class LogicalOrAlternativeNested<N> extends LogicalOrFluent<LogicalOrAlternativeNested<N>> implements Nested<N> {
    LogicalOrAlternativeNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLogicalOrAlternative() {
      return and();
    }

  }

  public class NotEqualsAlternativeNested<N> extends NotEqualsFluent<NotEqualsAlternativeNested<N>> implements Nested<N> {
    NotEqualsAlternativeNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNotEqualsAlternative() {
      return and();
    }

  }

  public class DivideAlternativeNested<N> extends DivideFluent<DivideAlternativeNested<N>> implements Nested<N> {
    DivideAlternativeNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endDivideAlternative() {
      return and();
    }

  }

  public class LessThanAlternativeNested<N> extends LessThanFluent<LessThanAlternativeNested<N>> implements Nested<N> {
    LessThanAlternativeNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLessThanAlternative() {
      return and();
    }

  }

  public class BitwiseOrAlternativeNested<N> extends BitwiseOrFluent<BitwiseOrAlternativeNested<N>> implements Nested<N> {
    BitwiseOrAlternativeNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endBitwiseOrAlternative() {
      return and();
    }

  }

  public class PropertyRefAlternativeNested<N> extends PropertyRefFluent<PropertyRefAlternativeNested<N>> implements Nested<N> {
    PropertyRefAlternativeNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPropertyRefAlternative() {
      return and();
    }

  }

  public class RightShiftAlternativeNested<N> extends RightShiftFluent<RightShiftAlternativeNested<N>> implements Nested<N> {
    RightShiftAlternativeNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endRightShiftAlternative() {
      return and();
    }

  }

  public class GreaterThanAlternativeNested<N> extends GreaterThanFluent<GreaterThanAlternativeNested<N>> implements Nested<N> {
    GreaterThanAlternativeNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endGreaterThanAlternative() {
      return and();
    }

  }

  public class DeclareAlternativeNested<N> extends DeclareFluent<DeclareAlternativeNested<N>> implements Nested<N> {
    DeclareAlternativeNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endDeclareAlternative() {
      return and();
    }

  }

  public class CastAlternativeNested<N> extends CastFluent<CastAlternativeNested<N>> implements Nested<N> {
    CastAlternativeNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    CastBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endCastAlternative() {
      return and();
    }

  }

  public class ModuloAlternativeNested<N> extends ModuloFluent<ModuloAlternativeNested<N>> implements Nested<N> {
    ModuloAlternativeNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endModuloAlternative() {
      return and();
    }

  }

  public class ValueRefAlternativeNested<N> extends ValueRefFluent<ValueRefAlternativeNested<N>> implements Nested<N> {
    ValueRefAlternativeNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endValueRefAlternative() {
      return and();
    }

  }

  public class LeftShiftAlternativeNested<N> extends LeftShiftFluent<LeftShiftAlternativeNested<N>> implements Nested<N> {
    LeftShiftAlternativeNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLeftShiftAlternative() {
      return and();
    }

  }

  public class TernaryAlternativeNested<N> extends TernaryFluent<TernaryAlternativeNested<N>> implements Nested<N> {
    TernaryAlternativeNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endTernaryAlternative() {
      return and();
    }

  }

  public class BinaryExpressionAlternativeNested<N> extends BinaryExpressionFluent<BinaryExpressionAlternativeNested<N>>
      implements Nested<N> {
    BinaryExpressionAlternativeNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endBinaryExpressionAlternative() {
      return and();
    }

  }

  public class EqualsAlternativeNested<N> extends EqualsFluent<EqualsAlternativeNested<N>> implements Nested<N> {
    EqualsAlternativeNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endEqualsAlternative() {
      return and();
    }

  }

  public class EnclosedAlternativeNested<N> extends EnclosedFluent<EnclosedAlternativeNested<N>> implements Nested<N> {
    EnclosedAlternativeNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endEnclosedAlternative() {
      return and();
    }

  }

  public class PreDecrementAlternativeNested<N> extends PreDecrementFluent<PreDecrementAlternativeNested<N>>
      implements Nested<N> {
    PreDecrementAlternativeNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPreDecrementAlternative() {
      return and();
    }

  }

  public class PostDecrementAlternativeNested<N> extends PostDecrementFluent<PostDecrementAlternativeNested<N>>
      implements Nested<N> {
    PostDecrementAlternativeNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPostDecrementAlternative() {
      return and();
    }

  }

  public class LambdaAlternativeNested<N> extends LambdaFluent<LambdaAlternativeNested<N>> implements Nested<N> {
    LambdaAlternativeNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLambdaAlternative() {
      return and();
    }

  }

  public class NotAlternativeNested<N> extends NotFluent<NotAlternativeNested<N>> implements Nested<N> {
    NotAlternativeNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNotAlternative() {
      return and();
    }

  }

  public class AssignAlternativeNested<N> extends AssignFluent<AssignAlternativeNested<N>> implements Nested<N> {
    AssignAlternativeNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endAssignAlternative() {
      return and();
    }

  }

  public class NegativeAlternativeNested<N> extends NegativeFluent<NegativeAlternativeNested<N>> implements Nested<N> {
    NegativeAlternativeNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endNegativeAlternative() {
      return and();
    }

  }

  public class ThisAlternativeNested<N> extends ThisFluent<ThisAlternativeNested<N>> implements Nested<N> {
    ThisAlternativeNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endThisAlternative() {
      return and();
    }

  }

  public class LogicalAndAlternativeNested<N> extends LogicalAndFluent<LogicalAndAlternativeNested<N>> implements Nested<N> {
    LogicalAndAlternativeNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLogicalAndAlternative() {
      return and();
    }

  }

  public class PostIncrementAlternativeNested<N> extends PostIncrementFluent<PostIncrementAlternativeNested<N>>
      implements Nested<N> {
    PostIncrementAlternativeNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPostIncrementAlternative() {
      return and();
    }

  }

  public class RightUnsignedShiftAlternativeNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftAlternativeNested<N>>
      implements Nested<N> {
    RightUnsignedShiftAlternativeNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endRightUnsignedShiftAlternative() {
      return and();
    }

  }

  public class PlusAlternativeNested<N> extends PlusFluent<PlusAlternativeNested<N>> implements Nested<N> {
    PlusAlternativeNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPlusAlternative() {
      return and();
    }

  }

  public class ConstructAlternativeNested<N> extends ConstructFluent<ConstructAlternativeNested<N>> implements Nested<N> {
    ConstructAlternativeNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endConstructAlternative() {
      return and();
    }

  }

  public class XorAlternativeNested<N> extends XorFluent<XorAlternativeNested<N>> implements Nested<N> {
    XorAlternativeNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endXorAlternative() {
      return and();
    }

  }

  public class PreIncrementAlternativeNested<N> extends PreIncrementFluent<PreIncrementAlternativeNested<N>>
      implements Nested<N> {
    PreIncrementAlternativeNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPreIncrementAlternative() {
      return and();
    }

  }

  public class LessThanOrEqualAlternativeNested<N> extends LessThanOrEqualFluent<LessThanOrEqualAlternativeNested<N>>
      implements Nested<N> {
    LessThanOrEqualAlternativeNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endLessThanOrEqualAlternative() {
      return and();
    }

  }

  public class PositiveAlternativeNested<N> extends PositiveFluent<PositiveAlternativeNested<N>> implements Nested<N> {
    PositiveAlternativeNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) TernaryFluent.this.withAlternative(builder.build());
    }

    public N endPositiveAlternative() {
      return and();
    }

  }

}
