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

package io.sundr.codegen.converters.parser;

import com.github.javaparser.ast.TypeParameter;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import io.sundr.Function;
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class DeclarationToJavaClazz implements Function<ClassOrInterfaceDeclaration, JavaType> {

    private final ClassOrInterfaceTypeToJavaType classOrInterfaceTypeToJavaType = new ClassOrInterfaceTypeToJavaType();

    public JavaType apply(ClassOrInterfaceDeclaration item) {
        JavaType superClassType = null;
        List<JavaType> implementsTypes = new ArrayList<JavaType>();
        List<JavaType> genericTypes = new ArrayList<JavaType>();

        for (ClassOrInterfaceType type : item.getImplements()) {
            if (item.isInterface()) {
                implementsTypes.add(classOrInterfaceTypeToJavaType.apply(type));
            } else if (superClassType == null) {
                superClassType = classOrInterfaceTypeToJavaType.apply(type);
            } else {
                throw new IllegalStateException("Multiple extends found and type is not an interface");
            }
        }

        for (ClassOrInterfaceType type : item.getImplements()) {
            implementsTypes.add(classOrInterfaceTypeToJavaType.apply(type));
        }

        for (TypeParameter type : item.getTypeParameters()) {

        }


        return new JavaTypeBuilder()
                .withPackageName("changeme")
                .withClassName(item.getName())
                .withKind(item.isInterface() ? JavaKind.INTERFACE : JavaKind.CLASS)
                .withConcrete(Modifier.isAbstract(item.getModifiers()))
                .withSuperClass(superClassType)
                .withInterfaces(implementsTypes.toArray(new JavaType[implementsTypes.size()]))
                .build();
    }
}
