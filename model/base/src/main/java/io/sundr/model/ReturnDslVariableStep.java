package io.sundr.model;

public class ReturnDslVariableStep implements Statement {

  private final String name;

  public ReturnDslVariableStep(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Variable name cannot be null or empty");
    }
    this.name = name;
  }

  public ReturnDslVariableStep(Property property) {
    if (property == null) {
      throw new IllegalArgumentException("Property cannot be null");
    }
    if (property.getName() == null || property.getName().isEmpty()) {
      throw new IllegalArgumentException("Property name cannot be null or empty");
    }
    this.name = property.getName();
  }

  public Return call(String method, Expression... arguments) {
    return new Return(Property.newProperty(name).call(method, arguments));
  }

  public Return call(String method) {
    return new Return(Property.newProperty(name).call(method));
  }

  String getName() {
    return name;
  }

  @Override
  public String render() {
    return "return " + name + SEMICOLN;
  }
}
