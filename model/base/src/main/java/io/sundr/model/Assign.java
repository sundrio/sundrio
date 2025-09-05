package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Assign implements ExpressionOrStatement {

  private final Expression target;
  private final Expression value;

  public Assign(Expression target, Expression value) {
    this.target = target;
    this.value = value;
  }

  public Expression getTarget() {
    return target;
  }

  public Expression getValue() {
    return value;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (target != null) {
      refs.addAll(target.getReferences());
    }
    if (value != null) {
      refs.addAll(value.getReferences());
    }
    return refs;
  }

  public String render() {
    return target.renderExpression() + " = " + value.renderExpression();
  }

  //
  // DSL
  //
  public static DslToStep to(Expression target) {
    return new DslToStep(target);
  }

  public static class DslToStep {
    private final Expression target;

    public DslToStep(Expression target) {
      this.target = target;
    }

    public Assign value(Object value, Object... more) {
      return new Assign(target, ValueRef.from(value, more));
    }
  }
}
