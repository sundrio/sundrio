
package io.sundr.it;

import io.sundr.builder.annotations.Buildable;

@Buildable
public class Resource<T> {

  private final String name;
  private final T spec;

  public Resource(String name, T spec) {
    this.name = name;
    this.spec = spec;
  }

  public String getName() {
    return name;
  }

  public T getSpec() {
    return spec;
  }
}
