package io.sundr.model;

import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class LambdaFluent<A extends io.sundr.model.LambdaFluent<A>> extends BaseFluent<A> {

  private List<String> parameters = new ArrayList<String>();
  private VisitableBuilder<? extends Statement, ?> statement;

  public LambdaFluent() {
  }

  public LambdaFluent(Lambda instance) {
    this.copyInstance(instance);
  }

  public A addAllToParameters(Collection<String> items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    for (String item : items) {
      this.parameters.add(item);
    }
    return (A) this;
  }

  public A addToParameters(String... items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    for (String item : items) {
      this.parameters.add(item);
    }
    return (A) this;
  }

  public A addToParameters(int index, String item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    this.parameters.add(index, item);
    return (A) this;
  }

  public Statement buildStatement() {
    return this.statement != null ? this.statement.build() : null;
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "ReturnDslThisStep":

        return (VisitableBuilder<T, ?>) new ReturnDslThisStepBuilder((ReturnDslThisStep) item);

      case "Multiply":

        return (VisitableBuilder<T, ?>) new MultiplyBuilder((Multiply) item);

      case "MethodCall":

        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);

      case "Try":

        return (VisitableBuilder<T, ?>) new TryBuilder((Try) item);

      case "Switch":

        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);

      case "Synchronized":

        return (VisitableBuilder<T, ?>) new SynchronizedBuilder((Synchronized) item);

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

      case "Break":

        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);

      case "LessThan":

        return (VisitableBuilder<T, ?>) new LessThanBuilder((LessThan) item);

      case "BitwiseOr":

        return (VisitableBuilder<T, ?>) new BitwiseOrBuilder((BitwiseOr) item);

      case "PropertyRef":

        return (VisitableBuilder<T, ?>) new PropertyRefBuilder((PropertyRef) item);

      case "RightShift":

        return (VisitableBuilder<T, ?>) new RightShiftBuilder((RightShift) item);

      case "GreaterThan":

        return (VisitableBuilder<T, ?>) new GreaterThanBuilder((GreaterThan) item);

      case "Declare":

        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);

      case "While":

        return (VisitableBuilder<T, ?>) new WhileBuilder((While) item);

      case "Continue":

        return (VisitableBuilder<T, ?>) new ContinueBuilder((Continue) item);

      case "Modulo":

        return (VisitableBuilder<T, ?>) new ModuloBuilder((Modulo) item);

      case "LeftShift":

        return (VisitableBuilder<T, ?>) new LeftShiftBuilder((LeftShift) item);

      case "Throw":

        return (VisitableBuilder<T, ?>) new ThrowBuilder((Throw) item);

      case "StringStatement":

        return (VisitableBuilder<T, ?>) new StringStatementBuilder((StringStatement) item);

      case "Empty":

        return (VisitableBuilder<T, ?>) new EmptyBuilder((Empty) item);

      case "BinaryExpression":

        return (VisitableBuilder<T, ?>) new BinaryExpressionBuilder((BinaryExpression) item);

      case "Equals":

        return (VisitableBuilder<T, ?>) new EqualsBuilder((Equals) item);

      case "Do":

        return (VisitableBuilder<T, ?>) new DoBuilder((Do) item);

      case "Foreach":

        return (VisitableBuilder<T, ?>) new ForeachBuilder((Foreach) item);

      case "Block":

        return (VisitableBuilder<T, ?>) new BlockBuilder((Block) item);

      case "PreDecrement":

        return (VisitableBuilder<T, ?>) new PreDecrementBuilder((PreDecrement) item);

      case "ReturnDslVariableStep":

        return (VisitableBuilder<T, ?>) new ReturnDslVariableStepBuilder((ReturnDslVariableStep) item);

      case "PostDecrement":

        return (VisitableBuilder<T, ?>) new PostDecrementBuilder((PostDecrement) item);

      case "If":

        return (VisitableBuilder<T, ?>) new IfBuilder((If) item);

      case "Lambda":

        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);

      case "Return":

        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);

      case "Assign":

        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);

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

      case "LessThanOrEqual":

        return (VisitableBuilder<T, ?>) new LessThanOrEqualBuilder((LessThanOrEqual) item);

      case "IfDslThenStep":

        return (VisitableBuilder<T, ?>) new IfDslThenStepBuilder((IfDslThenStep) item);

      case "For":

        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);

      default:

        return (VisitableBuilder<T, ?>) builderOf(item);

    }
  }

  protected void copyInstance(Lambda instance) {
    if (instance != null) {
      this.withParameters(instance.getParameters());
      this.withStatement(instance.getStatement());
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
    LambdaFluent that = (LambdaFluent) o;
    if (!(Objects.equals(parameters, that.parameters))) {
      return false;
    }
    if (!(Objects.equals(statement, that.statement))) {
      return false;
    }
    return true;
  }

  public String getFirstParameter() {
    return this.parameters.get(0);
  }

  public String getLastParameter() {
    return this.parameters.get(parameters.size() - 1);
  }

  public String getMatchingParameter(Predicate<String> predicate) {
    for (String item : parameters) {
      if (predicate.test(item)) {
        return item;
      }
    }
    return null;
  }

  public String getParameter(int index) {
    return this.parameters.get(index);
  }

  public List<String> getParameters() {
    return this.parameters;
  }

  public boolean hasMatchingParameter(Predicate<String> predicate) {
    for (String item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasParameters() {
    return this.parameters != null && !(this.parameters.isEmpty());
  }

  public boolean hasStatement() {
    return this.statement != null;
  }

  public int hashCode() {
    return Objects.hash(parameters, statement);
  }

  public A removeAllFromParameters(Collection<String> items) {
    if (this.parameters == null) {
      return (A) this;
    }
    for (String item : items) {
      this.parameters.remove(item);
    }
    return (A) this;
  }

  public A removeFromParameters(String... items) {
    if (this.parameters == null) {
      return (A) this;
    }
    for (String item : items) {
      this.parameters.remove(item);
    }
    return (A) this;
  }

  public A setToParameters(int index, String item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList();
    }
    this.parameters.set(index, item);
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(parameters == null) && !(parameters.isEmpty())) {
      sb.append("parameters:");
      sb.append(parameters);
      sb.append(",");
    }
    if (!(statement == null)) {
      sb.append("statement:");
      sb.append(statement);
    }
    sb.append("}");
    return sb.toString();
  }

  public AssignStatementNested<A> withNewAssignStatement() {
    return new AssignStatementNested(null);
  }

  public AssignStatementNested<A> withNewAssignStatementLike(Assign item) {
    return new AssignStatementNested(item);
  }

  public BinaryExpressionStatementNested<A> withNewBinaryExpressionStatement() {
    return new BinaryExpressionStatementNested(null);
  }

  public BinaryExpressionStatementNested<A> withNewBinaryExpressionStatementLike(BinaryExpression item) {
    return new BinaryExpressionStatementNested(item);
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

  public BitwiseOrStatementNested<A> withNewBitwiseOrStatement() {
    return new BitwiseOrStatementNested(null);
  }

  public A withNewBitwiseOrStatement(Object left, Object right) {
    return (A) this.withStatement(new BitwiseOr(left, right));
  }

  public BitwiseOrStatementNested<A> withNewBitwiseOrStatementLike(BitwiseOr item) {
    return new BitwiseOrStatementNested(item);
  }

  public BlockStatementNested<A> withNewBlockStatement() {
    return new BlockStatementNested(null);
  }

  public BlockStatementNested<A> withNewBlockStatementLike(Block item) {
    return new BlockStatementNested(item);
  }

  public BreakStatementNested<A> withNewBreakStatement() {
    return new BreakStatementNested(null);
  }

  public BreakStatementNested<A> withNewBreakStatementLike(Break item) {
    return new BreakStatementNested(item);
  }

  public ConstructStatementNested<A> withNewConstructStatement() {
    return new ConstructStatementNested(null);
  }

  public ConstructStatementNested<A> withNewConstructStatementLike(Construct item) {
    return new ConstructStatementNested(item);
  }

  public ContinueStatementNested<A> withNewContinueStatement() {
    return new ContinueStatementNested(null);
  }

  public ContinueStatementNested<A> withNewContinueStatementLike(Continue item) {
    return new ContinueStatementNested(item);
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

  public DivideStatementNested<A> withNewDivideStatement() {
    return new DivideStatementNested(null);
  }

  public A withNewDivideStatement(Object left, Object right) {
    return (A) this.withStatement(new Divide(left, right));
  }

  public DivideStatementNested<A> withNewDivideStatementLike(Divide item) {
    return new DivideStatementNested(item);
  }

  public DoStatementNested<A> withNewDoStatement() {
    return new DoStatementNested(null);
  }

  public DoStatementNested<A> withNewDoStatementLike(Do item) {
    return new DoStatementNested(item);
  }

  public EmptyStatementNested<A> withNewEmptyStatement() {
    return new EmptyStatementNested(null);
  }

  public EmptyStatementNested<A> withNewEmptyStatementLike(Empty item) {
    return new EmptyStatementNested(item);
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

  public ForStatementNested<A> withNewForStatement() {
    return new ForStatementNested(null);
  }

  public ForStatementNested<A> withNewForStatementLike(For item) {
    return new ForStatementNested(item);
  }

  public ForeachStatementNested<A> withNewForeachStatement() {
    return new ForeachStatementNested(null);
  }

  public ForeachStatementNested<A> withNewForeachStatementLike(Foreach item) {
    return new ForeachStatementNested(item);
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

  public IfDslThenStepStatementNested<A> withNewIfDslThenStepStatement() {
    return new IfDslThenStepStatementNested(null);
  }

  public IfDslThenStepStatementNested<A> withNewIfDslThenStepStatementLike(IfDslThenStep item) {
    return new IfDslThenStepStatementNested(item);
  }

  public IfStatementNested<A> withNewIfStatement() {
    return new IfStatementNested(null);
  }

  public IfStatementNested<A> withNewIfStatementLike(If item) {
    return new IfStatementNested(item);
  }

  public LambdaStatementNested<A> withNewLambdaStatement() {
    return new LambdaStatementNested(null);
  }

  public LambdaStatementNested<A> withNewLambdaStatementLike(Lambda item) {
    return new LambdaStatementNested(item);
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

  public LogicalAndStatementNested<A> withNewLogicalAndStatement() {
    return new LogicalAndStatementNested(null);
  }

  public A withNewLogicalAndStatement(Object left, Object right) {
    return (A) this.withStatement(new LogicalAnd(left, right));
  }

  public LogicalAndStatementNested<A> withNewLogicalAndStatementLike(LogicalAnd item) {
    return new LogicalAndStatementNested(item);
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

  public MethodCallStatementNested<A> withNewMethodCallStatement() {
    return new MethodCallStatementNested(null);
  }

  public MethodCallStatementNested<A> withNewMethodCallStatementLike(MethodCall item) {
    return new MethodCallStatementNested(item);
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

  public ModuloStatementNested<A> withNewModuloStatement() {
    return new ModuloStatementNested(null);
  }

  public A withNewModuloStatement(Object left, Object right) {
    return (A) this.withStatement(new Modulo(left, right));
  }

  public ModuloStatementNested<A> withNewModuloStatementLike(Modulo item) {
    return new ModuloStatementNested(item);
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

  public NotEqualsStatementNested<A> withNewNotEqualsStatement() {
    return new NotEqualsStatementNested(null);
  }

  public A withNewNotEqualsStatement(Object left, Object right) {
    return (A) this.withStatement(new NotEquals(left, right));
  }

  public NotEqualsStatementNested<A> withNewNotEqualsStatementLike(NotEquals item) {
    return new NotEqualsStatementNested(item);
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

  public PostDecrementStatementNested<A> withNewPostDecrementStatement() {
    return new PostDecrementStatementNested(null);
  }

  public PostDecrementStatementNested<A> withNewPostDecrementStatementLike(PostDecrement item) {
    return new PostDecrementStatementNested(item);
  }

  public PostIncrementStatementNested<A> withNewPostIncrementStatement() {
    return new PostIncrementStatementNested(null);
  }

  public PostIncrementStatementNested<A> withNewPostIncrementStatementLike(PostIncrement item) {
    return new PostIncrementStatementNested(item);
  }

  public PreDecrementStatementNested<A> withNewPreDecrementStatement() {
    return new PreDecrementStatementNested(null);
  }

  public PreDecrementStatementNested<A> withNewPreDecrementStatementLike(PreDecrement item) {
    return new PreDecrementStatementNested(item);
  }

  public PreIncrementStatementNested<A> withNewPreIncrementStatement() {
    return new PreIncrementStatementNested(null);
  }

  public PreIncrementStatementNested<A> withNewPreIncrementStatementLike(PreIncrement item) {
    return new PreIncrementStatementNested(item);
  }

  public PropertyRefStatementNested<A> withNewPropertyRefStatement() {
    return new PropertyRefStatementNested(null);
  }

  public PropertyRefStatementNested<A> withNewPropertyRefStatementLike(PropertyRef item) {
    return new PropertyRefStatementNested(item);
  }

  public ReturnDslThisStepStatementNested<A> withNewReturnDslThisStepStatement() {
    return new ReturnDslThisStepStatementNested(null);
  }

  public ReturnDslThisStepStatementNested<A> withNewReturnDslThisStepStatementLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepStatementNested(item);
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

  public ReturnStatementNested<A> withNewReturnStatement() {
    return new ReturnStatementNested(null);
  }

  public A withNewReturnStatement(Object object) {
    return (A) this.withStatement(new Return(object));
  }

  public ReturnStatementNested<A> withNewReturnStatementLike(Return item) {
    return new ReturnStatementNested(item);
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

  public StringStatementNested<A> withNewStringStatementLike(StringStatement item) {
    return new StringStatementNested(item);
  }

  public SwitchStatementNested<A> withNewSwitchStatement() {
    return new SwitchStatementNested(null);
  }

  public SwitchStatementNested<A> withNewSwitchStatementLike(Switch item) {
    return new SwitchStatementNested(item);
  }

  public SynchronizedStatementNested<A> withNewSynchronizedStatement() {
    return new SynchronizedStatementNested(null);
  }

  public SynchronizedStatementNested<A> withNewSynchronizedStatementLike(Synchronized item) {
    return new SynchronizedStatementNested(item);
  }

  public ThrowStatementNested<A> withNewThrowStatement() {
    return new ThrowStatementNested(null);
  }

  public ThrowStatementNested<A> withNewThrowStatementLike(Throw item) {
    return new ThrowStatementNested(item);
  }

  public TryStatementNested<A> withNewTryStatement() {
    return new TryStatementNested(null);
  }

  public TryStatementNested<A> withNewTryStatementLike(Try item) {
    return new TryStatementNested(item);
  }

  public WhileStatementNested<A> withNewWhileStatement() {
    return new WhileStatementNested(null);
  }

  public WhileStatementNested<A> withNewWhileStatementLike(While item) {
    return new WhileStatementNested(item);
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

  public A withParameters(List<String> parameters) {
    if (parameters != null) {
      this.parameters = new ArrayList();
      for (String item : parameters) {
        this.addToParameters(item);
      }
    } else {
      this.parameters = null;
    }
    return (A) this;
  }

  public A withParameters(String... parameters) {
    if (this.parameters != null) {
      this.parameters.clear();
      _visitables.remove("parameters");
    }
    if (parameters != null) {
      for (String item : parameters) {
        this.addToParameters(item);
      }
    }
    return (A) this;
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

  public class AssignStatementNested<N> extends AssignFluent<AssignStatementNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignStatementNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endAssignStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endBinaryExpressionStatement() {
      return and();
    }

  }

  public class BitwiseAndStatementNested<N> extends BitwiseAndFluent<BitwiseAndStatementNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;

    BitwiseAndStatementNested(BitwiseAnd item) {
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endBitwiseAndStatement() {
      return and();
    }

  }

  public class BitwiseOrStatementNested<N> extends BitwiseOrFluent<BitwiseOrStatementNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;

    BitwiseOrStatementNested(BitwiseOr item) {
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endBitwiseOrStatement() {
      return and();
    }

  }

  public class BlockStatementNested<N> extends BlockFluent<BlockStatementNested<N>> implements Nested<N> {

    BlockBuilder builder;

    BlockStatementNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endBlockStatement() {
      return and();
    }

  }

  public class BreakStatementNested<N> extends BreakFluent<BreakStatementNested<N>> implements Nested<N> {

    BreakBuilder builder;

    BreakStatementNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endBreakStatement() {
      return and();
    }

  }

  public class ConstructStatementNested<N> extends ConstructFluent<ConstructStatementNested<N>> implements Nested<N> {

    ConstructBuilder builder;

    ConstructStatementNested(Construct item) {
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endConstructStatement() {
      return and();
    }

  }

  public class ContinueStatementNested<N> extends ContinueFluent<ContinueStatementNested<N>> implements Nested<N> {

    ContinueBuilder builder;

    ContinueStatementNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endContinueStatement() {
      return and();
    }

  }

  public class DeclareStatementNested<N> extends DeclareFluent<DeclareStatementNested<N>> implements Nested<N> {

    DeclareBuilder builder;

    DeclareStatementNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endDeclareStatement() {
      return and();
    }

  }

  public class DivideStatementNested<N> extends DivideFluent<DivideStatementNested<N>> implements Nested<N> {

    DivideBuilder builder;

    DivideStatementNested(Divide item) {
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endDivideStatement() {
      return and();
    }

  }

  public class DoStatementNested<N> extends DoFluent<DoStatementNested<N>> implements Nested<N> {

    DoBuilder builder;

    DoStatementNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endDoStatement() {
      return and();
    }

  }

  public class EmptyStatementNested<N> extends EmptyFluent<EmptyStatementNested<N>> implements Nested<N> {

    EmptyBuilder builder;

    EmptyStatementNested(Empty item) {
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endEmptyStatement() {
      return and();
    }

  }

  public class EqualsStatementNested<N> extends EqualsFluent<EqualsStatementNested<N>> implements Nested<N> {

    EqualsBuilder builder;

    EqualsStatementNested(Equals item) {
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endEqualsStatement() {
      return and();
    }

  }

  public class ForStatementNested<N> extends ForFluent<ForStatementNested<N>> implements Nested<N> {

    ForBuilder builder;

    ForStatementNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endForStatement() {
      return and();
    }

  }

  public class ForeachStatementNested<N> extends ForeachFluent<ForeachStatementNested<N>> implements Nested<N> {

    ForeachBuilder builder;

    ForeachStatementNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endForeachStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endGreaterThanStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endIfDslThenStepStatement() {
      return and();
    }

  }

  public class IfStatementNested<N> extends IfFluent<IfStatementNested<N>> implements Nested<N> {

    IfBuilder builder;

    IfStatementNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endIfStatement() {
      return and();
    }

  }

  public class LambdaStatementNested<N> extends LambdaFluent<LambdaStatementNested<N>> implements Nested<N> {

    LambdaBuilder builder;

    LambdaStatementNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endLambdaStatement() {
      return and();
    }

  }

  public class LeftShiftStatementNested<N> extends LeftShiftFluent<LeftShiftStatementNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;

    LeftShiftStatementNested(LeftShift item) {
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endLeftShiftStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endLessThanStatement() {
      return and();
    }

  }

  public class LogicalAndStatementNested<N> extends LogicalAndFluent<LogicalAndStatementNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;

    LogicalAndStatementNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endLogicalAndStatement() {
      return and();
    }

  }

  public class LogicalOrStatementNested<N> extends LogicalOrFluent<LogicalOrStatementNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;

    LogicalOrStatementNested(LogicalOr item) {
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endLogicalOrStatement() {
      return and();
    }

  }

  public class MethodCallStatementNested<N> extends MethodCallFluent<MethodCallStatementNested<N>> implements Nested<N> {

    MethodCallBuilder builder;

    MethodCallStatementNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endMethodCallStatement() {
      return and();
    }

  }

  public class MinusStatementNested<N> extends MinusFluent<MinusStatementNested<N>> implements Nested<N> {

    MinusBuilder builder;

    MinusStatementNested(Minus item) {
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endMinusStatement() {
      return and();
    }

  }

  public class ModuloStatementNested<N> extends ModuloFluent<ModuloStatementNested<N>> implements Nested<N> {

    ModuloBuilder builder;

    ModuloStatementNested(Modulo item) {
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endModuloStatement() {
      return and();
    }

  }

  public class MultiplyStatementNested<N> extends MultiplyFluent<MultiplyStatementNested<N>> implements Nested<N> {

    MultiplyBuilder builder;

    MultiplyStatementNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endMultiplyStatement() {
      return and();
    }

  }

  public class NotEqualsStatementNested<N> extends NotEqualsFluent<NotEqualsStatementNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;

    NotEqualsStatementNested(NotEquals item) {
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endNotEqualsStatement() {
      return and();
    }

  }

  public class PlusStatementNested<N> extends PlusFluent<PlusStatementNested<N>> implements Nested<N> {

    PlusBuilder builder;

    PlusStatementNested(Plus item) {
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endPlusStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endPostDecrementStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endPostIncrementStatement() {
      return and();
    }

  }

  public class PreDecrementStatementNested<N> extends PreDecrementFluent<PreDecrementStatementNested<N>> implements Nested<N> {

    PreDecrementBuilder builder;

    PreDecrementStatementNested(PreDecrement item) {
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endPreDecrementStatement() {
      return and();
    }

  }

  public class PreIncrementStatementNested<N> extends PreIncrementFluent<PreIncrementStatementNested<N>> implements Nested<N> {

    PreIncrementBuilder builder;

    PreIncrementStatementNested(PreIncrement item) {
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endPreIncrementStatement() {
      return and();
    }

  }

  public class PropertyRefStatementNested<N> extends PropertyRefFluent<PropertyRefStatementNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;

    PropertyRefStatementNested(PropertyRef item) {
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endPropertyRefStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endReturnDslThisStepStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endReturnDslVariableStepStatement() {
      return and();
    }

  }

  public class ReturnStatementNested<N> extends ReturnFluent<ReturnStatementNested<N>> implements Nested<N> {

    ReturnBuilder builder;

    ReturnStatementNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endReturnStatement() {
      return and();
    }

  }

  public class RightShiftStatementNested<N> extends RightShiftFluent<RightShiftStatementNested<N>> implements Nested<N> {

    RightShiftBuilder builder;

    RightShiftStatementNested(RightShift item) {
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endRightShiftStatement() {
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endRightUnsignedShiftStatement() {
      return and();
    }

  }

  public class StringStatementNested<N> extends StringStatementFluent<StringStatementNested<N>> implements Nested<N> {

    StringStatementBuilder builder;

    StringStatementNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endStringStatement() {
      return and();
    }

  }

  public class SwitchStatementNested<N> extends SwitchFluent<SwitchStatementNested<N>> implements Nested<N> {

    SwitchBuilder builder;

    SwitchStatementNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endSwitchStatement() {
      return and();
    }

  }

  public class SynchronizedStatementNested<N> extends SynchronizedFluent<SynchronizedStatementNested<N>> implements Nested<N> {

    SynchronizedBuilder builder;

    SynchronizedStatementNested(Synchronized item) {
      this.builder = new SynchronizedBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endSynchronizedStatement() {
      return and();
    }

  }

  public class ThrowStatementNested<N> extends ThrowFluent<ThrowStatementNested<N>> implements Nested<N> {

    ThrowBuilder builder;

    ThrowStatementNested(Throw item) {
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endThrowStatement() {
      return and();
    }

  }

  public class TryStatementNested<N> extends TryFluent<TryStatementNested<N>> implements Nested<N> {

    TryBuilder builder;

    TryStatementNested(Try item) {
      this.builder = new TryBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endTryStatement() {
      return and();
    }

  }

  public class WhileStatementNested<N> extends WhileFluent<WhileStatementNested<N>> implements Nested<N> {

    WhileBuilder builder;

    WhileStatementNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endWhileStatement() {
      return and();
    }

  }

  public class XorStatementNested<N> extends XorFluent<XorStatementNested<N>> implements Nested<N> {

    XorBuilder builder;

    XorStatementNested(Xor item) {
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endXorStatement() {
      return and();
    }

  }
}
