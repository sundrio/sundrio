package io.sundr.model;

public class Continue implements Statement {

  @Override
  public String render() {
    return "continue;";
  }
}
