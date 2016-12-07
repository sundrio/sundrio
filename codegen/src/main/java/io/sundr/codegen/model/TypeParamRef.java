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

public class TypeParamRef extends TypeRef {

    private final String name;
    private final int dimensions;

    public TypeParamRef(String name, int dimensions, Map<AttributeKey, Object> attributes) {
        super(attributes);
        this.name = name;
        this.dimensions = dimensions;
    }

    public String getName() {
        return name;
    }

    public int getDimensions() {
        return dimensions;
    }

    public TypeParamRef withDimensions(int dimensions) {
        return new TypeParamRefBuilder(this).withDimensions(dimensions).build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeParamRef that = (TypeParamRef) o;

        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean isAssignableFrom(TypeRef ref) {
        return this.equals(ref);
    }
}
