package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;
import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class PropertyRefFluent<A extends PropertyRefFluent<A>> extends BaseFluent<A> {
  public PropertyRefFluent() {
  }

  public PropertyRefFluent(PropertyRef instance) {
    if (instance != null) {
      this.withProperty(instance.getProperty());
    }
  }

  private PropertyBuilder property;

  public Property buildProperty() {
    return this.property != null ? this.property.build() : null;
  }

  public A withProperty(Property property) {
    _visitables.get("property").remove(this.property);
    if (property != null) {
      this.property = new PropertyBuilder(property);
      _visitables.get("property").add(this.property);
    } else {
      this.property = null;
      _visitables.get("property").remove(this.property);
    }
    return (A) this;
  }

  public boolean hasProperty() {
    return this.property != null;
  }

  public PropertyNested<A> withNewProperty() {
    return new PropertyNested(null);
  }

  public PropertyNested<A> withNewPropertyLike(Property item) {
    return new PropertyNested(item);
  }

  public PropertyNested<A> editProperty() {
    return withNewPropertyLike(java.util.Optional.ofNullable(buildProperty()).orElse(null));
  }

  public PropertyNested<A> editOrNewProperty() {
    return withNewPropertyLike(java.util.Optional.ofNullable(buildProperty()).orElse(new PropertyBuilder().build()));
  }

  public PropertyNested<A> editOrNewPropertyLike(Property item) {
    return withNewPropertyLike(java.util.Optional.ofNullable(buildProperty()).orElse(item));
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PropertyRefFluent that = (PropertyRefFluent) o;
    if (!java.util.Objects.equals(property, that.property))
      return false;

    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(property, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (property != null) {
      sb.append("property:");
      sb.append(property);
    }
    sb.append("}");
    return sb.toString();
  }

  public class PropertyNested<N> extends PropertyFluent<PropertyNested<N>> implements Nested<N> {
    PropertyNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    PropertyBuilder builder;

    public N and() {
      return (N) PropertyRefFluent.this.withProperty(builder.build());
    }

    public N endProperty() {
      return and();
    }

  }

}
