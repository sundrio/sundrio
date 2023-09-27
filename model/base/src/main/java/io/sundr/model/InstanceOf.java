package io.sundr.model;

public class InstanceOf implements Expression {

  private final Expression expression;
  private final ClassRef type;

  public InstanceOf(Expression expression, ClassRef type) {
    this.expression = expression;
    this.type = type;
  }

  public InstanceOf(Expression expression, Class type) {
    this(expression, ClassRef.forClass(type));
  }

  public Expression getExpression() {
    return expression;
  }

  public ClassRef getType() {
    return type;
  }

  @Override
  public String render() {
    return expression.render() + " instanceof " + type.render();
  }
}
