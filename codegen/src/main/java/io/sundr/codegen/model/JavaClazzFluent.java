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

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaClazzFluent<T extends JavaClazzFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    JavaTypeBuilder type;
    Set<JavaMethodBuilder> methods = new LinkedHashSet();
    Set<JavaMethodBuilder> constructors = new LinkedHashSet();
    Set<JavaPropertyBuilder> fields = new LinkedHashSet();
    Set<JavaTypeBuilder> imports = new LinkedHashSet();
    Set<JavaClazzBuilder> nested = new LinkedHashSet();

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

    public T addToMethods(JavaMethod ...items) {
        for (JavaMethod item : items) {
            JavaMethodBuilder builder = new JavaMethodBuilder(item);
            _visitables.add(builder);
            this.methods.add(builder);
        }
        return (T) this;
    }

    public Set<JavaMethod> getMethods() {
        return build(methods);
    }

    public T withMethods(Set<JavaMethod> methods) {
        this.methods.clear();
        if (methods != null) {
            for (JavaMethod item : methods) {
                this.addToMethods(item);
            }
        }
        return (T) this;
    }

    public MethodsNested<T> addNewMethod() {
        return new MethodsNested<T>();
    }

    public T addToConstructors(JavaMethod item) {
        if (item != null) {
            JavaMethodBuilder builder = new JavaMethodBuilder(item);
            _visitables.add(builder);
            this.constructors.add(builder);
        }
        return (T) this;
    }

    public Set<JavaMethod> getConstructors() {
        return build(constructors);
    }

    public T withConstructors(Set<JavaMethod> constructors) {
        this.constructors.clear();
        if (constructors != null) {
            for (JavaMethod item : constructors) {
                this.addToConstructors(item);
            }
        }
        return (T) this;
    }

    public ConstructorsNested<T> addNewConstructor() {
        return new ConstructorsNested<T>();
    }

    public T addToFields(JavaProperty item) {
        if (item != null) {
            JavaPropertyBuilder builder = new JavaPropertyBuilder(item);
            _visitables.add(builder);
            this.fields.add(builder);
        }
        return (T) this;
    }

    public Set<JavaProperty> getFields() {
        return build(fields);
    }

    public T withFields(Set<JavaProperty> fields) {
        this.fields.clear();
        if (fields != null) {
            for (JavaProperty item : fields) {
                this.addToFields(item);
            }
        }
        return (T) this;
    }

    public FieldsNested<T> addNewField() {
        return new FieldsNested<T>();
    }

    public T addToImports(JavaType item) {
        if (item != null) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.imports.add(builder);
        }
        return (T) this;
    }

    public Set<JavaType> getImports() {
        return build(imports);
    }

    public T withImports(Set<JavaType> imports) {
        this.imports.clear();
        if (imports != null) {
            for (JavaType item : imports) {
                this.addToImports(item);
            }
        }
        return (T) this;
    }

    public ImportsNested<T> addNewImport() {
        return new ImportsNested<T>();
    }

    public T addToNested(JavaClazz item) {
        if (item != null) {
            JavaClazzBuilder builder = new JavaClazzBuilder(item);
            _visitables.add(builder);
            this.nested.add(builder);
        }
        return (T) this;
    }

    public Set<JavaClazz> getNested() {
        return build(nested);
    }

    public T withNested(Set<JavaClazz> nested) {
        this.nested.clear();
        if (nested != null) {
            for (JavaClazz item : nested) {
                this.addToNested(item);
            }
        }
        return (T) this;
    }

    public NestedNested<T> addNewNested() {
        return new NestedNested<T>();
    }

    public class TypeNested<N> extends JavaTypeFluent<TypeNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder = new JavaTypeBuilder(this);

        public N endType() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.withType(builder.build());
        }

    }

    public class MethodsNested<N> extends JavaMethodFluent<MethodsNested<N>> implements Nested<N> {

        private final JavaMethodBuilder builder = new JavaMethodBuilder(this);

        public N endMethod() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToMethods(builder.build());
        }

    }

    public class ConstructorsNested<N> extends JavaMethodFluent<ConstructorsNested<N>> implements Nested<N> {

        private final JavaMethodBuilder builder = new JavaMethodBuilder(this);

        public N and() {
            return (N) JavaClazzFluent.this.addToConstructors(builder.build());
        }

        public N endConstructor() {
            return and();
        }

    }

    public class FieldsNested<N> extends JavaPropertyFluent<FieldsNested<N>> implements Nested<N> {

        private final JavaPropertyBuilder builder = new JavaPropertyBuilder(this);

        public N and() {
            return (N) JavaClazzFluent.this.addToFields(builder.build());
        }

        public N endField() {
            return and();
        }

    }

    public class ImportsNested<N> extends JavaTypeFluent<ImportsNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder = new JavaTypeBuilder(this);

        public N and() {
            return (N) JavaClazzFluent.this.addToImports(builder.build());
        }

        public N endImport() {
            return and();
        }

    }

    public class NestedNested<N> extends JavaClazzFluent<NestedNested<N>> implements Nested<N> {

        private final JavaClazzBuilder builder = new JavaClazzBuilder(this);

        public N endNested() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToNested(builder.build());
        }

    }


}
