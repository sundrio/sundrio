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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class JavaMethodFluent<T extends JavaMethodFluent<T>> extends AttributeSupportFluent<T> implements Fluent<T> {

    private String name;
    private JavaType returnType;
    private List<JavaProperty> arguments = new ArrayList();
    private Set<JavaType> exceptions = new LinkedHashSet();

    public String getName() {
        return this.name;
    }

    public T withName(String name) {
        this.name = name;
        return (T) this;
    }

    public JavaType getReturnType() {
        return this.returnType;
    }

    public T withReturnType(JavaType returnType) {
        this.returnType = returnType;
        return (T) this;
    }

    public T withArguments(JavaProperty[] arguments) {
        this.arguments.clear();
        for (JavaProperty item : arguments) {
            this.arguments.add(item);
        }
        return (T) this;
    }

    public JavaProperty[] getArguments() {
        return this.arguments.toArray(new JavaProperty[arguments.size()]);
    }

    public Set<JavaType> getExceptions() {
        return this.exceptions;
    }

    public T withExceptions(Set<JavaType> exceptions) {
        this.exceptions.clear();
        this.exceptions.addAll(exceptions);
        return (T) this;
    }

    public ReturnTypeNested<T> withNewReturnType() {
        return new ReturnTypeNested<T>();
    }

    public T addToArguments(JavaProperty item) {
        this.arguments.add(item);
        return (T) this;
    }

    public ArgumentsNested<T> addNewArgument() {
        return new ArgumentsNested<T>();
    }

    public T addToExceptions(JavaType item) {
        this.exceptions.add(item);
        return (T) this;
    }

    public ExceptionsNested<T> addNewException() {
        return new ExceptionsNested<T>();
    }

    public class ReturnTypeNested<N> extends JavaTypeFluent<ReturnTypeNested<N>> implements Nested<N> {

        public N and() {
            return (N) withReturnType(new JavaTypeBuilder(this).build());
        }

        public N endReturnType() {
            return and();
        }

    }

    public class ArgumentsNested<N> extends JavaPropertyFluent<ArgumentsNested<N>> implements Nested<N> {

        public N and() {
            return (N) addToArguments(new JavaPropertyBuilder(this).build());
        }

        public N endArgument() {
            return and();
        }

    }

    public class ExceptionsNested<N> extends JavaTypeFluent<ExceptionsNested<N>> implements Nested<N> {

        public N endException() {
            return and();
        }

        public N and() {
            return (N) addToExceptions(new JavaTypeBuilder(this).build());
        }

    }


}
