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

package io.sundr.codegen.coverters;

import io.sundr.Function;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.sundr.codegen.utils.ModelUtils.getFullyQualifiedName;
import static io.sundr.codegen.utils.ModelUtils.splitTypes;

public class JavaTypeFunction implements Function<String, JavaType> {

    private final Elements elements;
    private final boolean deep;
    private final JavaTypeFunction shallow;

    public JavaTypeFunction(Elements elements, boolean deep) {
        this.elements = elements;
        this.deep = deep;
        this.shallow = deep ? new JavaTypeFunction(elements, false) : this;
    }

    @Override
    public JavaType apply(String fullName) {
        boolean isArray = false;
        String packageName = null;
        String className = null;
        List<JavaType> interfaces = new ArrayList<>();
        List<JavaType> genericTypes = new ArrayList<>();

        TypeElement typeElement = elements.getTypeElement(getFullyQualifiedName(fullName));
        if (typeElement == null) {
            className = fullName;
        } else {
            for (TypeMirror interfaceType : typeElement.getInterfaces()) {
                if (deep) {
                    interfaces.add(shallow.apply(interfaceType.toString()));
                }
            }

            packageName = elements.getPackageOf(typeElement).toString();
            className = fullName.contains(packageName) ? fullName.substring(packageName.length() + 1) : fullName;
        }

        if (fullName.endsWith("[]")) {
            className = className.substring(0, className.indexOf('['));
            isArray = true;
        }
        if (className.contains("<")) {
            String genericTypeList = fullName.substring(fullName.indexOf('<') + 1, fullName.lastIndexOf('>'));
            for (String genericType : splitTypes(genericTypeList)) {
                JavaType t = apply(genericType);
                genericTypes.add(t);
            }
            className = className.substring(0, className.indexOf('<'));
        }
        JavaType defaultImplementation = null;
        String qualifiedName = packageName + "." + className;
        boolean collection = false;
        if (qualifiedName.equals(Set.class.getCanonicalName())) {
            defaultImplementation = apply(LinkedHashSet.class.getCanonicalName());
            collection = true;
        } else if (qualifiedName.equals(List.class.getCanonicalName())) {
            defaultImplementation = apply(ArrayList.class.getCanonicalName());
            collection = true;
        } else if (qualifiedName.equals(Map.class.getCanonicalName())) {
            defaultImplementation = apply(HashMap.class.getCanonicalName());
            collection = true;
        }

        JavaType type = new JavaTypeBuilder()
                .withKind(JavaKind.CLASS)
                .withPackageName(packageName)
                .withClassName(className)
                .withArray(isArray)
                .withConcrete(defaultImplementation == null)
                .withCollection(collection)
                .withDefaultImplementation(defaultImplementation)
                .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()])).build();
        return type;
    }
}
