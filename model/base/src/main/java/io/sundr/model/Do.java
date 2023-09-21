package io.sundr.model;

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

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("do").append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(statement.render()));
    sb.append(CB).append(" while ").append(OP).append(condition.render()).append(CP).append(SEMICOLN).append(NEWLINE);
    return sb.toString();
  }
}
