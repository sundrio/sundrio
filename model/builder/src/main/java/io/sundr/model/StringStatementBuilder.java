package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class StringStatementBuilder extends StringStatementFluentImpl<StringStatementBuilder>
    implements VisitableBuilder<StringStatement, io.sundr.model.StringStatementBuilder> {
  public StringStatementBuilder() {
    this(false);
  }

  public StringStatementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(io.sundr.model.StringStatementFluent<?> fluent) {
    this(fluent, false);
  }

  public StringStatementBuilder(io.sundr.model.StringStatementFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(io.sundr.model.StringStatementFluent<?> fluent, io.sundr.model.StringStatement instance) {
    this(fluent, instance, false);
  }

  public StringStatementBuilder(io.sundr.model.StringStatementFluent<?> fluent, io.sundr.model.StringStatement instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withSupplier(instance.getSupplier());
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(io.sundr.model.StringStatement instance) {
    this(instance, false);
  }

  public StringStatementBuilder(io.sundr.model.StringStatement instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withSupplier(instance.getSupplier());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.StringStatementFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.StringStatement build() {
    StringStatement buildable = new StringStatement(fluent.getSupplier());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    StringStatementBuilder that = (StringStatementBuilder) o;
    if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
      return false;

    if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(fluent, validationEnabled, super.hashCode());
  }

}
