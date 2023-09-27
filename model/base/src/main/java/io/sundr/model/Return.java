package io.sundr.model;

public class Return implements Statement {

  private final Expression expression;

  public Return(Expression expression) {
    this.expression = expression;
  }

  public Return(Property property) {
    this(property.toReference());
  }

  public Return(Object object) {
    this(ValueRef.from(object));
  }

  public Expression getExpression() {
    return expression;
  }

  public String render() {
    return "return " + expression.render() + SEMICOLN;
  }
}
