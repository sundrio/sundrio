/*
 * Copyright 2016 The original authors.
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
import io.sundr.builder.annotations.Buildable;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;

import javax.lang.model.element.TypeElement;
import java.util.HashMap;
import java.util.Map;

public class ToBuildableJavaClazz implements Function<TypeElement, JavaClazz> {

    private static final String BUILDABLE = "BUILDABLE";
    private Function<TypeElement, JavaClazz> delegate;

    public ToBuildableJavaClazz(Function<TypeElement, JavaClazz> delegate) {
        this.delegate = delegate;
    }

    @Override
    public JavaClazz apply(TypeElement typeElement) {
        JavaClazz clazz = delegate.apply(typeElement);
        boolean isBuildable = typeElement.getAnnotation(Buildable.class) != null;
        Map<String, Object> attributes = new HashMap<String, Object>(clazz.getAttributes());
        attributes.put(BUILDABLE, isBuildable);
        return new JavaClazzBuilder(clazz).withAttributes(attributes).build();
    }
}
