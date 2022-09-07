package io.sundr.model;

import java.lang.Boolean;
import java.lang.String;
import java.util.function.Supplier;

import io.sundr.builder.Fluent;

/**
 * Generated
 */
public interface StringStatementFluent<A extends StringStatementFluent<A>> extends Fluent<A> {
  public Supplier<String> getSupplier();

  public <T> A withSupplier(Supplier<String> supplier);

  public Boolean hasSupplier();

}
