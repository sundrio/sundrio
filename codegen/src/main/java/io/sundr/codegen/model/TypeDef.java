/*
 * Copyright 2016 The original authors.
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

package io.sundr.codegen.model;

import io.sundr.codegen.PackageScope;
import io.sundr.codegen.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TypeDef extends ModifierSupport {

    public static TypeDef OBJECT = new TypeDefBuilder()
            .withPackageName("java.lang")
            .withName("Object")
            .build();

    public static TypeDef ENUM = new TypeDefBuilder()
            .withPackageName("java.lang")
            .withName("Enum")
            .build();

    public static ClassRef OBJECT_REF = OBJECT.toReference();
    public static ClassRef ENUM_REF = ENUM.toReference();

    private final Kind kind;
    private final String packageName;
    private final String name;

    private final List<AnnotationRef> annotations;
    private final List<ClassRef> extendsList;
    private final List<ClassRef> implementsList;
    private final List<TypeParamDef> parameters;

    private final List<Property> properties;
    private final List<Method> constructors;
    private final List<Method> methods;
    private final TypeDef outerType;
    private final List<TypeDef> innerTypes;

    public TypeDef(Kind kind, String packageName, String name, List<AnnotationRef> annotations, List<ClassRef> extendsList, List<ClassRef> implementsList, List<TypeParamDef> parameters, List<Property> properties, List<Method> constructors, List<Method> methods, TypeDef outerType, List<TypeDef> innerTypes, int modifiers, Map<AttributeKey, Object> attributes) {
        super(modifiers, attributes);
        this.kind = kind != null ? kind : Kind.CLASS;
        this.packageName = packageName;
        this.name = name;
        this.annotations = annotations;
        this.extendsList = extendsList;
        this.implementsList = implementsList;
        this.parameters = parameters;
        this.properties = properties;
        this.constructors = adaptConstructors(constructors, this);
        this.methods = methods;
        this.outerType = outerType;
        this.innerTypes = setOuterType(innerTypes, this);
    }

    /**
     * The method adapts constructor method to the current class. It unsets any name that may be
     * presetn in the method. It also sets as a return type a reference to the current type.
     */
    private static List<Method> adaptConstructors(List<Method> methods, TypeDef target) {
        List<Method> adapted = new ArrayList<Method>();
        for (Method m : methods) {
            adapted.add(new MethodBuilder(m)
                    .withName(null)
                    .withReturnType(target.toUnboundedReference())
                    .build());
        }
        return adapted;
    }

    private static List<TypeDef> setOuterType(List<TypeDef> types, TypeDef outer) {
        List<TypeDef> updated = new ArrayList<TypeDef>();
        for (TypeDef typeDef : types) {
            if (outer.equals(typeDef.getOuterType())) {
                updated.add(typeDef);
            } else {
                updated.add(new TypeDefBuilder(typeDef).withOuterType(outer).withPackageName(outer.getPackageName()).build());
            }
        }
        return updated;
    }

    /**
     * Returns the fully qualified name of the type.
     */
    public String getFullyQualifiedName() {
        StringBuilder sb = new StringBuilder();
        if (packageName != null && !packageName.isEmpty()) {
            sb.append(getPackageName()).append(".");
        }

        if (outerType != null) {
            sb.append(outerType.getName()).append(".");
        }
        sb.append(getName());

        return sb.toString();
    }

    public boolean isAssignableFrom(TypeDef o) {
        if (this == o || this.equals(o)) {
            return true;
        }

        if (packageName == null && "java.lang".equals(o.packageName) && name.equalsIgnoreCase(o.name)) {
            return true;
        }
        if (o.packageName == null && "java.lang".equals(packageName) && name.equalsIgnoreCase(o.name)) {
            return true;
        }

        for (ClassRef e : o.getExtendsList()) {
            if (isAssignableFrom(e.getDefinition())) {
                return true;
            }
        }

        for (ClassRef i : o.getImplementsList()) {
            if (isAssignableFrom(i.getDefinition())) {
                return true;
            }
        }


        return false;
    }

    public Kind getKind() {
        return kind;
    }

    public List<AnnotationRef> getAnnotations() {
        return annotations;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getName() {
        return name;
    }

    public List<ClassRef> getExtendsList() {
        return extendsList;
    }

    public List<ClassRef> getImplementsList() {
        return implementsList;
    }

    public List<TypeParamDef> getParameters() {
        return parameters;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public List<Method> getConstructors() {
        return constructors;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public TypeDef getOuterType() {
        return outerType;
    }

    public List<TypeDef> getInnerTypes() {
        return innerTypes;
    }

    public boolean isClass() {
        return kind == Kind.INTERFACE;
    }

    public boolean isInterface() {
        return kind == Kind.INTERFACE;
    }

    public boolean isEnum() {
        return kind == Kind.ENUM || (kind == Kind.CLASS && extendsList.contains(ENUM_REF));
    }

    public boolean isAnnotation() {
        return kind == Kind.ANNOTATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeDef typeDef = (TypeDef) o;

        if (packageName != null ? !packageName.equals(typeDef.packageName) : typeDef.packageName != null)
            return false;
        if (outerType != null ? !outerType.equals(typeDef.outerType) : typeDef.outerType != null)
            return false;
        return name != null ? name.equals(typeDef.name) : typeDef.name == null;

    }

    @Override
    public int hashCode() {
        int result = packageName != null ? packageName.hashCode() : 0;
        result = 31 * result + (outerType != null ? outerType.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    /**
     * Creates a {@link ClassRef} for the current definition with the specified arguments.
     *
     * @param arguments The arguments to be passed to the reference.
     */
    public ClassRef toReference(TypeRef... arguments) {
        List<TypeRef> actualArguments = new ArrayList<TypeRef>();
        for (int i = 0; i < parameters.size(); i++) {
            if (i < arguments.length) {
                actualArguments.add(arguments[i]);
            } else {
                actualArguments.add(new WildcardRef());
            }
        }
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(actualArguments)
                .withAttributes(getAttributes())
                .build();
    }

    /**
     * Creates a {@link ClassRef} for the current definition with the specified arguments.
     *
     * @param arguments The arguments to be passed to the reference.
     */
    public ClassRef toReference(List<TypeRef> arguments) {
        List<TypeRef> actualArguments = new ArrayList<TypeRef>();
        for (int i = 0; i < parameters.size(); i++) {
            if (i < arguments.size()) {
                actualArguments.add(arguments.get(i));
            } else {
                actualArguments.add(new WildcardRef());
            }
        }
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(actualArguments)
                .withAttributes(getAttributes())
                .build();
    }

    /**
     * Creates a {@link ClassRef} for the current definition with the specified arguments.
     *
     * @param arguments The arguments to be passed to the reference.
     */
    public ClassRef toReference(Collection<TypeRef> arguments) {
        return toReference(arguments);
    }

    /**
     * Creates a {@link ClassRef} for internal use inside the scope of the type (methods, properties
     * etc). It uses as arguments the same 'letters' as the parameters definition.
     */
    public ClassRef toInternalReference() {
        List<TypeRef> arguments = new ArrayList<TypeRef>();
        for (TypeParamDef parameter : parameters) {
            arguments.add(parameter.toReference());
        }
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(arguments)
                .withAttributes(getAttributes())
                .build();
    }

    /**
     * Creates a {@link ClassRef} without bounds.
     */
    public ClassRef toUnboundedReference() {
        return new ClassRefBuilder()
                .withDefinition(this)
                .withArguments(new TypeRef[0])
                .build();
    }

    public Set<String> getImports() {
        final Set<String> imports = new LinkedHashSet<String>();
        for (ClassRef ref : getReferenceMap().values()) {
            TypeDef definition = ref.getDefinition();
            if (definition.getPackageName() == null ||
                    definition.getPackageName().isEmpty() ||
                    definition.getPackageName().equals(packageName) ||
                    definition.getName().equals(name)) {
                continue;
            } else {
                imports.add(ref.getDefinition().getFullyQualifiedName());
            }
        }
        return imports;
    }


    /**
     * Create a mapping from class name to {@link ClassRef}.
     */
    private Map<String, ClassRef> getReferenceMap() {
        Map<String, ClassRef> mapping = new HashMap<String, ClassRef>();
        List<ClassRef> refs = getReferences();

        //It's best to have predictable order, so that we can generate uniform code.
        Collections.sort(refs, new Comparator<ClassRef>() {
            @Override
            public int compare(ClassRef o1, ClassRef o2) {
                return o1.getFullyQualifiedName().compareTo(o2.getFullyQualifiedName());
            }
        });

        for (ClassRef ref : refs) {
            String key = ref.getDefinition().getName();
            if (!mapping.containsKey(key)) {
                mapping.put(key, ref);
            }
        }
        return mapping;
    }

    public List<ClassRef> getReferences() {
        final List<ClassRef> refs = new ArrayList<ClassRef>();

        for (AnnotationRef a : annotations) {
            refs.addAll(a.getClassRef().getReferences());
        }

        for (ClassRef i : implementsList) {
            refs.addAll(i.getReferences());
        }

        for (ClassRef e : extendsList) {
            refs.addAll(e.getReferences());
        }

        for (Property property : properties) {
            refs.addAll(property.getReferences());
        }

        for (Method method : constructors) {
            refs.addAll(method.getReferences());
        }


        for (Method method : methods) {
            refs.addAll(method.getReferences());
        }

        for (TypeParamDef typeParamDef : parameters) {
            for (ClassRef bound : typeParamDef.getBounds()) {
                refs.addAll(bound.getReferences());
            }
        }

        for (TypeDef innerType : innerTypes) {
            refs.addAll(innerType.getReferences());
        }

        if (getAttributes().containsKey(ALSO_IMPORT)) {
            Object obj = getAttributes().get(ALSO_IMPORT);
            if (obj instanceof ClassRef) {
                refs.add((ClassRef) obj);
            } else if (obj instanceof Collection) {
                refs.addAll((Collection<? extends ClassRef>) obj);
            }
        }
        return refs;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (AnnotationRef annotationRef: annotations) {
            sb.append(annotationRef.toString()).append(SPACE);
        }

        if (isPublic()) {
            sb.append(PUBLIC).append(SPACE);
        } else if (isProtected()) {
            sb.append(PROTECTED).append(SPACE);
        } else if (isPrivate()) {
            sb.append(PRIVATE).append(SPACE);
        }
        if (isStatic()) {
            sb.append(STATIC).append(SPACE);
        }
        if (isAbstract()) {
            sb.append(ABSTRACT).append(SPACE);
        }
        if (isFinal()) {
            sb.append(FINAL).append(SPACE);
        }

        sb.append(kind.name().toLowerCase()).append(SPACE);
        sb.append(name);

        if (parameters != null && !parameters.isEmpty()) {
            sb.append(LT);
            sb.append(StringUtils.join(parameters, COMA));
            sb.append(GT);
        }

        if (extendsList != null && !extendsList.isEmpty()
                && (extendsList.size() != 1 || !extendsList.contains(OBJECT.toReference()))) {
            sb.append(SPACE).append(EXTENDS).append(SPACE);
            sb.append(StringUtils.join(extendsList, COMA));
        }

        if (implementsList != null && !implementsList.isEmpty()) {
            sb.append(SPACE).append(IMPLEMENTS).append(SPACE);
            sb.append(StringUtils.join(implementsList, COMA));
        }
        return sb.toString();
    }
}
