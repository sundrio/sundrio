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

import io.sundr.builder.Fluent;
import io.sundr.builder.Nested;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

public class JavaPropertyFluent<T extends JavaPropertyFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    Set<Modifier> modifiers = new LinkedHashSet();
    JavaTypeBuilder type;
    String name;
    boolean array;

    public T addToModifiers(Modifier item) {
        if (item != null) {
            this.modifiers.add(item);
        }
        return (T) this;
    }

    public Set<Modifier> getModifiers() {
        return this.modifiers;
    }

    public T withModifiers(Set<Modifier> modifiers) {
        this.modifiers.clear();
        if (modifiers != null) {
            for (Modifier item : modifiers) {
                this.addToModifiers(item);
            }
        }
        return (T) this;
    }

    public JavaType getType() {
        return this.type != null ? this.type.build() : null;
    }

    public T withType(JavaType type) {
        if (type != null) {
            this.type = new JavaTypeBuilder(type);
            _visitables.add(this.type);
        }
        return (T) this;
    }

    public TypeNested<T> withNewType() {
        return new TypeNested<T>();
    }

    public String getName() {
        return this.name;
    }

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public boolean isArray() {
        return this.array;
    }

    public T withArray(boolean array) {
        this.array = array;
        return (T) this;
    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder = new JavaTypeBuilder(this);

        public N and() {
            return (N) JavaPropertyFluent.this.withType(builder.build());
        }

        public N endType() {
            return and();
        }

    }


}
