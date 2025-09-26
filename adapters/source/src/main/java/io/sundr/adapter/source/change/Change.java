package io.sundr.adapter.source.change;

/**
 * Represents a change to a source code element.
 *
 * @param <T> the type of element that changed (Method, Property, etc.)
 */
public class Change<T> {
  private final ChangeType changeType;
  private final T oldElement; // null for ADDED
  private final T newElement; // null for REMOVED

  private Change(ChangeType changeType, T oldElement, T newElement) {
    this.changeType = changeType;
    this.oldElement = oldElement;
    this.newElement = newElement;
  }

  /**
   * Creates a change representing an added element.
   */
  public static <T> Change<T> added(T newElement) {
    return new Change<>(ChangeType.ADDED, null, newElement);
  }

  /**
   * Creates a change representing a removed element.
   */
  public static <T> Change<T> removed(T oldElement) {
    return new Change<>(ChangeType.REMOVED, oldElement, null);
  }

  /**
   * Creates a change representing a modified element.
   */
  public static <T> Change<T> modified(T oldElement, T newElement) {
    return new Change<>(ChangeType.MODIFIED, oldElement, newElement);
  }

  public ChangeType getChangeType() {
    return changeType;
  }

  public T getOldElement() {
    return oldElement;
  }

  public T getNewElement() {
    return newElement;
  }

  /**
   * Returns the current element (new for ADDED/MODIFIED, old for REMOVED).
   */
  public T getElement() {
    return newElement != null ? newElement : oldElement;
  }

  @Override
  public String toString() {
    switch (changeType) {
      case ADDED:
        return "ADDED: " + newElement;
      case REMOVED:
        return "REMOVED: " + oldElement;
      case MODIFIED:
        return "MODIFIED: " + oldElement + " -> " + newElement;
      default:
        return changeType + ": " + getElement();
    }
  }
}