package io.sundr.model;

public class Positive implements Expression {

  private final Expression expresion;

  public Positive(Expression expresion) {
    this.expresion = expresion;
  }

  public Expression getExpresion() {
    return expresion;
  }

  @Override
  public String render() {
    return "+" + expresion.render();
  }
}
