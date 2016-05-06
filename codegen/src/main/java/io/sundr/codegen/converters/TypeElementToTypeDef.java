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
import io.sundr.codegen.model.ClassRef;
import io.sundr.codegen.model.Kind;
import io.sundr.codegen.model.Method;
import io.sundr.codegen.model.Property;
import io.sundr.codegen.model.TypeDef;
import io.sundr.codegen.model.TypeDefBuilder;
import io.sundr.codegen.model.TypeParamDef;
import io.sundr.codegen.model.TypeParamDefBuilder;
import io.sundr.codegen.model.TypeRef;

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
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static io.sundr.codegen.utils.ModelUtils.getClassName;
import static io.sundr.codegen.utils.ModelUtils.getPackageName;

public class TypeElementToTypeDef implements Function<TypeElement, TypeDef> {

    private static final String OBJECT_BOUND = "java.lang.Object";
    private final Elements elements;
    private final Function<TypeMirror, TypeRef> toTypeRef;
    private final Function<ExecutableElement, Method> toJavaMethod;
    private final Function<VariableElement, Property> toJavaProperty;

    public TypeElementToTypeDef(Elements elements, Function<TypeMirror, TypeRef> toTypeRef, Function<ExecutableElement, Method> toJavaMethod, Function<VariableElement, Property> toJavaProperty) {
        this.elements = elements;
        this.toTypeRef = toTypeRef;
        this.toJavaMethod = toJavaMethod;
        this.toJavaProperty = toJavaProperty;
    }

    public TypeDef apply(TypeElement classElement) {
        //Check SuperClass
        Kind kind = Kind.CLASS;

        TypeMirror superClass = classElement.getSuperclass();
        TypeRef superClassType = toTypeRef.apply(superClass);

        List<TypeParamDef> genericTypes = new ArrayList<TypeParamDef>();
        List<ClassRef> interfaces = new ArrayList<ClassRef>();

        if (classElement.getKind() == ElementKind.INTERFACE) {
            kind = Kind.INTERFACE;
        } else if (classElement.getKind() == ElementKind.CLASS) {
            kind = Kind.CLASS;
        }

        for (TypeMirror interfaceTypeMirrror : classElement.getInterfaces()) {
            TypeRef interfaceType = toTypeRef.apply(interfaceTypeMirrror);
            if (interfaceType instanceof ClassRef) {
                interfaces.add((ClassRef) interfaceType);
            } else {
                throw new IllegalStateException("Interface: [" + interfaceType + "] not mapped to a class ref.");
            }
        }

        for (TypeParameterElement typeParameter : classElement.getTypeParameters()) {
            List<ClassRef> genericBounds = new ArrayList<ClassRef>();
            if (!typeParameter.getBounds().isEmpty()) {
                TypeMirror bound = typeParameter.getBounds().get(0);
                if (!OBJECT_BOUND.equals(bound.toString())) {
                    TypeRef boundRef = toTypeRef.apply(bound);
                    if (boundRef instanceof ClassRef) {
                        genericBounds.add((ClassRef) boundRef);
                    } else {
                        throw new IllegalStateException("Parameter bound: [" + boundRef + "] not mapped to a class ref.");
                    }
                }
            }

            TypeParamDef genericType = new TypeParamDefBuilder().withName(typeParameter.getSimpleName().toString())
                    .withBounds(genericBounds.toArray(new ClassRef[genericBounds.size()]))
                    .build();

            genericTypes.add(genericType);
        }

        TypeDefBuilder builder = new TypeDefBuilder()
                        .withKind(kind)
                        .withPackageName(getPackageName(classElement))
                        .withName(getClassName(classElement))
                        .withParameters(genericTypes.toArray(new TypeParamDef[genericTypes.size()]))
                        .withExtendsList(superClassType instanceof ClassRef ? (ClassRef) superClassType : null)
                        .withImplementsList(interfaces.toArray(new ClassRef[interfaces.size()]));


        for (ExecutableElement constructor : ElementFilter.constructorsIn(classElement.getEnclosedElements())) {
            builder.addToConstructors(toJavaMethod.apply(constructor));
        }

        //Populate Fields
        for (VariableElement variableElement : ElementFilter.fieldsIn(classElement.getEnclosedElements())) {
            builder.addToProperties(toJavaProperty.apply(variableElement));
        }

        Set<ExecutableElement> allMethods = new LinkedHashSet<ExecutableElement>();
        allMethods.addAll(ElementFilter.methodsIn(classElement.getEnclosedElements()));
        allMethods.addAll(getInheritedMethods(classElement));

        for (ExecutableElement method : allMethods) {
            builder.addToMethods(toJavaMethod.apply(method));
        }

        for (AnnotationMirror annotationMirror : classElement.getAnnotationMirrors()) {
            TypeRef annotationType = toTypeRef.apply(annotationMirror.getAnnotationType());
            if (annotationType instanceof ClassRef) {
                builder.addToAnnotations((ClassRef) annotationType);
            } else {
                throw new IllegalStateException("Annotation type: [" + annotationType + "] not mapped to a class ref.");
            }
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
