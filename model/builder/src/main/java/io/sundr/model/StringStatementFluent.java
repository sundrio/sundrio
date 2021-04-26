package io.sundr.model;

import java.lang.Boolean;
import java.lang.Object;
import java.lang.String;
import java.util.function.Supplier;

import io.sundr.builder.Fluent;

public interface StringStatementFluent<A extends StringStatementFluent<A>> extends Fluent<A> {

  public Supplier<String> getSupplier();

  public <T extends Object> A withSupplier(Supplier<String> supplier);

  public Boolean hasSupplier();
}
