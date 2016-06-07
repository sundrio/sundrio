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
import java.util.List;
import java.util.Map;

@Buildable
public class TypeParamDef extends AttributeSupport {

    private final String name;
    private final List<ClassRef> bounds;

    public TypeParamDef(String name, List<ClassRef> bounds, Map<String, Object> attributes) {
        super(attributes);
        this.name = name;
        this.bounds = bounds;
    }

    public String getName() {
        return name;
    }

    public List<ClassRef> getBounds() {
        return bounds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeParamDef that = (TypeParamDef) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return bounds != null ? bounds.equals(that.bounds) : that.bounds == null;

    }

    public TypeParamRef toReference() {
        return new TypeParamRefBuilder()
                .withName(name)
                .build();
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (bounds != null ? bounds.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (bounds != null && !bounds.isEmpty()) {
            sb.append(" extends ");
            sb.append(StringUtils.join(bounds, ","));
        }
        return sb.toString();
    }
}
