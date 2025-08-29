package io.sundr.model;

public class Ternary implements Expression {

  private Expression condition;
  private Expression result;
  private Expression alternative;

  public Ternary(Expression condition, Expression result, Expression alternative) {
    this.condition = condition;
    this.result = result;
    this.alternative = alternative;
  }

  public Expression getCondition() {
    return condition;
  }

  public Expression getResult() {
    return result;
  }

  public Expression getAlternative() {
    return alternative;
  }

  public String render() {
    StringBuilder sb = new StringBuilder();
    sb.append(condition.render())
        .append(" ? ")
        .append(result.renderExpression())
        .append(" : ")
        .append(alternative.renderExpression());
    return sb.toString();
  }
}
