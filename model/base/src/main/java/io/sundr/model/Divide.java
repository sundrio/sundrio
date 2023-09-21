package io.sundr.model;

public class Divide extends BinaryExpression {

  private static final String SYMBOL = "/";

  public Divide(Expression left, Expression right) {
    super(left, right);
  }

  public Divide(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
