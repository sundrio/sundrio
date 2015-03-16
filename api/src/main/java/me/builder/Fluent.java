package me.builder;

/**
 * The base interface for Fluent interfaces.
 * Implementers of this interface are usually classes that need to provide chained method calls (e.g. Builders).
 * @param <F>   The type that is expected to be returned by the Fluent methods.
 */
public interface Fluent<F extends Fluent<F>> {

}
