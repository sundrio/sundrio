package io.sundr.model.visitors.context.resolver;

import static io.sundr.model.utils.Resolvers.createResolutionMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.sundr.builder.Visitable;
import io.sundr.builder.Visitor;
import io.sundr.model.BlockFluent;
import io.sundr.model.Declare;
import io.sundr.model.Expression;
import io.sundr.model.Statement;

public class LocalVariableResolver implements Visitor<BlockFluent<?>> {
  private final Map<String, Expression> resolved;

  public LocalVariableResolver(Map<String, Expression> resolved) {
    this.resolved = resolved;
  }

  @Override
  public void visit(BlockFluent<?> fluent) {
    List<Statement> statements = fluent.buildStatements();
    Map<String, Expression> resolutionWithLocalVariablesMap = new HashMap<>(resolved);

    for (Statement statement : statements) {
      if (statement instanceof Declare) {
        Declare declare = (Declare) statement;
        resolutionWithLocalVariablesMap.putAll(createResolutionMap(declare.getProperties()));
      }
    }

    if (fluent instanceof Visitable<?>) {
      Visitable<?> visitable = (Visitable<?>) fluent;
      visitable.accept(new MethodArgumentsResolver(resolutionWithLocalVariablesMap));
      visitable.accept(new ScopeResolver(resolutionWithLocalVariablesMap));
      visitable.accept(new MethodCallArgumentsResolver(resolutionWithLocalVariablesMap));
    }
  }
}