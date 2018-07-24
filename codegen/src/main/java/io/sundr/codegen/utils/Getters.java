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
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;

import static io.sundr.codegen.Constants.BOOLEAN_REF;
import static io.sundr.codegen.utils.StringUtils.capitalizeFirst;

public class Getters {

    public static final String GET_PREFIX = "get";
    public static final String IS_PREFIX = "is";
    public static final String SHOULD_PREFIX = "should";

    public static Method findGetter(TypeDef clazz, Property property) {
        TypeDef current = clazz;
        while (current!= null && !current.equals(TypeDef.OBJECT)) {
            //1st pass strict
            for (Method method : current.getMethods()) {
                if (isApplicableGetterOf(method, property, true)) {
                    return method;
                }
            }
            //2nd pass relaxed
            for (Method method : current.getMethods()) {
                if (isApplicableGetterOf(method, property, false)) {
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

    public static boolean isGetter(Method method) {
        if (method.isPrivate() || method.isStatic()) {
            return false;
        }

        if (!method.getArguments().isEmpty()) {
            return false;
        }

        if (method.getName().startsWith(GET_PREFIX)) {
            return true;
        }

        if ((method.getName().startsWith(IS_PREFIX) || method.getName().startsWith(SHOULD_PREFIX)) &&
                (method.getReturnType().equals(BOOLEAN_REF) || method.getReturnType().toString().equals("boolean"))) {
            return true;
        }
        return false;
    }

    private static boolean isApplicableGetterOf(Method method, Property property) {
        return isApplicableGetterOf(method, property, false);
    }

    /**
    * Returns true if method is a getter of property.
    * In strict mode it will not strip non-alphanumeric characters.
    */
    private static boolean isApplicableGetterOf(Method method, Property property, boolean strict) {
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
}
