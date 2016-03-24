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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

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

    public static String deCaptializeFirst(String str) {
        return str.substring(0, 1).toLowerCase() + str.substring(1);
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

    public static <T> String join(Iterable<T> items, Function<T, String> function, String delimiter) {
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

    public static <T> String getPrefix(Iterable<T> items, Function<T, String> function) {
        int length = 0;
        String prefix = "";
        String current = "";

        while (true) {
            boolean first = true;
            for (T item : items) {
                String currnetItem = function.apply(item);
                if (first) {
                    first = false;
                    if (currnetItem.length() > length) {
                        current = currnetItem.substring(0, length);
                    } else {
                        return prefix;
                    }
                } else if (!currnetItem.startsWith(current)) {
                    return prefix;
                }
            }
            length++;
            prefix = current;
        }
    }

    public static String getPrefix(Iterable<String> items) {
        return getPrefix(items, new Function<String, String>() {
            @Override
            public String apply(String item) {
                return item;
            }
        });
    }

    public static final String loadResourceQuietly(String resourceName) {
        try {
            return loadResource(resourceName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadResource(String resourceName) throws IOException {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(
                StringUtils.class.getClassLoader().getResourceAsStream(resourceName)))) {
            
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }

    public static final String loadResourceQuietly(URL resourceUrl) {
        try {
            return loadResource(resourceUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String loadResource(URL resourceUrl) throws IOException {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(resourceUrl.openStream()))) {
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        }
    }
}
