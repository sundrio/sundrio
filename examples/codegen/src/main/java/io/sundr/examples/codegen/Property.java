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

import java.util.Map;
import java.util.Set;

@Buildable
public class Property extends ModifierSupport {

    private final Set<ClassRef> annotations;
    private final ClassRef classRef;
    private final String name;

    public Property(Set<ClassRef> annotations, ClassRef classRef, String name, int modifiers, Map<String, Object> attributes) {
        super(modifiers, attributes);
        this.annotations = annotations;
        this.classRef = classRef;
        this.name = name;
    }

    public Set<ClassRef> getAnnotations() {
        return annotations;
    }

    public ClassRef getClassRef() {
        return classRef;
    }

    public String getName() {
        return name;
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
}
