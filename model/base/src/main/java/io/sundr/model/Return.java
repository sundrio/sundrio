package io.sundr.model;

public class Return implements Statement {

  private final Expression expression;

  public static Return newInstance(ClassRef type, Expression... arguments) {
    return new Return(new Construct(type, arguments));
  }

  public static Return newInstance(ClassRef type, java.util.List<Expression> arguments) {
    return new Return(new Construct(type, arguments));
  }

  public static Return newInstance(Class type, Expression... arguments) {
    return new Return(new Construct(type, arguments));
  }

  public static Return newInstance(Class type, java.util.List<Expression> arguments) {
    return new Return(new Construct(type, arguments));
  }

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
    return "return " + expression.renderExpression() + SEMICOLN;
  }
}
