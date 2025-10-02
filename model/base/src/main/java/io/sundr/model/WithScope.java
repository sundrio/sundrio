package io.sundr.model;

/**
 * Abstract class for expressions that have a scope.
 * This allows for generic handling of scoped expressions like PropertyRef, MethodCall, Index, etc.
 */
public abstract class WithScope implements Expression {

  private final Expression scope;

  protected WithScope(Expression scope) {
    this.scope = scope;
  }

  /**
   * Gets the scope expression for this scoped expression.
   *
   * @return the scope expression, or null if unscoped
   */
  public Expression getScope() {
    return scope;
  }
}