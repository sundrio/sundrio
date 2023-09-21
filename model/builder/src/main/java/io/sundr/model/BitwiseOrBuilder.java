package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class BitwiseOrBuilder extends BitwiseOrFluent<BitwiseOrBuilder>
    implements VisitableBuilder<BitwiseOr, BitwiseOrBuilder> {
  public BitwiseOrBuilder() {
    this(false);
  }

  public BitwiseOrBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public BitwiseOrBuilder(BitwiseOrFluent<?> fluent) {
    this(fluent, false);
  }

  public BitwiseOrBuilder(BitwiseOrFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public BitwiseOrBuilder(BitwiseOrFluent<?> fluent, BitwiseOr instance) {
    this(fluent, instance, false);
  }

  public BitwiseOrBuilder(BitwiseOrFluent<?> fluent, BitwiseOr instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public BitwiseOrBuilder(BitwiseOr instance) {
    this(instance, false);
  }

  public BitwiseOrBuilder(BitwiseOr instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  BitwiseOrFluent<?> fluent;
  Boolean validationEnabled;

  public BitwiseOr build() {
    BitwiseOr buildable = new BitwiseOr(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
