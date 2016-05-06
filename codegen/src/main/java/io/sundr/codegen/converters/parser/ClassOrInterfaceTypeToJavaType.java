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
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.Type;
import io.sundr.Function;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.ArrayList;
import java.util.List;

public class ClassOrInterfaceTypeToJavaType implements Function<ClassOrInterfaceType, JavaType> {


    private Function<TypeParameter, JavaType> typeParamterToJavaType;

    public JavaType apply(ClassOrInterfaceType item) {
        List<JavaType> types = new ArrayList<JavaType>();

        for (Type type : item.getTypeArgs()) {

        }

        return new JavaTypeBuilder()
                .withPackageName("changeme")
                .withClassName(item.getName())
                .build();
    }
}
