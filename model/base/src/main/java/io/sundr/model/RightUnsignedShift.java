package io.sundr.model;

public class RightUnsignedShift extends BinaryExpression {

  private static final String SYMBOL = ">>>";

  public RightUnsignedShift(Expression left, Expression right) {
    super(left, right);
  }

  public RightUnsignedShift(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
