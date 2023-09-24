package io.sundr.model;

public class PropertyRef implements Expression {

  private final Property property;

  public PropertyRef(Property property) {
    this.property = property;
  }

  public Property getProperty() {
    return property;
  }

  @Override
  public String render() {
    return property.getName();
  }
}
