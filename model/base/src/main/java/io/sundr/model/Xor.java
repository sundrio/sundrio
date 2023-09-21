package io.sundr.model;

public class Xor extends BinaryExpression {

  private static final String SYMBOL = "^";

  public Xor(Expression left, Expression right) {
    super(left, right);
  }

  public Xor(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
