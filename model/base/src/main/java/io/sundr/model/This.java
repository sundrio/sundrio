package io.sundr.model;

import java.util.List;

public class This implements Expression {

  public static PropertyRef ref(String name) {
    return new PropertyRef(name, new This());
  }

  public static PropertyRef ref(Property property) {
    return new PropertyRef(property, new This());
  }

  public static MethodCall call(Expression... arguments) {
    return new MethodCall("this", (Expression) null, arguments);
  }

  public static MethodCall call(List<? extends Expression> arguments) {
    return call(arguments.toArray(new Expression[0]));
  }

  @Override
  public String render() {
    return "this";
  }
}
