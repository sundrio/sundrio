package io.sundr.model;

public class Return implements Statement {

  private final Expression expression;

  public static Return True() {
    return new Return(new ValueRef(true));
  }

  public static Return False() {
    return new Return(new ValueRef(false));
  }

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

  public static ReturnDslThisStep This() {
    return new ReturnDslThisStep();
  }

  public static Return This(ClassRef castTo) {
    return new Return(new Cast(castTo, new This()));
  }

  public static ReturnDslVariableStep variable(Property property) {
    return new ReturnDslVariableStep(property);
  }

  public static ReturnDslVariableStep variable(String variableName) {
    return new ReturnDslVariableStep(variableName);
  }

  public static Return call(String method, Expression... arguments) {
    return new Return(new MethodCall(method, (Expression) null, arguments));
  }

  public static Return value(Object value) {
    return new Return(ValueRef.from(value));
  }

  public static Return Null() {
    return new Return(ValueRef.NULL);
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
