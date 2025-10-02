package io.sundr.model.visitors.context.resolver;

import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.model.ContextRef;
import io.sundr.model.Expression;
import io.sundr.model.WithScopeFluent;

public class ScopeResolver implements Visitor<WithScopeFluent<?>> {

  private final Map<String, Expression> resolved;

  public ScopeResolver(Map<String, Expression> resolved) {
    this.resolved = resolved;
  }

  @Override
  public void visit(WithScopeFluent<?> fluent) {
    Expression scope = fluent.buildScope();

    if (scope instanceof ContextRef) {
      ContextRef contextRef = (ContextRef) scope;
      String name = contextRef.getName();
      if (resolved.containsKey(name)) {
        fluent.withScope(resolved.get(name));
      }
    }
  }
}
