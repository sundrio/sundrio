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

  public Foreach(Property declarationProperty, Expression expression, Statement body) {
    this(new Declare(declarationProperty), expression, body);
  }

  public Foreach(Property declarationProperty, Property expressionProperty, Statement body) {
    this(new Declare(declarationProperty), expressionProperty.toReference(), body);
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
    sb.append(" : ");
    sb.append(expression.render());
    sb.append(CP);
    sb.append(SPACE).append(OB).append(NEWLINE);
    sb.append(tab(body.renderStatement()));
    sb.append(CB).append(NEWLINE);
    return sb.toString();
  }
}
