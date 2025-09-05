package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

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

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (condition != null) {
      refs.addAll(condition.getReferences());
    }
    if (result != null) {
      refs.addAll(result.getReferences());
    }
    if (alternative != null) {
      refs.addAll(alternative.getReferences());
    }
    return refs;
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
