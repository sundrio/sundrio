/*
 * Copyright 2015 The original authors.
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

import io.sundr.builder.Builder;

public class AttributeSupportBuilder extends AttributeSupportFluent<AttributeSupportBuilder> implements Builder<AttributeSupport> {

    AttributeSupportFluent<?> fluent;

    public AttributeSupportBuilder() {
        this.fluent = this;
    }

    public AttributeSupportBuilder(AttributeSupportFluent<?> fluent) {
        this.fluent = fluent;
    }

    public AttributeSupportBuilder(AttributeSupportFluent<?> fluent, AttributeSupport instance) {
        this.fluent = fluent;
        fluent.withAttributes(instance.getAttributes());
    }

    public AttributeSupportBuilder(AttributeSupport instance) {
        this.fluent = this;
        this.withAttributes(instance.getAttributes());
    }

    public EditableAttributeSupport build() {
        EditableAttributeSupport buildable = new EditableAttributeSupport(fluent.getAttributes());
        validate(buildable);
        return buildable;

    }

    private <T> void validate(T item) {
    }


}
    