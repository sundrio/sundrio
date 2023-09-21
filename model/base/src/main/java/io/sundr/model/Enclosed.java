package io.sundr.model;

public class Enclosed implements Expression {

  private final Expression expresion;

  public Enclosed(Expression expresion) {
    this.expresion = expresion;
  }

  public Expression getExpresion() {
    return expresion;
  }

  @Override
  public String render() {
    return "(" + expresion.render() + ")";
  }
}
