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


import io.sundr.SundrException;
import io.sundr.builder.Constants;
import io.sundr.builder.annotations.Buildable;
import io.sundr.builder.annotations.BuildableReference;
import io.sundr.builder.annotations.ExternalBuildables;
import io.sundr.builder.annotations.Inline;
import io.sundr.builder.internal.BuildableRepository;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.PropertyAs;
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.AttributeSupport;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.StringUtils;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static io.sundr.builder.Constants.DESCENDANT_OF;
import static io.sundr.builder.Constants.LIST;
import static io.sundr.builder.Constants.MAP;
import static io.sundr.builder.Constants.SET;
import static io.sundr.codegen.utils.StringUtils.deCaptializeFirst;
import static io.sundr.codegen.utils.TypeUtils.unwrapGeneric;

public class BuilderUtils {

    public static final String BUILDABLE = "BUILDABLE";

    public static JavaMethod findBuildableConstructor(JavaClazz clazz) {
        //1st pass go for annotated method
        for (JavaMethod candidate : clazz.getConstructors()) {
            if (candidate.getAnnotations().contains(Constants.BUILDABLE_ANNOTATION)) {
                return candidate;
            }
        }

        //2nd pass go for the first non-empty constructor
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
        throw new SundrException("No getter found for property: " + property.getName() + " on class: " + clazz.getType().getFullyQualifiedName());
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
     *
     * @param item The clazz to check.
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

    /**
     * Find all buildable descendant equivalents of a property.
     *
     * @param property
     * @return
     */
    public static Set<JavaProperty> getPropertyBuildableAncestors(JavaProperty property) {
        Set<JavaProperty> result = new LinkedHashSet<JavaProperty>();
        JavaType baseType = property.getType();

        if (baseType.isCollection()) {
            JavaType candidate = TypeAs.UNWRAP_COLLECTION_OF.apply(baseType);
            for (JavaType descendant : BuilderUtils.getBuildableDescendants(candidate)) {
                JavaType collectionType = new JavaTypeBuilder(baseType).withGenericTypes(new JavaType[]{descendant}).build();
                String propertyName = deCaptializeFirst(descendant.getClassName()) + property.getNameCapitalized();
                result.add(new JavaPropertyBuilder(property)
                        .withName(propertyName)
                        .withType(collectionType)
                        .addToAttributes(DESCENDANT_OF, property)
                        .addToAttributes(BUILDABLE, true)
                        .build());
            }
        } else {
            for (JavaType descendant : BuilderUtils.getBuildableDescendants(baseType)) {
                String propertyName = descendant.getSimpleName() + property.getNameCapitalized();
                result.add(new JavaPropertyBuilder(property)
                        .withName(propertyName)
                        .withType(descendant)
                        .addToAttributes(DESCENDANT_OF, property)
                        .addToAttributes(BUILDABLE, true)
                        .build());
            }
        }
        return result;
    }

    /**
     * Finds all the descendants of a type that are buildable.
     *
     * @param item The type.
     * @return
     */
    public static Set<JavaType> getBuildableDescendants(JavaType item) {
        if (item.getFullyQualifiedName().equals(Constants.OBJECT.getFullyQualifiedName())) {
            return new LinkedHashSet<JavaType>();
        }

        Set<JavaType> result = new LinkedHashSet<JavaType>();
        BuilderContext ctx = BuilderContextManager.getContext();
        BuildableRepository repository = ctx.getRepository();
        for (TypeElement element : repository.getBuildables()) {
            JavaClazz clazz = ctx.getTypeElementToJavaClazz().apply(element);
            JavaType type = clazz.getType();
            if (isDescendant(type, item)) {
                result.add(type);
            }
        }
        return result;
    }


    /**
     * Checks if type has any descendants that are "buildable"
     *
     * @param item The type.
     * @return true if a buildable ancestor is found.
     */
    public static boolean hasBuildableDescendants(JavaType item) {
        if (item.getFullyQualifiedName().equals(Constants.OBJECT.getFullyQualifiedName())) {
            return false;
        }
        BuilderContext ctx = BuilderContextManager.getContext();
        BuildableRepository repository = ctx.getRepository();
        for (TypeElement element : repository.getBuildables()) {
            JavaType type = ctx.getStringJavaTypeFunction().apply(element.toString());
            if (isDescendant(type, item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a type is an descendant of an other type
     *
     * @param item      The base type.
     * @param candidate The candidate type.
     * @return true if candidate is a descendant of base type.
     */
    public static boolean isDescendant(JavaType item, JavaType candidate) {
        if (item == null || candidate == null) {
            return false;
        } else if (item.getFullyQualifiedName().equals(candidate.getFullyQualifiedName())) {
            return true;
        } else if (isDescendant(item.getSuperClass(), candidate)) {
            return true;
        } else {
            for (JavaType interfaceType : item.getInterfaces()) {
                if (isDescendant(interfaceType, candidate)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static Set<JavaMethod> getInlineableConstructors(JavaProperty property) {
        Set<JavaMethod> result = new HashSet<JavaMethod>();
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

    public static JavaType getInlineType(BuilderContext context, Inline inline) {
        try {
            return ClassToJavaType.FUNCTION.apply(inline.type());
        } catch (MirroredTypeException e) {
            return context.getStringJavaTypeFunction().apply(e.getTypeMirror().toString());
        }
    }

    public static JavaType getInlineReturnType(BuilderContext context, Inline inline) {
        try {
            return ClassToJavaType.FUNCTION.apply(inline.returnType());
        } catch (MirroredTypeException e) {
            return context.getStringJavaTypeFunction().apply(e.getTypeMirror().toString());
        }
    }

    public static Set<TypeElement> getBuildableReferences(BuilderContext context, Buildable buildable) {
        Set<TypeElement> result = new LinkedHashSet<TypeElement>();
        for (BuildableReference ref : buildable.refs()) {
            try {
                result.add(context.getElements().getTypeElement(ref.value().getCanonicalName()));
            } catch (MirroredTypeException e) {
                result.add(context.getElements().getTypeElement(e.getTypeMirror().toString()));
            }
        }
        return result;
    }

    public static Set<TypeElement> getBuildableReferences(BuilderContext context, ExternalBuildables buildable) {
        Set<TypeElement> result = new LinkedHashSet<TypeElement>();
        for (BuildableReference ref : buildable.refs()) {
            try {
                result.add(context.getElements().getTypeElement(ref.value().getCanonicalName()));
            } catch (MirroredTypeException e) {
                result.add(context.getElements().getTypeElement(e.getTypeMirror().toString()));
            }
        }
        return result;
    }

    public static boolean isPrimitive(JavaType type) {
        for (JavaType t : Constants.PRIMITIVE_TYPES) {
            if (type.getSimpleName().equals(t.getSimpleName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMap(JavaType type) {
        if (unwrapGeneric(type).equals(unwrapGeneric(MAP))) {
            return true;
        }
        for (JavaType i : type.getInterfaces()) {
            //prevent infinite loop
            if (!type.getFullyQualifiedName().equals(i.getFullyQualifiedName()) && isMap(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isList(JavaType type) {
        if (unwrapGeneric(type).equals(unwrapGeneric(LIST))) {
            return true;
        }
        for (JavaType i : type.getInterfaces()) {
            //prevent infinite loop
            if (!type.getFullyQualifiedName().equals(i.getFullyQualifiedName()) && isList(i)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSet(JavaType type) {
        if (unwrapGeneric(type).equals(unwrapGeneric(SET))) {
            return true;
        }
        for (JavaType i : type.getInterfaces()) {
            //prevent infinite loop
            if (!type.getFullyQualifiedName().equals(i.getFullyQualifiedName()) && isSet(i)) {
                return true;
            }
        }
        return false;
    }

    public static JavaType getNextGeneric(JavaType type, Collection<JavaType> excluded) {
        Set<String> skip = new HashSet<String>();
        for (JavaType s : allGenericsOf(type)) {
            skip.add(s.getClassName());
        }

        for (JavaType s : excluded) {
            skip.add(s.getClassName());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < GENERIC_NAMES.length; j++) {

                String name = GENERIC_NAMES[j] + ((i > 0) ? String.valueOf(i) : "");
                if (!skip.contains(name)) {
                    return TypeUtils.newGeneric(name);
                }
            }
        }
        throw new IllegalStateException("Could not allocate generic parameter letter for: " + type.getFullyQualifiedName());
    }

    public static JavaType getNextGeneric(JavaType type, JavaType... excluded) {
        return getNextGeneric(type, Arrays.asList(excluded));
    }


    public static Set<JavaType> allGenericsOf(JavaClazz clazz) {
        Set<JavaType> result = new HashSet<JavaType>(allGenericsOf(clazz.getType()));

        for (JavaProperty property : clazz.getFields()) {
            result.addAll(allGenericsOf(property));
        }

        for (JavaMethod method : clazz.getMethods()) {
            result.addAll(allGenericsOf(method));
        }

        return result;
    }

    public static Set<JavaType> allGenericsOf(JavaType type) {
        return new HashSet<JavaType>(Arrays.asList(type.getGenericTypes()));
    }

    public static Collection<JavaType> allGenericsOf(JavaProperty property) {
        return allGenericsOf(property.getType());
    }

    public static Collection<JavaType> allGenericsOf(JavaMethod method) {
        Set<JavaType> result = new HashSet<JavaType>(allGenericsOf(method.getReturnType()));
        for (JavaProperty property : method.getArguments()) {
            result.addAll(allGenericsOf(property));

        }
        return result;
    }

    private static final String[] GENERIC_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"};
    private static int counter = 0;
}
