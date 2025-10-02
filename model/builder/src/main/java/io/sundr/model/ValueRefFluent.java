package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ValueRefFluent<A extends ValueRefFluent<A>> extends BaseFluent<A> {

  private Object value;

  public ValueRefFluent() {
  }

  public ValueRefFluent(ValueRef instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(ValueRef instance) {
    if (instance != null) {
      this.withValue(instance.getValue());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ValueRefFluent that = (ValueRefFluent) o;
    if (!java.util.Objects.equals(value, that.value))
      return false;
    return true;
  }

  public Object getValue() {
    return this.value;
  }

  public boolean hasValue() {
    return this.value != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(value, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (value != null) {
      sb.append("value:");
      sb.append(value);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withValue(Object value) {
    this.value = value;
    return (A) this;
  }

}
