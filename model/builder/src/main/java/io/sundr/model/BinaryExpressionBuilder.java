package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class BinaryExpressionBuilder extends BinaryExpressionFluent<BinaryExpressionBuilder>
    implements VisitableBuilder<BinaryExpression, BinaryExpressionBuilder> {
  public BinaryExpressionBuilder() {
    this.fluent = this;
  }

  public BinaryExpressionBuilder(BinaryExpressionFluent<?> fluent) {
    this.fluent = fluent;
  }

  public BinaryExpressionBuilder(BinaryExpressionFluent<?> fluent, BinaryExpression instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public BinaryExpressionBuilder(BinaryExpression instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  BinaryExpressionFluent<?> fluent;

  public BinaryExpression build() {
    BinaryExpression buildable = new BinaryExpression(fluent.buildLeft(), fluent.buildRight());
    return buildable;
  }

}