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

import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;

import javax.lang.model.element.Modifier;
import java.util.Collection;

public final class TypeUtils {
    
    private TypeUtils() {
        //Utility Class
    }

    /**
     * Creates a new generic JavaType.
     * @param letter       The letter of the type.
     * @return
     */
    public static TypeParamRef newTypeParamRef(String letter) {
        return new TypeParamRefBuilder().withName(letter).build();
    }

    /**
     * Creates a new generic JavaType.
     * @param letter       The letter of the type.
     * @return
     */
    public static TypeParamDef newTypeParamDef(String letter) {
        return new TypeParamDefBuilder().withName(letter).build();
    }

    /**
     * Removes a new generic JavaType.
     * @param base       The base type.
     * @return
     */
    public static TypeDef unwrapGeneric(TypeDef base) {
        return new TypeDefBuilder(base).withParameters().build();
    }

    /**
     * Sets one {@link io.sundr.codegen.model.TypeDef} as a generic of an other.
     *
     * @param base          The base type.
     * @param parameters    The parameter types.
     * @return
     */
    public static TypeDef typeGenericOf(TypeDef base, TypeParamDef... parameters) {
        return new TypeDefBuilder(base)
                .withParameters(parameters)
                .build();
    }

    /**
     * Sets one {@link io.sundr.codegen.model.TypeDef} as a super class of an other.
     *
     * @param base       The base type.
     * @param superClass The super type.
     * @return
     */
    public static TypeDef typeExtends(TypeDef base, ClassRef superClass) {
        return new TypeDefBuilder(base)
                .withExtendsList(superClass)
                .build();
    }

    /**
     * Sets one {@link io.sundr.codegen.model.TypeDef} as an interface of an other.
     *
     * @param base       The base type.
     * @param superClass The super type.
     * @return
     */
    public static TypeDef typeImplements(TypeDef base, ClassRef... superClass) {
        return new TypeDefBuilder(base)
                .withImplementsList(superClass)
                .build();
    }


    public static int modifiersToInt(Collection<Modifier> modifiers) {
        int result = 0;

        for (Modifier m : modifiers) {
            switch (m) {
                case ABSTRACT:
                    result = result | java.lang.reflect.Modifier.ABSTRACT;
                    break;
                case FINAL:
                    result = result | java.lang.reflect.Modifier.FINAL;
                    break;
                case NATIVE:
                    result = result | java.lang.reflect.Modifier.NATIVE;
                    break;
                case PRIVATE:
                    result = result | java.lang.reflect.Modifier.PRIVATE;
                    break;
                case PROTECTED:
                    result = result | java.lang.reflect.Modifier.PROTECTED;
                    break;
                case PUBLIC:
                    result = result | java.lang.reflect.Modifier.PUBLIC;
                    break;
                case STATIC:
                    result = result | java.lang.reflect.Modifier.STATIC;
                    break;
                case SYNCHRONIZED:
                    result = result | java.lang.reflect.Modifier.SYNCHRONIZED;
                    break;
                case TRANSIENT:
                    result = result | java.lang.reflect.Modifier.TRANSIENT;
                    break;
            }
        }

        return result;
    }

}
