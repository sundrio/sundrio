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

package io.sundr.builder;

import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.codegen.utils.TypeUtils.newGeneric;

public class Constants {

    public static final String DEFAULT_BUILDER_PACKAGE = "io.sundr.builder";

    public static final String BUILDABLE = "BUILDABLE";
    public static final String MEMBER_OF = "MEMBER_OF";
    public static final String BODY = "BODY";

    public static final JavaType T = newGeneric("T");
    public static final JavaType N = newGeneric("N");
    public static final JavaType VOID = new JavaTypeBuilder().withClassName("void").build();
    public static final JavaType Q = newGeneric("?");
    public static final JavaType OBJECT = ClassToJavaType.FUNCTION.apply(Object.class);
    public static final JavaType MAP = ClassToJavaType.FUNCTION.apply(Map.class);
    public static final JavaType LINKED_HASH_MAP = ClassToJavaType.FUNCTION.apply(LinkedHashMap.class);
    public static final JavaType LIST = ClassToJavaType.FUNCTION.apply(List.class);
    public static final JavaType ARRAY_LIST = ClassToJavaType.FUNCTION.apply(ArrayList.class);
    public static final JavaType SET = ClassToJavaType.FUNCTION.apply(Set.class);
    public static final JavaType LINKED_HASH_SET = ClassToJavaType.FUNCTION.apply(LinkedHashSet.class);
}
