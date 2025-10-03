package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;
import java.util.Optional;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class IfFluent<A extends io.sundr.model.IfFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> condition;
  private Optional<VisitableBuilder<? extends Statement, ?>> elseStatement = Optional.empty();
  private VisitableBuilder<? extends Statement, ?> statement;

  public IfFluent() {
  }

  public IfFluent(If instance) {
    this.copyInstance(instance);
  }

  public Expression buildCondition() {
    return this.condition != null ? this.condition.build() : null;
  }

  public Optional<Statement> buildElseStatement() {
    return this.elseStatement != null ? this.elseStatement.map(v -> v.build()) : Optional.empty();
  }

  public Statement buildStatement() {
    return this.statement != null ? this.statement.build() : null;
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

      case "ReturnDslThisStep":

        return (VisitableBuilder<T, ?>) new ReturnDslThisStepBuilder((ReturnDslThisStep) item);

      case "Try":

        return (VisitableBuilder<T, ?>) new TryBuilder((Try) item);

      case "Switch":

        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);

      case "Synchronized":

        return (VisitableBuilder<T, ?>) new SynchronizedBuilder((Synchronized) item);

      case "Break":

        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);

      case "While":

        return (VisitableBuilder<T, ?>) new WhileBuilder((While) item);

      case "Continue":

        return (VisitableBuilder<T, ?>) new ContinueBuilder((Continue) item);

      case "Throw":

        return (VisitableBuilder<T, ?>) new ThrowBuilder((Throw) item);

      case "StringStatement":

        return (VisitableBuilder<T, ?>) new StringStatementBuilder((StringStatement) item);

      case "Do":

        return (VisitableBuilder<T, ?>) new DoBuilder((Do) item);

      case "Foreach":

        return (VisitableBuilder<T, ?>) new ForeachBuilder((Foreach) item);

      case "Block":

        return (VisitableBuilder<T, ?>) new BlockBuilder((Block) item);

      case "ReturnDslVariableStep":

        return (VisitableBuilder<T, ?>) new ReturnDslVariableStepBuilder((ReturnDslVariableStep) item);

      case "If":

        return (VisitableBuilder<T, ?>) new IfBuilder((If) item);

      case "Return":

        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);

      case "IfDslThenStep":

        return (VisitableBuilder<T, ?>) new IfDslThenStepBuilder((IfDslThenStep) item);

      case "For":

        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
  }

  protected void copyInstance(If instance) {
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withStatement(instance.getStatement());
      this.withElseStatement(instance.getElseStatement());
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
    IfFluent that = (IfFluent) o;
    if (!(Objects.equals(condition, that.condition))) {
      return false;
    }
    if (!(Objects.equals(statement, that.statement))) {
      return false;
    }
    if (!(Objects.equals(elseStatement, that.elseStatement))) {
      return false;
    }
    return true;
  }

  public boolean hasCondition() {
    return this.condition != null;
  }

  public boolean hasElseStatement() {
    return this.elseStatement != null && this.elseStatement.isPresent();
  }

  public boolean hasStatement() {
    return this.statement != null;
  }

  public int hashCode() {
    return Objects.hash(condition, statement, elseStatement);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(condition == null)) {
      sb.append("condition:");
      sb.append(condition);
      sb.append(",");
    }
    if (!(statement == null)) {
      sb.append("statement:");
      sb.append(statement);
      sb.append(",");
    }
    if (!(elseStatement == null)) {
      sb.append("elseStatement:");
      sb.append(elseStatement);
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

  public A withElseStatement(Optional<Statement> elseStatement) {
    if (elseStatement == null || !(elseStatement.isPresent())) {
      this.elseStatement = Optional.empty();
    } else {
      VisitableBuilder<? extends Statement, ?> b = builder(elseStatement.get());
      _visitables.get("elseStatement").add(b);
      this.elseStatement = Optional.of(b);
    }
    return (A) this;
  }

  public A withElseStatement(Statement elseStatement) {
    if (elseStatement == null) {
      this.elseStatement = Optional.empty();
    } else {
      VisitableBuilder<? extends Statement, ?> b = builder(elseStatement);
      _visitables.get("elseStatement").add(b);
      this.elseStatement = Optional.of(b);
    }
    return (A) this;
  }

  public AssignConditionNested<A> withNewAssignCondition() {
    return new AssignConditionNested(null);
  }

  public AssignConditionNested<A> withNewAssignConditionLike(Assign item) {
    return new AssignConditionNested(item);
  }

  public AssignElseStatementNested<A> withNewAssignElseStatement() {
    return new AssignElseStatementNested(null);
  }

  public AssignElseStatementNested<A> withNewAssignElseStatementLike(Assign item) {
    return new AssignElseStatementNested(item);
  }

  public AssignStatementNested<A> withNewAssignStatement() {
    return new AssignStatementNested(null);
  }

  public AssignStatementNested<A> withNewAssignStatementLike(Assign item) {
    return new AssignStatementNested(item);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionCondition() {
    return new BinaryExpressionConditionNested(null);
  }

  public BinaryExpressionConditionNested<A> withNewBinaryExpressionConditionLike(BinaryExpression item) {
    return new BinaryExpressionConditionNested(item);
  }

  public BinaryExpressionElseStatementNested<A> withNewBinaryExpressionElseStatement() {
    return new BinaryExpressionElseStatementNested(null);
  }

  public BinaryExpressionElseStatementNested<A> withNewBinaryExpressionElseStatementLike(BinaryExpression item) {
    return new BinaryExpressionElseStatementNested(item);
  }

  public BinaryExpressionStatementNested<A> withNewBinaryExpressionStatement() {
    return new BinaryExpressionStatementNested(null);
  }

  public BinaryExpressionStatementNested<A> withNewBinaryExpressionStatementLike(BinaryExpression item) {
    return new BinaryExpressionStatementNested(item);
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndCondition() {
    return new BitwiseAndConditionNested(null);
  }

  public A withNewBitwiseAndCondition(Object left, Object right) {
    return (A) this.withCondition(new BitwiseAnd(left, right));
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndConditionLike(BitwiseAnd item) {
    return new BitwiseAndConditionNested(item);
  }

  public BitwiseAndElseStatementNested<A> withNewBitwiseAndElseStatement() {
    return new BitwiseAndElseStatementNested(null);
  }

  public A withNewBitwiseAndElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new BitwiseAnd(left, right));
  }

  public BitwiseAndElseStatementNested<A> withNewBitwiseAndElseStatementLike(BitwiseAnd item) {
    return new BitwiseAndElseStatementNested(item);
  }

  public BitwiseAndStatementNested<A> withNewBitwiseAndStatement() {
    return new BitwiseAndStatementNested(null);
  }

  public A withNewBitwiseAndStatement(Object left, Object right) {
    return (A) this.withStatement(new BitwiseAnd(left, right));
  }

  public BitwiseAndStatementNested<A> withNewBitwiseAndStatementLike(BitwiseAnd item) {
    return new BitwiseAndStatementNested(item);
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrCondition() {
    return new BitwiseOrConditionNested(null);
  }

  public A withNewBitwiseOrCondition(Object left, Object right) {
    return (A) this.withCondition(new BitwiseOr(left, right));
  }

  public BitwiseOrConditionNested<A> withNewBitwiseOrConditionLike(BitwiseOr item) {
    return new BitwiseOrConditionNested(item);
  }

  public BitwiseOrElseStatementNested<A> withNewBitwiseOrElseStatement() {
    return new BitwiseOrElseStatementNested(null);
  }

  public A withNewBitwiseOrElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new BitwiseOr(left, right));
  }

  public BitwiseOrElseStatementNested<A> withNewBitwiseOrElseStatementLike(BitwiseOr item) {
    return new BitwiseOrElseStatementNested(item);
  }

  public BitwiseOrStatementNested<A> withNewBitwiseOrStatement() {
    return new BitwiseOrStatementNested(null);
  }

  public A withNewBitwiseOrStatement(Object left, Object right) {
    return (A) this.withStatement(new BitwiseOr(left, right));
  }

  public BitwiseOrStatementNested<A> withNewBitwiseOrStatementLike(BitwiseOr item) {
    return new BitwiseOrStatementNested(item);
  }

  public BlockElseStatementNested<A> withNewBlockElseStatement() {
    return new BlockElseStatementNested(null);
  }

  public BlockElseStatementNested<A> withNewBlockElseStatementLike(Block item) {
    return new BlockElseStatementNested(item);
  }

  public BlockStatementNested<A> withNewBlockStatement() {
    return new BlockStatementNested(null);
  }

  public BlockStatementNested<A> withNewBlockStatementLike(Block item) {
    return new BlockStatementNested(item);
  }

  public BreakElseStatementNested<A> withNewBreakElseStatement() {
    return new BreakElseStatementNested(null);
  }

  public BreakElseStatementNested<A> withNewBreakElseStatementLike(Break item) {
    return new BreakElseStatementNested(item);
  }

  public BreakStatementNested<A> withNewBreakStatement() {
    return new BreakStatementNested(null);
  }

  public BreakStatementNested<A> withNewBreakStatementLike(Break item) {
    return new BreakStatementNested(item);
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

  public ConstructElseStatementNested<A> withNewConstructElseStatement() {
    return new ConstructElseStatementNested(null);
  }

  public ConstructElseStatementNested<A> withNewConstructElseStatementLike(Construct item) {
    return new ConstructElseStatementNested(item);
  }

  public ConstructStatementNested<A> withNewConstructStatement() {
    return new ConstructStatementNested(null);
  }

  public ConstructStatementNested<A> withNewConstructStatementLike(Construct item) {
    return new ConstructStatementNested(item);
  }

  public ContextRefConditionNested<A> withNewContextRefCondition() {
    return new ContextRefConditionNested(null);
  }

  public A withNewContextRefCondition(String name) {
    return (A) this.withCondition(new ContextRef(name));
  }

  public ContextRefConditionNested<A> withNewContextRefConditionLike(ContextRef item) {
    return new ContextRefConditionNested(item);
  }

  public ContinueElseStatementNested<A> withNewContinueElseStatement() {
    return new ContinueElseStatementNested(null);
  }

  public ContinueElseStatementNested<A> withNewContinueElseStatementLike(Continue item) {
    return new ContinueElseStatementNested(item);
  }

  public ContinueStatementNested<A> withNewContinueStatement() {
    return new ContinueStatementNested(null);
  }

  public ContinueStatementNested<A> withNewContinueStatementLike(Continue item) {
    return new ContinueStatementNested(item);
  }

  public DeclareConditionNested<A> withNewDeclareCondition() {
    return new DeclareConditionNested(null);
  }

  public A withNewDeclareCondition(Class type, String name) {
    return (A) this.withCondition(new Declare(type, name));
  }

  public A withNewDeclareCondition(Class type, String name, Object value) {
    return (A) this.withCondition(new Declare(type, name, value));
  }

  public DeclareConditionNested<A> withNewDeclareConditionLike(Declare item) {
    return new DeclareConditionNested(item);
  }

  public DeclareElseStatementNested<A> withNewDeclareElseStatement() {
    return new DeclareElseStatementNested(null);
  }

  public A withNewDeclareElseStatement(Class type, String name) {
    return (A) this.withElseStatement(new Declare(type, name));
  }

  public A withNewDeclareElseStatement(Class type, String name, Object value) {
    return (A) this.withElseStatement(new Declare(type, name, value));
  }

  public DeclareElseStatementNested<A> withNewDeclareElseStatementLike(Declare item) {
    return new DeclareElseStatementNested(item);
  }

  public DeclareStatementNested<A> withNewDeclareStatement() {
    return new DeclareStatementNested(null);
  }

  public A withNewDeclareStatement(Class type, String name) {
    return (A) this.withStatement(new Declare(type, name));
  }

  public A withNewDeclareStatement(Class type, String name, Object value) {
    return (A) this.withStatement(new Declare(type, name, value));
  }

  public DeclareStatementNested<A> withNewDeclareStatementLike(Declare item) {
    return new DeclareStatementNested(item);
  }

  public DivideConditionNested<A> withNewDivideCondition() {
    return new DivideConditionNested(null);
  }

  public A withNewDivideCondition(Object left, Object right) {
    return (A) this.withCondition(new Divide(left, right));
  }

  public DivideConditionNested<A> withNewDivideConditionLike(Divide item) {
    return new DivideConditionNested(item);
  }

  public DivideElseStatementNested<A> withNewDivideElseStatement() {
    return new DivideElseStatementNested(null);
  }

  public A withNewDivideElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Divide(left, right));
  }

  public DivideElseStatementNested<A> withNewDivideElseStatementLike(Divide item) {
    return new DivideElseStatementNested(item);
  }

  public DivideStatementNested<A> withNewDivideStatement() {
    return new DivideStatementNested(null);
  }

  public A withNewDivideStatement(Object left, Object right) {
    return (A) this.withStatement(new Divide(left, right));
  }

  public DivideStatementNested<A> withNewDivideStatementLike(Divide item) {
    return new DivideStatementNested(item);
  }

  public DoElseStatementNested<A> withNewDoElseStatement() {
    return new DoElseStatementNested(null);
  }

  public DoElseStatementNested<A> withNewDoElseStatementLike(Do item) {
    return new DoElseStatementNested(item);
  }

  public DoStatementNested<A> withNewDoStatement() {
    return new DoStatementNested(null);
  }

  public DoStatementNested<A> withNewDoStatementLike(Do item) {
    return new DoStatementNested(item);
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

  public EmptyElseStatementNested<A> withNewEmptyElseStatement() {
    return new EmptyElseStatementNested(null);
  }

  public EmptyElseStatementNested<A> withNewEmptyElseStatementLike(Empty item) {
    return new EmptyElseStatementNested(item);
  }

  public EmptyStatementNested<A> withNewEmptyStatement() {
    return new EmptyStatementNested(null);
  }

  public EmptyStatementNested<A> withNewEmptyStatementLike(Empty item) {
    return new EmptyStatementNested(item);
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
    return (A) this.withCondition(new Equals(left, right));
  }

  public EqualsConditionNested<A> withNewEqualsConditionLike(Equals item) {
    return new EqualsConditionNested(item);
  }

  public EqualsElseStatementNested<A> withNewEqualsElseStatement() {
    return new EqualsElseStatementNested(null);
  }

  public A withNewEqualsElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Equals(left, right));
  }

  public EqualsElseStatementNested<A> withNewEqualsElseStatementLike(Equals item) {
    return new EqualsElseStatementNested(item);
  }

  public EqualsStatementNested<A> withNewEqualsStatement() {
    return new EqualsStatementNested(null);
  }

  public A withNewEqualsStatement(Object left, Object right) {
    return (A) this.withStatement(new Equals(left, right));
  }

  public EqualsStatementNested<A> withNewEqualsStatementLike(Equals item) {
    return new EqualsStatementNested(item);
  }

  public ForElseStatementNested<A> withNewForElseStatement() {
    return new ForElseStatementNested(null);
  }

  public ForElseStatementNested<A> withNewForElseStatementLike(For item) {
    return new ForElseStatementNested(item);
  }

  public ForStatementNested<A> withNewForStatement() {
    return new ForStatementNested(null);
  }

  public ForStatementNested<A> withNewForStatementLike(For item) {
    return new ForStatementNested(item);
  }

  public ForeachElseStatementNested<A> withNewForeachElseStatement() {
    return new ForeachElseStatementNested(null);
  }

  public ForeachElseStatementNested<A> withNewForeachElseStatementLike(Foreach item) {
    return new ForeachElseStatementNested(item);
  }

  public ForeachStatementNested<A> withNewForeachStatement() {
    return new ForeachStatementNested(null);
  }

  public ForeachStatementNested<A> withNewForeachStatementLike(Foreach item) {
    return new ForeachStatementNested(item);
  }

  public GreaterThanConditionNested<A> withNewGreaterThanCondition() {
    return new GreaterThanConditionNested(null);
  }

  public A withNewGreaterThanCondition(Object left, Object right) {
    return (A) this.withCondition(new GreaterThan(left, right));
  }

  public GreaterThanConditionNested<A> withNewGreaterThanConditionLike(GreaterThan item) {
    return new GreaterThanConditionNested(item);
  }

  public GreaterThanElseStatementNested<A> withNewGreaterThanElseStatement() {
    return new GreaterThanElseStatementNested(null);
  }

  public A withNewGreaterThanElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new GreaterThan(left, right));
  }

  public GreaterThanElseStatementNested<A> withNewGreaterThanElseStatementLike(GreaterThan item) {
    return new GreaterThanElseStatementNested(item);
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualCondition() {
    return new GreaterThanOrEqualConditionNested(null);
  }

  public A withNewGreaterThanOrEqualCondition(Object left, Object right) {
    return (A) this.withCondition(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualConditionLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualConditionNested(item);
  }

  public GreaterThanOrEqualElseStatementNested<A> withNewGreaterThanOrEqualElseStatement() {
    return new GreaterThanOrEqualElseStatementNested(null);
  }

  public A withNewGreaterThanOrEqualElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualElseStatementNested<A> withNewGreaterThanOrEqualElseStatementLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualElseStatementNested(item);
  }

  public GreaterThanOrEqualStatementNested<A> withNewGreaterThanOrEqualStatement() {
    return new GreaterThanOrEqualStatementNested(null);
  }

  public A withNewGreaterThanOrEqualStatement(Object left, Object right) {
    return (A) this.withStatement(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualStatementNested<A> withNewGreaterThanOrEqualStatementLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualStatementNested(item);
  }

  public GreaterThanStatementNested<A> withNewGreaterThanStatement() {
    return new GreaterThanStatementNested(null);
  }

  public A withNewGreaterThanStatement(Object left, Object right) {
    return (A) this.withStatement(new GreaterThan(left, right));
  }

  public GreaterThanStatementNested<A> withNewGreaterThanStatementLike(GreaterThan item) {
    return new GreaterThanStatementNested(item);
  }

  public IfDslThenStepElseStatementNested<A> withNewIfDslThenStepElseStatement() {
    return new IfDslThenStepElseStatementNested(null);
  }

  public IfDslThenStepElseStatementNested<A> withNewIfDslThenStepElseStatementLike(IfDslThenStep item) {
    return new IfDslThenStepElseStatementNested(item);
  }

  public IfDslThenStepStatementNested<A> withNewIfDslThenStepStatement() {
    return new IfDslThenStepStatementNested(null);
  }

  public IfDslThenStepStatementNested<A> withNewIfDslThenStepStatementLike(IfDslThenStep item) {
    return new IfDslThenStepStatementNested(item);
  }

  public IfElseStatementNested<A> withNewIfElseStatement() {
    return new IfElseStatementNested(null);
  }

  public IfElseStatementNested<A> withNewIfElseStatementLike(If item) {
    return new IfElseStatementNested(item);
  }

  public IfStatementNested<A> withNewIfStatement() {
    return new IfStatementNested(null);
  }

  public IfStatementNested<A> withNewIfStatementLike(If item) {
    return new IfStatementNested(item);
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

  public LambdaElseStatementNested<A> withNewLambdaElseStatement() {
    return new LambdaElseStatementNested(null);
  }

  public LambdaElseStatementNested<A> withNewLambdaElseStatementLike(Lambda item) {
    return new LambdaElseStatementNested(item);
  }

  public LambdaStatementNested<A> withNewLambdaStatement() {
    return new LambdaStatementNested(null);
  }

  public LambdaStatementNested<A> withNewLambdaStatementLike(Lambda item) {
    return new LambdaStatementNested(item);
  }

  public LeftShiftConditionNested<A> withNewLeftShiftCondition() {
    return new LeftShiftConditionNested(null);
  }

  public A withNewLeftShiftCondition(Object left, Object right) {
    return (A) this.withCondition(new LeftShift(left, right));
  }

  public LeftShiftConditionNested<A> withNewLeftShiftConditionLike(LeftShift item) {
    return new LeftShiftConditionNested(item);
  }

  public LeftShiftElseStatementNested<A> withNewLeftShiftElseStatement() {
    return new LeftShiftElseStatementNested(null);
  }

  public A withNewLeftShiftElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new LeftShift(left, right));
  }

  public LeftShiftElseStatementNested<A> withNewLeftShiftElseStatementLike(LeftShift item) {
    return new LeftShiftElseStatementNested(item);
  }

  public LeftShiftStatementNested<A> withNewLeftShiftStatement() {
    return new LeftShiftStatementNested(null);
  }

  public A withNewLeftShiftStatement(Object left, Object right) {
    return (A) this.withStatement(new LeftShift(left, right));
  }

  public LeftShiftStatementNested<A> withNewLeftShiftStatementLike(LeftShift item) {
    return new LeftShiftStatementNested(item);
  }

  public LessThanConditionNested<A> withNewLessThanCondition() {
    return new LessThanConditionNested(null);
  }

  public A withNewLessThanCondition(Object left, Object right) {
    return (A) this.withCondition(new LessThan(left, right));
  }

  public LessThanConditionNested<A> withNewLessThanConditionLike(LessThan item) {
    return new LessThanConditionNested(item);
  }

  public LessThanElseStatementNested<A> withNewLessThanElseStatement() {
    return new LessThanElseStatementNested(null);
  }

  public A withNewLessThanElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new LessThan(left, right));
  }

  public LessThanElseStatementNested<A> withNewLessThanElseStatementLike(LessThan item) {
    return new LessThanElseStatementNested(item);
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualCondition() {
    return new LessThanOrEqualConditionNested(null);
  }

  public A withNewLessThanOrEqualCondition(Object left, Object right) {
    return (A) this.withCondition(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualConditionLike(LessThanOrEqual item) {
    return new LessThanOrEqualConditionNested(item);
  }

  public LessThanOrEqualElseStatementNested<A> withNewLessThanOrEqualElseStatement() {
    return new LessThanOrEqualElseStatementNested(null);
  }

  public A withNewLessThanOrEqualElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualElseStatementNested<A> withNewLessThanOrEqualElseStatementLike(LessThanOrEqual item) {
    return new LessThanOrEqualElseStatementNested(item);
  }

  public LessThanOrEqualStatementNested<A> withNewLessThanOrEqualStatement() {
    return new LessThanOrEqualStatementNested(null);
  }

  public A withNewLessThanOrEqualStatement(Object left, Object right) {
    return (A) this.withStatement(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualStatementNested<A> withNewLessThanOrEqualStatementLike(LessThanOrEqual item) {
    return new LessThanOrEqualStatementNested(item);
  }

  public LessThanStatementNested<A> withNewLessThanStatement() {
    return new LessThanStatementNested(null);
  }

  public A withNewLessThanStatement(Object left, Object right) {
    return (A) this.withStatement(new LessThan(left, right));
  }

  public LessThanStatementNested<A> withNewLessThanStatementLike(LessThan item) {
    return new LessThanStatementNested(item);
  }

  public LogicalAndConditionNested<A> withNewLogicalAndCondition() {
    return new LogicalAndConditionNested(null);
  }

  public A withNewLogicalAndCondition(Object left, Object right) {
    return (A) this.withCondition(new LogicalAnd(left, right));
  }

  public LogicalAndConditionNested<A> withNewLogicalAndConditionLike(LogicalAnd item) {
    return new LogicalAndConditionNested(item);
  }

  public LogicalAndElseStatementNested<A> withNewLogicalAndElseStatement() {
    return new LogicalAndElseStatementNested(null);
  }

  public A withNewLogicalAndElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new LogicalAnd(left, right));
  }

  public LogicalAndElseStatementNested<A> withNewLogicalAndElseStatementLike(LogicalAnd item) {
    return new LogicalAndElseStatementNested(item);
  }

  public LogicalAndStatementNested<A> withNewLogicalAndStatement() {
    return new LogicalAndStatementNested(null);
  }

  public A withNewLogicalAndStatement(Object left, Object right) {
    return (A) this.withStatement(new LogicalAnd(left, right));
  }

  public LogicalAndStatementNested<A> withNewLogicalAndStatementLike(LogicalAnd item) {
    return new LogicalAndStatementNested(item);
  }

  public LogicalOrConditionNested<A> withNewLogicalOrCondition() {
    return new LogicalOrConditionNested(null);
  }

  public A withNewLogicalOrCondition(Object left, Object right) {
    return (A) this.withCondition(new LogicalOr(left, right));
  }

  public LogicalOrConditionNested<A> withNewLogicalOrConditionLike(LogicalOr item) {
    return new LogicalOrConditionNested(item);
  }

  public LogicalOrElseStatementNested<A> withNewLogicalOrElseStatement() {
    return new LogicalOrElseStatementNested(null);
  }

  public A withNewLogicalOrElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new LogicalOr(left, right));
  }

  public LogicalOrElseStatementNested<A> withNewLogicalOrElseStatementLike(LogicalOr item) {
    return new LogicalOrElseStatementNested(item);
  }

  public LogicalOrStatementNested<A> withNewLogicalOrStatement() {
    return new LogicalOrStatementNested(null);
  }

  public A withNewLogicalOrStatement(Object left, Object right) {
    return (A) this.withStatement(new LogicalOr(left, right));
  }

  public LogicalOrStatementNested<A> withNewLogicalOrStatementLike(LogicalOr item) {
    return new LogicalOrStatementNested(item);
  }

  public MethodCallConditionNested<A> withNewMethodCallCondition() {
    return new MethodCallConditionNested(null);
  }

  public MethodCallConditionNested<A> withNewMethodCallConditionLike(MethodCall item) {
    return new MethodCallConditionNested(item);
  }

  public MethodCallElseStatementNested<A> withNewMethodCallElseStatement() {
    return new MethodCallElseStatementNested(null);
  }

  public MethodCallElseStatementNested<A> withNewMethodCallElseStatementLike(MethodCall item) {
    return new MethodCallElseStatementNested(item);
  }

  public MethodCallStatementNested<A> withNewMethodCallStatement() {
    return new MethodCallStatementNested(null);
  }

  public MethodCallStatementNested<A> withNewMethodCallStatementLike(MethodCall item) {
    return new MethodCallStatementNested(item);
  }

  public MinusConditionNested<A> withNewMinusCondition() {
    return new MinusConditionNested(null);
  }

  public A withNewMinusCondition(Object left, Object right) {
    return (A) this.withCondition(new Minus(left, right));
  }

  public MinusConditionNested<A> withNewMinusConditionLike(Minus item) {
    return new MinusConditionNested(item);
  }

  public MinusElseStatementNested<A> withNewMinusElseStatement() {
    return new MinusElseStatementNested(null);
  }

  public A withNewMinusElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Minus(left, right));
  }

  public MinusElseStatementNested<A> withNewMinusElseStatementLike(Minus item) {
    return new MinusElseStatementNested(item);
  }

  public MinusStatementNested<A> withNewMinusStatement() {
    return new MinusStatementNested(null);
  }

  public A withNewMinusStatement(Object left, Object right) {
    return (A) this.withStatement(new Minus(left, right));
  }

  public MinusStatementNested<A> withNewMinusStatementLike(Minus item) {
    return new MinusStatementNested(item);
  }

  public ModuloConditionNested<A> withNewModuloCondition() {
    return new ModuloConditionNested(null);
  }

  public A withNewModuloCondition(Object left, Object right) {
    return (A) this.withCondition(new Modulo(left, right));
  }

  public ModuloConditionNested<A> withNewModuloConditionLike(Modulo item) {
    return new ModuloConditionNested(item);
  }

  public ModuloElseStatementNested<A> withNewModuloElseStatement() {
    return new ModuloElseStatementNested(null);
  }

  public A withNewModuloElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Modulo(left, right));
  }

  public ModuloElseStatementNested<A> withNewModuloElseStatementLike(Modulo item) {
    return new ModuloElseStatementNested(item);
  }

  public ModuloStatementNested<A> withNewModuloStatement() {
    return new ModuloStatementNested(null);
  }

  public A withNewModuloStatement(Object left, Object right) {
    return (A) this.withStatement(new Modulo(left, right));
  }

  public ModuloStatementNested<A> withNewModuloStatementLike(Modulo item) {
    return new ModuloStatementNested(item);
  }

  public MultiplyConditionNested<A> withNewMultiplyCondition() {
    return new MultiplyConditionNested(null);
  }

  public A withNewMultiplyCondition(Object left, Object right) {
    return (A) this.withCondition(new Multiply(left, right));
  }

  public MultiplyConditionNested<A> withNewMultiplyConditionLike(Multiply item) {
    return new MultiplyConditionNested(item);
  }

  public MultiplyElseStatementNested<A> withNewMultiplyElseStatement() {
    return new MultiplyElseStatementNested(null);
  }

  public A withNewMultiplyElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Multiply(left, right));
  }

  public MultiplyElseStatementNested<A> withNewMultiplyElseStatementLike(Multiply item) {
    return new MultiplyElseStatementNested(item);
  }

  public MultiplyStatementNested<A> withNewMultiplyStatement() {
    return new MultiplyStatementNested(null);
  }

  public A withNewMultiplyStatement(Object left, Object right) {
    return (A) this.withStatement(new Multiply(left, right));
  }

  public MultiplyStatementNested<A> withNewMultiplyStatementLike(Multiply item) {
    return new MultiplyStatementNested(item);
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
    return (A) this.withCondition(new NewArray(type, sizes));
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
    return (A) this.withCondition(new NotEquals(left, right));
  }

  public NotEqualsConditionNested<A> withNewNotEqualsConditionLike(NotEquals item) {
    return new NotEqualsConditionNested(item);
  }

  public NotEqualsElseStatementNested<A> withNewNotEqualsElseStatement() {
    return new NotEqualsElseStatementNested(null);
  }

  public A withNewNotEqualsElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new NotEquals(left, right));
  }

  public NotEqualsElseStatementNested<A> withNewNotEqualsElseStatementLike(NotEquals item) {
    return new NotEqualsElseStatementNested(item);
  }

  public NotEqualsStatementNested<A> withNewNotEqualsStatement() {
    return new NotEqualsStatementNested(null);
  }

  public A withNewNotEqualsStatement(Object left, Object right) {
    return (A) this.withStatement(new NotEquals(left, right));
  }

  public NotEqualsStatementNested<A> withNewNotEqualsStatementLike(NotEquals item) {
    return new NotEqualsStatementNested(item);
  }

  public PlusConditionNested<A> withNewPlusCondition() {
    return new PlusConditionNested(null);
  }

  public A withNewPlusCondition(Object left, Object right) {
    return (A) this.withCondition(new Plus(left, right));
  }

  public PlusConditionNested<A> withNewPlusConditionLike(Plus item) {
    return new PlusConditionNested(item);
  }

  public PlusElseStatementNested<A> withNewPlusElseStatement() {
    return new PlusElseStatementNested(null);
  }

  public A withNewPlusElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Plus(left, right));
  }

  public PlusElseStatementNested<A> withNewPlusElseStatementLike(Plus item) {
    return new PlusElseStatementNested(item);
  }

  public PlusStatementNested<A> withNewPlusStatement() {
    return new PlusStatementNested(null);
  }

  public A withNewPlusStatement(Object left, Object right) {
    return (A) this.withStatement(new Plus(left, right));
  }

  public PlusStatementNested<A> withNewPlusStatementLike(Plus item) {
    return new PlusStatementNested(item);
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

  public PostDecrementElseStatementNested<A> withNewPostDecrementElseStatement() {
    return new PostDecrementElseStatementNested(null);
  }

  public PostDecrementElseStatementNested<A> withNewPostDecrementElseStatementLike(PostDecrement item) {
    return new PostDecrementElseStatementNested(item);
  }

  public PostDecrementStatementNested<A> withNewPostDecrementStatement() {
    return new PostDecrementStatementNested(null);
  }

  public PostDecrementStatementNested<A> withNewPostDecrementStatementLike(PostDecrement item) {
    return new PostDecrementStatementNested(item);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementCondition() {
    return new PostIncrementConditionNested(null);
  }

  public PostIncrementConditionNested<A> withNewPostIncrementConditionLike(PostIncrement item) {
    return new PostIncrementConditionNested(item);
  }

  public PostIncrementElseStatementNested<A> withNewPostIncrementElseStatement() {
    return new PostIncrementElseStatementNested(null);
  }

  public PostIncrementElseStatementNested<A> withNewPostIncrementElseStatementLike(PostIncrement item) {
    return new PostIncrementElseStatementNested(item);
  }

  public PostIncrementStatementNested<A> withNewPostIncrementStatement() {
    return new PostIncrementStatementNested(null);
  }

  public PostIncrementStatementNested<A> withNewPostIncrementStatementLike(PostIncrement item) {
    return new PostIncrementStatementNested(item);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementCondition() {
    return new PreDecrementConditionNested(null);
  }

  public PreDecrementConditionNested<A> withNewPreDecrementConditionLike(PreDecrement item) {
    return new PreDecrementConditionNested(item);
  }

  public PreDecrementElseStatementNested<A> withNewPreDecrementElseStatement() {
    return new PreDecrementElseStatementNested(null);
  }

  public PreDecrementElseStatementNested<A> withNewPreDecrementElseStatementLike(PreDecrement item) {
    return new PreDecrementElseStatementNested(item);
  }

  public PreDecrementStatementNested<A> withNewPreDecrementStatement() {
    return new PreDecrementStatementNested(null);
  }

  public PreDecrementStatementNested<A> withNewPreDecrementStatementLike(PreDecrement item) {
    return new PreDecrementStatementNested(item);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementCondition() {
    return new PreIncrementConditionNested(null);
  }

  public PreIncrementConditionNested<A> withNewPreIncrementConditionLike(PreIncrement item) {
    return new PreIncrementConditionNested(item);
  }

  public PreIncrementElseStatementNested<A> withNewPreIncrementElseStatement() {
    return new PreIncrementElseStatementNested(null);
  }

  public PreIncrementElseStatementNested<A> withNewPreIncrementElseStatementLike(PreIncrement item) {
    return new PreIncrementElseStatementNested(item);
  }

  public PreIncrementStatementNested<A> withNewPreIncrementStatement() {
    return new PreIncrementStatementNested(null);
  }

  public PreIncrementStatementNested<A> withNewPreIncrementStatementLike(PreIncrement item) {
    return new PreIncrementStatementNested(item);
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

  public PropertyRefElseStatementNested<A> withNewPropertyRefElseStatement() {
    return new PropertyRefElseStatementNested(null);
  }

  public PropertyRefElseStatementNested<A> withNewPropertyRefElseStatementLike(PropertyRef item) {
    return new PropertyRefElseStatementNested(item);
  }

  public PropertyRefStatementNested<A> withNewPropertyRefStatement() {
    return new PropertyRefStatementNested(null);
  }

  public PropertyRefStatementNested<A> withNewPropertyRefStatementLike(PropertyRef item) {
    return new PropertyRefStatementNested(item);
  }

  public ReturnDslThisStepElseStatementNested<A> withNewReturnDslThisStepElseStatement() {
    return new ReturnDslThisStepElseStatementNested(null);
  }

  public ReturnDslThisStepElseStatementNested<A> withNewReturnDslThisStepElseStatementLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepElseStatementNested(item);
  }

  public ReturnDslThisStepStatementNested<A> withNewReturnDslThisStepStatement() {
    return new ReturnDslThisStepStatementNested(null);
  }

  public ReturnDslThisStepStatementNested<A> withNewReturnDslThisStepStatementLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepStatementNested(item);
  }

  public ReturnDslVariableStepElseStatementNested<A> withNewReturnDslVariableStepElseStatement() {
    return new ReturnDslVariableStepElseStatementNested(null);
  }

  public A withNewReturnDslVariableStepElseStatement(String name) {
    return (A) this.withElseStatement(new ReturnDslVariableStep(name));
  }

  public ReturnDslVariableStepElseStatementNested<A> withNewReturnDslVariableStepElseStatementLike(ReturnDslVariableStep item) {
    return new ReturnDslVariableStepElseStatementNested(item);
  }

  public ReturnDslVariableStepStatementNested<A> withNewReturnDslVariableStepStatement() {
    return new ReturnDslVariableStepStatementNested(null);
  }

  public A withNewReturnDslVariableStepStatement(String name) {
    return (A) this.withStatement(new ReturnDslVariableStep(name));
  }

  public ReturnDslVariableStepStatementNested<A> withNewReturnDslVariableStepStatementLike(ReturnDslVariableStep item) {
    return new ReturnDslVariableStepStatementNested(item);
  }

  public ReturnElseStatementNested<A> withNewReturnElseStatement() {
    return new ReturnElseStatementNested(null);
  }

  public A withNewReturnElseStatement(Object object) {
    return (A) this.withElseStatement(new Return(object));
  }

  public ReturnElseStatementNested<A> withNewReturnElseStatementLike(Return item) {
    return new ReturnElseStatementNested(item);
  }

  public ReturnStatementNested<A> withNewReturnStatement() {
    return new ReturnStatementNested(null);
  }

  public A withNewReturnStatement(Object object) {
    return (A) this.withStatement(new Return(object));
  }

  public ReturnStatementNested<A> withNewReturnStatementLike(Return item) {
    return new ReturnStatementNested(item);
  }

  public RightShiftConditionNested<A> withNewRightShiftCondition() {
    return new RightShiftConditionNested(null);
  }

  public A withNewRightShiftCondition(Object left, Object right) {
    return (A) this.withCondition(new RightShift(left, right));
  }

  public RightShiftConditionNested<A> withNewRightShiftConditionLike(RightShift item) {
    return new RightShiftConditionNested(item);
  }

  public RightShiftElseStatementNested<A> withNewRightShiftElseStatement() {
    return new RightShiftElseStatementNested(null);
  }

  public A withNewRightShiftElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new RightShift(left, right));
  }

  public RightShiftElseStatementNested<A> withNewRightShiftElseStatementLike(RightShift item) {
    return new RightShiftElseStatementNested(item);
  }

  public RightShiftStatementNested<A> withNewRightShiftStatement() {
    return new RightShiftStatementNested(null);
  }

  public A withNewRightShiftStatement(Object left, Object right) {
    return (A) this.withStatement(new RightShift(left, right));
  }

  public RightShiftStatementNested<A> withNewRightShiftStatementLike(RightShift item) {
    return new RightShiftStatementNested(item);
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftCondition() {
    return new RightUnsignedShiftConditionNested(null);
  }

  public A withNewRightUnsignedShiftCondition(Object left, Object right) {
    return (A) this.withCondition(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftConditionNested<A> withNewRightUnsignedShiftConditionLike(RightUnsignedShift item) {
    return new RightUnsignedShiftConditionNested(item);
  }

  public RightUnsignedShiftElseStatementNested<A> withNewRightUnsignedShiftElseStatement() {
    return new RightUnsignedShiftElseStatementNested(null);
  }

  public A withNewRightUnsignedShiftElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftElseStatementNested<A> withNewRightUnsignedShiftElseStatementLike(RightUnsignedShift item) {
    return new RightUnsignedShiftElseStatementNested(item);
  }

  public RightUnsignedShiftStatementNested<A> withNewRightUnsignedShiftStatement() {
    return new RightUnsignedShiftStatementNested(null);
  }

  public A withNewRightUnsignedShiftStatement(Object left, Object right) {
    return (A) this.withStatement(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftStatementNested<A> withNewRightUnsignedShiftStatementLike(RightUnsignedShift item) {
    return new RightUnsignedShiftStatementNested(item);
  }

  public StringStatementNested<A> withNewStringStatement() {
    return new StringStatementNested(null);
  }

  public A withNewStringStatement(String data) {
    return (A) this.withStatement(new StringStatement(data));
  }

  public A withNewStringStatement(String data, Object[] parameters) {
    return (A) this.withStatement(new StringStatement(data, parameters));
  }

  public StringStatementElseNested<A> withNewStringStatementElse() {
    return new StringStatementElseNested(null);
  }

  public A withNewStringStatementElse(String data) {
    return (A) this.withElseStatement(new StringStatement(data));
  }

  public A withNewStringStatementElse(String data, Object[] parameters) {
    return (A) this.withElseStatement(new StringStatement(data, parameters));
  }

  public StringStatementElseNested<A> withNewStringStatementElseLike(StringStatement item) {
    return new StringStatementElseNested(item);
  }

  public StringStatementNested<A> withNewStringStatementLike(StringStatement item) {
    return new StringStatementNested(item);
  }

  public SuperConditionNested<A> withNewSuperCondition() {
    return new SuperConditionNested(null);
  }

  public SuperConditionNested<A> withNewSuperConditionLike(Super item) {
    return new SuperConditionNested(item);
  }

  public SwitchElseStatementNested<A> withNewSwitchElseStatement() {
    return new SwitchElseStatementNested(null);
  }

  public SwitchElseStatementNested<A> withNewSwitchElseStatementLike(Switch item) {
    return new SwitchElseStatementNested(item);
  }

  public SwitchStatementNested<A> withNewSwitchStatement() {
    return new SwitchStatementNested(null);
  }

  public SwitchStatementNested<A> withNewSwitchStatementLike(Switch item) {
    return new SwitchStatementNested(item);
  }

  public SynchronizedElseStatementNested<A> withNewSynchronizedElseStatement() {
    return new SynchronizedElseStatementNested(null);
  }

  public SynchronizedElseStatementNested<A> withNewSynchronizedElseStatementLike(Synchronized item) {
    return new SynchronizedElseStatementNested(item);
  }

  public SynchronizedStatementNested<A> withNewSynchronizedStatement() {
    return new SynchronizedStatementNested(null);
  }

  public SynchronizedStatementNested<A> withNewSynchronizedStatementLike(Synchronized item) {
    return new SynchronizedStatementNested(item);
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

  public ThrowElseStatementNested<A> withNewThrowElseStatement() {
    return new ThrowElseStatementNested(null);
  }

  public ThrowElseStatementNested<A> withNewThrowElseStatementLike(Throw item) {
    return new ThrowElseStatementNested(item);
  }

  public ThrowStatementNested<A> withNewThrowStatement() {
    return new ThrowStatementNested(null);
  }

  public ThrowStatementNested<A> withNewThrowStatementLike(Throw item) {
    return new ThrowStatementNested(item);
  }

  public TryElseStatementNested<A> withNewTryElseStatement() {
    return new TryElseStatementNested(null);
  }

  public TryElseStatementNested<A> withNewTryElseStatementLike(Try item) {
    return new TryElseStatementNested(item);
  }

  public TryStatementNested<A> withNewTryStatement() {
    return new TryStatementNested(null);
  }

  public TryStatementNested<A> withNewTryStatementLike(Try item) {
    return new TryStatementNested(item);
  }

  public ValueRefConditionNested<A> withNewValueRefCondition() {
    return new ValueRefConditionNested(null);
  }

  public A withNewValueRefCondition(Object value) {
    return (A) this.withCondition(new ValueRef(value));
  }

  public ValueRefConditionNested<A> withNewValueRefConditionLike(ValueRef item) {
    return new ValueRefConditionNested(item);
  }

  public WhileElseStatementNested<A> withNewWhileElseStatement() {
    return new WhileElseStatementNested(null);
  }

  public WhileElseStatementNested<A> withNewWhileElseStatementLike(While item) {
    return new WhileElseStatementNested(item);
  }

  public WhileStatementNested<A> withNewWhileStatement() {
    return new WhileStatementNested(null);
  }

  public WhileStatementNested<A> withNewWhileStatementLike(While item) {
    return new WhileStatementNested(item);
  }

  public XorConditionNested<A> withNewXorCondition() {
    return new XorConditionNested(null);
  }

  public A withNewXorCondition(Object left, Object right) {
    return (A) this.withCondition(new Xor(left, right));
  }

  public XorConditionNested<A> withNewXorConditionLike(Xor item) {
    return new XorConditionNested(item);
  }

  public XorElseStatementNested<A> withNewXorElseStatement() {
    return new XorElseStatementNested(null);
  }

  public A withNewXorElseStatement(Object left, Object right) {
    return (A) this.withElseStatement(new Xor(left, right));
  }

  public XorElseStatementNested<A> withNewXorElseStatementLike(Xor item) {
    return new XorElseStatementNested(item);
  }

  public XorStatementNested<A> withNewXorStatement() {
    return new XorStatementNested(null);
  }

  public A withNewXorStatement(Object left, Object right) {
    return (A) this.withStatement(new Xor(left, right));
  }

  public XorStatementNested<A> withNewXorStatementLike(Xor item) {
    return new XorStatementNested(item);
  }

  public A withStatement(Statement statement) {
    if (statement == null) {
      this.statement = null;
      this._visitables.remove("statement");
      return (A) this;
    } else {
      VisitableBuilder<? extends Statement, ?> builder = builder(statement);
      this._visitables.get("statement").clear();
      this._visitables.get("statement").add(builder);
      this.statement = builder;
      return (A) this;
    }
  }

  public class AssignConditionNested<N> extends AssignFluent<AssignConditionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignConditionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endAssignCondition() {
      return and();
    }

  }

  public class AssignElseStatementNested<N> extends AssignFluent<AssignElseStatementNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignElseStatementNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endAssignElseStatement() {
      return and();
    }

  }

  public class AssignStatementNested<N> extends AssignFluent<AssignStatementNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignStatementNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endAssignStatement() {
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endBinaryExpressionCondition() {
      return and();
    }

  }

  public class BinaryExpressionElseStatementNested<N> extends BinaryExpressionFluent<BinaryExpressionElseStatementNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionElseStatementNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endBinaryExpressionElseStatement() {
      return and();
    }

  }

  public class BinaryExpressionStatementNested<N> extends BinaryExpressionFluent<BinaryExpressionStatementNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;

    BinaryExpressionStatementNested(BinaryExpression item) {
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBinaryExpressionStatement() {
      return and();
    }

  }

  public class BitwiseAndConditionNested<N> extends BitwiseAndFluent<BitwiseAndConditionNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndConditionNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endBitwiseAndCondition() {
      return and();
    }

  }

  public class BitwiseAndElseStatementNested<N> extends BitwiseAndFluent<BitwiseAndElseStatementNested<N>>
      implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndElseStatementNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endBitwiseAndElseStatement() {
      return and();
    }

  }

  public class BitwiseAndStatementNested<N> extends BitwiseAndFluent<BitwiseAndStatementNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndStatementNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBitwiseAndStatement() {
      return and();
    }

  }

  public class BitwiseOrConditionNested<N> extends BitwiseOrFluent<BitwiseOrConditionNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrConditionNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endBitwiseOrCondition() {
      return and();
    }

  }

  public class BitwiseOrElseStatementNested<N> extends BitwiseOrFluent<BitwiseOrElseStatementNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrElseStatementNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endBitwiseOrElseStatement() {
      return and();
    }

  }

  public class BitwiseOrStatementNested<N> extends BitwiseOrFluent<BitwiseOrStatementNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrStatementNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBitwiseOrStatement() {
      return and();
    }

  }

  public class BlockElseStatementNested<N> extends BlockFluent<BlockElseStatementNested<N>> implements Nested<N> {

    BlockBuilder builder;

    BlockElseStatementNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endBlockElseStatement() {
      return and();
    }

  }

  public class BlockStatementNested<N> extends BlockFluent<BlockStatementNested<N>> implements Nested<N> {

    BlockBuilder builder;

    BlockStatementNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBlockStatement() {
      return and();
    }

  }

  public class BreakElseStatementNested<N> extends BreakFluent<BreakElseStatementNested<N>> implements Nested<N> {

    BreakBuilder builder;

    BreakElseStatementNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endBreakElseStatement() {
      return and();
    }

  }

  public class BreakStatementNested<N> extends BreakFluent<BreakStatementNested<N>> implements Nested<N> {

    BreakBuilder builder;

    BreakStatementNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBreakStatement() {
      return and();
    }

  }

  public class CastConditionNested<N> extends CastFluent<CastConditionNested<N>> implements Nested<N> {

    CastBuilder builder;

    CastConditionNested(Cast item) {
      this.builder = new CastBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endConstructCondition() {
      return and();
    }

  }

  public class ConstructElseStatementNested<N> extends ConstructFluent<ConstructElseStatementNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructElseStatementNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endConstructElseStatement() {
      return and();
    }

  }

  public class ConstructStatementNested<N> extends ConstructFluent<ConstructStatementNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructStatementNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endConstructStatement() {
      return and();
    }

  }

  public class ContextRefConditionNested<N> extends ContextRefFluent<ContextRefConditionNested<N>> implements Nested<N> {

    ContextRefBuilder builder;

    ContextRefConditionNested(ContextRef item) {
      this.builder = new ContextRefBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endContextRefCondition() {
      return and();
    }

  }

  public class ContinueElseStatementNested<N> extends ContinueFluent<ContinueElseStatementNested<N>> implements Nested<N> {

    ContinueBuilder builder;

    ContinueElseStatementNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endContinueElseStatement() {
      return and();
    }

  }

  public class ContinueStatementNested<N> extends ContinueFluent<ContinueStatementNested<N>> implements Nested<N> {

    ContinueBuilder builder;

    ContinueStatementNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endContinueStatement() {
      return and();
    }

  }

  public class DeclareConditionNested<N> extends DeclareFluent<DeclareConditionNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareConditionNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endDeclareCondition() {
      return and();
    }

  }

  public class DeclareElseStatementNested<N> extends DeclareFluent<DeclareElseStatementNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareElseStatementNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endDeclareElseStatement() {
      return and();
    }

  }

  public class DeclareStatementNested<N> extends DeclareFluent<DeclareStatementNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareStatementNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endDeclareStatement() {
      return and();
    }

  }

  public class DivideConditionNested<N> extends DivideFluent<DivideConditionNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideConditionNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endDivideCondition() {
      return and();
    }

  }

  public class DivideElseStatementNested<N> extends DivideFluent<DivideElseStatementNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideElseStatementNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endDivideElseStatement() {
      return and();
    }

  }

  public class DivideStatementNested<N> extends DivideFluent<DivideStatementNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideStatementNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endDivideStatement() {
      return and();
    }

  }

  public class DoElseStatementNested<N> extends DoFluent<DoElseStatementNested<N>> implements Nested<N> {

    DoBuilder builder;

    DoElseStatementNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endDoElseStatement() {
      return and();
    }

  }

  public class DoStatementNested<N> extends DoFluent<DoStatementNested<N>> implements Nested<N> {

    DoBuilder builder;

    DoStatementNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endDoStatement() {
      return and();
    }

  }

  public class DotClassConditionNested<N> extends DotClassFluent<DotClassConditionNested<N>> implements Nested<N> {

    DotClassBuilder builder;

    DotClassConditionNested(DotClass item) {
      this.builder = new DotClassBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endEmptyCondition() {
      return and();
    }

  }

  public class EmptyElseStatementNested<N> extends EmptyFluent<EmptyElseStatementNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyElseStatementNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endEmptyElseStatement() {
      return and();
    }

  }

  public class EmptyStatementNested<N> extends EmptyFluent<EmptyStatementNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyStatementNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endEmptyStatement() {
      return and();
    }

  }

  public class EnclosedConditionNested<N> extends EnclosedFluent<EnclosedConditionNested<N>> implements Nested<N> {

    EnclosedBuilder builder;

    EnclosedConditionNested(Enclosed item) {
      this.builder = new EnclosedBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endEqualsCondition() {
      return and();
    }

  }

  public class EqualsElseStatementNested<N> extends EqualsFluent<EqualsElseStatementNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsElseStatementNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endEqualsElseStatement() {
      return and();
    }

  }

  public class EqualsStatementNested<N> extends EqualsFluent<EqualsStatementNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsStatementNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endEqualsStatement() {
      return and();
    }

  }

  public class ForElseStatementNested<N> extends ForFluent<ForElseStatementNested<N>> implements Nested<N> {

    ForBuilder builder;

    ForElseStatementNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endForElseStatement() {
      return and();
    }

  }

  public class ForStatementNested<N> extends ForFluent<ForStatementNested<N>> implements Nested<N> {

    ForBuilder builder;

    ForStatementNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endForStatement() {
      return and();
    }

  }

  public class ForeachElseStatementNested<N> extends ForeachFluent<ForeachElseStatementNested<N>> implements Nested<N> {

    ForeachBuilder builder;

    ForeachElseStatementNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endForeachElseStatement() {
      return and();
    }

  }

  public class ForeachStatementNested<N> extends ForeachFluent<ForeachStatementNested<N>> implements Nested<N> {

    ForeachBuilder builder;

    ForeachStatementNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endForeachStatement() {
      return and();
    }

  }

  public class GreaterThanConditionNested<N> extends GreaterThanFluent<GreaterThanConditionNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanConditionNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanCondition() {
      return and();
    }

  }

  public class GreaterThanElseStatementNested<N> extends GreaterThanFluent<GreaterThanElseStatementNested<N>>
      implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanElseStatementNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endGreaterThanElseStatement() {
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanOrEqualCondition() {
      return and();
    }

  }

  public class GreaterThanOrEqualElseStatementNested<N>
      extends GreaterThanOrEqualFluent<GreaterThanOrEqualElseStatementNested<N>> implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualElseStatementNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endGreaterThanOrEqualElseStatement() {
      return and();
    }

  }

  public class GreaterThanOrEqualStatementNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualStatementNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;

    GreaterThanOrEqualStatementNested(GreaterThanOrEqual item) {
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endGreaterThanOrEqualStatement() {
      return and();
    }

  }

  public class GreaterThanStatementNested<N> extends GreaterThanFluent<GreaterThanStatementNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;

    GreaterThanStatementNested(GreaterThan item) {
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endGreaterThanStatement() {
      return and();
    }

  }

  public class IfDslThenStepElseStatementNested<N> extends IfDslThenStepFluent<IfDslThenStepElseStatementNested<N>>
      implements Nested<N> {

    IfDslThenStepBuilder builder;

    IfDslThenStepElseStatementNested(IfDslThenStep item) {
      this.builder = new IfDslThenStepBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endIfDslThenStepElseStatement() {
      return and();
    }

  }

  public class IfDslThenStepStatementNested<N> extends IfDslThenStepFluent<IfDslThenStepStatementNested<N>>
      implements Nested<N> {

    IfDslThenStepBuilder builder;

    IfDslThenStepStatementNested(IfDslThenStep item) {
      this.builder = new IfDslThenStepBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endIfDslThenStepStatement() {
      return and();
    }

  }

  public class IfElseStatementNested<N> extends IfFluent<IfElseStatementNested<N>> implements Nested<N> {

    IfBuilder builder;

    IfElseStatementNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endIfElseStatement() {
      return and();
    }

  }

  public class IfStatementNested<N> extends IfFluent<IfStatementNested<N>> implements Nested<N> {

    IfBuilder builder;

    IfStatementNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endIfStatement() {
      return and();
    }

  }

  public class IndexConditionNested<N> extends IndexFluent<IndexConditionNested<N>> implements Nested<N> {

    IndexBuilder builder;

    IndexConditionNested(Index item) {
      this.builder = new IndexBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endLambdaCondition() {
      return and();
    }

  }

  public class LambdaElseStatementNested<N> extends LambdaFluent<LambdaElseStatementNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaElseStatementNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endLambdaElseStatement() {
      return and();
    }

  }

  public class LambdaStatementNested<N> extends LambdaFluent<LambdaStatementNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaStatementNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLambdaStatement() {
      return and();
    }

  }

  public class LeftShiftConditionNested<N> extends LeftShiftFluent<LeftShiftConditionNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftConditionNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endLeftShiftCondition() {
      return and();
    }

  }

  public class LeftShiftElseStatementNested<N> extends LeftShiftFluent<LeftShiftElseStatementNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftElseStatementNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endLeftShiftElseStatement() {
      return and();
    }

  }

  public class LeftShiftStatementNested<N> extends LeftShiftFluent<LeftShiftStatementNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftStatementNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLeftShiftStatement() {
      return and();
    }

  }

  public class LessThanConditionNested<N> extends LessThanFluent<LessThanConditionNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanConditionNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endLessThanCondition() {
      return and();
    }

  }

  public class LessThanElseStatementNested<N> extends LessThanFluent<LessThanElseStatementNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanElseStatementNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endLessThanElseStatement() {
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endLessThanOrEqualCondition() {
      return and();
    }

  }

  public class LessThanOrEqualElseStatementNested<N> extends LessThanOrEqualFluent<LessThanOrEqualElseStatementNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualElseStatementNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endLessThanOrEqualElseStatement() {
      return and();
    }

  }

  public class LessThanOrEqualStatementNested<N> extends LessThanOrEqualFluent<LessThanOrEqualStatementNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;

    LessThanOrEqualStatementNested(LessThanOrEqual item) {
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLessThanOrEqualStatement() {
      return and();
    }

  }

  public class LessThanStatementNested<N> extends LessThanFluent<LessThanStatementNested<N>> implements Nested<N> {

    LessThanBuilder builder;

    LessThanStatementNested(LessThan item) {
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLessThanStatement() {
      return and();
    }

  }

  public class LogicalAndConditionNested<N> extends LogicalAndFluent<LogicalAndConditionNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndConditionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endLogicalAndCondition() {
      return and();
    }

  }

  public class LogicalAndElseStatementNested<N> extends LogicalAndFluent<LogicalAndElseStatementNested<N>>
      implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndElseStatementNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endLogicalAndElseStatement() {
      return and();
    }

  }

  public class LogicalAndStatementNested<N> extends LogicalAndFluent<LogicalAndStatementNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndStatementNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLogicalAndStatement() {
      return and();
    }

  }

  public class LogicalOrConditionNested<N> extends LogicalOrFluent<LogicalOrConditionNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrConditionNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endLogicalOrCondition() {
      return and();
    }

  }

  public class LogicalOrElseStatementNested<N> extends LogicalOrFluent<LogicalOrElseStatementNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrElseStatementNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endLogicalOrElseStatement() {
      return and();
    }

  }

  public class LogicalOrStatementNested<N> extends LogicalOrFluent<LogicalOrStatementNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrStatementNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLogicalOrStatement() {
      return and();
    }

  }

  public class MethodCallConditionNested<N> extends MethodCallFluent<MethodCallConditionNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallConditionNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endMethodCallCondition() {
      return and();
    }

  }

  public class MethodCallElseStatementNested<N> extends MethodCallFluent<MethodCallElseStatementNested<N>>
      implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallElseStatementNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endMethodCallElseStatement() {
      return and();
    }

  }

  public class MethodCallStatementNested<N> extends MethodCallFluent<MethodCallStatementNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallStatementNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endMethodCallStatement() {
      return and();
    }

  }

  public class MinusConditionNested<N> extends MinusFluent<MinusConditionNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusConditionNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endMinusCondition() {
      return and();
    }

  }

  public class MinusElseStatementNested<N> extends MinusFluent<MinusElseStatementNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusElseStatementNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endMinusElseStatement() {
      return and();
    }

  }

  public class MinusStatementNested<N> extends MinusFluent<MinusStatementNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusStatementNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endMinusStatement() {
      return and();
    }

  }

  public class ModuloConditionNested<N> extends ModuloFluent<ModuloConditionNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloConditionNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endModuloCondition() {
      return and();
    }

  }

  public class ModuloElseStatementNested<N> extends ModuloFluent<ModuloElseStatementNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloElseStatementNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endModuloElseStatement() {
      return and();
    }

  }

  public class ModuloStatementNested<N> extends ModuloFluent<ModuloStatementNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloStatementNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endModuloStatement() {
      return and();
    }

  }

  public class MultiplyConditionNested<N> extends MultiplyFluent<MultiplyConditionNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyConditionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endMultiplyCondition() {
      return and();
    }

  }

  public class MultiplyElseStatementNested<N> extends MultiplyFluent<MultiplyElseStatementNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyElseStatementNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endMultiplyElseStatement() {
      return and();
    }

  }

  public class MultiplyStatementNested<N> extends MultiplyFluent<MultiplyStatementNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyStatementNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endMultiplyStatement() {
      return and();
    }

  }

  public class NegativeConditionNested<N> extends NegativeFluent<NegativeConditionNested<N>> implements Nested<N> {

    NegativeBuilder builder;

    NegativeConditionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endNotEqualsCondition() {
      return and();
    }

  }

  public class NotEqualsElseStatementNested<N> extends NotEqualsFluent<NotEqualsElseStatementNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsElseStatementNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endNotEqualsElseStatement() {
      return and();
    }

  }

  public class NotEqualsStatementNested<N> extends NotEqualsFluent<NotEqualsStatementNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsStatementNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endNotEqualsStatement() {
      return and();
    }

  }

  public class PlusConditionNested<N> extends PlusFluent<PlusConditionNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusConditionNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPlusCondition() {
      return and();
    }

  }

  public class PlusElseStatementNested<N> extends PlusFluent<PlusElseStatementNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusElseStatementNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endPlusElseStatement() {
      return and();
    }

  }

  public class PlusStatementNested<N> extends PlusFluent<PlusStatementNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusStatementNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endPlusStatement() {
      return and();
    }

  }

  public class PositiveConditionNested<N> extends PositiveFluent<PositiveConditionNested<N>> implements Nested<N> {

    PositiveBuilder builder;

    PositiveConditionNested(Positive item) {
      this.builder = new PositiveBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPostDecrementCondition() {
      return and();
    }

  }

  public class PostDecrementElseStatementNested<N> extends PostDecrementFluent<PostDecrementElseStatementNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementElseStatementNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endPostDecrementElseStatement() {
      return and();
    }

  }

  public class PostDecrementStatementNested<N> extends PostDecrementFluent<PostDecrementStatementNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;

    PostDecrementStatementNested(PostDecrement item) {
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endPostDecrementStatement() {
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPostIncrementCondition() {
      return and();
    }

  }

  public class PostIncrementElseStatementNested<N> extends PostIncrementFluent<PostIncrementElseStatementNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementElseStatementNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endPostIncrementElseStatement() {
      return and();
    }

  }

  public class PostIncrementStatementNested<N> extends PostIncrementFluent<PostIncrementStatementNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;

    PostIncrementStatementNested(PostIncrement item) {
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endPostIncrementStatement() {
      return and();
    }

  }

  public class PreDecrementConditionNested<N> extends PreDecrementFluent<PreDecrementConditionNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementConditionNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPreDecrementCondition() {
      return and();
    }

  }

  public class PreDecrementElseStatementNested<N> extends PreDecrementFluent<PreDecrementElseStatementNested<N>>
      implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementElseStatementNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endPreDecrementElseStatement() {
      return and();
    }

  }

  public class PreDecrementStatementNested<N> extends PreDecrementFluent<PreDecrementStatementNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementStatementNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endPreDecrementStatement() {
      return and();
    }

  }

  public class PreIncrementConditionNested<N> extends PreIncrementFluent<PreIncrementConditionNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementConditionNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPreIncrementCondition() {
      return and();
    }

  }

  public class PreIncrementElseStatementNested<N> extends PreIncrementFluent<PreIncrementElseStatementNested<N>>
      implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementElseStatementNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endPreIncrementElseStatement() {
      return and();
    }

  }

  public class PreIncrementStatementNested<N> extends PreIncrementFluent<PreIncrementStatementNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementStatementNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endPreIncrementStatement() {
      return and();
    }

  }

  public class PropertyConditionNested<N> extends PropertyFluent<PropertyConditionNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyConditionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPropertyRefCondition() {
      return and();
    }

  }

  public class PropertyRefElseStatementNested<N> extends PropertyRefFluent<PropertyRefElseStatementNested<N>>
      implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefElseStatementNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endPropertyRefElseStatement() {
      return and();
    }

  }

  public class PropertyRefStatementNested<N> extends PropertyRefFluent<PropertyRefStatementNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefStatementNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endPropertyRefStatement() {
      return and();
    }

  }

  public class ReturnDslThisStepElseStatementNested<N> extends ReturnDslThisStepFluent<ReturnDslThisStepElseStatementNested<N>>
      implements Nested<N> {

    ReturnDslThisStepBuilder builder;

    ReturnDslThisStepElseStatementNested(ReturnDslThisStep item) {
      this.builder = new ReturnDslThisStepBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endReturnDslThisStepElseStatement() {
      return and();
    }

  }

  public class ReturnDslThisStepStatementNested<N> extends ReturnDslThisStepFluent<ReturnDslThisStepStatementNested<N>>
      implements Nested<N> {

    ReturnDslThisStepBuilder builder;

    ReturnDslThisStepStatementNested(ReturnDslThisStep item) {
      this.builder = new ReturnDslThisStepBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endReturnDslThisStepStatement() {
      return and();
    }

  }

  public class ReturnDslVariableStepElseStatementNested<N>
      extends ReturnDslVariableStepFluent<ReturnDslVariableStepElseStatementNested<N>> implements Nested<N> {

    ReturnDslVariableStepBuilder builder;

    ReturnDslVariableStepElseStatementNested(ReturnDslVariableStep item) {
      this.builder = new ReturnDslVariableStepBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endReturnDslVariableStepElseStatement() {
      return and();
    }

  }

  public class ReturnDslVariableStepStatementNested<N>
      extends ReturnDslVariableStepFluent<ReturnDslVariableStepStatementNested<N>> implements Nested<N> {

    ReturnDslVariableStepBuilder builder;

    ReturnDslVariableStepStatementNested(ReturnDslVariableStep item) {
      this.builder = new ReturnDslVariableStepBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endReturnDslVariableStepStatement() {
      return and();
    }

  }

  public class ReturnElseStatementNested<N> extends ReturnFluent<ReturnElseStatementNested<N>> implements Nested<N> {

    ReturnBuilder builder;

    ReturnElseStatementNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endReturnElseStatement() {
      return and();
    }

  }

  public class ReturnStatementNested<N> extends ReturnFluent<ReturnStatementNested<N>> implements Nested<N> {

    ReturnBuilder builder;

    ReturnStatementNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endReturnStatement() {
      return and();
    }

  }

  public class RightShiftConditionNested<N> extends RightShiftFluent<RightShiftConditionNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftConditionNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endRightShiftCondition() {
      return and();
    }

  }

  public class RightShiftElseStatementNested<N> extends RightShiftFluent<RightShiftElseStatementNested<N>>
      implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftElseStatementNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endRightShiftElseStatement() {
      return and();
    }

  }

  public class RightShiftStatementNested<N> extends RightShiftFluent<RightShiftStatementNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftStatementNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endRightShiftStatement() {
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endRightUnsignedShiftCondition() {
      return and();
    }

  }

  public class RightUnsignedShiftElseStatementNested<N>
      extends RightUnsignedShiftFluent<RightUnsignedShiftElseStatementNested<N>> implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftElseStatementNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endRightUnsignedShiftElseStatement() {
      return and();
    }

  }

  public class RightUnsignedShiftStatementNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftStatementNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;

    RightUnsignedShiftStatementNested(RightUnsignedShift item) {
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endRightUnsignedShiftStatement() {
      return and();
    }

  }

  public class StringStatementElseNested<N> extends StringStatementFluent<StringStatementElseNested<N>> implements Nested<N> {

    StringStatementBuilder builder;

    StringStatementElseNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endStringStatementElse() {
      return and();
    }

  }

  public class StringStatementNested<N> extends StringStatementFluent<StringStatementNested<N>> implements Nested<N> {

    StringStatementBuilder builder;

    StringStatementNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endStringStatement() {
      return and();
    }

  }

  public class SuperConditionNested<N> extends SuperFluent<SuperConditionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperConditionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endSuperCondition() {
      return and();
    }

  }

  public class SwitchElseStatementNested<N> extends SwitchFluent<SwitchElseStatementNested<N>> implements Nested<N> {

    SwitchBuilder builder;

    SwitchElseStatementNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endSwitchElseStatement() {
      return and();
    }

  }

  public class SwitchStatementNested<N> extends SwitchFluent<SwitchStatementNested<N>> implements Nested<N> {

    SwitchBuilder builder;

    SwitchStatementNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endSwitchStatement() {
      return and();
    }

  }

  public class SynchronizedElseStatementNested<N> extends SynchronizedFluent<SynchronizedElseStatementNested<N>>
      implements Nested<N> {

    SynchronizedBuilder builder;

    SynchronizedElseStatementNested(Synchronized item) {
      this.builder = new SynchronizedBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endSynchronizedElseStatement() {
      return and();
    }

  }

  public class SynchronizedStatementNested<N> extends SynchronizedFluent<SynchronizedStatementNested<N>> implements Nested<N> {

    SynchronizedBuilder builder;

    SynchronizedStatementNested(Synchronized item) {
      this.builder = new SynchronizedBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endSynchronizedStatement() {
      return and();
    }

  }

  public class TernaryConditionNested<N> extends TernaryFluent<TernaryConditionNested<N>> implements Nested<N> {

    TernaryBuilder builder;

    TernaryConditionNested(Ternary item) {
      this.builder = new TernaryBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endThisCondition() {
      return and();
    }

  }

  public class ThrowElseStatementNested<N> extends ThrowFluent<ThrowElseStatementNested<N>> implements Nested<N> {

    ThrowBuilder builder;

    ThrowElseStatementNested(Throw item) {
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endThrowElseStatement() {
      return and();
    }

  }

  public class ThrowStatementNested<N> extends ThrowFluent<ThrowStatementNested<N>> implements Nested<N> {

    ThrowBuilder builder;

    ThrowStatementNested(Throw item) {
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endThrowStatement() {
      return and();
    }

  }

  public class TryElseStatementNested<N> extends TryFluent<TryElseStatementNested<N>> implements Nested<N> {

    TryBuilder builder;

    TryElseStatementNested(Try item) {
      this.builder = new TryBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endTryElseStatement() {
      return and();
    }

  }

  public class TryStatementNested<N> extends TryFluent<TryStatementNested<N>> implements Nested<N> {

    TryBuilder builder;

    TryStatementNested(Try item) {
      this.builder = new TryBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endTryStatement() {
      return and();
    }

  }

  public class ValueRefConditionNested<N> extends ValueRefFluent<ValueRefConditionNested<N>> implements Nested<N> {

    ValueRefBuilder builder;

    ValueRefConditionNested(ValueRef item) {
      this.builder = new ValueRefBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endValueRefCondition() {
      return and();
    }

  }

  public class WhileElseStatementNested<N> extends WhileFluent<WhileElseStatementNested<N>> implements Nested<N> {

    WhileBuilder builder;

    WhileElseStatementNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endWhileElseStatement() {
      return and();
    }

  }

  public class WhileStatementNested<N> extends WhileFluent<WhileStatementNested<N>> implements Nested<N> {

    WhileBuilder builder;

    WhileStatementNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endWhileStatement() {
      return and();
    }

  }

  public class XorConditionNested<N> extends XorFluent<XorConditionNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorConditionNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endXorCondition() {
      return and();
    }

  }

  public class XorElseStatementNested<N> extends XorFluent<XorElseStatementNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorElseStatementNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withElseStatement(builder.build());
    }

    public N endXorElseStatement() {
      return and();
    }

  }

  public class XorStatementNested<N> extends XorFluent<XorStatementNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorStatementNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endXorStatement() {
      return and();
    }

  }
}
