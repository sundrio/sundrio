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

package io.sundr.dsl.internal.utils;

import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.MethodBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.ModelUtils;
import io.sundr.codegen.utils.TypeUtils;
import io.sundr.dsl.annotations.Begin;
import io.sundr.dsl.annotations.End;
import io.sundr.dsl.annotations.EntryPoint;
import io.sundr.dsl.annotations.InterfaceName;
import io.sundr.dsl.annotations.MethodName;
import io.sundr.dsl.annotations.Multiple;
import io.sundr.dsl.annotations.Or;
import io.sundr.dsl.annotations.Terminal;
import io.sundr.dsl.internal.element.functions.filter.AndTransitionFilter;
import io.sundr.dsl.internal.element.functions.filter.OrTransitionFilter;
import io.sundr.dsl.internal.element.functions.filter.TransitionFilter;
import io.sundr.dsl.internal.processor.DslContext;
import io.sundr.dsl.internal.type.functions.Generics;
import io.sundr.dsl.internal.type.functions.Merge;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.codegen.utils.StringUtils.capitalizeFirst;
import static io.sundr.dsl.internal.Constants.BEGIN_SCOPE;
import static io.sundr.dsl.internal.Constants.CARDINALITY_MULTIPLE;
import static io.sundr.dsl.internal.Constants.CLASSES;
import static io.sundr.dsl.internal.Constants.END_SCOPE;
import static io.sundr.dsl.internal.Constants.FILTER;
import static io.sundr.dsl.internal.Constants.INTERFACE_SUFFIX;
import static io.sundr.dsl.internal.Constants.IS_COMPOSITE;
import static io.sundr.dsl.internal.Constants.IS_ENTRYPOINT;
import static io.sundr.dsl.internal.Constants.IS_GENERIC;
import static io.sundr.dsl.internal.Constants.IS_TERMINAL;
import static io.sundr.dsl.internal.Constants.IS_TRANSITION;
import static io.sundr.dsl.internal.Constants.KEYWORDS;
import static io.sundr.dsl.internal.Constants.METHODS;
import static io.sundr.dsl.internal.Constants.METHOD_NAME;
import static io.sundr.dsl.internal.Constants.ORIGINAL_RETURN_TYPE;
import static io.sundr.dsl.internal.Constants.TERMINATING_TYPES;
import static io.sundr.dsl.internal.Constants.TRANSPARENT;
import static io.sundr.dsl.internal.Constants.TRANSPARENT_REF;
import static io.sundr.dsl.internal.Constants.VOID_REF;

public final class TypeDefUtils {

    private TypeDefUtils() {
        //Utility Class
    }

