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
import io.sundr.builder.VisitableBuilder;

import javax.lang.model.element.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;

public class JavaPropertyFluent<T extends JavaPropertyFluent<T>> extends AttributeSupportFluentImpl<T> implements Fluent<T> {

    Set<VisitableBuilder<JavaType, ?>> annotations = new LinkedHashSet();
    Set<Modifier> modifiers = new LinkedHashSet();
    VisitableBuilder<JavaType, ?> type;
    String name;
    boolean array;

    public T addToAnnotations(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.annotations.add(builder);
        }
        return (T) this;
    }

    public T removeFromAnnotations(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.remove(builder);
            this.annotations.remove(builder);
        }
        return (T) this;
    }

    public Set<JavaType> getAnnotations() {
        return build(annotations);
    }

    public T withAnnotations(Set<JavaType> annotations) {
        this.annotations.clear();
        if (annotations != null) {
            for (JavaType item : annotations) {
                this.addToAnnotations(item);
            }
        }
        return (T) this;
    }

    public T withAnnotations(JavaType... annotations) {
        this.annotations.clear();
        if (annotations != null) {
            for (JavaType item : annotations) {
                this.addToAnnotations(item);
            }
        }
        return (T) this;
    }

    public AnnotationsNested<T> addNewAnnotation() {
        return new AnnotationsNested<T>();
    }

    public AnnotationsNested<T> addNewAnnotationLike(JavaType item) {
        return new AnnotationsNested<T>(item);
    }

    public T addToModifiers(Modifier... items) {
        for (Modifier item : items) {
            this.modifiers.add(item);
        }
        return (T) this;
    }

    public T removeFromModifiers(Modifier... items) {
        for (Modifier item : items) {
            this.modifiers.remove(item);
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

    public T withModifiers(Modifier... modifiers) {
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

    public TypeNested<T> withNewTypeLike(JavaType item) {
        return new TypeNested<T>(item);
    }

    public TypeNested<T> editType() {
        return withNewTypeLike(getType());
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

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JavaPropertyFluent that = (JavaPropertyFluent) o;
        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null) return false;
        if (modifiers != null ? !modifiers.equals(that.modifiers) : that.modifiers != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (array != that.array) return false;
        return true;

    }

    public class AnnotationsNested<N> extends JavaTypeFluent<AnnotationsNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        AnnotationsNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        AnnotationsNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endAnnotation() {
            return and();
        }

        public N and() {
            return (N) JavaPropertyFluent.this.addToAnnotations(builder.build());
        }

    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        TypeNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        TypeNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endType() {
            return and();
        }

        public N and() {
            return (N) JavaPropertyFluent.this.withType(builder.build());
        }

    }


}
