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

package io.sundr.builder.internal.functions;

import io.sundr.Function;
import io.sundr.codegen.model.JavaClazz;
import io.sundr.codegen.model.JavaClazzBuilder;
import io.sundr.codegen.model.JavaMethod;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaPropertyBuilder;
import io.sundr.codegen.model.JavaType;

import java.util.HashSet;
import java.util.Set;

public enum PropertyToClazz implements Function<JavaProperty, JavaClazz> {

    NESTED;

    @Override
    public JavaClazz apply(JavaProperty item) {
        JavaType nestedType = PropretyToType.NESTED.apply(item);
        Set<JavaMethod> nestedMethods = new HashSet<>();
        nestedMethods.add(ProtpertyToMethod.AND.apply(item));
        nestedMethods.add(ProtpertyToMethod.END.apply(item));

        Set<JavaProperty> properties = new HashSet<>();

        properties.add(new JavaPropertyBuilder()
                .withName("builder")
                .withType(TypeAs.SHALLOW_BUILDER.apply(TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType()))).build());

        return new JavaClazzBuilder()
                .withType(nestedType)
                .withFields(properties)
                .withMethods(nestedMethods)
                .build();
    }
}
