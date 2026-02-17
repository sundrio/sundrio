
package io.sundr.it;

import org.junit.jupiter.api.Test;

public class ConnectionTest {

  @Test
  public void testConnection() throws Exception {
    Connection connection = new ConnectionBuilder()
      .withName("http")
      .withNewSpec()
         .withHostname("my-host")
        .withPort(80)
      .endSpec()
      .build();
  }
}
