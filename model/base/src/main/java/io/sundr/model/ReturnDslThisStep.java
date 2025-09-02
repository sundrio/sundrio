package io.sundr.model;

public class ReturnDslThisStep implements Statement {

  public Return ref(String propertyName) {
    return new Return(new This().ref(propertyName));
  }

  public Return ref(Property property) {
    return new Return(new This().ref(property));
  }

  public Return call(String methodName, Expression... arguments) {
    return new Return(new This().call(methodName, arguments));
  }

  public Return call(String methodName) {
    return new Return(new This().call(methodName));
  }

  @Override
  public String render() {
    return "return this" + SEMICOLN;
  }
}
