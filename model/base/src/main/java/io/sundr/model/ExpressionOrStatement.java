package io.sundr.model;

public interface ExpressionOrStatement extends Expression, Statement {

  default Statement asStatement() {
    return new StatementAdapter(this);
  }

  public class StatementAdapter implements Statement {

    private final ExpressionOrStatement expressionOrStatement;

    public StatementAdapter(ExpressionOrStatement expressionOrStatement) {
      this.expressionOrStatement = expressionOrStatement;
    }

    @Override
    public String render() {
      return expressionOrStatement.render() + SEMICOLN;
    }
  }
}
