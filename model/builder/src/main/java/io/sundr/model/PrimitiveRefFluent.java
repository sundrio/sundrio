package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class PrimitiveRefFluent<A extends io.sundr.model.PrimitiveRefFluent<A>> extends TypeRefFluent<A> {

  private int dimensions;
  private String name;

  public PrimitiveRefFluent() {
  }

  public PrimitiveRefFluent(PrimitiveRef instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(PrimitiveRef instance) {
    if (instance != null) {
      this.withName(instance.getName());
      this.withDimensions(instance.getDimensions());
      this.withAttributes(instance.getAttributes());
    }
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
    PrimitiveRefFluent that = (PrimitiveRefFluent) o;
    if (!(Objects.equals(name, that.name))) {
      return false;
    }
    if (dimensions != that.dimensions) {
      return false;
    }
    return true;
  }

  public int getDimensions() {
    return this.dimensions;
  }

  public String getName() {
    return this.name;
  }

  public boolean hasDimensions() {
    return true;
  }

  public boolean hasName() {
    return this.name != null;
  }

  public int hashCode() {
    return Objects.hash(name, dimensions);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(name == null)) {
      sb.append("name:");
      sb.append(name);
      sb.append(",");
    }
    sb.append("dimensions:");
    sb.append(dimensions);
    sb.append("}");
    return sb.toString();
  }

  public A withDimensions(int dimensions) {
    this.dimensions = dimensions;
    return (A) this;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

}