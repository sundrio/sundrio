package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class AnnotationRefBuilder extends AnnotationRefFluent<AnnotationRefBuilder>
    implements VisitableBuilder<AnnotationRef, AnnotationRefBuilder> {
  public AnnotationRefBuilder() {
    this(false);
  }

  public AnnotationRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public AnnotationRefBuilder(AnnotationRefFluent<?> fluent) {
    this(fluent, false);
  }

  public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, AnnotationRef instance) {
    this(fluent, instance, false);
  }

  public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, AnnotationRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    if (instance != null) {
      fluent.withClassRef(instance.getClassRef());
      fluent.withParameters(instance.getParameters());
      fluent.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  public AnnotationRefBuilder(AnnotationRef instance) {
    this(instance, false);
  }

  public AnnotationRefBuilder(AnnotationRef instance, Boolean validationEnabled) {
    this.fluent = this;
    if (instance != null) {
      this.withClassRef(instance.getClassRef());
      this.withParameters(instance.getParameters());
      this.withAttributes(instance.getAttributes());
    }
    this.validationEnabled = validationEnabled;
  }

  AnnotationRefFluent<?> fluent;
  Boolean validationEnabled;

  public AnnotationRef build() {
    AnnotationRef buildable = new AnnotationRef(fluent.buildClassRef(), fluent.getParameters(), fluent.getAttributes());
    return buildable;
  }

}
