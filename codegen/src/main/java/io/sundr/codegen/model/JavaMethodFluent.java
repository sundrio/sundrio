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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaMethodFluent<T extends JavaMethodFluent<T>> extends AttributeSupportFluentImpl<T> implements Fluent<T> {

    Set<VisitableBuilder<JavaType, ?>> annotations = new LinkedHashSet();
    Set<Modifier> modifiers = new LinkedHashSet();
    Set<VisitableBuilder<JavaType, ?>> typeParameters = new LinkedHashSet();
    String name;
    VisitableBuilder<JavaType, ?> returnType;
    List<VisitableBuilder<JavaProperty, ?>> arguments = new ArrayList();
    Set<VisitableBuilder<JavaType, ?>> exceptions = new LinkedHashSet();

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

    public T addToTypeParameters(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.typeParameters.add(builder);
        }
        return (T) this;
    }

    public T removeFromTypeParameters(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.remove(builder);
            this.typeParameters.remove(builder);
        }
        return (T) this;
    }

    public Set<JavaType> getTypeParameters() {
        return build(typeParameters);
    }

    public T withTypeParameters(Set<JavaType> typeParameters) {
        this.typeParameters.clear();
        if (typeParameters != null) {
            for (JavaType item : typeParameters) {
                this.addToTypeParameters(item);
            }
        }
        return (T) this;
    }

    public T withTypeParameters(JavaType... typeParameters) {
        this.typeParameters.clear();
        if (typeParameters != null) {
            for (JavaType item : typeParameters) {
                this.addToTypeParameters(item);
            }
        }
        return (T) this;
    }

    public TypeParametersNested<T> addNewTypeParameter() {
        return new TypeParametersNested<T>();
    }

    public TypeParametersNested<T> addNewTypeParameterLike(JavaType item) {
        return new TypeParametersNested<T>(item);
    }

    public String getName() {
        return this.name;
    }

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public JavaType getReturnType() {
        return this.returnType != null ? this.returnType.build() : null;
    }

    public T withReturnType(JavaType returnType) {
        if (returnType != null) {
            this.returnType = new JavaTypeBuilder(returnType);
            _visitables.add(this.returnType);
        }
        return (T) this;
    }

    public ReturnTypeNested<T> withNewReturnType() {
        return new ReturnTypeNested<T>();
    }

    public ReturnTypeNested<T> withNewReturnTypeLike(JavaType item) {
        return new ReturnTypeNested<T>(item);
    }

    public ReturnTypeNested<T> editReturnType() {
        return withNewReturnTypeLike(getReturnType());
    }

    public T withArguments(JavaProperty... arguments) {
        this.arguments.clear();
        if (arguments != null) {
            for (JavaProperty item : arguments) {
                this.addToArguments(item);
            }
        }
        return (T) this;
    }

    public JavaProperty[] getArguments() {
        List<JavaProperty> result = new ArrayList<JavaProperty>();
        for (VisitableBuilder<JavaProperty, ?> builder : arguments) {
            result.add(builder.build());
        }
        return result.toArray(new JavaProperty[result.size()]);

    }

    public T addToArguments(JavaProperty... items) {
        for (JavaProperty item : items) {
            JavaPropertyBuilder builder = new JavaPropertyBuilder(item);
            _visitables.add(builder);
            this.arguments.add(builder);
        }
        return (T) this;
    }

    public T removeFromArguments(JavaProperty... items) {
        for (JavaProperty item : items) {
            JavaPropertyBuilder builder = new JavaPropertyBuilder(item);
            _visitables.remove(builder);
            this.arguments.remove(builder);
        }
        return (T) this;
    }

    public ArgumentsNested<T> addNewArgument() {
        return new ArgumentsNested<T>();
    }

    public ArgumentsNested<T> addNewArgumentLike(JavaProperty item) {
        return new ArgumentsNested<T>(item);
    }

    public T addToExceptions(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.exceptions.add(builder);
        }
        return (T) this;
    }

    public T removeFromExceptions(JavaType... items) {
        for (JavaType item : items) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.remove(builder);
            this.exceptions.remove(builder);
        }
        return (T) this;
    }

    public Set<JavaType> getExceptions() {
        return build(exceptions);
    }

    public T withExceptions(Set<JavaType> exceptions) {
        this.exceptions.clear();
        if (exceptions != null) {
            for (JavaType item : exceptions) {
                this.addToExceptions(item);
            }
        }
        return (T) this;
    }

    public T withExceptions(JavaType... exceptions) {
        this.exceptions.clear();
        if (exceptions != null) {
            for (JavaType item : exceptions) {
                this.addToExceptions(item);
            }
        }
        return (T) this;
    }

    public ExceptionsNested<T> addNewException() {
        return new ExceptionsNested<T>();
    }

    public ExceptionsNested<T> addNewExceptionLike(JavaType item) {
        return new ExceptionsNested<T>(item);
    }

    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JavaMethodFluent that = (JavaMethodFluent) o;
        if (annotations != null ? !annotations.equals(that.annotations) : that.annotations != null) return false;
        if (modifiers != null ? !modifiers.equals(that.modifiers) : that.modifiers != null) return false;
        if (typeParameters != null ? !typeParameters.equals(that.typeParameters) : that.typeParameters != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (returnType != null ? !returnType.equals(that.returnType) : that.returnType != null) return false;
        if (arguments != null ? !arguments.equals(that.arguments) : that.arguments != null) return false;
        if (exceptions != null ? !exceptions.equals(that.exceptions) : that.exceptions != null) return false;
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
            return (N) JavaMethodFluent.this.addToAnnotations(builder.build());
        }

    }

    public class TypeParametersNested<N> extends JavaTypeFluent<TypeParametersNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        TypeParametersNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        TypeParametersNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        public N endTypeParameter() {
            return and();
        }

        public N and() {
            return (N) JavaMethodFluent.this.addToTypeParameters(builder.build());
        }

    }

    public class ReturnTypeNested<N> extends JavaTypeFluent<ReturnTypeNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        ReturnTypeNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        ReturnTypeNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N and() {
            return (N) JavaMethodFluent.this.withReturnType(builder.build());
        }

        public N endReturnType() {
            return and();
        }

    }

    public class ArgumentsNested<N> extends JavaPropertyFluent<ArgumentsNested<N>> implements Nested<N> {

        private final JavaPropertyBuilder builder;

        ArgumentsNested() {
            this.builder = new JavaPropertyBuilder(this);
        }

        ArgumentsNested(JavaProperty item) {
            this.builder = new JavaPropertyBuilder(this, item);
        }

        public N endArgument() {
            return and();
        }

        public N and() {
            return (N) JavaMethodFluent.this.addToArguments(builder.build());
        }

    }

    public class ExceptionsNested<N> extends JavaTypeFluent<ExceptionsNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder;

        ExceptionsNested() {
            this.builder = new JavaTypeBuilder(this);
        }

        ExceptionsNested(JavaType item) {
            this.builder = new JavaTypeBuilder(this, item);
        }

        public N endException() {
            return and();
        }

        public N and() {
            return (N) JavaMethodFluent.this.addToExceptions(builder.build());
        }

    }


}
