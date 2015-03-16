package me.builder.internal.processor.model;

public class Type {

    private final String type;
    private final String name;

    public Type(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
