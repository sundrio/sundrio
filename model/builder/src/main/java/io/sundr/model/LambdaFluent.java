package io.sundr.model;

import java.lang.Class;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;
import io.sundr.builder.VisitableBuilder;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class LambdaFluent<A extends LambdaFluent<A>> extends BaseFluent<A> {
  public LambdaFluent() {
  }

  public LambdaFluent(Lambda instance) {
    this.copyInstance(instance);
  }

  private List<String> parameters = new ArrayList<String>();
  private VisitableBuilder<? extends Statement, ?> statement;

  protected void copyInstance(Lambda instance) {
    if (instance != null) {
      this.withParameters(instance.getParameters());
      this.withStatement(instance.getStatement());
    }
  }

  public A addToParameters(int index, String item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<String>();
    }
    this.parameters.add(index, item);
    return (A) this;
  }

  public A setToParameters(int index, String item) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<String>();
    }
    this.parameters.set(index, item);
    return (A) this;
  }

  public A addToParameters(java.lang.String... items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<String>();
    }
    for (String item : items) {
      this.parameters.add(item);
    }
    return (A) this;
  }

  public A addAllToParameters(Collection<String> items) {
    if (this.parameters == null) {
      this.parameters = new ArrayList<String>();
    }
    for (String item : items) {
      this.parameters.add(item);
    }
    return (A) this;
  }

  public A removeFromParameters(java.lang.String... items) {
    if (this.parameters == null)
      return (A) this;
    for (String item : items) {
      this.parameters.remove(item);
    }
    return (A) this;
  }

  public A removeAllFromParameters(Collection<String> items) {
    if (this.parameters == null)
      return (A) this;
    for (String item : items) {
      this.parameters.remove(item);
    }
    return (A) this;
  }

  public List<String> getParameters() {
    return this.parameters;
  }

  public String getParameter(int index) {
    return this.parameters.get(index);
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

  public boolean hasMatchingParameter(Predicate<String> predicate) {
    for (String item : parameters) {
      if (predicate.test(item)) {
        return true;
      }
    }
    return false;
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

  public A withParameters(java.lang.String... parameters) {
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

  public boolean hasParameters() {
    return this.parameters != null && !this.parameters.isEmpty();
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
      ;
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

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    LambdaFluent that = (LambdaFluent) o;
    if (!java.util.Objects.equals(parameters, that.parameters))
      return false;
    if (!java.util.Objects.equals(statement, that.statement))
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(parameters, statement, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (parameters != null && !parameters.isEmpty()) {
      sb.append("parameters:");
      sb.append(parameters + ",");
    }
    if (statement != null) {
      sb.append("statement:");
      sb.append(statement);
    }
    sb.append("}");
    return sb.toString();
  }

  protected static <T> VisitableBuilder<T, ?> builder(Object item) {
    switch (item.getClass().getName()) {
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
      case "io.sundr.model." + "Lambda":
        return (VisitableBuilder<T, ?>) new LambdaBuilder((Lambda) item);
      case "io.sundr.model." + "Return":
        return (VisitableBuilder<T, ?>) new ReturnBuilder((Return) item);
      case "io.sundr.model." + "Assign":
        return (VisitableBuilder<T, ?>) new AssignBuilder((Assign) item);
      case "io.sundr.model." + "For":
        return (VisitableBuilder<T, ?>) new ForBuilder((For) item);
    }
    return (VisitableBuilder<T, ?>) builderOf(item);
  }

  public class MethodCallStatementNested<N> extends MethodCallFluent<MethodCallStatementNested<N>> implements Nested<N> {
    MethodCallStatementNested(MethodCall item) {
      this.builder = new MethodCallBuilder(this, item);
    }

    MethodCallBuilder builder;

    public N and() {
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
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
      return (N) LambdaFluent.this.withStatement(builder.build());
    }

    public N endForStatement() {
      return and();
    }

  }

}
