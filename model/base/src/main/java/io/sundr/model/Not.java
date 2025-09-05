package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

public class Not implements Expression {

  private final Expression expresion;

  public Not(Expression expresion) {
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
    return "!(" + expresion.renderExpression() + ")";
  }
}
