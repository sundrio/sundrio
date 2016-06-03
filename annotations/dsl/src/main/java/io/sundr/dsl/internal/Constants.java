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

package io.sundr.dsl.internal;

import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.model.VoidRef;

public final class Constants {
    
    private Constants() {}

    public static final String IS_GENERATED = "IS_GENERATED";
    public static final String[] REMOVABLE_PREFIXES = {"With"};
    public static final String INTERFACE_SUFFIX = "Interface";
    public static final String ORIGINAL_RETURN_TYPE = "ORIGINAL_RETURN_TYPE";
    public static final String TERMINATING_TYPES = "TERMINATING_TYPES";
    public static final String IS_ENTRYPOINT = "IS_ENTRYPOINT";
    public static final String IS_TERMINAL = "IS_TERMINAL";
    public static final String IS_GENERIC = "IS_GENERIC";
    public static final String CLASSES = "CLASSES";
    public static final String KEYWORDS = "KEYWORDS";
    public static final String METHODS = "METHODS";
    public static final String BEGIN_SCOPE = "BEGIN_SCOPE";
    public static final String END_SCOPE = "END_SCOPE";
    public static final String IS_COMPOSITE = "IS_COMPOSITE";

    public static final String IS_TRANSITION = "IS_TRANSITION";
    public static final String CARDINALITY_MULTIPLE = "CARDINALITY_MULTIPLE";
    public static final String METHOD_NAME = "METHOD_NAME";


    public static final TypeDef VOID = ClassTo.TYPEDEF.apply(Void.class);
    public static final ClassRef VOID_REF = VOID.toInternalReference();

    public static final TypeParamDef TRANSPARENT = new TypeParamDefBuilder().withName("T").addToAttributes(IS_GENERIC, true).build();
    public static final TypeParamRef TRANSPARENT_REF = new TypeParamRefBuilder().withName("T").addToAttributes(IS_GENERIC, true).build();

    public static final String FILTER = "FILTER";

    public static final String SCOPE_SUFFIX = "Scope";
}
