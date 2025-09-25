package io.sundr.examples;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {

  @Test
  public void testAdd() {
    Calculator calculator = new Calculator();
    assertEquals(5, calculator.add(2, 3));
    assertEquals(0, calculator.add(-1, 1));
    assertEquals(-5, calculator.add(-2, -3));
  }

  @Test
  public void testSubtract() {
    Calculator calculator = new Calculator();
    assertEquals(-1, calculator.subtract(2, 3));
    assertEquals(0, calculator.subtract(1, 1));
    assertEquals(5, calculator.subtract(3, -2));
  }

  @Test
  public void testMultiply() {
    Calculator calculator = new Calculator();
    assertEquals(6, calculator.multiply(2, 3));
    assertEquals(0, calculator.multiply(0, 5));
    assertEquals(-6, calculator.multiply(-2, 3));
  }

  @Test
  public void testDivide() {
    Calculator calculator = new Calculator();
    assertEquals(2.0, calculator.divide(6, 3), 0.001);
    assertEquals(0.5, calculator.divide(1, 2), 0.001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDivideByZero() {
    Calculator calculator = new Calculator();
    calculator.divide(5, 0);
  }
}
