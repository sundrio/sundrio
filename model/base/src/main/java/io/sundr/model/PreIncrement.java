package io.sundr.model;

public class PreIncrement implements Expression {

  private final Expression expression;

  public PreIncrement(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public String render() {
    return "++" + expression.renderExpression();
  }
}
