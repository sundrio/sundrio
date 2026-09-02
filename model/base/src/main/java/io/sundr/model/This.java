package io.sundr.model;

import java.util.List;

public class This implements Expression {

  public static FieldRef ref(String name) {
    return new FieldRef(name, new This());
  }

  public static FieldRef ref(Field field) {
    return new FieldRef(field, new This());
  }

  /**
   * Creates a this constructor delegation, rendered as {@code this(...)}.
   * This is distinct from calling a method on this, which is {@code this.name(...)}.
   *
   * @param arguments the constructor arguments
   * @return the constructor delegation call
   */
  public static MethodCall construct(Expression... arguments) {
    return new MethodCall("this", (Expression) null, arguments);
  }

  /**
   * Creates a this constructor delegation, rendered as {@code this(...)}.
   *
   * @param arguments the constructor arguments
   * @return the constructor delegation call
   */
  public static MethodCall construct(List<? extends Expression> arguments) {
    return construct(arguments.toArray(new Expression[0]));
  }

  /**
   * Creates a this constructor delegation, accepting literal values in place of expressions.
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
    return "this";
  }
}
