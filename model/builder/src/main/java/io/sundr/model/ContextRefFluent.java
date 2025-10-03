package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ContextRefFluent<A extends io.sundr.model.ContextRefFluent<A>> extends BaseFluent<A> {

  private String name;

  public ContextRefFluent() {
  }

  public ContextRefFluent(ContextRef instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(ContextRef instance) {
    if (instance != null) {
      this.withName(instance.getName());
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
    ContextRefFluent that = (ContextRefFluent) o;
    if (!(Objects.equals(name, that.name))) {
      return false;
    }
    return true;
  }

  public String getName() {
    return this.name;
  }

  public boolean hasName() {
    return this.name != null;
  }

  public int hashCode() {
    return Objects.hash(name);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(name == null)) {
      sb.append("name:");
      sb.append(name);
    }
    sb.append("}");
    return sb.toString();
  }

  public A withName(String name) {
    this.name = name;
    return (A) this;
  }

}
