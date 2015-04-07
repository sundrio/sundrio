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

package io.sundr.dsl.internal.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.IS_COMPOSITE;
import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TERMINATING_TYPES;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.getTerminatingTypes;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.isGeneric;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.stripSuffix;
import static io.sundr.dsl.internal.processor.JavaTypeUtils.toInterfaceName;

public class Combination {

    public static JavaClazz create(JavaClazz... alternatives) {
        return create(Arrays.asList(alternatives));
    }
    
    public static JavaClazz create(Collection<JavaClazz> alternatives) {
        Set<JavaType> genericTypes = new LinkedHashSet<>();
        Set<JavaType> interfaces = new LinkedHashSet<>();
        Set<JavaType> terminatingTypes = new LinkedHashSet<>();
        
        JavaClazz fallback = null;
        for (JavaClazz alternative : alternatives) {
            if (!canBeExcluded(alternative, alternatives)) {
                interfaces.add(alternative.getType());
                terminatingTypes.addAll(getTerminatingTypes(alternative.getType()));
                for (JavaType candidate : alternative.getType().getGenericTypes()) {
                    if (isGeneric(candidate)) {
                        genericTypes.add(candidate);
                    }
                }
            } else {
                if (fallback == null) {
                    fallback = alternative;    
                } else if (canBeExcluded(fallback, Arrays.asList(alternative))) {
                    fallback = alternative;
                }
            }
        }
        if (interfaces.isEmpty()) {
            interfaces.add(fallback.getType());
            terminatingTypes.addAll(getTerminatingTypes(fallback.getType()));
            for (JavaType candidate : fallback.getType().getGenericTypes()) {
                if (isGeneric(candidate)) {
                    genericTypes.add(candidate);
                }
            }
        }

        String className = classNameOf(interfaces);
        
        return new JavaClazzBuilder()
                .addType()
                .withKind(JavaKind.INTERFACE)
                .withClassName(className)
                .withPackageName(interfaces.iterator().next().getPackageName())
                .withInterfaces(interfaces)
                .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()]))
                .addToAttributes(ORIGINAL_RETURN_TYPE, TRANSPARENT)
                .addToAttributes(TERMINATING_TYPES, terminatingTypes)
                .addToAttributes(IS_TERMINAL, false)
                .addToAttributes(IS_COMPOSITE, false)
                .endType()
                .build();

    }

    private static final String classNameOf(Set<JavaType> types) {
        return toInterfaceName(StringUtils.join(types, new Function<JavaType, String>() {
            @Override
            public String apply(JavaType item) {
                return item.getClassName();
            }
        }, "Or"));
    }
    
            
    private static boolean canBeExcluded(JavaClazz candidate, Iterable<JavaClazz> provided) {
        Set<JavaType> allOther = new LinkedHashSet<>();
        for (JavaClazz c : provided) {
            if (!c.equals(candidate)) {
                allOther.addAll(extractInterfaces(c.getType()));
            }
        }

        Set<JavaType> allProvided = extractInterfaces(allOther);
        for (JavaType type : extractInterfaces(candidate.getType())) {
            if (!allProvided.contains(type)) {
                return false;
            }
        }
        return true;
    }
    
    private static Set<JavaType> extractInterfaces(Set<JavaType> types) {
        Set<JavaType> result = new LinkedHashSet<>();
        for (JavaType type : types) {
            result.addAll(extractInterfaces(type));
        }
        return result;
    }
    
    private static Set<JavaType> extractInterfaces(JavaType type) {
        Set<JavaType> result = new LinkedHashSet<>();
        if (type.getInterfaces().isEmpty()) {
            result.add(type);
        } else {
            for (JavaType interfaceType : type.getInterfaces()) {
                result.addAll(extractInterfaces(interfaceType));
            }
        }
        return result;
    }
}
