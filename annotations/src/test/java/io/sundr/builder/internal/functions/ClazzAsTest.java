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
import io.sundr.codegen.model.JavaKind;
import io.sundr.codegen.model.JavaType;
import org.junit.Test;

import static io.sundr.codegen.utils.StringUtils.join;

public class ClazzAsTest {

    @Test
    public void testToFluent() {

        JavaClazz clazz = new JavaClazzBuilder()
                .addType()
                    .withClassName("MyClass")
                    .withPackageName(getClass().getPackage().getName())
                .endType().build();
        
        JavaClazz result = ClazzAs.FLUENT.apply(clazz);
        System.out.println(JavaTypeToString.INSTANCE.apply(result.getType()));
    }

    //Enum Singleton
    private enum JavaTypeToString implements Function<JavaType, String> {
        INSTANCE;
        @Override
        public String apply(JavaType item) {
            StringBuilder sb = new StringBuilder();
            sb.append(item.getClassName());
            if (item.getKind() == JavaKind.GENERIC && item.getSuperClass() != null) {
                sb.append(" extends " + apply(item.getSuperClass()));
            }
            if (item.getGenericTypes() != null && item.getGenericTypes().length > 0) {
                sb.append("<").append(join(item.getGenericTypes(), JavaTypeToString.INSTANCE, ",")).append(">");
            }
            return sb.toString();
        }
    }
}