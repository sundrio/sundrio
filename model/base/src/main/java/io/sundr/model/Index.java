package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Index implements Expression {

  private final Expression scope;
  private final Expression expression;

  public Index(Expression scope, Expression expression) {
    this.scope = scope;
    this.expression = expression;
  }

  public Expression getScope() {
    return scope;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (scope != null) {
      refs.addAll(scope.getReferences());
    }
    if (expression != null) {
      refs.addAll(expression.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    return scope.renderExpression() + "[" + expression.renderExpression() + "]";
  }
}
