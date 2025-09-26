package io.sundr.adapter.source.change;

/**
 * Enumeration of the types of changes that can occur to source code elements.
 */
public enum ChangeType {
  /**
   * Element was added in the new version.
   */
  ADDED,

  /**
   * Element was removed in the new version.
   */
  REMOVED,

  /**
   * Element was modified between versions.
   */
  MODIFIED
}