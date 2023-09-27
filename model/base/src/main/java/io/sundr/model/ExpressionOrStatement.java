package io.sundr.model;

public interface ExpressionOrStatement extends Expression, Statement {

  default Statement toStatement() {
    return new StatementAdapter(this);
  }
}
