package io.sundr.model;

public class LessThanOrEqual extends BinaryExpression {

  private static final String SYMBOL = "<=";

  public LessThanOrEqual(Expression left, Expression right) {
    super(left, right);
  }

  public LessThanOrEqual(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
