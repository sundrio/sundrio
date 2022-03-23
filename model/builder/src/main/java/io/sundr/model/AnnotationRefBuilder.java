package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class AnnotationRefBuilder extends AnnotationRefFluentImpl<AnnotationRefBuilder>
    implements VisitableBuilder<AnnotationRef, io.sundr.model.AnnotationRefBuilder> {
  public AnnotationRefBuilder() {
    this(false);
  }

  public AnnotationRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public AnnotationRefBuilder(io.sundr.model.AnnotationRefFluent<?> fluent) {
    this(fluent, false);
  }

  public AnnotationRefBuilder(io.sundr.model.AnnotationRefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public AnnotationRefBuilder(io.sundr.model.AnnotationRefFluent<?> fluent, io.sundr.model.AnnotationRef instance) {
    this(fluent, instance, false);
  }

  public AnnotationRefBuilder(io.sundr.model.AnnotationRefFluent<?> fluent, io.sundr.model.AnnotationRef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withClassRef(instance.getClassRef());
    fluent.withParameters(instance.getParameters());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public AnnotationRefBuilder(io.sundr.model.AnnotationRef instance) {
    this(instance, false);
  }

  public AnnotationRefBuilder(io.sundr.model.AnnotationRef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withClassRef(instance.getClassRef());
    this.withParameters(instance.getParameters());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.AnnotationRefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.AnnotationRef build() {
    AnnotationRef buildable = new AnnotationRef(fluent.getClassRef(), fluent.getParameters(), fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    AnnotationRefBuilder that = (AnnotationRefBuilder) o;
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
