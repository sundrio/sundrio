package io.sundr.model;

public class ReturnDslVariableStep implements Statement {

  private final String name;

  public ReturnDslVariableStep(Property property) {
    this.name = property.getName();
  }

  public ReturnDslVariableStep(String name) {
    this.name = name;
  }

  public Return call(String method, Expression... arguments) {
    return new Return(Property.newProperty(name).call(method, arguments));
  }

  public Return call(String method) {
    return new Return(Property.newProperty(name).call(method));
  }

  @Override
  public String render() {
    return "return " + name + SEMICOLN;
  }
}
