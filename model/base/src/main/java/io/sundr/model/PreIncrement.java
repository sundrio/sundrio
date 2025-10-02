package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class PreIncrement implements ExpressionOrStatement {

  private final Expression expression;

  public PreIncrement(Expression expression) {
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (expression != null) {
      refs.addAll(expression.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    return "++" + expression.renderExpression();
  }
}
