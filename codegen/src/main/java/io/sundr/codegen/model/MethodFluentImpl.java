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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MethodFluentImpl<A extends MethodFluent<A>> extends ModifierSupportFluentImpl<A> implements MethodFluent<A> {

    Set<TypeRef> annotations = new LinkedHashSet();
    Set<TypeParamDef> parameters = new LinkedHashSet();
    String name;
    TypeRef returnType;
    List<VisitableBuilder<Property, ?>> arguments = new ArrayList();
    Set<TypeRef> exceptions = new LinkedHashSet();
    Block block;

    public MethodFluentImpl() {

    }

    public MethodFluentImpl(Method instance) {
        this.withAnnotations(instance.getAnnotations());
        this.withParameters(instance.getParameters());
        this.withName(instance.getName());
        this.withReturnType(instance.getReturnType());
        this.withArguments(instance.getArguments());
        this.withExceptions(instance.getExceptions());
        this.withBlock(instance.getBlock());
        this.withModifiers(instance.getModifiers());
        this.withAttributes(instance.getAttributes());
    }

    public A addToAnnotations(TypeRef... items) {
        for (TypeRef item : items) {
            this.annotations.add(item);
        }
        return (A) this;
    }

    public A removeFromAnnotations(TypeRef... items) {
        for (TypeRef item : items) {
            this.annotations.remove(item);
        }
        return (A) this;
    }

    public Set<TypeRef> getAnnotations() {
        return this.annotations;
    }

    public A withAnnotations(Set<TypeRef> annotations) {
        this.annotations.clear();
        if (annotations != null) {
            for (TypeRef item : annotations) {
                this.addToAnnotations(item);
            }
        }
        return (A) this;
    }

    public A withAnnotations(TypeRef... annotations) {
        this.annotations.clear();
        if (annotations != null) {
            for (TypeRef item : annotations) {
                this.addToAnnotations(item);
            }
        }
        return (A) this;
    }

    public A addToParameters(TypeParamDef... items) {
        for (TypeParamDef item : items) {
            this.parameters.add(item);
        }
        return (A) this;
    }

    public A removeFromParameters(TypeParamDef... items) {
        for (TypeParamDef item : items) {
            this.parameters.remove(item);
        }
        return (A) this;
    }

    public Set<TypeParamDef> getParameters() {
        return this.parameters;
    }

    public A withParameters(Set<TypeParamDef> parameters) {
        this.parameters.clear();
        if (parameters != null) {
            for (TypeParamDef item : parameters) {
                this.addToParameters(item);
            }
        }
        return (A) this;
    }

    public A withParameters(TypeParamDef... parameters) {
        this.parameters.clear();
        if (parameters != null) {
            for (TypeParamDef item : parameters) {
                this.addToParameters(item);
            }
        }
        return (A) this;
    }

    public String getName() {
        return this.name;
    }

    public A withName(String name) {
        this.name = name;
        return (A) this;
    }

    public TypeRef getReturnType() {
        return this.returnType;
    }

    public A withReturnType(TypeRef returnType) {
        this.returnType = returnType;
        return (A) this;
    }

    public A withArguments(Property... arguments) {
        this.arguments.clear();
        if (arguments != null) {
            for (Property item : arguments) {
                this.addToArguments(item);
            }
        }
        return (A) this;
    }

    public Property[] getArguments() {
        List<Property> result = new ArrayList<Property>();
        for (VisitableBuilder<Property, ?> builder : arguments) {
            result.add(builder.build());
        }
        return result.toArray(new Property[result.size()]);

    }

    public A addToArguments(Property... items) {
        for (Property item : items) {
            PropertyBuilder builder = new PropertyBuilder(item);
            _visitables.add(builder);
            this.arguments.add(builder);
        }
        return (A) this;
    }

    public A removeFromArguments(Property... items) {
        for (Property item : items) {
            PropertyBuilder builder = new PropertyBuilder(item);
            _visitables.remove(builder);
            this.arguments.remove(builder);
        }
        return (A) this;
    }

    public ArgumentsNested<A> addNewArgument() {
        return new ArgumentsNestedImpl();
    }

    public ArgumentsNested<A> addNewArgumentLike(Property item) {
        return new ArgumentsNestedImpl(item);
    }

    public A addToExceptions(TypeRef... items) {
        for (TypeRef item : items) {
            this.exceptions.add(item);
        }
        return (A) this;
    }

    public A removeFromExceptions(TypeRef... items) {
        for (TypeRef item : items) {
            this.exceptions.remove(item);
        }
        return (A) this;
    }

    public Set<TypeRef> getExceptions() {
        return this.exceptions;
    }

    public A withExceptions(Set<TypeRef> exceptions) {
        this.exceptions.clear();
        if (exceptions != null) {
            for (TypeRef item : exceptions) {
                this.addToExceptions(item);
            }
        }
        return (A) this;
    }

    public A withExceptions(TypeRef... exceptions) {
        this.exceptions.clear();
        if (exceptions != null) {
            for (TypeRef item : exceptions) {
                this.addToExceptions(item);
            }
        }
        return (A) this;
    }

    public Block getBlock() {
        return this.block;
    }

    public A withBlock(Block block) {
        this.block = block;
        return (A) this;
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MethodFluentImpl that = (MethodFluentImpl) o;
        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null) return false;
        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null) return false;
        if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null) return false;
        if (exceptions != null ? !exceptions.equals(that.exceptions) : that.exceptions != null) return false;
        if (block != null ? !block.equals(that.block) : that.block != null) return false;
        return true;

    }

    public class ArgumentsNestedImpl<N> extends PropertyFluentImpl<ArgumentsNested<N>> implements ArgumentsNested<N> {

        private final PropertyBuilder builder;

        ArgumentsNestedImpl() {
            this.builder = new PropertyBuilder(this);
        }

        ArgumentsNestedImpl(Property item) {
            this.builder = new PropertyBuilder(this, item);
        }

        public N endArgument() {
            return and();
        }

        public N and() {
            return (N) MethodFluentImpl.this.addToArguments(builder.build());
        }

    }


}
