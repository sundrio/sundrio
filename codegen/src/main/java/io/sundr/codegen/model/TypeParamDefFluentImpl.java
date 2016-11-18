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

public class TypeParamDefFluentImpl<A extends TypeParamDefFluent<A>> extends AttributeSupportFluentImpl<A> implements TypeParamDefFluent<A> {

    String name;
    List<VisitableBuilder<ClassRef, ?>> bounds = new ArrayList();

    public TypeParamDefFluentImpl() {

    }

    public TypeParamDefFluentImpl(TypeParamDef instance) {
        this.withName(instance.getName());
        this.withBounds(instance.getBounds());
        this.withAttributes(instance.getAttributes());
    }

    public String getName() {
        return this.name;
    }

    public A withName(String name) {
        this.name = name;
        return (A) this;
    }

    public A addToBounds(ClassRef... items) {
        for (ClassRef item : items) {
            ClassRefBuilder builder = new ClassRefBuilder(item);
            _visitables.add(builder);
            this.bounds.add(builder);
        }
        return (A) this;
    }

    public A removeFromBounds(ClassRef... items) {
        for (ClassRef item : items) {
            ClassRefBuilder builder = new ClassRefBuilder(item);
            _visitables.remove(builder);
            this.bounds.remove(builder);
        }
        return (A) this;
    }

    public List<ClassRef> getBounds() {
        return build(bounds);
    }

    public A withBounds(List<ClassRef> bounds) {
        this.bounds.clear();
        if (bounds != null) {
            for (ClassRef item : bounds) {
                this.addToBounds(item);
            }
        }
        return (A) this;
    }

    public A withBounds(ClassRef... bounds) {
        this.bounds.clear();
        if (bounds != null) {
            for (ClassRef item : bounds) {
                this.addToBounds(item);
            }
        }
        return (A) this;
    }

    public BoundsNested<A> addNewBound() {
        return new BoundsNestedImpl();
    }

    public BoundsNested<A> addNewBoundLike(ClassRef item) {
        return new BoundsNestedImpl(item);
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TypeParamDefFluentImpl that = (TypeParamDefFluentImpl) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (bounds != null ? !bounds.equals(that.bounds) : that.bounds != null) return false;
        return true;

    }

    public class BoundsNestedImpl<N> extends ClassRefFluentImpl<BoundsNested<N>> implements BoundsNested<N> {

        private final ClassRefBuilder builder;

        BoundsNestedImpl() {
            this.builder = new ClassRefBuilder(this);
        }

        BoundsNestedImpl(ClassRef item) {
            this.builder = new ClassRefBuilder(this, item);
        }

        public N endBound() {
            return and();
        }

        public N and() {
            return (N) TypeParamDefFluentImpl.this.addToBounds(builder.build());
        }

    }


}
