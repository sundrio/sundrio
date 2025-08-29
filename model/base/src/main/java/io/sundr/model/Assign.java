package io.sundr.model;

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
