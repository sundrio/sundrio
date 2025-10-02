package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class StringStatementBuilder extends StringStatementFluent<StringStatementBuilder>
    implements VisitableBuilder<StringStatement, StringStatementBuilder> {

  StringStatementFluent<?> fluent;

  public StringStatementBuilder() {
    this.fluent = this;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public StringStatementBuilder(StringStatement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public StringStatement build() {
    StringStatement buildable = new StringStatement(fluent.getSupplier());
    return buildable;
  }

}