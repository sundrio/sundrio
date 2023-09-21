package io.sundr.model;

public class BitwiseOr extends BinaryExpression {

  private static final String SYMBOL = "|";

  public BitwiseOr(Expression left, Expression right) {
    super(left, right);
  }

  public BitwiseOr(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
