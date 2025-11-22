package io.sundr.model;

import java.util.List;

public class This implements Expression {

  public static FieldRef ref(String name) {
    return new FieldRef(name, new This());
  }

  public static FieldRef ref(Field field) {
    return new FieldRef(field, new This());
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
