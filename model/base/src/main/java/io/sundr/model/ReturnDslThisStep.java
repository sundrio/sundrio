package io.sundr.model;

public class ReturnDslThisStep implements Statement {

  public Return ref(String fieldName) {
    return new Return(new This().ref(fieldName));
  }

  public Return ref(Field field) {
    return new Return(new This().ref(field));
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
