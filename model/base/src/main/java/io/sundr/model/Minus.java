package io.sundr.model;

public class Minus extends BinaryExpression {

  private static final String SYMBOL = "-";

  public Minus(Expression left, Expression right) {
    super(left, right);
  }

  public Minus(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
