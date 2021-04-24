package io.sundr.model.builder;

import java.lang.FunctionalInterface;

@FunctionalInterface
public interface Visitor<T> {

  void visit(T element);
}
