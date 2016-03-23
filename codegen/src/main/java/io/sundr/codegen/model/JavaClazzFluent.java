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

import java.util.LinkedHashSet;
import java.util.Set;

public class JavaClazzFluent<T extends JavaClazzFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    VisitableBuilder<JavaType, ?> type;
    Set<VisitableBuilder<JavaType, ?>> annotations = new LinkedHashSet();
    Set<VisitableBuilder<JavaMethod, ?>> methods = new LinkedHashSet();
    Set<VisitableBuilder<JavaMethod, ?>> constructors = new LinkedHashSet();
    Set<VisitableBuilder<JavaProperty, ?>> fields = new LinkedHashSet();
    Set<VisitableBuilder<JavaType, ?>> imports = new LinkedHashSet();
    Set<VisitableBuilder<JavaClazz, ?>> nested = new LinkedHashSet();

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

    public T addToMethods(JavaMethod... items) {
        for (JavaMethod item : items) {
            JavaMethodBuilder builder = new JavaMethodBuilder(item);
            _visitables.add(builder);
            this.methods.add(builder);
        }
        return (T) this;
    }

    public T removeFromMethods(JavaMethod... items) {
        for (JavaMethod item : items) {
            JavaMethodBuilder builder = new JavaMethodBuilder(item);
            _visitables.remove(builder);
            this.methods.remove(builder);
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

    public T withMethods(JavaMethod... methods) {
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

    public MethodsNested<T> addNewMethodLike(JavaMethod item) {
        return new MethodsNested<T>(item);
    }

    public T addToConstructors(JavaMethod... items) {
        for (JavaMethod item : items) {
            JavaMethodBuilder builder = new JavaMethodBuilder(item);
            _visitables.add(builder);
            this.constructors.add(builder);
        }
        return (T) this;
    }

    public T removeFromConstructors(JavaMethod... items) {
        for (JavaMethod item : items) {
            JavaMethodBuilder builder = new JavaMethodBuilder(item);
            _visitables.remove(builder);
            this.constructors.remove(builder);
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

    public T withConstructors(JavaMethod... constructors) {
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

    public ConstructorsNested<T> addNewConstructorLike(JavaMethod item) {
        return new ConstructorsNested<T>(item);
    }

    public T addToFields(JavaProperty... items) {
        for (JavaProperty item : items) {
            JavaPropertyBuilder builder = new JavaPropertyBuilder(item);
            _visitables.add(builder);
            this.fields.add(builder);
        }
        return (T) this;
    }

    public T removeFromFields(JavaProperty... items) {
        for (JavaProperty item : items) {
            JavaPropertyBuilder builder = new JavaPropertyBuilder(item);
            _visitables.remove(builder);
            this.fields.remove(builder);
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

    public T withFields(JavaProperty... fields) {
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

    public FieldsNested<T> addNewFieldLike(JavaProperty item) {
        return new FieldsNested<T>(item);
    }

    public T addToImports(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.imports.add(builder);
        }
        return (T) this;
    }

    public T removeFromImports(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.remove(builder);
            this.imports.remove(builder);
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

    public T withImports(JavaType... imports) {
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

    public ImportsNested<T> addNewImportLike(JavaType item) {
        return new ImportsNested<T>(item);
    }

    public T addToNested(JavaClazz... items) {
        for (JavaClazz item : items) {
            JavaClazzBuilder builder = new JavaClazzBuilder(item);
            _visitables.add(builder);
            this.nested.add(builder);
        }
        return (T) this;
    }

    public T removeFromNested(JavaClazz... items) {
        for (JavaClazz item : items) {
            JavaClazzBuilder builder = new JavaClazzBuilder(item);
            _visitables.remove(builder);
            this.nested.remove(builder);
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

    public T withNested(JavaClazz... nested) {
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

    public NestedNested<T> addNewNestedLike(JavaClazz item) {
        return new NestedNested<T>(item);
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JavaClazzFluent that = (JavaClazzFluent) o;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null) return false;
        if (methods != null ? !methods.equals(that.methods) : that.methods != null) return false;
        if (constructors != null ? !constructors.equals(that.constructors) : that.constructors != null) return false;
        if (fields != null ? !fields.equals(that.fields) : that.fields != null) return false;
        if (imports != null ? !imports.equals(that.imports) : that.imports != null) return false;
        if (nested != null ? !nested.equals(that.nested) : that.nested != null) return false;
        return true;

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
            return (N) JavaClazzFluent.this.withType(builder.build());
        }

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
            return (N) JavaClazzFluent.this.addToAnnotations(builder.build());
        }

    }

    public class MethodsNested<N> extends JavaMethodFluent<MethodsNested<N>> implements Nested<N> {

        private final JavaMethodBuilder builder;

        MethodsNested() {
            this.builder = new JavaMethodBuilder(this);
        }

        MethodsNested(JavaMethod item) {
            this.builder = new JavaMethodBuilder(this, item);
        }

        public N endMethod() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToMethods(builder.build());
        }

    }

    public class ConstructorsNested<N> extends JavaMethodFluent<ConstructorsNested<N>> implements Nested<N> {

        private final JavaMethodBuilder builder;

        ConstructorsNested() {
            this.builder = new JavaMethodBuilder(this);
        }

        ConstructorsNested(JavaMethod item) {
            this.builder = new JavaMethodBuilder(this, item);
        }

        public N endConstructor() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToConstructors(builder.build());
        }

    }

    public class FieldsNested<N> extends JavaPropertyFluent<FieldsNested<N>> implements Nested<N> {

        private final JavaPropertyBuilder builder;

        FieldsNested() {
            this.builder = new JavaPropertyBuilder(this);
        }

        FieldsNested(JavaProperty item) {
            this.builder = new JavaPropertyBuilder(this, item);
        }

        public N endField() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToFields(builder.build());
        }

    }

    public class ImportsNested<N> extends JavaTypeFluent<ImportsNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        ImportsNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        ImportsNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endImport() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToImports(builder.build());
        }

    }

    public class NestedNested<N> extends JavaClazzFluent<NestedNested<N>> implements Nested<N> {

        private final JavaClazzBuilder builder;

        NestedNested() {
            this.builder = new JavaClazzBuilder(this);
        }

        NestedNested(JavaClazz item) {
            this.builder = new JavaClazzBuilder(this, item);
        }

        public N endNested() {
            return and();
        }

        public N and() {
            return (N) JavaClazzFluent.this.addToNested(builder.build());
        }

    }


}
