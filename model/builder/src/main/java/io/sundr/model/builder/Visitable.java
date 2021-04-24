package io.sundr.model.builder;

import java.lang.Class;

public interface Visitable<T> {

  T accept(io.sundr.model.builder.Visitor... visitor);

  default <V> T accept(Class<V> type, io.sundr.model.builder.Visitor<V> visitor) {
    return accept(new TypedVisitor<V>() {

      @Override
      public Class<V> getType() {
        return type;
      }

      @Override
      public void visit(V element) {
        visitor.visit(element);
      }
    });
  }

}
