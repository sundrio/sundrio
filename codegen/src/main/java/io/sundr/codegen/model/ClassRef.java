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

import io.sundr.codegen.utils.StringUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassRef extends TypeRef {

    public static final String UNKWNON = "<unkwnon>";
    public static final String BRACKETS = "[]";

    public static final ClassRef OBJECT = new ClassRefBuilder()
            .withDefinition(TypeDef.OBJECT)
            .build();

    private final TypeDef definition;
    private final int dimensions;
    private final List<TypeRef> arguments;

    public ClassRef(TypeDef definition, int dimensions, List<TypeRef> arguments, Map<String, Object> attributes) {
        super(attributes);
        this.definition = definition != null ? definition : new TypeDefBuilder().build();
        this.dimensions = dimensions;
        this.arguments = arguments;
    }

    public TypeDef getDefinition() {
        return definition;
    }

    public int getDimensions() {
        return dimensions;
    }

    public List<TypeRef> getArguments() {
        return arguments;
    }

    public ClassRef withDimensions(int dimensions) {
        return new ClassRefBuilder(this).withDimensions(dimensions).build();
    }

    public boolean isAssignableFrom(TypeRef other) {
        if (this == other) {
            return true;
        } else if (other == null) {
            return false;
        } else if (other instanceof PrimitiveRef) {
            if (!getDefinition().getPackageName().equals(JAVA_LANG)) {
                return false;
            }

            if(!getDefinition().getName().toUpperCase().startsWith(((PrimitiveRef) other).getName().toUpperCase())) {
                return false;
            }
            return true;
        }

        if (!(other instanceof ClassRef)) {
            return false;
        }

        if (this == other || this.equals(other)) {
            return true;
        }

        return definition.isAssignableFrom(((ClassRef) other).getDefinition());
    }

    public Set<ClassRef> getReferences() {
        Set<ClassRef> refs = new LinkedHashSet<ClassRef>();
        for (TypeRef argument : arguments) {
            if (argument instanceof ClassRef) {
                refs.addAll(((ClassRef)argument).getReferences());
            }
        }
        refs.add(this);
        return refs;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassRef classRef = (ClassRef) o;

        if (dimensions != classRef.dimensions) return false;
        if (definition != null ? !definition.equals(classRef.definition) : classRef.definition != null) return false;
        return arguments != null ? arguments.equals(classRef.arguments) : classRef.arguments == null;

    }

    @Override
    public int hashCode() {
        int result = definition != null ? definition.hashCode() : 0;
        result = 31 * result + dimensions;
        result = 31 * result + (arguments != null ? arguments.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (definition == null) {
            sb.append(UNKWNON);
        }
        else if (definition.getOuterType() != null) {
            sb.append(definition.getOuterType().getName()).append(DOT).append(definition.getName());
        } else {
            sb.append(definition.getName());
        }

        if (arguments.size() > 0) {
            sb.append(LT);
            sb.append(StringUtils.join(arguments, COMA));
            sb.append(GT);
        }

        for (int i=0;i<dimensions;i++) {
            sb.append(BRACKETS);
        }
        return sb.toString();
    }
}
