package io.sundr.model;

public class Plus extends BinaryExpression {

  private static final String SYMBOL = "+";

  public Plus(Expression left, Expression right) {
    super(left, right);
  }

  public Plus(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
