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

import java.util.Arrays;
import java.util.Map;

public class TypeParamDef extends AttributeSupport {

    private final String name;
    private final TypeRef[] bounds;

    public TypeParamDef(String name, TypeRef[] bounds, Map<String, Object> attributes) {
        super(attributes);
        this.name = name;
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public TypeRef[] getBounds() {
        return bounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeParamDef that = (TypeParamDef) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(bounds, that.bounds);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + Arrays.hashCode(bounds);
        return result;
    }
}
