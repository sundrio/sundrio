package io.sundr.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TypeDefTest {

  @Test
  public void testFQCN() throws Exception {
    TypeDef def = TypeDef.forName("java.lang.System.Logger");
    assertEquals("java.lang.System.Logger", def.getFullyQualifiedName());
  }
}
