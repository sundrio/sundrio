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

  public Variable<?> getVariable() {
    return variable;
  }

  @Override
  public String render() {
    return "return " + variable.getName() + SEMICOLN;
  }
}
