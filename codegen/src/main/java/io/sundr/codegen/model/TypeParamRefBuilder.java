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

public class TypeParamRefBuilder extends TypeParamRefFluentImpl<TypeParamRefBuilder> implements VisitableBuilder<TypeParamRef, TypeParamRefBuilder> {

    TypeParamRefFluent<?> fluent;

    public TypeParamRefBuilder() {
        this.fluent = this;
    }

    public TypeParamRefBuilder(TypeParamRefFluent<?> fluent) {
        this.fluent = fluent;
    }

    public TypeParamRefBuilder(TypeParamRefFluent<?> fluent, TypeParamRef instance) {
        this.fluent = fluent;
        fluent.withName(instance.getName());
        fluent.withDefinition(instance.getDefinition());
        fluent.withAttributes(instance.getAttributes());
    }

    public TypeParamRefBuilder(TypeParamRef instance) {
        this.fluent = this;
        this.withName(instance.getName());
        this.withDefinition(instance.getDefinition());
        this.withAttributes(instance.getAttributes());
    }

    public EditableTypeParamRef build() {
        EditableTypeParamRef buildable = new EditableTypeParamRef(fluent.getName(), fluent.getDefinition(), fluent.getAttributes());
        validate(buildable);
        return buildable;

    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TypeParamRefBuilder that = (TypeParamRefBuilder) o;
        if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
            return false;
        return true;

    }

    private <T> void validate(T item) {
    }


}
    
