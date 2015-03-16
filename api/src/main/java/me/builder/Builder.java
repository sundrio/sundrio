package me.builder;

/**
 * An interfaces that describes a Builder.
 * @param <T> The type of objects this Builder builds.
 */
public interface Builder<T> {

    /**
     * Build the target object.
     * @return  The target object.
     */
    T build();
}
