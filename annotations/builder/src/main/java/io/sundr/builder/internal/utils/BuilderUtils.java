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
import io.sundr.builder.internal.functions.TypeAs;
import io.sundr.codegen.DefinitionRepository;
import io.sundr.codegen.functions.ClassTo;
import io.sundr.codegen.functions.Collections;
import io.sundr.codegen.functions.ElementTo;
import io.sundr.codegen.functions.Optionals;
import io.sundr.codegen.model.AnnotationRef;
import io.sundr.codegen.model.Attributeable;
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.PropertyBuilder;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeParamRef;
import io.sundr.codegen.model.TypeRef;
import io.sundr.codegen.utils.TypeUtils;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.builder.Constants.BOOLEAN_REF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_ARRAY_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_COLLECTION_OF;
import static io.sundr.builder.internal.functions.TypeAs.UNWRAP_OPTIONAL_OF;
import static io.sundr.codegen.model.Attributeable.ALSO_IMPORT;
import static io.sundr.codegen.model.Attributeable.INIT;
import static io.sundr.codegen.utils.StringUtils.capitalizeFirst;
import static io.sundr.codegen.utils.TypeUtils.isAbstract;

public class BuilderUtils {

    private BuilderUtils() {}

    public static boolean isBuildable(TypeRef  typeRef) {
        BuildableRepository repository =  BuilderContextManager.getContext().getBuildableRepository();
        return repository.isBuildable(typeRef);
    }

    public static boolean isBuildable(TypeDef  typeDef) {
        BuildableRepository repository =  BuilderContextManager.getContext().getBuildableRepository();
        return repository.isBuildable(typeDef);
    }

