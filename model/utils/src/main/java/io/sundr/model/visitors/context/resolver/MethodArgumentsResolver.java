package io.sundr.model.visitors.context.resolver;

import static io.sundr.model.utils.Resolvers.createResolutionMap;

import java.util.HashMap;
import java.util.Map;

import io.sundr.builder.Visitable;
import io.sundr.builder.Visitor;
import io.sundr.model.Expression;
import io.sundr.model.MethodFluent;

public class MethodArgumentsResolver implements Visitor<MethodFluent<?>> {
  private final Map<String, Expression> resolved;

  public MethodArgumentsResolver(Map<String, Expression> resolved) {
    this.resolved = resolved;
  }

  @Override
  public void visit(MethodFluent<?> fluent) {
    Map<String, Expression> resolutionWithMehtodArgumentsMap = new HashMap<>(resolved);
    resolutionWithMehtodArgumentsMap.putAll(createResolutionMap(fluent.buildArguments()));
    if (fluent instanceof Visitable<?>) {
      Visitable<?> visitable = (Visitable<?>) fluent;
      visitable.accept(new ScopeResolver(resolutionWithMehtodArgumentsMap));
      visitable.accept(new MethodCallArgumentsResolver(resolutionWithMehtodArgumentsMap));
    }
  }
}
