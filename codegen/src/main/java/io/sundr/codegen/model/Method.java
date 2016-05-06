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

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class Method extends ModifierSupport {

    private final Set<TypeRef> annotations;
    private final Set<TypeParamDef> parameters;
    private final String name;
    private final TypeRef returnType;
    private final Property[] arguments;
    private final Set<TypeRef> exceptions;
    private final Block block;

    public Method(Set<TypeRef> annotations, Set<TypeParamDef> parameters, String name, TypeRef returnType, Property[] arguments, Set<TypeRef> exceptions, Block block, int modifiers, Map<String, Object> attributes) {
        super(modifiers, attributes);
        this.annotations = annotations;
        this.parameters = parameters;
        this.name = name;
        this.returnType = returnType;
        this.arguments = arguments;
        this.exceptions = exceptions;
        this.block = block;
    }

    public Set<TypeRef> getAnnotations() {
        return annotations;
    }

    public Set<TypeParamDef> getParameters() {
        return parameters;
    }

    public String getName() {
        return name;
    }

    public TypeRef getReturnType() {
        return returnType;
    }

    public Property[] getArguments() {
        return arguments;
    }

    public Set<TypeRef> getExceptions() {
        return exceptions;
    }

    public Block getBlock() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        if (!name.equals(method.name)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(arguments, method.arguments);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + Arrays.hashCode(arguments);
        return result;
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

        sb.append(returnType).append(" ");
        sb.append(name);

        sb.append("(");
        StringUtils.join(arguments, "");
        sb.append(")");

        return sb.toString();
    }
}
