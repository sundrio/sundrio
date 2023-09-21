package io.sundr.model;

public class RightShift extends BinaryExpression {

  private static final String SYMBOL = ">>";

  public RightShift(Expression left, Expression right) {
    super(left, right);
  }

  public RightShift(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
