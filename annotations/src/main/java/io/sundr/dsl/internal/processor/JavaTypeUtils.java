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

package io.sundr.dsl.internal.processor;

import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaMethodBuilder;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.ModelUtils;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.TargetName;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.dsl.internal.functions.Generics;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ExecutableElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.codegen.utils.StringUtils.captializeFirst;
import static io.sundr.dsl.internal.Constants.INTERFACE_SUFFIX;
import static io.sundr.dsl.internal.Constants.IS_COMPOSITE;
import static io.sundr.dsl.internal.Constants.IS_ENTRYPOINT;
import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.METHOD_NAME;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TERMINATING_TYPES;
import static io.sundr.dsl.internal.Constants.TRANSITIONS;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.Constants.VOID;
import static io.sundr.dsl.internal.Constants.TRANSITIONS;

public final class JavaTypeUtils {

    private JavaTypeUtils() {
        //Utility Class
    }

    /**
     * Convert an {@link javax.lang.model.element.ExecutableElement} to a {@link io.sundr.codegen.model.JavaClazz}
     *
     * @param context           The context of the operation.
     * @param executableElement The target element.
     * @return An instance of {@link io.sundr.codegen.model.JavaClazz} that describes the interface.
     */
    public static JavaClazz executableToInterface(DslProcessorContext context, ExecutableElement executableElement) {
        //Do generate the interface
        String methodName = executableElement.getSimpleName().toString();
        Boolean isEntryPoint = executableElement.getAnnotation(EntryPoint.class) != null;
        Boolean isTerminal = executableElement.getAnnotation(Terminal.class) != null
                || !isVoid(executableElement);

        Set<String> transitions = new LinkedHashSet<>();
        Set<String> keywords = new LinkedHashSet<>();
        for (AnnotationMirror annotationMirror : context.getToTransitionAnnotations().apply(executableElement)) {
            transitions.add(context.getToTransitionClassName().apply(annotationMirror));
        }

        for (AnnotationMirror annotationMirror : context.getToKeywordAnnotations().apply(executableElement)) {
            keywords.add(context.getToKeywordClassName().apply(annotationMirror));
        }

        JavaType returnType = null;
        
        if (isTerminal(executableElement)) {
            returnType = isVoid(executableElement) ?
                    VOID :
                    context.getToType().apply(executableElement.getReturnType().toString());
        } else {
            returnType = TRANSPARENT;
        }

        JavaType genericType = Generics.MAP.apply(returnType);

        JavaMethod sourceMethod = context.getToMethod().apply(executableElement);
        JavaMethod targetMethod = new JavaMethodBuilder(sourceMethod).withReturnType(genericType).build();

        TargetName targetInterfaceName = executableElement.getAnnotation(TargetName.class);
        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(targetMethod.getName());

        return new JavaClazzBuilder()
                .addType()
                .withPackageName(ModelUtils.getPackageElement(executableElement).toString())
                .withClassName(interfaceName)
                .addToGenericTypes(genericType)
                .withKind(JavaKind.INTERFACE)
                .addToAttributes(ORIGINAL_RETURN_TYPE, returnType)
                .addToAttributes(IS_ENTRYPOINT, isEntryPoint)
                .addToAttributes(IS_TERMINAL, isTerminal)
                .addToAttributes(KEYWORDS, keywords)
                .addToAttributes(TRANSITIONS, isTerminal ? Collections.emptySet() : transitions)
                .addToAttributes(TERMINATING_TYPES, isTerminal ? new LinkedHashSet<>(Arrays.asList(returnType)) : Collections.emptySet())
                .addToAttributes(IS_COMPOSITE, false)
                .addToAttributes(METHOD_NAME, methodName)
                .endType()
                .addToMethods(targetMethod)
                .build();
    }


