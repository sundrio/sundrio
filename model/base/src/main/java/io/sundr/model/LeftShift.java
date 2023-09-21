package io.sundr.model;

public class LeftShift extends BinaryExpression {

  private static final String SYMBOL = "<<";

  public LeftShift(Expression left, Expression right) {
    super(left, right);
  }

  public LeftShift(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
