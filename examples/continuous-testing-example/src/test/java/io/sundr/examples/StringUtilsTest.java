package io.sundr.examples;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilsTest {

  @Test
  public void testIsEmpty() {
    assertTrue(StringUtils.isEmpty(null));
    assertTrue(StringUtils.isEmpty(""));
    assertTrue(StringUtils.isEmpty("   "));
    assertFalse(StringUtils.isEmpty("test"));
    assertFalse(StringUtils.isEmpty(" test "));
  }

  @Test
  public void testCapitalize() {
    assertEquals("Test", StringUtils.capitalize("test"));
    assertEquals("Test", StringUtils.capitalize("TEST"));
    assertEquals("Test", StringUtils.capitalize("TeSt"));
    assertNull(StringUtils.capitalize(null));
    assertEquals("", StringUtils.capitalize(""));
  }

  @Test
  public void testReverse() {
    assertEquals("tset", StringUtils.reverse("test"));
    assertEquals("12345", StringUtils.reverse("54321"));
    assertNull(StringUtils.reverse(null));
    assertEquals("", StringUtils.reverse(""));
  }
}
