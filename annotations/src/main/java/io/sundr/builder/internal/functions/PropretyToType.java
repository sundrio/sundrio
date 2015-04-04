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
import io.sundr.builder.Nested;
import io.sundr.codegen.functions.ClassToJavaType;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.codegen.model.JavaTypeBuilder;

import java.util.Arrays;
import java.util.HashSet;

import static io.sundr.codegen.utils.TypeUtils.newGeneric;
import static io.sundr.codegen.utils.TypeUtils.typeGenericOf;
import static io.sundr.codegen.utils.StringUtils.captializeFirst;

public enum PropretyToType implements Function<JavaProperty, JavaType> {

    NESTED {
        @Override
        public JavaType apply(JavaProperty item) {
            JavaType nested = SHALLOW_NESTED.apply(item);
            //Not a typical fluent
            JavaType fluent = TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType());
            JavaType superClassFluent = new JavaTypeBuilder(fluent)
                    .withClassName(TypeAs.UNWRAP_COLLECTION_OF.apply(item.getType()) + "Fluent")
                    .withGenericTypes(new JavaType[]{nested})
                    .build();

            return new JavaTypeBuilder(nested)
                    .withGenericTypes(new JavaType[]{N})
                    .withSuperClass(superClassFluent)
                    .withInterfaces(new HashSet(Arrays.asList(NESTED_INTEFACE)))
                    .build();
        }

    },
    SHALLOW_NESTED {
        public JavaType apply(JavaProperty property) {
            return new JavaTypeBuilder()
                    .withClassName(captializeFirst(property.getName() + "Nested"))
                    .withGenericTypes(new JavaType[]{N})
                    .build();
        }
    };

    private static final JavaType N = newGeneric("N");
    private static final JavaType NESTED_INTEFACE = typeGenericOf(ClassToJavaType.FUNCTION.apply(Nested.class), N);

}
