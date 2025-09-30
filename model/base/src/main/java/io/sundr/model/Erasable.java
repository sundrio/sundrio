package io.sundr.model;

/**
 * Interface for types that can provide their erasure representation.
 * Type erasure removes generic type information and provides a canonical
 * representation that can be used for comparison and resolution.
 *
 * @param <T> the type that implements this interface
 */
public interface Erasable<T> {

  /**
   * Returns the erasure of this type as a canonical string representation.
   * The erasure removes generic type parameters and provides a stable
   * way to compare types regardless of generic instantiation.
   *
   * @return the type erasure string
   */
  String getErasure();

  /**
   * Returns an erased version of this object with generic type information removed.
   * This creates a canonical instance that can be used for comparison and resolution.
   *
   * @return the erased version of this object
   */
  T withErasure();
}