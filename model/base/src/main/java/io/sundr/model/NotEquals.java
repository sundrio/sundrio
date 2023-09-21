package io.sundr.model;

public class NotEquals extends BinaryExpression {

  private static final String SYMBOL = "!=";

  public NotEquals(Expression left, Expression right) {
    super(left, right);
  }

  public NotEquals(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
