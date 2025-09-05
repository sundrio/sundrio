package io.sundr.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    refs.addAll(condition.getReferences());
    refs.addAll(statement.getReferences());
    elseStatement.ifPresent(s -> refs.addAll(s.getReferences()));
    return refs;
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

  public static IfDslConditionStep condition(Expression condition) {
    return new IfDslConditionStep(condition);
  }

  public static IfDslConditionStep not(Expression condition) {
    return new IfDslConditionStep(Expression.not(condition));
  }

  public static IfDslConditionStep eq(Expression left, Expression right) {
    return new IfDslConditionStep(Expression.eq(left, right));
  }

  public static IfDslConditionStep ge(Expression left, Expression right) {
    return new IfDslConditionStep(new GreaterThanOrEqual(left, right));
  }

  public static IfDslConditionStep gt(Expression left, Expression right) {
    return new IfDslConditionStep(new GreaterThan(left, right));
  }

  public static IfDslConditionStep le(Expression left, Expression right) {
    return new IfDslConditionStep(new LessThanOrEqual(left, right));
  }

  public static IfDslConditionStep lt(Expression left, Expression right) {
    return new IfDslConditionStep(new LessThan(left, right));
  }

  public static IfDslConditionStep ne(Expression left, Expression right) {
    return new IfDslConditionStep(Expression.ne(left, right));
  }

  public static IfDslConditionStep isNull(Expression expr) {
    return new IfDslConditionStep(Expression.isNull(expr));
  }

  public static IfDslConditionStep notNull(Expression expr) {
    return new IfDslConditionStep(Expression.notNull(expr));
  }
}
