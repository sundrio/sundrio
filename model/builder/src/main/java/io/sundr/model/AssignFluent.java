package io.sundr.model;

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
public class AssignFluent<A extends AssignFluent<A>> extends BaseFluent<A> {
  public AssignFluent() {
  }

  public AssignFluent(Assign instance) {
    if (instance != null) {
      this.withTarget(instance.getTarget());
      this.withValue(instance.getValue());
    }
  }

  private VisitableBuilder<? extends Expression, ?> target;
  private VisitableBuilder<? extends Expression, ?> value;

  public Expression buildTarget() {
    return this.target != null ? this.target.build() : null;
  }

  public A withTarget(Expression target) {
    if (target == null) {
      this.target = null;
      _visitables.remove("target");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(target);
    _visitables.get("target").clear();
    _visitables.get("target").add(builder);
    this.target = builder;
    return (A) this;
  }

  public boolean hasTarget() {
    return this.target != null;
  }

  public MultiplyTargetNested<A> withNewMultiplyTarget() {
    return new MultiplyTargetNested(null);
  }

  public MultiplyTargetNested<A> withNewMultiplyTargetLike(Multiply item) {
    return new MultiplyTargetNested(item);
  }

  public A withNewMultiplyTarget(Object left, Object right) {
    return (A) withTarget(new Multiply(left, right));
  }

  public MethodCallTargetNested<A> withNewMethodCallTarget() {
    return new MethodCallTargetNested(null);
  }

  public MethodCallTargetNested<A> withNewMethodCallTargetLike(MethodCall item) {
    return new MethodCallTargetNested(item);
  }

  public InverseTargetNested<A> withNewInverseTarget() {
    return new InverseTargetNested(null);
  }

  public InverseTargetNested<A> withNewInverseTargetLike(Inverse item) {
    return new InverseTargetNested(item);
  }

  public GreaterThanOrEqualTargetNested<A> withNewGreaterThanOrEqualTarget() {
    return new GreaterThanOrEqualTargetNested(null);
  }

  public GreaterThanOrEqualTargetNested<A> withNewGreaterThanOrEqualTargetLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualTargetNested(item);
  }

