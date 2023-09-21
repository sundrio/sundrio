package io.sundr.model;

public class PostIncrement implements Expression {

  private final Expression expression;

  public PostIncrement(Expression expression) {
    this.expression = expression;
  }

  public Expression getexpression() {
    return expression;
  }

  @Override
  public String render() {
    return expression.render() + "++";
  }
}
