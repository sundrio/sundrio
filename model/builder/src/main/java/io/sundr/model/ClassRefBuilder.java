package io.sundr.model;

import io.sundr.builder.VisitableBuilder;

public class ClassRefBuilder extends ClassRefFluent<ClassRefBuilder> implements VisitableBuilder<ClassRef, ClassRefBuilder> {
  public ClassRefBuilder() {
    this.fluent = this;
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent) {
    this.fluent = fluent;
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent, ClassRef instance) {
    this.fluent = fluent;
    fluent.copyInstance(instance);
  }

  public ClassRefBuilder(ClassRef instance) {
    this.fluent = this;
    this.copyInstance(instance);
  }

  ClassRefFluent<?> fluent;

  public ClassRef build() {
    ClassRef buildable = new ClassRef(fluent.getFullyQualifiedName(), fluent.getDimensions(), fluent.buildArguments(),
        fluent.getAttributes());
    return buildable;
  }

}