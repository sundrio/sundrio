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
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.dsl.annotations.Keyword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.IS_GENERIC;

public enum Generify implements Function<Set<JavaClazz>, Set<JavaClazz>> {

    FUNCTION;

    @Override
    public Set<JavaClazz> apply(Set<JavaClazz> source) {
        Set<JavaClazz> result = new LinkedHashSet<JavaClazz>(source);
        Set<JavaType> generifiableTypes = findGenerifiables(source);

        for (JavaType type : generifiableTypes) {
            Set<JavaClazz> intermediate = new LinkedHashSet<JavaClazz>();
            for (JavaClazz clazz : result) {
                intermediate.add(new JavaClazzBuilder(clazz).withType(replaceGenericWith(clazz.getType(), type, Generics.MAP.apply(type))).build());
            }
            result = new LinkedHashSet<JavaClazz>(intermediate);
        }
        return result;
    }


    private static Set<JavaType> findGenerifiables(Set<JavaClazz> interfaces) {
        if (interfaces.size() < 2) {
            return Collections.<JavaType>emptySet();
        }
        //1st pass find all generics
        Set<JavaType> allGenerics = new LinkedHashSet<JavaType>();
        for (JavaClazz clazz : interfaces) {
            popullateGenericTypes(clazz.getType(), allGenerics);
        }

        //2nd pass collect common generics
        Set<JavaType> common = new LinkedHashSet<JavaType>(allGenerics);
        for (JavaClazz clazz : interfaces) {
            Set<JavaType> ownGenerics = new LinkedHashSet<JavaType>();
            popullateGenericTypes(clazz.getType(), ownGenerics);
            common.remove(clazz.getType());
            common.retainAll(ownGenerics);
        }
        Set<JavaType> result = new LinkedHashSet<JavaType>();
        for (JavaType type : common) {
            Boolean isGeneric = type.getAttributes().containsKey(IS_GENERIC) ?  (Boolean) type.getAttributes().get(IS_GENERIC) : false;
            if (!isGeneric) {
                result.add(type);
            }
        }
        return result;
    }


    private static void popullateGenericTypes(JavaType type, Set<JavaType> result) {
        for (JavaType generic : type.getGenericTypes()) {
            popullateGenericTypes(generic, result);
        }
        result.add(type);
    }

    private JavaType replaceGenericWith(JavaType source, JavaType from, JavaType to) {
        List<JavaType> generics = Arrays.asList(source.getGenericTypes());
        if (generics.isEmpty()) {
            return source;
        } else if (source.equals(from)) {
            return to;
        } else {
            List<JavaType> updatedGenerics = new ArrayList<JavaType>();
            for (JavaType generic : generics) {
                updatedGenerics.add(replaceGenericWith(generic, from, to));
            }
            return new JavaTypeBuilder(source).withGenericTypes(updatedGenerics.toArray(new JavaType[generics.size()])).build();
        }
    }
}
