package me.dsl.internal.processor;

import java.util.Set;

public class Node<T> {

    private final T item;
    private final Set<T> transitions;

    public Node(T item, Set<T> transitions) {
        this.item = item;
        this.transitions = transitions;
    }

    public T getItem() {
        return item;
    }

    public Set<T> getTransitions() {
        return transitions;
    }
}
