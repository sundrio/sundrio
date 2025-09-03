package io.sundr.model;

import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class BlockFluent<A extends BlockFluent<A>> extends BaseFluent<A> {
  public BlockFluent() {
  }

  public BlockFluent(Block instance) {
    this.copyInstance(instance);
  }

  private ArrayList<VisitableBuilder<? extends Statement, ?>> statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();

  protected void copyInstance(Block instance) {
    if (instance != null) {
      this.withStatements(instance.getStatements());
    }
  }

  public A addToStatements(VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    _visitables.get("statements").add(builder);
    this.statements.add(builder);
    return (A) this;
  }

  public A addToStatements(int index, VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
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
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
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

  public A setToStatements(int index, Statement item) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
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

  public A addToStatements(io.sundr.model.Statement... items) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A addAllToStatements(Collection<Statement> items) {
    if (this.statements == null) {
      this.statements = new ArrayList<VisitableBuilder<? extends Statement, ?>>();
    }
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").add(builder);
      this.statements.add(builder);
    }
    return (A) this;
  }

  public A removeFromStatements(VisitableBuilder<? extends Statement, ?> builder) {
    if (this.statements == null)
      return (A) this;
    _visitables.get("statements").remove(builder);
    this.statements.remove(builder);
    return (A) this;
  }

  public A removeFromStatements(io.sundr.model.Statement... items) {
    if (this.statements == null)
      return (A) this;
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").remove(builder);
      this.statements.remove(builder);
    }
    return (A) this;
  }

  public A removeAllFromStatements(Collection<Statement> items) {
    if (this.statements == null)
      return (A) this;
    for (Statement item : items) {
      VisitableBuilder<? extends Statement, ?> builder = builder(item);
      _visitables.get("statements").remove(builder);
      this.statements.remove(builder);
    }
    return (A) this;
  }

  public A removeMatchingFromStatements(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    if (statements == null)
      return (A) this;
    final Iterator<VisitableBuilder<? extends Statement, ?>> each = statements.iterator();
    final List visitables = _visitables.get("statements");
    while (each.hasNext()) {
      VisitableBuilder<? extends Statement, ?> builder = each.next();
      if (predicate.test(builder)) {
        visitables.remove(builder);
        each.remove();
      }
    }
    return (A) this;
  }

  public List<Statement> buildStatements() {
    return build(statements);
  }

  public Statement buildStatement(int index) {
    return this.statements.get(index).build();
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

  public boolean hasMatchingStatement(Predicate<VisitableBuilder<? extends Statement, ?>> predicate) {
    for (VisitableBuilder<? extends Statement, ?> item : statements) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
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

  public A withStatements(io.sundr.model.Statement... statements) {
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

  public boolean hasStatements() {
    return this.statements != null && !(this.statements.isEmpty());
  }

  public ReturnDslThisStepStatementsNested<A> addNewReturnDslThisStepStatement() {
    return new ReturnDslThisStepStatementsNested(-1, null);
  }

  public ReturnDslThisStepStatementsNested<A> addNewReturnDslThisStepStatementLike(ReturnDslThisStep item) {
    return new ReturnDslThisStepStatementsNested(-1, item);
  }

  public ReturnDslThisStepStatementsNested<A> setNewReturnDslThisStepStatementLike(int index, ReturnDslThisStep item) {
    return new ReturnDslThisStepStatementsNested(index, item);
  }

  public MethodCallStatementsNested<A> addNewMethodCallStatement() {
    return new MethodCallStatementsNested(-1, null);
  }

  public MethodCallStatementsNested<A> addNewMethodCallStatementLike(MethodCall item) {
    return new MethodCallStatementsNested(-1, item);
  }

  public MethodCallStatementsNested<A> setNewMethodCallStatementLike(int index, MethodCall item) {
    return new MethodCallStatementsNested(index, item);
  }

  public SwitchStatementsNested<A> addNewSwitchStatement() {
    return new SwitchStatementsNested(-1, null);
  }

  public SwitchStatementsNested<A> addNewSwitchStatementLike(Switch item) {
    return new SwitchStatementsNested(-1, item);
  }

  public SwitchStatementsNested<A> setNewSwitchStatementLike(int index, Switch item) {
    return new SwitchStatementsNested(index, item);
  }

  public BreakStatementsNested<A> addNewBreakStatement() {
    return new BreakStatementsNested(-1, null);
  }

  public BreakStatementsNested<A> addNewBreakStatementLike(Break item) {
    return new BreakStatementsNested(-1, item);
  }

  public BreakStatementsNested<A> setNewBreakStatementLike(int index, Break item) {
    return new BreakStatementsNested(index, item);
  }

  public DeclareStatementsNested<A> addNewDeclareStatement() {
    return new DeclareStatementsNested(-1, null);
  }

  public DeclareStatementsNested<A> addNewDeclareStatementLike(Declare item) {
    return new DeclareStatementsNested(-1, item);
  }

  public A addNewDeclareStatement(Class type, String name) {
    return (A) addToStatements(new Declare(type, name));
  }

  public A addNewDeclareStatement(Class type, String name, Object value) {
    return (A) addToStatements(new Declare(type, name, value));
  }

  public DeclareStatementsNested<A> setNewDeclareStatementLike(int index, Declare item) {
    return new DeclareStatementsNested(index, item);
  }

  public WhileStatementsNested<A> addNewWhileStatement() {
    return new WhileStatementsNested(-1, null);
  }

  public WhileStatementsNested<A> addNewWhileStatementLike(While item) {
    return new WhileStatementsNested(-1, item);
  }

  public WhileStatementsNested<A> setNewWhileStatementLike(int index, While item) {
    return new WhileStatementsNested(index, item);
  }

  public ContinueStatementsNested<A> addNewContinueStatement() {
    return new ContinueStatementsNested(-1, null);
  }

  public ContinueStatementsNested<A> addNewContinueStatementLike(Continue item) {
    return new ContinueStatementsNested(-1, item);
  }

  public ContinueStatementsNested<A> setNewContinueStatementLike(int index, Continue item) {
    return new ContinueStatementsNested(index, item);
  }

  public ThrowStatementsNested<A> addNewThrowStatement() {
    return new ThrowStatementsNested(-1, null);
  }

  public ThrowStatementsNested<A> addNewThrowStatementLike(Throw item) {
    return new ThrowStatementsNested(-1, item);
  }

  public ThrowStatementsNested<A> setNewThrowStatementLike(int index, Throw item) {
    return new ThrowStatementsNested(index, item);
  }

  public StringStatementStatementsNested<A> addNewStringStatementStatement() {
    return new StringStatementStatementsNested(-1, null);
  }

  public StringStatementStatementsNested<A> addNewStringStatementStatementLike(StringStatement item) {
    return new StringStatementStatementsNested(-1, item);
  }

  public A addNewStringStatementStatement(String data) {
    return (A) addToStatements(new StringStatement(data));
  }

  public A addNewStringStatementStatement(String data, Object[] parameters) {
    return (A) addToStatements(new StringStatement(data, parameters));
  }

  public StringStatementStatementsNested<A> setNewStringStatementStatementLike(int index, StringStatement item) {
    return new StringStatementStatementsNested(index, item);
  }

  public DoStatementsNested<A> addNewDoStatement() {
    return new DoStatementsNested(-1, null);
  }

  public DoStatementsNested<A> addNewDoStatementLike(Do item) {
    return new DoStatementsNested(-1, item);
  }

  public DoStatementsNested<A> setNewDoStatementLike(int index, Do item) {
    return new DoStatementsNested(index, item);
  }

  public ForeachStatementsNested<A> addNewForeachStatement() {
    return new ForeachStatementsNested(-1, null);
  }

  public ForeachStatementsNested<A> addNewForeachStatementLike(Foreach item) {
    return new ForeachStatementsNested(-1, item);
  }

  public ForeachStatementsNested<A> setNewForeachStatementLike(int index, Foreach item) {
    return new ForeachStatementsNested(index, item);
  }

  public BlockStatementsNested<A> addNewBlockStatement() {
    return new BlockStatementsNested(-1, null);
  }

  public BlockStatementsNested<A> addNewBlockStatementLike(Block item) {
    return new BlockStatementsNested(-1, item);
  }

  public BlockStatementsNested<A> setNewBlockStatementLike(int index, Block item) {
    return new BlockStatementsNested(index, item);
  }

  public ReturnDslVariableStepStatementsNested<A> addNewReturnDslVariableStepStatement() {
    return new ReturnDslVariableStepStatementsNested(-1, null);
  }

  public ReturnDslVariableStepStatementsNested<A> addNewReturnDslVariableStepStatementLike(ReturnDslVariableStep item) {
    return new ReturnDslVariableStepStatementsNested(-1, item);
  }

  public A addNewReturnDslVariableStepStatement(String name) {
    return (A) addToStatements(new ReturnDslVariableStep(name));
  }

  public ReturnDslVariableStepStatementsNested<A> setNewReturnDslVariableStepStatementLike(int index,
      ReturnDslVariableStep item) {
    return new ReturnDslVariableStepStatementsNested(index, item);
  }

  public IfStatementsNested<A> addNewIfStatement() {
    return new IfStatementsNested(-1, null);
  }

  public IfStatementsNested<A> addNewIfStatementLike(If item) {
    return new IfStatementsNested(-1, item);
  }

  public IfStatementsNested<A> setNewIfStatementLike(int index, If item) {
    return new IfStatementsNested(index, item);
  }

  public LambdaStatementsNested<A> addNewLambdaStatement() {
    return new LambdaStatementsNested(-1, null);
  }

  public LambdaStatementsNested<A> addNewLambdaStatementLike(Lambda item) {
    return new LambdaStatementsNested(-1, item);
  }

  public LambdaStatementsNested<A> setNewLambdaStatementLike(int index, Lambda item) {
    return new LambdaStatementsNested(index, item);
  }

  public ReturnStatementsNested<A> addNewReturnStatement() {
    return new ReturnStatementsNested(-1, null);
  }

  public ReturnStatementsNested<A> addNewReturnStatementLike(Return item) {
    return new ReturnStatementsNested(-1, item);
  }

  public A addNewReturnStatement(Object object) {
    return (A) addToStatements(new Return(object));
  }

  public ReturnStatementsNested<A> setNewReturnStatementLike(int index, Return item) {
    return new ReturnStatementsNested(index, item);
  }

  public AssignStatementsNested<A> addNewAssignStatement() {
    return new AssignStatementsNested(-1, null);
  }

  public AssignStatementsNested<A> addNewAssignStatementLike(Assign item) {
    return new AssignStatementsNested(-1, item);
  }

  public AssignStatementsNested<A> setNewAssignStatementLike(int index, Assign item) {
    return new AssignStatementsNested(index, item);
  }

  public IfDslThenStepStatementsNested<A> addNewIfDslThenStepStatement() {
    return new IfDslThenStepStatementsNested(-1, null);
  }

  public IfDslThenStepStatementsNested<A> addNewIfDslThenStepStatementLike(IfDslThenStep item) {
    return new IfDslThenStepStatementsNested(-1, item);
  }

  public IfDslThenStepStatementsNested<A> setNewIfDslThenStepStatementLike(int index, IfDslThenStep item) {
    return new IfDslThenStepStatementsNested(index, item);
  }

  public ForStatementsNested<A> addNewForStatement() {
    return new ForStatementsNested(-1, null);
  }

  public ForStatementsNested<A> addNewForStatementLike(For item) {
    return new ForStatementsNested(-1, item);
  }

  public ForStatementsNested<A> setNewForStatementLike(int index, For item) {
    return new ForStatementsNested(index, item);
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    BlockFluent that = (BlockFluent) o;
    if (!java.util.Objects.equals(statements, that.statements))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(statements, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (statements != null && !statements.isEmpty()) {
      sb.append("statements:");
      sb.append(statements);
    }
    sb.append("}");
    return sb.toString();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
      case "io.sundr.model." + "ReturnDslThisStep":
        return (VisitableBuilder<T, ?>) new ReturnDslThisStepBuilder((ReturnDslThisStep) item);
      case "io.sundr.model." + "MethodCall":
        return (VisitableBuilder<T, ?>) new MethodCallBuilder((MethodCall) item);
      case "io.sundr.model." + "Switch":
        return (VisitableBuilder<T, ?>) new SwitchBuilder((Switch) item);
      case "io.sundr.model." + "Break":
        return (VisitableBuilder<T, ?>) new BreakBuilder((Break) item);
      case "io.sundr.model." + "Declare":
        return (VisitableBuilder<T, ?>) new DeclareBuilder((Declare) item);
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
      case "io.sundr.model." + "Lambda":
        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);
      case "io.sundr.model." + "Return":
        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);
      case "io.sundr.model." + "Assign":
        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);
      case "io.sundr.model." + "IfDslThenStep":
        return (VisitableBuilder<T, ?>) new IfDslThenStepBuilder((IfDslThenStep) item);
      case "io.sundr.model." + "For":
        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class ReturnDslThisStepStatementsNested<N> extends ReturnDslThisStepFluent<ReturnDslThisStepStatementsNested<N>>
      implements Nested<N> {
    ReturnDslThisStepStatementsNested(int index, ReturnDslThisStep item) {
      this.index = index;
      this.builder = new ReturnDslThisStepBuilder(this, item);
    }

    ReturnDslThisStepBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endReturnDslThisStepStatement() {
      return and();
    }

  }

  public class MethodCallStatementsNested<N> extends MethodCallFluent<MethodCallStatementsNested<N>> implements Nested<N> {
    MethodCallStatementsNested(int index, MethodCall item) {
      this.index = index;
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endMethodCallStatement() {
      return and();
    }

  }

  public class SwitchStatementsNested<N> extends SwitchFluent<SwitchStatementsNested<N>> implements Nested<N> {
    SwitchStatementsNested(int index, Switch item) {
      this.index = index;
      this.builder = new SwitchBuilder(this, item);
    }

    SwitchBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endSwitchStatement() {
      return and();
    }

  }

  public class BreakStatementsNested<N> extends BreakFluent<BreakStatementsNested<N>> implements Nested<N> {
    BreakStatementsNested(int index, Break item) {
      this.index = index;
      this.builder = new BreakBuilder(this, item);
    }

    BreakBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endBreakStatement() {
      return and();
    }

  }

  public class DeclareStatementsNested<N> extends DeclareFluent<DeclareStatementsNested<N>> implements Nested<N> {
    DeclareStatementsNested(int index, Declare item) {
      this.index = index;
      this.builder = new DeclareBuilder(this, item);
    }

    DeclareBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endDeclareStatement() {
      return and();
    }

  }

  public class WhileStatementsNested<N> extends WhileFluent<WhileStatementsNested<N>> implements Nested<N> {
    WhileStatementsNested(int index, While item) {
      this.index = index;
      this.builder = new WhileBuilder(this, item);
    }

    WhileBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endWhileStatement() {
      return and();
    }

  }

  public class ContinueStatementsNested<N> extends ContinueFluent<ContinueStatementsNested<N>> implements Nested<N> {
    ContinueStatementsNested(int index, Continue item) {
      this.index = index;
      this.builder = new ContinueBuilder(this, item);
    }

    ContinueBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endContinueStatement() {
      return and();
    }

  }

  public class ThrowStatementsNested<N> extends ThrowFluent<ThrowStatementsNested<N>> implements Nested<N> {
    ThrowStatementsNested(int index, Throw item) {
      this.index = index;
      this.builder = new ThrowBuilder(this, item);
    }

    ThrowBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endThrowStatement() {
      return and();
    }

  }

  public class StringStatementStatementsNested<N> extends StringStatementFluent<StringStatementStatementsNested<N>>
      implements Nested<N> {
    StringStatementStatementsNested(int index, StringStatement item) {
      this.index = index;
      this.builder = new StringStatementBuilder(this, item);
    }

    StringStatementBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endStringStatementStatement() {
      return and();
    }

  }

  public class DoStatementsNested<N> extends DoFluent<DoStatementsNested<N>> implements Nested<N> {
    DoStatementsNested(int index, Do item) {
      this.index = index;
      this.builder = new DoBuilder(this, item);
    }

    DoBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endDoStatement() {
      return and();
    }

  }

  public class ForeachStatementsNested<N> extends ForeachFluent<ForeachStatementsNested<N>> implements Nested<N> {
    ForeachStatementsNested(int index, Foreach item) {
      this.index = index;
      this.builder = new ForeachBuilder(this, item);
    }

    ForeachBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endForeachStatement() {
      return and();
    }

  }

  public class BlockStatementsNested<N> extends BlockFluent<BlockStatementsNested<N>> implements Nested<N> {
    BlockStatementsNested(int index, Block item) {
      this.index = index;
      this.builder = new BlockBuilder(this, item);
    }

    BlockBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endBlockStatement() {
      return and();
    }

  }

  public class ReturnDslVariableStepStatementsNested<N>
      extends ReturnDslVariableStepFluent<ReturnDslVariableStepStatementsNested<N>> implements Nested<N> {
    ReturnDslVariableStepStatementsNested(int index, ReturnDslVariableStep item) {
      this.index = index;
      this.builder = new ReturnDslVariableStepBuilder(this, item);
    }

    ReturnDslVariableStepBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endReturnDslVariableStepStatement() {
      return and();
    }

  }

  public class IfStatementsNested<N> extends IfFluent<IfStatementsNested<N>> implements Nested<N> {
    IfStatementsNested(int index, If item) {
      this.index = index;
      this.builder = new IfBuilder(this, item);
    }

    IfBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endIfStatement() {
      return and();
    }

  }

  public class LambdaStatementsNested<N> extends LambdaFluent<LambdaStatementsNested<N>> implements Nested<N> {
    LambdaStatementsNested(int index, Lambda item) {
      this.index = index;
      this.builder = new LambdaBuilder(this, item);
    }

    LambdaBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endLambdaStatement() {
      return and();
    }

  }

  public class ReturnStatementsNested<N> extends ReturnFluent<ReturnStatementsNested<N>> implements Nested<N> {
    ReturnStatementsNested(int index, Return item) {
      this.index = index;
      this.builder = new ReturnBuilder(this, item);
    }

    ReturnBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endReturnStatement() {
      return and();
    }

  }

  public class AssignStatementsNested<N> extends AssignFluent<AssignStatementsNested<N>> implements Nested<N> {
    AssignStatementsNested(int index, Assign item) {
      this.index = index;
      this.builder = new AssignBuilder(this, item);
    }

    AssignBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endAssignStatement() {
      return and();
    }

  }

  public class IfDslThenStepStatementsNested<N> extends IfDslThenStepFluent<IfDslThenStepStatementsNested<N>>
      implements Nested<N> {
    IfDslThenStepStatementsNested(int index, IfDslThenStep item) {
      this.index = index;
      this.builder = new IfDslThenStepBuilder(this, item);
    }

    IfDslThenStepBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endIfDslThenStepStatement() {
      return and();
    }

  }

  public class ForStatementsNested<N> extends ForFluent<ForStatementsNested<N>> implements Nested<N> {
    ForStatementsNested(int index, For item) {
      this.index = index;
      this.builder = new ForBuilder(this, item);
    }

    ForBuilder builder;
    int index;

    public N and() {
      return (N) BlockFluent.this.setToStatements(index, builder.build());
    }

    public N endForStatement() {
      return and();
    }

  }

}
