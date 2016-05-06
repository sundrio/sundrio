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

import java.util.LinkedHashSet;
import java.util.Set;

public class PropertyFluentImpl<A extends PropertyFluent<A>> extends ModifierSupportFluentImpl<A> implements PropertyFluent<A> {

    Set<TypeRef> annotations = new LinkedHashSet();
    TypeRef typeRef;
    String name;

    public PropertyFluentImpl() {

    }

    public PropertyFluentImpl(Property instance) {
        this.withAnnotations(instance.getAnnotations());
        this.withTypeRef(instance.getTypeRef());
        this.withName(instance.getName());
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

    public TypeRef getTypeRef() {
        return this.typeRef;
    }

    public A withTypeRef(TypeRef typeRef) {
        this.typeRef = typeRef;
        return (A) this;
    }

    public String getName() {
        return this.name;
    }

    public A withName(String name) {
        this.name = name;
        return (A) this;
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PropertyFluentImpl that = (PropertyFluentImpl) o;
        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null) return false;
        if (typeRef != null ? !typeRef.equals(that.typeRef) : that.typeRef != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return true;

    }


}
