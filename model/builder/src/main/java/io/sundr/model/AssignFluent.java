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
public class AssignFluent<A extends AssignFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> target;
  private VisitableBuilder<? extends Expression, ?> value;

  public AssignFluent() {
  }

  public AssignFluent(Assign instance) {
    this.copyInstance(instance);
  }

  public Expression buildTarget() {
    return this.target != null ? this.target.build() : null;
  }

  public Expression buildValue() {
    return this.value != null ? this.value.build() : null;
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

  protected void copyInstance(Assign instance) {
    if (instance != null) {
      this.withTarget(instance.getTarget());
      this.withValue(instance.getValue());
    }
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

  public boolean hasTarget() {
    return this.target != null;
  }

  public boolean hasValue() {
    return this.value != null;
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

  public AssignTargetNested<A> withNewAssignTarget() {
    return new AssignTargetNested(null);
  }

  public AssignTargetNested<A> withNewAssignTargetLike(Assign item) {
    return new AssignTargetNested(item);
  }

  public AssignValueNested<A> withNewAssignValue() {
    return new AssignValueNested(null);
  }

  public AssignValueNested<A> withNewAssignValueLike(Assign item) {
    return new AssignValueNested(item);
  }

  public BinaryExpressionTargetNested<A> withNewBinaryExpressionTarget() {
    return new BinaryExpressionTargetNested(null);
  }

  public BinaryExpressionTargetNested<A> withNewBinaryExpressionTargetLike(BinaryExpression item) {
    return new BinaryExpressionTargetNested(item);
  }

  public BinaryExpressionValueNested<A> withNewBinaryExpressionValue() {
    return new BinaryExpressionValueNested(null);
  }

  public BinaryExpressionValueNested<A> withNewBinaryExpressionValueLike(BinaryExpression item) {
    return new BinaryExpressionValueNested(item);
  }

  public BitwiseAndTargetNested<A> withNewBitwiseAndTarget() {
    return new BitwiseAndTargetNested(null);
  }

  public A withNewBitwiseAndTarget(Object left, Object right) {
    return (A) withTarget(new BitwiseAnd(left, right));
  }

  public BitwiseAndTargetNested<A> withNewBitwiseAndTargetLike(BitwiseAnd item) {
    return new BitwiseAndTargetNested(item);
  }

  public BitwiseAndValueNested<A> withNewBitwiseAndValue() {
    return new BitwiseAndValueNested(null);
  }

  public A withNewBitwiseAndValue(Object left, Object right) {
    return (A) withValue(new BitwiseAnd(left, right));
  }

  public BitwiseAndValueNested<A> withNewBitwiseAndValueLike(BitwiseAnd item) {
    return new BitwiseAndValueNested(item);
  }

  public BitwiseOrTargetNested<A> withNewBitwiseOrTarget() {
    return new BitwiseOrTargetNested(null);
  }

  public A withNewBitwiseOrTarget(Object left, Object right) {
    return (A) withTarget(new BitwiseOr(left, right));
  }

  public BitwiseOrTargetNested<A> withNewBitwiseOrTargetLike(BitwiseOr item) {
    return new BitwiseOrTargetNested(item);
  }

  public BitwiseOrValueNested<A> withNewBitwiseOrValue() {
    return new BitwiseOrValueNested(null);
  }

  public A withNewBitwiseOrValue(Object left, Object right) {
    return (A) withValue(new BitwiseOr(left, right));
  }

  public BitwiseOrValueNested<A> withNewBitwiseOrValueLike(BitwiseOr item) {
    return new BitwiseOrValueNested(item);
  }

  public CastTargetNested<A> withNewCastTarget() {
    return new CastTargetNested(null);
  }

  public CastTargetNested<A> withNewCastTargetLike(Cast item) {
    return new CastTargetNested(item);
  }

  public CastValueNested<A> withNewCastValue() {
    return new CastValueNested(null);
  }

  public CastValueNested<A> withNewCastValueLike(Cast item) {
    return new CastValueNested(item);
  }

  public ClassRefTargetNested<A> withNewClassRefTarget() {
    return new ClassRefTargetNested(null);
  }

  public ClassRefTargetNested<A> withNewClassRefTargetLike(ClassRef item) {
    return new ClassRefTargetNested(item);
  }

  public ClassRefValueNested<A> withNewClassRefValue() {
    return new ClassRefValueNested(null);
  }

  public ClassRefValueNested<A> withNewClassRefValueLike(ClassRef item) {
    return new ClassRefValueNested(item);
  }

  public ConstructTargetNested<A> withNewConstructTarget() {
    return new ConstructTargetNested(null);
  }

  public ConstructTargetNested<A> withNewConstructTargetLike(Construct item) {
    return new ConstructTargetNested(item);
  }

  public ConstructValueNested<A> withNewConstructValue() {
    return new ConstructValueNested(null);
  }

  public ConstructValueNested<A> withNewConstructValueLike(Construct item) {
    return new ConstructValueNested(item);
  }

  public ContextRefTargetNested<A> withNewContextRefTarget() {
    return new ContextRefTargetNested(null);
  }

  public A withNewContextRefTarget(String name) {
    return (A) withTarget(new ContextRef(name));
  }

  public ContextRefTargetNested<A> withNewContextRefTargetLike(ContextRef item) {
    return new ContextRefTargetNested(item);
  }

  public ContextRefValueNested<A> withNewContextRefValue() {
    return new ContextRefValueNested(null);
  }

  public A withNewContextRefValue(String name) {
    return (A) withValue(new ContextRef(name));
  }

  public ContextRefValueNested<A> withNewContextRefValueLike(ContextRef item) {
    return new ContextRefValueNested(item);
  }

  public DeclareTargetNested<A> withNewDeclareTarget() {
    return new DeclareTargetNested(null);
  }

  public A withNewDeclareTarget(Class type, String name) {
    return (A) withTarget(new Declare(type, name));
  }

  public A withNewDeclareTarget(Class type, String name, Object value) {
    return (A) withTarget(new Declare(type, name, value));
  }

  public DeclareTargetNested<A> withNewDeclareTargetLike(Declare item) {
    return new DeclareTargetNested(item);
  }

  public DeclareValueNested<A> withNewDeclareValue() {
    return new DeclareValueNested(null);
  }

  public A withNewDeclareValue(Class type, String name) {
    return (A) withValue(new Declare(type, name));
  }

  public A withNewDeclareValue(Class type, String name, Object value) {
    return (A) withValue(new Declare(type, name, value));
  }

  public DeclareValueNested<A> withNewDeclareValueLike(Declare item) {
    return new DeclareValueNested(item);
  }

  public DivideTargetNested<A> withNewDivideTarget() {
    return new DivideTargetNested(null);
  }

  public A withNewDivideTarget(Object left, Object right) {
    return (A) withTarget(new Divide(left, right));
  }

  public DivideTargetNested<A> withNewDivideTargetLike(Divide item) {
    return new DivideTargetNested(item);
  }

  public DivideValueNested<A> withNewDivideValue() {
    return new DivideValueNested(null);
  }

  public A withNewDivideValue(Object left, Object right) {
    return (A) withValue(new Divide(left, right));
  }

  public DivideValueNested<A> withNewDivideValueLike(Divide item) {
    return new DivideValueNested(item);
  }

  public DotClassTargetNested<A> withNewDotClassTarget() {
    return new DotClassTargetNested(null);
  }

  public DotClassTargetNested<A> withNewDotClassTargetLike(DotClass item) {
    return new DotClassTargetNested(item);
  }

  public DotClassValueNested<A> withNewDotClassValue() {
    return new DotClassValueNested(null);
  }

  public DotClassValueNested<A> withNewDotClassValueLike(DotClass item) {
    return new DotClassValueNested(item);
  }

  public EmptyTargetNested<A> withNewEmptyTarget() {
    return new EmptyTargetNested(null);
  }

  public EmptyTargetNested<A> withNewEmptyTargetLike(Empty item) {
    return new EmptyTargetNested(item);
  }

  public EmptyValueNested<A> withNewEmptyValue() {
    return new EmptyValueNested(null);
  }

  public EmptyValueNested<A> withNewEmptyValueLike(Empty item) {
    return new EmptyValueNested(item);
  }

  public EnclosedTargetNested<A> withNewEnclosedTarget() {
    return new EnclosedTargetNested(null);
  }

  public EnclosedTargetNested<A> withNewEnclosedTargetLike(Enclosed item) {
    return new EnclosedTargetNested(item);
  }

  public EnclosedValueNested<A> withNewEnclosedValue() {
    return new EnclosedValueNested(null);
  }

  public EnclosedValueNested<A> withNewEnclosedValueLike(Enclosed item) {
    return new EnclosedValueNested(item);
  }

  public EqualsTargetNested<A> withNewEqualsTarget() {
    return new EqualsTargetNested(null);
  }

  public A withNewEqualsTarget(Object left, Object right) {
    return (A) withTarget(new Equals(left, right));
  }

  public EqualsTargetNested<A> withNewEqualsTargetLike(Equals item) {
    return new EqualsTargetNested(item);
  }

  public EqualsValueNested<A> withNewEqualsValue() {
    return new EqualsValueNested(null);
  }

  public A withNewEqualsValue(Object left, Object right) {
    return (A) withValue(new Equals(left, right));
  }

  public EqualsValueNested<A> withNewEqualsValueLike(Equals item) {
    return new EqualsValueNested(item);
  }

  public GreaterThanOrEqualTargetNested<A> withNewGreaterThanOrEqualTarget() {
    return new GreaterThanOrEqualTargetNested(null);
  }

  public A withNewGreaterThanOrEqualTarget(Object left, Object right) {
    return (A) withTarget(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualTargetNested<A> withNewGreaterThanOrEqualTargetLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualTargetNested(item);
  }

  public GreaterThanOrEqualValueNested<A> withNewGreaterThanOrEqualValue() {
    return new GreaterThanOrEqualValueNested(null);
  }

  public A withNewGreaterThanOrEqualValue(Object left, Object right) {
    return (A) withValue(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualValueNested<A> withNewGreaterThanOrEqualValueLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualValueNested(item);
  }

  public GreaterThanTargetNested<A> withNewGreaterThanTarget() {
    return new GreaterThanTargetNested(null);
  }

  public A withNewGreaterThanTarget(Object left, Object right) {
    return (A) withTarget(new GreaterThan(left, right));
  }

  public GreaterThanTargetNested<A> withNewGreaterThanTargetLike(GreaterThan item) {
    return new GreaterThanTargetNested(item);
  }

  public GreaterThanValueNested<A> withNewGreaterThanValue() {
    return new GreaterThanValueNested(null);
  }

  public A withNewGreaterThanValue(Object left, Object right) {
    return (A) withValue(new GreaterThan(left, right));
  }

  public GreaterThanValueNested<A> withNewGreaterThanValueLike(GreaterThan item) {
    return new GreaterThanValueNested(item);
  }

  public IndexTargetNested<A> withNewIndexTarget() {
    return new IndexTargetNested(null);
  }

  public IndexTargetNested<A> withNewIndexTargetLike(Index item) {
    return new IndexTargetNested(item);
  }

  public IndexValueNested<A> withNewIndexValue() {
    return new IndexValueNested(null);
  }

  public IndexValueNested<A> withNewIndexValueLike(Index item) {
    return new IndexValueNested(item);
  }

  public InstanceOfTargetNested<A> withNewInstanceOfTarget() {
    return new InstanceOfTargetNested(null);
  }

  public InstanceOfTargetNested<A> withNewInstanceOfTargetLike(InstanceOf item) {
    return new InstanceOfTargetNested(item);
  }

  public InstanceOfValueNested<A> withNewInstanceOfValue() {
    return new InstanceOfValueNested(null);
  }

  public InstanceOfValueNested<A> withNewInstanceOfValueLike(InstanceOf item) {
    return new InstanceOfValueNested(item);
  }

  public InverseTargetNested<A> withNewInverseTarget() {
    return new InverseTargetNested(null);
  }

  public InverseTargetNested<A> withNewInverseTargetLike(Inverse item) {
    return new InverseTargetNested(item);
  }

  public InverseValueNested<A> withNewInverseValue() {
    return new InverseValueNested(null);
  }

  public InverseValueNested<A> withNewInverseValueLike(Inverse item) {
    return new InverseValueNested(item);
  }

  public LambdaTargetNested<A> withNewLambdaTarget() {
    return new LambdaTargetNested(null);
  }

  public LambdaTargetNested<A> withNewLambdaTargetLike(Lambda item) {
    return new LambdaTargetNested(item);
  }

  public LambdaValueNested<A> withNewLambdaValue() {
    return new LambdaValueNested(null);
  }

  public LambdaValueNested<A> withNewLambdaValueLike(Lambda item) {
    return new LambdaValueNested(item);
  }

  public LeftShiftTargetNested<A> withNewLeftShiftTarget() {
    return new LeftShiftTargetNested(null);
  }

  public A withNewLeftShiftTarget(Object left, Object right) {
    return (A) withTarget(new LeftShift(left, right));
  }

  public LeftShiftTargetNested<A> withNewLeftShiftTargetLike(LeftShift item) {
    return new LeftShiftTargetNested(item);
  }

  public LeftShiftValueNested<A> withNewLeftShiftValue() {
    return new LeftShiftValueNested(null);
  }

  public A withNewLeftShiftValue(Object left, Object right) {
    return (A) withValue(new LeftShift(left, right));
  }

  public LeftShiftValueNested<A> withNewLeftShiftValueLike(LeftShift item) {
    return new LeftShiftValueNested(item);
  }

  public LessThanOrEqualTargetNested<A> withNewLessThanOrEqualTarget() {
    return new LessThanOrEqualTargetNested(null);
  }

  public A withNewLessThanOrEqualTarget(Object left, Object right) {
    return (A) withTarget(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualTargetNested<A> withNewLessThanOrEqualTargetLike(LessThanOrEqual item) {
    return new LessThanOrEqualTargetNested(item);
  }

  public LessThanOrEqualValueNested<A> withNewLessThanOrEqualValue() {
    return new LessThanOrEqualValueNested(null);
  }

  public A withNewLessThanOrEqualValue(Object left, Object right) {
    return (A) withValue(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualValueNested<A> withNewLessThanOrEqualValueLike(LessThanOrEqual item) {
    return new LessThanOrEqualValueNested(item);
  }

  public LessThanTargetNested<A> withNewLessThanTarget() {
    return new LessThanTargetNested(null);
  }

  public A withNewLessThanTarget(Object left, Object right) {
    return (A) withTarget(new LessThan(left, right));
  }

  public LessThanTargetNested<A> withNewLessThanTargetLike(LessThan item) {
    return new LessThanTargetNested(item);
  }

  public LessThanValueNested<A> withNewLessThanValue() {
    return new LessThanValueNested(null);
  }

  public A withNewLessThanValue(Object left, Object right) {
    return (A) withValue(new LessThan(left, right));
  }

  public LessThanValueNested<A> withNewLessThanValueLike(LessThan item) {
    return new LessThanValueNested(item);
  }

  public LogicalAndTargetNested<A> withNewLogicalAndTarget() {
    return new LogicalAndTargetNested(null);
  }

  public A withNewLogicalAndTarget(Object left, Object right) {
    return (A) withTarget(new LogicalAnd(left, right));
  }

  public LogicalAndTargetNested<A> withNewLogicalAndTargetLike(LogicalAnd item) {
    return new LogicalAndTargetNested(item);
  }

  public LogicalAndValueNested<A> withNewLogicalAndValue() {
    return new LogicalAndValueNested(null);
  }

  public A withNewLogicalAndValue(Object left, Object right) {
    return (A) withValue(new LogicalAnd(left, right));
  }

  public LogicalAndValueNested<A> withNewLogicalAndValueLike(LogicalAnd item) {
    return new LogicalAndValueNested(item);
  }

  public LogicalOrTargetNested<A> withNewLogicalOrTarget() {
    return new LogicalOrTargetNested(null);
  }

  public A withNewLogicalOrTarget(Object left, Object right) {
    return (A) withTarget(new LogicalOr(left, right));
  }

  public LogicalOrTargetNested<A> withNewLogicalOrTargetLike(LogicalOr item) {
    return new LogicalOrTargetNested(item);
  }

  public LogicalOrValueNested<A> withNewLogicalOrValue() {
    return new LogicalOrValueNested(null);
  }

  public A withNewLogicalOrValue(Object left, Object right) {
    return (A) withValue(new LogicalOr(left, right));
  }

  public LogicalOrValueNested<A> withNewLogicalOrValueLike(LogicalOr item) {
    return new LogicalOrValueNested(item);
  }

  public MethodCallTargetNested<A> withNewMethodCallTarget() {
    return new MethodCallTargetNested(null);
  }

  public MethodCallTargetNested<A> withNewMethodCallTargetLike(MethodCall item) {
    return new MethodCallTargetNested(item);
  }

  public MethodCallValueNested<A> withNewMethodCallValue() {
    return new MethodCallValueNested(null);
  }

  public MethodCallValueNested<A> withNewMethodCallValueLike(MethodCall item) {
    return new MethodCallValueNested(item);
  }

  public MinusTargetNested<A> withNewMinusTarget() {
    return new MinusTargetNested(null);
  }

  public A withNewMinusTarget(Object left, Object right) {
    return (A) withTarget(new Minus(left, right));
  }

  public MinusTargetNested<A> withNewMinusTargetLike(Minus item) {
    return new MinusTargetNested(item);
  }

  public MinusValueNested<A> withNewMinusValue() {
    return new MinusValueNested(null);
  }

  public A withNewMinusValue(Object left, Object right) {
    return (A) withValue(new Minus(left, right));
  }

  public MinusValueNested<A> withNewMinusValueLike(Minus item) {
    return new MinusValueNested(item);
  }

  public ModuloTargetNested<A> withNewModuloTarget() {
    return new ModuloTargetNested(null);
  }

  public A withNewModuloTarget(Object left, Object right) {
    return (A) withTarget(new Modulo(left, right));
  }

  public ModuloTargetNested<A> withNewModuloTargetLike(Modulo item) {
    return new ModuloTargetNested(item);
  }

  public ModuloValueNested<A> withNewModuloValue() {
    return new ModuloValueNested(null);
  }

  public A withNewModuloValue(Object left, Object right) {
    return (A) withValue(new Modulo(left, right));
  }

  public ModuloValueNested<A> withNewModuloValueLike(Modulo item) {
    return new ModuloValueNested(item);
  }

  public MultiplyTargetNested<A> withNewMultiplyTarget() {
    return new MultiplyTargetNested(null);
  }

  public A withNewMultiplyTarget(Object left, Object right) {
    return (A) withTarget(new Multiply(left, right));
  }

  public MultiplyTargetNested<A> withNewMultiplyTargetLike(Multiply item) {
    return new MultiplyTargetNested(item);
  }

  public MultiplyValueNested<A> withNewMultiplyValue() {
    return new MultiplyValueNested(null);
  }

  public A withNewMultiplyValue(Object left, Object right) {
    return (A) withValue(new Multiply(left, right));
  }

  public MultiplyValueNested<A> withNewMultiplyValueLike(Multiply item) {
    return new MultiplyValueNested(item);
  }

  public NegativeTargetNested<A> withNewNegativeTarget() {
    return new NegativeTargetNested(null);
  }

  public NegativeTargetNested<A> withNewNegativeTargetLike(Negative item) {
    return new NegativeTargetNested(item);
  }

  public NegativeValueNested<A> withNewNegativeValue() {
    return new NegativeValueNested(null);
  }

  public NegativeValueNested<A> withNewNegativeValueLike(Negative item) {
    return new NegativeValueNested(item);
  }

  public NewArrayTargetNested<A> withNewNewArrayTarget() {
    return new NewArrayTargetNested(null);
  }

  public A withNewNewArrayTarget(Class type, Integer[] sizes) {
    return (A) withTarget(new NewArray(type, sizes));
  }

  public NewArrayTargetNested<A> withNewNewArrayTargetLike(NewArray item) {
    return new NewArrayTargetNested(item);
  }

  public NewArrayValueNested<A> withNewNewArrayValue() {
    return new NewArrayValueNested(null);
  }

  public A withNewNewArrayValue(Class type, Integer[] sizes) {
    return (A) withValue(new NewArray(type, sizes));
  }

  public NewArrayValueNested<A> withNewNewArrayValueLike(NewArray item) {
    return new NewArrayValueNested(item);
  }

  public NotEqualsTargetNested<A> withNewNotEqualsTarget() {
    return new NotEqualsTargetNested(null);
  }

  public A withNewNotEqualsTarget(Object left, Object right) {
    return (A) withTarget(new NotEquals(left, right));
  }

  public NotEqualsTargetNested<A> withNewNotEqualsTargetLike(NotEquals item) {
    return new NotEqualsTargetNested(item);
  }

  public NotEqualsValueNested<A> withNewNotEqualsValue() {
    return new NotEqualsValueNested(null);
  }

  public A withNewNotEqualsValue(Object left, Object right) {
    return (A) withValue(new NotEquals(left, right));
  }

  public NotEqualsValueNested<A> withNewNotEqualsValueLike(NotEquals item) {
    return new NotEqualsValueNested(item);
  }

  public NotTargetNested<A> withNewNotTarget() {
    return new NotTargetNested(null);
  }

  public NotTargetNested<A> withNewNotTargetLike(Not item) {
    return new NotTargetNested(item);
  }

  public NotValueNested<A> withNewNotValue() {
    return new NotValueNested(null);
  }

  public NotValueNested<A> withNewNotValueLike(Not item) {
    return new NotValueNested(item);
  }

  public PlusTargetNested<A> withNewPlusTarget() {
    return new PlusTargetNested(null);
  }

  public A withNewPlusTarget(Object left, Object right) {
    return (A) withTarget(new Plus(left, right));
  }

  public PlusTargetNested<A> withNewPlusTargetLike(Plus item) {
    return new PlusTargetNested(item);
  }

  public PlusValueNested<A> withNewPlusValue() {
    return new PlusValueNested(null);
  }

  public A withNewPlusValue(Object left, Object right) {
    return (A) withValue(new Plus(left, right));
  }

  public PlusValueNested<A> withNewPlusValueLike(Plus item) {
    return new PlusValueNested(item);
  }

  public PositiveTargetNested<A> withNewPositiveTarget() {
    return new PositiveTargetNested(null);
  }

  public PositiveTargetNested<A> withNewPositiveTargetLike(Positive item) {
    return new PositiveTargetNested(item);
  }

  public PositiveValueNested<A> withNewPositiveValue() {
    return new PositiveValueNested(null);
  }

  public PositiveValueNested<A> withNewPositiveValueLike(Positive item) {
    return new PositiveValueNested(item);
  }

  public PostDecrementTargetNested<A> withNewPostDecrementTarget() {
    return new PostDecrementTargetNested(null);
  }

  public PostDecrementTargetNested<A> withNewPostDecrementTargetLike(PostDecrement item) {
    return new PostDecrementTargetNested(item);
  }

  public PostDecrementValueNested<A> withNewPostDecrementValue() {
    return new PostDecrementValueNested(null);
  }

  public PostDecrementValueNested<A> withNewPostDecrementValueLike(PostDecrement item) {
    return new PostDecrementValueNested(item);
  }

  public PostIncrementTargetNested<A> withNewPostIncrementTarget() {
    return new PostIncrementTargetNested(null);
  }

  public PostIncrementTargetNested<A> withNewPostIncrementTargetLike(PostIncrement item) {
    return new PostIncrementTargetNested(item);
  }

  public PostIncrementValueNested<A> withNewPostIncrementValue() {
    return new PostIncrementValueNested(null);
  }

  public PostIncrementValueNested<A> withNewPostIncrementValueLike(PostIncrement item) {
    return new PostIncrementValueNested(item);
  }

  public PreDecrementTargetNested<A> withNewPreDecrementTarget() {
    return new PreDecrementTargetNested(null);
  }

  public PreDecrementTargetNested<A> withNewPreDecrementTargetLike(PreDecrement item) {
    return new PreDecrementTargetNested(item);
  }

  public PreDecrementValueNested<A> withNewPreDecrementValue() {
    return new PreDecrementValueNested(null);
  }

  public PreDecrementValueNested<A> withNewPreDecrementValueLike(PreDecrement item) {
    return new PreDecrementValueNested(item);
  }

  public PreIncrementTargetNested<A> withNewPreIncrementTarget() {
    return new PreIncrementTargetNested(null);
  }

  public PreIncrementTargetNested<A> withNewPreIncrementTargetLike(PreIncrement item) {
    return new PreIncrementTargetNested(item);
  }

  public PreIncrementValueNested<A> withNewPreIncrementValue() {
    return new PreIncrementValueNested(null);
  }

  public PreIncrementValueNested<A> withNewPreIncrementValueLike(PreIncrement item) {
    return new PreIncrementValueNested(item);
  }

  public PropertyRefTargetNested<A> withNewPropertyRefTarget() {
    return new PropertyRefTargetNested(null);
  }

  public PropertyRefTargetNested<A> withNewPropertyRefTargetLike(PropertyRef item) {
    return new PropertyRefTargetNested(item);
  }

  public PropertyRefValueNested<A> withNewPropertyRefValue() {
    return new PropertyRefValueNested(null);
  }

  public PropertyRefValueNested<A> withNewPropertyRefValueLike(PropertyRef item) {
    return new PropertyRefValueNested(item);
  }

  public PropertyTargetNested<A> withNewPropertyTarget() {
    return new PropertyTargetNested(null);
  }

  public PropertyTargetNested<A> withNewPropertyTargetLike(Property item) {
    return new PropertyTargetNested(item);
  }

  public PropertyValueNested<A> withNewPropertyValue() {
    return new PropertyValueNested(null);
  }

  public PropertyValueNested<A> withNewPropertyValueLike(Property item) {
    return new PropertyValueNested(item);
  }

  public RightShiftTargetNested<A> withNewRightShiftTarget() {
    return new RightShiftTargetNested(null);
  }

  public A withNewRightShiftTarget(Object left, Object right) {
    return (A) withTarget(new RightShift(left, right));
  }

  public RightShiftTargetNested<A> withNewRightShiftTargetLike(RightShift item) {
    return new RightShiftTargetNested(item);
  }

  public RightShiftValueNested<A> withNewRightShiftValue() {
    return new RightShiftValueNested(null);
  }

  public A withNewRightShiftValue(Object left, Object right) {
    return (A) withValue(new RightShift(left, right));
  }

  public RightShiftValueNested<A> withNewRightShiftValueLike(RightShift item) {
    return new RightShiftValueNested(item);
  }

  public RightUnsignedShiftTargetNested<A> withNewRightUnsignedShiftTarget() {
    return new RightUnsignedShiftTargetNested(null);
  }

  public A withNewRightUnsignedShiftTarget(Object left, Object right) {
    return (A) withTarget(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftTargetNested<A> withNewRightUnsignedShiftTargetLike(RightUnsignedShift item) {
    return new RightUnsignedShiftTargetNested(item);
  }

  public RightUnsignedShiftValueNested<A> withNewRightUnsignedShiftValue() {
    return new RightUnsignedShiftValueNested(null);
  }

  public A withNewRightUnsignedShiftValue(Object left, Object right) {
    return (A) withValue(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftValueNested<A> withNewRightUnsignedShiftValueLike(RightUnsignedShift item) {
    return new RightUnsignedShiftValueNested(item);
  }

  public SuperTargetNested<A> withNewSuperTarget() {
    return new SuperTargetNested(null);
  }

  public SuperTargetNested<A> withNewSuperTargetLike(Super item) {
    return new SuperTargetNested(item);
  }

  public SuperValueNested<A> withNewSuperValue() {
    return new SuperValueNested(null);
  }

  public SuperValueNested<A> withNewSuperValueLike(Super item) {
    return new SuperValueNested(item);
  }

  public TernaryTargetNested<A> withNewTernaryTarget() {
    return new TernaryTargetNested(null);
  }

  public TernaryTargetNested<A> withNewTernaryTargetLike(Ternary item) {
    return new TernaryTargetNested(item);
  }

  public TernaryValueNested<A> withNewTernaryValue() {
    return new TernaryValueNested(null);
  }

  public TernaryValueNested<A> withNewTernaryValueLike(Ternary item) {
    return new TernaryValueNested(item);
  }

  public ThisTargetNested<A> withNewThisTarget() {
    return new ThisTargetNested(null);
  }

  public ThisTargetNested<A> withNewThisTargetLike(This item) {
    return new ThisTargetNested(item);
  }

  public ThisValueNested<A> withNewThisValue() {
    return new ThisValueNested(null);
  }

  public ThisValueNested<A> withNewThisValueLike(This item) {
    return new ThisValueNested(item);
  }

  public ValueRefTargetNested<A> withNewValueRefTarget() {
    return new ValueRefTargetNested(null);
  }

  public A withNewValueRefTarget(Object value) {
    return (A) withTarget(new ValueRef(value));
  }

  public ValueRefTargetNested<A> withNewValueRefTargetLike(ValueRef item) {
    return new ValueRefTargetNested(item);
  }

  public ValueRefValueNested<A> withNewValueRefValue() {
    return new ValueRefValueNested(null);
  }

  public A withNewValueRefValue(Object value) {
    return (A) withValue(new ValueRef(value));
  }

  public ValueRefValueNested<A> withNewValueRefValueLike(ValueRef item) {
    return new ValueRefValueNested(item);
  }

  public XorTargetNested<A> withNewXorTarget() {
    return new XorTargetNested(null);
  }

  public A withNewXorTarget(Object left, Object right) {
    return (A) withTarget(new Xor(left, right));
  }

  public XorTargetNested<A> withNewXorTargetLike(Xor item) {
    return new XorTargetNested(item);
  }

  public XorValueNested<A> withNewXorValue() {
    return new XorValueNested(null);
  }

  public A withNewXorValue(Object left, Object right) {
    return (A) withValue(new Xor(left, right));
  }

  public XorValueNested<A> withNewXorValueLike(Xor item) {
    return new XorValueNested(item);
  }

  public A withTarget(Expression target) {
    if (target == null) {
      this.target = null;
      this._visitables.remove("target");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(target);
      this._visitables.get("target").clear();
      this._visitables.get("target").add(builder);
      this.target = builder;
      return (A) this;
    }
  }

  public A withValue(Expression value) {
    if (value == null) {
      this.value = null;
      this._visitables.remove("value");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(value);
      this._visitables.get("value").clear();
      this._visitables.get("value").add(builder);
      this.value = builder;
      return (A) this;
    }
  }

  public class AssignTargetNested<N> extends AssignFluent<AssignTargetNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignTargetNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endAssignTarget() {
      return and();
    }

  }

  public class AssignValueNested<N> extends AssignFluent<AssignValueNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignValueNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endAssignValue() {
      return and();
    }

  }

  public class BinaryExpressionTargetNested<N> extends BinaryExpressionFluent<BinaryExpressionTargetNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionTargetNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endBinaryExpressionTarget() {
      return and();
    }

  }

  public class BinaryExpressionValueNested<N> extends BinaryExpressionFluent<BinaryExpressionValueNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionValueNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endBinaryExpressionValue() {
      return and();
    }

  }

  public class BitwiseAndTargetNested<N> extends BitwiseAndFluent<BitwiseAndTargetNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndTargetNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endBitwiseAndTarget() {
      return and();
    }

  }

  public class BitwiseAndValueNested<N> extends BitwiseAndFluent<BitwiseAndValueNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndValueNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endBitwiseAndValue() {
      return and();
    }

  }

  public class BitwiseOrTargetNested<N> extends BitwiseOrFluent<BitwiseOrTargetNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrTargetNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endBitwiseOrTarget() {
      return and();
    }

  }

  public class BitwiseOrValueNested<N> extends BitwiseOrFluent<BitwiseOrValueNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrValueNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endBitwiseOrValue() {
      return and();
    }

  }

  public class CastTargetNested<N> extends CastFluent<CastTargetNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastTargetNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endCastTarget() {
      return and();
    }

  }

  public class CastValueNested<N> extends CastFluent<CastValueNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastValueNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endCastValue() {
      return and();
    }

  }

  public class ClassRefTargetNested<N> extends ClassRefFluent<ClassRefTargetNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefTargetNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endClassRefTarget() {
      return and();
    }

  }

  public class ClassRefValueNested<N> extends ClassRefFluent<ClassRefValueNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefValueNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endClassRefValue() {
      return and();
    }

  }

  public class ConstructTargetNested<N> extends ConstructFluent<ConstructTargetNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructTargetNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endConstructTarget() {
      return and();
    }

  }

  public class ConstructValueNested<N> extends ConstructFluent<ConstructValueNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructValueNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endConstructValue() {
      return and();
    }

  }

  public class ContextRefTargetNested<N> extends ContextRefFluent<ContextRefTargetNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefTargetNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endContextRefTarget() {
      return and();
    }

  }

  public class ContextRefValueNested<N> extends ContextRefFluent<ContextRefValueNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefValueNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endContextRefValue() {
      return and();
    }

  }

  public class DeclareTargetNested<N> extends DeclareFluent<DeclareTargetNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareTargetNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endDeclareTarget() {
      return and();
    }

  }

  public class DeclareValueNested<N> extends DeclareFluent<DeclareValueNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareValueNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endDeclareValue() {
      return and();
    }

  }

  public class DivideTargetNested<N> extends DivideFluent<DivideTargetNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideTargetNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endDivideTarget() {
      return and();
    }

  }

  public class DivideValueNested<N> extends DivideFluent<DivideValueNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideValueNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endDivideValue() {
      return and();
    }

  }

  public class DotClassTargetNested<N> extends DotClassFluent<DotClassTargetNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassTargetNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endDotClassTarget() {
      return and();
    }

  }

  public class DotClassValueNested<N> extends DotClassFluent<DotClassValueNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassValueNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endDotClassValue() {
      return and();
    }

  }

  public class EmptyTargetNested<N> extends EmptyFluent<EmptyTargetNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyTargetNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endEmptyTarget() {
      return and();
    }

  }

  public class EmptyValueNested<N> extends EmptyFluent<EmptyValueNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyValueNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endEmptyValue() {
      return and();
    }

  }

  public class EnclosedTargetNested<N> extends EnclosedFluent<EnclosedTargetNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedTargetNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endEnclosedTarget() {
      return and();
    }

  }

  public class EnclosedValueNested<N> extends EnclosedFluent<EnclosedValueNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedValueNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endEnclosedValue() {
      return and();
    }

  }

  public class EqualsTargetNested<N> extends EqualsFluent<EqualsTargetNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsTargetNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endEqualsTarget() {
      return and();
    }

  }

  public class EqualsValueNested<N> extends EqualsFluent<EqualsValueNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsValueNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endEqualsValue() {
      return and();
    }

  }

  public class GreaterThanOrEqualTargetNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualTargetNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualTargetNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endGreaterThanOrEqualTarget() {
      return and();
    }

  }

  public class GreaterThanOrEqualValueNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualValueNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualValueNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endGreaterThanOrEqualValue() {
      return and();
    }

  }

  public class GreaterThanTargetNested<N> extends GreaterThanFluent<GreaterThanTargetNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanTargetNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endGreaterThanTarget() {
      return and();
    }

  }

  public class GreaterThanValueNested<N> extends GreaterThanFluent<GreaterThanValueNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanValueNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endGreaterThanValue() {
      return and();
    }

  }

  public class IndexTargetNested<N> extends IndexFluent<IndexTargetNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexTargetNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endIndexTarget() {
      return and();
    }

  }

  public class IndexValueNested<N> extends IndexFluent<IndexValueNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexValueNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endIndexValue() {
      return and();
    }

  }

  public class InstanceOfTargetNested<N> extends InstanceOfFluent<InstanceOfTargetNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfTargetNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endInstanceOfTarget() {
      return and();
    }

  }

  public class InstanceOfValueNested<N> extends InstanceOfFluent<InstanceOfValueNested<N>> implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfValueNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endInstanceOfValue() {
      return and();
    }

  }

  public class InverseTargetNested<N> extends InverseFluent<InverseTargetNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseTargetNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endInverseTarget() {
      return and();
    }

  }

  public class InverseValueNested<N> extends InverseFluent<InverseValueNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseValueNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endInverseValue() {
      return and();
    }

  }

  public class LambdaTargetNested<N> extends LambdaFluent<LambdaTargetNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaTargetNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLambdaTarget() {
      return and();
    }

  }

  public class LambdaValueNested<N> extends LambdaFluent<LambdaValueNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaValueNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLambdaValue() {
      return and();
    }

  }

  public class LeftShiftTargetNested<N> extends LeftShiftFluent<LeftShiftTargetNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftTargetNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLeftShiftTarget() {
      return and();
    }

  }

  public class LeftShiftValueNested<N> extends LeftShiftFluent<LeftShiftValueNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftValueNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLeftShiftValue() {
      return and();
    }

  }

  public class LessThanOrEqualTargetNested<N> extends LessThanOrEqualFluent<LessThanOrEqualTargetNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualTargetNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLessThanOrEqualTarget() {
      return and();
    }

  }

  public class LessThanOrEqualValueNested<N> extends LessThanOrEqualFluent<LessThanOrEqualValueNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualValueNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLessThanOrEqualValue() {
      return and();
    }

  }

  public class LessThanTargetNested<N> extends LessThanFluent<LessThanTargetNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanTargetNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLessThanTarget() {
      return and();
    }

  }

  public class LessThanValueNested<N> extends LessThanFluent<LessThanValueNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanValueNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLessThanValue() {
      return and();
    }

  }

  public class LogicalAndTargetNested<N> extends LogicalAndFluent<LogicalAndTargetNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndTargetNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLogicalAndTarget() {
      return and();
    }

  }

  public class LogicalAndValueNested<N> extends LogicalAndFluent<LogicalAndValueNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndValueNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLogicalAndValue() {
      return and();
    }

  }

  public class LogicalOrTargetNested<N> extends LogicalOrFluent<LogicalOrTargetNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrTargetNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endLogicalOrTarget() {
      return and();
    }

  }

  public class LogicalOrValueNested<N> extends LogicalOrFluent<LogicalOrValueNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrValueNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endLogicalOrValue() {
      return and();
    }

  }

  public class MethodCallTargetNested<N> extends MethodCallFluent<MethodCallTargetNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallTargetNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endMethodCallTarget() {
      return and();
    }

  }

  public class MethodCallValueNested<N> extends MethodCallFluent<MethodCallValueNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallValueNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endMethodCallValue() {
      return and();
    }

  }

  public class MinusTargetNested<N> extends MinusFluent<MinusTargetNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusTargetNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endMinusTarget() {
      return and();
    }

  }

  public class MinusValueNested<N> extends MinusFluent<MinusValueNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusValueNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endMinusValue() {
      return and();
    }

  }

  public class ModuloTargetNested<N> extends ModuloFluent<ModuloTargetNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloTargetNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endModuloTarget() {
      return and();
    }

  }

  public class ModuloValueNested<N> extends ModuloFluent<ModuloValueNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloValueNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endModuloValue() {
      return and();
    }

  }

  public class MultiplyTargetNested<N> extends MultiplyFluent<MultiplyTargetNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyTargetNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endMultiplyTarget() {
      return and();
    }

  }

  public class MultiplyValueNested<N> extends MultiplyFluent<MultiplyValueNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyValueNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endMultiplyValue() {
      return and();
    }

  }

  public class NegativeTargetNested<N> extends NegativeFluent<NegativeTargetNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeTargetNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNegativeTarget() {
      return and();
    }

  }

  public class NegativeValueNested<N> extends NegativeFluent<NegativeValueNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeValueNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNegativeValue() {
      return and();
    }

  }

  public class NewArrayTargetNested<N> extends NewArrayFluent<NewArrayTargetNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayTargetNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNewArrayTarget() {
      return and();
    }

  }

  public class NewArrayValueNested<N> extends NewArrayFluent<NewArrayValueNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayValueNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNewArrayValue() {
      return and();
    }

  }

  public class NotEqualsTargetNested<N> extends NotEqualsFluent<NotEqualsTargetNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsTargetNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNotEqualsTarget() {
      return and();
    }

  }

  public class NotEqualsValueNested<N> extends NotEqualsFluent<NotEqualsValueNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsValueNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNotEqualsValue() {
      return and();
    }

  }

  public class NotTargetNested<N> extends NotFluent<NotTargetNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotTargetNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endNotTarget() {
      return and();
    }

  }

  public class NotValueNested<N> extends NotFluent<NotValueNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotValueNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endNotValue() {
      return and();
    }

  }

  public class PlusTargetNested<N> extends PlusFluent<PlusTargetNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusTargetNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPlusTarget() {
      return and();
    }

  }

  public class PlusValueNested<N> extends PlusFluent<PlusValueNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusValueNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPlusValue() {
      return and();
    }

  }

  public class PositiveTargetNested<N> extends PositiveFluent<PositiveTargetNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveTargetNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPositiveTarget() {
      return and();
    }

  }

  public class PositiveValueNested<N> extends PositiveFluent<PositiveValueNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveValueNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPositiveValue() {
      return and();
    }

  }

  public class PostDecrementTargetNested<N> extends PostDecrementFluent<PostDecrementTargetNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementTargetNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPostDecrementTarget() {
      return and();
    }

  }

  public class PostDecrementValueNested<N> extends PostDecrementFluent<PostDecrementValueNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementValueNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPostDecrementValue() {
      return and();
    }

  }

  public class PostIncrementTargetNested<N> extends PostIncrementFluent<PostIncrementTargetNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementTargetNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPostIncrementTarget() {
      return and();
    }

  }

  public class PostIncrementValueNested<N> extends PostIncrementFluent<PostIncrementValueNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementValueNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPostIncrementValue() {
      return and();
    }

  }

  public class PreDecrementTargetNested<N> extends PreDecrementFluent<PreDecrementTargetNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementTargetNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPreDecrementTarget() {
      return and();
    }

  }

  public class PreDecrementValueNested<N> extends PreDecrementFluent<PreDecrementValueNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementValueNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPreDecrementValue() {
      return and();
    }

  }

  public class PreIncrementTargetNested<N> extends PreIncrementFluent<PreIncrementTargetNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementTargetNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPreIncrementTarget() {
      return and();
    }

  }

  public class PreIncrementValueNested<N> extends PreIncrementFluent<PreIncrementValueNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementValueNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPreIncrementValue() {
      return and();
    }

  }

  public class PropertyRefTargetNested<N> extends PropertyRefFluent<PropertyRefTargetNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefTargetNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPropertyRefTarget() {
      return and();
    }

  }

  public class PropertyRefValueNested<N> extends PropertyRefFluent<PropertyRefValueNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefValueNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPropertyRefValue() {
      return and();
    }

  }

  public class PropertyTargetNested<N> extends PropertyFluent<PropertyTargetNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyTargetNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endPropertyTarget() {
      return and();
    }

  }

  public class PropertyValueNested<N> extends PropertyFluent<PropertyValueNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyValueNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endPropertyValue() {
      return and();
    }

  }

  public class RightShiftTargetNested<N> extends RightShiftFluent<RightShiftTargetNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftTargetNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endRightShiftTarget() {
      return and();
    }

  }

  public class RightShiftValueNested<N> extends RightShiftFluent<RightShiftValueNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftValueNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endRightShiftValue() {
      return and();
    }

  }

  public class RightUnsignedShiftTargetNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftTargetNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftTargetNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endRightUnsignedShiftTarget() {
      return and();
    }

  }

  public class RightUnsignedShiftValueNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftValueNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftValueNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endRightUnsignedShiftValue() {
      return and();
    }

  }

  public class SuperTargetNested<N> extends SuperFluent<SuperTargetNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperTargetNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endSuperTarget() {
      return and();
    }

  }

  public class SuperValueNested<N> extends SuperFluent<SuperValueNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperValueNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endSuperValue() {
      return and();
    }

  }

  public class TernaryTargetNested<N> extends TernaryFluent<TernaryTargetNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryTargetNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endTernaryTarget() {
      return and();
    }

  }

  public class TernaryValueNested<N> extends TernaryFluent<TernaryValueNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryValueNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endTernaryValue() {
      return and();
    }

  }

  public class ThisTargetNested<N> extends ThisFluent<ThisTargetNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisTargetNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endThisTarget() {
      return and();
    }

  }

  public class ThisValueNested<N> extends ThisFluent<ThisValueNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisValueNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endThisValue() {
      return and();
    }

  }

  public class ValueRefTargetNested<N> extends ValueRefFluent<ValueRefTargetNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefTargetNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endValueRefTarget() {
      return and();
    }

  }

  public class ValueRefValueNested<N> extends ValueRefFluent<ValueRefValueNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefValueNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endValueRefValue() {
      return and();
    }

  }

  public class XorTargetNested<N> extends XorFluent<XorTargetNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorTargetNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withTarget(builder.build());
    }

    public N endXorTarget() {
      return and();
    }

  }

  public class XorValueNested<N> extends XorFluent<XorValueNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorValueNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) AssignFluent.this.withValue(builder.build());
    }

    public N endXorValue() {
      return and();
    }

  }
}
