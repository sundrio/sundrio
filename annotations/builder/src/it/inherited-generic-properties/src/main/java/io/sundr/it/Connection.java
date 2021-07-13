
package io.sundr.it;

import io.sundr.builder.annotations.Buildable;

@Buildable
public class Connection extends Resource<ConnectionSpec> {

  private final boolean connected;

  public Connection(String name, ConnectionSpec spec, boolean connected) {
    super(name, spec);
    this.connected = connected;
  }

  public boolean isConnected() {
    return connected;
  }
}
