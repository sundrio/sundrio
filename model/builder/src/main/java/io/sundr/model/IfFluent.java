package io.sundr.model;

import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Optional;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class IfFluent<A extends IfFluent<A>> extends BaseFluent<A> {
  public IfFluent() {
  }

  public IfFluent(If instance) {
    this.copyInstance(instance);
  }

  private VisitableBuilder<? extends Expression, ?> condition;
  private VisitableBuilder<? extends Statement, ?> statement;
  private Optional<Statement> elseStatement = Optional.empty();

  protected void copyInstance(If instance) {
    if (instance != null) {
      this.withCondition(instance.getCondition());
      this.withStatement(instance.getStatement());
      this.withElseStatement(instance.getElseStatement());
    }
  }

  public Expression buildCondition() {
    return this.condition != null ? this.condition.build() : null;
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

  public ThisConditionNested<A> withNewThisCondition() {
    return new ThisConditionNested(null);
  }

  public ThisConditionNested<A> withNewThisConditionLike(This item) {
    return new ThisConditionNested(item);
  }

  public NegativeConditionNested<A> withNewNegativeCondition() {
    return new NegativeConditionNested(null);
  }

  public NegativeConditionNested<A> withNewNegativeConditionLike(Negative item) {
    return new NegativeConditionNested(item);
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

  public PropertyConditionNested<A> withNewPropertyCondition() {
    return new PropertyConditionNested(null);
  }

  public PropertyConditionNested<A> withNewPropertyConditionLike(Property item) {
    return new PropertyConditionNested(item);
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

  public Statement buildStatement() {
    return this.statement != null ? this.statement.build() : null;
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

  public boolean hasStatement() {
    return this.statement != null;
  }

  public MethodCallStatementNested<A> withNewMethodCallStatement() {
    return new MethodCallStatementNested(null);
  }

  public MethodCallStatementNested<A> withNewMethodCallStatementLike(MethodCall item) {
    return new MethodCallStatementNested(item);
  }

  public SwitchStatementNested<A> withNewSwitchStatement() {
    return new SwitchStatementNested(null);
  }

  public SwitchStatementNested<A> withNewSwitchStatementLike(Switch item) {
    return new SwitchStatementNested(item);
  }

  public BreakStatementNested<A> withNewBreakStatement() {
    return new BreakStatementNested(null);
  }

  public BreakStatementNested<A> withNewBreakStatementLike(Break item) {
    return new BreakStatementNested(item);
  }

  public DeclareStatementNested<A> withNewDeclareStatement() {
    return new DeclareStatementNested(null);
  }

  public DeclareStatementNested<A> withNewDeclareStatementLike(Declare item) {
    return new DeclareStatementNested(item);
  }

  public A withNewDeclareStatement(Class type, String name) {
    return (A) withStatement(new Declare(type, name));
  }

  public A withNewDeclareStatement(Class type, String name, Object value) {
    return (A) withStatement(new Declare(type, name, value));
  }

  public WhileStatementNested<A> withNewWhileStatement() {
    return new WhileStatementNested(null);
  }

  public WhileStatementNested<A> withNewWhileStatementLike(While item) {
    return new WhileStatementNested(item);
  }

  public ContinueStatementNested<A> withNewContinueStatement() {
    return new ContinueStatementNested(null);
  }

  public ContinueStatementNested<A> withNewContinueStatementLike(Continue item) {
    return new ContinueStatementNested(item);
  }

  public StringStatementNested<A> withNewStringStatement() {
    return new StringStatementNested(null);
  }

  public StringStatementNested<A> withNewStringStatementLike(StringStatement item) {
    return new StringStatementNested(item);
  }

  public A withNewStringStatement(String data) {
    return (A) withStatement(new StringStatement(data));
  }

  public A withNewStringStatement(String data, Object[] parameters) {
    return (A) withStatement(new StringStatement(data, parameters));
  }

  public DoStatementNested<A> withNewDoStatement() {
    return new DoStatementNested(null);
  }

  public DoStatementNested<A> withNewDoStatementLike(Do item) {
    return new DoStatementNested(item);
  }

  public ForeachStatementNested<A> withNewForeachStatement() {
    return new ForeachStatementNested(null);
  }

  public ForeachStatementNested<A> withNewForeachStatementLike(Foreach item) {
    return new ForeachStatementNested(item);
  }

  public BlockStatementNested<A> withNewBlockStatement() {
    return new BlockStatementNested(null);
  }

  public BlockStatementNested<A> withNewBlockStatementLike(Block item) {
    return new BlockStatementNested(item);
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

  public ReturnStatementNested<A> withNewReturnStatement() {
    return new ReturnStatementNested(null);
  }

  public ReturnStatementNested<A> withNewReturnStatementLike(Return item) {
    return new ReturnStatementNested(item);
  }

  public A withNewReturnStatement(Object object) {
    return (A) withStatement(new Return(object));
  }

  public AssignStatementNested<A> withNewAssignStatement() {
    return new AssignStatementNested(null);
  }

  public AssignStatementNested<A> withNewAssignStatementLike(Assign item) {
    return new AssignStatementNested(item);
  }

  public ForStatementNested<A> withNewForStatement() {
    return new ForStatementNested(null);
  }

  public ForStatementNested<A> withNewForStatementLike(For item) {
    return new ForStatementNested(item);
  }

  public A withElseStatement(Optional<Statement> elseStatement) {
    if (elseStatement == null || !elseStatement.isPresent()) {
      this.elseStatement = java.util.Optional.empty();
    } else {
      this.elseStatement = elseStatement;
    }
    return (A) this;
  }

  public A withElseStatement(Statement elseStatement) {
    if (elseStatement == null) {
      this.elseStatement = java.util.Optional.empty();
    } else {
      this.elseStatement = java.util.Optional.of(elseStatement);
    }
    return (A) this;
  }

  public Optional<Statement> getElseStatement() {
    return this.elseStatement;
  }

  public boolean hasElseStatement() {
    return this.elseStatement != null && this.elseStatement.isPresent();
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    IfFluent that = (IfFluent) o;
    if (!java.util.Objects.equals(condition, that.condition))
      return false;
    if (!java.util.Objects.equals(statement, that.statement))
      return false;
    if (!java.util.Objects.equals(elseStatement, that.elseStatement))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(condition, statement, elseStatement, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (condition != null) {
      sb.append("condition:");
      sb.append(condition + ",");
    }
    if (statement != null) {
      sb.append("statement:");
      sb.append(statement + ",");
    }
    if (elseStatement != null) {
      sb.append("elseStatement:");
      sb.append(elseStatement);
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
      case "io.sundr.model." + "Switch":
        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);
      case "io.sundr.model." + "Break":
        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);
      case "io.sundr.model." + "While":
        return (VisitableBuilder<T, ?>) new WhileBuilder((While) item);
      case "io.sundr.model." + "Continue":
        return (VisitableBuilder<T, ?>) new ContinueBuilder((Continue) item);
      case "io.sundr.model." + "StringStatement":
        return (VisitableBuilder<T, ?>) new StringStatementBuilder((StringStatement) item);
      case "io.sundr.model." + "Do":
        return (VisitableBuilder<T, ?>) new DoBuilder((Do) item);
      case "io.sundr.model." + "Foreach":
        return (VisitableBuilder<T, ?>) new ForeachBuilder((Foreach) item);
      case "io.sundr.model." + "Block":
        return (VisitableBuilder<T, ?>) new BlockBuilder((Block) item);
      case "io.sundr.model." + "If":
        return (VisitableBuilder<T, ?>) new IfBuilder((If) item);
      case "io.sundr.model." + "Return":
        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);
      case "io.sundr.model." + "For":
        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class MultiplyConditionNested<N> extends MultiplyFluent<MultiplyConditionNested<N>> implements Nested<N> {
    MultiplyConditionNested(Multiply item) {
      this.builder = new MultiplyBuilder(this, item);
    }

    MultiplyBuilder builder;

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endAssignCondition() {
      return and();
    }

  }

  public class ThisConditionNested<N> extends ThisFluent<ThisConditionNested<N>> implements Nested<N> {
    ThisConditionNested(This item) {
      this.builder = new ThisBuilder(this, item);
    }

    ThisBuilder builder;

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endThisCondition() {
      return and();
    }

  }

  public class NegativeConditionNested<N> extends NegativeFluent<NegativeConditionNested<N>> implements Nested<N> {
    NegativeConditionNested(Negative item) {
      this.builder = new NegativeBuilder(this, item);
    }

    NegativeBuilder builder;

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endNegativeCondition() {
      return and();
    }

  }

  public class LogicalAndConditionNested<N> extends LogicalAndFluent<LogicalAndConditionNested<N>> implements Nested<N> {
    LogicalAndConditionNested(LogicalAnd item) {
      this.builder = new LogicalAndBuilder(this, item);
    }

    LogicalAndBuilder builder;

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPreIncrementCondition() {
      return and();
    }

  }

  public class PropertyConditionNested<N> extends PropertyFluent<PropertyConditionNested<N>> implements Nested<N> {
    PropertyConditionNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;

    public N and() {
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPropertyCondition() {
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
      return (N) IfFluent.this.withCondition(builder.build());
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
      return (N) IfFluent.this.withCondition(builder.build());
    }

    public N endPositiveCondition() {
      return and();
    }

  }

  public class MethodCallStatementNested<N> extends MethodCallFluent<MethodCallStatementNested<N>> implements Nested<N> {
    MethodCallStatementNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endMethodCallStatement() {
      return and();
    }

  }

  public class SwitchStatementNested<N> extends SwitchFluent<SwitchStatementNested<N>> implements Nested<N> {
    SwitchStatementNested(Switch item) {
      this.builder = new SwitchBuilder(this, item);
    }

    SwitchBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endSwitchStatement() {
      return and();
    }

  }

  public class BreakStatementNested<N> extends BreakFluent<BreakStatementNested<N>> implements Nested<N> {
    BreakStatementNested(Break item) {
      this.builder = new BreakBuilder(this, item);
    }

    BreakBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBreakStatement() {
      return and();
    }

  }

  public class DeclareStatementNested<N> extends DeclareFluent<DeclareStatementNested<N>> implements Nested<N> {
    DeclareStatementNested(Declare item) {
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endDeclareStatement() {
      return and();
    }

  }

  public class WhileStatementNested<N> extends WhileFluent<WhileStatementNested<N>> implements Nested<N> {
    WhileStatementNested(While item) {
      this.builder = new WhileBuilder(this, item);
    }

    WhileBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endWhileStatement() {
      return and();
    }

  }

  public class ContinueStatementNested<N> extends ContinueFluent<ContinueStatementNested<N>> implements Nested<N> {
    ContinueStatementNested(Continue item) {
      this.builder = new ContinueBuilder(this, item);
    }

    ContinueBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endContinueStatement() {
      return and();
    }

  }

  public class StringStatementNested<N> extends StringStatementFluent<StringStatementNested<N>> implements Nested<N> {
    StringStatementNested(StringStatement item) {
      this.builder = new StringStatementBuilder(this, item);
    }

    StringStatementBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endStringStatement() {
      return and();
    }

  }

  public class DoStatementNested<N> extends DoFluent<DoStatementNested<N>> implements Nested<N> {
    DoStatementNested(Do item) {
      this.builder = new DoBuilder(this, item);
    }

    DoBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endDoStatement() {
      return and();
    }

  }

  public class ForeachStatementNested<N> extends ForeachFluent<ForeachStatementNested<N>> implements Nested<N> {
    ForeachStatementNested(Foreach item) {
      this.builder = new ForeachBuilder(this, item);
    }

    ForeachBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endForeachStatement() {
      return and();
    }

  }

  public class BlockStatementNested<N> extends BlockFluent<BlockStatementNested<N>> implements Nested<N> {
    BlockStatementNested(Block item) {
      this.builder = new BlockBuilder(this, item);
    }

    BlockBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endBlockStatement() {
      return and();
    }

  }

  public class IfStatementNested<N> extends IfFluent<IfStatementNested<N>> implements Nested<N> {
    IfStatementNested(If item) {
      this.builder = new IfBuilder(this, item);
    }

    IfBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endIfStatement() {
      return and();
    }

  }

  public class LambdaStatementNested<N> extends LambdaFluent<LambdaStatementNested<N>> implements Nested<N> {
    LambdaStatementNested(Lambda item) {
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endLambdaStatement() {
      return and();
    }

  }

  public class ReturnStatementNested<N> extends ReturnFluent<ReturnStatementNested<N>> implements Nested<N> {
    ReturnStatementNested(Return item) {
      this.builder = new ReturnBuilder(this, item);
    }

    ReturnBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endReturnStatement() {
      return and();
    }

  }

  public class AssignStatementNested<N> extends AssignFluent<AssignStatementNested<N>> implements Nested<N> {
    AssignStatementNested(Assign item) {
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endAssignStatement() {
      return and();
    }

  }

  public class ForStatementNested<N> extends ForFluent<ForStatementNested<N>> implements Nested<N> {
    ForStatementNested(For item) {
      this.builder = new ForBuilder(this, item);
    }

    ForBuilder builder;

    public N and() {
      return (N) IfFluent.this.withStatement(builder.build());
    }

    public N endForStatement() {
      return and();
    }

  }

}
