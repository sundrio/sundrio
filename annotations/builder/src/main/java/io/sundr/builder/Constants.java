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
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;
import io.sundr.codegen.model.WildcardRef;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.codegen.functions.ClassTo.TYPEDEF;
import static io.sundr.codegen.functions.ClassTo.TYPEREF;
import static io.sundr.codegen.utils.TypeUtils.*;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;

public class Constants {

    private Constants() {}

    public static final String DEFAULT_BUILDER_PACKAGE = "io.sundr.builder";

    public static final String REPLACEABLE = "REPLACEABLE";
    public static final String MEMBER_OF = "MEMBER_OF";
    public static final String DESCENDANT_OF = "DESCENDANT_OF";
    public static final String BODY = "BODY";
    public static final String EMPTY = "";

    public static final TypeParamDef F = newTypeParamDef("F");
    public static final TypeParamDef I = newTypeParamDef("I");
    public static final TypeParamDef O = newTypeParamDef("O");
    public static final TypeParamDef B = newTypeParamDef("B");
    
    public static final TypeParamDef T = new TypeParamDefBuilder().withName("T").addToAttributes(REPLACEABLE, Boolean.TRUE).build();

    public static final TypeParamRef T_REF = new TypeParamRefBuilder().withName("T").addToAttributes(REPLACEABLE, Boolean.TRUE).build();
    
    public static final TypeParamDef N = newTypeParamDef("N");
    public static final TypeParamRef N_REF = newTypeParamRef("N");

    public static final TypeParamDef V = newTypeParamDef("V");

    public static final VoidRef VOID = new VoidRef();
    public static final WildcardRef Q = new WildcardRef();

    public static final TypeDef BOOLEAN = TYPEDEF.apply(Boolean.class);

    public static final TypeDef BUILDABLE_ANNOTATION = TYPEDEF.apply(Buildable.class);
    public static final TypeDef CLASS = TYPEDEF.apply(Class.class);
    public static final TypeDef OBJECT = TypeDef.OBJECT;
    public static final TypeDef MAP = TYPEDEF.apply(Map.class);
    public static final TypeDef LINKED_HASH_MAP = TYPEDEF.apply(LinkedHashMap.class);
    public static final TypeDef LIST = TYPEDEF.apply(List.class);
    public static final TypeDef ARRAY_LIST = TYPEDEF.apply(ArrayList.class);
    public static final TypeDef SET = TYPEDEF.apply(Set.class);
    public static final TypeDef LINKED_HASH_SET = TYPEDEF.apply(LinkedHashSet.class);

    public static final TypeDef ARRAY = TYPEDEF.apply(Array.class);
    public static final TypeDef TYPE = TYPEDEF.apply(Type.class);
    public static final TypeDef TYPE_VARIABLE = TYPEDEF.apply(TypeVariable.class);
    public static final TypeDef GENERIC_ARRAY_TYPE = TYPEDEF.apply(GenericArrayType.class);
    public static final TypeDef PARAMETERIZED_TYPE = TYPEDEF.apply(ParameterizedType.class);

    public static final TypeDef BUILDER = typeGenericOf(TYPEDEF.apply(Builder.class), T);
    public static final TypeDef BASE_FLUENT = typeGenericOf(TYPEDEF.apply(BaseFluent.class), T);
    public static final TypeDef EDITABLE = typeGenericOf(TYPEDEF.apply(Editable.class), T);
    public static final TypeDef FLUENT = typeGenericOf(TYPEDEF.apply(Fluent.class), T);
    public static final TypeDef FUNCTION = typeGenericOf(TYPEDEF.apply(Function.class), I, O);
    public static final TypeDef INLINEABLE = typeGenericOf(TYPEDEF.apply(Inlineable.class), T);
    public static final TypeDef NESTED = typeGenericOf(TYPEDEF.apply(Nested.class), N);
    public static final TypeDef VISITOR = typeGenericOf(TYPEDEF.apply(Visitor.class), V);
    public static final TypeDef TYPED_VISITOR = typeGenericOf(TYPEDEF.apply(TypedVisitor.class), V);
    public static final TypeDef VISITABLE = TYPEDEF.apply(Visitable.class);
    public static final TypeDef VISITABLE_BUILDER = typeGenericOf(TYPEDEF.apply(VisitableBuilder.class), T, V);
    public static final TypeDef BOXED_VOID = TYPEDEF.apply(Void.class);

    public static final String DEFAULT_INTERFACE_TEMPLATE_LOCATION = "templates/builder/interface.vm";
    public static final String DEFAULT_CLASS_TEMPLATE_LOCATION = "templates/builder/class.vm";
    public static final String DEFAULT_FLUENT_TEMPLATE_LOCATION = "templates/builder/fluentinterface.vm";
    public static final String DEFAULT_FLUENT_IMPL_TEMPLATE_LOCATION = "templates/builder/fluentimpl.vm";
    public static final String DEFAULT_EDITABLE_TEMPLATE_LOCATION = "templates/builder/editable.vm";
    public static final String DEFAULT_BUILDER_TEMPLATE_LOCATION = "templates/builder/builder.vm";
    public static final String VALIDATING_BUILDER_TEMPLATE_LOCATION = "templates/builder/validating-builder.vm";

    public static final String ACCEPT_VISITOR_SNIPPET = "snippets/accept-visitor.txt";
    public static final String BUILD_LIST_SNIPPET = "snippets/build-list.txt";
    public static final String CAN_VISIT_SNIPPET = "snippets/can-visit.txt";
    public static final String GET_TYPE_SNIPPET = "snippets/get-type.txt";
    public static final String GET_CLASS_SNIPPET = "snippets/get-class.txt";
    public static final String GET_TYPE_ARGUMENTS_SNIPPET = "snippets/get-type-arguments.txt";
    public static final String AGGREGATE_SET_SNIPPET = "snippets/aggregate-set.txt";
    public static final String AGGREGATE_LIST_SNIPPET = "snippets/aggregate-list.txt";
    public static final String BUILD_SET_SNIPPET = "snippets/build-set.txt";
    public static final String SIMPLE_ARRAY_GETTER_SNIPPET = "snippets/simple-array-getter.txt";
    public static final String BUILDABLE_ARRAY_GETTER_SNIPPET = "snippets/buildable-array-getter.txt";
    public static final String EMPTY_FUNCTION_SNIPPET = "snippets/empty-function.txt";

    public static Class[] PRIMITIVES = {boolean.class, byte.class, char.class, short.class, int.class, long.class, double.class, float.class};

    public static TypeRef[] PRIMITIVE_TYPES =
            {TYPEREF.apply(boolean.class),
                    TYPEREF.apply(byte.class), TYPEREF.apply(char.class),
                    TYPEREF.apply(short.class), TYPEREF.apply(int.class), TYPEREF.apply(long.class),
                    TYPEREF.apply(double.class), TYPEREF.apply(float.class)};

    public static TypeRef[] BOXED_PRIMITIVE_TYPES = {TYPEREF.apply(Boolean.class),
            TYPEREF.apply(Byte.class), TYPEREF.apply(Character.class),
            TYPEREF.apply(Short.class), TYPEREF.apply(Integer.class), TYPEREF.apply(Long.class),
            TYPEREF.apply(Double.class), TYPEREF.apply(Float.class)};

}
