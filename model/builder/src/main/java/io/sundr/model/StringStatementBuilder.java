package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class StringStatementBuilder extends StringStatementFluent<StringStatementBuilder>
    implements VisitableBuilder<StringStatement, StringStatementBuilder> {
  public StringStatementBuilder() {
    this(false);
  }

  public StringStatementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent) {
    this(fluent, false);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance) {
    this(fluent, instance, false);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withSupplier(instance.getSupplier());
    }
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatement instance) {
    this(instance, false);
  }

  public StringStatementBuilder(StringStatement instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withSupplier(instance.getSupplier());
    }
    this.validationEnabled = validationEnabled;
  }

  StringStatementFluent<?> fluent;
  Boolean validationEnabled;

  public StringStatement build() {
    StringStatement buildable = new StringStatement(fluent.getSupplier());
    return buildable;
  }

}
