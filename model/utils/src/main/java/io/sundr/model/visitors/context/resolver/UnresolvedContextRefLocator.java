package io.sundr.model.visitors.context.resolver;

import java.util.ArrayList;
import java.util.List;

import io.sundr.builder.Visitor;
import io.sundr.model.ContextRef;
import io.sundr.model.ContextRefBuilder;
import io.sundr.model.TypeDef;
import io.sundr.model.TypeDefBuilder;

/**
 * Visitor that locates all unresolved ContextRef objects in a model.
 * Used for testing to verify that all ContextRef objects have been resolved.
 */
public class UnresolvedContextRefLocator implements Visitor<ContextRefBuilder> {

  private final List<ContextRef> unresolvedContextRefs = new ArrayList<>();

  public static UnresolvedContextRefLocator forType(TypeDef typeDef) {
    UnresolvedContextRefLocator locator = new UnresolvedContextRefLocator();
    TypeDefBuilder typeDefBuilder = new TypeDefBuilder(typeDef);
    typeDefBuilder.accept(locator);
    return locator;
  }

  @Override
  public void visit(ContextRefBuilder fluent) {
    ContextRef contextRef = fluent.build();
    unresolvedContextRefs.add(contextRef);
  }

  public List<ContextRef> getUnresolvedContextRefs() {
    return new ArrayList<>(unresolvedContextRefs);
  }

  public int getCount() {
    return unresolvedContextRefs.size();
  }

  public void reset() {
    unresolvedContextRefs.clear();
  }

  public boolean hasUnresolvedContextRefs() {
    return !unresolvedContextRefs.isEmpty();
  }
}
