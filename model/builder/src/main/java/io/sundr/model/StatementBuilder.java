package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class StatementBuilder extends StatementFluentImpl<StatementBuilder>
    implements io.sundr.model.builder.VisitableBuilder<Statement, StatementBuilder> {

  StatementFluent<?> fluent;
  Boolean validationEnabled;

  public StatementBuilder() {
    this(true);
  }

  public StatementBuilder(Boolean validationEnabled) {
    this(new Statement(), validationEnabled);
  }

  public StatementBuilder(StatementFluent<?> fluent) {
    this(fluent, true);
  }

  public StatementBuilder(StatementFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Statement(), validationEnabled);
  }

  public StatementBuilder(StatementFluent<?> fluent, Statement instance) {
    this(fluent, instance, true);
  }

  public StatementBuilder(StatementFluent<?> fluent, Statement instance, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public StatementBuilder(Statement instance) {
    this(instance, true);
  }

  public StatementBuilder(Statement instance, Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public Statement build() {
    Statement buildable = new Statement();
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    StatementBuilder that = (StatementBuilder) o;
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
