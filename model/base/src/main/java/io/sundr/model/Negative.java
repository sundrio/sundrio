package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Negative implements Expression {

  private final Expression expresion;

  public Negative(Expression expresion) {
    this.expresion = expresion;
  }

  public Expression getExpresion() {
    return expresion;
  }

  @Override
  public Set<ClassRef> getReferences() {
    Set<ClassRef> refs = new HashSet<>();
    if (expresion != null) {
      refs.addAll(expresion.getReferences());
    }
    return refs;
  }

  @Override
  public String render() {
    return "-" + expresion.renderExpression();
  }
}
