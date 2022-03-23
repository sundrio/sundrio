package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class SourceBuilder extends SourceFluentImpl<SourceBuilder>
    implements VisitableBuilder<Source, io.sundr.model.SourceBuilder> {
  public SourceBuilder() {
    this(false);
  }

  public SourceBuilder(Boolean validationEnabled) {
    this(new Source(), validationEnabled);
  }

  public SourceBuilder(io.sundr.model.SourceFluent<?> fluent) {
    this(fluent, false);
  }

  public SourceBuilder(io.sundr.model.SourceFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this(fluent, new Source(), validationEnabled);
  }

  public SourceBuilder(io.sundr.model.SourceFluent<?> fluent, io.sundr.model.Source instance) {
    this(fluent, instance, false);
  }

  public SourceBuilder(io.sundr.model.SourceFluent<?> fluent, io.sundr.model.Source instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withTypes(instance.getTypes());
    this.validationEnabled = validationEnabled;
  }

  public SourceBuilder(io.sundr.model.Source instance) {
    this(instance, false);
  }

  public SourceBuilder(io.sundr.model.Source instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withTypes(instance.getTypes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.SourceFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.Source build() {
    Source buildable = new Source(fluent.getTypes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    SourceBuilder that = (SourceBuilder) o;
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
