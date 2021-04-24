package io.sundr.model;

import java.lang.Boolean;
import java.util.List;
import java.util.function.Supplier;

import io.sundr.model.builder.Fluent;

public interface BlockFluent<A extends BlockFluent<A>> extends io.sundr.model.builder.Fluent<A> {

  public Supplier<List<Statement>> getProvider();

  public <T> A withProvider(Supplier<List<Statement>> provider);

  public Boolean hasProvider();
}
