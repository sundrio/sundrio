package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class StringStatementBuilder extends StringStatementFluent<StringStatementBuilder>
    implements VisitableBuilder<StringStatement, StringStatementBuilder> {
  public StringStatementBuilder() {
    this.fluent = this;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent) {
    this.fluent = fluent;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public StringStatementBuilder(StringStatement instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  StringStatementFluent<?> fluent;

  public StringStatement build() {
    StringStatement buildable = new StringStatement(fluent.getSupplier());
    return buildable;
  }

}