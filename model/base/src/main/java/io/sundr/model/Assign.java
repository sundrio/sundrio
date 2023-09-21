package io.sundr.model;

public class Assign implements ExpressionOrStatement {

  private final Expression target;
  private final Expression value;

  public Assign(Expression target, Expression value) {
    this.target = target;
    this.value = value;
  }

  public Assign(Property property, Expression value) {
    this(new PropertyRef(property), value);
  }

  public Expression getTarget() {
    return target;
  }

  public Assign(Property property, Object value, Object... rest) {
    this(property, ValueRef.from(value, rest));
  }

  public Expression getValue() {
    return value;
  }

  public String render() {
    return target.render() + " = " + value.render();
  }
}
