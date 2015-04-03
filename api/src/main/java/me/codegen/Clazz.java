package me.codegen;

import java.util.Set;

public interface Clazz<T extends Type, P extends Property<T>> {

    T getType();
    Set<? extends Method<T, P>> getConstructors();
    Set<? extends Method<T, P>> getMethods();
    Set<P> getFields();
    Set<T> getImports();
    Set<? extends Clazz> getNested();
}
