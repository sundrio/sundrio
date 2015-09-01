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
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;
import io.sundr.codegen.utils.ModelUtils;

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

public class JavaClazzFunction implements Function<TypeElement, JavaClazz> {

    private final Elements elements;
    private final Function<String, JavaType> toJavaType;
    private final Function<ExecutableElement, JavaMethod> toJavaMethod;
    private final Function<VariableElement, JavaProperty> toJavaProperty;

    public JavaClazzFunction(Elements elements, Function<String, JavaType> toJavaType, Function<ExecutableElement, JavaMethod> toJavaMethod, Function<VariableElement, JavaProperty> toJavaProperty) {
        this.elements = elements;
        this.toJavaType = toJavaType;
        this.toJavaMethod = toJavaMethod;
        this.toJavaProperty = toJavaProperty;
    }

    @Override
    public JavaClazz apply(TypeElement classElement) {
        //Check SuperClass
        TypeMirror superClass = classElement.getSuperclass();
        JavaType superClassType = superClass != null && !ModelUtils.NONE.equals(superClass) ? toJavaType.apply(superClass.toString()) : null;
        List<JavaType> genericTypes = new ArrayList<JavaType>();
        for (TypeParameterElement typeParameter : classElement.getTypeParameters()) {
            JavaType genericType = toJavaType.apply(typeParameter.toString());
            genericTypes.add(genericType);
        }
        JavaClazzBuilder builder = new JavaClazzBuilder()
                .withType(new JavaTypeBuilder()
                        .withPackageName(getPackageName(classElement))
                        .withClassName(getClassName(classElement))
                        .withGenericTypes(genericTypes.toArray(new JavaType[genericTypes.size()]))
                        .withSuperClass(superClassType)
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
