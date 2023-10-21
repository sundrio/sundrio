package io.sundr.model;

public interface ExpressionOrStatement extends Expression, Statement {

  @Override
  default String renderStatement() {
    return renderExpression() + SEMICOLN;
  }
}
