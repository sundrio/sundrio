package io.sundr.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class While implements Statement {

  private final Expression condition;
  private final Statement statement;

  public While(Expression condition, Statement statement) {
    this.condition = condition;
    this.statement = statement;
  }

  public Expression getCondition() {
    return condition;
  }

  public Statement getStatement() {
    return statement;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    refs.addAll(condition.getReferences());
    refs.addAll(statement.getReferences());
    return refs;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("while").append(SPACE).append(OP).append(condition.render()).append(CP)
        .append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(statement.renderStatement()));
    sb.append(CB).append(NEWLINE);
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

    public While body(Statement... statements) {
      return new While(condition, Block.wrap(statements));
    }

    public While body(List<Statement> statements) {
      return new While(condition, Block.wrap(statements));
    }
  }
}
