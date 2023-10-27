package io.sundr.builder.internal.visitors.lombok;

import io.sundr.builder.internal.visitors.AddGetters;

public class AddLombokGetters extends AddGetters {

  public AddLombokGetters() {
    super(def -> def.hasMatchingAnnotation(a -> {
      String name = a.buildClassRef().getFullyQualifiedName();
      return name.equals("lombok.Getter") || name.equals("lombok.Data");
    }));
  }
}
