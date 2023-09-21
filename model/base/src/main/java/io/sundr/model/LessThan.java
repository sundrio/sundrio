package io.sundr.model;

public class LessThan extends BinaryExpression {

  private static final String SYMBOL = "<";

  public LessThan(Expression left, Expression right) {
    super(left, right);
  }

  public LessThan(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
