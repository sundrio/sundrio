package io.sundr.model;

public class GreaterThanOrEqual extends BinaryExpression {

  private static final String SYMBOL = ">=";

  public GreaterThanOrEqual(Expression left, Expression right) {
    super(left, right);
  }

  public GreaterThanOrEqual(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
