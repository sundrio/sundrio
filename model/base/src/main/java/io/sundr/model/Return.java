package io.sundr.model;

public class Return implements Statement {

  private Expression expression;

  public String render() {
    return "return " + expression.render();
  }
}
