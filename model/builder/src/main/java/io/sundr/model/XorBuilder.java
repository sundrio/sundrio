package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class XorBuilder extends XorFluent<XorBuilder> implements VisitableBuilder<Xor, XorBuilder> {
  public XorBuilder() {
    this.fluent = this;
  }

  public XorBuilder(XorFluent<?> fluent) {
    this.fluent = fluent;
  }

  public XorBuilder(XorFluent<?> fluent, Xor instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public XorBuilder(Xor instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  XorFluent<?> fluent;

  public Xor build() {
    Xor buildable = new Xor(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}