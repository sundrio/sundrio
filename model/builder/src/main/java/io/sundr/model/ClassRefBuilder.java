package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.builder.VisitableBuilder;

public class ClassRefBuilder extends ClassRefFluentImpl<ClassRefBuilder>
    implements VisitableBuilder<io.sundr.model.ClassRef, ClassRefBuilder> {
  public ClassRefBuilder() {
    this(false);
  }

  public ClassRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(io.sundr.model.ClassRefFluent<?> fluent) {
    this(fluent, false);
  }

  public ClassRefBuilder(io.sundr.model.ClassRefFluent<?> fluent, java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(io.sundr.model.ClassRefFluent<?> fluent, io.sundr.model.ClassRef instance) {
    this(fluent, instance, false);
  }

  public ClassRefBuilder(io.sundr.model.ClassRefFluent<?> fluent, io.sundr.model.ClassRef instance,
      java.lang.Boolean validationEnabled) {
    this.fluent = fluent;
    fluent.withFullyQualifiedName(instance.getFullyQualifiedName());
    fluent.withDimensions(instance.getDimensions());
    fluent.withArguments(instance.getArguments());
    fluent.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(io.sundr.model.ClassRef instance) {
    this(instance, false);
  }

  public ClassRefBuilder(io.sundr.model.ClassRef instance, java.lang.Boolean validationEnabled) {
    this.fluent = this;
    this.withFullyQualifiedName(instance.getFullyQualifiedName());
    this.withDimensions(instance.getDimensions());
    this.withArguments(instance.getArguments());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  io.sundr.model.ClassRefFluent<?> fluent;
  java.lang.Boolean validationEnabled;

  public io.sundr.model.ClassRef build() {
    ClassRef buildable = new ClassRef(fluent.getFullyQualifiedName(), fluent.getDimensions(), fluent.getArguments(),
        fluent.getAttributes());
    return buildable;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ClassRefBuilder that = (ClassRefBuilder) o;
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
