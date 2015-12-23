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
import io.sundr.codegen.converters.ExecutableElementToJavaMethod;
import io.sundr.codegen.converters.VariableElementToJavaProperty;
import io.sundr.codegen.converters.StringToJavaType;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.element.functions.ToAllAnnotations;
import io.sundr.dsl.internal.element.functions.ToNoneAnnotations;
import io.sundr.dsl.internal.element.functions.ToKeywordAnnotations;
import io.sundr.dsl.internal.element.functions.ToKeywordClassName;
import io.sundr.dsl.internal.element.functions.ToAnyAnnotations;
import io.sundr.dsl.internal.element.functions.ToTransitionClassName;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslProcessorContext {

    private final Elements elements;
    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final ExecutableElementToJavaMethod toMethod;
    private final ToAnyAnnotations toAnyAnnotations;
    private final ToAllAnnotations toAllAnnotations;
    private final ToNoneAnnotations toNoneAnnotations;
    private final ToKeywordAnnotations toKeywordAnnotations;
    private final ToTransitionClassName toTransitionClassName;
    private final ToKeywordClassName toKeywordClassName;

    public DslProcessorContext(Elements elements, Types types) {
        this.elements = elements;
        this.toType = new StringToJavaType(elements);
        this.toProperty = new VariableElementToJavaProperty(toType);
        this.toMethod = new ExecutableElementToJavaMethod(toType, toProperty);
        this.toAnyAnnotations = new ToAnyAnnotations(elements);
        this.toAllAnnotations = new ToAllAnnotations(elements);
        this.toNoneAnnotations = new ToNoneAnnotations(elements);
        toTransitionClassName = new ToTransitionClassName(elements);
        toKeywordAnnotations = new ToKeywordAnnotations(elements);
        toKeywordClassName = new ToKeywordClassName();
    }

    public Elements getElements() {
        return elements;
    }

    public Function<String, JavaType> getToType() {
        return toType;
    }

    public ExecutableElementToJavaMethod getToMethod() {
        return toMethod;
    }

    public ToAnyAnnotations getToAnyAnnotations() {
        return toAnyAnnotations;
    }

    public ToAllAnnotations getToAllAnnotations() {
        return toAllAnnotations;
    }

    public ToNoneAnnotations getToNoneAnnotations() {
        return toNoneAnnotations;
    }

    public ToTransitionClassName getToTransitionClassName() {
        return toTransitionClassName;
    }

    public ToKeywordAnnotations getToKeywordAnnotations() {
        return toKeywordAnnotations;
    }

    public ToKeywordClassName getToKeywordClassName() {
        return toKeywordClassName;
    }
}
