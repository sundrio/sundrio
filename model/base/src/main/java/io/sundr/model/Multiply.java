package io.sundr.model;

public class Multiply extends BinaryExpression {

  private static final String SYMBOL = "*";

  public Multiply(Expression left, Expression right) {
    super(left, right);
  }

  public Multiply(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
