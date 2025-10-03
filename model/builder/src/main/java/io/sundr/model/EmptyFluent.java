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
public class EmptyFluent<A extends io.sundr.model.EmptyFluent<A>> extends BaseFluent<A> {

  public EmptyFluent() {
  }

  public EmptyFluent(Empty instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(Empty instance) {
    instance = instance != null ? instance : new Empty();
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
    EmptyFluent that = (EmptyFluent) o;
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
