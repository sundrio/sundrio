package me.dsl.internal.processor.model;

public class Property {
    
    private final Type type;
    private final String name;

    public Property(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