    public static JavaType merge(JavaType left, JavaType right) {
        JavaTypeBuilder builder = new JavaTypeBuilder(left);
        for (JavaType type : right.getInterfaces()) {
            builder = builder.addToInterfaces(type);
        }

        for (JavaType type : right.getGenericTypes()) {
            if (!Arrays.asList(left.getGenericTypes()).contains(type)) {
                builder = builder.addToGenericTypes(type);
            }
        }
        return builder.build();
    }

    public static JavaClazz merge(JavaClazz left, JavaClazz right) {
        JavaType mergedType = merge(left.getType(), right.getType());

        JavaClazzBuilder builder = new JavaClazzBuilder(left).withType(mergedType);
        for (JavaMethod constructor : right.getConstructors()) {
            builder = builder.addToConstructors(constructor);
        }

        for (JavaMethod method : right.getMethods()) {
            builder = builder.addToMethods(method);
        }
        for (JavaProperty property : right.getFields()) {
            builder = builder.addToFields(property);
        }
        return builder.build();
    }

    public static final JavaClazz unwrapGenerics(JavaClazz clazz) {
        if (clazz.getType().getGenericTypes().length == 0) {
            return clazz;
        } else if (clazz.getType().getGenericTypes().length == 1) {
            Object originalType = clazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE);
            if (originalType instanceof JavaType) {
                JavaType unwrapped = new JavaTypeBuilder(clazz.getType()).withGenericTypes(new JavaType[]{(JavaType) originalType}).build();
                return new JavaClazzBuilder(clazz).withType(unwrapped).build();
            }
            throw new IllegalStateException("Invalid original type");
        } else {
            throw new UnsupportedOperationException("Unwrapping types with multiple generic arguments is currently not supported");
        }
    }

    public static final Set<JavaType> getTerminatingTypes(JavaType type) {
        Set<JavaType> result = new LinkedHashSet<>();
        if (type.getAttributes().containsKey(TERMINATING_TYPES)) {
            result.addAll((Collection<JavaType>) type.getAttributes().get(TERMINATING_TYPES));
        }
        if (type.getAttributes().containsKey(IS_COMPOSITE) && (Boolean) type.getAttributes().get(IS_TERMINAL)) {
            result.add((JavaType) type.getAttributes().get(ORIGINAL_RETURN_TYPE));
        }
        return result;
    }

    public static final String stripSuffix(String str) {
        if (str.endsWith(INTERFACE_SUFFIX)) {
            return str.substring(0, str.length() - INTERFACE_SUFFIX.length());
        }
        return str;
    }

    public static String toInterfaceName(String name) {
        if (name.endsWith(INTERFACE_SUFFIX)) {
            return name;
        }
        return captializeFirst(name) + INTERFACE_SUFFIX;
    }

    public static boolean isVoid(ExecutableElement executableElement) {
        return executableElement.getReturnType().toString().equals("void");
    }

    public static boolean isTerminal(ExecutableElement executableElement) {
        return executableElement.getAnnotation(Terminal.class) != null;
    }

    public static boolean hasReturnType(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(ORIGINAL_RETURN_TYPE)
                && (clazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE) instanceof JavaType)
                && !((JavaType) clazz.getType().getAttributes().get(ORIGINAL_RETURN_TYPE)).equals(TRANSPARENT);
    }

    public static boolean isComposite(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(IS_COMPOSITE)
                && (clazz.getType().getAttributes().get(IS_COMPOSITE) instanceof Boolean)
                && !(Boolean) clazz.getType().getAttributes().get(IS_COMPOSITE);
    }

    public static boolean isEntryPoint(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(IS_ENTRYPOINT)
                && (clazz.getType().getAttributes().get(IS_ENTRYPOINT) instanceof Boolean)
                && (Boolean) clazz.getType().getAttributes().get(IS_ENTRYPOINT);
    }

    public static boolean isTerminal(JavaClazz clazz) {
        return clazz.getType().getAttributes().containsKey(IS_TERMINAL)
                && (clazz.getType().getAttributes().get(IS_TERMINAL) instanceof Boolean)
                && (Boolean) clazz.getType().getAttributes().get(IS_TERMINAL);
    }
}
