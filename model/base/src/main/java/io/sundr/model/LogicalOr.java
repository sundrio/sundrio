package io.sundr.model;

public class LogicalOr extends BinaryExpression {

  private static final String SYMBOL = "||";

  public LogicalOr(Expression left, Expression right) {
    super(left, right);
  }

  public LogicalOr(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
