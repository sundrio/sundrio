package me.codegen.utils;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }


    public static String captializeFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public static <T> String join(Iterable<T> items, String delimiter) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (T item : items) {
            if (!first) {
                sb.append(delimiter);
            }
            sb.append(item.toString());
            first = false;
        }
        return sb.toString();
    }
}
