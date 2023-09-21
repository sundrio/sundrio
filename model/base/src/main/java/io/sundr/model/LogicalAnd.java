package io.sundr.model;

public class LogicalAnd extends BinaryExpression {

  private static final String SYMBOL = "&&";

  public LogicalAnd(Expression left, Expression right) {
    super(left, right);
  }

  public LogicalAnd(Object left, Object right) {
    this(ValueRef.from(left), ValueRef.from(right));
  }

  public String getSymbol() {
    return SYMBOL;
  }
}
