package io.sundr.model;

public class Inverse implements Expression {

  private final Expression expresion;

  public Inverse(Expression expresion) {
    this.expresion = expresion;
  }

  public Expression getExpresion() {
    return expresion;
  }

  @Override
  public String render() {
    return "~" + expresion.render();
  }
}
