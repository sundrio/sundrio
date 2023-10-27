package io.sundr.builder.internal.visitors.lombok;

import io.sundr.builder.internal.visitors.AddSetters;

public class AddLombokSetters extends AddSetters {

  public AddLombokSetters() {
    super(def -> def.hasMatchingAnnotation(a -> {
      String name = a.buildClassRef().getFullyQualifiedName();
      return name.equals("lombok.Setter") || name.equals("lombok.Data");
    }));
  }
}
