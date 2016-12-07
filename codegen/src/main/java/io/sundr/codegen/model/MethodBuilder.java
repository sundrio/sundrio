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

public class MethodBuilder extends MethodFluentImpl<MethodBuilder> implements VisitableBuilder<Method, MethodBuilder> {

    MethodFluent<?> fluent;
    Boolean validationEnabled;

    public MethodBuilder() {
        this(true);
    }

    public MethodBuilder(Boolean validationEnabled) {
        this.fluent = this;
        this.validationEnabled = validationEnabled;
    }

    public MethodBuilder(MethodFluent<?> fluent) {
        this(fluent, true);
    }

    public MethodBuilder(MethodFluent<?> fluent, Boolean validationEnabled) {
        this.fluent = fluent;
        this.validationEnabled = validationEnabled;
    }

    public MethodBuilder(MethodFluent<?> fluent, Method instance) {
        this(fluent, instance, true);
    }

    public MethodBuilder(MethodFluent<?> fluent, Method instance, Boolean validationEnabled) {
        this.fluent = fluent;
        fluent.withAnnotations(instance.getAnnotations());
        fluent.withParameters(instance.getParameters());
        fluent.withName(instance.getName());
        fluent.withReturnType(instance.getReturnType());
        fluent.withArguments(instance.getArguments());
        fluent.withVarArgPreferred(instance.isVarArgPreferred());
        fluent.withExceptions(instance.getExceptions());
        fluent.withBlock(instance.getBlock());
        fluent.withModifiers(instance.getModifiers());
        fluent.withAttributes(instance.getAttributes());
        this.validationEnabled = validationEnabled;
    }

    public MethodBuilder(Method instance) {
        this(instance, true);
    }

    public MethodBuilder(Method instance, Boolean validationEnabled) {
        this.fluent = this;
        this.withAnnotations(instance.getAnnotations());
        this.withParameters(instance.getParameters());
        this.withName(instance.getName());
        this.withReturnType(instance.getReturnType());
        this.withArguments(instance.getArguments());
        this.withVarArgPreferred(instance.isVarArgPreferred());
        this.withExceptions(instance.getExceptions());
        this.withBlock(instance.getBlock());
        this.withModifiers(instance.getModifiers());
        this.withAttributes(instance.getAttributes());
        this.validationEnabled = validationEnabled;
    }

    public EditableMethod build() {
        EditableMethod buildable = new EditableMethod(fluent.getAnnotations(), fluent.getParameters(), fluent.getName(), fluent.getReturnType(), fluent.getArguments(), fluent.isVarArgPreferred(), fluent.getExceptions(), fluent.getBlock(), fluent.getModifiers(), fluent.getAttributes());
        validate(buildable);
        return buildable;
    }

    private <T> void validate(T item) {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MethodBuilder that = (MethodBuilder) o;
        if (fluent != null && fluent != this ? !fluent.equals(that.fluent) : that.fluent != null && fluent != this)
            return false;

        if (validationEnabled != null ? !validationEnabled.equals(that.validationEnabled) : that.validationEnabled != null)
            return false;
        return true;
    }


}
