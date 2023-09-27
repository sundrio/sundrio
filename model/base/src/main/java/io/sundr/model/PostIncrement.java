package io.sundr.model;

public class PostIncrement implements Expression {

  private final Expression expression;

  public PostIncrement(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public String render() {
    return expression.render() + "++";
  }
}
