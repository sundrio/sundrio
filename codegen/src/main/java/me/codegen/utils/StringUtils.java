package me.codegen.utils;

import me.Function;

public final class StringUtils {

    public static final class ToString<X> implements Function<X, String> {
        @Override
        public String apply(X item) {
            return String.valueOf(item);
        }
    }
    
    
    private StringUtils() {
        //Utility Class
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static String captializeFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static <T> String join(Iterable<T> items, String delimiter) {
        return join(items, new ToString<T>(), delimiter);
    }
    
    public static <T> String join(Iterable<T> items, Function<T, String> function,String delimiter) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (T item : items) {
            if (!first) {
                sb.append(delimiter);
            }
            sb.append(function.apply(item));
            first = false;
        }
        return sb.toString();
    }

    public static <T> String join(T[] items, String delimiter) {
        return join(items, new ToString<T>(), delimiter);
    }
    
    public static <T> String join(T[] items, Function<T, String> function, String delimiter) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (T item : items) {
            if (!first) {
                sb.append(delimiter);
            }
            sb.append(function.apply(item));
            first = false;
        }
        return sb.toString();
    }
}
