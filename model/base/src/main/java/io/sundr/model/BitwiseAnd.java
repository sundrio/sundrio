package io.sundr.model;

public class BitwiseAnd extends BinaryExpression {

  private static final String SYMBOL = "&";

  public BitwiseAnd(Expression left, Expression right) {
    super(left, right);
  }

  public BitwiseAnd(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
