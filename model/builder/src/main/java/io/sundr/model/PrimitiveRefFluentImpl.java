package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings(value = "unchecked")
public class PrimitiveRefFluentImpl<A extends PrimitiveRefFluent<A>> extends TypeRefFluentImpl<A>
    implements PrimitiveRefFluent<A> {
  public PrimitiveRefFluentImpl() {
  }

  public PrimitiveRefFluentImpl(PrimitiveRef instance) {
    this.withName(instance.getName());
    this.withDimensions(instance.getDimensions());
    this.withAttributes(instance.getAttributes());
  }

  private String name;
  private int dimensions;

  public String getName() {
    return this.name;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

  public Boolean hasName() {
    return this.name != null;
  }

  public int getDimensions() {
    return this.dimensions;
  }

  public A withDimensions(int dimensions) {
    this.dimensions = dimensions;
    return (A) this;
  }

  public Boolean hasDimensions() {
    return true;
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    PrimitiveRefFluentImpl that = (PrimitiveRefFluentImpl) o;
    if (name != null ? !name.equals(that.name) : that.name != null)
      return false;
    if (dimensions != that.dimensions)
      return false;
    return true;
  }

  public int hashCode() {
    return java.util.Objects.hash(name, dimensions, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (name != null) {
      sb.append("name:");
      sb.append(name + ",");
    }
    sb.append("dimensions:");
    sb.append(dimensions);
    sb.append("}");
    return sb.toString();
  }

}
