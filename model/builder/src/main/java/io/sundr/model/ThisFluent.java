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
public class ThisFluent<A extends io.sundr.model.ThisFluent<A>> extends BaseFluent<A> {

  public ThisFluent() {
  }

  public ThisFluent(This instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(This instance) {
    instance = instance != null ? instance : new This();
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
    ThisFluent that = (ThisFluent) o;
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
