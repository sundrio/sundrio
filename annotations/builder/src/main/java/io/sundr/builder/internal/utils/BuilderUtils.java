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
import io.sundr.builder.annotations.*;
import io.sundr.builder.internal.BuildableRepository;
import io.sundr.builder.internal.BuilderContext;
import io.sundr.builder.internal.BuilderContextManager;
import io.sundr.builder.internal.functions.CollectionTypes;
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.PrimitiveRef;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeRef;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class BuilderUtils {

    private BuilderUtils() {}

    public static final String BUILDABLE = "BUILDABLE";

    public static boolean isBuildable(TypeRef  typeRef) {
        BuildableRepository repository =  BuilderContextManager.getContext().getBuildableRepository();
        return repository.isBuildable(typeRef);
    }

    public static boolean isBuildable(TypeDef  typeDef) {
        BuildableRepository repository =  BuilderContextManager.getContext().getBuildableRepository();
        return repository.isBuildable(typeDef);
    }

    public static TypeDef findBuildableSuperClass(TypeDef clazz) {
        BuildableRepository repository =  BuilderContextManager.getContext().getBuildableRepository();

        for (ClassRef superClass : clazz.getExtendsList()) {
            if (repository.isBuildable(superClass)) {
                return repository.getBuildable(superClass);
            }
        }
        return null;
    }

    public static Method findBuildableConstructor(TypeDef clazz) {
        //1st pass go for annotated method
        for (Method candidate : clazz.getConstructors()) {
            if (candidate.getAnnotations().contains(Constants.BUILDABLE_ANNOTATION_REF)) {
                return candidate;
            }
        }

        //2nd pass go for the first non-empty constructor
        for (Method candidate : clazz.getConstructors()) {
            if (candidate.getArguments().size() != 0) {
                return candidate;
            }
        }

        if (!clazz.getConstructors().isEmpty()) {
            return clazz.getConstructors().iterator().next();
        } else {
            throw new IllegalStateException("Could not find buildable constructor in: ["+clazz.getFullyQualifiedName()+"].");
        }

    }

    public static Method findGetter(TypeDef clazz, Property property) {
        TypeDef current = clazz;
        while (current!= null && !current.equals(TypeDef.OBJECT)) {
            for (Method method : current.getMethods()) {
                if (isApplicableGetterOf(method, property)) {
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

    public static boolean hasSetter(TypeDef clazz, Property property) {
        for (Method method : clazz.getMethods()) {
            if (isApplicableSetterOf(method, property)) {
                return true;
            }
        }
        return false;
    }


    public static boolean hasOrInheritsSetter(TypeDef clazz, Property property) {
        TypeDef current = clazz;
        //Iterate parent objects and check for properties with setters but not ctor arguments.
        while (current!= null && !current.equals(TypeDef.OBJECT)) {
            for (Method method : current.getMethods()) {
                if (isApplicableSetterOf(method, property)) {
                    return true;
                }
            }

            if (!current.getExtendsList().isEmpty()) {
                String fqn = current.getExtendsList().iterator().next().getDefinition().getFullyQualifiedName();
                current = DefinitionRepository.getRepository().getDefinition(fqn);
            } else {
                current = null;
            }
        }
        return false;
    }

    private static boolean isApplicableGetterOf(Method method, Property property) {
        if (!method.getReturnType().isAssignableFrom(property.getTypeRef())) {
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


    private static boolean isApplicableSetterOf(Method method, Property property) {
        if (method.getArguments().size() != 1) {
            return false;
        } else if (!method.getArguments().get(0).getTypeRef().equals(property.getTypeRef())) {
            return false;
        } else if (method.getName().endsWith("set" + property.getNameCapitalized())) {
            return true;
        }
        return false;
    }


    /**
     * Checks if method has a specific argument.
     * @param method        The method.
     * @param property      The arguement.
     * @return              True if matching argument if found.
     */
    public static boolean methodHasArgument(Method method, Property property) {
        for (Property candidate : method.getArguments()) {
            if (candidate.equals(property)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasBuildableConstructorWithArgument(TypeDef clazz, Property property) {
        Method constructor = findBuildableConstructor(clazz);
        if (constructor == null) {
            return false;
        } else {
            return methodHasArgument(constructor, property);
        }

    }

    /**
     * Checks if there is a default constructor available.
     *
     * @param item The clazz to check.
     * @return
     */
    public static boolean hasDefaultConstructor(TypeDef item) {
        if (item == null) {
            return false;
        } else if (item.getConstructors().isEmpty()) {
            return true;
        } else {
            for (Method constructor : item.getConstructors()) {
                if (constructor.getArguments().size() == 0) {
                    return true;
                }
            }
        }
        return false;
    }


    public static Set<Method> getInlineableConstructors(Property property) {
        Set<Method> result = new HashSet<Method>();
        TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF).apply(property.getTypeRef());

        if (unwrapped instanceof ClassRef) {
            for (Method candidate : ((ClassRef)unwrapped).getDefinition().getConstructors()) {
                if (isInlineable(candidate)) {
                    result.add(candidate);
                }
            }
        }

        //We try both types to make sure...
        TypeDef fromRepo = BuilderContextManager.getContext().getDefinitionRepository().getDefinition(unwrapped);
        if (fromRepo != null) {
            for (Method candidate : fromRepo.getConstructors()) {
                if (isInlineable(candidate)) {
                    result.add(candidate);
                }
            }
        }
        return result;
    }

    public static boolean isInlineable(Method method) {
        if (method.getArguments().size() == 0 || method.getArguments().size() > 5) {
            return false;
        }

        for (Property argument : method.getArguments()) {
            if (!(argument.getTypeRef() instanceof ClassRef)) {
                continue;
            } else if (((ClassRef)argument.getTypeRef()).getDefinition().getFullyQualifiedName().startsWith("java.lang")) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static TypeDef getInlineType(BuilderContext context, Inline inline) {
        try {
            return ClassTo.TYPEDEF.apply(inline.type());
        } catch (MirroredTypeException e) {
            Element element = context.getTypes().asElement(e.getTypeMirror());
            return ElementTo.TYPEDEF.apply((TypeElement) element);
        }
    }

    public static TypeDef getInlineReturnType(BuilderContext context, Inline inline, TypeDef fallback) {
        try {
            Class returnType = inline.returnType();
            if (returnType == null) {
                return fallback;
            }
            return ClassTo.TYPEDEF.apply(inline.returnType());
        } catch (MirroredTypeException e) {
            if (None.FQN.equals(e.getTypeMirror().toString())) {
                return fallback;
            }

            Element element = context.getTypes().asElement(e.getTypeMirror());
            return ElementTo.TYPEDEF.apply((TypeElement) element);
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

    public static boolean isPrimitive(TypeRef type) {
        return type instanceof PrimitiveRef;
    }

    public static boolean isMap(TypeRef type) {
       return CollectionTypes.IS_MAP.apply(type);
    }

    public static boolean isList(TypeRef type) {
        return CollectionTypes.IS_LIST.apply(type);
    }

    public static boolean isSet(TypeRef type) {
        return CollectionTypes.IS_SET.apply(type);
    }

    public static boolean isCollection(TypeRef type) {
        return CollectionTypes.IS_COLLECTION.apply(type);
    }

    public static boolean isBoolean(TypeRef type) {
        if (type instanceof PrimitiveRef) {
            return ((PrimitiveRef)type).getName().equals("boolean");
        } else if (!(type instanceof ClassRef)) {
            return false;
        } else {
            return ((ClassRef)type).getDefinition().equals(Constants.BOOLEAN);
        }
    }


    public static boolean isArray(TypeRef type) {

        if (type instanceof ClassRef) {
            return ((ClassRef)type).getDimensions() > 0;
        } else if (type instanceof PrimitiveRef) {
            return ((PrimitiveRef)type).getDimensions() > 0;
        } else if (type instanceof TypeParamRef) {
            return ((TypeParamRef)type).getDimensions() > 0;
        } else {
            return false;
        }
    }

    public static TypeParamDef getNextGeneric(TypeDef type, TypeParamDef... excluded) {
        return getNextGeneric(type, Arrays.asList(excluded));
    }


    public static TypeParamDef getNextGeneric(TypeDef type, Collection<TypeParamDef> excluded) {
        Set<String> skip = new HashSet<String>();
        for (String s : allGenericsOf(type)) {
            skip.add(s);
        }

        for (TypeParamDef e : excluded) {
            skip.add(e.getName());
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < GENERIC_NAMES.length; j++) {

                String name = GENERIC_NAMES[j] + ((i > 0) ? String.valueOf(i) : "");
                if (!skip.contains(name)) {
                    return new TypeParamDefBuilder().withName(name).build();
                }
            }
        }
        throw new IllegalStateException("Could not allocate generic parameter letter for: " + type.getFullyQualifiedName());
    }

    public static Set<String> allGenericsOf(TypeDef clazz) {
        Set<String> result = new HashSet<String>();

        for (TypeParamDef paramDef : clazz.getParameters()) {
            result.add(paramDef.getName());
        }
        for (Property property : clazz.getProperties()) {
            result.addAll(allGenericsOf(property));
        }

        for (Method method : clazz.getMethods()) {
            result.addAll(allGenericsOf(method));
        }

        return result;
    }

    public static Set<String> allGenericsOf(TypeRef type) {
        Set<String> result = new HashSet<String>();
        if (type instanceof ClassRef) {
            for (TypeRef ref : ((ClassRef)type).getArguments()) {
                if (ref instanceof TypeParamRef) {
                    result.add(((TypeParamRef)ref).getName());
                }
            }
        }
        return result;
    }


    public static Collection<String> allGenericsOf(Property property) {
        return allGenericsOf(property.getTypeRef());
    }

    public static Collection<String> allGenericsOf(Method method) {
        Set<String> result = new HashSet<String>(allGenericsOf(method.getReturnType()));
        for (Property property : method.getArguments()) {
            result.addAll(allGenericsOf(property));

        }
        return result;
    }

    private static final String[] GENERIC_NAMES = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S"};
}
