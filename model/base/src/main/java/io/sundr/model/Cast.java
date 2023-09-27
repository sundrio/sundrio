package io.sundr.model;

public class Cast implements Expression {

  private final TypeRef type;
  private final Expression expression;

  public Cast(TypeRef type, Expression expression) {
    this.type = type;
    this.expression = expression;
  }

  public Cast(Class type, Expression expression) {
    this(ClassRef.forClass(type), expression);
  }

  public Cast(Class type, Property property) {
    this(ClassRef.forClass(type), property.toReference());
  }

  public Expression getExpression() {
    return expression;
  }

  public TypeRef getType() {
    return type;
  }

  @Override
  public String render() {
    return OP + type.render() + CP + SPACE + expression.render();
  }
}
