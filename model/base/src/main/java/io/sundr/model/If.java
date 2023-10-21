package io.sundr.model;

import java.util.Optional;

public class If implements Statement {

  private final Expression condition;
  private final Statement statement;
  private final Optional<Statement> elseStatement;

  public If(Expression condition, Statement statement, Optional<Statement> elseStatement) {
    this.condition = condition;
    this.statement = statement;
    this.elseStatement = elseStatement;
  }

  public If(Expression condition, Statement statement, Statement elseStatement) {
    this(condition, statement, Optional.ofNullable(elseStatement));
  }

  public If(Expression condition, Statement statement) {
    this(condition, statement, Optional.empty());
  }

  public Expression getCondition() {
    return condition;
  }

  public Statement getStatement() {
    return statement;
  }

  public Optional<Statement> getElseStatement() {
    return elseStatement;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("if").append(SPACE)
        .append(OP).append(condition.render()).append(CP)
        .append(SPACE).append(OB).append(NEWLINE);

    sb.append(tab(statement.renderStatement()));
    sb.append(CB);
    elseStatement.ifPresent(e -> {
      sb.append(" else ");
      if (e instanceof If) {
        sb.append(e.render());
      } else {
        sb.append(OB);
        sb.append(NEWLINE);
        sb.append(tab(e.renderStatement()));
        sb.append(CB);
      }
    });
    sb.append(NEWLINE);
    return sb.toString();
  }
}
