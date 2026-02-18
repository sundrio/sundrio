package io.sundr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TypeDefTest {

  @Test
  public void testFQCN() throws Exception {
    TypeDef def = TypeDef.forName("java.lang.System.Logger");
    assertEquals("java.lang.System.Logger", def.getFullyQualifiedName());
  }
}
