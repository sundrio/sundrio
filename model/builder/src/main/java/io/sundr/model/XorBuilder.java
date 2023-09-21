package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class XorBuilder extends XorFluent<XorBuilder> implements VisitableBuilder<Xor, XorBuilder> {
  public XorBuilder() {
    this(false);
  }

  public XorBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public XorBuilder(XorFluent<?> fluent) {
    this(fluent, false);
  }

  public XorBuilder(XorFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public XorBuilder(XorFluent<?> fluent, Xor instance) {
    this(fluent, instance, false);
  }

  public XorBuilder(XorFluent<?> fluent, Xor instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public XorBuilder(Xor instance) {
    this(instance, false);
  }

  public XorBuilder(Xor instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  XorFluent<?> fluent;
  Boolean validationEnabled;

  public Xor build() {
    Xor buildable = new Xor(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
