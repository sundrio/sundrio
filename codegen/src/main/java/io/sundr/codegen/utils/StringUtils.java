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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class StringUtils {

    private static final String SPLITTER_REGEX = "(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])";

    private static final List<String> KEYWORDS = Arrays.asList("abstract", "continue", "for", "new", "switch", "assert", "default", "goto", "package", "synchronized", "boolean", "do", "if", "private", "this", "break", "double", "implements", "protected", "throw", "byte", "else", "import", "public", "throws", "case", "enum", "instanceof", "return", "transient", "catch", "extends", "int", "short", "try", "char", "final", "interface", "static", "void", "class", "finally", "long", "strictfp", "volatile", "const", "float", "native", "super", "while");


    public static final class ToString<X> implements Function<X, String> {
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
        InputStream is = null;
        BufferedReader in = null;
        try {
            is = StringUtils.class.getClassLoader().getResourceAsStream(resourceName);
            in = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } finally {
            if (in != null) {
                in.close();
            }
            if (is != null) {
                is.close();
            }
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
        InputStream is = null;
        BufferedReader in = null;
        try {
            is = resourceUrl.openStream();
            in = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = in.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } finally {
            if (in != null) {
                in.close();
            }
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * Remove repeating strings that are appearing in the name.
     * This is done by splitting words (camel case) and using each word once.
     * @param name  The name to compact.
     * @return      The compact name.
     */
    public static final String compact(String name) {
        Set<String> parts = new LinkedHashSet<String>();
        for (String part : name.split(SPLITTER_REGEX)) {
            parts.add(part);
        }
        return join(parts,"");
    }


    /**
     * Adds an underscore to the specified String, if its a Java Keyword.
     * @param name  The specified string.
     * @return      The specified string if not a keyword, the string prefixed with underscore otherwise.
     */
    public static final String prefixKeywords(String name) {
        if (KEYWORDS.contains(name)) {
            return "_" + name;
        }
        else return name;
    }

    /**
     * Converts the string into a safe field name.
     * @param name  The field name.
     * @return      The safe field name.
     */
    public static final String toFieldName(String name) {
        return compact(prefixKeywords(name));
    }


    /**
     * Converts a name of an interface or abstract class to Pojo name.
     * Remove leading "I" and "Abstract" or trailing "Interface".
     * @param name      The name to convert.
     * @param prefix    The prefix to use if needed.
     * @param suffix    The suffix to user if needed.
     * @return          The converted name, if a conversion actually happened or the original name prefixed and suffixed otherwise.
     */
    public static final String toPojoName(String name, String prefix, String suffix) {
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(name.split(SPLITTER_REGEX)));
        if (parts.isEmpty()) {
            return prefix + name + suffix;
        }

        if (parts.getFirst().equals("I")) {
            parts.removeFirst();
        }

        if (parts.getFirst().equals("Abstract")) {
            parts.removeFirst();
        }

        if (parts.getLast().equals("Interface")) {
            parts.removeLast();
        }

        String candidate = join(parts, "");
        if (name.equals(candidate)) {
            return prefix + name + suffix;
        }

        return candidate;
    }
}
