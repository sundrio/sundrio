package io.sundr.model;

import java.util.List;

public class Super implements Expression {

  /**
   * Creates a super constructor delegation, rendered as {@code super(...)}.
   * This is distinct from calling a method on super, which is {@code super.name(...)}.
   *
   * @param arguments the constructor arguments
   * @return the constructor delegation call
   */
  public static MethodCall construct(Expression... arguments) {
    return new MethodCall("super", (Expression) null, arguments);
  }

  /**
   * Creates a super constructor delegation, rendered as {@code super(...)}.
   *
   * @param arguments the constructor arguments
   * @return the constructor delegation call
   */
  public static MethodCall construct(List<? extends Expression> arguments) {
    return construct(arguments.toArray(new Expression[0]));
  }

  /**
   * Creates a super constructor delegation, accepting literal values in place of expressions.
   * Literals are wrapped in a {@link ValueRef}, expressions are used as is.
   *
   * @param arguments the constructor arguments, each a literal or an expression
   * @return the constructor delegation call
   */
  public static MethodCall construct(Object... arguments) {
    return construct(ValueRef.toExpressions(arguments));
  }

  @Override
  public String render() {
    return "super";
  }
}
