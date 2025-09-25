package io.sundr.examples;

public class StringUtils {

  public static boolean isEmpty(String str) {
    return str == null || str.trim().isEmpty();
  }

  public static String capitalize(String str) {
    if (isEmpty(str)) {
      return str;
    }
    return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
  }

  public static String reverse(String str) {
    if (isEmpty(str)) {
      return str;
    }
    return new StringBuilder(str).reverse().toString();
  }
}