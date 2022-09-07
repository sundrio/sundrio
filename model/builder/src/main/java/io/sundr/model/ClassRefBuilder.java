package io.sundr.model;

import java.lang.Boolean;

import io.sundr.builder.VisitableBuilder;

public class ClassRefBuilder extends ClassRefFluentImpl<ClassRefBuilder>
    implements VisitableBuilder<ClassRef, ClassRefBuilder> {
  public ClassRefBuilder() {
    this(false);
  }

  public ClassRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent) {
    this(fluent, false);
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent, ClassRef instance) {
    this(fluent, instance, false);
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent, ClassRef instance, Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withFullyQualifiedName(instance.getFullyQualifiedName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withArguments(instance.getArguments());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(ClassRef instance) {
    this(instance, false);
  }

  public ClassRefBuilder(ClassRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withFullyQualifiedName(instance.getFullyQualifiedName());
    this.withDimensions(instance.getDimensions());
    this.withArguments(instance.getArguments());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  ClassRefFluent<?> fluent;
  Boolean validationEnabled;

  public ClassRef build() {
    ClassRef buildable = new ClassRef(fluent.getFullyQualifiedName(), fluent.getDimensions(), fluent.getArguments(),
        fluent.getAttributes());
    return buildable;
  }

}
