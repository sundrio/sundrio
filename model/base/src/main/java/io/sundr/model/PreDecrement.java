package io.sundr.model;

public class PreDecrement implements Expression {

  private final Expression expression;

  public PreDecrement(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public String render() {
    return "--" + expression.renderExpression();
  }
}
