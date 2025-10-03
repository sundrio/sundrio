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
public class ReturnDslThisStepFluent<A extends io.sundr.model.ReturnDslThisStepFluent<A>> extends BaseFluent<A> {

  public ReturnDslThisStepFluent() {
  }

  public ReturnDslThisStepFluent(ReturnDslThisStep instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(ReturnDslThisStep instance) {
    instance = instance != null ? instance : new ReturnDslThisStep();
    if (instance != null) {

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
    ReturnDslThisStepFluent that = (ReturnDslThisStepFluent) o;
    return true;
  }

  public int hashCode() {
    return Objects.hash();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    sb.append("}");
    return sb.toString();
  }

}
