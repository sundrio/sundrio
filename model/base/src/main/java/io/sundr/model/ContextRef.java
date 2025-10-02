package io.sundr.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a context reference that needs to be resolved later.
 * This is used during parsing when we encounter names that could be:
 * - Local variables
 * - Method parameters
 * - Class fields
 * - Class names (for static method calls)
 * - Imported types
 *
 * Once the actual type is known, this ContextRef should be replaced entirely
 * with the appropriate Expression (PropertyRef, ClassRef, etc.).
 */
public class ContextRef implements Expression {

  private final String name;

  public ContextRef(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public Set<ClassRef> getReferences() {
    // Unresolved context references don't contribute any class references
    return new HashSet<>();
  }

  @Override
  public String render() {
    return name;
  }

  @Override
  public String renderExpression() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null || getClass() != obj.getClass())
      return false;
    ContextRef that = (ContextRef) obj;
    return name.equals(that.name);
  }

  @Override
  public int hashCode() {
    return name.hashCode();
  }

  @Override
  public String toString() {
    return "ContextRef[" + name + "]";
  }
}