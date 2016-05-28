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

package io.sundr.dsl.internal.type.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.IS_GENERIC;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.Constants.VOID;

public enum Generics implements Function<JavaType, JavaType> {

    MAP {
        public JavaType apply(JavaType item) {
            if (!GENERIC_MAPPINGS.containsKey(item)) {
                int iteration = counter / GENERIC_NAMES.length;
                String name = GENERIC_NAMES[counter % GENERIC_NAMES.length];
                if (iteration > 0) {
                    name += iteration;
                }
                counter++;
                GENERIC_MAPPINGS.put(item, new JavaTypeBuilder().withName(name).addToAttributes(IS_GENERIC, true).build());
            }
            return GENERIC_MAPPINGS.get(item);
        }

    }, UNMAP {
        public JavaType apply(JavaType item) {
            if (containsValue(GENERIC_MAPPINGS, item)) {
                return getKeyForValue(GENERIC_MAPPINGS, item);
            } else {
                return item;
            }
        }
    }, UNWRAP {
        public JavaType apply(JavaType type) {
           return unwrap(type, new LinkedHashSet<String>());
        }
    };

    private static final String[] GENERIC_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"};
    private static final Map<JavaType, JavaType> GENERIC_MAPPINGS = new HashMap<JavaType, JavaType>();
    private static int counter = 0;

    private static JavaType unwrap(JavaType type, Set<String> visited) {
        //Try to detect circles...
        Set<String> path = new LinkedHashSet<String>(visited);
        path.add(type.getFullyQualifiedName());
        //We use containsValue that is not using equals, but getFullyQualifiedName instead.
        //This is needed to prevent possible infinite loops
        if (containsValue(GENERIC_MAPPINGS, type)) {
            JavaType unmapped = UNMAP.apply(type);
            if (!unmapped.equals(type) && !path.contains(unmapped)) {
                return unwrap(unmapped, path);
            }
            return unmapped;
        } else {
            Set<JavaType> interfaces = new LinkedHashSet<JavaType>();
            Set<JavaType> generics = new LinkedHashSet<JavaType>();

            for (JavaType iface : type.getInterfaces()) {
                if (!path.contains(iface.getFullyQualifiedName())) {
                    interfaces.add(unwrap(iface, path));
                }
            }
            for (JavaType generic : type.getGenericTypes()) {
                if (!path.contains(generic.getFullyQualifiedName())) {
                    generics.add(unwrap(generic, path));
                }
            }
            return new JavaTypeBuilder(type)
                    .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                    .withInterfaces(interfaces)
                    .build();
        }
    }

    private static boolean containsValue(Map<JavaType, JavaType> map, JavaType value) {
        for (Map.Entry<JavaType,JavaType> entry : map.entrySet()) {
            if (value.getFullyQualifiedName().equals(entry.getValue().getFullyQualifiedName())) {
                return true;
            }
        }
        return false;
    }

    private static JavaType getKeyForValue(Map<JavaType, JavaType> map, JavaType value) {
        for (Map.Entry<JavaType,JavaType> entry : map.entrySet()) {
            if (value.getFullyQualifiedName().equals(entry.getValue().getFullyQualifiedName())) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Key not found for value:" + value.getFullyQualifiedName());
    }
    
    static {
        GENERIC_MAPPINGS.put(VOID, new JavaTypeBuilder().withName("V").addToAttributes(IS_GENERIC, true).build());
        GENERIC_MAPPINGS.put(TRANSPARENT, TRANSPARENT);
    }
}
