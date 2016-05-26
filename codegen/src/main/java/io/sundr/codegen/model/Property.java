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


import java.util.Map;
import java.util.Set;

public class Property extends ModifierSupport {

    private final Set<ClassRef> annotations;
    private final TypeRef typeRef;
    private final String name;

    public Property(Set<ClassRef> annotations, TypeRef typeRef, String name, int modifiers, Map<String, Object> attributes) {
        super(modifiers, attributes);
        this.annotations = annotations;
        this.typeRef = typeRef;
        this.name = name;
    }

    public Set<ClassRef> getAnnotations() {
        return annotations;
    }

    public TypeRef getTypeRef() {
        return typeRef;
    }

    public String getName() {
        return name;
    }

    public String getNameCapitalized() {
        StringBuilder sb = new StringBuilder();
        sb.append(name.replaceAll("_", "").substring(0, 1).toUpperCase());
        sb.append(name.replaceAll("_", "").substring(1));
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        return name.equals(property.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (isPublic()) {
            sb.append("public ");
        } else if (isProtected()) {
            sb.append("protected ");
        } else if (isPrivate()) {
            sb.append("private ");
        }

        if (isSynchronized()) {
            sb.append("synchronized ");
        }

        if (isStatic()) {
            sb.append("static ");
        }

        if (isFinal()) {
            sb.append("final ");
        }

        sb.append(typeRef).append(" ");
        sb.append(name);

        return sb.toString();
    }
}
