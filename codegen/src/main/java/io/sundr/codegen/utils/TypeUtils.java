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

import io.sundr.Function;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.ClassRefBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeParamRefBuilder;
import io.sundr.codegen.model.TypeRef;

import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class TypeUtils {
    
    private TypeUtils() {
        //Utility Class
    }

    /**
     * Checks if a {@link TypeDef} is an instance of an other {@link TypeDef}.
     * @param type          The type to compare.
     * @param targetType    The target type.
     * @param function
     * @return  true if match, false otherwise.
     */
    public static boolean isInstanceOf(TypeRef type, TypeDef targetType, Function<TypeRef, Boolean> function) {
        if (type instanceof ClassRef) {
            ClassRef classRef = (ClassRef) type;
            TypeDef definition = classRef.getDefinition();
            if (type.equals(targetType)) {
                return true;
            }

            for (TypeRef i : definition.getImplementsList()) {
                if (function.apply(i)) {
                    return true;
                }
            }

            for (TypeRef e : definition.getExtendsList()) {
                if (function.apply(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Creates a new generic TypeParamRef.
     * @param letter       The letter of the type.
     * @return
     */
    public static TypeParamRef newTypeParamRef(String letter) {
        return new TypeParamRefBuilder().withName(letter).build();
    }

    /**
     * Creates a new TypeParamDef.
     * @param letter       The letter of the type.
     * @return
     */
    public static TypeParamDef newTypeParamDef(String letter) {
        return new TypeParamDefBuilder().withName(letter).build();
    }

    /**
     * Create a {@link ClassRef} for the specified {@link TypeDef}.
     * @param typeDef
     * @return
     */
    public static ClassRef classRefOf(TypeDef typeDef, Object... arguments) {
        List<TypeRef> acutalArguments = new ArrayList<TypeRef>();
        for (Object a : arguments) {
            if (a instanceof TypeRef) {
                acutalArguments.add((TypeRef)a);
            } else if (a instanceof TypeDef) {
                acutalArguments.add(classRefOf((TypeDef)a));
            }
        }
        return new ClassRefBuilder()
                .withDefinition(typeDef)
                .withArguments(acutalArguments)
                .build();
    }


    /**
     * Removes parameters from a TypeDef.
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


    public static int modifiersToInt(Modifier... modifiers) {
        return modifiersToInt(Arrays.asList(modifiers));
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
