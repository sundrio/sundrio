/*
 *      Copyright 2016 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.codegen.functions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.sundr.FunctionFactory;
import io.sundr.Function;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.TypeUtils;

import static io.sundr.codegen.functions.ClassTo.TYPEDEF;

public class Collections {

    public static final TypeDef MAP = TYPEDEF.apply(Map.class);
    public static final TypeDef LINKED_HASH_MAP = TYPEDEF.apply(LinkedHashMap.class);
    public static final TypeDef LIST = TYPEDEF.apply(List.class);
    public static final TypeDef ARRAY_LIST = TYPEDEF.apply(ArrayList.class);
    public static final TypeDef SET = TYPEDEF.apply(Set.class);
    public static final TypeDef LINKED_HASH_SET = TYPEDEF.apply(LinkedHashSet.class);    public static final Function<TypeRef, Boolean> IS_LIST = FunctionFactory.cache(new Function<TypeRef, Boolean>() {

    public Boolean apply(TypeRef type) {
           return TypeUtils.isInstanceOf(type, LIST, IS_LIST);
        }
    });

    public static final Function<TypeRef, Boolean> IS_SET = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
        public Boolean apply(TypeRef type) {
            return TypeUtils.isInstanceOf(type, SET, IS_SET);
        }
    });

    public static final Function<TypeRef, Boolean> IS_MAP = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
        public Boolean apply(TypeRef type) {
            return TypeUtils.isInstanceOf(type, MAP, IS_MAP);
        }
    });

    public static final Function<TypeRef, Boolean> IS_COLLECTION = FunctionFactory.cache(new Function<TypeRef, Boolean>() {
        public Boolean apply(TypeRef type) {
            return IS_LIST.apply(type) || IS_SET.apply(type);
        }
    });

}
