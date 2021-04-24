package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class StringStatementBuilder extends StringStatementFluentImpl<StringStatementBuilder>
    implements io.sundr.model.builder.VisitableBuilder<StringStatement, StringStatementBuilder> {

  StringStatementFluent<?> fluent;
  Boolean validationEnabled;

  public StringStatementBuilder() {
    this(true);
  }

  public StringStatementBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent) {
    this(fluent, true);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance) {
    this(fluent, instance, true);
  }

  public StringStatementBuilder(StringStatementFluent<?> fluent, StringStatement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withProvider(instance.getProvider());
    this.validationEnabled = validationEnabled;
  }

  public StringStatementBuilder(StringStatement instance) {
    this(instance, true);
  }

  public StringStatementBuilder(StringStatement instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withProvider(instance.getProvider());
    this.validationEnabled = validationEnabled;
  }

  public StringStatement build() {
    StringStatement buildable = new StringStatement(fluent.getProvider());
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
