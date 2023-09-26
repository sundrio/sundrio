package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class AnnotationRefBuilder extends AnnotationRefFluent<AnnotationRefBuilder>
    implements VisitableBuilder<AnnotationRef, AnnotationRefBuilder> {
  public AnnotationRefBuilder() {
    this.fluent = this;
  }

  public AnnotationRefBuilder(AnnotationRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public AnnotationRefBuilder(AnnotationRefFluent<?> fluent, AnnotationRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public AnnotationRefBuilder(AnnotationRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  AnnotationRefFluent<?> fluent;

  public AnnotationRef build() {
    AnnotationRef buildable = new AnnotationRef(fluent.buildClassRef(), fluent.getParameters(), fluent.getAttributes());
    return buildable;
  }

}