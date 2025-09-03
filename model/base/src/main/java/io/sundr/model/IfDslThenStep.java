package io.sundr.model;

import java.util.List;

public class IfDslThenStep implements Statement {
  private Expression condition;
  private List<Statement> statements;

  public IfDslThenStep(Expression condition, List<Statement> statements) {
    this.condition = condition;
    this.statements = statements;
  }

  public Expression getCondition() {
    return condition;
  }

  public List<Statement> getStatements() {
    return statements;
  }

  public If orElse(Statement... elseStatement) {
    return new If(condition, Block.wrap(statements), Block.wrap(elseStatement));
  }

  public If orElse(List<Statement> elseStatement) {
    return new If(condition, Block.wrap(statements), Block.wrap(elseStatement));
  }

  public If end() {
    return new If(condition, Block.wrap(statements));
  }

  void setCondition(Expression condition) {
    this.condition = condition;
  }

  void setStatements(List<Statement> statements) {
    this.statements = statements;
  }

  @Override
  public String render() {
    return end().render();
  }
}
