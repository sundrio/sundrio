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

public class TypeParamRefFluentImpl<A extends TypeParamRefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeParamRefFluent<A> {

    String name;
    VisitableBuilder<TypeParamDef, ?> definition;

    public TypeParamRefFluentImpl() {

    }

    public TypeParamRefFluentImpl(TypeParamRef instance) {
        this.withName(instance.getName());
        this.withDefinition(instance.getDefinition());
        this.withAttributes(instance.getAttributes());
    }

    public String getName() {
        return this.name;
    }

    public A withName(String name) {
        this.name = name;
        return (A) this;
    }

    public TypeParamDef getDefinition() {
        return this.definition != null ? this.definition.build() : null;
    }

    public A withDefinition(TypeParamDef definition) {
        if (definition != null) {
            this.definition = new TypeParamDefBuilder(definition);
            _visitables.add(this.definition);
        }
        return (A) this;
    }

    public DefinitionNested<A> withNewDefinition() {
        return new DefinitionNestedImpl();
    }

    public DefinitionNested<A> withNewDefinitionLike(TypeParamDef item) {
        return new DefinitionNestedImpl(item);
    }

    public DefinitionNested<A> editDefinition() {
        return withNewDefinitionLike(getDefinition());
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TypeParamRefFluentImpl that = (TypeParamRefFluentImpl) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;
        return true;

    }

    public class DefinitionNestedImpl<N> extends TypeParamDefFluentImpl<DefinitionNested<N>> implements DefinitionNested<N> {

        private final TypeParamDefBuilder builder;

        DefinitionNestedImpl() {
            this.builder = new TypeParamDefBuilder(this);
        }

        DefinitionNestedImpl(TypeParamDef item) {
            this.builder = new TypeParamDefBuilder(this, item);
        }

        public N endDefinition() {
            return and();
        }

        public N and() {
            return (N) TypeParamRefFluentImpl.this.withDefinition(builder.build());
        }

    }


}
