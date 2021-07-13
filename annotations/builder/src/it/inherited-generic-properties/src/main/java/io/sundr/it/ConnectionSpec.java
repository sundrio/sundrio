
package io.sundr.it;

import io.sundr.builder.annotations.Buildable;

@Buildable
public class ConnectionSpec {

  private final String hostname;
  private final int port;

  public ConnectionSpec(String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

}
