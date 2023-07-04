package io.sundr.builder.internal.visitors.lombok;

import io.sundr.builder.internal.visitors.AddGetters;

public class AddLombokGetters extends AddGetters {

  public AddLombokGetters() {
    super(def -> def.hasMatchingAnnotation(a -> a.buildClassRef().getFullyQualifiedName().equals("lombok.Getter")));
  }
}
