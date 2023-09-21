package io.sundr.model;

public class GreaterThan extends BinaryExpression {

  private static final String SYMBOL = ">";

  public GreaterThan(Expression left, Expression right) {
    super(left, right);
  }

  public GreaterThan(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
