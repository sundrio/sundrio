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

import io.sundr.builder.annotations.Buildable;
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
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public class Constants {

    public static final String DEFAULT_BUILDER_PACKAGE = "io.sundr.builder";

    public static final String REPLACEABLE = "REPLACEABLE";
    public static final String BUILDABLE = "BUILDABLE";
    public static final String MEMBER_OF = "MEMBER_OF";
    public static final String DESCENDANT_OF = "DESCENDANT_OF";
    public static final String BODY = "BODY";
    public static final String EMPTY = "";

    public static final JavaType I = newGeneric("I");
    public static final JavaType O = newGeneric("O");
    public static final JavaType B = newGeneric("B");
    public static final JavaType T = new JavaTypeBuilder(newGeneric("T")).addToAttributes(REPLACEABLE, Boolean.TRUE).build();
    public static final JavaType N = newGeneric("N");
    public static final JavaType V = newGeneric("V");
    public static final JavaType VOID = new JavaTypeBuilder().withClassName("void").build();
    public static final JavaType Q = newGeneric("?");
    public static final JavaType BUILDABLE_ANNOTATION = ClassToJavaType.FUNCTION.apply(Buildable.class);
    public static final JavaType CLASS = ClassToJavaType.FUNCTION.apply(Class.class);
    public static final JavaType OBJECT = ClassToJavaType.FUNCTION.apply(Object.class);
    public static final JavaType MAP = ClassToJavaType.FUNCTION.apply(Map.class);
    public static final JavaType LINKED_HASH_MAP = ClassToJavaType.FUNCTION.apply(LinkedHashMap.class);
    public static final JavaType LIST = ClassToJavaType.FUNCTION.apply(List.class);
    public static final JavaType ARRAY_LIST = ClassToJavaType.FUNCTION.apply(ArrayList.class);
    public static final JavaType SET = ClassToJavaType.FUNCTION.apply(Set.class);
    public static final JavaType LINKED_HASH_SET = ClassToJavaType.FUNCTION.apply(LinkedHashSet.class);

    public static final JavaType BUILDER = typeGenericOf(ClassToJavaType.FUNCTION.apply(Builder.class), T);
    public static final JavaType BASE_FLUENT = typeGenericOf(ClassToJavaType.FUNCTION.apply(BaseFluent.class), T);
    public static final JavaType EDITABLE = typeGenericOf(ClassToJavaType.FUNCTION.apply(Editable.class), T);
    public static final JavaType FLUENT = typeGenericOf(ClassToJavaType.FUNCTION.apply(Fluent.class), T);
    public static final JavaType FUNCTION = typeGenericOf(ClassToJavaType.FUNCTION.apply(Function.class), I, O);
    public static final JavaType INLINEABLE = typeGenericOf(ClassToJavaType.FUNCTION.apply(Inlineable.class), T);
    public static final JavaType NESTED = typeGenericOf(ClassToJavaType.FUNCTION.apply(Nested.class), N);
    public static final JavaType VISITOR = typeGenericOf(ClassToJavaType.FUNCTION.apply(Visitor.class), V);
    public static final JavaType VISITABLE = ClassToJavaType.FUNCTION.apply(Visitable.class);
    public static final JavaType VISITABLE_BUILDER = typeGenericOf(ClassToJavaType.FUNCTION.apply(VisitableBuilder.class), T, V);
    public static final JavaType BOXED_VOID = ClassToJavaType.FUNCTION.apply(Void.class);

    public static final String DEFAULT_INTERFACE_TEMPLATE_LOCATION = "templates/builder/interface.vm";
    public static final String DEFAULT_CLASS_TEMPLATE_LOCATION = "templates/builder/class.vm";
    public static final String DEFAULT_FLUENT_TEMPLATE_LOCATION = "templates/builder/fluentinterface.vm";
    public static final String DEFAULT_FLUENT_IMPL_TEMPLATE_LOCATION = "templates/builder/fluentimpl.vm";
    public static final String DEFAULT_EDITABLE_TEMPLATE_LOCATION = "templates/builder/editable.vm";
    public static final String DEFAULT_BUILDER_TEMPLATE_LOCATION = "templates/builder/builder.vm";
    public static final String VALIDATING_BUILDER_TEMPLATE_LOCATION = "templates/builder/validating-builder.vm";

    public static final String ACCEPT_VISITOR_SNIPPET = "snippets/accept-visitor.txt";
    public static final String BUILD_LIST_SNIPPET = "snippets/build-list.txt";
    public static final String AGGREGATE_SET_SNIPPET = "snippets/aggregate-set.txt";
    public static final String AGGREGATE_LIST_SNIPPET = "snippets/aggregate-list.txt";
    public static final String BUILD_SET_SNIPPET = "snippets/build-set.txt";
    public static final String SIMPLE_ARRAY_GETTER_SNIPPET = "snippets/simple-array-getter.txt";
    public static final String BUILDABLE_ARRAY_GETTER_SNIPPET = "snippets/buildable-array-getter.txt";
    public static final String EMPTY_FUNCTION_SNIPPET = "snippets/empty-function.txt";

    public static Class[] PRIMITIVES = {boolean.class, byte.class, char.class, short.class, int.class, long.class, double.class, float.class};
    public static JavaType[] PRIMITIVE_TYPES =
            {ClassToJavaType.FUNCTION.apply(boolean.class), ClassToJavaType.FUNCTION.apply(byte.class), ClassToJavaType.FUNCTION.apply(char.class),
                    ClassToJavaType.FUNCTION.apply(short.class), ClassToJavaType.FUNCTION.apply(int.class), ClassToJavaType.FUNCTION.apply(long.class),
                    ClassToJavaType.FUNCTION.apply(double.class), ClassToJavaType.FUNCTION.apply(float.class)};


}
