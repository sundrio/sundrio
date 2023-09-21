package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ForBuilder extends ForFluent<ForBuilder> implements VisitableBuilder<For, ForBuilder> {
  public ForBuilder() {
    this(false);
  }

  public ForBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ForBuilder(ForFluent<?> fluent) {
    this(fluent, false);
  }

  public ForBuilder(ForFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ForBuilder(ForFluent<?> fluent, For instance) {
    this(fluent, instance, false);
  }

  public ForBuilder(ForFluent<?> fluent, For instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withInit(instance.getInit());
      fluent.withCompare(instance.getCompare());
      fluent.withUpdate(instance.getUpdate());
      fluent.withBody(instance.getBody());
    }
    this.validationEnabled = validationEnabled;
  }

  public ForBuilder(For instance) {
    this(instance, false);
  }

  public ForBuilder(For instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withInit(instance.getInit());
      this.withCompare(instance.getCompare());
      this.withUpdate(instance.getUpdate());
      this.withBody(instance.getBody());
    }
    this.validationEnabled = validationEnabled;
  }

  ForFluent<?> fluent;
  Boolean validationEnabled;

  public For build() {
    For buildable = new For(fluent.buildInit(), fluent.buildCompare(), fluent.buildUpdate(), fluent.buildBody());
    return buildable;
  }

}
