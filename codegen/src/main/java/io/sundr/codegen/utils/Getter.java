/*
 *      Copyright 2018 The original authors.
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

package io.sundr.codegen.utils;

import io.sundr.SundrException;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;

import static io.sundr.codegen.Constants.BOOLEAN_REF;
import static io.sundr.codegen.utils.StringUtils.capitalizeFirst;

public class Getter {

    public static final String GET_PREFIX = "get";
    public static final String IS_PREFIX = "is";
    public static final String SHOULD_PREFIX = "should";

    public static Method find(TypeDef clazz, Property property) {
        TypeDef current = clazz;
        while (current!= null && !current.equals(TypeDef.OBJECT)) {
            //1st pass strict
            for (Method method : current.getMethods()) {
                if (isApplicable(method, property, true)) {
                    return method;
                }
            }
            //2nd pass relaxed
            for (Method method : current.getMethods()) {
                if (isApplicable(method, property, false)) {
                    return method;
                }
            }
            if (!current.getExtendsList().iterator().hasNext()) {
                break;
            }
            String fqn = current.getExtendsList().iterator().next().getDefinition().getFullyQualifiedName();
            current = DefinitionRepository.getRepository().getDefinition(fqn);
        }
        throw new SundrException("No getter found for property: " + property.getName() + " on class: " + clazz.getFullyQualifiedName());
    }

    public static boolean is(Method method) {
        int length = method.getName().length();

        if (method.isPrivate() || method.isStatic()) {
            return false;
        }

        if (!method.getArguments().isEmpty()) {
            return false;
        }

        if (method.getName().startsWith(GET_PREFIX)) {
            return length > GET_PREFIX.length();
        }

        if (method.getName().startsWith(IS_PREFIX) && TypeUtils.isBoolean(method.getReturnType())) {
            return length > IS_PREFIX.length();
        }

        if (method.getName().startsWith(SHOULD_PREFIX) && TypeUtils.isBoolean(method.getReturnType())) {
            return length > SHOULD_PREFIX.length();
        }
        return false;
    }

    private static boolean isApplicable(Method method, Property property) {
        return isApplicable(method, property, false);
    }

    /**
    * Returns true if method is a getter of property.
    * In strict mode it will not strip non-alphanumeric characters.
    */
    private static boolean isApplicable(Method method, Property property, boolean strict) {
        if (!method.getReturnType().isAssignableFrom(property.getTypeRef())) {
            return false;
        }

        String capitalized = capitalizeFirst(property.getName());
        if (method.getName().endsWith(GET_PREFIX + capitalized)) {
            return true;
        }

        if (method.getName().endsWith(IS_PREFIX + capitalized)) {
            return true;
        }

        if (!strict) {
            if (method.getName().endsWith(GET_PREFIX + property.getNameCapitalized())) {
                return true;
            }

            if (method.getName().endsWith(IS_PREFIX + property.getNameCapitalized())) {
                return true;
            }
        }
        return false;
    }

    public static final Method forProperty(Property property) {
        return new MethodBuilder()
                .withName(name(property))
                .withReturnType(property.getTypeRef())
                .withNewBlock()
                .addNewStringStatementStatement("return this." + property + ";")
                .endBlock()
                .build();
    }

    /**
     * Return the getter name for the specified {@link Property}.
     * @param property  The property.
     * @return          The name.
     */
    public static String name(Property property) {
        return prefix(property) +  property.getNameCapitalized();
    }

    /**
     * Return the property name for the specified getter {@link Method method}.
     * @param method    The method.
     * @return          The name.
     */
    public static String propertyName(Method method) {
        if (!is(method)) {
            throw new IllegalArgumentException("Method: " + method + " is not a real getter.");
        }
        String name = method.getName();

        if (name.startsWith(GET_PREFIX)) {
            return name.substring(GET_PREFIX.length());
        } else if (name.startsWith(IS_PREFIX)) {
            return name.substring(IS_PREFIX.length());
        } else if (name.startsWith(SHOULD_PREFIX)) {
            name = name.substring(SHOULD_PREFIX.length());
        } else {
            throw new IllegalStateException("Method: " + method + " is a getter but couldn't find a valid prefix.");
        }

        if (name.length() == 1) {
            return name.toUpperCase();
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    public static String prefix(Property property) {
        return TypeUtils.isBoolean(property.getTypeRef()) ? IS_PREFIX : GET_PREFIX;
    }
}
