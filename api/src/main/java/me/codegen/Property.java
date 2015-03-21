package me.codegen;

public interface Property<T extends Type> {
    
    T getType();
    String getName();
    boolean isArray();
}
