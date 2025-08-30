package io.sundr.model;

public class This implements Expression {

  public static PropertyRef ref(String name) {
    return new PropertyRef(name, new This());
  }

  public static PropertyRef ref(Property property) {
    return new PropertyRef(property, new This());
  }

  @Override
  public String render() {
    return "this";
  }
}
