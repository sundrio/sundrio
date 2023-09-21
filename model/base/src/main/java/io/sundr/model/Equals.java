package io.sundr.model;

public class Equals extends BinaryExpression {

  private static final String SYMBOL = "==";

  public Equals(Expression left, Expression right) {
    super(left, right);
  }

  public Equals(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
