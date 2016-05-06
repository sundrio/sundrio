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

import java.util.ArrayList;
import java.util.List;

public class TypeRefFluentImpl<A extends TypeRefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeRefFluent<A> {

    VisitableBuilder<TypeDef, ?> definition;
    int dimensions;
    List<ParameterReference> arguments = new ArrayList();
    List<VisitableBuilder<TypeParamRef, ?>> typeParamRefArguments = new ArrayList();
    List<VisitableBuilder<TypeRef, ?>> typeRefArguments = new ArrayList();

    public TypeRefFluentImpl() {

    }

    public TypeRefFluentImpl(TypeRef instance) {
        this.withDefinition(instance.getDefinition());
        this.withDimensions(instance.getDimensions());
        this.withArguments(instance.getArguments());
        this.withAttributes(instance.getAttributes());
    }

    public TypeDef getDefinition() {
        return this.definition != null ? this.definition.build() : null;
    }

    public A withDefinition(TypeDef definition) {
        if (definition != null) {
            this.definition = new TypeDefBuilder(definition);
            _visitables.add(this.definition);
        }
        return (A) this;
    }

    public DefinitionNested<A> withNewDefinition() {
        return new DefinitionNestedImpl();
    }

    public DefinitionNested<A> withNewDefinitionLike(TypeDef item) {
        return new DefinitionNestedImpl(item);
    }

    public DefinitionNested<A> editDefinition() {
        return withNewDefinitionLike(getDefinition());
    }

    public int getDimensions() {
        return this.dimensions;
    }

    public A withDimensions(int dimensions) {
        this.dimensions = dimensions;
        return (A) this;
    }

    public A withArguments(ParameterReference... arguments) {
        this.arguments.clear();
        if (arguments != null) {
            for (ParameterReference item : arguments) {
                this.addToArguments(item);
            }
        }
        return (A) this;
    }

    public ParameterReference[] getArguments() {
        ParameterReference[] result = new ParameterReference[arguments.size()];
        int index = 0;
        for (ParameterReference item : arguments) {
            result[index++] = item;
        }
        return result;

    }

    public A addToArguments(ParameterReference... items) {
        for (ParameterReference item : items) {
            if (item instanceof TypeParamRef) {
                addToTypeParamRefArguments((TypeParamRef) item);
            } else if (item instanceof TypeRef) {
                addToTypeRefArguments((TypeRef) item);
            }
        }
        return (A) this;
    }

    public A removeFromArguments(ParameterReference... items) {
        for (ParameterReference item : items) {
            if (item instanceof TypeParamRef) {
                removeFromTypeParamRefArguments((TypeParamRef) item);
            } else if (item instanceof TypeRef) {
                removeFromTypeRefArguments((TypeRef) item);
            }
        }
        return (A) this;
    }

    public A addToTypeParamRefArguments(TypeParamRef... items) {
        for (TypeParamRef item : items) {
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
            _visitables.add(builder);
            this.typeParamRefArguments.add(builder);
        }
        return (A) this;
    }

    public A removeFromTypeParamRefArguments(TypeParamRef... items) {
        for (TypeParamRef item : items) {
            TypeParamRefBuilder builder = new TypeParamRefBuilder(item);
            _visitables.remove(builder);
            this.typeParamRefArguments.remove(builder);
        }
        return (A) this;
    }

    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgument() {
        return new TypeParamRefArgumentsNestedImpl();
    }

    public TypeParamRefArgumentsNested<A> addNewTypeParamRefArgumentLike(TypeParamRef item) {
        return new TypeParamRefArgumentsNestedImpl(item);
    }

    public A addToTypeRefArguments(TypeRef... items) {
        for (TypeRef item : items) {
            TypeRefBuilder builder = new TypeRefBuilder(item);
            _visitables.add(builder);
            this.typeRefArguments.add(builder);
        }
        return (A) this;
    }

    public A removeFromTypeRefArguments(TypeRef... items) {
        for (TypeRef item : items) {
            TypeRefBuilder builder = new TypeRefBuilder(item);
            _visitables.remove(builder);
            this.typeRefArguments.remove(builder);
        }
        return (A) this;
    }

    public TypeRefArgumentsNested<A> addNewTypeRefArgument() {
        return new TypeRefArgumentsNestedImpl();
    }

    public TypeRefArgumentsNested<A> addNewTypeRefArgumentLike(TypeRef item) {
        return new TypeRefArgumentsNestedImpl(item);
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TypeRefFluentImpl that = (TypeRefFluentImpl) o;
        if (definition != null ? !definition.equals(that.definition) : that.definition != null) return false;
        if (dimensions != that.dimensions) return false;
        if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null) return false;
        if (typeParamRefArguments != null ? !typeParamRefArguments.equals(that.typeParamRefArguments) : that.typeParamRefArguments != null)
            return false;
        if (typeRefArguments != null ? !typeRefArguments.equals(that.typeRefArguments) : that.typeRefArguments != null)
            return false;
        return true;

    }

    public class DefinitionNestedImpl<N> extends TypeDefFluentImpl<DefinitionNested<N>> implements DefinitionNested<N> {

        private final TypeDefBuilder builder;

        DefinitionNestedImpl() {
            this.builder = new TypeDefBuilder(this);
        }

        DefinitionNestedImpl(TypeDef item) {
            this.builder = new TypeDefBuilder(this, item);
        }

        public N endDefinition() {
            return and();
        }

        public N and() {
            return (N) TypeRefFluentImpl.this.withDefinition(builder.build());
        }

    }

    public class TypeParamRefArgumentsNestedImpl<N> extends TypeParamRefFluentImpl<TypeParamRefArgumentsNested<N>> implements TypeParamRefArgumentsNested<N> {

        private final TypeParamRefBuilder builder;

        TypeParamRefArgumentsNestedImpl() {
            this.builder = new TypeParamRefBuilder(this);
        }

        TypeParamRefArgumentsNestedImpl(TypeParamRef item) {
            this.builder = new TypeParamRefBuilder(this, item);
        }

        public N endTypeParamRefArgument() {
            return and();
        }

        public N and() {
            return (N) TypeRefFluentImpl.this.addToTypeParamRefArguments(builder.build());
        }

    }

    public class TypeRefArgumentsNestedImpl<N> extends TypeRefFluentImpl<TypeRefArgumentsNested<N>> implements TypeRefArgumentsNested<N> {

        private final TypeRefBuilder builder;

        TypeRefArgumentsNestedImpl(TypeRef item) {
            this.builder = new TypeRefBuilder(this, item);
        }

        TypeRefArgumentsNestedImpl() {
            this.builder = new TypeRefBuilder(this);
        }

        public N endTypeRefArgument() {
            return and();
        }

        public N and() {
            return (N) TypeRefFluentImpl.this.addToTypeRefArguments(builder.build());
        }

    }


}
