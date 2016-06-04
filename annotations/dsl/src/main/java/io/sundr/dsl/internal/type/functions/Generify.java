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
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.dsl.internal.visitors.TypeArgumentReplace;
import io.sundr.dsl.internal.visitors.TypeParamDefReplace;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.dsl.internal.Constants.IS_GENERIC;

public class Generify {

    public static final Function<Set<ClassRef>, Set<ClassRef>> CLASSREFS = new Function<Set<ClassRef>, Set<ClassRef>>() {
        public Set<ClassRef> apply(Set<ClassRef> source) {
            Set<ClassRef> result = new LinkedHashSet<ClassRef>(source);
            Set<TypeRef> typeArguments = findTypeArguments(source);

            for (TypeRef type : typeArguments) {
                Set<ClassRef> intermediate = new LinkedHashSet<ClassRef>();
                for (ClassRef clazz : result) {
                    intermediate.add(new ClassRefBuilder(clazz).accept(new TypeArgumentReplace(type, Generics.MAP.apply(type).toReference())).build());
                }
                result = new LinkedHashSet<ClassRef>(intermediate);
            }
            return result;
        }
    };

    public static final Function<Set<TypeDef>, Set<TypeDef>> TYPEDEFS = new Function<Set<TypeDef>, Set<TypeDef>>() {
        public Set<TypeDef> apply(Set<TypeDef> source) {
            Set<TypeDef> result = new LinkedHashSet<TypeDef>(source);
            Set<TypeParamDef> typeParameters = findParameters(source);

            for (TypeParamDef type : typeParameters) {
                Set<TypeDef> intermediate = new LinkedHashSet<TypeDef>();
                for (TypeDef clazz : result) {
                    intermediate.add(new TypeDefBuilder(clazz).accept(new TypeParamDefReplace(type, Generics.MAP.apply(type.toReference()))).build());
                }
                result = new LinkedHashSet<TypeDef>(intermediate);
            }
            return result;
        }
    };


    private static Set<TypeRef> findTypeArguments(Set<ClassRef> interfaces) {
        if (interfaces.size() < 2) {
            return Collections.<TypeRef>emptySet();
        }
        //1st pass find all generics
        Set<TypeRef> allGenerics = new LinkedHashSet<TypeRef>();
        for (ClassRef clazz : interfaces) {
            allGenerics.addAll(clazz.getArguments());
        }

        //2nd pass collect common generics
        Set<TypeRef> common = new LinkedHashSet<TypeRef>(allGenerics);
        for (ClassRef clazz : interfaces) {
            Set<TypeRef> ownGenerics = new LinkedHashSet<TypeRef>();
            ownGenerics.addAll(clazz.getArguments());
            common.remove(clazz);
            common.retainAll(ownGenerics);
        }
        Set<TypeRef> result = new LinkedHashSet<TypeRef>();
        for (TypeRef type : common) {
            Boolean isGeneric = type.getAttributes().containsKey(IS_GENERIC) ?  (Boolean) type.getAttributes().get(IS_GENERIC) : false;
            if (!isGeneric) {
                result.add(type);
            }
        }
        return result;

    }


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
