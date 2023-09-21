package io.sundr.model;

public class Foreach implements Statement {

  private final Declare declare;
  private final Expression expression;
  private final Statement body;

  public Foreach(Declare declare, Expression expression, Statement body) {
    this.declare = declare;
    this.expression = expression;
    this.body = body;
  }

  public Declare getDeclare() {
    return declare;
  }

  public Expression getExpression() {
    return expression;
  }

  public Statement getBody() {
    return body;
  }

  @Override
  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append("for").append(SPACE).append(OP);
    sb.append(declare.render().replaceAll(";$", ""));
    sb.append(COLN);
    sb.append(expression.render());
    sb.append(CP);
    sb.append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(body.render()));
    sb.append(CB).append(NEWLINE);
    return sb.toString();
  }
}
