package me.builder;

/**
 * An interface that describes an Editable object.
 * Editable objects are objects that can by edited by obtaining
 * a new instance of the appropriate {@link Builder} feed with the information encapsulated
 * by the current instance.
 * @param <B>
 */
public interface Editable<B extends Builder> {

    B edit();
}
