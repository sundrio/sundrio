package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class BinaryExpressionBuilder extends BinaryExpressionFluent<BinaryExpressionBuilder>
    implements VisitableBuilder<BinaryExpression, BinaryExpressionBuilder> {
  public BinaryExpressionBuilder() {
    this(false);
  }

  public BinaryExpressionBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public BinaryExpressionBuilder(BinaryExpressionFluent<?> fluent) {
    this(fluent, false);
  }

  public BinaryExpressionBuilder(BinaryExpressionFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public BinaryExpressionBuilder(BinaryExpressionFluent<?> fluent, BinaryExpression instance) {
    this(fluent, instance, false);
  }

  public BinaryExpressionBuilder(BinaryExpressionFluent<?> fluent, BinaryExpression instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withLeft(instance.getLeft());
      fluent.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  public BinaryExpressionBuilder(BinaryExpression instance) {
    this(instance, false);
  }

  public BinaryExpressionBuilder(BinaryExpression instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withLeft(instance.getLeft());
      this.withRight(instance.getRight());
    }
    this.validationEnabled = validationEnabled;
  }

  BinaryExpressionFluent<?> fluent;
  Boolean validationEnabled;

  public BinaryExpression build() {
    BinaryExpression buildable = new BinaryExpression(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}
