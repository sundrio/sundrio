package io.sundr.model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class If implements Statement {

  private final Expression condition;
  private final Statement statement;
  private final Optional<Statement> elseStatement;

  public If(Expression condition, Statement statement, Optional<Statement> elseStatement) {
    this.condition = condition;
    this.statement = statement;
    this.elseStatement = elseStatement;
  }

  public If(Expression condition, Statement statement, Statement elseStatement) {
    this(condition, statement, Optional.ofNullable(elseStatement));
  }

  public If(Expression condition, Statement statement) {
    this(condition, statement, Optional.empty());
  }

  public Expression getCondition() {
    return condition;
  }

  public Statement getStatement() {
    return statement;
  }

  public Optional<Statement> getElseStatement() {
    return elseStatement;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("if").append(SPACE)
        .append(OP).append(condition.renderExpression()).append(CP)
        .append(SPACE).append(OB).append(NEWLINE);

    sb.append(tab(statement.renderStatement()));
    sb.append(CB);
    elseStatement.ifPresent(e -> {
      sb.append(" else ");
      if (e instanceof If) {
        sb.append(e.render());
      } else {
        sb.append(OB);
        sb.append(NEWLINE);
        sb.append(tab(e.renderStatement()));
        sb.append(CB);
      }
    });
    sb.append(NEWLINE);
    return sb.toString();
  }

  //
  // DSL
  //

  public static DslConditionStep condition(Expression condition) {
    return new DslConditionStep(condition);
  }

  public static DslConditionStep eq(Expression left, Expression right) {
    return new DslConditionStep(Expression.eq(left, right));
  }

  public static DslConditionStep ge(Expression left, Expression right) {
    return new DslConditionStep(new GreaterThanOrEqual(left, right));
  }

  public static DslConditionStep gt(Expression left, Expression right) {
    return new DslConditionStep(new GreaterThan(left, right));
  }

  public static DslConditionStep le(Expression left, Expression right) {
    return new DslConditionStep(new LessThanOrEqual(left, right));
  }

  public static DslConditionStep lt(Expression left, Expression right) {
    return new DslConditionStep(new LessThan(left, right));
  }

  public static DslConditionStep ne(Expression left, Expression right) {
    return new DslConditionStep(Expression.ne(left, right));
  }

  public static DslConditionStep isNull(Expression expr) {
    return new DslConditionStep(Expression.isNull(expr));
  }

  public static DslConditionStep notNull(Expression expr) {
    return new DslConditionStep(Expression.notNull(expr));
  }

  public static class DslConditionStep {
    private Expression condition;

    DslConditionStep(Expression condition) {
      this.condition = condition;
    }

    public DslThenStep then(Statement... statement) {
      return new DslThenStep(condition, Arrays.asList(statement));
    }

    public DslThenStep then(List<Statement> statements) {
      return new DslThenStep(condition, statements);
    }
  }

  public static class DslThenStep implements Statement {
    private Expression condition;
    private Statement statement;

    DslThenStep(Expression condition, List<Statement> statements) {
      this.condition = condition;
      this.statement = Block.wrap(statements);
    }

    public If orElse(Statement... elseStatement) {
      return new If(condition, statement, Block.wrap(elseStatement));
    }

    public If orElse(List<Statement> elseStatement) {
      return new If(condition, statement, Block.wrap(elseStatement));
    }

    public If end() {
      return new If(condition, statement);
    }

    @Override
    public String render() {
      return end().render();
    }
  }
}
