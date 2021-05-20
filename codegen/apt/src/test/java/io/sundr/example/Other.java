package io.sundr.example;

public class Other {
  public static final String FOO_NAME = "foo";
  public static final String SOMETHING_NAME = "myName";

  @TestAnnotation(name = FOO_NAME)
  private String foo;

  @TestAnnotation(name = SOMETHING_NAME, values = { 1, 2, 3, 5, 7 })
  private void something() {
  }
}
