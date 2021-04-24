package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class SourceBuilder extends SourceFluentImpl<SourceBuilder>
    implements io.sundr.model.builder.VisitableBuilder<Source, SourceBuilder> {

  SourceFluent<?> fluent;
  Boolean validationEnabled;

  public SourceBuilder() {
    this(true);
  }

  public SourceBuilder(Boolean validationEnabled) {
    this(new Source(), validationEnabled);
  }

  public SourceBuilder(SourceFluent<?> fluent) {
    this(fluent, true);
  }

  public SourceBuilder(SourceFluent<?> fluent, Boolean validationEnabled) {
    this(fluent, new Source(), validationEnabled);
  }

  public SourceBuilder(SourceFluent<?> fluent, Source instance) {
    this(fluent, instance, true);
  }

  public SourceBuilder(SourceFluent<?> fluent, Source instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withTypes(instance.getTypes());
    this.validationEnabled = validationEnabled;
  }

  public SourceBuilder(Source instance) {
    this(instance, true);
  }

  public SourceBuilder(Source instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withTypes(instance.getTypes());
    this.validationEnabled = validationEnabled;
  }

  public Source build() {
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
