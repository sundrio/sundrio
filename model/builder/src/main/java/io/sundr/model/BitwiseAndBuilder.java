package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class BitwiseAndBuilder extends BitwiseAndFluent<BitwiseAndBuilder>
    implements VisitableBuilder<BitwiseAnd, BitwiseAndBuilder> {
  public BitwiseAndBuilder() {
    this(false);
  }

  public BitwiseAndBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent) {
    this(fluent, false);
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent, BitwiseAnd instance) {
    this(fluent, instance, false);
  }

  public BitwiseAndBuilder(BitwiseAndFluent<?> fluent, BitwiseAnd instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public BitwiseAndBuilder(BitwiseAnd instance) {
    this(instance, false);
  }

  public BitwiseAndBuilder(BitwiseAnd instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  BitwiseAndFluent<?> fluent;
  Boolean validationEnabled;

  public BitwiseAnd build() {
    BitwiseAnd buildable = new BitwiseAnd(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
