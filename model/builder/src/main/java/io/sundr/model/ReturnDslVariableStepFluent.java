package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.SuppressWarnings;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class ReturnDslVariableStepFluent<A extends ReturnDslVariableStepFluent<A>> extends BaseFluent<A> {

  private String name;

  public ReturnDslVariableStepFluent() {
  }

  public ReturnDslVariableStepFluent(ReturnDslVariableStep instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(ReturnDslVariableStep instance) {
    if (instance != null) {
      this.withName(instance.getName());
    }
  }

  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    if (!super.equals(o))
      return false;
    ReturnDslVariableStepFluent that = (ReturnDslVariableStepFluent) o;
    if (!java.util.Objects.equals(name, that.name))
      return false;
    return true;
  }

  public String getName() {
    return this.name;
  }

  public boolean hasName() {
    return this.name != null;
  }

  public int hashCode() {
    return java.util.Objects.hash(name, super.hashCode());
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (name != null) {
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
