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

import io.sundr.builder.VisitableBuilder;

public class PropertyBuilder extends PropertyFluentImpl<PropertyBuilder> implements VisitableBuilder<Property, PropertyBuilder> {

    PropertyFluent<?> fluent;

    public PropertyBuilder() {
        this.fluent = this;
    }

    public PropertyBuilder(PropertyFluent<?> fluent) {
        this.fluent = fluent;
    }

    public PropertyBuilder(PropertyFluent<?> fluent, Property instance) {
        this.fluent = fluent;
        fluent.withAnnotations(instance.getAnnotations());
        fluent.withTypeRef(instance.getTypeRef());
        fluent.withName(instance.getName());
        fluent.withModifiers(instance.getModifiers());
        fluent.withAttributes(instance.getAttributes());
    }

    public PropertyBuilder(Property instance) {
        this.fluent = this;
        this.withAnnotations(instance.getAnnotations());
        this.withTypeRef(instance.getTypeRef());
        this.withName(instance.getName());
        this.withModifiers(instance.getModifiers());
        this.withAttributes(instance.getAttributes());
    }

    public EditableProperty build() {
        EditableProperty buildable = new EditableProperty(fluent.getAnnotations(), fluent.getTypeRef(), fluent.getName(), fluent.getModifiers(), fluent.getAttributes());
        validate(buildable);
        return buildable;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PropertyBuilder that = (PropertyBuilder) o;
        if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
            return false;
        return true;

    }

    private <T> void validate(T item) {
    }


}
    
