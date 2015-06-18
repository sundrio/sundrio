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

package io.sundr.builder.internal.functions.overrides;

import io.sundr.Function;
import io.sundr.codegen.coverters.JavaPropertyFunction;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;

import javax.lang.model.element.VariableElement;
import java.util.HashMap;
import java.util.Map;

public class ToBuildableJavaProperty implements Function<VariableElement, JavaProperty> {
    
    private static final String BUILDABLE = "BUILDABLE";
    private Function<VariableElement, JavaProperty> delegate;
    
    public ToBuildableJavaProperty(Function<String, JavaType> toType) {
        this.delegate = new JavaPropertyFunction(toType);
    }

    @Override
    public JavaProperty apply(VariableElement variableElement) {
        JavaProperty property = delegate.apply(variableElement);
        boolean isBuildable = property.getType().getAttributes().containsKey(BUILDABLE) ?
                (Boolean) property.getType().getAttributes().get(BUILDABLE) : false;
        Map<String, Object> attributes = new HashMap<String, Object>(property.getAttributes());
        attributes.put(BUILDABLE, isBuildable);
        return new JavaPropertyBuilder(property).withAttributes(attributes).build();
    }
}
