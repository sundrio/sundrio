package me.codegen;

import java.util.Set;

public interface Method<T extends Type, P extends Property<T>> {
    
    String getName();
    T getReturnType();
    P[] getArguments();
    Set<T> getExceptions();
}
