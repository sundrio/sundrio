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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaTypeFluent<T extends JavaTypeFluent<T>> extends AttributeSupportFluentImpl<T> implements Fluent<T> {

    JavaKind kind;
    String packageName;
    String className;
    boolean array;
    boolean collection;
    boolean concrete;
    VisitableBuilder<JavaType, ?> defaultImplementation;
    VisitableBuilder<JavaType, ?> superClass;
    Set<VisitableBuilder<JavaType, ?>> interfaces = new LinkedHashSet();
    List<VisitableBuilder<JavaType, ?>> genericTypes = new ArrayList();

    public JavaKind getKind() {
        return this.kind;
    }

    public T withKind(JavaKind kind) {
        this.kind = kind;
        return (T) this;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public T withPackageName(String packageName) {
        this.packageName = packageName;
        return (T) this;
    }

    public String getClassName() {
        return this.className;
    }

    public T withClassName(String className) {
        this.className = className;
        return (T) this;
    }

    public boolean isArray() {
        return this.array;
    }

    public T withArray(boolean array) {
        this.array = array;
        return (T) this;
    }

    public boolean isCollection() {
        return this.collection;
    }

    public T withCollection(boolean collection) {
        this.collection = collection;
        return (T) this;
    }

    public boolean isConcrete() {
        return this.concrete;
    }

    public T withConcrete(boolean concrete) {
        this.concrete = concrete;
        return (T) this;
    }

    public JavaType getDefaultImplementation() {
        return this.defaultImplementation != null ? this.defaultImplementation.build() : null;
    }

    public T withDefaultImplementation(JavaType defaultImplementation) {
        if (defaultImplementation != null) {
            this.defaultImplementation = new JavaTypeBuilder(defaultImplementation);
            _visitables.add(this.defaultImplementation);
        } else {
            this.defaultImplementation = null;
        }
        return (T) this;
    }

    public DefaultImplementationNested<T> withNewDefaultImplementation() {
        return new DefaultImplementationNested<T>();
    }

    public DefaultImplementationNested<T> withNewDefaultImplementationLike(JavaType item) {
        return new DefaultImplementationNested<T>(item);
    }

    public DefaultImplementationNested<T> editDefaultImplementation() {
        return withNewDefaultImplementationLike(getDefaultImplementation());
    }

    public JavaType getSuperClass() {
        return this.superClass != null ? this.superClass.build() : null;
    }

    public T withSuperClass(JavaType superClass) {
        if (superClass != null) {
            this.superClass = new JavaTypeBuilder(superClass);
            _visitables.add(this.superClass);
        } else {
            this.superClass = null;
        }
        return (T) this;
    }

    public SuperClassNested<T> withNewSuperClass() {
        return new SuperClassNested<T>();
    }

    public SuperClassNested<T> withNewSuperClassLike(JavaType item) {
        return new SuperClassNested<T>(item);
    }

    public SuperClassNested<T> editSuperClass() {
        return withNewSuperClassLike(getSuperClass());
    }

    public T addToInterfaces(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.interfaces.add(builder);
        }
        return (T) this;
    }

    public T removeFromInterfaces(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.remove(builder);
            this.interfaces.remove(builder);
        }
        return (T) this;
    }

    public Set<JavaType> getInterfaces() {
        return build(interfaces);
    }

    public T withInterfaces(Set<JavaType> interfaces) {
        this.interfaces.clear();
        if (interfaces != null) {
            for (JavaType item : interfaces) {
                this.addToInterfaces(item);
            }
        }
        return (T) this;
    }

    public T withInterfaces(JavaType... interfaces) {
        this.interfaces.clear();
        if (interfaces != null) {
            for (JavaType item : interfaces) {
                this.addToInterfaces(item);
            }
        }
        return (T) this;
    }

    public InterfacesNested<T> addNewInterface() {
        return new InterfacesNested<T>();
    }

    public InterfacesNested<T> addNewInterfaceLike(JavaType item) {
        return new InterfacesNested<T>(item);
    }

    public T withGenericTypes(JavaType... genericTypes) {
        this.genericTypes.clear();
        if (genericTypes != null) {
            for (JavaType item : genericTypes) {
                this.addToGenericTypes(item);
            }
        }
        return (T) this;
    }

    public JavaType[] getGenericTypes() {
        List<JavaType> result = new ArrayList<JavaType>();
        for (VisitableBuilder<JavaType, ?> builder : genericTypes) {
            result.add(builder.build());
        }
        return result.toArray(new JavaType[result.size()]);

    }

    public T addToGenericTypes(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.genericTypes.add(builder);
        }
        return (T) this;
    }

    public T removeFromGenericTypes(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.remove(builder);
            this.genericTypes.remove(builder);
        }
        return (T) this;
    }

    public GenericTypesNested<T> addNewGenericType() {
        return new GenericTypesNested<T>();
    }

    public GenericTypesNested<T> addNewGenericTypeLike(JavaType item) {
        return new GenericTypesNested<T>(item);
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JavaTypeFluent that = (JavaTypeFluent) o;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (packageName != null ? !packageName.equals(that.packageName) : that.packageName != null) return false;
        if (className != null ? !className.equals(that.className) : that.className != null) return false;
        if (array != that.array) return false;
        if (collection != that.collection) return false;
        if (concrete != that.concrete) return false;
        if (defaultImplementation != null ? !defaultImplementation.equals(that.defaultImplementation) : that.defaultImplementation != null)
            return false;
        if (superClass != null ? !superClass.equals(that.superClass) : that.superClass != null) return false;
        if (interfaces != null ? !interfaces.equals(that.interfaces) : that.interfaces != null) return false;
        if (genericTypes != null ? !genericTypes.equals(that.genericTypes) : that.genericTypes != null) return false;
        return true;

    }

    public class DefaultImplementationNested<N> extends JavaTypeFluent<DefaultImplementationNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        DefaultImplementationNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        DefaultImplementationNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endDefaultImplementation() {
            return and();
        }

        public N and() {
            return (N) JavaTypeFluent.this.withDefaultImplementation(builder.build());
        }

    }

    public class SuperClassNested<N> extends JavaTypeFluent<SuperClassNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        SuperClassNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        SuperClassNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N and() {
            return (N) JavaTypeFluent.this.withSuperClass(builder.build());
        }

        public N endSuperClass() {
            return and();
        }

    }

    public class InterfacesNested<N> extends JavaTypeFluent<InterfacesNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        InterfacesNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        InterfacesNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endInterface() {
            return and();
        }

        public N and() {
            return (N) JavaTypeFluent.this.addToInterfaces(builder.build());
        }

    }

    public class GenericTypesNested<N> extends JavaTypeFluent<GenericTypesNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        GenericTypesNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        GenericTypesNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endGenericType() {
            return and();
        }

        public N and() {
            return (N) JavaTypeFluent.this.addToGenericTypes(builder.build());
        }

    }


}