    public static ClassRef findBuildableSuperClassRef(TypeDef clazz) {
        BuildableRepository repository =  BuilderContextManager.getContext().getBuildableRepository();

        for (ClassRef superClass : clazz.getExtendsList()) {
            if (repository.isBuildable(superClass)) {
                return superClass;
            }
        }
        return null;
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
            if (hasBuildableAnnotation(candidate)) {
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

    private static boolean hasBuildableAnnotation(Method method) {
        for (AnnotationRef annotationRef : method.getAnnotations()) {
            if (annotationRef.getClassRef().equals(Constants.BUILDABLE_ANNOTATION.getClassRef())) {
                return true;
            }
        }
        return false;
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

    public static boolean isGetter(Method method) {
        if (method.isPrivate() || method.isStatic()) {
            return false;
        }

        if (!method.getArguments().isEmpty()) {
            return false;
        }

        if (method.getName().startsWith("get")) {
            return true;
        }

        if (method.getName().startsWith("is") && (method.getReturnType().equals(BOOLEAN_REF) || method.getReturnType().toString().equals("boolean"))) {
            return true;
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
     * @return     True if default constructor is found, false otherwise.
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
        TypeRef unwrapped = TypeAs.combine(TypeAs.UNWRAP_COLLECTION_OF, TypeAs.UNWRAP_ARRAY_OF, TypeAs.UNWRAP_OPTIONAL_OF).apply(property.getTypeRef());

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

        if (method.isPrivate()) {
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


    public static String fullyQualifiedNameDiff(TypeRef typeRef, TypeDef originType) {
        Map<String, String> map = DefinitionRepository.getRepository().getReferenceMap();
        String currentPackage = originType != null ? originType.getPackageName() : null;

        if (typeRef instanceof ClassRef) {
            TypeRef unwrapped =   TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(typeRef);

            if (unwrapped instanceof ClassRef) {
                ClassRef classRef = (ClassRef) unwrapped;

                String candidateFqn = classRef.getFullyQualifiedName().replace(classRef.getDefinition().getPackageName(), currentPackage);

                //If classRef is inside the current package.
                if (candidateFqn.equals(classRef.getFullyQualifiedName())) {
                    return "";
                }

                //If candidate is imported and different that the actual name, do a diff
                if (originType.getImports().contains(candidateFqn) && !classRef.getDefinition().getFullyQualifiedName().equals(candidateFqn)) {
                    return capitalizeFirst(TypeUtils.fullyQualifiedNameDiff(candidateFqn, classRef.getFullyQualifiedName()));
                }

                //If not then we compare against what has been found in the map.
                String fqn = map.get(classRef.getDefinition().getName());
                if (fqn == null) {
                    System.out.println("Warning: Expected to find class with name:" + classRef.getDefinition().getName());
                } else if (!classRef.getDefinition().getFullyQualifiedName().equals(fqn)) {
                    return capitalizeFirst(TypeUtils.fullyQualifiedNameDiff(fqn, classRef.getFullyQualifiedName()));
                }
            }
        }
        return "";
    }

    public static ClassRef buildableRef(TypeRef typeRef) {
        ClassRef unwrapped = (ClassRef) TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(typeRef);
        return isAbstract(unwrapped) || unwrapped.getDefinition().getKind() == Kind.INTERFACE ? TypeAs.VISITABLE_BUILDER.apply(unwrapped) : TypeAs.BUILDER.apply(unwrapped.getDefinition()).toInternalReference();
    }

    public static Property buildableField(Property property) {
        TypeRef typeRef = property.getTypeRef();
        ClassRef unwrapped = (ClassRef) TypeAs.combine(UNWRAP_COLLECTION_OF, UNWRAP_ARRAY_OF, UNWRAP_OPTIONAL_OF).apply(typeRef);
        ClassRef classRef = (ClassRef) typeRef;
        ClassRef builderType = isAbstract(unwrapped) ||  unwrapped.getDefinition().getKind() == Kind.INTERFACE  ? TypeAs.VISITABLE_BUILDER.apply(unwrapped) : TypeAs.BUILDER.apply(unwrapped.getDefinition()).toInternalReference();

        if (TypeUtils.isList(classRef)) {
            ClassRef listRef =  Collections.ARRAY_LIST.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(Collections.LIST.toReference(builderType))
                    .addToAttributes(INIT, " new " + listRef + "()")
                    .addToAttributes(ALSO_IMPORT, alsoImport(property, listRef, builderType))
                    .build();
        }

        if (TypeUtils.isSet(classRef)) {
            ClassRef setRef = Collections.LINKED_HASH_SET.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(Collections.SET.toReference(builderType))
                    .addToAttributes(INIT, " new " + setRef+ "()")
                    .addToAttributes(ALSO_IMPORT,  alsoImport(property, setRef, builderType))
                    .build();
        }

        if (TypeUtils.isOptionalLong(classRef)) {
            ClassRef optionalRef = Optionals.OPTIONAL_LONG.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(optionalRef)
                .addToAttributes(INIT, " OptionalLong.empty()")
                .build();
        }

        if (TypeUtils.isOptionalDouble(classRef)) {
            ClassRef optionalRef = Optionals.OPTIONAL_DOUBLE.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(optionalRef)
                .addToAttributes(INIT, " OptionalDouble.empty()")
                .build();
        }

        if (TypeUtils.isOptionalInt(classRef)) {
            ClassRef optionalRef = Optionals.OPTIONAL_INT.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(optionalRef)
                .addToAttributes(INIT, " OptionalInt.empty()")
                .build();
        }

        if (TypeUtils.isOptional(classRef)) {
            ClassRef optionalRef = Optionals.OPTIONAL.toReference(builderType);
            return new PropertyBuilder(property).withTypeRef(optionalRef)
                .addToAttributes(INIT, " Optional.empty()")
                .addToAttributes(ALSO_IMPORT,  alsoImport(property, optionalRef, builderType))
                .build();
        }

        return new PropertyBuilder(property).withTypeRef(builderType)
                    .addToAttributes(ALSO_IMPORT, alsoImport(property, builderType))
                    .build();
    }

    public static List<ClassRef> alsoImportAsList(Attributeable attributeable) {
        List<ClassRef> result = new ArrayList<ClassRef>();
        if (attributeable.hasAttribute(ALSO_IMPORT)) {
            result.addAll(attributeable.getAttribute(ALSO_IMPORT));
        }
        return result;
    }

    public static List<ClassRef> alsoImport(Attributeable attributeable, ClassRef... refs) {
        List<ClassRef> result = alsoImportAsList(attributeable);
        result.addAll(Arrays.asList(refs));
        return result;
    }
}
