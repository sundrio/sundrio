/*
 *      Copyright 2016 The original authors.
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package io.sundr.codegen.model;

import io.sundr.builder.VisitableBuilder;

import java.lang.Boolean;
import java.lang.Object;

public class VoidRefBuilder extends VoidRefFluentImpl<VoidRefBuilder> implements VisitableBuilder<VoidRef, VoidRefBuilder> {

    VoidRefFluent<?> fluent;
    Boolean validationEnabled;

    public VoidRefBuilder() {
        this(true);
    }

    public VoidRefBuilder(Boolean validationEnabled) {
        this(new VoidRef(), validationEnabled);
    }

    public VoidRefBuilder(VoidRefFluent<?> fluent) {
        this(fluent, true);
    }

    public VoidRefBuilder(VoidRefFluent<?> fluent, Boolean validationEnabled) {
        this(fluent, new VoidRef(), validationEnabled);
    }

    public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance) {
        this(fluent, instance, true);
    }

    public VoidRefBuilder(VoidRefFluent<?> fluent, VoidRef instance, Boolean validationEnabled) {
        this.fluent = fluent;
        fluent.withAttributes(instance.getAttributes());
        this.validationEnabled = validationEnabled;
    }

    public VoidRefBuilder(VoidRef instance) {
        this(instance, true);
    }

    public VoidRefBuilder(VoidRef instance, Boolean validationEnabled) {
        this.fluent = this;
        this.withAttributes(instance.getAttributes());
        this.validationEnabled = validationEnabled;
    }

    public EditableVoidRef build() {
        EditableVoidRef buildable = new EditableVoidRef(fluent.getAttributes());
        validate(buildable);
        return buildable;
    }

    private <T> void validate(T item) {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VoidRefBuilder that = (VoidRefBuilder) o;
        if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
            return false;

        if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
            return false;
        return true;
    }


}
