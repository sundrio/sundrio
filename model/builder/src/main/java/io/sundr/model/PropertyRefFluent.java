package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;
import java.util.Optional;

import io.sundr.builder.Nested;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class PropertyRefFluent<A extends io.sundr.model.PropertyRefFluent<A>> extends WithScopeFluent<A> {

  private PropertyBuilder property;

  public PropertyRefFluent() {
  }

  public PropertyRefFluent(PropertyRef instance) {
    this.copyInstance(instance);
  }

  public Property buildProperty() {
    return this.property != null ? this.property.build() : null;
  }

  protected void copyInstance(PropertyRef instance) {
    if (instance != null) {
      this.withProperty(instance.getProperty());
      this.withScope(instance.getScope());
    }
  }

  public PropertyNested<A> editOrNewProperty() {
    return this.withNewPropertyLike(Optional.ofNullable(this.buildProperty()).orElse(new PropertyBuilder().build()));
  }

  public PropertyNested<A> editOrNewPropertyLike(Property item) {
    return this.withNewPropertyLike(Optional.ofNullable(this.buildProperty()).orElse(item));
  }

  public PropertyNested<A> editProperty() {
    return this.withNewPropertyLike(Optional.ofNullable(this.buildProperty()).orElse(null));
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    if (!(super.equals(o))) {
      return false;
    }
    PropertyRefFluent that = (PropertyRefFluent) o;
    if (!(Objects.equals(property, that.property))) {
      return false;
    }
    return true;
  }

  public boolean hasProperty() {
    return this.property != null;
  }

  public int hashCode() {
    return Objects.hash(property);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(property == null)) {
      sb.append("property:");
      sb.append(property);
    }
    sb.append("}");
    return sb.toString();
  }

  public PropertyNested<A> withNewProperty() {
    return new PropertyNested(null);
  }

  public PropertyNested<A> withNewPropertyLike(Property item) {
    return new PropertyNested(item);
  }

  public A withProperty(Property property) {
    this._visitables.remove("property");
    if (property != null) {
      this.property = new PropertyBuilder(property);
      this._visitables.get("property").add(this.property);
    } else {
      this.property = null;
      this._visitables.get("property").remove(this.property);
    }
    return (A) this;
  }

  public class PropertyNested<N> extends PropertyFluent<PropertyNested<N>> implements Nested<N> {

    PropertyBuilder builder;

    PropertyNested(Property item) {
      this.builder = new PropertyBuilder(this, item);
    }

    public N and() {
      return (N) PropertyRefFluent.this.withProperty(builder.build());
    }

    public N endProperty() {
      return and();
    }

  }
}
