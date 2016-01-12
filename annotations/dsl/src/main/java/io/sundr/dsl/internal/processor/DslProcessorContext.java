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
import io.sundr.codegen.converters.StringToJavaType;
import io.sundr.codegen.converters.VariableElementToJavaProperty;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.element.functions.ToKeywords;
import io.sundr.dsl.internal.element.functions.ToRequiresAll;
import io.sundr.dsl.internal.element.functions.ToRequiresAny;
import io.sundr.dsl.internal.element.functions.ToRequiresNoneOf;
import io.sundr.dsl.internal.element.functions.ToRequiresOnly;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslProcessorContext {

    private final Elements elements;
    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final ExecutableElementToJavaMethod toMethod;
    private final ToRequiresAny toRequiresAny;
    private final ToRequiresAll toRequiresAll;
    private final ToRequiresNoneOf toRequiresNoneOf;
    private final ToRequiresOnly toRequiresOnly;
    private final ToKeywords toKeywords;

    public DslProcessorContext(Elements elements, Types types) {
        this.elements = elements;
        this.toType = new StringToJavaType(elements);
        this.toProperty = new VariableElementToJavaProperty(toType);
        this.toMethod = new ExecutableElementToJavaMethod(toType, toProperty);
        this.toRequiresAny = new ToRequiresAny(elements);
        this.toRequiresAll = new ToRequiresAll(elements);
        this.toRequiresNoneOf = new ToRequiresNoneOf(elements);
        this.toRequiresOnly = new ToRequiresOnly(elements);
        this.toKeywords = new ToKeywords(elements);
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

    public ToRequiresAny getToRequiresAny() {
        return toRequiresAny;
    }

    public ToRequiresAll getToRequiresAll() {
        return toRequiresAll;
    }

    public ToRequiresNoneOf getToRequiresNoneOf() {
        return toRequiresNoneOf;
    }

    public ToRequiresOnly getToRequiresOnly() {
        return toRequiresOnly;
    }

    public ToKeywords getToKeywords() {
        return toKeywords;
    }
}
