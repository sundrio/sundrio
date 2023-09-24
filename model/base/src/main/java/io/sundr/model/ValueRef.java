package io.sundr.model;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ValueRef implements Expression {

  private final Object value;

  public ValueRef(Object value) {
    this.value = value;
  }

  public static <T> ValueRef from(T[] value) {
    return new ValueRef(value);
  }

  public static <T> ValueRef from(T value, T... rest) {
    if (rest.length == 0) {
      return new ValueRef(value);
    }
    T[] array = (T[]) Array.newInstance(value.getClass(), rest.length + 1);
    array[0] = value;
    System.arraycopy(rest, 0, array, 1, rest.length);
    return new ValueRef(array);
  }

  public Object getValue() {
    return value;
  }

  public static String toString(Object value) {
    if (value == null) {
      return "null";
    } else if (value.getClass().isArray()) {
      int length = Array.getLength(value);
      Object[] array = new Object[length];
      for (int i = 0; i < length; i++) {
        array[i] = Array.get(value, i);
      }
      return OB + Arrays.stream(array).map(ValueRef::toString).collect(Collectors.joining(", ")) + CB;
    } else if (value instanceof Number) {
      return String.valueOf(value);
    } else if (value instanceof Boolean) {
      return String.valueOf(value);
    } else if (value instanceof String) {
      return DQ + value + DQ;
    } else if (value instanceof Property) {
      return ((Property) value).getName();
    } else if (value instanceof ClassRef) {
      return ((ClassRef) value).getFullyQualifiedName() + ".class";
    } else if (value instanceof PrimitiveRef) {
      return ((PrimitiveRef) value).getName() + ".class";
    }
    return "";

  }

  @Override
  public String render() {
    return toString(value);
  }
}
