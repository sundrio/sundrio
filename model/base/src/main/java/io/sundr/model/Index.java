package io.sundr.model;

public class Index implements Expression {

  private final Expression scope;
  private final Expression expression;

  public Index(Expression scope, Expression expression) {
    this.scope = scope;
    this.expression = expression;
  }

  public Expression getScope() {
    return scope;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public String render() {
    return scope.render() + "[" + expression.render() + "]";
  }
}
