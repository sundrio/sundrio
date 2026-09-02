package io.sundr.model;

public class ReturnDslVariableStep implements Statement {

  private final Variable<?> variable;

  /*
   * public ReturnDslVariableStep(String name) {
   * if (name == null || name.isEmpty()) {
   * throw new IllegalArgumentException("Variable name cannot be null or empty");
   * }
   * this.name = name;
   * }
   */

  public ReturnDslVariableStep(Variable<?> variable) {
    if (variable == null) {
      throw new IllegalArgumentException("Property cannot be null");
    }
    if (variable.getName() == null || variable.getName().isEmpty()) {
      throw new IllegalArgumentException("Property name cannot be null or empty");
    }
    this.variable = variable;
  }

  public Return call(String method, Expression... arguments) {
    return new Return(variable.call(method, arguments));
  }

  public Return call(String method) {
    return new Return(variable.call(method));
  }

  /**
   * Returns the result of a method call, accepting literal values in place of expressions.
   * Literals are wrapped in a {@link ValueRef}, expressions are used as is.
   *
   * @param method the name of the method to call
   * @param arguments the method arguments, each a literal or an expression
   * @return the return statement
   */
  public Return call(String method, Object... arguments) {
    return new Return(variable.call(method, ValueRef.toExpressions(arguments)));
  }

  public Variable<?> getVariable() {
    return variable;
  }

  @Override
  public String render() {
    return "return " + variable.getName() + SEMICOLN;
  }
}
