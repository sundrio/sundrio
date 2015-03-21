package me.dsl.internal.processor.model;

import java.util.Set;

public class Keyword {
    
    private final String name;
    private final Set<Property> parameters;
    private final Type to;

    public Keyword(String name, Set<Property> parameters, Type to) {
        this.name = name;
        this.parameters = parameters;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public Set<Property> getParameters() {
        return parameters;
    }

    public Type getTo() {
        return to;
    }
}
