package io.sundr.model;

public class Not implements Expression {

  private final Expression expresion;

  public Not(Expression expresion) {
    this.expresion = expresion;
  }

  public Expression getExpresion() {
    return expresion;
  }

  @Override
  public String render() {
    return "!" + expresion.renderExpression();
  }
}
