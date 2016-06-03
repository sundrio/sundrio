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
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.dsl.internal.visitors.TypeParamDefReplace;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.IS_GENERIC;

public class Generify {

    public static final Function<Set<TypeDef>, Set<TypeDef>> TYPEDEFS = new Function<Set<TypeDef>, Set<TypeDef>>() {
        public Set<TypeDef> apply(Set<TypeDef> source) {
            Set<TypeDef> result = new LinkedHashSet<TypeDef>(source);
            Set<TypeParamDef> generifiableTypes = findParameters(source);

            for (TypeParamDef type : generifiableTypes) {
                Set<TypeDef> intermediate = new LinkedHashSet<TypeDef>();
                for (TypeDef clazz : result) {
                    intermediate.add(new TypeDefBuilder(clazz).accept(new TypeParamDefReplace(type, Generics.MAP.apply(type.toReference()))).build());
                }
                result = new LinkedHashSet<TypeDef>(intermediate);
            }
            return result;
        }
    };


    private static Set<TypeParamDef> findParameters(Set<TypeDef> interfaces) {
        if (interfaces.size() < 2) {
            return Collections.<TypeParamDef>emptySet();
        }
        //1st pass find all generics
        Set<TypeParamDef> allGenerics = new LinkedHashSet<TypeParamDef>();
        for (TypeDef clazz : interfaces) {
            allGenerics.addAll(clazz.getParameters());
        }

        //2nd pass collect common generics
        Set<TypeParamDef> common = new LinkedHashSet<TypeParamDef>(allGenerics);
        for (TypeDef clazz : interfaces) {
            Set<TypeParamDef> ownGenerics = new LinkedHashSet<TypeParamDef>();
            ownGenerics.addAll(clazz.getParameters());
            common.remove(clazz);
            common.retainAll(ownGenerics);
        }
        Set<TypeParamDef> result = new LinkedHashSet<TypeParamDef>();
        for (TypeParamDef type : common) {
            Boolean isGeneric = type.getAttributes().containsKey(IS_GENERIC) ?  (Boolean) type.getAttributes().get(IS_GENERIC) : false;
            if (!isGeneric) {
                result.add(type);
            }
        }
        return result;

    }
}
