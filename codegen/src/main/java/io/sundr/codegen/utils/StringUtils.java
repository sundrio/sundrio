/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.codegen.utils;

import io.sundr.Function;

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

    public static String singularize(String str) {
        //TODO: Apparently this needs a lot of work.
        if (str.endsWith("ies")) {
            return str.substring(0, str.length() - 3) + "y";
        } else if (str.endsWith("s") && str.length() > 1) {
            return str.substring(0, str.length() - 1);
        }
        return str;
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
