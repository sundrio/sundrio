package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
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
public class IfDslThenStepFluent<A extends io.sundr.model.IfDslThenStepFluent<A>> extends BaseFluent<A> {

  private VisitableBuilder<? extends Expression, ?> condition;
  private ArrayList<VisitableBuilder<? extends Statement, ?>> statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();

  public IfDslThenStepFluent() {
  }

  public IfDslThenStepFluent(IfDslThenStep instance) {
    this.copyInstance(instance);
  }

  public A addAllToStatements(Collection<Statement> items) {
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public AssignStatementsNested<A> addNewAssignStatement() {
    return new AssignStatementsNested(-1, null);
  }

  public AssignStatementsNested<A> addNewAssignStatementLike(Assign item) {
    return new AssignStatementsNested(-1, item);
  }

  public BinaryExpressionStatementsNested<A> addNewBinaryExpressionStatement() {
    return new BinaryExpressionStatementsNested(-1, null);
  }

  public BinaryExpressionStatementsNested<A> addNewBinaryExpressionStatementLike(BinaryExpression item) {
    return new BinaryExpressionStatementsNested(-1, item);
  }

  public BitwiseAndStatementsNested<A> addNewBitwiseAndStatement() {
    return new BitwiseAndStatementsNested(-1, null);
  }

  public A addNewBitwiseAndStatement(Object left, Object right) {
    return (A) this.addToStatements(new BitwiseAnd(left, right));
  }

  public BitwiseAndStatementsNested<A> addNewBitwiseAndStatementLike(BitwiseAnd item) {
    return new BitwiseAndStatementsNested(-1, item);
  }

  public BitwiseOrStatementsNested<A> addNewBitwiseOrStatement() {
    return new BitwiseOrStatementsNested(-1, null);
  }

  public A addNewBitwiseOrStatement(Object left, Object right) {
    return (A) this.addToStatements(new BitwiseOr(left, right));
  }

  public BitwiseOrStatementsNested<A> addNewBitwiseOrStatementLike(BitwiseOr item) {
    return new BitwiseOrStatementsNested(-1, item);
  }

  public BlockStatementsNested<A> addNewBlockStatement() {
    return new BlockStatementsNested(-1, null);
  }

  public BlockStatementsNested<A> addNewBlockStatementLike(Block item) {
    return new BlockStatementsNested(-1, item);
  }

  public BreakStatementsNested<A> addNewBreakStatement() {
    return new BreakStatementsNested(-1, null);
  }

  public BreakStatementsNested<A> addNewBreakStatementLike(Break item) {
    return new BreakStatementsNested(-1, item);
  }

  public ConstructStatementsNested<A> addNewConstructStatement() {
    return new ConstructStatementsNested(-1, null);
  }

  public ConstructStatementsNested<A> addNewConstructStatementLike(Construct item) {
    return new ConstructStatementsNested(-1, item);
  }

  public ContinueStatementsNested<A> addNewContinueStatement() {
    return new ContinueStatementsNested(-1, null);
  }

  public ContinueStatementsNested<A> addNewContinueStatementLike(Continue item) {
    return new ContinueStatementsNested(-1, item);
  }

  public DeclareStatementsNested<A> addNewDeclareStatement() {
    return new DeclareStatementsNested(-1, null);
  }

  public A addNewDeclareStatement(Class type, String name) {
    return (A) this.addToStatements(new Declare(type, name));
  }

  public A addNewDeclareStatement(Class type, String name, Object value) {
    return (A) this.addToStatements(new Declare(type, name, value));
  }

  public DeclareStatementsNested<A> addNewDeclareStatementLike(Declare item) {
    return new DeclareStatementsNested(-1, item);
  }

  public DivideStatementsNested<A> addNewDivideStatement() {
    return new DivideStatementsNested(-1, null);
  }

  public A addNewDivideStatement(Object left, Object right) {
    return (A) this.addToStatements(new Divide(left, right));
  }

  public DivideStatementsNested<A> addNewDivideStatementLike(Divide item) {
    return new DivideStatementsNested(-1, item);
  }

  public DoStatementsNested<A> addNewDoStatement() {
    return new DoStatementsNested(-1, null);
  }

  public DoStatementsNested<A> addNewDoStatementLike(Do item) {
    return new DoStatementsNested(-1, item);
  }

  public EmptyStatementsNested<A> addNewEmptyStatement() {
    return new EmptyStatementsNested(-1, null);
  }

  public EmptyStatementsNested<A> addNewEmptyStatementLike(Empty item) {
    return new EmptyStatementsNested(-1, item);
  }

  public EqualsStatementsNested<A> addNewEqualsStatement() {
    return new EqualsStatementsNested(-1, null);
  }

  public A addNewEqualsStatement(Object left, Object right) {
    return (A) this.addToStatements(new Equals(left, right));
  }

  public EqualsStatementsNested<A> addNewEqualsStatementLike(Equals item) {
    return new EqualsStatementsNested(-1, item);
  }

  public ForStatementsNested<A> addNewForStatement() {
    return new ForStatementsNested(-1, null);
  }

  public ForStatementsNested<A> addNewForStatementLike(For item) {
    return new ForStatementsNested(-1, item);
  }

  public ForeachStatementsNested<A> addNewForeachStatement() {
    return new ForeachStatementsNested(-1, null);
  }

  public ForeachStatementsNested<A> addNewForeachStatementLike(Foreach item) {
    return new ForeachStatementsNested(-1, item);
  }

  public GreaterThanOrEqualStatementsNested<A> addNewGreaterThanOrEqualStatement() {
    return new GreaterThanOrEqualStatementsNested(-1, null);
  }

  public A addNewGreaterThanOrEqualStatement(Object left, Object right) {
    return (A) this.addToStatements(new GreaterThanOrEqual(left, right));
  }

  public GreaterThanOrEqualStatementsNested<A> addNewGreaterThanOrEqualStatementLike(GreaterThanOrEqual item) {
    return new GreaterThanOrEqualStatementsNested(-1, item);
  }

  public GreaterThanStatementsNested<A> addNewGreaterThanStatement() {
    return new GreaterThanStatementsNested(-1, null);
  }

  public A addNewGreaterThanStatement(Object left, Object right) {
    return (A) this.addToStatements(new GreaterThan(left, right));
  }

  public GreaterThanStatementsNested<A> addNewGreaterThanStatementLike(GreaterThan item) {
    return new GreaterThanStatementsNested(-1, item);
  }

  public IfDslThenStepStatementsNested<A> addNewIfDslThenStepStatement() {
    return new IfDslThenStepStatementsNested(-1, null);
  }

  public IfDslThenStepStatementsNested<A> addNewIfDslThenStepStatementLike(IfDslThenStep item) {
    return new IfDslThenStepStatementsNested(-1, item);
  }

  public IfStatementsNested<A> addNewIfStatement() {
    return new IfStatementsNested(-1, null);
  }

  public IfStatementsNested<A> addNewIfStatementLike(If item) {
    return new IfStatementsNested(-1, item);
  }

  public LambdaStatementsNested<A> addNewLambdaStatement() {
    return new LambdaStatementsNested(-1, null);
  }

  public LambdaStatementsNested<A> addNewLambdaStatementLike(Lambda item) {
    return new LambdaStatementsNested(-1, item);
  }

  public LeftShiftStatementsNested<A> addNewLeftShiftStatement() {
    return new LeftShiftStatementsNested(-1, null);
  }

  public A addNewLeftShiftStatement(Object left, Object right) {
    return (A) this.addToStatements(new LeftShift(left, right));
  }

  public LeftShiftStatementsNested<A> addNewLeftShiftStatementLike(LeftShift item) {
    return new LeftShiftStatementsNested(-1, item);
  }

  public LessThanOrEqualStatementsNested<A> addNewLessThanOrEqualStatement() {
    return new LessThanOrEqualStatementsNested(-1, null);
  }

  public A addNewLessThanOrEqualStatement(Object left, Object right) {
    return (A) this.addToStatements(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualStatementsNested<A> addNewLessThanOrEqualStatementLike(LessThanOrEqual item) {
    return new LessThanOrEqualStatementsNested(-1, item);
  }

  public LessThanStatementsNested<A> addNewLessThanStatement() {
    return new LessThanStatementsNested(-1, null);
  }

  public A addNewLessThanStatement(Object left, Object right) {
    return (A) this.addToStatements(new LessThan(left, right));
  }

  public LessThanStatementsNested<A> addNewLessThanStatementLike(LessThan item) {
    return new LessThanStatementsNested(-1, item);
  }

  public LogicalAndStatementsNested<A> addNewLogicalAndStatement() {
    return new LogicalAndStatementsNested(-1, null);
  }

  public A addNewLogicalAndStatement(Object left, Object right) {
    return (A) this.addToStatements(new LogicalAnd(left, right));
  }

  public LogicalAndStatementsNested<A> addNewLogicalAndStatementLike(LogicalAnd item) {
    return new LogicalAndStatementsNested(-1, item);
  }

  public LogicalOrStatementsNested<A> addNewLogicalOrStatement() {
    return new LogicalOrStatementsNested(-1, null);
  }

  public A addNewLogicalOrStatement(Object left, Object right) {
    return (A) this.addToStatements(new LogicalOr(left, right));
  }

  public LogicalOrStatementsNested<A> addNewLogicalOrStatementLike(LogicalOr item) {
    return new LogicalOrStatementsNested(-1, item);
  }

  public MethodCallStatementsNested<A> addNewMethodCallStatement() {
    return new MethodCallStatementsNested(-1, null);
  }

  public MethodCallStatementsNested<A> addNewMethodCallStatementLike(MethodCall item) {
    return new MethodCallStatementsNested(-1, item);
  }

  public MinusStatementsNested<A> addNewMinusStatement() {
    return new MinusStatementsNested(-1, null);
  }

  public A addNewMinusStatement(Object left, Object right) {
    return (A) this.addToStatements(new Minus(left, right));
  }

  public MinusStatementsNested<A> addNewMinusStatementLike(Minus item) {
    return new MinusStatementsNested(-1, item);
  }

  public ModuloStatementsNested<A> addNewModuloStatement() {
    return new ModuloStatementsNested(-1, null);
  }

  public A addNewModuloStatement(Object left, Object right) {
    return (A) this.addToStatements(new Modulo(left, right));
  }

  public ModuloStatementsNested<A> addNewModuloStatementLike(Modulo item) {
    return new ModuloStatementsNested(-1, item);
  }

  public MultiplyStatementsNested<A> addNewMultiplyStatement() {
    return new MultiplyStatementsNested(-1, null);
  }

  public A addNewMultiplyStatement(Object left, Object right) {
    return (A) this.addToStatements(new Multiply(left, right));
  }

  public MultiplyStatementsNested<A> addNewMultiplyStatementLike(Multiply item) {
    return new MultiplyStatementsNested(-1, item);
  }

  public NotEqualsStatementsNested<A> addNewNotEqualsStatement() {
    return new NotEqualsStatementsNested(-1, null);
  }

  public A addNewNotEqualsStatement(Object left, Object right) {
    return (A) this.addToStatements(new NotEquals(left, right));
  }

  public NotEqualsStatementsNested<A> addNewNotEqualsStatementLike(NotEquals item) {
    return new NotEqualsStatementsNested(-1, item);
  }

  public PlusStatementsNested<A> addNewPlusStatement() {
    return new PlusStatementsNested(-1, null);
  }

  public A addNewPlusStatement(Object left, Object right) {
    return (A) this.addToStatements(new Plus(left, right));
  }

  public PlusStatementsNested<A> addNewPlusStatementLike(Plus item) {
    return new PlusStatementsNested(-1, item);
  }

  public PostDecrementStatementsNested<A> addNewPostDecrementStatement() {
    return new PostDecrementStatementsNested(-1, null);
  }

  public PostDecrementStatementsNested<A> addNewPostDecrementStatementLike(PostDecrement item) {
    return new PostDecrementStatementsNested(-1, item);
  }

  public PostIncrementStatementsNested<A> addNewPostIncrementStatement() {
    return new PostIncrementStatementsNested(-1, null);
  }

  public PostIncrementStatementsNested<A> addNewPostIncrementStatementLike(PostIncrement item) {
    return new PostIncrementStatementsNested(-1, item);
  }

  public PreDecrementStatementsNested<A> addNewPreDecrementStatement() {
    return new PreDecrementStatementsNested(-1, null);
  }

  public PreDecrementStatementsNested<A> addNewPreDecrementStatementLike(PreDecrement item) {
    return new PreDecrementStatementsNested(-1, item);
  }

  public PreIncrementStatementsNested<A> addNewPreIncrementStatement() {
    return new PreIncrementStatementsNested(-1, null);
  }

  public PreIncrementStatementsNested<A> addNewPreIncrementStatementLike(PreIncrement item) {
    return new PreIncrementStatementsNested(-1, item);
  }

  public PropertyRefStatementsNested<A> addNewPropertyRefStatement() {
    return new PropertyRefStatementsNested(-1, null);
  }

  public PropertyRefStatementsNested<A> addNewPropertyRefStatementLike(PropertyRef item) {
    return new PropertyRefStatementsNested(-1, item);
  }

  public ReturnDslThisStepStatementsNested<A> addNewReturnDslThisStepStatement() {
    return new ReturnDslThisStepStatementsNested(-1, null);
  }

  public ReturnDslThisStepStatementsNested<A> addNewReturnDslThisStepStatementLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepStatementsNested(-1, item);
  }

  public ReturnDslVariableStepStatementsNested<A> addNewReturnDslVariableStepStatement() {
    return new ReturnDslVariableStepStatementsNested(-1, null);
  }

  public A addNewReturnDslVariableStepStatement(String name) {
    return (A) this.addToStatements(new ReturnDslVariableStep(name));
  }

  public ReturnDslVariableStepStatementsNested<A> addNewReturnDslVariableStepStatementLike(ReturnDslVariableStep item) {
    return new ReturnDslVariableStepStatementsNested(-1, item);
  }

  public ReturnStatementsNested<A> addNewReturnStatement() {
    return new ReturnStatementsNested(-1, null);
  }

  public A addNewReturnStatement(Object object) {
    return (A) this.addToStatements(new Return(object));
  }

  public ReturnStatementsNested<A> addNewReturnStatementLike(Return item) {
    return new ReturnStatementsNested(-1, item);
  }

  public RightShiftStatementsNested<A> addNewRightShiftStatement() {
    return new RightShiftStatementsNested(-1, null);
  }

  public A addNewRightShiftStatement(Object left, Object right) {
    return (A) this.addToStatements(new RightShift(left, right));
  }

  public RightShiftStatementsNested<A> addNewRightShiftStatementLike(RightShift item) {
    return new RightShiftStatementsNested(-1, item);
  }

  public RightUnsignedShiftStatementsNested<A> addNewRightUnsignedShiftStatement() {
    return new RightUnsignedShiftStatementsNested(-1, null);
  }

  public A addNewRightUnsignedShiftStatement(Object left, Object right) {
    return (A) this.addToStatements(new RightUnsignedShift(left, right));
  }

  public RightUnsignedShiftStatementsNested<A> addNewRightUnsignedShiftStatementLike(RightUnsignedShift item) {
    return new RightUnsignedShiftStatementsNested(-1, item);
  }

  public StringStatementStatementsNested<A> addNewStringStatementStatement() {
    return new StringStatementStatementsNested(-1, null);
  }

  public A addNewStringStatementStatement(String data) {
    return (A) this.addToStatements(new StringStatement(data));
  }

  public A addNewStringStatementStatement(String data, Object[] parameters) {
    return (A) this.addToStatements(new StringStatement(data, parameters));
  }

  public StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item) {
    return new StringStatementStatementsNested(-1, item);
  }

  public SwitchStatementsNested<A> addNewSwitchStatement() {
    return new SwitchStatementsNested(-1, null);
  }

  public SwitchStatementsNested<A> addNewSwitchStatementLike(Switch item) {
    return new SwitchStatementsNested(-1, item);
  }

  public SynchronizedStatementsNested<A> addNewSynchronizedStatement() {
    return new SynchronizedStatementsNested(-1, null);
  }

  public SynchronizedStatementsNested<A> addNewSynchronizedStatementLike(Synchronized item) {
    return new SynchronizedStatementsNested(-1, item);
  }

  public ThrowStatementsNested<A> addNewThrowStatement() {
    return new ThrowStatementsNested(-1, null);
  }

  public ThrowStatementsNested<A> addNewThrowStatementLike(Throw item) {
    return new ThrowStatementsNested(-1, item);
  }

  public TryStatementsNested<A> addNewTryStatement() {
    return new TryStatementsNested(-1, null);
  }

  public TryStatementsNested<A> addNewTryStatementLike(Try item) {
    return new TryStatementsNested(-1, item);
  }

  public WhileStatementsNested<A> addNewWhileStatement() {
    return new WhileStatementsNested(-1, null);
  }

  public WhileStatementsNested<A> addNewWhileStatementLike(While item) {
    return new WhileStatementsNested(-1, item);
  }

  public XorStatementsNested<A> addNewXorStatement() {
    return new XorStatementsNested(-1, null);
  }

  public A addNewXorStatement(Object left, Object right) {
    return (A) this.addToStatements(new Xor(left, right));
  }

  public XorStatementsNested<A> addNewXorStatementLike(Xor item) {
    return new XorStatementsNested(-1, item);
  }

  public A addToStatements(VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    _visitables.get("statements").add(builder);
    this.statements.add(builder);
    return (A) this;
  }

  public A addToStatements(Statement... items) {
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A addToStatements(int index, VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    if (index < 0 || index >= statements.size()) {
      _visitables.get("statements").add(builder);
      statements.add(builder);
    } else {
      _visitables.get("statements").add(builder);
      statements.add(index, builder);
    }
    return (A) this;
  }

  public A addToStatements(int index, Statement item) {
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    VisitableBuilder<? extends Statement, ?> builder = builder(item);
    if (index < 0 || index >= statements.size()) {
      _visitables.get("statements").add(builder);
      statements.add(builder);
    } else {
      _visitables.get("statements").add(builder);
      statements.add(index, builder);
    }
    return (A) this;
  }

  public Expression buildCondition() {
    return this.condition != null ? this.condition.build() : null;
  }

  public Statement buildFirstStatement() {
    return this.statements.get(0).build();
  }

  public Statement buildLastStatement() {
    return this.statements.get(statements.size() - 1).build();
  }

  public Statement buildMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    for (VisitableBuilder<? extends Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return item.build();
      }
    }
    return null;
  }