    /**
     * Convert an {@link javax.lang.model.element.ExecutableElement} to a {@link io.sundr.codegen.model.TypeDef}
     *
     * @param context           The context of the operation.
     * @param executableElement The target element.
     * @return                  An instance of {@link io.sundr.codegen.model.TypeDef} that describes the interface.
     */
    public static TypeDef executableToInterface(DslContext context, ExecutableElement executableElement) {
        //Do generate the interface
        Boolean multiple = executableElement.getAnnotation(Multiple.class) != null;
        Boolean isEntryPoint = executableElement.getAnnotation(EntryPoint.class) != null;
        Boolean isTerminal = executableElement.getAnnotation(Terminal.class) != null
                || !isVoid(executableElement);

        Set<String> classes = new HashSet<String>();
        Set<String> keywords = new HashSet<String>();
        Set<String> methods = new HashSet<String>();

        TransitionFilter filter = executableElement.getAnnotation(Or.class) != null
                ? new OrTransitionFilter(context.getToRequiresAll().apply(executableElement), context.getToRequiresAny().apply(executableElement), context.getToRequiresOnly().apply(executableElement), context.getToRequiresNoneOf().apply(executableElement))
                : new AndTransitionFilter(context.getToRequiresAll().apply(executableElement), context.getToRequiresAny().apply(executableElement), context.getToRequiresOnly().apply(executableElement), context.getToRequiresNoneOf().apply(executableElement));

        for (String clazz : context.getToClasses().apply(executableElement)) {
            classes.add(clazz);
        }

        for (String keyword : context.getToKeywords().apply(executableElement)) {
            keywords.add(keyword);
        }

        //Let's add the name of the method as a keyword to make things simpler
        methods.add(executableElement.getSimpleName().toString());

        TypeRef returnType;
        if (isTerminal(executableElement)) {
            returnType = isVoid(executableElement) ?
                    VOID_REF :
                    ElementTo.MIRROR_TO_TYPEREF.apply(executableElement.getReturnType());
        } else {
            returnType = TRANSPARENT_REF;
        }

        InterfaceName targetInterfaceName = executableElement.getAnnotation(InterfaceName.class);
        MethodName tagetMethodName = executableElement.getAnnotation(MethodName.class);
        Begin begin = executableElement.getAnnotation(Begin.class);
        End end = executableElement.getAnnotation(End.class);

        if (begin != null) {
            keywords.add(begin.value());
        }
        if (end != null) {
            keywords.add(end.value());
        }

        String methodName = tagetMethodName != null ? tagetMethodName.value() : executableElement.getSimpleName().toString();

        String beginScope = begin != null ? begin.value() : null;
        String endScope = end != null ? end.value() : null;

        TypeParamDef paremeterType = Generics.MAP.apply(returnType);

        Method sourceMethod = ElementTo.METHOD.apply(executableElement);
        List<AnnotationRef> annotations = new ArrayList<AnnotationRef>();
        for (AnnotationRef candidate : sourceMethod.getAnnotations()) {
            if (!candidate.getClassRef().getFullyQualifiedName().startsWith("io.sundr")) {
                annotations.add(candidate);
            }
        }
        Method targetMethod = new MethodBuilder(sourceMethod)
                .withAnnotations(annotations)
                .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                .withReturnType(paremeterType.toReference())
                .withName(methodName)
                .build();


        String interfaceName = targetInterfaceName != null ?
                targetInterfaceName.value() :
                toInterfaceName(targetMethod.getName());

        return new TypeDefBuilder()
                    .withPackageName(ModelUtils.getPackageElement(executableElement).toString())
                    .withName(interfaceName)
                    .withParameters(paremeterType)
                    .withKind(Kind.INTERFACE)
                    .withModifiers(TypeUtils.modifiersToInt(Modifier.PUBLIC))
                    .addToAttributes(ORIGINAL_RETURN_TYPE, returnType)
                    .addToAttributes(IS_ENTRYPOINT, isEntryPoint)
                    .addToAttributes(IS_TERMINAL, isTerminal)
                    .addToAttributes(IS_GENERIC, Boolean.FALSE)
                    .addToAttributes(CLASSES, classes)
                    .addToAttributes(KEYWORDS, keywords)
                    .addToAttributes(METHODS, methods)
                    .addToAttributes(BEGIN_SCOPE, beginScope)
                    .addToAttributes(END_SCOPE, endScope)
                    .addToAttributes(FILTER, filter)
                    .addToAttributes(CARDINALITY_MULTIPLE, multiple)
                    .addToAttributes(METHOD_NAME, methodName)
                .addToMethods(targetMethod)
                .build();
    }


    /**
     * Convert a {@link Collection} of {@link javax.lang.model.element.ExecutableElement}s to a {@link java.util.Set} of {@link io.sundr.codegen.model.TypeDef}es.
     *
     * @param context           The context of the operation.
     * @param elements          The target elements.
     * @return                  A set of {@link io.sundr.codegen.model.TypeDef} that describes the interfaces.
     */
    public static Set<TypeDef> executablesToInterfaces(DslContext context, Collection<ExecutableElement> elements) {
        Map<String, TypeDef> byName = new LinkedHashMap<String, TypeDef>();
        for (ExecutableElement current : elements) {
            TypeDef clazz = executableToInterface(context, current);
            InterfaceName interfaceName = current.getAnnotation(InterfaceName.class);
            String name = interfaceName != null ? clazz.getPackageName() + "." + interfaceName.value() : clazz.getFullyQualifiedName();
            if (byName.containsKey(name)) {
                TypeDef other = byName.remove(name);
                byName.put(name, Merge.CLASSES.apply(new TypeDef[]{other, clazz}));
            } else {
                byName.put(name, clazz);
            }
        }
        return new LinkedHashSet<TypeDef>(byName.values());
    }



    public static final Set<TypeDef> getTerminatingTypes(Attributeable type) {
        Set<TypeDef> result = new LinkedHashSet<TypeDef>();
        if (type.getAttributes().containsKey(TERMINATING_TYPES)) {
            result.addAll((Collection<TypeDef>) type.getAttributes().get(TERMINATING_TYPES));
        }
        if (type.getAttributes().containsKey(IS_COMPOSITE)
                && (Boolean) type.getAttributes().get(IS_TERMINAL)
                && !(type.getAttributes().get(ORIGINAL_RETURN_TYPE).equals(TRANSPARENT))) {
            result.add((TypeDef) type.getAttributes().get(ORIGINAL_RETURN_TYPE));
        }
        return result;
    }




