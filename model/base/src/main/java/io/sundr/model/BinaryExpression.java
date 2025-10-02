package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a binary expression with a left and right operand.
 * While it does not have value to use as a statement, the language allows it.
 */
public class BinaryExpression implements ExpressionOrStatement {

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

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (left != null) {
      refs.addAll(left.getReferences());
    }
    if (right != null) {
      refs.addAll(right.getReferences());
    }
    return refs;
  }

  public String render() {
    return left.renderExpression() + " " + getSymbol() + " " + right.renderExpression();
  }

}
