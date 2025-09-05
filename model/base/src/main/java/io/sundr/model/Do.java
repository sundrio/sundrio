package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Do implements Statement {

  private final Expression condition;
  private final Statement statement;

  public Do(Expression condition, Statement statement) {
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
    if (condition != null) {
      refs.addAll(condition.getReferences());
    }
    if (statement != null) {
      refs.addAll(statement.getReferences());
    }
    return refs;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("do").append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(statement.renderStatement()));
    sb.append(CB).append(" while ").append(OP).append(condition.render()).append(CP).append(SEMICOLN).append(NEWLINE);
    return sb.toString();
  }
}
