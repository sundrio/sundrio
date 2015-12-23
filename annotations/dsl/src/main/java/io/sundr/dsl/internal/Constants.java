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

import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

public final class Constants {

    public static final String INTERFACE_SUFFIX = "Interface";
    public static final String ORIGINAL_RETURN_TYPE = "ORIGINAL_RETURN_TYPE";
    public static final String TERMINATING_TYPES = "TERMINATING_TYPES";
    public static final String IS_ENTRYPOINT = "IS_ENTRYPOINT";
    public static final String IS_TERMINAL = "IS_TERMINAL";
    public static final String IS_GENERIC = "IS_GENERIC";
    public static final String KEYWORDS = "KEYWORDS";
    public static final String IS_COMPOSITE = "IS_COMPOSITE";
    public static final String CARDINALITY_MULTIPLE = "CARDINALITY_MULTIPLE";
    public static final String METHOD_NAME = "METHOD_NAME";

    public static final JavaType VOID = new JavaTypeBuilder().withClassName("Void").build();
    public static final JavaType TRANSPARENT = new JavaTypeBuilder().withClassName("T").addToAttributes(IS_GENERIC, true).build();

    public static final String REQUIRES_ALL = "REQUIRES_ALL";
    public static final String REQUIRES_ANY = "REQUIRES_ANY";
    public static final String NONE = "NONE";
}
