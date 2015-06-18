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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaMethodFluent<T extends JavaMethodFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    Set<Modifier> modifiers = new LinkedHashSet();
    Set<JavaTypeBuilder> typeParameters = new LinkedHashSet();
    String name;
    JavaTypeBuilder returnType;
    List<JavaPropertyBuilder> arguments = new ArrayList();
    Set<JavaTypeBuilder> exceptions = new LinkedHashSet();

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

    public T addToTypeParameters(JavaType item) {
        if (item != null) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.typeParameters.add(builder);
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

    public TypeParametersNested<T> addNewTypeParameter() {
        return new TypeParametersNested<T>();
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

    public T withArguments(JavaProperty[] arguments) {
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
        for (JavaPropertyBuilder builder : arguments) {
            result.add(builder.build());
        }
        return result.toArray(new JavaProperty[result.size()]);
    }

    public T addToArguments(JavaProperty item) {
        if (item != null) {
            JavaPropertyBuilder builder = new JavaPropertyBuilder(item);
            _visitables.add(builder);
            this.arguments.add(builder);
        }
        return (T) this;
    }

    public ArgumentsNested<T> addNewArgument() {
        return new ArgumentsNested<T>();
    }

    public T addToExceptions(JavaType item) {
        if (item != null) {
            JavaTypeBuilder builder = new JavaTypeBuilder(item);
            _visitables.add(builder);
            this.exceptions.add(builder);
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

    public ExceptionsNested<T> addNewException() {
        return new ExceptionsNested<T>();
    }

    public class TypeParametersNested<N> extends JavaTypeFluent<TypeParametersNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder = new JavaTypeBuilder(this);

        public N endTypeParameter() {
            return and();
        }

        public N and() {
            return (N) JavaMethodFluent.this.addToTypeParameters(builder.build());
        }

    }

    public class ReturnTypeNested<N> extends JavaTypeFluent<ReturnTypeNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder = new JavaTypeBuilder(this);

        public N and() {
            return (N) JavaMethodFluent.this.withReturnType(builder.build());
        }

        public N endReturnType() {
            return and();
        }

    }

    public class ArgumentsNested<N> extends JavaPropertyFluent<ArgumentsNested<N>> implements Nested<N> {

        private final JavaPropertyBuilder builder = new JavaPropertyBuilder(this);

        public N endArgument() {
            return and();
        }

        public N and() {
            return (N) JavaMethodFluent.this.addToArguments(builder.build());
        }

    }

    public class ExceptionsNested<N> extends JavaTypeFluent<ExceptionsNested<N>> implements Nested<N> {

        private final JavaTypeBuilder builder = new JavaTypeBuilder(this);

        public N endException() {
            return and();
        }

        public N and() {
            return (N) JavaMethodFluent.this.addToExceptions(builder.build());
        }

    }


}
