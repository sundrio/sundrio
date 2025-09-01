package io.sundr.model;

import java.util.List;

public class Super implements Expression {

  public static MethodCall call(Expression... arguments) {
    return new MethodCall("super", (Expression) null, arguments);
  }

  public static MethodCall call(List<? extends Expression> arguments) {
    return call(arguments.toArray(new Expression[0]));
  }

  @Override
  public String render() {
    return "super";
  }
}
