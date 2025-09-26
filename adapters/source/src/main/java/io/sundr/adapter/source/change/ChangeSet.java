package io.sundr.adapter.source.change;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import io.sundr.model.Method;
import io.sundr.model.Property;
import io.sundr.model.TypeDef;

/**
 * Represents all changes detected between two versions of a class.
 * Can work with TypeDef objects or source files directly.
 */
public class ChangeSet {
  private final TypeDef oldTypeDef;
  private final TypeDef newTypeDef;
  private final Set<Change<Method>> methodChanges;
  private final Set<Change<Property>> propertyChanges;

  public ChangeSet(TypeDef oldTypeDef, TypeDef newTypeDef,
      Set<Change<Method>> methodChanges,
      Set<Change<Property>> propertyChanges) {
    this.oldTypeDef = oldTypeDef;
    this.newTypeDef = newTypeDef;
    this.methodChanges = methodChanges != null ? new HashSet<>(methodChanges) : new HashSet<>();
    this.propertyChanges = propertyChanges != null ? new HashSet<>(propertyChanges) : new HashSet<>();
  }

  public TypeDef getOldTypeDef() {
    return oldTypeDef;
  }

  public TypeDef getNewTypeDef() {
    return newTypeDef;
  }

  public Set<Change<Method>> getMethodChanges() {
    return Collections.unmodifiableSet(methodChanges);
  }

  public Set<Change<Property>> getPropertyChanges() {
    return Collections.unmodifiableSet(propertyChanges);
  }

  /**
   * Returns true if any changes were detected.
   */
  public boolean hasChanges() {
    return !methodChanges.isEmpty() || !propertyChanges.isEmpty();
  }

  /**
   * Returns the total number of changes.
   */
  public int getTotalChanges() {
    return methodChanges.size() + propertyChanges.size();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("ChangeSet for ").append(newTypeDef != null ? newTypeDef.getName() : oldTypeDef.getName())
        .append(":\n");

    if (!methodChanges.isEmpty()) {
      sb.append("  Method Changes (").append(methodChanges.size()).append("):\n");
      methodChanges.forEach(change -> sb.append("    ").append(change).append("\n"));
    }

    if (!propertyChanges.isEmpty()) {
      sb.append("  Property Changes (").append(propertyChanges.size()).append("):\n");
      propertyChanges.forEach(change -> sb.append("    ").append(change).append("\n"));
    }

    if (!hasChanges()) {
      sb.append("  No changes detected.\n");
    }

    return sb.toString();
  }
}
