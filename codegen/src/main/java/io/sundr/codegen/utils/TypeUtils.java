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

package io.sundr.codegen.utils;

import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

public final class TypeUtils {
    
    private TypeUtils() {
        //Utility Class
    }

    /**
     * Creates a new generic JavaType.
     * @param letter       The letter of the type.
     * @return
     */
    public static JavaType newGeneric(String letter) {
        return new JavaTypeBuilder().withKind(JavaKind.GENERIC).withClassName(letter).build();
    }

    /**
     * Sets one {@link io.sundr.codegen.model.JavaType} as a generic of an other.
     *
     * @param base       The base type.
     * @param generic    The generic type.
     * @return
     */
    public static JavaType typeGenericOf(JavaType base, JavaType... generic) {
        return new JavaTypeBuilder(base)
                .withGenericTypes(generic)
                .build();
    }

    /**
     * Sets one {@link io.sundr.codegen.model.JavaType} as a super class of an other.
     *
     * @param base       The base type.
     * @param superClass The super type.
     * @return
     */
    public static JavaType typeExtends(JavaType base, JavaType superClass) {
        return new JavaTypeBuilder(base)
                .withSuperClass(superClass)
                .build();
    }

}
