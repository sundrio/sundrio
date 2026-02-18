package io.sundr.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ValueRefTest {

  @Test
  public void testStringValue() {
    String value = "value";
    ValueRef ref = new ValueRef(value);
    assertEquals("\"" + value + "\"", ref.render());
  }

  @Test
  public void testNumberValue() {
    int value = 1;
    ValueRef ref = new ValueRef(value);
    assertEquals("1", ref.render());
  }
}
