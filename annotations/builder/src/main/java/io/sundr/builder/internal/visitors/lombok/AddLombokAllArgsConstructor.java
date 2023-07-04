package io.sundr.builder.internal.visitors.lombok;

import io.sundr.builder.internal.visitors.AddAllArgsConstructor;

public class AddLombokAllArgsConstructor extends AddAllArgsConstructor {

  public AddLombokAllArgsConstructor() {
    super(def -> def.hasMatchingAnnotation(a -> a.buildClassRef().getFullyQualifiedName().equals("lombok.AllArgsConstructor")));
  }
}
