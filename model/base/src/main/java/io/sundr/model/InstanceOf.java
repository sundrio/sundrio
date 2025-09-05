package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class InstanceOf implements Expression {

  private final Expression expression;
  private final ClassRef type;

  public InstanceOf(Expression expression, ClassRef type) {
    this.expression = expression;
    this.type = type;
  }

  public InstanceOf(Expression expression, Class type) {
    this(expression, ClassRef.forClass(type));
  }

  public Expression getExpression() {
    return expression;
  }

  public ClassRef getType() {
    return type;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (expression != null) {
      refs.addAll(expression.getReferences());
    }
    if (type != null) {
      refs.addAll(type.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    return expression.renderExpression() + " instanceof " + type.render();
  }
}
