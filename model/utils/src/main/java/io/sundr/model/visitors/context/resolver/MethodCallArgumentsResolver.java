package io.sundr.model.visitors.context.resolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.sundr.builder.Visitor;
import io.sundr.model.ContextRef;
import io.sundr.model.Expression;
import io.sundr.model.MethodCallFluent;

public class MethodCallArgumentsResolver implements Visitor<MethodCallFluent<?>> {

  private final Map<String, Expression> resolved;

  public MethodCallArgumentsResolver(Map<String, Expression> resolved) {
    this.resolved = resolved;
  }

  @Override
  public void visit(MethodCallFluent<?> fluent) {
    List<Expression> arguments = fluent.buildArguments();
    List<Expression> resolvedArguments = new ArrayList<>();
    for (Expression arg : arguments) {
      if (arg instanceof ContextRef) {
        ContextRef contextRef = (ContextRef) arg;
        String name = contextRef.getName();
        if (resolved.containsKey(name)) {
          resolvedArguments.add(resolved.get(name));
          continue;
        }
      }
      resolvedArguments.add(arg);
    }
    fluent.withArguments(resolvedArguments);
  }
}
