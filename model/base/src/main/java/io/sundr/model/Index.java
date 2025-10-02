package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Index extends WithScope implements Expression {

  private final Expression expression;

  public Index(Expression scope, Expression expression) {
    super(scope);
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (getScope() != null) {
      refs.addAll(getScope().getReferences());
    }
    if (expression != null) {
      refs.addAll(expression.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    return getScope().renderExpression() + "[" + expression.renderExpression() + "]";
  }
}
