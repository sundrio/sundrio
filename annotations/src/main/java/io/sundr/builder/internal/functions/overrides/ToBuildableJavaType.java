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
import io.sundr.builder.annotations.Buildable;
import io.sundr.codegen.coverters.JavaTypeFunction;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.Map;

public class ToBuildableJavaType implements Function<String, JavaType> {

    private static final String BUILDABLE = "BUILDABLE";
    private final Elements elements;
    private final Function<String, JavaType> delegate;
    
    public ToBuildableJavaType(Elements elements) {
        this.elements = elements;
        delegate = new JavaTypeFunction(elements, true);
    }

    @Override
    public JavaType apply(String fullName) {
        JavaType type = delegate.apply(fullName);
        TypeElement typeElement = elements.getTypeElement(fullName);
        boolean isBuildable = false;
        if (fullName.endsWith("[]")) {
            typeElement  = elements.getTypeElement(fullName.substring(0, fullName.length() - 2));
            isBuildable = isBuildable(typeElement);
        } else if (type.isCollection()) {
            for (JavaType genericType : type.getGenericTypes()) {
                isBuildable = isBuildable(elements.getTypeElement(genericType.getFullyQualifiedName()));
            }
        } else {
            isBuildable = isBuildable(typeElement);
        }
        Map<String, Object> attributes = new HashMap<>(type.getAttributes());
        attributes.put(BUILDABLE, isBuildable);
        
        return new JavaTypeBuilder(type).withAttributes(attributes).build();
    }

    private boolean isBuildable(TypeElement typeElement) {
        if (typeElement != null) {
            for (Element el : typeElement.getEnclosedElements()) {
                if (el.getAnnotation(Buildable.class) != null) {
                    return true;
                }
            }
        }
        return false;
    }
}
