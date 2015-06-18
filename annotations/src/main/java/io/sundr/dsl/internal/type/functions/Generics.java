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
                GENERIC_MAPPINGS.put(item, new JavaTypeBuilder().withClassName(name).addToAttributes(IS_GENERIC, true).build());
            }
            return GENERIC_MAPPINGS.get(item);
        }

    }, UNMAP {
        public JavaType apply(JavaType item) {
            for (Map.Entry<JavaType, JavaType> enty : GENERIC_MAPPINGS.entrySet()) {
                JavaType value = enty.getValue();
                if (value.equals(item)) {
                    return enty.getKey();
                }
            }
            return item;
        }
    }, UNWRAP {
        public JavaType apply(JavaType type) {
            Set<JavaType> interfaces = new LinkedHashSet<JavaType>();
            Set<JavaType> generics = new LinkedHashSet<JavaType>();

            if (GENERIC_MAPPINGS.containsValue(type)) {
                return UNMAP.apply(type);
            } else {
                for (JavaType iface : type.getInterfaces()) {
                    interfaces.add(UNWRAP.apply(iface));
                }
                for (JavaType generic : type.getGenericTypes()) {
                    generics.add(UNWRAP.apply(generic));
                }
                return new JavaTypeBuilder(type)
                        .withGenericTypes(generics.toArray(new JavaType[generics.size()]))
                        .withInterfaces(interfaces)
                        .build();
            }
        }
    };

    private static final String[] GENERIC_NAMES = {"X", "Y", "Z"};
    private static final Map<JavaType, JavaType> GENERIC_MAPPINGS = new HashMap<JavaType, JavaType>();
    private static int counter = 0;

    
    static {
        GENERIC_MAPPINGS.put(VOID, new JavaTypeBuilder().withClassName("V").addToAttributes(IS_GENERIC, true).build());
        GENERIC_MAPPINGS.put(TRANSPARENT, TRANSPARENT);
    }

}
