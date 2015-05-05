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

package io.sundr.builder.internal.utils;

import io.sundr.builder.internal.functions.PropertyAs;
import io.sundr.codegen.model.AttributeSupport;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.utils.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class BuilderUtils {

    public static final String BUILDABLE = "BUILDABLE";

    public static JavaMethod findBuildableConstructor(JavaClazz clazz) {
        for (JavaMethod candidate : clazz.getConstructors()) {
            if (candidate.getArguments().length != 0) {
                return candidate;
            }
        }
        return clazz.getConstructors().iterator().next();
    }

    public static JavaMethod findGetter(JavaClazz clazz, JavaProperty property) {
        for (JavaMethod method : clazz.getMethods()) {
            if (isApplicableGetterOf(method, property)) {
                return method;
            }
        }
        return null;
    }


    private static boolean isApplicableGetterOf(JavaMethod method, JavaProperty property) {
        if (!method.getReturnType().isAssignable(property.getType())) {
            return false;
        }

        if (method.getName().endsWith("get" + property.getNameCapitalized())) {
            return true;
        }

        if (method.getName().endsWith("is" + property.getNameCapitalized())) {
            return true;
        }
        return false;
    }

    /**
     * Checks if there is a default constructor available.
     * @param item  The clazz to check.
     * @return
     */
    public static boolean hasDefaultConstructor(JavaClazz item) {
        if (item == null) {
            return false;
        } else if (item.getConstructors().isEmpty()) {
            return true;
        } else {
            for (JavaMethod constructor : item.getConstructors()) {
                if (constructor.getArguments().length == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if {@link io.sundr.codegen.model.JavaType} has the BUILDABLE attribute set to true.
     *
     * @param item The type to check.
     * @return
     */
    public static boolean isBuildable(AttributeSupport item) {
        if (item == null) {
            return false;
        } else if (item.getAttributes().containsKey(BUILDABLE)) {
            return (Boolean) item.getAttributes().get(BUILDABLE);
        }
        return false;
    }

    public static Set<JavaMethod> getInlineableConstructors(JavaProperty property) {
        Set<JavaMethod> result = new HashSet<>();
        JavaClazz clazz = PropertyAs.CLASS.apply(property);
        for (JavaMethod candidate : clazz.getConstructors()) {
            if (isInlineable(candidate)) {
                result.add(candidate);
            }
        }
        return result;
    }

    public static boolean isInlineable(JavaMethod method) {
        if (method.getArguments().length == 0 || method.getArguments().length > 5) {
            return false;
        }
        
        for (JavaProperty argument : method.getArguments()) {
            if (StringUtils.isNullOrEmpty(argument.getType().getPackageName())) {
                continue;
            } else if (argument.getType().getPackageName().startsWith("java.lang")) {
                continue;
            } else {
                return false;
            } 
        }
        return true;
    }
}
