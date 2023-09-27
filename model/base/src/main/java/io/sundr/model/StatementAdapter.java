package io.sundr.model;

public class StatementAdapter implements Statement {

  private final Expression expression;

  public StatementAdapter(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public String render() {
    return expression.render() + SEMICOLN;
  }
}
