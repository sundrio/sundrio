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

package io.sundr.codegen.converters;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.ModelUtils;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;
import javax.lang.model.util.Elements;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.codegen.utils.ModelUtils.getClassName;
import static io.sundr.codegen.utils.ModelUtils.getPackageName;

public class TypeElementToJavaClazz implements Function<TypeElement, JavaClazz> {

    private final Elements elements;
    private final Function<String, JavaType> toJavaType;
    private final Function<ExecutableElement, JavaMethod> toJavaMethod;
    private final Function<VariableElement, JavaProperty> toJavaProperty;

    public TypeElementToJavaClazz(Elements elements, Function<String, JavaType> toJavaType, Function<ExecutableElement, JavaMethod> toJavaMethod, Function<VariableElement, JavaProperty> toJavaProperty) {
        this.elements = elements;
        this.toJavaType = toJavaType;
        this.toJavaMethod = toJavaMethod;
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaClazz apply(TypeElement classElement) {
        //Check SuperClass
        JavaKind kind = JavaKind.CLASS;
        boolean concrete = false;
        TypeMirror superClass = classElement.getSuperclass();
        JavaType superClassType = superClass != null && !ModelUtils.NONE.equals(superClass) ? toJavaType.apply(superClass.toString()) : null;
        List<JavaType> genericTypes = new ArrayList<JavaType>();
        List<JavaType> interfaces = new ArrayList<JavaType>();

        if (classElement.getKind() == ElementKind.INTERFACE) {
            kind = JavaKind.INTERFACE;
            concrete = false;
        } else if (classElement.getKind() == ElementKind.CLASS) {
            kind = JavaKind.CLASS;
            concrete = !classElement.getModifiers().contains(Modifier.ABSTRACT);
        }

        for (TypeMirror interfaceTypeMirrror : classElement.getInterfaces()) {
            JavaType interfaceType = toJavaType.apply(interfaceTypeMirrror.toString());
            interfaces.add(interfaceType);
        }

        for (TypeParameterElement typeParameter : classElement.getTypeParameters()) {

            JavaType genericBound;
            if (!typeParameter.getBounds().isEmpty()) {
                TypeMirror bound = typeParameter.getBounds().get(0);
                genericBound = toJavaType.apply(bound.toString());
            } else {
                genericBound = null;
            }

            JavaType genericType = new JavaTypeBuilder(toJavaType.apply(typeParameter.toString())).withSuperClass(genericBound).withKind(JavaKind.GENERIC).build();
            genericTypes.add(genericType);
        }

        JavaClazzBuilder builder = new JavaClazzBuilder()
                .withType(new JavaTypeBuilder()
                        .withKind(kind)
                        .withPackageName(getPackageName(classElement))
                        .withClassName(getClassName(classElement))
                        .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()]))
                        .withSuperClass(superClassType)
                        .withInterfaces(interfaces.toArray(new JavaType[interfaces.size()]))
                        .withConcrete(concrete)
                        .build());

        for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
            builder.addToConstructors(toJavaMethod.apply(constructor));
        }

        //Populate Fields
        for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            builder.addToFields(toJavaProperty.apply(variableElement));
        }

        Set<ExecutableElement> allMethods = new LinkedHashSet<ExecutableElement>();
        allMethods.addAll(ElementFilter.methodsIn(classElement.getEnclosedElements()));
        allMethods.addAll(getInheritedMethods(classElement));

        for (ExecutableElement method : allMethods) {
            builder.addToMethods(toJavaMethod.apply(method));
        }

        for (AnnotationMirror annotationMirror : classElement.getAnnotationMirrors()) {
            JavaType annotationType = toJavaType.apply(annotationMirror.getAnnotationType().toString());
            builder.addToAnnotations(annotationType);
        }
        return builder.build();
    }

    public Set<ExecutableElement> getInheritedMethods(TypeElement typeElement) {
        Set<ExecutableElement> result = new LinkedHashSet<ExecutableElement>();
        if (typeElement != null) {
            for (ExecutableElement method : ElementFilter.methodsIn(typeElement.getEnclosedElements())) {
                if (!method.getModifiers().contains(Modifier.PRIVATE)) {
                    result.add(method);
                }
            }
            result.addAll(getInheritedMethods(typeElement.getSuperclass() != null ?
                    elements.getTypeElement(typeElement.getSuperclass().toString()) : null));

        }

        return result;
    }
}
