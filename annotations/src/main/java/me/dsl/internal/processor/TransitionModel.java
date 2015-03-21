package me.dsl.internal.processor;

import javax.lang.model.element.ExecutableElement;

public class TransitionModel {
    
    private final ExecutableElement from;
    private final ExecutableElement to;


    public TransitionModel(ExecutableElement from, ExecutableElement to) {
        this.from = from;
        this.to = to;
    }

    public ExecutableElement getFrom() {
        return from;
    }

    public ExecutableElement getTo() {
        return to;
    }
}
