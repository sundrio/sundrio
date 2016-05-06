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

package io.sundr.examples.codegen;

import io.sundr.builder.annotations.Buildable;
import io.sundr.codegen.utils.StringUtils;

import java.util.Arrays;
import java.util.Map;

@Buildable
public class ClassRef extends AttributeSupport implements TypeRef {

    private final TypeDef definition;
    private final int dimensions;
    private final TypeRef[] arguments;

    public ClassRef(TypeDef definition, int dimensions, TypeRef[] arguments, Map<String, Object> attributes) {
        super(attributes);
        this.definition = definition;
        this.dimensions = dimensions;
        this.arguments = arguments;
    }

    public TypeDef getDefinition() {
        return definition;
    }

    public int getDimensions() {
        return dimensions;
    }

    public TypeRef[] getArguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassRef that = (ClassRef) o;

        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(arguments, that.arguments);

    }

    @Override
    public int hashCode() {
        int result = definition != null ? definition.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (definition != null) {
            sb.append(definition.getFullyQualifiedName());
        } else {
            sb.append("<unknown>");
        }

        if (arguments.length > 0) {
            sb.append("<");
            sb.append(StringUtils.join(arguments, ","));
            sb.append(">");
        }

        for (int i=0;i<dimensions;i++) {
            sb.append("[]");
        }
        return sb.toString();
    }
}
