package io.sundr.model;

import java.lang.Object;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.Objects;
import java.util.function.Supplier;

import io.sundr.builder.BaseFluent;

/**
 * Generated
 */
@SuppressWarnings("unchecked")
public class StringStatementFluent<A extends io.sundr.model.StringStatementFluent<A>> extends BaseFluent<A> {

  private Supplier<String> supplier;

  public StringStatementFluent() {
  }

  public StringStatementFluent(StringStatement instance) {
    this.copyInstance(instance);
  }

  protected void copyInstance(StringStatement instance) {
    if (instance != null) {
      this.withSupplier(instance.getSupplier());
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
    StringStatementFluent that = (StringStatementFluent) o;
    if (!(Objects.equals(supplier, that.supplier))) {
      return false;
    }
    return true;
  }

  public Supplier<String> getSupplier() {
    return this.supplier;
  }

  public boolean hasSupplier() {
    return this.supplier != null;
  }

  public int hashCode() {
    return Objects.hash(supplier);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    if (!(supplier == null)) {
      sb.append("supplier:");
      sb.append(supplier);
    }
    sb.append("}");
    return sb.toString();
  }

  public <T> A withSupplier(Supplier<String> supplier) {
    this.supplier = supplier;
    return (A) this;
  }

}
