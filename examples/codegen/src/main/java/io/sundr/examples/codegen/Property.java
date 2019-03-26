/*
 *      Copyright 2019 The original authors.
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

package io.sundr.examples.codegen;


import io.sundr.builder.annotations.Buildable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Buildable(lazyCollectionInitEnabled=false)
public class Property extends ModifierSupport {

    private final List<AnnotationRef> annotations;
    private final TypeRef typeRef;
    private final String name;

    public Property(List<AnnotationRef> annotations, TypeRef typeRef, String name, int modifiers, Map<AttributeKey, Object> attributes) {
        super(modifiers, attributes);
        this.annotations = annotations;
        this.typeRef = typeRef;
        this.name = name;
    }

    public List<AnnotationRef> getAnnotations() {
        return annotations;
    }

    public TypeRef getTypeRef() {
        return typeRef;
    }

    public String getName() {
        return name;
    }

    public String getNameCapitalized() {
        return Stream.of(name.split("[^a-zA-Z0-9]"))
            .filter(s -> s != null && s.length() > 0)
            .map(v -> Character.toUpperCase(v.charAt(0)) + v.substring(1))
            .collect(Collectors.joining());
    }

    public Set<ClassRef> getReferences() {
        Set<ClassRef> refs = new LinkedHashSet<ClassRef>();

        for (AnnotationRef annotationRef : annotations) {
            refs.addAll(annotationRef.getReferences());
        }

        if (typeRef instanceof ClassRef) {
            ClassRef classRef = (ClassRef) typeRef;
            refs.addAll(classRef.getReferences());
        }
        for (AnnotationRef a : getAnnotations()) {
            refs.addAll(a.getClassRef().getReferences());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        if (typeRef != null ? !typeRef.equals(property.typeRef) : property.typeRef != null)
            return false;
        return name != null ? name.equals(property.name) : property.name == null;

    }

    @Override
    public int hashCode() {
        int result = typeRef != null ? typeRef.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

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

        if (isFinal()) {
            sb.append(FINAL).append(SPACE);
        }

        sb.append(typeRef).append(SPACE);
        sb.append(name);

        return sb.toString();
    }
}
