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

package io.sundr.dsl.internal.processor;

import io.sundr.Function;
import io.sundr.codegen.coverters.JavaClazzFunction;
import io.sundr.codegen.coverters.JavaMethodFunction;
import io.sundr.codegen.coverters.JavaPropertyFunction;
import io.sundr.codegen.coverters.JavaTypeFunction;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.functions.ToTransitionAnnotations;
import io.sundr.dsl.internal.functions.ToTransitionClassName;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslProcessorContext {

    private final Elements elements;
    private final Types types;
    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final JavaMethodFunction toMethod;
    private final JavaClazzFunction toClazz;
    private final DependencyManager dependencyManager;
    private final ToTransitionAnnotations toTransitionAnnotations;
    private final ToTransitionClassName toTransitionClassName;

    public DslProcessorContext(Elements elements, Types types) {
        this.types = types;
        this.elements = elements;
        this.toType = new JavaTypeFunction(elements, true);
        this.toProperty = new JavaPropertyFunction(toType);
        this.toMethod = new JavaMethodFunction(toType, toProperty);
        this.toClazz = new JavaClazzFunction(toType, toMethod, toProperty);
        this.dependencyManager = new DependencyManager(elements);
        this.toTransitionAnnotations = new ToTransitionAnnotations(elements);
        toTransitionClassName = new ToTransitionClassName(elements);
    }

    public Types getTypes() {
        return types;
    }

    public Elements getElements() {
        return elements;
    }

    public Function<String, JavaType> getToType() {
        return toType;
    }

    public Function<VariableElement, JavaProperty> getToProperty() {
        return toProperty;
    }

    public JavaMethodFunction getToMethod() {
        return toMethod;
    }

    public JavaClazzFunction getToClazz() {
        return toClazz;
    }

    public DependencyManager getDependencyManager() {
        return dependencyManager;
    }

    public ToTransitionAnnotations getToTransitionAnnotations() {
        return toTransitionAnnotations;
    }

    public ToTransitionClassName getToTransitionClassName() {
        return toTransitionClassName;
    }
}
