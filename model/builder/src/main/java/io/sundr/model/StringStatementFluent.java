package io.sundr.model;

import java.lang.Boolean;
import java.lang.String;
import java.util.function.Supplier;

public interface StringStatementFluent<A extends StringStatementFluent<A>> extends StatementFluent<A> {

  public Supplier<String> getProvider();

  public <T> A withProvider(Supplier<String> provider);

  public Boolean hasProvider();
}
