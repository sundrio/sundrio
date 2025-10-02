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
public class ForeachFluent<A extends ForeachFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Statement, ?> body;
  private DeclareBuilder declare;
  private VisitableBuilder<? extends Expression, ?> expression;

  public ForeachFluent() {
  }

  public ForeachFluent(Foreach instance) {
    this.copyInstance(instance);
  }

  public Statement buildBody() {
    return this.body != null ? this.body.build() : null;
  }

  public Declare buildDeclare() {
    return this.declare != null ? this.declare.build() : null;
  }

  public Expression buildExpression() {
    return this.expression != null ? this.expression.build() : null;
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

  protected void copyInstance(Foreach instance) {
    if (instance != null) {
      this.withDeclare(instance.getDeclare());
      this.withExpression(instance.getExpression());
      this.withBody(instance.getBody());
    }
  }

  public DeclareNested<A> editDeclare() {
    return withNewDeclareLike(java.util.Optional.ofNullable(buildDeclare()).orElse(null));
  }

  public DeclareNested<A> editOrNewDeclare() {
    return withNewDeclareLike(java.util.Optional.ofNullable(buildDeclare()).orElse(new DeclareBuilder().build()));
  }

  public DeclareNested<A> editOrNewDeclareLike(Declare item) {
    return withNewDeclareLike(java.util.Optional.ofNullable(buildDeclare()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ForeachFluent that = (ForeachFluent) o;
    if (!java.util.Objects.equals(declare, that.declare))
      return false;
    if (!java.util.Objects.equals(expression, that.expression))
      return false;
    if (!java.util.Objects.equals(body, that.body))
      return false;
    return true;
  }

  public boolean hasBody() {
    return this.body != null;
  }

  public boolean hasDeclare() {
    return this.declare != null;
  }

  public boolean hasExpression() {
    return this.expression != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(declare, expression, body, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (declare != null) {
      sb.append("declare:");
      sb.append(declare + ",");
    }
    if (expression != null) {
      sb.append("expression:");
      sb.append(expression + ",");
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

  public A withDeclare(Declare declare) {
    this._visitables.remove("declare");
    if (declare != null) {
      this.declare = new DeclareBuilder(declare);
      this._visitables.get("declare").add(this.declare);
    } else {
      this.declare = null;
      this._visitables.get("declare").remove(this.declare);
    }
    return (A) this;
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

  public AssignBodyNested<A> withNewAssignBody() {
    return new AssignBodyNested(null);
  }

  public AssignBodyNested<A> withNewAssignBodyLike(Assign item) {
    return new AssignBodyNested(item);
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

  public BinaryExpressionBodyNested<A> withNewBinaryExpressionBody() {
    return new BinaryExpressionBodyNested(null);
  }

  public BinaryExpressionBodyNested<A> withNewBinaryExpressionBodyLike(BinaryExpression item) {
    return new BinaryExpressionBodyNested(item);
  }

  public BinaryExpressionNested<A> withNewBinaryExpressionLike(BinaryExpression item) {
    return new BinaryExpressionNested(item);
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

  public BitwiseAndExpressionNested<A> withNewBitwiseAndExpression() {
    return new BitwiseAndExpressionNested(null);
  }

  public A withNewBitwiseAndExpression(Object left, Object right) {
    return (A) withExpression(new BitwiseAnd(left, right));
  }

  public BitwiseAndExpressionNested<A> withNewBitwiseAndExpressionLike(BitwiseAnd item) {
    return new BitwiseAndExpressionNested(item);
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

  public BitwiseOrExpressionNested<A> withNewBitwiseOrExpression() {
    return new BitwiseOrExpressionNested(null);
  }

  public A withNewBitwiseOrExpression(Object left, Object right) {
    return (A) withExpression(new BitwiseOr(left, right));
  }

  public BitwiseOrExpressionNested<A> withNewBitwiseOrExpressionLike(BitwiseOr item) {
    return new BitwiseOrExpressionNested(item);
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

  public ConstructBodyNested<A> withNewConstructBody() {
    return new ConstructBodyNested(null);
  }

  public ConstructBodyNested<A> withNewConstructBodyLike(Construct item) {
    return new ConstructBodyNested(item);
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
    return (A) withExpression(new ContextRef(name));
  }

  public ContextRefExpressionNested<A> withNewContextRefExpressionLike(ContextRef item) {
    return new ContextRefExpressionNested(item);
  }

  public ContinueBodyNested<A> withNewContinueBody() {
    return new ContinueBodyNested(null);
  }

  public ContinueBodyNested<A> withNewContinueBodyLike(Continue item) {
    return new ContinueBodyNested(item);
  }

  public DeclareNested<A> withNewDeclare() {
    return new DeclareNested(null);
  }

  public A withNewDeclare(Class type, String name) {
    return (A) withDeclare(new Declare(type, name));
  }

  public A withNewDeclare(Class type, String name, Object value) {
    return (A) withDeclare(new Declare(type, name, value));
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

  public DeclareExpressionNested<A> withNewDeclareExpression() {
    return new DeclareExpressionNested(null);
  }

  public A withNewDeclareExpression(Class type, String name) {
    return (A) withExpression(new Declare(type, name));
  }

  public A withNewDeclareExpression(Class type, String name, Object value) {
    return (A) withExpression(new Declare(type, name, value));
  }

  public DeclareExpressionNested<A> withNewDeclareExpressionLike(Declare item) {
    return new DeclareExpressionNested(item);
  }

  public DeclareNested<A> withNewDeclareLike(Declare item) {
    return new DeclareNested(item);
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

  public DivideExpressionNested<A> withNewDivideExpression() {
    return new DivideExpressionNested(null);
  }

  public A withNewDivideExpression(Object left, Object right) {
    return (A) withExpression(new Divide(left, right));
  }

  public DivideExpressionNested<A> withNewDivideExpressionLike(Divide item) {
    return new DivideExpressionNested(item);
  }

  public DoBodyNested<A> withNewDoBody() {
    return new DoBodyNested(null);
  }

  public DoBodyNested<A> withNewDoBodyLike(Do item) {
    return new DoBodyNested(item);
  }

  public DotClassExpressionNested<A> withNewDotClassExpression() {
    return new DotClassExpressionNested(null);
  }

  public DotClassExpressionNested<A> withNewDotClassExpressionLike(DotClass item) {
    return new DotClassExpressionNested(item);
  }

  public EmptyBodyNested<A> withNewEmptyBody() {
    return new EmptyBodyNested(null);
  }

  public EmptyBodyNested<A> withNewEmptyBodyLike(Empty item) {
    return new EmptyBodyNested(item);
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

  public EqualsBodyNested<A> withNewEqualsBody() {
    return new EqualsBodyNested(null);
  }

  public A withNewEqualsBody(Object left, Object right) {
    return (A) withBody(new Equals(left, right));
  }

  public EqualsBodyNested<A> withNewEqualsBodyLike(Equals item) {
    return new EqualsBodyNested(item);
  }

  public EqualsExpressionNested<A> withNewEqualsExpression() {
    return new EqualsExpressionNested(null);
  }

  public A withNewEqualsExpression(Object left, Object right) {
    return (A) withExpression(new Equals(left, right));
  }

  public EqualsExpressionNested<A> withNewEqualsExpressionLike(Equals item) {
    return new EqualsExpressionNested(item);
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

  public GreaterThanExpressionNested<A> withNewGreaterThanExpression() {
    return new GreaterThanExpressionNested(null);
  }

  public A withNewGreaterThanExpression(Object left, Object right) {
    return (A) withExpression(new GreaterThan(left, right));
  }

  public GreaterThanExpressionNested<A> withNewGreaterThanExpressionLike(GreaterThan item) {
    return new GreaterThanExpressionNested(item);
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

  public GreaterThanOrEqualExpressionNested<A> withNewGreaterThanOrEqualExpression() {
    return new GreaterThanOrEqualExpressionNested(null);
  }

  public A withNewGreaterThanOrEqualExpression(Object left, Object right) {
    return (A) withExpression(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualExpressionNested<A> withNewGreaterThanOrEqualExpressionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualExpressionNested(item);
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

  public LambdaBodyNested<A> withNewLambdaBody() {
    return new LambdaBodyNested(null);
  }

  public LambdaBodyNested<A> withNewLambdaBodyLike(Lambda item) {
    return new LambdaBodyNested(item);
  }

  public LambdaExpressionNested<A> withNewLambdaExpression() {
    return new LambdaExpressionNested(null);
  }

  public LambdaExpressionNested<A> withNewLambdaExpressionLike(Lambda item) {
    return new LambdaExpressionNested(item);
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

  public LeftShiftExpressionNested<A> withNewLeftShiftExpression() {
    return new LeftShiftExpressionNested(null);
  }

  public A withNewLeftShiftExpression(Object left, Object right) {
    return (A) withExpression(new LeftShift(left, right));
  }

  public LeftShiftExpressionNested<A> withNewLeftShiftExpressionLike(LeftShift item) {
    return new LeftShiftExpressionNested(item);
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

  public LessThanExpressionNested<A> withNewLessThanExpression() {
    return new LessThanExpressionNested(null);
  }

  public A withNewLessThanExpression(Object left, Object right) {
    return (A) withExpression(new LessThan(left, right));
  }

  public LessThanExpressionNested<A> withNewLessThanExpressionLike(LessThan item) {
    return new LessThanExpressionNested(item);
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

  public LessThanOrEqualExpressionNested<A> withNewLessThanOrEqualExpression() {
    return new LessThanOrEqualExpressionNested(null);
  }

  public A withNewLessThanOrEqualExpression(Object left, Object right) {
    return (A) withExpression(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualExpressionNested<A> withNewLessThanOrEqualExpressionLike(LessThanOrEqual item) {
    return new LessThanOrEqualExpressionNested(item);
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

  public LogicalAndExpressionNested<A> withNewLogicalAndExpression() {
    return new LogicalAndExpressionNested(null);
  }

  public A withNewLogicalAndExpression(Object left, Object right) {
    return (A) withExpression(new LogicalAnd(left, right));
  }

  public LogicalAndExpressionNested<A> withNewLogicalAndExpressionLike(LogicalAnd item) {
    return new LogicalAndExpressionNested(item);
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

  public LogicalOrExpressionNested<A> withNewLogicalOrExpression() {
    return new LogicalOrExpressionNested(null);
  }

  public A withNewLogicalOrExpression(Object left, Object right) {
    return (A) withExpression(new LogicalOr(left, right));
  }

  public LogicalOrExpressionNested<A> withNewLogicalOrExpressionLike(LogicalOr item) {
    return new LogicalOrExpressionNested(item);
  }

  public MethodCallBodyNested<A> withNewMethodCallBody() {
    return new MethodCallBodyNested(null);
  }

  public MethodCallBodyNested<A> withNewMethodCallBodyLike(MethodCall item) {
    return new MethodCallBodyNested(item);
  }

  public MethodCallExpressionNested<A> withNewMethodCallExpression() {
    return new MethodCallExpressionNested(null);
  }

  public MethodCallExpressionNested<A> withNewMethodCallExpressionLike(MethodCall item) {
    return new MethodCallExpressionNested(item);
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

  public MinusExpressionNested<A> withNewMinusExpression() {
    return new MinusExpressionNested(null);
  }

  public A withNewMinusExpression(Object left, Object right) {
    return (A) withExpression(new Minus(left, right));
  }

  public MinusExpressionNested<A> withNewMinusExpressionLike(Minus item) {
    return new MinusExpressionNested(item);
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

  public ModuloExpressionNested<A> withNewModuloExpression() {
    return new ModuloExpressionNested(null);
  }

  public A withNewModuloExpression(Object left, Object right) {
    return (A) withExpression(new Modulo(left, right));
  }

  public ModuloExpressionNested<A> withNewModuloExpressionLike(Modulo item) {
    return new ModuloExpressionNested(item);
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

  public MultiplyExpressionNested<A> withNewMultiplyExpression() {
    return new MultiplyExpressionNested(null);
  }

  public A withNewMultiplyExpression(Object left, Object right) {
    return (A) withExpression(new Multiply(left, right));
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
    return (A) withExpression(new NewArray(type, sizes));
  }

  public NewArrayExpressionNested<A> withNewNewArrayExpressionLike(NewArray item) {
    return new NewArrayExpressionNested(item);
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

  public NotEqualsExpressionNested<A> withNewNotEqualsExpression() {
    return new NotEqualsExpressionNested(null);
  }

  public A withNewNotEqualsExpression(Object left, Object right) {
    return (A) withExpression(new NotEquals(left, right));
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

  public PlusBodyNested<A> withNewPlusBody() {
    return new PlusBodyNested(null);
  }

  public A withNewPlusBody(Object left, Object right) {
    return (A) withBody(new Plus(left, right));
  }

  public PlusBodyNested<A> withNewPlusBodyLike(Plus item) {
    return new PlusBodyNested(item);
  }

  public PlusExpressionNested<A> withNewPlusExpression() {
    return new PlusExpressionNested(null);
  }

  public A withNewPlusExpression(Object left, Object right) {
    return (A) withExpression(new Plus(left, right));
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

  public PostDecrementBodyNested<A> withNewPostDecrementBody() {
    return new PostDecrementBodyNested(null);
  }

  public PostDecrementBodyNested<A> withNewPostDecrementBodyLike(PostDecrement item) {
    return new PostDecrementBodyNested(item);
  }

  public PostDecrementExpressionNested<A> withNewPostDecrementExpression() {
    return new PostDecrementExpressionNested(null);
  }

  public PostDecrementExpressionNested<A> withNewPostDecrementExpressionLike(PostDecrement item) {
    return new PostDecrementExpressionNested(item);
  }

  public PostIncrementBodyNested<A> withNewPostIncrementBody() {
    return new PostIncrementBodyNested(null);
  }

  public PostIncrementBodyNested<A> withNewPostIncrementBodyLike(PostIncrement item) {
    return new PostIncrementBodyNested(item);
  }

  public PostIncrementExpressionNested<A> withNewPostIncrementExpression() {
    return new PostIncrementExpressionNested(null);
  }

  public PostIncrementExpressionNested<A> withNewPostIncrementExpressionLike(PostIncrement item) {
    return new PostIncrementExpressionNested(item);
  }

  public PreDecrementBodyNested<A> withNewPreDecrementBody() {
    return new PreDecrementBodyNested(null);
  }

  public PreDecrementBodyNested<A> withNewPreDecrementBodyLike(PreDecrement item) {
    return new PreDecrementBodyNested(item);
  }

  public PreDecrementExpressionNested<A> withNewPreDecrementExpression() {
    return new PreDecrementExpressionNested(null);
  }

  public PreDecrementExpressionNested<A> withNewPreDecrementExpressionLike(PreDecrement item) {
    return new PreDecrementExpressionNested(item);
  }

  public PreIncrementBodyNested<A> withNewPreIncrementBody() {
    return new PreIncrementBodyNested(null);
  }

  public PreIncrementBodyNested<A> withNewPreIncrementBodyLike(PreIncrement item) {
    return new PreIncrementBodyNested(item);
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

  public PropertyRefBodyNested<A> withNewPropertyRefBody() {
    return new PropertyRefBodyNested(null);
  }

  public PropertyRefBodyNested<A> withNewPropertyRefBodyLike(PropertyRef item) {
    return new PropertyRefBodyNested(item);
  }

  public PropertyRefExpressionNested<A> withNewPropertyRefExpression() {
    return new PropertyRefExpressionNested(null);
  }

  public PropertyRefExpressionNested<A> withNewPropertyRefExpressionLike(PropertyRef item) {
    return new PropertyRefExpressionNested(item);
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

  public RightShiftExpressionNested<A> withNewRightShiftExpression() {
    return new RightShiftExpressionNested(null);
  }

  public A withNewRightShiftExpression(Object left, Object right) {
    return (A) withExpression(new RightShift(left, right));
  }

  public RightShiftExpressionNested<A> withNewRightShiftExpressionLike(RightShift item) {
    return new RightShiftExpressionNested(item);
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

  public RightUnsignedShiftExpressionNested<A> withNewRightUnsignedShiftExpression() {
    return new RightUnsignedShiftExpressionNested(null);
  }

  public A withNewRightUnsignedShiftExpression(Object left, Object right) {
    return (A) withExpression(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftExpressionNested<A> withNewRightUnsignedShiftExpressionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftExpressionNested(item);
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

  public SuperExpressionNested<A> withNewSuperExpression() {
    return new SuperExpressionNested(null);
  }

  public SuperExpressionNested<A> withNewSuperExpressionLike(Super item) {
    return new SuperExpressionNested(item);
  }

  public SwitchBodyNested<A> withNewSwitchBody() {
    return new SwitchBodyNested(null);
  }

  public SwitchBodyNested<A> withNewSwitchBodyLike(Switch item) {
    return new SwitchBodyNested(item);
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

  public ValueRefExpressionNested<A> withNewValueRefExpression() {
    return new ValueRefExpressionNested(null);
  }

  public A withNewValueRefExpression(Object value) {
    return (A) withExpression(new ValueRef(value));
  }

  public ValueRefExpressionNested<A> withNewValueRefExpressionLike(ValueRef item) {
    return new ValueRefExpressionNested(item);
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

  public XorExpressionNested<A> withNewXorExpression() {
    return new XorExpressionNested(null);
  }

  public A withNewXorExpression(Object left, Object right) {
    return (A) withExpression(new Xor(left, right));
  }

  public XorExpressionNested<A> withNewXorExpressionLike(Xor item) {
    return new XorExpressionNested(item);
  }

  public class AssignBodyNested<N> extends AssignFluent<AssignBodyNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignBodyNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endAssignBody() {
      return and();
    }

  }

  public class AssignExpressionNested<N> extends AssignFluent<AssignExpressionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignExpressionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endAssignExpression() {
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endBinaryExpressionBody() {
      return and();
    }

  }

  public class BinaryExpressionNested<N> extends BinaryExpressionFluent<BinaryExpressionNested<N>> implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endBinaryExpression() {
      return and();
    }

  }

  public class BitwiseAndBodyNested<N> extends BitwiseAndFluent<BitwiseAndBodyNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndBodyNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endBitwiseAndBody() {
      return and();
    }

  }

  public class BitwiseAndExpressionNested<N> extends BitwiseAndFluent<BitwiseAndExpressionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndExpressionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endBitwiseAndExpression() {
      return and();
    }

  }

  public class BitwiseOrBodyNested<N> extends BitwiseOrFluent<BitwiseOrBodyNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrBodyNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endBitwiseOrBody() {
      return and();
    }

  }

  public class BitwiseOrExpressionNested<N> extends BitwiseOrFluent<BitwiseOrExpressionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrExpressionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endBitwiseOrExpression() {
      return and();
    }

  }

  public class BlockBodyNested<N> extends BlockFluent<BlockBodyNested<N>> implements Nested<N> {

    BlockBuilder builder;

    BlockBodyNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endBreakBody() {
      return and();
    }

  }

  public class CastExpressionNested<N> extends CastFluent<CastExpressionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastExpressionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endClassRefExpression() {
      return and();
    }

  }

  public class ConstructBodyNested<N> extends ConstructFluent<ConstructBodyNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructBodyNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endConstructBody() {
      return and();
    }

  }

  public class ConstructExpressionNested<N> extends ConstructFluent<ConstructExpressionNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructExpressionNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endContextRefExpression() {
      return and();
    }

  }

  public class ContinueBodyNested<N> extends ContinueFluent<ContinueBodyNested<N>> implements Nested<N> {

    ContinueBuilder builder;

    ContinueBodyNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endDeclareBody() {
      return and();
    }

  }

  public class DeclareExpressionNested<N> extends DeclareFluent<DeclareExpressionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareExpressionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endDeclareExpression() {
      return and();
    }

  }

  public class DeclareNested<N> extends DeclareFluent<DeclareNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withDeclare(builder.build());
    }

    public N endDeclare() {
      return and();
    }

  }

  public class DivideBodyNested<N> extends DivideFluent<DivideBodyNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideBodyNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endDivideBody() {
      return and();
    }

  }

  public class DivideExpressionNested<N> extends DivideFluent<DivideExpressionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideExpressionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endDivideExpression() {
      return and();
    }

  }

  public class DoBodyNested<N> extends DoFluent<DoBodyNested<N>> implements Nested<N> {

    DoBuilder builder;

    DoBodyNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endDoBody() {
      return and();
    }

  }

  public class DotClassExpressionNested<N> extends DotClassFluent<DotClassExpressionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassExpressionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endDotClassExpression() {
      return and();
    }

  }

  public class EmptyBodyNested<N> extends EmptyFluent<EmptyBodyNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyBodyNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endEmptyBody() {
      return and();
    }

  }

  public class EmptyExpressionNested<N> extends EmptyFluent<EmptyExpressionNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyExpressionNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endEnclosedExpression() {
      return and();
    }

  }

  public class EqualsBodyNested<N> extends EqualsFluent<EqualsBodyNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsBodyNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endEqualsBody() {
      return and();
    }

  }

  public class EqualsExpressionNested<N> extends EqualsFluent<EqualsExpressionNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsExpressionNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endEqualsExpression() {
      return and();
    }

  }

  public class ForBodyNested<N> extends ForFluent<ForBodyNested<N>> implements Nested<N> {

    ForBuilder builder;

    ForBodyNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endGreaterThanBody() {
      return and();
    }

  }

  public class GreaterThanExpressionNested<N> extends GreaterThanFluent<GreaterThanExpressionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanExpressionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endGreaterThanExpression() {
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endGreaterThanOrEqualBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endGreaterThanOrEqualExpression() {
      return and();
    }

  }

  public class IfBodyNested<N> extends IfFluent<IfBodyNested<N>> implements Nested<N> {

    IfBuilder builder;

    IfBodyNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endIfDslThenStepBody() {
      return and();
    }

  }

  public class IndexExpressionNested<N> extends IndexFluent<IndexExpressionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexExpressionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endInverseExpression() {
      return and();
    }

  }

  public class LambdaBodyNested<N> extends LambdaFluent<LambdaBodyNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaBodyNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endLambdaBody() {
      return and();
    }

  }

  public class LambdaExpressionNested<N> extends LambdaFluent<LambdaExpressionNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaExpressionNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endLambdaExpression() {
      return and();
    }

  }

  public class LeftShiftBodyNested<N> extends LeftShiftFluent<LeftShiftBodyNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftBodyNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endLeftShiftBody() {
      return and();
    }

  }

  public class LeftShiftExpressionNested<N> extends LeftShiftFluent<LeftShiftExpressionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftExpressionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endLeftShiftExpression() {
      return and();
    }

  }

  public class LessThanBodyNested<N> extends LessThanFluent<LessThanBodyNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanBodyNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endLessThanBody() {
      return and();
    }

  }

  public class LessThanExpressionNested<N> extends LessThanFluent<LessThanExpressionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanExpressionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endLessThanExpression() {
      return and();
    }

  }

  public class LessThanOrEqualBodyNested<N> extends LessThanOrEqualFluent<LessThanOrEqualBodyNested<N>> implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualBodyNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endLessThanOrEqualBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endLessThanOrEqualExpression() {
      return and();
    }

  }

  public class LogicalAndBodyNested<N> extends LogicalAndFluent<LogicalAndBodyNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndBodyNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endLogicalAndBody() {
      return and();
    }

  }

  public class LogicalAndExpressionNested<N> extends LogicalAndFluent<LogicalAndExpressionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndExpressionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endLogicalAndExpression() {
      return and();
    }

  }

  public class LogicalOrBodyNested<N> extends LogicalOrFluent<LogicalOrBodyNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrBodyNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endLogicalOrBody() {
      return and();
    }

  }

  public class LogicalOrExpressionNested<N> extends LogicalOrFluent<LogicalOrExpressionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrExpressionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endLogicalOrExpression() {
      return and();
    }

  }

  public class MethodCallBodyNested<N> extends MethodCallFluent<MethodCallBodyNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallBodyNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endMethodCallBody() {
      return and();
    }

  }

  public class MethodCallExpressionNested<N> extends MethodCallFluent<MethodCallExpressionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallExpressionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endMethodCallExpression() {
      return and();
    }

  }

  public class MinusBodyNested<N> extends MinusFluent<MinusBodyNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusBodyNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endMinusBody() {
      return and();
    }

  }

  public class MinusExpressionNested<N> extends MinusFluent<MinusExpressionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusExpressionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endMinusExpression() {
      return and();
    }

  }

  public class ModuloBodyNested<N> extends ModuloFluent<ModuloBodyNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloBodyNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endModuloBody() {
      return and();
    }

  }

  public class ModuloExpressionNested<N> extends ModuloFluent<ModuloExpressionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloExpressionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endModuloExpression() {
      return and();
    }

  }

  public class MultiplyBodyNested<N> extends MultiplyFluent<MultiplyBodyNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyBodyNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endMultiplyBody() {
      return and();
    }

  }

  public class MultiplyExpressionNested<N> extends MultiplyFluent<MultiplyExpressionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyExpressionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endNewArrayExpression() {
      return and();
    }

  }

  public class NotEqualsBodyNested<N> extends NotEqualsFluent<NotEqualsBodyNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsBodyNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endNotEqualsBody() {
      return and();
    }

  }

  public class NotEqualsExpressionNested<N> extends NotEqualsFluent<NotEqualsExpressionNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsExpressionNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endNotExpression() {
      return and();
    }

  }

  public class PlusBodyNested<N> extends PlusFluent<PlusBodyNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusBodyNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endPlusBody() {
      return and();
    }

  }

  public class PlusExpressionNested<N> extends PlusFluent<PlusExpressionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusExpressionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endPositiveExpression() {
      return and();
    }

  }

  public class PostDecrementBodyNested<N> extends PostDecrementFluent<PostDecrementBodyNested<N>> implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementBodyNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endPostDecrementBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endPostDecrementExpression() {
      return and();
    }

  }

  public class PostIncrementBodyNested<N> extends PostIncrementFluent<PostIncrementBodyNested<N>> implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementBodyNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endPostIncrementBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endPostIncrementExpression() {
      return and();
    }

  }

  public class PreDecrementBodyNested<N> extends PreDecrementFluent<PreDecrementBodyNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementBodyNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endPreDecrementBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endPreDecrementExpression() {
      return and();
    }

  }

  public class PreIncrementBodyNested<N> extends PreIncrementFluent<PreIncrementBodyNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementBodyNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endPreIncrementBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endPropertyExpression() {
      return and();
    }

  }

  public class PropertyRefBodyNested<N> extends PropertyRefFluent<PropertyRefBodyNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefBodyNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endPropertyRefBody() {
      return and();
    }

  }

  public class PropertyRefExpressionNested<N> extends PropertyRefFluent<PropertyRefExpressionNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefExpressionNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endPropertyRefExpression() {
      return and();
    }

  }

  public class ReturnBodyNested<N> extends ReturnFluent<ReturnBodyNested<N>> implements Nested<N> {

    ReturnBuilder builder;

    ReturnBodyNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endRightShiftBody() {
      return and();
    }

  }

  public class RightShiftExpressionNested<N> extends RightShiftFluent<RightShiftExpressionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftExpressionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endRightShiftExpression() {
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endRightUnsignedShiftBody() {
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endRightUnsignedShiftExpression() {
      return and();
    }

  }

  public class StringStatementBodyNested<N> extends StringStatementFluent<StringStatementBodyNested<N>> implements Nested<N> {

    StringStatementBuilder builder;

    StringStatementBodyNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endStringStatementBody() {
      return and();
    }

  }

  public class SuperExpressionNested<N> extends SuperFluent<SuperExpressionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperExpressionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endSuperExpression() {
      return and();
    }

  }

  public class SwitchBodyNested<N> extends SwitchFluent<SwitchBodyNested<N>> implements Nested<N> {

    SwitchBuilder builder;

    SwitchBodyNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endSwitchBody() {
      return and();
    }

  }

  public class TernaryExpressionNested<N> extends TernaryFluent<TernaryExpressionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryExpressionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
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
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endThisExpression() {
      return and();
    }

  }

  public class ThrowBodyNested<N> extends ThrowFluent<ThrowBodyNested<N>> implements Nested<N> {

    ThrowBuilder builder;

    ThrowBodyNested(Throw item) {
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endTryBody() {
      return and();
    }

  }

  public class ValueRefExpressionNested<N> extends ValueRefFluent<ValueRefExpressionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefExpressionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endValueRefExpression() {
      return and();
    }

  }

  public class WhileBodyNested<N> extends WhileFluent<WhileBodyNested<N>> implements Nested<N> {

    WhileBuilder builder;

    WhileBodyNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withBody(builder.build());
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
      return (N) ForeachFluent.this.withBody(builder.build());
    }

    public N endXorBody() {
      return and();
    }

  }

  public class XorExpressionNested<N> extends XorFluent<XorExpressionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorExpressionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) ForeachFluent.this.withExpression(builder.build());
    }

    public N endXorExpression() {
      return and();
    }

  }
}
