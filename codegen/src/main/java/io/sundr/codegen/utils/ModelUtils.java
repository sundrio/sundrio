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

package io.sundr.codegen.utils;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public final class ModelUtils {

    public static final String NONE = "<none>";
    public static final String OBJECT = "java.lang.Object";

    private ModelUtils(){
        //Utility Class
    }

    public static TypeElement getClassElement(Element element) {
        if (element instanceof PackageElement) {
            throw new IllegalArgumentException("Invalid element. A package element can't be used to retrieve a class element");
        } else if (element instanceof TypeElement && element.getEnclosingElement() instanceof PackageElement) {
            return (TypeElement) element;
        } else {
            return getClassElement(element.getEnclosingElement());
        }
    }

    public static String getClassName(Element element) {
        TypeElement typeElement = getClassElement(element);
        String qualifiedName = typeElement.getQualifiedName().toString();
        String packageName = getPackageName(typeElement);
        if (packageName.isEmpty()) {
            return qualifiedName;
        }
        return  qualifiedName.substring(packageName.length() + 1);
    }

    public static PackageElement getPackageElement(Element element) {
        if (element instanceof PackageElement) {
            return (PackageElement) element;
        } else {
            return getPackageElement(element.getEnclosingElement());
        }
    }

    public static String getPackageName(Element element) {
        return getPackageElement(element).getQualifiedName().toString();
    }

    public static List<VariableElement> getFields(TypeElement element) {
        return ElementFilter.fieldsIn(element.getEnclosedElements());
    }

    static TypeElement getElementMatching(Iterable<TypeElement> typeElements, TypeMirror type) {
        for (TypeElement typeElement : typeElements) {
            if (typeElement.asType().equals(type)) {
                return typeElement;
            }
        }
        return null;
    }

    public static List<String> splitTypes(String fullName) {
        String name = fullName;//.replace(" ", "");
        List<String> result = new ArrayList<>();
        int openBrackets = 0;
        int lastIndex = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ',' && openBrackets == 0) {
                result.add(name.substring(0, i));
                lastIndex = i + 1;
            } else if (name.charAt(i) == '<') {
                openBrackets++;
            } else if (name.charAt(i) == '>') {
                openBrackets--;
            }
        }
        result.add(name.substring(lastIndex));
        return result;
    }

    public static String getFullyQualifiedName(String fullName) {
        String result = fullName.trim();
        if (result.contains("[")) {
            result = result.substring(0, result.indexOf('['));
        }
        if (result.contains("<")) {
            result = result.substring(0, result.indexOf('<'));
        }
        return result;
    }


    public static <A extends Annotation> List<ExecutableElement> findMethodsAnnotatedWith(TypeElement classElement, Class<A> annotation) {
        return filterByAnnotation(ElementFilter.methodsIn(classElement.getEnclosedElements()), annotation);
    }

    public static <A extends Annotation, E extends Element> List<E> filterByAnnotation(List<E> elements, Class<A> annotation) {
        List<E> result = new ArrayList<>();
        for (E executableElement : elements) {
            if (executableElement.getAnnotation(annotation) != null) {
                result.add(executableElement);
            }
        }
        return result;
    }
}
