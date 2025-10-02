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
public class SynchronizedFluent<A extends SynchronizedFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Statement, ?> body;
  private VisitableBuilder<? extends Expression, ?> lockExpression;

  public SynchronizedFluent() {
  }

  public SynchronizedFluent(Synchronized instance) {
    this.copyInstance(instance);
  }

  public Statement buildBody() {
    return this.body != null ? this.body.build() : null;
  }

  public Expression buildLockExpression() {
    return this.lockExpression != null ? this.lockExpression.build() : null;
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
      case "io.sundr.model." + "ReturnDslThisStep":
        return (VisitableBuilder<T, ?>) new ReturnDslThisStepBuilder((ReturnDslThisStep) item);
      case "io.sundr.model." + "Try":
        return (VisitableBuilder<T, ?>) new TryBuilder((Try) item);
      case "io.sundr.model." + "Switch":
        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);
      case "io.sundr.model." + "Synchronized":
        return (VisitableBuilder<T, ?>) new SynchronizedBuilder((Synchronized) item);
      case "io.sundr.model." + "Break":
        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);
      case "io.sundr.model." + "While":
        return (VisitableBuilder<T, ?>) new WhileBuilder((While) item);
      case "io.sundr.model." + "Continue":
        return (VisitableBuilder<T, ?>) new ContinueBuilder((Continue) item);
      case "io.sundr.model." + "Throw":
        return (VisitableBuilder<T, ?>) new ThrowBuilder((Throw) item);
      case "io.sundr.model." + "StringStatement":
        return (VisitableBuilder<T, ?>) new StringStatementBuilder((StringStatement) item);
      case "io.sundr.model." + "Do":
        return (VisitableBuilder<T, ?>) new DoBuilder((Do) item);
      case "io.sundr.model." + "Foreach":
        return (VisitableBuilder<T, ?>) new ForeachBuilder((Foreach) item);
      case "io.sundr.model." + "Block":
        return (VisitableBuilder<T, ?>) new BlockBuilder((Block) item);
      case "io.sundr.model." + "ReturnDslVariableStep":
        return (VisitableBuilder<T, ?>) new ReturnDslVariableStepBuilder((ReturnDslVariableStep) item);
      case "io.sundr.model." + "If":
        return (VisitableBuilder<T, ?>) new IfBuilder((If) item);
      case "io.sundr.model." + "Return":
        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);
      case "io.sundr.model." + "IfDslThenStep":
        return (VisitableBuilder<T, ?>) new IfDslThenStepBuilder((IfDslThenStep) item);
      case "io.sundr.model." + "For":
        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  protected void copyInstance(Synchronized instance) {
    if (instance != null) {
      this.withLockExpression(instance.getLockExpression());
      this.withBody(instance.getBody());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    SynchronizedFluent that = (SynchronizedFluent) o;
    if (!java.util.Objects.equals(lockExpression, that.lockExpression))
      return false;
    if (!java.util.Objects.equals(body, that.body))
      return false;
    return true;
  }

  public boolean hasBody() {
    return this.body != null;
  }

  public boolean hasLockExpression() {
    return this.lockExpression != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(lockExpression, body, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (lockExpression != null) {
      sb.append("lockExpression:");
      sb.append(lockExpression + ",");
    }
    if (body != null) {
      sb.append("body:");
      sb.append(body);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withBody(Statement body) {
    if (body == null) {
      this.body = null;
      this._visitables.remove("body");
      return (A) this;
    } else {
      VisitableBuilder<? extends Statement, ?> builder = builder(body);
      this._visitables.get("body").clear();
      this._visitables.get("body").add(builder);
      this.body = builder;
      return (A) this;
    }
  }

  public A withLockExpression(Expression lockExpression) {
    if (lockExpression == null) {
      this.lockExpression = null;
      this._visitables.remove("lockExpression");
      return (A) this;
    } else {
      VisitableBuilder<? extends Expression, ?> builder = builder(lockExpression);
      this._visitables.get("lockExpression").clear();
      this._visitables.get("lockExpression").add(builder);
      this.lockExpression = builder;
      return (A) this;
    }
  }

  public AssignBodyNested<A> withNewAssignBody() {
    return new AssignBodyNested(null);
  }

  public AssignBodyNested<A> withNewAssignBodyLike(Assign item) {
    return new AssignBodyNested(item);
  }

  public AssignLockExpressionNested<A> withNewAssignLockExpression() {
    return new AssignLockExpressionNested(null);
  }

  public AssignLockExpressionNested<A> withNewAssignLockExpressionLike(Assign item) {
    return new AssignLockExpressionNested(item);
  }

  public BinaryExpressionBodyNested<A> withNewBinaryExpressionBody() {
    return new BinaryExpressionBodyNested(null);
  }

  public BinaryExpressionBodyNested<A> withNewBinaryExpressionBodyLike(BinaryExpression item) {
    return new BinaryExpressionBodyNested(item);
  }

  public BinaryExpressionLockNested<A> withNewBinaryExpressionLock() {
    return new BinaryExpressionLockNested(null);
  }

  public BinaryExpressionLockNested<A> withNewBinaryExpressionLockLike(BinaryExpression item) {
    return new BinaryExpressionLockNested(item);
  }

  public BitwiseAndBodyNested<A> withNewBitwiseAndBody() {
    return new BitwiseAndBodyNested(null);
  }

  public A withNewBitwiseAndBody(Object left, Object right) {
    return (A) withBody(new BitwiseAnd(left, right));
  }

  public BitwiseAndBodyNested<A> withNewBitwiseAndBodyLike(BitwiseAnd item) {
    return new BitwiseAndBodyNested(item);
  }

  public BitwiseAndLockExpressionNested<A> withNewBitwiseAndLockExpression() {
    return new BitwiseAndLockExpressionNested(null);
  }

  public A withNewBitwiseAndLockExpression(Object left, Object right) {
    return (A) withLockExpression(new BitwiseAnd(left, right));
  }

  public BitwiseAndLockExpressionNested<A> withNewBitwiseAndLockExpressionLike(BitwiseAnd item) {
    return new BitwiseAndLockExpressionNested(item);
  }

  public BitwiseOrBodyNested<A> withNewBitwiseOrBody() {
    return new BitwiseOrBodyNested(null);
  }

  public A withNewBitwiseOrBody(Object left, Object right) {
    return (A) withBody(new BitwiseOr(left, right));
  }

  public BitwiseOrBodyNested<A> withNewBitwiseOrBodyLike(BitwiseOr item) {
    return new BitwiseOrBodyNested(item);
  }

  public BitwiseOrLockExpressionNested<A> withNewBitwiseOrLockExpression() {
    return new BitwiseOrLockExpressionNested(null);
  }

  public A withNewBitwiseOrLockExpression(Object left, Object right) {
    return (A) withLockExpression(new BitwiseOr(left, right));
  }

  public BitwiseOrLockExpressionNested<A> withNewBitwiseOrLockExpressionLike(BitwiseOr item) {
    return new BitwiseOrLockExpressionNested(item);
  }

  public BlockBodyNested<A> withNewBlockBody() {
    return new BlockBodyNested(null);
  }

  public BlockBodyNested<A> withNewBlockBodyLike(Block item) {
    return new BlockBodyNested(item);
  }

  public BreakBodyNested<A> withNewBreakBody() {
    return new BreakBodyNested(null);
  }

  public BreakBodyNested<A> withNewBreakBodyLike(Break item) {
    return new BreakBodyNested(item);
  }

  public CastLockExpressionNested<A> withNewCastLockExpression() {
    return new CastLockExpressionNested(null);
  }

  public CastLockExpressionNested<A> withNewCastLockExpressionLike(Cast item) {
    return new CastLockExpressionNested(item);
  }

  public ClassRefLockExpressionNested<A> withNewClassRefLockExpression() {
    return new ClassRefLockExpressionNested(null);
  }

  public ClassRefLockExpressionNested<A> withNewClassRefLockExpressionLike(ClassRef item) {
    return new ClassRefLockExpressionNested(item);
  }

  public ConstructBodyNested<A> withNewConstructBody() {
    return new ConstructBodyNested(null);
  }

  public ConstructBodyNested<A> withNewConstructBodyLike(Construct item) {
    return new ConstructBodyNested(item);
  }

  public ConstructLockExpressionNested<A> withNewConstructLockExpression() {
    return new ConstructLockExpressionNested(null);
  }

  public ConstructLockExpressionNested<A> withNewConstructLockExpressionLike(Construct item) {
    return new ConstructLockExpressionNested(item);
  }

  public ContextRefLockExpressionNested<A> withNewContextRefLockExpression() {
    return new ContextRefLockExpressionNested(null);
  }

  public A withNewContextRefLockExpression(String name) {
    return (A) withLockExpression(new ContextRef(name));
  }

  public ContextRefLockExpressionNested<A> withNewContextRefLockExpressionLike(ContextRef item) {
    return new ContextRefLockExpressionNested(item);
  }

  public ContinueBodyNested<A> withNewContinueBody() {
    return new ContinueBodyNested(null);
  }

  public ContinueBodyNested<A> withNewContinueBodyLike(Continue item) {
    return new ContinueBodyNested(item);
  }

  public DeclareBodyNested<A> withNewDeclareBody() {
    return new DeclareBodyNested(null);
  }

  public A withNewDeclareBody(Class type, String name) {
    return (A) withBody(new Declare(type, name));
  }

  public A withNewDeclareBody(Class type, String name, Object value) {
    return (A) withBody(new Declare(type, name, value));
  }

  public DeclareBodyNested<A> withNewDeclareBodyLike(Declare item) {
    return new DeclareBodyNested(item);
  }

  public DeclareLockExpressionNested<A> withNewDeclareLockExpression() {
    return new DeclareLockExpressionNested(null);
  }

  public A withNewDeclareLockExpression(Class type, String name) {
    return (A) withLockExpression(new Declare(type, name));
  }

  public A withNewDeclareLockExpression(Class type, String name, Object value) {
    return (A) withLockExpression(new Declare(type, name, value));
  }

  public DeclareLockExpressionNested<A> withNewDeclareLockExpressionLike(Declare item) {
    return new DeclareLockExpressionNested(item);
  }

  public DivideBodyNested<A> withNewDivideBody() {
    return new DivideBodyNested(null);
  }

  public A withNewDivideBody(Object left, Object right) {
    return (A) withBody(new Divide(left, right));
  }

  public DivideBodyNested<A> withNewDivideBodyLike(Divide item) {
    return new DivideBodyNested(item);
  }

  public DivideLockExpressionNested<A> withNewDivideLockExpression() {
    return new DivideLockExpressionNested(null);
  }

  public A withNewDivideLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Divide(left, right));
  }

  public DivideLockExpressionNested<A> withNewDivideLockExpressionLike(Divide item) {
    return new DivideLockExpressionNested(item);
  }

  public DoBodyNested<A> withNewDoBody() {
    return new DoBodyNested(null);
  }

  public DoBodyNested<A> withNewDoBodyLike(Do item) {
    return new DoBodyNested(item);
  }

  public DotClassLockExpressionNested<A> withNewDotClassLockExpression() {
    return new DotClassLockExpressionNested(null);
  }

  public DotClassLockExpressionNested<A> withNewDotClassLockExpressionLike(DotClass item) {
    return new DotClassLockExpressionNested(item);
  }

  public EmptyBodyNested<A> withNewEmptyBody() {
    return new EmptyBodyNested(null);
  }

  public EmptyBodyNested<A> withNewEmptyBodyLike(Empty item) {
    return new EmptyBodyNested(item);
  }

  public EmptyLockExpressionNested<A> withNewEmptyLockExpression() {
    return new EmptyLockExpressionNested(null);
  }

  public EmptyLockExpressionNested<A> withNewEmptyLockExpressionLike(Empty item) {
    return new EmptyLockExpressionNested(item);
  }

  public EnclosedLockExpressionNested<A> withNewEnclosedLockExpression() {
    return new EnclosedLockExpressionNested(null);
  }

  public EnclosedLockExpressionNested<A> withNewEnclosedLockExpressionLike(Enclosed item) {
    return new EnclosedLockExpressionNested(item);
  }

  public EqualsBodyNested<A> withNewEqualsBody() {
    return new EqualsBodyNested(null);
  }

  public A withNewEqualsBody(Object left, Object right) {
    return (A) withBody(new Equals(left, right));
  }

  public EqualsBodyNested<A> withNewEqualsBodyLike(Equals item) {
    return new EqualsBodyNested(item);
  }

  public EqualsLockExpressionNested<A> withNewEqualsLockExpression() {
    return new EqualsLockExpressionNested(null);
  }

  public A withNewEqualsLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Equals(left, right));
  }

  public EqualsLockExpressionNested<A> withNewEqualsLockExpressionLike(Equals item) {
    return new EqualsLockExpressionNested(item);
  }

  public ForBodyNested<A> withNewForBody() {
    return new ForBodyNested(null);
  }

  public ForBodyNested<A> withNewForBodyLike(For item) {
    return new ForBodyNested(item);
  }

  public ForeachBodyNested<A> withNewForeachBody() {
    return new ForeachBodyNested(null);
  }

  public ForeachBodyNested<A> withNewForeachBodyLike(Foreach item) {
    return new ForeachBodyNested(item);
  }

  public GreaterThanBodyNested<A> withNewGreaterThanBody() {
    return new GreaterThanBodyNested(null);
  }

  public A withNewGreaterThanBody(Object left, Object right) {
    return (A) withBody(new GreaterThan(left, right));
  }

  public GreaterThanBodyNested<A> withNewGreaterThanBodyLike(GreaterThan item) {
    return new GreaterThanBodyNested(item);
  }

  public GreaterThanLockExpressionNested<A> withNewGreaterThanLockExpression() {
    return new GreaterThanLockExpressionNested(null);
  }

  public A withNewGreaterThanLockExpression(Object left, Object right) {
    return (A) withLockExpression(new GreaterThan(left, right));
  }

  public GreaterThanLockExpressionNested<A> withNewGreaterThanLockExpressionLike(GreaterThan item) {
    return new GreaterThanLockExpressionNested(item);
  }

  public GreaterThanOrEqualBodyNested<A> withNewGreaterThanOrEqualBody() {
    return new GreaterThanOrEqualBodyNested(null);
  }

  public A withNewGreaterThanOrEqualBody(Object left, Object right) {
    return (A) withBody(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualBodyNested<A> withNewGreaterThanOrEqualBodyLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualBodyNested(item);
  }

  public GreaterThanOrEqualLockExpressionNested<A> withNewGreaterThanOrEqualLockExpression() {
    return new GreaterThanOrEqualLockExpressionNested(null);
  }

  public A withNewGreaterThanOrEqualLockExpression(Object left, Object right) {
    return (A) withLockExpression(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualLockExpressionNested<A> withNewGreaterThanOrEqualLockExpressionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualLockExpressionNested(item);
  }

  public IfBodyNested<A> withNewIfBody() {
    return new IfBodyNested(null);
  }

  public IfBodyNested<A> withNewIfBodyLike(If item) {
    return new IfBodyNested(item);
  }

  public IfDslThenStepBodyNested<A> withNewIfDslThenStepBody() {
    return new IfDslThenStepBodyNested(null);
  }

  public IfDslThenStepBodyNested<A> withNewIfDslThenStepBodyLike(IfDslThenStep item) {
    return new IfDslThenStepBodyNested(item);
  }

  public IndexLockExpressionNested<A> withNewIndexLockExpression() {
    return new IndexLockExpressionNested(null);
  }

  public IndexLockExpressionNested<A> withNewIndexLockExpressionLike(Index item) {
    return new IndexLockExpressionNested(item);
  }

  public InstanceOfLockExpressionNested<A> withNewInstanceOfLockExpression() {
    return new InstanceOfLockExpressionNested(null);
  }

  public InstanceOfLockExpressionNested<A> withNewInstanceOfLockExpressionLike(InstanceOf item) {
    return new InstanceOfLockExpressionNested(item);
  }

  public InverseLockExpressionNested<A> withNewInverseLockExpression() {
    return new InverseLockExpressionNested(null);
  }

  public InverseLockExpressionNested<A> withNewInverseLockExpressionLike(Inverse item) {
    return new InverseLockExpressionNested(item);
  }

  public LambdaBodyNested<A> withNewLambdaBody() {
    return new LambdaBodyNested(null);
  }

  public LambdaBodyNested<A> withNewLambdaBodyLike(Lambda item) {
    return new LambdaBodyNested(item);
  }

  public LambdaLockExpressionNested<A> withNewLambdaLockExpression() {
    return new LambdaLockExpressionNested(null);
  }

  public LambdaLockExpressionNested<A> withNewLambdaLockExpressionLike(Lambda item) {
    return new LambdaLockExpressionNested(item);
  }

  public LeftShiftBodyNested<A> withNewLeftShiftBody() {
    return new LeftShiftBodyNested(null);
  }

  public A withNewLeftShiftBody(Object left, Object right) {
    return (A) withBody(new LeftShift(left, right));
  }

  public LeftShiftBodyNested<A> withNewLeftShiftBodyLike(LeftShift item) {
    return new LeftShiftBodyNested(item);
  }

  public LeftShiftLockExpressionNested<A> withNewLeftShiftLockExpression() {
    return new LeftShiftLockExpressionNested(null);
  }

  public A withNewLeftShiftLockExpression(Object left, Object right) {
    return (A) withLockExpression(new LeftShift(left, right));
  }

  public LeftShiftLockExpressionNested<A> withNewLeftShiftLockExpressionLike(LeftShift item) {
    return new LeftShiftLockExpressionNested(item);
  }

  public LessThanBodyNested<A> withNewLessThanBody() {
    return new LessThanBodyNested(null);
  }

  public A withNewLessThanBody(Object left, Object right) {
    return (A) withBody(new LessThan(left, right));
  }

  public LessThanBodyNested<A> withNewLessThanBodyLike(LessThan item) {
    return new LessThanBodyNested(item);
  }

  public LessThanLockExpressionNested<A> withNewLessThanLockExpression() {
    return new LessThanLockExpressionNested(null);
  }

  public A withNewLessThanLockExpression(Object left, Object right) {
    return (A) withLockExpression(new LessThan(left, right));
  }

  public LessThanLockExpressionNested<A> withNewLessThanLockExpressionLike(LessThan item) {
    return new LessThanLockExpressionNested(item);
  }

  public LessThanOrEqualBodyNested<A> withNewLessThanOrEqualBody() {
    return new LessThanOrEqualBodyNested(null);
  }

  public A withNewLessThanOrEqualBody(Object left, Object right) {
    return (A) withBody(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualBodyNested<A> withNewLessThanOrEqualBodyLike(LessThanOrEqual item) {
    return new LessThanOrEqualBodyNested(item);
  }

  public LessThanOrEqualLockExpressionNested<A> withNewLessThanOrEqualLockExpression() {
    return new LessThanOrEqualLockExpressionNested(null);
  }

  public A withNewLessThanOrEqualLockExpression(Object left, Object right) {
    return (A) withLockExpression(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualLockExpressionNested<A> withNewLessThanOrEqualLockExpressionLike(LessThanOrEqual item) {
    return new LessThanOrEqualLockExpressionNested(item);
  }

  public LogicalAndBodyNested<A> withNewLogicalAndBody() {
    return new LogicalAndBodyNested(null);
  }

  public A withNewLogicalAndBody(Object left, Object right) {
    return (A) withBody(new LogicalAnd(left, right));
  }

  public LogicalAndBodyNested<A> withNewLogicalAndBodyLike(LogicalAnd item) {
    return new LogicalAndBodyNested(item);
  }

  public LogicalAndLockExpressionNested<A> withNewLogicalAndLockExpression() {
    return new LogicalAndLockExpressionNested(null);
  }

  public A withNewLogicalAndLockExpression(Object left, Object right) {
    return (A) withLockExpression(new LogicalAnd(left, right));
  }

  public LogicalAndLockExpressionNested<A> withNewLogicalAndLockExpressionLike(LogicalAnd item) {
    return new LogicalAndLockExpressionNested(item);
  }

  public LogicalOrBodyNested<A> withNewLogicalOrBody() {
    return new LogicalOrBodyNested(null);
  }

  public A withNewLogicalOrBody(Object left, Object right) {
    return (A) withBody(new LogicalOr(left, right));
  }

  public LogicalOrBodyNested<A> withNewLogicalOrBodyLike(LogicalOr item) {
    return new LogicalOrBodyNested(item);
  }

  public LogicalOrLockExpressionNested<A> withNewLogicalOrLockExpression() {
    return new LogicalOrLockExpressionNested(null);
  }

  public A withNewLogicalOrLockExpression(Object left, Object right) {
    return (A) withLockExpression(new LogicalOr(left, right));
  }

  public LogicalOrLockExpressionNested<A> withNewLogicalOrLockExpressionLike(LogicalOr item) {
    return new LogicalOrLockExpressionNested(item);
  }

  public MethodCallBodyNested<A> withNewMethodCallBody() {
    return new MethodCallBodyNested(null);
  }

  public MethodCallBodyNested<A> withNewMethodCallBodyLike(MethodCall item) {
    return new MethodCallBodyNested(item);
  }

  public MethodCallLockExpressionNested<A> withNewMethodCallLockExpression() {
    return new MethodCallLockExpressionNested(null);
  }

  public MethodCallLockExpressionNested<A> withNewMethodCallLockExpressionLike(MethodCall item) {
    return new MethodCallLockExpressionNested(item);
  }

  public MinusBodyNested<A> withNewMinusBody() {
    return new MinusBodyNested(null);
  }

  public A withNewMinusBody(Object left, Object right) {
    return (A) withBody(new Minus(left, right));
  }

  public MinusBodyNested<A> withNewMinusBodyLike(Minus item) {
    return new MinusBodyNested(item);
  }

  public MinusLockExpressionNested<A> withNewMinusLockExpression() {
    return new MinusLockExpressionNested(null);
  }

  public A withNewMinusLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Minus(left, right));
  }

  public MinusLockExpressionNested<A> withNewMinusLockExpressionLike(Minus item) {
    return new MinusLockExpressionNested(item);
  }

  public ModuloBodyNested<A> withNewModuloBody() {
    return new ModuloBodyNested(null);
  }

  public A withNewModuloBody(Object left, Object right) {
    return (A) withBody(new Modulo(left, right));
  }

  public ModuloBodyNested<A> withNewModuloBodyLike(Modulo item) {
    return new ModuloBodyNested(item);
  }

  public ModuloLockExpressionNested<A> withNewModuloLockExpression() {
    return new ModuloLockExpressionNested(null);
  }

  public A withNewModuloLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Modulo(left, right));
  }

  public ModuloLockExpressionNested<A> withNewModuloLockExpressionLike(Modulo item) {
    return new ModuloLockExpressionNested(item);
  }

  public MultiplyBodyNested<A> withNewMultiplyBody() {
    return new MultiplyBodyNested(null);
  }

  public A withNewMultiplyBody(Object left, Object right) {
    return (A) withBody(new Multiply(left, right));
  }

  public MultiplyBodyNested<A> withNewMultiplyBodyLike(Multiply item) {
    return new MultiplyBodyNested(item);
  }

  public MultiplyLockExpressionNested<A> withNewMultiplyLockExpression() {
    return new MultiplyLockExpressionNested(null);
  }

  public A withNewMultiplyLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Multiply(left, right));
  }

  public MultiplyLockExpressionNested<A> withNewMultiplyLockExpressionLike(Multiply item) {
    return new MultiplyLockExpressionNested(item);
  }

  public NegativeLockExpressionNested<A> withNewNegativeLockExpression() {
    return new NegativeLockExpressionNested(null);
  }

  public NegativeLockExpressionNested<A> withNewNegativeLockExpressionLike(Negative item) {
    return new NegativeLockExpressionNested(item);
  }

  public NewArrayLockExpressionNested<A> withNewNewArrayLockExpression() {
    return new NewArrayLockExpressionNested(null);
  }

  public A withNewNewArrayLockExpression(Class type, Integer[] sizes) {
    return (A) withLockExpression(new NewArray(type, sizes));
  }

  public NewArrayLockExpressionNested<A> withNewNewArrayLockExpressionLike(NewArray item) {
    return new NewArrayLockExpressionNested(item);
  }

  public NotEqualsBodyNested<A> withNewNotEqualsBody() {
    return new NotEqualsBodyNested(null);
  }

  public A withNewNotEqualsBody(Object left, Object right) {
    return (A) withBody(new NotEquals(left, right));
  }

  public NotEqualsBodyNested<A> withNewNotEqualsBodyLike(NotEquals item) {
    return new NotEqualsBodyNested(item);
  }

  public NotEqualsLockExpressionNested<A> withNewNotEqualsLockExpression() {
    return new NotEqualsLockExpressionNested(null);
  }

  public A withNewNotEqualsLockExpression(Object left, Object right) {
    return (A) withLockExpression(new NotEquals(left, right));
  }

  public NotEqualsLockExpressionNested<A> withNewNotEqualsLockExpressionLike(NotEquals item) {
    return new NotEqualsLockExpressionNested(item);
  }

  public NotLockExpressionNested<A> withNewNotLockExpression() {
    return new NotLockExpressionNested(null);
  }

  public NotLockExpressionNested<A> withNewNotLockExpressionLike(Not item) {
    return new NotLockExpressionNested(item);
  }

  public PlusBodyNested<A> withNewPlusBody() {
    return new PlusBodyNested(null);
  }

  public A withNewPlusBody(Object left, Object right) {
    return (A) withBody(new Plus(left, right));
  }

  public PlusBodyNested<A> withNewPlusBodyLike(Plus item) {
    return new PlusBodyNested(item);
  }

  public PlusLockExpressionNested<A> withNewPlusLockExpression() {
    return new PlusLockExpressionNested(null);
  }

  public A withNewPlusLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Plus(left, right));
  }

  public PlusLockExpressionNested<A> withNewPlusLockExpressionLike(Plus item) {
    return new PlusLockExpressionNested(item);
  }

  public PositiveLockExpressionNested<A> withNewPositiveLockExpression() {
    return new PositiveLockExpressionNested(null);
  }

  public PositiveLockExpressionNested<A> withNewPositiveLockExpressionLike(Positive item) {
    return new PositiveLockExpressionNested(item);
  }

  public PostDecrementBodyNested<A> withNewPostDecrementBody() {
    return new PostDecrementBodyNested(null);
  }

  public PostDecrementBodyNested<A> withNewPostDecrementBodyLike(PostDecrement item) {
    return new PostDecrementBodyNested(item);
  }

  public PostDecrementLockExpressionNested<A> withNewPostDecrementLockExpression() {
    return new PostDecrementLockExpressionNested(null);
  }

  public PostDecrementLockExpressionNested<A> withNewPostDecrementLockExpressionLike(PostDecrement item) {
    return new PostDecrementLockExpressionNested(item);
  }

  public PostIncrementBodyNested<A> withNewPostIncrementBody() {
    return new PostIncrementBodyNested(null);
  }

  public PostIncrementBodyNested<A> withNewPostIncrementBodyLike(PostIncrement item) {
    return new PostIncrementBodyNested(item);
  }

  public PostIncrementLockExpressionNested<A> withNewPostIncrementLockExpression() {
    return new PostIncrementLockExpressionNested(null);
  }

  public PostIncrementLockExpressionNested<A> withNewPostIncrementLockExpressionLike(PostIncrement item) {
    return new PostIncrementLockExpressionNested(item);
  }

  public PreDecrementBodyNested<A> withNewPreDecrementBody() {
    return new PreDecrementBodyNested(null);
  }

  public PreDecrementBodyNested<A> withNewPreDecrementBodyLike(PreDecrement item) {
    return new PreDecrementBodyNested(item);
  }

  public PreDecrementLockExpressionNested<A> withNewPreDecrementLockExpression() {
    return new PreDecrementLockExpressionNested(null);
  }

  public PreDecrementLockExpressionNested<A> withNewPreDecrementLockExpressionLike(PreDecrement item) {
    return new PreDecrementLockExpressionNested(item);
  }

  public PreIncrementBodyNested<A> withNewPreIncrementBody() {
    return new PreIncrementBodyNested(null);
  }

  public PreIncrementBodyNested<A> withNewPreIncrementBodyLike(PreIncrement item) {
    return new PreIncrementBodyNested(item);
  }

  public PreIncrementLockExpressionNested<A> withNewPreIncrementLockExpression() {
    return new PreIncrementLockExpressionNested(null);
  }

  public PreIncrementLockExpressionNested<A> withNewPreIncrementLockExpressionLike(PreIncrement item) {
    return new PreIncrementLockExpressionNested(item);
  }

  public PropertyLockExpressionNested<A> withNewPropertyLockExpression() {
    return new PropertyLockExpressionNested(null);
  }

  public PropertyLockExpressionNested<A> withNewPropertyLockExpressionLike(Property item) {
    return new PropertyLockExpressionNested(item);
  }

  public PropertyRefBodyNested<A> withNewPropertyRefBody() {
    return new PropertyRefBodyNested(null);
  }

  public PropertyRefBodyNested<A> withNewPropertyRefBodyLike(PropertyRef item) {
    return new PropertyRefBodyNested(item);
  }

  public PropertyRefLockExpressionNested<A> withNewPropertyRefLockExpression() {
    return new PropertyRefLockExpressionNested(null);
  }

  public PropertyRefLockExpressionNested<A> withNewPropertyRefLockExpressionLike(PropertyRef item) {
    return new PropertyRefLockExpressionNested(item);
  }

  public ReturnBodyNested<A> withNewReturnBody() {
    return new ReturnBodyNested(null);
  }

  public A withNewReturnBody(Object object) {
    return (A) withBody(new Return(object));
  }

  public ReturnBodyNested<A> withNewReturnBodyLike(Return item) {
    return new ReturnBodyNested(item);
  }

  public ReturnDslThisStepBodyNested<A> withNewReturnDslThisStepBody() {
    return new ReturnDslThisStepBodyNested(null);
  }

  public ReturnDslThisStepBodyNested<A> withNewReturnDslThisStepBodyLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepBodyNested(item);
  }

  public ReturnDslVariableStepBodyNested<A> withNewReturnDslVariableStepBody() {
    return new ReturnDslVariableStepBodyNested(null);
  }

  public A withNewReturnDslVariableStepBody(String name) {
    return (A) withBody(new ReturnDslVariableStep(name));
  }

  public ReturnDslVariableStepBodyNested<A> withNewReturnDslVariableStepBodyLike(ReturnDslVariableStep item) {
    return new ReturnDslVariableStepBodyNested(item);
  }

  public RightShiftBodyNested<A> withNewRightShiftBody() {
    return new RightShiftBodyNested(null);
  }

  public A withNewRightShiftBody(Object left, Object right) {
    return (A) withBody(new RightShift(left, right));
  }

  public RightShiftBodyNested<A> withNewRightShiftBodyLike(RightShift item) {
    return new RightShiftBodyNested(item);
  }

  public RightShiftLockExpressionNested<A> withNewRightShiftLockExpression() {
    return new RightShiftLockExpressionNested(null);
  }

  public A withNewRightShiftLockExpression(Object left, Object right) {
    return (A) withLockExpression(new RightShift(left, right));
  }

  public RightShiftLockExpressionNested<A> withNewRightShiftLockExpressionLike(RightShift item) {
    return new RightShiftLockExpressionNested(item);
  }

  public RightUnsignedShiftBodyNested<A> withNewRightUnsignedShiftBody() {
    return new RightUnsignedShiftBodyNested(null);
  }

  public A withNewRightUnsignedShiftBody(Object left, Object right) {
    return (A) withBody(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftBodyNested<A> withNewRightUnsignedShiftBodyLike(RightUnsignedShift item) {
    return new RightUnsignedShiftBodyNested(item);
  }

  public RightUnsignedShiftLockExpressionNested<A> withNewRightUnsignedShiftLockExpression() {
    return new RightUnsignedShiftLockExpressionNested(null);
  }

  public A withNewRightUnsignedShiftLockExpression(Object left, Object right) {
    return (A) withLockExpression(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftLockExpressionNested<A> withNewRightUnsignedShiftLockExpressionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftLockExpressionNested(item);
  }

  public StringStatementBodyNested<A> withNewStringStatementBody() {
    return new StringStatementBodyNested(null);
  }

  public A withNewStringStatementBody(String data) {
    return (A) withBody(new StringStatement(data));
  }

  public A withNewStringStatementBody(String data, Object[] parameters) {
    return (A) withBody(new StringStatement(data, parameters));
  }

  public StringStatementBodyNested<A> withNewStringStatementBodyLike(StringStatement item) {
    return new StringStatementBodyNested(item);
  }

  public SuperLockExpressionNested<A> withNewSuperLockExpression() {
    return new SuperLockExpressionNested(null);
  }

  public SuperLockExpressionNested<A> withNewSuperLockExpressionLike(Super item) {
    return new SuperLockExpressionNested(item);
  }

  public SwitchBodyNested<A> withNewSwitchBody() {
    return new SwitchBodyNested(null);
  }

  public SwitchBodyNested<A> withNewSwitchBodyLike(Switch item) {
    return new SwitchBodyNested(item);
  }

  public SynchronizedBodyNested<A> withNewSynchronizedBody() {
    return new SynchronizedBodyNested(null);
  }

  public SynchronizedBodyNested<A> withNewSynchronizedBodyLike(Synchronized item) {
    return new SynchronizedBodyNested(item);
  }

  public TernaryLockExpressionNested<A> withNewTernaryLockExpression() {
    return new TernaryLockExpressionNested(null);
  }

  public TernaryLockExpressionNested<A> withNewTernaryLockExpressionLike(Ternary item) {
    return new TernaryLockExpressionNested(item);
  }

  public ThisLockExpressionNested<A> withNewThisLockExpression() {
    return new ThisLockExpressionNested(null);
  }

  public ThisLockExpressionNested<A> withNewThisLockExpressionLike(This item) {
    return new ThisLockExpressionNested(item);
  }

  public ThrowBodyNested<A> withNewThrowBody() {
    return new ThrowBodyNested(null);
  }

  public ThrowBodyNested<A> withNewThrowBodyLike(Throw item) {
    return new ThrowBodyNested(item);
  }

  public TryBodyNested<A> withNewTryBody() {
    return new TryBodyNested(null);
  }

  public TryBodyNested<A> withNewTryBodyLike(Try item) {
    return new TryBodyNested(item);
  }

  public ValueRefLockExpressionNested<A> withNewValueRefLockExpression() {
    return new ValueRefLockExpressionNested(null);
  }

  public A withNewValueRefLockExpression(Object value) {
    return (A) withLockExpression(new ValueRef(value));
  }

  public ValueRefLockExpressionNested<A> withNewValueRefLockExpressionLike(ValueRef item) {
    return new ValueRefLockExpressionNested(item);
  }

  public WhileBodyNested<A> withNewWhileBody() {
    return new WhileBodyNested(null);
  }

  public WhileBodyNested<A> withNewWhileBodyLike(While item) {
    return new WhileBodyNested(item);
  }

  public XorBodyNested<A> withNewXorBody() {
    return new XorBodyNested(null);
  }

  public A withNewXorBody(Object left, Object right) {
    return (A) withBody(new Xor(left, right));
  }

  public XorBodyNested<A> withNewXorBodyLike(Xor item) {
    return new XorBodyNested(item);
  }

  public XorLockExpressionNested<A> withNewXorLockExpression() {
    return new XorLockExpressionNested(null);
  }

  public A withNewXorLockExpression(Object left, Object right) {
    return (A) withLockExpression(new Xor(left, right));
  }

  public XorLockExpressionNested<A> withNewXorLockExpressionLike(Xor item) {
    return new XorLockExpressionNested(item);
  }

  public class AssignBodyNested<N> extends AssignFluent<AssignBodyNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignBodyNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endAssignBody() {
      return and();
    }

  }

  public class AssignLockExpressionNested<N> extends AssignFluent<AssignLockExpressionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignLockExpressionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endAssignLockExpression() {
      return and();
    }

  }

  public class BinaryExpressionBodyNested<N> extends BinaryExpressionFluent<BinaryExpressionBodyNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionBodyNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endBinaryExpressionBody() {
      return and();
    }

  }

  public class BinaryExpressionLockNested<N> extends BinaryExpressionFluent<BinaryExpressionLockNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionLockNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endBinaryExpressionLock() {
      return and();
    }

  }

  public class BitwiseAndBodyNested<N> extends BitwiseAndFluent<BitwiseAndBodyNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndBodyNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endBitwiseAndBody() {
      return and();
    }

  }

  public class BitwiseAndLockExpressionNested<N> extends BitwiseAndFluent<BitwiseAndLockExpressionNested<N>>
      implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndLockExpressionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endBitwiseAndLockExpression() {
      return and();
    }

  }

  public class BitwiseOrBodyNested<N> extends BitwiseOrFluent<BitwiseOrBodyNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrBodyNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endBitwiseOrBody() {
      return and();
    }

  }

  public class BitwiseOrLockExpressionNested<N> extends BitwiseOrFluent<BitwiseOrLockExpressionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrLockExpressionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endBitwiseOrLockExpression() {
      return and();
    }

  }

  public class BlockBodyNested<N> extends BlockFluent<BlockBodyNested<N>> implements Nested<N> {

    BlockBuilder builder;

    BlockBodyNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endBlockBody() {
      return and();
    }

  }

  public class BreakBodyNested<N> extends BreakFluent<BreakBodyNested<N>> implements Nested<N> {

    BreakBuilder builder;

    BreakBodyNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endBreakBody() {
      return and();
    }

  }

  public class CastLockExpressionNested<N> extends CastFluent<CastLockExpressionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastLockExpressionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endCastLockExpression() {
      return and();
    }

  }

  public class ClassRefLockExpressionNested<N> extends ClassRefFluent<ClassRefLockExpressionNested<N>> implements Nested<N> {

    ClassRefBuilder builder;

    ClassRefLockExpressionNested(ClassRef item) {
      this.builder = new ClassRefBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endClassRefLockExpression() {
      return and();
    }

  }

  public class ConstructBodyNested<N> extends ConstructFluent<ConstructBodyNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructBodyNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endConstructBody() {
      return and();
    }

  }

  public class ConstructLockExpressionNested<N> extends ConstructFluent<ConstructLockExpressionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructLockExpressionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endConstructLockExpression() {
      return and();
    }

  }

  public class ContextRefLockExpressionNested<N> extends ContextRefFluent<ContextRefLockExpressionNested<N>>
      implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefLockExpressionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endContextRefLockExpression() {
      return and();
    }

  }

  public class ContinueBodyNested<N> extends ContinueFluent<ContinueBodyNested<N>> implements Nested<N> {

    ContinueBuilder builder;

    ContinueBodyNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endContinueBody() {
      return and();
    }

  }

  public class DeclareBodyNested<N> extends DeclareFluent<DeclareBodyNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareBodyNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endDeclareBody() {
      return and();
    }

  }

  public class DeclareLockExpressionNested<N> extends DeclareFluent<DeclareLockExpressionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareLockExpressionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endDeclareLockExpression() {
      return and();
    }

  }

  public class DivideBodyNested<N> extends DivideFluent<DivideBodyNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideBodyNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endDivideBody() {
      return and();
    }

  }

  public class DivideLockExpressionNested<N> extends DivideFluent<DivideLockExpressionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideLockExpressionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endDivideLockExpression() {
      return and();
    }

  }

  public class DoBodyNested<N> extends DoFluent<DoBodyNested<N>> implements Nested<N> {

    DoBuilder builder;

    DoBodyNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endDoBody() {
      return and();
    }

  }

  public class DotClassLockExpressionNested<N> extends DotClassFluent<DotClassLockExpressionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassLockExpressionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endDotClassLockExpression() {
      return and();
    }

  }

  public class EmptyBodyNested<N> extends EmptyFluent<EmptyBodyNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyBodyNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endEmptyBody() {
      return and();
    }

  }

  public class EmptyLockExpressionNested<N> extends EmptyFluent<EmptyLockExpressionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyLockExpressionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endEmptyLockExpression() {
      return and();
    }

  }

  public class EnclosedLockExpressionNested<N> extends EnclosedFluent<EnclosedLockExpressionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedLockExpressionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endEnclosedLockExpression() {
      return and();
    }

  }

  public class EqualsBodyNested<N> extends EqualsFluent<EqualsBodyNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsBodyNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endEqualsBody() {
      return and();
    }

  }

  public class EqualsLockExpressionNested<N> extends EqualsFluent<EqualsLockExpressionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsLockExpressionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endEqualsLockExpression() {
      return and();
    }

  }

  public class ForBodyNested<N> extends ForFluent<ForBodyNested<N>> implements Nested<N> {

    ForBuilder builder;

    ForBodyNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endForBody() {
      return and();
    }

  }

  public class ForeachBodyNested<N> extends ForeachFluent<ForeachBodyNested<N>> implements Nested<N> {

    ForeachBuilder builder;

    ForeachBodyNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endForeachBody() {
      return and();
    }

  }

  public class GreaterThanBodyNested<N> extends GreaterThanFluent<GreaterThanBodyNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanBodyNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endGreaterThanBody() {
      return and();
    }

  }

  public class GreaterThanLockExpressionNested<N> extends GreaterThanFluent<GreaterThanLockExpressionNested<N>>
      implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanLockExpressionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endGreaterThanLockExpression() {
      return and();
    }

  }

  public class GreaterThanOrEqualBodyNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualBodyNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualBodyNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endGreaterThanOrEqualBody() {
      return and();
    }

  }

  public class GreaterThanOrEqualLockExpressionNested<N>
      extends GreaterThanOrEqualFluent<GreaterThanOrEqualLockExpressionNested<N>> implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualLockExpressionNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endGreaterThanOrEqualLockExpression() {
      return and();
    }

  }

  public class IfBodyNested<N> extends IfFluent<IfBodyNested<N>> implements Nested<N> {

    IfBuilder builder;

    IfBodyNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endIfBody() {
      return and();
    }

  }

  public class IfDslThenStepBodyNested<N> extends IfDslThenStepFluent<IfDslThenStepBodyNested<N>> implements Nested<N> {

    IfDslThenStepBuilder builder;

    IfDslThenStepBodyNested(IfDslThenStep item) {
      this.builder = new IfDslThenStepBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endIfDslThenStepBody() {
      return and();
    }

  }

  public class IndexLockExpressionNested<N> extends IndexFluent<IndexLockExpressionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexLockExpressionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endIndexLockExpression() {
      return and();
    }

  }

  public class InstanceOfLockExpressionNested<N> extends InstanceOfFluent<InstanceOfLockExpressionNested<N>>
      implements Nested<N> {

    InstanceOfBuilder builder;

    InstanceOfLockExpressionNested(InstanceOf item) {
      this.builder = new InstanceOfBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endInstanceOfLockExpression() {
      return and();
    }

  }

  public class InverseLockExpressionNested<N> extends InverseFluent<InverseLockExpressionNested<N>> implements Nested<N> {

    InverseBuilder builder;

    InverseLockExpressionNested(Inverse item) {
      this.builder = new InverseBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endInverseLockExpression() {
      return and();
    }

  }

  public class LambdaBodyNested<N> extends LambdaFluent<LambdaBodyNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaBodyNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endLambdaBody() {
      return and();
    }

  }

  public class LambdaLockExpressionNested<N> extends LambdaFluent<LambdaLockExpressionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaLockExpressionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endLambdaLockExpression() {
      return and();
    }

  }

  public class LeftShiftBodyNested<N> extends LeftShiftFluent<LeftShiftBodyNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftBodyNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endLeftShiftBody() {
      return and();
    }

  }

  public class LeftShiftLockExpressionNested<N> extends LeftShiftFluent<LeftShiftLockExpressionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftLockExpressionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endLeftShiftLockExpression() {
      return and();
    }

  }

  public class LessThanBodyNested<N> extends LessThanFluent<LessThanBodyNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanBodyNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endLessThanBody() {
      return and();
    }

  }

  public class LessThanLockExpressionNested<N> extends LessThanFluent<LessThanLockExpressionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanLockExpressionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endLessThanLockExpression() {
      return and();
    }

  }

  public class LessThanOrEqualBodyNested<N> extends LessThanOrEqualFluent<LessThanOrEqualBodyNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualBodyNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endLessThanOrEqualBody() {
      return and();
    }

  }

  public class LessThanOrEqualLockExpressionNested<N> extends LessThanOrEqualFluent<LessThanOrEqualLockExpressionNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualLockExpressionNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endLessThanOrEqualLockExpression() {
      return and();
    }

  }

  public class LogicalAndBodyNested<N> extends LogicalAndFluent<LogicalAndBodyNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndBodyNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endLogicalAndBody() {
      return and();
    }

  }

  public class LogicalAndLockExpressionNested<N> extends LogicalAndFluent<LogicalAndLockExpressionNested<N>>
      implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndLockExpressionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endLogicalAndLockExpression() {
      return and();
    }

  }

  public class LogicalOrBodyNested<N> extends LogicalOrFluent<LogicalOrBodyNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrBodyNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endLogicalOrBody() {
      return and();
    }

  }

  public class LogicalOrLockExpressionNested<N> extends LogicalOrFluent<LogicalOrLockExpressionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrLockExpressionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endLogicalOrLockExpression() {
      return and();
    }

  }

  public class MethodCallBodyNested<N> extends MethodCallFluent<MethodCallBodyNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallBodyNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endMethodCallBody() {
      return and();
    }

  }

  public class MethodCallLockExpressionNested<N> extends MethodCallFluent<MethodCallLockExpressionNested<N>>
      implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallLockExpressionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endMethodCallLockExpression() {
      return and();
    }

  }

  public class MinusBodyNested<N> extends MinusFluent<MinusBodyNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusBodyNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endMinusBody() {
      return and();
    }

  }

  public class MinusLockExpressionNested<N> extends MinusFluent<MinusLockExpressionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusLockExpressionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endMinusLockExpression() {
      return and();
    }

  }

  public class ModuloBodyNested<N> extends ModuloFluent<ModuloBodyNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloBodyNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endModuloBody() {
      return and();
    }

  }

  public class ModuloLockExpressionNested<N> extends ModuloFluent<ModuloLockExpressionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloLockExpressionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endModuloLockExpression() {
      return and();
    }

  }

  public class MultiplyBodyNested<N> extends MultiplyFluent<MultiplyBodyNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyBodyNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endMultiplyBody() {
      return and();
    }

  }

  public class MultiplyLockExpressionNested<N> extends MultiplyFluent<MultiplyLockExpressionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyLockExpressionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endMultiplyLockExpression() {
      return and();
    }

  }

  public class NegativeLockExpressionNested<N> extends NegativeFluent<NegativeLockExpressionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeLockExpressionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endNegativeLockExpression() {
      return and();
    }

  }

  public class NewArrayLockExpressionNested<N> extends NewArrayFluent<NewArrayLockExpressionNested<N>> implements Nested<N> {

    NewArrayBuilder builder;

    NewArrayLockExpressionNested(NewArray item) {
      this.builder = new NewArrayBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endNewArrayLockExpression() {
      return and();
    }

  }

  public class NotEqualsBodyNested<N> extends NotEqualsFluent<NotEqualsBodyNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsBodyNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endNotEqualsBody() {
      return and();
    }

  }

  public class NotEqualsLockExpressionNested<N> extends NotEqualsFluent<NotEqualsLockExpressionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsLockExpressionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endNotEqualsLockExpression() {
      return and();
    }

  }

  public class NotLockExpressionNested<N> extends NotFluent<NotLockExpressionNested<N>> implements Nested<N> {

    NotBuilder builder;

    NotLockExpressionNested(Not item) {
      this.builder = new NotBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endNotLockExpression() {
      return and();
    }

  }

  public class PlusBodyNested<N> extends PlusFluent<PlusBodyNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusBodyNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endPlusBody() {
      return and();
    }

  }

  public class PlusLockExpressionNested<N> extends PlusFluent<PlusLockExpressionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusLockExpressionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPlusLockExpression() {
      return and();
    }

  }

  public class PositiveLockExpressionNested<N> extends PositiveFluent<PositiveLockExpressionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveLockExpressionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPositiveLockExpression() {
      return and();
    }

  }

  public class PostDecrementBodyNested<N> extends PostDecrementFluent<PostDecrementBodyNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementBodyNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endPostDecrementBody() {
      return and();
    }

  }

  public class PostDecrementLockExpressionNested<N> extends PostDecrementFluent<PostDecrementLockExpressionNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementLockExpressionNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPostDecrementLockExpression() {
      return and();
    }

  }

  public class PostIncrementBodyNested<N> extends PostIncrementFluent<PostIncrementBodyNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementBodyNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endPostIncrementBody() {
      return and();
    }

  }

  public class PostIncrementLockExpressionNested<N> extends PostIncrementFluent<PostIncrementLockExpressionNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementLockExpressionNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPostIncrementLockExpression() {
      return and();
    }

  }

  public class PreDecrementBodyNested<N> extends PreDecrementFluent<PreDecrementBodyNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementBodyNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endPreDecrementBody() {
      return and();
    }

  }

  public class PreDecrementLockExpressionNested<N> extends PreDecrementFluent<PreDecrementLockExpressionNested<N>>
      implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementLockExpressionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPreDecrementLockExpression() {
      return and();
    }

  }

  public class PreIncrementBodyNested<N> extends PreIncrementFluent<PreIncrementBodyNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementBodyNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endPreIncrementBody() {
      return and();
    }

  }

  public class PreIncrementLockExpressionNested<N> extends PreIncrementFluent<PreIncrementLockExpressionNested<N>>
      implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementLockExpressionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPreIncrementLockExpression() {
      return and();
    }

  }

  public class PropertyLockExpressionNested<N> extends PropertyFluent<PropertyLockExpressionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyLockExpressionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPropertyLockExpression() {
      return and();
    }

  }

  public class PropertyRefBodyNested<N> extends PropertyRefFluent<PropertyRefBodyNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefBodyNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endPropertyRefBody() {
      return and();
    }

  }

  public class PropertyRefLockExpressionNested<N> extends PropertyRefFluent<PropertyRefLockExpressionNested<N>>
      implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefLockExpressionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endPropertyRefLockExpression() {
      return and();
    }

  }

  public class ReturnBodyNested<N> extends ReturnFluent<ReturnBodyNested<N>> implements Nested<N> {

    ReturnBuilder builder;

    ReturnBodyNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endReturnBody() {
      return and();
    }

  }

  public class ReturnDslThisStepBodyNested<N> extends ReturnDslThisStepFluent<ReturnDslThisStepBodyNested<N>>
      implements Nested<N> {

    ReturnDslThisStepBuilder builder;

    ReturnDslThisStepBodyNested(ReturnDslThisStep item) {
      this.builder = new ReturnDslThisStepBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endReturnDslThisStepBody() {
      return and();
    }

  }

  public class ReturnDslVariableStepBodyNested<N> extends ReturnDslVariableStepFluent<ReturnDslVariableStepBodyNested<N>>
      implements Nested<N> {

    ReturnDslVariableStepBuilder builder;

    ReturnDslVariableStepBodyNested(ReturnDslVariableStep item) {
      this.builder = new ReturnDslVariableStepBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endReturnDslVariableStepBody() {
      return and();
    }

  }

  public class RightShiftBodyNested<N> extends RightShiftFluent<RightShiftBodyNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftBodyNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endRightShiftBody() {
      return and();
    }

  }

  public class RightShiftLockExpressionNested<N> extends RightShiftFluent<RightShiftLockExpressionNested<N>>
      implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftLockExpressionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endRightShiftLockExpression() {
      return and();
    }

  }

  public class RightUnsignedShiftBodyNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftBodyNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftBodyNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endRightUnsignedShiftBody() {
      return and();
    }

  }

  public class RightUnsignedShiftLockExpressionNested<N>
      extends RightUnsignedShiftFluent<RightUnsignedShiftLockExpressionNested<N>> implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftLockExpressionNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endRightUnsignedShiftLockExpression() {
      return and();
    }

  }

  public class StringStatementBodyNested<N> extends StringStatementFluent<StringStatementBodyNested<N>> implements Nested<N> {

    StringStatementBuilder builder;

    StringStatementBodyNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endStringStatementBody() {
      return and();
    }

  }

  public class SuperLockExpressionNested<N> extends SuperFluent<SuperLockExpressionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperLockExpressionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endSuperLockExpression() {
      return and();
    }

  }

  public class SwitchBodyNested<N> extends SwitchFluent<SwitchBodyNested<N>> implements Nested<N> {

    SwitchBuilder builder;

    SwitchBodyNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endSwitchBody() {
      return and();
    }

  }

  public class SynchronizedBodyNested<N> extends SynchronizedFluent<SynchronizedBodyNested<N>> implements Nested<N> {

    SynchronizedBuilder builder;

    SynchronizedBodyNested(Synchronized item) {
      this.builder = new SynchronizedBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endSynchronizedBody() {
      return and();
    }

  }

  public class TernaryLockExpressionNested<N> extends TernaryFluent<TernaryLockExpressionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryLockExpressionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endTernaryLockExpression() {
      return and();
    }

  }

  public class ThisLockExpressionNested<N> extends ThisFluent<ThisLockExpressionNested<N>> implements Nested<N> {

    ThisBuilder builder;

    ThisLockExpressionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endThisLockExpression() {
      return and();
    }

  }

  public class ThrowBodyNested<N> extends ThrowFluent<ThrowBodyNested<N>> implements Nested<N> {

    ThrowBuilder builder;

    ThrowBodyNested(Throw item) {
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endThrowBody() {
      return and();
    }

  }

  public class TryBodyNested<N> extends TryFluent<TryBodyNested<N>> implements Nested<N> {

    TryBuilder builder;

    TryBodyNested(Try item) {
      this.builder = new TryBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endTryBody() {
      return and();
    }

  }

  public class ValueRefLockExpressionNested<N> extends ValueRefFluent<ValueRefLockExpressionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefLockExpressionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endValueRefLockExpression() {
      return and();
    }

  }

  public class WhileBodyNested<N> extends WhileFluent<WhileBodyNested<N>> implements Nested<N> {

    WhileBuilder builder;

    WhileBodyNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endWhileBody() {
      return and();
    }

  }

  public class XorBodyNested<N> extends XorFluent<XorBodyNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorBodyNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withBody(builder.build());
    }

    public N endXorBody() {
      return and();
    }

  }

  public class XorLockExpressionNested<N> extends XorFluent<XorLockExpressionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorLockExpressionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) SynchronizedFluent.this.withLockExpression(builder.build());
    }

    public N endXorLockExpression() {
      return and();
    }

  }
}