  public Statement buildStatement(int index) {
    return this.statements.get(index).build();
  }

  public List<Statement> buildStatements() {
    return build(statements);
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

  protected void copyInstance(IfDslThenStep instance) {
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withStatements(instance.getStatements());
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
    IfDslThenStepFluent that = (IfDslThenStepFluent) o;
    if (!(Objects.equals(condition, that.condition))) {
      return false;
    }
    if (!(Objects.equals(statements, that.statements))) {
      return false;
    }
    return true;
  }

  public boolean hasCondition() {
    return this.condition != null;
  }

  public boolean hasMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    for (VisitableBuilder<? extends Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
  }

  public boolean hasStatements() {
    return this.statements != null && !(this.statements.isEmpty());
  }

  public int hashCode() {
    return Objects.hash(condition, statements);
  }

  public A removeAllFromStatements(Collection<Statement> items) {
    if (this.statements == null) {
      return (A) this;
    }
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").remove(builder);
      this.statements.remove(builder);
    }
    return (A) this;
  }

  public A removeFromStatements(VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      return (A) this;
    }
    _visitables.get("statements").remove(builder);
    this.statements.remove(builder);
    return (A) this;
  }

  public A removeFromStatements(Statement... items) {
    if (this.statements == null) {
      return (A) this;
    }
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").remove(builder);
      this.statements.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromStatements(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    if (statements == null) {
      return (A) this;
    }
    Iterator<VisitableBuilder<? extends Statement, ?>> each = statements.iterator();
    List visitables = _visitables.get("statements");
    while (each.hasNext()) {
      VisitableBuilder<? extends Statement, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public AssignStatementsNested<A> setNewAssignStatementLike(int index, Assign item) {
    return new AssignStatementsNested(index, item);
  }

  public BinaryExpressionStatementsNested<A> setNewBinaryExpressionStatementLike(int index, BinaryExpression item) {
    return new BinaryExpressionStatementsNested(index, item);
  }

  public BitwiseAndStatementsNested<A> setNewBitwiseAndStatementLike(int index, BitwiseAnd item) {
    return new BitwiseAndStatementsNested(index, item);
  }

  public BitwiseOrStatementsNested<A> setNewBitwiseOrStatementLike(int index, BitwiseOr item) {
    return new BitwiseOrStatementsNested(index, item);
  }

  public BlockStatementsNested<A> setNewBlockStatementLike(int index, Block item) {
    return new BlockStatementsNested(index, item);
  }

  public BreakStatementsNested<A> setNewBreakStatementLike(int index, Break item) {
    return new BreakStatementsNested(index, item);
  }

  public ConstructStatementsNested<A> setNewConstructStatementLike(int index, Construct item) {
    return new ConstructStatementsNested(index, item);
  }

  public ContinueStatementsNested<A> setNewContinueStatementLike(int index, Continue item) {
    return new ContinueStatementsNested(index, item);
  }

  public DeclareStatementsNested<A> setNewDeclareStatementLike(int index, Declare item) {
    return new DeclareStatementsNested(index, item);
  }

  public DivideStatementsNested<A> setNewDivideStatementLike(int index, Divide item) {
    return new DivideStatementsNested(index, item);
  }

  public DoStatementsNested<A> setNewDoStatementLike(int index, Do item) {
    return new DoStatementsNested(index, item);
  }

  public EmptyStatementsNested<A> setNewEmptyStatementLike(int index, Empty item) {
    return new EmptyStatementsNested(index, item);
  }

  public EqualsStatementsNested<A> setNewEqualsStatementLike(int index, Equals item) {
    return new EqualsStatementsNested(index, item);
  }

  public ForStatementsNested<A> setNewForStatementLike(int index, For item) {
    return new ForStatementsNested(index, item);
  }

  public ForeachStatementsNested<A> setNewForeachStatementLike(int index, Foreach item) {
    return new ForeachStatementsNested(index, item);
  }

  public GreaterThanOrEqualStatementsNested<A> setNewGreaterThanOrEqualStatementLike(int index, GreaterThanOrEqual item) {
    return new GreaterThanOrEqualStatementsNested(index, item);
  }

  public GreaterThanStatementsNested<A> setNewGreaterThanStatementLike(int index, GreaterThan item) {
    return new GreaterThanStatementsNested(index, item);
  }

  public IfDslThenStepStatementsNested<A> setNewIfDslThenStepStatementLike(int index, IfDslThenStep item) {
    return new IfDslThenStepStatementsNested(index, item);
  }

  public IfStatementsNested<A> setNewIfStatementLike(int index, If item) {
    return new IfStatementsNested(index, item);
  }

  public LambdaStatementsNested<A> setNewLambdaStatementLike(int index, Lambda item) {
    return new LambdaStatementsNested(index, item);
  }

  public LeftShiftStatementsNested<A> setNewLeftShiftStatementLike(int index, LeftShift item) {
    return new LeftShiftStatementsNested(index, item);
  }

  public LessThanOrEqualStatementsNested<A> setNewLessThanOrEqualStatementLike(int index, LessThanOrEqual item) {
    return new LessThanOrEqualStatementsNested(index, item);
  }

  public LessThanStatementsNested<A> setNewLessThanStatementLike(int index, LessThan item) {
    return new LessThanStatementsNested(index, item);
  }

  public LogicalAndStatementsNested<A> setNewLogicalAndStatementLike(int index, LogicalAnd item) {
    return new LogicalAndStatementsNested(index, item);
  }

  public LogicalOrStatementsNested<A> setNewLogicalOrStatementLike(int index, LogicalOr item) {
    return new LogicalOrStatementsNested(index, item);
  }

  public MethodCallStatementsNested<A> setNewMethodCallStatementLike(int index, MethodCall item) {
    return new MethodCallStatementsNested(index, item);
  }

  public MinusStatementsNested<A> setNewMinusStatementLike(int index, Minus item) {
    return new MinusStatementsNested(index, item);
  }

  public ModuloStatementsNested<A> setNewModuloStatementLike(int index, Modulo item) {
    return new ModuloStatementsNested(index, item);
  }

  public MultiplyStatementsNested<A> setNewMultiplyStatementLike(int index, Multiply item) {
    return new MultiplyStatementsNested(index, item);
  }

  public NotEqualsStatementsNested<A> setNewNotEqualsStatementLike(int index, NotEquals item) {
    return new NotEqualsStatementsNested(index, item);
  }

  public PlusStatementsNested<A> setNewPlusStatementLike(int index, Plus item) {
    return new PlusStatementsNested(index, item);
  }

  public PostDecrementStatementsNested<A> setNewPostDecrementStatementLike(int index, PostDecrement item) {
    return new PostDecrementStatementsNested(index, item);
  }

  public PostIncrementStatementsNested<A> setNewPostIncrementStatementLike(int index, PostIncrement item) {
    return new PostIncrementStatementsNested(index, item);
  }

  public PreDecrementStatementsNested<A> setNewPreDecrementStatementLike(int index, PreDecrement item) {
    return new PreDecrementStatementsNested(index, item);
  }

  public PreIncrementStatementsNested<A> setNewPreIncrementStatementLike(int index, PreIncrement item) {
    return new PreIncrementStatementsNested(index, item);
  }

  public PropertyRefStatementsNested<A> setNewPropertyRefStatementLike(int index, PropertyRef item) {
    return new PropertyRefStatementsNested(index, item);
  }

  public ReturnDslThisStepStatementsNested<A> setNewReturnDslThisStepStatementLike(int index, ReturnDslThisStep item) {
    return new ReturnDslThisStepStatementsNested(index, item);
  }

  public ReturnDslVariableStepStatementsNested<A> setNewReturnDslVariableStepStatementLike(int index,
      ReturnDslVariableStep item) {
    return new ReturnDslVariableStepStatementsNested(index, item);
  }

  public ReturnStatementsNested<A> setNewReturnStatementLike(int index, Return item) {
    return new ReturnStatementsNested(index, item);
  }

  public RightShiftStatementsNested<A> setNewRightShiftStatementLike(int index, RightShift item) {
    return new RightShiftStatementsNested(index, item);
  }

  public RightUnsignedShiftStatementsNested<A> setNewRightUnsignedShiftStatementLike(int index, RightUnsignedShift item) {
    return new RightUnsignedShiftStatementsNested(index, item);
  }

  public StringStatementStatementsNested<A> setNewStringStatementStatementLike(int index, StringStatement item) {
    return new StringStatementStatementsNested(index, item);
  }

  public SwitchStatementsNested<A> setNewSwitchStatementLike(int index, Switch item) {
    return new SwitchStatementsNested(index, item);
  }

  public SynchronizedStatementsNested<A> setNewSynchronizedStatementLike(int index, Synchronized item) {
    return new SynchronizedStatementsNested(index, item);
  }

  public ThrowStatementsNested<A> setNewThrowStatementLike(int index, Throw item) {
    return new ThrowStatementsNested(index, item);
  }

  public TryStatementsNested<A> setNewTryStatementLike(int index, Try item) {
    return new TryStatementsNested(index, item);
  }

  public WhileStatementsNested<A> setNewWhileStatementLike(int index, While item) {
    return new WhileStatementsNested(index, item);
  }

  public XorStatementsNested<A> setNewXorStatementLike(int index, Xor item) {
    return new XorStatementsNested(index, item);
  }

  public A setToStatements(int index, Statement item) {
    if (this.statements == null) {
      this.statements = new ArrayList();
    }
    VisitableBuilder<? extends Statement, ?> builder = builder(item);
    if (index < 0 || index >= statements.size()) {
      _visitables.get("statements").add(builder);
      statements.add(builder);
    } else {
      _visitables.get("statements").add(builder);
      statements.set(index, builder);
    }
    return (A) this;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(condition == null)) {
      sb.append("condition:");
      sb.append(condition);
      sb.append(",");
    }
    if (!(statements == null) && !(statements.isEmpty())) {
      sb.append("statements:");
      sb.append(statements);
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
    return (A) this.withCondition(new BitwiseAnd(left, right));
  }

  public BitwiseAndConditionNested<A> withNewBitwiseAndConditionLike(BitwiseAnd item) {
    return new BitwiseAndConditionNested(item);
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
    return (A) this.withCondition(new ContextRef(name));
  }

  public ContextRefConditionNested<A> withNewContextRefConditionLike(ContextRef item) {
    return new ContextRefConditionNested(item);
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

  public DivideConditionNested<A> withNewDivideCondition() {
    return new DivideConditionNested(null);
  }

  public A withNewDivideCondition(Object left, Object right) {
    return (A) this.withCondition(new Divide(left, right));
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
    return (A) this.withCondition(new Equals(left, right));
  }

  public EqualsConditionNested<A> withNewEqualsConditionLike(Equals item) {
    return new EqualsConditionNested(item);
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

  public GreaterThanOrEqualConditionNested<A> withNewGreaterThanOrEqualCondition() {
    return new GreaterThanOrEqualConditionNested(null);
  }

  public A withNewGreaterThanOrEqualCondition(Object left, Object right) {
    return (A) this.withCondition(new GreaterThanOrEqual(left, right));
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
    return (A) this.withCondition(new LeftShift(left, right));
  }

  public LeftShiftConditionNested<A> withNewLeftShiftConditionLike(LeftShift item) {
    return new LeftShiftConditionNested(item);
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

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualCondition() {
    return new LessThanOrEqualConditionNested(null);
  }

  public A withNewLessThanOrEqualCondition(Object left, Object right) {
    return (A) this.withCondition(new LessThanOrEqual(left, right));
  }

  public LessThanOrEqualConditionNested<A> withNewLessThanOrEqualConditionLike(LessThanOrEqual item) {
    return new LessThanOrEqualConditionNested(item);
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

  public LogicalOrConditionNested<A> withNewLogicalOrCondition() {
    return new LogicalOrConditionNested(null);
  }

  public A withNewLogicalOrCondition(Object left, Object right) {
    return (A) this.withCondition(new LogicalOr(left, right));
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
    return (A) this.withCondition(new Minus(left, right));
  }

  public MinusConditionNested<A> withNewMinusConditionLike(Minus item) {
    return new MinusConditionNested(item);
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

  public MultiplyConditionNested<A> withNewMultiplyCondition() {
    return new MultiplyConditionNested(null);
  }

  public A withNewMultiplyCondition(Object left, Object right) {
    return (A) this.withCondition(new Multiply(left, right));
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

  public PlusConditionNested<A> withNewPlusCondition() {
    return new PlusConditionNested(null);
  }

  public A withNewPlusCondition(Object left, Object right) {
    return (A) this.withCondition(new Plus(left, right));
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
    return (A) this.withCondition(new RightShift(left, right));
  }

  public RightShiftConditionNested<A> withNewRightShiftConditionLike(RightShift item) {
    return new RightShiftConditionNested(item);
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
    return (A) this.withCondition(new ValueRef(value));
  }

  public ValueRefConditionNested<A> withNewValueRefConditionLike(ValueRef item) {
    return new ValueRefConditionNested(item);
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

  public A withStatements(List<Statement> statements) {
    if (statements != null) {
      this.statements = new ArrayList();
      for (Statement item : statements) {
        this.addToStatements(item);
      }
    } else {
      this.statements = null;
    }
    return (A) this;
  }

  public A withStatements(Statement... statements) {
    if (this.statements != null) {
      this.statements.clear();
      _visitables.remove("statements");
    }
    if (statements != null) {
      for (Statement item : statements) {
        this.addToStatements(item);
      }
    }
    return (A) this;
  }

  public class AssignConditionNested<N> extends AssignFluent<AssignConditionNested<N>> implements Nested<N> {

    AssignBuilder builder;

    AssignConditionNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endAssignCondition() {
      return and();
    }

  }

  public class AssignStatementsNested<N> extends AssignFluent<AssignStatementsNested<N>> implements Nested<N> {

    AssignBuilder builder;
    int index;

    AssignStatementsNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endBinaryExpressionCondition() {
      return and();
    }

  }

  public class BinaryExpressionStatementsNested<N> extends BinaryExpressionFluent<BinaryExpressionStatementsNested<N>>
      implements Nested<N> {

    BinaryExpressionBuilder builder;
    int index;

    BinaryExpressionStatementsNested(int index, BinaryExpression item) {
      this.index = index;
      this.builder = new BinaryExpressionBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endBitwiseAndCondition() {
      return and();
    }

  }

  public class BitwiseAndStatementsNested<N> extends BitwiseAndFluent<BitwiseAndStatementsNested<N>> implements Nested<N> {

    BitwiseAndBuilder builder;
    int index;

    BitwiseAndStatementsNested(int index, BitwiseAnd item) {
      this.index = index;
      this.builder = new BitwiseAndBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endBitwiseOrCondition() {
      return and();
    }

  }

  public class BitwiseOrStatementsNested<N> extends BitwiseOrFluent<BitwiseOrStatementsNested<N>> implements Nested<N> {

    BitwiseOrBuilder builder;
    int index;

    BitwiseOrStatementsNested(int index, BitwiseOr item) {
      this.index = index;
      this.builder = new BitwiseOrBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endBitwiseOrStatement() {
      return and();
    }

  }

  public class BlockStatementsNested<N> extends BlockFluent<BlockStatementsNested<N>> implements Nested<N> {

    BlockBuilder builder;
    int index;

    BlockStatementsNested(int index, Block item) {
      this.index = index;
      this.builder = new BlockBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endBlockStatement() {
      return and();
    }

  }

  public class BreakStatementsNested<N> extends BreakFluent<BreakStatementsNested<N>> implements Nested<N> {

    BreakBuilder builder;
    int index;

    BreakStatementsNested(int index, Break item) {
      this.index = index;
      this.builder = new BreakBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endConstructCondition() {
      return and();
    }

  }

  public class ConstructStatementsNested<N> extends ConstructFluent<ConstructStatementsNested<N>> implements Nested<N> {

    ConstructBuilder builder;
    int index;

    ConstructStatementsNested(int index, Construct item) {
      this.index = index;
      this.builder = new ConstructBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endContextRefCondition() {
      return and();
    }

  }

  public class ContinueStatementsNested<N> extends ContinueFluent<ContinueStatementsNested<N>> implements Nested<N> {

    ContinueBuilder builder;
    int index;

    ContinueStatementsNested(int index, Continue item) {
      this.index = index;
      this.builder = new ContinueBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endDeclareCondition() {
      return and();
    }

  }

  public class DeclareStatementsNested<N> extends DeclareFluent<DeclareStatementsNested<N>> implements Nested<N> {

    DeclareBuilder builder;
    int index;

    DeclareStatementsNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endDivideCondition() {
      return and();
    }

  }

  public class DivideStatementsNested<N> extends DivideFluent<DivideStatementsNested<N>> implements Nested<N> {

    DivideBuilder builder;
    int index;

    DivideStatementsNested(int index, Divide item) {
      this.index = index;
      this.builder = new DivideBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endDivideStatement() {
      return and();
    }

  }

  public class DoStatementsNested<N> extends DoFluent<DoStatementsNested<N>> implements Nested<N> {

    DoBuilder builder;
    int index;

    DoStatementsNested(int index, Do item) {
      this.index = index;
      this.builder = new DoBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endEmptyCondition() {
      return and();
    }

  }

  public class EmptyStatementsNested<N> extends EmptyFluent<EmptyStatementsNested<N>> implements Nested<N> {

    EmptyBuilder builder;
    int index;

    EmptyStatementsNested(int index, Empty item) {
      this.index = index;
      this.builder = new EmptyBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endEqualsCondition() {
      return and();
    }

  }

  public class EqualsStatementsNested<N> extends EqualsFluent<EqualsStatementsNested<N>> implements Nested<N> {

    EqualsBuilder builder;
    int index;

    EqualsStatementsNested(int index, Equals item) {
      this.index = index;
      this.builder = new EqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endEqualsStatement() {
      return and();
    }

  }

  public class ForStatementsNested<N> extends ForFluent<ForStatementsNested<N>> implements Nested<N> {

    ForBuilder builder;
    int index;

    ForStatementsNested(int index, For item) {
      this.index = index;
      this.builder = new ForBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endForStatement() {
      return and();
    }

  }

  public class ForeachStatementsNested<N> extends ForeachFluent<ForeachStatementsNested<N>> implements Nested<N> {

    ForeachBuilder builder;
    int index;

    ForeachStatementsNested(int index, Foreach item) {
      this.index = index;
      this.builder = new ForeachBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endGreaterThanOrEqualCondition() {
      return and();
    }

  }

  public class GreaterThanOrEqualStatementsNested<N> extends GreaterThanOrEqualFluent<GreaterThanOrEqualStatementsNested<N>>
      implements Nested<N> {

    GreaterThanOrEqualBuilder builder;
    int index;

    GreaterThanOrEqualStatementsNested(int index, GreaterThanOrEqual item) {
      this.index = index;
      this.builder = new GreaterThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endGreaterThanOrEqualStatement() {
      return and();
    }

  }

  public class GreaterThanStatementsNested<N> extends GreaterThanFluent<GreaterThanStatementsNested<N>> implements Nested<N> {

    GreaterThanBuilder builder;
    int index;

    GreaterThanStatementsNested(int index, GreaterThan item) {
      this.index = index;
      this.builder = new GreaterThanBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endGreaterThanStatement() {
      return and();
    }

  }

  public class IfDslThenStepStatementsNested<N> extends IfDslThenStepFluent<IfDslThenStepStatementsNested<N>>
      implements Nested<N> {

    IfDslThenStepBuilder builder;
    int index;

    IfDslThenStepStatementsNested(int index, IfDslThenStep item) {
      this.index = index;
      this.builder = new IfDslThenStepBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endIfDslThenStepStatement() {
      return and();
    }

  }

  public class IfStatementsNested<N> extends IfFluent<IfStatementsNested<N>> implements Nested<N> {

    IfBuilder builder;
    int index;

    IfStatementsNested(int index, If item) {
      this.index = index;
      this.builder = new IfBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endLambdaCondition() {
      return and();
    }

  }

  public class LambdaStatementsNested<N> extends LambdaFluent<LambdaStatementsNested<N>> implements Nested<N> {

    LambdaBuilder builder;
    int index;

    LambdaStatementsNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endLeftShiftCondition() {
      return and();
    }

  }

  public class LeftShiftStatementsNested<N> extends LeftShiftFluent<LeftShiftStatementsNested<N>> implements Nested<N> {

    LeftShiftBuilder builder;
    int index;

    LeftShiftStatementsNested(int index, LeftShift item) {
      this.index = index;
      this.builder = new LeftShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endLessThanOrEqualCondition() {
      return and();
    }

  }

  public class LessThanOrEqualStatementsNested<N> extends LessThanOrEqualFluent<LessThanOrEqualStatementsNested<N>>
      implements Nested<N> {

    LessThanOrEqualBuilder builder;
    int index;

    LessThanOrEqualStatementsNested(int index, LessThanOrEqual item) {
      this.index = index;
      this.builder = new LessThanOrEqualBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endLessThanOrEqualStatement() {
      return and();
    }

  }

  public class LessThanStatementsNested<N> extends LessThanFluent<LessThanStatementsNested<N>> implements Nested<N> {

    LessThanBuilder builder;
    int index;

    LessThanStatementsNested(int index, LessThan item) {
      this.index = index;
      this.builder = new LessThanBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endLogicalAndCondition() {
      return and();
    }

  }

  public class LogicalAndStatementsNested<N> extends LogicalAndFluent<LogicalAndStatementsNested<N>> implements Nested<N> {

    LogicalAndBuilder builder;
    int index;

    LogicalAndStatementsNested(int index, LogicalAnd item) {
      this.index = index;
      this.builder = new LogicalAndBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endLogicalOrCondition() {
      return and();
    }

  }

  public class LogicalOrStatementsNested<N> extends LogicalOrFluent<LogicalOrStatementsNested<N>> implements Nested<N> {

    LogicalOrBuilder builder;
    int index;

    LogicalOrStatementsNested(int index, LogicalOr item) {
      this.index = index;
      this.builder = new LogicalOrBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endMethodCallCondition() {
      return and();
    }

  }

  public class MethodCallStatementsNested<N> extends MethodCallFluent<MethodCallStatementsNested<N>> implements Nested<N> {

    MethodCallBuilder builder;
    int index;

    MethodCallStatementsNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endMinusCondition() {
      return and();
    }

  }

  public class MinusStatementsNested<N> extends MinusFluent<MinusStatementsNested<N>> implements Nested<N> {

    MinusBuilder builder;
    int index;

    MinusStatementsNested(int index, Minus item) {
      this.index = index;
      this.builder = new MinusBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endModuloCondition() {
      return and();
    }

  }

  public class ModuloStatementsNested<N> extends ModuloFluent<ModuloStatementsNested<N>> implements Nested<N> {

    ModuloBuilder builder;
    int index;

    ModuloStatementsNested(int index, Modulo item) {
      this.index = index;
      this.builder = new ModuloBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endMultiplyCondition() {
      return and();
    }

  }

  public class MultiplyStatementsNested<N> extends MultiplyFluent<MultiplyStatementsNested<N>> implements Nested<N> {

    MultiplyBuilder builder;
    int index;

    MultiplyStatementsNested(int index, Multiply item) {
      this.index = index;
      this.builder = new MultiplyBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endNotEqualsCondition() {
      return and();
    }

  }

  public class NotEqualsStatementsNested<N> extends NotEqualsFluent<NotEqualsStatementsNested<N>> implements Nested<N> {

    NotEqualsBuilder builder;
    int index;

    NotEqualsStatementsNested(int index, NotEquals item) {
      this.index = index;
      this.builder = new NotEqualsBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endPlusCondition() {
      return and();
    }

  }

  public class PlusStatementsNested<N> extends PlusFluent<PlusStatementsNested<N>> implements Nested<N> {

    PlusBuilder builder;
    int index;

    PlusStatementsNested(int index, Plus item) {
      this.index = index;
      this.builder = new PlusBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endPostDecrementCondition() {
      return and();
    }

  }

  public class PostDecrementStatementsNested<N> extends PostDecrementFluent<PostDecrementStatementsNested<N>>
      implements Nested<N> {

    PostDecrementBuilder builder;
    int index;

    PostDecrementStatementsNested(int index, PostDecrement item) {
      this.index = index;
      this.builder = new PostDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endPostIncrementCondition() {
      return and();
    }

  }

  public class PostIncrementStatementsNested<N> extends PostIncrementFluent<PostIncrementStatementsNested<N>>
      implements Nested<N> {

    PostIncrementBuilder builder;
    int index;

    PostIncrementStatementsNested(int index, PostIncrement item) {
      this.index = index;
      this.builder = new PostIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endPreDecrementCondition() {
      return and();
    }

  }

  public class PreDecrementStatementsNested<N> extends PreDecrementFluent<PreDecrementStatementsNested<N>>
      implements Nested<N> {

    PreDecrementBuilder builder;
    int index;

    PreDecrementStatementsNested(int index, PreDecrement item) {
      this.index = index;
      this.builder = new PreDecrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endPreIncrementCondition() {
      return and();
    }

  }

  public class PreIncrementStatementsNested<N> extends PreIncrementFluent<PreIncrementStatementsNested<N>>
      implements Nested<N> {

    PreIncrementBuilder builder;
    int index;

    PreIncrementStatementsNested(int index, PreIncrement item) {
      this.index = index;
      this.builder = new PreIncrementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endPropertyRefCondition() {
      return and();
    }

  }

  public class PropertyRefStatementsNested<N> extends PropertyRefFluent<PropertyRefStatementsNested<N>> implements Nested<N> {

    PropertyRefBuilder builder;
    int index;

    PropertyRefStatementsNested(int index, PropertyRef item) {
      this.index = index;
      this.builder = new PropertyRefBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endPropertyRefStatement() {
      return and();
    }

  }

  public class ReturnDslThisStepStatementsNested<N> extends ReturnDslThisStepFluent<ReturnDslThisStepStatementsNested<N>>
      implements Nested<N> {

    ReturnDslThisStepBuilder builder;
    int index;

    ReturnDslThisStepStatementsNested(int index, ReturnDslThisStep item) {
      this.index = index;
      this.builder = new ReturnDslThisStepBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endReturnDslThisStepStatement() {
      return and();
    }

  }

  public class ReturnDslVariableStepStatementsNested<N>
      extends ReturnDslVariableStepFluent<ReturnDslVariableStepStatementsNested<N>> implements Nested<N> {

    ReturnDslVariableStepBuilder builder;
    int index;

    ReturnDslVariableStepStatementsNested(int index, ReturnDslVariableStep item) {
      this.index = index;
      this.builder = new ReturnDslVariableStepBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endReturnDslVariableStepStatement() {
      return and();
    }

  }

  public class ReturnStatementsNested<N> extends ReturnFluent<ReturnStatementsNested<N>> implements Nested<N> {

    ReturnBuilder builder;
    int index;

    ReturnStatementsNested(int index, Return item) {
      this.index = index;
      this.builder = new ReturnBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endRightShiftCondition() {
      return and();
    }

  }

  public class RightShiftStatementsNested<N> extends RightShiftFluent<RightShiftStatementsNested<N>> implements Nested<N> {

    RightShiftBuilder builder;
    int index;

    RightShiftStatementsNested(int index, RightShift item) {
      this.index = index;
      this.builder = new RightShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endRightUnsignedShiftCondition() {
      return and();
    }

  }

  public class RightUnsignedShiftStatementsNested<N> extends RightUnsignedShiftFluent<RightUnsignedShiftStatementsNested<N>>
      implements Nested<N> {

    RightUnsignedShiftBuilder builder;
    int index;

    RightUnsignedShiftStatementsNested(int index, RightUnsignedShift item) {
      this.index = index;
      this.builder = new RightUnsignedShiftBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endRightUnsignedShiftStatement() {
      return and();
    }

  }

  public class StringStatementStatementsNested<N> extends StringStatementFluent<StringStatementStatementsNested<N>>
      implements Nested<N> {

    StringStatementBuilder builder;
    int index;

    StringStatementStatementsNested(int index, StringStatement item) {
      this.index = index;
      this.builder = new StringStatementBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endStringStatementStatement() {
      return and();
    }

  }

  public class SuperConditionNested<N> extends SuperFluent<SuperConditionNested<N>> implements Nested<N> {

    SuperBuilder builder;

    SuperConditionNested(Super item) {
      this.builder = new SuperBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endSuperCondition() {
      return and();
    }

  }

  public class SwitchStatementsNested<N> extends SwitchFluent<SwitchStatementsNested<N>> implements Nested<N> {

    SwitchBuilder builder;
    int index;

    SwitchStatementsNested(int index, Switch item) {
      this.index = index;
      this.builder = new SwitchBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endSwitchStatement() {
      return and();
    }

  }

  public class SynchronizedStatementsNested<N> extends SynchronizedFluent<SynchronizedStatementsNested<N>>
      implements Nested<N> {

    SynchronizedBuilder builder;
    int index;

    SynchronizedStatementsNested(int index, Synchronized item) {
      this.index = index;
      this.builder = new SynchronizedBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endThisCondition() {
      return and();
    }

  }

  public class ThrowStatementsNested<N> extends ThrowFluent<ThrowStatementsNested<N>> implements Nested<N> {

    ThrowBuilder builder;
    int index;

    ThrowStatementsNested(int index, Throw item) {
      this.index = index;
      this.builder = new ThrowBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endThrowStatement() {
      return and();
    }

  }

  public class TryStatementsNested<N> extends TryFluent<TryStatementsNested<N>> implements Nested<N> {

    TryBuilder builder;
    int index;

    TryStatementsNested(int index, Try item) {
      this.index = index;
      this.builder = new TryBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endValueRefCondition() {
      return and();
    }

  }

  public class WhileStatementsNested<N> extends WhileFluent<WhileStatementsNested<N>> implements Nested<N> {

    WhileBuilder builder;
    int index;

    WhileStatementsNested(int index, While item) {
      this.index = index;
      this.builder = new WhileBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
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
      return (N) IfDslThenStepFluent.this.withCondition(builder.build());
    }

    public N endXorCondition() {
      return and();
    }

  }

  public class XorStatementsNested<N> extends XorFluent<XorStatementsNested<N>> implements Nested<N> {

    XorBuilder builder;
    int index;

    XorStatementsNested(int index, Xor item) {
      this.index = index;
      this.builder = new XorBuilder(this, item);
    }

    public N and() {
      return (N) IfDslThenStepFluent.this.setToStatements(index, builder.build());
    }

    public N endXorStatement() {
      return and();
    }

  }
}
