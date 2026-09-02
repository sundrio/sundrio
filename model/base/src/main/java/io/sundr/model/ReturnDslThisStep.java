package io.sundr.model;

public class ReturnDslThisStep implements Statement {

  public Return ref(String fieldName) {
    return new Return(This.ref(fieldName));
  }

  public Return ref(Field field) {
    return new Return(This.ref(field));
  }

  public Return call(String methodName, Expression... arguments) {
    return new Return(new This().call(methodName, arguments));
  }

  public Return call(String methodName) {
    return new Return(new This().call(methodName));
  }

  /**
   * Returns the result of a method call, accepting literal values in place of expressions.
   * Literals are wrapped in a {@link ValueRef}, expressions are used as is.
   *
   * @param methodName the name of the method to call
   * @param arguments the method arguments, each a literal or an expression
   * @return the return statement
   */
  public Return call(String methodName, Object... arguments) {
    return new Return(new This().call(methodName, ValueRef.toExpressions(arguments)));
  }

  @Override
  public String render() {
    return "return this" + SEMICOLN;
  }
}
