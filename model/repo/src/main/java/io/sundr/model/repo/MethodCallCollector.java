package io.sundr.model.repo;

import java.util.HashSet;
import java.util.Set;

import io.sundr.builder.Visitor;
import io.sundr.model.MethodCall;
import io.sundr.model.MethodCallBuilder;
import io.sundr.model.MethodCallFluent;

/**
 * Visitor that collects all MethodCall objects from a visitable AST structure.
 * The visitor pattern automatically traverses the entire AST, so this just collects
 * every MethodCall it encounters.
 */
public class MethodCallCollector implements Visitor<MethodCallFluent<?>> {
  private final Set<MethodCall> methodCalls = new HashSet<>();

  @Override
  public void visit(MethodCallFluent<?> methodCallFluent) {
    MethodCallBuilder builder = new MethodCallBuilder(methodCallFluent);
    MethodCall methodCall = builder.build();
    methodCalls.add(methodCall);
  }

  /**
   * Get all collected method calls
   */
  public Set<MethodCall> getMethodCalls() {
    return new HashSet<>(methodCalls);
  }

  /**
   * Clear the collected method calls (useful for reusing the collector)
   */
  public void clear() {
    methodCalls.clear();
  }
}