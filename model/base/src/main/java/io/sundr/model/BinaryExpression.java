package io.sundr.model;

public class BinaryExpression implements Expression {

  private final Expression left;
  private final Expression right;

  public BinaryExpression(Expression left, Expression right) {
    this.left = left;
    this.right = right;
  }

  public String getSymbol() {
    return "";
  }

  public Expression getLeft() {
    return left;
  }

  public Expression getRight() {
    return right;
  }

  public String render() {
    return left.render() + " " + getSymbol() + " " + right.render();
  }

}
