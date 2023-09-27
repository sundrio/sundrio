package io.sundr.model;

public class PostDecrement implements Expression {

  private final Expression expression;

  public PostDecrement(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public String render() {
    return expression.render() + "--";
  }
}
