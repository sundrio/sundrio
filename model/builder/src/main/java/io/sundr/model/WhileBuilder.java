package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class WhileBuilder extends WhileFluent<WhileBuilder> implements VisitableBuilder<While, WhileBuilder> {

  WhileFluent<?> fluent;

  public WhileBuilder() {
    this.fluent = this;
  }

  public WhileBuilder(WhileFluent<?> fluent) {
    this.fluent = fluent;
  }

  public WhileBuilder(While instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public WhileBuilder(WhileFluent<?> fluent, While instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public While build() {
    While buildable = new While(fluent.buildCondition(), fluent.buildStatement());
    return buildable;
  }

}
