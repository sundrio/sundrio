package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class TypeParamRefFluent<A extends TypeParamRefFluent<A>> extends TypeRefFluent<A> {

  private int dimensions;
  private String name;

  public TypeParamRefFluent() {
  }

  public TypeParamRefFluent(TypeParamRef instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(TypeParamRef instance) {
    if (instance != null) {
      this.withName(instance.getName());
      this.withDimensions(instance.getDimensions());
      this.withAttributes(instance.getAttributes());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    TypeParamRefFluent that = (TypeParamRefFluent) o;
    if (!java.util.Objects.equals(name, that.name))
      return false;
    if (dimensions != that.dimensions)
      return false;
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

  public A withDimensions(int dimensions) {
    this.dimensions = dimensions;
    return (A) this;
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

}