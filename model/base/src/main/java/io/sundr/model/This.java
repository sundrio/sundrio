package io.sundr.model;

import java.util.List;

public class This implements Expression {

  public static FieldRef ref(String name) {
    return new FieldRef(name, new This());
  }

  public static FieldRef ref(Field field) {
    return new FieldRef(field, new This());
  }

  public static MethodCall call(Expression... arguments) {
    return new MethodCall("this", (Expression) null, arguments);
  }

  public static MethodCall call(List<? extends Expression> arguments) {
    return call(arguments.toArray(new Expression[0]));
  }

  /**
   * Creates a this constructor delegation, accepting literal values in place of expressions.
   * Literals are wrapped in a {@link ValueRef}, expressions are used as is.
   *
   * @param arguments the constructor arguments, each a literal or an expression
   * @return the constructor delegation call
   */
  public static MethodCall call(Object... arguments) {
    return call(ValueRef.toExpressions(arguments));
  }

  @Override
  public String render() {
    return "this";
  }
}
