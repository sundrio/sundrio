package io.sundr.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (condition != null) {
      refs.addAll(condition.getReferences());
    }
    if (statements != null) {
      for (Statement stmt : statements) {
        if (stmt != null) {
          refs.addAll(stmt.getReferences());
        }
      }
    }
    return refs;
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
