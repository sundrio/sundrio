package me.codegen;

import java.util.Set;

public interface Clazz<T extends Type, P extends Property<T>> {

    T getType();
    Method<T, P> getConstructor();
    Set<Method<T, P>> getMethods();
    Set<P> getFields();
    Set<T> getImports();
}