  public A withNewGreaterThanOrEqualTarget(Object left, Object right) {
    return (A) withTarget(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndTargetNested<A> withNewBitwiseAndTarget() {
    return new BitwiseAndTargetNested(null);
  }

  public BitwiseAndTargetNested<A> withNewBitwiseAndTargetLike(BitwiseAnd item) {
    return new BitwiseAndTargetNested(item);
  }

  public A withNewBitwiseAndTarget(Object left, Object right) {
    return (A) withTarget(new BitwiseAnd(left, right));
  }

  public MinusTargetNested<A> withNewMinusTarget() {
    return new MinusTargetNested(null);
  }

  public MinusTargetNested<A> withNewMinusTargetLike(Minus item) {
    return new MinusTargetNested(item);
  }

  public A withNewMinusTarget(Object left, Object right) {
    return (A) withTarget(new Minus(left, right));
  }

  public LogicalOrTargetNested<A> withNewLogicalOrTarget() {
    return new LogicalOrTargetNested(null);
  }

  public LogicalOrTargetNested<A> withNewLogicalOrTargetLike(LogicalOr item) {
    return new LogicalOrTargetNested(item);
  }

  public A withNewLogicalOrTarget(Object left, Object right) {
    return (A) withTarget(new LogicalOr(left, right));
  }

  public NotEqualsTargetNested<A> withNewNotEqualsTarget() {
    return new NotEqualsTargetNested(null);
  }

  public NotEqualsTargetNested<A> withNewNotEqualsTargetLike(NotEquals item) {
    return new NotEqualsTargetNested(item);
  }

  public A withNewNotEqualsTarget(Object left, Object right) {
    return (A) withTarget(new NotEquals(left, right));
  }

  public DivideTargetNested<A> withNewDivideTarget() {
    return new DivideTargetNested(null);
  }

  public DivideTargetNested<A> withNewDivideTargetLike(Divide item) {
    return new DivideTargetNested(item);
  }

  public A withNewDivideTarget(Object left, Object right) {
    return (A) withTarget(new Divide(left, right));
  }

  public LessThanTargetNested<A> withNewLessThanTarget() {
    return new LessThanTargetNested(null);
  }

  public LessThanTargetNested<A> withNewLessThanTargetLike(LessThan item) {
    return new LessThanTargetNested(item);
  }

  public A withNewLessThanTarget(Object left, Object right) {
    return (A) withTarget(new LessThan(left, right));
  }

  public BitwiseOrTargetNested<A> withNewBitwiseOrTarget() {
    return new BitwiseOrTargetNested(null);
  }

  public BitwiseOrTargetNested<A> withNewBitwiseOrTargetLike(BitwiseOr item) {
    return new BitwiseOrTargetNested(item);
  }

  public A withNewBitwiseOrTarget(Object left, Object right) {
    return (A) withTarget(new BitwiseOr(left, right));
  }

  public PropertyRefTargetNested<A> withNewPropertyRefTarget() {
    return new PropertyRefTargetNested(null);
  }

  public PropertyRefTargetNested<A> withNewPropertyRefTargetLike(PropertyRef item) {
    return new PropertyRefTargetNested(item);
  }

  public RightShiftTargetNested<A> withNewRightShiftTarget() {
    return new RightShiftTargetNested(null);
  }

  public RightShiftTargetNested<A> withNewRightShiftTargetLike(RightShift item) {
    return new RightShiftTargetNested(item);
  }

  public A withNewRightShiftTarget(Object left, Object right) {
    return (A) withTarget(new RightShift(left, right));
  }

  public GreaterThanTargetNested<A> withNewGreaterThanTarget() {
    return new GreaterThanTargetNested(null);
  }

  public GreaterThanTargetNested<A> withNewGreaterThanTargetLike(GreaterThan item) {
    return new GreaterThanTargetNested(item);
  }

  public A withNewGreaterThanTarget(Object left, Object right) {
    return (A) withTarget(new GreaterThan(left, right));
  }

  public ModuloTargetNested<A> withNewModuloTarget() {
    return new ModuloTargetNested(null);
  }

  public ModuloTargetNested<A> withNewModuloTargetLike(Modulo item) {
    return new ModuloTargetNested(item);
  }

  public A withNewModuloTarget(Object left, Object right) {
    return (A) withTarget(new Modulo(left, right));
  }

  public ValueRefTargetNested<A> withNewValueRefTarget() {
    return new ValueRefTargetNested(null);
  }

  public ValueRefTargetNested<A> withNewValueRefTargetLike(ValueRef item) {
    return new ValueRefTargetNested(item);
  }

  public A withNewValueRefTarget(Object value) {
    return (A) withTarget(new ValueRef(value));
  }

  public LeftShiftTargetNested<A> withNewLeftShiftTarget() {
    return new LeftShiftTargetNested(null);
  }

  public LeftShiftTargetNested<A> withNewLeftShiftTargetLike(LeftShift item) {
    return new LeftShiftTargetNested(item);
  }

  public A withNewLeftShiftTarget(Object left, Object right) {
    return (A) withTarget(new LeftShift(left, right));
  }

  public TernaryTargetNested<A> withNewTernaryTarget() {
    return new TernaryTargetNested(null);
  }

  public TernaryTargetNested<A> withNewTernaryTargetLike(Ternary item) {
    return new TernaryTargetNested(item);
  }

  public BinaryExpressionTargetNested<A> withNewBinaryExpressionTarget() {
    return new BinaryExpressionTargetNested(null);
  }

  public BinaryExpressionTargetNested<A> withNewBinaryExpressionTargetLike(BinaryExpression item) {
    return new BinaryExpressionTargetNested(item);
  }

  public EqualsTargetNested<A> withNewEqualsTarget() {
    return new EqualsTargetNested(null);
  }

  public EqualsTargetNested<A> withNewEqualsTargetLike(Equals item) {
    return new EqualsTargetNested(item);
  }

  public A withNewEqualsTarget(Object left, Object right) {
    return (A) withTarget(new Equals(left, right));
  }

  public EnclosedTargetNested<A> withNewEnclosedTarget() {
    return new EnclosedTargetNested(null);
  }

  public EnclosedTargetNested<A> withNewEnclosedTargetLike(Enclosed item) {
    return new EnclosedTargetNested(item);
  }

  public PreDecrementTargetNested<A> withNewPreDecrementTarget() {
    return new PreDecrementTargetNested(null);
  }

  public PreDecrementTargetNested<A> withNewPreDecrementTargetLike(PreDecrement item) {
    return new PreDecrementTargetNested(item);
  }

  public PostDecrementTargetNested<A> withNewPostDecrementTarget() {
    return new PostDecrementTargetNested(null);
  }

  public PostDecrementTargetNested<A> withNewPostDecrementTargetLike(PostDecrement item) {
    return new PostDecrementTargetNested(item);
  }

  public NotTargetNested<A> withNewNotTarget() {
    return new NotTargetNested(null);
  }

  public NotTargetNested<A> withNewNotTargetLike(Not item) {
    return new NotTargetNested(item);
  }

  public AssignTargetNested<A> withNewAssignTarget() {
    return new AssignTargetNested(null);
  }

  public AssignTargetNested<A> withNewAssignTargetLike(Assign item) {
    return new AssignTargetNested(item);
  }

  public NegativeTargetNested<A> withNewNegativeTarget() {
    return new NegativeTargetNested(null);
  }

  public NegativeTargetNested<A> withNewNegativeTargetLike(Negative item) {
    return new NegativeTargetNested(item);
  }

  public ThisTargetNested<A> withNewThisTarget() {
    return new ThisTargetNested(null);
  }

  public ThisTargetNested<A> withNewThisTargetLike(This item) {
    return new ThisTargetNested(item);
  }

  public LogicalAndTargetNested<A> withNewLogicalAndTarget() {
    return new LogicalAndTargetNested(null);
  }

  public LogicalAndTargetNested<A> withNewLogicalAndTargetLike(LogicalAnd item) {
    return new LogicalAndTargetNested(item);
  }

  public A withNewLogicalAndTarget(Object left, Object right) {
    return (A) withTarget(new LogicalAnd(left, right));
  }

  public PostIncrementTargetNested<A> withNewPostIncrementTarget() {
    return new PostIncrementTargetNested(null);
  }

  public PostIncrementTargetNested<A> withNewPostIncrementTargetLike(PostIncrement item) {
    return new PostIncrementTargetNested(item);
  }

  public RightUnsignedShiftTargetNested<A> withNewRightUnsignedShiftTarget() {
    return new RightUnsignedShiftTargetNested(null);
  }

  public RightUnsignedShiftTargetNested<A> withNewRightUnsignedShiftTargetLike(RightUnsignedShift item) {
    return new RightUnsignedShiftTargetNested(item);
  }

  public A withNewRightUnsignedShiftTarget(Object left, Object right) {
    return (A) withTarget(new RightUnsignedShift(left, right));
  }

  public PlusTargetNested<A> withNewPlusTarget() {
    return new PlusTargetNested(null);
  }

  public PlusTargetNested<A> withNewPlusTargetLike(Plus item) {
    return new PlusTargetNested(item);
  }

  public A withNewPlusTarget(Object left, Object right) {
    return (A) withTarget(new Plus(left, right));
  }

  public ConstructTargetNested<A> withNewConstructTarget() {
    return new ConstructTargetNested(null);
  }

  public ConstructTargetNested<A> withNewConstructTargetLike(Construct item) {
    return new ConstructTargetNested(item);
  }

  public XorTargetNested<A> withNewXorTarget() {
    return new XorTargetNested(null);
  }

  public XorTargetNested<A> withNewXorTargetLike(Xor item) {
    return new XorTargetNested(item);
  }

  public A withNewXorTarget(Object left, Object right) {
    return (A) withTarget(new Xor(left, right));
  }

  public PreIncrementTargetNested<A> withNewPreIncrementTarget() {
    return new PreIncrementTargetNested(null);
  }

  public PreIncrementTargetNested<A> withNewPreIncrementTargetLike(PreIncrement item) {
    return new PreIncrementTargetNested(item);
  }

  public LessThanOrEqualTargetNested<A> withNewLessThanOrEqualTarget() {
    return new LessThanOrEqualTargetNested(null);
  }

  public LessThanOrEqualTargetNested<A> withNewLessThanOrEqualTargetLike(LessThanOrEqual item) {
    return new LessThanOrEqualTargetNested(item);
  }

  public A withNewLessThanOrEqualTarget(Object left, Object right) {
    return (A) withTarget(new LessThanOrEqual(left, right));
  }

  public PositiveTargetNested<A> withNewPositiveTarget() {
    return new PositiveTargetNested(null);
  }

  public PositiveTargetNested<A> withNewPositiveTargetLike(Positive item) {
    return new PositiveTargetNested(item);
  }

  public Expression buildValue() {
    return this.value != null ? this.value.build() : null;
  }

  public A withValue(Expression value) {
    if (value == null) {
      this.value = null;
      _visitables.remove("value");
      return (A) this;
    }
    VisitableBuilder<? extends Expression, ?> builder = builder(value);
    _visitables.get("value").clear();
    _visitables.get("value").add(builder);
    this.value = builder;
    return (A) this;
  }

  public boolean hasValue() {
    return this.value != null;
  }

  public MultiplyValueNested<A> withNewMultiplyValue() {
    return new MultiplyValueNested(null);
  }

  public MultiplyValueNested<A> withNewMultiplyValueLike(Multiply item) {
    return new MultiplyValueNested(item);
  }

  public A withNewMultiplyValue(Object left, Object right) {
    return (A) withValue(new Multiply(left, right));
  }

  public MethodCallValueNested<A> withNewMethodCallValue() {
    return new MethodCallValueNested(null);
  }

  public MethodCallValueNested<A> withNewMethodCallValueLike(MethodCall item) {
    return new MethodCallValueNested(item);
  }

  public InverseValueNested<A> withNewInverseValue() {
    return new InverseValueNested(null);
  }

  public InverseValueNested<A> withNewInverseValueLike(Inverse item) {
    return new InverseValueNested(item);
  }

  public GreaterThanOrEqualValueNested<A> withNewGreaterThanOrEqualValue() {
    return new GreaterThanOrEqualValueNested(null);
  }

  public GreaterThanOrEqualValueNested<A> withNewGreaterThanOrEqualValueLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualValueNested(item);
  }

  public A withNewGreaterThanOrEqualValue(Object left, Object right) {
    return (A) withValue(new GreaterThanOrEqual(left, right));
  }

  public BitwiseAndValueNested<A> withNewBitwiseAndValue() {
    return new BitwiseAndValueNested(null);
  }

  public BitwiseAndValueNested<A> withNewBitwiseAndValueLike(BitwiseAnd item) {
    return new BitwiseAndValueNested(item);
  }

  public A withNewBitwiseAndValue(Object left, Object right) {
    return (A) withValue(new BitwiseAnd(left, right));
  }

  public MinusValueNested<A> withNewMinusValue() {
    return new MinusValueNested(null);
  }

  public MinusValueNested<A> withNewMinusValueLike(Minus item) {
    return new MinusValueNested(item);
  }

  public A withNewMinusValue(Object left, Object right) {
    return (A) withValue(new Minus(left, right));
  }

  public LogicalOrValueNested<A> withNewLogicalOrValue() {
    return new LogicalOrValueNested(null);
  }

  public LogicalOrValueNested<A> withNewLogicalOrValueLike(LogicalOr item) {
    return new LogicalOrValueNested(item);
  }

  public A withNewLogicalOrValue(Object left, Object right) {
    return (A) withValue(new LogicalOr(left, right));
  }

  public NotEqualsValueNested<A> withNewNotEqualsValue() {
    return new NotEqualsValueNested(null);
  }

  public NotEqualsValueNested<A> withNewNotEqualsValueLike(NotEquals item) {
    return new NotEqualsValueNested(item);
  }

  public A withNewNotEqualsValue(Object left, Object right) {
    return (A) withValue(new NotEquals(left, right));
  }

  public DivideValueNested<A> withNewDivideValue() {
    return new DivideValueNested(null);
  }

  public DivideValueNested<A> withNewDivideValueLike(Divide item) {
    return new DivideValueNested(item);
  }

  public A withNewDivideValue(Object left, Object right) {
    return (A) withValue(new Divide(left, right));
  }

  public LessThanValueNested<A> withNewLessThanValue() {
    return new LessThanValueNested(null);
  }

  public LessThanValueNested<A> withNewLessThanValueLike(LessThan item) {
    return new LessThanValueNested(item);
  }

  public A withNewLessThanValue(Object left, Object right) {
    return (A) withValue(new LessThan(left, right));
  }

  public BitwiseOrValueNested<A> withNewBitwiseOrValue() {
    return new BitwiseOrValueNested(null);
  }

  public BitwiseOrValueNested<A> withNewBitwiseOrValueLike(BitwiseOr item) {
    return new BitwiseOrValueNested(item);
  }

  public A withNewBitwiseOrValue(Object left, Object right) {
    return (A) withValue(new BitwiseOr(left, right));
  }

  public PropertyRefValueNested<A> withNewPropertyRefValue() {
    return new PropertyRefValueNested(null);
  }

  public PropertyRefValueNested<A> withNewPropertyRefValueLike(PropertyRef item) {
    return new PropertyRefValueNested(item);
  }

  public RightShiftValueNested<A> withNewRightShiftValue() {
    return new RightShiftValueNested(null);
  }

  public RightShiftValueNested<A> withNewRightShiftValueLike(RightShift item) {
    return new RightShiftValueNested(item);
  }

  public A withNewRightShiftValue(Object left, Object right) {
    return (A) withValue(new RightShift(left, right));
  }

  public GreaterThanValueNested<A> withNewGreaterThanValue() {
    return new GreaterThanValueNested(null);
  }

  public GreaterThanValueNested<A> withNewGreaterThanValueLike(GreaterThan item) {
    return new GreaterThanValueNested(item);
  }

  public A withNewGreaterThanValue(Object left, Object right) {
    return (A) withValue(new GreaterThan(left, right));
  }

  public ModuloValueNested<A> withNewModuloValue() {
    return new ModuloValueNested(null);
  }

  public ModuloValueNested<A> withNewModuloValueLike(Modulo item) {
    return new ModuloValueNested(item);
  }

  public A withNewModuloValue(Object left, Object right) {
    return (A) withValue(new Modulo(left, right));
  }

  public ValueRefValueNested<A> withNewValueRefValue() {
    return new ValueRefValueNested(null);
  }

  public ValueRefValueNested<A> withNewValueRefValueLike(ValueRef item) {
    return new ValueRefValueNested(item);
  }

  public A withNewValueRefValue(Object value) {
    return (A) withValue(new ValueRef(value));
  }

  public LeftShiftValueNested<A> withNewLeftShiftValue() {
    return new LeftShiftValueNested(null);
  }

  public LeftShiftValueNested<A> withNewLeftShiftValueLike(LeftShift item) {
    return new LeftShiftValueNested(item);
  }

  public A withNewLeftShiftValue(Object left, Object right) {
    return (A) withValue(new LeftShift(left, right));
  }

  public TernaryValueNested<A> withNewTernaryValue() {
    return new TernaryValueNested(null);
  }

  public TernaryValueNested<A> withNewTernaryValueLike(Ternary item) {
    return new TernaryValueNested(item);
  }

  public BinaryExpressionValueNested<A> withNewBinaryExpressionValue() {
    return new BinaryExpressionValueNested(null);
  }

  public BinaryExpressionValueNested<A> withNewBinaryExpressionValueLike(BinaryExpression item) {
    return new BinaryExpressionValueNested(item);
  }

  public EqualsValueNested<A> withNewEqualsValue() {
    return new EqualsValueNested(null);
  }

  public EqualsValueNested<A> withNewEqualsValueLike(Equals item) {
    return new EqualsValueNested(item);
  }

  public A withNewEqualsValue(Object left, Object right) {
    return (A) withValue(new Equals(left, right));
  }

  public EnclosedValueNested<A> withNewEnclosedValue() {
    return new EnclosedValueNested(null);
  }

  public EnclosedValueNested<A> withNewEnclosedValueLike(Enclosed item) {
    return new EnclosedValueNested(item);
  }

  public PreDecrementValueNested<A> withNewPreDecrementValue() {
    return new PreDecrementValueNested(null);
  }

  public PreDecrementValueNested<A> withNewPreDecrementValueLike(PreDecrement item) {
    return new PreDecrementValueNested(item);
  }

  public PostDecrementValueNested<A> withNewPostDecrementValue() {
    return new PostDecrementValueNested(null);
  }

  public PostDecrementValueNested<A> withNewPostDecrementValueLike(PostDecrement item) {
    return new PostDecrementValueNested(item);
  }

  public NotValueNested<A> withNewNotValue() {
    return new NotValueNested(null);
  }

  public NotValueNested<A> withNewNotValueLike(Not item) {
    return new NotValueNested(item);
  }

  public AssignValueNested<A> withNewAssignValue() {
    return new AssignValueNested(null);
  }

  public AssignValueNested<A> withNewAssignValueLike(Assign item) {
    return new AssignValueNested(item);
  }

  public NegativeValueNested<A> withNewNegativeValue() {
    return new NegativeValueNested(null);
  }

  public NegativeValueNested<A> withNewNegativeValueLike(Negative item) {
    return new NegativeValueNested(item);
  }

  public ThisValueNested<A> withNewThisValue() {
    return new ThisValueNested(null);
  }

  public ThisValueNested<A> withNewThisValueLike(This item) {
    return new ThisValueNested(item);
  }

  public LogicalAndValueNested<A> withNewLogicalAndValue() {
    return new LogicalAndValueNested(null);
  }

  public LogicalAndValueNested<A> withNewLogicalAndValueLike(LogicalAnd item) {
    return new LogicalAndValueNested(item);
  }

  public A withNewLogicalAndValue(Object left, Object right) {
    return (A) withValue(new LogicalAnd(left, right));
  }

  public PostIncrementValueNested<A> withNewPostIncrementValue() {
    return new PostIncrementValueNested(null);
  }

  public PostIncrementValueNested<A> withNewPostIncrementValueLike(PostIncrement item) {
    return new PostIncrementValueNested(item);
  }

  public RightUnsignedShiftValueNested<A> withNewRightUnsignedShiftValue() {
    return new RightUnsignedShiftValueNested(null);
  }

  public RightUnsignedShiftValueNested<A> withNewRightUnsignedShiftValueLike(RightUnsignedShift item) {
    return new RightUnsignedShiftValueNested(item);
  }

  public A withNewRightUnsignedShiftValue(Object left, Object right) {
    return (A) withValue(new RightUnsignedShift(left, right));
  }

  public PlusValueNested<A> withNewPlusValue() {
    return new PlusValueNested(null);
  }

  public PlusValueNested<A> withNewPlusValueLike(Plus item) {
    return new PlusValueNested(item);
  }

  public A withNewPlusValue(Object left, Object right) {
    return (A) withValue(new Plus(left, right));
  }

  public ConstructValueNested<A> withNewConstructValue() {
    return new ConstructValueNested(null);
  }

  public ConstructValueNested<A> withNewConstructValueLike(Construct item) {
    return new ConstructValueNested(item);
  }

  public XorValueNested<A> withNewXorValue() {
    return new XorValueNested(null);
  }

  public XorValueNested<A> withNewXorValueLike(Xor item) {
    return new XorValueNested(item);
  }

  public A withNewXorValue(Object left, Object right) {
    return (A) withValue(new Xor(left, right));
  }

  public PreIncrementValueNested<A> withNewPreIncrementValue() {
    return new PreIncrementValueNested(null);
  }

  public PreIncrementValueNested<A> withNewPreIncrementValueLike(PreIncrement item) {
    return new PreIncrementValueNested(item);
  }

  public LessThanOrEqualValueNested<A> withNewLessThanOrEqualValue() {
    return new LessThanOrEqualValueNested(null);
  }

  public LessThanOrEqualValueNested<A> withNewLessThanOrEqualValueLike(LessThanOrEqual item) {
    return new LessThanOrEqualValueNested(item);
  }

  public A withNewLessThanOrEqualValue(Object left, Object right) {
    return (A) withValue(new LessThanOrEqual(left, right));
  }

  public PositiveValueNested<A> withNewPositiveValue() {
    return new PositiveValueNested(null);
  }

  public PositiveValueNested<A> withNewPositiveValueLike(Positive item) {
    return new PositiveValueNested(item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    AssignFluent that = (AssignFluent) o;
    if (!java.util.Objects.equals(target, that.target))
      return false;

    if (!java.util.Objects.equals(value, that.value))
      return false;

    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(target, value, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (target != null) {
      sb.append("target:");
      sb.append(target + ",");
    }
    if (value != null) {
      sb.append("value:");
      sb.append(value);
    }
    sb.append("}");
    return sb.toString();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "io.sundr.model." + "Multiply":
        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);
      case "io.sundr.model." + "MethodCall":
        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);
      case "io.sundr.model." + "Inverse":
        return (VisitableBuilder<T, ?>) new InverseBuilder((Inverse) item);
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

  public class MultiplyTargetNested<N> extends MultiplyFluent<MultiplyTargetNested<N>> implements Nested<N> {
    MultiplyTargetNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endMultiplyTarget() {
      return and();
    }

  }

  public class MethodCallTargetNested<N> extends MethodCallFluent<MethodCallTargetNested<N>> implements Nested<N> {
    MethodCallTargetNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endMethodCallTarget() {
      return and();
    }

  }

  public class InverseTargetNested<N> extends InverseFluent<InverseTargetNested<N>> implements Nested<N> {
    InverseTargetNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endInverseTarget() {
      return and();
    }

  }

  public class GreaterThanOrEqualTargetNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualTargetNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualTargetNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endGreaterThanOrEqualTarget() {
      return and();
    }

  }

  public class BitwiseAndTargetNested<N> extends BitwiseAndFluent<BitwiseAndTargetNested<N>> implements Nested<N> {
    BitwiseAndTargetNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endBitwiseAndTarget() {
      return and();
    }

  }

  public class MinusTargetNested<N> extends MinusFluent<MinusTargetNested<N>> implements Nested<N> {
    MinusTargetNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endMinusTarget() {
      return and();
    }

  }

  public class LogicalOrTargetNested<N> extends LogicalOrFluent<LogicalOrTargetNested<N>> implements Nested<N> {
    LogicalOrTargetNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLogicalOrTarget() {
      return and();
    }

  }

  public class NotEqualsTargetNested<N> extends NotEqualsFluent<NotEqualsTargetNested<N>> implements Nested<N> {
    NotEqualsTargetNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNotEqualsTarget() {
      return and();
    }

  }

  public class DivideTargetNested<N> extends DivideFluent<DivideTargetNested<N>> implements Nested<N> {
    DivideTargetNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endDivideTarget() {
      return and();
    }

  }

  public class LessThanTargetNested<N> extends LessThanFluent<LessThanTargetNested<N>> implements Nested<N> {
    LessThanTargetNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLessThanTarget() {
      return and();
    }

  }

  public class BitwiseOrTargetNested<N> extends BitwiseOrFluent<BitwiseOrTargetNested<N>> implements Nested<N> {
    BitwiseOrTargetNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endBitwiseOrTarget() {
      return and();
    }

  }

  public class PropertyRefTargetNested<N> extends PropertyRefFluent<PropertyRefTargetNested<N>> implements Nested<N> {
    PropertyRefTargetNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPropertyRefTarget() {
      return and();
    }

  }

  public class RightShiftTargetNested<N> extends RightShiftFluent<RightShiftTargetNested<N>> implements Nested<N> {
    RightShiftTargetNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endRightShiftTarget() {
      return and();
    }

  }

  public class GreaterThanTargetNested<N> extends GreaterThanFluent<GreaterThanTargetNested<N>> implements Nested<N> {
    GreaterThanTargetNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endGreaterThanTarget() {
      return and();
    }

  }

  public class ModuloTargetNested<N> extends ModuloFluent<ModuloTargetNested<N>> implements Nested<N> {
    ModuloTargetNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endModuloTarget() {
      return and();
    }

  }

  public class ValueRefTargetNested<N> extends ValueRefFluent<ValueRefTargetNested<N>> implements Nested<N> {
    ValueRefTargetNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endValueRefTarget() {
      return and();
    }

  }

  public class LeftShiftTargetNested<N> extends LeftShiftFluent<LeftShiftTargetNested<N>> implements Nested<N> {
    LeftShiftTargetNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLeftShiftTarget() {
      return and();
    }

  }

  public class TernaryTargetNested<N> extends TernaryFluent<TernaryTargetNested<N>> implements Nested<N> {
    TernaryTargetNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endTernaryTarget() {
      return and();
    }

  }

  public class BinaryExpressionTargetNested<N> extends BinaryExpressionFluent<BinaryExpressionTargetNested<N>>
      implements Nested<N> {
    BinaryExpressionTargetNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endBinaryExpressionTarget() {
      return and();
    }

  }

  public class EqualsTargetNested<N> extends EqualsFluent<EqualsTargetNested<N>> implements Nested<N> {
    EqualsTargetNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endEqualsTarget() {
      return and();
    }

  }

  public class EnclosedTargetNested<N> extends EnclosedFluent<EnclosedTargetNested<N>> implements Nested<N> {
    EnclosedTargetNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endEnclosedTarget() {
      return and();
    }

  }

  public class PreDecrementTargetNested<N> extends PreDecrementFluent<PreDecrementTargetNested<N>> implements Nested<N> {
    PreDecrementTargetNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPreDecrementTarget() {
      return and();
    }

  }

  public class PostDecrementTargetNested<N> extends PostDecrementFluent<PostDecrementTargetNested<N>> implements Nested<N> {
    PostDecrementTargetNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPostDecrementTarget() {
      return and();
    }

  }

  public class NotTargetNested<N> extends NotFluent<NotTargetNested<N>> implements Nested<N> {
    NotTargetNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNotTarget() {
      return and();
    }

  }

  public class AssignTargetNested<N> extends AssignFluent<AssignTargetNested<N>> implements Nested<N> {
    AssignTargetNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endAssignTarget() {
      return and();
    }

  }

  public class NegativeTargetNested<N> extends NegativeFluent<NegativeTargetNested<N>> implements Nested<N> {
    NegativeTargetNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNegativeTarget() {
      return and();
    }

  }

  public class ThisTargetNested<N> extends ThisFluent<ThisTargetNested<N>> implements Nested<N> {
    ThisTargetNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endThisTarget() {
      return and();
    }

  }

  public class LogicalAndTargetNested<N> extends LogicalAndFluent<LogicalAndTargetNested<N>> implements Nested<N> {
    LogicalAndTargetNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLogicalAndTarget() {
      return and();
    }

  }

  public class PostIncrementTargetNested<N> extends PostIncrementFluent<PostIncrementTargetNested<N>> implements Nested<N> {
    PostIncrementTargetNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPostIncrementTarget() {
      return and();
    }

  }

  public class RightUnsignedShiftTargetNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftTargetNested<N>>
      implements Nested<N> {
    RightUnsignedShiftTargetNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endRightUnsignedShiftTarget() {
      return and();
    }

  }

  public class PlusTargetNested<N> extends PlusFluent<PlusTargetNested<N>> implements Nested<N> {
    PlusTargetNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPlusTarget() {
      return and();
    }

  }

  public class ConstructTargetNested<N> extends ConstructFluent<ConstructTargetNested<N>> implements Nested<N> {
    ConstructTargetNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endConstructTarget() {
      return and();
    }

  }

  public class XorTargetNested<N> extends XorFluent<XorTargetNested<N>> implements Nested<N> {
    XorTargetNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endXorTarget() {
      return and();
    }

  }

  public class PreIncrementTargetNested<N> extends PreIncrementFluent<PreIncrementTargetNested<N>> implements Nested<N> {
    PreIncrementTargetNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPreIncrementTarget() {
      return and();
    }

  }

  public class LessThanOrEqualTargetNested<N> extends LessThanOrEqualFluent<LessThanOrEqualTargetNested<N>>
      implements Nested<N> {
    LessThanOrEqualTargetNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLessThanOrEqualTarget() {
      return and();
    }

  }

  public class PositiveTargetNested<N> extends PositiveFluent<PositiveTargetNested<N>> implements Nested<N> {
    PositiveTargetNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPositiveTarget() {
      return and();
    }

  }

  public class MultiplyValueNested<N> extends MultiplyFluent<MultiplyValueNested<N>> implements Nested<N> {
    MultiplyValueNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endMultiplyValue() {
      return and();
    }

  }

  public class MethodCallValueNested<N> extends MethodCallFluent<MethodCallValueNested<N>> implements Nested<N> {
    MethodCallValueNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endMethodCallValue() {
      return and();
    }

  }

  public class InverseValueNested<N> extends InverseFluent<InverseValueNested<N>> implements Nested<N> {
    InverseValueNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    InverseBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endInverseValue() {
      return and();
    }

  }

  public class GreaterThanOrEqualValueNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualValueNested<N>>
      implements Nested<N> {
    GreaterThanOrEqualValueNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    GreaterThanOrEqualBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endGreaterThanOrEqualValue() {
      return and();
    }

  }

  public class BitwiseAndValueNested<N> extends BitwiseAndFluent<BitwiseAndValueNested<N>> implements Nested<N> {
    BitwiseAndValueNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    BitwiseAndBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endBitwiseAndValue() {
      return and();
    }

  }

  public class MinusValueNested<N> extends MinusFluent<MinusValueNested<N>> implements Nested<N> {
    MinusValueNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    MinusBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endMinusValue() {
      return and();
    }

  }

  public class LogicalOrValueNested<N> extends LogicalOrFluent<LogicalOrValueNested<N>> implements Nested<N> {
    LogicalOrValueNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    LogicalOrBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLogicalOrValue() {
      return and();
    }

  }

  public class NotEqualsValueNested<N> extends NotEqualsFluent<NotEqualsValueNested<N>> implements Nested<N> {
    NotEqualsValueNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    NotEqualsBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNotEqualsValue() {
      return and();
    }

  }

  public class DivideValueNested<N> extends DivideFluent<DivideValueNested<N>> implements Nested<N> {
    DivideValueNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    DivideBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endDivideValue() {
      return and();
    }

  }

  public class LessThanValueNested<N> extends LessThanFluent<LessThanValueNested<N>> implements Nested<N> {
    LessThanValueNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    LessThanBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLessThanValue() {
      return and();
    }

  }

  public class BitwiseOrValueNested<N> extends BitwiseOrFluent<BitwiseOrValueNested<N>> implements Nested<N> {
    BitwiseOrValueNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    BitwiseOrBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endBitwiseOrValue() {
      return and();
    }

  }

  public class PropertyRefValueNested<N> extends PropertyRefFluent<PropertyRefValueNested<N>> implements Nested<N> {
    PropertyRefValueNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    PropertyRefBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPropertyRefValue() {
      return and();
    }

  }

  public class RightShiftValueNested<N> extends RightShiftFluent<RightShiftValueNested<N>> implements Nested<N> {
    RightShiftValueNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    RightShiftBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endRightShiftValue() {
      return and();
    }

  }

  public class GreaterThanValueNested<N> extends GreaterThanFluent<GreaterThanValueNested<N>> implements Nested<N> {
    GreaterThanValueNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    GreaterThanBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endGreaterThanValue() {
      return and();
    }

  }

  public class ModuloValueNested<N> extends ModuloFluent<ModuloValueNested<N>> implements Nested<N> {
    ModuloValueNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    ModuloBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endModuloValue() {
      return and();
    }

  }

  public class ValueRefValueNested<N> extends ValueRefFluent<ValueRefValueNested<N>> implements Nested<N> {
    ValueRefValueNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    ValueRefBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endValueRefValue() {
      return and();
    }

  }

  public class LeftShiftValueNested<N> extends LeftShiftFluent<LeftShiftValueNested<N>> implements Nested<N> {
    LeftShiftValueNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    LeftShiftBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLeftShiftValue() {
      return and();
    }

  }

  public class TernaryValueNested<N> extends TernaryFluent<TernaryValueNested<N>> implements Nested<N> {
    TernaryValueNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    TernaryBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endTernaryValue() {
      return and();
    }

  }

  public class BinaryExpressionValueNested<N> extends BinaryExpressionFluent<BinaryExpressionValueNested<N>>
      implements Nested<N> {
    BinaryExpressionValueNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    BinaryExpressionBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endBinaryExpressionValue() {
      return and();
    }

  }

  public class EqualsValueNested<N> extends EqualsFluent<EqualsValueNested<N>> implements Nested<N> {
    EqualsValueNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    EqualsBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endEqualsValue() {
      return and();
    }

  }

  public class EnclosedValueNested<N> extends EnclosedFluent<EnclosedValueNested<N>> implements Nested<N> {
    EnclosedValueNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    EnclosedBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endEnclosedValue() {
      return and();
    }

  }

  public class PreDecrementValueNested<N> extends PreDecrementFluent<PreDecrementValueNested<N>> implements Nested<N> {
    PreDecrementValueNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    PreDecrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPreDecrementValue() {
      return and();
    }

  }

  public class PostDecrementValueNested<N> extends PostDecrementFluent<PostDecrementValueNested<N>> implements Nested<N> {
    PostDecrementValueNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    PostDecrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPostDecrementValue() {
      return and();
    }

  }

  public class NotValueNested<N> extends NotFluent<NotValueNested<N>> implements Nested<N> {
    NotValueNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    NotBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNotValue() {
      return and();
    }

  }

  public class AssignValueNested<N> extends AssignFluent<AssignValueNested<N>> implements Nested<N> {
    AssignValueNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endAssignValue() {
      return and();
    }

  }

  public class NegativeValueNested<N> extends NegativeFluent<NegativeValueNested<N>> implements Nested<N> {
    NegativeValueNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNegativeValue() {
      return and();
    }

  }

  public class ThisValueNested<N> extends ThisFluent<ThisValueNested<N>> implements Nested<N> {
    ThisValueNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endThisValue() {
      return and();
    }

  }

  public class LogicalAndValueNested<N> extends LogicalAndFluent<LogicalAndValueNested<N>> implements Nested<N> {
    LogicalAndValueNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLogicalAndValue() {
      return and();
    }

  }

  public class PostIncrementValueNested<N> extends PostIncrementFluent<PostIncrementValueNested<N>> implements Nested<N> {
    PostIncrementValueNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    PostIncrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPostIncrementValue() {
      return and();
    }

  }

  public class RightUnsignedShiftValueNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftValueNested<N>>
      implements Nested<N> {
    RightUnsignedShiftValueNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    RightUnsignedShiftBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endRightUnsignedShiftValue() {
      return and();
    }

  }

  public class PlusValueNested<N> extends PlusFluent<PlusValueNested<N>> implements Nested<N> {
    PlusValueNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    PlusBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPlusValue() {
      return and();
    }

  }

  public class ConstructValueNested<N> extends ConstructFluent<ConstructValueNested<N>> implements Nested<N> {
    ConstructValueNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    ConstructBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endConstructValue() {
      return and();
    }

  }

  public class XorValueNested<N> extends XorFluent<XorValueNested<N>> implements Nested<N> {
    XorValueNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    XorBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endXorValue() {
      return and();
    }

  }

  public class PreIncrementValueNested<N> extends PreIncrementFluent<PreIncrementValueNested<N>> implements Nested<N> {
    PreIncrementValueNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    PreIncrementBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPreIncrementValue() {
      return and();
    }

  }

  public class LessThanOrEqualValueNested<N> extends LessThanOrEqualFluent<LessThanOrEqualValueNested<N>> implements Nested<N> {
    LessThanOrEqualValueNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    LessThanOrEqualBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLessThanOrEqualValue() {
      return and();
    }

  }

  public class PositiveValueNested<N> extends PositiveFluent<PositiveValueNested<N>> implements Nested<N> {
    PositiveValueNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    PositiveBuilder builder;

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPositiveValue() {
      return and();
    }

  }

}
