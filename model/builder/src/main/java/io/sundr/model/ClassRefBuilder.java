package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;

import io.sundr.model.builder.VisitableBuilder;

public class ClassRefBuilder extends ClassRefFluentImpl<ClassRefBuilder>
    implements io.sundr.model.builder.VisitableBuilder<ClassRef, ClassRefBuilder> {

  ClassRefFluent<?> fluent;
  Boolean validationEnabled;

  public ClassRefBuilder() {
    this(true);
  }

  public ClassRefBuilder(Boolean validationEnabled) {
    this.fluent = this;
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent) {
    this(fluent, true);
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent, Boolean validationEnabled) {
    this.fluent = fluent;
    this.validationEnabled = validationEnabled;
  }

  public ClassRefBuilder(ClassRefFluent<?> fluent, ClassRef instance) {
    this(fluent, instance, true);
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
    this(instance, true);
  }

  public ClassRefBuilder(ClassRef instance, Boolean validationEnabled) {
    this.fluent = this;
    this.withFullyQualifiedName(instance.getFullyQualifiedName());
    this.withDimensions(instance.getDimensions());
    this.withArguments(instance.getArguments());
    this.withAttributes(instance.getAttributes());
    this.validationEnabled = validationEnabled;
  }

  public ClassRef build() {
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
