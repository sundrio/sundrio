package io.sundr.model;

public class Modulo extends BinaryExpression {

  private static final String SYMBOL = "%";

  public Modulo(Expression left, Expression right) {
    super(left, right);
  }

  public Modulo(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