    public static Set<ClassRef> extractInterfacesFromTypes(Set<TypeDef> types) {
        Set<ClassRef> result = new LinkedHashSet<ClassRef>();
        for (TypeDef type : types) {
            result.addAll(extractInterfacesFromType(type));
        }
        return result;
    }

    public static Set<ClassRef> extractInterfacesFromClassRefs(Set<ClassRef> types) {
        Set<ClassRef> result = new LinkedHashSet<ClassRef>();
        for (ClassRef type : types) {
            result.addAll(extractInterfacesFromClassRef(type));
        }
        return result;
    }

    public static Set<ClassRef> extractInterfacesFromType(TypeDef type) {
        Set<ClassRef> result = new LinkedHashSet<ClassRef>();
        if (type.getExtendsList().isEmpty()) {
            result.add(type.toInternalReference());
        } else {
            for (ClassRef interfaceType : type.getExtendsList()) {
                result.addAll(extractInterfacesFromClassRef(interfaceType));
            }
        }
        return result;
    }

    public static Set<ClassRef> extractInterfacesFromClassRef(ClassRef classRef) {
        return extractInterfacesFromType(classRef.getDefinition());
    }

    public static String toInterfaceName(String name) {
        if (name.endsWith(INTERFACE_SUFFIX)) {
            return name;
        }
        return capitalizeFirst(name) + INTERFACE_SUFFIX;
    }

    public static boolean isVoid(ExecutableElement executableElement) {
        return executableElement.getReturnType().toString().equals("void");
    }

    public static boolean isTerminal(ExecutableElement executableElement) {
        return executableElement.getAnnotation(Terminal.class) != null;
    }

    public static boolean isEntryPoint(Attributeable type) {
        return type.getAttributes().containsKey(IS_ENTRYPOINT)
                && (type.getAttributes().get(IS_ENTRYPOINT) instanceof Boolean)
                && (Boolean) type.getAttributes().get(IS_ENTRYPOINT);
    }

    public static boolean isTerminal(Attributeable type) {
        return type.getAttributes().containsKey(IS_TERMINAL)
                && (type.getAttributes().get(IS_TERMINAL) instanceof Boolean)
                && (Boolean) type.getAttributes().get(IS_TERMINAL);
    }

    public static boolean isTransition(Attributeable type) {
        return type.getAttributes().containsKey(IS_TRANSITION)
                && (type.getAttributes().get(IS_TRANSITION) instanceof Boolean)
                && (Boolean) type.getAttributes().get(IS_TRANSITION);
    }

    public static boolean isBeginScope(Attributeable type) {
        return type.getAttributes().containsKey(BEGIN_SCOPE);
    }

    public static boolean isEndScope(Attributeable type) {
        return type.getAttributes().containsKey(END_SCOPE);
    }

    public static boolean isCardinalityMultiple(Attributeable type) {
        return type.getAttributes().containsKey(CARDINALITY_MULTIPLE)
                && (type.getAttributes().get(CARDINALITY_MULTIPLE) instanceof Boolean)
                && (Boolean) type.getAttributes().get(CARDINALITY_MULTIPLE);
    }

    public static boolean isGeneric(Attributeable type) {
        return type.getAttributes().containsKey(IS_GENERIC)
                && (type.getAttributes().get(IS_GENERIC) instanceof Boolean)
                && (Boolean) type.getAttributes().get(IS_GENERIC);
    }


    public static <T> List<String> toClassNames(T value) {
        List<String> classNames = new ArrayList<String>();
        if (value instanceof String) {
            classNames.add(removeSuffix((String) value));
        } else if (value instanceof List) {
            List list = (List) value;
            for (Object item : list) {
                String str = String.valueOf(item);
                classNames.add(removeQuotes(removeSuffix(str)));
            }
        }
        return classNames;
    }

    private static String removeLeftQuote(String str) {
        if (str.startsWith("\"")) {
            return str.substring(1);
        } else {
            return str;
        }
    }

    private static String removeRightQuote(String str) {
        if (str.endsWith("\"")) {
            return str.substring(0, str.length() - 1);
        } else {
            return str;
        }
    }

    private static String removeQuotes(String str) {
        return removeLeftQuote(removeRightQuote(str));
    }

    private static String removeSuffix(String str) {
        if (str.endsWith(".class")) {
            return str.substring(0, str.length() - 6);
        } else {
            return str;
        }
    }
}
