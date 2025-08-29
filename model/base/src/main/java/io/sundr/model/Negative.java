package io.sundr.model;

public class Negative implements Expression {

  private final Expression expresion;

  public Negative(Expression expresion) {
    this.expresion = expresion;
  }

  public Expression getExpresion() {
    return expresion;
  }

  @Override
  public String render() {
    return "-" + expresion.renderExpression();
  }
}
